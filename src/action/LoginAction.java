package action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import service.IAC0006Service;
import service.IAC0007Service;
import service.IAC0008Service;
import service.IAC0009Service;
import service.IHOLIDAYService;
import service.IJE0101Service;
import service.IMP0002Service;
import service.IMP0011Service;
import service.IMP1001Service;
import service.IMP1010Service;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import common.Constant;
import common.LogUtil;
import common.Mail;
import common.UtilCommon;
import common.UtilDate;

import entity.HOLIDAY;
import entity.JE0101;
import entity.MP0002;
import entity.MP0011;
import entity.MP1001;
import entity.MP1010;

public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginAction.class);
	
	private IMP1001Service serviceMP1001;
	private IMP1010Service serviceMP1010;
	private IMP0002Service serviceMp0002;
	private IMP0011Service serviceMP0011;
	private IAC0006Service serviceAC0006;
	private IAC0007Service serviceAC0007;
	private IAC0008Service serviceAC0008;
	private IAC0009Service serviceAC0009;
	private IHOLIDAYService serviceHOLIDAY;
	private IJE0101Service serviceJE0101;
	private MP1001 mp1001;
	private List<?> mp1001s;
	private String MP1001_EMPLOYEE_NUM;
	private List<MP0002> departmentList;
	private String errMessage;
	private String group;
	private String prePage;
	private String oldPassword;
	private String newPassword1;
	private String newPassword2;
	private String departmentID;
	private String position;
	private String fromDate;
	private String toDate;
	private String mp0011EmpNumber;
	private int pageNum;
	private int pageCount;
	private String errMsg;
	private String changePsdType;
	
	//forget password for email
	private String companyEmailAddr;
	private String forgetPasswordSeq;
	
	private List<MP0011> loginHistoryInfo = new ArrayList<MP0011>();
	
	//------------------权限控制用变量---------------
	private String func0001001="0";// 
	private String func0001002="0";// 
	private String func0001003="0";// 
	private String func0001004="0";// 
	private String func0001005="0";// 
	private String func0001006="0";// 
	private String func0001007="0";// 
	private String func0001008="0";// 
	private String func0001009="0";// 
	
	private String func0002001="0";// 
	private String func0002002="0";// 
	private String func0002003="0";// 
	private String func0002004="0";// 
	private String func0002005="0";//
	private String func0002006="0";
	private String func0002007="0";
	private String func0002008="0";
	
	private String func0003001="0";// 
	private String func0003002="0";// 
	private String func0003003="0";// 
	private String func0003004="0";// 
	private String func0003005="0";//
	private String func0003006="0";//
	private String func0003007="0";
	private String func0003008="0";
	private String func0003009="0";
	private String func0003010="0";
	private String func0003011="0";
	
	private String func0004001="0";// 
	private String func0004002="0";// 
	private String func0004003="0";// 
	private String func0004004="0";// 
	
	private String func0005001="0";// 
	private String func0005002="0";// 
	
	private String func0020001 = "0";
	private String func0020002 = "0";
	private String func0020003 = "0";
	private String func0020004 = "0";
	
	private String func0021001 = "0";
	private String func0021002 = "0";
	private String func0021003 = "0";
	private String func0021004 = "0";
	private String func0021005 = "0";
	private String func0021006 = "0";
	private String func0021007 = "0";
	
	private String func001006 = "0";
	private String func001006001 = "0";
	private String func001006002 = "0";
	
	private String sys0001 = "0";
	private String sys0004 = "0";
	private String sys0005 = "0";
	private String sys0006 = "0";
	
	private String optSave="0";// 
	private String optCancel="0";// 
	private String optApproval = "0";// 
	
	private String optSearch = "0";// 
	private String optDel = "0";// 
	private String optEdit = "0";// 
	private String optView = "0";// 
	
	private String optAll = "0";// 
	private String optDepartment = "0";// 
	private String optPersonal = "0";// 
	
	private String optPdf = "0";

	// 跳转到登陆页面
	public String loginInit() throws Exception{
		return SUCCESS;
	}
	
	private Map<String,String> getHolidayList(){
		Map<String,String> holidayMap = new LinkedHashMap<String, String>();
		//List<HOLIDAY> holidayList = serviceHOLIDAY.findByProperty("HOLIDAY_YEAR", currentYear);
		List<HOLIDAY> holidayList = serviceHOLIDAY.findAll();
		
		for(int i=0,j=holidayList.size(); i<j; i++){
			HOLIDAY obj = holidayList.get(i);
			holidayMap.put(obj.getHOLIDAY_DATE(), obj.getHOLIDAY_NAME());
		}
		
		return holidayMap;
	}

	// 登陆检查逻辑
	public String login(){
		try{
			String employeeNum= mp1001.getMP1001_EMPLOYEE_NUM();
			
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
			
			// 判断用户名是否存在
			MP1001 employeeData = new MP1001();
			MP1010 employeeData2 = new MP1010();
			if(employeeNum.indexOf("M") >= 0){
				// 权限检查
				boolean ret = serviceAC0006.systemAccessCheck(mp1001.getMP1001_EMPLOYEE_NUM(),Constant.SYSTEM_NUM_HR);
				if(ret == false){
					addFieldError("mp1001.MP1001_PASSWORD","you do not have permission to access the system.\r\n Please contact administrator to request access.");
					return INPUT;
				}
				
				// 判断是否有查看试用期员工的权限
				HashMap<String,String> funcMap = serviceAC0006.functionAccessCheck(mp1001.getMP1001_EMPLOYEE_NUM(),Constant.SYSTEM_NUM_HR);
				//试用期员工
				if(funcMap.containsKey(Constant.F0001002)){
					func0001002 = "1";
				}
				
				employeeData =serviceMP1001.findById(employeeNum);
			}else{
				employeeData2 = serviceMP1010.findById(employeeNum);
				employeeData.setMP1001_EMPLOYEE_NUM(employeeData2.getMP1010_EMPLOYEE_NUM());
				employeeData.setMP1001_PASSWORD(employeeData2.getMP1010_PASSWORD());
				employeeData.setMP1001_PREFERED_NAME(employeeData2.getMP1010_PREFERED_NAME());
				employeeData.setMP1001_SURNAME(employeeData2.getMP1010_FIRST_NAME());
				employeeData.setMP1001_STATUS("1");
				employeeData.setMP1001_DEPARTMENT_ID(employeeData2.getMP1010_DEPARTMENT());
				employeeData.setMP1001_PASSWORD_DATE(UtilDate.getCurrentUtilDate().toString());
			}
			
			if( null == employeeData){
	            addFieldError("mp1001.MP1001_EMPLOYEE_NUM","user name is not exist.");
				return INPUT;
			}else{
				employeeData.setMP1001_EMPLOYEE_NUM(employeeData.getMP1001_EMPLOYEE_NUM().toUpperCase());
			}
			// 如果该员工已经离职，则无法登陆。
			if(employeeData.getMP1001_STATUS().equals("3")){
				addFieldError("mp1001.MP1001_EMPLOYEE_NUM","login failure.");
				return INPUT;
			}
			// 判断密码是否正确
//			if(!mp1001.getMP1001_PASSWORD().equals(employeeData.getMP1001_PASSWORD())){
//				addFieldError("mp1001.MP1001_PASSWORD","login failure.");
//				writeLoginLog(employeeData,"N");
//				return INPUT;
//			}

			group = employeeData.getMP1001_GROUP();
			mp1001.setMP1001_EMPLOYEE_NUM(employeeNum.toUpperCase());
			mp1001.setMP1001_PREFERED_NAME(employeeData.getMP1001_PREFERED_NAME());
			mp1001.setMP1001_FIRSTNAME(employeeData.getMP1001_PREFERED_NAME());
			mp1001.setMP1001_SURNAME(employeeData.getMP1001_SURNAME());
			
			departmentList = serviceMp0002.findAll(true);
			
			String departmentId = employeeData.getMP1001_DEPARTMENT_ID();
			if(departmentId != null && !departmentId.equals("")){
				int depId = Integer.parseInt(departmentId);
				MP0002 departmentData = serviceMp0002.findById(depId);
				employeeData.setMP1001_DEPARTMENT_NAME(departmentData.getMP0002_DEPARTMENT_NAME());
			    session.put("DEPARTMENT_DATA", departmentData);
			}
			Map<String,String> holidayMap = getHolidayList();
			// 缓存HOLIDAY信息
			session.put("HOLIDAY_DATE", holidayMap);
			// 缓存登陆用户的信息
			session.put("EMPLOYEE_DATA", employeeData);
		    // 缓存部门信息
		    session.put("DEPARTMENT_LIST", departmentList);
		    
		    //获取跳转到登陆界面之前的页面地址，由拦截器提供
		    prePage = (String) session.get("prePage");
	        //清除session中的数据
		    session.remove("prePage");
		    
		    log.info(employeeData.getMP1001_EMPLOYEE_NUM() + " " + employeeData.getMP1001_PREFERED_NAME() + " " + "login success!");
		    
		    if(employeeNum.indexOf("M") >= 0){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date passwordDate = df.parse(employeeData.getMP1001_PASSWORD_DATE());
			    
				Calendar calendar1 = new GregorianCalendar(); 
				calendar1.setTime(passwordDate);
				calendar1.add(Calendar.MONTH, 3);//每三个月更新一次密码
				
				Calendar calendar2 = Calendar.getInstance();
				calendar2.set(Calendar.HOUR_OF_DAY, 0);  //小时上设为0
				calendar2.set(Calendar.MINUTE, 0);//分钟设为0
				calendar2.set(Calendar.SECOND, 0);//秒钟设为0
				calendar2.set(Calendar.MILLISECOND, 0);

				int result = calendar1.compareTo(calendar2);
				if(result <= 0 ){
					writeLoginLog(employeeData,"P");
					return "changePw";
				}else{
					// 如果密码为初始密码"mpisi123"，则强制进入密码修改页面。
					if(mp1001.getMP1001_PASSWORD().equals("mpisi123")){
						writeLoginLog(employeeData,"P");
						return "changePw";
					}else{
						writeLoginLog(employeeData,"Y");
						return SUCCESS;
					}
				}
		    }else{
		    	return SUCCESS;
		    }
		    //return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	private void writeLoginLog(MP1001 obj,String type){
		MP0011 mp0011 = new MP0011();
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		// 生成主KEY
		String key = sdf.format(now) + Integer.toString(new Double(Math.ceil(Math.random()*100000 )).intValue()) ;
		// 取得登陆时间
		String loginTime = sdf1.format(now);
		
		// 取得Request
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		// 取得IP地址
		String ip = UtilCommon.getIPAddress(req);
		
		mp0011.setMP0011_SEQ(key);//设置KEY
		mp0011.setMP0011_SYS_CODE(Constant.SYSTEM_NUM_HR);//设置系统编码
		mp0011.setMP0011_USR_ID(obj.getMP1001_EMPLOYEE_NUM());//设置员工编码
		mp0011.setMP0011_USR_NAME(obj.getMP1001_PREFERED_NAME());//设置员工名称
		mp0011.setMP0011_LOGIN_TIME(loginTime);//设置登陆时间
		mp0011.setMP0011_LOGIN_IP(ip);//设置IP地址
		
		if(type.equals("Y")){//备注
			mp0011.setMP0011_MEMO(Constant.LOGIN_SUCCESS);
		}else if(type.equals("N")){
			mp0011.setMP0011_MEMO(Constant.LOGIN_FAILURE);
		}else if(type.equals("P")){
			mp0011.setMP0011_MEMO(Constant.RESET_PWD);
		}else{
			mp0011.setMP0011_MEMO("");
		}
		
		serviceMP0011.save(mp0011);		
	}
	
	public void validateLogin(){
		// 判断用户名称是否为空
		if(mp1001.getMP1001_EMPLOYEE_NUM().equals("")){
			addFieldError("mp1001.MP1001_EMPLOYEE_NUM","user name is empty.");
		}else if(!Pattern.matches("\\w{5,5}", mp1001.getMP1001_EMPLOYEE_NUM().trim())){
			addFieldError("mp1001.MP1001_EMPLOYEE_NUM","user name`s length is error.");
		}
		// 判断用户密码是否为空
		if(mp1001.getMP1001_PASSWORD().equals("")){
			addFieldError("mp1001.MP1001_PASSWORD","password is empty.");
		}
	}
	
	public String logout(){
		try{
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
			
			session.clear();
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}

	public String loadHrMenu(){
		try{
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
			
			// 取得登陆人信息
			MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
			group = employeeData.getMP1001_GROUP();
			departmentID = employeeData.getMP1001_DEPARTMENT_ID();
			position = employeeData.getMP1001_POSITION();
			
			MP1001_EMPLOYEE_NUM = employeeData.getMP1001_EMPLOYEE_NUM();
			
			getMenuAuthority(MP1001_EMPLOYEE_NUM,Constant.SYSTEM_NUM_HR);//取得该登陆用户的访问权限
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public String loadPmsMenu(){
		try{
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
			
			// 取得登陆人信息
			MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
			group = employeeData.getMP1001_GROUP();
			departmentID = employeeData.getMP1001_DEPARTMENT_ID();
			position = employeeData.getMP1001_POSITION();
			
			MP1001_EMPLOYEE_NUM = employeeData.getMP1001_EMPLOYEE_NUM();
			
			getMenuAuthority(MP1001_EMPLOYEE_NUM,Constant.SYSTEM_NUM_PMS);//取得该登陆用户的访问权限
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public void getMenuAuthority(String employeeNum,String systemNum){
		HashMap<String,String> funcMap = serviceAC0006.functionAccessCheck(employeeNum,systemNum);
		
		//--------------HR管理系统 Start--------------------------//
		// 新增员工
		if(funcMap.containsKey(Constant.F0001001)){
			func0001001 = "1";
		}
		//试用期员工
		if(funcMap.containsKey(Constant.F0001002)){
			func0001002 = "1";
		}
		//员工信息查询
		if(funcMap.containsKey(Constant.F0001003)){
			func0001003 = "1";
		}
		//员工电话分机
		if(funcMap.containsKey(Constant.F0001004)){
			func0001004 = "1";
		}
		//个人信息浏览
		if(funcMap.containsKey(Constant.F0001005)){
			func0001005 = "1";
		}

		if(funcMap.containsKey(Constant.F0001006)){
			func0001006 = "1";
		}
		if(funcMap.containsKey(Constant.F0001007)){
			func0001007 = "1";
		}
		if(funcMap.containsKey(Constant.F0001008)){
			func0001008 = "1";
		}
		if(funcMap.containsKey(Constant.F0001009)){
			func0001009 = "1";
		}

		//请假
		if(funcMap.containsKey(Constant.F0002001)){
			func0002001 = "1";
		}
		//请假查询
		if(funcMap.containsKey(Constant.F0002002)){
			func0002002 = "1";
		}
		//个人假期查询
		if(funcMap.containsKey(Constant.F0002003)){
			func0002003 = "1";
		}
		//出勤记录
		if(funcMap.containsKey(Constant.F0002004)){
			func0002004 = "1";
		}
		//出勤报表
		if(funcMap.containsKey(Constant.F0002005)){
			func0002005 = "1";
		}
		//出国申请表
		if(funcMap.containsKey(Constant.F0002006)){
			func0002006 = "1";
		}
		//出国申请管理
		if(funcMap.containsKey(Constant.F0002007)){
			func0002007 = "1";
		}
		//出国申请审核
		if(funcMap.containsKey(Constant.F0002008)){
			func0002008 = "1";
		}

		//公告管理
		if(funcMap.containsKey(Constant.F0003001)){
			func0003001 = "1";
		}
		//公告查询
		if(funcMap.containsKey(Constant.F0003002)){
			func0003002 = "1";
		}
		//修改密码
		if(funcMap.containsKey(Constant.F0003003)){
			func0003003 = "1";
		}
		//职位管理
		if(funcMap.containsKey(Constant.F0003004)){
			func0003004 = "1";
		}
		//重置密码
		if(funcMap.containsKey(Constant.F0003005)){
			func0003005 = "1";
		}
		//服务邮件地址设定
		if(funcMap.containsKey(Constant.F0003006)){
			func0003006 = "1";
		}
		//部门管理
		if(funcMap.containsKey(Constant.F0003007)){
			func0003007 = "1";
		}
		//新增部门
		if(funcMap.containsKey(Constant.F0003008)){
			func0003008 = "1";
		}
		//部门职位管理
		if(funcMap.containsKey(Constant.F0003009)){
			func0003009 = "1";
		}
		//公共假日管理
		if(funcMap.containsKey(Constant.F0003010)){
			func0003010 = "1";
		}
		//公共假日管理
		if(funcMap.containsKey(Constant.F0003011)){
			func0003011 = "1";
		}
		
		if(funcMap.containsKey(Constant.F0004001)){
			func0004001 = "1";
		}
		if(funcMap.containsKey(Constant.F0004002)){
			func0004002 = "1";
		}
		if(funcMap.containsKey(Constant.F0004003)){
			func0004003 = "1";
		}
		if(funcMap.containsKey(Constant.F0004004)){
			func0004004 = "1";
		}
		
		if(funcMap.containsKey(Constant.F0005001)){
			func0005001 = "1";
		}
		if(funcMap.containsKey(Constant.F0005002)){
			func0005002 = "1";
		}
		//--------------HR管理系统 End--------------------------//
		
		//--------------采购管理系统 Start--------------------------//
		if(funcMap.containsKey(Constant.F0020001)){
			func0020001 = "1";
		}
		if(funcMap.containsKey(Constant.F0020002)){
			func0020002 = "1";
		}
		if(funcMap.containsKey(Constant.F0020003)){
			func0020003 = "1";
		}
		if(funcMap.containsKey(Constant.F0020004)){
			func0020004 = "1";
		}
		if(funcMap.containsKey(Constant.F0021001)){
			func0021001 = "1";
		}
		if(funcMap.containsKey(Constant.F0021002)){
			func0021002 = "1";
		}
		if(funcMap.containsKey(Constant.F0021003)){
			func0021003 = "1";
		}
		if(funcMap.containsKey(Constant.F0021004)){
			func0021004 = "1";
		}
		if(funcMap.containsKey(Constant.F0021005)){
			func0021005 = "1";
		}
		if(funcMap.containsKey(Constant.F0021006)){
			func0021006 = "1";
		}
		if(funcMap.containsKey(Constant.F0021007)){
			func0021007 = "1";
		}
		
		//--------------采购管理系统 End--------------------------//
		
		if(funcMap.containsKey(Constant.F001006)){
			func001006 = "1";
		}
		if(funcMap.containsKey(Constant.F001006001)){
			func001006001 = "1";
		}
		if(funcMap.containsKey(Constant.F001006002)){
			func001006002 = "1";
		}
	}
	
	// 修改密码
	public String changePassword(){
		try{
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
			
			// 取得登陆人信息
			MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
			MP1001_EMPLOYEE_NUM = employeeData.getMP1001_EMPLOYEE_NUM();
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Change Password");
			//----------------------------Operation History------------------
			
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0003003,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_SAVE)){
				optSave = "1";
			}
			if(optMap.containsKey(Constant.OPT_CANCEL)){
				optCancel = "1";
			}

			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}

	// 修改密码
	public String savePassword(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Save Password");
			//----------------------------Operation History------------------
			
			MP1001 _mp1 = serviceMP1001.findById(MP1001_EMPLOYEE_NUM);
			
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String pwdDate = sdf.format(now);
			
			_mp1.setMP1001_PASSWORD(newPassword1);
			_mp1.setMP1001_PASSWORD_DATE(pwdDate);
			
			serviceMP1001.update(_mp1);
			
			// 发邮件通知本人密码已经修改
			Mail mail = new Mail();
		    
			String to = _mp1.getMP1001_COMPANY_EMAIL();
			mail.setSubject("HRMS Password updated");
			mail.setContent("Dear Colleagues,\r\n \r\n Please note that the your password has been updated.\r\n Employee No. : " + MP1001_EMPLOYEE_NUM + "\r\n Time:" + UtilDate.get24DateTime() + "\r\n \r\n Thank you very much! \r\n \r\n\r\n Your Faithfully, \r\n HRMS Administrator");
			mail.setTo(to);
			mail.send();
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public void validateSavePassword(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		MP1001_EMPLOYEE_NUM = employeeData.getMP1001_EMPLOYEE_NUM();
		
		// 判断用户名称是否为空
		if(MP1001_EMPLOYEE_NUM.equals("")){
			addFieldError("MP1001_EMPLOYEE_NUM","user is empty.");
		}
		// 判断用户密码是否为空(旧)
		if(null != oldPassword && oldPassword.equals("")){
			addFieldError("oldPassword","old password is empty.");
		}else if(null != oldPassword && !employeeData.getMP1001_PASSWORD().equals(oldPassword)){
			addFieldError("oldPassword","old password is error.");
		}
		// 判断用户密码是否为空(新)
		if(newPassword1.equals("")){
			addFieldError("newPassword1","new password is empty.");
		}
		// 判断用户密码是否为空(新)
		if(newPassword2.equals("")){
			addFieldError("newPassword2","new password is empty.");
		}
		// 判断两次新密码是否匹配
		if(!newPassword1.equals("") && !newPassword2.equals("") && !newPassword1.equals(newPassword2)){
			addFieldError("newPassword2","两次新输入的密码不一致");
		}
		// 密码长度小于8位
		if(newPassword1.length() < 8){
			addFieldError("newPassword1","new password's length is less than 8.");
		}
		if((null != oldPassword && newPassword1.equals(oldPassword)) || newPassword1.equals(employeeData.getMP1001_PASSWORD())){
			addFieldError("newPassword1","new password and old password are the same.");
		}
	}
	
	// 主界面
	public String navigationPageInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String loginUsrId = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			boolean hrFlag = serviceAC0006.systemAccessCheck(loginUsrId, Constant.SYSTEM_NUM_HR);
			boolean pmsFlag = serviceAC0006.systemAccessCheck(loginUsrId, Constant.SYSTEM_NUM_PMS);
			boolean schFlag = serviceAC0006.systemAccessCheck(loginUsrId, Constant.SYSTEM_NUM_SCHEDULE);
			boolean performanceFlg = serviceAC0006.systemAccessCheck(loginUsrId, Constant.SYSTEM_NUM_PERFORMANCE);
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Nacigation Page Init");
			//----------------------------Operation History------------------
			
			if(hrFlag == true){
				sys0001 = "1";
			}else{
				sys0001 = "0";
			}
			if(pmsFlag == true){
				sys0004 = "1";
			}else{
				sys0004 = "0";
			}
			if(schFlag == true){
				sys0005 = "1";
			}else{
				sys0005 = "0";
			}
			if(performanceFlg == true){
				sys0006 = "1";
			}else{
				sys0006 = "0";
			}
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			if(session.containsKey("ERR_MSG")){
				session.remove("ERR_MSG");
				session.put("ERR_MSG", ex.getMessage());
			}
			return "error";
		}
	}
	
	// 登陆历史记录管理
	public String loginHistoryInit(){
		try{
			ActionContext context = ActionContext.getContext();
			Map<String,Object> session = context.getSession();
			MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
			String userID = employeeData.getMP1001_EMPLOYEE_NUM();
			// 权限
			getPageAuthority(userID,Constant.F0003011,Constant.SYSTEM_NUM_HR);
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Login History Management Init");
			//----------------------------Operation History------------------

			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	// 登陆历史记录检索
	public String loginHistoryInfoSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String userID = employeeData.getMP1001_EMPLOYEE_NUM();
		// 权限
		getPageAuthority(userID,Constant.F0003011,Constant.SYSTEM_NUM_HR);
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Login History Information Search");
			//----------------------------Operation History------------------
		
			loginHistoryInfo = serviceMP0011.findByProperty(mp0011EmpNumber,"",fromDate,toDate,-1,-1);
			if( 0 == loginHistoryInfo.size()%Constant.PAGE_SIZE){
				pageCount = loginHistoryInfo.size()/Constant.PAGE_SIZE;
			}else{
				pageCount = loginHistoryInfo.size()/Constant.PAGE_SIZE + 1;
			}
			
			loginHistoryInfo = serviceMP0011.findByProperty(mp0011EmpNumber,"",fromDate,toDate, 1, Constant.PAGE_SIZE);
			
			return SUCCESS;
		}catch(Exception ex){
			log.info("Abnormal:" + ex.getMessage());
			return "error"; 
		}
	}
	// 登陆历史记录分页检索
	public String loginHistoryDetailList(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String userID = employeeData.getMP1001_EMPLOYEE_NUM();
		// 权限
		getPageAuthority(userID,Constant.F0003011,Constant.SYSTEM_NUM_HR);
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Login History Detail List");
			//----------------------------Operation History------------------
		
			loginHistoryInfo = serviceMP0011.findByProperty(mp0011EmpNumber,"",fromDate,toDate,-1,-1);
			if( 0 == loginHistoryInfo.size()%Constant.PAGE_SIZE){
				pageCount = loginHistoryInfo.size()/Constant.PAGE_SIZE;
			}else{
				pageCount = loginHistoryInfo.size()/Constant.PAGE_SIZE + 1;
			}
			
			loginHistoryInfo = serviceMP0011.findByProperty(mp0011EmpNumber,"",fromDate,toDate, pageNum, Constant.PAGE_SIZE);
			
			return SUCCESS;
		}catch(Exception ex){
			log.info("Abnormal:" + ex.getMessage());
			return "error"; 
		}
	}
	// HRMS系统主页面初始化
	public String mainPageInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		mp1001 = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String userID = mp1001.getMP1001_EMPLOYEE_NUM();
		try{		
			// 权限
			getPageAuthority(userID,Constant.F0001002,Constant.SYSTEM_NUM_HR);
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(mp1001.getMP1001_EMPLOYEE_NUM(),mp1001.getMP1001_PREFERED_NAME(),"HRMS Main Page Init");
			//----------------------------Operation History------------------
			
			return SUCCESS;
		}catch(Exception ex){
			log.info("Abnormal:" + ex.getMessage());
			return "error"; 
		}
	}
	// 取得采购管理页面权限信息
	private void getPageAuthority(String _employeeNum,String functionNum,String systemNum){
		HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(_employeeNum, functionNum,systemNum);
		if(optMap.containsKey(Constant.OPT_SEARCH)){
			optSearch = "1";
		}
		if(optMap.containsKey(Constant.OPT_DELETE)){
			optDel = "1";
		}
		if(optMap.containsKey(Constant.OPT_EDIT)){
			optEdit = "1";
		}
		if(optMap.containsKey(Constant.OPT_VIEW)){
			optView = "1";
		}
		if(optMap.containsKey(Constant.OPT_APPROVAL)){
			optApproval = "1";
		}
		
		if(optMap.containsKey(Constant.OPT_PERSONAL)){
			optPersonal = "1";
		}
		if(optMap.containsKey(Constant.OPT_DEPARTMENT)){
			optDepartment = "1";
		}
		if(optMap.containsKey(Constant.OPT_ALL)){
			optAll = "1";
		}
		if(optMap.containsKey(Constant.OPT_SAVE)){
			optSave = "1";
		}
		if(optMap.containsKey(Constant.OPT_CANCEL)){
			optCancel = "1";
		}		
	}
	
	//forget password ---reset password
	public String forgetPassword(){
		try{
			if(null == MP1001_EMPLOYEE_NUM || MP1001_EMPLOYEE_NUM.equalsIgnoreCase("")){
				return "resetPasswordPage";
			}
			MP1001 _mp1 = serviceMP1001.findById(MP1001_EMPLOYEE_NUM);
			if(_mp1.getMP1001_COMPANY_EMAIL().equalsIgnoreCase("") || _mp1.getMP1001_COMPANY_EMAIL().equalsIgnoreCase("null") || null == _mp1.getMP1001_COMPANY_EMAIL()){
				//can't find email addresss
				return "noEmailAddr";
			}

			String randomValue = "";
			randomValue = Constant.generateSeq() + UtilCommon.getTempSeq();
			JE0101 je0101 = new JE0101();
			je0101.setJE0101_PROPERTY(randomValue);
			je0101.setJE0101_VALUE(MP1001_EMPLOYEE_NUM);
			je0101.setJE0101_TYPE("FORGETPSWD");
			serviceJE0101.save(je0101);
			
			// 发邮件通知本人密码已经修改
			Mail mail = new Mail();
		    
			String to = _mp1.getMP1001_COMPANY_EMAIL();
			mail.setSubject("HRMS Password Reset");
			mail.setContent("Dear Colleagues,<br/>&nbsp;&nbsp;&nbsp;Please click below link to reset your password, please be adviced that the link only available for 1 day! <br />&nbsp;&nbsp;&nbsp;&nbsp;Link Address:   <a href='http://192.168.50.11:8080/mpisi74/resetPasswordFromMailLink?forgetPasswordSeq=" + randomValue + "'>Reset My Password</a><br/>&nbsp;&nbsp;&nbsp;Time:" + UtilDate.get24DateTime() + "<br/>&nbsp;&nbsp;&nbsp;Thank you very much! <br />&nbsp;&nbsp;&nbsp;Your Faithfully, <br />&nbsp;&nbsp;&nbsp;HRMS Administrator");
			mail.setTo(to);
			//mail.send();
			mail.sendTextHtml();
			
			return SUCCESS;
		}catch(Exception ex){
			log.info("Abnormal:" + ex.getMessage());
			return "error"; 
		}
	}
	
	public String resetPasswordFromMailLink(){
		try{
			JE0101 je0101 = serviceJE0101.findByKey(forgetPasswordSeq);
			if(null == je0101){
				return "linkNotAvailable";
			}
			
			String employeeNum = je0101.getJE0101_VALUE();
			MP1001 mp11 = serviceMP1001.findById(employeeNum);
			mp11.setMP1001_PASSWORD("mpisi123");
			serviceMP1001.update(mp11);
			
			//delete record
			serviceJE0101.delete(je0101);
			
			return SUCCESS;
		}catch(Exception ex){
			log.info("Abnormal:" + ex.getMessage());
			return "error"; 
		}
	}
	
	
	/**
	 * @return the mp1001
	 */
	public MP1001 getMp1001() {
		return mp1001;
	}

	/**
	 * @param mp1001 the mp1001 to set
	 */
	public void setMp1001(MP1001 mp1001) {
		this.mp1001 = mp1001;
	}

	/**
	 * @return the mp1001s
	 */
	public List<?> getMp1001s() {
		return mp1001s;
	}

	/**
	 * @param mp1001s the mp1001s to set
	 */
	public void setMp1001s(List<?> mp1001s) {
		this.mp1001s = mp1001s;
	}

	/**
	 * @return the mP1001_EMPLOYEE_NUM
	 */
	public String getMP1001_EMPLOYEE_NUM() {
		return MP1001_EMPLOYEE_NUM;
	}

	/**
	 * @param mP1001_EMPLOYEE_NUM the mP1001_EMPLOYEE_NUM to set
	 */
	public void setMP1001_EMPLOYEE_NUM(String mP1001_EMPLOYEE_NUM) {
		MP1001_EMPLOYEE_NUM = mP1001_EMPLOYEE_NUM;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the serviceMP1001
	 */
	public IMP1001Service getServiceMP1001() {
		return serviceMP1001;
	}

	/**
	 * @param serviceMP1001 the serviceMP1001 to set
	 */
	public void setServiceMP1001(IMP1001Service serviceMP1001) {
		this.serviceMP1001 = serviceMP1001;
	}

	/**
	 * @return the serviceMp0002
	 */
	public IMP0002Service getServiceMp0002() {
		return serviceMp0002;
	}

	/**
	 * @param serviceMp0002 the serviceMp0002 to set
	 */
	public void setServiceMp0002(IMP0002Service serviceMp0002) {
		this.serviceMp0002 = serviceMp0002;
	}

	/**
	 * @return the departmentList
	 */
	public List<MP0002> getDepartmentList() {
		return departmentList;
	}

	/**
	 * @param departmentList the departmentList to set
	 */
	public void setDepartmentList(List<MP0002> departmentList) {
		this.departmentList = departmentList;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * @return the prePage
	 */
	public String getPrePage() {
		return prePage;
	}

	/**
	 * @param prePage the prePage to set
	 */
	public void setPrePage(String prePage) {
		this.prePage = prePage;
	}

	/**
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * @param oldPassword the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * @return the newPassword1
	 */
	public String getNewPassword1() {
		return newPassword1;
	}

	/**
	 * @param newPassword1 the newPassword1 to set
	 */
	public void setNewPassword1(String newPassword1) {
		this.newPassword1 = newPassword1;
	}

	/**
	 * @return the newPassword2
	 */
	public String getNewPassword2() {
		return newPassword2;
	}

	/**
	 * @param newPassword2 the newPassword2 to set
	 */
	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}
	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	/**
	 * @return the departmentID
	 */
	public String getDepartmentID() {
		return departmentID;
	}

	/**
	 * @param departmentID the departmentID to set
	 */
	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the serviceHOLIDAY
	 */
	public IHOLIDAYService getServiceHOLIDAY() {
		return serviceHOLIDAY;
	}

	/**
	 * @param serviceHOLIDAY the serviceHOLIDAY to set
	 */
	public void setServiceHOLIDAY(IHOLIDAYService serviceHOLIDAY) {
		this.serviceHOLIDAY = serviceHOLIDAY;
	}

	/**
	 * @return the serviceAC0006
	 */
	public IAC0006Service getServiceAC0006() {
		return serviceAC0006;
	}

	/**
	 * @param serviceAC0006 the serviceAC0006 to set
	 */
	public void setServiceAC0006(IAC0006Service serviceAC0006) {
		this.serviceAC0006 = serviceAC0006;
	}

	/**
	 * @return the serviceAC0009
	 */
	public IAC0009Service getServiceAC0009() {
		return serviceAC0009;
	}

	/**
	 * @param serviceAC0009 the serviceAC0009 to set
	 */
	public void setServiceAC0009(IAC0009Service serviceAC0009) {
		this.serviceAC0009 = serviceAC0009;
	}

	/**
	 * @return the serviceAC0007
	 */
	public IAC0007Service getServiceAC0007() {
		return serviceAC0007;
	}

	/**
	 * @param serviceAC0007 the serviceAC0007 to set
	 */
	public void setServiceAC0007(IAC0007Service serviceAC0007) {
		this.serviceAC0007 = serviceAC0007;
	}

	/**
	 * @return the func0001001
	 */
	public String getFunc0001001() {
		return func0001001;
	}

	/**
	 * @param func0001001 the func0001001 to set
	 */
	public void setFunc0001001(String func0001001) {
		this.func0001001 = func0001001;
	}

	/**
	 * @return the func0001002
	 */
	public String getFunc0001002() {
		return func0001002;
	}

	/**
	 * @param func0001002 the func0001002 to set
	 */
	public void setFunc0001002(String func0001002) {
		this.func0001002 = func0001002;
	}

	/**
	 * @return the func0001003
	 */
	public String getFunc0001003() {
		return func0001003;
	}

	/**
	 * @param func0001003 the func0001003 to set
	 */
	public void setFunc0001003(String func0001003) {
		this.func0001003 = func0001003;
	}

	/**
	 * @return the func0001004
	 */
	public String getFunc0001004() {
		return func0001004;
	}

	/**
	 * @param func0001004 the func0001004 to set
	 */
	public void setFunc0001004(String func0001004) {
		this.func0001004 = func0001004;
	}

	/**
	 * @return the func0002001
	 */
	public String getFunc0002001() {
		return func0002001;
	}

	/**
	 * @param func0002001 the func0002001 to set
	 */
	public void setFunc0002001(String func0002001) {
		this.func0002001 = func0002001;
	}

	/**
	 * @return the func0002002
	 */
	public String getFunc0002002() {
		return func0002002;
	}

	/**
	 * @param func0002002 the func0002002 to set
	 */
	public void setFunc0002002(String func0002002) {
		this.func0002002 = func0002002;
	}

	/**
	 * @return the func0002003
	 */
	public String getFunc0002003() {
		return func0002003;
	}

	/**
	 * @param func0002003 the func0002003 to set
	 */
	public void setFunc0002003(String func0002003) {
		this.func0002003 = func0002003;
	}

	/**
	 * @return the func0002004
	 */
	public String getFunc0002004() {
		return func0002004;
	}

	/**
	 * @param func0002004 the func0002004 to set
	 */
	public void setFunc0002004(String func0002004) {
		this.func0002004 = func0002004;
	}

	/**
	 * @return the func0002005
	 */
	public String getFunc0002005() {
		return func0002005;
	}

	/**
	 * @param func0002005 the func0002005 to set
	 */
	public void setFunc0002005(String func0002005) {
		this.func0002005 = func0002005;
	}

	/**
	 * @return the func0003001
	 */
	public String getFunc0003001() {
		return func0003001;
	}

	/**
	 * @param func0003001 the func0003001 to set
	 */
	public void setFunc0003001(String func0003001) {
		this.func0003001 = func0003001;
	}

	/**
	 * @return the func0003002
	 */
	public String getFunc0003002() {
		return func0003002;
	}

	/**
	 * @param func0003002 the func0003002 to set
	 */
	public void setFunc0003002(String func0003002) {
		this.func0003002 = func0003002;
	}

	/**
	 * @return the func0003003
	 */
	public String getFunc0003003() {
		return func0003003;
	}

	/**
	 * @param func0003003 the func0003003 to set
	 */
	public void setFunc0003003(String func0003003) {
		this.func0003003 = func0003003;
	}

	/**
	 * @return the func0003004
	 */
	public String getFunc0003004() {
		return func0003004;
	}

	/**
	 * @param func0003004 the func0003004 to set
	 */
	public void setFunc0003004(String func0003004) {
		this.func0003004 = func0003004;
	}

	/**
	 * @return the func0003005
	 */
	public String getFunc0003005() {
		return func0003005;
	}

	/**
	 * @param func0003005 the func0003005 to set
	 */
	public void setFunc0003005(String func0003005) {
		this.func0003005 = func0003005;
	}

	/**
	 * @return the func0001005
	 */
	public String getFunc0001005() {
		return func0001005;
	}

	/**
	 * @param func0001005 the func0001005 to set
	 */
	public void setFunc0001005(String func0001005) {
		this.func0001005 = func0001005;
	}

	/**
	 * @return the serviceAC0008
	 */
	public IAC0008Service getServiceAC0008() {
		return serviceAC0008;
	}

	/**
	 * @param serviceAC0008 the serviceAC0008 to set
	 */
	public void setServiceAC0008(IAC0008Service serviceAC0008) {
		this.serviceAC0008 = serviceAC0008;
	}

	/**
	 * @return the optSave
	 */
	public String getOptSave() {
		return optSave;
	}

	/**
	 * @param optSave the optSave to set
	 */
	public void setOptSave(String optSave) {
		this.optSave = optSave;
	}

	/**
	 * @return the optCancel
	 */
	public String getOptCancel() {
		return optCancel;
	}

	/**
	 * @param optCancel the optCancel to set
	 */
	public void setOptCancel(String optCancel) {
		this.optCancel = optCancel;
	}

	/**
	 * @return the optApproval
	 */
	public String getOptApproval() {
		return optApproval;
	}

	/**
	 * @param optApproval the optApproval to set
	 */
	public void setOptApproval(String optApproval) {
		this.optApproval = optApproval;
	}

	/**
	 * @return the optSearch
	 */
	public String getOptSearch() {
		return optSearch;
	}

	/**
	 * @param optSearch the optSearch to set
	 */
	public void setOptSearch(String optSearch) {
		this.optSearch = optSearch;
	}

	/**
	 * @return the optDel
	 */
	public String getOptDel() {
		return optDel;
	}

	/**
	 * @param optDel the optDel to set
	 */
	public void setOptDel(String optDel) {
		this.optDel = optDel;
	}

	/**
	 * @return the optEdit
	 */
	public String getOptEdit() {
		return optEdit;
	}

	/**
	 * @param optEdit the optEdit to set
	 */
	public void setOptEdit(String optEdit) {
		this.optEdit = optEdit;
	}

	/**
	 * @return the optView
	 */
	public String getOptView() {
		return optView;
	}

	/**
	 * @param optView the optView to set
	 */
	public void setOptView(String optView) {
		this.optView = optView;
	}

	/**
	 * @return the optAll
	 */
	public String getOptAll() {
		return optAll;
	}

	/**
	 * @param optAll the optAll to set
	 */
	public void setOptAll(String optAll) {
		this.optAll = optAll;
	}

	/**
	 * @return the optDepartment
	 */
	public String getOptDepartment() {
		return optDepartment;
	}

	/**
	 * @param optDepartment the optDepartment to set
	 */
	public void setOptDepartment(String optDepartment) {
		this.optDepartment = optDepartment;
	}

	/**
	 * @return the optPersonal
	 */
	public String getOptPersonal() {
		return optPersonal;
	}

	/**
	 * @param optPersonal the optPersonal to set
	 */
	public void setOptPersonal(String optPersonal) {
		this.optPersonal = optPersonal;
	}

	/**
	 * @return the optPdf
	 */
	public String getOptPdf() {
		return optPdf;
	}

	/**
	 * @param optPdf the optPdf to set
	 */
	public void setOptPdf(String optPdf) {
		this.optPdf = optPdf;
	}

	/**
	 * @return the func0001006
	 */
	public String getFunc0001006() {
		return func0001006;
	}

	/**
	 * @param func0001006 the func0001006 to set
	 */
	public void setFunc0001006(String func0001006) {
		this.func0001006 = func0001006;
	}

	/**
	 * @return the func0001007
	 */
	public String getFunc0001007() {
		return func0001007;
	}

	/**
	 * @param func0001007 the func0001007 to set
	 */
	public void setFunc0001007(String func0001007) {
		this.func0001007 = func0001007;
	}

	/**
	 * @return the func0001008
	 */
	public String getFunc0001008() {
		return func0001008;
	}

	/**
	 * @param func0001008 the func0001008 to set
	 */
	public void setFunc0001008(String func0001008) {
		this.func0001008 = func0001008;
	}

	/**
	 * @return the func0001009
	 */
	public String getFunc0001009() {
		return func0001009;
	}

	/**
	 * @param func0001009 the func0001009 to set
	 */
	public void setFunc0001009(String func0001009) {
		this.func0001009 = func0001009;
	}

	/**
	 * @return the func0004001
	 */
	public String getFunc0004001() {
		return func0004001;
	}

	/**
	 * @param func0004001 the func0004001 to set
	 */
	public void setFunc0004001(String func0004001) {
		this.func0004001 = func0004001;
	}

	/**
	 * @return the func0004002
	 */
	public String getFunc0004002() {
		return func0004002;
	}

	/**
	 * @param func0004002 the func0004002 to set
	 */
	public void setFunc0004002(String func0004002) {
		this.func0004002 = func0004002;
	}

	/**
	 * @return the func0004003
	 */
	public String getFunc0004003() {
		return func0004003;
	}

	/**
	 * @param func0004003 the func0004003 to set
	 */
	public void setFunc0004003(String func0004003) {
		this.func0004003 = func0004003;
	}

	/**
	 * @return the func0004004
	 */
	public String getFunc0004004() {
		return func0004004;
	}

	/**
	 * @param func0004004 the func0004004 to set
	 */
	public void setFunc0004004(String func0004004) {
		this.func0004004 = func0004004;
	}

	/**
	 * @return the func0005001
	 */
	public String getFunc0005001() {
		return func0005001;
	}

	/**
	 * @param func0005001 the func0005001 to set
	 */
	public void setFunc0005001(String func0005001) {
		this.func0005001 = func0005001;
	}

	/**
	 * @return the func0005002
	 */
	public String getFunc0005002() {
		return func0005002;
	}

	/**
	 * @param func0005002 the func0005002 to set
	 */
	public void setFunc0005002(String func0005002) {
		this.func0005002 = func0005002;
	}

	/**
	 * @return the serviceMP0011
	 */
	public IMP0011Service getServiceMP0011() {
		return serviceMP0011;
	}

	/**
	 * @param serviceMP0011 the serviceMP0011 to set
	 */
	public void setServiceMP0011(IMP0011Service serviceMP0011) {
		this.serviceMP0011 = serviceMP0011;
	}

	/**
	 * @return the func0002006
	 */
	public String getFunc0002006() {
		return func0002006;
	}

	/**
	 * @param func0002006 the func0002006 to set
	 */
	public void setFunc0002006(String func0002006) {
		this.func0002006 = func0002006;
	}

	/**
	 * @return the func0002007
	 */
	public String getFunc0002007() {
		return func0002007;
	}

	/**
	 * @param func0002007 the func0002007 to set
	 */
	public void setFunc0002007(String func0002007) {
		this.func0002007 = func0002007;
	}

	/**
	 * @return the func0002008
	 */
	public String getFunc0002008() {
		return func0002008;
	}

	/**
	 * @param func0002008 the func0002008 to set
	 */
	public void setFunc0002008(String func0002008) {
		this.func0002008 = func0002008;
	}

	/**
	 * @return the func0020001
	 */
	public String getFunc0020001() {
		return func0020001;
	}

	/**
	 * @param func0020001 the func0020001 to set
	 */
	public void setFunc0020001(String func0020001) {
		this.func0020001 = func0020001;
	}

	/**
	 * @return the func0020002
	 */
	public String getFunc0020002() {
		return func0020002;
	}

	/**
	 * @param func0020002 the func0020002 to set
	 */
	public void setFunc0020002(String func0020002) {
		this.func0020002 = func0020002;
	}

	/**
	 * @return the func0020003
	 */
	public String getFunc0020003() {
		return func0020003;
	}

	/**
	 * @param func0020003 the func0020003 to set
	 */
	public void setFunc0020003(String func0020003) {
		this.func0020003 = func0020003;
	}

	/**
	 * @return the func0020004
	 */
	public String getFunc0020004() {
		return func0020004;
	}

	/**
	 * @param func0020004 the func0020004 to set
	 */
	public void setFunc0020004(String func0020004) {
		this.func0020004 = func0020004;
	}

	/**
	 * @return the sys0001
	 */
	public String getSys0001() {
		return sys0001;
	}

	/**
	 * @param sys0001 the sys0001 to set
	 */
	public void setSys0001(String sys0001) {
		this.sys0001 = sys0001;
	}

	/**
	 * @return the sys0004
	 */
	public String getSys0004() {
		return sys0004;
	}

	/**
	 * @param sys0004 the sys0004 to set
	 */
	public void setSys0004(String sys0004) {
		this.sys0004 = sys0004;
	}

	/**
	 * @return the func0003006
	 */
	public String getFunc0003006() {
		return func0003006;
	}

	/**
	 * @param func0003006 the func0003006 to set
	 */
	public void setFunc0003006(String func0003006) {
		this.func0003006 = func0003006;
	}

	/**
	 * @return the func0021001
	 */
	public String getFunc0021001() {
		return func0021001;
	}

	/**
	 * @param func0021001 the func0021001 to set
	 */
	public void setFunc0021001(String func0021001) {
		this.func0021001 = func0021001;
	}

	/**
	 * @return the func0021002
	 */
	public String getFunc0021002() {
		return func0021002;
	}

	/**
	 * @param func0021002 the func0021002 to set
	 */
	public void setFunc0021002(String func0021002) {
		this.func0021002 = func0021002;
	}

	/**
	 * @return the func0003007
	 */
	public String getFunc0003007() {
		return func0003007;
	}

	/**
	 * @param func0003007 the func0003007 to set
	 */
	public void setFunc0003007(String func0003007) {
		this.func0003007 = func0003007;
	}

	/**
	 * @return the func0003008
	 */
	public String getFunc0003008() {
		return func0003008;
	}

	/**
	 * @param func0003008 the func0003008 to set
	 */
	public void setFunc0003008(String func0003008) {
		this.func0003008 = func0003008;
	}

	/**
	 * @return the func0003009
	 */
	public String getFunc0003009() {
		return func0003009;
	}

	/**
	 * @param func0003009 the func0003009 to set
	 */
	public void setFunc0003009(String func0003009) {
		this.func0003009 = func0003009;
	}

	/**
	 * @return the func0003010
	 */
	public String getFunc0003010() {
		return func0003010;
	}

	/**
	 * @param func0003010 the func0003010 to set
	 */
	public void setFunc0003010(String func0003010) {
		this.func0003010 = func0003010;
	}

	/**
	 * @return the loginHistoryInfo
	 */
	public List<MP0011> getLoginHistoryInfo() {
		return loginHistoryInfo;
	}

	/**
	 * @param loginHistoryInfo the loginHistoryInfo to set
	 */
	public void setLoginHistoryInfo(List<MP0011> loginHistoryInfo) {
		this.loginHistoryInfo = loginHistoryInfo;
	}

	/**
	 * @return the func0003011
	 */
	public String getFunc0003011() {
		return func0003011;
	}

	/**
	 * @param func0003011 the func0003011 to set
	 */
	public void setFunc0003011(String func0003011) {
		this.func0003011 = func0003011;
	}

	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the mp0011EmpNumber
	 */
	public String getMp0011EmpNumber() {
		return mp0011EmpNumber;
	}

	/**
	 * @param mp0011EmpNumber the mp0011EmpNumber to set
	 */
	public void setMp0011EmpNumber(String mp0011EmpNumber) {
		this.mp0011EmpNumber = mp0011EmpNumber;
	}

	/**
	 * @return the pageNum
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * @return the pageCount
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount the pageCount to set
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @return the errMsg
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * @param errMsg the errMsg to set
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	/**
	 * @return the func0021003
	 */
	public String getFunc0021003() {
		return func0021003;
	}

	/**
	 * @param func0021003 the func0021003 to set
	 */
	public void setFunc0021003(String func0021003) {
		this.func0021003 = func0021003;
	}

	/**
	 * @return the func0021004
	 */
	public String getFunc0021004() {
		return func0021004;
	}

	/**
	 * @param func0021004 the func0021004 to set
	 */
	public void setFunc0021004(String func0021004) {
		this.func0021004 = func0021004;
	}

	/**
	 * @return the func0021005
	 */
	public String getFunc0021005() {
		return func0021005;
	}

	/**
	 * @param func0021005 the func0021005 to set
	 */
	public void setFunc0021005(String func0021005) {
		this.func0021005 = func0021005;
	}

	/**
	 * @return the func0021006
	 */
	public String getFunc0021006() {
		return func0021006;
	}

	/**
	 * @param func0021006 the func0021006 to set
	 */
	public void setFunc0021006(String func0021006) {
		this.func0021006 = func0021006;
	}

	/**
	 * @return the func0021007
	 */
	public String getFunc0021007() {
		return func0021007;
	}

	/**
	 * @param func0021007 the func0021007 to set
	 */
	public void setFunc0021007(String func0021007) {
		this.func0021007 = func0021007;
	}

	/**
	 * @return the sys0005
	 */
	public String getSys0005() {
		return sys0005;
	}

	/**
	 * @param sys0005 the sys0005 to set
	 */
	public void setSys0005(String sys0005) {
		this.sys0005 = sys0005;
	}

	/**
	 * @return the sys0006
	 */
	public String getSys0006() {
		return sys0006;
	}

	/**
	 * @param sys0006 the sys0006 to set
	 */
	public void setSys0006(String sys0006) {
		this.sys0006 = sys0006;
	}

	/**
	 * @return the serviceMP1010
	 */
	public IMP1010Service getServiceMP1010() {
		return serviceMP1010;
	}

	/**
	 * @param serviceMP1010 the serviceMP1010 to set
	 */
	public void setServiceMP1010(IMP1010Service serviceMP1010) {
		this.serviceMP1010 = serviceMP1010;
	}

	public IJE0101Service getServiceJE0101() {
		return serviceJE0101;
	}

	public void setServiceJE0101(IJE0101Service serviceJE0101) {
		this.serviceJE0101 = serviceJE0101;
	}

	public String getCompanyEmailAddr() {
		return companyEmailAddr;
	}

	public void setCompanyEmailAddr(String companyEmailAddr) {
		this.companyEmailAddr = companyEmailAddr;
	}

	public String getForgetPasswordSeq() {
		return forgetPasswordSeq;
	}

	public void setForgetPasswordSeq(String forgetPasswordSeq) {
		this.forgetPasswordSeq = forgetPasswordSeq;
	}

	public static Log getLog() {
		return log;
	}

	public String getFunc001006() {
		return func001006;
	}

	public void setFunc001006(String func001006) {
		this.func001006 = func001006;
	}

	public String getFunc001006001() {
		return func001006001;
	}

	public void setFunc001006001(String func001006001) {
		this.func001006001 = func001006001;
	}

	public String getFunc001006002() {
		return func001006002;
	}

	public void setFunc001006002(String func001006002) {
		this.func001006002 = func001006002;
	}

	public String getChangePsdType() {
		return changePsdType;
	}

	public void setChangePsdType(String changePsdType) {
		this.changePsdType = changePsdType;
	}



}
