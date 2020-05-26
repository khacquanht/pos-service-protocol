/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wahsis.chat.common;

import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 *
 * @author diepth
 */
public class JsonParserUtil {
    public static String parseStringValue(String data, String elementName) {
        try { 
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonEle = jsonParser.parse(data);
            return jsonEle.getAsJsonObject()
                .get(elementName)
                .getAsString();
        } catch (Exception ex) {
            
        }
        return null;
    }
    
    public static String parseStringValue(String data, String objectName, String elementName) {
        try { 
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonEle = jsonParser.parse(data);
            return jsonEle.getAsJsonObject()
                    .getAsJsonObject(objectName)
                    .get(elementName)
                    .getAsString();
        } catch (Exception ex) {
            
        }
        
        return null;
    }
    
    public static JsonObject parseJsonObject(String data) {
        try {
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonEle = jsonParser.parse(data);
            return (jsonEle.isJsonObject())?jsonEle.getAsJsonObject():null;
        } catch (Exception ex) {
            
        }
        return null;
    }
    
    public static JsonObject parseJsonObject(String data, String objectName) {
        try { 
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonEle = jsonParser.parse(data);
            return jsonEle.getAsJsonObject().getAsJsonObject(objectName);
        } catch (Exception ex) {
            
        }
        
        return null;
    }
}
