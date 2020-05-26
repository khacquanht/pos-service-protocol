package apigateway.apiunittest;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import com.wahsis.common.CommonService;
import com.wahsis.common.DefinedName;

public class EmployeesUnitTest {
//http://localhost:83/api/employees?cm=list&dt={employee_id:0}
//http://localhost:83/api/employees?cm=list&dt={employee_id:1}
//http://localhost:83/api/employees?cm=list&dt={employee_id:1}

    public void addEmployees() {
        com.wahsis.staff_service.Employees employees = new com.wahsis.staff_service.Employees();
        StringBuilder vrfContent = new StringBuilder();
        employees.setEmployeeId(1);
        employees.setEmployeeName("employee_name_ver01");
        employees.setAddress("address_ver01");
        employees.setAddress2("address2_ver01");
        employees.setState("state_ver01");
        employees.setCity("city_ver01");
        employees.setZipCode("zip_code_ver01");
        employees.setCellPhone("cell_phone_ver01");
        employees.setHomePhone("home_phone_ver01");
        employees.setEmail("email_ver01");
        employees.setDateOfBirth("date_of_birth_ver01");
        employees.setCountryId("country_id_ver01");
        employees.setGender(1);
        employees.setPassportNumber("passport_number_ver01");
        employees.setIdentifyNumber("identify_number_ver01");
        employees.setVisaNumber("visa_number_ver01");
        employees.setDrivingLicenseNumber("driving_license_number_ver01");
        employees.setPersonalImagePath("personal_image_path_ver01");
        employees.setDepartmentId(1);
        employees.setCreatedDate("2016-03-16");
        employees.setLastModifiedDate("2016-03-16");
        employees.setCreatedBy(1);
        employees.setLastModifiedBy(1);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.EMPLOYEES, employees);
        //com.wahsis.staff_service.EmployeesHandler.getInstance().addEmployees(dt, vrfContent);
    }

    public void updateEmployees() {
        com.wahsis.staff_service.Employees employees = new com.wahsis.staff_service.Employees();
        StringBuilder vrfContent = new StringBuilder();
        employees.setEmployeeId(1);
        employees.setEmployeeName("employee_name_ver01");
        employees.setAddress("address_ver01");
        employees.setAddress2("address2_ver01");
        employees.setState("state_ver01");
        employees.setCity("city_ver01");
        employees.setZipCode("zip_code_ver01");
        employees.setCellPhone("cell_phone_ver01");
        employees.setHomePhone("home_phone_ver01");
        employees.setEmail("email_ver01");
        employees.setDateOfBirth("date_of_birth_ver01");
        employees.setCountryId("country_id_ver01");
        employees.setGender(1);
        employees.setPassportNumber("passport_number_ver01");
        employees.setIdentifyNumber("identify_number_ver01");
        employees.setVisaNumber("visa_number_ver01");
        employees.setDrivingLicenseNumber("driving_license_number_ver01");
        employees.setPersonalImagePath("personal_image_path_ver01");
        employees.setDepartmentId(1);
        employees.setCreatedDate("2016-03-16");
        employees.setLastModifiedDate("2016-03-16");
        employees.setCreatedBy(1);
        employees.setLastModifiedBy(1);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.EMPLOYEES, employees);
        com.wahsis.staff_service.EmployeesHandler.getInstance().updateEmployees(dt, vrfContent);
    }
}
