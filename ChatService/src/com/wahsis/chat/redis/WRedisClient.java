/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wahsis.chat.redis;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisFuture;
import com.lambdaworks.redis.ScriptOutputType;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.wahsis.chat.common.Config;
import com.wahsis.chat.main.WebServer;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HaiNT
 */
public class WRedisClient {
    
    private static WRedisClient _instance = null;
    private static final Lock createLock_ = new ReentrantLock();
    
    private RedisClient _client = null;
    private StatefulRedisConnection<String, String> _asyncCommand = null;
    
    private String _scriptAddToList = "";
    private String _scriptWriteHistory = "";

    private WRedisClient() {}
    public static WRedisClient getInstance() {
        if (_instance == null) {
            createLock_.lock();
            try {
                if (_instance == null) {
                    _instance = new WRedisClient();
                }
            } finally {
                createLock_.unlock();
            }
        }
        return _instance;
    }
    
    public void start() {
        String uri = Config.getParam("redis", "uri");
        System.out.println("Redis uri = " + uri);
        if (uri != null && uri.isEmpty() == false) {
            _client = RedisClient.create(uri);
            _asyncCommand = _client.connect();
            
            _scriptAddToList = _asyncCommand.sync().scriptLoad(""
                    + "redis.call('lrem',KEYS[1], 1,KEYS[2])"
                    + "redis.call('lpush',KEYS[1],KEYS[2])"
                    + "return ''");
            
            _scriptWriteHistory = _asyncCommand.sync().scriptLoad(""
                    + "local index = redis.call('rpushx', KEYS[1], KEYS[2]) - 1\n"
                    + "redis.call('lpush', KEYS[3], index)\n"
                    + "return ''");
        }
    }
    
    public void stop() {
        if (_asyncCommand != null) {
            _asyncCommand.close();
        }
        if (_client != null) {
            _client.shutdown();
        }
    }
    
    public StatefulRedisConnection<String, String> getAsyncConnection() {
        return _asyncCommand;
    }
    
    public void runScriptAddToList(String key, String roomId) {
        _asyncCommand.async().evalsha(_scriptAddToList, ScriptOutputType.VALUE, key, roomId);
    }
    
    public void runScriptWriteHistory(String roomIdKey, String staffChatListKey, String message) {
        if (_asyncCommand != null) {
            _asyncCommand.async().evalsha(_scriptWriteHistory, ScriptOutputType.VALUE, roomIdKey, message, staffChatListKey);
        }
    }
    
    public void set(String key, String value) {
        if (_asyncCommand != null) {
            _asyncCommand.async().set(key, value);
        }
    }
    
    public void lpush(String key, String... values) {
        if (_asyncCommand != null) {
            _asyncCommand.async().lpush(key, values);
        }
    }
    
    public void lpushx(String key, String value) {
        if (_asyncCommand != null) {
            String[] values = {value};
            _asyncCommand.async().lpush(key, values);
        }
    }
    
    public long lpushincr(String key, String idKey) {
        if (_asyncCommand != null) {
           
            long id = _asyncCommand.sync().incr(idKey);
            if (id > 0) {
                String sid = Long.toString(id);
                String[] values = {sid};
                _asyncCommand.sync().lpush(key, values);
               // _asyncCommand.sync().exec();

                return id;
            }
        }
        
        return 0;
    }
    
    public void rpushx(String key, String value) {
        if (_asyncCommand != null) {
            String[] values = {value};
            _asyncCommand.async().rpush(key, values);
        }
    }
    
    public void hmset(String key, Map<String, String> map) {
        if (_asyncCommand != null) {
            _asyncCommand.async().hmset(key, map);
        }
    }
    
    public long llen(String key) {
        if (_asyncCommand != null) {
            return _asyncCommand.sync().llen(key);
        }
        
        return -1;
    }
}
