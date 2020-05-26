package apigateway.service;

import apigateway.common.CommonModel;
import apigateway.common.DefinedName;
import com.wahsis.lib.auth.SessionManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wahsis.module_service.ModuleActiveHandler;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khiemnv
 */
public class ModuleActiveServlet extends AbstractServletNonAuthenticate {

    @Override
    protected void processs(HttpServletRequest req, HttpServletResponse resp, StringBuilder respContent) {
        try {
            StringBuilder vrfContent = new StringBuilder();
            String cmd = req.getParameter(DefinedName.PARAM_COMMAND);
            String data = req.getParameter(DefinedName.PARAM_DATA);
            if (cmd != null && data != null) {
                ModuleActiveHandler.getInstance().process(cmd, data, respContent, vrfContent);
            } else {
                respContent.append(CommonModel.toJSON(-1, DefinedName.RESP_MSG_INVALID_REQUEST));
            }
        } catch (Exception ex) {
            Logger.getLogger(ModuleActiveServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected int authenticate(HttpServletRequest req, StringBuilder authenErrorMsg) {
        int ret = 0;

        //String data = req.getParameter(DefinedName.PARAM_DATA);
        //if (cmd != null && data != null) {
        if (authentication != null && authentication.equals("1")) {
            String cmd = req.getParameter(DefinedName.PARAM_COMMAND);
            if (cmd.equals("search") == false) {
                SessionManager.SessionState state = SessionManager.checkUserSession(req);
                if (state != SessionManager.SessionState.OK) {
                    if (state == SessionManager.SessionState.Invalid) {
                        ret = 403;
                        authenErrorMsg.append("Invalid session");
                        //content = CommonModel.FormatResponse(403, "Invalid session");

                    } else {
                        ret = -1;
                        authenErrorMsg.append("Invalid authenticate");
                        //content = CommonModel.FormatResponse(-1, "Error authenticate");
                    }
                    //CommonModel.out(content, resp);
                }
            }
        }
        //}
        return ret;
    }
}
