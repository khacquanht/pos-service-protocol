package com.wpos.controller;
import com.wahsis.data.Categories;
import com.wahsis.model.CategoriesModel;
import com.wpos.common.CommonModel;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
/*
*@author khiemnv
*/


public class CategoriesController extends HttpServlet{

	private static final Logger logger = Logger.getLogger(CountryController.class);
	private final Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		handle(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException {
		handle(req, resp);
	}

	private void handle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			processs(req, resp);
		} catch (IOException ex) {
			logger.error("CategoriesController.handle:" + ex.getMessage(), ex);
		}
	}

	private void processs(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String content = "";
		try {
			//String pathInfo = (req.getPathInfo() == null) ? "" : req.getPathInfo();
			//            
			//CommonModel.prepareHeader(resp, CommonModel.HEADER_JS);
			//pathInfo = pathInfo.toLowerCase();
                        
                        String pathInfo = (req.getPathInfo() == null) ? "" : req.getPathInfo();
                        String data = "";
			Map<String,String[]> mapParams = null ; 
			CommonModel.prepareHeader(resp, CommonModel.HEADER_JS);
			pathInfo = pathInfo.toLowerCase();
                        
			switch (pathInfo) {
				case "/add":
                                        data = req.getParameter("data");
					content = com.wahsis.process.CategoriesHandler.getInstance().addCategories(data);
					break;
				case "/update":
                                        data = req.getParameter("data");
					content = com.wahsis.process.CategoriesHandler.getInstance().updateCategories(data);
					break;
				case "/detail":
                                        mapParams = req.getParameterMap();
					content = com.wahsis.process.CategoriesHandler.getInstance().getCategoriesById(mapParams);
					break;
				case "/del":
                                        mapParams = req.getParameterMap();
					content = com.wahsis.process.CategoriesHandler.getInstance().deleteCategories(mapParams);
					break;
				default:
                                        mapParams = req.getParameterMap();
					content = com.wahsis.process.CategoriesHandler.getInstance().getListCategories(mapParams);
					break;
			}
		}
		catch(Exception ex){
			logger.error("CategoriesController.processs :" + ex.getMessage(), ex);
		}
		CommonModel.out(content, resp);
        }
}
