package apigateway.common;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author diepth
 */
public class CommonModel {

    public static final byte HEADER_HTML = 0;
    public static final byte HEADER_JS = 1;
    public static final byte HEADER_TEXT_PLAIN = 2;
    private static final Gson _gson = new Gson();

    //https://jvaneyck.wordpress.com/2014/01/07/cross-domain-requests-in-javascript/
//    public static void prepareHeader(HttpServletResponse resp, byte type) {
//        resp.setCharacterEncoding("utf-8");
//        if (type == HEADER_HTML) {
//            resp.setContentType("text/html; charset=utf-8");
//        } else if (type == HEADER_JS) {
//            resp.setContentType("text/javascript; charset=utf-8");
//        } else if (type == HEADER_TEXT_PLAIN) {
//            resp.setContentType("text/plain; charset=utf-8");
//        }
//        String appName = Config.getParameter("static", "app_name");
//        resp.addHeader("Server", appName);
//        resp.setHeader("Access-Control-Allow-Origin", "*");
//        //resp.setHeader("Access-Control-Allow-Methods", "GET POST");
//        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
//        //resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
//        resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
//        resp.setHeader("Access-Control-Max-Age", "86400");
//    }

    public static void prepareHeader(HttpServletResponse resp, byte type) {
        resp.setCharacterEncoding("utf-8");
        if (type == HEADER_HTML) {
            resp.setContentType("text/html; charset=utf-8");
        } else if (type == HEADER_JS) {
            resp.setContentType("text/javascript; charset=utf-8");
        } else if (type == HEADER_TEXT_PLAIN) {
            resp.setContentType("text/plain; charset=utf-8");
        }
        String appName = Config.getParameter("static", "app_name");
        resp.addHeader("Server", appName);
        resp.setHeader("Access-Control-Allow-Origin", "*");
        //resp.setHeader("Access-Control-Allow-Methods", "GET POST");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        //resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        resp.setHeader("Access-Control-Max-Age", "86400");
    }

    public static void out(String content, HttpServletResponse respon) throws IOException {
        PrintWriter out = respon.getWriter();
        out.print(content);
    }

    public static String toJSON(int error, String msg, Map data) {
        Map mapdata = new HashMap();
        JSONObject ldata = new JSONObject();
        mapdata.put("err", error);
        mapdata.put("msg", msg);
        mapdata.put("dt", data);
        ldata.putAll(mapdata);
        return ldata.toJSONString();
    }

    public static String toJSON(int error, String msg, String data) {
        Map mapdata = new HashMap();
        JSONObject ldata = new JSONObject();
        mapdata.put("err", error);
        mapdata.put("msg", msg);
        mapdata.put("dt", data);
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

    public static String appendDataResponse(String resp, String objName, String objData) {

        String err;
        try {

            JsonParser jsonParser = new JsonParser();
            JsonElement jsonEle = jsonParser.parse(resp);
            JsonObject jsonMain = jsonEle.getAsJsonObject();
            JsonElement jsonerr = jsonMain.get("err");
            //  if (jsonerr.getAsInt() == 0) {
            JsonElement jsonmsg = jsonMain.get("msg");
            JsonElement jsondt = jsonMain.get("dt");

            JsonObject jsondtObj = null;
            try {
                jsondtObj = jsondt.getAsJsonObject();
            } catch (Exception ex) {

            }
            if (jsondtObj != null) {
                jsondtObj.addProperty(objName, objData);
            } else {
                jsondtObj = new JsonObject();
                jsondtObj.addProperty(objName, objData);
            }

            JsonObject jsonNew = new JsonObject();

            jsonNew.add("err", jsonerr);
            jsonNew.add("msg", jsonmsg);
            jsonNew.add("dt", jsondtObj);

            return _gson.toJson(jsonNew);
            //  } else {
            //     return resp;
            //  }
        } catch (Exception ex) {
            err = ex.getMessage();
        }

        return "";
    }
}
