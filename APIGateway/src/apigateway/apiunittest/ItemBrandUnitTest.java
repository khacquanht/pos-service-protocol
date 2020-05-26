package apigateway.apiunittest;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import com.wahsis.common.CommonService;
import com.wahsis.common.DefinedName;

public class ItemBrandUnitTest {
//http://localhost:83/api/item_brand?cm=list&dt={item_brand_id:0}
//http://localhost:83/api/item_brand?cm=list&dt={item_brand_id:1}
//http://localhost:83/api/item_brand?cm=list&dt={item_brand_id:1}

    public void addItemBrand() {
        com.wahsis.items_service.ItemBrand itembrand = new com.wahsis.items_service.ItemBrand();
        StringBuilder vrfContent = new StringBuilder();
        itembrand.setItemBrandId(1);
        itembrand.setBrandName("brand_name_ver01");
        itembrand.setCreatedDate("2016-03-16");
        itembrand.setLastModifiedDate("2016-03-16");
        itembrand.setCreatedBy(1);
        itembrand.setLastModifiedBy(1);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.ITEM_BRAND, itembrand);
        com.wahsis.items_service.ItemBrandHandler.getInstance().addItemBrand(dt, vrfContent);
    }

    public void updateItemBrand() {
        com.wahsis.items_service.ItemBrand itembrand = new com.wahsis.items_service.ItemBrand();
        StringBuilder vrfContent = new StringBuilder();
        itembrand.setItemBrandId(1);
        itembrand.setBrandName("brand_name_ver01");
        itembrand.setCreatedDate("2016-03-16");
        itembrand.setLastModifiedDate("2016-03-16");
        itembrand.setCreatedBy(1);
        itembrand.setLastModifiedBy(1);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.ITEM_BRAND, itembrand);
        com.wahsis.items_service.ItemBrandHandler.getInstance().updateItemBrand(dt, vrfContent);
    }
}
