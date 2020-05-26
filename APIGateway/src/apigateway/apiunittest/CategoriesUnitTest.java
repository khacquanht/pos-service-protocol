package apigateway.apiunittest;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import com.wahsis.common.CommonService;
import com.wahsis.common.DefinedName;

public class CategoriesUnitTest {
//http://localhost:83/api/categories?cm=list&dt={category_id:0}
//http://localhost:83/api/categories?cm=list&dt={category_id:1}
//http://localhost:83/api/categories?cm=list&dt={category_id:1}

    public void addCategories() {
        com.wahsis.items_service.Categories categories = new com.wahsis.items_service.Categories();
        StringBuilder vrfContent = new StringBuilder();
        categories.setCategoryId(1);
        categories.setCategoryName("category_name_ver01");
        categories.setStatus(1);
        categories.setDescription("description_ver01");
        categories.setParentCategoryId(1);
        categories.setCreatedDate("2016-03-16");
        categories.setLastModifiedDate("2016-03-16");
        categories.setCreatedBy(1);
        categories.setLastModifiedBy(1);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.CATEGORIES, categories);
        //com.wahsis.items_service.CategoriesHandler.getInstance().addCategories(dt, vrfContent);
    }

    public void updateCategories() {
        com.wahsis.items_service.Categories categories = new com.wahsis.items_service.Categories();
        StringBuilder vrfContent = new StringBuilder();
        categories.setCategoryId(1);
        categories.setCategoryName("category_name_ver01");
        categories.setStatus(1);
        categories.setDescription("description_ver01");
        categories.setParentCategoryId(1);
        categories.setCreatedDate("2016-03-16");
        categories.setLastModifiedDate("2016-03-16");
        categories.setCreatedBy(1);
        categories.setLastModifiedBy(1);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.CATEGORIES, categories);
        //com.wahsis.items_service.CategoriesHandler.getInstance().updateCategories(dt, vrfContent);
    }
}
