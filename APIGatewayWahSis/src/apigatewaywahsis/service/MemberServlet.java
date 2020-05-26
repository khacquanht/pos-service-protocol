/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apigatewaywahsis.service;

import apigatewaywahsis.common.DefinedName;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author diepth
 */
public class MemberServlet extends AbstractServlet {

    @Override
    protected void processs(HttpServletRequest req, HttpServletResponse resp, StringBuilder respContent) {

        try {
            return;
        } catch (Exception ex) {
            Logger.getLogger(MemberServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
