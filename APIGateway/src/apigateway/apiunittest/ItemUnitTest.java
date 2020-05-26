/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apigateway.apiunittest;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;

import com.wahsis.common.CommonService;
import com.wahsis.common.DefinedName;

/**
 *
 * @author khiemnv
 */
public class ItemUnitTest {

    private static final Logger logger = Logger.getLogger(CategoriesUnitTest.class);

    public void addItems() {
        com.wahsis.items_service.Items items = new com.wahsis.items_service.Items();
        StringBuilder vrfContent = new StringBuilder();
        items.setItemId(1);
        items.setSku("sku_ver01");
        items.setServiceName("service_name_ver01");
        items.setShortDescription("short_description_ver01");
        items.setDescription("description_ver01");
        items.setItemImage("item_image_ver01");
        items.setBarcode("barcode_ver01");
        items.setStatus(1);
        items.setUnitPrice(1);
        items.setSpecialPrice(1);
        items.setSpecialPriceFrom("2016-03-16");
        items.setSpecialPriceTo("2016-03-16");
        items.setUnitId(1);
        items.setSizeId(1);
        items.setColorId(1);
        items.setMaterialId(1);
        items.setWeight(1.0);
        items.setHeight(1.0);
        items.setWidth(1.0);
        items.setLength(1);
        items.setBrand("brand_ver01");
        items.setStyle("style_ver01");
        items.setCollection("collection_ver01");
        items.setLabel("label_ver01");
        items.setIsManagedStock(1);
        items.setStockQuantity(1);
        items.setStockStatus(1);
        items.setItemTypeId(1);
        items.setCreatedDate("2016-03-16");
        items.setLastModifiedDate("2016-03-16");
        items.setCreatedBy(1);
        items.setLastModifiedBy(1);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.ITEMS, items);
        //com.wahsis.items_service.ItemsHandler.getInstance().addItems(dt, vrfContent);

    }

    public void updateItems(long id) {
        com.wahsis.items_service.Items items = new com.wahsis.items_service.Items();
        StringBuilder vrfContent = new StringBuilder();
        items.setItemId(id);
        items.setSku("sku_ver01");
        items.setServiceName("service_name_ver01");
        items.setShortDescription("short_description_ver01");
        items.setDescription("description_ver01");
        items.setItemImage("item_image_ver01");
        items.setBarcode("barcode_ver01");
        items.setStatus(1);
        items.setUnitPrice(1);
        items.setSpecialPrice(1);
        items.setSpecialPriceFrom("2016-03-16");
        items.setSpecialPriceTo("2016-03-16");
        items.setUnitId(1);
        items.setSizeId(1);
        items.setColorId(1);
        items.setMaterialId(1);
        items.setWeight(1.0);
        items.setHeight(1.0);
        items.setWidth(1.0);
        items.setLength(1);
        items.setBrand("brand_ver01");
        items.setStyle("style_ver01");
        items.setCollection("collection_ver01");
        items.setLabel("label_ver01");
        items.setIsManagedStock(1);
        items.setStockQuantity(1);
        items.setStockStatus(1);
        items.setItemTypeId(1);
        items.setCreatedDate("2016-03-16");
        items.setLastModifiedDate("2016-03-16");
        items.setCreatedBy(1);
        items.setLastModifiedBy(1);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.ITEMS, items);
        //com.wahsis.items_service.ItemsHandler.getInstance().updateItems(dt, vrfContent);
    }
}
