package action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import service.IAC0006Service;
import service.IAC0007Service;
import service.IAC0008Service;
import service.IAC0009Service;
import service.IHOLIDAYService;
import service.IMP0002Service;
import service.IMP0008Service;
import service.IMP0009Service;
import service.IMP0010Service;
import service.IMP0011Service;
import service.IMP1001Service;
import service.IMP7008Service;
import service.IMP8004Service;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import common.Constant;
import common.LogUtil;
import common.UtilCommon;
import common.UtilDate;

import entity.HOLIDAY;
import entity.MP0002;
import entity.MP0008;
import entity.MP0009;
import entity.MP0010;
import entity.MP1001;
import entity.MP7008;
import entity.MP8004;

public class DepPositionMngAction extends ActionSupport{

	private static final long serialVersionUID = 4925143279577759645L;
	private static final Log log = LogFactory.getLog(DepPositionMngAction.class);

	private IMP0009Service service;
	private IMP0008Service serviceMP0008;
	private IMP0002Service serviceMP0002;
	private IMP0010Service serviceMP0010;
	private IMP0011Service serviceMP0011;
	private IHOLIDAYService serviceHoliday;
	private IMP1001Service serviceMP1001;
	private IMP7008Service serviceMP7008;
	private IMP8004Service serviceMP8004;
	
	private IAC0006Service serviceAC0006;
	private IAC0007Service serviceAC0007;
	private IAC0008Service serviceAC0008;
	private IAC0009Service serviceAC0009;
	
	private MP0009 mp0009 = new MP0009();
	private MP0002 mp0002 = new MP0002();
	private MP0010 mp0010 = new MP0010();
	private HOLIDAY holiday = new HOLIDAY();
	
	private List<MP0009> positionDetailList = new ArrayList<MP0009>();
	private List<MP0008> mp0008InfoList = new ArrayList<MP0008>();
	private List<HOLIDAY> holidayInfoList = new ArrayList<HOLIDAY>();
	private List<MP0010> mp0010InfoList = new ArrayList<MP0010>();
	private List<MP0002> departmentList = new ArrayList<MP0002>();
	
	private Map<String,String> mp0002StatusList = new LinkedHashMap<String,String>();
	
	private String[] positionArray ;
	
	private String positionSeq;
	private String mp0002Status;
	private String mp0002Seq;
	private String mp0010Seq;
	private String holidaySeq;
	
	// 通用变量
	private int pageNum;
	private int pageCount;
	private String pageType;
	private String confirmType;
	private String loginUserID;
	private String loginUserDep;
	private String errMsg;
	private String errMsg1;
	private String commonSeq;
	private List<String>  depArray = new ArrayList<String>();
	private String pram1;
	private String pram2;
	private String pram3;
	private String pram4;
	
	//------------------权限控制用变量---------------	
	private String optAdd = "0";
	private String optDel = "0";
	private String optEdit = "0";
	private String optSave="0";
	
	// 初始化职位信息列表
	public String positionInfoInit(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Position Information Init");
			//----------------------------Operation History------------------
			
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0003004,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_ADD)){
				optAdd = "1";
			}
			if(optMap.containsKey(Constant.OPT_EDIT)){
				optEdit = "1";
			}
			if(optMap.containsKey(Constant.OPT_DELETE)){
				optDel = "1";
			}
			
			positionDetailList = service.findAll();
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	// 新增职位信息
	public String positionInfoAddInit(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Position Information Page Init");
			//----------------------------Operation History------------------
			
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0003004,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_ADD)){
				optAdd = "1";
			}
			if(optMap.containsKey(Constant.OPT_EDIT)){
				optEdit = "1";
			}
			if(optMap.containsKey(Constant.OPT_DELETE)){
				optDel = "1";
			}
			
			if(positionSeq != null && !positionSeq.equals("")){
				mp0009 = service.findById(Integer.parseInt(positionSeq));
			}
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	// 新增职位信息
	public String positionInfoAdd(){
		// 缓存登陆用户的信息
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String loginName = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			//----------------------------Operation History------------------
			
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0003009,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_ADD)){
				optAdd = "1";
			}
            
		    // 取得系统当前时间
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addtime = sdf.format(now);
			
			if(mp0009.getMP0009_SEQ() > 0){// 编辑职位信息
				MP0009 mp9 = service.findById(mp0009.getMP0009_SEQ());
				
				mp9.setMP0009_POSITION_NAME_C(mp0009.getMP0009_POSITION_NAME_C());
				mp9.setMP0009_POSITION_NAME_E(mp0009.getMP0009_POSITION_NAME_E());
				mp9.setMP0009_EDIT_USER(loginName);
				mp9.setMP0009_EDIT_DATETIME(addtime);
				
				service.update(mp9);
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Edit Position Information, Position Name:{" + mp9.getMP0009_POSITION_NAME_E() + "}");
			}else{// 新增职位信息
				if(mp0002Seq != null && !mp0002Seq.equals("")){
					mp0009.setMP0009_CREATE_USER(loginName);
					mp0009.setMP0009_CREATE_DATETIME(addtime);
					mp0009.setMP0009_POSITION_STATUS("1");
					service.save(mp0009);
					
					logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Position Information, Position Name:{" + mp0009.getMP0009_POSITION_NAME_E() + "}");
					
					MP0008 mp08 = new MP0008();
					mp08.setMP0008_DEPARTMENT_ID(Integer.parseInt(mp0002Seq));
					mp08.setMP0008_POSITION_ID(mp0009.getMP0009_SEQ());
					serviceMP0008.save(mp08);
				}else{
					out.println("<script type='text/javascript'>alert('The parameter of Seq is null.');window.close();</script>");
			        out.flush();
			        out.close();
				}
			}
			
			out.println("<script type='text/javascript'>window.dialogArguments.document.getElementById('refreshPage').click();window.close();</script>");
	        out.flush();
	        out.close();
			
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	// 编辑职位信息
	public String positionInfoDel(){
		// 缓存登陆用户的信息
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String loginName = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{		
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0003004,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_ADD)){
				optAdd = "1";
			}
			if(optMap.containsKey(Constant.OPT_EDIT)){
				optEdit = "1";
			}
			if(optMap.containsKey(Constant.OPT_DELETE)){
				optDel = "1";
			}
            
		    // 取得系统当前时间
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addtime = sdf.format(now);
			
			if(!positionSeq.equals("")){
				mp0009 = service.findById(Integer.parseInt(positionSeq));
				
				mp0009.setMP0009_POSITION_STATUS("2");
				mp0009.setMP0009_EDIT_USER(loginName);
				mp0009.setMP0009_EDIT_DATETIME(addtime);
				
				service.update(mp0009);
				
				//----------------------------Operation History------------------
				LogUtil logUtil = new LogUtil();
				logUtil.setServiceMP0011(serviceMP0011);
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Delete Position Information, Employee Num:{" + mp0009.getMP0009_POSITION_NAME_E() + "}");
				//----------------------------Operation History------------------
			}
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	// 职位信息查询
	public String positionDetailInfoSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Position Detail Information");
			//----------------------------Operation History------------------
			
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0003004,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_ADD)){
				optAdd = "1";
			}
			if(optMap.containsKey(Constant.OPT_EDIT)){
				optEdit = "1";
			}
			if(optMap.containsKey(Constant.OPT_DELETE)){
				optDel = "1";
			}
			
			positionDetailList = service.findAll();
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	// 部门新增和编辑页面初始化
	public String departmentAddInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			mp0002StatusList = Constant.getCommonStatus();
			errMsg = "";
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Department Information Init");
			//----------------------------Operation History------------------
			
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0003008,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_SAVE)){
				optSave = "1";
			}
			
			if(pageType.equals("edit")){
				if(mp0002Seq == null || mp0002Seq.equals("")){
					if(session.containsKey("ERR_MSG")){
						session.remove("ERR_MSG");
						session.put("ERR_MSG", "parameter[MP0002_SEQ] is null");
					}
					return "error";
				}else{
					mp0002 = serviceMP0002.findById(Integer.parseInt(mp0002Seq));
					mp0002Status = mp0002.getMP0002_DEPARTMENT_STATUS();
				}
				
				return "edit";
			}else{
				return "add";
			}
		}catch(Exception ex){
			log.info(ex.getMessage());
			if(session.containsKey("ERR_MSG")){
				session.remove("ERR_MSG");
				session.put("ERR_MSG", ex.getMessage());
			}
			return "error";
		}
	}
	// 保存部门信息
	public String departmentInfoSave(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String result;
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			//----------------------------Operation History------------------
			
			if(mp0002Status == null || mp0002Status.equals("") || mp0002Status.equals("-1")){
				mp0002Status = "0";
			}
			
			if(pageType.equals("edit")){
				if(mp0002Seq == null || mp0002Seq.equals("")){
					if(session.containsKey("ERR_MSG")){
						session.remove("ERR_MSG");
						session.put("ERR_MSG", "parameter[MP0002_SEQ] is null");
					}
					return "error";
				}else{
					MP0002 mp02 = serviceMP0002.findById(Integer.parseInt(mp0002Seq));
					
					mp02.setMP0002_DEPARTMENT_NUM(mp0002.getMP0002_DEPARTMENT_NUM());//部门编码
					mp02.setMP0002_DEPARTMENT_NAME(mp0002.getMP0002_DEPARTMENT_NAME());//部门名称
					mp02.setMP0002_DEPARTMENT_DESC(mp0002.getMP0002_DEPARTMENT_DESC());//描述
					mp02.setMP0002_DEPARTMENT_STATUS(mp0002Status);//状态
					mp02.setMP0002_EDIT_DATETIME(UtilDate.getToday());//修改时间
					mp02.setMP0002_EDIT_USER(employeeData.getMP1001_EMPLOYEE_NUM());//修改人
					
					serviceMP0002.update(mp02);	
					errMsg = "Save Success";
					result = "edit";
					logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Edit Department Information Init, Department Name:{" + mp02.getMP0002_DEPARTMENT_NAME() + "}");
				}
			}else{
				mp0002.setMP0002_DEPARTMENT_STATUS(mp0002Status);//状态
				mp0002.setMP0002_CREATE_DATETIME(UtilDate.getToday());//创建时间
				mp0002.setMP0002_CREATE_USER(employeeData.getMP1001_EMPLOYEE_NUM());//创建人
				serviceMP0002.save(mp0002);
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Edit Department Information Init, Department Name:{" + mp0002.getMP0002_DEPARTMENT_NAME() + "}");
				
				// 新增新部门成功后，往该部门新加一个经理职位
				MP0008 mp08 = new MP0008();
				mp08.setMP0008_DEPARTMENT_ID(mp0002.getMP0002_SEQ());
				mp08.setMP0008_POSITION_ID(1);
				serviceMP0008.save(mp08);
				
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script type='text/javascript'>window.close();</script>");
		        out.flush();
		        out.close();

		        result = null;
			}
			// 如果新增或者编辑了部门信息，需要更新Session中部门列表信息
			if(session.containsKey("DEPARTMENT_LIST")){
				session.remove("DEPARTMENT_LIST");
			}
			List<MP0002> departmentInfoList = serviceMP0002.findAll(true);
			session.put("DEPARTMENT_LIST", departmentInfoList);
			
			return result;
			
		}catch(Exception ex){
			log.info(ex.getMessage());
			if(session.containsKey("ERR_MSG")){
				session.remove("ERR_MSG");
				session.put("ERR_MSG", ex.getMessage());
			}
			return "error";
		}
	}
	
	public void validateDepartmentInfoSave(){
		if(mp0002.getMP0002_DEPARTMENT_NUM().equals("")){
			addFieldError("MP0002_DEPARTMENT_NUM", "erroro~");
		}
	}
	
	// 部门职位设置页面初始化
	public String departmentPositionSetInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);

		try{
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0003009,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_ADD)){
				optAdd = "1";
			}
			
			if(mp0002Seq != null && !mp0002Seq.equals("")){
				mp0008InfoList = serviceMP0008.findByDepartmentId(mp0002Seq);
			}
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Set Department Position Page Init");
			//----------------------------Operation History------------------
			
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
	// 保存部门职位信息
	public String departmentPosotionSave(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Save Department Position Information");
			//----------------------------Operation History------------------
			
			if(mp0002Seq != null && !mp0002Seq.equals("")){
				
			}
			
			errMsg = "Save Success";
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
	// 页面刷新
	public String departmentPositionRefresh(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);

		try{
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0003009,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_ADD)){
				optAdd = "1";
			}
			
			if(mp0002Seq != null && !mp0002Seq.equals("")){
				mp0008InfoList = serviceMP0008.findByDepartmentId(mp0002Seq);
			}
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Refresh Department Position Information");
			//----------------------------Operation History------------------
			
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
	
	// 公共假日及特殊日期设置页面初始化
	public String holidayMngInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);

		try{
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0003010,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_ADD)){
				optAdd = "1";
			}
			if(optMap.containsKey(Constant.OPT_DELETE)){
				optDel = "1";
			}

			holidayInfoList = serviceHoliday.findAll();
			mp0010InfoList = serviceMP0010.findAll();
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Holiday Management Page Init");
			//----------------------------Operation History------------------
			
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
    // 特殊日期列表信息刷新
	public String mp0010InfoListRefresh(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);

		try{
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0003010,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_ADD)){
				optAdd = "1";
			}
			if(optMap.containsKey(Constant.OPT_DELETE)){
				optDel = "1";
			}
			
			mp0010InfoList = serviceMP0010.findAll();
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"MP0010 Information Refresh");
			//----------------------------Operation History------------------
			
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
	// 公共假日信息列表刷新
	public String holidayInfoListRefresh(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);

		try{
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0003010,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_ADD)){
				optAdd = "1";
			}
			if(optMap.containsKey(Constant.OPT_DELETE)){
				optDel = "1";
			}
			
			holidayInfoList = serviceHoliday.findAll();
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Public Holiday Information Refresh");
			//----------------------------Operation History------------------
			
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
	// 新增特殊日期记录
	public String mp0010InfoAddInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add MP0010 Information Page Init");
			//----------------------------Operation History------------------
			
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
	// 新增公共假日记录
	public String holidayInfoAddInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Holiday Information Page Init");
			//----------------------------Operation History------------------
			
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
	// 保存特殊日期记录
	public String mp0010InfoSave(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			serviceMP0010.save(mp0010);
			errMsg = "Save Success";
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Save MP0010 Information Page Init");
			//----------------------------Operation History------------------
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>window.dialogArguments.document.getElementById('refreshtab1').click();window.close();</script>");
	        out.flush();
	        out.close();
			
			
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
	// 保存公共假日记录
	public String holidayInfoSave(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Save Holiday Information");
			//----------------------------Operation History------------------
			
			serviceHoliday.save(holiday);
			errMsg1 = "Save Success";
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>window.dialogArguments.document.getElementById('refreshtab2').click();window.close();</script>");
	        out.flush();
	        out.close();
			
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
	// 删除特殊日期记录
	public String mp0010InfoListDelete(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			if(mp0010Seq != null && !mp0010Seq.equals("")){
				MP0010 mp10 = serviceMP0010.findById(mp0010Seq);
				serviceMP0010.delete(mp10);
				
				//----------------------------Operation History------------------
				LogUtil logUtil = new LogUtil();
				logUtil.setServiceMP0011(serviceMP0011);
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Delete Information From MP0010,Name:{" + mp10.getMP0010_DATETIME() + "}");
				//----------------------------Operation History------------------
			}

			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0003010,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_ADD)){
				optAdd = "1";
			}
			if(optMap.containsKey(Constant.OPT_DELETE)){
				optDel = "1";
			}
			
			mp0010InfoList = serviceMP0010.findAll();

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
	// 删除公共假日记录
	public String holidayInfoListDelete(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			if(holidaySeq != null && !holidaySeq.equals("")){
				HOLIDAY holiday = serviceHoliday.findById(Integer.parseInt(holidaySeq));
				serviceHoliday.delete(holiday);
				
				//----------------------------Operation History------------------
				LogUtil logUtil = new LogUtil();
				logUtil.setServiceMP0011(serviceMP0011);
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Delete Public Holiday Information,Name:{" + holiday.getHOLIDAY_DATE() + " " + holiday.getHOLIDAY_NAME() + "}");
				//----------------------------Operation History------------------
			}

			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0003010,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_ADD)){
				optAdd = "1";
			}
			if(optMap.containsKey(Constant.OPT_DELETE)){
				optDel = "1";
			}
			
			holidayInfoList = serviceHoliday.findAll();
			
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
	
	//供应商选择共同页面初始化处理
	public String departmentSelectInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			getDepartmentSelectData();
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Department Select Common Page");
			//----------------------------Operation History------------------
			
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
	// 数据检索
	private void getDepartmentSelectData(){	
		StringBuffer searchStr = new StringBuffer();
		searchStr.append(" and mp11.MP1001_STATUS  in ('1','2') ");
		
		departmentList = serviceMP0002.findAll(true);
	}
	// 保存数据
	public String departmentSelectSave(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Department Select Save");
			//----------------------------Operation History------------------
			StringBuffer searchStr = new StringBuffer();
			StringBuffer search = new StringBuffer();
			// 普通员工考评用
			if(pageType != null && pageType.equals("performance")){
				for(int i=0, j=depArray.size(); i<j; i++){
					if(depArray.get(i) != null && !depArray.get(i).equals("")){
						if(i >0){
							searchStr.append(",");
						}
						searchStr.append( "'" + depArray.get(i) + "'");
					}
				}
				
				if(!searchStr.toString().equals("")){
					search.append("  and mp11.MP1001_DEPARTMENT_ID in ( " + searchStr.toString() + " ) ");
					search.append(" and mp11.MP1001_STATUS in ('1','2') ");
					List<MP1001> mp11List = serviceMP1001.findData(search.toString(), "", -1, -1);
					
					MP7008 mp78 = new MP7008();
					for(int i=0, j=mp11List.size(); i<j; i++){
						MP1001 mp11 = mp11List.get(i);
						
						searchStr = new StringBuffer();
						searchStr.append(" and mp78.MP7008_PLAN_SEQ = '" + commonSeq + "' ");
						searchStr.append(" and mp78.MP7008_EMPLOYEE_NUM = '" + mp11.getMP1001_EMPLOYEE_NUM() + "' ");
						List<MP7008> mp78List = serviceMP7008.findDataBySelfDefined(searchStr.toString(), "", -1, -1);
						if(mp78List != null && mp78List.size() == 0){
							mp78 = new MP7008();
							mp78.setMP7008_SEQ(Constant.generateSeq() + UtilCommon.getTempSeq());
							mp78.setMP7008_PLAN_SEQ(commonSeq);
							mp78.setMP7008_EMPLOYEE_NUM(mp11.getMP1001_EMPLOYEE_NUM());
							
							serviceMP7008.save(mp78);
						}else{
							logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Employee has exist:{Employee Number:" + mp11.getMP1001_EMPLOYEE_NUM() + ", Plan Seq:" + commonSeq + "}");
						}
					}
				}
			}else if(pageType != null && pageType.equals("performance2")){ // 部门考评用
				MP8004 mp84 = null;
				for(int i=0, j=depArray.size(); i<j; i++){
					search = new StringBuffer();
					search.append("  and mp8004.MP8004_DEPARTMENT_NUM = '" + depArray.get(i) + "' ");
					search.append("  and mp8004.MP8004_PLAN_SEQ = '" + commonSeq + "' ");
					List<MP8004> mp84List = serviceMP8004.findDataBySelfDefined(search.toString(), "", -1, -1);
					
					if(mp84List == null || mp84List.size() ==0){
						mp84 = new MP8004();
						mp84.setMP8004_DEPARTMENT_NUM(depArray.get(i));
						mp84.setMP8004_PLAN_SEQ(commonSeq);
						mp84.setMP8004_SEQ(Constant.generateSeq() + UtilCommon.getTempSeq() + i);
						
						serviceMP8004.save(mp84);
					}
				}
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.append("<script type='text/javascript'>");
			out.println(" window.dialogArguments.document.getElementById('depRefresh').click();");
			out.append(" window.close();");
			out.append("</script>");
	        out.flush();
	        out.close();
			
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
	

	/**
	 * @return the service
	 */
	public IMP0009Service getService() {
		return service;
	}
	/**
	 * @param service the service to set
	 */
	public void setService(IMP0009Service service) {
		this.service = service;
	}
	/**
	 * @return the serviceMP0008
	 */
	public IMP0008Service getServiceMP0008() {
		return serviceMP0008;
	}
	/**
	 * @param serviceMP0008 the serviceMP0008 to set
	 */
	public void setServiceMP0008(IMP0008Service serviceMP0008) {
		this.serviceMP0008 = serviceMP0008;
	}

	/**
	 * @return the positionDetailList
	 */
	public List<MP0009> getPositionDetailList() {
		return positionDetailList;
	}

	/**
	 * @param positionDetailList the positionDetailList to set
	 */
	public void setPositionDetailList(List<MP0009> positionDetailList) {
		this.positionDetailList = positionDetailList;
	}

	/**
	 * @return the mp0009
	 */
	public MP0009 getMp0009() {
		return mp0009;
	}

	/**
	 * @param mp0009 the mp0009 to set
	 */
	public void setMp0009(MP0009 mp0009) {
		this.mp0009 = mp0009;
	}

	/**
	 * @return the positionSeq
	 */
	public String getPositionSeq() {
		return positionSeq;
	}

	/**
	 * @param positionSeq the positionSeq to set
	 */
	public void setPositionSeq(String positionSeq) {
		this.positionSeq = positionSeq;
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
	 * @return the optAdd
	 */
	public String getOptAdd() {
		return optAdd;
	}

	/**
	 * @param optAdd the optAdd to set
	 */
	public void setOptAdd(String optAdd) {
		this.optAdd = optAdd;
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
	 * @return the mp0002Status
	 */
	public String getMp0002Status() {
		return mp0002Status;
	}

	/**
	 * @param mp0002Status the mp0002Status to set
	 */
	public void setMp0002Status(String mp0002Status) {
		this.mp0002Status = mp0002Status;
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
	 * @return the pageType
	 */
	public String getPageType() {
		return pageType;
	}

	/**
	 * @param pageType the pageType to set
	 */
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	/**
	 * @return the confirmType
	 */
	public String getConfirmType() {
		return confirmType;
	}

	/**
	 * @param confirmType the confirmType to set
	 */
	public void setConfirmType(String confirmType) {
		this.confirmType = confirmType;
	}

	/**
	 * @return the loginUserID
	 */
	public String getLoginUserID() {
		return loginUserID;
	}

	/**
	 * @param loginUserID the loginUserID to set
	 */
	public void setLoginUserID(String loginUserID) {
		this.loginUserID = loginUserID;
	}

	/**
	 * @return the loginUserDep
	 */
	public String getLoginUserDep() {
		return loginUserDep;
	}

	/**
	 * @param loginUserDep the loginUserDep to set
	 */
	public void setLoginUserDep(String loginUserDep) {
		this.loginUserDep = loginUserDep;
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
	 * @return the mp0002
	 */
	public MP0002 getMp0002() {
		return mp0002;
	}

	/**
	 * @param mp0002 the mp0002 to set
	 */
	public void setMp0002(MP0002 mp0002) {
		this.mp0002 = mp0002;
	}

	/**
	 * @return the mp0002StatusList
	 */
	public Map<String, String> getMp0002StatusList() {
		return mp0002StatusList;
	}

	/**
	 * @param mp0002StatusList the mp0002StatusList to set
	 */
	public void setMp0002StatusList(Map<String, String> mp0002StatusList) {
		this.mp0002StatusList = mp0002StatusList;
	}

	/**
	 * @return the mp0002Seq
	 */
	public String getMp0002Seq() {
		return mp0002Seq;
	}

	/**
	 * @param mp0002Seq the mp0002Seq to set
	 */
	public void setMp0002Seq(String mp0002Seq) {
		this.mp0002Seq = mp0002Seq;
	}

	/**
	 * @return the serviceMP0002
	 */
	public IMP0002Service getServiceMP0002() {
		return serviceMP0002;
	}

	/**
	 * @param serviceMP0002 the serviceMP0002 to set
	 */
	public void setServiceMP0002(IMP0002Service serviceMP0002) {
		this.serviceMP0002 = serviceMP0002;
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
	 * @return the positionArray
	 */
	public String[] getPositionArray() {
		return positionArray;
	}

	/**
	 * @param positionArray the positionArray to set
	 */
	public void setPositionArray(String[] positionArray) {
		this.positionArray = positionArray;
	}
	/**
	 * @return the mp0008InfoList
	 */
	public List<MP0008> getMp0008InfoList() {
		return mp0008InfoList;
	}
	/**
	 * @param mp0008InfoList the mp0008InfoList to set
	 */
	public void setMp0008InfoList(List<MP0008> mp0008InfoList) {
		this.mp0008InfoList = mp0008InfoList;
	}
	/**
	 * @return the holidayInfoList
	 */
	public List<HOLIDAY> getHolidayInfoList() {
		return holidayInfoList;
	}
	/**
	 * @param holidayInfoList the holidayInfoList to set
	 */
	public void setHolidayInfoList(List<HOLIDAY> holidayInfoList) {
		this.holidayInfoList = holidayInfoList;
	}
	/**
	 * @return the serviceHoliday
	 */
	public IHOLIDAYService getServiceHoliday() {
		return serviceHoliday;
	}
	/**
	 * @param serviceHoliday the serviceHoliday to set
	 */
	public void setServiceHoliday(IHOLIDAYService serviceHoliday) {
		this.serviceHoliday = serviceHoliday;
	}
	/**
	 * @return the serviceMP0010
	 */
	public IMP0010Service getServiceMP0010() {
		return serviceMP0010;
	}
	/**
	 * @param serviceMP0010 the serviceMP0010 to set
	 */
	public void setServiceMP0010(IMP0010Service serviceMP0010) {
		this.serviceMP0010 = serviceMP0010;
	}
	/**
	 * @return the mp0010InfoList
	 */
	public List<MP0010> getMp0010InfoList() {
		return mp0010InfoList;
	}
	/**
	 * @param mp0010InfoList the mp0010InfoList to set
	 */
	public void setMp0010InfoList(List<MP0010> mp0010InfoList) {
		this.mp0010InfoList = mp0010InfoList;
	}
	/**
	 * @return the mp0010
	 */
	public MP0010 getMp0010() {
		return mp0010;
	}
	/**
	 * @param mp0010 the mp0010 to set
	 */
	public void setMp0010(MP0010 mp0010) {
		this.mp0010 = mp0010;
	}
	/**
	 * @return the holiday
	 */
	public HOLIDAY getHoliday() {
		return holiday;
	}
	/**
	 * @param holiday the holiday to set
	 */
	public void setHoliday(HOLIDAY holiday) {
		this.holiday = holiday;
	}
	/**
	 * @return the errMsg1
	 */
	public String getErrMsg1() {
		return errMsg1;
	}
	/**
	 * @param errMsg1 the errMsg1 to set
	 */
	public void setErrMsg1(String errMsg1) {
		this.errMsg1 = errMsg1;
	}
	/**
	 * @return the mp0010Seq
	 */
	public String getMp0010Seq() {
		return mp0010Seq;
	}
	/**
	 * @param mp0010Seq the mp0010Seq to set
	 */
	public void setMp0010Seq(String mp0010Seq) {
		this.mp0010Seq = mp0010Seq;
	}
	/**
	 * @return the holidaySeq
	 */
	public String getHolidaySeq() {
		return holidaySeq;
	}
	/**
	 * @param holidaySeq the holidaySeq to set
	 */
	public void setHolidaySeq(String holidaySeq) {
		this.holidaySeq = holidaySeq;
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
	/**
	 * @return the depArray
	 */
	public List<String> getDepArray() {
		return depArray;
	}
	/**
	 * @param depArray the depArray to set
	 */
	public void setDepArray(List<String> depArray) {
		this.depArray = depArray;
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
	 * @return the commonSeq
	 */
	public String getCommonSeq() {
		return commonSeq;
	}
	/**
	 * @param commonSeq the commonSeq to set
	 */
	public void setCommonSeq(String commonSeq) {
		this.commonSeq = commonSeq;
	}
	/**
	 * @return the serviceMP7008
	 */
	public IMP7008Service getServiceMP7008() {
		return serviceMP7008;
	}
	/**
	 * @param serviceMP7008 the serviceMP7008 to set
	 */
	public void setServiceMP7008(IMP7008Service serviceMP7008) {
		this.serviceMP7008 = serviceMP7008;
	}
	/**
	 * @return the serviceMP8004
	 */
	public IMP8004Service getServiceMP8004() {
		return serviceMP8004;
	}
	/**
	 * @param serviceMP8004 the serviceMP8004 to set
	 */
	public void setServiceMP8004(IMP8004Service serviceMP8004) {
		this.serviceMP8004 = serviceMP8004;
	}
	/**
	 * @return the pram1
	 */
	public String getPram1() {
		return pram1;
	}
	/**
	 * @param pram1 the pram1 to set
	 */
	public void setPram1(String pram1) {
		this.pram1 = pram1;
	}
	/**
	 * @return the pram2
	 */
	public String getPram2() {
		return pram2;
	}
	/**
	 * @param pram2 the pram2 to set
	 */
	public void setPram2(String pram2) {
		this.pram2 = pram2;
	}
	/**
	 * @return the pram3
	 */
	public String getPram3() {
		return pram3;
	}
	/**
	 * @param pram3 the pram3 to set
	 */
	public void setPram3(String pram3) {
		this.pram3 = pram3;
	}
	/**
	 * @return the pram4
	 */
	public String getPram4() {
		return pram4;
	}
	/**
	 * @param pram4 the pram4 to set
	 */
	public void setPram4(String pram4) {
		this.pram4 = pram4;
	}
}
