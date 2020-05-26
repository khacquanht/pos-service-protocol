/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wahsis.chat.controller;

import com.wahsis.chat.common.CommonModel;
import com.wahsis.chat.common.CommonFunction;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author diepth
 */
public class UtilitiesController extends HttpServlet {
    
    protected final Logger logger = Logger.getLogger(this.getClass());
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }
    
    private void handle(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRemoteAddr();
            logger.info(getClass().getSimpleName() + ".handle: request from " +  req.getHeader("X-Forwarded-For"));
            processs(req, resp);
        } catch (Exception ex) {
            logger.error(getClass().getSimpleName() + ".handle: " + ex.getMessage(), ex);
        }
    }
    
    private void processs(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        String pathInfo = (req.getPathInfo() == null) ? "" : req.getPathInfo();
        String dt = req.getParameter("dt") != null ? req.getParameter("dt") : "";
        String content = "";
        
        if (pathInfo.endsWith("/") == false) {
            pathInfo += "/";
        }
        
        CommonModel.prepareHeader(resp, CommonModel.HEADER_JS);
        
        switch (pathInfo) {
            case "/gettime/":
                content = CommonFunction.getServerTime();
                break;
            case "ping":
                
                break;
        }
        
        CommonModel.out(content, resp);
    }
}
