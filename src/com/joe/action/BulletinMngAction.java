package com.joe.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;

import com.joe.common.Constant;
import com.joe.common.LogUtil;
import com.joe.common.Mail;
import com.joe.common.UtilCommon;
import com.joe.model.MP0002;
import com.joe.model.MP0005;
import com.joe.model.MP1001;
import com.joe.service.IAC0006Service;
import com.joe.service.IAC0007Service;
import com.joe.service.IAC0008Service;
import com.joe.service.IAC0009Service;
import com.joe.service.IMP0002Service;
import com.joe.service.IMP0005Service;
import com.joe.service.IMP0011Service;
import com.joe.service.IMP1001Service;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BulletinMngAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(BulletinMngAction.class);
	
	private IMP0005Service service;
	private IMP1001Service serviceMP1001;
	private IMP0002Service serviceMp0002;
	private IMP0011Service serviceMP0011;
	
	private IAC0006Service serviceAC0006;
	private IAC0007Service serviceAC0007;
	private IAC0008Service serviceAC0008;
	private IAC0009Service serviceAC0009;
	
	private MP0005 mp0005;
	private List<?> mp0005s;
	private int seq;
	private String type;
	private String departmentID;
	
	private List<MP0005> newBulletinInfo = new ArrayList<MP0005>();
	private List<MP0005> DailyBulletinInfo = new ArrayList<MP0005>();
	private List<MP0005> policyBulletinInfo = new ArrayList<MP0005>();
	//private Map<String,String> typeList = new HashMap<String,String>();

	private String group;
	
	//------------------权限控制用变量---------------
	private String optSave="0";
	private String optCancel="0";
	private String optApproval = "0";
	
	private String optSearch = "0";
	private String optDel = "0";
	private String optEdit = "0";
	private String optView = "0";
	
	private String optAll = "0";
	private String optDepartment = "0";
	private String optPersonal = "0";
	
	private String optPdf = "0";
	
	// 公告信息浏览
	public String bulletinList(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		// 取得登陆人信息a
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		group = employeeData.getMP1001_GROUP();
		departmentID = employeeData.getMP1001_DEPARTMENT_ID();
		
		try{			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"View Bulletin Information");
			//----------------------------Operation History------------------
			
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0003002,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_EDIT)){
				optEdit = "1";
			}
			if(optMap.containsKey(Constant.OPT_DELETE)){
				optDel = "1";
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
			
			log.debug("List bulletin info...");
			List<MP0002> departmentList = serviceMp0002.findAll(true);
			List<MP1001> employeeList = serviceMP1001.findAll();
			
			// 属性名
			String propertyName = "MP0005_TYPE";
			// 获取最新类公告信息
			newBulletinInfo = service.findByProperty(propertyName,"1");
			// 获取日常类公告信息
			DailyBulletinInfo = service.findByProperty(propertyName,"2");
			// 获取政策类公告信息
			policyBulletinInfo = service.findByProperty(propertyName,"3");
			
			convertIDtoName(employeeList, departmentList, newBulletinInfo);
			convertIDtoName(employeeList, departmentList, DailyBulletinInfo);
			convertIDtoName(employeeList, departmentList, policyBulletinInfo);
			
			//ActionContext context = ActionContext.getContext();
			//HttpServletRequest request = ServletActionContext.getRequest();
			//request.setAttribute("bulletins", allBulletinInfo);		
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	// 解析部门
	private void convertIDtoName(List<MP1001> employeeList, List<MP0002> departmentList, List<MP0005> bulletinInfo){
		
	    for(int i=0,j=bulletinInfo.size(); i<j; i++){
	    	MP0005 mp5 = bulletinInfo.get(i);
	    	int departId = mp5.getMP0005_DEPART_ID();
	    	String employeeNum = mp5.getMP0005_AUTHOR();
	    	boolean flag1 = false;
	    	boolean flag2 = false;
	    	
	    	if(true == flag1 && true == flag2){
	    		break;
	    	}
	    	
	    	// 解析部门
	    	for(int m=0,n=departmentList.size(); m<n&&false==flag1; m++){
	    		MP0002 mp2 = departmentList.get(m);
	    		int seq = mp2.getMP0002_SEQ();
	    		if(departId == seq){
	    			mp5.setMP0005_DEPART_NAME(mp2.getMP0002_DEPARTMENT_NAME());
	    			flag1 = true;
	    		}
	    	}
	    	// 解析发布人
	    	for(int u=0,v=employeeList.size(); u<v&&false==flag2; u++){
	    		MP1001 mp1001 = employeeList.get(u);
	    		String num = mp1001.getMP1001_EMPLOYEE_NUM();
	    		String name = mp1001.getMP1001_PREFERED_NAME();
	    		if(num.equals(employeeNum)){
	    			mp5.setMP0005_AUTHOR_NAME(name);
	    			flag2 = true;
	    		}
	    	}
	    }
	}
	
	// 公告类型初始化
	public String initBulletin() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Bulletin Page Init");
			//----------------------------Operation History------------------
			
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0003001,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_SAVE)){
				optSave = "1";
			}
			if(optMap.containsKey(Constant.OPT_CANCEL)){
				optCancel = "1";
			}
			
			if(type.equals("Edit")){
				mp0005 = service.findById(seq);
			}
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	// 新增公告信息
	public String addBulletinInfo() throws Exception{
		// 取得Session中缓存的用户信息
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		MP1001 employeeData = (MP1001) session.get("EMPLOYEE_DATA");
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Bulletin Information");
			//----------------------------Operation History------------------

			// 发布人（就是系统登录人）
			String employeeNum = employeeData.getMP1001_EMPLOYEE_NUM();
			// 发布人部门
			int departmentID = 6;// 默认为HR
			if(employeeData.getMP1001_DEPARTMENT_ID() != null && !employeeData.getMP1001_DEPARTMENT_ID().trim().equals("")){
				departmentID = Integer.parseInt(employeeData.getMP1001_DEPARTMENT_ID());
			}
			// 发布时间
			Date now = new Date();
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String bulletinTime = sdf.format(now);
			// 设定发布公告人、部门、发布时间
			mp0005.setMP0005_AUTHOR(employeeNum);
			mp0005.setMP0005_DEPART_ID(departmentID);
			mp0005.setMP0005_ADDTIME(bulletinTime);
			String content = mp0005.getMP0005_CONTENT().replace("\r\n", "<br/>");
			content = content.replace("'", "\'");
			mp0005.setMP0005_CONTENT(content);
			
			service.save(mp0005);
			
			Mail mail = new Mail();
			String toList = UtilCommon.getEmployeeMail(serviceMP1001);
			mail.setSubject(Constant.MESSAGE_TITLE);
			mail.setContent(Constant.MESSAGE_BODY);
			mail.setTo(toList);
			mail.send();
		
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	// 编辑公告信息
	public String editBulletinInfo() throws Exception{
		// 取得Session中缓存的用户信息
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		MP1001 employeeData = (MP1001) session.get("EMPLOYEE_DATA");
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Edit Bulletin Information");
			//----------------------------Operation History------------------
			
			// 发布人（就是系统登录人）
			String employeeNum = employeeData.getMP1001_EMPLOYEE_NUM();
			// 发布人部门
			int departmentID = 1;
			if(employeeData.getMP1001_DEPARTMENT_ID() != null && employeeData.getMP1001_DEPARTMENT_ID().trim().equals("")){
				departmentID = Integer.parseInt(employeeData.getMP1001_DEPARTMENT_ID());
			}
			// 发布时间
			Date now = new Date();
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String bulletinTime = sdf.format(now);
			// 设定发布公告人、部门、发布时间
			mp0005.setMP0005_AUTHOR(employeeNum);
			mp0005.setMP0005_DEPART_ID(departmentID);
			mp0005.setMP0005_ADDTIME(bulletinTime);
			String content = mp0005.getMP0005_CONTENT().replace("\r\n", "<br/>");
			mp0005.setMP0005_CONTENT(content);
			mp0005.setMP0005_SEQ(seq);
			
			service.update(mp0005);
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	// 删除公告信息
	public String delBulletin() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			if(type.equals("Del")){
				MP0005 entity = service.findById(seq);
				service.delete(entity);
				
				//----------------------------Operation History------------------
				LogUtil logUtil = new LogUtil();
				logUtil.setServiceMP0011(serviceMP0011);
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Delete Bulletin Information");
				//----------------------------Operation History------------------
			}
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	/**
	 * @return the service
	 */
	public IMP0005Service getService() {
		return service;
	}
	/**
	 * @param service the service to set
	 */
	public void setService(IMP0005Service service) {
		this.service = service;
	}
	/**
	 * @return the mp0005
	 */
	public MP0005 getMp0005() {
		return mp0005;
	}
	/**
	 * @param mp0005 the mp0005 to set
	 */
	public void setMp0005(MP0005 mp0005) {
		this.mp0005 = mp0005;
	}
	/**
	 * @return the mp0005s
	 */
	public List<?> getMp0005s() {
		return mp0005s;
	}
	/**
	 * @param mp0005s the mp0005s to set
	 */
	public void setMp0005s(List<?> mp0005s) {
		this.mp0005s = mp0005s;
	}
	/**
	 * @return the seq
	 */
	public int getSeq() {
		return seq;
	}
	/**
	 * @param seq the seq to set
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the newBulletinInfo
	 */
	public List<MP0005> getNewBulletinInfo() {
		return newBulletinInfo;
	}

	/**
	 * @param newBulletinInfo the newBulletinInfo to set
	 */
	public void setNewBulletinInfo(List<MP0005> newBulletinInfo) {
		this.newBulletinInfo = newBulletinInfo;
	}

	/**
	 * @return the dailyBulletinInfo
	 */
	public List<MP0005> getDailyBulletinInfo() {
		return DailyBulletinInfo;
	}
	
	/**
	 * @param dailyBulletinInfo the dailyBulletinInfo to set
	 */
	public void setDailyBulletinInfo(List<MP0005> dailyBulletinInfo) {
		DailyBulletinInfo = dailyBulletinInfo;
	}

	/**
	 * @return the policyBulletinInfo
	 */
	public List<MP0005> getPolicyBulletinInfo() {
		return policyBulletinInfo;
	}
	
	/**
	 * @param policyBulletinInfo the policyBulletinInfo to set
	 */
	public void setPolicyBulletinInfo(List<MP0005> policyBulletinInfo) {
		this.policyBulletinInfo = policyBulletinInfo;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
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
}
