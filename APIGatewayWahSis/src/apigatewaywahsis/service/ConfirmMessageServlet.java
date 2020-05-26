package apigatewaywahsis.service;

import apigatewaywahsis.common.CommonModel;
import apigatewaywahsis.common.DefinedName;
import com.wahsis.lib.auth.SessionManager;
import com.service_wahsis.user_service.UsersHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.service_wahsis.utility_service.ConfirmMessageHandler;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khiemnv
 */
public class ConfirmMessageServlet extends AbstractServletNonAuthenticate {

    @Override
    protected void processs(HttpServletRequest req, HttpServletResponse resp, StringBuilder respContent) {
//        try {
//            StringBuilder vrfContent = new StringBuilder();
//            String cmd = req.getParameter(DefinedName.PARAM_COMMAND);
//            String data = req.getParameter(DefinedName.PARAM_DATA);
//            if (cmd != null && data != null) {
//                ConfirmMessageHandler.getInstance().process(cmd, data, respContent, vrfContent);
//            } else {
//                respContent.append(CommonModel.toJSON(-1, DefinedName.RESP_MSG_INVALID_REQUEST));
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(ConfirmMessageServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try {
            StringBuilder vrfContent = new StringBuilder();
            String cmd = req.getParameter(DefinedName.PARAM_COMMAND);
            String data = req.getParameter(DefinedName.PARAM_DATA);
            if (cmd != null && data != null) {
                ConfirmMessageHandler.getInstance().process(cmd, data, respContent, vrfContent);
                if (cmd.equals("valid") || 
                        cmd.equals("valid_staff") || 
                        cmd.equals("valid_staff_new") ||
                        cmd.equals("valid_boss") ||
                        cmd.equals("valid_customer")) {
                    String token = "";
                    String err = com.service_wahsis.common.JsonParserUtil.getProperties(vrfContent.toString(), "err");
                    if (err != null && err.equals("0")) {
                        String app_id = com.service_wahsis.common.JsonParserUtil.getProperties(data, "app", "app_id");
                        String app_type_id = com.service_wahsis.common.JsonParserUtil.getProperties(data, "app", "app_type_id");
                        String customer_id = com.service_wahsis.common.JsonParserUtil.getProperties(data, com.service_wahsis.common.DefinedName.CUSTOMERS, com.service_wahsis.common.DefinedName.CUSTOMER_ID);
                        //String company_id = com.wahsis.common.JsonParserUtil.getCompanyId(data);
                        token = SessionManager.createUserSession(app_id, app_type_id, customer_id);
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

    @Override
    protected int authenticate(HttpServletRequest req, StringBuilder authenErrorMsg) {

        //valid
        //valid_staff
        //valid_customer
        return 0;
    }
}
