/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apigateway.server;

import apigateway.common.Config;
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

import apigateway.service.CartServlet;
import apigateway.service.FavoriteCartServlet;
import apigateway.service.CategoriesServlet;
import apigateway.service.ColorsServlet;
import apigateway.service.CompanyServlet;
import apigateway.service.CompanyReviewServlet;
import apigateway.service.CompanyReviewReplyServlet;

import apigateway.service.CompanyTypeServlet;
import apigateway.service.CompanyWorkSheduleServlet;
import apigateway.service.StoreServlet;
import apigateway.service.ConfigServlet;
//import apigateway.service.CountryServlet;
import apigateway.service.CurrencyServlet;
import apigateway.service.CurrencyActivesServlet;
import apigateway.service.ExchangeRateServlet;
import apigateway.service.PaymentExchangeRateServlet;

//import apigateway.service.CustomerAddressBooksServlet;
//import apigateway.service.CustomerTypesServlet;
import apigateway.service.CustomersServlet;
import apigateway.service.EmployeesServlet;
import apigateway.service.EmployeesDepartmentServlet;
import apigateway.service.ItemCategoryServlet;
//import apigateway.service.ItemColorsServlet;
//import apigateway.service.ItemImagesServlet;
import apigateway.service.ItemMaterialsServlet;
import apigateway.service.ItemSizeServlet;
import apigateway.service.ItemStockInDetailsServlet;
import apigateway.service.ItemStockInTypesServlet;
import apigateway.service.ItemStockInsServlet;
import apigateway.service.ItemStockOutDetailsServlet;
import apigateway.service.ItemStockOutTypesServlet;
import apigateway.service.ItemStockOutsServlet;
import apigateway.service.ItemStocksServlet;
import apigateway.service.ItemTypesServlet;
import apigateway.service.ItemWarehousesServlet;
import apigateway.service.ItemsServlet;
import apigateway.service.ItemBrandServlet;
import apigateway.service.ItemGiftCardServlet;

import apigateway.service.MasterDataServlet;
import apigateway.service.MasterGroupServlet;
import apigateway.service.OrderCommentsServlet;
//import apigateway.service.OrderDetailsServlet;
import apigateway.service.OrderStatusServlet;
import apigateway.service.OrdersServlet;
//import apigatewaywahsis.service.PaymentMethodsServlet;
//import apigatewaywahsis.service.ShippingMethodsServlet;
import apigateway.service.UnitServlet;
import apigateway.service.UnitConvertsServlet;
//import apigateway.service.UsersServlet;
//import apigateway.service.UsersRolesServlet;
import apigateway.service.VendorsServlet;

import apigateway.service.PermissionServlet;
import apigateway.service.ResourcesServlet;
import apigateway.service.RolesServlet;
import apigateway.service.UsersServlet;
import apigateway.service.UsersRolesServlet;
import apigateway.service.CompanyCommentServlet;
import apigateway.service.CompanyCommentReplyServlet;
import apigateway.service.CompanyDevicesServlet;
import apigateway.service.CompanyPaymentMethodsServlet;
import apigateway.service.CompanyShippingMethodsServlet;
import apigateway.service.ItemBranchServlet;
import apigateway.service.CompanyLocationServlet;
import apigateway.service.CompanyTableServlet;
import apigateway.service.CompanyTableStaffServlet;
import apigateway.service.OrderTablesServlet;
import apigateway.service.DeliveryMethodsServlet;
import apigateway.service.DeliveryDateSettingServlet;
import apigateway.service.DeliveryTimeSettingServlet;

//promotion
import apigateway.service.SalesRuleServlet;
import apigateway.service.CatalogRuleServlet;
import apigateway.service.SalesRuleCouponServlet;
import apigateway.service.SalesRuleCouponUsageServlet;
import apigateway.service.SalesRuleCustomerUsageServlet;
import apigateway.service.PromotionBannerServlet;

//order status
import apigateway.service.SalesOrderStatusServlet;

//ship
import apigateway.service.SalesShipmentServlet;
import apigateway.service.SalesShipmentCommentServlet;
//import apigateway.service.SalesShipmentItemServlet;
import apigateway.service.SalesShipmentTrackServlet;

//invoice
import apigateway.service.SalesInvoiceServlet;
import apigateway.service.SalesInvoiceCommentServlet;
import apigateway.service.SalesInvoicePaymentServlet;
import apigateway.service.TransferShiftServlet;
import apigateway.service.PosDebtVoucherServlet;
import apigateway.service.PosCashReceiptPaymentServlet;
import apigateway.service.PosCashReceiptServlet;
import apigateway.service.PosCashPaymentServlet;
//config
import apigateway.service.NewsletterTemplateServlet;
import apigateway.service.CoreConfigDataServlet;

//return
import apigateway.service.SalesCreditMemoServlet;
import apigateway.service.SalesCreditMemoCommentServlet;
//import apigateway.service.SalesCreditMemoItemServlet;
import apigateway.service.PackagesServlet;
//import apigateway.service.OrderPackageServlet;
import apigateway.service.TableRateConditionServlet;
import apigateway.service.LanguagesServlet;
//import apigateway.service.OrdersReportServlet;
import apigateway.service.ReportInfoServlet;
import apigateway.service.CustomerCumulatePointServlet;

import apigateway.service.CustomerCardServlet;
import apigateway.service.CustomerCardTypeServlet;
import apigateway.service.CustomerCumulativePointHistoryServlet;
import apigateway.service.CumulativePointFormulaServlet;
import apigateway.service.RedeemPointFormulaServlet;
import apigateway.service.CustomerRedeemPointHistoryServlet;
import apigateway.service.CustomerCumulativeRedeemPointServlet;
import apigateway.service.CmsServlet;
import apigateway.service.InventoryTempServlet;
import apigateway.service.ItemPurchasePriceServlet;
import apigateway.service.CustomerClaimingDebtsServlet;
import apigateway.service.ModuleActiveServlet;






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

            servletHandler.addServletWithMapping(CompanyServlet.class, "/api/company/*");
            servletHandler.addServletWithMapping(CompanyReviewServlet.class, "/api/company_review/*");
            servletHandler.addServletWithMapping(CompanyReviewReplyServlet.class, "/api/company_review_reply/*");
            servletHandler.addServletWithMapping(CompanyCommentServlet.class, "/api/company_comment/*");
            servletHandler.addServletWithMapping(CompanyCommentReplyServlet.class, "/api/company_comment_reply/*");
            servletHandler.addServletWithMapping(CompanyTypeServlet.class, "/api/company_type/*");
            servletHandler.addServletWithMapping(CompanyWorkSheduleServlet.class, "/api/company_work_shedule/*");
            servletHandler.addServletWithMapping(CompanyDevicesServlet.class, "/api/company_devices/*");
            servletHandler.addServletWithMapping(CompanyLocationServlet.class, "/api/company_location/*");
            servletHandler.addServletWithMapping(CompanyTableServlet.class, "/api/company_table/*");
            servletHandler.addServletWithMapping(CompanyTableStaffServlet.class, "/api/company_table_staff/*");
            servletHandler.addServletWithMapping(StoreServlet.class, "/api/store/*");
            servletHandler.addServletWithMapping(MasterDataServlet.class, "/api/master/*");
            servletHandler.addServletWithMapping(ConfigServlet.class, "/api/config/*");
            servletHandler.addServletWithMapping(CurrencyServlet.class, "/api/currency/*");
            servletHandler.addServletWithMapping(CurrencyActivesServlet.class, "/api/currency_actives/*");
            servletHandler.addServletWithMapping(ExchangeRateServlet.class, "/api/exchange_rate/*");
            servletHandler.addServletWithMapping(PaymentExchangeRateServlet.class, "/api/payment_exchange_rate/*");
            servletHandler.addServletWithMapping(MasterGroupServlet.class, "/api/master_group/*");
            servletHandler.addServletWithMapping(UnitServlet.class, "/api/unit/*");
            servletHandler.addServletWithMapping(UnitConvertsServlet.class, "/api/unit_converts/*");
            servletHandler.addServletWithMapping(VendorsServlet.class, "/api/vendors/*");
            servletHandler.addServletWithMapping(ColorsServlet.class, "/api/colors/*");
            servletHandler.addServletWithMapping(CategoriesServlet.class, "/api/categories/*");
            servletHandler.addServletWithMapping(ItemCategoryServlet.class, "/api/item_category/*");
            servletHandler.addServletWithMapping(ItemBranchServlet.class, "/api/item_branch/*");
            servletHandler.addServletWithMapping(ItemMaterialsServlet.class, "/api/item_materials/*");
            servletHandler.addServletWithMapping(ItemSizeServlet.class, "/api/item_size/*");
            servletHandler.addServletWithMapping(ItemStockInDetailsServlet.class, "/api/item_stock_in_details/*");
            servletHandler.addServletWithMapping(ItemStockInTypesServlet.class, "/api/item_stock_in_types/*");
            servletHandler.addServletWithMapping(ItemStockInsServlet.class, "/api/item_stock_ins/*");
            servletHandler.addServletWithMapping(ItemStockOutDetailsServlet.class, "/api/item_stock_out_details/*");
            servletHandler.addServletWithMapping(ItemStockOutTypesServlet.class, "/api/item_stock_out_types/*");
            servletHandler.addServletWithMapping(ItemStockOutsServlet.class, "/api/item_stock_outs/*");
            servletHandler.addServletWithMapping(ItemStocksServlet.class, "/api/item_stocks/*");
            servletHandler.addServletWithMapping(ItemTypesServlet.class, "/api/item_types/*");
            servletHandler.addServletWithMapping(ItemWarehousesServlet.class, "/api/item_warehouses/*");
            servletHandler.addServletWithMapping(ItemsServlet.class, "/api/items/*");
            servletHandler.addServletWithMapping(ItemBrandServlet.class, "/api/item_brand/*");
            servletHandler.addServletWithMapping(InventoryTempServlet.class, "/api/inventory_temp/*");
            servletHandler.addServletWithMapping(ItemGiftCardServlet.class, "/api/item_gift_card/*");
            servletHandler.addServletWithMapping(CustomersServlet.class, "/api/customers/*");
            servletHandler.addServletWithMapping(CustomerCumulatePointServlet.class, "/api/customer_cumulate_point/*");
            servletHandler.addServletWithMapping(EmployeesServlet.class, "/api/employees/*");
            servletHandler.addServletWithMapping(EmployeesDepartmentServlet.class, "/api/employees_department/*");
            servletHandler.addServletWithMapping(OrderCommentsServlet.class, "/api/order_comments/*");
            servletHandler.addServletWithMapping(OrderStatusServlet.class, "/api/order_status/*");
            servletHandler.addServletWithMapping(OrdersServlet.class, "/api/orders/*");
            servletHandler.addServletWithMapping(OrderTablesServlet.class, "/api/order_tables/*");
            servletHandler.addServletWithMapping(PackagesServlet.class, "/api/packages/*");
            servletHandler.addServletWithMapping(CompanyPaymentMethodsServlet.class, "/api/company_payment_methods/*");
            servletHandler.addServletWithMapping(CompanyShippingMethodsServlet.class, "/api/company_shipping_methods/*");
            servletHandler.addServletWithMapping(DeliveryMethodsServlet.class, "/api/delivery_methods/*");
            servletHandler.addServletWithMapping(DeliveryDateSettingServlet.class, "/api/delivery_date_setting/*");
            servletHandler.addServletWithMapping(DeliveryTimeSettingServlet.class, "/api/delivery_time_setting/*");
            servletHandler.addServletWithMapping(PermissionServlet.class, "/api/permission/*");
            servletHandler.addServletWithMapping(ResourcesServlet.class, "/api/resources/*");
            servletHandler.addServletWithMapping(RolesServlet.class, "/api/roles/*");
            servletHandler.addServletWithMapping(UsersServlet.class, "/api/users/*");
            servletHandler.addServletWithMapping(UsersRolesServlet.class, "/api/users_roles/*");
            servletHandler.addServletWithMapping(CartServlet.class, "/api/cart/*");
            servletHandler.addServletWithMapping(FavoriteCartServlet.class, "/api/favorite_cart/*");
            servletHandler.addServletWithMapping(SalesRuleServlet.class, "/api/sales_rule/*");
            servletHandler.addServletWithMapping(CatalogRuleServlet.class, "/api/catalog_rule/*");
            servletHandler.addServletWithMapping(SalesRuleCouponServlet.class, "/api/sales_rule_coupon/*");
            servletHandler.addServletWithMapping(SalesRuleCouponUsageServlet.class, "/api/sales_rule_coupon_usage/*");
            servletHandler.addServletWithMapping(SalesRuleCustomerUsageServlet.class, "/api/sales_rule_customer_usage/*");
            servletHandler.addServletWithMapping(PromotionBannerServlet.class, "/api/promotion_banner/*");
            servletHandler.addServletWithMapping(SalesOrderStatusServlet.class, "/api/sales_order_status/*");
            servletHandler.addServletWithMapping(SalesShipmentServlet.class, "/api/sales_shipment/*");
            servletHandler.addServletWithMapping(SalesShipmentCommentServlet.class, "/api/sales_shipment_comment/*");
            servletHandler.addServletWithMapping(SalesShipmentTrackServlet.class, "/api/sales_shipment_track/*");
            servletHandler.addServletWithMapping(SalesInvoiceServlet.class, "/api/sales_invoice/*");
            servletHandler.addServletWithMapping(SalesInvoicePaymentServlet.class, "/api/sales_invoice_payment/*");
            servletHandler.addServletWithMapping(SalesInvoiceCommentServlet.class, "/api/sales_invoice_comment/*");
            servletHandler.addServletWithMapping(SalesCreditMemoServlet.class, "/api/sales_credit_memo/*");
            servletHandler.addServletWithMapping(SalesCreditMemoCommentServlet.class, "/api/sales_credit_memo_comment/*");
            servletHandler.addServletWithMapping(NewsletterTemplateServlet.class, "/api/newsletter_template/*");
            servletHandler.addServletWithMapping(CoreConfigDataServlet.class, "/api/core_config_data/*");
            servletHandler.addServletWithMapping(TableRateConditionServlet.class, "/api/table_rate_condition/*");
            servletHandler.addServletWithMapping(LanguagesServlet.class, "/api/languages/*");
            servletHandler.addServletWithMapping(ReportInfoServlet.class, "/api/report_info/*");
            servletHandler.addServletWithMapping(CustomerCardServlet.class, "/api/customer_card/*");
            servletHandler.addServletWithMapping(CustomerCardTypeServlet.class, "/api/customer_card_type/*");
            servletHandler.addServletWithMapping(CustomerCumulativePointHistoryServlet.class, "/api/customer_cumulative_point_history/*");
            servletHandler.addServletWithMapping(CustomerRedeemPointHistoryServlet.class, "/api/customer_redeem_point_history/*");
            servletHandler.addServletWithMapping(CustomerCumulativePointHistoryServlet.class, "/api/cumulative_point_history/*");
            servletHandler.addServletWithMapping(CumulativePointFormulaServlet.class, "/api/cumulative_point_formula/*");
            servletHandler.addServletWithMapping(RedeemPointFormulaServlet.class, "/api/redeem_point_formula/*");
            servletHandler.addServletWithMapping(CustomerCumulativeRedeemPointServlet.class, "/api/customer_cumulative_redeem_point/*");
            servletHandler.addServletWithMapping(CmsServlet.class, "/api/cms/*");
            servletHandler.addServletWithMapping(PosDebtVoucherServlet.class, "/api/pos_debt_voucher/*");
            servletHandler.addServletWithMapping(PosCashReceiptPaymentServlet.class, "/api/pos_cash_receipt_payment/*");
            servletHandler.addServletWithMapping(PosCashReceiptServlet.class, "/api/pos_cash_receipt/*");
            servletHandler.addServletWithMapping(PosCashPaymentServlet.class, "/api/pos_cash_payment/*");
            servletHandler.addServletWithMapping(TransferShiftServlet.class, "/api/transfer_shift/*");
            servletHandler.addServletWithMapping(ItemPurchasePriceServlet.class, "/api/item_purchase_price/*");
            servletHandler.addServletWithMapping(CustomerClaimingDebtsServlet.class, "/api/customer_claiming_debts/*");
            servletHandler.addServletWithMapping(ModuleActiveServlet.class, "/api/module_active/*");

            servletHandler.addServletWithMapping(CompanyServlet.class, "/pos/api/company/*");
            servletHandler.addServletWithMapping(CompanyReviewServlet.class, "/pos/api/company_review/*");
            servletHandler.addServletWithMapping(CompanyReviewReplyServlet.class, "/pos/api/company_review_reply/*");
            servletHandler.addServletWithMapping(CompanyCommentServlet.class, "/pos/api/company_comment/*");
            servletHandler.addServletWithMapping(CompanyCommentReplyServlet.class, "/pos/api/company_comment_reply/*");
            servletHandler.addServletWithMapping(CompanyTypeServlet.class, "/pos/api/company_type/*");
            servletHandler.addServletWithMapping(CompanyWorkSheduleServlet.class, "/pos/api/company_work_shedule/*");
            servletHandler.addServletWithMapping(CompanyDevicesServlet.class, "/pos/api/company_devices/*");
            servletHandler.addServletWithMapping(CompanyLocationServlet.class, "/pos/api/company_location/*");
            servletHandler.addServletWithMapping(CompanyTableServlet.class, "/pos/api/company_table/*");
            servletHandler.addServletWithMapping(CompanyTableStaffServlet.class, "/pos/api/company_table_staff/*");
            servletHandler.addServletWithMapping(StoreServlet.class, "/pos/api/store/*");
            servletHandler.addServletWithMapping(MasterDataServlet.class, "/pos/api/master/*");
            servletHandler.addServletWithMapping(ConfigServlet.class, "/pos/api/config/*");
            servletHandler.addServletWithMapping(CurrencyServlet.class, "/pos/api/currency/*");
            servletHandler.addServletWithMapping(CurrencyActivesServlet.class, "/pos/api/currency_actives/*");
            servletHandler.addServletWithMapping(ExchangeRateServlet.class, "/pos/api/exchange_rate/*");
            servletHandler.addServletWithMapping(PaymentExchangeRateServlet.class, "/pos/api/payment_exchange_rate/*");
            servletHandler.addServletWithMapping(MasterGroupServlet.class, "/pos/api/master_group/*");
            servletHandler.addServletWithMapping(UnitServlet.class, "/pos/api/unit/*");
            servletHandler.addServletWithMapping(UnitConvertsServlet.class, "/pos/api/unit_converts/*");
            servletHandler.addServletWithMapping(VendorsServlet.class, "/pos/api/vendors/*");
            servletHandler.addServletWithMapping(ColorsServlet.class, "/pos/api/colors/*");
            servletHandler.addServletWithMapping(CategoriesServlet.class, "/pos/api/categories/*");
            servletHandler.addServletWithMapping(ItemCategoryServlet.class, "/pos/api/item_category/*");
            servletHandler.addServletWithMapping(ItemBranchServlet.class, "/pos/api/item_branch/*");
            servletHandler.addServletWithMapping(ItemMaterialsServlet.class, "/pos/api/item_materials/*");
            servletHandler.addServletWithMapping(ItemSizeServlet.class, "/pos/api/item_size/*");
            servletHandler.addServletWithMapping(ItemStockInDetailsServlet.class, "/pos/api/item_stock_in_details/*");
            servletHandler.addServletWithMapping(ItemStockInTypesServlet.class, "/pos/api/item_stock_in_types/*");
            servletHandler.addServletWithMapping(ItemStockInsServlet.class, "/pos/api/item_stock_ins/*");
            servletHandler.addServletWithMapping(ItemStockOutDetailsServlet.class, "/pos/api/item_stock_out_details/*");
            servletHandler.addServletWithMapping(ItemStockOutTypesServlet.class, "/pos/api/item_stock_out_types/*");
            servletHandler.addServletWithMapping(ItemStockOutsServlet.class, "/pos/api/item_stock_outs/*");
            servletHandler.addServletWithMapping(ItemStocksServlet.class, "/pos/api/item_stocks/*");
            servletHandler.addServletWithMapping(ItemTypesServlet.class, "/pos/api/item_types/*");
            servletHandler.addServletWithMapping(ItemWarehousesServlet.class, "/pos/api/item_warehouses/*");
            servletHandler.addServletWithMapping(ItemsServlet.class, "/pos/api/items/*");
            servletHandler.addServletWithMapping(ItemBrandServlet.class, "/pos/api/item_brand/*");
            servletHandler.addServletWithMapping(InventoryTempServlet.class, "/pos/api/inventory_temp/*");
            servletHandler.addServletWithMapping(ItemGiftCardServlet.class, "/pos/api/item_gift_card/*");
            servletHandler.addServletWithMapping(CustomersServlet.class, "/pos/api/customers/*");
            servletHandler.addServletWithMapping(CustomerCumulatePointServlet.class, "/pos/api/customer_cumulate_point/*");
            servletHandler.addServletWithMapping(EmployeesServlet.class, "/pos/api/employees/*");
            servletHandler.addServletWithMapping(EmployeesDepartmentServlet.class, "/pos/api/employees_department/*");
            servletHandler.addServletWithMapping(OrderCommentsServlet.class, "/pos/api/order_comments/*");
            servletHandler.addServletWithMapping(OrderStatusServlet.class, "/pos/api/order_status/*");
            servletHandler.addServletWithMapping(OrdersServlet.class, "/pos/api/orders/*");
            servletHandler.addServletWithMapping(OrderTablesServlet.class, "/pos/api/order_tables/*");
            servletHandler.addServletWithMapping(PackagesServlet.class, "/pos/api/packages/*");
            servletHandler.addServletWithMapping(CompanyPaymentMethodsServlet.class, "/pos/api/company_payment_methods/*");
            servletHandler.addServletWithMapping(CompanyShippingMethodsServlet.class, "/pos/api/company_shipping_methods/*");
            servletHandler.addServletWithMapping(DeliveryMethodsServlet.class, "/pos/api/delivery_methods/*");
            servletHandler.addServletWithMapping(DeliveryDateSettingServlet.class, "/pos/api/delivery_date_setting/*");
            servletHandler.addServletWithMapping(DeliveryTimeSettingServlet.class, "/pos/api/delivery_time_setting/*");
            servletHandler.addServletWithMapping(PermissionServlet.class, "/pos/api/permission/*");
            servletHandler.addServletWithMapping(ResourcesServlet.class, "/pos/api/resources/*");
            servletHandler.addServletWithMapping(RolesServlet.class, "/pos/api/roles/*");
            servletHandler.addServletWithMapping(UsersServlet.class, "/pos/api/users/*");
            servletHandler.addServletWithMapping(UsersRolesServlet.class, "/pos/api/users_roles/*");
            servletHandler.addServletWithMapping(CartServlet.class, "/pos/api/cart/*");
             servletHandler.addServletWithMapping(FavoriteCartServlet.class, "/pos/api/favorite_cart/*");
            
            servletHandler.addServletWithMapping(SalesRuleServlet.class, "/pos/api/sales_rule/*");
            servletHandler.addServletWithMapping(CatalogRuleServlet.class, "/pos/api/catalog_rule/*");
            servletHandler.addServletWithMapping(SalesRuleCouponServlet.class, "/pos/api/sales_rule_coupon/*");
            servletHandler.addServletWithMapping(SalesRuleCouponUsageServlet.class, "/pos/api/sales_rule_coupon_usage/*");
            servletHandler.addServletWithMapping(SalesRuleCustomerUsageServlet.class, "/pos/api/sales_rule_customer_usage/*");
            servletHandler.addServletWithMapping(PromotionBannerServlet.class, "/pos/api/promotion_banner/*");
            servletHandler.addServletWithMapping(SalesOrderStatusServlet.class, "/pos/api/sales_order_status/*");
            servletHandler.addServletWithMapping(SalesShipmentServlet.class, "/pos/api/sales_shipment/*");
            servletHandler.addServletWithMapping(SalesShipmentCommentServlet.class, "/pos/api/sales_shipment_comment/*");
            servletHandler.addServletWithMapping(SalesShipmentTrackServlet.class, "/pos/api/sales_shipment_track/*");
            servletHandler.addServletWithMapping(SalesInvoiceServlet.class, "/pos/api/sales_invoice/*");
            servletHandler.addServletWithMapping(SalesInvoicePaymentServlet.class, "/pos/api/sales_invoice_payment/*");
            servletHandler.addServletWithMapping(SalesInvoiceCommentServlet.class, "/pos/api/sales_invoice_comment/*");
            servletHandler.addServletWithMapping(SalesCreditMemoServlet.class, "/pos/api/sales_credit_memo/*");
            servletHandler.addServletWithMapping(SalesCreditMemoCommentServlet.class, "/pos/api/sales_credit_memo_comment/*");
            servletHandler.addServletWithMapping(NewsletterTemplateServlet.class, "/pos/api/newsletter_template/*");
            servletHandler.addServletWithMapping(CoreConfigDataServlet.class, "/pos/api/core_config_data/*");
            servletHandler.addServletWithMapping(TableRateConditionServlet.class, "/pos/api/table_rate_condition/*");
            servletHandler.addServletWithMapping(LanguagesServlet.class, "/pos/api/languages/*");
            servletHandler.addServletWithMapping(ReportInfoServlet.class, "/pos/api/report_info/*");
            servletHandler.addServletWithMapping(CustomerCardServlet.class, "/pos/api/customer_card/*");
            servletHandler.addServletWithMapping(CustomerCardTypeServlet.class, "/pos/api/customer_card_type/*");
            servletHandler.addServletWithMapping(CustomerCumulativePointHistoryServlet.class, "/pos/api/customer_cumulative_point_history/*");
            servletHandler.addServletWithMapping(CustomerRedeemPointHistoryServlet.class, "/pos/api/customer_redeem_point_history/*");
            servletHandler.addServletWithMapping(CustomerCumulativePointHistoryServlet.class, "/pos/api/cumulative_point_history/*");
            servletHandler.addServletWithMapping(CumulativePointFormulaServlet.class, "/pos/api/cumulative_point_formula/*");
            servletHandler.addServletWithMapping(RedeemPointFormulaServlet.class, "/pos/api/redeem_point_formula/*");
            servletHandler.addServletWithMapping(CustomerCumulativeRedeemPointServlet.class, "/pos/api/customer_cumulative_redeem_point/*");
            servletHandler.addServletWithMapping(CmsServlet.class, "/pos/api/cms/*");
            servletHandler.addServletWithMapping(PosDebtVoucherServlet.class, "/pos/api/pos_debt_voucher/*");
            servletHandler.addServletWithMapping(PosCashReceiptPaymentServlet.class, "/pos/api/pos_cash_receipt_payment/*");
            servletHandler.addServletWithMapping(PosCashReceiptServlet.class, "/pos/api/pos_cash_receipt/*");
            servletHandler.addServletWithMapping(PosCashPaymentServlet.class, "/pos/api/pos_cash_payment/*");
            servletHandler.addServletWithMapping(TransferShiftServlet.class, "/pos/api/transfer_shift/*");
            servletHandler.addServletWithMapping(ItemPurchasePriceServlet.class, "/pos/api/item_purchase_price/*");
            servletHandler.addServletWithMapping(CustomerClaimingDebtsServlet.class, "/pos/api/customer_claiming_debts/*");
            servletHandler.addServletWithMapping(ModuleActiveServlet.class, "/pos/api/module_active/*");

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
