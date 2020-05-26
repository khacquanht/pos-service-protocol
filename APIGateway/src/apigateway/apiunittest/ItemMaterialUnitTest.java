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
public class ItemMaterialUnitTest {

    private static final Logger logger = Logger.getLogger(ItemMaterialUnitTest.class);
//    private ItemMaterialUnitTest categories = new ItemMaterialUnitTest();
//    private static ItemMaterialUnitTest _instance = null;
//    private static final Lock createLock_ = new ReentrantLock();
//    
//    public static ItemMaterialUnitTest getInstance() {
//        if (_instance == null) {
//            createLock_.lock();
//            try {
//                if (_instance == null) {
//                    _instance = new ItemMaterialUnitTest();
//                }
//            } finally {
//                createLock_.unlock();
//            }
//        }
//        return _instance;
//    }
    //http://localhost:83/api/item_materials?cm=list&dt={item_material_id:0}
    //http://localhost:83/api/item_materials?cm=list&dt={item_material_id:1}
    //http://localhost:83/api/item_materials?cm=list&dt={item_material_id:1}

    public void addItemMaterials() {
        com.wahsis.items_service.ItemMaterials itemmaterials = new com.wahsis.items_service.ItemMaterials();
        StringBuilder vrfContent = new StringBuilder();
        itemmaterials.setItemMaterialId(1);
        itemmaterials.setItemMaterialName("item_material_namever01");
        itemmaterials.setCreatedDate("2016-03-16");
        itemmaterials.setLastModifiedDate("2016-03-16");
        itemmaterials.setCreatedBy(1);
        itemmaterials.setLastModifiedBy(1);
        //String cmd = "add";
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.ITEM_MATERIALS, itemmaterials);
        com.wahsis.items_service.ItemMaterialsHandler.getInstance().addItemMaterials(dt, vrfContent);

        logger.info("add ITEM_MATERIALS to sql server..." + dt);
    }

    public void updateItemMaterials() {
        com.wahsis.items_service.ItemMaterials itemmaterials = new com.wahsis.items_service.ItemMaterials();
        StringBuilder vrfContent = new StringBuilder();
        itemmaterials.setItemMaterialId(1);
        itemmaterials.setItemMaterialName("item_material_nameve_r02");
        itemmaterials.setCreatedDate("2016-03-16");
        itemmaterials.setLastModifiedDate("2016-03-16");
        itemmaterials.setCreatedBy(1);
        itemmaterials.setLastModifiedBy(1);
        //String cmd = "add";
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.ITEM_MATERIALS, itemmaterials);
        com.wahsis.items_service.ItemMaterialsHandler.getInstance().updateItemMaterials(dt, vrfContent);

        logger.info("update categories to sql server..." + dt);
    }
}
