/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apigateway.service;

import apigateway.common.CommonModel;
import apigateway.common.DefinedName;
import com.wahsis.master_service.CountryHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author diepth
 */
public class CountryServlet extends AbstractServlet{
    
    @Override
     protected void processs(HttpServletRequest req, HttpServletResponse resp, StringBuilder respContent) {
         
        

        try {
            StringBuilder vrfContent = new StringBuilder(); 
            String cmd = req.getParameter(DefinedName.PARAM_COMMAND);
            String data = req.getParameter(DefinedName.PARAM_DATA);
            if (cmd != null && data != null)
                CountryHandler.getInstance().process(cmd, data, respContent, vrfContent);
            else
                respContent.append(CommonModel.toJSON(-1, DefinedName.RESP_MSG_INVALID_REQUEST));
            
        } catch (Exception ex) {
            Logger.getLogger(CompanyServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    @Override
    protected int authenticate(HttpServletRequest req, String authenErrorMsg) 
    {        
        return 0;
    }
}
