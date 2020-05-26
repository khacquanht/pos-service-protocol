package apigateway.apiunittest;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import com.wahsis.common.CommonService;
import com.wahsis.common.DefinedName;

public class UsersUnitTest {
//http://localhost:83/api/users?cm=list&dt={users_id:0}
//http://localhost:83/api/users?cm=list&dt={users_id:1}
//http://localhost:83/api/users?cm=list&dt={users_id:1}

    public void addUsers() {
        com.wahsis.user_service.Users users = new com.wahsis.user_service.Users();
        StringBuilder vrfContent = new StringBuilder();
        users.setUsersId(1);
        users.setUsersName("users_name_ver01");
        users.setPassword("password_ver01");
        users.setEmployeeId(1);
        users.setFullName("full_name_ver01");
        users.setEmailAddress("email_address_ver01");
        users.setPhone("phone_ver01");
        users.setCreatedDate("2016-03-16");
        users.setCreatedBy("created_by_ver01");
        users.setLastModifiedDate("2016-03-16");
        users.setLastModifiedBy("last_modified_by_ver01");
        users.setIsActive(1);
        users.setCpFirstLogin(true);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.USERS, users);
        com.wahsis.user_service.UsersHandler.getInstance().addUsers("2", dt, vrfContent);
    }

    public void updateUsers() {
        com.wahsis.user_service.Users users = new com.wahsis.user_service.Users();
        StringBuilder vrfContent = new StringBuilder();
        users.setUsersId(2);
        users.setUsersName("users_name_ver01");
        users.setPassword("password_ver01");
        users.setEmployeeId(1);
        users.setFullName("full_name_ver01");
        users.setEmailAddress("email_address_ver01");
        users.setPhone("phone_ver01");
        users.setCreatedDate("2016-03-16");
        users.setCreatedBy("created_by_ver01");
        users.setLastModifiedDate("2016-03-16");
        users.setLastModifiedBy("last_modified_by_ver01");
        users.setIsActive(1);
        users.setCpFirstLogin(true);
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.USERS, users);
        com.wahsis.user_service.UsersHandler.getInstance().updateUsers("2", dt, vrfContent);
    }
}
