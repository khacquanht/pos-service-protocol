/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apigatewaywahsis.service;

import apigatewaywahsis.common.CommonModel;
import apigatewaywahsis.common.DefinedName;
import com.service_wahsis.master_service.CountryHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wahsis.lib.auth.SessionManager;
import org.apache.log4j.Priority;

/**
 *
 * @author diepth
 */
public class CountryServlet extends AbstractServlet {

    @Override
    protected void processs(HttpServletRequest req, HttpServletResponse resp, StringBuilder respContent) {

        try {
            StringBuilder vrfContent = new StringBuilder();
            String cmd = req.getParameter(DefinedName.PARAM_COMMAND);
            String data = req.getParameter(DefinedName.PARAM_DATA);
            if (cmd != null && data != null) {
                // SessionManager.SessionState sesState = SessionManager.checkStaffSession(req);
//                SessionManager.SessionState sesState = SessionManager.SessionState.OK;
//                if (sesState == SessionManager.SessionState.OK) 
//                    logger.info(CompanyServlet.class.getName() + " session is OK");
//                else if (sesState == SessionManager.SessionState.Invalid) 
//                    logger.info(CompanyServlet.class.getName() + " session is invalid");
//                else
//                    logger.info(CompanyServlet.class.getName() + " check session error");
//                
                CountryHandler.getInstance().process(cmd, data, respContent, vrfContent);
            } else {
                respContent.append(CommonModel.toJSON(-1, DefinedName.RESP_MSG_INVALID_REQUEST));
            }

        } catch (Exception ex) {
            Logger.getLogger(CompanyServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
