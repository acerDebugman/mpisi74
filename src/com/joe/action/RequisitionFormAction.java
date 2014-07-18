package com.joe.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import com.joe.common.ChartUtil;
import com.joe.common.Constant;
import com.joe.common.ExcelUtil;
import com.joe.common.LogUtil;
import com.joe.common.UtilCommon;
import com.joe.common.UtilDate;
import com.joe.model.MP0002;
import com.joe.model.MP0006;
import com.joe.model.MP1001;
import com.joe.model.MP1009;
import com.joe.model.MP4001;
import com.joe.model.MP4003;
import com.joe.model.MP4004;
import com.joe.service.IAC0006Service;
import com.joe.service.IAC0007Service;
import com.joe.service.IAC0008Service;
import com.joe.service.IAC0009Service;
import com.joe.service.IMP0002Service;
import com.joe.service.IMP0006Service;
import com.joe.service.IMP0011Service;
import com.joe.service.IMP1001Service;
import com.joe.service.IMP1009Service;
import com.joe.service.IMP4001Service;
import com.joe.service.IMP4002Service;
import com.joe.service.IMP4003Service;
import com.joe.service.IMP4004Service;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RequisitionFormAction extends ActionSupport {

	private static final long serialVersionUID = -4511250439266884805L;
	private static final Log log = LogFactory.getLog(RequisitionFormAction.class);
	
	private IMP1009Service serviceMP1009;
	private IMP0002Service serviceMP0002;
	private IMP0006Service serviceMP0006;
	private IMP0011Service serviceMP0011;
	
	private IMP1001Service serviceMP1001;
	private IMP4001Service serviceMP4001;
	private IMP4002Service serviceMP4002;
	private IMP4003Service serviceMP4003;
	private IMP4004Service serviceMP4004;
	
	private IAC0006Service serviceAC0006;
	private IAC0007Service serviceAC0007;
	private IAC0008Service serviceAC0008;
	private IAC0009Service serviceAC0009;
	
	private MP4004 mp4004 = new MP4004();
	private MP1001 mp1001 = new MP1001();
	
	private List<MP0002> departmentObjList = new ArrayList<MP0002>();
	private List<MP4003> mp4003InfoList = new ArrayList<MP4003>();
	private List<MP1009> attachedDocumentList = new ArrayList<MP1009>();
	private List<MP4004> budgetAdditionInfoList = new ArrayList<MP4004>();
	
	private Map<String,String> departmentInfoList = new LinkedHashMap<String,String>();
	private Map<String,String> paymentTypeInfoList = new LinkedHashMap<String,String>();
	private Map<String,String> pr1001RelatedDepList = new LinkedHashMap<String,String>();
	private Map<String,String> typeInfoList = new LinkedHashMap<String,String>();
	private Map<String,String> typeInfoList2 = new LinkedHashMap<String,String>();
	private Map<String, String> currencyTypeList = new LinkedHashMap<String, String>();
	private Map<String, String> yearInfoList = new LinkedHashMap<String, String>();
	private Map<String, String> monthInfoList = new LinkedHashMap<String, String>();
	private Map<String, String> mainClassList = new LinkedHashMap<String, String>();
	
	private String[] amountArray ;
	
	private String fromDate;
	private String toDate;
	private String PR1001_NUM;
	private String pr1001DepartmenSeq;
	private String pr1001AmountStatus;//1---(<=3000) 2--(3000~100000) 3--(>100000)
	
	private String pr1001Type;
	private String pr1001Type2;
	private String pr1001PaymentType;
	private String pr1001RequisitionNum;
	private String pr1001TotalAmount;
	private String pr1001RelatedDep;
	private String pr1001RelatedDepName;
	
	private String pr1002Seq;
	private String pr1002RequisitionNum;
	private String pr1002ProductName;
	private String pr1002Quantity;
	private String pr1002EstimationPrice;
	private String pr1002Amount;
	private String pr1002Comment;
	
	private String pr1003Seq;
	private String pr1003ItemSeq;
	private String pr1003SupplierID;
	private String pr1003SupplierName;
	private String pr1003Price;
	private String pr1003Opinion;
	private String pr1003ChooseResult;
	private String pr1003Comment;
	
	private String mp4003Seq;
	private String budgetCode;
	private String budgetName;
	private String bugdet;
	private String currencyType;
	private String currentYear;
	private String currentMonth;
	private String currentMonth1;
	private String departmentId;
	private String budgetStatus;
	private String currentMonthFlag;
	private String btnName1;
	private String btnName2;
	private String mainClass;
	
	private BigDecimal budgetAmount1;
	private BigDecimal budgetAmount2;
	private BigDecimal budgetAmount3;
	private BigDecimal budgetAmount4;
	
	private String mp4004Seq;
	
	private int pageNum;
	private int pageCount;
	private String pageType;
	private String parameterType;
	private String statusType;
	private String confirmType;
	private String loginUserID;
	private String loginUserDep;
	private String errMsg;
	private String param1;
	private String param2;
	private String param3;
	private String commonSeq;
	
	//------------------Download用变量-------------
	private String fileName = "";
	private String downLoadFileName;

	//------------------权限控制用变量---------------
	private String optSave="0";
	private String optCancel="0";
	private String optApproval = "0";
	
	private String optSearch = "0";
	private String optDel = "0";
	private String optEdit = "0";
	private String optAdd = "0";
	private String optView = "0";
	
	private String optAll = "0";
	private String optDepartment = "0";
	private String optPersonal = "0";
	
	private String optPdf = "0";
	
	private String optApprovalCeo;
	private String optApprovalDirecotor;
	private String optApprovalProcurement;
	private String optApprovalDepartment;
	
    /* 
	* @getDownloadFile 此方法对应的是struts.xml文件中的： <param 
	* name="inputName">downloadFile</param> 返回下载文件的流，可以参看struts2的源码 
	*/  
	public InputStream getDownloadFile(){
        //return ServletActionContext.getServletContext().getResourceAsStream("/"+UploadConfigurationRead.getInstance().getConfigItem("uploadFilePath").trim()+"/" + fileName);
		return ServletActionContext.getServletContext().getResourceAsStream("/uploadfile/" + fileName);
	}
	
	public String documentDownloadPR(){
		return SUCCESS;
	}
	// 清除SESSION
	private void clearSession(Map<String,Object> session){
		if(session.containsKey("SEQ_MAP")){
			session.remove("SEQ_MAP");
		}
		if(session.containsKey("PURCHASE_ITEM_INFO")){
			session.remove("PURCHASE_ITEM_INFO");
		}
		if(session.containsKey("SUPPLIER_INFO")){
			session.remove("SUPPLIER_INFO");
		}
	}
	// 主界面初始化处理
	public String mainPmsInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		mp1001 = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
        	//----------------------------Operation History------------------
        	LogUtil logUtil = new LogUtil();
        	logUtil.setServiceMP0011(serviceMP0011);
        	logUtil.writeOperationLog(mp1001.getMP1001_EMPLOYEE_NUM(),mp1001.getMP1001_PREFERED_NAME(),"Procument Management System Main Page Init");
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

	// 取得采购管理页面权限信息
	private void getRequisitionFormMngAuthority(String _employeeNum,String functionNum,String systemNum){
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
		if(optMap.containsKey(Constant.OPT_ADD)){
			optAdd = "1";
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
		
		
		
		if(optMap.containsKey(Constant.OPT_APPROVAL_CEO)){
			optApprovalCeo = "1";
		}
		if(optMap.containsKey(Constant.OPT_APPROVAL_DIRECTOR)){
			optApprovalDirecotor = "1";
		}
		if(optMap.containsKey(Constant.OPT_APPROVAL_PROCUREMENT)){
			optApprovalProcurement = "1";
		}
		if(optMap.containsKey(Constant.OPT_APPROVAL_DEPARTMENT)){
			optApprovalDepartment= "1";
		}
		
	}
	
	/* 预算管理 */
	public String bugdetMngInit(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Budget Management Page Init");
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
	// 取得所有有效部门信息
	public String departmentList(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Get All department Records");
			//----------------------------Operation History------------------
			
			// 权限
			getRequisitionFormMngAuthority(employeeData.getMP1001_EMPLOYEE_NUM(),Constant.F0021001,Constant.SYSTEM_NUM_PMS);
			
			if(optAll.equals("1")){// 具有查看所有部门的权限
				departmentObjList = new ArrayList<MP0002>();
				if(statusType.equals("true")){
					departmentObjList = serviceMP0002.findAll(true);
				}else{
					departmentObjList = serviceMP0002.findAll(false);
				}
			}else{
				MP0002 mp02 = serviceMP0002.findById(Integer.parseInt(employeeData.getMP1001_DEPARTMENT_ID()));
				departmentObjList = new ArrayList<MP0002>();
				departmentObjList.add(mp02);
			}
			
			mainClassList = Constant.getMainClassList();
			
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
	// 设定各个部门的预算总金额
	public String budgetSetInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Department Budget Amount Set Init");
			//----------------------------Operation History------------------
			
			// 权限
			getRequisitionFormMngAuthority(employeeData.getMP1001_EMPLOYEE_NUM(),Constant.F0021001,Constant.SYSTEM_NUM_PMS);
			
			setButtonName();
			
			errMsg = "";
			if(session.containsKey("MP4003INFO_LIST")){
				session.remove("MP4003INFO_LIST");
			}
			//departmentInfoList = UtilCommon.getDepartmentList(serviceMP0002);
			departmentInfoList = UtilCommon.getBudgetDepartmentList();
			mainClassList = Constant.getMainClassList();
			if(pageType ==null || pageType.equals("") || pageType.equals("view")){
/*				if(session.containsKey("ERR_MSG")){
					session.remove("ERR_MSG");
					session.put("ERR_MSG", "parameter is null");
				}
				return "error";*/
				departmentId = employeeData.getMP1001_DEPARTMENT_ID();
				
				yearInfoList = Constant.getYearInfoList();
				monthInfoList = Constant.getMonthInfoList();
			}else{
				departmentId = pageType;
			}
			
			if(currentYear == null || currentYear.equals("")){
				currentYear = UtilDate.getYear();
			}
			if(currentMonth == null || currentMonth.equals("")){
				currentMonth = UtilDate.getMonthInYear();
				currentMonthFlag = "1";
			}
			if(currentMonth.equals("current")){
				currentYear = UtilDate.getYear();
				currentMonth = UtilDate.getMonthInYear();
				currentMonthFlag = "1";
			}else if(currentMonth.equals("next")){
				currentMonthFlag = "0";
				int tempMonth = Integer.parseInt(UtilDate.getMonthInYear());
				int tempYear = Integer.parseInt(UtilDate.getYear()) + 1;
				if(tempMonth == 12){
					currentMonth = "1";
					currentYear = String.valueOf(tempYear);
				}else{
					currentYear = UtilDate.getYear();
					currentMonth = String.valueOf(tempMonth + 1);
				}
			}
			if(departmentId == null || departmentId.equals("") || departmentId.equals("-1")){
				departmentId= employeeData.getMP1001_DEPARTMENT_ID();
			}
			if(mainClass == null || mainClass.equals("")){
				mainClass = "1";
			}
			if(mainClass.equals("-1")){
				mainClass = "";
			}
			
			Map<String, String> propertyMap = new HashMap<String, String>();
			propertyMap.put("YEAR", currentYear);
			propertyMap.put("MONTH", currentMonth);
			propertyMap.put("DEPARTMENT_ID", departmentId);
			propertyMap.put("MAIN_CLASS", mainClass);
			mp4003InfoList = serviceMP4003.findByPropertyByPage(propertyMap, -1, -1);	
			
			amountArray = new String[mp4003InfoList.size()];
			BigDecimal _budgetAmount1 = new BigDecimal("0");
			BigDecimal _budgetAmount2 = new BigDecimal("0");
			BigDecimal _budgetAmount3 = new BigDecimal("0");
			for(int i=0,j=mp4003InfoList.size(); i<j; i++){
				MP4003 mp43 = mp4003InfoList.get(i);
				
				amountArray[i] = mp43.getMP4003_AMOUNT().toString();
				budgetStatus = mp43.getMP4003_STATUS();
				
				_budgetAmount1 = _budgetAmount1.add(mp43.getMP4003_AMOUNT());
				_budgetAmount2 = _budgetAmount2.add(mp43.getMP4003_AMOUNT2());
				_budgetAmount3 = _budgetAmount3.add(mp43.getMP4003_AMOUNT3());
			}
			budgetAmount1 = _budgetAmount1; // 部门预算总金额
			budgetAmount2 = _budgetAmount2; // 部门可支配预算总金额
			budgetAmount3 = _budgetAmount3; // 部门追加预算总金额
			budgetAmount4 = (budgetAmount1.add(budgetAmount3)).subtract(budgetAmount2);// 部门已使用预算总金额
			
			session.put("MP4003INFO_LIST", mp4003InfoList);
			
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
	// 保存各部门预算总金额
	@SuppressWarnings("unchecked")
	public String departmentBudgetSave(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Department Budget Save");
			//----------------------------Operation History------------------
			
			MP4003 mp43 = new MP4003();
			if(session.containsKey("MP4003INFO_LIST")){
				mp4003InfoList = (List<MP4003>) session.get("MP4003INFO_LIST");
			}
			else{
				if(session.containsKey("ERR_MSG")){
					session.remove("ERR_MSG");
					session.put("ERR_MSG", "Data abnormal,system cannot update data.");
				}
				return "error";
			}
			
			String _departId = "";
			String _year ="";
			String _month = "";
			for(int i=0,j=mp4003InfoList.size(); i<j; i++){
				mp43 = mp4003InfoList.get(i);
				String amount = mp43.getMP4003_AMOUNT().toString();
				_departId = mp43.getMP4003_DEPARTMENT_ID();
				_year = mp43.getMP4003_YEAR();
				_month = mp43.getMP4003_MONTH();
				
				// 如果更改了该部门中的某项预算，则更新数据库
				if(amount != null && !amount.equals(amountArray[i])){
					mp43.setMP4003_AMOUNT(new BigDecimal(amountArray[i]));
					mp43.setMP4003_AMOUNT2(new BigDecimal(amountArray[i]));
					serviceMP4003.update(mp43);
				}
			}
			
			serviceMP4003.update2(_departId, _year, _month, budgetStatus);
			
			//departmentInfoList = UtilCommon.getDepartmentList(serviceMP0002);
			departmentInfoList = UtilCommon.getBudgetDepartmentList();
			mainClassList = Constant.getMainClassList();
			//session.remove("MP4003INFO_LIST");
			Map<String, String> propertyMap = new HashMap<String, String>();
			propertyMap.put("YEAR", _year);
			propertyMap.put("MONTH", _month);
			propertyMap.put("DEPARTMENT_ID", _departId);
			propertyMap.put("MAIN_CLASS", mainClass);
			mp4003InfoList = serviceMP4003.findByPropertyByPage(propertyMap, -1, -1);	

			if(budgetStatus.equals("0")){
				amountArray = new String[mp4003InfoList.size()];
				
				for(int i=0,j=mp4003InfoList.size(); i<j; i++){
					MP4003 mp43obj = mp4003InfoList.get(i);
					
					amountArray[i] = mp43obj.getMP4003_AMOUNT().toString();
					budgetStatus = mp43obj.getMP4003_STATUS();
				}
			}else{
				BigDecimal _budgetAmount1 = new BigDecimal("0");
				BigDecimal _budgetAmount2 = new BigDecimal("0");
				BigDecimal _budgetAmount3 = new BigDecimal("0");
				
				for(int i=0,j=mp4003InfoList.size(); i<j; i++){
					MP4003 mp43obj2 = mp4003InfoList.get(i);
					
					_budgetAmount1 = _budgetAmount1.add(mp43obj2.getMP4003_AMOUNT());
					_budgetAmount2 = _budgetAmount2.add(mp43obj2.getMP4003_AMOUNT2());
					_budgetAmount3 = _budgetAmount3.add(mp43obj2.getMP4003_AMOUNT3());
				}
				
				budgetAmount1 = _budgetAmount1; // 部门预算总金额
				budgetAmount2 = _budgetAmount2; // 部门可支配预算总金额
				budgetAmount3 = _budgetAmount3; // 部门追加预算总金额
				budgetAmount4 = (budgetAmount1.add(budgetAmount3)).subtract(budgetAmount2);// 部门已使用预算总金额
			}
			session.put("MP4003INFO_LIST", mp4003InfoList);

			setButtonName();
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
	// 保存
	public String departmentBudgetSave2(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		//----------------------------Operation History------------------
		LogUtil logUtil = new LogUtil();
		logUtil.setServiceMP0011(serviceMP0011);
		logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Department Budget Save2");
		//----------------------------Operation History------------------
		try{
			String _year ="";
			String _month ="";
			if(currentMonthFlag == null || currentMonthFlag.equals("") || currentMonthFlag.equals("1")){
				_year = UtilDate.getYear();
				_month = UtilDate.getMonthInYear();
			}else if(currentMonthFlag.equals("0")){
				int tempMonth = Integer.parseInt(UtilDate.getMonthInYear());
				int tempYear = Integer.parseInt(UtilDate.getYear()) + 1;
				if(tempMonth == 12){
					_year = String.valueOf(tempYear);
					_month = "1";
				}else{
					_year = UtilDate.getYear();
					_month = String.valueOf(tempMonth + 1);
				}
			}
			if(departmentId == null || departmentId.equals("") || departmentId.equals("-1")){
				departmentId= employeeData.getMP1001_DEPARTMENT_ID();
			}
			
			serviceMP4003.update2(departmentId, _year, _month, budgetStatus);
			
			//departmentInfoList = UtilCommon.getDepartmentList(serviceMP0002);
			departmentInfoList = UtilCommon.getBudgetDepartmentList();
			mainClassList = Constant.getMainClassList();

			Map<String, String> propertyMap = new HashMap<String, String>();
			propertyMap.put("YEAR", _year);
			propertyMap.put("MONTH", _month);
			propertyMap.put("DEPARTMENT_ID", departmentId);
			propertyMap.put("MAIN_CLASS", mainClass);
			mp4003InfoList = serviceMP4003.findByPropertyByPage(propertyMap, -1, -1);	

			if(budgetStatus.equals("0")){
				amountArray = new String[mp4003InfoList.size()];
				
				for(int i=0,j=mp4003InfoList.size(); i<j; i++){
					MP4003 mp43 = mp4003InfoList.get(i);
					
					amountArray[i] = mp43.getMP4003_AMOUNT().toString();
					budgetStatus = mp43.getMP4003_STATUS();
				}
			}else{
				BigDecimal _budgetAmount1 = new BigDecimal("0");
				BigDecimal _budgetAmount2 = new BigDecimal("0");
				BigDecimal _budgetAmount3 = new BigDecimal("0");
				
				for(int i=0,j=mp4003InfoList.size(); i<j; i++){
					MP4003 mp43obj2 = mp4003InfoList.get(i);
					
					_budgetAmount1 = _budgetAmount1.add(mp43obj2.getMP4003_AMOUNT());
					_budgetAmount2 = _budgetAmount2.add(mp43obj2.getMP4003_AMOUNT2());
					_budgetAmount3 = _budgetAmount3.add(mp43obj2.getMP4003_AMOUNT3());
				}
				
				budgetAmount1 = _budgetAmount1; // 部门预算总金额
				budgetAmount2 = _budgetAmount2; // 部门可支配预算总金额
				budgetAmount3 = _budgetAmount3; // 部门追加预算总金额
				budgetAmount4 = (budgetAmount1.add(budgetAmount3)).subtract(budgetAmount2);// 部门已使用预算总金额
			}
			session.put("MP4003INFO_LIST", mp4003InfoList);
			setButtonName();
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
	//
	public String departmentBudgetInfoList(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);

		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Department Budget Info List");
			//----------------------------Operation History------------------
			
			// 权限
			getRequisitionFormMngAuthority(employeeData.getMP1001_EMPLOYEE_NUM(),Constant.F0021001,Constant.SYSTEM_NUM_PMS);
			if(pageType == null || pageType.equals("")){
				pageType = "edit";
			}
			
			if(departmentId == null || departmentId.equals("") || departmentId.equals("-1")){
				departmentId= employeeData.getMP1001_DEPARTMENT_ID();
			}
			if(currentYear == null || currentYear.equals("") || currentYear.equals("-1")){
				currentYear = UtilDate.getYear();
			}
			if(currentMonth == null || currentMonth.equals("") || currentMonth.equals("-1")){
				currentMonth = UtilDate.getMonthInYear();
			}
			if(currentMonth.equals("current")){
				currentYear = UtilDate.getYear();
				currentMonth = UtilDate.getMonthInYear();
			}else if(currentMonth.equals("next")){
				int tempMonth = Integer.parseInt(UtilDate.getMonthInYear());
				int tempYear = Integer.parseInt(UtilDate.getYear()) + 1;
				if(tempMonth == 12){
					currentMonth = "1";
					currentYear = String.valueOf(tempYear);
				}else{
					currentYear = UtilDate.getYear();
					currentMonth = String.valueOf(tempMonth + 1);
				}
			}
			if(mainClass == null || mainClass.equals("")){
				mainClass = "1";
			}
			if(mainClass.equals("-1")){
				mainClass = "";
			}
			
			Map<String, String> propertyMap = new HashMap<String, String>();
			propertyMap.put("YEAR", currentYear);
			propertyMap.put("MONTH", currentMonth);
			propertyMap.put("DEPARTMENT_ID", departmentId);
			propertyMap.put("MAIN_CLASS", mainClass);
			mp4003InfoList = serviceMP4003.findByPropertyByPage(propertyMap, -1, -1);
			
			amountArray = new String[mp4003InfoList.size()];
			BigDecimal _budgetAmount1 = new BigDecimal("0");
			BigDecimal _budgetAmount2 = new BigDecimal("0");
			BigDecimal _budgetAmount3 = new BigDecimal("0");
			
			for(int i=0,j=mp4003InfoList.size(); i<j; i++){
				MP4003 mp43 = mp4003InfoList.get(i);
				
				amountArray[i] = mp43.getMP4003_AMOUNT().toString();
				budgetStatus = mp43.getMP4003_STATUS();
				
				_budgetAmount1 = _budgetAmount1.add(mp43.getMP4003_AMOUNT());
				_budgetAmount2 = _budgetAmount2.add(mp43.getMP4003_AMOUNT2());
				_budgetAmount3 = _budgetAmount3.add(mp43.getMP4003_AMOUNT3());
			}
			budgetAmount1 = _budgetAmount1; // 部门预算总金额
			budgetAmount2 = _budgetAmount2; // 部门可支配预算总金额
			budgetAmount3 = _budgetAmount3; // 部门追加预算总金额
			budgetAmount4 = (budgetAmount1.add(budgetAmount3)).subtract(budgetAmount2);// 部门已使用预算总金额
			
			session.put("MP4003INFO_LIST", mp4003InfoList);
			
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
	// 动态设定按钮名称
	public void setButtonName(){
		Map<String,String> monthNameMap = Constant.getMonthNameList();
		String _currMonth = UtilDate.getMonthInYear();
		String _nextMonth = UtilDate.getNextMonthInYear();
		btnName1 = "< " + monthNameMap.get(_currMonth);
		btnName2 = monthNameMap.get(_nextMonth) + " >";
	}
	
	// 局部刷新,取得预算类型明细类别
	public String type2Info(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			if(pr1001Type != null && !pr1001Type.equals("") && !pr1001Type.equals("undefined")){
				String _year = UtilDate.getYear();
				String _month = UtilDate.getMonthInYear();
				// 取得当年当月对应的预算信息
				typeInfoList2 = UtilCommon.getBugdetTypeLevel2(_year,_month,employeeData.getMP1001_DEPARTMENT_ID(), pr1001Type, serviceMP4003);
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
	// 局部刷新,取得预算金额
	public String budgetInfo(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Get Budget Amount");
			//----------------------------Operation History------------------
			
			if(!pr1001Type2.equals("")){
				int mp43Seq = Integer.parseInt(pr1001Type2);
				MP4003 mp43 = serviceMP4003.findById(mp43Seq);
				MP4001 mp41 = serviceMP4001.findById(mp43.getMP4003_ACCOUNTING_NUM());
				
				bugdet = mp43.getMP4003_AMOUNT2().toString();
				pr1001RelatedDep = mp41.getMP4001_RELATED_DEPARTMENT();
				pr1001RelatedDepName = mp41.getMP4001_RELATED_DEPARTMENT_NAME();
			}
			
			if(pageType.equals("budget")){
				return "page1";
			}if(pageType.equals("relatedDep")){
				return "page2";
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
	// 追加预算金额页面初始化
	public String superaddBudgetInfoInit(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Superadd Budget Info Init");
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
	// 保存追加的预算金额
	public String superaddBudgetInfoSave(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Superadd Budget Info Save");
			//----------------------------Operation History------------------
			
			//更新预算追加表
			MP4004 mp44 = new MP4004();

			mp44.setMP4004_AMOUNT(new BigDecimal(bugdet));
			mp44.setMP4004_DATETIME(UtilDate.get24DateTime());
			mp44.setMP4004_KEY(mp4003Seq);
			mp44.setMP4004_REASON(mp4004.getMP4004_REASON());
            mp44.setMP4004_USER(employeeData.getMP1001_EMPLOYEE_NUM());
            mp44.setMP4004_STATUS("2");// 0:Declined 1:Pending 2:Approved
            
            serviceMP4004.save(mp44);
            
            MP4003 mp43 = serviceMP4003.findById(Integer.parseInt(mp4003Seq));
            BigDecimal mp43Amount2 = mp43.getMP4003_AMOUNT2();
            BigDecimal mp43Amount3 = mp43.getMP4003_AMOUNT3();
            
            mp43.setMP4003_AMOUNT2(mp43Amount2.add(mp44.getMP4004_AMOUNT()));            
            mp43.setMP4003_AMOUNT3(mp43Amount3.add(mp44.getMP4004_AMOUNT()));
            
            serviceMP4003.update(mp43);
            
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			
			out.append("<script type='text/javascript'>");
			out.append("window.dialogArguments.document.getElementById('btnRefresh').click();");
			out.append("window.close();");
			out.append("</script>");
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
	
	// 按部门、年分组统计预算
	public String budgetInfoReportByDepYearInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Budget Information Report By Department Year Init");
			//----------------------------Operation History------------------
			
			getBudgetInfoReportByDepYear(employeeData,Constant.F0021003,false);
			
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
	// 按部门、年分组统计预算，查询处理
	public String budgetInfoReportByDepYearSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Budget Information Report By Department Year Search");
			//----------------------------Operation History------------------
			
			getBudgetInfoReportByDepYear(employeeData,Constant.F0021003,false);
			
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
	// 按部门、年、月分组统计预算
	public String budgetInfoReportByDepYearMonthInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Budget Information Report By Department Year Month Init");
			//----------------------------Operation History------------------
			
			getBudgetInfoReportByDepYear(employeeData,Constant.F0021004,true);
			
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
	// 按部门、年、月分组统计预算
	public String budgetInfoReportByDepYearMonthSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Budget Information Report By Department Year Month Search");
			//----------------------------Operation History------------------
			
			getBudgetInfoReportByDepYear(employeeData,Constant.F0021004,true);
			
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
	// 取得预算统计数据
	private void getBudgetInfoReportByDepYear(MP1001 employeeData,String funcName,boolean flag){
		//departmentInfoList = UtilCommon.getDepartmentList(serviceMP0002);
		departmentInfoList = UtilCommon.getBudgetDepartmentList();
		yearInfoList = Constant.getYearInfoList();
		monthInfoList = Constant.getMonthInfoList();
		mainClassList = Constant.getMainClassList();
		// 权限
		getRequisitionFormMngAuthority(employeeData.getMP1001_EMPLOYEE_NUM(),funcName,Constant.SYSTEM_NUM_PMS);
		// 取得数据
		getDataListByDepYear(employeeData, flag);
		
		MP4003 mp43 = new MP4003();
		BigDecimal _budgetAmount1 = new BigDecimal("0");
		BigDecimal _budgetAmount2 = new BigDecimal("0");
		BigDecimal _budgetAmount3 = new BigDecimal("0");
		for(int i=0; i<mp4003InfoList.size(); i++){
			mp43 = mp4003InfoList.get(i);
			
			_budgetAmount1 = _budgetAmount1.add(mp43.getMP4003_AMOUNT());
			_budgetAmount2 = _budgetAmount2.add(mp43.getMP4003_AMOUNT2());
			_budgetAmount3 = _budgetAmount3.add(mp43.getMP4003_AMOUNT3());
		}
		
		budgetAmount1 = _budgetAmount1; // 部门预算总金额
		budgetAmount2 = _budgetAmount2; // 部门可支配预算总金额
		budgetAmount3 = _budgetAmount3; // 部门追加预算总金额
		budgetAmount4 = (budgetAmount1.add(budgetAmount3)).subtract(budgetAmount2);// 部门已使用预算总金额
	}
	// 取得数据
	private void getDataListByDepYear(MP1001 employeeData,boolean flag){
		if(departmentId == null || departmentId.equals("")){
			departmentId = employeeData.getMP1001_DEPARTMENT_ID();
			//departmentId = ""; 
		}else if(departmentId.equals("-1")){
			departmentId = ""; 
		}
		if(currentYear == null || currentYear.equals("") || currentYear.equals("-1")){
			currentYear = UtilDate.getYear();
		}
		if(currentMonth == null || currentMonth.equals("") || currentMonth.equals("-1")){
			//currentMonth = UtilDate.getMonthInYear();
			currentMonth = "";
		}
		if(currentMonth1 == null || currentMonth1.equals("") || currentMonth1.equals("-1")){
			//currentMonth = UtilDate.getMonthInYear();
			currentMonth1 = "";
		}
		if(mainClass == null || mainClass.equals("")){
			mainClass = "1";
		}
		if(mainClass.equals("-1")){
			mainClass = "";
		}
		
		mp4003InfoList = serviceMP4003.getBudgetInfoListByDepYear(mainClass,departmentId, currentYear,currentMonth, currentMonth1,flag);
	}
	
	
	// 按科目、年分组统计预算
	public String budgetInfoReportByAccYearInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Budget Information Report By Accounting Year Init");
			//----------------------------Operation History------------------
			
			getBudgetInfoReportByAccountYear(employeeData,Constant.F0021005,false);
			
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
	// 按部门、年分组统计预算，查询处理
	public String budgetInfoReportByAccYearSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Budget Information Report By Accounting Year Search");
			//----------------------------Operation History------------------
			
			getBudgetInfoReportByAccountYear(employeeData,Constant.F0021005,false);
			
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
	// 按科目、年分组统计预算
	public String budgetInfoReportByAccYearMonthInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Budget Information Report By Accounting Year Month Init");
			//----------------------------Operation History------------------
			
			getBudgetInfoReportByAccountYear(employeeData,Constant.F0021006,true);
			
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
	// 按部门、年分组统计预算，查询处理
	public String budgetInfoReportByAccYearMonthSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Budget Information Report By Accounting Year Month Search");
			//----------------------------Operation History------------------
			
			getBudgetInfoReportByAccountYear(employeeData,Constant.F0021006,true);
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
	// 取得预算统计数据
	private void getBudgetInfoReportByAccountYear(MP1001 employeeData,String funcName,boolean flag){
		//departmentInfoList = UtilCommon.getDepartmentList(serviceMP0002);
		departmentInfoList = UtilCommon.getBudgetDepartmentList();
		yearInfoList = Constant.getYearInfoList();
		monthInfoList = Constant.getMonthInfoList();
		mainClassList = Constant.getMainClassList();
		// 权限
		getRequisitionFormMngAuthority(employeeData.getMP1001_EMPLOYEE_NUM(),funcName,Constant.SYSTEM_NUM_PMS);
		// 查询数据
		getDataListByAccountYear(employeeData,flag);
		
		MP4003 mp43 = new MP4003();
		BigDecimal _budgetAmount1 = new BigDecimal("0");
		BigDecimal _budgetAmount2 = new BigDecimal("0");
		BigDecimal _budgetAmount3 = new BigDecimal("0");
		for(int i=0; i<mp4003InfoList.size(); i++){
			mp43 = mp4003InfoList.get(i);
			
			_budgetAmount1 = _budgetAmount1.add(mp43.getMP4003_AMOUNT());
			_budgetAmount2 = _budgetAmount2.add(mp43.getMP4003_AMOUNT2());
			_budgetAmount3 = _budgetAmount3.add(mp43.getMP4003_AMOUNT3());
		}
		
		budgetAmount1 = _budgetAmount1; // 部门预算总金额
		budgetAmount2 = _budgetAmount2; // 部门可支配预算总金额
		budgetAmount3 = _budgetAmount3; // 部门追加预算总金额
		budgetAmount4 = (budgetAmount1.add(budgetAmount3)).subtract(budgetAmount2);// 部门已使用预算总金额
	}
	// 取得数据
	public void getDataListByAccountYear(MP1001 employeeData,boolean flag){
		if(departmentId == null || departmentId.equals("")){
			departmentId = employeeData.getMP1001_DEPARTMENT_ID();
			//departmentId = ""; 
		}else if(departmentId.equals("-1")){
			departmentId = ""; 
		}
		if(currentYear == null || currentYear.equals("") || currentYear.equals("-1")){
			currentYear = UtilDate.getYear();
		}
		if(currentMonth == null || currentMonth.equals("") || currentMonth.equals("-1")){
			//currentMonth = UtilDate.getMonthInYear();
			currentMonth = "";
		}
		if(currentMonth1 == null || currentMonth1.equals("") || currentMonth1.equals("-1")){
			//currentMonth = UtilDate.getMonthInYear();
			currentMonth1 = "";
		}
		if(mainClass == null || mainClass.equals("")){
			mainClass = "1";
		}
		if(mainClass.equals("-1")){
			mainClass = "";
		}
		
		mp4003InfoList = serviceMP4003.getBudgetInfoListByAccYear(mainClass,departmentId,currentYear,currentMonth, currentMonth1,flag);
	}
	
	// 按部门、科目、年分组统计预算
	public String budgetInfoReportByDepAccYearInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Budget Information Report By department Accounting Year Init");
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
	// 新建Excel报表
	public String createBudgetExcelDocument(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Create Budget Excel Document");
			//----------------------------Operation History------------------
			
			// 新建EXCEL工作表
			Workbook wb = ExcelUtil.CreateHSSFWorkBook();
			// 新建一个SHEET页面
			Sheet sheet = ExcelUtil.CreateSheet(wb, "Department Budget(1)");
			// 设置SHEET页面属性
			ExcelUtil.SetSheetPropertyHSSF(sheet);
			// 获取预定的样式
			Map<String, CellStyle> styles = ExcelUtil.CreateStyles(wb);
			
			// Header标题
			String[] titles = {"ID", "DEPARTMENT", "YEAR", "BUDGET AMOUNT", "ADDITIONAL BUDGET", "AVAILABLE BUDGET", "EXPENDITURE"};
			// 生成标题行
			float rowHeight = 27f;
			ExcelUtil.CreateHeadRow(sheet, titles, rowHeight, styles);
	        // 冻结第一行
	        sheet.createFreezePane(0, 1);
	        
	        int[] cellsWidth = {5,20,16,20,20,20,20};
	        // 设置单元格的宽度
	        ExcelUtil.SetCellsWidth(sheet, cellsWidth);
	        
	      //---------------------------主报表-----------------------------------------------------------------------------------------
	        // 取得报表数据
	        getDataListByDepYear(employeeData, false);
	        String[] datas = new String[7];
	        int count;
	        for(int i=0,j=mp4003InfoList.size(); i<j; i++){
	        	MP4003 mp43 = mp4003InfoList.get(i);
	        	
	        	count = i + 1;
	        	datas = new String[8];
	        	datas[0] = String.valueOf(count);
	        	datas[1] = mp43.getMP4003_DEPARTMENT_NAME();
	        	datas[2] = mp43.getMP4003_YEAR();
	        	datas[3] = mp43.getMP4003_AMOUNT().toString(); 
	        	datas[4] = mp43.getMP4003_AMOUNT2().toString();
	        	datas[5] = mp43.getMP4003_AMOUNT3().toString(); 
	        	datas[6] = mp43.getMP4003_AMOUNT4().toString();
	        	
	        	ExcelUtil.SetCellsValue(count, sheet, styles, datas);
	        }
	        
            //---------------------------明细报表-----------------------------------------------------------------------------------------
			// 新建一个SHEET页面
			Sheet sheet2 = ExcelUtil.CreateSheet(wb, "Department Budget(2)");
			// 设置SHEET页面属性
			ExcelUtil.SetSheetPropertyHSSF(sheet2);			
			// Header标题
			String[] titles2 = {"ID", "DEPARTMENT", "YEAR", "MONTH", "BUDGET AMOUNT", "ADDITIONAL BUDGET", "AVAILABLE BUDGET", "EXPENDITURE"};
			// 生成标题行
			ExcelUtil.CreateHeadRow(sheet2, titles2, rowHeight, styles);
	        // 冻结第一行
	        sheet.createFreezePane(0, 1);
	        
	        int[] cellsWidth2 = {5,20,16,16,20,20,20,20};
	        // 设置单元格的宽度
	        ExcelUtil.SetCellsWidth(sheet2, cellsWidth2);
	        // 取得报表数据
	        getDataListByDepYear(employeeData, true);
	        String[] datas2 = new String[8];
	        int count2 = 0;
	        for(int i=0,j=mp4003InfoList.size(); i<j; i++){
	        	MP4003 mp43 = mp4003InfoList.get(i);
	        	
	        	count2 = i + 1;
	        	datas2 = new String[8];
	        	datas2[0] = String.valueOf(count2);
	        	datas2[1] = mp43.getMP4003_DEPARTMENT_NAME();
	        	datas2[2] = mp43.getMP4003_YEAR();
	        	datas2[3] = mp43.getMP4003_MONTH_NAME();
	        	datas2[4] = mp43.getMP4003_AMOUNT().toString(); 
	        	datas2[5] = mp43.getMP4003_AMOUNT2().toString();
	        	datas2[6] = mp43.getMP4003_AMOUNT3().toString(); 
	        	datas2[7] = mp43.getMP4003_AMOUNT4().toString();
	        	
	        	ExcelUtil.SetCellsValue(count2, sheet2, styles, datas2);
	        }

			// 生成Excel文件
	        fileName = "businessplan.xls";
	        String _path = ServletActionContext.getServletContext().getRealPath("/") + "uploadfile\\" + fileName;
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
	// 新建Excel报表
	public String createBudgetExcelDocument2(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Create Budget Excel Document2");
			//----------------------------Operation History------------------
			
			// 新建EXCEL工作表
			Workbook wb = ExcelUtil.CreateHSSFWorkBook();
			// 新建一个SHEET页面
			Sheet sheet = ExcelUtil.CreateSheet(wb, "Department Budget(1)");
			// 设置SHEET页面属性
			ExcelUtil.SetSheetPropertyHSSF(sheet);
			// 获取预定的样式
			Map<String, CellStyle> styles = ExcelUtil.CreateStyles(wb);
			
			// Header标题
			String[] titles = {"ID", "ITEM", "NAME", "YEAR", "MONTH", "BUDGET AMOUNT", "ADDITIONAL BUDGET", "AVAILABLE BUDGET", "EXPENDITURE"};
			// 生成标题行
			float rowHeight = 27f;
			ExcelUtil.CreateHeadRow(sheet, titles, rowHeight, styles);
	        // 冻结第一行
	        sheet.createFreezePane(0, 1);
	        
	        int[] cellsWidth = {5,20,100,16,16,20,20,20,20};
	        // 设置单元格的宽度
	        ExcelUtil.SetCellsWidth(sheet, cellsWidth);
	        // 取得报表数据
	        getDataListByAccountYear(employeeData, false);
	        String[] datas = new String[9];
	        int count;
	        for(int i=0,j=mp4003InfoList.size(); i<j; i++){
	        	MP4003 mp43 = mp4003InfoList.get(i);
	        	
	        	count = i + 1;
	        	datas = new String[9];
	        	datas[0] = String.valueOf(count);
	        	datas[1] = mp43.getMP4003_ACCOUNTING_NUM();
	        	datas[2] = mp43.getMP4003_ACCOUNTING_NUM_NAME();
	        	datas[3] = mp43.getMP4003_YEAR(); 
	        	datas[4] = mp43.getMP4003_MONTH_NAME();
	        	datas[5] = mp43.getMP4003_AMOUNT().toString(); 
	        	datas[6] = mp43.getMP4003_AMOUNT2().toString();
	        	datas[7] = mp43.getMP4003_AMOUNT3().toString();
	        	datas[8] = mp43.getMP4003_AMOUNT4().toString();
	        	
	        	ExcelUtil.SetCellsValue(count, sheet, styles, datas);
	        }

            //---------------------------明细报表-----------------------------------------------------------------------------------------
			// 新建一个SHEET页面
			Sheet sheet2 = ExcelUtil.CreateSheet(wb, "Department Budget(2)");
			// 设置SHEET页面属性
			ExcelUtil.SetSheetPropertyHSSF(sheet2);			
			// Header标题
			String[] titles2 = {"ID", "ITEM", "NAME", "YEAR", "MONTH", "BUDGET AMOUNT", "ADDITIONAL BUDGET", "AVAILABLE BUDGET", "EXPENDITURE"};
			// 生成标题行
			ExcelUtil.CreateHeadRow(sheet2, titles2, rowHeight, styles);
	        // 冻结第一行
	        sheet.createFreezePane(0, 1);
	        
	        int[] cellsWidth2 = {5,20,100,16,16,20,20,20,20};
	        // 设置单元格的宽度
	        ExcelUtil.SetCellsWidth(sheet2, cellsWidth2);
	        // 取得报表数据
	        getDataListByAccountYear(employeeData, true);
	        String[] datas2 = new String[9];
	        int count2 = 0;
	        for(int i=0,j=mp4003InfoList.size(); i<j; i++){
	        	MP4003 mp43 = mp4003InfoList.get(i);
	        	
	        	count2 = i + 1;
	        	datas2 = new String[8];
	        	datas[0] = String.valueOf(datas2);
	        	datas[1] = mp43.getMP4003_ACCOUNTING_NUM();
	        	datas[2] = mp43.getMP4003_ACCOUNTING_NUM_NAME();
	        	datas[3] = mp43.getMP4003_YEAR(); 
	        	datas[4] = mp43.getMP4003_MONTH_NAME();
	        	datas[5] = mp43.getMP4003_AMOUNT().toString(); 
	        	datas[6] = mp43.getMP4003_AMOUNT2().toString();
	        	datas[7] = mp43.getMP4003_AMOUNT3().toString();
	        	datas[8] = mp43.getMP4003_AMOUNT4().toString();
	        	
	        	ExcelUtil.SetCellsValue(count2, sheet2, styles, datas2);
	        }
	        
			// 生成Excel文件
	        fileName = "businessplan.xls";
	        String _path = ServletActionContext.getServletContext().getRealPath("/") + "uploadfile\\" + fileName;
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
	
	
	
	// 新增追加预算页面初始化
	public String applyBudgetAdditionInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Budget Addition Application Init");
			//----------------------------Operation History------------------
			
			// 采购类型
			typeInfoList = UtilCommon.getBugdetTypeLevel1(serviceMP4002);
			
			if(pageType != null && (pageType.equals("edit") || pageType.equals("view"))){
				if(mp4004Seq != null && !mp4004Seq.equals("")){
					MP4004 mp44 = serviceMP4004.findById(Integer.parseInt(mp4004Seq));
					MP4003 mp43 = serviceMP4003.findById(Integer.parseInt(mp44.getMP4004_KEY()));
					MP4001 mp41 = serviceMP4001.findById(mp43.getMP4003_ACCOUNTING_NUM());
					
					pr1001Type = mp41.getMP4001_PARENT_TYPE();
					pr1001Type2 = String.valueOf(mp43.getMP4003_SEQ());
					bugdet = mp44.getMP4004_AMOUNT().toString();
					mp4004.setMP4004_REASON(mp44.getMP4004_REASON());
					
					String _year = UtilDate.getYear();
					String _month = UtilDate.getMonthInYear();
					// 取得当年当月对应的预算信息
					typeInfoList2 = UtilCommon.getBugdetTypeLevel2(_year,_month,employeeData.getMP1001_DEPARTMENT_ID(), pr1001Type, serviceMP4003);
				}else{
					return "error";
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
	// 追加预算管理页面初始化
	public String budgetAdditionMngInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Budget Addition Management Init");
			//----------------------------Operation History------------------
			
			departmentInfoList = UtilCommon.getBudgetDepartmentList();
			// 权限
			getRequisitionFormMngAuthority(employeeData.getMP1001_EMPLOYEE_NUM(),Constant.F0021007,Constant.SYSTEM_NUM_PMS);
			
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
	// 追加预算页面保存处理
	public String budgetAdditionSave(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Budget Addition Save");
			//----------------------------Operation History------------------
			
			//更新预算追加表
			MP4004 mp44 = new MP4004();
			
/*			MP4003 mp43 = new MP4003();
 *         Map<String, String> propertyMap = new HashMap<String,String>();
			propertyMap.put("DEPARTMENT_ID", employeeData.getMP1001_DEPARTMENT_ID());
			propertyMap.put("YEAR", UtilDate.getYear());
			propertyMap.put("MONTH", UtilDate.getMonthInYear());
			propertyMap.put("ACCOUNT_NUM", pr1001Type2);
			List<MP4003> mp43List = serviceMP4003.findByPropertyByPage(propertyMap, -1, -1);
			if(mp43List != null && mp43List.size() > 0){
				mp43 = mp43List.get(0);
			}else{
				return "error";
			}*/
			
	        HttpServletResponse response = ServletActionContext.getResponse();
	        response.setCharacterEncoding("utf-8"); // 务必，防止返回文件名是乱码
	        PrintWriter out = response.getWriter();
	        
			if(mp4004Seq != null && !mp4004Seq.equals("")){
				MP4004 mp44Obj =serviceMP4004.findById(Integer.parseInt(mp4004Seq));
				
				mp44Obj.setMP4004_KEY(pr1001Type2);
				mp44Obj.setMP4004_AMOUNT(new BigDecimal(bugdet));
				mp44Obj.setMP4004_REASON(mp4004.getMP4004_REASON());
				
				serviceMP4004.update(mp44Obj);
				
		        out.print("update");
	            out.flush();
	            out.close();
			}else{
				mp44.setMP4004_KEY(pr1001Type2);
				mp44.setMP4004_AMOUNT(new BigDecimal(bugdet));
				mp44.setMP4004_DATETIME(UtilDate.get24DateTime());
				mp44.setMP4004_REASON(mp4004.getMP4004_REASON());
	            mp44.setMP4004_USER(employeeData.getMP1001_EMPLOYEE_NUM());
	            mp44.setMP4004_STATUS("1");// 0:Declined 1:Pending 2:Approved
	            
	            serviceMP4004.save(mp44);
	            
		        out.print("Save Success");
	            out.flush();
	            out.close();
			}

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
	// 追加预算检索处理
	public String BudgetAdditionSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Budget Addition Search");
			//----------------------------Operation History------------------
			
			// 权限
			getRequisitionFormMngAuthority(employeeData.getMP1001_EMPLOYEE_NUM(),Constant.F0021007,Constant.SYSTEM_NUM_PMS);
			
			departmentInfoList = UtilCommon.getBudgetDepartmentList();
			getData(1);
			
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
	// 异步刷新
	public String budgetAdditionRefresh(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Budget Addition Refresh");
			//----------------------------Operation History------------------
			
			// 权限
			getRequisitionFormMngAuthority(employeeData.getMP1001_EMPLOYEE_NUM(),Constant.F0021007,Constant.SYSTEM_NUM_PMS);
			
			getData(pageNum);
			
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
	// 删除处理
	public String budgetAdditionDel(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Budget Addition Delete");
			//----------------------------Operation History------------------
			
			// 权限
			getRequisitionFormMngAuthority(employeeData.getMP1001_EMPLOYEE_NUM(),Constant.F0021007,Constant.SYSTEM_NUM_PMS);
			
			// 删除处理
			MP4004 mp44 = serviceMP4004.findById(Integer.parseInt(mp4004Seq));
			serviceMP4004.delete(mp44);
			
			getData(pageNum);
			
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
	private void getData(int _pageNum){
		Map<String,String> propMap = new HashMap<String,String>();
		if( pr1001DepartmenSeq == null || pr1001DepartmenSeq.equals("undefined") ){
			pr1001DepartmenSeq = "";
		}
		propMap.put("YEAR", fromDate);
		propMap.put("MONTH", toDate);
		propMap.put("DEPARTMENT_ID", pr1001DepartmenSeq);
		
		budgetAdditionInfoList = serviceMP4004.findByPropertyByPage(propMap, -1, -1);
		if( 0 == budgetAdditionInfoList.size()%Constant.PAGE_SIZE){
			pageCount = budgetAdditionInfoList.size()/Constant.PAGE_SIZE;
		}else{
			pageCount = budgetAdditionInfoList.size()/Constant.PAGE_SIZE + 1;
		}
		
		if(pageCount < _pageNum){
			_pageNum = pageCount;
		}
		
		budgetAdditionInfoList = serviceMP4004.findByPropertyByPage(propMap, _pageNum, Constant.PAGE_SIZE);
	}
	// 审核数据
	public String budgetAdditionApprove(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Budget Addition Approve");
			//----------------------------Operation History------------------
			
			// 删除处理
			MP4004 mp44 = serviceMP4004.findById(Integer.parseInt(mp4004Seq));
			//serviceMP4004.delete(mp44);
			if(statusType.equals("y")){
				mp44.setMP4004_STATUS("2");
			}else if(statusType.equals("n")){
				mp44.setMP4004_STATUS("0");
			}
			// 更新状态
			serviceMP4004.update(mp44);
			
			// 如果状态为批准，需要更新预算数据
			if(statusType.equals("y")){
				MP4003 mp43 = serviceMP4003.findById(Integer.parseInt(mp44.getMP4004_KEY()));
				
				BigDecimal additionalBudget = mp43.getMP4003_AMOUNT3();
				additionalBudget = additionalBudget.add(mp44.getMP4004_AMOUNT());
				mp43.setMP4003_AMOUNT3(additionalBudget);
				
				serviceMP4003.update(mp43);
			}
			// 重新检索数据
			//getData(pageNum);
			
	        HttpServletResponse response = ServletActionContext.getResponse();
	        response.setCharacterEncoding("utf-8"); // 务必，防止返回文件名是乱码
	        PrintWriter out = response.getWriter();
	        out.print("Update Success");
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
	
	// 图形报表(所有部门当月的总预算)
	public String budgetChartReportBar() throws IOException{
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		OutputStream out = null;

		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Budget Chart Report Bar");
			//----------------------------Operation History------------------
			
			/*int year = Integer.parseInt(UtilDate.getYear());
			int month =Integer.parseInt(UtilDate.getMonthInYear());
			double[][] data = new double[1][5];
			data[0][0] = 10;
			data[0][1] = 11;
			data[0][2] = 12;
			data[0][3] = 13;
			data[0][4] = 14;
			String[] columnKeys = { "待激活", "激活", "暂停", "黑名单", "作废" };
			String[] columnKeys = UtilCommon.getDepartmentArrayInfo();*/
			
			DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
			List<MP4003> mp43List = serviceMP4003.getBudgetChartData(UtilDate.getYear(), UtilDate.getMonthInYear());
			
			for(int i=0,j=mp43List.size(); i<j; i++){
				MP4003 mp43 = mp43List.get(i);
				
				dataset.addValue(mp43.getMP4003_AMOUNT(), "Budget", mp43.getMP4003_DEPARTMENT_NAME());
				dataset.addValue(mp43.getMP4003_AMOUNT3(), "Additional Budget", mp43.getMP4003_DEPARTMENT_NAME());
				
				//columnKeys[i] = mp43.getMP4003_DEPARTMENT_NAME();
				//data[0][i] = Double.parseDouble(totalAmount.toString());
			}
			
			try {
			    ChartUtil chartUtil = new ChartUtil();
				 
			    //JFreeChart chart = chartUtil.createBarChart3D("", "", "",chartUtil.getBarData(rowKeys, columnKeys,data), "chartStatus.jpg");
			    //chart = demo.createBarChart3D("", "","数量", demo.getBarData(rowKeys, columnKeys,data), PlotOrientation.VERTICAL, true, true, true);
			    //ChartUtilities.writeChartAsJPEG(ServletActionContext.getResponse().getOutputStream(), chart, 780, 370, null);
			    //ServletActionContext.getResponse().setContentType("image/jpg");
			    JFreeChart chart;
			    if(pageType != null && pageType.equals("3D")){
			    	chart = chartUtil.createBarChart3D("", "", "",dataset, "chartStatus.jpg");
			    }else{
			    	chart = chartUtil.createBarChart("", "", "",dataset, "chartStatus.jpg");
			    }
			    
			    HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
			    response.setContentType("image/jpg");
			    ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart, 1200, 600);
			    out = response.getOutputStream();
			    out.flush();
			 } catch (Exception e) {
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
	// 图形报表(一个部门一年的预算折线图)
	public String budgetChartReportLine() throws IOException{
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		OutputStream out = null;

		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Budget Chart Report Line");
			//----------------------------Operation History------------------
			
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			
			if(departmentId == null || departmentId.equals("")){
				departmentId = employeeData.getMP1001_DEPARTMENT_ID();
			}
			List<MP4003> mp43List = serviceMP4003.getBudgetChartData2(UtilDate.getYear(), departmentId);
			
			for(int i=0,j=mp43List.size(); i<j; i++){
				MP4003 mp43 = mp43List.get(i);
				
				dataset.addValue(mp43.getMP4003_AMOUNT(), "Budget", mp43.getMP4003_MONTH_NAME());
				dataset.addValue(mp43.getMP4003_AMOUNT3(), "Additional Budget", mp43.getMP4003_MONTH_NAME());
			}
			
			try {
			    ChartUtil chartUtil = new ChartUtil();
			    JFreeChart chart = chartUtil.createTimeXYChar("", "", "",dataset, "chartStatus.jpg");
			    HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
			    response.setContentType("image/jpg");
			    ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart, 1500, 600);
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
	// 
	public String budgetChartReportInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			departmentInfoList = UtilCommon.getBudgetDepartmentList();
			departmentId = employeeData.getMP1001_DEPARTMENT_ID();
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Budget Chart Report Init");
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
	 * @return the departmentInfoList
	 */
	public Map<String, String> getDepartmentInfoList() {
		return departmentInfoList;
	}

	/**
	 * @param departmentInfoList the departmentInfoList to set
	 */
	public void setDepartmentInfoList(Map<String, String> departmentInfoList) {
		this.departmentInfoList = departmentInfoList;
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
	 * @return the pR1001_NUM
	 */
	public String getPR1001_NUM() {
		return PR1001_NUM;
	}

	/**
	 * @param pR1001_NUM the pR1001_NUM to set
	 */
	public void setPR1001_NUM(String pR1001_NUM) {
		PR1001_NUM = pR1001_NUM;
	}

	/**
	 * @return the pr1001DepartmenSeq
	 */
	public String getPr1001DepartmenSeq() {
		return pr1001DepartmenSeq;
	}

	/**
	 * @param pr1001DepartmenSeq the pr1001DepartmenSeq to set
	 */
	public void setPr1001DepartmenSeq(String pr1001DepartmenSeq) {
		this.pr1001DepartmenSeq = pr1001DepartmenSeq;
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
	 * @return the typeInfoList
	 */
	public Map<String, String> getTypeInfoList() {
		return typeInfoList;
	}

	/**
	 * @param typeInfoList the typeInfoList to set
	 */
	public void setTypeInfoList(Map<String, String> typeInfoList) {
		this.typeInfoList = typeInfoList;
	}
	/**
	 * @return the paymentTypeInfoList
	 */
	public Map<String, String> getPaymentTypeInfoList() {
		return paymentTypeInfoList;
	}
	/**
	 * @param paymentTypeInfoList the paymentTypeInfoList to set
	 */
	public void setPaymentTypeInfoList(Map<String, String> paymentTypeInfoList) {
		this.paymentTypeInfoList = paymentTypeInfoList;
	}
	/**
	 * @return the pr1001Type
	 */
	public String getPr1001Type() {
		return pr1001Type;
	}
	/**
	 * @param pr1001Type the pr1001Type to set
	 */
	public void setPr1001Type(String pr1001Type) {
		this.pr1001Type = pr1001Type;
	}
	/**
	 * @return the pr1001PaymentType
	 */
	public String getPr1001PaymentType() {
		return pr1001PaymentType;
	}
	/**
	 * @param pr1001PaymentType the pr1001PaymentType to set
	 */
	public void setPr1001PaymentType(String pr1001PaymentType) {
		this.pr1001PaymentType = pr1001PaymentType;
	}

	/**
	 * @return the pr1002RequisitionNum
	 */
	public String getPr1002RequisitionNum() {
		return pr1002RequisitionNum;
	}

	/**
	 * @param pr1002RequisitionNum the pr1002RequisitionNum to set
	 */
	public void setPr1002RequisitionNum(String pr1002RequisitionNum) {
		this.pr1002RequisitionNum = pr1002RequisitionNum;
	}

	/**
	 * @return the pr1002ProductName
	 */
	public String getPr1002ProductName() {
		return pr1002ProductName;
	}

	/**
	 * @param pr1002ProductName the pr1002ProductName to set
	 */
	public void setPr1002ProductName(String pr1002ProductName) {
		this.pr1002ProductName = pr1002ProductName;
	}

	/**
	 * @return the pr1002Quantity
	 */
	public String getPr1002Quantity() {
		return pr1002Quantity;
	}

	/**
	 * @param pr1002Quantity the pr1002Quantity to set
	 */
	public void setPr1002Quantity(String pr1002Quantity) {
		this.pr1002Quantity = pr1002Quantity;
	}

	/**
	 * @return the pr1002EstimationPrice
	 */
	public String getPr1002EstimationPrice() {
		return pr1002EstimationPrice;
	}

	/**
	 * @param pr1002EstimationPrice the pr1002EstimationPrice to set
	 */
	public void setPr1002EstimationPrice(String pr1002EstimationPrice) {
		this.pr1002EstimationPrice = pr1002EstimationPrice;
	}

	/**
	 * @return the pr1002Amount
	 */
	public String getPr1002Amount() {
		return pr1002Amount;
	}

	/**
	 * @param pr1002Amount the pr1002Amount to set
	 */
	public void setPr1002Amount(String pr1002Amount) {
		this.pr1002Amount = pr1002Amount;
	}

	/**
	 * @return the pr1002Comment
	 */
	public String getPr1002Comment() {
		return pr1002Comment;
	}

	/**
	 * @param pr1002Comment the pr1002Comment to set
	 */
	public void setPr1002Comment(String pr1002Comment) {
		this.pr1002Comment = pr1002Comment;
	}

	/**
	 * @return the pr1003SupplierName
	 */
	public String getPr1003SupplierName() {
		return pr1003SupplierName;
	}

	/**
	 * @param pr1003SupplierName the pr1003SupplierName to set
	 */
	public void setPr1003SupplierName(String pr1003SupplierName) {
		this.pr1003SupplierName = pr1003SupplierName;
	}

	/**
	 * @return the pr1003Price
	 */
	public String getPr1003Price() {
		return pr1003Price;
	}

	/**
	 * @param pr1003Price the pr1003Price to set
	 */
	public void setPr1003Price(String pr1003Price) {
		this.pr1003Price = pr1003Price;
	}

	/**
	 * @return the pr1003Opinion
	 */
	public String getPr1003Opinion() {
		return pr1003Opinion;
	}

	/**
	 * @param pr1003Opinion the pr1003Opinion to set
	 */
	public void setPr1003Opinion(String pr1003Opinion) {
		this.pr1003Opinion = pr1003Opinion;
	}

	/**
	 * @return the pr1003ChooseResult
	 */
	public String getPr1003ChooseResult() {
		return pr1003ChooseResult;
	}

	/**
	 * @param pr1003ChooseResult the pr1003ChooseResult to set
	 */
	public void setPr1003ChooseResult(String pr1003ChooseResult) {
		this.pr1003ChooseResult = pr1003ChooseResult;
	}

	/**
	 * @return the pr1003Comment
	 */
	public String getPr1003Comment() {
		return pr1003Comment;
	}

	/**
	 * @param pr1003Comment the pr1003Comment to set
	 */
	public void setPr1003Comment(String pr1003Comment) {
		this.pr1003Comment = pr1003Comment;
	}

	/**
	 * @return the pr1002Seq
	 */
	public String getPr1002Seq() {
		return pr1002Seq;
	}

	/**
	 * @param pr1002Seq the pr1002Seq to set
	 */
	public void setPr1002Seq(String pr1002Seq) {
		this.pr1002Seq = pr1002Seq;
	}

	/**
	 * @return the pr1003Seq
	 */
	public String getPr1003Seq() {
		return pr1003Seq;
	}

	/**
	 * @param pr1003Seq the pr1003Seq to set
	 */
	public void setPr1003Seq(String pr1003Seq) {
		this.pr1003Seq = pr1003Seq;
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
	 * @return the serviceMP0006
	 */
	public IMP0006Service getServiceMP0006() {
		return serviceMP0006;
	}
	/**
	 * @param serviceMP0006 the serviceMP0006 to set
	 */
	public void setServiceMP0006(IMP0006Service serviceMP0006) {
		this.serviceMP0006 = serviceMP0006;
	}
	/**
	 * @return the pr1001RequisitionNum
	 */
	public String getPr1001RequisitionNum() {
		return pr1001RequisitionNum;
	}
	/**
	 * @param pr1001RequisitionNum the pr1001RequisitionNum to set
	 */
	public void setPr1001RequisitionNum(String pr1001RequisitionNum) {
		this.pr1001RequisitionNum = pr1001RequisitionNum;
	}
	/**
	 * @return the pr1001TotalAmount
	 */
	public String getPr1001TotalAmount() {
		return pr1001TotalAmount;
	}
	/**
	 * @param pr1001TotalAmount the pr1001TotalAmount to set
	 */
	public void setPr1001TotalAmount(String pr1001TotalAmount) {
		this.pr1001TotalAmount = pr1001TotalAmount;
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
	 * @return the pr1001RelatedDepList
	 */
	public Map<String, String> getPr1001RelatedDepList() {
		return pr1001RelatedDepList;
	}
	/**
	 * @param pr1001RelatedDepList the pr1001RelatedDepList to set
	 */
	public void setPr1001RelatedDepList(Map<String, String> pr1001RelatedDepList) {
		this.pr1001RelatedDepList = pr1001RelatedDepList;
	}
	/**
	 * @return the pr1001RelatedDep
	 */
	public String getPr1001RelatedDep() {
		return pr1001RelatedDep;
	}
	/**
	 * @param pr1001RelatedDep the pr1001RelatedDep to set
	 */
	public void setPr1001RelatedDep(String pr1001RelatedDep) {
		this.pr1001RelatedDep = pr1001RelatedDep;
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
	 * @return the pr1001AmountStatus
	 */
	public String getPr1001AmountStatus() {
		return pr1001AmountStatus;
	}
	/**
	 * @param pr1001AmountStatus the pr1001AmountStatus to set
	 */
	public void setPr1001AmountStatus(String pr1001AmountStatus) {
		this.pr1001AmountStatus = pr1001AmountStatus;
	}
	/**
	 * @return the optApprovalCeo
	 */
	public String getOptApprovalCeo() {
		return optApprovalCeo;
	}
	/**
	 * @param optApprovalCeo the optApprovalCeo to set
	 */
	public void setOptApprovalCeo(String optApprovalCeo) {
		this.optApprovalCeo = optApprovalCeo;
	}
	/**
	 * @return the optApprovalDirecotor
	 */
	public String getOptApprovalDirecotor() {
		return optApprovalDirecotor;
	}
	/**
	 * @param optApprovalDirecotor the optApprovalDirecotor to set
	 */
	public void setOptApprovalDirecotor(String optApprovalDirecotor) {
		this.optApprovalDirecotor = optApprovalDirecotor;
	}
	/**
	 * @return the optApprovalProcurement
	 */
	public String getOptApprovalProcurement() {
		return optApprovalProcurement;
	}
	/**
	 * @param optApprovalProcurement the optApprovalProcurement to set
	 */
	public void setOptApprovalProcurement(String optApprovalProcurement) {
		this.optApprovalProcurement = optApprovalProcurement;
	}
	/**
	 * @return the optApprovalDepartment
	 */
	public String getOptApprovalDepartment() {
		return optApprovalDepartment;
	}
	/**
	 * @param optApprovalDepartment the optApprovalDepartment to set
	 */
	public void setOptApprovalDepartment(String optApprovalDepartment) {
		this.optApprovalDepartment = optApprovalDepartment;
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
	 * @return the serviceMP4001
	 */
	public IMP4001Service getServiceMP4001() {
		return serviceMP4001;
	}
	/**
	 * @param serviceMP4001 the serviceMP4001 to set
	 */
	public void setServiceMP4001(IMP4001Service serviceMP4001) {
		this.serviceMP4001 = serviceMP4001;
	}
	/**
	 * @return the serviceMP4002
	 */
	public IMP4002Service getServiceMP4002() {
		return serviceMP4002;
	}
	/**
	 * @param serviceMP4002 the serviceMP4002 to set
	 */
	public void setServiceMP4002(IMP4002Service serviceMP4002) {
		this.serviceMP4002 = serviceMP4002;
	}
	/**
	 * @return the serviceMP4003
	 */
	public IMP4003Service getServiceMP4003() {
		return serviceMP4003;
	}
	/**
	 * @param serviceMP4003 the serviceMP4003 to set
	 */
	public void setServiceMP4003(IMP4003Service serviceMP4003) {
		this.serviceMP4003 = serviceMP4003;
	}
	/**
	 * @return the departmentObjList
	 */
	public List<MP0002> getDepartmentObjList() {
		return departmentObjList;
	}
	/**
	 * @param departmentObjList the departmentObjList to set
	 */
	public void setDepartmentObjList(List<MP0002> departmentObjList) {
		this.departmentObjList = departmentObjList;
	}
	/**
	 * @return the mp4003InfoList
	 */
	public List<MP4003> getMp4003InfoList() {
		return mp4003InfoList;
	}
	/**
	 * @param mp4003InfoList the mp4003InfoList to set
	 */
	public void setMp4003InfoList(List<MP4003> mp4003InfoList) {
		this.mp4003InfoList = mp4003InfoList;
	}
	/**
	 * @return the amountArray
	 */
	public String[] getAmountArray() {
		return amountArray;
	}
	/**
	 * @param amountArray the amountArray to set
	 */
	public void setAmountArray(String[] amountArray) {
		this.amountArray = amountArray;
	}
	/**
	 * @return the parameterType
	 */
	public String getParameterType() {
		return parameterType;
	}
	/**
	 * @param parameterType the parameterType to set
	 */
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}
	/**
	 * @return the statusType
	 */
	public String getStatusType() {
		return statusType;
	}
	/**
	 * @param statusType the statusType to set
	 */
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	/**
	 * @return the typeInfoList2
	 */
	public Map<String, String> getTypeInfoList2() {
		return typeInfoList2;
	}
	/**
	 * @param typeInfoList2 the typeInfoList2 to set
	 */
	public void setTypeInfoList2(Map<String, String> typeInfoList2) {
		this.typeInfoList2 = typeInfoList2;
	}
	/**
	 * @return the pr1001Type2
	 */
	public String getPr1001Type2() {
		return pr1001Type2;
	}
	/**
	 * @param pr1001Type2 the pr1001Type2 to set
	 */
	public void setPr1001Type2(String pr1001Type2) {
		this.pr1001Type2 = pr1001Type2;
	}
	/**
	 * @return the bugdet
	 */
	public String getBugdet() {
		return bugdet;
	}
	/**
	 * @param bugdet the bugdet to set
	 */
	public void setBugdet(String bugdet) {
		this.bugdet = bugdet;
	}
	/**
	 * @return the pr1001RelatedDepName
	 */
	public String getPr1001RelatedDepName() {
		return pr1001RelatedDepName;
	}
	/**
	 * @param pr1001RelatedDepName the pr1001RelatedDepName to set
	 */
	public void setPr1001RelatedDepName(String pr1001RelatedDepName) {
		this.pr1001RelatedDepName = pr1001RelatedDepName;
	}
	/**
	 * @return the pr1003ItemSeq
	 */
	public String getPr1003ItemSeq() {
		return pr1003ItemSeq;
	}
	/**
	 * @param pr1003ItemSeq the pr1003ItemSeq to set
	 */
	public void setPr1003ItemSeq(String pr1003ItemSeq) {
		this.pr1003ItemSeq = pr1003ItemSeq;
	}
	/**
	 * @return the serviceMP1009
	 */
	public IMP1009Service getServiceMP1009() {
		return serviceMP1009;
	}
	/**
	 * @param serviceMP1009 the serviceMP1009 to set
	 */
	public void setServiceMP1009(IMP1009Service serviceMP1009) {
		this.serviceMP1009 = serviceMP1009;
	}
	/**
	 * @return the attachedDocumentList
	 */
	public List<MP1009> getAttachedDocumentList() {
		return attachedDocumentList;
	}
	/**
	 * @param attachedDocumentList the attachedDocumentList to set
	 */
	public void setAttachedDocumentList(List<MP1009> attachedDocumentList) {
		this.attachedDocumentList = attachedDocumentList;
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
	 * @return the currencyTypeList
	 */
	public Map<String, String> getCurrencyTypeList() {
		return currencyTypeList;
	}

	/**
	 * @param currencyTypeList the currencyTypeList to set
	 */
	public void setCurrencyTypeList(Map<String, String> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	/**
	 * @return the currencyType
	 */
	public String getCurrencyType() {
		return currencyType;
	}

	/**
	 * @param currencyType the currencyType to set
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	/**
	 * @return the yearInfoList
	 */
	public Map<String, String> getYearInfoList() {
		return yearInfoList;
	}

	/**
	 * @param yearInfoList the yearInfoList to set
	 */
	public void setYearInfoList(Map<String, String> yearInfoList) {
		this.yearInfoList = yearInfoList;
	}

	/**
	 * @return the monthInfoList
	 */
	public Map<String, String> getMonthInfoList() {
		return monthInfoList;
	}

	/**
	 * @param monthInfoList the monthInfoList to set
	 */
	public void setMonthInfoList(Map<String, String> monthInfoList) {
		this.monthInfoList = monthInfoList;
	}

	/**
	 * @return the currentYear
	 */
	public String getCurrentYear() {
		return currentYear;
	}

	/**
	 * @param currentYear the currentYear to set
	 */
	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}

	/**
	 * @return the currentMonth
	 */
	public String getCurrentMonth() {
		return currentMonth;
	}

	/**
	 * @param currentMonth the currentMonth to set
	 */
	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}

	/**
	 * @return the departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the mp4003Seq
	 */
	public String getMp4003Seq() {
		return mp4003Seq;
	}

	/**
	 * @param mp4003Seq the mp4003Seq to set
	 */
	public void setMp4003Seq(String mp4003Seq) {
		this.mp4003Seq = mp4003Seq;
	}

	/**
	 * @return the budgetCode
	 */
	public String getBudgetCode() {
		return budgetCode;
	}

	/**
	 * @param budgetCode the budgetCode to set
	 */
	public void setBudgetCode(String budgetCode) {
		this.budgetCode = budgetCode;
	}

	/**
	 * @return the budgetName
	 */
	public String getBudgetName() {
		return budgetName;
	}

	/**
	 * @param budgetName the budgetName to set
	 */
	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}

	/**
	 * @return the serviceMP4004
	 */
	public IMP4004Service getServiceMP4004() {
		return serviceMP4004;
	}

	/**
	 * @param serviceMP4004 the serviceMP4004 to set
	 */
	public void setServiceMP4004(IMP4004Service serviceMP4004) {
		this.serviceMP4004 = serviceMP4004;
	}

	/**
	 * @return the mp4004
	 */
	public MP4004 getMp4004() {
		return mp4004;
	}

	/**
	 * @param mp4004 the mp4004 to set
	 */
	public void setMp4004(MP4004 mp4004) {
		this.mp4004 = mp4004;
	}

	/**
	 * @return the budgetStatus
	 */
	public String getBudgetStatus() {
		return budgetStatus;
	}

	/**
	 * @param budgetStatus the budgetStatus to set
	 */
	public void setBudgetStatus(String budgetStatus) {
		this.budgetStatus = budgetStatus;
	}

	/**
	 * @return the currentMonthFlag
	 */
	public String getCurrentMonthFlag() {
		return currentMonthFlag;
	}

	/**
	 * @param currentMonthFlag the currentMonthFlag to set
	 */
	public void setCurrentMonthFlag(String currentMonthFlag) {
		this.currentMonthFlag = currentMonthFlag;
	}

	/**
	 * @return the btnName1
	 */
	public String getBtnName1() {
		return btnName1;
	}

	/**
	 * @param btnName1 the btnName1 to set
	 */
	public void setBtnName1(String btnName1) {
		this.btnName1 = btnName1;
	}

	/**
	 * @return the btnName2
	 */
	public String getBtnName2() {
		return btnName2;
	}

	/**
	 * @param btnName2 the btnName2 to set
	 */
	public void setBtnName2(String btnName2) {
		this.btnName2 = btnName2;
	}

	/**
	 * @return the currentMonth1
	 */
	public String getCurrentMonth1() {
		return currentMonth1;
	}

	/**
	 * @param currentMonth1 the currentMonth1 to set
	 */
	public void setCurrentMonth1(String currentMonth1) {
		this.currentMonth1 = currentMonth1;
	}

	/**
	 * @return the mainClassList
	 */
	public Map<String, String> getMainClassList() {
		return mainClassList;
	}

	/**
	 * @param mainClassList the mainClassList to set
	 */
	public void setMainClassList(Map<String, String> mainClassList) {
		this.mainClassList = mainClassList;
	}

	/**
	 * @return the mainClass
	 */
	public String getMainClass() {
		return mainClass;
	}

	/**
	 * @param mainClass the mainClass to set
	 */
	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}

	/**
	 * @return the budgetAmount1
	 */
	public BigDecimal getBudgetAmount1() {
		return budgetAmount1;
	}

	/**
	 * @param budgetAmount1 the budgetAmount1 to set
	 */
	public void setBudgetAmount1(BigDecimal budgetAmount1) {
		this.budgetAmount1 = budgetAmount1;
	}

	/**
	 * @return the budgetAmount2
	 */
	public BigDecimal getBudgetAmount2() {
		return budgetAmount2;
	}

	/**
	 * @param budgetAmount2 the budgetAmount2 to set
	 */
	public void setBudgetAmount2(BigDecimal budgetAmount2) {
		this.budgetAmount2 = budgetAmount2;
	}

	/**
	 * @return the budgetAmount3
	 */
	public BigDecimal getBudgetAmount3() {
		return budgetAmount3;
	}

	/**
	 * @param budgetAmount3 the budgetAmount3 to set
	 */
	public void setBudgetAmount3(BigDecimal budgetAmount3) {
		this.budgetAmount3 = budgetAmount3;
	}

	/**
	 * @return the budgetAmount4
	 */
	public BigDecimal getBudgetAmount4() {
		return budgetAmount4;
	}

	/**
	 * @param budgetAmount4 the budgetAmount4 to set
	 */
	public void setBudgetAmount4(BigDecimal budgetAmount4) {
		this.budgetAmount4 = budgetAmount4;
	}

	/**
	 * @return the budgetAdditionInfoList
	 */
	public List<MP4004> getBudgetAdditionInfoList() {
		return budgetAdditionInfoList;
	}

	/**
	 * @param budgetAdditionInfoList the budgetAdditionInfoList to set
	 */
	public void setBudgetAdditionInfoList(List<MP4004> budgetAdditionInfoList) {
		this.budgetAdditionInfoList = budgetAdditionInfoList;
	}

	/**
	 * @return the mp4004Seq
	 */
	public String getMp4004Seq() {
		return mp4004Seq;
	}

	/**
	 * @param mp4004Seq the mp4004Seq to set
	 */
	public void setMp4004Seq(String mp4004Seq) {
		this.mp4004Seq = mp4004Seq;
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
	 * @return the pr1003SupplierID
	 */
	public String getPr1003SupplierID() {
		return pr1003SupplierID;
	}

	/**
	 * @param pr1003SupplierID the pr1003SupplierID to set
	 */
	public void setPr1003SupplierID(String pr1003SupplierID) {
		this.pr1003SupplierID = pr1003SupplierID;
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
}
