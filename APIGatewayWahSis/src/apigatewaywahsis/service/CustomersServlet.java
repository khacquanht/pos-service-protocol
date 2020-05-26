package apigatewaywahsis.service;

import apigatewaywahsis.common.CommonModel;
import apigatewaywahsis.common.DefinedName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.service_wahsis.customer_service.CustomersHandler;
import com.wahsis.lib.auth.SessionManager;
import com.service_wahsis.user_service.UsersHandler;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khiemnv
 */
public class CustomersServlet extends AbstractServlet {

    @Override
    protected void processs(HttpServletRequest req, HttpServletResponse resp, StringBuilder respContent) {
        try {
            StringBuilder vrfContent = new StringBuilder();
            String cmd = req.getParameter(DefinedName.PARAM_COMMAND);
            String data = req.getParameter(DefinedName.PARAM_DATA);
            if (cmd != null && data != null) {
                CustomersHandler.getInstance().process(cmd, data, respContent, vrfContent);
                if (cmd.equals("login")) {
                    String token = "";
                    String err = com.service_wahsis.common.JsonParserUtil.getProperties(vrfContent.toString(), "err");
                    if (err != null && err.equals("0")) {
                        String app_id = com.service_wahsis.common.JsonParserUtil.getProperties(data, "app", "app_id");
                        String app_type_id = com.service_wahsis.common.JsonParserUtil.getProperties(data, "app", "app_type_id");
                        String cell_phone = com.service_wahsis.common.JsonParserUtil.getProperties(data, com.service_wahsis.common.DefinedName.CUSTOMERS, "cell_phone");
                        String company_id = com.service_wahsis.common.JsonParserUtil.getCompanyId(data);
                        token = SessionManager.createStaffSession(app_id, app_type_id, company_id, cell_phone);
                    }
                    //logger.info(UsersServlet.class.getName() + " create session: " + token);
                    if (!token.equals("")) {
                        String respNew = CommonModel.appendDataResponse(respContent.toString(), "token", token);
                        if (!respNew.equals("")) {
                            respContent.delete(0, respContent.length());
                            respContent.append(respNew);
                        }
                    }
                }
            } else {
                respContent.append(CommonModel.toJSON(-1, DefinedName.RESP_MSG_INVALID_REQUEST));
            }
        } catch (Exception ex) {
            Logger.getLogger(UsersServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*
    dev.wahsis.net/api/customers?cm=login
    dev.wahsis.net/api/customers?cm=change_password
    dev.wahsis.net/api/customers?cm=check_installed_app
     */
    @Override
    protected int authenticate(HttpServletRequest req, StringBuilder authenErrorMsg) {
        int ret = 0;
        if (authentication != null && authentication.equals("1")) {
            String cmd = req.getParameter(DefinedName.PARAM_COMMAND);
            if (cmd.equals("login") == false 
                    && cmd.equals("change_password") == false 
                    && cmd.equals("check_installed_app") == false) {
                SessionManager.SessionState state = SessionManager.checkUserSession(req);
                if (state != SessionManager.SessionState.OK) {
                    if (state == SessionManager.SessionState.Invalid) {
                        authenErrorMsg.append("Invalid session");
                        ret = 403;
                    } else {
                        ret = -1;
                        authenErrorMsg.append("Invalid authenticate");

                    }
                }
            }
        }
        return ret;
    }
}
