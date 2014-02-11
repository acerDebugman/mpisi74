package action;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ItService extends ActionSupport {
	private static final Log log = LogFactory.getLog(LoginAction.class);
	
	
	
	public String dailyCheckInit(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		try{
			
			
			return SUCCESS;
		}
		catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}

	public static Log getLog() {
		return log;
	}
}
