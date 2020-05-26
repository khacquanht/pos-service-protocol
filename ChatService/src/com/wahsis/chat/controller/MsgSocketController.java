/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wahsis.chat.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wahsis.chat.common.CommonFunction;
import com.wahsis.chat.define.JsonKeyName;
import com.wahsis.chat.define.MessageType;
import com.wahsis.chat.define.UserTypeDefine;
import com.wahsis.chat.info.ClientSessionInfo;
import com.wahsis.chat.data.MessageAutoRespToStaff;
import com.wahsis.chat.data.MessageChatData;
import com.wahsis.chat.data.MessageRequestGetHistory;
import com.wahsis.chat.data.MessageResponseGetHistory;
import java.io.IOException;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.math.NumberUtils;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import com.wahsis.chat.common.JsonParserUtil;
import java.util.Date;
import com.wahsis.chat.data.MessageNotifyData;

/**
 *
 * @author HaiNT
 */
@WebSocket
public class MsgSocketController {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MsgSocketController.class);
    private static final String USER_TYPE_KEY = "loginType";
    private static final String COMPANY_ID_KEY = "companyId";
    private static final String USERID_KEY = "loginId";
    private static final String DISPLAY_NAME_KEY = "displayName";
    private static final String SESSION_ID_KEY = "sessionId";
    private static final String TOKEN_KEY = "tk";
    private static final String VERIFY_KEY = "dth@wah$ntf#sys";

    private static final Gson _gson = new Gson();
    private static final Map<String, ClientSessionInfo> clientSessions = Collections.synchronizedMap(new LinkedHashMap<String, ClientSessionInfo>());

    private static final Map<String, List<String>> mapWaitForStaffReply = Collections.synchronizedMap(new LinkedHashMap<String, List<String>>());
    
    private static final Map<String, List<String>> mapCompanyStaffId = Collections.synchronizedMap(new LinkedHashMap<String, List<String>>());

    @OnWebSocketConnect
    public void onConnect(Session session) {
        //check valid
        logger.info("MsgSocketController.onConnect: remote address = " + session.getRemoteAddress().getHostString());

        Map<String, List<String>> params = session.getUpgradeRequest().getParameterMap();
        List<String> types = params.get(USER_TYPE_KEY);
        if (types == null || types.isEmpty()) {
            session.close();
            logger.warn("onConnect: userType null or empty");
            return;
        }

        int userType = NumberUtils.toInt(types.get(0));
        if (userType != UserTypeDefine.USER && userType != UserTypeDefine.STAFF) {
            session.close();
            logger.warn("onConnect: userType != user != staff");
            return;
        }

        String companyId = "";
        if (userType == UserTypeDefine.STAFF) {
            List<String> companyIds = params.get(COMPANY_ID_KEY);
            if (companyIds == null || companyIds.isEmpty()) {
                session.close();
                logger.warn("onConnect: COMPANY_ID_KEY null or empty");
                return;
            }
            companyId = companyIds.get(0);
        }
        
        String displayName = "";
/*
        List<String> displayNames = params.get(DISPLAY_NAME_KEY);
        if (displayNames == null || displayNames.isEmpty()) {
            session.close();
            logger.warn("onConnect: DISPLAY_NAME_KEY null or empty");
            return;
        }
        displayName = displayNames.get(0);
*/

        String userId = "";
        List<String> userIds = params.get(USERID_KEY);
        if (userIds == null || userIds.isEmpty()) {
            session.close();
            logger.warn("onConnect: USERID_KEY null or empty");
            return;
        }
        userId = userIds.get(0);
        
        String sessionId = "";
        List<String> sessionIds = params.get(SESSION_ID_KEY);
        if (sessionIds == null || sessionIds.isEmpty()) {
            session.close();
            logger.warn("onConnect: SESSION_ID_KEY null or empty");
            return;
        }
        sessionId = sessionIds.get(0);
        
        String token = "";
        List<String> tokens = params.get(TOKEN_KEY);
        if (tokens == null || tokens.isEmpty()) {
            session.close();
            logger.warn("onConnect: TOKEN_KEY null or empty");
            return;
        }
        token = tokens.get(0);
        
        String verify = CommonFunction.toMD5(sessionId + VERIFY_KEY + userId);
        
        if (verify.compareToIgnoreCase(token) != 0) {
            session.close();
            logger.warn("onConnect: Invalid connection");
            return;
        }
        
        String key = "";
        if (userType == UserTypeDefine.STAFF) {
            key = companyId + ":" + userId;
        } else {
            key = userId;
        }
        
        List<HttpCookie> listCookie = new ArrayList<>();
        listCookie.add(new HttpCookie(USERID_KEY, key));
        session.getUpgradeRequest().setCookies(listCookie);

        clientSessions.put(key, new ClientSessionInfo(session, userType, userId, companyId, displayName));
        if (userType == UserTypeDefine.STAFF) {
            List<String> listStaffId = mapCompanyStaffId.get(companyId);
             
            Boolean exist = false;
            if (listStaffId != null) {
                for (String id : listStaffId) {
                   if (id.compareToIgnoreCase(userId) == 0) {
                       exist = true;
                       break;
                   }
                }
             } else {
                 listStaffId = new ArrayList<String>();
             }
             
             if (exist == false) {
                 listStaffId.add(userId);
                 mapCompanyStaffId.put(companyId, listStaffId);
             }
        }
        session.setIdleTimeout(1000*60*1000);
    }

    @OnWebSocketClose
    public void onClose(Session session, int status, String reason) {
        //check valid
        logger.info("MsgSocketController.onClose: remote address = " + session.getRemoteAddress().getHostString() + reason);
        session.close();
        removeClient(session);
    }

    @OnWebSocketMessage
    public void onText(Session session, String message) {
        logger.info("MsgSocketController.onText: remote address = " + session.getRemoteAddress().getHostString());
        processMsg(session, message);
    }

    public void closeSession(Session session) {
        if (session.isOpen()) {
            session.close();
        }
    }

    public void removeClient(Session session) {
        if (session.isOpen()) {
            session.close();
            //return;
        }
        
        ClientSessionInfo cInfo = null;
        List<HttpCookie> listCookie = session.getUpgradeRequest().getCookies();
        for (HttpCookie cookie : listCookie) {
            if (cookie.getName().compareToIgnoreCase(USERID_KEY) == 0) {
                cInfo = clientSessions.remove(cookie.getValue());
                break;
            }
        }
        
        if (cInfo != null && cInfo.getUserType() == UserTypeDefine.STAFF) {
            List<String> listStaffId = mapCompanyStaffId.get(cInfo.getCompanyId());

            if (listStaffId != null) {
                for (String id : listStaffId) {
                   if (id.compareToIgnoreCase(cInfo.getUserId()) == 0) {
                       listStaffId.remove(id);
                       mapCompanyStaffId.put(cInfo.getCompanyId(), listStaffId);
                       break;
                   }
                }
            }
        }
    }
    
    public String getRoomId(String endpointId1, String endpointId2) {
        return endpointId1 + "[vs]" + endpointId2;
    }

    public void processMsg(Session session, String message) {
    /*    
        JsonParser parser = new JsonParser();
        JsonElement ele = parser.parse(message);
        if (ele == null) {
            closeSession(session);
            return;
        }

        if (ele.isJsonObject() == false) {
            closeSession(session);
            return;
        }
    */
      
        try {
            JsonObject jsonObject = JsonParserUtil.parseJsonObject(message);
            if (jsonObject == null) {
                closeSession(session);
                return;
            }
            
            int mt = jsonObject.get(JsonKeyName.MSG_TYPE).getAsInt();
            switch (mt) {
                case MessageType.USER_CHAT:
                case MessageType.STAFF_CHAT:
                    MessageChatData chatdata = _gson.fromJson(jsonObject, MessageChatData.class);
                    processChatMsg(session, mt, chatdata);
                    break;
                case MessageType.REQUEST_CHAT_LIST:
                    processGetListRoomChat(session, jsonObject.get(JsonKeyName.USER_ID).getAsString());
                    break;
                case MessageType.REQUEST_USER_CHAT_HISTORY: 
                case MessageType.REQUEST_STAFF_CHAT_HISTORY:
                    MessageRequestGetHistory getHistorydata = _gson.fromJson(jsonObject, MessageRequestGetHistory.class);
                    processGetHistory(session, getHistorydata);
                    break;
                case MessageType.REQUEST_USER_CHAT_HISTORY_REVERSE:
                case MessageType.REQUEST_STAFF_CHAT_HISTORY_REVERSE:
                    MessageRequestGetHistory getHistorydata2 = _gson.fromJson(jsonObject, MessageRequestGetHistory.class);
                    processGetHistoryReverse(session, getHistorydata2);
                    break;
                case MessageType.PUSH_NOTIFICATION:
                    //processPushNotification(jsonObject);
                    MessageNotifyData notifydata = _gson.fromJson(jsonObject, MessageNotifyData.class);
                    processPushNotificationEx(notifydata, message);
                    break;
                case MessageType.STAFF_PUSH_NOTIFICATION:
                    MessageNotifyData notifydata1 = _gson.fromJson(jsonObject, MessageNotifyData.class);
                    processStaffPushNotificationEx(notifydata1, message);
                    break;
                case MessageType.REQUEST_ORDER_CHAT_HISTORY:
                    
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            
        }
    }

    public static long getCurrentDateTime() {
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+0"));
        return calendar.getTimeInMillis();
    }

    public void processChatMsg(Session session, int msgType, MessageChatData data) {
        data.setDatetime(getCurrentDateTime());
        
        if (msgType == MessageType.USER_CHAT) {
            processUserChatMsg(session, data);
        } else if (msgType == MessageType.STAFF_CHAT) {
            processStaffChatMsg(session, data);
        }
    }

    public void processUserChatMsg(Session session, MessageChatData data) {
 
        logger.info("MsgSocketController.processUserChatMsg: (" + data.getDisplayName() + ")");
        
        String message = HistoryHelper.getInstance().writeHistoryUserVSStaff(data.getCompanyId() , data.getFromId(), data.getToId(), data);
        if (message.compareTo("") != 0)
        {
            try {
                session.getRemote().sendString(message);
                logger.info("MsgSocketController.processUserChatMsg: send message response User succ (" + data.getDisplayName() + ")");
            } catch (IOException ex) {
                closeSession(session);
                logger.error("MsgSocketController.processUserChatMsg: send message response User error (" + data.getDisplayName() + ")", ex);
            }
            
            List<String> listStaffId = mapCompanyStaffId.get(data.getCompanyId());

            String key;
            if (listStaffId != null) {
                for (String staffId : listStaffId) {

                    key = data.getCompanyId() + ":" + staffId;
                    ClientSessionInfo remoteClient = clientSessions.get(key);
                    if (remoteClient != null && remoteClient.getUserType() == UserTypeDefine.STAFF) {
                        try {
                            remoteClient.getSession().getRemote().sendString(message);
                            logger.info("MsgSocketController.processUserChatMsg: send message to Staff succ" );
                        } catch (IOException ex) {
                            closeSession(remoteClient.getSession());
                            logger.error("MsgSocketController.processUserChatMsg: send message to Staff error", ex);
                        }
                    } else {
                        //closeSession(session);
                    }
                }
            }
        }
    }

    public void processStaffChatMsg(Session session, MessageChatData data) {
        
        logger.info("MsgSocketController.processStaffChatMsg (" + data.getDisplayName() + ")");
        
        String message = HistoryHelper.getInstance().writeHistoryUserVSStaff(data.getCompanyId(), data.getToId(), data.getFromId(), data);
        if (message.compareTo("") != 0) {
            
            //Send message response staff
            try {
                session.getRemote().sendString(message);
                logger.info("MsgSocketController.processStaffChatMsg: send message response Staff succ(" + data.getDisplayName() + ")");
            } catch (IOException ex) {
                closeSession(session);
                logger.error("MsgSocketController.processStaffChatMsg: send message response Staff error (" + data.getDisplayName() + ")", ex);
            }
            
            // Send message to Custommer
            ClientSessionInfo remoteClient = clientSessions.get(data.getToId());
            if (remoteClient != null && remoteClient.getUserType() == UserTypeDefine.USER) {
                try {
                    remoteClient.getSession().getRemote().sendString(message);
                    logger.info("MsgSocketController.processStaffChatMsg: send message to User succ" );
                } catch (IOException ex) {
                    closeSession(remoteClient.getSession());
                    logger.error("MsgSocketController.processStaffChatMsg: send message to User error", ex);
                }
            } else {
                //closeSession(session);
            }
            
            // Send message to other staffs
            List<String> listStaffId = mapCompanyStaffId.get(data.getCompanyId());
            String key;
            if (listStaffId != null) {
                data.setMsgType(MessageType.STAFF_CHAT_FORWARD);
                String message2 = _gson.toJson(data);
                for (String staffId : listStaffId) {
                    if (staffId.compareToIgnoreCase(data.getFromId()) != 0) {
                        key = data.getCompanyId() + ":" + staffId;
                        ClientSessionInfo remoteClient2 = clientSessions.get(key);
                        if (remoteClient2 != null && remoteClient2.getUserType() == UserTypeDefine.STAFF) {
                            try {
                                remoteClient2.getSession().getRemote().sendString(message2);
                                logger.info("MsgSocketController.processStaffChatMsg: send message to other Staffs succ" );
                            } catch (IOException ex) {
                                closeSession(remoteClient2.getSession());
                                logger.error("MsgSocketController.processStaffChatMsg: send message to other Staffs error", ex);
                            }
                        } else {
                            //closeSession(session);
                        }
                    }
                }
            }
        }
    }
    
    public void processGetListRoomChat(Session session, String userId) {
        try {
            ClientSessionInfo remoteClient = clientSessions.get(userId);
            JsonObject object = new JsonObject();
            object.addProperty(JsonKeyName.MSG_TYPE, MessageType.RESPONSE_CHAT_LIST);
            object.add(JsonKeyName.CHAT_TO_IDS, _gson.toJsonTree(HistoryHelper.getInstance().getListRoomChat(userId, remoteClient.getUserType())));
            session.getRemote().sendString(_gson.toJson(object));
        } catch (IOException ex) {
            closeSession(session);
            logger.error("MsgSocketController.processGetListRoomChat: ", ex);
        }
    }
    
    public void processGetHistory(Session session, MessageRequestGetHistory request) {
        
        logger.info("MsgSocketController.processGetHistory" );
        
        try {
            MessageResponseGetHistory msgResonseHistory = new MessageResponseGetHistory();
            msgResonseHistory.setMsgType(MessageType.RESPONSE_CHAT_HISTORY); 
            msgResonseHistory.setBegin(request.getBegin());
            msgResonseHistory.setCount(request.getCount());
            msgResonseHistory.setLastId(request.getLastId());
            msgResonseHistory.setUserId(request.getUserId());
            msgResonseHistory.setArrayMsg(HistoryHelper.getInstance().getHistory(request.getCompanyId(), request.getUserId(), request.getStaffId(), request.getBegin(), request.getCount(), request.getLastId()));
           
            session.getRemote().sendString(_gson.toJson(msgResonseHistory));
        } catch (IOException ex) {
            closeSession(session);
            logger.error("MsgSocketController.processGetHistory: error", ex);
        }
    }
    
    public void processGetHistoryReverse(Session session, MessageRequestGetHistory request) {
        
        logger.info("MsgSocketController.processGetHistoryReverse" );
        
        try {
            MessageResponseGetHistory msgResonseHistory = new MessageResponseGetHistory();
            msgResonseHistory.setMsgType(MessageType.RESPONSE_CHAT_HISTORY_REVERSE); 
            msgResonseHistory.setBegin(request.getBegin());
            msgResonseHistory.setCount(request.getCount());
            msgResonseHistory.setLastId(request.getLastId());
            msgResonseHistory.setUserId(request.getUserId());
            msgResonseHistory.setArrayMsg(HistoryHelper.getInstance().getHistoryReverse(request.getCompanyId(), request.getUserId(), request.getStaffId(), request.getBegin(), request.getCount(), request.getLastId()));
           
            session.getRemote().sendString(_gson.toJson(msgResonseHistory));
        } catch (IOException ex) {
            closeSession(session);
            logger.error("MsgSocketController.processGetHistory: error", ex);
        }
    }
    
    public void processPushNotification(JsonObject jsonObject) {
        String toCompanyId = jsonObject.get(JsonKeyName.TO_COMPANY_ID).getAsString();
        if (toCompanyId == null || toCompanyId.isEmpty()) {
            return;
        }
        
        jsonObject.addProperty(JsonKeyName.DATE_TIME, getCurrentDateTime());
        String messageData = _gson.toJson(jsonObject);
        
        List<String> staffList = new ArrayList<>();
            for (Entry<String, ClientSessionInfo> remoteClient : clientSessions.entrySet()) {
                if (remoteClient.getValue().isAllowReplyToUser()) {
                    if (toCompanyId.compareToIgnoreCase(remoteClient.getValue().getCompanyId()) == 0) {
                        try {
                            remoteClient.getValue().getSession().getRemote().sendString(messageData);
                            staffList.add(remoteClient.getValue().getUserId());
                        } catch (IOException ex) {
                            closeSession(remoteClient.getValue().getSession());
                            logger.error("MsgSocketController.processPushNotification: ", ex);
                        }
                    }
                }
            }
    }
    
    
     public void processPushNotificationEx(MessageNotifyData data, String messageData) {
        String toCompanyId = data.getCompanyId();
        if (toCompanyId == null || toCompanyId.isEmpty()) {
            return;
        }
        
        logger.info("MsgSocketController: Guest: " + data.getFromId() + "push notify with order" + data.getOrderCode() );
        
       // jsonObject.addProperty(JsonKeyName.DATE_TIME, getCurrentDateTime());
       // String messageData = _gson.toJson(data);
/*
        List<String> staffList = new ArrayList<>();
            for (Entry<String, ClientSessionInfo> remoteClient : clientSessions.entrySet()) {
                if (remoteClient.getValue().isAllowReplyToUser()) {
                    if (toCompanyId.compareToIgnoreCase(remoteClient.getValue().getCompanyId()) == 0) {
                        try {
                            remoteClient.getValue().getSession().getRemote().sendString(messageData);
                            staffList.add(remoteClient.getValue().getUserId());
                        } catch (IOException ex) {
                            closeSession(remoteClient.getValue().getSession());
                            logger.error("MsgSocketController.processPushNotification: ", ex);
                        }
                    }
                }
            }
*/
    
        List<String> listStaffId = mapCompanyStaffId.get(toCompanyId);
        
        String key;
        if (listStaffId != null) {
            for (String id : listStaffId) {
              
                key = toCompanyId + ":" + id;
                ClientSessionInfo remoteClient = clientSessions.get(key);
                if (remoteClient != null && remoteClient.getUserType() == UserTypeDefine.STAFF) {
                    try {
                        remoteClient.getSession().getRemote().sendString(messageData);
                        logger.info("MsgSocketController: send notify to Staff" );
                    } catch (IOException ex) {
                        closeSession(remoteClient.getSession());
                        logger.error("MsgSocketController.processPushNotificationEx 1: ", ex);
                    }
                } else {
                    //closeSession(session);
                }
            }
        }
        
        HistoryHelper.getInstance().writeHistoryOrder(toCompanyId, data.getFromId(), data.getOrderCode());
    }
     
    public void processStaffPushNotificationEx(MessageNotifyData data, String messageData) {
        String toCompanyId = data.getCompanyId();
        if (toCompanyId == null || toCompanyId.isEmpty()) {
            return;
        }
        
        logger.info("MsgSocketController: Staff: " + data.getFromId() + "push notify with order" + data.getOrderCode() );
        
        ClientSessionInfo remoteClient = clientSessions.get(data.getToId());
        if (remoteClient != null && remoteClient.getUserType() == UserTypeDefine.USER) {
            try {
                remoteClient.getSession().getRemote().sendString(messageData);
                logger.info("MsgSocketController: send notify to Guest" );
            } catch (IOException ex) {
                closeSession(remoteClient.getSession());
                logger.error("MsgSocketController.processStaffPushNotificationEx 1: ", ex);
            }
        } else {
            //closeSession(session);
        }
    }
}
