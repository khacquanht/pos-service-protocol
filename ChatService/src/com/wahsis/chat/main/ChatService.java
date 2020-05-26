/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wahsis.chat.main;

import com.wahsis.chat.common.Config;
import org.apache.log4j.Logger;

/**
 *
 * @author HaiNT
 */
public class ChatService {

    private static final String DEFAULT_CONFIGURATION_FILE = "chatservice.ini";
    private static final Logger logger = Logger.getLogger(ChatService.class);
    private static WebServer webServer = null;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            Config.init(DEFAULT_CONFIGURATION_FILE);
            
            webServer = WebServer.getInstance();
            new Thread(webServer).start();

            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        logger.info("Shutdown thread before webserver getinstance");
                        if (webServer != null) {
                            webServer.stop();
                        }
                    } catch (Exception e) {
                    }
                }
            }, "Stop Jetty Hook"));

        } catch (Throwable e) {
            logger.error("Uncaught exception: " + e.getMessage(), e);
            System.exit(3);
        }
    }
    
}
