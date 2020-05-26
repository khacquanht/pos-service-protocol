package apigateway.service;

import apigateway.common.CommonModel;
import apigateway.common.DefinedName;
import com.wahsis.lib.auth.SessionManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wahsis.user_service.UsersHandler;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khiemnv
 */
public class UsersServlet extends AbstractServletNonAuthenticate {

    @Override
    protected void processs(HttpServletRequest req, HttpServletResponse resp, StringBuilder respContent) {
        try {
            StringBuilder vrfContent = new StringBuilder();
            String cmd = req.getParameter(DefinedName.PARAM_COMMAND);
            String data = req.getParameter(DefinedName.PARAM_DATA);
            if (cmd != null && data != null) {
                UsersHandler.getInstance().process(cmd, data, respContent, vrfContent);
                if (cmd.equals("login")) {

                    String app_id = com.wahsis.common.JsonParserUtil.getProperties(data, "app", "app_id");
                    String app_type_id = com.wahsis.common.JsonParserUtil.getProperties(data, "app", "app_type_id");
                    String users_name = com.wahsis.common.JsonParserUtil.getProperties(data, com.wahsis.common.DefinedName.USERS, "users_name");
                    String company_id = com.wahsis.common.JsonParserUtil.getCompanyId(data);
                    String token = SessionManager.createStaffSession(app_id, app_type_id, company_id, users_name);
                    logger.info(UsersServlet.class.getName() + " create session: " + token);

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
        int ret = 0;
        if (authentication != null && authentication.equals("1")) {
            String cmd = req.getParameter(DefinedName.PARAM_COMMAND);
            if (cmd.equals("login") == false
                    && cmd.equals("send_confirm_key") == false) {
                SessionManager.SessionState state = SessionManager.checkUserSession(req);
                if (state != SessionManager.SessionState.OK) {
                    if (state == SessionManager.SessionState.Invalid) {
                        authenErrorMsg.append("Invalid session");
                        //content = CommonModel.FormatResponse(403, "Invalid session");
                        ret = 403;
                    } else {
                        ret = -1;
                        authenErrorMsg.append("Invalid authenticate");
                    }
                }
            }
        }
        return ret;
        //return 0;
    }
}
