/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wahsis.chat.data;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author HaiNT
 */
public class MessageChatData {
    @SerializedName("mt")
    private int msgType = 0;
    @SerializedName("fi")
    private String fromId = null;
    @SerializedName("ci")
    private String companyId = null;
    @SerializedName("dn")
    private String displayName = null;
    @SerializedName("ti")
    private String toId = null;
    @SerializedName("ms")
    private String msg = null;
    @SerializedName("dt")
    private long datetime = 0;
    @SerializedName("mi")
    private long msgId = 0;

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }
    
    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }
    
    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }
}
