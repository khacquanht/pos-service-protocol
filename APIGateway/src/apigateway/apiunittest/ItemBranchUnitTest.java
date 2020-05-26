package apigateway.apiunittest;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import com.wahsis.common.CommonService;
import com.wahsis.common.DefinedName;

public class ItemBranchUnitTest {
//http://localhost:83/api/item_branch?cm=list&dt={item_branch_id:0}
//http://localhost:83/api/item_branch?cm=list&dt={item_branch_id:1}
//http://localhost:83/api/item_branch?cm=list&dt={item_branch_id:1}

    public void addItemBranch() {
        com.wahsis.items_service.ItemBranch itembranch = new com.wahsis.items_service.ItemBranch();
        StringBuilder vrfContent = new StringBuilder();
        itembranch.setItemBranchId(1);
        itembranch.setItemId(1);
        itembranch.setBranchId(1);
        itembranch.setItemPrice(1);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.ITEM_BRANCH, itembranch);
        //com.wahsis.items_service.ItemBranchHandler.getInstance().addItemBranch(dt, vrfContent);
    }

    public void updateItemBranch() {
        com.wahsis.items_service.ItemBranch itembranch = new com.wahsis.items_service.ItemBranch();
        StringBuilder vrfContent = new StringBuilder();
        itembranch.setItemBranchId(1);
        itembranch.setItemId(1);
        itembranch.setBranchId(1);
        itembranch.setItemPrice(1);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.ITEM_BRANCH, itembranch);
        //com.wahsis.items_service.ItemBranchHandler.getInstance().updateItemBranch(dt, vrfContent);
    }
}
