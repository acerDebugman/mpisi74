package com.joe.action;

import java.io.InputStream;
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

import com.joe.common.Constant;
import com.joe.common.CustomerContextHolder;
import com.joe.common.ExcelUtil;
import com.joe.common.LogUtil;
import com.joe.model.CHECKINOUT;
import com.joe.model.MP1001;
import com.joe.model.MP1010;
import com.joe.model.MP2008;
import com.joe.schedule.executeJobs;
import com.joe.service.IAC0006Service;
import com.joe.service.IAC0007Service;
import com.joe.service.IAC0008Service;
import com.joe.service.IAC0009Service;
import com.joe.service.ICHECKINOUTService;
import com.joe.service.IMP0011Service;
import com.joe.service.IMP1001Service;
import com.joe.service.IMP1010Service;
import com.joe.service.IMP2003Service;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AttendanceDataLoadAction extends ActionSupport{
	private static final long serialVersionUID = 5884129028125351753L;
	private static final Log log = LogFactory.getLog(AttendanceDataLoadAction.class);
	
	private ICHECKINOUTService serviceCHECKINOUT;
	private IMP1001Service serviceMP1001;
	private IMP1010Service serviceMP1010;
	private IMP0011Service serviceMP0011;
	private IMP2003Service serviceMP2003;
	
	private IAC0006Service serviceAC0006;
	private IAC0007Service serviceAC0007;
	private IAC0008Service serviceAC0008;
	private IAC0009Service serviceAC0009;
	
	private List<CHECKINOUT> checkinoutInfoList = new ArrayList<CHECKINOUT>();
	
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
	
	//------------------下载用变量---------------
	private String fileName = "";
	
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
	
    /* 
	* @getDownloadFile 此方法对应的是struts.xml文件中的： <param 
	* name="inputName">downloadFile</param> 返回下载文件的流，可以参看struts2的源码 
	*/  
	public InputStream getDownloadFile(){
        //return ServletActionContext.getServletContext().getResourceAsStream("/"+UploadConfigurationRead.getInstance().getConfigItem("uploadFilePath").trim()+"/" + fileName);
		return ServletActionContext.getServletContext().getResourceAsStream("/uploadfile/" + fileName);
	}
	// CHECKINOUT管理页面初始化
	public String checkinoutInfoMngInit(){
	    ActionContext context = ActionContext.getContext();
	    Map<String,Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    String empNum = employeeData.getMP1001_EMPLOYEE_NUM();

	    try{
	        //----------------------------Operation History------------------
	        LogUtil logUtil = new LogUtil();
	        logUtil.setServiceMP0011(serviceMP0011);
	        logUtil.writeOperationLog(empNum,employeeData.getMP1001_PREFERED_NAME(),"CHECKINOUT Information Management Page Init");
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
	// CHECKINOUT信息分页处理
	public String checkinoutInfoSearch(){
	    ActionContext context = ActionContext.getContext();
	    Map<String,Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    String empNum = employeeData.getMP1001_EMPLOYEE_NUM();

	    try{
	        //----------------------------Operation History------------------
	        LogUtil logUtil = new LogUtil();
	        logUtil.setServiceMP0011(serviceMP0011);
	        logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME()," Search CHECKINOUT Information");
	        //----------------------------Operation History------------------
	        Map<String,String> propMap = getParamInfo();

	        getCHECKINOUTInfoByPage(propMap, 1,empNum);

	        return SUCCESS;
	    }catch(Exception ex){
	    	CustomerContextHolder.remove();//设置为另一个数据源  
	        log.info(ex.getMessage());
	        if(session.containsKey("ERR_MSG")){
	            session.remove("ERR_MSG");
	            session.put("ERR_MSG", ex.getMessage());
	        }
	        return "error";
	    }
	}
	// CHECKINOUT信息分页处理
	public String checkinoutInfoPageClick(){
	    ActionContext context = ActionContext.getContext();
	    Map<String,Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    String empNum = employeeData.getMP1001_EMPLOYEE_NUM();

	    try{
	        //----------------------------Operation History------------------
	        LogUtil logUtil = new LogUtil();
	        logUtil.setServiceMP0011(serviceMP0011);
	        logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"CHECKINOUT Information Page Click");
	        //----------------------------Operation History------------------

	        if(param1.equals("") || param2.equals("")){
	        	return null;
	        }else{
	        	Map<String,String> propMap = getParamInfo();

		        getCHECKINOUTInfoByPage(propMap,pageNum,empNum);
		        return SUCCESS;
	        }

	    }catch(Exception ex){
	    	CustomerContextHolder.remove();//设置为另一个数据源  
	        log.info(ex.getMessage());
	        if(session.containsKey("ERR_MSG")){
	            session.remove("ERR_MSG");
	            session.put("ERR_MSG", ex.getMessage());
	        }
	        return "error";
	    }
	}
	public String loadDataToHrSystem(){
	    ActionContext context = ActionContext.getContext();
	    Map<String,Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    String empNum = employeeData.getMP1001_EMPLOYEE_NUM();

	    try{
	        //----------------------------Operation History------------------
	        LogUtil logUtil = new LogUtil();
	        logUtil.setServiceMP0011(serviceMP0011);
	        logUtil.writeOperationLog(empNum,employeeData.getMP1001_PREFERED_NAME(),"Load Data To HR System");
	        //----------------------------Operation History------------------

	        if(param1.equals("") || param2.equals("")){
	        	return null;
	        }else{
		        Map<String,String> propMap = getParamInfo();

		        StringBuffer condition = new StringBuffer();
		        condition.append(" and MP2003_DATETIME >= '" + param1.substring(0,10) + "' ");
		        condition.append(" and MP2003_DATETIME <= '" + param2.substring(0,10) + "' ");
		        if(param3 != null && !param3.equals("")){
		        	condition.append(" and MP2003_EMPLOYEE_NUM = '" + param3 + "' ");
		        }
		        
		        CustomerContextHolder.setCustomerType("finger");
		        checkinoutInfoList = serviceCHECKINOUT.findByPropertyByPage(propMap, -1, -1);
		        CustomerContextHolder.remove();
		        
		        Map<String, CHECKINOUT> inoutMap = new HashMap<String, CHECKINOUT>();
		        CHECKINOUT checkObj = null;
		        for(CHECKINOUT checkinout : checkinoutInfoList){
		        	checkObj = new CHECKINOUT();
		        	checkObj.setKey(checkinout.getEMPLOYEE_NUM() + "#@" + checkinout.getCHECKTIME().substring(0,10));
		        	if(inoutMap.containsKey(checkObj.getKey())){
		        		checkObj = inoutMap.get(checkObj.getKey());
		        		checkObj.setDate2(checkinout.getCHECKTIME());
		        		checkObj.setDoor2(checkinout.getSENSORID());
		        		
		        		inoutMap.remove(checkObj.getKey());
		        	}else{
		        		checkObj.setDate1(checkinout.getCHECKTIME());
		        		checkObj.setDoor1(checkinout.getSENSORID());
		        	}
		        	inoutMap.put(checkObj.getKey(), checkObj);
		        }
		        
		        serviceMP2003.loadDataToHR(inoutMap, condition.toString());
		        
		        return SUCCESS;
	        }

	    }catch(Exception ex){
	    	CustomerContextHolder.remove();
	        log.info(ex.getMessage());
	        if(session.containsKey("ERR_MSG")){
	            session.remove("ERR_MSG");
	            session.put("ERR_MSG", ex.getMessage());
	        }
	        return "error";
	    }
	}
	public String calculateAttendanceData(){
	    ActionContext context = ActionContext.getContext();
	    Map<String,Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    String empNum = employeeData.getMP1001_EMPLOYEE_NUM();

	    try{
	        //----------------------------Operation History------------------
	        LogUtil logUtil = new LogUtil();
	        logUtil.setServiceMP0011(serviceMP0011);
	        logUtil.writeOperationLog(empNum,employeeData.getMP1001_PREFERED_NAME(),"Calculate Attendance Data");
	        //----------------------------Operation History------------------
	        
	    	executeJobs jobs = new executeJobs();
//	    	jobs.executeJob1();
	    	jobs.executeJob13();
	    	
	    	return SUCCESS;
	    }catch(Exception ex){
	    	CustomerContextHolder.remove();//设置为另一个数据源  
	        log.info(ex.getMessage());
	        if(session.containsKey("ERR_MSG")){
	            session.remove("ERR_MSG");
	            session.put("ERR_MSG", ex.getMessage());
	        }
	        return "error";
	    }
	}
	// 重新取得分页数据
	private void getCHECKINOUTInfoByPage(Map<String,String> propMap, int _pageNum, String empId){
		CustomerContextHolder.setCustomerType("finger");//设置数据源
		
	    checkinoutInfoList = serviceCHECKINOUT.findByPropertyByPage(propMap, -1, -1);
	    if( 0 == checkinoutInfoList.size()%Constant.PAGE_SIZE){
	        pageCount = checkinoutInfoList.size()/Constant.PAGE_SIZE;
	    }else{
	        pageCount = checkinoutInfoList.size()/Constant.PAGE_SIZE + 1;
	    }
	    if(_pageNum > pageCount){
	        _pageNum= pageCount;
	    }
	    checkinoutInfoList = serviceCHECKINOUT.findByPropertyByPage(propMap, _pageNum, Constant.PAGE_SIZE);
	    
	    CustomerContextHolder.remove();//移除数据源
	}
	// 检索参数
	private Map<String,String> getParamInfo(){
        Map<String,String> propMap = new HashMap<String,String>();
        propMap.put("PARAM1", param1 + " 00:00");
        propMap.put("PARAM2", param2 + " 23:59");
        propMap.put("PARAM3", param3);
        if(pageType != null && !pageType.equals("") && pageType.equals("T") ){
        	propMap.put("PARAM4", pageType);
        }
        return propMap;
	}
	private Map<String,MP1001> getMP1001Map(){
		List<MP1001> mp1001List = serviceMP1001.findData("","", -1, -1);
		Map<String,MP1001> mp1001Map = new HashMap<String,MP1001>();
		
		for(MP1001 mp11 : mp1001List){
			if(!mp1001Map.containsKey(mp11.getMP1001_EMPLOYEE_NUM())){
				mp1001Map.put(mp11.getMP1001_EMPLOYEE_NUM(), mp11);
			}
		}
		
		return mp1001Map;
	}
	private Map<String,MP1010> getMP1010Map(){
		List<MP1010> mp1010List = serviceMP1010.findByPropertyByPage( new HashMap<String, String>() , -1, -1);
		Map<String,MP1010> mp1010Map = new HashMap<String,MP1010>();
		
		for(MP1010 mp11 : mp1010List){
			if(!mp1010Map.containsKey(mp11.getMP1010_EMPLOYEE_NUM())){
				mp1010Map.put(mp11.getMP1010_EMPLOYEE_NUM(), mp11);
			}
		}
		
		return mp1010Map;
	}
	private List<CHECKINOUT> getExcelInfo(){
		CustomerContextHolder.setCustomerType("finger");
		
        Map<String,String> propMap = getParamInfo();
        List<CHECKINOUT> infoList = serviceCHECKINOUT.findByPropertyByPage(propMap, -1, -1);
        
        CustomerContextHolder.remove();
        
        
        MP1001 mp1001 = null;
        MP1010 mp1010 = null;
        for(CHECKINOUT checkObj : infoList){
        	 if(pageType != null && !pageType.equals("") && pageType.equals("T") ){
        		 Map<String,MP1010> mp1010Map = getMP1010Map();
        		 
              	if(mp1010Map.containsKey(checkObj.getEMPLOYEE_NUM())){
              		mp1010 = mp1010Map.get(checkObj.getEMPLOYEE_NUM());
             		checkObj.setEMPLOYEE_NAME(mp1010.getMP1010_PREFERED_NAME());
             		checkObj.setDEPARTMENT_NAME(mp1010.getMP1010_DEPARTMENT_NAME());
             	}
        	 }else{
        		 Map<String,MP1001> mp1001Map = getMP1001Map();
             	if(mp1001Map.containsKey(checkObj.getEMPLOYEE_NUM())){
            		mp1001 = mp1001Map.get(checkObj.getEMPLOYEE_NUM());
            		checkObj.setEMPLOYEE_NAME(mp1001.getMP1001_PREFERED_NAME());
            		checkObj.setDEPARTMENT_NAME(mp1001.getMP1001_DEPARTMENT_NAME());
            	}
        	 }
        }
        return infoList;
	}
	
	// Excel文档生成
	public String createCheckInOuteExcel(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Create CheckInOut Information Excel Document");
			//----------------------------Operation History------------------
			
			// 新建EXCEL工作表
			Workbook wb = ExcelUtil.CreateHSSFWorkBook();
			// 新建一个SHEET页面
			Sheet sheet = ExcelUtil.CreateSheet(wb, "Employee CheckInOut Informatiojn");
			// 设置SHEET页面属性
			ExcelUtil.SetSheetPropertyHSSF(sheet);
			// 获取预定的样式
			Map<String, CellStyle> styles = ExcelUtil.CreateStyles(wb);
			
			// Header标题
			String[] titles = {"ID", "EMPLOYEE NUMBER", "NAME", "DEPARTMENT", "DATE TIME","DOOR"};
			// 生成标题行
			float rowHeight = 27f;
			ExcelUtil.CreateHeadRow(sheet, titles, rowHeight, styles);
	        // 冻结第一行
	        sheet.createFreezePane(0, 1);
	        
	        int[] cellsWidth = {5,25,20,20,30,15};
	        // 设置单元格的宽度
	        ExcelUtil.SetCellsWidth(sheet, cellsWidth);
	        
	      //---------------------------主报表-----------------------------------------------------------------------------------------
	        // 取得报表数据
	        int count;
	        checkinoutInfoList = getExcelInfo();
	        String[] datas = new String[6];
	        for(int i=0,j=checkinoutInfoList.size(); i<j; i++){
	        	CHECKINOUT clockObj = checkinoutInfoList.get(i);
	        	count = i + 1;
	        	datas = new String[6];
	        	datas[0] = String.valueOf(count);
	        	datas[1] = clockObj.getEMPLOYEE_NUM();
	        	datas[2] = clockObj.getEMPLOYEE_NAME();
	        	datas[3] = clockObj.getDEPARTMENT_NAME();
	        	datas[4] = clockObj.getCHECKTIME();
	        	datas[5] = clockObj.getSENSORID();
	        			
	        	ExcelUtil.SetCellsValue(count, sheet, styles, datas);
	        }
	        
			// 生成Excel文件
	        fileName = "checkInOut.xls";
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


	/**
	 * @return the serviceCHECKINOUT
	 */
	public ICHECKINOUTService getServiceCHECKINOUT() {
		return serviceCHECKINOUT;
	}
	/**
	 * @param serviceCHECKINOUT the serviceCHECKINOUT to set
	 */
	public void setServiceCHECKINOUT(ICHECKINOUTService serviceCHECKINOUT) {
		this.serviceCHECKINOUT = serviceCHECKINOUT;
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
	 * @return the checkinoutInfoList
	 */
	public List<CHECKINOUT> getCheckinoutInfoList() {
		return checkinoutInfoList;
	}
	/**
	 * @param checkinoutInfoList the checkinoutInfoList to set
	 */
	public void setCheckinoutInfoList(List<CHECKINOUT> checkinoutInfoList) {
		this.checkinoutInfoList = checkinoutInfoList;
	}
	/**
	 * @return the serviceMP2003
	 */
	public IMP2003Service getServiceMP2003() {
		return serviceMP2003;
	}
	/**
	 * @param serviceMP2003 the serviceMP2003 to set
	 */
	public void setServiceMP2003(IMP2003Service serviceMP2003) {
		this.serviceMP2003 = serviceMP2003;
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

}
