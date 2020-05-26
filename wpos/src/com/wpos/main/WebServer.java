package com.wpos.main;


import com.wpos.common.Config;
import com.wpos.controller.CategoriesController;
import com.wpos.controller.CompanyController;
import com.wpos.controller.CompanyTypeController;
import com.wpos.controller.CompanyImagesController;
import com.wpos.controller.CountryController;
import com.wpos.controller.ColorsController;
import com.wpos.controller.CurrencyController;
import com.wpos.controller.MasterGroupController;
import com.wpos.controller.MasterDataController;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletHandler;
 /*
 *
 * @author duclm2
 */
public class WebServer implements Runnable{
    private static final Logger logger = Logger.getLogger(WebServer.class);
    private final Server server = new Server();
    private static WebServer _instance = null;
    private static final Lock createLock_ = new ReentrantLock();
    
    public static WebServer getInstance() {
        if (_instance == null) {
            createLock_.lock();
            try {
                if (_instance == null) {
                    _instance = new WebServer();
                }
            } finally {
                createLock_.unlock();
            }
        }
        return _instance;
    }

    @Override
    public void run() {
        try {
            int port_listen = Integer.valueOf(Config.getParam("server", "port_listen"));
            
            ServerConnector connector = new ServerConnector(server);
            connector.setPort(port_listen);
            connector.setIdleTimeout(30000);
            server.setConnectors(new Connector[]{connector});
            logger.info("Start server...");
            
            ServletHandler servletHandler = new ServletHandler();
            
           
            //com.wpos.main.TestService.test();

            servletHandler.addServletWithMapping(CompanyController.class, "/api/company/*");
            servletHandler.addServletWithMapping(CompanyTypeController.class, "/api/companytype/*");
            servletHandler.addServletWithMapping(CountryController.class, "/api/country/*");           
            servletHandler.addServletWithMapping(CategoriesController.class, "/api/category/*");
            servletHandler.addServletWithMapping(ColorsController.class, "/api/colors/*");           
            servletHandler.addServletWithMapping(CompanyImagesController.class, "/api/company_image/*");             
            servletHandler.addServletWithMapping(MasterGroupController.class, "/api/mastergroup/*");
            servletHandler.addServletWithMapping(MasterDataController.class, "/api/masterdata/*");
            servletHandler.addServletWithMapping(com.wpos.controller.CurrencyController.class, "/api/currency/*");          
            servletHandler.addServletWithMapping(com.wpos.controller.VendorsController.class, "/api/vendors/*");
                        
            ResourceHandler resource_handler = new ResourceHandler();
            resource_handler.setResourceBase("./html/");
            ContextHandler resourceContext = new ContextHandler();
            resourceContext.setContextPath("/html");
            resourceContext.setHandler(resource_handler);
            
            ResourceHandler resource_handler2 = new ResourceHandler();
            resource_handler2.setResourceBase("./upload/");
            ContextHandler resourceContext2 = new ContextHandler();
            resourceContext2.setContextPath("/upload");
            resourceContext2.setHandler(resource_handler2);

            HandlerList handlers = new HandlerList();
            handlers.setHandlers(new Handler[]{resourceContext, resourceContext2, servletHandler, new DefaultHandler()});
            server.setHandler(handlers);
            
            server.start();
            server.join();
            
           
            
        } catch (Exception e) {
            logger.error("Cannot start web server: " + e.getMessage());
            System.exit(1);
        }
    }
     public void stop() throws Exception {
        server.stop();
    }
     
   
}