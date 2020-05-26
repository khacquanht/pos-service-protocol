/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apigateway.service;

import apigateway.common.CommonModel;
import apigateway.common.Config;
import apigateway.common.DefinedName;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.wahsis.lib.auth.SessionManager;

/**
 *
 * @author diepth
 */
public abstract class AbstractServlet extends HttpServlet {

    protected final Logger logger = Logger.getLogger(this.getClass());
    protected final String authentication = Config.getParameter("config_authenticate", "authenticate");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommonModel.prepareHeader(resp, CommonModel.HEADER_JS);
        resp.setStatus(200);
    }

    private void handle(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String content = new String();
            StringBuilder authenErrorMsg = new StringBuilder();
            int ret;
            CommonModel.prepareHeader(resp, CommonModel.HEADER_JS);
            ret = authenticate(req, authenErrorMsg);
            if (ret != 0) {
                content = CommonModel.toJSON(ret, DefinedName.RESP_MSG_INVALID_REQUEST, authenErrorMsg.toString());
            } else {
                StringBuilder contentBuilder = new StringBuilder();
                processs(req, resp, contentBuilder);
                content = contentBuilder.toString();
            }

            CommonModel.out(content, resp);
        } catch (Exception ex) {
            logger.error(getClass().getSimpleName() + ".handle: " + ex.getMessage(), ex);
        }
    }

    protected int authenticate(HttpServletRequest req, StringBuilder authenErrorMsg) {
        int ret = 0;
        if (authentication != null && authentication.equals("1")) {
            SessionManager.SessionState state = SessionManager.checkUserSession(req);
            if (state != SessionManager.SessionState.OK) {
                if (state == SessionManager.SessionState.Invalid) {
                    ret = 403;
                    authenErrorMsg.append("Invalid session");
                } else {
                    ret = -1;
                    authenErrorMsg.append("Invalid authenticate");
                }
            }
        }
        return ret;
    }

    protected abstract void processs(HttpServletRequest req, HttpServletResponse resp, StringBuilder respContent);
}

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package apigateway.service;
//
//import apigateway.common.CommonModel;
//import apigateway.common.DefinedName;
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.apache.log4j.Logger;
//
///**
// *
// * @author diepth
// */
//public abstract class AbstractServlet extends HttpServlet {
//
//    protected final Logger logger = Logger.getLogger(this.getClass());
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        handle(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        handle(req, resp);
//    }
//
//    private void handle(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            String content = new String();
//            String authenErrorMsg = new String();
//            int ret;
//
//            CommonModel.prepareHeader(resp, CommonModel.HEADER_JS);
//            ret = authenticate(req, authenErrorMsg);
//            if (ret != 0) {
//                content = CommonModel.toJSON(ret, DefinedName.RESP_MSG_INVALID_REQUEST, authenErrorMsg);
//            } else {
//                StringBuilder contentBuilder = new StringBuilder();
//                processs(req, resp, contentBuilder);
//                content = contentBuilder.toString();
//            }
//
//            CommonModel.out(content, resp);
//        } catch (Exception ex) {
//            logger.error(getClass().getSimpleName() + ".handle: " + ex.getMessage(), ex);
//        }
//    }
//
//    protected int authenticate(HttpServletRequest req, String authenErrorMsg) {
//        return 0;
//    }
//
//    protected abstract void processs(HttpServletRequest req, HttpServletResponse resp, StringBuilder respContent);
//}
