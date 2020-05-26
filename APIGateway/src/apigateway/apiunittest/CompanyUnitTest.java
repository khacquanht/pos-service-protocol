package apigateway.apiunittest;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import com.wahsis.common.CommonService;
import com.wahsis.common.DefinedName;

public class CompanyUnitTest {
//http://localhost:83/api/company?cm=list&dt={company_id:0}
//http://localhost:83/api/company?cm=list&dt={company_id:1}
//http://localhost:83/api/company?cm=list&dt={company_id:1}

    public void addCompany() {
        com.wahsis.company_partnet_service.Company company = new com.wahsis.company_partnet_service.Company();
        StringBuilder vrfContent = new StringBuilder();
        company.setCompanyId(1);
        company.setCompanyCode("company_code_ver01");
        company.setCompanyName("company_name_ver01");
        company.setAddress("address_ver01");
        company.setAddress2("address2_ver01");
        company.setState("state_ver01");
        company.setCity("city_ver01");
        company.setZipCode("zip_code_ver01");
        company.setCountryCode("country_code_ver01");
        company.setProvinceCode("province_code_ver01");
        company.setDictrictCode("dictrict_code_ver01");
        company.setWardsCode("wards_code_ver01");
        company.setLongitude(1);
        company.setLatitude(1);
        company.setPhone1("phone1_ver01");
        company.setPhone2("phone2_ver01");
        company.setCellPhone("cell_phone_ver01");
        company.setFax("fax_ver01");
        company.setEmail("email_ver01");
        company.setVatNumber("vat_number_ver01");
        company.setLogoPath("logo_path_ver01");
        company.setImagesList("images_list_ver01");
        company.setParentCompanyId(1);
        company.setCompanyTypeId(1);
        company.setReviewStarRate(1);
        company.setReviewStarComment("review_star_comment_ver01");
        company.setCreatedDate("2016-03-16");
        company.setLastModifiedDate("2016-03-16");
        company.setCreatedBy(1);
        company.setLastModifiedBy(1);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.COMPANY, company);
        //com.wahsis.company_service.CompanyHandler.getInstance().addCompany(dt, vrfContent);
    }

    public void updateCompany() {
        com.wahsis.company_partnet_service.Company company = new com.wahsis.company_partnet_service.Company();
        StringBuilder vrfContent = new StringBuilder();
        company.setCompanyId(106);
        company.setCompanyCode("company_code_ver02");
        company.setCompanyName("company_name_ver02");
        company.setAddress("address_ver01");
        company.setAddress2("address2_ver01");
        company.setState("state_ver01");
        company.setCity("city_ver01");
        company.setZipCode("zip_code_ver01");
        company.setCountryCode("country_code_ver01");
        company.setProvinceCode("province_code_ver01");
        company.setDictrictCode("dictrict_code_ver01");
        company.setWardsCode("wards_code_ver01");
        company.setLongitude(1);
        company.setLatitude(1);
        company.setPhone1("phone1_ver01");
        company.setPhone2("phone2_ver01");
        company.setCellPhone("cell_phone_ver01");
        company.setFax("fax_ver01");
        company.setEmail("email_ver01");
        company.setVatNumber("vat_number_ver01");
        company.setLogoPath("logo_path_ver01");
        company.setImagesList("images_list_ver01");
        company.setParentCompanyId(1);
        company.setCompanyTypeId(1);
        //company.setReviewStarRate(1);
        //company.setReviewStarComment("review_star_comment_ver01");
        company.setCreatedDate("2016-03-16");
        company.setLastModifiedDate("2016-03-16");
        company.setCreatedBy(1);
        company.setLastModifiedBy(1);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.COMPANY, company);
        //com.wahsis.company_service.CompanyHandler.getInstance().addCompany(dt, vrfContent);
    }
}
