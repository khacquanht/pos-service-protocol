package com.wpos.main;


import com.wpos.common.Config;
import com.vng.csm.configuration.ConfigException;
import org.apache.log4j.Logger;

/**
 *
 * @author duclm2
 */
public class ServiceDaemon {

    private static final String DEFAULT_CONFIGURATION_FILE = "wpos.ini";
    private static final Logger logger = Logger.getLogger(ServiceDaemon.class);
    private static WebServer webServer = null;

    public static void main(String[] args) throws ConfigException {
        Config.init(DEFAULT_CONFIGURATION_FILE);
        com.wahsis.common.Config.setConfigPath(DEFAULT_CONFIGURATION_FILE);

        try {
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
            logger.error("Uncaught exception: " + e.getMessage());
            System.exit(3);
        }
    }
}