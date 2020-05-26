/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apigatewaywahsis.service;

import apigatewaywahsis.common.CommonModel;
import apigatewaywahsis.common.DefinedName;
import com.service_wahsis.common.CommonService;
import com.service_wahsis.common.JsonParserUtil;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author phucth
 */
public class WmsAuthentication extends AbstractServletNonAuthenticate {

    private static final String ORCHIDS_DEFAULT_PASSWORD = "ORCHIDS";
    
    @Override
    protected void processs(HttpServletRequest req, HttpServletResponse resp, StringBuilder respContent) {
        try {
            String cmd = req.getParameter(DefinedName.PARAM_COMMAND);
            String data = req.getParameter(DefinedName.PARAM_DATA);
            
            if (cmd == null && data == null) {
                respContent.append(CommonModel.toJSON(-1, DefinedName.RESP_MSG_INVALID_REQUEST));
            }
            
            switch (cmd) {
                case "orchids-hotel" : {
                    if (authenOrchidsHotel(data)) {
                        respContent.append(CommonService.FormatResponse(1, "Success"));
                    } else {
                        respContent.append(CommonService.FormatResponse(0, "Failed"));
                    }
                    break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UsersServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    private boolean authenOrchidsHotel(String data) {
        HashMap<String, String> roomAuth = new HashMap<>();
        roomAuth.put("201", "201");
        roomAuth.put("202", "202");
        roomAuth.put("203", "203");
        roomAuth.put("204", "204");
        roomAuth.put("205", "205");
        roomAuth.put("206", "206");
        roomAuth.put("207", "207");
        roomAuth.put("208", "208");
        roomAuth.put("209", "209");
        roomAuth.put("210", "210");
        roomAuth.put("211", "211");

        roomAuth.put("301", "301");
        roomAuth.put("302", "302");
        roomAuth.put("303", "303");
        roomAuth.put("304", "304");
        roomAuth.put("305", "305");
        roomAuth.put("306", "306");
        roomAuth.put("307", "307");
        roomAuth.put("308", "308");
        roomAuth.put("309", "309");
        roomAuth.put("310", "310");
        roomAuth.put("311", "311");

        roomAuth.put("401", "401");
        roomAuth.put("402", "402");
        roomAuth.put("403", "403");
        roomAuth.put("404", "404");
        roomAuth.put("405", "405");
        roomAuth.put("406", "406");
        roomAuth.put("407", "407");
        roomAuth.put("408", "408");
        roomAuth.put("409", "409");
        roomAuth.put("410", "410");
        roomAuth.put("411", "411");

        roomAuth.put("501", "501");
        roomAuth.put("502", "502");
        roomAuth.put("503", "503");
        roomAuth.put("504", "504");
        roomAuth.put("505", "505");
        roomAuth.put("506", "506");
        roomAuth.put("507", "507");
        roomAuth.put("508", "508");
        roomAuth.put("509", "509");
        roomAuth.put("510", "510");
        roomAuth.put("511", "511");

        roomAuth.put("601", "601");
        roomAuth.put("602", "602");
        roomAuth.put("603", "603");
        roomAuth.put("604", "604");
        roomAuth.put("605", "605");
        roomAuth.put("606", "606");
        roomAuth.put("607", "607");
        roomAuth.put("608", "608");
        roomAuth.put("609", "609");
        roomAuth.put("610", "610");
        roomAuth.put("611", "611");

        roomAuth.put("701", "701");
        roomAuth.put("702", "702");
        roomAuth.put("703", "703");
        roomAuth.put("704", "704");
        roomAuth.put("705", "705");
        roomAuth.put("706", "706");
        roomAuth.put("707", "707");
        roomAuth.put("708", "708");
        roomAuth.put("709", "709");
        roomAuth.put("710", "710");
        roomAuth.put("711", "711");

        roomAuth.put("801", "801");
        roomAuth.put("802", "802");
        roomAuth.put("803", "803");
        roomAuth.put("804", "804");
        roomAuth.put("805", "805");
        roomAuth.put("806", "806");
        roomAuth.put("807", "807");
        roomAuth.put("808", "808");

        roomAuth.put("1001", "1001");
        roomAuth.put("1002", "1002");
        roomAuth.put("1003", "1003");
        roomAuth.put("1004", "1004");
        roomAuth.put("1005", "1005");

        roomAuth.put("1101", "1101");
        roomAuth.put("1102", "1102");
        roomAuth.put("1103", "1103");
        roomAuth.put("1104", "1104");
        roomAuth.put("1105", "1105");
        roomAuth.put("1106", "1106");
        roomAuth.put("1107", "1107");
        roomAuth.put("1108", "1108");
        roomAuth.put("1109", "1109");

        roomAuth.put("1201", "1201");
        roomAuth.put("1202", "1202");
        roomAuth.put("1203", "1203");
        roomAuth.put("1204", "1204");
        roomAuth.put("1205", "1205");
        roomAuth.put("1206", "1206");
        roomAuth.put("1207", "1207");
        roomAuth.put("1208", "1208");
        roomAuth.put("1209", "1209");

        roomAuth.put("1401", "1401");
        roomAuth.put("1402", "1402");
        roomAuth.put("1403", "1403");
        roomAuth.put("1404", "1404");
        roomAuth.put("1405", "1405");

        String roomName = JsonParserUtil.getProperties(data, "room");
        String userName = JsonParserUtil.getProperties(data, "username");
        
        String value = roomAuth.get(roomName);
        return value != null && userName.compareToIgnoreCase(ORCHIDS_DEFAULT_PASSWORD) == 0;
    }
    
}
