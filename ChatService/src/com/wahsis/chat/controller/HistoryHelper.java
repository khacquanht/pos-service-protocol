/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wahsis.chat.controller;

import com.google.gson.Gson;
import com.lambdaworks.redis.RedisFuture;
import com.wahsis.chat.data.MessageChatData;
import com.wahsis.chat.redis.WRedisClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import com.wahsis.chat.data.MessageChatResponse;

/**
 *
 * @author HaiNT
 */
public class HistoryHelper {
    
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MsgSocketController.class);
    private static final Gson _gson = new Gson();
    private static HistoryHelper _instance = null;
    private static final Lock createLock_ = new ReentrantLock();

    public static HistoryHelper getInstance() {
        if (_instance == null) {
            createLock_.lock();
            try {
                if (_instance == null) {
                    _instance = new HistoryHelper();
                }
            } finally {
                createLock_.unlock();
            }
        }
        return _instance;
    }
    
    public String writeHistoryUserVSStaff(String companyId, String userId, String staffId, MessageChatData msgData) {
    
        String msgKey = "";
        String historyKey = "";
        String idKey = "";
        long id = 0;
        
        historyKey = companyId + ":" + "c:" + userId + ":ids";
        idKey = companyId + ":" + "c:" + userId + ":id";
        id = WRedisClient.getInstance().lpushincr(historyKey, idKey);
        if (id != 0) {
            msgKey = companyId + ":" + "c:" + userId + ":" + id;
            msgData.setMsgId(id);
            MessageChatResponse msgResponse = new MessageChatResponse();
            
            String message = _gson.toJson(msgData);
            
            WRedisClient.getInstance().set(msgKey, message);
            
            msgResponse.setCompanyId(msgData.getCompanyId());
            msgResponse.setDatetime(msgData.getDatetime());
            msgResponse.setDisplayName(msgData.getDisplayName());
            msgResponse.setFromId(msgData.getFromId());
            msgResponse.setMsg(msgData.getMsg());
            msgResponse.setMsgId(msgData.getMsgId());
            msgResponse.setMsgType(msgData.getMsgType());
            msgResponse.setPreviousId(id -1);
            msgResponse.setToId(msgData.getToId());
            
            message = _gson.toJson(msgResponse);
            
            return message;
        }
        
        return "";
    }
    
    public void writeHistoryOrder(String companyId, String userId, String orderId) {
        
        String historyKey = companyId + ":" + "c:" + userId + ":ids";
        String msgKey = companyId + ":" + "o:" + userId + ":" + orderId;
        long count = 0;
        String maxId = "0";
        String countString = "0";
        Map<String, String> mapInfo = new HashMap<>();
        
        count = WRedisClient.getInstance().getAsyncConnection().sync().llen(historyKey);
        countString = Long.toString(count);
        
        List<String> listId = WRedisClient.getInstance().getAsyncConnection().sync().lrange(historyKey, 0, 0);
        if (listId.size() > 0)
            maxId = listId.get(0);
        
        mapInfo.put("count", countString);
        mapInfo.put("id", maxId);
        WRedisClient.getInstance().hmset(msgKey, mapInfo);
    }
    
    public List<String> getListRoomChat(String userId, int userType) {
        return null;
    }
    
    public List<String> getListRoomChatStaff(String staffId) {
        return null;
    }
    
    public List<MessageChatData> getHistory(String companyId, String userId, String staffId, int begin, int count, long lastID) {
        if (staffId == null || staffId.isEmpty()) {
            return getUserHistory(companyId, userId, begin, count, lastID);
        } else {
            //return getStaffHistory(staffId, userId, begin, count);
            return getUserHistory(companyId, userId, begin, count, lastID);
        }
    }
    
    public List<MessageChatData> getUserHistory(String companyId, String userId, int begin, int count, long lastID) {
        /*
        String key = "uc:" + userId + ":" + companyId;
        String message = null;
        List<MessageChatData> response = new ArrayList<>();
        List<String> chatList = WRedisClient.getInstance().getAsyncConnection().sync().lrange(key, begin, count);
        for (String chat : chatList) {
            message = WRedisClient.getInstance().getAsyncConnection().sync().get(chat);
            if (message != null) {
                response.add(_gson.fromJson(message, MessageChatData.class));
            }
        }
        
        return response;
*/
        String key = companyId + ":" + "c:" + userId + ":ids";
        String msgKey = "";
        String message = null;
        List<MessageChatData> response = new ArrayList<>();
        
        begin = 0;
        if (lastID > 0) {
            long maxId = 0;
            List<String> listId = WRedisClient.getInstance().getAsyncConnection().sync().lrange(key, 0, 0);
            if (listId.size() > 0)
                maxId = Long.parseLong(listId.get(0));

            if (maxId >= lastID) {
                
                begin = (int)(maxId - lastID) + 1;
                /*
                long numReverse = maxId - lastID;

                if (count >= numReverse) {
                    count = (int)numReverse;
                    begin = 0;
                } else {
                    begin = (int)numReverse - count;
                }
                */
            } else {
                return response;
            }
        }
        
        int end = begin + count - 1;
        List<String> chatList = WRedisClient.getInstance().getAsyncConnection().sync().lrange(key, begin, end);
         for (String chat : chatList) {
              msgKey = companyId + ":" + "c:" + userId + ":" + chat;
            message = WRedisClient.getInstance().getAsyncConnection().sync().get(msgKey);
            if (message != null) {
                response.add(_gson.fromJson(message, MessageChatData.class));
            }
        }
        
        return response;
    }
    
    public List<MessageChatData> getStaffHistory(String staffId, String userId, int begin, int count) {
        String chatKey = null;
        if (userId.compareToIgnoreCase(staffId) <= 0) {
            chatKey = "sc:" + userId + ":" + staffId;
        } else {
            chatKey = "sc:" + staffId + ":" + userId;
        }
        
        String message = null;
        List<MessageChatData> response = new ArrayList<>();
        List<String> chatList = WRedisClient.getInstance().getAsyncConnection().sync().lrange(chatKey, begin, count);
        for (String chat : chatList) {
            message = WRedisClient.getInstance().getAsyncConnection().sync().get(chatKey + ":" + chat);
            if (message != null) {
                response.add(_gson.fromJson(message, MessageChatData.class));
            }
        }
        
        return response;
    }
    
    public List<MessageChatData> getHistoryReverse(String companyId, String userId, String staffId, int begin, int count, long lastID) {
        
        String historyKey = companyId + ":" + "c:" + userId + ":ids";
        long maxId = 0;
        List<MessageChatData> response = new ArrayList<>();
        
        List<String> listId = WRedisClient.getInstance().getAsyncConnection().sync().lrange(historyKey, 0, 0);
        if (listId.size() > 0)
            maxId = Long.parseLong(listId.get(0));
        
        if (maxId >= lastID) {
            long numReverse = maxId - lastID;
            
            if (count > 0) {
                if (count >= numReverse) {
                    count = (int)numReverse;
                    begin = 0;
                } else {
                    begin = (int)numReverse - count;
                }
            } else {
                begin = 0;
                count = (int)numReverse;
            }
            
            String msgKey = "";
            String message = null;
            int end = begin + count - 1;
            List<String> chatList = WRedisClient.getInstance().getAsyncConnection().sync().lrange(historyKey, begin, end);
            for (String chat : chatList) {
                 if (Long.parseLong(chat) != lastID) {
                    msgKey = companyId + ":" + "c:" + userId + ":" + chat;
                    message = WRedisClient.getInstance().getAsyncConnection().sync().get(msgKey);
                    if (message != null) {
                        response.add(0, _gson.fromJson(message, MessageChatData.class));
                    }
                 } else {
                     break;
                 }
            }
        }
        
        return response;
    }
}
