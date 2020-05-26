package apigateway.apiunittest;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import com.wahsis.common.CommonService;
import com.wahsis.common.DefinedName;

public class CompanyTableUnitTest {
//http://localhost:83/api/company_table?cm=list&dt={company_table_id:0}
//http://localhost:83/api/company_table?cm=list&dt={company_table_id:1}
//http://localhost:83/api/company_table?cm=list&dt={company_table_id:1}

    public void addCompanyTable() {
        com.wahsis.company_service.CompanyTable companytable = new com.wahsis.company_service.CompanyTable();
        StringBuilder vrfContent = new StringBuilder();
        companytable.setCompanyTableId(1);
        companytable.setCompanyTableName("company_table_name_ver01");
        companytable.setCompanyLocationId(1);
        companytable.setCreatedDate("2016-03-16");
        companytable.setLastModifiedDate("2016-03-16");
        companytable.setCreatedBy(1);
        companytable.setLastModifiedBy(1);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.COMPANY_TABLE, companytable);
        com.wahsis.company_service.CompanyTableHandler.getInstance().addCompanyTable(dt, vrfContent);
    }

    public void updateCompanyTable() {
        com.wahsis.company_service.CompanyTable companytable = new com.wahsis.company_service.CompanyTable();
        StringBuilder vrfContent = new StringBuilder();
        companytable.setCompanyTableId(1);
        companytable.setCompanyTableName("company_table_name_ver01");
        companytable.setCompanyLocationId(1);
        companytable.setCreatedDate("2016-03-16");
        companytable.setLastModifiedDate("2016-03-16");
        companytable.setCreatedBy(1);
        companytable.setLastModifiedBy(1);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.COMPANY_TABLE, companytable);
        com.wahsis.company_service.CompanyTableHandler.getInstance().updateCompanyTable(dt, vrfContent);
    }
}
