package apigateway.apiunittest;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import com.wahsis.common.CommonService;
import com.wahsis.common.DefinedName;

public class CustomersUnitTest {
//http://localhost:83/api/customers?cm=list&dt={customer_id:0}
//http://localhost:83/api/customers?cm=list&dt={customer_id:1}
//http://localhost:83/api/customers?cm=list&dt={customer_id:1}

    public void addCustomers() {
        com.wahsis.customer_service.Customers customers = new com.wahsis.customer_service.Customers();
        StringBuilder vrfContent = new StringBuilder();
        customers.setCustomerId(1);
        customers.setCustomerName("customer_name_ver01");
        customers.setAddress("address_ver01");
        customers.setAddress2("address2_ver01");
        customers.setState("state_ver01");
        customers.setCity("city_ver01");
        customers.setZipCode("zip_code_ver01");
        customers.setCellPhone("cell_phone_ver01");
        customers.setHomePhone("home_phone_ver01");
        customers.setEmail("email_ver01");
        customers.setDateOfBirth("date_of_birth_ver01");
        customers.setCountryId("country_id_ver01");
        customers.setGender(1);
        customers.setPassportNumber("passport_number_ver01");
        customers.setIdentifyNumber("identify_number_ver01");
        customers.setVisaNumber("visa_number_ver01");
        customers.setDrivingLicenseNumber("driving_license_number_ver01");
        customers.setPersonalImagePath("personal_image_path_ver01");
        customers.setCustomerTypeId(1);
        customers.setCreatedDate("2016-03-16");
        customers.setLastModifiedDate("2016-03-16");
        customers.setCreatedBy(1L);
        customers.setLastModifiedBy(0L);
        customers.setImei("imei_ver01");
        customers.setIsActive(true);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.WAHSIS_CUSTOMERS, customers);
        // com.wahsis.customer_service.CustomersHandler.getInstance().addCustomers(dt, vrfContent);
    }

    public void updateCustomers() {
        com.wahsis.customer_service.Customers customers = new com.wahsis.customer_service.Customers();
        StringBuilder vrfContent = new StringBuilder();
        customers.setCustomerId(1);
        customers.setCustomerName("customer_name_ver01");
        customers.setAddress("address_ver01");
        customers.setAddress2("address2_ver01");
        customers.setState("state_ver01");
        customers.setCity("city_ver01");
        customers.setZipCode("zip_code_ver01");
        customers.setCellPhone("cell_phone_ver01");
        customers.setHomePhone("home_phone_ver01");
        customers.setEmail("email_ver01");
        customers.setDateOfBirth("date_of_birth_ver01");
        customers.setCountryId("country_id_ver01");
        customers.setGender(1);
        customers.setPassportNumber("passport_number_ver01");
        customers.setIdentifyNumber("identify_number_ver01");
        customers.setVisaNumber("visa_number_ver01");
        customers.setDrivingLicenseNumber("driving_license_number_ver01");
        customers.setPersonalImagePath("personal_image_path_ver01");
        customers.setCustomerTypeId(1);
        customers.setCreatedDate("2016-03-16");
        customers.setLastModifiedDate("2016-03-16");
        customers.setCreatedBy(0L);
        customers.setLastModifiedBy(0L);
        customers.setImei("imei_ver01");
        customers.setIsActive(false);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.WAHSIS_CUSTOMERS, customers);
        // com.wahsis.customer_service.CustomersHandler.getInstance().updateCustomers(dt, vrfContent);
    }
}
