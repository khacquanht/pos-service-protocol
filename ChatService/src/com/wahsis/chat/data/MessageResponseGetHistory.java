/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wahsis.chat.data;

import java.util.List;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author HaiNT
 */
public class MessageResponseGetHistory {
    @SerializedName("mt")
    private int msgType = 0;
    private int begin = 0;
    private int count = 0;
    private long lastId = 0;
    @SerializedName("ui")
    private String userId = "";
    @SerializedName("ams")
    private List<MessageChatData> arrayMsg = null;

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }
    
    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.lastId = count;
    }
    
    public long getLastId() {
        return lastId;
    }

    public void setLastId(long lastId) {
        this.lastId = lastId;
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<MessageChatData> getArrayMsg() {
        return arrayMsg;
    }

    public void setArrayMsg(List<MessageChatData> arrayMsg) {
        this.arrayMsg = arrayMsg;
    }
}
