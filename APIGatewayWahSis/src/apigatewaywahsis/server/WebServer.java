/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apigatewaywahsis.server;

import apigatewaywahsis.common.Config;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletHandler;

import apigatewaywahsis.service.AppTypeServlet;
import apigatewaywahsis.service.AppServlet;
import apigatewaywahsis.service.WifiAccessPointServlet;
import apigatewaywahsis.service.CustomerWifiAccessPointServlet;

//import apigatewaywahsis.service.CategoriesServlet;
//import apigatewaywahsis.service.ColorsServlet;
import apigatewaywahsis.service.CompanyServlet;
//import apigatewaywahsis.service.CompanyReviewServlet;
import apigatewaywahsis.service.CompanyTypeServlet;
//import apigatewaywahsis.service.CompanyWorkSheduleServlet;
import apigatewaywahsis.service.ConfigServlet;
import apigatewaywahsis.service.CountryServlet;
import apigatewaywahsis.service.CurrencyServlet;
import apigatewaywahsis.service.CurrencyActivesServlet;
import apigatewaywahsis.service.CustomerAddressBooksServlet;
import apigatewaywahsis.service.CustomerLocationServlet;

//import apigatewaywahsis.service.CustomerTypesServlet;
import apigatewaywahsis.service.CustomersServlet;
//import apigatewaywahsis.service.EmployeesServlet;
//import apigatewaywahsis.service.EmployeesDepartmentServlet;
//import apigatewaywahsis.service.ItemCategoryServlet;
//import apigateway.service.ItemColorsServlet;
//import apigatewaywahsis.service.ItemImagesServlet;
//import apigatewaywahsis.service.ItemMaterialsServlet;
//import apigatewaywahsis.service.ItemSizeServlet;
//import apigatewaywahsis.service.ItemStockInDetailsServlet;
//import apigatewaywahsis.service.ItemStockInTypesServlet;
//import apigatewaywahsis.service.ItemStockInsServlet;
//import apigatewaywahsis.service.ItemStockOutDetailsServlet;
//import apigatewaywahsis.service.ItemStockOutTypesServlet;
//import apigatewaywahsis.service.ItemStockOutsServlet;
//import apigatewaywahsis.service.ItemStocksServlet;
//import apigatewaywahsis.service.ItemTypesServlet;
//import apigatewaywahsis.service.ItemWarehousesServlet;
//import apigatewaywahsis.service.ItemsServlet;
import apigatewaywahsis.service.MasterDataServlet;
import apigatewaywahsis.service.MasterGroupServlet;
//import apigatewaywahsis.service.OrderCommentsServlet;
//import apigatewaywahsis.service.OrderDetailsServlet;
//import apigatewaywahsis.service.OrderStatusServlet;
import apigatewaywahsis.service.OrdersServlet;
import apigatewaywahsis.service.PaymentMethodsServlet;
//import apigatewaywahsis.service.PermissionServlet;
//import apigatewaywahsis.service.ResourcesServlet;
//import apigatewaywahsis.service.RolesServlet;
import apigatewaywahsis.service.ShippingMethodsServlet;
//import apigatewaywahsis.service.UnitServlet;
//import apigatewaywahsis.service.UnitConvertsServlet;
//import apigatewaywahsis.service.UsersServlet;
//import apigatewaywahsis.service.UsersRolesServlet;
//import apigatewaywahsis.service.VendorsServlet;
import apigatewaywahsis.service.NewsletterTemplateServlet;
import apigatewaywahsis.service.PermissionServlet;
import apigatewaywahsis.service.ResourcesServlet;
import apigatewaywahsis.service.RolesServlet;
import apigatewaywahsis.service.UsersServlet;
import apigatewaywahsis.service.UsersRolesServlet;
import apigatewaywahsis.service.ConfirmMessageServlet;
import apigatewaywahsis.service.MailingQueueServlet;
import apigatewaywahsis.service.CompanyDevicesServlet;
import apigatewaywahsis.service.CoreConfigDataServlet;
import apigatewaywahsis.service.LanguagesServlet;
import apigatewaywahsis.service.CompanyCustomersServlet;
import apigatewaywahsis.service.CustomerRequestServlet;
import apigatewaywahsis.service.CustomerRequestReplyServlet;
import apigatewaywahsis.service.CustomerSearchCompanyServlet;
import apigatewaywahsis.service.BuildingsServlet;
import apigatewaywahsis.service.ContractsServlet;
import apigatewaywahsis.service.CompanyImagesServlet;
import apigatewaywahsis.service.ReportInfoServlet;
import apigatewaywahsis.service.SetupDataCompanyServlet;
import apigatewaywahsis.service.ExchangeRateServlet;
import apigatewaywahsis.service.CompanyWorkingScheduleServlet;
import apigatewaywahsis.service.CompanyBossAccountServlet;

import apigatewaywahsis.service.ClusterServerServlet;
import apigatewaywahsis.service.ClusterServerCompanyServlet;
import apigatewaywahsis.service.ClusterServerDatabaseConfigServlet;
import apigatewaywahsis.service.ClusterServerDetailServlet;
import apigatewaywahsis.service.ClusterServerHostingConfigServlet;
import apigatewaywahsis.service.UserGuidServlet;
import apigatewaywahsis.service.CompanyProvideServicePartnerServlet;
import apigatewaywahsis.service.CustomerMyCartServlet;
import apigatewaywahsis.service.CustomerMyListServlet;
import apigatewaywahsis.service.CoordinatorServlet;
import apigatewaywahsis.service.DeviceSetupAppServlet;
import apigatewaywahsis.service.CustomerContactsServlet;
import apigatewaywahsis.service.HotlineServlet;
import apigatewaywahsis.service.WmsAuthentication;


/**
 *
 * @author diepth
 */
public class WebServer implements Runnable {

    private static final Logger logger = Logger.getLogger(WebServer.class);
    private Server server = new Server();
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
            int port_listen = Integer.valueOf(Config.getParameter("server", "port_listen"));

            ServerConnector connector = new ServerConnector(server);
            connector.setPort(port_listen);
            connector.setIdleTimeout(30000);
            server.setConnectors(new Connector[]{connector});
            logger.info("Start server...");

            ServletHandler servletHandler = new ServletHandler();

            servletHandler.addServletWithMapping(AppTypeServlet.class, "/api/app_type/*");
            servletHandler.addServletWithMapping(AppServlet.class, "/api/app/*");
            servletHandler.addServletWithMapping(CompanyServlet.class, "/api/company/*");
            //servletHandler.addServletWithMapping(CompanyReviewServlet.class, "/api/company_review/*");
            servletHandler.addServletWithMapping(CompanyTypeServlet.class, "/api/company_type/*");
            servletHandler.addServletWithMapping(CompanyImagesServlet.class, "/api/company_images/*");
            // servletHandler.addServletWithMapping(CompanyWorkSheduleServlet.class, "/api/company_work_shedule/*");
            servletHandler.addServletWithMapping(CompanyWorkingScheduleServlet.class, "/api/company_working_schedule/*");
            servletHandler.addServletWithMapping(CompanyDevicesServlet.class, "/api/company_devices/*");
            servletHandler.addServletWithMapping(CoreConfigDataServlet.class, "/api/core_config_data/*");
            servletHandler.addServletWithMapping(NewsletterTemplateServlet.class, "/api/newsletter_template/*");
            servletHandler.addServletWithMapping(WifiAccessPointServlet.class, "/api/wifi_access_point/*");
            servletHandler.addServletWithMapping(CustomerWifiAccessPointServlet.class, "/api/customer_wifi_access_point/*");
            servletHandler.addServletWithMapping(CountryServlet.class, "/api/country/*");
            servletHandler.addServletWithMapping(ConfigServlet.class, "/api/config/*");
            servletHandler.addServletWithMapping(CurrencyServlet.class, "/api/currency/*");
            servletHandler.addServletWithMapping(CurrencyActivesServlet.class, "/api/currency_actives/*");
            servletHandler.addServletWithMapping(MasterGroupServlet.class, "/api/master_group/*");
            servletHandler.addServletWithMapping(MasterDataServlet.class, "/api/master/*");

            servletHandler.addServletWithMapping(CustomerAddressBooksServlet.class, "/api/customer_address_books/*");
            servletHandler.addServletWithMapping(CustomerLocationServlet.class, "/api/customer_location/*");
            //servletHandler.addServletWithMapping(CustomerTypesServlet.class, "/api/customer_types/*");
            servletHandler.addServletWithMapping(CustomersServlet.class, "/api/customers/*");
            servletHandler.addServletWithMapping(CompanyCustomersServlet.class, "/api/company_customers/*");
            servletHandler.addServletWithMapping(CustomerRequestServlet.class, "/api/customer_request/*");
            servletHandler.addServletWithMapping(CustomerRequestReplyServlet.class, "/api/customer_request_reply/*");

            servletHandler.addServletWithMapping(CustomerSearchCompanyServlet.class, "/api/customer_search_company/*");

            servletHandler.addServletWithMapping(OrdersServlet.class, "/api/orders/*");
            servletHandler.addServletWithMapping(PaymentMethodsServlet.class, "/api/payment_methods/*");
            servletHandler.addServletWithMapping(ShippingMethodsServlet.class, "/api/shipping_methods/*");

            servletHandler.addServletWithMapping(PermissionServlet.class, "/api/permission/*");
            servletHandler.addServletWithMapping(ResourcesServlet.class, "/api/resources/*");
            servletHandler.addServletWithMapping(RolesServlet.class, "/api/roles/*");
            servletHandler.addServletWithMapping(UsersServlet.class, "/api/users/*");
            servletHandler.addServletWithMapping(UsersRolesServlet.class, "/api/users_roles/*");

            servletHandler.addServletWithMapping(ConfirmMessageServlet.class, "/api/confirm_message/*");
            servletHandler.addServletWithMapping(MailingQueueServlet.class, "/api/mailing_queue/*");
            servletHandler.addServletWithMapping(LanguagesServlet.class, "/api/languages/*");

            servletHandler.addServletWithMapping(BuildingsServlet.class, "/api/buildings/*");
            servletHandler.addServletWithMapping(ContractsServlet.class, "/api/contracts/*");
            servletHandler.addServletWithMapping(ReportInfoServlet.class, "/api/report_info/*");
            servletHandler.addServletWithMapping(SetupDataCompanyServlet.class, "/api/setup_data_company/*");
            servletHandler.addServletWithMapping(ExchangeRateServlet.class, "/api/exchange_rate/*");
            servletHandler.addServletWithMapping(CompanyBossAccountServlet.class, "/api/company_boss_account/*");

            servletHandler.addServletWithMapping(ClusterServerServlet.class, "/api/cluster_server/*");
            servletHandler.addServletWithMapping(ClusterServerCompanyServlet.class, "/api/cluster_server_company/*");
            servletHandler.addServletWithMapping(ClusterServerDatabaseConfigServlet.class, "/api/cluster_server_database_config/*");
            servletHandler.addServletWithMapping(ClusterServerDetailServlet.class, "/api/cluster_server_detail/*");
            servletHandler.addServletWithMapping(ClusterServerHostingConfigServlet.class, "/api/cluster_server_hosting_config/*");

            servletHandler.addServletWithMapping(UserGuidServlet.class, "/api/user_guid/*");
            servletHandler.addServletWithMapping(CompanyProvideServicePartnerServlet.class, "/api/company_provide_service_partner/*");
            servletHandler.addServletWithMapping(CustomerMyListServlet.class, "/api/customer_my_list/*");
            servletHandler.addServletWithMapping(CustomerMyCartServlet.class, "/api/customer_my_cart/*");
            servletHandler.addServletWithMapping(CoordinatorServlet.class, "/api/coordinator/*");
            servletHandler.addServletWithMapping(DeviceSetupAppServlet.class, "/api/device_setup_app/*");
            servletHandler.addServletWithMapping(CustomerContactsServlet.class, "/api/customer_contacts/*");
            servletHandler.addServletWithMapping(HotlineServlet.class, "/api/wahsis_hotline/*");

            servletHandler.addServletWithMapping(WmsAuthentication.class, "/api/wms/authen/*");
            
            ResourceHandler resource_handler1 = new ResourceHandler();
            resource_handler1.setResourceBase("./html/");

            ResourceHandler resource_handler2 = new ResourceHandler();
            resource_handler2.setResourceBase("./upload/");

            ContextHandler resourceContext1 = new ContextHandler();
            resourceContext1.setContextPath("/html");
            resourceContext1.setHandler(resource_handler1);

            ContextHandler resourceContext2 = new ContextHandler();
            resourceContext2.setContextPath("/upload");
            resourceContext2.setHandler(resource_handler2);

            HandlerList handlers = new HandlerList();
            handlers.setHandlers(new Handler[]{resourceContext1, resourceContext2, servletHandler, new DefaultHandler()});
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
