package apigateway.apiunittest;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import com.wahsis.common.CommonService;
import com.wahsis.common.DefinedName;

public class CompanyLocationUnitTest {
//http://localhost:83/api/company_location?cm=list&dt={company_location_id:0}
//http://localhost:83/api/company_location?cm=list&dt={company_location_id:1}
//http://localhost:83/api/company_location?cm=list&dt={company_location_id:1}

    public void addCompanyLocation() {
        com.wahsis.company_service.CompanyLocation companylocation = new com.wahsis.company_service.CompanyLocation();
        StringBuilder vrfContent = new StringBuilder();
        companylocation.setCompanyLocationId(1);
        companylocation.setCompanyLocationName("company_location_name_ver01");
        companylocation.setBranchId(1);
        companylocation.setCreatedDate("2016-03-16");
        companylocation.setLastModifiedDate("2016-03-16");
        companylocation.setCreatedBy(1);
        companylocation.setLastModifiedBy(1);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.COMPANY_LOCATION, companylocation);
        com.wahsis.company_service.CompanyLocationHandler.getInstance().addCompanyLocation(dt, vrfContent);
    }

    public void updateCompanyLocation() {
        com.wahsis.company_service.CompanyLocation companylocation = new com.wahsis.company_service.CompanyLocation();
        StringBuilder vrfContent = new StringBuilder();
        companylocation.setCompanyLocationId(1);
        companylocation.setCompanyLocationName("company_location_name_ver01");
        companylocation.setBranchId(1);
        companylocation.setCreatedDate("2016-03-16");
        companylocation.setLastModifiedDate("2016-03-16");
        companylocation.setCreatedBy(1);
        companylocation.setLastModifiedBy(1);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.COMPANY_LOCATION, companylocation);
        com.wahsis.company_service.CompanyLocationHandler.getInstance().updateCompanyLocation(dt, vrfContent);
    }
}
