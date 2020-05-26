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
public class MessageRequestGetHistory {
    @SerializedName("mt")
    private int msgType = 0;
    private int begin = 0;
    private int count = 0;
    private long lastId = 0;
    @SerializedName("ci")
    private String companyId = null;
    @SerializedName("ui")
    private String userId = null;
    @SerializedName("si")
    private String staffId = null;

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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    
}
