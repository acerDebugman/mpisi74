package action;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import entity.AC0009;
import entity.MP0002;
import entity.MP0006;
import entity.MP0008;
import entity.MP0009;
import entity.MP1001;
import entity.MP1002;
import entity.MP1003;
import entity.MP1004;
import entity.MP1005;
import entity.MP1008;
import entity.MP1009;
import entity.MP1010;
import entity.MP2001;
import entity.MP2002;
import entity.MP2003;
import entity.MP2004;
import entity.MP2008;
import entity.MP7002;
import entity.MP7003;
import entity.MP7008;
import service.IAC0006Service;
import service.IAC0007Service;
import service.IAC0008Service;
import service.IAC0009Service;
import service.IMP0006Service;
import service.IMP0008Service;
import service.IMP0009Service;
import service.IMP0011Service;
import service.IMP1001Service;
import service.IMP1002Service;
import service.IMP1003Service;
import service.IMP1004Service;
import service.IMP1005Service;
import service.IMP1008Service;
import service.IMP1009Service;
import service.IMP1010Service;
import service.IMP2001Service;
import service.IMP2002Service;
import service.IMP2003Service;
import service.IMP2004Service;
import service.IMP7002Service;
import service.IMP7003Service;
import service.IMP7008Service;


import common.HeaderFooter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
import common.Constant;
import common.ExcelUtil;
import common.LogUtil;
import common.Mail;
import common.UtilCommon;
import common.UtilDate;

public class EmployeeMngAction extends ActionSupport implements ServletResponseAware {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(EmployeeMngAction.class);
	
	private IMP1001Service service;
	private IMP1002Service serviceMP1002;
	private IMP1003Service serviceMP1003;
	private IMP1004Service serviceMP1004;
	private IMP1005Service serviceMP1005;
	private IMP1008Service serviceMP1008;
	private IMP1009Service serviceMP1009;
	private IMP1010Service serviceMP1010;
	private IMP0006Service serviceMP0006;
	private IMP0008Service serviceMP0008;
	private IMP0009Service serviceMP0009;
	private IMP2002Service serviceMP2002;
	private IMP2001Service serviceMP2001;
	private IMP2003Service serviceMP2003;
	private IMP2004Service serviceMP2004;
	private IAC0006Service serviceAC0006;
	private IAC0007Service serviceAC0007;
	private IAC0008Service serviceAC0008;
	private IAC0009Service serviceAC0009;
	private IMP0011Service serviceMP0011;
	private IMP7008Service serviceMP7008;
	private IMP7003Service serviceMP7003;
	private IMP7002Service serviceMP7002;
	
	private MP1001 mp1001 = new MP1001();
	private MP1002 mp1002 = new MP1002();
	private MP1003 mp1003 = new MP1003();
	private MP1004 mp1004 = new MP1004();
	private MP1005 mp1005 = new MP1005();
	private MP1008 mp1008 = new MP1008();
	private MP1009 mp1009 = new MP1009();
	private MP1010 mp1010 = new MP1010();
	private MP0006 mp0006 = new MP0006();
	private MP0008 mp0008 = new MP0008();
	private MP0009 mp0009 = new MP0009();
	private MP2002 mp2002 = new MP2002();
	private MP2001 mp2001 = new MP2001();
	private MP2003 mp2003 = new MP2003();
	private MP2004 mp2004 = new MP2004();
	
	private List<?> mp1001s;
	private String employeeNum;
	private String employeeName;
	private String departmentID;
	private String departmentName;
	private String visaType;
	private String empStatus;
	private String educationHidden;
	private String workHidden;
	private String documentName;
	private String mp1009Seq;
	private String intervieweeID;
	private String intervieweeName;
	private String intervieweeSelectedID;
	private String interviewPhone;
	private String interviewTime;
	private String interviewer;
	private String pageType;
	private String mp2005Num;
	
	private List<MP0002> departmentList = new ArrayList<MP0002>();
	private List<MP1001> employeeInfo = new ArrayList<MP1001>();
	private List<MP1001> tmpEmployeeDataList = new ArrayList<MP1001>();
	private List<MP1002> educationInfoList = new ArrayList<MP1002>();
	private List<MP1002> educationInfoList_D = new ArrayList<MP1002>();
	private List<MP1003> companyInfoList = new ArrayList<MP1003>();
	private List<MP1005> contactInfoList = new ArrayList<MP1005>();
	private List<MP1001> employeeTelephoneList = new ArrayList<MP1001>();
	private List<MP1001> depSumList = new ArrayList<MP1001>();
	private List<MP1009> documentInfo = new ArrayList<MP1009>();
	private List<MP1008> interviewInfo = new ArrayList<MP1008>();
	private List<MP1004> departmentalTransfersInfo = new ArrayList<MP1004>();
	private List<MP1001> turnoverInfo = new ArrayList<MP1001>();
	private List<MP1010> mp1010InfoList = new ArrayList<MP1010>();
	
	private Map<String, String> groupList = new LinkedHashMap<String, String>();
	private Map<String, String> visaTypeList = new LinkedHashMap<String, String>();
	private Map<String, String> genderList = new LinkedHashMap<String, String>();
	private Map<String, String> marryStatusList = new LinkedHashMap<String, String>();
	private Map<String, String> institutionTypeList = new LinkedHashMap<String, String>();
	private Map<String, String> qualificationLevList = new LinkedHashMap<String, String>();
	private Map<String, String> topDegreeList = new LinkedHashMap<String, String>();
	private Map<String, String> departmentInfoList = new LinkedHashMap<String, String>();
	private Map<String, String> positionList = new LinkedHashMap<String, String>();
	private Map<String, String> employeeInfoList = new LinkedHashMap<String, String>();
	private Map<String, String> statusList = new LinkedHashMap<String, String>();
	private Map<String, String> resignTypeList = new LinkedHashMap<String, String>();
	private Map<String, String> birthdayList = new LinkedHashMap<String, String>();
	private Map<String,String> commonDepartmentList = new LinkedHashMap<String,String>();
	private String filePath = "";
	private String fileName = "";

	private String universityType;
	private String universityName;
	private String level;
	private String major;
	private String start;
	private String finish;
	private String rowIndex;
	
	private String companyName;
	private String depName;
	private String position;
	private String entryDate;
	private String terminationDate;
	private String description;
	private String reason;
	private String contact;
	// 页面错误提示信息
	private String errorMessage;
	private int pageNum;
	private int pageCount;
	private String group;
	private String param1;
	private String param2;
	private String param3;
	private String param4;
	
	// Struts2拦截器获得的文件名,命名规则，File的名字+FileName
	// 如此处为 'fileupload' + 'FileName' = 'fileuploadFileName'
	private File fileupload; // 和JSP中input标记name同名
	private String imageUrl;
	private String attachmentUrl;
	private String fileRealName;
	private HttpServletResponse response;
	private String fileuploadFileName; // 上传来的文件的名字
	//private String saveType; // 用来区分提交的内容
	private String eduSeq;
	private String expSeq;
	private String conSeq;
	private String saveFlag;
	private String loginPosition;
	private String loginDepartId;
	private String pageInitType; // 判断是否第一次（first）进入本页面，如果是第一次，则清空Session中KEY为SEARCH_CONDITION的值
	
	// Add by 2011/12/29
	private String gender;
	private String nationality;
	private String religion;
	private String race;
	private String passportNum;
	private String startingDate;
	private String birthday;
	private String qualification;
	private String birth;
	
	private String itServiceMail;
	private String directorServiceMail;
	private String commonSeq;

	private List<String>  empArray = new ArrayList<String>();
	//------------------权限控制用变量---------------
	private String optSave="0";
	private String optCancel="0";
	private String optApproval = "0";
	
	private String optSearch = "0";
	private String optAdd = "0";
	private String optEdit = "0";
	private String optDel = "0";
	private String optView = "0";
	private String optAdvanceSearch = "0";
	private String optAll = "0";
	private String optPersonal = "0";
	private String optDepartment = "0";
	
	private String optPdf = "0";
	private String optReset = "0";
	
    /* 
	* @getDownloadFile 此方法对应的是struts.xml文件中的： 
	* <param name="inputName">downloadFile</param> 返回下载文件的流，可以参看struts2的源码 
	*/  
	public InputStream getDownloadFile(){
        //return ServletActionContext.getServletContext().getResourceAsStream("/"+UploadConfigurationRead.getInstance().getConfigItem("uploadFilePath").trim()+"/" + fileName);
		return ServletActionContext.getServletContext().getResourceAsStream("/uploadfile/" + fileName);
	}
	public String documentDownloadEmp(){
		return SUCCESS;
	}
	
	public String serviceMailSetting(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Service Mail Setting");
			//----------------------------Operation History------------------
			
			// 设定IT服务人员的邮件
			if(itServiceMail != null && !itServiceMail.trim().equals("")){
				List<MP0006> mp06List = serviceMP0006.findByProperty("MP0006_CODE", "MAIL_LIST");
				if(mp06List != null && mp06List.size() > 0){
					MP0006 mp06 =mp06List.get(0);
					mp06.setMP0006_VALUE(itServiceMail);
					
					serviceMP0006.update(mp06);
				}
			}
			// 设定Director邮件地址
			if(directorServiceMail != null && !directorServiceMail.trim().equals("")){
				List<MP0006> mp06List = serviceMP0006.findByProperty("MP0006_CODE", "DIRECTOR_LIST");
				if(mp06List != null && mp06List.size() > 0){
					MP0006 mp06 =mp06List.get(0);
					mp06.setMP0006_VALUE(directorServiceMail);
					
					serviceMP0006.update(mp06);
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
	
	
	
	
	// 文档管理初始化页面
	public String DocumentMngInit(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Document Management Init");
			//----------------------------Operation History------------------
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	//文档检索
	public String documentListSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Document List Search");
			//----------------------------Operation History------------------
			
			documentInfo = serviceMP1009.findByProperty(employeeNum, employeeName, documentName, -1, -1);
			if( 0 == documentInfo.size()%Constant.PAGE_SIZE){
				pageCount = documentInfo.size()/Constant.PAGE_SIZE;
			}else{
				pageCount = documentInfo.size()/Constant.PAGE_SIZE + 1;
			}
			
			documentInfo = serviceMP1009.findByProperty(employeeNum, employeeName, documentName, 1, Constant.PAGE_SIZE);
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	//单击文档检索分页按钮后执行此操作
	public String documentDetailList(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Document Detail List");
			//----------------------------Operation History------------------
			
			documentInfo = serviceMP1009.findByProperty(employeeNum, employeeName, documentName, -1, -1);
			if( 0 == documentInfo.size()%Constant.PAGE_SIZE){
				pageCount = documentInfo.size()/Constant.PAGE_SIZE;
			}else{
				pageCount = documentInfo.size()/Constant.PAGE_SIZE + 1;
			}
			
			documentInfo = serviceMP1009.findByProperty(employeeNum, employeeName, documentName, pageNum, Constant.PAGE_SIZE);
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	//删除文档
	public String deleteDocumentInfo(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Delete Document Informaton");
			//----------------------------Operation History------------------

			if(mp1009Seq != null && !mp1009Seq.equals("")){
				MP1009 mp19 = serviceMP1009.findById(Integer.parseInt(mp1009Seq));
				
				if(mp19 != null){
					serviceMP1009.delete(mp19);
				}
			}
			
			documentInfo = serviceMP1009.findByProperty(employeeNum, employeeName, documentName, -1, -1);
			if( 0 == documentInfo.size()%Constant.PAGE_SIZE){
				pageCount = documentInfo.size()/Constant.PAGE_SIZE;
			}else{
				pageCount = documentInfo.size()/Constant.PAGE_SIZE + 1;
			}
			
			documentInfo = serviceMP1009.findByProperty(employeeNum, employeeName, documentName, pageNum, Constant.PAGE_SIZE);

			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	//初始化文档新增和编辑页面
	public String DocumentInfoInit(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Document Info Init");
			//----------------------------Operation History------------------
			
			if(mp2005Num != null && !mp2005Num.equals("")){
				mp1009.setMP1009_EMPLOYEE_NUM(mp2005Num);
			}
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	//保存上传文档
	public String documentInfoSave(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Document Info Init");
			//----------------------------Operation History------------------
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	// 上传文档
	public String uploadDocument(){
		try{
	        String extName = ""; // 保存文件拓展名
	        String newFileName = ""; // 保存新的文件名
	        String nowTimeStr = ""; // 保存当前时间
	        PrintWriter out = null;
	        SimpleDateFormat sDateFormat;  
	        Random r = new Random();  
	        String savePath = ServletActionContext.getServletContext().getRealPath(""); // 获取项目根路径
	        savePath = savePath + "\\uploadfile\\";  
	        HttpServletResponse response = ServletActionContext.getResponse();
	        response.setCharacterEncoding("utf-8"); // 务必，防止返回文件名是乱码
	        // 生成随机文件名：当前年月日时分秒+五位随机数（为了在实际项目中防止文件同名而进行的处理）
	        int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; // 获取随机数
	        sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); // 时间格式化的格式
	        nowTimeStr = sDateFormat.format(new Date()); // 当前时间

	        // 获取拓展名
	        if (fileuploadFileName.lastIndexOf(".") >= 0) {
	            extName = fileuploadFileName.substring(fileuploadFileName.lastIndexOf("."));  
	        }
	        
            out = response.getWriter();
            newFileName = nowTimeStr + rannum + extName; // 文件重命名后的名字
            String filePath = savePath + newFileName;
            
            FileUtils.copyFile(fileupload, new File(filePath));
            
            boolean retFlag = saveDocument(newFileName);
            if(retFlag){
                //检查上传的是否是图片
                //out.print("<font color='red'>" + fileuploadFileName + " upload success</font>");
            	out.print("<script type='text/javascript'>alert('upload success.');window.close();</script>");
            }else{
                //检查上传的是否是图片
                out.print("<font color='red'>" + fileuploadFileName + " upload failure</font>");
            }

            out.flush();
            out.close();
            
            return null;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	private boolean saveDocument(String _path){
		// 判断员工编码是否存在
		if(employeeNum == null || employeeNum.equals("")){
			return false;
		}
		// 判断文档名称是否存在
		if(documentName == null || documentName.equals("")){
			return false;
		}
		
		try{
			SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 时间格式化的格式
			String currTime = sDateFormat.format(new Date()); // 当前时间
			
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
			MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
			
			MP1009 mp19 = new MP1009();
			mp19.setMP1009_EMPLOYEE_NUM(employeeNum.toUpperCase());//员工编码
			mp19.setMP1009_DOCUMENT_NAME(documentName);//文档名称
			mp19.setMP1009_PATH(_path);
			mp19.setMP1009_UPLOAD_TIME(currTime);
			mp19.setMP1009_UPLOADER(employeeData.getMP1001_EMPLOYEE_NUM());
			
			serviceMP1009.save(mp19);

			return true;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return false;
		}
	}
	
	
	
	
	
	
	
	
	// 面试信息管理初始化页面 
	public String InterviewMngInit(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Interview Management Init");
			//----------------------------Operation History------------------
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	// CV检索
	public String interviewListSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Interview Data Search");
			//----------------------------Operation History------------------
			
			interviewInfo = serviceMP1008.findByProperty(intervieweeID, intervieweeName, -1, -1);
			if( 0 == interviewInfo.size()%Constant.PAGE_SIZE){
				pageCount = interviewInfo.size()/Constant.PAGE_SIZE;
			}else{
				pageCount = interviewInfo.size()/Constant.PAGE_SIZE + 1;
			}
			
			interviewInfo = serviceMP1008.findByProperty(intervieweeID, intervieweeName, 1, Constant.PAGE_SIZE);
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	// 分页按钮事件
	public String interviewDetailList(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Interview Detail Data List");
			//----------------------------Operation History------------------
			
			interviewInfo = serviceMP1008.findByProperty(intervieweeID, intervieweeName, -1, -1);
			if( 0 == interviewInfo.size()%Constant.PAGE_SIZE){
				pageCount = interviewInfo.size()/Constant.PAGE_SIZE;
			}else{
				pageCount = interviewInfo.size()/Constant.PAGE_SIZE + 1;
			}
			
			interviewInfo = serviceMP1008.findByProperty(intervieweeID, intervieweeName, pageNum, Constant.PAGE_SIZE);
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	//删除CV
	public String deleteInterviewInfo(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Delete Interview Data");
			//----------------------------Operation History------------------
			
			if(intervieweeSelectedID != null && !intervieweeSelectedID.equals("")){
				MP1008 mp18 = serviceMP1008.findById(intervieweeSelectedID);
				
				if(mp18 != null){
					serviceMP1008.delete(mp18);
				}
			}
			
			interviewInfo = serviceMP1008.findByProperty(intervieweeID, intervieweeName, -1, -1);
			if( 0 == interviewInfo.size()%Constant.PAGE_SIZE){
				pageCount = interviewInfo.size()/Constant.PAGE_SIZE;
			}else{
				pageCount = interviewInfo.size()/Constant.PAGE_SIZE + 1;
			}
			
			interviewInfo = serviceMP1008.findByProperty(intervieweeID, intervieweeName, pageNum, Constant.PAGE_SIZE);

			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	//初始化新增和编辑页面
	public String interviewInfoInit(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Interview Information Init");
			//----------------------------Operation History------------------
			
			// 编辑页面的情况
			if(pageType.equals("EDIT")){
				mp1008 = serviceMP1008.findById(intervieweeSelectedID);
			}
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	//保存CV信息
	public String interviewInfoSave() throws IOException{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			//----------------------------Operation History------------------
			
			if(mp1008.getMP1008_ID().equals("")){
				return "error";
			}
			
			if(pageType.equals("ADD")){
				MP1008 mp18 = new MP1008();
				
				mp18.setMP1008_ID(intervieweeID);
				mp18.setMP1008_NAME(intervieweeName);
				mp18.setMP1008_PHONE(interviewPhone);
				mp18.setMP1008_INTERVIEW_DATETIME(interviewTime);
				mp18.setMP1008_INTERVIEWER(interviewer);
				
				serviceMP1008.save(mp18);
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Interview Information Add");
			}else if(pageType.equals("EDIT")){
				if(intervieweeID == null || intervieweeID.equals("")){
					return "error";
				}
				MP1008 mp18 = serviceMP1008.findById(intervieweeSelectedID);
				if(mp18 !=null){
					mp18.setMP1008_ID(intervieweeID);
					mp18.setMP1008_INTERVIEW_DATETIME(interviewTime);
					mp18.setMP1008_INTERVIEWER(interviewer);
					mp18.setMP1008_NAME(intervieweeName);
					mp18.setMP1008_PHONE(interviewPhone);
					
					if(intervieweeSelectedID.equals(intervieweeID)){
						serviceMP1008.update(mp18);
					}else{
						serviceMP1008.update(mp18,intervieweeSelectedID);
					}
				}
				
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Interview Information Edit");
			}
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8"); // 务必，防止返回文件名是乱码
			PrintWriter out = response.getWriter();
			
			out.println("<script type='text/javascript'>window.dialogArguments.document.getElementById('refreshInterview').click();window.close();</script>");
			out.flush();
			out.close();
			
			return null;
		}catch(Exception ex){
			log.info(ex.getMessage());
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8"); // 务必，防止返回文件名是乱码
			PrintWriter out = response.getWriter();
			
			out.println("<script type='text/javascript'>window.close();</script>");
			out.flush();
			out.close();
			
			return "error";
		}
	}
	
	public String uploadCV(){
	    String extName = ""; // 保存文件拓展名
	    String newFileName = ""; // 保存新的文件名
	    String nowTimeStr = ""; // 保存当前时间
	    PrintWriter out = null;
	    SimpleDateFormat sDateFormat;  
	    Random r = new Random();  
	    String savePath = ServletActionContext.getServletContext().getRealPath(""); // 获取项目根路径
	    savePath = savePath + "\\uploadfile\\";  
	    HttpServletResponse response = ServletActionContext.getResponse();
	    response.setCharacterEncoding("utf-8"); // 务必，防止返回文件名是乱码
	    // 生成随机文件名：当前年月日时分秒+五位随机数（为了在实际项目中防止文件同名而进行的处理）
	    int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; // 获取随机数
	    sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); // 时间格式化的格式
	    nowTimeStr = sDateFormat.format(new Date()); // 当前时间
	    try{
	        // 获取拓展名
	        if (fileuploadFileName.lastIndexOf(".") >= 0) {  
	            extName = fileuploadFileName.substring(fileuploadFileName.lastIndexOf("."));  
	        }
	        
            out = response.getWriter();
            newFileName = nowTimeStr + rannum + extName; // 文件重命名后的名字
            String filePath = savePath + newFileName;
            
            FileUtils.copyFile(fileupload, new File(filePath));
            
            boolean retFlag = saveCV(newFileName);
            if(retFlag){
                //检查上传的是否是图片
                //out.print("<font color='red'>" + fileuploadFileName + " upload success</font>");
            	out.println("<script type='text/javascript'>window.dialogArguments.document.getElementById('refreshInterview').click();window.close();</script>");
            }else{
                //检查上传的是否是图片
                out.print("<font color='red'>" + fileuploadFileName + " upload failure</font>");
            }

            out.flush();
            out.close();
            
            return null;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	private boolean saveCV(String _path){
		if(intervieweeID == null || intervieweeID.equals("")){
			return false;
		}
		if(intervieweeName == null || intervieweeName.equals("")){
			return false;
		}
		if(interviewPhone == null || interviewPhone.equals("")){
			return false;
		}
		if(interviewTime == null || interviewTime.equals("")){
			return false;
		}
		if(interviewer == null || interviewer.equals("")){
			return false;
		}
		if(_path == null || _path.equals("")){
			return false;
		}
		if(pageType == null || pageType.equals("")){
			return false;
		}
		
		try{
			if(pageType.equals("ADD")){
				MP1008 mp18 = new MP1008();
				
				mp18.setMP1008_ID(intervieweeID);
				mp18.setMP1008_NAME(intervieweeName);
				mp18.setMP1008_PHONE(interviewPhone);
				mp18.setMP1008_INTERVIEW_DATETIME(interviewTime);
				mp18.setMP1008_INTERVIEWER(interviewer);
				mp18.setMP1008_CV_PATH(_path);
				
				serviceMP1008.save(mp18);
			}else if(pageType.equals("EDIT")){
				MP1008 mp18 = serviceMP1008.findById(intervieweeSelectedID);
				
				mp18.setMP1008_ID(intervieweeID);
				mp18.setMP1008_NAME(intervieweeName);
				mp18.setMP1008_PHONE(interviewPhone);
				mp18.setMP1008_INTERVIEW_DATETIME(interviewTime);
				mp18.setMP1008_INTERVIEWER(interviewer);
				mp18.setMP1008_CV_PATH(_path);
				if(intervieweeSelectedID.equals(intervieweeID)){
					serviceMP1008.update(mp18);
				}else{
					serviceMP1008.update(mp18,intervieweeSelectedID);
				}
			}
			return true;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return false;
		}
	}
	
	
	
	
	
	//取得公司总人数、正式员工人数、试用期软工人数
	public String headcountInit(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Headcount Data Init");
			//----------------------------Operation History------------------
			
			List<MP1001> mp11List = service.findAll();

			List<MP1001> employeeList = new ArrayList<MP1001>();
			List<MP1001> tmpEmployeeList = new ArrayList<MP1001>();
			for(int i=0,j=mp11List.size(); i<j; i++){
				MP1001 obj = mp11List.get(i);
				String status = obj.getMP1001_STATUS();
				
				if(status.equals("1")){
					tmpEmployeeList.add(obj);
				}else if(status.equals("2")){
					employeeList.add(obj);
				}
			}
			
			mp1001.setMP1001_HEADCOUNT_TOTAL(String.valueOf(mp11List.size()));
			mp1001.setMP1001_HEADCOUNT_EMPLOYEE(String.valueOf(employeeList.size()));
			mp1001.setMP1001_HEADCOUNT_TMP_EMPLOYEE(String.valueOf(tmpEmployeeList.size()));
			
			depSumList = service.getDepartmentHeadcount();
			
			MP1001 mp1001Obj = new MP1001();
			mp1001Obj.setMP1001_DEPARTMENT_NAME("");
			mp1001Obj.setMP1001_HEADCOUNT_DEPARTMENT("");
			depSumList.add(mp1001Obj);
			
			return SUCCESS;
		}catch(Exception ex){
            log.info(ex.getMessage());
            return "error";
		}
	}
	
	public String telInfoPdf(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		Calendar calendar = Calendar.getInstance();			
		fileName = String.valueOf(calendar.get(Calendar.YEAR)) + String.format("%02d",calendar.get(Calendar.MONTH) + 1) + String.format("%02d",calendar.get(Calendar.DAY_OF_MONTH)) + String.format("%02d",calendar.get(Calendar.HOUR_OF_DAY)) + String.format("%02d",calendar.get(Calendar.MINUTE)) + String.format("%02d",calendar.get(Calendar.SECOND)) + ".pdf";
		String _path = ServletActionContext.getServletContext().getRealPath("/") + "uploadfile\\" + fileName;
		
        try {
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Create Tel Information Pdf");
			//----------------------------Operation History------------------
			
        	employeeTelephoneList = service.findAll();
        	
        	Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        	document.addTitle("Mpisi Employee Telephone List");
        	document.addAuthor("Mpisi HRMS");
        	document.addSubject("Telephone List");
        	document.addKeywords("Mpisi, Telephone");
        	document.addCreator("HRMS");
        	
            // creation of the different writers
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(_path));
            writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));

            // Header&Footer
            HeaderFooter event = new HeaderFooter();
            writer.setPageEvent(event);

            // various fonts
            //BaseFont bf_helv = BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false);
            //BaseFont bf_times = BaseFont.createFont(BaseFont.TIMES_ROMAN, "Cp1252", false);
            //BaseFont bf_courier = BaseFont.createFont(BaseFont.COURIER, "Cp1252", false);
            //BaseFont bf_symbol = BaseFont.createFont(BaseFont.SYMBOL, "Cp1252", false);

            //int y_line1 = 650;
            //int y_line2 = y_line1 - 50;
            //int y_line3 = y_line2 - 50;

            document.open();
            
            //------------------------Employee Tel Info------------------------
            PdfPTable pdfTable = new PdfPTable(4);
            pdfTable.setSpacingBefore(20);
            pdfTable.getDefaultCell().setPadding(5);

            PdfPCell cell0 = new PdfPCell(new Phrase("Mpisi Employee Telephone List"));
            cell0.setPadding(5);
            cell0.setColspan(4);
            cell0.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell0.setBorderWidthLeft(0);
            cell0.setBorderWidthRight(0);
            cell0.setBorderWidthTop(0);
            pdfTable.addCell(cell0);
            pdfTable.setHeaderRows(1);
                        
            cell0 = new PdfPCell(new Phrase("Employee No"));
            cell0.setNoWrap(true);
            pdfTable.addCell(cell0);
            cell0 = new PdfPCell(new Phrase("Employee Name"));
            cell0.setNoWrap(true);
            pdfTable.addCell(cell0);
            cell0 = new PdfPCell(new Phrase("Telephone"));
            cell0.setNoWrap(true);
            pdfTable.addCell(cell0);
            cell0 = new PdfPCell(new Phrase("Mobile"));
            cell0.setNoWrap(true);
            pdfTable.addCell(cell0);

            pdfTable.setHeaderRows(2);
            
            for(int i=0,j=employeeTelephoneList.size(); i<j; i++){
            	MP1001 mp11 = employeeTelephoneList.get(i);
            	
            	pdfTable.addCell(mp11.getMP1001_EMPLOYEE_NUM());
            	pdfTable.addCell(mp11.getMP1001_PREFERED_NAME());
            	pdfTable.addCell(mp11.getMP1001_TELEPHONE());
            	pdfTable.addCell(mp11.getMP1001_MOBILE_PHONE());
            }
            
            document.add(pdfTable);
            //------------------------Employee Tel Info------------------------
            
            document.close();
            
    		//HttpServletResponse response = ServletActionContext.getResponse();  
            //response.setCharacterEncoding("utf-8"); // 务必，防止返回文件名是乱码
            //PrintWriter out = response.getWriter();
            //out.print(fileName);
            return SUCCESS;
        } catch (Exception ex) {
            log.info(ex.getMessage());
            return "error";
        }
	}
	
	public String resetPasswordInit() {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0003005,Constant.SYSTEM_NUM_HR);
		if(optMap.containsKey(Constant.OPT_SEARCH)){
			optSearch = "1";
		}
		if(optMap.containsKey(Constant.OPT_RESET)){
			optReset = "1";
		}
		
		//----------------------------Operation History------------------
		LogUtil logUtil = new LogUtil();
		logUtil.setServiceMP0011(serviceMP0011);
		logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Reset Password Page Init");
		//----------------------------Operation History------------------
		
		return SUCCESS;
	}
	
	public String resetPassword() throws IOException{
		try{
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
			MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Reset Password Employee Num:{" +employeeNum + "}");
			//----------------------------Operation History------------------
			
			mp1001 = service.findById(employeeNum);
			mp1001.setMP1001_PASSWORD("mpisi123"); // 重置密码为"mpisi123"
			
			service.update(mp1001);
			
			// 发邮件通知本人密码已经重置
			Mail mail = new Mail();
			String to = mp1001.getMP1001_COMPANY_EMAIL();
			mail.setSubject("HRMS Password updated");
			mail.setContent("Dear Colleagues,\r\n \r\n Please note that the your password has been reset.\r\n Employee No. : " + mp1001.getMP1001_EMPLOYEE_NUM() + "\r\n Password: mpisi123  \r\n Time:" + UtilDate.get24DateTime() + "\r\n \r\n Thank you very much! \r\n \r\n\r\n Your Faithfully, \r\n HRMS Administrator(" + employeeData.getMP1001_PREFERED_NAME() + ")");
			mail.setTo(to);
			mail.send();
			
	        HttpServletResponse response = ServletActionContext.getResponse();
	        response.setCharacterEncoding("utf-8"); // 务必，防止返回文件名是乱码
	        PrintWriter out = response.getWriter();
			
	        //out.println("<script type='text/javascript'>alert('Reset Success');</script>");
	        out.print("Reset Success");
	        out.flush();
	        out.close();
			
			return null;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public String employeePasswordSearch() throws ParseException{
		try{
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
			MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Employee Password Search");
			//----------------------------Operation History------------------
			
			HashMap<String, String> propertyMap = new HashMap<String, String>();
			propertyMap.put("NUM", employeeNum); // 员工编码
			propertyMap.put("NAME", employeeName); // 员工名
			
			employeeInfo = service.findByProperty(propertyMap);
			convertIDtoName(employeeInfo);
			
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0003005,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_SEARCH)){
				optSearch = "1";
			}
			if(optMap.containsKey(Constant.OPT_RESET)){
				optReset = "1";
			}
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public void validateEmployeePasswordSearch(){
		if(employeeNum.equals("") && employeeName.equals("")){
			addFieldError("employeeNum","employee id is empty.");
			addFieldError("employeeName","employee name is empty.");
		}
	}

	// 上传文件
	public String uploadFile() {
        String extName = ""; // 保存文件拓展名
        String newFileName = ""; // 保存新的文件名
        String nowTimeStr = ""; // 保存当前时间
        PrintWriter out = null;
        SimpleDateFormat sDateFormat;  
        Random r = new Random();  
        String savePath = ServletActionContext.getServletContext().getRealPath(""); // 获取项目根路径
        savePath = savePath + "\\uploadfile\\";  
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8"); // 务必，防止返回文件名是乱码
        // 生成随机文件名：当前年月日时分秒+五位随机数（为了在实际项目中防止文件同名而进行的处理）
        int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; // 获取随机数
        sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); // 时间格式化的格式
        nowTimeStr = sDateFormat.format(new Date()); // 当前时间
        
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		//----------------------------Operation History------------------
		LogUtil logUtil = new LogUtil();
		logUtil.setServiceMP0011(serviceMP0011);
		logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Upload File");
		//----------------------------Operation History------------------
        
        // 获取拓展名
        if (fileuploadFileName.lastIndexOf(".") >= 0) {  
            extName = fileuploadFileName.substring(fileuploadFileName.lastIndexOf("."));  
        }
        
        try {
            out = response.getWriter();
            newFileName = nowTimeStr + rannum + extName; // 文件重命名后的名字
            String filePath = savePath + newFileName;
            //filePath = filePath.replace("//", "/");
          //检查上传的是否是图片
            if (UtilCommon.checkIsImage(extName)) {
                FileUtils.copyFile(fileupload, new File(filePath));
                session.put("PICTURE_NAME", Constant.UPLOAD_FILE_DIR + newFileName);
                out.print("<font color='red'>" + fileuploadFileName + " upload success</font>#" + filePath + "#" + fileuploadFileName);
                log.info("File Name:" + filePath);
            } else {
                out.print("<font color='red'>File type must be jpg,jpeg,png,gif</font>");
                log.info("File type must be jpg,jpeg,png,gif");
            }
            out.flush();
            out.close();
            
            return null;
        }catch (IOException e) {
            log.info("upload failed.detail message：" + e.getMessage());
            return "error";
        }
    }
		
	// 显示上传的图片
    public String showImage() throws Exception {
    	try{
        	// 根据图片地址构造file对象
            File file = new File(imageUrl);
            InputStream is = new FileInputStream(file);
            Image image = ImageIO.read(is);// 读图片
            String imageType = imageUrl.substring(imageUrl.lastIndexOf(".") + 1);
            RenderedImage img = (RenderedImage) image;
            OutputStream out = response.getOutputStream();
            ImageIO.write(img, imageType, out);
            out.flush();
            out.close();
    	}catch(Exception ex){
    		log.info(ex.getMessage());
    	}
        return null;  
    }  

	// 上传图片(此方法废止)
	public String uploadPic() throws Exception{
		try{
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
			System.out.println("Upload File:" + filePath);
			int startIndex = filePath.lastIndexOf("\\") + 1;
			fileName = filePath.substring(startIndex);
			
			String path = ServletActionContext.getServletContext().getRealPath("/");
			System.out.println("Path:" + path);
			//String fileName = path + "\\images\\employeePic\\" + employeeNum + ".gif";
			String fName = path + "images\\employeePic\\" + fileName;
			System.out.println("fName:" + fName);
			File uploadFile = new File(filePath);
			File destFile = new File(fName);
			
			if(destFile.exists()){
				destFile.delete();
			}
			
			FileOutputStream fos = new FileOutputStream(fName);
			FileInputStream fis = new FileInputStream(uploadFile);
			byte[] b = new byte[1024];
			int length = 0;
			while ((length = fis.read(b)) > 0) {
				fos.write(b, 0, length);
			}
			
			session.put("PICTURE_NAME", fileName);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		return SUCCESS;
	}
	
	// 初始化页面List信息
	private void listInit(){
		// 签证类型
		visaTypeList.clear();
		visaTypeList = Constant.getVisaTypeList();
		
		// 性别
		genderList.clear();
		genderList = Constant.getGenderList();
		
		// 职位
		//positionList.clear();
		//positionList = Constant.getPositionList();
		
		// 婚姻状态
		marryStatusList.clear();
		marryStatusList = Constant.getMarryStatusList();
		
		// 学校类别
		institutionTypeList.clear();
		institutionTypeList = Constant.getInstitutionTypeList();
		
		//学校等级
		qualificationLevList.clear();
		qualificationLevList= Constant.getQualificationLevList();
		
		topDegreeList.clear();
		topDegreeList = Constant.getQualificationLevList();
		
		//权限Group
		groupList.clear();
		groupList = Constant.getGroupList();
		
		// 状态列表
		statusList.clear();
		statusList = Constant.getEmployeeStatusList();
		
		// 离职类型
		resignTypeList.clear();
		resignTypeList = Constant.getEmployeeResignType();
		
		//年月日
		birthdayList.clear();
		birthdayList = Constant.getYearMonthDay();
	}
	
	// 员工信息初始化
	@SuppressWarnings("unchecked")
	public String employeeAddInit() throws Exception{
		try{
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
			MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		    // 部门信息在登录后就缓存在Session中了
		    departmentList = (List<MP0002>) session.get("DEPARTMENT_LIST");
		    listInit();
		    
		  //----------------------------Operation History------------------
		    LogUtil logUtil = new LogUtil();
		    logUtil.setServiceMP0011(serviceMP0011);
		    logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Employee Add Init");
		    //----------------------------Operation History------------------
		    
			if(session.containsKey("SEARCH_CONDITION")){
				session.remove("SEARCH_CONDITION");
			}
			
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0001001,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_SAVE)){
				optSave = "1";
			}
			if(optMap.containsKey(Constant.OPT_CANCEL)){
				optCancel = "1";
			}
		    
		    List<MP0006> mp06List = serviceMP0006.findByProperty("MP0006_CODE", "EMP_NUM"); //正式员工编码
		    //List<MP0006> mp06List = serviceMP0006.findByProperty("MP0006_CODE", "EMP_NUM_T"); //非正式员工编码
		    MP0006 mp6 = mp06List.get(0);
		    int maxNum = Integer.parseInt(mp6.getMP0006_VALUE()) + 1;
		    java.text.DecimalFormat format = new java.text.DecimalFormat("0000");
		    String empNum = "M" + format.format(maxNum);
		    //String empNum = "T" + format.format(maxNum);
		    mp1001.setMP1001_EMPLOYEE_NUM(empNum);
			
		    mp6.setMP0006_VALUE(String.valueOf(maxNum));
		    session.put("MP0006", mp6);
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}

	// 员工编辑页面初始化
	public String employeeEditInit() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			log.debug("Employee Number:" + employeeNum);
			System.out.println("Employee Number:" + employeeNum);
			
			  //----------------------------Operation History------------------
		    LogUtil logUtil = new LogUtil();
		    logUtil.setServiceMP0011(serviceMP0011);
		    logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Employee Edit Init");
		    //----------------------------Operation History------------------
	        
			mp1001 = service.findById(employeeNum);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			listInit();
			
			// 初始化职位信息列表
			if(!mp1001.getMP1001_DEPARTMENT_ID().equals("")){
				positionInfoInit(mp1001.getMP1001_DEPARTMENT_ID());
			}
							
			// 解析部门
			String depName = getdepartmentNameById(mp1001.getMP1001_DEPARTMENT_ID());
			mp1001.setMP1001_DEPARTMENT_NAME(depName);
			// 解析性别(1:Male 2:Female)
			String gender = mp1001.getMP1001_GENDER();
			if(gender.equals("1")){
				mp1001.setMP1001_GENDER_NAME("Male");
			}else if(gender.equals("2")){
				mp1001.setMP1001_GENDER_NAME("Female");
			}else{
				mp1001.setMP1001_GENDER_NAME("-");
			}
			// 格式化时间
			if(mp1001.getMP1001_BIRTHDAY() != null && !mp1001.getMP1001_BIRTHDAY().equals("")){
				Date birthdayDate = sdf.parse(mp1001.getMP1001_BIRTHDAY());
				mp1001.setMP1001_BIRTHDAY(sdf.format(birthdayDate));
			}
			if(mp1001.getMP1001_ENTRY_DATE() != null && !mp1001.getMP1001_ENTRY_DATE().equals("")){
				Date entryDate = sdf.parse(mp1001.getMP1001_ENTRY_DATE());
				mp1001.setMP1001_ENTRY_DATE(sdf.format(entryDate));
			}
			if(mp1001.getMP1001_RESIGN_DATE() != null && !mp1001.getMP1001_RESIGN_DATE().equals("")){
				Date resignDate = sdf.parse(mp1001.getMP1001_RESIGN_DATE());
				mp1001.setMP1001_RESIGN_DATE(sdf.format(resignDate));
			}
			
			educationInfoList = serviceMP1002.findByProperty("MP1002_EMPLOYEE_NUM", employeeNum);
			// 格式化时间
			if(educationInfoList != null && educationInfoList.size() > 0){
			    Map<String,String> eduType = Constant.getInstitutionTypeList();
			    Map<String,String> eduLevel = Constant.getQualificationLevList();
			    
				for(int i=0,j=educationInfoList.size(); i<j; i++){
					MP1002 eduData = educationInfoList.get(i);
					String _type = eduData.getMP1002_INSTITUTION_TYPE();
				    String _level = eduData.getMP1002_QUALIFICATION_LEV();
					
				    // 入学时间
					if(eduData.getMP1002_START_DATETIME() != null && !eduData.getMP1002_START_DATETIME().equals("")){
						Date startDate = sdf.parse(eduData.getMP1002_START_DATETIME());
						eduData.setMP1002_START_DATETIME(sdf.format(startDate));
					}
					// 毕业时间
					if(eduData.getMP1002_FINISH_DATETIME() != null && !eduData.getMP1002_FINISH_DATETIME().equals("")){
						Date finishDate = sdf.parse(eduData.getMP1002_FINISH_DATETIME());
						eduData.setMP1002_FINISH_DATETIME(sdf.format(finishDate));
					}
					// 学校类型
					if(!_type.equals("") && eduType.containsKey(_type)){
						eduData.setMP1002_INSTITUTION_TYPE_NAME(eduType.get(_type));
					}
					// 学校等级
					if(!_level.equals("") && eduLevel.containsKey(_level)){
						eduData.setMP1002_QUALIFICATION_LEV_NAME(eduLevel.get(_level));
					}
				}
			}
			
			companyInfoList = serviceMP1003.findByProperty("MP1003_EMPLOYEE_NUM", employeeNum);
			// 格式化时间
			if(companyInfoList != null && companyInfoList.size() > 0){
				for(int m=0,n=companyInfoList.size(); m<n; m++){
					MP1003 comData = companyInfoList.get(m);
					
					if(comData.getMP1003_ENTRY_DATETIME() != null && !comData.getMP1003_ENTRY_DATETIME().equals("")){
						Date entryDate = sdf.parse(comData.getMP1003_ENTRY_DATETIME());
						comData.setMP1003_ENTRY_DATETIME(sdf.format(entryDate));
					}
					if(comData.getMP1003_TERMINATION_DATETIME() != null && !comData.getMP1003_TERMINATION_DATETIME().equals("")){
						Date terminDate = sdf.parse(comData.getMP1003_TERMINATION_DATETIME());
						comData.setMP1003_TERMINATION_DATETIME(sdf.format(terminDate));
					}
				}
			}
			
			// 取得要查询员工的紧急联系人信息列表
			contactInfoList = serviceMP1005.findByProperty("MP1005_EMPLOYEE_NUM", employeeNum);
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	// 变更部门初始化
	@SuppressWarnings("unchecked")
	public String changeDepartmentInit() throws Exception{
		try{
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
		    // 部门信息在登录后就缓存在Session中了
		    departmentList = (List<MP0002>) session.get("DEPARTMENT_LIST");
		    employeeNum = mp1001.getMP1001_EMPLOYEE_NUM();
		    // 取得登陆人信息
		    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		    
		  //----------------------------Operation History------------------
		    LogUtil logUtil = new LogUtil();
		    logUtil.setServiceMP0011(serviceMP0011);
		    logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Change Department Init");
		    //----------------------------Operation History------------------
		    
		    departmentInfoList.put("-1", "Please Select");
		    for(int i=0,j=departmentList.size(); i<j; i++){
		    	MP0002 mp02 = departmentList.get(i);
		    	departmentInfoList.put(String.valueOf(mp02.getMP0002_SEQ()), mp02.getMP0002_DEPARTMENT_NAME());
		    }
		    
		    mp1004.setMP1004_EMPLOYEE_NUM(mp1001.getMP1001_EMPLOYEE_NUM()); // 员工编码
		    mp1004.setMP1004_EMPLOYEE_NAME(mp1001.getMP1001_PREFERED_NAME()); // 员工名
		    mp1004.setMP1004_DEPARTMENT_CODE(mp1001.getMP1001_DEPARTMENT_ID()); // 部门编码
		    mp1004.setMP1004_POSITION_OLD(mp1001.getMP1001_POSITION()); // 变动前部门
		    
		    if(mp1001.getMP1001_POSITION() != null && !mp1001.getMP1001_POSITION().equals("")){
		    	String posName = getPositionName(mp1001.getMP1001_DEPARTMENT_ID(),mp1001.getMP1001_POSITION());
		    	mp1004.setMP1004_POSITION_OLD_NAME(posName);
		    }else{
		    	mp1004.setMP1004_POSITION_OLD_NAME("");
		    }
		    
		    //for(int i=0,j=positionList.size(); i <j; i++){
		    //	MP0008 mp8 = positionList.get(i);
		    //	if(mp1001.getMP1001_POSITION() != null && !mp1001.getMP1001_POSITION().equals("") && mp8.getMP0008_POSITION_ID() == Integer.parseInt(mp1001.getMP1001_POSITION())){
		    //		mp1004.setMP1004_POSITION_OLD_NAME(mp8.getMP0008_POSITION_NAME_E());
		    //		break;
		    //	}
		    //}
		    //if(positionList.containsKey(mp1001.getMP1001_POSITION())){
		    //	mp1004.setMP1004_POSITION_OLD_NAME(positionList.get(mp1001.getMP1001_POSITION()));
		    //}
		    
		    mp1004.setMP1004_DEPARTMENT_NAME_OLD(mp1001.getMP1001_DEPARTMENT_NAME()); // 部门名称
		    
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	// 根据职位ID解析职位名
	public String getPositionName(String depId,String positionId){
		//positionList = serviceMP0008.findByProperty("MP0008_DEPARTMENT_ID", depId);
		List<MP0008> list = serviceMP0008.findByDepartmentId(depId);
		String positionName = "";
		
		for(int i=0,j=list.size();i<j;i++){
			MP0008 obj = list.get(i);
			String mp08PositionId = String.valueOf(obj.getMP0008_POSITION_ID());
			if(mp08PositionId.equals(positionId)){
				positionName = obj.getMP0008_POSITION_NAME_E();
				break;
			}
		}
	    
	    return positionName;
	}
	
	// 保存部门变动
	public String changeDepartment() throws Exception{
		try{
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
			MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Change Department");
			//----------------------------Operation History------------------
			
			if(mp1004.getMP1004_EMPLOYEE_NUM().equals("")){
				return "error";
			}else{
				MP1001 mp11 = service.findById(mp1004.getMP1004_EMPLOYEE_NUM());
				
				Date now = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String bulletinTime = sdf.format(now);
				
				mp11.setMP1001_POSITION(mp1004.getMP1004_POSITION());
				mp11.setMP1001_DEPARTMENT_ID(mp1004.getMP1004_DEPARTMENT_CODE());
				
				mp1004.setMP1004_CREATE_DATETIME(bulletinTime);
				mp1004.setMP1004_CREATE_USER(employeeData.getMP1001_EMPLOYEE_NUM());
				
				service.update(mp11); // 更新用户部门和职位
				serviceMP1004.save(mp1004); // 保存部门历史信息
			}
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	// 新增员工基本信息
	public String employeeAdd() throws Exception{
		try{
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
			
			MP0006 mp6 = (MP0006) session.get("MP0006");
			MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
			String loginName = employeeData.getMP1001_EMPLOYEE_NUM();
			listInit();
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Employee");
			//----------------------------Operation History------------------
			
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String bulletinTime = sdf.format(now);
			
			// 员工基本信息
			mp1001.setMP1001_CREATE_USER(loginName);
			mp1001.setMP1001_CREATE_DATE(bulletinTime);
			mp1001.setMP1001_PASSWORD("mpisi123");
			mp1001.setMP1001_PASSWORD_DATE(bulletinTime);
			mp1001.setMP1001_STATUS("1");
			mp1001.setMP1001_ANNUAL_STATUS("1"); // 1:每个月可以累加年假  0:每个月不可以累加年假
			//mp1001.setMP1001_GROUP();
			String picName = "";
			if(session.containsKey("PICTURE_NAME")){
				picName = session.get("PICTURE_NAME").toString();
			}
			mp1001.setMP1001_PICTURE_NAME(picName);
			
			// 保存员工基本信息
			service.save(mp1001);
			saveFlag = "true";
			
/*			MP1004 mp14 = new MP1004();
			mp14.setMP1004_EMPLOYEE_NUM(mp1001.getMP1001_EMPLOYEE_NUM());//Employee Number
			mp14.setMP1004_DEPARTMENT_CODE(mp1001.getMP1001_DEPARTMENT_ID());//Department ID
			mp14.setMP1004_DEPARTMENT_NAME(mp1001.getMP1001_DEPARTMENT_NAME());//Department Name
			mp14.setMP1004_POSITION(mp1001.getMP1001_POSITION());//Position
			mp14.setMP1004_TRANSFER_DATETIME(mp1001.getMP1001_ENTRY_DATE());//transfer date
			mp14.setMP1004_JOB_DESC("");
			serviceMP1004.save(mp14);*/

			// 更新员工 编号值
			serviceMP0006.update(mp6);
			// 更新员工假期数据
			insertVacationData(mp1001.getMP1001_EMPLOYEE_NUM(),mp1001.getMP1001_ENTRY_DATE(),mp1001.getMP1001_DEPARTMENT_ID(),mp1001.getMP1001_POSITION());
			// 分配基本员工权限
			AC0009 ac09 = new AC0009();
			ac09.setAC0009_EMPLOYEE_NUM(mp1001.getMP1001_EMPLOYEE_NUM());
			ac09.setAC0009_ROLE_NUM("R1002");
			serviceAC0009.save(ac09);
			
			addFieldError("errorMessage","Save Success");
			positionInfoInit(mp1001.getMP1001_DEPARTMENT_ID());
			
			session.remove("PICTURE_NAME");
			
			return SUCCESS;
		}catch(ParseException pe){
			log.debug(pe.getMessage());
			return "error";
		}
		catch(Exception ex){
			log.debug(ex.getMessage());
			return "error";
		}
	}
	
	private boolean isCurrentYear(String startsDate){
		boolean ret = true;
		int cur_year = Integer.parseInt(UtilCommon.getFinanceYear());
		int entry_year = Integer.parseInt(UtilCommon.getFinanceYear(startsDate));
		
		if( entry_year < cur_year){
			ret = false;
		}
		
		return ret;
	}
	
	private boolean isHolidayOrWeekend(String startsDate) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		boolean ret = false;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date _start = sdf.parse(startsDate);
		calendar.setTime(_start);
		
		if(true == UtilCommon.isWeekEnd(calendar)){
			ret = true;
		}
		if(true == UtilCommon.isHoliday(calendar)){
			ret = true;
		}
		
		return ret;
	}
	
	private boolean checkDate(int _day,String startsDate) throws ParseException{
		String yearMonth = startsDate.substring(0,8);
		
		// 如果1号，直接结束
		if(1==_day){
			return true;
		}else if(2 == _day && true == isHolidayOrWeekend(yearMonth + "01") ){
			// 如果是2号，但是1号为公共假日，则结束判断
			return true;
		}else if(3 == _day && true == isHolidayOrWeekend(yearMonth + "01") && true == isHolidayOrWeekend(yearMonth + "02") ){
			// 如果是3号，但是1号、2号为公共假日，则结束判断
			return true;
		}else if(4 == _day && true == isHolidayOrWeekend(yearMonth + "01") && true == isHolidayOrWeekend(yearMonth + "02") && true == isHolidayOrWeekend(yearMonth + "03") ){
			// 如果是4号，但是1号、2号、3号为公共假日，则结束判断
			return true;
		}
		return false;
	}
	
	private void insertVacationData(String empNum,String startsDate,String department,String position) throws ParseException{
		Calendar calendar1 = new GregorianCalendar();

		int _month_c = calendar1.get(Calendar.MONTH);
		//int _day_c = calendar1.get(Calendar.DAY_OF_MONTH);
		
		int _month = Integer.parseInt(startsDate.substring(5,7)) - 1;
		int _day = Integer.parseInt(startsDate.substring(8,10));
		
		MP2002 mp22 = new MP2002();
		mp22.setMP2002_EMPLOYEE_NUM(empNum);
		
		boolean ret = isCurrentYear(startsDate);
		int annualDays = 0;
		// 如果前一年已经入职
		if(ret == false){
			_month = 3;
			_day =1;
		}

		int everyDays = 10;
		if(department.equals("12") || position.equals("136")){
			everyDays = 14;
		}
		annualDays = (_month_c - _month)*everyDays;
		
		// 如果入职日期为12月份，年假加30小时；如果入职日期为1月份，年假加20小时；其它月份，年假加10小时。（如果每个月的第一天入职，则给1.25天，否则当月不给年假）
		// 因为每年12月份会把12月份，1月份，2月份的假期全部给员工。
		//if(11 == _month){//12月份
		if(10 == _month){ //November ---changed by Joe zhang @2013-12-13, 'cause I distribute the vacation of four month 11,12,1,2 @the last day of November. 
			if(true == checkDate(_day,startsDate)){
				//continue;
			}else{
				annualDays = annualDays - 10;
			}
		}else if(11 == _month){ //@December
			if(true == checkDate(_day, startsDate)){
				annualDays = annualDays + 30; 
			}else{
				annualDays = annualDays + 20;
			}
		}
		else if(0 == _month){//1月份
			if(true == checkDate(_day,startsDate)){
				annualDays = annualDays + 20;
			}else{
				annualDays = annualDays + 10;
			}
		}else if(1 == _month){//2月份
			if(true == checkDate(_day,startsDate)){
				annualDays = annualDays + 10;
			}else{
				//continue;
			}
		}else {
			if(true == checkDate(_day,startsDate)){
				//continue;
			}else{
				annualDays = annualDays - 10;
			}
		}
		
		mp22.setMP2002_ANNUAL(String.valueOf(annualDays));
		mp22.setMP2002_ANNUAL_T(String.valueOf(annualDays));
		
		//如果入职月份为三月份，病假11*8天；四月份10*8小时；五月份9*8；六月份8*8；七月份7*8；八月份6*8；九月份5*8；十月份4*8；十一月份3*8；十二月份2*8；一月份1*8；二月份0*8；
		String _sickDays = "0";
		if(ret == false){
			_sickDays = "96";
		}else{
			if(2 == _month){//三月份
				_sickDays = String.valueOf(11*8);
			}else if(3 == _month){//四月份
				_sickDays = String.valueOf(10*8);
			}else if(4 == _month){//五月份
				_sickDays = String.valueOf(9*8);
			}else if(5 == _month){//六月份
				_sickDays = String.valueOf(8*8);
			}else if(6 == _month){//七月份
				_sickDays = String.valueOf(7*8);
			}else if(7 == _month){//八月份
				_sickDays = String.valueOf(6*8);
			}else if(8 == _month){//九月份
				_sickDays = String.valueOf(5*8);
			}else if(9 == _month){//十月份
				_sickDays = String.valueOf(4*8);
			}else if(10 == _month){//十一月份
				_sickDays = String.valueOf(3*8);
			}else if(11 == _month){//十二月份
				_sickDays = String.valueOf(2*8);
			}else if(0 == _month){//一月份
				_sickDays = String.valueOf(1*8);
			}else if(1 == _month){//二月份
				_sickDays = String.valueOf(0*8);
			}
		}
		mp22.setMP2002_SICK(_sickDays);
		mp22.setMP2002_SICK_T(_sickDays);
		
		mp22.setMP2002_FAMILY_RESP("24");
		mp22.setMP2002_FAMILY_RESP_T("24");
		mp22.setMP2002_MATERNITY("0");
		mp22.setMP2002_MATERNITY_T("0");
		mp22.setMP2002_STUDY("0");
		mp22.setMP2002_STUDY_T("0");
		
		mp22.setMP2002_YEAR(UtilCommon.getFinanceYear());
		
		serviceMP2002.save(mp22);
	}

	@SuppressWarnings("unchecked")
	private boolean validEmployeeInfo(){
		boolean retflag = true;

		// 验证员工编码（不能为空、最大程度为30）
		if(mp1001.getMP1001_EMPLOYEE_ID().equals("")){
			addFieldError("mp1001.MP1001_EMPLOYEE_ID","employee id is empty.");
			retflag = false;
		}else if(mp1001.getMP1001_EMPLOYEE_ID().length() > 30){
			addFieldError("mp1001.MP1001_EMPLOYEE_ID","max length is 30");
			retflag = false;
		}
		// 验证员工名（不能为空、最大程度为50）
		if(mp1001.getMP1001_FIRSTNAME().equals("")){
			addFieldError("mp1001.MP1001_FIRSTNAME","first name is empty.");
			retflag = false;
		}else if(mp1001.getMP1001_FIRSTNAME().length() > 50){
			addFieldError("mp1001.MP1001_FIRSTNAME","max length is 50");
			retflag = false;
		}
		// 验证员工姓（不能为空、最大程度为50）
		if(mp1001.getMP1001_SURNAME().equals("")){
			addFieldError("mp1001.MP1001_SURNAME","surname is empty.");
			retflag = false;
		}else if(mp1001.getMP1001_SURNAME().length() > 50){
			addFieldError("mp1001.getMP1001_SURNAME","max length is 50");
			retflag = false;
		}
		// 验证员工英文名（不能为空、最大程度为50）
		if(mp1001.getMP1001_PREFERED_NAME().equals("")){
			addFieldError("mp1001.MP1001_PREFERED_NAME","preffered name is empty.");
			retflag = false;
		}else if(mp1001.getMP1001_PREFERED_NAME().length() > 50){
			addFieldError("mp1001.getMP1001_PREFERED_NAME","max length is 50");
			retflag = false;
		}
		// 验证员工签证类型（不能为空）
		if(mp1001.getMP1001_VISA_TYPE().equals("0")){
			addFieldError("mp1001.MP1001_VISA_TYPE","employee type is empty.");
			retflag = false;
		}
		// 验证员工所属部门（不能为空）
		if(mp1001.getMP1001_DEPARTMENT_ID().equals("") || mp1001.getMP1001_DEPARTMENT_NAME().equals("")){
			addFieldError("mp1001.MP1001_DEPARTMENT_NAME","department is empty.");
			retflag = false;
		}
		// 验证员工职位
		if(mp1001.getMP1001_POSITION() == null || mp1001.getMP1001_POSITION().equals("0")){
			addFieldError("mp1001.MP1001_POSITION","position is empty.");
			retflag = false;
		}
		// 验证邮箱地址
		//"^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$"
		String check = "^([a-z0-9A-Z]+[_-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(check);
		String email = mp1001.getMP1001_EMAIL();
		if(email != null && !email.trim().equals("")){
			Matcher matcher = regex.matcher(email);
			if(!matcher.matches()){
				addFieldError("mp1001.MP1001_EMAIL","E-mail's format is incorrect");
				retflag = false;
			}
		}
		// 验证手机号码
		String phone = mp1001.getMP1001_MOBILE_PHONE();
		String phoneCheck = "[0-9]{1,50}";
		Pattern p = Pattern.compile(phoneCheck);
		if(phone != null && !phone.trim().equals("")){
			Matcher matcher = p.matcher(phone);
			if(!matcher.matches()){
				addFieldError("mp1001.MP1001_MOBILE_PHONE","mobil phone's format is incorrect");
				retflag = false;
			}
		}
		// 验证入职日期
		if(mp1001.getMP1001_ENTRY_DATE().equals("")){
			addFieldError("mp1001.MP1001_ENTRY_DATE","starting date is empty.");
			retflag = false;
		}
		
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
	    // 部门信息在登录后就缓存在Session中了
	    departmentList = (List<MP0002>) session.get("DEPARTMENT_LIST");
		listInit();
		
		if(!mp1001.getMP1001_DEPARTMENT_ID().equals("")){
			positionInfoInit(mp1001.getMP1001_DEPARTMENT_ID());
		}
		
		return retflag;
	}
	
	// 校验新增员工基本信息
	public void validateEmployeeAdd(){
		validEmployeeInfo();
	}
	
	// 新增员工基本信息
	public String employeeEdit() throws Exception{
		try{
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
			
			MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
			String loginName = employeeData.getMP1001_EMPLOYEE_NUM();
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Edit Employee: {" + mp1001.getMP1001_EMPLOYEE_NUM() + "}");
			//----------------------------Operation History------------------
			
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String editTime = sdf.format(now);
			
			// 员工基本信息
			MP1001 mp1Data = service.findById(mp1001.getMP1001_EMPLOYEE_NUM());
			mp1001.setMP1001_EDIT_USER(loginName);
			mp1001.setMP1001_EDIT_DATE(editTime);
			
			String picName =  "";
			if(session.containsKey("PICTURE_NAME")){
				picName = session.get("PICTURE_NAME").toString();
			}else{
				picName = mp1Data.getMP1001_PICTURE_NAME();
			}
			
			mp1001.setMP1001_PICTURE_NAME(picName);
			//mp1001.setMP1001_STATUS(mp1Data.getMP1001_STATUS());
			mp1001.setMP1001_PASSWORD(mp1Data.getMP1001_PASSWORD());
			mp1001.setMP1001_PASSWORD_DATE(mp1Data.getMP1001_PASSWORD_DATE());
			mp1001.setMP1001_CREATE_USER(mp1Data.getMP1001_CREATE_USER());
			mp1001.setMP1001_CREATE_DATE(mp1Data.getMP1001_CREATE_DATE());
			// 如果离职，则离职时间为当前时间
			if(mp1001.getMP1001_STATUS().equals("3")){
				//add log who edit this !!
				//----------------------------Operation History------------------
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Edit Employee: Unmployee {" + mp1001.getMP1001_EMPLOYEE_NUM() + "}");
				//----------------------------Operation History------------------
				if(mp1001.getMP1001_RESIGN_DATE() != null && !mp1001.getMP1001_RESIGN_DATE().equals("")){
					// nothing to do
				}else{
					mp1001.setMP1001_RESIGN_DATE(editTime);
				}
			}else{
				mp1001.setMP1001_RESIGN_DATE(null); // 离职时间
				mp1001.setMP1001_RESIGN_REASON(""); // 离职原因
				mp1001.setMP1001_RESIGN_TYPE(""); // 离职类型(正常离职和非正常离职)
			}
			
			// 保存员工基本信息
			service.update(mp1001);
			
			// 如果换部门，则需要往部门经历表中插入一条记录
			if(!mp1Data.getMP1001_DEPARTMENT_ID().equals(mp1001.getMP1001_DEPARTMENT_ID())){
				MP1004 mp14 = new MP1004();
				mp14.setMP1004_EMPLOYEE_NUM(mp1001.getMP1001_EMPLOYEE_NUM());//Employee Number
				mp14.setMP1004_DEPARTMENT_CODE(mp1001.getMP1001_DEPARTMENT_ID());//Department ID
				mp14.setMP1004_DEPARTMENT_NAME(mp1001.getMP1001_DEPARTMENT_NAME());//Department Name
				mp14.setMP1004_POSITION(mp1001.getMP1001_POSITION());//Position
				mp14.setMP1004_TRANSFER_DATETIME(UtilDate.get24DateTime());//transfer date
				mp14.setMP1004_JOB_DESC("");
				mp14.setMP1004_DEPARTMENT_CODE_OLD(mp1Data.getMP1001_DEPARTMENT_ID());
				mp14.setMP1004_POSITION_OLD(mp1Data.getMP1001_POSITION());
				
				serviceMP1004.save(mp14);
			}
			
			if(!mp1001.getMP1001_DEPARTMENT_ID().equals("")){
				positionInfoInit(mp1001.getMP1001_DEPARTMENT_ID());
			}
			
			session.remove("PICTURE_NAME");
			errorMessage = "Save Success";
		}catch(Exception ex){
			log.debug(ex.getMessage());
			errorMessage = "Save Failure:" + ex.getMessage();
			return INPUT;
		}
		return SUCCESS;
	}
	
	// 校验新增员工基本信息
	@SuppressWarnings("unchecked")
	public void validateEmployeeEdit(){ 
		// 验证员工编码（不能为空、最大程度为30）
		if(mp1001.getMP1001_EMPLOYEE_ID().equals("")){
			addFieldError("mp1001.MP1001_EMPLOYEE_ID","employee id is empty.");
		}else if(mp1001.getMP1001_EMPLOYEE_ID().length() > 30){
			addFieldError("mp1001.MP1001_EMPLOYEE_ID","max length is 30");
		}
		// 验证员工名（不能为空、最大程度为50）
		if(mp1001.getMP1001_FIRSTNAME().equals("")){
			addFieldError("mp1001.MP1001_FIRSTNAME","first name is empty.");
		}else if(mp1001.getMP1001_FIRSTNAME().length() > 50){
			addFieldError("mp1001.MP1001_FIRSTNAME","max length is 50");
		}
		// 验证员工姓（不能为空、最大程度为50）
		if(mp1001.getMP1001_SURNAME().equals("")){
			addFieldError("mp1001.MP1001_SURNAME","surname is empty.");
		}else if(mp1001.getMP1001_SURNAME().length() > 50){
			addFieldError("mp1001.getMP1001_SURNAME","max length is 50");
		}
		// 验证员工英文名（不能为空、最大程度为50）
		if(mp1001.getMP1001_PREFERED_NAME().equals("")){
			addFieldError("mp1001.MP1001_PREFERED_NAME","preffered name is empty.");
		}else if(mp1001.getMP1001_PREFERED_NAME().length() > 50){
			addFieldError("mp1001.getMP1001_PREFERED_NAME","max length is 50");
		}
		// 验证员工签证类型（不能为空）
		if(mp1001.getMP1001_VISA_TYPE().equals("0")){
			addFieldError("mp1001.MP1001_VISA_TYPE","employee type is empty.");
		}
		// 验证员工所属部门（不能为空）
		if(mp1001.getMP1001_DEPARTMENT_ID().equals("") || mp1001.getMP1001_DEPARTMENT_NAME().equals("")){
			addFieldError("mp1001.MP1001_DEPARTMENT_NAME","department is empty.");
		}
		// 验证员工职位
		if(mp1001.getMP1001_POSITION() == null || mp1001.getMP1001_POSITION().equals("0")){
			addFieldError("mp1001.MP1001_POSITION","position is empty.");
		}
		// 验证邮箱地址
		//"^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$"
		String check = "^([a-z0-9A-Z]+[_-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(check);
		String email = mp1001.getMP1001_EMAIL();
		if(email != null && !email.trim().equals("")){
			Matcher matcher = regex.matcher(email);
			if(!matcher.matches()){
				addFieldError("mp1001.MP1001_EMAIL","E-mail's format is incorrect");
			}
		}
		// 验证手机号码
		String phone = mp1001.getMP1001_MOBILE_PHONE();
		String phoneCheck = "[0-9]{1,50}";
		Pattern p = Pattern.compile(phoneCheck);
		if(phone != null && !phone.trim().equals("")){
			Matcher matcher = p.matcher(phone);
			if(!matcher.matches()){
				addFieldError("mp1001.MP1001_MOBILE_PHONE","mobil phone's format is incorrect");
			}
		}
		// 验证入职日期
		if(mp1001.getMP1001_ENTRY_DATE().equals("")){
			addFieldError("mp1001.MP1001_ENTRY_DATE","starting date is empty.");
		}
		
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
	    // 部门信息在登录后就缓存在Session中了
	    departmentList = (List<MP0002>) session.get("DEPARTMENT_LIST");
		listInit();
		
		if(!mp1001.getMP1001_DEPARTMENT_ID().equals("")){
			positionInfoInit(mp1001.getMP1001_DEPARTMENT_ID());
		}
	}
	
	// 新增教育信息页面初始化
	public String employeeEducationInit() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			listInit();
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Employee Education Init");
			//----------------------------Operation History------------------
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	// 新增教育
	public String employeeEducationAdd() throws Exception{
		try{
			// 缓存登陆用户的信息
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
			MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
			String loginName = employeeData.getMP1001_EMPLOYEE_NUM();
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Employee Education");
			//----------------------------Operation History------------------
            
		    // 取得系统当前时间
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addtime = sdf.format(now);
			
			mp1002.setMP1002_EMPLOYEE_NUM(employeeNum); // 设置编码
			
            if(mp1002.getMP1002_SEQ() > 0){
            	mp1002.setMP1002_EDIT_DATETIME(addtime); // 修改时间
            	mp1002.setMP1002_EDIT_USER(loginName); // 修改人
            	
            	serviceMP1002.update(mp1002);
            }else{
    			mp1002.setMP1002_CREATE_DATETIME(addtime); // 创建时间
    			mp1002.setMP1002_CREATE_USER(loginName); // 创建人
            	serviceMP1002.save(mp1002);
            }
            
            return SUCCESS;
		}catch(Exception ex){
			log.debug(ex.getMessage());
			return INPUT;
		}
	}
	
	// 教育信息查询
	public String employeeEducationSearch() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Employee Education Search");
			//----------------------------Operation History------------------
			
			educationSearchResult(employeeNum);		
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	private void educationSearchResult(String empNum){
		log.debug("Employee Number:" + empNum);
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Employee Education Search Result");
			//----------------------------Operation History------------------
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			educationInfoList = serviceMP1002.findByProperty("MP1002_EMPLOYEE_NUM", empNum);
			// 格式化时间
			if(educationInfoList != null && educationInfoList.size() > 0){
			    Map<String,String> eduType = Constant.getInstitutionTypeList();
			    Map<String,String> eduLevel = Constant.getQualificationLevList();
			    
				for(int i=0,j=educationInfoList.size(); i<j; i++){
					MP1002 eduData = educationInfoList.get(i);
					String _type = eduData.getMP1002_INSTITUTION_TYPE();
				    String _level = eduData.getMP1002_QUALIFICATION_LEV();
					
				    // 入学时间
					if(eduData.getMP1002_START_DATETIME() != null && !eduData.getMP1002_START_DATETIME().equals("")){
						Date startDate = sdf.parse(eduData.getMP1002_START_DATETIME());
						eduData.setMP1002_START_DATETIME(sdf.format(startDate));
					}
					// 毕业时间
					if(eduData.getMP1002_FINISH_DATETIME() != null && !eduData.getMP1002_FINISH_DATETIME().equals("")){
						Date finishDate = sdf.parse(eduData.getMP1002_FINISH_DATETIME());
						eduData.setMP1002_FINISH_DATETIME(sdf.format(finishDate));
					}
					// 学校类型
					if(!_type.equals("") && eduType.containsKey(_type)){
						eduData.setMP1002_INSTITUTION_TYPE_NAME(eduType.get(_type));
					}
					// 学校等级
					if(!_level.equals("") && eduLevel.containsKey(_level)){
						eduData.setMP1002_QUALIFICATION_LEV_NAME(eduLevel.get(_level));
					}
				}
			}
		}catch(Exception ex){
			log.info(ex.getMessage());
		}
	}
	
	// 删除员工教育信息eduSeq
	public String educationInfoDel(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{		
			int seq = Integer.parseInt(eduSeq);
			MP1002 mp12 = serviceMP1002.findById(seq);
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Delete Education Information, SEQ:{" + mp12.getMP1002_EMPLOYEE_NUM() + "}");
			//----------------------------Operation History------------------
			serviceMP1002.delete(mp12);
			
			educationSearchResult(mp12.getMP1002_EMPLOYEE_NUM());
			
			return SUCCESS;
		}catch(Exception ex){
			log.debug(ex.getMessage());
			return "error";
		}
	}
	
	public String employeeEducationEdit() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			listInit();
			
			int seq = Integer.parseInt(eduSeq);
			mp1002 = serviceMP1002.findById(seq);
			employeeNum = mp1002.getMP1002_EMPLOYEE_NUM();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Delete Education Information, Employee Num:{" + mp1002.getMP1002_EMPLOYEE_NUM() + "}");
			//----------------------------Operation History------------------
			
			// 解析开始日期
			Date startDate = sdf.parse(mp1002.getMP1002_START_DATETIME());
			mp1002.setMP1002_START_DATETIME(sdf.format(startDate));
			
			// 解析结束日期
			Date finshDate = sdf.parse(mp1002.getMP1002_FINISH_DATETIME());
			mp1002.setMP1002_FINISH_DATETIME(sdf.format(finshDate));
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	// 新增工作经历信息页面初始化
	public String employeeExperienceInit() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Employee Experience Init");
			//----------------------------Operation History------------------
			
			listInit();
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	// 新增教育
	public String employeeExperienceAdd() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			String loginName = employeeData.getMP1001_EMPLOYEE_NUM();
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Employee Experience, Employee Num:{" + employeeNum + "}");
			//----------------------------Operation History------------------
			
		    // 取得系统当前时间
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addtime = sdf.format(now);
			
			mp1003.setMP1003_EMPLOYEE_NUM(employeeNum); // 设置编码
			
            if(mp1003.getMP1003_SEQ() > 0){
            	mp1003.setMP1003_EDIT_DATETIME(addtime); // 修改时间
            	mp1003.setMP1003_EDIT_USER(loginName); // 修改人
            	
            	serviceMP1003.update(mp1003);
            }else{
            	mp1003.setMP1003_CREATE_DATETIME(addtime); // 创建时间
            	mp1003.setMP1003_CREATE_USER(loginName); // 创建人
            	serviceMP1003.save(mp1003);
            }
            
            return SUCCESS;
		}catch(Exception ex){
			log.debug(ex.getMessage());
			return INPUT;
		}
	}
	
	// 工作经历信息查询
	public String employeeExperienceSearch() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Employee Experience, Employee Num:{" + employeeNum + "}");
			//----------------------------Operation History------------------
			
			experienceSearchResult(employeeNum);		
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	private void experienceSearchResult(String empNum){
		log.debug("Employee Number:" + empNum);
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Employee Experience, Employee Num:{" + empNum + "}");
			//----------------------------Operation History------------------
						
			companyInfoList = serviceMP1003.findByProperty("MP1003_EMPLOYEE_NUM", empNum);
			// 格式化时间
			if(companyInfoList != null && companyInfoList.size() > 0){
				for(int m=0,n=companyInfoList.size(); m<n; m++){
					MP1003 comData = companyInfoList.get(m);
					
					if(comData.getMP1003_ENTRY_DATETIME() != null && !comData.getMP1003_ENTRY_DATETIME().equals("")){
						Date entryDate = sdf.parse(comData.getMP1003_ENTRY_DATETIME());
						comData.setMP1003_ENTRY_DATETIME(sdf.format(entryDate));
					}
					if(comData.getMP1003_TERMINATION_DATETIME() != null && !comData.getMP1003_TERMINATION_DATETIME().equals("")){
						Date terminDate = sdf.parse(comData.getMP1003_TERMINATION_DATETIME());
						comData.setMP1003_TERMINATION_DATETIME(sdf.format(terminDate));
					}
				}
			}
		}catch(Exception ex){
			log.info(ex.getMessage());
		}
	}
	
	// 删除员工工作经历信息
	public String workInfoDel(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			int seq = Integer.parseInt(expSeq);
			MP1003 mp13 = serviceMP1003.findById(seq);
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Employee Experience, Employee Num:{" + mp13.getMP1003_EMPLOYEE_NUM() + "}");
			//----------------------------Operation History------------------
			
			serviceMP1003.delete(mp13);
			experienceSearchResult(mp13.getMP1003_EMPLOYEE_NUM());
			return SUCCESS;
		}catch(Exception ex){
			log.debug(ex.getMessage());
			return "error";
		}
	}
	
	public String employeeExperienceEdit() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			listInit();
		
			int seq = Integer.parseInt(expSeq);
			mp1003 = serviceMP1003.findById(seq);
			employeeNum = mp1003.getMP1003_EMPLOYEE_NUM();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Edit Employee Experience, Employee Num:{" + mp1003.getMP1003_EMPLOYEE_NUM() + "}");
			//----------------------------Operation History------------------
			
			// 解析开始日期
			Date startDate = sdf.parse(mp1003.getMP1003_ENTRY_DATETIME());
			mp1003.setMP1003_ENTRY_DATETIME(sdf.format(startDate));
			
			// 解析结束日期
			Date finshDate = sdf.parse(mp1003.getMP1003_TERMINATION_DATETIME());
			mp1003.setMP1003_TERMINATION_DATETIME(sdf.format(finshDate));
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
		// 编辑员工教育信息

	// 新增联系人信息页面初始化
	public String employeeContactInit() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			listInit();
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Employee Contract Init");
			//----------------------------Operation History------------------
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	// 新增教育
	public String employeeContactAdd() throws Exception{
		try{
			// 缓存登陆用户的信息
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
			MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
			String loginName = employeeData.getMP1001_EMPLOYEE_NUM();
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Employee Contract, Employee Num:{" + employeeNum + "}");
			//----------------------------Operation History------------------
            
		    // 取得系统当前时间
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addtime = sdf.format(now);
			
			mp1005.setMP1005_EMPLOYEE_NUM(employeeNum); // 设置编码
			
            if(mp1005.getMP1005_SEQ() > 0){
            	mp1005.setMP1005_EDIT_DATETIME(addtime); // 修改时间
            	mp1005.setMP1005_EDIT_USER(loginName); // 修改人
            	
            	serviceMP1005.update(mp1005);
            }else{
            	mp1005.setMP1005_CREATE_DATETIME(addtime); // 创建时间
            	mp1005.setMP1005_CREATE_USER(loginName); // 创建人
            	
            	serviceMP1005.save(mp1005);
            }
            
            return SUCCESS;
		}catch(Exception ex){
			log.debug(ex.getMessage());
			return INPUT;
		}
	}
	
	// 工作经历信息查询
	public String employeeContactSearch() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Employee Contract, Employee Num:{" + employeeNum + "}");
			//----------------------------Operation History------------------
			
			contactSearchResult(employeeNum);		
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	private void contactSearchResult(String empNum){
		log.debug("Employee Number:" + empNum);
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Employee Contract Result, Employee Num:{" + empNum + "}");
			//----------------------------Operation History------------------
			
			// 取得要查询员工的紧急联系人信息列表
			contactInfoList = serviceMP1005.findByProperty("MP1005_EMPLOYEE_NUM", empNum);
		}catch(Exception ex){
			log.info(ex.getMessage());
		}
	}
	
	// 删除员工工作经历信息
	public String contactInfoDel(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			int seq = Integer.parseInt(conSeq);
			MP1005 mp15 = serviceMP1005.findById(seq);
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Delete Employee Contract Information, Employee Num:{" + mp15.getMP1005_EMPLOYEE_NUM() + "}");
			//----------------------------Operation History------------------
			
			serviceMP1005.delete(mp15);
			
			contactSearchResult(mp15.getMP1005_EMPLOYEE_NUM());
			return SUCCESS;
		}catch(Exception ex){
			log.debug(ex.getMessage());
			return "error";
		}
	}
	
	public String employeeContactEdit() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			listInit();
			
			int seq = Integer.parseInt(conSeq);
			mp1005 = serviceMP1005.findById(seq);
			employeeNum = mp1005.getMP1005_EMPLOYEE_NUM();
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Edit Employee Contract Information, Employee Num:{" + employeeNum + "}");
			//----------------------------Operation History------------------

			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	// 解析工作信息
	@SuppressWarnings("unused")
	private void saveEmployeeWorkInfo(String loginName, String bulletinTime) throws Exception {
		// 按照分隔符[|]拆分字符串
		String[] workArray = workHidden.split("#@\\$");
		for (int i = 0, j = workArray.length; i < j; i++) {
			String[] workDetailInfo = workArray[i].split("%#");
			if (workDetailInfo.length != 8) {
				throw new Exception("解析工作信息时格式不正确");
			}
			
			String companyName = workDetailInfo[0];  // 公司名称
			String department = workDetailInfo[1];  // 部门
			String position = workDetailInfo[2]; // 职位
		    String startDate = workDetailInfo[3]; // 入职日期
		    String endDate = workDetailInfo[4]; // 离职日期
		    String jobDesc = workDetailInfo[5];   // 工作描述
		    String reason = workDetailInfo[6];   // 离职原因
		    String contact = workDetailInfo[7];   // 联系人
		    mp1003 = new MP1003();
		    
			// 员工编号
			mp1003.setMP1003_EMPLOYEE_NUM(mp1001.getMP1001_EMPLOYEE_NUM());
			// 公司名称
			mp1003.setMP1003_COMPANY_NAME(companyName);
			// 部门
			mp1003.setMP1003_DEPARTMENT_ID(department);
			// 职位
			mp1003.setMP1003_POSITION(position);
			// 入职日期
			mp1003.setMP1003_ENTRY_DATETIME(startDate);
			// 离职日期
			mp1003.setMP1003_TERMINATION_DATETIME(endDate);
			// 工作描述
			mp1003.setMP1003_JOB_DESC(jobDesc);
			// 离职原因
			mp1003.setMP1003_TERMINATION_REASON(reason);
			// 联系人
			mp1003.setMP1003_CONTACT_PERSON_INFO(contact);
			mp1003.setMP1003_CREATE_USER(loginName);
			mp1003.setMP1003_CREATE_DATETIME(bulletinTime);
			
			serviceMP1003.save(mp1003);
		}
	}
	
	// 解析员工教育信息
	@SuppressWarnings("unused")
	private void saveEmployeeEducationInfo(String loginName, String bulletinTime) throws Exception {
		// 按照分隔符[|]拆分字符串
		String[] eduArray = educationHidden.split("#@\\$");
		for (int i = 0, j = eduArray.length; i < j; i++) {
			String[] eduDetailInfo = eduArray[i].split("%#");
			if (eduDetailInfo.length != 6) {
				throw new Exception("解析教育信息时格式不正确");
			}
			
			String type = eduDetailInfo[0];  // 学校类别
			String name = eduDetailInfo[1];  // 学校名称
			String level = eduDetailInfo[2]; // 等级
		    String major = eduDetailInfo[3]; // 专业
		    String start = eduDetailInfo[4]; // 入学时间
		    String end = eduDetailInfo[5];   // 毕业时间
		    mp1002 = new MP1002();
		    
			// 员工编号
			mp1002.setMP1002_EMPLOYEE_NUM(mp1001.getMP1001_EMPLOYEE_NUM());
			// 学校类别
			mp1002.setMP1002_INSTITUTION_TYPE(type);
			// 学校名称
			mp1002.setMP1002_INSTITUTION_NAME(name);
			// 等级
			mp1002.setMP1002_QUALIFICATION_LEV(level);
			// 专业
			mp1002.setMP1002_MAJOR(major);
			// 入学时间
			mp1002.setMP1002_START_DATETIME(start);
			// 毕业时间
			mp1002.setMP1002_FINISH_DATETIME(end);
			mp1002.setMP1002_CREATE_USER(loginName);
			mp1002.setMP1002_CREATE_DATETIME(bulletinTime);

			serviceMP1002.save(mp1002);
		}
	}
	
	// 新增员工教育信息
	@SuppressWarnings("unchecked")
	public String educationInfoAdd() throws Exception{
		try{
			// 缓存登陆用户的信息
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
		    // 部门信息在登录后就缓存在Session中了
			educationInfoList = (List<MP1002>) session.get("EDUCATION_INFO");
			MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
			String loginName = employeeData.getMP1001_EMPLOYEE_NUM();
			
		    Map<String,String> eduType = Constant.getInstitutionTypeList();
		    Map<String,String> eduLevel = Constant.getQualificationLevList();
            
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addtime = sdf.format(now);
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Employee Education Information, Employee Num:{" + employeeNum + "}");
			//----------------------------Operation History------------------

			mp1002.setMP1002_EMPLOYEE_NUM(employeeNum);
			mp1002.setMP1002_INSTITUTION_TYPE(universityType);
			if(eduType.containsKey(universityType)){
				mp1002.setMP1002_INSTITUTION_TYPE_NAME(eduType.get(universityType));
			}
			
			mp1002.setMP1002_INSTITUTION_NAME(universityName);
			mp1002.setMP1002_QUALIFICATION_LEV(level);
			if(eduLevel.containsKey(level)){
				mp1002.setMP1002_QUALIFICATION_LEV_NAME(eduLevel.get(level));
			}
			mp1002.setMP1002_MAJOR(major);
			mp1002.setMP1002_START_DATETIME(start);
			mp1002.setMP1002_FINISH_DATETIME(finish);
			mp1002.setMP1002_CREATE_DATETIME(addtime);
			mp1002.setMP1002_CREATE_USER(loginName);
			
			serviceMP1002.save(mp1002);
			
			educationInfoList.add(mp1002);
			
			if(session.containsKey("EDUCATION_INFO")){
				session.remove("EDUCATION_INFO");
			}
			session.put("EDUCATION_INFO", educationInfoList);
			
			return SUCCESS;
		}catch(Exception ex){
			log.debug(ex.getMessage());
			return "error";
		}
		
	}
	
	// 新增员工工作信息
	@SuppressWarnings("unchecked")
	public String workInfoAdd() throws Exception{
		try{
			// 缓存登陆用户的信息
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
		    // 部门信息在登录后就缓存在Session中了
			companyInfoList = (List<MP1003>) session.get("COMPANY_INFO");
			MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
			String loginName = employeeData.getMP1001_EMPLOYEE_NUM();
            
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addtime = sdf.format(now);
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Employee Work Information, Employee Num:{" + employeeNum + "}");
			//----------------------------Operation History------------------

			mp1003.setMP1003_EMPLOYEE_NUM(employeeNum);
			mp1003.setMP1003_COMPANY_NAME(companyName);
			mp1003.setMP1003_CONTACT_PERSON_INFO(contact);
			mp1003.setMP1003_DEPARTMENT_ID(depName);
			mp1003.setMP1003_ENTRY_DATETIME(entryDate);
			mp1003.setMP1003_JOB_DESC(description);
			mp1003.setMP1003_POSITION(position);
			mp1003.setMP1003_TERMINATION_DATETIME(terminationDate);
			mp1003.setMP1003_TERMINATION_REASON(reason);
			mp1003.setMP1003_CREATE_DATETIME(addtime);
			mp1003.setMP1003_CREATE_USER(loginName);

			serviceMP1003.save(mp1003);
			
			companyInfoList.add(mp1003);
			
			if(session.containsKey("COMPANY_INFO")){
				session.remove("COMPANY_INFO");
			}
			session.put("COMPANY_INFO", companyInfoList);
			
			System.out.println("add work success");
			
			return SUCCESS;
		}catch(Exception ex){
			log.debug(ex.getMessage());
			return "error";
		}
	}
	
	// 临时员工信息浏览
	public String tmpEmployeeView() throws Exception{
        try{
    		getTmpEmpData();
    		
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
			MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"View Temporary Employee Information");
			//----------------------------Operation History------------------
			
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0001002,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_APPROVAL)){
				optApproval = "1";
			}
    		
    		return SUCCESS;
        }catch(Exception ex){
        	log.info(ex.getMessage());
        	return "error";
        }
	}
	
	private void getTmpEmpData() throws ParseException, IOException{
		HashMap<String, String> propertyMap = new HashMap<String, String>();
		propertyMap.put("EMP_STATUS", "1");
		tmpEmployeeDataList = service.findByProperty(propertyMap);
		convertIDtoName(tmpEmployeeDataList);
		
/*		if(tmpEmployeeDataList.size() == 0){
			StringBuffer sb = new StringBuffer();
			sb.append("<script type='text/javascript'>");
			sb.append("window.close();");
			sb.append("</script>");
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
	        out.flush();
	        out.close();
		}*/
	}
	
	//临时员工转为正式员工
	public String tmpEmployeeApproval(){
		try{
			if(!employeeNum.equals("")){
				//List<MP0006> mp06List = serviceMP0006.findByProperty("MP0006_CODE", "EMP_NUM"); //正式员工编码
			    //MP0006 mp6 = mp06List.get(0);
			    //int maxNum = Integer.parseInt(mp6.getMP0006_VALUE()) + 1;
			    //java.text.DecimalFormat format = new java.text.DecimalFormat("0000");
			    //String empNum = "M" + format.format(maxNum);
			    //mp6.setMP0006_VALUE(String.valueOf(maxNum));
				String empNum = employeeNum;
			    
				ActionContext context = ActionContext.getContext();
				Map<String, Object> session = context.getSession();
				MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
				MP1001 mp11Obj = service.findById(employeeNum);
				
				//----------------------------Operation History------------------
				LogUtil logUtil = new LogUtil();
				logUtil.setServiceMP0011(serviceMP0011);
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Approve Temporary Employee, Employee Num:{" + employeeNum + "}");
				//----------------------------Operation History------------------
				
			    service.updateEmployeeNum(employeeNum,empNum,employeeData.getMP1001_EMPLOYEE_NUM()); //employeeNum:临时员工编码 ; empNum:正式员工编码
			    //serviceMP0006.update(mp6);
			    
			    getTmpEmpData();

				Mail mail = new Mail();
				List<MP0006> mailList = serviceMP0006.findByProperty("MP0006_CODE", "MAIL_LIST"); //正式员工编码
			    MP0006 mailListObj = mailList.get(0);
			    
				String toList = mailListObj.getMP0006_VALUE() + "," + mp11Obj.getMP1001_COMPANY_EMAIL();
				mail.setSubject("welcome new recruit");
				mail.setContent("Dear Colleagues,\r\n \r\n Please note that the below employee's number has been updated.\r\n Employee No. : " + employeeNum + "(" + mp11Obj.getMP1001_PREFERED_NAME() + ")--------->" + empNum + "\r\n \r\n Thank you very much! \r\n \r\n\r\n Your Faithfully, \r\n HRMS Administrator");
				mail.setTo(toList);
				mail.send();
				
				HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0001002,Constant.SYSTEM_NUM_HR);
				if(optMap.containsKey(Constant.OPT_APPROVAL)){
					optApproval = "1";
				}
			}
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	// 员工详细信息浏览
	@SuppressWarnings("unchecked")
	public String employeeList() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Employee Information List");
			//----------------------------Operation History------------------
			
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0001003,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_SEARCH)){
				optSearch = "1";
			}
			if(optMap.containsKey(Constant.OPT_EDIT)){
				optEdit = "1";
			}
			if(optMap.containsKey(Constant.OPT_VIEW)){
				optView = "1";
			}
			if(optMap.containsKey(Constant.OPT_ADVANCE_SEARCH)){
				optAdvanceSearch = "1";
			}
			if(optMap.containsKey(Constant.OPT_ALL)){
				optAll = "1";
			}
			
			// 判断是否第一次进入本页面，如果是则清空ＫＥＹ为SEARCH_CONDITION的值
			if(pageInitType != null && !pageInitType.equals("")){
				if(session.containsKey("SEARCH_CONDITION")){session.remove("SEARCH_CONDITION");}
			}

		    // 部门信息在登录后就缓存在Session中了
		    departmentList = (List<MP0002>) session.get("DEPARTMENT_LIST");
			group = employeeData.getMP1001_GROUP();
			loginPosition = employeeData.getMP1001_POSITION();
			loginDepartId = employeeData.getMP1001_DEPARTMENT_ID();
			
			listInit();
			if(group.equals("2")){
				departmentID = employeeData.getMP1001_DEPARTMENT_ID();
		    	// 解析部门
		    	for(int m=0,n=departmentList.size(); m<n; m++){
		    		MP0002 mp2 = departmentList.get(m);
		    		String seq = String.valueOf(mp2.getMP0002_SEQ());
		    		if(departmentID.equals(seq)){
		    			departmentName = mp2.getMP0002_DEPARTMENT_NAME();
		    			break;
		    		}
		    	}
			}
			
			//session.put("SEARCH_CONDITION", propertyMap);
			HashMap<String, String> propertyMap = new HashMap<String, String>();
			if(session.containsKey("SEARCH_CONDITION") && session.get("SEARCH_CONDITION") != null){
				propertyMap = (HashMap<String, String>) session.get("SEARCH_CONDITION");
			}else{
				propertyMap.put("NUM", "");
				propertyMap.put("NAME", "");
				propertyMap.put("DEPARTMENT", departmentID);
				propertyMap.put("TYPE", "");
				propertyMap.put("STATUS", ""); // 员工状态
				
				// Add by Tim 2011/12/29--Start
				propertyMap.put("gender", "");
				propertyMap.put("nationality", "");
				propertyMap.put("religion", "");
				propertyMap.put("race", "");
				propertyMap.put("passportNum", "");
				propertyMap.put("qualification", "");
				propertyMap.put("startingDate", "");
				propertyMap.put("birth", "");
				propertyMap.put("birthday", "");
				// Add by Tim 2011/12/29--End
			}			
			//employeeInfo = service.findAll();
			employeeInfo = service.findByProperty(propertyMap);
			if( 0 == employeeInfo.size()%Constant.PAGE_SIZE){
				pageCount = employeeInfo.size()/Constant.PAGE_SIZE;
			}else{
				pageCount = employeeInfo.size()/Constant.PAGE_SIZE + 1;
			}
			
			if(session.containsKey("SEARCH_CONDITION") && session.get("SEARCH_CONDITION") != null){
				// continue;
				employeeNum = propertyMap.get("NUM");
				employeeName = propertyMap.get("NAME");
				departmentID = propertyMap.get("DEPARTMENT");
				visaType = propertyMap.get("TYPE");
				pageNum = Integer.parseInt(propertyMap.get("PAGE_NUM"));
				departmentName = propertyMap.get("DEPARTMENT_NAME");
				empStatus = propertyMap.get("STATUS");
				
				// Add by Tim 2011/12/29--Start
				gender = propertyMap.get("gender");
				nationality = propertyMap.get("nationality");
				religion = propertyMap.get("religion");
				race = propertyMap.get("race");
				passportNum = propertyMap.get("passportNum");
				qualification = propertyMap.get("qualification");
				startingDate = propertyMap.get("startingDate");
				birth = propertyMap.get("birth");
				birthday = propertyMap.get("birthday");
				// Add by Tim 2011/12/29--End
				
			}else{
				propertyMap.put("PAGE_NUM", "1"); // 当前页
				propertyMap.put("PAGE_COUNT", String.valueOf(Constant.PAGE_SIZE)); // 每页显示条数
			}

			employeeInfo = service.findByPropertyPage(propertyMap);
			convertIDtoName(employeeInfo);
			
			return SUCCESS;
		}catch(Exception ex){
		    log.debug(ex.getMessage());
		    return "error";
		}
	}
	
	// 员工详细信息浏览
	public String employeeDisplay() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			log.debug("Employee Number:" + employeeNum);
			System.out.println("Employee Number:" + employeeNum);
			
	        listInit();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			mp1001 = service.findById(employeeNum);
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Display Employee Information, Employee Num:{" + employeeNum + "}");
			//----------------------------Operation History------------------
			
	        // 解析所属Group
			String group = mp1001.getMP1001_GROUP();
			if(group.equals("1")){
				mp1001.setMP1001_GROUP_NAME(Constant.GROUP.Employee.toString());
			}else if(group.equals("2")){
				mp1001.setMP1001_GROUP_NAME(Constant.GROUP.Manager.toString());
			}else if(group.equals("3")){
				mp1001.setMP1001_GROUP_NAME(Constant.GROUP.HR_Manager.toString());
			}else if(group.equals("4")){
				mp1001.setMP1001_GROUP_NAME(Constant.GROUP.Director.toString());
			}else if(group.equals("5")){
				mp1001.setMP1001_GROUP_NAME(Constant.GROUP.Admin.toString());
			}
			
			// 解析部门
			String depName = getdepartmentNameById(mp1001.getMP1001_DEPARTMENT_ID());
			mp1001.setMP1001_DEPARTMENT_NAME(depName);
			// 解析性别(1:Male 2:Female)
			String gender = mp1001.getMP1001_GENDER();
			if(gender.equals("1")){
				mp1001.setMP1001_GENDER_NAME("Male");
			}else if(gender.equals("2")){
				mp1001.setMP1001_GENDER_NAME("Female");
			}else{
				mp1001.setMP1001_GENDER_NAME("-");
			}
			// 解析职位
			//String position = mp1001.getMP1001_POSITION();
	        //if(positionList.containsKey(position)){
	        //	mp1001.setMP1001_POSITION_NAME(positionList.get(position));
	        //}else{
	        //	mp1001.setMP1001_POSITION_NAME("-");
	        //}
			if(mp1001.getMP1001_POSITION() != null && !mp1001.getMP1001_POSITION().equals("")){
				MP0009 mp09 = serviceMP0009.findById(Integer.parseInt(mp1001.getMP1001_POSITION()));
				mp1001.setMP1001_POSITION_NAME(mp09.getMP0009_POSITION_NAME_E());
			}else{
				mp1001.setMP1001_POSITION_NAME("-");
			}
	        
			// 解析婚姻状态(1:Single 2:Married)
			String married = mp1001.getMP1001_MARRIAGE_STATUS();
			if(married.equals("1")){
				mp1001.setMP1001_MARRIAGE_STATUS_NAME("Single");
			}else if(married.equals("2")){
				mp1001.setMP1001_MARRIAGE_STATUS_NAME("Married");
			}else{
				mp1001.setMP1001_MARRIAGE_STATUS_NAME("-");
			}
			// 格式化时间
			if(mp1001.getMP1001_BIRTHDAY() != null && !mp1001.getMP1001_BIRTHDAY().equals("")){
				Date birthdayDate = sdf.parse(mp1001.getMP1001_BIRTHDAY());
				mp1001.setMP1001_BIRTHDAY(sdf.format(birthdayDate));
			}
			if(mp1001.getMP1001_ENTRY_DATE() != null && !mp1001.getMP1001_ENTRY_DATE().equals("")){
				Date entryDate = sdf.parse(mp1001.getMP1001_ENTRY_DATE());
				mp1001.setMP1001_ENTRY_DATE(sdf.format(entryDate));
			}

			educationInfoList = serviceMP1002.findByProperty("MP1002_EMPLOYEE_NUM", employeeNum);
			for(int m=0,n=educationInfoList.size(); m<n; m++){
				MP1002 obj = educationInfoList.get(m);
				String institutionType = obj.getMP1002_INSTITUTION_TYPE();
				String level = obj.getMP1002_QUALIFICATION_LEV();
				
				// 解析学校类别
				if(institutionTypeList.containsKey(institutionType)){
					obj.setMP1002_INSTITUTION_TYPE_NAME(institutionTypeList.get(institutionType));
				}else{
					obj.setMP1002_INSTITUTION_TYPE_NAME("-");
				}
				// 解析学校等级
				if(qualificationLevList.containsKey(level)){
					obj.setMP1002_QUALIFICATION_LEV_NAME(qualificationLevList.get(level));
				}else{
					obj.setMP1002_QUALIFICATION_LEV_NAME("-");
				}
				// 格式化时间
				if(obj.getMP1002_START_DATETIME() != null && !obj.getMP1002_START_DATETIME().equals("")){
					Date startDate = sdf.parse(obj.getMP1002_START_DATETIME());
					obj.setMP1002_START_DATETIME(sdf.format(startDate));
				}
				if(obj.getMP1002_FINISH_DATETIME() != null && !obj.getMP1002_FINISH_DATETIME().equals("")){
					Date finishDate = sdf.parse(obj.getMP1002_FINISH_DATETIME());
					obj.setMP1002_FINISH_DATETIME(sdf.format(finishDate));
				}
			}
			
			companyInfoList = serviceMP1003.findByProperty("MP1003_EMPLOYEE_NUM", employeeNum);
			// 格式化时间
			if(companyInfoList != null && companyInfoList.size() > 0){
				for(int m=0,n=companyInfoList.size(); m<n; m++){
					MP1003 comData = companyInfoList.get(m);
					
					if(comData.getMP1003_ENTRY_DATETIME() != null && !comData.getMP1003_ENTRY_DATETIME().equals("")){
						Date entryDate = sdf.parse(comData.getMP1003_ENTRY_DATETIME());
						comData.setMP1003_ENTRY_DATETIME(sdf.format(entryDate));
					}
					if(comData.getMP1003_TERMINATION_DATETIME() != null && !comData.getMP1003_TERMINATION_DATETIME().equals("")){
						Date terminDate = sdf.parse(comData.getMP1003_TERMINATION_DATETIME());
						comData.setMP1003_TERMINATION_DATETIME(sdf.format(terminDate));
					}
				}
			}
			
			List<MP1005> emgencyPersonList = serviceMP1005.findByProperty("MP1005_EMPLOYEE_NUM", employeeNum);
			if(emgencyPersonList != null && emgencyPersonList.size() > 0){
				mp1005 = emgencyPersonList.get(0);
			}		
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}

	}
	
	// 员工基本信息查询
	@SuppressWarnings("unchecked")
	public String employeeSearch() throws Exception{
		// 缓存登陆用户的信息
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			listInit();

			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Employee Information");
			//----------------------------Operation History------------------
			
			if(departmentID == null || departmentID.equals("undefined")){
				departmentID = employeeData.getMP1001_DEPARTMENT_ID();
			}
			if(departmentName == null || departmentName.equals("undefined")){
				departmentName = employeeData.getMP1001_DEPARTMENT_NAME();
			}
			HashMap<String, String> propertyMap = new HashMap<String, String>();
			propertyMap.put("NUM", employeeNum); // 员工编码
			propertyMap.put("NAME", employeeName); // 员工名
			propertyMap.put("DEPARTMENT", departmentID); // 部门
			propertyMap.put("DEPARTMENT_NAME",departmentName); //部门 名称
			propertyMap.put("TYPE", visaType); // 员工签证类型
			propertyMap.put("STATUS", empStatus); // 员工状态
			
			propertyMap.put("gender", gender);
			propertyMap.put("nationality", nationality);
			propertyMap.put("religion", religion);
			propertyMap.put("race", race);
			propertyMap.put("passportNum", passportNum);
			propertyMap.put("qualification", qualification);
			propertyMap.put("startingDate", startingDate);
			propertyMap.put("birth", birth);
			propertyMap.put("birthday", birthday);
			
			employeeInfo = service.findByProperty(propertyMap);
			if( 0 == employeeInfo.size()%Constant.PAGE_SIZE){
				pageCount = employeeInfo.size()/Constant.PAGE_SIZE;
			}else{
				pageCount = employeeInfo.size()/Constant.PAGE_SIZE + 1;
			}
			
			pageNum = 1;
			propertyMap.put("PAGE_NUM", "1"); // 当前页
			propertyMap.put("PAGE_COUNT", String.valueOf(Constant.PAGE_SIZE)); // 每页显示条数
			employeeInfo = service.findByPropertyPage(propertyMap);
			convertIDtoName(employeeInfo);
			
		    // 部门信息在登录后就缓存在Session中了
		    departmentList = (List<MP0002>) session.get("DEPARTMENT_LIST");
		    session.put("QueryData", mp1001);
		    
			if(session.containsKey("SEARCH_CONDITION")){
				session.remove("SEARCH_CONDITION");
			}
		    session.put("SEARCH_CONDITION", propertyMap);
		    
		    return "search";
		}catch(Exception ex){
			log.debug(ex.getMessage());
			return "error";
		}
	}
	
	// 根据部门选择职位信息
	public String positionSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Position Information");
			//----------------------------Operation History------------------
			
			positionInfoInit(departmentID);
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	// 初始化职位列表
	public void positionInfoInit(String depId){
		List<MP0008> list = serviceMP0008.findByDepartmentId(depId);
		
		positionList.put("0", "Select");
		for(int i=0,j=list.size();i<j;i++){
			MP0008 obj = list.get(i);
			String mp08PositionId = String.valueOf(obj.getMP0008_POSITION_ID());
			String mp08PositionName = obj.getMP0008_POSITION_NAME_E();
			
			positionList.put(mp08PositionId, mp08PositionName);
		}
	}
	
	public String employeeDetailList() throws Exception{
		// 缓存登陆用户的信息
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Employee Detail Information List");
			//----------------------------Operation History------------------
			
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0001003,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_SEARCH)){
				optSearch = "1";
			}
			if(optMap.containsKey(Constant.OPT_EDIT)){
				optEdit = "1";
			}
			if(optMap.containsKey(Constant.OPT_VIEW)){
				optView = "1";
			}
			if(optMap.containsKey(Constant.OPT_ADVANCE_SEARCH)){
				optAdvanceSearch = "1";
			}
			if(optMap.containsKey(Constant.OPT_ALL)){
				optAll = "1";
			}
			
			HashMap<String, String> propertyMap = new HashMap<String, String>();
			propertyMap.put("NUM", employeeNum); // 员工编码
			propertyMap.put("NAME", employeeName); // 员工名
			propertyMap.put("DEPARTMENT", departmentID.equals("undefined")?employeeData.getMP1001_DEPARTMENT_ID():departmentID); // 部门
			propertyMap.put("DEPARTMENT_NAME",departmentName.equals("undefined")?employeeData.getMP1001_DEPARTMENT_NAME():departmentName); //部门 名称
			propertyMap.put("TYPE", visaType); // 员工签证类型
			propertyMap.put("STATUS", empStatus); // 员工状态
			propertyMap.put("PAGE_NUM", String.valueOf(pageNum)); // 当前页
			propertyMap.put("PAGE_COUNT", String.valueOf(Constant.PAGE_SIZE)); // 每页显示条数
			//propertyMap.put("PAGE_TOTAL", String.valueOf(pageCount)); // 总页数
			
			// Add by Tim 2011/12/29--Start
			propertyMap.put("gender", gender.equals("undefined")?"":gender);
			propertyMap.put("nationality", nationality.equals("undefined")?"":nationality);
			propertyMap.put("religion", religion.equals("undefined")?"":religion);
			propertyMap.put("race", race.equals("undefined")?"":race);
			propertyMap.put("passportNum", passportNum.equals("undefined")?"":passportNum);
			propertyMap.put("qualification", qualification.equals("undefined")?"":qualification);
			propertyMap.put("startingDate", startingDate.equals("undefined")?"":startingDate);
			propertyMap.put("birth", birth.equals("undefined")?"":birth);
			propertyMap.put("birthday", birthday.equals("undefined")?"":birthday);
			// Add by Tim 2011/12/29--End
			
			employeeInfo = service.findByPropertyPage(propertyMap);
			convertIDtoName(employeeInfo);

			group = employeeData.getMP1001_GROUP();
			loginPosition = employeeData.getMP1001_POSITION();
			loginDepartId = employeeData.getMP1001_DEPARTMENT_ID();
			
			if(session.containsKey("SEARCH_CONDITION")){
				session.remove("SEARCH_CONDITION");
			}
		    session.put("SEARCH_CONDITION", propertyMap);
			
		    // 部门信息在登录后就缓存在Session中了
		    //departmentList = (List<MP0002>) session.get("DEPARTMENT_LIST");
		    //session.put("QueryData", mp1001);
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	// 解析部门
	@SuppressWarnings("unchecked")
	private void convertIDtoName(List<MP1001> employeeInfo) throws ParseException{
		// 缓存登陆用户的信息
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
	    // 部门信息在登录后就缓存在Session中了
	    departmentList = (List<MP0002>) session.get("DEPARTMENT_LIST");
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    
	    for(int i=0,j=employeeInfo.size(); i<j; i++){
	    	MP1001 mp1 = employeeInfo.get(i);
	    	String departId = mp1.getMP1001_DEPARTMENT_ID();
	    	String startDate = mp1.getMP1001_ENTRY_DATE();
	    	
	    	mp1.setCURRENT_PAGE_NUM(String.valueOf(pageNum));
	    	
	    	if(startDate != null && !startDate.equals("")){
		    	//格式化入职日期
				Date entryDate = sdf.parse(mp1.getMP1001_ENTRY_DATE());
				mp1.setMP1001_ENTRY_DATE(sdf.format(entryDate));
	    	}

	    	//String employeeNum = mp5.getMP0005_AUTHOR();
	    	// 如果没有部门，进行下一条处理
	    	if( null == departId || departId.equals("")){
	    		continue;
	    	}
	    	// 解析部门
	    	for(int m=0,n=departmentList.size(); m<n; m++){
	    		MP0002 mp2 = departmentList.get(m);
	    		String seq = String.valueOf(mp2.getMP0002_SEQ());
	    		if(departId.equals(seq)){
	    			mp1.setMP1001_DEPARTMENT_NAME(mp2.getMP0002_DEPARTMENT_NAME());
	    			break;
	    		}
	    	}
	    }
	}
	
	// 根据部门ID取得部门名称
	@SuppressWarnings("unchecked")
	private String getdepartmentNameById(String departmentSeq){
		// 缓存登陆用户的信息
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		String departmentName = "";
		
		if(null == session){
			System.out.println("session is null.");
			return Action.LOGIN;
		}
		if(!session.containsKey("DEPARTMENT_LIST")){
			System.out.println("There is the key(DEPARTMENT_LIST) in session.");
			return Action.LOGIN;
		}
		// 部门信息在登录后就缓存在Session中了
	    departmentList = (List<MP0002>) session.get("DEPARTMENT_LIST");
	    
	    for(int m=0,n=departmentList.size(); m<n; m++){
	    	MP0002 mp2 = departmentList.get(m);
	    	String seq = String.valueOf(mp2.getMP0002_SEQ());
	    	
	    	if(departmentSeq.equals(seq)){
	    		departmentName = mp2.getMP0002_DEPARTMENT_NAME();
	    		break;
	    	}
	    }
	    
	    return departmentName;
	}
	
	public String departmentEmployeeSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Department Employee Information");
			//----------------------------Operation History------------------
			
			if(!departmentID.equals("")){
			    List<MP1001> mp11List = service.findbyDepartmentId(departmentID);
			    
			    for(int i=0, j=mp11List.size(); i<j; i++){
			    	MP1001 mp11 = mp11List.get(i);
			    	employeeInfoList.put(mp11.getMP1001_EMPLOYEE_NUM(), mp11.getMP1001_EMPLOYEE_NUM() + " " + mp11.getMP1001_PREFERED_NAME());
			    }
			}
			
			//employeeInfoList
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	public String telephoneListInit() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
    	try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Employee Telephone Information List Init");
			//----------------------------Operation History------------------
		
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0001004,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_SEARCH)){
				optSearch = "1";
			}
			if(optMap.containsKey(Constant.OPT_PDF)){
				optPdf = "1";
			}

        	employeeTelephoneList = service.findAll();
        	return SUCCESS;
    	}catch(Exception ex){
    		log.info(ex.getMessage());
    		return "error";
    	}
    }
	
	public String employeeTelephoneSearch() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Employee Telephone Information");
			//----------------------------Operation History------------------
			
			HashMap<String, String> propertyMap = new HashMap<String, String>();
			propertyMap.put("NUM", employeeNum); // 员工编码
			propertyMap.put("NAME", employeeName); // 员工名
			propertyMap.put("STATUS", "99");//过滤掉临时员工
			
			employeeTelephoneList = service.findByProperty(propertyMap);
	
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	
	public String departmentalTransfersReportInit(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    
		try{
			departmentalTransfersInfo = serviceMP1004.findAll2("","");
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Departmental Transfers Information Report Init");
			//----------------------------Operation History------------------
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public String departmentalTransfersSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    
		try{
			departmentalTransfersInfo = serviceMP1004.findAll2(employeeNum,employeeName);
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Departmental Transfers Information");
			//----------------------------Operation History------------------
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public String trunoverReportInit(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Turnover Report Init");
			//----------------------------Operation History------------------
			
/*			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
		    // 部门信息在登录后就缓存在Session中了
		    departmentList = (List<MP0002>) session.get("DEPARTMENT_LIST");
		    Map<String,String> departmentMap = new HashMap<String,String>();
		    
		    for(int i=0,j=departmentList.size(); i<j; i++){
		    	MP0002 mp02 = departmentList.get(i);

		    	if(!departmentMap.containsKey(String.valueOf(mp02.getMP0002_SEQ()))){
		    		departmentMap.put(String.valueOf(mp02.getMP0002_SEQ()), mp02.getMP0002_DEPARTMENT_NAME());
		    	}
		    }
		    
			turnoverInfo = service.findAllResign("","");
			
			for(int m=0,n=turnoverInfo.size(); m<n; m++){
				MP1001 mp11 = turnoverInfo.get(m);
				
				// 解析离职时间
				mp11.setMP1001_RESIGN_DATE(mp11.getMP1001_RESIGN_DATE().substring(0,10));
				// 解析部门名称
				if(departmentMap.containsKey(mp11.getMP1001_DEPARTMENT_ID())){
					mp11.setMP1001_DEPARTMENT_NAME(departmentMap.get(mp11.getMP1001_DEPARTMENT_ID()));
				}
			}*/
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	@SuppressWarnings("unchecked")
	public String turnoverSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
	    // 部门信息在登录后就缓存在Session中了
	    departmentList = (List<MP0002>) session.get("DEPARTMENT_LIST");
	    Map<String,String> departmentMap = new HashMap<String,String>();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Turnover Information");
			//----------------------------Operation History------------------
		    
		    for(int i=0,j=departmentList.size(); i<j; i++){
		    	MP0002 mp02 = departmentList.get(i);

		    	if(!departmentMap.containsKey(String.valueOf(mp02.getMP0002_SEQ()))){
		    		departmentMap.put(String.valueOf(mp02.getMP0002_SEQ()), mp02.getMP0002_DEPARTMENT_NAME());
		    	}
		    }
		    
			turnoverInfo = service.findAllResign(employeeNum,employeeName);
			
			for(int m=0,n=turnoverInfo.size(); m<n; m++){
				MP1001 mp11 = turnoverInfo.get(m);
				
				// 解析离职时间
				mp11.setMP1001_RESIGN_DATE(mp11.getMP1001_RESIGN_DATE().substring(0,10));
				// 解析部门名称
				if(departmentMap.containsKey(mp11.getMP1001_DEPARTMENT_ID())){
					mp11.setMP1001_DEPARTMENT_NAME(departmentMap.get(mp11.getMP1001_DEPARTMENT_ID()));
				}
			}
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public String createEmployeeInfoExcelDocument(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Create Employee Information Excel Document");
			//----------------------------Operation History------------------
			
			// 新建EXCEL工作表
			Workbook wb = ExcelUtil.CreateHSSFWorkBook();
			// 新建一个SHEET页面
			Sheet sheet = ExcelUtil.CreateSheet(wb, "Employee Information");
			// 设置SHEET页面属性
			ExcelUtil.SetSheetPropertyHSSF(sheet);
			// 获取预定的样式
			Map<String, CellStyle> styles = ExcelUtil.CreateStyles(wb);
			
			// Header标题
			String[] titles = {"ID", "EMPLOYEE NUMBER", "NAME", "DEPARTMENT", "JOB TITLE"};
			// 生成标题行
			float rowHeight = 27f;
			ExcelUtil.CreateHeadRow(sheet, titles, rowHeight, styles);
	        // 冻结第一行
	        sheet.createFreezePane(0, 1);
	        
	        int[] cellsWidth = {5,20,16,20,20};
	        // 设置单元格的宽度
	        ExcelUtil.SetCellsWidth(sheet, cellsWidth);
	        
	      //---------------------------主报表-----------------------------------------------------------------------------------------
	        // 取得报表数据
	        String sortStr = "order by mp11.MP1001_EMPLOYEE_NUM ";
	        String searchStr = " and mp11.MP1001_STATUS in ('1','2') ";
	        fileName = "Employees Inforation statistics1.xls";
	        if(pageInitType != null && pageInitType.equals("2")){
	        	sortStr = "order by mp11.MP1001_DEPARTMENT_ID ";
	        	fileName = "Employees Inforation statistics2.xls";
	        }else if(pageInitType != null && pageInitType.equals("3")){
	        	sortStr = "order by mp11.MP1001_DEPARTMENT_ID ";
	        	searchStr = " and mp11.MP1001_STATUS = '3' ";
	        	fileName = "Employees Inforation statistics3.xls";
	        }
	        List<MP1001> mp11List = getEmployeeInfoList(searchStr, sortStr);
	        int count;
	        String[] datas;
	        for(int i=0,j=mp11List.size(); i<j; i++){
	        	MP1001 mp1001Obj = mp11List.get(i);
	        	count = i + 1;
	        	datas = new String[5];
	        	datas[0] = String.valueOf(count);
	        	datas[1] = mp1001Obj.getMP1001_EMPLOYEE_NUM();
	        	datas[2] = mp1001Obj.getMP1001_PREFERED_NAME();
	        	datas[3] = mp1001Obj.getMP1001_DEPARTMENT_NAME(); 
	        	datas[4] = mp1001Obj.getMP1001_POSITION_NAME();
	        	
	        	ExcelUtil.SetCellsValue(count, sheet, styles, datas);
	        }
	        
			// 生成Excel文件
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
	// 取得员工信息列表
	private List<MP1001> getEmployeeInfoList(String search, String order){
		List<MP1001> mp11List = service.findData(search, order,-1,-1);
		return mp11List;
	}
	
	
	public String  createEmployeeInfoExcelDocument2(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Create Employee Information Excel Document2");
			//----------------------------Operation History------------------
			
			// 新建EXCEL工作表
			Workbook wb = ExcelUtil.CreateHSSFWorkBook();
			// 新建一个SHEET页面
			Sheet sheet = ExcelUtil.CreateSheet(wb, "Employee Information");
			// 设置SHEET页面属性
			ExcelUtil.SetSheetPropertyHSSF(sheet);
			// 获取预定的样式
			Map<String, CellStyle> styles = ExcelUtil.CreateStyles(wb);
			
			// Header标题
			String[] titles = {"ID", "EMPLOYEE NUMBER", "NAME", "DEPARTMENT(OLD)", "DEPARTMENT(NEW)","STARTING DATE"};
			// 生成标题行
			float rowHeight = 27f;
			ExcelUtil.CreateHeadRow(sheet, titles, rowHeight, styles);
	        // 冻结第一行
	        sheet.createFreezePane(0, 1);
	        
	        int[] cellsWidth = {5,20,16,30,30,30};
	        // 设置单元格的宽度
	        ExcelUtil.SetCellsWidth(sheet, cellsWidth);
	        
	      //---------------------------主报表-----------------------------------------------------------------------------------------
	        // 取得报表数据
	        List<MP1004> mp14List = serviceMP1004.findAll2("","");
	        int count;
	        String[] datas = new String[6];
	        for(int i=0,j=mp14List.size(); i<j; i++){
	        	MP1004 mp1004Obj = mp14List.get(i);
	        	count = i + 1;
	        	datas = new String[6];
	        	datas[0] = String.valueOf(count);
	        	datas[1] = mp1004Obj.getMP1004_EMPLOYEE_NUM();
	        	datas[2] = mp1004Obj.getMP1004_EMPLOYEE_NAME();
	        	datas[3] = mp1004Obj.getMP1004_DEPARTMENT_NAME_OLD(); 
	        	datas[4] = mp1004Obj.getMP1004_DEPARTMENT_NAME();
	        	datas[5] = mp1004Obj.getMP1004_TRANSFER_DATETIME();
	        	
	        	ExcelUtil.SetCellsValue(count, sheet, styles, datas);
	        }
	        
			// 生成Excel文件
	        fileName = "Employees Inforation statistics4.xls";
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
	
	
	//供应商选择共同页面初始化处理
	public String employeeSelectInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			pageNum = 1;
			String departmentId = "";
			if(commonSeq == null || commonSeq.trim().equals("")){
				departmentId = employeeData.getMP1001_DEPARTMENT_ID();
			}else{
				departmentId = commonSeq;
			}

			getEmployeeSelectData(departmentId, pageNum);
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"employee Select Common Page");
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
	//供应商选择共同页面初始化处理
	public String employeeSelectInit2(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			String departmentId = employeeData.getMP1001_DEPARTMENT_ID();
			getEmployeeSelectData2(departmentId);
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"employee Select Common Page");
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
	// 异步刷新
	public String employeeSelectRefresh(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			String departmentId = employeeData.getMP1001_DEPARTMENT_ID();
			getEmployeeSelectData(departmentId, pageNum);
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"employee Select Refresh");
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
	// 异步刷新
	public String employeeSelectRefresh2(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			String departmentId = employeeData.getMP1001_DEPARTMENT_ID();
			getEmployeeSelectData2(departmentId);
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"employee Select Refresh");
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
	private void getEmployeeSelectData(String departmentId, int _pageNum){	
		StringBuffer searchStr = new StringBuffer();
		// Employee Number
		if(param1 != null && !param1.equals("")){
			searchStr.append(" and mp11.MP1001_EMPLOYEE_NUM = '" + param1 + "' ");
		}
		// Employee Name
		if(param2 != null && !param2.equals("")){
			searchStr.append(" and mp11.MP1001_PREFERED_NAME like '%" + param2 + "%' ");
		}
		// Department
		if(pageType != null && pageType.equals("performance")){
			if(param3 != null && !param3.equals("") && !param3.equals("-1") && !param3.equals("undefined")){
				searchStr.append(" and mp11.MP1001_DEPARTMENT_ID = '" + param3 + "' ");
			}else{
				if(departmentId != null && !departmentId.equals("")){
					searchStr.append(" and mp11.MP1001_DEPARTMENT_ID = '" + departmentId + "' ");
				}
			}
		}else{
			if(departmentId != null && !departmentId.equals("")){
				searchStr.append(" and mp11.MP1001_DEPARTMENT_ID = '" + departmentId + "' ");
			}
		}
		searchStr.append(" and mp11.MP1001_STATUS  in ('1','2') ");
		
		employeeInfo = service.findData(searchStr.toString(),"", -1, -1);
		
		if( 0 == employeeInfo.size()%Constant.PAGE_SIZE){
			pageCount = employeeInfo.size()/Constant.PAGE_SIZE;
		}else{
			pageCount = employeeInfo.size()/Constant.PAGE_SIZE + 1;
		}
		
		if(pageCount < _pageNum){
			_pageNum = pageCount;
		}
		
		employeeInfo = service.findData(searchStr.toString(),"", _pageNum, Constant.PAGE_SIZE);
		commonDepartmentList = UtilCommon.getDepartmentList();
	}
	private void getEmployeeSelectData2(String departmentId){	
		StringBuffer searchStr = new StringBuffer();
		// Employee Number
		if(param1 != null && !param1.equals("")){
			searchStr.append(" and mp11.MP1001_EMPLOYEE_NUM = '" + param1 + "' ");
		}
		// Employee Name
		if(param2 != null && !param2.equals("")){
			searchStr.append(" and mp11.MP1001_PREFERED_NAME like '%" + param2 + "%' ");
		}
		// Department
		if(pageType != null && pageType.equals("performance")){
			if(param3 != null && !param3.equals("") && !param3.equals("-1") && !param3.equals("undefined")){
				searchStr.append(" and mp11.MP1001_DEPARTMENT_ID = '" + param3 + "' ");
			}else{
				if(departmentId != null && !departmentId.equals("")){
					searchStr.append(" and mp11.MP1001_DEPARTMENT_ID = '" + departmentId + "' ");
				}
			}
		}else{
			if(departmentId != null && !departmentId.equals("")){
				searchStr.append(" and mp11.MP1001_DEPARTMENT_ID = '" + departmentId + "' ");
			}
		}
		searchStr.append(" and mp11.MP1001_STATUS  in ('1','2') ");
		
		employeeInfo = service.findData(searchStr.toString(),"", -1, -1);
		
		commonDepartmentList = UtilCommon.getDepartmentList();
	}
	// 保存数据
	public String employeeSelectSave2(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"employee Select Save");
			//----------------------------Operation History------------------
			
			//serviceMP7008.executeStatement(" update MP7008 set MP7008_PLAN_SEQ = 'temp001' where MP7008_PLAN_SEQ= '" + commonSeq + "' ");
			
			MP7008 mp78 = new MP7008();
			StringBuffer searchStr = new StringBuffer();
			for(int i=0, j=empArray.size(); i<j; i++){
				if(empArray.get(i) != null && !empArray.get(i).equals("")){
					searchStr = new StringBuffer();
					searchStr.append(" and mp78.MP7008_PLAN_SEQ = '" + commonSeq + "' ");
					searchStr.append(" and mp78.MP7008_EMPLOYEE_NUM = '" + empArray.get(i) + "' ");
					List<MP7008> mp78List = serviceMP7008.findDataBySelfDefined(searchStr.toString(), "", -1, -1);
					if(mp78List != null && mp78List.size() == 0){
						mp78 = new MP7008();
						mp78.setMP7008_SEQ(Constant.generateSeq() + UtilCommon.getTempSeq());
						mp78.setMP7008_PLAN_SEQ(commonSeq);
						mp78.setMP7008_EMPLOYEE_NUM(empArray.get(i));
						
						serviceMP7008.save(mp78);
					}else{
						logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Employee has exist:{Employee Number:" + empArray.get(i) + ", Plan Seq:" + commonSeq + "}");
					}
				}
			}

			List<MP7003> mp73List = serviceMP7003.findByProperty("MP7003_MASTER_KEY", commonSeq);
			List<MP7008> mp78List = serviceMP7008.findByProperty("MP7008_PLAN_SEQ", commonSeq);
			MP7002 mp72 = serviceMP7002.findById(commonSeq);
			if(mp73List == null || mp73List.size() == 0 || mp78List == null || mp78List.size() == 0){
				mp72.setMP7002_EXECUTE_STATUS("0");
			}else{
				mp72.setMP7002_EXECUTE_STATUS("1");
			}
			serviceMP7002.update(mp72);
			
			//serviceMP7008.executeStatement(" delete from MP7008 where MP7008_PLAN_SEQ= 'temp001' ");
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.append("<script type='text/javascript'>");
			out.println(" window.dialogArguments.document.getElementById('empRefresh').click();");
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
	
	//-----------------临时员工管理--------------------------
	// 表(MP1010)新增、编辑页面初始化
	public String mp1010AddInit(){
	    ActionContext context = ActionContext.getContext();
	    Map<String,Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    //String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
	    try{
	        //----------------------------Operation History------------------
	        LogUtil logUtil = new LogUtil();
	        logUtil.setServiceMP0011(serviceMP0011);
	        logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add MP1010 Information Page Init");
	        //----------------------------Operation History------------------
	        departmentInfoList = UtilCommon.getDepartmentList();
	        genderList = Constant.getGenderList();
	        
	        if(pageType.equals("edit")){
	            if(commonSeq != null && !commonSeq.equals("")){
	                mp1010 = serviceMP1010.findById(commonSeq);
	            }else{
	                return "error";
	            }
	        }else if(pageType.equals("add")){
			    List<MP0006> mp06List = serviceMP0006.findByProperty("MP0006_CODE", "TMP_EMP_NUM"); //临时员工编码
			    MP0006 mp6 = mp06List.get(0);
			    int maxNum = Integer.parseInt(mp6.getMP0006_VALUE());
			    java.text.DecimalFormat format = new java.text.DecimalFormat("0000");
			    String tempEmpNum = "T" + format.format(maxNum);
			    
			    mp1010.setMP1010_EMPLOYEE_NUM(tempEmpNum);
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

	// MP1010管理页面初始化
	public String mp1010InfoMngInit(){
	    ActionContext context = ActionContext.getContext();
	    Map<String,Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    String empNum = employeeData.getMP1001_EMPLOYEE_NUM();

	    try{
	        //----------------------------Operation History------------------
	        LogUtil logUtil = new LogUtil();
	        logUtil.setServiceMP0011(serviceMP0011);
	        logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"MP1010 Information Management Page Init");
	        //----------------------------Operation History------------------
	        
	        Map<String,String> propMap = new HashMap<String,String>();
	        getMP1010InfoByPage(propMap, 1,empNum);

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

	// MP1010信息分页处理
	public String mp1010InfoSearch(){
	    ActionContext context = ActionContext.getContext();
	    Map<String,Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    String empNum = employeeData.getMP1001_EMPLOYEE_NUM();

	    try{
	        //----------------------------Operation History------------------
	        LogUtil logUtil = new LogUtil();
	        logUtil.setServiceMP0011(serviceMP0011);
	        logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME()," SearchMP1010 Information");
	        //----------------------------Operation History------------------

	        Map<String,String> propMap = new HashMap<String,String>();
	        pageNum = 1;
	        getMP1010InfoByPage(propMap, pageNum,empNum);

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

	// MP1010信息分页处理
	public String mp1010InfoPageClick(){
	    ActionContext context = ActionContext.getContext();
	    Map<String,Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    String empNum = employeeData.getMP1001_EMPLOYEE_NUM();

	    try{
	        //----------------------------Operation History------------------
	        LogUtil logUtil = new LogUtil();
	        logUtil.setServiceMP0011(serviceMP0011);
	        logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"MP1010 Information Page Click");
	        //----------------------------Operation History------------------
	        Map<String,String> propMap = new HashMap<String,String>();
	        getMP1010InfoByPage(propMap,pageNum,empNum);

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

	// MP1010信息分页处理
	public String mp1010InfoDelete(){
	    ActionContext context = ActionContext.getContext();
	    Map<String,Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    String empNum = employeeData.getMP1001_EMPLOYEE_NUM();

	    try{
	        if (commonSeq != null && !commonSeq.equals(""))
	        {
	            MP1010 _mp1010= serviceMP1010.findById(commonSeq);
	            if (_mp1010 != null)
	            {
	            	_mp1010.setMP1010_STATUS("0");
	            	serviceMP1010.update(_mp1010);
	                //----------------------------Operation History------------------
	                LogUtil logUtil = new LogUtil();
	                logUtil.setServiceMP0011(serviceMP0011);
	                logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Delete MP1010 Information");
	                //----------------------------Operation History------------------
	             }
	        }
	        Map<String,String> propMap = new HashMap<String,String>();
	        getMP1010InfoByPage(propMap,pageNum,empNum);

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

	// 表(MP1010)新增、编辑页面初始化
	public String mp1010InfoSave(){
	    ActionContext context = ActionContext.getContext();
	    Map<String,Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
	    try{
	        //----------------------------Operation History------------------
	        LogUtil logUtil = new LogUtil();
	        logUtil.setServiceMP0011(serviceMP0011);
	        //----------------------------Operation History------------------
	        if(pageType.equals("add")){
	        	mp1010.setMP1010_CREATE_DATETIME(UtilDate.get24DateTime());
	        	mp1010.setMP1010_CREATE_USER(empNum);
	        	mp1010.setMP1010_STATUS("1");
	        	
	            serviceMP1010.save(mp1010);
	            addFieldError("errMsg","数据保存成功");
	            
			    List<MP0006> mp06List = serviceMP0006.findByProperty("MP0006_CODE", "TMP_EMP_NUM"); //临时员工编码
			    MP0006 mp6 = mp06List.get(0);
			    int maxNum = Integer.parseInt(mp6.getMP0006_VALUE()) + 1;
			    mp6.setMP0006_VALUE(Integer.toString(maxNum));
			    serviceMP0006.update(mp6);
	            
	            // 记录操作日志
	            logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add MP1010 Information");

	            //mp1010 = new MP1010();
	            HttpServletResponse response = ServletActionContext.getResponse();
	            response.setCharacterEncoding("utf-8");
	            PrintWriter out = response.getWriter();

	            out.append("<script type='text/javascript'>");
	            out.append("alert('Save Success');");
	            out.append("window.dialogArguments.document.getElementById('btnRefresh').click();");
	            out.append("window.close();");
	            out.append("</script>");
	            out.flush();
	            out.close();
	            
	            return SUCCESS;
	        }else if(pageType.equals("edit")){
	            MP1010 _mp1010 = serviceMP1010.findById(commonSeq);
	            _mp1010.setMP1010_PREFERED_NAME(mp1010.getMP1010_PREFERED_NAME());
	            _mp1010.setMP1010_DEPARTMENT(mp1010.getMP1010_DEPARTMENT());
	            _mp1010.setMP1010_EMPLOYEE_ID(mp1010.getMP1010_EMPLOYEE_ID());
	            _mp1010.setMP1010_GENDER(mp1010.getMP1010_GENDER());
	            _mp1010.setMP1010_EDIT_DATETIME(UtilDate.get24DateTime());
	            _mp1010.setMP1010_EDIT_USER(empNum);
	            _mp1010.setMP1010_FIRST_NAME(mp1010.getMP1010_FIRST_NAME());
	            _mp1010.setMP1010_TEL(mp1010.getMP1010_TEL());
	            _mp1010.setMP1010_NATIONAL(mp1010.getMP1010_NATIONAL());
	            _mp1010.setMP1010_PAYROLL_NUM(mp1010.getMP1010_PAYROLL_NUM());

	            serviceMP1010.update(_mp1010);
	            // 记录操作日志
	            logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Save MP1010 Information");

	            HttpServletResponse response = ServletActionContext.getResponse();
	            response.setCharacterEncoding("utf-8");
	            PrintWriter out = response.getWriter();

	            out.append("<script type='text/javascript'>");
	            out.append("alert('Save Success');");
	            out.append("window.dialogArguments.document.getElementById('btnRefresh').click();");
	            out.append("window.close();");
	            out.append("</script>");
	            out.flush();
	            out.close();

	            return SUCCESS;
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

	// 重新取得分页数据
	private void getMP1010InfoByPage(Map<String,String> propMap, int _pageNum, String empId){
		departmentInfoList = UtilCommon.getDepartmentList();
		getAuthority(empId,Constant.F0001010);
        if(param3==null || param3.equals("-1")){
        	param3 = "";
        }
		
        propMap.put("PARAM1", param1);
        propMap.put("PARAM2", param2);
        propMap.put("PARAM3", param3);
        propMap.put("PARAM4", param4);
        
	    mp1010InfoList = serviceMP1010.findByPropertyByPage(propMap, -1, -1);
	    if( 0 == mp1010InfoList.size()%Constant.PAGE_SIZE){
	        pageCount = mp1010InfoList.size()/Constant.PAGE_SIZE;
	    }else{
	        pageCount = mp1010InfoList.size()/Constant.PAGE_SIZE + 1;
	    }
	    if(_pageNum > pageCount){
	        _pageNum= pageCount;
	    }
	    mp1010InfoList = serviceMP1010.findByPropertyByPage(propMap, _pageNum, Constant.PAGE_SIZE);
	}
	
	// 取得页面权限信息
	private void getAuthority(String _employeeNum,String functionNum){
		HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(_employeeNum, functionNum,Constant.SYSTEM_NUM_HR);
		if(optMap.containsKey(Constant.OPT_SEARCH)){
			optSearch = "1";
		}
		if(optMap.containsKey(Constant.OPT_ADD)){
			optAdd = "1";
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

	
	/**
	 * @return the service
	 */
	public IMP1001Service getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(IMP1001Service service) {
		this.service = service;
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
	 * @return the employeeInfo
	 */
	public List<MP1001> getEmployeeInfo() {
		return employeeInfo;
	}

	/**
	 * @param employeeInfo the employeeInfo to set
	 */
	public void setEmployeeInfo(List<MP1001> employeeInfo) {
		this.employeeInfo = employeeInfo;
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
	 * @return the educationInfoList
	 */
	public List<MP1002> getEducationInfoList() {
		return educationInfoList;
	}

	/**
	 * @param educationInfoList the educationInfoList to set
	 */
	public void setEducationInfoList(List<MP1002> educationInfoList) {
		this.educationInfoList = educationInfoList;
	}

	/**
	 * @return the companyInfoList
	 */
	public List<MP1003> getCompanyInfoList() {
		return companyInfoList;
	}

	/**
	 * @param companyInfoList the companyInfoList to set
	 */
	public void setCompanyInfoList(List<MP1003> companyInfoList) {
		this.companyInfoList = companyInfoList;
	}

	/**
	 * @return the serviceMP1002
	 */
	public IMP1002Service getServiceMP1002() {
		return serviceMP1002;
	}

	/**
	 * @param serviceMP1002 the serviceMP1002 to set
	 */
	public void setServiceMP1002(IMP1002Service serviceMP1002) {
		this.serviceMP1002 = serviceMP1002;
	}

	/**
	 * @return the serviceMP1003
	 */
	public IMP1003Service getServiceMP1003() {
		return serviceMP1003;
	}

	/**
	 * @param serviceMP1003 the serviceMP1003 to set
	 */
	public void setServiceMP1003(IMP1003Service serviceMP1003) {
		this.serviceMP1003 = serviceMP1003;
	}

	/**
	 * @return the mp1002
	 */
	public MP1002 getMp1002() {
		return mp1002;
	}

	/**
	 * @param mp1002 the mp1002 to set
	 */
	public void setMp1002(MP1002 mp1002) {
		this.mp1002 = mp1002;
	}

	/**
	 * @return the mp1003
	 */
	public MP1003 getMp1003() {
		return mp1003;
	}

	/**
	 * @param mp1003 the mp1003 to set
	 */
	public void setMp1003(MP1003 mp1003) {
		this.mp1003 = mp1003;
	}

	/**
	 * @return the educationHidden
	 */
	public String getEducationHidden() {
		return educationHidden;
	}

	/**
	 * @param educationHidden the educationHidden to set
	 */
	public void setEducationHidden(String educationHidden) {
		this.educationHidden = educationHidden;
	}

	/**
	 * @return the workHidden
	 */
	public String getWorkHidden() {
		return workHidden;
	}

	/**
	 * @param workHidden the workHidden to set
	 */
	public void setWorkHidden(String workHidden) {
		this.workHidden = workHidden;
	}

	/**
	 * @return the mp1005
	 */
	public MP1005 getMp1005() {
		return mp1005;
	}

	/**
	 * @param mp1005 the mp1005 to set
	 */
	public void setMp1005(MP1005 mp1005) {
		this.mp1005 = mp1005;
	}

	/**
	 * @return the serviceMP1005
	 */
	public IMP1005Service getServiceMP1005() {
		return serviceMP1005;
	}

	/**
	 * @param serviceMP1005 the serviceMP1005 to set
	 */
	public void setServiceMP1005(IMP1005Service serviceMP1005) {
		this.serviceMP1005 = serviceMP1005;
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
	 * @return the mp0006
	 */
	public MP0006 getMp0006() {
		return mp0006;
	}

	/**
	 * @param mp0006 the mp0006 to set
	 */
	public void setMp0006(MP0006 mp0006) {
		this.mp0006 = mp0006;
	}

	/**
	 * @return the universityType
	 */
	public String getUniversityType() {
		return universityType;
	}

	/**
	 * @param universityType the universityType to set
	 */
	public void setUniversityType(String universityType) {
		this.universityType = universityType;
	}

	/**
	 * @return the universityName
	 */
	public String getUniversityName() {
		return universityName;
	}

	/**
	 * @param universityName the universityName to set
	 */
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @return the major
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * @param major the major to set
	 */
	public void setMajor(String major) {
		this.major = major;
	}

	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * @return the finish
	 */
	public String getFinish() {
		return finish;
	}

	/**
	 * @param finish the finish to set
	 */
	public void setFinish(String finish) {
		this.finish = finish;
	}

	/**
	 * @return the rowIndex
	 */
	public String getRowIndex() {
		return rowIndex;
	}

	/**
	 * @param rowIndex the rowIndex to set
	 */
	public void setRowIndex(String rowIndex) {
		this.rowIndex = rowIndex;
	}

	/**
	 * @return the educationInfoList_D
	 */
	public List<MP1002> getEducationInfoList_D() {
		return educationInfoList_D;
	}

	/**
	 * @param educationInfoList_D the educationInfoList_D to set
	 */
	public void setEducationInfoList_D(List<MP1002> educationInfoList_D) {
		this.educationInfoList_D = educationInfoList_D;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the depName
	 */
	public String getDepName() {
		return depName;
	}

	/**
	 * @param depName the depName to set
	 */
	public void setDepName(String depName) {
		this.depName = depName;
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
	 * @return the entryDate
	 */
	public String getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return the terminationDate
	 */
	public String getTerminationDate() {
		return terminationDate;
	}

	/**
	 * @param terminationDate the terminationDate to set
	 */
	public void setTerminationDate(String terminationDate) {
		this.terminationDate = terminationDate;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return the visaTypeList
	 */
	public Map<String, String> getVisaTypeList() {
		return visaTypeList;
	}

	/**
	 * @param visaTypeList the visaTypeList to set
	 */
	public void setVisaTypeList(Map<String, String> visaTypeList) {
		this.visaTypeList = visaTypeList;
	}

	/**
	 * @return the genderList
	 */
	public Map<String, String> getGenderList() {
		return genderList;
	}

	/**
	 * @param genderList the genderList to set
	 */
	public void setGenderList(Map<String, String> genderList) {
		this.genderList = genderList;
	}

	/**
	 * @return the marryStatusList
	 */
	public Map<String, String> getMarryStatusList() {
		return marryStatusList;
	}

	/**
	 * @param marryStatusList the marryStatusList to set
	 */
	public void setMarryStatusList(Map<String, String> marryStatusList) {
		this.marryStatusList = marryStatusList;
	}

	/**
	 * @return the institutionTypeList
	 */
	public Map<String, String> getInstitutionTypeList() {
		return institutionTypeList;
	}

	/**
	 * @param institutionTypeList the institutionTypeList to set
	 */
	public void setInstitutionTypeList(Map<String, String> institutionTypeList) {
		this.institutionTypeList = institutionTypeList;
	}

	/**
	 * @return the qualificationLevList
	 */
	public Map<String, String> getQualificationLevList() {
		return qualificationLevList;
	}

	/**
	 * @param qualificationLevList the qualificationLevList to set
	 */
	public void setQualificationLevList(Map<String, String> qualificationLevList) {
		this.qualificationLevList = qualificationLevList;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	 * @return the departmentId
	 */
	public String getDepartmentID() {
		return departmentID;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @return the visaType
	 */
	public String getVisaType() {
		return visaType;
	}

	/**
	 * @param visaType the visaType to set
	 */
	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}

	/**
	 * @return the serviceMP1004
	 */
	public IMP1004Service getServiceMP1004() {
		return serviceMP1004;
	}

	/**
	 * @param serviceMP1004 the serviceMP1004 to set
	 */
	public void setServiceMP1004(IMP1004Service serviceMP1004) {
		this.serviceMP1004 = serviceMP1004;
	}

	/**
	 * @return the mp1004
	 */
	public MP1004 getMp1004() {
		return mp1004;
	}

	/**
	 * @param mp1004 the mp1004 to set
	 */
	public void setMp1004(MP1004 mp1004) {
		this.mp1004 = mp1004;
	}

	public Map<String, String> getGroupList() {
		return groupList;
	}

	public void setGroupList(Map<String, String> groupList) {
		this.groupList = groupList;
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

	public File getFileupload() {
		return fileupload;
	}

	public void setFileupload(File fileupload) {
		this.fileupload = fileupload;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAttachmentUrl() {
		return attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	public String getFileRealName() {
		return fileRealName;
	}

	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getFileuploadFileName() {
		return fileuploadFileName;
	}

	public void setFileuploadFileName(String fileuploadFileName) {
		this.fileuploadFileName = fileuploadFileName;
	}
	/**
	 * @return the tmpEmployeeDataList
	 */
	public List<MP1001> getTmpEmployeeDataList() {
		return tmpEmployeeDataList;
	}
	/**
	 * @param tmpEmployeeDataList the tmpEmployeeDataList to set
	 */
	public void setTmpEmployeeDataList(List<MP1001> tmpEmployeeDataList) {
		this.tmpEmployeeDataList = tmpEmployeeDataList;
	}

	/**
	 * @return the eduSeq
	 */
	public String getEduSeq() {
		return eduSeq;
	}

	/**
	 * @param eduSeq the eduSeq to set
	 */
	public void setEduSeq(String eduSeq) {
		this.eduSeq = eduSeq;
	}

	/**
	 * @return the expSeq
	 */
	public String getExpSeq() {
		return expSeq;
	}

	/**
	 * @param expSeq the expSeq to set
	 */
	public void setExpSeq(String expSeq) {
		this.expSeq = expSeq;
	}

	/**
	 * @return the conSeq
	 */
	public String getConSeq() {
		return conSeq;
	}

	/**
	 * @param conSeq the conSeq to set
	 */
	public void setConSeq(String conSeq) {
		this.conSeq = conSeq;
	}

	/**
	 * @return the contactInfoList
	 */
	public List<MP1005> getContactInfoList() {
		return contactInfoList;
	}

	/**
	 * @param contactInfoList the contactInfoList to set
	 */
	public void setContactInfoList(List<MP1005> contactInfoList) {
		this.contactInfoList = contactInfoList;
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
	 * @return the mp0008
	 */
	public MP0008 getMp0008() {
		return mp0008;
	}

	/**
	 * @param mp0008 the mp0008 to set
	 */
	public void setMp0008(MP0008 mp0008) {
		this.mp0008 = mp0008;
	}

	/**
	 * @return the positionList
	 */
	public Map<String, String> getPositionList() {
		return positionList;
	}

	/**
	 * @param positionList the positionList to set
	 */
	public void setPositionList(Map<String, String> positionList) {
		this.positionList = positionList;
	}

	/**
	 * @return the topDegreeList
	 */
	public Map<String, String> getTopDegreeList() {
		return topDegreeList;
	}

	/**
	 * @param topDegreeList the topDegreeList to set
	 */
	public void setTopDegreeList(Map<String, String> topDegreeList) {
		this.topDegreeList = topDegreeList;
	}

	/**
	 * @return the saveFlag
	 */
	public String getSaveFlag() {
		return saveFlag;
	}

	/**
	 * @param saveFlag the saveFlag to set
	 */
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}

	/**
	 * @return the statusList
	 */
	public Map<String, String> getStatusList() {
		return statusList;
	}

	/**
	 * @param statusList the statusList to set
	 */
	public void setStatusList(Map<String, String> statusList) {
		this.statusList = statusList;
	}

	/**
	 * @return the employeeTelephoneList
	 */
	public List<MP1001> getEmployeeTelephoneList() {
		return employeeTelephoneList;
	}

	/**
	 * @param employeeTelephoneList the employeeTelephoneList to set
	 */
	public void setEmployeeTelephoneList(List<MP1001> employeeTelephoneList) {
		this.employeeTelephoneList = employeeTelephoneList;
	}

	/**
	 * @return the loginPosition
	 */
	public String getLoginPosition() {
		return loginPosition;
	}

	/**
	 * @param loginPosition the loginPosition to set
	 */
	public void setLoginPosition(String loginPosition) {
		this.loginPosition = loginPosition;
	}

	/**
	 * @return the loginDepartId
	 */
	public String getLoginDepartId() {
		return loginDepartId;
	}

	/**
	 * @param loginDepartId the loginDepartId to set
	 */
	public void setLoginDepartId(String loginDepartId) {
		this.loginDepartId = loginDepartId;
	}

	/**
	 * @return the resignTypeList
	 */
	public Map<String, String> getResignTypeList() {
		return resignTypeList;
	}

	/**
	 * @param resignTypeList the resignTypeList to set
	 */
	public void setResignTypeList(Map<String, String> resignTypeList) {
		this.resignTypeList = resignTypeList;
	}

	/**
	 * @return the pageInitType
	 */
	public String getPageInitType() {
		return pageInitType;
	}

	/**
	 * @param pageInitType the pageInitType to set
	 */
	public void setPageInitType(String pageInitType) {
		this.pageInitType = pageInitType;
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
	 * @return the empStatus
	 */
	public String getEmpStatus() {
		return empStatus;
	}

	/**
	 * @param empStatus the empStatus to set
	 */
	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	/**
	 * @return the serviceMP2002
	 */
	public IMP2002Service getServiceMP2002() {
		return serviceMP2002;
	}

	/**
	 * @param serviceMP2002 the serviceMP2002 to set
	 */
	public void setServiceMP2002(IMP2002Service serviceMP2002) {
		this.serviceMP2002 = serviceMP2002;
	}

	/**
	 * @return the mp2002
	 */
	public MP2002 getMp2002() {
		return mp2002;
	}

	/**
	 * @param mp2002 the mp2002 to set
	 */
	public void setMp2002(MP2002 mp2002) {
		this.mp2002 = mp2002;
	}

	/**
	 * @return the birthdayList
	 */
	public Map<String, String> getBirthdayList() {
		return birthdayList;
	}

	/**
	 * @param birthdayList the birthdayList to set
	 */
	public void setBirthdayList(Map<String, String> birthdayList) {
		this.birthdayList = birthdayList;
	}

	/**
	 * @return the response
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the religion
	 */
	public String getReligion() {
		return religion;
	}

	/**
	 * @param religion the religion to set
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}

	/**
	 * @return the race
	 */
	public String getRace() {
		return race;
	}

	/**
	 * @param race the race to set
	 */
	public void setRace(String race) {
		this.race = race;
	}

	/**
	 * @return the passportNum
	 */
	public String getPassportNum() {
		return passportNum;
	}

	/**
	 * @param passportNum the passportNum to set
	 */
	public void setPassportNum(String passportNum) {
		this.passportNum = passportNum;
	}

	/**
	 * @return the startingDate
	 */
	public String getStartingDate() {
		return startingDate;
	}

	/**
	 * @param startingDate the startingDate to set
	 */
	public void setStartingDate(String startingDate) {
		this.startingDate = startingDate;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the qualification
	 */
	public String getQualification() {
		return qualification;
	}

	/**
	 * @param qualification the qualification to set
	 */
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	/**
	 * @return the birth
	 */
	public String getBirth() {
		return birth;
	}

	/**
	 * @param birth the birth to set
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}

	/**
	 * @return the serviceMP2001
	 */
	public IMP2001Service getServiceMP2001() {
		return serviceMP2001;
	}

	/**
	 * @param serviceMP2001 the serviceMP2001 to set
	 */
	public void setServiceMP2001(IMP2001Service serviceMP2001) {
		this.serviceMP2001 = serviceMP2001;
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
	 * @return the serviceMP2004
	 */
	public IMP2004Service getServiceMP2004() {
		return serviceMP2004;
	}

	/**
	 * @param serviceMP2004 the serviceMP2004 to set
	 */
	public void setServiceMP2004(IMP2004Service serviceMP2004) {
		this.serviceMP2004 = serviceMP2004;
	}

	/**
	 * @return the mp2001
	 */
	public MP2001 getMp2001() {
		return mp2001;
	}

	/**
	 * @param mp2001 the mp2001 to set
	 */
	public void setMp2001(MP2001 mp2001) {
		this.mp2001 = mp2001;
	}

	/**
	 * @return the mp2003
	 */
	public MP2003 getMp2003() {
		return mp2003;
	}

	/**
	 * @param mp2003 the mp2003 to set
	 */
	public void setMp2003(MP2003 mp2003) {
		this.mp2003 = mp2003;
	}

	/**
	 * @return the mp2004
	 */
	public MP2004 getMp2004() {
		return mp2004;
	}

	/**
	 * @param mp2004 the mp2004 to set
	 */
	public void setMp2004(MP2004 mp2004) {
		this.mp2004 = mp2004;
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
	 * @return the optAdvanceSearch
	 */
	public String getOptAdvanceSearch() {
		return optAdvanceSearch;
	}

	/**
	 * @param optAdvanceSearch the optAdvanceSearch to set
	 */
	public void setOptAdvanceSearch(String optAdvanceSearch) {
		this.optAdvanceSearch = optAdvanceSearch;
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
	 * @return the optReset
	 */
	public String getOptReset() {
		return optReset;
	}

	/**
	 * @param optReset the optReset to set
	 */
	public void setOptReset(String optReset) {
		this.optReset = optReset;
	}


	/**
	 * @return the depSumList
	 */
	public List<MP1001> getDepSumList() {
		return depSumList;
	}


	/**
	 * @param depSumList the depSumList to set
	 */
	public void setDepSumList(List<MP1001> depSumList) {
		this.depSumList = depSumList;
	}



	/**
	 * @return the serviceMP1008
	 */
	public IMP1008Service getServiceMP1008() {
		return serviceMP1008;
	}



	/**
	 * @param serviceMP1008 the serviceMP1008 to set
	 */
	public void setServiceMP1008(IMP1008Service serviceMP1008) {
		this.serviceMP1008 = serviceMP1008;
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
	 * @return the documentInfo
	 */
	public List<MP1009> getDocumentInfo() {
		return documentInfo;
	}



	/**
	 * @param documentInfo the documentInfo to set
	 */
	public void setDocumentInfo(List<MP1009> documentInfo) {
		this.documentInfo = documentInfo;
	}



	/**
	 * @return the documentName
	 */
	public String getDocumentName() {
		return documentName;
	}



	/**
	 * @param documentName the documentName to set
	 */
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}



	/**
	 * @return the mp1009Seq
	 */
	public String getMp1009Seq() {
		return mp1009Seq;
	}



	/**
	 * @param mp1009Seq the mp1009Seq to set
	 */
	public void setMp1009Seq(String mp1009Seq) {
		this.mp1009Seq = mp1009Seq;
	}



	/**
	 * @return the mp1008
	 */
	public MP1008 getMp1008() {
		return mp1008;
	}



	/**
	 * @param mp1008 the mp1008 to set
	 */
	public void setMp1008(MP1008 mp1008) {
		this.mp1008 = mp1008;
	}



	/**
	 * @return the mp1009
	 */
	public MP1009 getMp1009() {
		return mp1009;
	}



	/**
	 * @param mp1009 the mp1009 to set
	 */
	public void setMp1009(MP1009 mp1009) {
		this.mp1009 = mp1009;
	}



	/**
	 * @return the intervieweeID
	 */
	public String getIntervieweeID() {
		return intervieweeID;
	}



	/**
	 * @param intervieweeID the intervieweeID to set
	 */
	public void setIntervieweeID(String intervieweeID) {
		this.intervieweeID = intervieweeID;
	}



	/**
	 * @return the intervieweeName
	 */
	public String getIntervieweeName() {
		return intervieweeName;
	}



	/**
	 * @param intervieweeName the intervieweeName to set
	 */
	public void setIntervieweeName(String intervieweeName) {
		this.intervieweeName = intervieweeName;
	}



	/**
	 * @return the interviewInfo
	 */
	public List<MP1008> getInterviewInfo() {
		return interviewInfo;
	}



	/**
	 * @param interviewInfo the interviewInfo to set
	 */
	public void setInterviewInfo(List<MP1008> interviewInfo) {
		this.interviewInfo = interviewInfo;
	}



	/**
	 * @return the intervieweeSelectedID
	 */
	public String getIntervieweeSelectedID() {
		return intervieweeSelectedID;
	}



	/**
	 * @param intervieweeSelectedID the intervieweeSelectedID to set
	 */
	public void setIntervieweeSelectedID(String intervieweeSelectedID) {
		this.intervieweeSelectedID = intervieweeSelectedID;
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
	 * @return the interviewPhone
	 */
	public String getInterviewPhone() {
		return interviewPhone;
	}



	/**
	 * @param interviewPhone the interviewPhone to set
	 */
	public void setInterviewPhone(String interviewPhone) {
		this.interviewPhone = interviewPhone;
	}



	/**
	 * @return the interviewTime
	 */
	public String getInterviewTime() {
		return interviewTime;
	}



	/**
	 * @param interviewTime the interviewTime to set
	 */
	public void setInterviewTime(String interviewTime) {
		this.interviewTime = interviewTime;
	}



	/**
	 * @return the interviewer
	 */
	public String getInterviewer() {
		return interviewer;
	}



	/**
	 * @param interviewer the interviewer to set
	 */
	public void setInterviewer(String interviewer) {
		this.interviewer = interviewer;
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
	 * @return the employeeInfoList
	 */
	public Map<String, String> getEmployeeInfoList() {
		return employeeInfoList;
	}



	/**
	 * @param employeeInfoList the employeeInfoList to set
	 */
	public void setEmployeeInfoList(Map<String, String> employeeInfoList) {
		this.employeeInfoList = employeeInfoList;
	}



	/**
	 * @return the departmentalTransfersInfo
	 */
	public List<MP1004> getDepartmentalTransfersInfo() {
		return departmentalTransfersInfo;
	}



	/**
	 * @param departmentalTransfersInfo the departmentalTransfersInfo to set
	 */
	public void setDepartmentalTransfersInfo(List<MP1004> departmentalTransfersInfo) {
		this.departmentalTransfersInfo = departmentalTransfersInfo;
	}



	/**
	 * @return the turnoverInfo
	 */
	public List<MP1001> getTurnoverInfo() {
		return turnoverInfo;
	}



	/**
	 * @param turnoverInfo the turnoverInfo to set
	 */
	public void setTurnoverInfo(List<MP1001> turnoverInfo) {
		this.turnoverInfo = turnoverInfo;
	}



	/**
	 * @return the mp2005Num
	 */
	public String getMp2005Num() {
		return mp2005Num;
	}



	/**
	 * @param mp2005Num the mp2005Num to set
	 */
	public void setMp2005Num(String mp2005Num) {
		this.mp2005Num = mp2005Num;
	}



	/**
	 * @return the itServiceMail
	 */
	public String getItServiceMail() {
		return itServiceMail;
	}



	/**
	 * @param itServiceMail the itServiceMail to set
	 */
	public void setItServiceMail(String itServiceMail) {
		this.itServiceMail = itServiceMail;
	}



	/**
	 * @return the directorServiceMail
	 */
	public String getDirectorServiceMail() {
		return directorServiceMail;
	}



	/**
	 * @param directorServiceMail the directorServiceMail to set
	 */
	public void setDirectorServiceMail(String directorServiceMail) {
		this.directorServiceMail = directorServiceMail;
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
	 * @return the empArray
	 */
	public List<String> getEmpArray() {
		return empArray;
	}
	/**
	 * @param empArray the empArray to set
	 */
	public void setEmpArray(List<String> empArray) {
		this.empArray = empArray;
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
	/**
	 * @return the mp1010
	 */
	public MP1010 getMp1010() {
		return mp1010;
	}
	/**
	 * @param mp1010 the mp1010 to set
	 */
	public void setMp1010(MP1010 mp1010) {
		this.mp1010 = mp1010;
	}
	/**
	 * @return the mp1010InfoList
	 */
	public List<MP1010> getMp1010InfoList() {
		return mp1010InfoList;
	}
	/**
	 * @param mp1010InfoList the mp1010InfoList to set
	 */
	public void setMp1010InfoList(List<MP1010> mp1010InfoList) {
		this.mp1010InfoList = mp1010InfoList;
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


}
