/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wahsis.chat.common;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author diepth
 */
public class CommonFunction {
    
    public static String getServerTime() {
        
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        
        cal.setTime(date);
        long time = cal.getTimeInMillis();
        
        return Long.toString(time);
    }
    
    public static String toMD5(String data) {
        
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception ex) {
            return null;
        }
    }
}
