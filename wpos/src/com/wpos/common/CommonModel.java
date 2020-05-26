package com.wpos.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author duclm2
 */
public class CommonModel {

    public static final byte HEADER_HTML = 0;
    public static final byte HEADER_JS = 1;
    private static final Logger logger = Logger.getLogger(CommonModel.class);

    
    public static void out(String content, HttpServletResponse respon) throws IOException {
        PrintWriter out = respon.getWriter();
        out.print(content);
    }
    
    
    public static boolean checkAuth(String tk,String param1, String param2)
    {
        try
        {
            String result = CSMEncryption.GenToken.CryptGenToken(param1, param2);
            return result.equals(tk);
        }
        catch(Exception ex)
        {
            logger.error("CommonModel.checkAuth : " + ex.getMessage(),ex);
            return false;
        }
    }
    
    public static String getTimeStamp() 
    {
        long unixTime = System.currentTimeMillis() / 1000L;
        return String.valueOf(unixTime);
    }

    public static void prepareHeader(HttpServletResponse resp, byte type) {
        resp.setCharacterEncoding("utf-8");
        if (type == HEADER_HTML) {
            resp.setContentType("text/html; charset=utf-8");
        } else if (type == HEADER_JS) {
            resp.setContentType("text/javascript; charset=utf-8");
        }
        String appName = Config.getParam("static", "app_name");
        resp.addHeader("Server", appName);
    }
    
    public static String toJSON(int error, String msg, Map data) {
        Map mapdata = new HashMap();
        JSONObject ldata = new JSONObject();
        mapdata.put("err", error);
        mapdata.put("msg", msg);
        mapdata.put("data", data);
        ldata.putAll(mapdata);
        return ldata.toJSONString();
    }

    public static String toJSON(int error, String msg, String data) {
        Map mapdata = new HashMap();
        JSONObject ldata = new JSONObject();
        mapdata.put("err", error);
        mapdata.put("msg", msg);
        mapdata.put("data", data);
        ldata.putAll(mapdata);
        return ldata.toJSONString();
    }

    public static String toJSON(int error, String msg) {
        Map mapdata = new HashMap();
        JSONObject ldata = new JSONObject();
        mapdata.put("err", error);
        mapdata.put("msg", msg);
        ldata.putAll(mapdata);
        return ldata.toJSONString();
    }

    public static String toJSON(int error, String msg, List<Map> data) {
        Map mapdata = new HashMap();
        JSONObject ldata = new JSONObject();
        mapdata.put("err", error);
        mapdata.put("msg", msg);
        mapdata.put("data", data);
        ldata.putAll(mapdata);
        return ldata.toJSONString();
    }
}
