/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apigateway.server;

import apigateway.common.Config;

import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.text.*;
import java.util.*;
import java.util.Base64;
import java.util.UUID;
import java.io.UnsupportedEncodingException;
import com.wahsis.common.RSA;
import java.math.BigInteger;
import java.security.SecureRandom;
/**
 *
 * @author diepth
 */
public class ServiceDaemon {

    private static final String DEFAULT_CONFIGURATION_FILE = "wpos.ini";
    private static final Logger logger = Logger.getLogger(ServiceDaemon.class);
    private static WebServer webServer = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        try {

//            RSA rsa = new RSA(1024);
//
//            String text1 = "Yellow and Black Border Collies";
//            System.out.println("Plaintext: " + text1);
//            BigInteger plaintext = new BigInteger(text1.getBytes());
//
//            BigInteger ciphertext = rsa.encrypt(plaintext);
//            System.out.println("Ciphertext: " + ciphertext);
//            plaintext = rsa.decrypt(ciphertext);
//
//            String text2 = new String(plaintext.toByteArray());
//            System.out.println("Plaintext: " + text2);

//            Date date = new Date();
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(date);
//            int year = cal.get(Calendar.YEAR);
//            int month = cal.get(Calendar.MONTH) + 1;
//            int day = cal.get(Calendar.DAY_OF_MONTH);
//            System.out.println(year);
//            System.out.println(month);
//            System.out.println(day);
//
//            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
//            long d2 = formater.parse("2001-1-1").getTime();
//            long d1 = formater.parse("2001-1-2").getTime();
//            System.out.println(Math.abs((d1 - d2) / (1000 * 60 * 60 * 24)));
//            try {
//
//                // Encode using basic encoder
//                String base64encodedString = Base64.getEncoder().encodeToString("TutorialsPoint?java8".getBytes("utf-8"));
//                System.out.println("Base64 Encoded String (Basic) :" + base64encodedString);
//
//                // Decode
//                byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
//
//                System.out.println("Original String: " + new String(base64decodedBytes, "utf-8"));
//                base64encodedString = Base64.getUrlEncoder().encodeToString("TutorialsPoint?java8".getBytes("utf-8"));
//                System.out.println("Base64 Encoded String (URL) :" + base64encodedString);
//
////                StringBuilder stringBuilder = new StringBuilder();
////                for (int i = 0; i < 10; ++i) {
////                    stringBuilder.append(UUID.randomUUID().toString());
////                }
//
//                byte[] mimeBytes = "TutorialsPoint?java8".getBytes("utf-8");
//                String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
//                System.out.println("Base64 Encoded String (MIME) :" + mimeEncodedString);
//
//            } catch (UnsupportedEncodingException e) {
//                System.out.println("Error :" + e.getMessage());
//            }
            Config.setConfigPath(DEFAULT_CONFIGURATION_FILE);
            com.wahsis.common.Config.setConfigPath(DEFAULT_CONFIGURATION_FILE);
            logger.info("Start server...");
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
            String msg = "Exception encountered during startup.";
            logger.error(msg, e);
            System.out.println(msg);
            logger.error("Uncaught exception: " + e.getMessage(), e);
            System.exit(3);
        }
    }

}
