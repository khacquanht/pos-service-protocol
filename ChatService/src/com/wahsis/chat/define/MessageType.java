/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wahsis.chat.define;

/**
 *
 * @author HaiNT
 */
public class MessageType {
    public static final int USER_CHAT                   = 1;
    public static final int STAFF_CHAT                  = 2;
    public static final int STAFF_CHAT_FORWARD          = 3;
    public static final int BROAD_CAST                  = 4;
    public static final int REQUEST_CHAT_LIST           = 10;
    public static final int RESPONSE_CHAT_LIST          = 11;
    public static final int REQUEST_USER_CHAT_HISTORY   = 12;
    public static final int REQUEST_STAFF_CHAT_HISTORY  = 13;
    public static final int RESPONSE_CHAT_HISTORY       = 14;
    public static final int REQUEST_ORDER_CHAT_HISTORY  = 15;
    public static final int REQUEST_USER_CHAT_HISTORY_REVERSE   = 16;
    public static final int REQUEST_STAFF_CHAT_HISTORY_REVERSE  = 17;
    public static final int RESPONSE_CHAT_HISTORY_REVERSE       = 18;
    
    public static final int PUSH_NOTIFICATION           = 100;
    public static final int STAFF_PUSH_NOTIFICATION     = 101;
}
