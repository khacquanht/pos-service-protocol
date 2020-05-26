/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wpos.controller;

import com.google.gson.Gson;
import com.wahsis.data.CompanyType;
import com.wahsis.model.CompanyTypeModel;
import com.wpos.common.CommonModel;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author duclm2
 * @version 1.0
 * @date 19-02-2016
 */
public class CompanyTypeController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(CompanyTypeController.class);
    private final Gson gson = new Gson();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        handle(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException {
        handle(req, resp);
    }

    private void handle(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        try {
            processs(req, resp);
            
        } catch (IOException ex) {
            logger.error("CompanyTypeController.handle:" + ex.getMessage(), ex);
        }
    }
     
    private void processs(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String content = "";
        try{
        String pathInfo = (req.getPathInfo() == null) ? "" : req.getPathInfo();
        Map<String,String[]> mapParams = req.getParameterMap();
        CommonModel.prepareHeader(resp, CommonModel.HEADER_JS);
        pathInfo = pathInfo.toLowerCase();
        switch (pathInfo) {
            case "/add":
                content = com.wahsis.process.CompanyTypeHandler.getInstance().addCompanyType(mapParams);
                break;
            case "/update":
                 content = com.wahsis.process.CompanyTypeHandler.getInstance().updateCompanyType(mapParams);
                break;    
            case "/detail":
                 content = com.wahsis.process.CompanyTypeHandler.getInstance().getCompanyTypeById(mapParams);
                break;    
            case "/del":
                 content = com.wahsis.process.CompanyTypeHandler.getInstance().deleteCompanyType(mapParams);
                break;   
            
            default:
                content = com.wahsis.process.CompanyTypeHandler.getInstance().getListCompanyType(mapParams);
                break;
        }
        }
        catch(Exception ex){
            logger.error("CompanyTypeController.processs:" + ex.getMessage(), ex);
        }
        CommonModel.out(content, resp);
    }
     
}
