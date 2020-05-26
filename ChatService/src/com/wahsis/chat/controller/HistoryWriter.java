/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wahsis.chat.controller;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author HaiNT
 */
public class HistoryWriter {
    
    private static HistoryWriter _instance = null;
    private static final Lock createLock_ = new ReentrantLock();

    public static HistoryWriter getInstance() {
        if (_instance == null) {
            createLock_.lock();
            try {
                if (_instance == null) {
                    _instance = new HistoryWriter();
                }
            } finally {
                createLock_.unlock();
            }
        }
        return _instance;
    }
    
    public void write(String userId, String roomId, String message) {
        
    }
}
