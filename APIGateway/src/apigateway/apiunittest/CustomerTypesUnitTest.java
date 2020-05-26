package apigateway.apiunittest;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import com.wahsis.common.CommonService;
import com.wahsis.common.DefinedName;
public class CustomerTypesUnitTest 
{
//http://localhost:83/api/customer_types?cm=list&dt={customer_type_id:0}
//http://localhost:83/api/customer_types?cm=list&dt={customer_type_id:1}
//http://localhost:83/api/customer_types?cm=list&dt={customer_type_id:1}
public void addCustomerTypes(){
	com.wahsis.customer_service.CustomerTypes customertypes = new com.wahsis.customer_service.CustomerTypes();
	StringBuilder vrfContent = new StringBuilder();
	customertypes.setCustomerTypeId(1);
	customertypes.setCustomerTypesName("customer_types_name_ver01");
	customertypes.setCreatedDate("2016-03-16");
	customertypes.setLastModifiedDate("2016-03-16");
	customertypes.setCreatedBy(1);
	customertypes.setLastModifiedBy(1);
	String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.CUSTOMER_TYPES, customertypes);
	com.wahsis.customer_service.CustomerTypesHandler.getInstance().addCustomerTypes(dt, vrfContent);
}
public void updateCustomerTypes(){
	com.wahsis.customer_service.CustomerTypes customertypes = new com.wahsis.customer_service.CustomerTypes();
	StringBuilder vrfContent = new StringBuilder();
	customertypes.setCustomerTypeId(16);
	customertypes.setCustomerTypesName("customer_types_name_ver_02");
	customertypes.setCreatedDate("2016-03-16");
	customertypes.setLastModifiedDate("2016-03-16");
	customertypes.setCreatedBy(1);
	customertypes.setLastModifiedBy(1);
	String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.CUSTOMER_TYPES, customertypes);
	com.wahsis.customer_service.CustomerTypesHandler.getInstance().updateCustomerTypes(dt, vrfContent);
}}
