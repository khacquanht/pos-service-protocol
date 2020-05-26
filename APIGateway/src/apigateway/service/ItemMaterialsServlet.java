package apigateway.service;

import apigateway.common.CommonModel;
import apigateway.common.DefinedName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wahsis.items_service.ItemMaterialsHandler;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khiemnv
 */
public class ItemMaterialsServlet extends AbstractServlet {

    @Override
    protected void processs(HttpServletRequest req, HttpServletResponse resp, StringBuilder respContent) {
        try {
            StringBuilder vrfContent = new StringBuilder();
            String cmd = req.getParameter(DefinedName.PARAM_COMMAND);
            String data = req.getParameter(DefinedName.PARAM_DATA);
            if (cmd != null && data != null) {
                ItemMaterialsHandler.getInstance().process(cmd, data, respContent, vrfContent);
            } else {
                respContent.append(CommonModel.toJSON(-1, DefinedName.RESP_MSG_INVALID_REQUEST));
            }
        } catch (Exception ex) {
            Logger.getLogger(ItemMaterialsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @Override
//    protected int authenticate(HttpServletRequest req, String authenErrorMsg) {
//        return 0;
//    }
}
