/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apigatewaywahsis.service;

import apigatewaywahsis.common.CommonModel;
import apigatewaywahsis.common.DefinedName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.service_wahsis.company_service.CompanyHandler;
import com.wahsis.lib.auth.SessionManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import com.wahsis.lib.auth.SessionManager;

/**
 *
 * @author diepth
 */
public class CompanyServlet extends AbstractServletNonAuthenticate {

    @Override
    protected void processs(HttpServletRequest req, HttpServletResponse resp, StringBuilder respContent) {

        try {
            StringBuilder vrfContent = new StringBuilder();
            String cmd = req.getParameter(DefinedName.PARAM_COMMAND);
            String data = req.getParameter(DefinedName.PARAM_DATA);
            if (cmd != null && data != null) {
                CompanyHandler.getInstance().process(cmd, data, respContent, vrfContent);
            } else {
                respContent.append(CommonModel.toJSON(-1, DefinedName.RESP_MSG_INVALID_REQUEST));
            }

        } catch (Exception ex) {
            Logger.getLogger(CompanyServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected int authenticate(HttpServletRequest req, StringBuilder authenErrorMsg) {
        int ret = 0;
        if (authentication != null && authentication.equals("1")) {
            String cmd = req.getParameter(DefinedName.PARAM_COMMAND);
            if (cmd.equals("get_by_code") == false && 
                   cmd.equals("detail") == false) {
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
    //
}
