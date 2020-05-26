/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wahsis.chat.data;

import com.google.gson.annotations.SerializedName;
import com.wahsis.chat.data.MessageChatData;

/**
 *
 * @author diepth
 */
public class MessageChatResponse extends MessageChatData {
   
    private long previousId = 0;
    
    public long getPreviousId() {
        return previousId;
    }

    public void setPreviousId(long preId) {
        this.previousId = preId;
    }
}
