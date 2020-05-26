package apigateway.apiunittest;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import com.wahsis.common.CommonService;
import com.wahsis.common.DefinedName;

public class ColorsUnitTest {
//http://localhost:83/api/colors?cm=list&dt={color_id:0}
//http://localhost:83/api/colors?cm=list&dt={color_id:1}
//http://localhost:83/api/colors?cm=list&dt={color_id:1}

    public void addColors() {
        com.wahsis.items_service.Colors colors = new com.wahsis.items_service.Colors();
        StringBuilder vrfContent = new StringBuilder();
        colors.setColorId(1);
        colors.setColorName("color_name_ver01");
        colors.setCreatedDate("2016-03-16");
        colors.setLastModifiedDate("2016-03-16");
        colors.setCreatedBy(1);
        colors.setLastModifiedBy(1);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.COLORS, colors);
        //com.wahsis.items_service.ColorsHandler.getInstance().addColors(dt, vrfContent);
    }

    public void updateColors() {
        com.wahsis.items_service.Colors colors = new com.wahsis.items_service.Colors();
        StringBuilder vrfContent = new StringBuilder();
        colors.setColorId(1);
        colors.setColorName("color_name_ver01");
        colors.setCreatedDate("2016-03-16");
        colors.setLastModifiedDate("2016-03-16");
        colors.setCreatedBy(1);
        colors.setLastModifiedBy(1);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.COLORS, colors);
        //com.wahsis.items_service.ColorsHandler.getInstance().updateColors(dt, vrfContent);
    }
}
