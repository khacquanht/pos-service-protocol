package apigatewaywahsis.service;
import apigatewaywahsis.common.CommonModel;
import apigatewaywahsis.common.DefinedName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.service_wahsis.building_service.BuildingsHandler;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
*
 * @author khiemnv
*/
public class BuildingsServlet extends AbstractServlet{
	@Override
	protected void processs(HttpServletRequest req, HttpServletResponse resp, StringBuilder respContent) {
		try {
		StringBuilder vrfContent = new StringBuilder(); 
		 String cmd = req.getParameter(DefinedName.PARAM_COMMAND);
		String data = req.getParameter(DefinedName.PARAM_DATA);
		 if (cmd != null && data != null)
			BuildingsHandler.getInstance().process(cmd, data, respContent, vrfContent);
		 else
			respContent.append(CommonModel.toJSON(-1, DefinedName.RESP_MSG_INVALID_REQUEST));
		} catch (Exception ex) {
			Logger.getLogger(BuildingsServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	 
}
