package action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import service.IAC0006Service;
import service.IAC0007Service;
import service.IAC0008Service;
import service.IAC0009Service;
import service.IMP0009Service;
import service.IMP0011Service;
import service.IMP1001Service;
import service.IMP7001Service;
import service.IMP7002Service;
import service.IMP7003Service;
import service.IMP7004Service;
import service.IMP7005Service;
import service.IMP7006Service;
import service.IMP7007Service;
import service.IMP7008Service;
import service.IMP8001Service;
import service.IMP8002Service;
import service.IMP8003Service;
import service.IMP8004Service;
import service.IMP8005Service;
import service.IMP8006Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import common.Arith;
import common.ChartUtil;
import common.Constant;
import common.ExcelUtil;
import common.LogUtil;
import common.UtilCommon;
import common.UtilDate;

import entity.MP0009;
import entity.MP1001;
import entity.MP7001;
import entity.MP7002;
import entity.MP7003;
import entity.MP7004;
import entity.MP7005;
import entity.MP7006;
import entity.MP7007;
import entity.MP7008;
import entity.MP8001;
import entity.MP8002;
import entity.MP8003;
import entity.MP8004;
import entity.MP8005;
import entity.MP8006;

public class PerformanceMngAction extends ActionSupport {

	private static final long serialVersionUID = 1525933647042309067L;
	private static final Log log = LogFactory.getLog(PerformanceMngAction.class);
	
	private IMP7001Service serviceMP7001;
	private IMP7002Service serviceMP7002;
	private IMP7003Service serviceMP7003;
	private IMP7004Service serviceMP7004;
	private IMP7005Service serviceMP7005;
	private IMP7006Service serviceMP7006;
	private IMP7007Service serviceMP7007;
	
	private IMP8001Service serviceMP8001;
	private IMP8002Service serviceMP8002;
	private IMP8003Service serviceMP8003;
	private IMP8004Service serviceMP8004;
	private IMP8005Service serviceMP8005;
	private IMP8006Service serviceMP8006;
	
	private IMP1001Service serviceMP1001;
	private IMP0011Service serviceMP0011;
	private IMP0009Service serviceMP0009;
	private IMP7008Service serviceMP7008;
	
	private IAC0006Service serviceAC0006;
	private IAC0007Service serviceAC0007;
	private IAC0008Service serviceAC0008;
	private IAC0009Service serviceAC0009;
	
	private MP7001 mp7001 = new MP7001();
	private MP7002 mp7002 = new MP7002();
	private MP7003 mp7003 = new MP7003();
	private MP7004 mp7004 = new MP7004();
	private MP7005 mp7005 = new MP7005();
	private MP7006 mp7006 = new MP7006();
	private MP7007 mp7007 = new MP7007();
	private MP1001 mp1001 = new MP1001();
	private MP8001 mp8001 = new MP8001();
	private MP8002 mp8002 = new MP8002();
	private MP8003 mp8003 = new MP8003();
	private MP8005 mp8005 = new MP8005();
	
	private List<MP7001> examQuestionInfoList = new ArrayList<MP7001>();
	private List<MP7002> examPlanInfoList = new ArrayList<MP7002>();
	private List<MP7003> examPlanInfoList2 = new ArrayList<MP7003>();
	private List<MP7003> examItemsInfo = new ArrayList<MP7003>();
	private List<MP7004> evaluationMonthlyInfoList = new ArrayList<MP7004>();
	private List<MP7005> evaluationMonthlyScoresList = new ArrayList<MP7005>();
	private List<MP7006> evaluationYearlyInfoList = new ArrayList<MP7006>();
	private List<MP7007> evaluationYearlyScoresList = new ArrayList<MP7007>();
	private List<MP1001> examAppraiserInfoList = new ArrayList<MP1001>();
	private List<MP7004> monthlyHistoryScoresList = new ArrayList<MP7004>();
	private List<MP7008> employeeListInfo = new ArrayList<MP7008>();
	
	private List<MP8001> examQuestion2InfoList = new ArrayList<MP8001>();
	private List<MP8002> examPlanInfoList3 = new ArrayList<MP8002>();
	private List<MP8003> examPlanInfoList4 = new ArrayList<MP8003>();
	private List<MP8003> examItemsInfo2 = new ArrayList<MP8003>();
	private List<MP8004> relatedDepartmentListInfo = new ArrayList<MP8004>();
	private List<MP8005> evaluationMonthly2InfoList = new ArrayList<MP8005>();
	private List<MP8005> evaluationMonthlyDetailInfoList = new ArrayList<MP8005>();
	private List<MP8006> evaluationMonthly2ScoresList = new ArrayList<MP8006>();
	
	private Map<String,String> commonStatusList = new LinkedHashMap<String,String>();
	private Map<String,String> commonMonthList = new LinkedHashMap<String,String>();
	private Map<String,String> commonSeasonList = new LinkedHashMap<String, String>(); 
	private Map<String,String> commonDepartmentList = new LinkedHashMap<String,String>();
	private Map<String,String> commonDepartmentList2 = new LinkedHashMap<String,String>();
	private Map<String,String> departmentEmpList = new LinkedHashMap<String,String>();

	private Map<String,String> scoreLevelList = new LinkedHashMap<String, String>();
	
	private List<String>  scoreArray1 = new ArrayList<String>();
	private List<String>  scoreArray2 = new ArrayList<String>();
	private List<String>  scoreArray3 = new ArrayList<String>();
	private List<String>  scoreArray4 = new ArrayList<String>();
	private List<String>  scoreArray5 = new ArrayList<String>();
	
	private String existScore;
	private String selectMap_In;
	private String Mp8005comment_In;
	
	//------------失败做法--哈哈--------------
	private String DEP_1;
	private String DEP_2;
	private String DEP_3;
	private String DEP_5;
	private String DEP_6;
	private String DEP_7;
	private String DEP_8;
	private String DEP_10;
	private String DEP_11;
	private String DEP_12;
	private String DEP_14;
	private String DEP_15;
	private String DEP_16;
	private String DEP_18;
	private String DEP_19;
	private String DEP_21;
	private String DEP_22;
	
	//------------------常用变量---------------
	private String employeeNum;
	private String employeeName;
	private String commonSeq;
	private String commonStatus;
	private String warnType;
	private String pageType;
	private String errMsg;
	private String param1;
	private String param2;
	private String param3;
	private String param4;
	private String param5;
	private String reviewPeriod;
	//------------------文档下载用变量---------------
	private String fileName = "";
	private String downLoadFileName;
	
	//------------------分页用变量---------------
	private int pageNum;
	private int pageCount;
	
	//------------------权限控制用变量---------------
	private String optSave="0";
	private String optCancel="0";
	private String optApproval = "0";
	
	private String optAdd = "0";
	private String optSearch = "0";
	private String optDel = "0";
	private String optEdit = "0";
	private String optView = "0";
	
	private String optAll = "0";
	private String optDepartment = "0";
	private String optPersonal = "0";
	
	private String optPdf = "0";
	private String optExecute = "0";
	private String optReview = "0";
	
	//department appraisal, Appraisal Management(F006009001)
	private String A006009001001 = "0";
	private String A006009001002 = "0";
	private String A006008001001 = "0";
	
	private String fun0040001="0";
	private String fun0040002="0";
	private String fun0041001="0";
	private String fun0041002="0";
	private String fun0041003="0";
	private String fun0042001="0";
	private String fun0043001="0";
	
	private String fun0050001="0";
	private String fun0050002="0";
	private String fun0051001="0";
	private String fun0051002="0";
	private String fun0052001="0";
	private String fun0053001="0";
	//---------------------- 共用权限处理方法------------------
	
    /* 
	* @getDownloadFile 此方法对应的是struts.xml文件中的： 
	* <param name="inputName">downloadFile</param> 返回下载文件的流，可以参看struts2的源码 
	*/  
	public InputStream getDownloadFile(){
        //return ServletActionContext.getServletContext().getResourceAsStream("/"+UploadConfigurationRead.getInstance().getConfigItem("uploadFilePath").trim()+"/" + fileName);
		return ServletActionContext.getServletContext().getResourceAsStream("/temp/" + fileName);
	}
	// 员工个人计划管理Main页面初始化
	public String performanceMngInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
        	//----------------------------Operation History------------------
        	LogUtil logUtil = new LogUtil();
        	logUtil.setServiceMP0011(serviceMP0011);
        	logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Personal Schedule Information Main Page Init");
        	//----------------------------Operation History------------------
        	
			employeeNum=employeeData.getMP1001_EMPLOYEE_NUM();
			employeeName=employeeData.getMP1001_PREFERED_NAME();
			
			getMenuAuthority(employeeNum,Constant.SYSTEM_NUM_PERFORMANCE);
			
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
	// 员工个人计划管理Main页面初始化
	public String performance2MngInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
        	//----------------------------Operation History------------------
        	LogUtil logUtil = new LogUtil();
        	logUtil.setServiceMP0011(serviceMP0011);
        	logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Personal Schedule Information Main Page Init(Department)");
        	//----------------------------Operation History------------------
        	
			employeeNum=employeeData.getMP1001_EMPLOYEE_NUM();
			employeeName=employeeData.getMP1001_PREFERED_NAME();
			
			getMenuAuthority(employeeNum,Constant.SYSTEM_NUM_PERFORMANCE);
			
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
	// 菜单权限
	private void getMenuAuthority(String employeeNum,String systemNum){
		HashMap<String,String> funcMap = serviceAC0006.functionAccessCheck(employeeNum,systemNum);
		
		if(funcMap.containsKey(Constant.F0040001)){
			fun0040001 = "1";
		}
		if(funcMap.containsKey(Constant.F0040002)){
			fun0040002 = "1";
		}
		if(funcMap.containsKey(Constant.F0041001)){
			fun0041001 = "1";
		}
		if(funcMap.containsKey(Constant.F0041002)){
			fun0041002 = "1";
		}
		if(funcMap.containsKey(Constant.F0041003)){
			fun0041003 = "1";
		}
		if(funcMap.containsKey(Constant.F0042001)){
			fun0042001 = "1";
		}
		if(funcMap.containsKey(Constant.F0043001)){
			fun0043001 = "1";
		} 
		if(funcMap.containsKey(Constant.F0050001)){
			fun0050001 = "1";
		}
		if(funcMap.containsKey(Constant.F0050002)){
			fun0050002 = "1";
		}
		if(funcMap.containsKey(Constant.F0051001)){
			fun0051001 = "1";
		}
		if(funcMap.containsKey(Constant.F0051002)){
			fun0051002 = "1";
		}
		if(funcMap.containsKey(Constant.F0052001)){
			fun0052001 = "1";
		}
		if(funcMap.containsKey(Constant.F0053001)){
			fun0053001 = "1";
		}
	}
	// 取得管理页面权限信息
	private void getAuthority(String _employeeNum,String functionNum){
		HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(_employeeNum, functionNum,Constant.SYSTEM_NUM_PERFORMANCE);
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
		if(optMap.containsKey(Constant.OPT_PDF)){
			optPdf = "1";
		}
		if(optMap.containsKey(Constant.OPT_EXECUTE)){
			optExecute = "1";
		}
		if(optMap.containsKey(Constant.OPT_REVIEW)){
			optReview = "1";
		}
		if(optMap.containsKey(Constant.A006009001001)){
			A006009001001 = "1";
		}
		if(optMap.containsKey(Constant.A006009001002)){
			A006009001002 = "1";
		}
		if(optMap.containsKey(Constant.A006008001001)){
			A006008001001 = "1";
		}
		
	}
	
	//---------------------- 绩效考核题目管理------------------
	// 绩效考核题目新增、编辑页面初始化
	public String examQuestionAddInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Exam Question Information Page Init");
			//----------------------------Operation History------------------
			// 处理权限
			getAuthority(empNum,Constant.F0040001);
			getMenuAuthority(empNum,Constant.SYSTEM_NUM_PERFORMANCE);
			
			if(fun0040001.equals("0")){
				return "noAuthority";
			}

			if(pageType != null){				
				if(pageType.equals("edit")){
					if(commonSeq != null && !commonSeq.equals("")){
						commonStatusList = Constant.getCommonStatus2();
						mp7001 = serviceMP7001.findById(commonSeq);
						
						commonStatus= mp7001.getMP7001_STATUS();
					}else{
						return "error";
					}
				}else if(pageType.equals("add")){
					// nothing to do
				}else{
					return "error";
				}
			}else{
				return "error";
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
	// 绩效考核题目管理页面初始化
	public String examQuestionInfoMngInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Exam Question Information Management Page Init");
			//----------------------------Operation History------------------
			// 处理权限
			getMenuAuthority(empNum,Constant.SYSTEM_NUM_PERFORMANCE);
			
			if(fun0040002.equals("0")){
				return "noAuthority";
			}
			
			Map<String,String> propMap = new HashMap<String,String>();
			propMap.put("QUESTION_NUM", commonSeq);
			propMap.put("QUESTION_TITLE", param1);
			propMap.put("QUESTION_STATUS", param2);
			
			getExamQuestionInfoByPage(propMap, 1,empNum);

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
	// 保存绩效考核题目信息
	public String examQuestionInfoSave(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Exam Question Information Save");
			//----------------------------Operation History------------------
			
			if(pageType.equals("add")){
				mp7001.setMP7001_STATUS("1");
				mp7001.setMP7001_CREATOR(empNum);
				mp7001.setMP7001_CREATE_TIME(UtilDate.get24DateTime());
				mp7001.setMP7001_SEQ(Constant.generateSeq());
				
				if(mp7001.getMP7001_TITLE() == null || mp7001.getMP7001_TITLE().equals("")){
					addFieldError("mp7001.MP7001_TITLE","Title cannot be empty");
					return INPUT;
				}
				
				serviceMP7001.save(mp7001);
				
				//errMsg = "数据保存成功";
				addFieldError("errMsg","数据保存成功");
				// 记录操作日志
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Exam Question Information, Exam Question:{" + mp7001.getMP7001_TITLE() + "}");
				
				mp7001 = new MP7001();
				return INPUT;
			}else if(pageType.equals("edit")){
				MP7001 mp71 = serviceMP7001.findById(commonSeq);
				
				mp71.setMP7001_TITLE(mp7001.getMP7001_TITLE()); // 标题
				mp71.setMP7001_SUB_TITLE(mp7001.getMP7001_SUB_TITLE()); // 子标题
				mp71.setMP7001_STATUS(commonStatus); // 状态
				mp71.setMP7001_COMMENT(mp7001.getMP7001_COMMENT()); // 备注
				
				serviceMP7001.update(mp71);
				// 记录操作日志
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Edit Exam Question Information, Exam Question:{" + mp7001.getMP7001_TITLE() + "}");
				
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				
				out.append("<script type='text/javascript'>");
				out.append("window.dialogArguments.document.getElementById('btnRefresh').click();");
				out.append("window.close();");
				out.append("</script>");
		        out.flush();
		        out.close();
				
				return INPUT;
			}else{
				return "error";
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
	// 绩效考核题目信息检索处理
	public String examQuestionInfoSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Exam Question Information");
			//----------------------------Operation History------------------
			
			// 重新取得分页数据
			Map<String,String> propMap = new HashMap<String,String>();
			propMap.put("QUESTION_NUM", commonSeq);
			propMap.put("QUESTION_TITLE", param1);
			propMap.put("QUESTION_STATUS", param2);
			pageNum = 1;
			getExamQuestionInfoByPage(propMap, pageNum,empNum);
			
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
	// 绩效考核题目信息分页处理
	public String examQuestionInfoPageClick(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Exam Question Information Page Click");
			//----------------------------Operation History------------------
			
			// 重新取得分页数据
			Map<String,String> propMap = new HashMap<String,String>();
			propMap.put("QUESTION_NUM", commonSeq);
			propMap.put("QUESTION_TITLE", param1);
			propMap.put("QUESTION_STATUS", param2);
			getExamQuestionInfoByPage(propMap, pageNum,empNum);
			
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
	// 绩效考核题目信息删除处理
	public String examQuestionInfoDelete(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{			
			if(commonSeq != null && !commonSeq.equals("")){
				MP7001 mp71 = serviceMP7001.findById(commonSeq);

				if(mp71 != null){
					//serviceMP7001.delete(mp71);不做物理删除，只须更新数据状态
					mp71.setMP7001_STATUS("0");
					serviceMP7001.update(mp71);
					
					//----------------------------Operation History------------------
					LogUtil logUtil = new LogUtil();
					logUtil.setServiceMP0011(serviceMP0011);
					logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Delete Exam Question Information, Exam Question:{" + mp71.getMP7001_TITLE() + "}");
					//----------------------------Operation History------------------
				}
			}
			
			// 重新取得分页数据
			Map<String,String> propMap = new HashMap<String,String>();
			propMap.put("QUESTION_NUM", commonSeq);
			propMap.put("QUESTION_TITLE", param1);
			propMap.put("QUESTION_STATUS", param2);
			getExamQuestionInfoByPage(propMap, pageNum,empNum);
			
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
	// 为绩效考核计划追加考核题目页面初始化
	public String examQuestionSelectInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Exam Question For Exam Plan Init");
			//----------------------------Operation History------------------
			
			Map<String,String> propMap = new HashMap<String,String>();
			getExamQuestionInfoByPage(propMap, 1,empNum);

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
	// 添加考核题目分页处理
	public String examQuestionSelectPageClick(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Exam Question For Exam Plan Page Click");
			//----------------------------Operation History------------------
			
			// 重新取得分页数据
			Map<String,String> propMap = new HashMap<String,String>();
			getExamQuestionInfoByPage(propMap, pageNum,empNum);
			
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
	// 重新取得分页数据
	private void getExamQuestionInfoByPage(Map<String,String> propMap, int _pageNum, String empId){	
		examQuestionInfoList = serviceMP7001.findByPropertyByPage(propMap, -1, -1);
		if( 0 == examQuestionInfoList.size()%Constant.PAGE_SIZE){
			pageCount = examQuestionInfoList.size()/Constant.PAGE_SIZE;
		}else{
			pageCount = examQuestionInfoList.size()/Constant.PAGE_SIZE + 1;
		}
		if(_pageNum > pageCount){
			_pageNum= pageCount;
		}
		examQuestionInfoList = serviceMP7001.findByPropertyByPage(propMap, _pageNum, Constant.PAGE_SIZE);
		commonStatusList = Constant.getCommonStatus2();
		// 处理权限
		getAuthority(empId,Constant.F0040002);
	}
	//---------------------- 绩效考核题目管理------------------
	
	//---------------------- 绩效考核计划管理------------------
	// 绩效考核计划新增、编辑页面初始化
	public String examPlanAddInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		param1 = "";
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Exam Question Information Page Init");
			//----------------------------Operation History------------------
			// 处理权限
			getAuthority(empNum,Constant.F0041001);
			getMenuAuthority(empNum,Constant.SYSTEM_NUM_PERFORMANCE);
			
			if(fun0041001.equals("0")){
				return "noAuthority";
			}
			
			if(pageType != null){
				commonMonthList = Constant.getMonthInfoList();
				
				if(pageType.equals("edit")){
					if(commonSeq != null && !commonSeq.equals("")){
						commonStatusList = Constant.getCommonStatus2();
						mp7002 = serviceMP7002.findById(commonSeq);
						commonStatus= mp7002.getMP7002_STATUS();
						param4 = mp7002.getMP7002_MONTH();
					    
						examItemsInfo = serviceMP7003.findByProperty("MP7003_MASTER_KEY", commonSeq);
						employeeListInfo = serviceMP7008.findByProperty("MP7008_PLAN_SEQ", commonSeq);
					}else{
						return "error";
					}
				}else if(pageType.equals("add")){
					// nothing to do
				}else{
					return "error";
				}
			}else{
				return "error";
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
	// 绩效考核题目管理页面初始化
	public String examPlanInfoMngInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Exam Question Information Management Page Init");
			//----------------------------Operation History------------------
			getMenuAuthority(empNum,Constant.SYSTEM_NUM_PERFORMANCE);
			
			if(fun0041002.equals("0")){
				return "noAuthority";
			}
			
			Map<String,String> propMap = new HashMap<String,String>();
			propMap.put("PLAN_NUM", commonSeq);
			propMap.put("PLAN_TITLE", param1);
			propMap.put("PLAN_STATUS", param2);
			propMap.put("PLAN_YEAR", param3);
			propMap.put("PLAN_MONTH", param4);
			
			getExamPlanInfoByPage(propMap, 1,empNum);

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
	// 保存绩效考核题目信息
	public String examPlanInfoSave(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Exam Question Information Save");
			//----------------------------Operation History------------------
			
			if(pageType.equals("add")){
				mp7002.setMP7002_STATUS("1");
				mp7002.setMP7002_CREATOR(empNum);
				mp7002.setMP7002_SEQ(Constant.generateSeq());
				mp7002.setMP7002_MONTH(param4);
				if(param2!=null && !param2.equals("")){
					mp7002.setMP7002_EXECUTE_STATUS("1");
				}else{
					mp7002.setMP7002_EXECUTE_STATUS("0");
				}
				mp7002.setMP7002_FINISH_STATUS("0");
				
				if(mp7002.getMP7002_TITLE() == null || mp7002.getMP7002_TITLE().equals("")){
					addFieldError("mp7002.MP7002_TITLE","Title cannot be empty");
					return INPUT;
				}
				
				serviceMP7002.save(mp7002);
				//saveExamItem(examItemsInfo);
				checkData(mp7002.getMP7002_SEQ());
				
				//errMsg = "数据保存成功";
				addFieldError("errMsg","数据保存成功");
				// 记录操作日志
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Exam Plan Information, Exam Question:{" + mp7002.getMP7002_TITLE() + "}");
				
				mp7002 = new MP7002();
				commonMonthList = Constant.getMonthInfoList();
				return INPUT;
			}else if(pageType.equals("edit")){
				MP7002 mp72 = serviceMP7002.findById(commonSeq);
				
				mp72.setMP7002_TITLE(mp7002.getMP7002_TITLE()); // 标题
				mp72.setMP7002_YEAR(mp7002.getMP7002_YEAR()); // 年
				mp72.setMP7002_MONTH(param4); // 月
				mp72.setMP7002_COMMENT(mp7002.getMP7002_COMMENT()); // 备注
				mp72.setMP7002_STATUS(commonStatus); // 状态
				if(param2!=null && !param2.equals("")){
					mp72.setMP7002_EXECUTE_STATUS("1");
				}
				
				serviceMP7002.update(mp72);
				saveExamItem(examItemsInfo);
				checkData(commonSeq);
				
				// 记录操作日志
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Edit Exam Plan Information, Exam Question:{" + mp7002.getMP7002_TITLE() + "}");
				
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				
				out.append("<script type='text/javascript'>");
				out.append("window.dialogArguments.document.getElementById('btnRefresh').click();");
				out.append("window.close();");
				out.append("</script>");
		        out.flush();
		        out.close();
				
				return INPUT;
			}else{
				return "error";
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
	// 添加绩效考核题目的比重到MP7003表
	private void saveExamItem(List<MP7003> examItemsInfo){
		for(int i=0,j=examItemsInfo.size(); i<j; i++){
			MP7003 mp73 = examItemsInfo.get(i);
			mp7003 = serviceMP7003.findById(mp73.getMP7003_SEQ());
			
			if(mp7003 != null){
				if(mp73.getMP7003_WEIGHTAGE() == null || mp73.getMP7003_WEIGHTAGE().equals("")){
					mp7003.setMP7003_WEIGHTAGE("5");
				}else{
					mp7003.setMP7003_WEIGHTAGE(mp73.getMP7003_WEIGHTAGE());
				}
				// 更新考题比重
				serviceMP7003.update(mp7003);
			}
		}
	}
	// 绩效考核题目信息检索处理
	public String examPlanInfoSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Exam Plan Information");
			//----------------------------Operation History------------------
			
			// 重新取得分页数据
			Map<String,String> propMap = new HashMap<String,String>();
			propMap.put("PLAN_NUM", commonSeq);
			propMap.put("PLAN_TITLE", param1);
			propMap.put("PLAN_STATUS", param2);
			propMap.put("PLAN_YEAR", param3);
			propMap.put("PLAN_MONTH", param4);
			pageNum = 1;
			
			getExamPlanInfoByPage(propMap, pageNum,empNum);
			
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
	// 绩效考核题目信息分页处理
	public String examPlanInfoPageClick(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Exam Plan Information Page Click");
			//----------------------------Operation History------------------
			
			// 重新取得分页数据
			Map<String,String> propMap = new HashMap<String,String>();
			propMap.put("PLAN_NUM", commonSeq);
			propMap.put("PLAN_TITLE", param1);
			propMap.put("PLAN_STATUS", param2);
			propMap.put("PLAN_YEAR", param3);
			propMap.put("PLAN_MONTH", param4);
			
			getExamPlanInfoByPage(propMap, pageNum,empNum);
			
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
	// 绩效考核计划信息删除处理
	public String examPlanInfoDelete(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{			
			if(commonSeq != null && !commonSeq.equals("")){
				MP7002 mp72 = serviceMP7002.findById(commonSeq);

				if(mp72 != null){
					//serviceMP7001.delete(mp71);不做物理删除，只须更新数据状态
					mp72.setMP7002_STATUS("0");
					serviceMP7002.update(mp72);
					
					//----------------------------Operation History------------------
					LogUtil logUtil = new LogUtil();
					logUtil.setServiceMP0011(serviceMP0011);
					logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Delete Exam Plan Information, Exam Question:{" + mp72.getMP7002_TITLE() + "}");
					//----------------------------Operation History------------------
				}
			}
			
			// 重新取得分页数据
			Map<String,String> propMap = new HashMap<String,String>();
			//propMap.put("PLAN_NUM", commonSeq);
			propMap.put("PLAN_NUM", "");
			propMap.put("PLAN_TITLE", param1);
			propMap.put("PLAN_STATUS", param2);
			propMap.put("PLAN_YEAR", param3);
			propMap.put("PLAN_MONTH", param4);
			
			getExamPlanInfoByPage(propMap, pageNum,empNum);
			
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
	// 绩效考核计划信息删除处理
	public String examPlanInfoCopy(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{			
			if(commonSeq != null && !commonSeq.equals("")){
				MP7002 mp72 = serviceMP7002.findById(commonSeq);

				if(mp72 != null){
					mp72.setMP7002_SEQ(Constant.generateSeq());
					mp72.setMP7002_EXECUTE_STATUS("0");
					mp72.setMP7002_FINISH_STATUS("0");
					mp72.setMP7002_STATUS("1");
					serviceMP7002.save(mp72);
					
					List<MP7003> mp73List = serviceMP7003.findByProperty("MP7003_MASTER_KEY", commonSeq);
					for(int i=0,j=mp73List.size(); i<j; i++){
						MP7003 mp73 = mp73List.get(i);
						mp73.setMP7003_SEQ(Constant.generateSeq() + UtilCommon.getTempSeq());
						mp73.setMP7003_MASTER_KEY(mp72.getMP7002_SEQ());
						serviceMP7003.save(mp73);
					}
					
					//----------------------------Operation History------------------
					LogUtil logUtil = new LogUtil();
					logUtil.setServiceMP0011(serviceMP0011);
					logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Copy Exam Plan Information, Exam Question:{" + mp72.getMP7002_TITLE() + "}");
					//----------------------------Operation History------------------
				}
			}
			
			// 重新取得分页数据
			Map<String,String> propMap = new HashMap<String,String>();
			//propMap.put("PLAN_NUM", commonSeq);
			propMap.put("PLAN_NUM", "");
			propMap.put("PLAN_TITLE", param1);
			propMap.put("PLAN_STATUS", param2);
			propMap.put("PLAN_YEAR", param3);
			propMap.put("PLAN_MONTH", param4);
			
			getExamPlanInfoByPage(propMap, pageNum,empNum);
			
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
	// 生成员工绩效考核数据
	public String examPlanInfoExecute(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Exam Plan Execute");
			//----------------------------Operation History------------------

			MP7002 mp72 = serviceMP7002.findById(commonSeq);
			List<MP7003> mp73List = serviceMP7003.findByProperty("MP7003_MASTER_KEY", commonSeq);
			Map<String, MP1001> managerMap = UtilCommon.getAllManager(serviceMP1001);
			
			List<MP7008> mp78List = serviceMP7008.findByProperty("MP7008_PLAN_SEQ", commonSeq);
			StringBuffer searchStr = new StringBuffer();
			MP7008 mp78 = new MP7008();
			for(int i=0, j=mp78List.size(); i<j; i++){
				mp78 = mp78List.get(i);
				
				if(mp78.getMP7008_EMPLOYEE_NUM() != null && !mp78.getMP7008_EMPLOYEE_NUM().equals("")){
					if(i >0){
						searchStr.append(",");
					}
					searchStr.append( "'" + mp78.getMP7008_EMPLOYEE_NUM() + "'");
				}
			}
			String search = "  and mp11.MP1001_EMPLOYEE_NUM in ( " + searchStr.toString() + " ) ";
			List<MP1001> mp11List = serviceMP1001.findData(search, "", -1, -1);
			
			String _month = "";
			if(mp72.getMP7002_MONTH().length() == 1){
				_month = "0" + mp72.getMP7002_MONTH();
			}
			else if(mp72.getMP7002_MONTH().length() == 2){
				_month = mp72.getMP7002_MONTH();
			}
			
			// 循环取得所有员工信息
			if(mp73List != null && mp73List.size() > 0 && mp72.getMP7002_TYPE() != null && !mp72.getMP7002_TYPE().equals("")){
				for(int i=0,j=mp11List.size(); i<j; i++){
					MP1001 mp11 = mp11List.get(i);
					
					if(mp72.getMP7002_TYPE().equals("1")){ // 年计划生成
						mp7006 = new MP7006();
						mp7006.setMP7006_SEQ(Constant.generateSeq2() + "YM" + i + UtilCommon.getTempSeq()); // KEY
						
						mp7006.setMP7006_EMPLOYEE_NUM(mp11.getMP1001_EMPLOYEE_NUM()); // 员工编码
						
						// 评定人 首先检查评定人是否有数据，如果该表没有员工对应的评定人，则设置评定人为该员工的部门主管
						if(mp11.getMP1001_APPRASIER() != null && !mp11.getMP1001_APPRASIER().equals("")){
							mp7006.setMP7006_APPRAISER(mp11.getMP1001_APPRASIER());
							mp7006.setMP7006_APPRAISER_DESIGNATION(mp11.getMP1001_POSITION());
						}else{
							if(managerMap.containsKey(mp11.getMP1001_DEPARTMENT_ID())){
								MP1001 mp11Obj = managerMap.get(mp11.getMP1001_DEPARTMENT_ID());
								mp7006.setMP7006_APPRAISER(mp11Obj.getMP1001_EMPLOYEE_NUM());
								mp7006.setMP7006_APPRAISER_DESIGNATION(mp11Obj.getMP1001_POSITION());
							}else{
								mp7006.setMP7006_APPRAISER("");// 没有找到Manager
								mp7006.setMP7006_APPRAISER_DESIGNATION("");
							}
						}
						mp7006.setMP7006_REVIEWER(""); // Review者
						mp7006.setMP7006_REVIEWER_DESIGNATION("");// Review者职位
						mp7006.setMP7006_DESIGNATION(mp11.getMP1001_POSITION()); // 职位
						mp7006.setMP7006_DEPARTMENT(mp11.getMP1001_DEPARTMENT_ID()); // 部门
						mp7006.setMP7006_CURRENT_APPRAISAL_CYCLE(mp72.getMP7002_YEAR() + "-" + _month); // 当前考核周期
						mp7006.setMP7006_COMPETENCE_TOTAL_SCORES("0"); // 本次考核总分数
						mp7006.setMP7006_GRAND_TOTAL_SCORES("0"); // 本次考核与最近六个月考核总分数
						mp7006.setMP7006_STATUS("1"); // 状态
						mp7006.setMP7006_TOTAL_WEIGHTAGE("0"); // 总比重
						mp7006.setMP7006_EFFECTIVE_DATE_APPRAISAL(""); // 本次考核最终评定日期
						// 上次最终考核结束日期的设定
						mp7006.setMP7006_EFFECTIVE_DATE_LAST_APPRAISAL(mp11.getMP1001_EFFECTIVE_DATE_YEAR());
						
						serviceMP7006.save(mp7006);
						
						// 循环取得考核科目信息
						for(int m=0,n=mp73List.size(); m<n; m++){
							MP7003 mp73 = mp73List.get(m);
							mp7007 = new MP7007();
							mp7007.setMP7007_SEQ(Constant.generateSeq2() + "YS" + i + m + UtilCommon.getTempSeq());
							mp7007.setMP7007_MASTER_KEY(mp7006.getMP7006_SEQ());
							mp7007.setMP7007_EXAM_CODE(mp73.getMP7003_EXAM_CODE());
							mp7007.setMP7007_SELF_SCORE("0");
							mp7007.setMP7007_MANAGER_SCORE("0");
							mp7007.setMP7007_AGREED_SCORE("0");
							mp7007.setMP7007_REMARKS("0");
							if(mp73.getMP7003_WEIGHTAGE() == null || mp73.getMP7003_WEIGHTAGE().equals("")){
								mp7007.setMP7007_WEIGHTAGE(Constant.DEFAULT_WEIGHTAGE); // 默认比重设定为5
							}else{
								mp7007.setMP7007_WEIGHTAGE(mp73.getMP7003_WEIGHTAGE());
							}
							mp7007.setMP7007_FINAL_SCORE("0");
							
							serviceMP7007.save(mp7007);					
						}// End 保存员工对应的考核科目信息
						
					}else if(mp72.getMP7002_TYPE().equals("2")){ // 月计划生成
						mp7004 = new MP7004();
						mp7004.setMP7004_SEQ(Constant.generateSeq2() + "MM" + i + UtilCommon.getTempSeq()); // KEY
						mp7004.setMP7004_EMPLOYEE_NUM(mp11.getMP1001_EMPLOYEE_NUM()); // 员工编码
						mp7004.setMP7004_JOB_TITLE(mp11.getMP1001_POSITION()); // 职位
						mp7004.setMP7004_DEPARTMENT(mp11.getMP1001_DEPARTMENT_ID()); // 部门
						mp7004.setMP7004_REVIEW_PERIOD(mp72.getMP7002_YEAR() + "-" + _month); // 当前考核期间
						mp7004.setMP7004_SCORES("0"); // 分数
						mp7004.setMP7004_COMMENT(""); // 备注
						mp7004.setMP7004_STATUS("1"); // 状态
						
						if(managerMap.containsKey(mp11.getMP1001_DEPARTMENT_ID())){
							MP1001 mp11Obj = managerMap.get(mp11.getMP1001_DEPARTMENT_ID());
							mp7004.setMP7004_MANAGER(mp11Obj.getMP1001_EMPLOYEE_NUM());
						}else{
							mp7004.setMP7004_MANAGER("");// 没有找到Manager
						}
						
						// 评定人 首先检查评定人是否有数据，如果该表没有员工对应的评定人，则设置评定人为该员工的部门主管
						if(mp11.getMP1001_APPRASIER() != null && !mp11.getMP1001_APPRASIER().equals("")){
							mp7004.setMP7004_APPRAISER(mp11.getMP1001_APPRASIER());
							mp7004.setMP7004_APPRAISER_DESIGNATION(mp11.getMP1001_POSITION());
						}else{
							if(managerMap.containsKey(mp11.getMP1001_DEPARTMENT_ID())){
								MP1001 mp11Obj = managerMap.get(mp11.getMP1001_DEPARTMENT_ID());
								mp7004.setMP7004_APPRAISER(mp11Obj.getMP1001_EMPLOYEE_NUM());
								mp7004.setMP7004_APPRAISER_DESIGNATION(mp11Obj.getMP1001_POSITION());
							}else{
								mp7004.setMP7004_APPRAISER("");// 没有找到Manager
								mp7004.setMP7004_APPRAISER_DESIGNATION("");
							}
						}
						serviceMP7004.save(mp7004);
						
						// 循环取得考核科目信息
						for(int m=0,n=mp73List.size(); m<n; m++){
							MP7003 mp73 = mp73List.get(m);
							mp7005 = new MP7005();
							
							mp7005.setMP7005_SEQ(Constant.generateSeq2() + "MS" + i + m + UtilCommon.getTempSeq());
							mp7005.setMP7005_MASTER_KEY(mp7004.getMP7004_SEQ());
							mp7005.setMP7005_EXAM_CODE(mp73.getMP7003_EXAM_CODE());
							mp7005.setMP7005_SCORES("");
							
							serviceMP7005.save(mp7005);					
						}// End 保存员工对应的考核科目信息
					}
				}// End 保存员工绩效考核的主表
				
				// 考核计划执行后，须设定MP7002_FINISH_STATUS为"1"
				mp72.setMP7002_FINISH_STATUS("1");
				serviceMP7002.update(mp72);
			}
			
			// 重新取得分页数据
			Map<String,String> propMap = new HashMap<String,String>();
			//propMap.put("PLAN_NUM", commonSeq);
			propMap.put("PLAN_NUM", "");
			propMap.put("PLAN_TITLE", param1);
			propMap.put("PLAN_STATUS", param2);
			propMap.put("PLAN_YEAR", param3);
			propMap.put("PLAN_MONTH", param4);
			
			getExamPlanInfoByPage(propMap, pageNum,empNum);
			
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
	// 重新取得分页数据
	private void getExamPlanInfoByPage(Map<String,String> propMap, int _pageNum, String empId){	
		examPlanInfoList = serviceMP7002.findByPropertyByPage(propMap, -1, -1);
		if( 0 == examPlanInfoList.size()%Constant.PAGE_SIZE){
			pageCount = examPlanInfoList.size()/Constant.PAGE_SIZE;
		}else{
			pageCount = examPlanInfoList.size()/Constant.PAGE_SIZE + 1;
		}
		if(_pageNum > pageCount){
			_pageNum= pageCount;
		}
		examPlanInfoList = serviceMP7002.findByPropertyByPage(propMap, _pageNum, Constant.PAGE_SIZE);
		
		commonStatusList = Constant.getCommonStatus2();
		commonMonthList = Constant.getMonthInfoList();
		// 处理权限
		getAuthority(empId,Constant.F0041002);
	}

	// 计划考核题目管理页面初始化
	public String examQuestionSelectMngInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Manage Exam Question For Exam Plan");
			//----------------------------Operation History------------------
			
			Map<String,String> propMap = new HashMap<String,String>();
			propMap.put("MASTER_KEY", commonSeq);
			getExamPlanInfoByPage2(propMap, 1);

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
	// 计划考核题目分页处理
	public String examQuestionSelectMngPageClick(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		//String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Manage Exam Question For Exam Plan Page Click");
			//----------------------------Operation History------------------
			
			// 重新取得分页数据
			Map<String,String> propMap = new HashMap<String,String>();
			propMap.put("MASTER_KEY", commonSeq);
			getExamPlanInfoByPage2(propMap, pageNum);
			
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
	// 绩效考核题目信息删除处理
	public String examPlanItemInfoRefresh(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		//String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{			
			if(commonSeq != null && !commonSeq.equals("")){
				// 重新取得分页数据
				examItemsInfo = serviceMP7003.findByProperty("MP7003_MASTER_KEY", commonSeq);
				
				//----------------------------Operation History------------------
				LogUtil logUtil = new LogUtil();
				logUtil.setServiceMP0011(serviceMP0011);
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Refresh Exam Plan Selected Question Information");
				//----------------------------Operation History------------------
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
	// 绩效考核题目信息删除处理
	public String examPlanEmpInfoRefresh(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		//String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{			
			if(commonSeq != null && !commonSeq.equals("")){
				// 重新取得分页数据
				employeeListInfo = serviceMP7008.findByProperty("MP7008_PLAN_SEQ", commonSeq);
				
				//----------------------------Operation History------------------
				LogUtil logUtil = new LogUtil();
				logUtil.setServiceMP0011(serviceMP0011);
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Refresh Exam Plan Selected Employee Information");
				//----------------------------Operation History------------------
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
	// 计划考核题目信息保存处理
	public String examPlanItemInfoSave(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		String msg = "";
		try{
			if(param1 != null && !param1.equals("")){
				
				StringBuffer search = new StringBuffer();
				search.append(" and mp73.MP7003_MASTER_KEY = '" + commonSeq + "' ");
				search.append(" and mp73.MP7003_EXAM_CODE = '" + param1 + "' ");
				
				List<MP7003> mp73List = serviceMP7003.findDataBySelfDefined(search.toString(), "", -1, -1);
				
				if(mp73List.size() ==0 ){
					String key = Constant.generateSeq() + UtilCommon.getTempSeq();
					mp7003.setMP7003_SEQ(key);
					mp7003.setMP7003_MASTER_KEY(commonSeq);
					mp7003.setMP7003_EXAM_CODE(param1);
					mp7003.setMP7003_WEIGHTAGE("");
					
					serviceMP7003.save(mp7003);
					checkData(mp7003.getMP7003_MASTER_KEY());
					msg = "Save Success";
				}else{
					msg = "The item has been selected, Please select another item.";
				}
				//----------------------------Operation History------------------
				LogUtil logUtil = new LogUtil();
				logUtil.setServiceMP0011(serviceMP0011);
				logUtil.writeOperationLog(empNum,employeeData.getMP1001_PREFERED_NAME(),"Save Exam Plan Selected Question Information, Exam Question:{" + mp7003.getMP7003_EXAM_CODE() + "}");
				//----------------------------Operation History------------------
			}
			
			HttpServletResponse response = ServletActionContext.getResponse();
	        response.setCharacterEncoding("utf-8"); // 务必，防止返回文件名是乱码
	        PrintWriter out = response.getWriter();
			
			out.append(msg);

	        out.flush();
	        out.close();
			
			return null;
		}catch(Exception ex){
			log.info(ex.getMessage());
			if(session.containsKey("ERR_MSG")){
				session.remove("ERR_MSG");
				session.put("ERR_MSG", ex.getMessage());
			}
			return "error";
		}
	}
	// 计划考核题目信息删除处理
	public String examPlanItemInfoDelete(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{
			if(param1 != null && !param1.equals("")){
				MP7003 mp73 = serviceMP7003.findById(param1);
				
				if(mp73 != null){
					serviceMP7003.delete(mp73);
					checkData(mp73.getMP7003_MASTER_KEY());
					
					//----------------------------Operation History------------------
					LogUtil logUtil = new LogUtil();
					logUtil.setServiceMP0011(serviceMP0011);
					logUtil.writeOperationLog(empNum,employeeData.getMP1001_PREFERED_NAME(),"Delete Exam Plan Selected Question Information, Exam Question:{" + mp7003.getMP7003_EXAM_CODE() + "}");
					//----------------------------Operation History------------------
				}
			}
			
			examItemsInfo = serviceMP7003.findByProperty("MP7003_MASTER_KEY", commonSeq);
			
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
	// 计划考核题目信息删除处理
	public String examPlanEmpInfoDelete(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{
			if(param1 != null && !param1.equals("")){
				MP7008 mp78 = serviceMP7008.findById(param1);
				
				if(mp78 != null){
					serviceMP7008.delete(mp78);
					checkData(mp78.getMP7008_PLAN_SEQ());
					
					//----------------------------Operation History------------------
					LogUtil logUtil = new LogUtil();
					logUtil.setServiceMP0011(serviceMP0011);
					logUtil.writeOperationLog(empNum,employeeData.getMP1001_PREFERED_NAME(),"Delete Exam Plan Selected Employee Information, Employee:{" + mp78.getMP7008_EMPLOYEE_NUM() + "}");
					//----------------------------Operation History------------------
				}
			}
			
			employeeListInfo = serviceMP7008.findByProperty("MP7008_PLAN_SEQ", commonSeq);
			
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
	// 重新取得分页数据
	private void getExamPlanInfoByPage2(Map<String,String> propMap, int _pageNum){	
		examPlanInfoList2 = serviceMP7003.findByPropertyByPage(propMap, -1, -1);
		if( 0 == examPlanInfoList2.size()%Constant.PAGE_SIZE){
			pageCount = examPlanInfoList2.size()/Constant.PAGE_SIZE;
		}else{
			pageCount = examPlanInfoList2.size()/Constant.PAGE_SIZE + 1;
		}
		if(_pageNum > pageCount){
			_pageNum= pageCount;
		}
		examPlanInfoList2 = serviceMP7003.findByPropertyByPage(propMap, _pageNum, Constant.PAGE_SIZE);
	}
	// 检测数据
	private void checkData(String parentKey){
		List<MP7003> mp73List = serviceMP7003.findByProperty("MP7003_MASTER_KEY", parentKey);
		List<MP7008> mp78List = serviceMP7008.findByProperty("MP7008_PLAN_SEQ", parentKey);
		MP7002 mp72 = serviceMP7002.findById(parentKey);
		if(mp73List == null || mp73List.size() == 0 || mp78List == null || mp78List.size() == 0){
			mp72.setMP7002_EXECUTE_STATUS("0");
		}else{
			mp72.setMP7002_EXECUTE_STATUS("1");
		}
		serviceMP7002.update(mp72);
	}
	
	//---------------------- 绩效考核计划管理------------------
	
	//---------------------- 绩效考核月度------------------
	// 绩效考核题目新增、编辑页面初始化
	public String examEvaluationMonthlyAddInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Fill In Performance Evaluation Form Page Init");
			//----------------------------Operation History------------------
			// 处理权限
			getAuthority(empNum,Constant.F0042002);
			
			if(pageType != null){				
				if(pageType.equals("edit")){
					if(commonSeq != null && !commonSeq.equals("")){
						mp7004 = serviceMP7004.findById(commonSeq);
						
						// 解析Manager信息
						Map<String, MP1001> managerMap = UtilCommon.getAllManager(serviceMP1001);
						if(managerMap.containsKey(mp7004.getMP7004_DEPARTMENT())){
							MP1001 mp11Obj = managerMap.get(mp7004.getMP7004_DEPARTMENT());
							mp7004.setMP7004_MANAGER_NAME(mp11Obj.getMP1001_PREFERED_NAME());
						}
						// 解析考核评定人信息
						MP1001 appraiserObj = serviceMP1001.findById(mp7004.getMP7004_APPRAISER());
						if(appraiserObj != null){
							mp7004.setMP7004_APPRAISER_NAME(appraiserObj.getMP1001_PREFERED_NAME());
						}
						
						evaluationMonthlyScoresList = serviceMP7005.findByProperty("MP7005_MASTER_KEY", commonSeq);
						for(int i=0,j=evaluationMonthlyScoresList.size(); i<j; i++){
							MP7005 mp75 = evaluationMonthlyScoresList.get(i);
							String examScores = "";
							if(mp75.getMP7005_SCORES() == null || mp75.getMP7005_SCORES().equals("")){
								examScores = "0";
							}else{
								examScores = mp75.getMP7005_SCORES();
							}
							
							 switch   (Integer.parseInt(examScores))   {      
							   case   1:
								   scoreArray1.add(mp75.getMP7005_EXAM_CODE());
							           break;
							   case   2:
								   scoreArray2.add(mp75.getMP7005_EXAM_CODE());
							           break;
							   case   3:
								   scoreArray3.add(mp75.getMP7005_EXAM_CODE());
							           break;
							   case   4:
								   scoreArray4.add(mp75.getMP7005_EXAM_CODE());
							           break;
							   case   5:
								   scoreArray5.add(mp75.getMP7005_EXAM_CODE());
							           break;
							}// End Switch
						}// End List
					}else{
						return "error";
					}
				}else if(pageType.equals("add")){
					// nothing to do
				}else{
					return "error";
				}
			}else{
				return "error";
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
	// 绩效考核题目管理页面初始化
	public String examEvaluationMonthlyInfoMngInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Performance Evaluation Form Management Page Init");
			//----------------------------Operation History------------------
			getMenuAuthority(empNum,Constant.SYSTEM_NUM_PERFORMANCE);
			
			if(fun0042001.equals("0")){
				return "noAuthority";
			}
			
			Map<String,String> propMap = examEvaluationMonthlyInfoParameterSet(empNum);
			getExamEvaluationMonthlyInfoByPage(propMap, 1,empNum,employeeData.getMP1001_DEPARTMENT_ID());

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
	// 保存绩效考核题目信息
	public String examEvaluationMonthlyInfoSave(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);

		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Performance Evaluation Form Save");
			//----------------------------Operation History------------------
			
			if(pageType.equals("add")){			
				//errMsg = "数据保存成功";
				addFieldError("errMsg","数据保存成功");

				return INPUT;
			}else if(pageType.equals("edit")){
				if(scoreArray1 == null) {scoreArray1 = new ArrayList<String>();}
				if(scoreArray2 == null) {scoreArray2 = new ArrayList<String>();}
				if(scoreArray3 == null) {scoreArray3 = new ArrayList<String>();}
				if(scoreArray4 == null) {scoreArray4 = new ArrayList<String>();}
				if(scoreArray5 == null) {scoreArray5 = new ArrayList<String>();}
				
				double averageScroes = 0.0;
				// 保存考核分数
				serviceMP7005.updateDataBySelf(scoreArray1, scoreArray2, scoreArray3, scoreArray4, scoreArray5, commonSeq);
				
				if(scoreArray1.size() == 0 && scoreArray2.size() == 0 && scoreArray3.size() == 0 && scoreArray4.size() == 0 && scoreArray5.size() == 0){
					averageScroes = 0.0;
				}else{
					int totalRecords = scoreArray1.size() + scoreArray2.size() + scoreArray3.size() + scoreArray4.size() + scoreArray5.size();
					int totalScores = scoreArray1.size()*1 + scoreArray2.size()*2 + scoreArray3.size()*3 + scoreArray4.size()*4 + scoreArray5.size()*5;
					averageScroes = Arith.div(Double.parseDouble(String.valueOf(totalScores)), Double.parseDouble(String.valueOf(totalRecords)), 1);
				}
				
				MP7004 mp74 = serviceMP7004.findById(commonSeq);
				mp74.setMP7004_COMMENT(mp7004.getMP7004_COMMENT());
				mp74.setMP7004_SCORES(Double.toString(averageScroes));
				mp74.setMP7004_APPRAISER(mp7004.getMP7004_APPRAISER());
				serviceMP7004.update(mp74);
				
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				
				out.append("<script type='text/javascript'>");
				out.append("window.dialogArguments.document.getElementById('btnRefresh').click();");
				out.append("window.close();");
				out.append("</script>");
		        out.flush();
		        out.close();
				
				return INPUT;
			}else{
				return "error";
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
	// 绩效考核题目信息检索处理
	public String examEvaluationMonthlyInfoSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Performance Evaluation Form");
			//----------------------------Operation History------------------
			
			// 重新取得分页数据
			Map<String,String> propMap = examEvaluationMonthlyInfoParameterSet(empNum);
			pageNum = 1;
			getExamEvaluationMonthlyInfoByPage(propMap, pageNum,empNum,employeeData.getMP1001_DEPARTMENT_ID());
			
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
	// 绩效考核题目信息分页处理
	public String examEvaluationMonthlyInfoPageClick(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Performance Evaluation Form Page Click");
			//----------------------------Operation History------------------
			
			// 重新取得分页数据
			Map<String,String> propMap = examEvaluationMonthlyInfoParameterSet(empNum);
			getExamEvaluationMonthlyInfoByPage(propMap, pageNum,empNum,employeeData.getMP1001_DEPARTMENT_ID());
			
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
	// 绩效考核题目信息删除处理
	public String examEvaluationMonthlyInfoDelete(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			if(commonSeq != null && !commonSeq.equals("")){
				MP7004 mp74 = serviceMP7004.findById(commonSeq);

				if(mp74 != null){
					//serviceMP7001.delete(mp71);不做物理删除，只须更新数据状态
					mp74.setMP7004_STATUS("0");
					serviceMP7004.update(mp74);
					
					//----------------------------Operation History------------------
					LogUtil logUtil = new LogUtil();
					logUtil.setServiceMP0011(serviceMP0011);
					logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Delete Performance Evaluation Form, SEQ:{" + mp74.getMP7004_SEQ() + "}");
					//----------------------------Operation History------------------
				}
			}
			
			// 重新取得分页数据
			Map<String,String> propMap = examEvaluationMonthlyInfoParameterSet(empNum);
			getExamEvaluationMonthlyInfoByPage(propMap, pageNum,empNum,employeeData.getMP1001_DEPARTMENT_ID());
			
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
	// 重新取得分页数据
	private void getExamEvaluationMonthlyInfoByPage(Map<String,String> propMap, int _pageNum, String empId, String departmentID){	
		// 处理权限
		getAuthority(empId,Constant.F0042001);
		if(optDepartment.equals("1")){
			propMap.put("DEPARTMENT",departmentID);
		}
		if(optAll.equals("1")){
			propMap.put("AUT_ALL", "1");
		}
		
		evaluationMonthlyInfoList = serviceMP7004.findByPropertyByPage(propMap, -1, -1);
		if( 0 == evaluationMonthlyInfoList.size()%Constant.PAGE_SIZE){
			pageCount = evaluationMonthlyInfoList.size()/Constant.PAGE_SIZE;
		}else{
			pageCount = evaluationMonthlyInfoList.size()/Constant.PAGE_SIZE + 1;
		}
		if(_pageNum > pageCount){
			_pageNum= pageCount;
		}
		if(_pageNum == 0){
			_pageNum = 1;
		}
		evaluationMonthlyInfoList = serviceMP7004.findByPropertyByPage(propMap, _pageNum, Constant.PAGE_SIZE);
		for(int i=0,j=evaluationMonthlyInfoList.size(); i<j; i++){
			MP7004 mp74 = evaluationMonthlyInfoList.get(i);
			
			if(mp74.getMP7004_APPRAISER() != null && mp74.getMP7004_APPRAISER().equals(empId)){
				mp74.setMP7004_IS_APPRAISER("1");
			}else{
				mp74.setMP7004_IS_APPRAISER("0");
			}
			
			if(mp74.getMP7004_APPRAISER() != null && !mp74.getMP7004_APPRAISER().equals("")){
				MP1001 mp11Obj = serviceMP1001.findById(mp74.getMP7004_APPRAISER());
				mp74.setMP7004_APPRAISER_NAME(mp11Obj.getMP1001_PREFERED_NAME());
			}
		}
		
		commonStatusList = Constant.getCommonStatus2();
		commonDepartmentList = UtilCommon.getDepartmentList();
	}
    // 参数设置
	private Map<String,String> examEvaluationMonthlyInfoParameterSet(String empNum){
		Map<String,String> propMap = new HashMap<String,String>();
		propMap.put("EMP_NUM", param1);
		propMap.put("REVIEW_PERIOD", param2);
		propMap.put("EMP_DEP", param3);
		propMap.put("EVL_STATUS", param4);
		propMap.put("APPRAISER", empNum);
		propMap.put("REVIEW_PERIOD_2", param5);
		
		return propMap;
	}
	//---------------------- 绩效考核月度------------------
	
	//---------------------- 绩效考核年度------------------
	// 绩效考核题目新增、编辑页面初始化
	public String examEvaluationYearlyAddInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Fill In Performance Evaluation Form Page Init(Yearly)");
			//----------------------------Operation History------------------
			// 处理权限
			getAuthority(empNum,Constant.F0043002);
			
			if(pageType != null){				
				if(pageType.equals("edit")){
					if(commonSeq != null && !commonSeq.equals("")){
						mp7006 = serviceMP7006.findById(commonSeq);
						
						// 上次最终评定日期
						if(mp7006.getMP7006_EFFECTIVE_DATE_LAST_APPRAISAL() != null && !mp7006.getMP7006_EFFECTIVE_DATE_LAST_APPRAISAL().equals("") && mp7006.getMP7006_EFFECTIVE_DATE_LAST_APPRAISAL().length() > 10){
							mp7006.setMP7006_EFFECTIVE_DATE_LAST_APPRAISAL(mp7006.getMP7006_EFFECTIVE_DATE_LAST_APPRAISAL().substring(0,10));
						}
						// 本次评定最终日期
						if(mp7006.getMP7006_EFFECTIVE_DATE_APPRAISAL() != null && !mp7006.getMP7006_EFFECTIVE_DATE_APPRAISAL().equals("") && mp7006.getMP7006_EFFECTIVE_DATE_APPRAISAL().length() > 10){
							mp7006.setMP7006_EFFECTIVE_DATE_APPRAISAL(mp7006.getMP7006_EFFECTIVE_DATE_APPRAISAL().substring(0,10));
						}
						// 解析考核评定人
						if(mp7006.getMP7006_APPRAISER() != null && !mp7006.getMP7006_APPRAISER().equals("")){
							MP1001 mp11Obj1 = serviceMP1001.findById(mp7006.getMP7006_APPRAISER());
							mp7006.setMP7006_APPRAISER_NAME(mp11Obj1.getMP1001_PREFERED_NAME());
						}
						// 解析考核评定人职位
						if(mp7006.getMP7006_APPRAISER_DESIGNATION() != null && !mp7006.getMP7006_APPRAISER_DESIGNATION().equals("")){
							MP0009 mp09Obj1 = serviceMP0009.findById(Integer.parseInt(mp7006.getMP7006_APPRAISER_DESIGNATION()));
							mp7006.setMP7006_APPRAISER_DESIGNATION_NAME(mp09Obj1.getMP0009_POSITION_NAME_E());
						}
						// 解析考核复查人
						if(mp7006.getMP7006_REVIEWER() != null && !mp7006.getMP7006_REVIEWER().equals("")){
							MP1001 mp11Obj2 = serviceMP1001.findById(mp7006.getMP7006_REVIEWER());
							mp7006.setMP7006_REVIEWER_NAME(mp11Obj2.getMP1001_PREFERED_NAME() + "    " + mp11Obj2.getMP1001_EMPLOYEE_NUM());
						}
						// 解析考核复查人职位
						if(mp7006.getMP7006_REVIEWER_DESIGNATION() != null && !mp7006.getMP7006_REVIEWER_DESIGNATION().equals("")){
							MP0009 mp09Obj2 = serviceMP0009.findById(Integer.parseInt(mp7006.getMP7006_REVIEWER_DESIGNATION()));
							mp7006.setMP7006_REVIEWER_DESIGNATION_NAME(mp09Obj2.getMP0009_POSITION_NAME_E());
						}
						
						evaluationYearlyScoresList = serviceMP7007.findByProperty("MP7007_MASTER_KEY", commonSeq);
						
						// 取得该员工最近六个月的绩效考核历史记录
						StringBuffer search = new StringBuffer();
						search.append(" and mp74.MP7004_EMPLOYEE_NUM = '" + mp7006.getMP7006_EMPLOYEE_NUM() + "' ");
						search.append(" and mp74.MP7004_REVIEW_PERIOD <= '" + mp7006.getMP7006_CURRENT_APPRAISAL_CYCLE() + "' ");
						
						String order = " ORDER BY mp74.MP7004_REVIEW_PERIOD DESC ";
						
						monthlyHistoryScoresList = serviceMP7004.findDataBySelfDefined(search.toString(), order, 1, 6);
						Map<String,String> monthInfoMap = new HashMap<String,String>();
						monthInfoMap = Constant.getMonthNameList();
						// 解析数据集
						BigDecimal totalMonthScore = new BigDecimal("0");
						for(int i=0,j=monthlyHistoryScoresList.size(); i<j; i++){
							MP7004 mp74 = monthlyHistoryScoresList.get(i);
							if(mp74.getMP7004_SCORES() != null && !mp74.getMP7004_SCORES().equals("")){								
								totalMonthScore = totalMonthScore.add(new BigDecimal(mp74.getMP7004_SCORES()));
							}
							String reviewMonth = mp74.getMP7004_REVIEW_PERIOD();
							if(reviewMonth != null && !reviewMonth.equals("") && reviewMonth.length() == 7){
								reviewMonth =String.valueOf(Integer.parseInt(reviewMonth.substring(5,7)));
								if(monthInfoMap.containsKey(reviewMonth)){
									mp74.setMP7004_REVIEW_MONTH_NAME(monthInfoMap.get(reviewMonth));
								}
							}
						}
						
						double sumMonthScore = Arith.div(Double.valueOf(totalMonthScore.toString()), Double.valueOf(monthlyHistoryScoresList.size()), 2);
						// 最近六个月考核总分
						mp7006.setMP7006_MONTH_TOTAL_SCORES(Double.toString(sumMonthScore));
						
					}else{
						return "error";
					}
				}else if(pageType.equals("add")){
					// nothing to do
				}else{
					return "error";
				}
			}else{
				return "error";
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
	// 绩效考核题目管理页面初始化
	public String examEvaluationYearlyInfoMngInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Performance Evaluation Form Management Page Init(Yearly)");
			//----------------------------Operation History------------------
			getMenuAuthority(empNum,Constant.SYSTEM_NUM_PERFORMANCE);
			
			if(fun0043001.equals("0")){
				return "noAuthority";
			}
			
			Map<String,String> propMap = examEvaluationYearlyInfoParameterSet(empNum);
			getExamEvaluationYearlyInfoByPage(propMap, 1,empNum,employeeData.getMP1001_DEPARTMENT_ID());

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
	// 保存绩效考核题目信息
	public String examEvaluationYearlyInfoSave(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);

		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Performance Evaluation Form Save");
			//----------------------------Operation History------------------
			
			if(pageType.equals("add")){			
				//errMsg = "数据保存成功";
				addFieldError("errMsg","数据保存成功");

				return INPUT;
			}else if(pageType.equals("edit")){
				String parentKey = ""; // 定义主表KEY
				int yearlyTotalScore = 0; // 年度考核总分数
				int weightAge = 0; //  试题比重
				int agreedScore = 0; // 协商后分数
				int totalWeidhtAge = 0;
				double finalScore = 0.0; // 计算后分数
				String defaultWeightAge = "5";
				// 循环保存考核分数
				for(int i=0,j=evaluationYearlyScoresList.size(); i<j; i++){
					MP7007 mp77 = evaluationYearlyScoresList.get(i);
					String mp77Key = mp77.getMP7007_SEQ();
					MP7007 mp77Obj = serviceMP7007.findById(mp77Key);
					
					// 设置评分理由
					mp77Obj.setMP7007_REMARKS(mp77.getMP7007_REMARKS());
					// 如果该题目未设置权重则设置权重为默认值"5"
					if(mp77.getMP7007_WEIGHTAGE() == null || mp77.getMP7007_WEIGHTAGE().equals("")){
						weightAge = Integer.parseInt(defaultWeightAge);
						mp77Obj.setMP7007_WEIGHTAGE(defaultWeightAge);
					}else{
						weightAge = Integer.parseInt(mp77.getMP7007_WEIGHTAGE());
						mp77Obj.setMP7007_WEIGHTAGE(mp77.getMP7007_WEIGHTAGE());
					}
					// 检查并设置协商后分数
					if(mp77.getMP7007_AGREED_SCORE() == null || mp77.getMP7007_AGREED_SCORE().equals("")){
						agreedScore = 0;
						mp77Obj.setMP7007_AGREED_SCORE("0");
					}else{
						agreedScore = Integer.parseInt(mp77.getMP7007_AGREED_SCORE());
						mp77Obj.setMP7007_AGREED_SCORE(mp77.getMP7007_AGREED_SCORE());
					}
					// 检查并设置计算后的最终分数((agreedScore*weightAge)/5)
					finalScore = Arith.div(Double.parseDouble(String.valueOf(agreedScore*weightAge)), Double.parseDouble("5"), 1);
					mp77Obj.setMP7007_FINAL_SCORE(Double.toString(finalScore));
					// 自评分数
					mp77Obj.setMP7007_SELF_SCORE(mp77.getMP7007_SELF_SCORE());
					 // 主管评分
					mp77Obj.setMP7007_MANAGER_SCORE(mp77.getMP7007_MANAGER_SCORE());

					serviceMP7007.update(mp77Obj);
					
					yearlyTotalScore += agreedScore*weightAge;
					totalWeidhtAge += weightAge;
					parentKey = mp77Obj.getMP7007_MASTER_KEY();
				}
				// 计算考核总分并更新考核主表
				finalScore = Arith.div(Double.parseDouble(String.valueOf(yearlyTotalScore)), Double.parseDouble("5"), 1);
				
				MP7006 mp76 = serviceMP7006.findById(parentKey);
				mp76.setMP7006_COMPETENCE_TOTAL_SCORES(Double.toString(finalScore));
/*				if(mp76.getMP7006_GRAND_TOTAL_SCORES() == null || mp76.getMP7006_GRAND_TOTAL_SCORES().equals("")){
					grandTotalScore = "0";
				}else{
					grandTotalScore = mp76.getMP7006_GRAND_TOTAL_SCORES();
				}
				mp76.setMP7006_GRAND_TOTAL_SCORES(Double.toString(Arith.add(Double.parseDouble(grandTotalScore), finalScore)));*/
				mp76.setMP7006_GRAND_TOTAL_SCORES(Double.toString(finalScore));
				mp76.setMP7006_TOTAL_WEIGHTAGE(Integer.toString(totalWeidhtAge));
				
				int mRating =100 - totalWeidhtAge;
				double mTotalScore = Arith.mul(Double.parseDouble(mp7006.getMP7006_MONTH_TOTAL_SCORES()), Double.parseDouble(String.valueOf(mRating)));
				mTotalScore = Arith.div(mTotalScore, Double.parseDouble("5"), 1);
				mTotalScore = Arith.add(mTotalScore, finalScore);
				
				mp76.setMP7006_GRAND_TOTAL_SCORES(Double.toString(mTotalScore));
				
				mp76.setMP7006_APPRAISER(mp7006.getMP7006_APPRAISER()); // 考核评定人
				MP1001 appraiserObj = serviceMP1001.findById(mp7006.getMP7006_APPRAISER());
				if(appraiserObj != null){
					mp76.setMP7006_APPRAISER_DESIGNATION(appraiserObj.getMP1001_POSITION());
				}
				
				serviceMP7006.update(mp76);
				
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				
				out.append("<script type='text/javascript'>");
				out.append("window.dialogArguments.document.getElementById('btnRefresh').click();");
				out.append("window.close();");
				out.append("</script>");
		        out.flush();
		        out.close();
				
				return INPUT;
			}else{
				return "error";
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
	// 绩效考核题目信息检索处理
	public String examEvaluationYearlyInfoSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Performance Evaluation Form(Yearly)");
			//----------------------------Operation History------------------
			
			// 重新取得分页数据
			Map<String,String> propMap = examEvaluationYearlyInfoParameterSet(empNum);
			pageNum = 1;
			getExamEvaluationYearlyInfoByPage(propMap, pageNum,empNum,employeeData.getMP1001_DEPARTMENT_ID());
			
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
	// 绩效考核题目信息分页处理
	public String examEvaluationYearlyInfoPageClick(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Performance Evaluation Form Page Click(Yearly), Page Number:" + pageNum);
			//----------------------------Operation History------------------
			
			// 重新取得分页数据
			Map<String,String> propMap = examEvaluationYearlyInfoParameterSet(empNum);
			getExamEvaluationYearlyInfoByPage(propMap, pageNum,empNum,employeeData.getMP1001_DEPARTMENT_ID());
			
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
	// 绩效考核题目信息删除处理
	public String examEvaluationYearlyInfoDelete(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			if(commonSeq != null && !commonSeq.equals("")){
				MP7006 mp76 = serviceMP7006.findById(commonSeq);

				if(mp76 != null){
					//serviceMP7001.delete(mp71);不做物理删除，只须更新数据状态
					mp76.setMP7006_STATUS("0");
					serviceMP7006.update(mp76);
					
					//----------------------------Operation History------------------
					LogUtil logUtil = new LogUtil();
					logUtil.setServiceMP0011(serviceMP0011);
					logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Delete Performance Evaluation Form, SEQ:{" + mp76.getMP7006_SEQ() + "}");
					//----------------------------Operation History------------------
				}
			}
			
			// 重新取得分页数据
			Map<String,String> propMap = examEvaluationYearlyInfoParameterSet(empNum);
			getExamEvaluationYearlyInfoByPage(propMap, pageNum,empNum,employeeData.getMP1001_DEPARTMENT_ID());
			
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
	// 重新取得分页数据
	private void getExamEvaluationYearlyInfoByPage(Map<String,String> propMap, int _pageNum, String empId, String departmentID){
		// 处理权限
		getAuthority(empId,Constant.F0043001);
		if(optDepartment.equals("1")){
			propMap.put("DEPARTMENT",departmentID);
		}
		if(optAll.equals("1")){
			propMap.put("AUT_ALL", "1");
		}
		evaluationYearlyInfoList = serviceMP7006.findByPropertyByPage(propMap, -1, -1);
		if( 0 == evaluationYearlyInfoList.size()%Constant.PAGE_SIZE){
			pageCount = evaluationYearlyInfoList.size()/Constant.PAGE_SIZE;
		}else{
			pageCount = evaluationYearlyInfoList.size()/Constant.PAGE_SIZE + 1;
		}
		if(_pageNum > pageCount){
			_pageNum= pageCount;
		}
		if(_pageNum == 0){
			_pageNum = 1;
		}
		evaluationYearlyInfoList = serviceMP7006.findByPropertyByPage(propMap, _pageNum, Constant.PAGE_SIZE);
		for(int i=0,j=evaluationYearlyInfoList.size(); i<j; i++){
			MP7006 mp76 = evaluationYearlyInfoList.get(i);
			
			if(mp76.getMP7006_APPRAISER() != null && mp76.getMP7006_APPRAISER().equals(empId)){
				mp76.setMP7006_IS_APPRAISER("1");
			}else{
				mp76.setMP7006_IS_APPRAISER("0");
			}
			
			if(mp76.getMP7006_APPRAISER() != null && !mp76.getMP7006_APPRAISER().equals("")){
				MP1001 mp11Obj = serviceMP1001.findById(mp76.getMP7006_APPRAISER());
				mp76.setMP7006_APPRAISER_NAME(mp11Obj.getMP1001_PREFERED_NAME());
			}
		}
		
		commonStatusList = Constant.getCommonStatus2();
		commonDepartmentList = UtilCommon.getDepartmentList();

	}
    // 参数设置
	private Map<String,String> examEvaluationYearlyInfoParameterSet(String empNum){
		Map<String,String> propMap = new HashMap<String,String>();
		propMap.put("EMP_NUM", param1);
		propMap.put("REVIEW_PERIOD", param2);
		propMap.put("EMP_DEP", param3);
		propMap.put("EVL_STATUS", param4);
		propMap.put("APPRAISER", empNum);
		
		return propMap;
	}
	
	//---------------------- 绩效考核年度------------------
	
	//---------------------- 绩效考核年度------------------
	// 绩效考核题目新增、编辑页面初始化
	public String examAppraiserAddInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Exam Appraiser Page Init");
			//----------------------------Operation History------------------

			if(pageType != null){				
				if(pageType.equals("edit")){
					if(commonSeq != null && !commonSeq.equals("")){
						mp1001 = serviceMP1001.findById(commonSeq);
						
						// 解析指定评定人姓名
						if(mp1001.getMP1001_APPRASIER() != null && !mp1001.getMP1001_APPRASIER().equals("")){
							MP1001 mp11 = serviceMP1001.findById(mp1001.getMP1001_APPRASIER());
							
							mp1001.setMP1001_APPRASIER_NAME(mp11.getMP1001_PREFERED_NAME());
						}
					}else{
						return "error";
					}
				}else if(pageType.equals("add")){
					// nothing to do
				}else{
					return "error";
				}
			}else{
				return "error";
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
	// 绩效考核题目管理页面初始化
	public String examAppraiserInfoMngInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Exam Appraiser Management Page Init");
			//----------------------------Operation History------------------
			getMenuAuthority(empNum,Constant.SYSTEM_NUM_PERFORMANCE);
			
			if(fun0041003.equals("0")){
				return "noAuthority";
			}
			
			getExamAppraiserInfoByPage(1,empNum);

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
	// 保存绩效考核题目信息
	public String examAppraiserInfoSave(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);

		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Exam Appraiser Save");
			//----------------------------Operation History------------------
			
			if(pageType.equals("add")){			
				//errMsg = "数据保存成功";
				addFieldError("errMsg","数据保存成功");

				return INPUT;
			}else if(pageType.equals("edit")){
				MP1001 mp11 = serviceMP1001.findById(commonSeq);
				mp11.setMP1001_APPRASIER(mp1001.getMP1001_APPRASIER()); // 评定指定人
				serviceMP1001.update(mp11);
				
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				
				out.append("<script type='text/javascript'>");
				out.append("window.dialogArguments.document.getElementById('btnRefresh').click();");
				out.append("window.close();");
				out.append("</script>");
		        out.flush();
		        out.close();
				
				return INPUT;
			}else{
				return "error";
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
	// 绩效考核题目信息检索处理
	public String examAppraiserInfoSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Exam Appraiser");
			//----------------------------Operation History------------------
			
			// 重新取得分页数据
			pageNum = 1;
			getExamAppraiserInfoByPage(pageNum, empNum);
			
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
	// 绩效考核题目信息分页处理
	public String examAppraiserInfoPageClick(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Exam Appraiser Page Click, Page Number:" + pageNum);
			//----------------------------Operation History------------------
			
			// 重新取得分页数据
			getExamAppraiserInfoByPage(pageNum,empNum);
			
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
	// 重新取得分页数据
	private void getExamAppraiserInfoByPage(int _pageNum, String empId){	
		StringBuffer searchStr = new StringBuffer();
		searchStr.append(" and mp11.MP1001_STATUS in ('1','2')");
		// employee number
		if(param1 != null && !param1.equals("") && !param1.equals("undefined")){
			searchStr.append(" and mp11.MP1001_EMPLOYEE_NUM = '" + param1 + "'");
		}
		// employee name
		if(param2 != null && !param2.equals("") && !param2.equals("undefined")){
			searchStr.append(" and mp11.MP1001_PREFERED_NAME like '%" + param2 + "%'");
		}
		// department
		if(param3 != null && !param3.equals("") && !param3.equals("undefined") && !param3.equals("-1")){
			searchStr.append(" and mp11.MP1001_DEPARTMENT_ID = '" + param3 + "'");
		}
		
		examAppraiserInfoList = serviceMP1001.findData(searchStr.toString(), " order by mp11.MP1001_EMPLOYEE_NUM", -1, -1);
		if( 0 == examAppraiserInfoList.size()%Constant.PAGE_SIZE){
			pageCount = examAppraiserInfoList.size()/Constant.PAGE_SIZE;
		}else{
			pageCount = examAppraiserInfoList.size()/Constant.PAGE_SIZE + 1;
		}
		if(_pageNum > pageCount){
			_pageNum= pageCount;
		}
		if(_pageNum == 0){
			_pageNum = 1;
		}
		examAppraiserInfoList = serviceMP1001.findData(searchStr.toString(), " order by mp11.MP1001_EMPLOYEE_NUM", _pageNum, Constant.PAGE_SIZE);
		for(int i=0,j=examAppraiserInfoList.size(); i<j; i++){
			MP1001 mp11 = examAppraiserInfoList.get(i);
			
			// 解析员工绩效考核评定指定人
			if(mp11.getMP1001_APPRASIER() != null && !mp11.getMP1001_APPRASIER().equals("")){
				MP1001 mp11Obj = serviceMP1001.findById(mp11.getMP1001_APPRASIER());
				//mp11.setMP1001_APPRASIER_NAME(mp11Obj.getMP1001_PREFERED_NAME());			
				mp11.setMP1001_APPRASIER_NAME(mp11Obj == null ? "-" : mp11Obj.getMP1001_PREFERED_NAME());
			}else{
				mp11.setMP1001_APPRASIER_NAME("-");
			}
		}
		commonDepartmentList = UtilCommon.getDepartmentList();
		// 处理权限
		getAuthority(empId,Constant.F0041003);
	}

	//---------------------- 绩效考核年度------------------
	
	//---------------------- 生成Excel文档-----------------
	public String createMonthlyAppraisalFormInfoExcelDocument(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(empNum,employeeData.getMP1001_PREFERED_NAME(),"Create Appraisal(Monthly) Information Excel Document");
			//----------------------------Operation History------------------
			// 处理权限
			getAuthority(empNum,Constant.F0042001);
			
			// 新建EXCEL工作表
			Workbook wb = ExcelUtil.CreateHSSFWorkBook();
			// 新建一个SHEET页面
			Sheet sheet = ExcelUtil.CreateSheet(wb, "Performance Appraisal Scores(Monthly)");
			// 设置SHEET页面属性
			ExcelUtil.SetSheetPropertyHSSF(sheet);
			// 获取预定的样式
			Map<String, CellStyle> styles = ExcelUtil.CreateStyles(wb);
			
			// Header标题
			String[] titles = {"ID", "EMPLOYEE NUMBER", "NAME", "DEPARTMENT", "JOB TITLE","MAMAGER","APPRAISER","REVIEW PERIOD","SCORES"};
			// 生成标题行
			float rowHeight = 27f;
			ExcelUtil.CreateHeadRow(sheet, titles, rowHeight, styles);
	        // 冻结第一行
	        sheet.createFreezePane(0, 1);
	        
	        int[] cellsWidth = {5,20,16,20,20,20,20,20,20};
	        // 设置单元格的宽度
	        ExcelUtil.SetCellsWidth(sheet, cellsWidth);
	        
	      //---------------------------主报表-----------------------------------------------------------------------------------------
	        // 取得报表数据
	        fileName = "performanceAppraisalScores(Monthly).xls";
	        String sortStr = " order by mp74.MP7004_DEPARTMENT ";
	        StringBuffer searchStr = new StringBuffer();
	        searchStr.append(" and mp74.MP7004_STATUS = '1'  ");
	        
	        if(optAll.equals("1")){
				// Employee Number
				if(param1 != null && !param1.equals("") && !param1.equals("undefined")){
					searchStr.append(" and mp74.MP7004_EMPLOYEE_NUM = '" + param1 + "' ");
				}
				// Department
				if(param3 != null && !param3.equals("") && !param3.equals("-1")){
					searchStr.append(" and mp74.MP7004_DEPARTMENT = '" + param3 + "' ");
				}
	        }else if(optDepartment.equals("1")){
				searchStr.append(" and mp74.MP7004_DEPARTMENT = '" + employeeData.getMP1001_DEPARTMENT_ID() + "' ");
				// Employee Number
				if(param1 != null && !param1.equals("") && !param1.equals("undefined")){
					searchStr.append(" and mp74.MP7004_EMPLOYEE_NUM = '" + param1 + "' ");
				}
			}else{
				searchStr.append(" and mp74.MP7004_EMPLOYEE_NUM = '" + empNum + "' ");
			}

			// Review Period
			if(param2 != null && !param2.equals("") && !param2.equals("undefined")){
				searchStr.append(" and mp74.MP7004_REVIEW_PERIOD = '" + param2 + "' ");
			}
	        
	        int count;
	        String[] datas;
	        Map<String,String> empInfoMap = getAllEmployeeInformation();
	        List<MP7004> mp74List = serviceMP7004.findDataBySelfDefined(searchStr.toString(), sortStr, -1, -1);
	        if(mp74List == null || mp74List.size() == 0){
	        	datas = new String[1];
	        	datas[0] = "No Data";      	
	        	
	        	ExcelUtil.SetCellsValue(1, sheet, styles, datas);
	        	sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 8));//第二行 二到三列
	        }else{
		        for(int i=0,j=mp74List.size(); i<j; i++){
		        	MP7004 mp7004Obj = mp74List.get(i);
		        	count = i + 1;
		        	datas = new String[9];
		        	datas[0] = String.valueOf(count);
		        	datas[1] = mp7004Obj.getMP7004_EMPLOYEE_NUM();
		        	datas[2] = mp7004Obj.getMP7004_EMPLOYEE_NAME();
		        	datas[3] = mp7004Obj.getMP7004_DEPARTMENT_NAME();
		        	datas[4] = mp7004Obj.getMP7004_JOB_NAME();
		        	if(empInfoMap.containsKey(mp7004Obj.getMP7004_MANAGER())){
		        		datas[5] = empInfoMap.get(mp7004Obj.getMP7004_MANAGER());
		        	}
		        	if(empInfoMap.containsKey(mp7004Obj.getMP7004_APPRAISER())){
		        		datas[6] = empInfoMap.get(mp7004Obj.getMP7004_APPRAISER());
		        	}
		        	datas[7] = mp7004Obj.getMP7004_REVIEW_PERIOD();
		        	datas[8] = mp7004Obj.getMP7004_SCORES();
		        	
		        	ExcelUtil.SetCellsValue(count, sheet, styles, datas);
		        }
	        }
	        
			// 生成Excel文件
	        String _path = ServletActionContext.getServletContext().getRealPath("/") + "temp\\" + fileName;
	        ExcelUtil.createExcelFile(_path, wb);

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
	public String createYearlyAppraisalFormInfoExcelDocument(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(empNum,employeeData.getMP1001_PREFERED_NAME(),"Create Appraisal(Yearly) Information Excel Document");
			//----------------------------Operation History------------------
			// 处理权限
			getAuthority(empNum,Constant.F0043001);
			
			// 新建EXCEL工作表
			Workbook wb = ExcelUtil.CreateHSSFWorkBook();
			// 新建一个SHEET页面
			Sheet sheet = ExcelUtil.CreateSheet(wb, "Performance Appraisal Scores(Yearly)");
			// 设置SHEET页面属性
			ExcelUtil.SetSheetPropertyHSSF(sheet);
			// 获取预定的样式
			Map<String, CellStyle> styles = ExcelUtil.CreateStyles(wb);
			
			// Header标题
			String[] titles = {"ID", "EMPLOYEE NUMBER", "NAME", "DEPARTMENT", "JOB TITLE","APPRAISER","REVIEW PERIOD","TOTAL OF COMPETENCIES","GRAND TOTAL"};
			// 生成标题行
			float rowHeight = 27f;
			ExcelUtil.CreateHeadRow(sheet, titles, rowHeight, styles);
	        // 冻结第一行
	        sheet.createFreezePane(0, 1);
	        
	        int[] cellsWidth = {5,20,16,20,20,20,20,30,20};
	        // 设置单元格的宽度
	        ExcelUtil.SetCellsWidth(sheet, cellsWidth);
	        
	      //---------------------------主报表-----------------------------------------------------------------------------------------
	        // 取得报表数据
	        fileName = "performanceAppraisalScores(Yearly).xls";
	        String sortStr = " order by mp76.MP7006_DEPARTMENT ";
	        StringBuffer searchStr = new StringBuffer();
	        searchStr.append(" and mp76.MP7006_STATUS = '1'  ");
	        
	        if(optAll.equals("1")){
				// Employee Number
				if(param1 != null && !param1.equals("") && !param1.equals("undefined")){
					searchStr.append(" and mp76.MP7006_EMPLOYEE_NUM = '" + param1 + "' ");
				}
				// Department
				if(param3 != null && !param3.equals("") && !param3.equals("-1")){
					searchStr.append(" and mp76.MP7006_DEPARTMENT = '" + param3 + "' ");
				}
	        }else if(optDepartment.equals("1")){
				searchStr.append(" and mp76.MP7006_DEPARTMENT = '" + employeeData.getMP1001_DEPARTMENT_ID() + "' ");
				// Employee Number
				if(param1 != null && !param1.equals("") && !param1.equals("undefined")){
					searchStr.append(" and mp76.MP7006_EMPLOYEE_NUM = '" + param1 + "' ");
				}
			}else{
				searchStr.append(" and mp76.MP7006_EMPLOYEE_NUM = '" + empNum + "' ");
			}

			// Review Period
			if(param2 != null && !param2.equals("") && !param2.equals("undefined")){
				searchStr.append(" and mp76.MP7006_CURRENT_APPRAISAL_CYCLE = '" + param2 + "' ");
			}
	        
	        int count;
	        String[] datas;
	        Map<String,String> empInfoMap = getAllEmployeeInformation();
	        List<MP7006> mp76List = serviceMP7006.findDataBySelfDefined(searchStr.toString(), sortStr, -1, -1);
	        if(mp76List == null || mp76List.size() == 0){
	        	datas = new String[1];
	        	datas[0] = "No Data";      	
	        	
	        	ExcelUtil.SetCellsValue(1, sheet, styles, datas);
	        	sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 8));//第二行 二到三列
	        }else{
		        for(int i=0,j=mp76List.size(); i<j; i++){
		        	MP7006 mp7006Obj = mp76List.get(i);
		        	count = i + 1;
		        	datas = new String[9];
		        	datas[0] = String.valueOf(count);
		        	datas[1] = mp7006Obj.getMP7006_EMPLOYEE_NUM();
		        	datas[2] = mp7006Obj.getMP7006_EMPLOYEE_NAME();
		        	datas[3] = mp7006Obj.getMP7006_DEPARTMENT_NAME();
		        	datas[4] = mp7006Obj.getMP7006_JOB_TITLE();
		        	if(empInfoMap.containsKey(mp7006Obj.getMP7006_APPRAISER())){
		        		datas[5] = empInfoMap.get(mp7006Obj.getMP7006_APPRAISER());
		        	}
		        	datas[6] = mp7006Obj.getMP7006_CURRENT_APPRAISAL_CYCLE();
		        	datas[7] = mp7006Obj.getMP7006_COMPETENCE_TOTAL_SCORES();
		        	datas[8] = mp7006Obj.getMP7006_GRAND_TOTAL_SCORES();
		        	
		        	ExcelUtil.SetCellsValue(count, sheet, styles, datas);
		        }
	        }
	        
			// 生成Excel文件
	        String _path = ServletActionContext.getServletContext().getRealPath("/") + "temp\\" + fileName;
	        ExcelUtil.createExcelFile(_path, wb);

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
	// 取得所有员工基本
	private Map<String,String> getAllEmployeeInformation(){
		Map<String,String> empInfoMap = new HashMap<String,String>();
		
		List<MP1001> mp11List = serviceMP1001.findAll();
		for(int i=0,j=mp11List.size(); i<j; i++){
			MP1001 mp11 = mp11List.get(i);
			if(empInfoMap.containsKey(mp11.getMP1001_EMPLOYEE_NUM())){
				continue;
			}else{
				empInfoMap.put(mp11.getMP1001_EMPLOYEE_NUM(), mp11.getMP1001_PREFERED_NAME());
			}
		}
		
		return empInfoMap;
	}
	
	public String examMonthlyMaxScoresSave(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);

		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"exam evaluation monthly max scores setting");
			//----------------------------Operation History------------------
			
			
			
			addFieldError("errMsg","数据保存成功");
			
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
	
	//----------------------- 图形报表-----------------------
	// 图形报表(所有部门当月的总预算)
	public String appraisalChartReport1() throws IOException{
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		OutputStream out = null;

		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"appraisal Chart Report Bar1");
			//----------------------------Operation History------------------
			
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			if(param1 == null || param2 == null || param1.equals("") || param1.equals("")){
				param1 = param2 =UtilDate.getToday("yyyy-MM");
			}else{
				param1 = param1.substring(0, 7);
				param2 = param2.substring(0, 7);
			}
			List<MP7004> mp74List = serviceMP7004.getBarCHart1(param1, param2);
			
			for(int i=0,j=mp74List.size(); i<j; i++){
				MP7004 mp74 = mp74List.get(i);
				dataset.addValue(Double.valueOf(mp74.getMP7004_SCORES()) , "Department Average Scores", mp74.getMP7004_DEPARTMENT_NAME());
			}
			
			try {
				String title = "Statistics Figure(" + param1 + "~" + param2 + ")";
			    ChartUtil chartUtil = new ChartUtil();
			    JFreeChart chart = chartUtil.createBarChart("", "", "",dataset, "chartStatus.jpg", title);
			    
			    HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
			    response.setContentType("image/jpg");
			    ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart, 1200, 600);
			    out = response.getOutputStream();
			    out.flush();
			 }catch (Exception e) {
			    e.printStackTrace();
			 }
			 
			 return null;
		}catch(Exception ex){
			log.info(ex.getMessage());
			if(session.containsKey("ERR_MSG")){
				session.remove("ERR_MSG");
				session.put("ERR_MSG", ex.getMessage());
			}
			return "error";
		}finally{
			out.close();
		}
	}
	// 图形报表(部门内部员工的平均分)
	public String appraisalChartReport2() throws IOException{
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		OutputStream out = null;

		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"appraisal Chart Report Bar2");
			//----------------------------Operation History------------------
			
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			if(param1 == null || param2 == null || param1.equals("") || param1.equals("")){
				param1 = param2 =UtilDate.getToday("yyyy-MM");
			}else{
				param1 = param1.substring(0, 7);
				param2 = param2.substring(0, 7);
			}
			if(param3 == null || param3.equals("") || param3.equals("-1")){
				param3 = employeeData.getMP1001_DEPARTMENT_ID();
			}
			List<MP7004> mp74List = serviceMP7004.getBarCHart2(param1, param2,param3);
			
			for(int i=0,j=mp74List.size(); i<j; i++){
				MP7004 mp74 = mp74List.get(i);
				dataset.addValue(Double.valueOf(mp74.getMP7004_SCORES()) , "Employee Average Scores", mp74.getMP7004_EMPLOYEE_NAME());
			}
			
			try {
				String title = "Statistics Figure(" + param1 + "~" + param2 + ")";
			    ChartUtil chartUtil = new ChartUtil();
			    JFreeChart chart = chartUtil.createBarChart("", "", "",dataset, "chartStatus.jpg", title);
			    
			    HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
			    response.setContentType("image/jpg");
			    ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart, 1200, 600);
			    out = response.getOutputStream();
			    out.flush();
			 }catch (Exception e) {
			    e.printStackTrace();
			 }
			 
			 return null;
		}catch(Exception ex){
			log.info(ex.getMessage());
			if(session.containsKey("ERR_MSG")){
				session.remove("ERR_MSG");
				session.put("ERR_MSG", ex.getMessage());
			}
			return "error";
		}finally{
			out.close();
		}
	}
	// 图形报表(部门内部员工的平均分)
	public String appraisalChartReport3() throws IOException{
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		OutputStream out = null;

		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"appraisal Chart Report Bar3");
			//----------------------------Operation History------------------
			
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			if(param1 == null || param2 == null || param1.equals("") || param1.equals("")){
				param1 = param2 =UtilDate.getToday("yyyy-MM");
			}else{
				param1 = param1.substring(0, 7);
				param2 = param2.substring(0, 7);
			}
			if(param3 == null || param3.equals("") || param3.equals("-1")){
				param3 = employeeData.getMP1001_DEPARTMENT_ID();
			}
			List<MP7004> mp74List = serviceMP7004.getLineCHart3(param1, param2,param3);
			
			for(int i=0,j=mp74List.size(); i<j; i++){
				MP7004 mp74 = mp74List.get(i);
				dataset.addValue(Double.valueOf(mp74.getMP7004_SCORES()) , "Department Average Scores", mp74.getMP7004_REVIEW_PERIOD());
			}
			
			try {
				String title = "Statistics Figure(" + param1 + "~" + param2 + ")";
			    ChartUtil chartUtil = new ChartUtil();
			    JFreeChart chart = chartUtil.createTimeXYChar(title, "", "",dataset, "appraisalChartReport3.jpg");
			    
			    HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
			    response.setContentType("image/jpg");
			    ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart, 1200, 600);
			    out = response.getOutputStream();
			    out.flush();
			 }catch (Exception e) {
			    e.printStackTrace();
			 }
			 
			 return null;
		}catch(Exception ex){
			log.info(ex.getMessage());
			if(session.containsKey("ERR_MSG")){
				session.remove("ERR_MSG");
				session.put("ERR_MSG", ex.getMessage());
			}
			return "error";
		}finally{
			out.close();
		}
	}
	// 图形报表(部门内部员工的平均分)
	public String appraisalChartReport4() throws IOException{
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		OutputStream out = null;

		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"appraisal Chart Report Bar4");
			//----------------------------Operation History------------------
			
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			if(param1 == null || param2 == null || param1.equals("") || param1.equals("")){
				param1 = param2 =UtilDate.getToday("yyyy-MM");
			}else{
				param1 = param1.substring(0, 7);
				param2 = param2.substring(0, 7);
			}
			if(param3 == null || param3.equals("") || param3.equals("-1")){
				param3 = employeeData.getMP1001_DEPARTMENT_ID();
			}
			List<MP7004> mp74List = serviceMP7004.getLineCHart4(param1, param2,param3,param4);
			
			for(int i=0,j=mp74List.size(); i<j; i++){
				MP7004 mp74 = mp74List.get(i);
				dataset.addValue(Double.valueOf(mp74.getMP7004_SCORES()) , "Employee Average Scores", mp74.getMP7004_REVIEW_PERIOD());
			}
			
			try {
				String title = "Statistics Figure(" + param1 + "~" + param2 + ")";
			    ChartUtil chartUtil = new ChartUtil();
			    JFreeChart chart = chartUtil.createTimeXYChar(title, "", "",dataset, "appraisalChartReport4.jpg");
			    
			    HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
			    response.setContentType("image/jpg");
			    ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart, 1200, 600);
			    out = response.getOutputStream();
			    out.flush();
			 }catch (Exception e) {
			    e.printStackTrace();
			 }
			 
			 return null;
		}catch(Exception ex){
			log.info(ex.getMessage());
			if(session.containsKey("ERR_MSG")){
				session.remove("ERR_MSG");
				session.put("ERR_MSG", ex.getMessage());
			}
			return "error";
		}finally{
			out.close();
		}
	}
	
	public String appraisalChartReport2Init(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(empNum,employeeData.getMP1001_PREFERED_NAME(),"Appraiser Chart Report2 Page Init");
			//----------------------------Operation History------------------
			
			commonDepartmentList = UtilCommon.getDepartmentList();

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
	public String appraisalChartReport3Init(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(empNum,employeeData.getMP1001_PREFERED_NAME(),"Appraiser Chart Report3 Page Init");
			//----------------------------Operation History------------------
			
			commonDepartmentList = UtilCommon.getDepartmentList();

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
	public String appraisalChartReport4Init(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(empNum,employeeData.getMP1001_PREFERED_NAME(),"Appraiser Chart Report4 Page Init");
			//----------------------------Operation History------------------
			
			commonDepartmentList = UtilCommon.getDepartmentList();
					
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
	
	
	
	
	
	//---------------------- 绩效考核题目管理(部门考评用)------------------
	// 绩效考核题目新增、编辑页面初始化
	public String examQuestion2AddInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Exam Question Information Page Init(Department)");
			//----------------------------Operation History------------------
			// 处理权限
			getAuthority(empNum,Constant.F0050001);
			getMenuAuthority(empNum,Constant.SYSTEM_NUM_PERFORMANCE);
			
			if(fun0050001.equals("0")){
				return "noAuthority";
			}

			if(pageType != null){				
				if(pageType.equals("edit")){
					if(commonSeq != null && !commonSeq.equals("")){
						commonStatusList = Constant.getCommonStatus2();
						mp8001 = serviceMP8001.findById(commonSeq);
						
						commonStatus= mp8001.getMP8001_STATUS();
					}else{
						return "error";
					}
				}else if(pageType.equals("add")){
					// nothing to do
				}else{
					return "error";
				}
			}else{
				return "error";
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
	// 绩效考核题目管理页面初始化
	public String examQuestion2InfoMngInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Exam Question Information Management Page Init(Department)");
			//----------------------------Operation History------------------
			// 处理权限
			getMenuAuthority(empNum,Constant.SYSTEM_NUM_PERFORMANCE);
			
			if(fun0050002.equals("0")){
				return "noAuthority";
			}
			
			Map<String,String> propMap = new HashMap<String,String>();
			propMap.put("QUESTION_NUM", commonSeq);
			propMap.put("QUESTION_TITLE", param1);
			propMap.put("QUESTION_STATUS", param2);
			
			getExamQuestion2InfoByPage(propMap, 1,empNum);

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
	// 保存绩效考核题目信息
	public String examQuestion2InfoSave(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Exam Question Information Save(Department)");
			//----------------------------Operation History------------------
			
			if(pageType.equals("add")){
				mp8001.setMP8001_STATUS("1");
				mp8001.setMP8001_CREATOR(empNum);
				mp8001.setMP8001_CREATE_TIME(UtilDate.get24DateTime());
				mp8001.setMP8001_SEQ(Constant.generateSeq());
				
				if(mp8001.getMP8001_TITLE() == null || mp8001.getMP8001_TITLE().equals("")){
					addFieldError("mp8001.MP8001_TITLE","Title cannot be empty");
					return INPUT;
				}
				
				serviceMP8001.save(mp8001);
				
				//errMsg = "数据保存成功";
				addFieldError("errMsg","数据保存成功");
				// 记录操作日志
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Exam Question Information, Exam Question:{" + mp8001.getMP8001_TITLE() + "}(Department)");
				
				mp8001 = new MP8001();
				return INPUT;
			}else if(pageType.equals("edit")){
				MP8001 mp71 = serviceMP8001.findById(commonSeq);
				
				mp71.setMP8001_TITLE(mp8001.getMP8001_TITLE()); // 标题
				mp71.setMP8001_SUB_TITLE(mp8001.getMP8001_SUB_TITLE()); // 子标题
				mp71.setMP8001_STATUS(commonStatus); // 状态
				mp71.setMP8001_COMMENT(mp8001.getMP8001_COMMENT()); // 备注
				
				serviceMP8001.update(mp71);
				// 记录操作日志
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Edit Exam Question Information, Exam Question:{" + mp8001.getMP8001_TITLE() + "}(Department)");
				
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				
				out.append("<script type='text/javascript'>");
				out.append("window.dialogArguments.document.getElementById('btnRefresh').click();");
				out.append("window.close();");
				out.append("</script>");
		        out.flush();
		        out.close();
				
				return INPUT;
			}else{
				return "error";
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
	// 绩效考核题目信息检索处理
	public String examQuestion2InfoSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Exam Question Information(Department)");
			//----------------------------Operation History------------------
			
			// 重新取得分页数据
			Map<String,String> propMap = new HashMap<String,String>();
			propMap.put("QUESTION_NUM", commonSeq);
			propMap.put("QUESTION_TITLE", param1);
			propMap.put("QUESTION_STATUS", param2);
			pageNum = 1;
			getExamQuestion2InfoByPage(propMap, pageNum,empNum);
			
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
	// 绩效考核题目信息分页处理
	public String examQuestion2InfoPageClick(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Exam Question Information Page Click(Department)");
			//----------------------------Operation History------------------
			
			// 重新取得分页数据
			Map<String,String> propMap = new HashMap<String,String>();
			propMap.put("QUESTION_NUM", commonSeq);
			propMap.put("QUESTION_TITLE", param1);
			propMap.put("QUESTION_STATUS", param2);
			getExamQuestion2InfoByPage(propMap, pageNum,empNum);
			
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
	// 绩效考核题目信息删除处理
	public String examQuestion2InfoDelete(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{			
			if(commonSeq != null && !commonSeq.equals("")){
				MP8001 mp81 = serviceMP8001.findById(commonSeq);

				if(mp81 != null){
					//serviceMP8001.delete(mp71);不做物理删除，只须更新数据状态
					mp81.setMP8001_STATUS("0");
					serviceMP8001.update(mp81);
					
					//----------------------------Operation History------------------
					LogUtil logUtil = new LogUtil();
					logUtil.setServiceMP0011(serviceMP0011);
					logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Delete Exam Question Information, Exam Question:{" + mp81.getMP8001_TITLE() + "}(Department)");
					//----------------------------Operation History------------------
				}
			}
			
			// 重新取得分页数据
			Map<String,String> propMap = new HashMap<String,String>();
			//propMap.put("QUESTION_NUM", commonSeq);
			propMap.put("QUESTION_TITLE", param1);
			propMap.put("QUESTION_STATUS", param2);
			getExamQuestion2InfoByPage(propMap, pageNum,empNum);
			
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
	// 为绩效考核计划追加考核题目页面初始化
	public String examQuestion2SelectInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Exam Question For Exam Plan Init(Department)");
			//----------------------------Operation History------------------
			
			Map<String,String> propMap = new HashMap<String,String>();
			getExamQuestion2InfoByPage(propMap, 1,empNum);

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
	// 添加考核题目分页处理
	public String examQuestion2SelectPageClick(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Exam Question For Exam Plan Page Click(Department)");
			//----------------------------Operation History------------------
			
			// 重新取得分页数据
			Map<String,String> propMap = new HashMap<String,String>();
			getExamQuestion2InfoByPage(propMap, pageNum,empNum);
			
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
	// 重新取得分页数据
	private void getExamQuestion2InfoByPage(Map<String,String> propMap, int _pageNum, String empId){	
		examQuestion2InfoList = serviceMP8001.findByPropertyByPage(propMap, -1, -1);
		if( 0 == examQuestion2InfoList.size()%Constant.PAGE_SIZE){
			pageCount = examQuestion2InfoList.size()/Constant.PAGE_SIZE;
		}else{
			pageCount = examQuestion2InfoList.size()/Constant.PAGE_SIZE + 1;
		}
		if(_pageNum > pageCount){
			_pageNum= pageCount;
		}
		examQuestion2InfoList = serviceMP8001.findByPropertyByPage(propMap, _pageNum, Constant.PAGE_SIZE);
		commonStatusList = Constant.getCommonStatus2();
		// 处理权限
		getAuthority(empId,Constant.F0050002);
	}
	//---------------------- 绩效考核题目管理------------------

	//---------------------- 绩效考核计划管理(部门考评用) ------------------
	// 绩效考核计划新增、编辑页面初始化
	public String examPlan2AddInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		param1 = "";
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Exam Plan Information Page Init(Department)");
			//----------------------------Operation History------------------
			// 处理权限
			getAuthority(empNum,Constant.F0051001);
			getMenuAuthority(empNum,Constant.SYSTEM_NUM_PERFORMANCE);
			
			if(fun0051001.equals("0")){
				return "noAuthority";
			}
			
			if(pageType != null){
				//commonMonthList = Constant.getMonthInfoList();
				commonSeasonList = Constant.getSeasonInfoList();
				commonDepartmentList = UtilCommon.getDepartmentList();
				
				if(pageType.equals("edit")){
					if(commonSeq != null && !commonSeq.equals("")){
						commonStatusList = Constant.getCommonStatus2();
						mp8002 = serviceMP8002.findById(commonSeq);
						commonStatus= mp8002.getMP8002_STATUS();
						param4 = mp8002.getMP8002_MONTH();
						param3 = mp8002.getMP8002_DEPARTMENT_NUM();
						
						examItemsInfo2 = serviceMP8003.findByProperty("MP8003_MASTER_KEY", commonSeq);
						relatedDepartmentListInfo = serviceMP8004.findByProperty("MP8004_PLAN_SEQ", commonSeq);
					}else{
						return "error";
					}
				}else if(pageType.equals("add")){
					// nothing to do
					commonSeq = Constant.generateSeq();
				}else{
					return "error";
				}
			}else{
				return "error";
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
	// 绩效考核题目管理页面初始化
	public String examPlan2InfoMngInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Exam Plan Information Management Page Init(Department)");
			//----------------------------Operation History------------------
			getMenuAuthority(empNum,Constant.SYSTEM_NUM_PERFORMANCE);
			
			if(fun0051002.equals("0")){
				return "noAuthority";
			}
			
			Map<String,String> propMap = new HashMap<String,String>();
			propMap.put("PLAN_NUM", commonSeq);
			propMap.put("PLAN_TITLE", param1);
			propMap.put("PLAN_STATUS", param2);
			propMap.put("PLAN_YEAR", param3);
			propMap.put("PLAN_MONTH", param4);
			
			getExamPlanInfoByPage3(propMap, 1,empNum);

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
	// 保存绩效考核题目信息
	public String examPlan2InfoSave(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Exam Plan Information Save(Department)");
			//----------------------------Operation History------------------
			
			if(pageType.equals("add")){
				mp8002.setMP8002_STATUS("1");
				mp8002.setMP8002_CREATOR(empNum);
				//mp8002.setMP8002_SEQ(Constant.generateSeq());
				mp8002.setMP8002_SEQ(commonSeq);
				mp8002.setMP8002_MONTH(param4);
				mp8002.setMP8002_DEPARTMENT_NUM(param3);
				if(param2!=null && !param2.equals("")){
					mp8002.setMP8002_EXECUTE_STATUS("1");
				}else{
					mp8002.setMP8002_EXECUTE_STATUS("0");
				}
				mp8002.setMP8002_FINISH_STATUS("0");
				
				if(mp8002.getMP8002_TITLE() == null || mp8002.getMP8002_TITLE().equals("")){
					addFieldError("mp8002.MP8002_TITLE","Title cannot be empty");
					return INPUT;
				}
				
				serviceMP8002.save(mp8002);
				addFieldError("errMsg","数据保存成功");
				// 记录操作日志
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Exam Plan Information, Exam Question:{" + mp8002.getMP8002_TITLE() + "}");
				
				mp8002 = new MP8002();
				//commonMonthList = Constant.getMonthInfoList();
				commonSeasonList = Constant.getSeasonInfoList();
				return INPUT;
			}else if(pageType.equals("edit")){
				MP8002 mp82 = serviceMP8002.findById(commonSeq);
				mp82.setMP8002_TITLE(mp8002.getMP8002_TITLE()); // 标题
				mp82.setMP8002_YEAR(mp8002.getMP8002_YEAR()); // 年
				mp82.setMP8002_MONTH(param4); // 月
				mp82.setMP8002_COMMENT(mp8002.getMP8002_COMMENT()); // 备注
				mp82.setMP8002_STATUS(commonStatus); // 状态
				mp82.setMP8002_DEPARTMENT_NUM(param3);
				if(param2!=null && !param2.equals("")){
					mp82.setMP8002_EXECUTE_STATUS("1");
				}
				
				serviceMP8002.update(mp82);
				// 记录操作日志
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Edit Exam Plan Information, Exam Question:{" + mp8002.getMP8002_TITLE() + "}");
				
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				
				out.append("<script type='text/javascript'>");
				out.append("window.dialogArguments.document.getElementById('btnRefresh').click();");
				out.append("window.close();");
				out.append("</script>");
		        out.flush();
		        out.close();
				
				return INPUT;
			}else{
				return "error";
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
	// 绩效考核题目信息检索处理
	public String examPlan2InfoSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Exam Plan Information(Department)");
			//----------------------------Operation History------------------
			
			// 重新取得分页数据
			Map<String,String> propMap = new HashMap<String,String>();
			propMap.put("PLAN_NUM", commonSeq);
			propMap.put("PLAN_TITLE", param1);
			propMap.put("PLAN_STATUS", param2);
			propMap.put("PLAN_YEAR", param3);
			propMap.put("PLAN_MONTH", param4);
			pageNum = 1;
			
			getExamPlanInfoByPage3(propMap, pageNum,empNum);
			
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
	// 绩效考核题目信息分页处理
	public String examPlan2InfoPageClick(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Exam Plan Information Page Click");
			//----------------------------Operation History------------------
			
			// 重新取得分页数据
			Map<String,String> propMap = new HashMap<String,String>();
			propMap.put("PLAN_NUM", commonSeq);
			propMap.put("PLAN_TITLE", param1);
			propMap.put("PLAN_STATUS", param2);
			propMap.put("PLAN_YEAR", param3);
			propMap.put("PLAN_MONTH", param4);
			
			getExamPlanInfoByPage3(propMap, pageNum,empNum);
			
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
	// 绩效考核计划信息删除处理
	public String examPlan2InfoDelete(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{			
			if(commonSeq != null && !commonSeq.equals("")){
				MP8002 mp82 = serviceMP8002.findById(commonSeq);

				if(mp82 != null){
					//serviceMP8001.delete(mp71);不做物理删除，只须更新数据状态
					mp82.setMP8002_STATUS("0");
					serviceMP8002.update(mp82);
					
					//----------------------------Operation History------------------
					LogUtil logUtil = new LogUtil();
					logUtil.setServiceMP0011(serviceMP0011);
					logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Delete Exam Plan Information, Exam Question:{" + mp82.getMP8002_TITLE() + "}(Department)");
					//----------------------------Operation History------------------
				}
			}
			
			// 重新取得分页数据
			Map<String,String> propMap = new HashMap<String,String>();
			//propMap.put("PLAN_NUM", commonSeq);
			propMap.put("PLAN_NUM", "");
			propMap.put("PLAN_TITLE", param1);
			propMap.put("PLAN_STATUS", param2);
			propMap.put("PLAN_YEAR", param3);
			propMap.put("PLAN_MONTH", param4);
			
			getExamPlanInfoByPage3(propMap, pageNum,empNum);
			
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
	// 绩效考核计划信息删除处理
	public String examPlan2InfoCopy(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{			
			if(commonSeq != null && !commonSeq.equals("")){
				MP8002 mp82 = serviceMP8002.findById(commonSeq);

				if(mp82 != null){
					mp82.setMP8002_SEQ(Constant.generateSeq());
					mp82.setMP8002_EXECUTE_STATUS("0");
					mp82.setMP8002_FINISH_STATUS("0");
					mp82.setMP8002_STATUS("1");
					serviceMP8002.save(mp82);
					
					List<MP8003> mp83List = serviceMP8003.findByProperty("MP8003_MASTER_KEY", commonSeq);
					for(int i=0,j=mp83List.size(); i<j; i++){
						MP8003 mp83 = mp83List.get(i);
						mp83.setMP8003_SEQ(Constant.generateSeq() + UtilCommon.getTempSeq());
						mp83.setMP8003_MASTER_KEY(mp82.getMP8002_SEQ());
						serviceMP8003.save(mp83);
					}
					
					//----------------------------Operation History------------------
					LogUtil logUtil = new LogUtil();
					logUtil.setServiceMP0011(serviceMP0011);
					logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Copy Exam Plan Information, Exam Question:{" + mp82.getMP8002_TITLE() + "}(Department)");
					//----------------------------Operation History------------------
				}
			}
			
			// 重新取得分页数据
			Map<String,String> propMap = new HashMap<String,String>();
			//propMap.put("PLAN_NUM", commonSeq);
			propMap.put("PLAN_NUM", "");
			propMap.put("PLAN_TITLE", param1);
			propMap.put("PLAN_STATUS", param2);
			propMap.put("PLAN_YEAR", param3);
			propMap.put("PLAN_MONTH", param4);
			
			getExamPlanInfoByPage3(propMap, pageNum,empNum);
			
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
	// 生成员工绩效考核数据
	public String examPlan2InfoExecute(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		Map<String,String> propMap = new HashMap<String,String>();
		try{			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Exam Plan Execute(Department)");
			//----------------------------Operation History------------------

			MP8002 mp82 = serviceMP8002.findById(commonSeq);
			List<MP8003> mp83List = serviceMP8003.findByProperty("MP8003_MASTER_KEY", commonSeq);
			Map<String, MP1001> managerMap = UtilCommon.getAllManager(serviceMP1001);
			
			List<MP8004> mp84List = serviceMP8004.findByProperty("MP8004_PLAN_SEQ", commonSeq);
			
			String _month = "";
			if(mp82.getMP8002_MONTH().length() == 1){
				_month = "0" + mp82.getMP8002_MONTH();
			}
			else if(mp82.getMP8002_MONTH().length() == 2){
				_month = mp82.getMP8002_MONTH();
			}
			
			propMap.put("REVIEW_PERIOD", mp82.getMP8002_YEAR() + "-" + _month);
			propMap.put("EMP_DEP", mp82.getMP8002_DEPARTMENT_NUM());
			List<MP8005> mp8005List = serviceMP8005.findByPropertyByPage(propMap, -1, -1);
			if(mp8005List != null && mp8005List.size() > 0){
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				
				out.append("<script type='text/javascript'>");
				out.append("alert('The records of this month  have exist. Please do not submit the same action.');");
				out.append("</script>");
		        out.flush();
		        out.close();
		        
				return SUCCESS;
			}
			
			MP8005 mp85 = null;
			MP8006 mp86 = null;
			// 循环取得所有员工信息
			for(int i=0,j=mp84List.size(); i<j; i++){
				MP8004 mp84 = mp84List.get(i);
				
				mp85 = new MP8005();
				mp85.setMP8005_SEQ(Constant.generateSeq2() + "MM" + i + UtilCommon.getTempSeq()); // KEY
				mp85.setMP8005_DEPARTMENT(mp82.getMP8002_DEPARTMENT_NUM());
				//mp85.setMP8005_MANAGER("");
				mp85.setMP8005_REVIEW_PERIOD(mp82.getMP8002_YEAR() + "-" + _month);
				mp85.setMP8005_SCORES("0");
				mp85.setMP8005_COMMENT("");
				mp85.setMP8005_APPRAISER(mp84.getMP8004_DEPARTMENT_NUM());
				mp85.setMP8005_STATUS("1");
				
				if(managerMap.containsKey(mp82.getMP8002_DEPARTMENT_NUM())){
					MP1001 mp11Obj = managerMap.get(mp82.getMP8002_DEPARTMENT_NUM());
					mp85.setMP8005_MANAGER(mp11Obj.getMP1001_EMPLOYEE_NUM());
				}else{
					mp85.setMP8005_MANAGER("");
				}
				serviceMP8005.save(mp85);
				
				// 循环取得考核科目信息
				for(int m=0,n=mp83List.size(); m<n; m++){
					MP8003 mp83 = mp83List.get(m);
					
					mp86 = new MP8006();
					mp86.setMP8006_SEQ(Constant.generateSeq2() + "MS" + i + m + UtilCommon.getTempSeq());
					mp86.setMP8006_MASTER_KEY(mp85.getMP8005_SEQ());
					mp86.setMP8006_EXAM_CODE(mp83.getMP8003_EXAM_CODE());
					mp86.setMP8006_SCORES("");
					serviceMP8006.save(mp86);					
				}
			}

			// 重新取得分页数据
			propMap = new HashMap<String,String>();
			//propMap.put("PLAN_NUM", commonSeq);
			propMap.put("PLAN_NUM", "");
			propMap.put("PLAN_TITLE", param1);
			propMap.put("PLAN_STATUS", param2);
			propMap.put("PLAN_YEAR", param3);
			propMap.put("PLAN_MONTH", param4);
			
			getExamPlanInfoByPage3(propMap, pageNum,empNum);
			
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
	// 重新取得分页数据
	private void getExamPlanInfoByPage3(Map<String,String> propMap, int _pageNum, String empId){	
		examPlanInfoList3 = serviceMP8002.findByPropertyByPage(propMap, -1, -1);
		if( 0 == examPlanInfoList3.size()%Constant.PAGE_SIZE){
			pageCount = examPlanInfoList3.size()/Constant.PAGE_SIZE;
		}else{
			pageCount = examPlanInfoList3.size()/Constant.PAGE_SIZE + 1;
		}
		if(_pageNum > pageCount){
			_pageNum= pageCount;
		}
		examPlanInfoList3 = serviceMP8002.findByPropertyByPage(propMap, _pageNum, Constant.PAGE_SIZE);
		
		commonStatusList = Constant.getCommonStatus2();
		commonMonthList = Constant.getMonthInfoList();
		// 处理权限
		getAuthority(empId,Constant.F0051002);
	}

	// 计划考核题目管理页面初始化
	public String examQuestion2SelectMngInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Manage Exam Question For Exam Plan(Department)");
			//----------------------------Operation History------------------
			
			Map<String,String> propMap = new HashMap<String,String>();
			propMap.put("MASTER_KEY", commonSeq);
			getExamPlanInfoByPage4(propMap, 1);

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
	// 计划考核题目分页处理
	public String examQuestion2SelectMngPageClick(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		//String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Manage Exam Question For Exam Plan Page Click(Department)");
			//----------------------------Operation History------------------
			
			// 重新取得分页数据
			Map<String,String> propMap = new HashMap<String,String>();
			propMap.put("MASTER_KEY", commonSeq);
			getExamPlanInfoByPage4(propMap, pageNum);
			
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
	// 绩效考核题目信息删除处理
	public String examPlan2ItemInfoRefresh(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		//String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{			
			if(commonSeq != null && !commonSeq.equals("")){
				// 重新取得分页数据
				examItemsInfo2 = serviceMP8003.findByProperty("MP8003_MASTER_KEY", commonSeq);
				
				//----------------------------Operation History------------------
				LogUtil logUtil = new LogUtil();
				logUtil.setServiceMP0011(serviceMP0011);
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Refresh Exam Plan Selected Question Information(Department)");
				//----------------------------Operation History------------------
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
	// 绩效考核题目信息删除处理
	public String examPlan2EmpInfoRefresh(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		//String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{			
			if(commonSeq != null && !commonSeq.equals("")){
				// 重新取得分页数据
				relatedDepartmentListInfo = serviceMP8004.findByProperty("MP8004_PLAN_SEQ", commonSeq);
				
				//----------------------------Operation History------------------
				LogUtil logUtil = new LogUtil();
				logUtil.setServiceMP0011(serviceMP0011);
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Refresh Exam Plan Selected Employee Information(Department)");
				//----------------------------Operation History------------------
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
	// 计划考核题目信息保存处理
	public String examPlan2ItemInfoSave(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		String msg = "";
		try{
			if(param1 != null && !param1.equals("")){
				
				StringBuffer search = new StringBuffer();
				search.append(" and mp8003.MP8003_MASTER_KEY = '" + commonSeq + "' ");
				search.append(" and mp8003.MP8003_EXAM_CODE = '" + param1 + "' ");
				
				List<MP8003> mp83List = serviceMP8003.findDataBySelfDefined(search.toString(), "", -1, -1);
				
				if(mp83List.size() ==0 ){
					String key = Constant.generateSeq() + UtilCommon.getTempSeq();
					mp8003.setMP8003_SEQ(key);
					mp8003.setMP8003_MASTER_KEY(commonSeq);
					mp8003.setMP8003_EXAM_CODE(param1);
					mp8003.setMP8003_WEIGHTAGE("");
					
					serviceMP8003.save(mp8003);
					msg = "Save Success";
				}else{
					msg = "The item has been selected, Please select another item.";
				}
				//----------------------------Operation History------------------
				LogUtil logUtil = new LogUtil();
				logUtil.setServiceMP0011(serviceMP0011);
				logUtil.writeOperationLog(empNum,employeeData.getMP1001_PREFERED_NAME(),"Save Exam Plan Selected Question Information, Exam Question:{" + mp8003.getMP8003_EXAM_CODE() + "}(Department)");
				//----------------------------Operation History------------------
			}
			
			HttpServletResponse response = ServletActionContext.getResponse();
	        response.setCharacterEncoding("utf-8"); // 务必，防止返回文件名是乱码
	        PrintWriter out = response.getWriter();
			
			out.append(msg);

	        out.flush();
	        out.close();
			
			return null;
		}catch(Exception ex){
			log.info(ex.getMessage());
			if(session.containsKey("ERR_MSG")){
				session.remove("ERR_MSG");
				session.put("ERR_MSG", ex.getMessage());
			}
			return "error";
		}
	}
	// 计划考核题目信息删除处理
	public String examPlan2ItemInfoDelete(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{
			if(param1 != null && !param1.equals("")){
				MP8003 mp83 = serviceMP8003.findById(param1);
				
				if(mp83 != null){
					serviceMP8003.delete(mp83);
					//----------------------------Operation History------------------
					LogUtil logUtil = new LogUtil();
					logUtil.setServiceMP0011(serviceMP0011);
					logUtil.writeOperationLog(empNum,employeeData.getMP1001_PREFERED_NAME(),"Delete Exam Plan Selected Question Information, Exam Question:{" + mp8003.getMP8003_EXAM_CODE() + "}(Department)");
					//----------------------------Operation History------------------
				}
			}
			
			examItemsInfo2 = serviceMP8003.findByProperty("MP8003_MASTER_KEY", commonSeq);
			
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
	// 计划考核题目信息删除处理
	public String examPlan2EmpInfoDelete(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{
			if(param1 != null && !param1.equals("")){
				MP8004 mp84 = serviceMP8004.findById(param1);
				
				if(mp84 != null){
					serviceMP8004.delete(mp84);
					//----------------------------Operation History------------------
					LogUtil logUtil = new LogUtil();
					logUtil.setServiceMP0011(serviceMP0011);
					logUtil.writeOperationLog(empNum,employeeData.getMP1001_PREFERED_NAME(),"Delete Exam Plan Selected Employee Information, Employee:{" + mp84.getMP8004_DEPARTMENT_NUM() + "}(Department)");
					//----------------------------Operation History------------------
				}
			}
			relatedDepartmentListInfo = serviceMP8004.findByProperty("MP8004_PLAN_SEQ", commonSeq);
			
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
	// 重新取得分页数据
	private void getExamPlanInfoByPage4(Map<String,String> propMap, int _pageNum){	
		examPlanInfoList4 = serviceMP8003.findByPropertyByPage(propMap, -1, -1);
		if( 0 == examPlanInfoList4.size()%Constant.PAGE_SIZE){
			pageCount = examPlanInfoList4.size()/Constant.PAGE_SIZE;
		}else{
			pageCount = examPlanInfoList4.size()/Constant.PAGE_SIZE + 1;
		}
		if(_pageNum > pageCount){
			_pageNum= pageCount;
		}
		examPlanInfoList4 = serviceMP8003.findByPropertyByPage(propMap, _pageNum, Constant.PAGE_SIZE);
	}
	
	//---------------------- 绩效考核计划管理------------------

	//---------------------- 绩效考核月度------------------
	// 绩效考核题目新增、编辑页面初始化
	public String examEvaluationMonthly2AddInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Fill In Performance Evaluation Form Page Init(Department)");
			//----------------------------Operation History------------------
			// 处理权限
			getAuthority(empNum,Constant.F0052002);
			Map<String,String> departmentMap = UtilCommon.getDepartmentList();

			if(pageType != null){				
				if(pageType.equals("edit")){
					if(commonSeq != null && !commonSeq.equals("")){
						mp8005 = serviceMP8005.findById(commonSeq);
						
						if(departmentMap.containsKey(mp8005.getMP8005_DEPARTMENT())){
							mp8005.setMP8005_DEPARTMENT_NAME(departmentMap.get(mp8005.getMP8005_DEPARTMENT()));
						}
						if(departmentMap.containsKey(mp8005.getMP8005_APPRAISER())){
							mp8005.setMP8005_APPRAISER_NAME(departmentMap.get(mp8005.getMP8005_APPRAISER()));
						}
						
						// 解析Manager信息
						Map<String, MP1001> managerMap = UtilCommon.getAllManager(serviceMP1001);
						if(managerMap.containsKey(mp8005.getMP8005_DEPARTMENT())){
							MP1001 mp11Obj = managerMap.get(mp8005.getMP8005_DEPARTMENT());
							mp8005.setMP8005_MANAGER_NAME(mp11Obj.getMP1001_PREFERED_NAME());
						}
						
						evaluationMonthly2ScoresList = serviceMP8006.findByProperty("MP8006_MASTER_KEY", commonSeq);
						scoreLevelList = Constant.getDepartmentScoreLevel();
						
						
						
						/*for(int i=0,j=evaluationMonthly2ScoresList.size(); i<j; i++){
							MP8006 mp86 = evaluationMonthly2ScoresList.get(i);
							String examScores = "";
							if(mp86.getMP8006_SCORES() == null || mp86.getMP8006_SCORES().equals("")){
								examScores = "0";
							}else{
								examScores = mp86.getMP8006_SCORES();
							}
							
							switch (Integer.parseInt(examScores))   {      
							   case   1:
								   scoreArray1.add(mp86.getMP8006_EXAM_CODE());
							           break;
							   case   2:
								   scoreArray2.add(mp86.getMP8006_EXAM_CODE());
							           break;
							   case   3:
								   scoreArray3.add(mp86.getMP8006_EXAM_CODE());
							           break;
							   case   4:
								   scoreArray4.add(mp86.getMP8006_EXAM_CODE());
							           break;
							   case   5:
								   scoreArray5.add(mp86.getMP8006_EXAM_CODE());
							           break;
							}// End Switch
						}// End List*/
					}else{
						return "error";
					}
				}else if(pageType.equals("add")){
					// nothing to do
				}else{
					return "error";
				}
			}else{
				return "error";
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
	// 绩效考核题目管理页面初始化
	public String examEvaluationMonthly2InfoMngInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		String depNum = employeeData.getMP1001_DEPARTMENT_ID();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Performance Evaluation Form Management Page Init(Department)");
			//----------------------------Operation History------------------
			getMenuAuthority(empNum,Constant.SYSTEM_NUM_PERFORMANCE);
			getAuthority(empNum,Constant.F0052001); //do it first, in getExamEvaluationMonthly2InfoByPage() function, it will do again.
			if(A006008001001.equalsIgnoreCase("1")){
				commonDepartmentList2 = UtilCommon.getDepartmentList();
				depNum = null; //here need to limit the appraiser
			}
			else{
				commonDepartmentList2.put(employeeData.getMP1001_DEPARTMENT_ID(), employeeData.getMP1001_DEPARTMENT_NAME());
			}
			
			if(fun0052001.equals("0")){
				return "noAuthority";
			}
			
			
			Map<String,String> propMap = examEvaluationMonthly2InfoParameterSet(depNum);
			getExamEvaluationMonthly2InfoByPage(propMap, 1,empNum,depNum);
			
			commonSeasonList = Constant.getSeasonInfoList();
			

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
	// 保存绩效考核题目信息
	public String examEvaluationMonthly2InfoSave(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);

		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Performance Evaluation Form Save(Department)");
			//----------------------------Operation History------------------
			
			if(pageType.equals("add")){
				//errMsg = "数据保存成功";
				addFieldError("errMsg","数据保存成功");
				return INPUT;
			}else if(pageType.equals("edit")){
				/*if(scoreArray1 == null) {scoreArray1 = new ArrayList<String>();}
				if(scoreArray2 == null) {scoreArray2 = new ArrayList<String>();}
				if(scoreArray3 == null) {scoreArray3 = new ArrayList<String>();}
				if(scoreArray4 == null) {scoreArray4 = new ArrayList<String>();}
				if(scoreArray5 == null) {scoreArray5 = new ArrayList<String>();}
				
				double averageScroes = 0.0;
				// 保存考核分数
				serviceMP8006.updateDataBySelf(scoreArray1, scoreArray2, scoreArray3, scoreArray4, scoreArray5, commonSeq);
				
				if(scoreArray1.size() == 0 && scoreArray2.size() == 0 && scoreArray3.size() == 0 && scoreArray4.size() == 0 && scoreArray5.size() == 0){
					averageScroes = 0.0;
				}else{
					int totalRecords = scoreArray1.size() + scoreArray2.size() + scoreArray3.size() + scoreArray4.size() + scoreArray5.size();
					int totalScores = scoreArray1.size()*1 + scoreArray2.size()*2 + scoreArray3.size()*3 + scoreArray4.size()*4 + scoreArray5.size()*5;
					averageScroes = Arith.div(Double.parseDouble(String.valueOf(totalScores)), Double.parseDouble(String.valueOf(totalRecords)), 1);
				}
				
				MP8005 mp85 = serviceMP8005.findById(commonSeq);
				mp85.setMP8005_COMMENT(mp8005.getMP8005_COMMENT());
				mp85.setMP8005_SCORES(Double.toString(averageScroes));
				//mp85.setMP8005_APPRAISER(mp8005.getMP8005_APPRAISER());
				serviceMP8005.update(mp85);
				*/
				
				//convert json to map object
				ObjectMapper mapper = new ObjectMapper();
				Map<String, String> map = new HashMap<String, String>();
				

				map = mapper.readValue(selectMap_In, new TypeReference<HashMap<String, String>>(){});
				//count average score.
				MP8005 mp85 = serviceMP8005.findById(commonSeq);
				mp85.setMP8005_COMMENT(Mp8005comment_In);
				double totalScore = 0;
				List<MP8006> mp86List = serviceMP8006.findByProperty("MP8006_MASTER_KEY", commonSeq);
				int i, j;
				for(i = 0, j = mp86List.size(); i < j; i++){
					if(map.containsKey(mp86List.get(i).getMP8006_SEQ())){
						String val = map.get(mp86List.get(i).getMP8006_SEQ());
						mp86List.get(i).setMP8006_SCORES(val);
						serviceMP8006.update(mp86List.get(i));
						
						totalScore += Integer.parseInt(val); 
					}
				}
				double averageScore = totalScore / i;
				mp85.setMP8005_SCORES(Double.toString(averageScore));
				serviceMP8005.update(mp85);	
				//for clause store all scores 
				
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				
				//out.append("<script type='text/javascript'>");
				out.append("window.dialogArguments.document.getElementById('btnRefresh').click();");
				out.append("window.close();");
				//out.append("</script>");
		        out.flush();
		        out.close();
				
				return INPUT;
			}else{
				return "error";
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
	// 绩效考核题目信息检索处理
	public String examEvaluationMonthly2InfoSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		String depNum = employeeData.getMP1001_DEPARTMENT_ID();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Performance Evaluation Form(Department)");
			//----------------------------Operation History------------------
			
			getAuthority(empNum,Constant.F0052001); //do it first, in getExamEvaluationMonthly2InfoByPage() function, it will do again.
			if(A006008001001.equalsIgnoreCase("1")){
				commonDepartmentList2 = UtilCommon.getDepartmentList();
			}
			else{
				commonDepartmentList2.put(employeeData.getMP1001_DEPARTMENT_ID(), employeeData.getMP1001_DEPARTMENT_NAME());
			}
			if(!param5.equalsIgnoreCase("") && !param5.equalsIgnoreCase("undifined") && !param5.equalsIgnoreCase(null)){
				depNum = param5; //here need to limit the appraiser, param5 is the select value
			}
			else{
				depNum = "-1";
			}

			// 重新取得分页数据
			Map<String,String> propMap = examEvaluationMonthly2InfoParameterSet(depNum);
			pageNum = 1;
			getExamEvaluationMonthly2InfoByPage(propMap, pageNum,empNum,depNum);
			
			commonSeasonList = Constant.getSeasonInfoList();
			
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
	// 绩效考核题目信息分页处理
	public String examEvaluationMonthly2InfoPageClick(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		String depNum = employeeData.getMP1001_DEPARTMENT_ID();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Performance Evaluation Form Page Click(Department)");
			//----------------------------Operation History------------------
			
			// 重新取得分页数据
			Map<String,String> propMap = examEvaluationMonthly2InfoParameterSet(depNum);
			getExamEvaluationMonthly2InfoByPage(propMap, pageNum,empNum,depNum);
			
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
	// 绩效考核题目信息删除处理
	public String examEvaluationMonthly2InfoDelete(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		String depNum = employeeData.getMP1001_DEPARTMENT_ID();
		try{
			if(commonSeq != null && !commonSeq.equals("")){
				MP8005 mp85 = serviceMP8005.findById(commonSeq);

				if(mp85 != null){
					//serviceMP7001.delete(mp71);不做物理删除，只须更新数据状态
					mp85.setMP8005_STATUS("0");
					serviceMP8005.update(mp85);
					
					//----------------------------Operation History------------------
					LogUtil logUtil = new LogUtil();
					logUtil.setServiceMP0011(serviceMP0011);
					logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Delete Performance Evaluation Form, SEQ:{" + mp85.getMP8005_SEQ() + "}(Department)");
					//----------------------------Operation History------------------
				}
			}
			
			// 重新取得分页数据
			Map<String,String> propMap = examEvaluationMonthly2InfoParameterSet(depNum);
			getExamEvaluationMonthly2InfoByPage(propMap, pageNum,empNum,depNum);
			
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
	// 重新取得分页数据
	private void getExamEvaluationMonthly2InfoByPage(Map<String,String> propMap, int _pageNum, String empId, String departmentID){	
		// 处理权限
		getAuthority(empId,Constant.F0052001);
		if(optDepartment.equals("1")){
			propMap.put("DEPARTMENT",departmentID);
		}
		if(optAll.equals("1")){
			propMap.put("AUT_ALL", "1");
		}
		
		evaluationMonthly2InfoList = serviceMP8005.findByPropertyByPage(propMap, -1, -1);
		if( 0 == evaluationMonthly2InfoList.size()%Constant.PAGE_SIZE){
			pageCount = evaluationMonthly2InfoList.size()/Constant.PAGE_SIZE;
		}else{
			pageCount = evaluationMonthly2InfoList.size()/Constant.PAGE_SIZE + 1;
		}
		if(_pageNum > pageCount){
			_pageNum= pageCount;
		}
		if(_pageNum == 0){
			_pageNum = 1;
		}
		evaluationMonthly2InfoList = serviceMP8005.findByPropertyByPage(propMap, _pageNum, Constant.PAGE_SIZE);
		Map<String, String> depMap = UtilCommon.getDepartmentList();
		for(MP8005 mp85 : evaluationMonthly2InfoList){
			if(depMap.containsKey(mp85.getMP8005_APPRAISER())){
				mp85.setMP8005_APPRAISER_NAME(depMap.get(mp85.getMP8005_APPRAISER()));
			}else{
				mp85.setMP8005_APPRAISER_NAME("-");
			}
		}
		
		commonStatusList = Constant.getCommonStatus2();
		commonDepartmentList = UtilCommon.getDepartmentList();
	}
    // 参数设置
	private Map<String,String> examEvaluationMonthly2InfoParameterSet(String depNum){
		Map<String,String> propMap = new HashMap<String,String>();
		//propMap.put("EMP_NUM", param1);
		//propMap.put("EVL_STATUS", param4);
		String period_param = param2 + "-" + param4;
		if(period_param.equalsIgnoreCase("null-null") || period_param.equalsIgnoreCase("--1")){
			period_param = null;
		}
		propMap.put("REVIEW_PERIOD", period_param);
		propMap.put("EMP_DEP", param3);
		propMap.put("APPRAISER", depNum);
		
		return propMap;
	}
	
	
	public String examEvaluationMonthly2ReportMngInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		//String depNum = employeeData.getMP1001_DEPARTMENT_ID();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(empNum,employeeData.getMP1001_PREFERED_NAME(),"Performance Evaluation Form Report Page Init(Department)");
			//----------------------------Operation History------------------
			getMenuAuthority(empNum,Constant.SYSTEM_NUM_PERFORMANCE);
			getAuthority(empNum,Constant.F0053001);
			commonDepartmentList = UtilCommon.getDepartmentList();
			
			commonSeasonList = Constant.getSeasonInfoList();
			
			if(fun0053001.equals("0")){
				return "noAuthority";
			}
			if(A006009001001.equals("0")){
				commonDepartmentList.clear();
				commonDepartmentList.put(employeeData.getMP1001_DEPARTMENT_ID(), employeeData.getMP1001_DEPARTMENT_NAME());
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
	public String examEvaluationMonthly2ReportMngSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		//String depNum = employeeData.getMP1001_DEPARTMENT_ID();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(empNum,employeeData.getMP1001_PREFERED_NAME(),"Performance Evaluation Form Report Page Search(Department)");
			//----------------------------Operation History------------------
			commonDepartmentList = UtilCommon.getDepartmentList();
			commonSeasonList = Constant.getSeasonInfoList();
			getAuthority(empNum,Constant.F0053001);
			
			if(param2.equals("") || param3.equals("")){
				addFieldError("errMsg","please fill all request blank");
				return SUCCESS;
			}
			
			String period_param = param2 + "-" + param3;
			if(period_param.equalsIgnoreCase("null-null")){
				period_param = null;
			}
			evaluationMonthly2InfoList = serviceMP8005.getReportList(period_param, param1);
			
			if(A006009001001.equals("0")){
				commonDepartmentList.clear();
				commonDepartmentList.put(employeeData.getMP1001_DEPARTMENT_ID(), employeeData.getMP1001_DEPARTMENT_NAME());
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
	public String examEvaluationMonthly2Detail(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		//String depNum = employeeData.getMP1001_DEPARTMENT_ID();
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(empNum,employeeData.getMP1001_PREFERED_NAME(),"Performance Evaluation Form Report Page Detail(Department){" + param1 + "|" + param2 + "}");
			//----------------------------Operation History------------------
			Map<String,String> propMap = new HashMap<String,String>();
			propMap.put("EMP_DEP", param1);
			propMap.put("REVIEW_PERIOD", param2);
			evaluationMonthlyDetailInfoList = serviceMP8005.findByPropertyByPage(propMap, -1, -1);
			
			Map<String, String> depMap = UtilCommon.getDepartmentList();
			for(MP8005 mp85 : evaluationMonthlyDetailInfoList){
				if(depMap.containsKey(mp85.getMP8005_APPRAISER())){
					mp85.setMP8005_APPRAISER_NAME(depMap.get(mp85.getMP8005_APPRAISER()));
				}else{
					mp85.setMP8005_APPRAISER_NAME("-");
				}
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
	
	//---------------------- 绩效考核月度------------------
	public String createMonthlyAppraisalFormInfoExcelDocument2(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(empNum,employeeData.getMP1001_PREFERED_NAME(),"Create Appraisal Information Excel Document(Department)");
			//----------------------------Operation History------------------
			// 处理权限
			getAuthority(empNum,Constant.F0052001);
			
			// 新建EXCEL工作表
			Workbook wb = ExcelUtil.CreateHSSFWorkBook();
			// 新建一个SHEET页面
			Sheet sheet = ExcelUtil.CreateSheet(wb, "Appraisal Scores(Department)");
			// 设置SHEET页面属性
			ExcelUtil.SetSheetPropertyHSSF(sheet);
			// 获取预定的样式
			Map<String, CellStyle> styles = ExcelUtil.CreateStyles(wb);
			
			// Header标题
			String[] titles = {"ID", "DEPARTMENT","MAMAGER","APPRAISER","REVIEW PERIOD","SCORES"};
			// 生成标题行
			float rowHeight = 27f;
			ExcelUtil.CreateHeadRow(sheet, titles, rowHeight, styles);
	        // 冻结第一行
	        sheet.createFreezePane(0, 1);
	        
	        int[] cellsWidth = {5,20,20,20,20,20};
	        // 设置单元格的宽度
	        ExcelUtil.SetCellsWidth(sheet, cellsWidth);
	        
	      //---------------------------主报表-----------------------------------------------------------------------------------------
	        // 取得报表数据
	        fileName = "performanceAppraisalScores(Department).xls";
	        String sortStr = " order by mp8005.MP8005_DEPARTMENT ";
	        StringBuffer searchStr = new StringBuffer();
	        searchStr.append(" and mp8005.MP8005_STATUS = '1'  ");
	        
	        if(optAll.equals("1")){
				// Department
				if(param3 != null && !param3.equals("") && !param3.equals("-1")){
					searchStr.append(" and mp8005.MP8005_DEPARTMENT = '" + param3 + "' ");
				}
	        }else if(optDepartment.equals("1")){
				searchStr.append(" and mp8005.MP8005_DEPARTMENT = '" + param3 + "' ");
			}else{
				return "noAuthority"; 
			}

			// Review Period
			//if(param2 != null && !param2.equals("") && !param2.equals("undefined")){
	        if(reviewPeriod!= null && !reviewPeriod.equals("") && !reviewPeriod.equals("undefined")){
				searchStr.append(" and mp8005.MP8005_REVIEW_PERIOD = '" + reviewPeriod + "' ");
			}
	        
	        int count;
	        String[] datas;
	        List<MP8005> mp85List = serviceMP8005.findDataBySelfDefined(searchStr.toString(), sortStr, -1, -1);
	        if(mp85List == null || mp85List.size() == 0){
	        	datas = new String[1];
	        	datas[0] = "No Data";      	
	        	
	        	ExcelUtil.SetCellsValue(1, sheet, styles, datas);
	        	sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 8));//第二行 二到三列
	        }else{
	    		Map<String, String> depMap = UtilCommon.getDepartmentList();
				Map<String, MP1001> managerMap = UtilCommon.getAllManager(serviceMP1001);
		        for(int i=0,j=mp85List.size(); i<j; i++){
		        	MP8005 mp8005Obj = mp85List.get(i);
	    			if(depMap.containsKey(mp8005Obj.getMP8005_APPRAISER())){
	    				mp8005Obj.setMP8005_APPRAISER_NAME(depMap.get(mp8005Obj.getMP8005_APPRAISER()));
	    			}else{
	    				mp8005Obj.setMP8005_APPRAISER_NAME("-");
	    			}
					if(managerMap.containsKey(mp8005Obj.getMP8005_DEPARTMENT())){
						MP1001 mp11Obj = managerMap.get(mp8005Obj.getMP8005_DEPARTMENT());
						mp8005Obj.setMP8005_MANAGER_NAME(mp11Obj.getMP1001_PREFERED_NAME());
					}
		        	
		        	count = i + 1;
		        	datas = new String[6];
		        	datas[0] = String.valueOf(count);
		        	datas[1] = mp8005Obj.getMP8005_DEPARTMENT_NAME();
		        	datas[2] = mp8005Obj.getMP8005_MANAGER_NAME();
		        	datas[3] = mp8005Obj.getMP8005_APPRAISER_NAME();
		        	datas[4] = mp8005Obj.getMP8005_REVIEW_PERIOD();
		        	datas[5] = mp8005Obj.getMP8005_SCORES();
		        	
		        	ExcelUtil.SetCellsValue(count, sheet, styles, datas);
		        }
	        }
	        
			// 生成Excel文件
	        String _path = ServletActionContext.getServletContext().getRealPath("/") + "temp\\" + fileName;
	        ExcelUtil.createExcelFile(_path, wb);

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
	public String createMonthlyAppraisalFormInfoExcelDocument3(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(empNum,employeeData.getMP1001_PREFERED_NAME(),"Create Appraisal Information report Excel Document(Department)");
			//----------------------------Operation History------------------
			// 处理权限
			getAuthority(empNum,Constant.F0053001);
			
			// 新建EXCEL工作表
			Workbook wb = ExcelUtil.CreateHSSFWorkBook();
			// 新建一个SHEET页面
			Sheet sheet = ExcelUtil.CreateSheet(wb, "Appraisal Scores(Department)");
			// 设置SHEET页面属性
			ExcelUtil.SetSheetPropertyHSSF(sheet);
			// 获取预定的样式 
			Map<String, CellStyle> styles = ExcelUtil.CreateStyles(wb);
			
			// Header标题
			String[] titles = {"ID", "DEPARTMENT","REVIEW PERIOD","SCORES"};
			// 生成标题行
			float rowHeight = 27f;
			ExcelUtil.CreateHeadRow(sheet, titles, rowHeight, styles);
	        // 冻结第一行
	        sheet.createFreezePane(0, 1);
	        
	        int[] cellsWidth = {5,20,20,20};
	        // 设置单元格的宽度
	        ExcelUtil.SetCellsWidth(sheet, cellsWidth);
	        
	      //---------------------------主报表-----------------------------------------------------------------------------------------
	        // 取得报表数据
	        fileName = "performanceAppraisalScores(Department).xls";
	        int count;
	        String[] datas;
	        List<MP8005> mp85List = serviceMP8005.getReportList(reviewPeriod, param1);
	        if(mp85List == null || mp85List.size() == 0){
	        	datas = new String[1];
	        	datas[0] = "No Data";      	
	        	
	        	ExcelUtil.SetCellsValue(1, sheet, styles, datas);
	        	sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 8));//第二行 二到三列
	        }else{
		        for(int i=0,j=mp85List.size(); i<j; i++){
		        	MP8005 mp8005Obj = mp85List.get(i);	        	
		        	count = i + 1;
		        	datas = new String[4];
		        	datas[0] = String.valueOf(count);
		        	datas[1] = mp8005Obj.getMP8005_DEPARTMENT_NAME();
		        	datas[2] = mp8005Obj.getMP8005_REVIEW_PERIOD();
		        	datas[3] = mp8005Obj.getMP8005_SCORES();
		        	
		        	ExcelUtil.SetCellsValue(count, sheet, styles, datas);
		        }
	        }
	        
			// 生成Excel文件
	        String _path = ServletActionContext.getServletContext().getRealPath("/") + "temp\\" + fileName;
	        ExcelUtil.createExcelFile(_path, wb);

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
	 * @return the log
	 */
	public static Log getLog() {
		return log;
	}	/**
	 * @return the serviceMP7001
	 */
	public IMP7001Service getServiceMP7001() {
		return serviceMP7001;
	}
	/**
	 * @param serviceMP7001 the serviceMP7001 to set
	 */
	public void setServiceMP7001(IMP7001Service serviceMP7001) {
		this.serviceMP7001 = serviceMP7001;
	}
	/**
	 * @return the serviceMP7002
	 */
	public IMP7002Service getServiceMP7002() {
		return serviceMP7002;
	}
	/**
	 * @param serviceMP7002 the serviceMP7002 to set
	 */
	public void setServiceMP7002(IMP7002Service serviceMP7002) {
		this.serviceMP7002 = serviceMP7002;
	}
	/**
	 * @return the serviceMP7003
	 */
	public IMP7003Service getServiceMP7003() {
		return serviceMP7003;
	}
	/**
	 * @param serviceMP7003 the serviceMP7003 to set
	 */
	public void setServiceMP7003(IMP7003Service serviceMP7003) {
		this.serviceMP7003 = serviceMP7003;
	}
	/**
	 * @return the serviceMP7004
	 */
	public IMP7004Service getServiceMP7004() {
		return serviceMP7004;
	}
	/**
	 * @param serviceMP7004 the serviceMP7004 to set
	 */
	public void setServiceMP7004(IMP7004Service serviceMP7004) {
		this.serviceMP7004 = serviceMP7004;
	}
	/**
	 * @return the serviceMP7005
	 */
	public IMP7005Service getServiceMP7005() {
		return serviceMP7005;
	}
	/**
	 * @param serviceMP7005 the serviceMP7005 to set
	 */
	public void setServiceMP7005(IMP7005Service serviceMP7005) {
		this.serviceMP7005 = serviceMP7005;
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
	}	/**
	 * @return the serviceAC0008
	 */
	public IAC0008Service getServiceAC0008() {
		return serviceAC0008;
	}	/**
	 * @param serviceAC0008 the serviceAC0008 to set
	 */
	public void setServiceAC0008(IAC0008Service serviceAC0008) {
		this.serviceAC0008 = serviceAC0008;
	}	/**
	 * @return the serviceAC0009
	 */
	public IAC0009Service getServiceAC0009() {
		return serviceAC0009;
	}	/**
	 * @param serviceAC0009 the serviceAC0009 to set
	 */
	public void setServiceAC0009(IAC0009Service serviceAC0009) {
		this.serviceAC0009 = serviceAC0009;
	}
	/**
	 * @return the mp7001
	 */
	public MP7001 getMp7001() {
		return mp7001;
	}
	/**
	 * @param mp7001 the mp7001 to set
	 */
	public void setMp7001(MP7001 mp7001) {
		this.mp7001 = mp7001;
	}
	/**
	 * @return the mp7002
	 */
	public MP7002 getMp7002() {
		return mp7002;
	}
	/**
	 * @param mp7002 the mp7002 to set
	 */
	public void setMp7002(MP7002 mp7002) {
		this.mp7002 = mp7002;
	}
	/**
	 * @return the mp7003
	 */
	public MP7003 getMp7003() {
		return mp7003;
	}
	/**
	 * @param mp7003 the mp7003 to set
	 */
	public void setMp7003(MP7003 mp7003) {
		this.mp7003 = mp7003;
	}
	/**
	 * @return the mp7004
	 */
	public MP7004 getMp7004() {
		return mp7004;
	}
	/**
	 * @param mp7004 the mp7004 to set
	 */
	public void setMp7004(MP7004 mp7004) {
		this.mp7004 = mp7004;
	}
	/**
	 * @return the mp7005
	 */
	public MP7005 getMp7005() {
		return mp7005;
	}
	/**
	 * @param mp7005 the mp7005 to set
	 */
	public void setMp7005(MP7005 mp7005) {
		this.mp7005 = mp7005;
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
	 * @return the warnType
	 */
	public String getWarnType() {
		return warnType;
	}
	/**
	 * @param warnType the warnType to set
	 */
	public void setWarnType(String warnType) {
		this.warnType = warnType;
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
	 * @return the param1
	 */
	public String getParam1() {
		return param1;
	}
	/**
	 * @param param1 the param1 to set
	 */
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	/**
	 * @return the param2
	 */
	public String getParam2() {
		return param2;
	}
	/**
	 * @param param2 the param2 to set
	 */
	public void setParam2(String param2) {
		this.param2 = param2;
	}
	/**
	 * @return the param3
	 */
	public String getParam3() {
		return param3;
	}
	/**
	 * @param param3 the param3 to set
	 */
	public void setParam3(String param3) {
		this.param3 = param3;
	}
	/**
	 * @return the param4
	 */
	public String getParam4() {
		return param4;
	}
	/**
	 * @param param4 the param4 to set
	 */
	public void setParam4(String param4) {
		this.param4 = param4;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	 * @return the commonStatusList
	 */
	public Map<String, String> getCommonStatusList() {
		return commonStatusList;
	}
	/**
	 * @param commonStatusList the commonStatusList to set
	 */
	public void setCommonStatusList(Map<String, String> commonStatusList) {
		this.commonStatusList = commonStatusList;
	}
	/**
	 * @return the commonStatus
	 */
	public String getCommonStatus() {
		return commonStatus;
	}
	/**
	 * @param commonStatus the commonStatus to set
	 */
	public void setCommonStatus(String commonStatus) {
		this.commonStatus = commonStatus;
	}
	/**
	 * @return the examQuestionInfoList
	 */
	public List<MP7001> getExamQuestionInfoList() {
		return examQuestionInfoList;
	}
	/**
	 * @param examQuestionInfoList the examQuestionInfoList to set
	 */
	public void setExamQuestionInfoList(List<MP7001> examQuestionInfoList) {
		this.examQuestionInfoList = examQuestionInfoList;
	}

	/**
	 * @return the employeeNum
	 */
	public String getEmployeeNum() {
		return employeeNum;
	}

	/**
	 * @param employeeNum the employeeNum to set
	 */
	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}

	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return the examPlanInfoList
	 */
	public List<MP7002> getExamPlanInfoList() {
		return examPlanInfoList;
	}
	/**
	 * @param examPlanInfoList the examPlanInfoList to set
	 */
	public void setExamPlanInfoList(List<MP7002> examPlanInfoList) {
		this.examPlanInfoList = examPlanInfoList;
	}
	/**
	 * @return the commonMonthList
	 */
	public Map<String, String> getCommonMonthList() {
		return commonMonthList;
	}
	/**
	 * @param commonMonthList the commonMonthList to set
	 */
	public void setCommonMonthList(Map<String, String> commonMonthList) {
		this.commonMonthList = commonMonthList;
	}
	/**
	 * @return the examPlanInfoList2
	 */
	public List<MP7003> getExamPlanInfoList2() {
		return examPlanInfoList2;
	}
	/**
	 * @param examPlanInfoList2 the examPlanInfoList2 to set
	 */
	public void setExamPlanInfoList2(List<MP7003> examPlanInfoList2) {
		this.examPlanInfoList2 = examPlanInfoList2;
	}
	/**
	 * @return the evaluationMonthlyInfoList
	 */
	public List<MP7004> getEvaluationMonthlyInfoList() {
		return evaluationMonthlyInfoList;
	}
	/**
	 * @param evaluationMonthlyInfoList the evaluationMonthlyInfoList to set
	 */
	public void setEvaluationMonthlyInfoList(List<MP7004> evaluationMonthlyInfoList) {
		this.evaluationMonthlyInfoList = evaluationMonthlyInfoList;
	}
	/**
	 * @return the commonDepartmentList
	 */
	public Map<String, String> getCommonDepartmentList() {
		return commonDepartmentList;
	}
	/**
	 * @param commonDepartmentList the commonDepartmentList to set
	 */
	public void setCommonDepartmentList(Map<String, String> commonDepartmentList) {
		this.commonDepartmentList = commonDepartmentList;
	}
	/**
	 * @return the evaluationMonthlyScoresList
	 */
	public List<MP7005> getEvaluationMonthlyScoresList() {
		return evaluationMonthlyScoresList;
	}
	/**
	 * @param evaluationMonthlyScoresList the evaluationMonthlyScoresList to set
	 */
	public void setEvaluationMonthlyScoresList(
			List<MP7005> evaluationMonthlyScoresList) {
		this.evaluationMonthlyScoresList = evaluationMonthlyScoresList;
	}
	/**
	 * @return the scoreArray1
	 */
	public List<String> getScoreArray1() {
		return scoreArray1;
	}
	/**
	 * @param scoreArray1 the scoreArray1 to set
	 */
	public void setScoreArray1(List<String> scoreArray1) {
		this.scoreArray1 = scoreArray1;
	}
	/**
	 * @return the scoreArray2
	 */
	public List<String> getScoreArray2() {
		return scoreArray2;
	}
	/**
	 * @param scoreArray2 the scoreArray2 to set
	 */
	public void setScoreArray2(List<String> scoreArray2) {
		this.scoreArray2 = scoreArray2;
	}
	/**
	 * @return the scoreArray3
	 */
	public List<String> getScoreArray3() {
		return scoreArray3;
	}
	/**
	 * @param scoreArray3 the scoreArray3 to set
	 */
	public void setScoreArray3(List<String> scoreArray3) {
		this.scoreArray3 = scoreArray3;
	}
	/**
	 * @return the scoreArray4
	 */
	public List<String> getScoreArray4() {
		return scoreArray4;
	}
	/**
	 * @param scoreArray4 the scoreArray4 to set
	 */
	public void setScoreArray4(List<String> scoreArray4) {
		this.scoreArray4 = scoreArray4;
	}
	/**
	 * @return the scoreArray5
	 */
	public List<String> getScoreArray5() {
		return scoreArray5;
	}
	/**
	 * @param scoreArray5 the scoreArray5 to set
	 */
	public void setScoreArray5(List<String> scoreArray5) {
		this.scoreArray5 = scoreArray5;
	}
	/**
	 * @return the mp7006
	 */
	public MP7006 getMp7006() {
		return mp7006;
	}
	/**
	 * @param mp7006 the mp7006 to set
	 */
	public void setMp7006(MP7006 mp7006) {
		this.mp7006 = mp7006;
	}
	/**
	 * @return the mp7007
	 */
	public MP7007 getMp7007() {
		return mp7007;
	}
	/**
	 * @param mp7007 the mp7007 to set
	 */
	public void setMp7007(MP7007 mp7007) {
		this.mp7007 = mp7007;
	}
	/**
	 * @return the evaluationYearlyInfoList
	 */
	public List<MP7006> getEvaluationYearlyInfoList() {
		return evaluationYearlyInfoList;
	}
	/**
	 * @param evaluationYearlyInfoList the evaluationYearlyInfoList to set
	 */
	public void setEvaluationYearlyInfoList(List<MP7006> evaluationYearlyInfoList) {
		this.evaluationYearlyInfoList = evaluationYearlyInfoList;
	}
	/**
	 * @return the serviceMP7006
	 */
	public IMP7006Service getServiceMP7006() {
		return serviceMP7006;
	}
	/**
	 * @param serviceMP7006 the serviceMP7006 to set
	 */
	public void setServiceMP7006(IMP7006Service serviceMP7006) {
		this.serviceMP7006 = serviceMP7006;
	}
	/**
	 * @return the serviceMP7007
	 */
	public IMP7007Service getServiceMP7007() {
		return serviceMP7007;
	}
	/**
	 * @param serviceMP7007 the serviceMP7007 to set
	 */
	public void setServiceMP7007(IMP7007Service serviceMP7007) {
		this.serviceMP7007 = serviceMP7007;
	}
	/**
	 * @return the evaluationYearlyScoresList
	 */
	public List<MP7007> getEvaluationYearlyScoresList() {
		return evaluationYearlyScoresList;
	}
	/**
	 * @param evaluationYearlyScoresList the evaluationYearlyScoresList to set
	 */
	public void setEvaluationYearlyScoresList(
			List<MP7007> evaluationYearlyScoresList) {
		this.evaluationYearlyScoresList = evaluationYearlyScoresList;
	}
	/**
	 * @return the examAppraiserInfoList
	 */
	public List<MP1001> getExamAppraiserInfoList() {
		return examAppraiserInfoList;
	}
	/**
	 * @param examAppraiserInfoList the examAppraiserInfoList to set
	 */
	public void setExamAppraiserInfoList(List<MP1001> examAppraiserInfoList) {
		this.examAppraiserInfoList = examAppraiserInfoList;
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
	 * @return the monthlyHistoryScoresList
	 */
	public List<MP7004> getMonthlyHistoryScoresList() {
		return monthlyHistoryScoresList;
	}
	/**
	 * @param monthlyHistoryScoresList the monthlyHistoryScoresList to set
	 */
	public void setMonthlyHistoryScoresList(List<MP7004> monthlyHistoryScoresList) {
		this.monthlyHistoryScoresList = monthlyHistoryScoresList;
	}
	/**
	 * @return the serviceMP0009
	 */
	public IMP0009Service getServiceMP0009() {
		return serviceMP0009;
	}
	/**
	 * @param serviceMP0009 the serviceMP0009 to set
	 */
	public void setServiceMP0009(IMP0009Service serviceMP0009) {
		this.serviceMP0009 = serviceMP0009;
	}
	/**
	 * @return the optExecute
	 */
	public String getOptExecute() {
		return optExecute;
	}
	/**
	 * @param optExecute the optExecute to set
	 */
	public void setOptExecute(String optExecute) {
		this.optExecute = optExecute;
	}
	/**
	 * @return the optReview
	 */
	public String getOptReview() {
		return optReview;
	}
	/**
	 * @param optReview the optReview to set
	 */
	public void setOptReview(String optReview) {
		this.optReview = optReview;
	}
	/**
	 * @return the examItemsInfo
	 */
	public List<MP7003> getExamItemsInfo() {
		return examItemsInfo;
	}
	/**
	 * @param examItemsInfo the examItemsInfo to set
	 */
	public void setExamItemsInfo(List<MP7003> examItemsInfo) {
		this.examItemsInfo = examItemsInfo;
	}
	/**
	 * @return the fun0040001
	 */
	public String getFun0040001() {
		return fun0040001;
	}
	/**
	 * @param fun0040001 the fun0040001 to set
	 */
	public void setFun0040001(String fun0040001) {
		this.fun0040001 = fun0040001;
	}
	/**
	 * @return the fun0040002
	 */
	public String getFun0040002() {
		return fun0040002;
	}
	/**
	 * @param fun0040002 the fun0040002 to set
	 */
	public void setFun0040002(String fun0040002) {
		this.fun0040002 = fun0040002;
	}
	/**
	 * @return the fun0041001
	 */
	public String getFun0041001() {
		return fun0041001;
	}
	/**
	 * @param fun0041001 the fun0041001 to set
	 */
	public void setFun0041001(String fun0041001) {
		this.fun0041001 = fun0041001;
	}
	/**
	 * @return the fun0041002
	 */
	public String getFun0041002() {
		return fun0041002;
	}
	/**
	 * @param fun0041002 the fun0041002 to set
	 */
	public void setFun0041002(String fun0041002) {
		this.fun0041002 = fun0041002;
	}
	/**
	 * @return the fun0041003
	 */
	public String getFun0041003() {
		return fun0041003;
	}
	/**
	 * @param fun0041003 the fun0041003 to set
	 */
	public void setFun0041003(String fun0041003) {
		this.fun0041003 = fun0041003;
	}
	/**
	 * @return the fun0042001
	 */
	public String getFun0042001() {
		return fun0042001;
	}
	/**
	 * @param fun0042001 the fun0042001 to set
	 */
	public void setFun0042001(String fun0042001) {
		this.fun0042001 = fun0042001;
	}
	/**
	 * @return the fun0043001
	 */
	public String getFun0043001() {
		return fun0043001;
	}
	/**
	 * @param fun0043001 the fun0043001 to set
	 */
	public void setFun0043001(String fun0043001) {
		this.fun0043001 = fun0043001;
	}
	/**
	 * @return the downLoadFileName
	 */
	public String getDownLoadFileName() {
		return downLoadFileName;
	}
	/**
	 * @param downLoadFileName the downLoadFileName to set
	 */
	public void setDownLoadFileName(String downLoadFileName) {
		this.downLoadFileName = downLoadFileName;
	}
	/**
	 * @return the employeeListInfo
	 */
	public List<MP7008> getEmployeeListInfo() {
		return employeeListInfo;
	}
	/**
	 * @param employeeListInfo the employeeListInfo to set
	 */
	public void setEmployeeListInfo(List<MP7008> employeeListInfo) {
		this.employeeListInfo = employeeListInfo;
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
	 * @return the departmentEmpList
	 */
	public Map<String, String> getDepartmentEmpList() {
		return departmentEmpList;
	}
	/**
	 * @param departmentEmpList the departmentEmpList to set
	 */
	public void setDepartmentEmpList(Map<String, String> departmentEmpList) {
		this.departmentEmpList = departmentEmpList;
	}
	/**
	 * @return the dEP_1
	 */
	public String getDEP_1() {
		return DEP_1;
	}
	/**
	 * @param dEP_1 the dEP_1 to set
	 */
	public void setDEP_1(String dEP_1) {
		DEP_1 = dEP_1;
	}
	/**
	 * @return the dEP_2
	 */
	public String getDEP_2() {
		return DEP_2;
	}
	/**
	 * @param dEP_2 the dEP_2 to set
	 */
	public void setDEP_2(String dEP_2) {
		DEP_2 = dEP_2;
	}
	/**
	 * @return the dEP_3
	 */
	public String getDEP_3() {
		return DEP_3;
	}
	/**
	 * @param dEP_3 the dEP_3 to set
	 */
	public void setDEP_3(String dEP_3) {
		DEP_3 = dEP_3;
	}
	/**
	 * @return the dEP_5
	 */
	public String getDEP_5() {
		return DEP_5;
	}
	/**
	 * @param dEP_5 the dEP_5 to set
	 */
	public void setDEP_5(String dEP_5) {
		DEP_5 = dEP_5;
	}
	/**
	 * @return the dEP_6
	 */
	public String getDEP_6() {
		return DEP_6;
	}
	/**
	 * @param dEP_6 the dEP_6 to set
	 */
	public void setDEP_6(String dEP_6) {
		DEP_6 = dEP_6;
	}
	/**
	 * @return the dEP_7
	 */
	public String getDEP_7() {
		return DEP_7;
	}
	/**
	 * @param dEP_7 the dEP_7 to set
	 */
	public void setDEP_7(String dEP_7) {
		DEP_7 = dEP_7;
	}
	/**
	 * @return the dEP_8
	 */
	public String getDEP_8() {
		return DEP_8;
	}
	/**
	 * @param dEP_8 the dEP_8 to set
	 */
	public void setDEP_8(String dEP_8) {
		DEP_8 = dEP_8;
	}
	/**
	 * @return the dEP_10
	 */
	public String getDEP_10() {
		return DEP_10;
	}
	/**
	 * @param dEP_10 the dEP_10 to set
	 */
	public void setDEP_10(String dEP_10) {
		DEP_10 = dEP_10;
	}
	/**
	 * @return the dEP_11
	 */
	public String getDEP_11() {
		return DEP_11;
	}
	/**
	 * @param dEP_11 the dEP_11 to set
	 */
	public void setDEP_11(String dEP_11) {
		DEP_11 = dEP_11;
	}
	/**
	 * @return the dEP_12
	 */
	public String getDEP_12() {
		return DEP_12;
	}
	/**
	 * @param dEP_12 the dEP_12 to set
	 */
	public void setDEP_12(String dEP_12) {
		DEP_12 = dEP_12;
	}
	/**
	 * @return the dEP_14
	 */
	public String getDEP_14() {
		return DEP_14;
	}
	/**
	 * @param dEP_14 the dEP_14 to set
	 */
	public void setDEP_14(String dEP_14) {
		DEP_14 = dEP_14;
	}
	/**
	 * @return the dEP_15
	 */
	public String getDEP_15() {
		return DEP_15;
	}
	/**
	 * @param dEP_15 the dEP_15 to set
	 */
	public void setDEP_15(String dEP_15) {
		DEP_15 = dEP_15;
	}
	/**
	 * @return the dEP_16
	 */
	public String getDEP_16() {
		return DEP_16;
	}
	/**
	 * @param dEP_16 the dEP_16 to set
	 */
	public void setDEP_16(String dEP_16) {
		DEP_16 = dEP_16;
	}
	/**
	 * @return the dEP_18
	 */
	public String getDEP_18() {
		return DEP_18;
	}
	/**
	 * @param dEP_18 the dEP_18 to set
	 */
	public void setDEP_18(String dEP_18) {
		DEP_18 = dEP_18;
	}
	/**
	 * @return the dEP_19
	 */
	public String getDEP_19() {
		return DEP_19;
	}
	/**
	 * @param dEP_19 the dEP_19 to set
	 */
	public void setDEP_19(String dEP_19) {
		DEP_19 = dEP_19;
	}
	/**
	 * @return the dEP_21
	 */
	public String getDEP_21() {
		return DEP_21;
	}
	/**
	 * @param dEP_21 the dEP_21 to set
	 */
	public void setDEP_21(String dEP_21) {
		DEP_21 = dEP_21;
	}
	/**
	 * @return the dEP_22
	 */
	public String getDEP_22() {
		return DEP_22;
	}
	/**
	 * @param dEP_22 the dEP_22 to set
	 */
	public void setDEP_22(String dEP_22) {
		DEP_22 = dEP_22;
	}
	/**
	 * @return the serviceMP8001
	 */
	public IMP8001Service getServiceMP8001() {
		return serviceMP8001;
	}
	/**
	 * @param serviceMP8001 the serviceMP8001 to set
	 */
	public void setServiceMP8001(IMP8001Service serviceMP8001) {
		this.serviceMP8001 = serviceMP8001;
	}
	/**
	 * @return the serviceMP8002
	 */
	public IMP8002Service getServiceMP8002() {
		return serviceMP8002;
	}
	/**
	 * @param serviceMP8002 the serviceMP8002 to set
	 */
	public void setServiceMP8002(IMP8002Service serviceMP8002) {
		this.serviceMP8002 = serviceMP8002;
	}
	/**
	 * @return the serviceMP8003
	 */
	public IMP8003Service getServiceMP8003() {
		return serviceMP8003;
	}
	/**
	 * @param serviceMP8003 the serviceMP8003 to set
	 */
	public void setServiceMP8003(IMP8003Service serviceMP8003) {
		this.serviceMP8003 = serviceMP8003;
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
	 * @return the serviceMP8005
	 */
	public IMP8005Service getServiceMP8005() {
		return serviceMP8005;
	}
	/**
	 * @param serviceMP8005 the serviceMP8005 to set
	 */
	public void setServiceMP8005(IMP8005Service serviceMP8005) {
		this.serviceMP8005 = serviceMP8005;
	}
	/**
	 * @return the serviceMP8006
	 */
	public IMP8006Service getServiceMP8006() {
		return serviceMP8006;
	}
	/**
	 * @param serviceMP8006 the serviceMP8006 to set
	 */
	public void setServiceMP8006(IMP8006Service serviceMP8006) {
		this.serviceMP8006 = serviceMP8006;
	}
	/**
	 * @return the examQuestion2InfoList
	 */
	public List<MP8001> getExamQuestion2InfoList() {
		return examQuestion2InfoList;
	}
	/**
	 * @param examQuestion2InfoList the examQuestion2InfoList to set
	 */
	public void setExamQuestion2InfoList(List<MP8001> examQuestion2InfoList) {
		this.examQuestion2InfoList = examQuestion2InfoList;
	}
	/**
	 * @return the mp8001
	 */
	public MP8001 getMp8001() {
		return mp8001;
	}
	/**
	 * @param mp8001 the mp8001 to set
	 */
	public void setMp8001(MP8001 mp8001) {
		this.mp8001 = mp8001;
	}
	/**
	 * @return the mp8002
	 */
	public MP8002 getMp8002() {
		return mp8002;
	}
	/**
	 * @param mp8002 the mp8002 to set
	 */
	public void setMp8002(MP8002 mp8002) {
		this.mp8002 = mp8002;
	}
	/**
	 * @return the examPlanInfoList3
	 */
	public List<MP8002> getExamPlanInfoList3() {
		return examPlanInfoList3;
	}
	/**
	 * @param examPlanInfoList3 the examPlanInfoList3 to set
	 */
	public void setExamPlanInfoList3(List<MP8002> examPlanInfoList3) {
		this.examPlanInfoList3 = examPlanInfoList3;
	}
	/**
	 * @return the examPlanInfoList4
	 */
	public List<MP8003> getExamPlanInfoList4() {
		return examPlanInfoList4;
	}
	/**
	 * @param examPlanInfoList4 the examPlanInfoList4 to set
	 */
	public void setExamPlanInfoList4(List<MP8003> examPlanInfoList4) {
		this.examPlanInfoList4 = examPlanInfoList4;
	}
	/**
	 * @return the mp8003
	 */
	public MP8003 getMp8003() {
		return mp8003;
	}
	/**
	 * @param mp8003 the mp8003 to set
	 */
	public void setMp8003(MP8003 mp8003) {
		this.mp8003 = mp8003;
	}
	/**
	 * @return the examItemsInfo2
	 */
	public List<MP8003> getExamItemsInfo2() {
		return examItemsInfo2;
	}
	/**
	 * @param examItemsInfo2 the examItemsInfo2 to set
	 */
	public void setExamItemsInfo2(List<MP8003> examItemsInfo2) {
		this.examItemsInfo2 = examItemsInfo2;
	}
	/**
	 * @return the relatedDepartmentListInfo
	 */
	public List<MP8004> getRelatedDepartmentListInfo() {
		return relatedDepartmentListInfo;
	}
	/**
	 * @param relatedDepartmentListInfo the relatedDepartmentListInfo to set
	 */
	public void setRelatedDepartmentListInfo(List<MP8004> relatedDepartmentListInfo) {
		this.relatedDepartmentListInfo = relatedDepartmentListInfo;
	}
	/**
	 * @return the evaluationMonthly2ScoresList
	 */
	public List<MP8006> getEvaluationMonthly2ScoresList() {
		return evaluationMonthly2ScoresList;
	}
	/**
	 * @param evaluationMonthly2ScoresList the evaluationMonthly2ScoresList to set
	 */
	public void setEvaluationMonthly2ScoresList(
			List<MP8006> evaluationMonthly2ScoresList) {
		this.evaluationMonthly2ScoresList = evaluationMonthly2ScoresList;
	}
	/**
	 * @return the evaluationMonthly2InfoList
	 */
	public List<MP8005> getEvaluationMonthly2InfoList() {
		return evaluationMonthly2InfoList;
	}
	/**
	 * @param evaluationMonthly2InfoList the evaluationMonthly2InfoList to set
	 */
	public void setEvaluationMonthly2InfoList(
			List<MP8005> evaluationMonthly2InfoList) {
		this.evaluationMonthly2InfoList = evaluationMonthly2InfoList;
	}
	/**
	 * @return the mp8005
	 */
	public MP8005 getMp8005() {
		return mp8005;
	}
	/**
	 * @param mp8005 the mp8005 to set
	 */
	public void setMp8005(MP8005 mp8005) {
		this.mp8005 = mp8005;
	}
	/**
	 * @return the fun0050001
	 */
	public String getFun0050001() {
		return fun0050001;
	}
	/**
	 * @param fun0050001 the fun0050001 to set
	 */
	public void setFun0050001(String fun0050001) {
		this.fun0050001 = fun0050001;
	}
	/**
	 * @return the fun0050002
	 */
	public String getFun0050002() {
		return fun0050002;
	}
	/**
	 * @param fun0050002 the fun0050002 to set
	 */
	public void setFun0050002(String fun0050002) {
		this.fun0050002 = fun0050002;
	}
	/**
	 * @return the fun0051001
	 */
	public String getFun0051001() {
		return fun0051001;
	}
	/**
	 * @param fun0051001 the fun0051001 to set
	 */
	public void setFun0051001(String fun0051001) {
		this.fun0051001 = fun0051001;
	}
	/**
	 * @return the fun0051002
	 */
	public String getFun0051002() {
		return fun0051002;
	}
	/**
	 * @param fun0051002 the fun0051002 to set
	 */
	public void setFun0051002(String fun0051002) {
		this.fun0051002 = fun0051002;
	}
	/**
	 * @return the fun0052001
	 */
	public String getFun0052001() {
		return fun0052001;
	}
	/**
	 * @param fun0052001 the fun0052001 to set
	 */
	public void setFun0052001(String fun0052001) {
		this.fun0052001 = fun0052001;
	}
	/**
	 * @return the fun0053001
	 */
	public String getFun0053001() {
		return fun0053001;
	}
	/**
	 * @param fun0053001 the fun0053001 to set
	 */
	public void setFun0053001(String fun0053001) {
		this.fun0053001 = fun0053001;
	}
	/**
	 * @return the evaluationMonthlyDetailInfoList
	 */
	public List<MP8005> getEvaluationMonthlyDetailInfoList() {
		return evaluationMonthlyDetailInfoList;
	}
	/**
	 * @param evaluationMonthlyDetailInfoList the evaluationMonthlyDetailInfoList to set
	 */
	public void setEvaluationMonthlyDetailInfoList(
			List<MP8005> evaluationMonthlyDetailInfoList) {
		this.evaluationMonthlyDetailInfoList = evaluationMonthlyDetailInfoList;
	}
	/**
	 * @return the param5
	 */
	public String getParam5() {
		return param5;
	}
	/**
	 * @param param5 the param5 to set
	 */
	public void setParam5(String param5) {
		this.param5 = param5;
	}
	public Map<String, String> getCommonSeasonList() {
		return commonSeasonList;
	}
	public void setCommonSeasonList(Map<String, String> commonSeasonList) {
		this.commonSeasonList = commonSeasonList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getA006009001001() {
		return A006009001001;
	}
	public void setA006009001001(String a006009001001) {
		A006009001001 = a006009001001;
	}
	public String getA006009001002() {
		return A006009001002;
	}
	public void setA006009001002(String a006009001002) {
		A006009001002 = a006009001002;
	}
	public Map<String, String> getCommonDepartmentList2() {
		return commonDepartmentList2;
	}
	public void setCommonDepartmentList2(Map<String, String> commonDepartmentList2) {
		this.commonDepartmentList2 = commonDepartmentList2;
	}
	public String getA006008001001() {
		return A006008001001;
	}
	public void setA006008001001(String a006008001001) {
		A006008001001 = a006008001001;
	}
	public String getReviewPeriod() {
		return reviewPeriod;
	}
	public void setReviewPeriod(String reviewPeriod) {
		this.reviewPeriod = reviewPeriod;
	}
	public Map<String, String> getScoreLevelList() {
		return scoreLevelList;
	}
	public void setScoreLevelList(Map<String, String> scoreLevelList) {
		this.scoreLevelList = scoreLevelList;
	}
	public String getExistScore() {
		return existScore;
	}
	public void setExistScore(String existScore) {
		this.existScore = existScore;
	}
	public String getSelectMap_In() {
		return selectMap_In;
	}
	public void setSelectMap_In(String selectMap_In) {
		this.selectMap_In = selectMap_In;
	}
	public String getMp8005comment_In() {
		return Mp8005comment_In;
	}
	public void setMp8005comment_In(String mp8005comment_In) {
		Mp8005comment_In = mp8005comment_In;
	}
	
}
