package com.wpos.controller;
import com.wahsis.data.CompanyImages;
import com.wahsis.model.CompanyImagesModel;
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

public class CompanyImagesController extends HttpServlet{

	private static final Logger logger = Logger.getLogger(CompanyImagesController.class);
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
			logger.error("CompanyImagesController.handle:" + ex.getMessage(), ex);
		}
	}

	private void processs(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String content = "";
		try {
			String pathInfo = (req.getPathInfo() == null) ? "" : req.getPathInfo();
			Map<String,String[]> mapParams = req.getParameterMap();
			CommonModel.prepareHeader(resp, CommonModel.HEADER_JS);
			pathInfo = pathInfo.toLowerCase();
			switch (pathInfo) {
				case "/add":
					content = com.wahsis.process.CompanyImagesHandler.getInstance().addCompanyImages(mapParams);
					break;
				case "/update":
					content = com.wahsis.process.CompanyImagesHandler.getInstance().updateCompanyImages(mapParams);
					break;
				case "/del":
					content = com.wahsis.process.CompanyImagesHandler.getInstance().deleteCompanyImages(mapParams);
					break;
				case "/detail":
					content = com.wahsis.process.CompanyImagesHandler.getInstance().getCompanyImagesById(mapParams);
					break;
                                case "company":
					content = com.wahsis.process.CompanyImagesHandler.getInstance().getListCompanyImages(mapParams);
					break;
			}
		}
		catch(Exception ex){
			logger.error("CompanyImagesController.processs :" + ex.getMessage(), ex);
		}
		CommonModel.out(content, resp);
	}

}
