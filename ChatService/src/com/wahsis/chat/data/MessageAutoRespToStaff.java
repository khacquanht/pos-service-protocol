/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wahsis.chat.data;

/**
 *
 * @author HaiNT
 */
public class MessageAutoRespToStaff {
    private int msgType = 0;
    private long datetime = 0;
    private String message = null;

    public MessageAutoRespToStaff(int msgType, long datetime, String message) {
        this.msgType = msgType;
        this.datetime = datetime;
        this.message = message;
    }
    
    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
