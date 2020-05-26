package apigateway.apiunittest;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import com.wahsis.common.CommonService;
import com.wahsis.common.DefinedName;

public class OrderDetailsUnitTest {
//http://localhost:83/api/order_details?cm=list&dt={order_detail_id:0}
//http://localhost:83/api/order_details?cm=list&dt={order_detail_id:1}
//http://localhost:83/api/order_details?cm=list&dt={order_detail_id:1}

    public void addOrderDetails() {
        com.wahsis.order_service.OrderDetails orderdetails = new com.wahsis.order_service.OrderDetails();
        StringBuilder vrfContent = new StringBuilder();
        orderdetails.setOrderDetailId(1);
        orderdetails.setOrderId(1);
        orderdetails.setItemId(1);
        orderdetails.setItemName("item_name_ver01");
        orderdetails.setItemInfo("item_info_ver01");
        orderdetails.setUnitPrice(1);
        orderdetails.setQuantity(1);
        orderdetails.setAmount(1);
        orderdetails.setDescription("description_ver01");
        //String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.ORDER_DETAILS, orderdetails);
        //com.wahsis.order_service.OrderDetailsHandler.getInstance().addOrderDetails(dt, vrfContent);
    }

    public void updateOrderDetails() {
        com.wahsis.order_service.OrderDetails orderdetails = new com.wahsis.order_service.OrderDetails();
        StringBuilder vrfContent = new StringBuilder();
        orderdetails.setOrderDetailId(1);
        orderdetails.setOrderId(1);
        orderdetails.setItemId(1);
        orderdetails.setItemName("item_name_ver01");
        orderdetails.setItemInfo("item_info_ver01");
        orderdetails.setUnitPrice(1);
        orderdetails.setQuantity(1);
        orderdetails.setAmount(1);
        orderdetails.setDescription("description_ver01");
        //String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.ORDER_DETAILS, orderdetails);
        // com.wahsis.order_service.OrderDetailsHandler.getInstance().updateOrderDetails(dt, vrfContent);
    }
}
