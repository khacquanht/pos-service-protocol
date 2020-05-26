package apigateway.apiunittest;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import com.wahsis.common.CommonService;
import com.wahsis.common.DefinedName;

public class CompanyDevicesUnitTest {
//http://localhost:83/api/company_devices?cm=list&dt={company_device_id:0}
//http://localhost:83/api/company_devices?cm=list&dt={company_device_id:1}
//http://localhost:83/api/company_devices?cm=list&dt={company_device_id:1}

    public void addCompanyDevices() {
        com.wahsis.company_service.CompanyDevices companydevices = new com.wahsis.company_service.CompanyDevices();
        StringBuilder vrfContent = new StringBuilder();
        companydevices.setCompanyDeviceId(1);
        companydevices.setDeviceId("device_id_ver01");
        companydevices.setDeviceName("device_name_ver01");
        companydevices.setDeviceType("device_type_ver01");
        companydevices.setCreatedDate("2016-03-16");
        companydevices.setLastModifiedDate("2016-03-16");
        companydevices.setCompanyId(1);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.COMPANY_DEVICES, companydevices);
        //com.wahsis.company_service.CompanyDevicesHandler.getInstance().addCompanyDevices(dt, vrfContent);
    }

    public void updateCompanyDevices() {
        com.wahsis.company_service.CompanyDevices companydevices = new com.wahsis.company_service.CompanyDevices();
        StringBuilder vrfContent = new StringBuilder();
        companydevices.setCompanyDeviceId(1);
        companydevices.setDeviceId("device_id_ver01");
        companydevices.setDeviceName("device_name_ver01");
        companydevices.setDeviceType("device_type_ver01");
        companydevices.setCreatedDate("2016-03-16");
        companydevices.setLastModifiedDate("2016-03-16");
        companydevices.setCompanyId(1);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.COMPANY_DEVICES, companydevices);
        //com.wahsis.company_service.CompanyDevicesHandler.getInstance().updateCompanyDevices(dt, vrfContent);
    }
}
