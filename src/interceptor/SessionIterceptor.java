package interceptor;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import common.Constant;

public class SessionIterceptor extends AbstractInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4471719373590306883L;
	private static final Log log = LogFactory.getLog(SessionIterceptor.class);

	public String intercept(ActionInvocation actionInvocation) throws Exception {

		ActionContext ctx = actionInvocation.getInvocationContext();
		Map<String, Object> session = ctx.getSession();

//		Action action = (Action) actionInvocation.getAction();
//		if (action instanceof loginAction) {
//		    return actionInvocation.invoke();
//		}
		
		// 获取HttpServletRequest对象
		HttpServletRequest req = ServletActionContext.getRequest();
		// 获取此请求的地址，请求地址包含application name，进行subString操作，去除application name
		//String path = req.getRequestURI().substring(10);
		String path = req.getRequestURI();
		//String path1 = path.substring(path.lastIndexOf("/") + 1);
//		if(path.equals("/hrms/employeeList.action") || path.equals("/hrms/employeeList.action"))

		if(session != null && session.containsKey(Constant.EMPLOYEE_DATA)){
			return actionInvocation.invoke();
		}else{
			log.info("------session time out------");
			log.info("path(I):" + req.getRequestURI());
			log.info("path(L):" + req.getRequestURL());

            //获得请求中的参数
            String queryString = req.getQueryString();
            // 预防空指针
            if (queryString == null) {
            	queryString = "";
            }
            
            // 拼凑得到登陆之前的地址
            String realPath = path + "?" + queryString;
            // 存入session，方便调用
            session.put("prePage", realPath);

			return "relogin";
		}
	}
}
