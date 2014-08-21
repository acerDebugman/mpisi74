package action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import schedule.CommonJobMethod;
import schedule.executeJobs;
import service.IAC0006Service;
import service.IAC0007Service;
import service.IAC0008Service;
import service.IAC0009Service;
import service.ICHECKINOUTService;
import service.IHOLIDAYService;
import service.IJE0101Service;
import service.IMP0002Service;
import service.IMP0006Service;
import service.IMP0009Service;
import service.IMP0010Service;
import service.IMP0011Service;
import service.IMP1001Service;
import service.IMP1009Service;
import service.IMP1010Service;
import service.IMP2001Service;
import service.IMP2002Service;
import service.IMP2003Service;
import service.IMP2004Service;
import service.IMP2007Service;
import service.IMP2008Service;
import service.IMP2009Service;
import service.IMP2010Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import common.Constant;
import common.CustomerContextHolder;
import common.ExcelUtil;
import common.HeaderFooter;
import common.LogUtil;
import common.Mail;
import common.PageBean;
import common.UtilCommon;
import common.UtilDate;

import dto.AbnormalReptRecdDto;
import dto.LateEarlyDto;
import dto.ShiftworkExcelRecordDto;
import entity.CHECKINOUT;
import entity.HOLIDAY;
import entity.JE0101;
import entity.MP0002;
import entity.MP0006;
import entity.MP0010;
import entity.MP1001;
import entity.MP1009;
import entity.MP1010;
import entity.MP2001;
import entity.MP2002;
import entity.MP2003;
import entity.MP2004;
import entity.MP2007;
import entity.MP2008;
import entity.MP2009;
import entity.MP2010;
import entity.Overtime;

public class ApplyLeaveAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ApplyLeaveAction.class);
	
	private IMP2001Service service;
	private IMP0002Service serviceMp0002;
	private IMP0006Service serviceMP0006;
	private IMP0009Service serviceMP0009;
	private IMP0010Service serviceMp0010;
	private IMP2002Service serviceMp2002;
	private IMP2003Service serviceMp2003;
	private IHOLIDAYService serviceHoliday;
	private IMP1001Service serviceMP1001;
	private IMP1009Service serviceMP1009;
	private IMP2004Service serviceMp2004;
	private IMP2007Service serviceMP2007;
	private IMP2008Service serviceMP2008;
	private IMP2009Service serviceMP2009;
	private IMP0011Service serviceMP0011;
	private IMP1010Service serviceMP1010;
	private ICHECKINOUTService serviceCHECKINOUT;
	
	//for job
	private executeJobs jb;
	
	//for shiftwork configuration
	private IJE0101Service serviceJE0101;
	
	private IAC0006Service serviceAC0006;
	private IAC0007Service serviceAC0007;
	private IAC0008Service serviceAC0008;
	private IAC0009Service serviceAC0009;
    
	private MP2001 mp2001 = new MP2001();
	private MP2002 mp2002 = new MP2002();
	private MP2003 mp2003 = new MP2003();
	private MP2004 mp2004 = new MP2004();
	private MP2007 mp2007 = new MP2007();
	private MP2008 mp2008 = new MP2008();
	private MP1001 mp1001 = new MP1001();
    
	private Map<String, String> leaveTypeList = new LinkedHashMap<String, String>();
	private Map<String, String> leaveStatusList = new LinkedHashMap<String, String>();
	private Map<String, String> approvalTypeList = new LinkedHashMap<String, String>();
	private Map<String, String> dateHoursList = new LinkedHashMap<String, String>();
	private Map<String, String> workingHoursFromList = new LinkedHashMap<String, String>();
	private Map<String, String> workingHoursToList = new LinkedHashMap<String, String>();
	private Map<String, String> hourInfoList = new LinkedHashMap<String, String>();
	private Map<String,String> commonMonthList = new LinkedHashMap<String,String>();
	
	private Map<String, String> workingMinuteList = new LinkedHashMap<String, String>();
	private Map<String, String> statusList = new LinkedHashMap<String, String>();
	private Map<String, String> majorList = new LinkedHashMap<String, String>();
	private Map<String, String> jobTitleList = new LinkedHashMap<String, String>();
	private Map<String, String> currencyTypeList = new LinkedHashMap<String, String>();
	private Map<String, String> attendenceStatusList = new LinkedHashMap<String, String>();
	private Map<String,String> departmentInfoList = new LinkedHashMap<String,String>();
	
	private List<String>  scoreArray5 = new ArrayList<String>();
	
	private List<MP2001> leaveInfo = new ArrayList<MP2001>();
	private List<MP2003> workingHourRecordList = new ArrayList<MP2003>();
	private List<MP2002> VacationInfoList = new ArrayList<MP2002>();
	private List<MP0002> departmentList = new ArrayList<MP0002>();
	private List<MP2001> leaveReportList = new ArrayList<MP2001>();
	private List<MP2009> leaveReportList2 = new ArrayList<MP2009>();
	private List<MP2004> majorInfoList = new ArrayList<MP2004>();
	private List<MP1009> attachedDocumentList = new ArrayList<MP1009>();
	private List<MP2007> mp2007InfoList = new ArrayList<MP2007>();
	private List<MP2008> mp2008InfoList = new ArrayList<MP2008>();
	
	private String approval; 
	private String positionLev; // 员工所属的角色
	private String annualDays; // 年假
	private String sickDays; // 病假
	private String familyDays; // 家庭假
	private String maternityDays; // 产假
	private String study; // 学习假
	private String days;
	private String Hours;
	private String Minutes;
	
	// 页面错误提示信息
	private String currencyType;
	private String mp2005JobID;
	private String mp2005Num = "";
	private String mp2005EmpNum;
	private String isCommunicationCost;
	private String mp2005Status;
	private String mp2005ID;
	private String mp2006Seq;
	private String stayAddress;
	private String visitAddress;
	private String purpose;
	private String type;
	private String MP2001_NUM="";
	private String employeeNum="";
	private String employeeName="";
	private String loginName="";
	private String errorMessage;
	private String pageType;
	private int pageNum;
	private int pageCount;
	
	private String leaveType;
	private String fromDate;
	private String toDate;
	private String approvalType;
	private String roleGroup;
	private String departmentID;
	private String departmentName;
	private String workingHours1;
	private String workingHours2;
	private String workingMinute1;
	private String workingMinute2;
	private String holidayInfo;
	private String actingType;
	private String personType;
	private String actingPersionNum;
	private String confirmNum;
	private String confirmDate;
	
	private String fileName = "";
	private String mp2002EmpNum;
	private String mp2002EmpName;
	private String depID;
	private String depName;
	
	private String majorName;
	private String majorDays;
	private String mp2004Seq;
	private String mp2004MajorDays;
	private String jobTitle;
	private String errMsg;
	private String downLoadFileName;
	private String attendenceStatus;
	
	private String mp2007Seq;
	private String commonSeq;
	private String warnType;
	private String param1;
	private String param2;
	private String param3;
	private String param4;
	private String param5;
	private String param6;
	private String param7;
	private String param8;
	private HttpServletRequest request ;
	//------------------权限控制用变量---------------
	private String optSave="0";
	private String optCancel="0";
	private String optApproval = "0";
	
	private String optAdd = "0";
	private String optSearch = "0";
	private String optDel = "0";
	private String optDel2 = "0";//用来限制假单批准后的删除
	private String optEdit = "0";
	private String optView = "0";
	
	private String optAll = "0";
	private String optDepartment = "0";
	private String optPersonal = "0";
	
	private String optPdf = "0";
	
	private String func0030001;
	private String func0030002;
	private String func0030003;
	
	
	//for shitwork test
	private IMP2010Service serviceMP2010;
	private List<ShiftworkExcelRecordDto> excelRecordsList;
	private List<String> branchSiteList; //= new ArrayList<String>();
	private String branchSiteId;
	private File shiftworkExcel;
	private String shiftworkExcelName;
	private String shiftworkExcelContentType;
	private String shiftWorkRadio;
	private String dayTypeChoose;
	private String shiftWorkType;
	private int currentPageNum;
	private String PER_PAGE_NUM = "13";
	private PageBean pageBean; // total
	
	//private String ;
	private List<MP2010> shiftworkScheduleList;
//	private IMP2010Service serviceMP2010;

	private String todayDate; 
	
    /* 
	* @getDownloadFile 此方法对应的是struts.xml文件中的： <param 
	* name="inputName">downloadFile</param> 返回下载文件的流，可以参看struts2的源码 
	*/  
	public InputStream getDownloadFile(){
        //return ServletActionContext.getServletContext().getResourceAsStream("/"+UploadConfigurationRead.getInstance().getConfigItem("uploadFilePath").trim()+"/" + fileName);
		return ServletActionContext.getServletContext().getResourceAsStream("/uploadfile/" + fileName);
	}
	
	public String documentDownload(){
		return SUCCESS;
	}
	
 	public String workingInfoPdf(){
		Calendar calendar = Calendar.getInstance();			
		fileName = String.valueOf(calendar.get(Calendar.YEAR)) + String.format("%02d",calendar.get(Calendar.MONTH) + 1) + String.format("%02d",calendar.get(Calendar.DAY_OF_MONTH)) + String.format("%02d",calendar.get(Calendar.HOUR_OF_DAY)) + String.format("%02d",calendar.get(Calendar.MINUTE)) + String.format("%02d",calendar.get(Calendar.SECOND)) + ".pdf";
		String _path = ServletActionContext.getServletContext().getRealPath("/") + "uploadfile\\" + fileName;
		
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
        try {
        	//----------------------------Operation History------------------
        	LogUtil logUtil = new LogUtil();
        	logUtil.setServiceMP0011(serviceMP0011);
        	logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Create Attendance Records Document");
        	//----------------------------Operation History------------------
			
        	if(departmentID.equals("undefined") || departmentID == null){
        		departmentID = employeeData.getMP1001_DEPARTMENT_ID();
        	}
        	if(employeeNum.equals("undefined") || employeeNum == null){
        		employeeNum = employeeData.getMP1001_EMPLOYEE_NUM();
        	}
        	List<MP2003> pdfList = serviceMp2003.getPdfData(employeeNum, fromDate, toDate, departmentID, attendenceStatus);
        	//dataConvert2003(pdfList,null);
        	
        	Document document = new Document(PageSize.A4, 5, 5, 20, 20);
        	document.addTitle("Mpisi Employee Attendance Record");
        	document.addAuthor("Mpisi HRMS");
        	document.addSubject("Telephone Liscvxt");
        	document.addKeywords("Mpisi, Telephone");
        	document.addCreator("HRMS");
        	
            // creation of the different writers
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(_path));
            writer.setBoxSize("art", new Rectangle(30, 30, 750, 750));

            // Header&Footer
            HeaderFooter event = new HeaderFooter();
            writer.setPageEvent(event);

            document.open();
            
            //------------------------Employee Tel Info------------------------
            PdfPTable pdfTable = new PdfPTable(6);
            pdfTable.setSpacingBefore(5);
            pdfTable.getDefaultCell().setPadding(1);
            pdfTable.setWidthPercentage(90);

            PdfPCell cell0 = new PdfPCell(new Phrase("Mpisi Employee Attendance Record"));
            cell0.setPadding(1);
            cell0.setColspan(6);
            cell0.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell0.setBorderWidthLeft(0);
            cell0.setBorderWidthRight(0);
            cell0.setBorderWidthTop(0);
            pdfTable.addCell(cell0);
            pdfTable.setHeaderRows(1);

            cell0 = new PdfPCell(new Phrase("No"));
            cell0.setNoWrap(true);
            pdfTable.addCell(cell0);
            cell0 = new PdfPCell(new Phrase("Name"));
            cell0.setNoWrap(true);
            pdfTable.addCell(cell0);
            cell0 = new PdfPCell(new Phrase("Date"));
            cell0.setNoWrap(true);
            pdfTable.addCell(cell0);
            cell0 = new PdfPCell(new Phrase("In"));
            cell0.setNoWrap(true);
            //pdfTable.addCell(cell0);
            //cell0 = new PdfPCell(new Phrase("Location"));
            //cell0.setNoWrap(true);
            pdfTable.addCell(cell0);
            cell0 = new PdfPCell(new Phrase("Out"));
            cell0.setNoWrap(true);
            pdfTable.addCell(cell0);
            //cell0 = new PdfPCell(new Phrase("Location"));
            //cell0.setNoWrap(true);
            //pdfTable.addCell(cell0);
            cell0 = new PdfPCell(new Phrase("Status"));
            cell0.setNoWrap(true);
            pdfTable.addCell(cell0);

            pdfTable.setHeaderRows(2);
            
            for(int i=0,j=pdfList.size(); i<j; i++){
            	MP2003 mp23 = pdfList.get(i);
            	
            	pdfTable.addCell(mp23.getMP2003_EMPLOYEE_NUM());
            	pdfTable.addCell(mp23.getMP2003_EMPLOYEE_NAME());
            	pdfTable.addCell(mp23.getMP2003_DATETIME());
            	pdfTable.addCell(mp23.getMP2003_START_TIME());
            	//pdfTable.addCell(mp23.getMP2003_START_TIME_DOOR());
            	pdfTable.addCell(mp23.getMP2003_FINISH_TIME());
            	//pdfTable.addCell(mp23.getMP2003_FINISH_TIME_DOOR());
            	pdfTable.addCell(mp23.getMP2003_COMMENT());
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
            System.err.println(ex.getMessage());
            return "error";
        }
	}

	public String  workingInfoExcel(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Create Employee Attendance Information Excel Document");
			//----------------------------Operation History------------------
			
			// 新建EXCEL工作表
			Workbook wb = ExcelUtil.CreateHSSFWorkBook();
			// 新建一个SHEET页面
			Sheet sheet = ExcelUtil.CreateSheet(wb, "Employee Attendance Information");
			// 设置SHEET页面属性
			ExcelUtil.SetSheetPropertyHSSF(sheet);
			// 获取预定的样式
			Map<String, CellStyle> styles = ExcelUtil.CreateStyles(wb);
			
			// Header标题
			String[] titles = {"No", "EMPLOYEE NUMBER", "Date", "In","Out","Status", "Department"};
			// 生成标题行
			float rowHeight = 27f;
			ExcelUtil.CreateHeadRow(sheet, titles, rowHeight, styles);
	        // 冻结第一行
	        sheet.createFreezePane(0, 1);
	        
	        int[] cellsWidth = {5,30,20,20,20,15,30};
	        // 设置单元格的宽度
	        ExcelUtil.SetCellsWidth(sheet, cellsWidth);
	        
	      //---------------------------主报表-----------------------------------------------------------------------------------------
	        // 取得报表数据
        	if(departmentID.equals("undefined") || departmentID == null){
        		departmentID = employeeData.getMP1001_DEPARTMENT_ID();
        	}
        	if(employeeNum.equals("undefined") || employeeNum == null){
        		employeeNum = employeeData.getMP1001_EMPLOYEE_NUM();
        	}
        	List<MP2003> excelList = serviceMp2003.getPdfData(employeeNum, fromDate, toDate, departmentID, attendenceStatus);
			
        	//add by Joe, add department name
        	//List<MP1001> excelMp1001 = servic
        	
	        int count;
	        //String[] datas = new String[6];
	        String[] datas = new String[7];
	        for(int i=0,j=excelList.size(); i<j; i++){
	        	MP2003 mp2003Obj = excelList.get(i);
	        	count = i + 1;
	        	datas = new String[7];
	        	datas[0] = String.valueOf(count);
	        	datas[1] = mp2003Obj.getMP2003_EMPLOYEE_NAME() + "(" + mp2003Obj.getMP2003_EMPLOYEE_NUM() + ")";
	        	datas[2] = mp2003Obj.getMP2003_DATETIME();
	        	datas[3] = mp2003Obj.getMP2003_START_TIME();
	        	datas[4] = mp2003Obj.getMP2003_FINISH_TIME();
	        	datas[5] = mp2003Obj.getMP2003_COMMENT();
	        	
	        	datas[6] = mp2003Obj.getMP2003_DEPARTMENT_NAME();//get department name
	        	
	        	ExcelUtil.SetCellsValue(count, sheet, styles, datas);
	        }
	        
			// 生成Excel文件
	        fileName = "AttendanceRecord.xls";
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
	
	// 初始化页面List信息
	private void listInit(){
		// 签证类型
		leaveTypeList.clear();
		leaveTypeList = Constant.getLeaveTypeList();
		
		// 批准类型
		approvalTypeList.clear();
		approvalTypeList = Constant.getApprovalTypeList();
		
		// 工作时间
		workingHoursFromList.clear();
		workingHoursFromList = Constant.getWorkingFromHour();
		
		workingHoursToList.clear();
		workingHoursToList = Constant.getWorkingToHour();
		
		// 分钟
		workingMinuteList.clear();
		workingMinuteList = Constant.getWorkingMinute();
		
		// 职位
		jobTitleList.clear();
		jobTitleList = Constant.getJobTitleList();
		
		//天/小时
		//dateHoursList.clear();
		//dateHoursList = Constant.getDateHoursList();
	}
	
		// 初始化页面List信息
	private void shiftworkListinit(){
		// 签证类型
		leaveTypeList.clear();
		//leaveTypeList = Constant.getLeaveTypeList();
		leaveTypeList = Constant.getShiftWorkLeaveTypeList();
		
		// 批准类型
		approvalTypeList.clear();
		approvalTypeList = Constant.getApprovalTypeList();
		
		// 工作时间
		workingHoursFromList.clear();
		workingHoursFromList = Constant.getWorkingFromHour();
		
		workingHoursToList.clear();
		workingHoursToList = Constant.getWorkingToHour();
		
		// 分钟
		workingMinuteList.clear();
		workingMinuteList = Constant.getWorkingMinute();
		
		// 职位
		jobTitleList.clear();
		jobTitleList = Constant.getJobTitleList();
		
		//天/小时
		//dateHoursList.clear();
		//dateHoursList = Constant.getDateHoursList();
	}

	
	public String refreshLeaveInfo(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Refresh Leave Application Information");
			//----------------------------Operation History------------------
			
		    //请假单编号
		    MP2001_NUM = "";
		    // 刷新页面数据
		    getLeaveListData();
		}catch(Exception ex){
			log.error(ex.getMessage());
			return "error";
		}
		return SUCCESS;
	}
	// 删除未经经理审批的请假单
	public String deleteLeaveInfo(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Delete Leave Application, Key:{" + MP2001_NUM + "}");
			//----------------------------Operation History------------------
			
		    MP2001 mp21 = service.findById(MP2001_NUM);  
		    
		    if(mp21 != null){
			    if(mp21.getMP2001_APPROVAL().equals("3")){
			    	mp21.setMP2001_STATUS("1");
			    	service.update(mp21);
			    	deleteMonthlyInfo( mp21.getMP2001_FROM_DATETIME(),  mp21.getMP2001_TO_DATETIME(),  mp21.getMP2001_EMPLOYEE_NUM(),  mp21.getMP2001_LEAVE_TYPE(),  mp21.getMP2001_DAYS());
			    }else {
			    	service.delete(mp21);
			    }
			    
		    	MP2002 mp22 = serviceMp2002.findById(mp21.getMP2001_EMPLOYEE_NUM());
		    	int days = Integer.parseInt(mp21.getMP2001_DAYS());
		    	
		    	if(mp21.getMP2001_LEAVE_TYPE().equals("1")){
		    		// Annual
		    		int _annual = Integer.parseInt(mp22.getMP2002_ANNUAL());
		    		_annual = _annual + days;
		    		mp22.setMP2002_ANNUAL(String.valueOf(_annual));
		    	}else if(mp21.getMP2001_LEAVE_TYPE().equals("2")){
		    		// Sick
		    		int _sick = Integer.parseInt(mp22.getMP2002_SICK());
		    		_sick = _sick + days;
		    		mp22.setMP2002_SICK(String.valueOf(_sick));
		    	}else if(mp21.getMP2001_LEAVE_TYPE().equals("3")){
		    		// Family
		    		int _family = Integer.parseInt(mp22.getMP2002_FAMILY_RESP());
		    		_family = _family + days;
		    		mp22.setMP2002_FAMILY_RESP(String.valueOf(_family));
		    	}else if(mp21.getMP2001_LEAVE_TYPE().equals("4")){
		    		// Study
		    		int _study = Integer.parseInt(mp22.getMP2002_STUDY());
		    		_study = _study + days;
		    		mp22.setMP2002_STUDY(String.valueOf(_study));
		    	}else if(mp21.getMP2001_LEAVE_TYPE().equals("5")){
		    		// Maternity
		    		int _maternity = Integer.parseInt(mp22.getMP2002_MATERNITY());
		    		_maternity = _maternity + days;
		    		mp22.setMP2002_MATERNITY(String.valueOf(_maternity));
		    	}
		    	serviceMp2002.update(mp22);
		    	
		    	if(mp21.getMP2001_LEAVE_TYPE().equals("4")){
		    		MP2004 mp24 = serviceMp2004.findById(mp21.getMP2001_MAJOR_SEQ());
		    		int _majorDays = Integer.parseInt(mp24.getMP2004_TIME()) + days;
		    		
		    		mp24.setMP2004_TIME(String.valueOf(_majorDays));
		    		
		    		serviceMp2004.update(mp24);
		    	}
		    }
		    //请假单编号
		    MP2001_NUM = "";
		    // 刷新页面数据
		    getLeaveListData();
		    
		    log.info("delete success！");
		}catch(Exception ex){
			log.error(ex.getMessage());
			return "error";
		}
		return SUCCESS;
	}
	// 删除数据
	private void deleteMonthlyInfo(String _fromDate, String _toDate, String _empNum, String leaveType, String totalHours) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calFrom = Calendar.getInstance();
		Calendar calTo = Calendar.getInstance();
		
		calFrom.setTime(sdf.parse(_fromDate));
		calTo.setTime(sdf.parse(_toDate));
		
		if(calFrom.get(Calendar.MONTH) == calTo.get(Calendar.MONTH)){
			updateMonthInfo3(leaveType,Double.parseDouble(totalHours),_empNum,String.valueOf(calFrom.get(Calendar.YEAR)),String.valueOf(calFrom.get(Calendar.MONTH) + 1));
		}else{
			int _tH = UtilCommon.calDays(sdf2.parse(_fromDate), sdf2.parse(getToDate(_fromDate)));
			double _h = getWorkHours(calFrom.get(Calendar.HOUR_OF_DAY), calFrom.get(Calendar.MINUTE));
			double _h2 = 0.0;
			_h = _tH - _h;
			_h2 = Integer.parseInt(totalHours) - _h;
			
			updateMonthInfo3(leaveType,_h,_empNum,String.valueOf(calFrom.get(Calendar.YEAR)),String.valueOf(calFrom.get(Calendar.MONTH) + 1));
			updateMonthInfo3(leaveType,_h2,_empNum,String.valueOf(calTo.get(Calendar.YEAR)),String.valueOf(calTo.get(Calendar.MONTH) + 1));
		}		
	}
	private void addMonthlyInfo(String _fromDate, String _toDate, String _empNum, String leaveType, String totalHours) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calFrom = Calendar.getInstance();
		Calendar calTo = Calendar.getInstance();
		
		calFrom.setTime(sdf.parse(_fromDate));
		calTo.setTime(sdf.parse(_toDate));

		MP2009 mp29Obj = checkMonthInfo(_empNum, String.valueOf(calFrom.get(Calendar.YEAR)),String.valueOf(calFrom.get(Calendar.MONTH) + 1));
		// 同一月份
		if(calFrom.get(Calendar.MONTH) == calTo.get(Calendar.MONTH)){
			if(mp29Obj != null){
				updateMonthInfo(leaveType,Double.parseDouble(totalHours),_empNum,String.valueOf(calFrom.get(Calendar.YEAR)),String.valueOf(calFrom.get(Calendar.MONTH) + 1));
			}else{
				updateMonthInfo2(leaveType,Double.parseDouble(totalHours),_empNum,String.valueOf(calFrom.get(Calendar.YEAR)),String.valueOf(calFrom.get(Calendar.MONTH) + 1));
			}
			
		}else{
			int _tH = UtilCommon.calDays(sdf2.parse(_fromDate), sdf2.parse(getToDate(_fromDate)));
			double _h = getWorkHours(calFrom.get(Calendar.HOUR_OF_DAY), calFrom.get(Calendar.MINUTE));
			double _h2 = 0.0;
			_h = _tH - _h;
			_h2 = Integer.parseInt(totalHours) - _h;
			
			if(mp29Obj != null){
				updateMonthInfo(leaveType,_h,_empNum,String.valueOf(calFrom.get(Calendar.YEAR)),String.valueOf(calFrom.get(Calendar.MONTH) + 1));
			}else{
				updateMonthInfo2(leaveType,_h,_empNum,String.valueOf(calFrom.get(Calendar.YEAR)),String.valueOf(calFrom.get(Calendar.MONTH) + 1));
			}
			MP2009 mp29 = checkMonthInfo(_empNum, String.valueOf(calTo.get(Calendar.YEAR)),String.valueOf(calTo.get(Calendar.MONTH) + 1));
			if(mp29 != null){
				updateMonthInfo(leaveType,_h2,_empNum,String.valueOf(calTo.get(Calendar.YEAR)),String.valueOf(calTo.get(Calendar.MONTH) + 1));
			}else{
				updateMonthInfo2(leaveType,_h2,_empNum,String.valueOf(calTo.get(Calendar.YEAR)),String.valueOf(calTo.get(Calendar.MONTH) + 1));
			}
		}
	}
	// 计算时间按
	private double getWorkHours(int _sH, int _sM){
		double minute = 60;
		double _h = 0;
		
		if(_sH < 13){
			_h = (_sH - 8) + _sM/minute;
		}else if(_sH == 13){
			_h = 5;
		}else if(_sH > 13){
			_h = (_sH - 8) + _sM/minute - 0.5;
		}
		
		return _h;
	}
	// 计算月末
	private String getToDate(String _fromDate) throws ParseException{
		String _lastDay = "30";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calFrom = Calendar.getInstance();
		calFrom.setTime(sdf.parse(_fromDate));
		
		_lastDay = getLastDay(calFrom.get(Calendar.MONTH));
		calFrom.set(calFrom.get(Calendar.YEAR), calFrom.get(Calendar.MONTH), Integer.parseInt(_lastDay));
		
		return sdf.format(calFrom.getTime());
	}
	// 判断月末
	private String getLastDay(int _month){
		String _lastDay = "30";
		
		 switch   (_month)   {
		   case   0:
			   _lastDay = "31";
		       break;
		   case   1:
			   _lastDay = "30";
		       break;
		   case   2:
			   _lastDay = "31";
		       break;
		   case   3:
			   _lastDay = "30";
		       break;
		   case   4:
			   _lastDay = "31";
		       break;
		   case   5:
			   _lastDay = "30";
		       break;
		   case   6:
			   _lastDay = "31";
		       break;
		   case   7:
			   _lastDay = "31";
		       break;
		   case   8:
			   _lastDay = "30";
		       break;
		   case   9:
			   _lastDay = "31";
		       break;
		   case   10:
			   _lastDay = "30";
		       break;
		   case   11:
			   _lastDay = "31";
		       break;
		}
		 
		 return _lastDay;
	}
	// 数据校验
	private MP2009 checkMonthInfo(String _empNum, String _year, String _month){
		Map<String, String> propertyMap = new HashMap<String, String>();
		propertyMap.put("EMP_NUM", _empNum);
		propertyMap.put("YEAR", _year);
		propertyMap.put("MONTH", _month);
		
		MP2009 mp29 = null;
		List<MP2009> mp29List = serviceMP2009.findByPropertyByPage(propertyMap, -1, -1);
		if(mp29List != null && mp29List.size() > 0){
			mp29 = mp29List.get(0);
		}
		
		return mp29;
	}
	// 更新数据
	private void updateMonthInfo(String leaveType, double totalHour,String _empNum, String _year, String _month){
		StringBuffer updateSql = new StringBuffer();
		updateSql.append("update MP2009 set ");
		
		 switch   (Integer.parseInt(leaveType))   {      
		   case   1:
			   updateSql.append(" MP2009_ANNUAL = MP2009_ANNUAL + " + totalHour);
		           break;
		   case   2:
			   updateSql.append(" MP2009_SICK = MP2009_SICK + " + totalHour);
		           break;
		   case   3:
			   updateSql.append(" MP2009_FAMILY_RESP = MP2009_FAMILY_RESP + " + totalHour);
		           break;
		   case   4:
			   updateSql.append(" MP2009_STUDY = MP2009_STUDY + " + totalHour);
		           break;
		   case   5:
			   updateSql.append(" MP2009_MATERNITY = MP2009_MATERNITY + " + totalHour);
		           break;
		   case   6:
			   updateSql.append(" MP2009_UNPAID = MP2009_UNPAID + " + totalHour);
		           break;
		   case   7:
			   updateSql.append(" MP2009_OFFICIAL_BUSINESS = MP2009_OFFICIAL_BUSINESS + " + totalHour);
		           break;
		}
		 updateSql.append(" where 1=1 ");
		 updateSql.append(" and MP2009_EMPLOYEE_NUM = '" + _empNum + "' ");
		 updateSql.append(" and MP2009_YEAR = '" + _year + "' ");
		 updateSql.append(" and MP2009_MONTH = '" + _month + "' ");
		 
		 serviceMP2009.executeStatement(updateSql.toString());
	}
	// 更新数据
	private void updateMonthInfo2(String leaveType, double totalHour,String _empNum, String _year, String _month){
		MP2009 mp29 = new MP2009();
		mp29.setMP2009_NUM(Constant.generateSeq());
		mp29.setMP2009_EMPLOYEE_NUM(_empNum);
		mp29.setMP2009_YEAR(_year);
		mp29.setMP2009_MONTH(_month);
		mp29.setMP2009_ANNUAL(new BigDecimal("0"));
		mp29.setMP2009_SICK(new BigDecimal("0"));
		mp29.setMP2009_FAMILY_RESP(new BigDecimal("0"));
		mp29.setMP2009_STUDY(new BigDecimal("0"));
		mp29.setMP2009_MATERNITY(new BigDecimal("0"));
		mp29.setMP2009_UNPAID(new BigDecimal("0"));
		mp29.setMP2009_OFFICIAL_BUSINESS(new BigDecimal("0"));
		mp29.setMP2009_LEAVE_EARLY(new BigDecimal("0"));
		mp29.setMP2009_LATE(new BigDecimal("0"));
		mp29.setMP2009_ABSENTEEISM(new BigDecimal("0"));
		
		BigDecimal _h = new BigDecimal(String.valueOf(totalHour));
		 switch   (Integer.parseInt(leaveType))   {      
		   case   1:
			   mp29.setMP2009_ANNUAL(_h);
		       break;
		   case   2:
			   mp29.setMP2009_SICK(_h);
		       break;
		   case   3:
			   mp29.setMP2009_FAMILY_RESP(_h);
		       break;
		   case   4:
		       mp29.setMP2009_STUDY(_h);
               break;
		   case   5:
			   mp29.setMP2009_MATERNITY(_h);
			   break;
		   case   6:
			   mp29.setMP2009_UNPAID(_h);
			   break;
		   case   7:
			   mp29.setMP2009_OFFICIAL_BUSINESS(_h);
			   break;
		}
		 
		 serviceMP2009.save(mp29);
	}
	// 更新数据
	private void updateMonthInfo3(String leaveType, double totalHour,String _empNum, String _year, String _month){
		StringBuffer updateSql = new StringBuffer();
		updateSql.append("update MP2009 set ");
		
		 switch   (Integer.parseInt(leaveType))   {      
		   case   1:
			   updateSql.append(" MP2009_ANNUAL = MP2009_ANNUAL - " + totalHour);
		           break;
		   case   2:
			   updateSql.append(" MP2009_SICK = MP2009_SICK - " + totalHour);
		           break;
		   case   3:
			   updateSql.append(" MP2009_FAMILY_RESP = MP2009_FAMILY_RESP - " + totalHour);
		           break;
		   case   4:
			   updateSql.append(" MP2009_STUDY = MP2009_STUDY - " + totalHour);
		           break;
		   case   5:
			   updateSql.append(" MP2009_MATERNITY = MP2009_MATERNITY - " + totalHour);
		           break;
		   case   6:
			   updateSql.append(" MP2009_UNPAID = MP2009_UNPAID - " + totalHour);
		           break;
		   case   7:
			   updateSql.append(" MP2009_OFFICIAL_BUSINESS = MP2009_OFFICIAL_BUSINESS - " + totalHour);
		           break;
		}
		 updateSql.append(" where 1=1 ");
		 updateSql.append(" and MP2009_EMPLOYEE_NUM = '" + _empNum + "' ");
		 updateSql.append(" and MP2009_YEAR = '" + _year + "' ");
		 updateSql.append(" and MP2009_MONTH = '" + _month + "' ");
		 
		 serviceMP2009.executeStatement(updateSql.toString());
	}
	// 审批员工请假单(批准)
	public String approvalLeaveInfo(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		listInit();
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Approve Leave Application, Key:{" + MP2001_NUM + "}");
			//----------------------------Operation History------------------
			
			//String _num = MP2001_NUM.substring(0,MP2001_NUM.indexOf(","));
			MP2001 mp21 = service.findById(MP2001_NUM);
			if(mp21.getMP2001_APPROVAL().equals("1")){
				mp21.setMP2001_COMMENT(mp2001.getMP2001_COMMENT());
				mp21.setMP2001_APPROVAL("3");
				
				addMonthlyInfo(mp21.getMP2001_FROM_DATETIME(), mp21.getMP2001_TO_DATETIME(), mp21.getMP2001_EMPLOYEE_NUM(), mp21.getMP2001_LEAVE_TYPE(), mp21.getMP2001_DAYS());
//				String _type = mp21.getMP2001_LEAVE_TYPE();
//				int _hours = Integer.parseInt(mp21.getMP2001_DAYS());
				
// 2012-02-24 申请假期的同时会减去请假天数，批准后，不再减去请假天数；如果不批准，则会返还请假天数。
//				MP2002 mp22 = serviceMp2002.findById(mp21.getMP2001_EMPLOYEE_NUM());
//				int _annualHours = Integer.parseInt(mp22.getMP2002_ANNUAL());
//				int _sickHours = Integer.parseInt(mp22.getMP2002_SICK());
//				int _familyHours = Integer.parseInt(mp22.getMP2002_FAMILY_RESP());
//				int _studyHours = Integer.parseInt(mp22.getMP2002_STUDY());
//				int _maternityHours = Integer.parseInt(mp22.getMP2002_MATERNITY());
//
//				String _leaveHours = "0";
//				if(_type.equals(Constant.ANNUAL)){//年假
//					_leaveHours = String.valueOf(_annualHours - _hours);
//				    mp22.setMP2002_ANNUAL(_leaveHours);
//				}else if(_type.equals(Constant.SICK)){//病假
//					_leaveHours = String.valueOf(_sickHours - _hours);
//					mp22.setMP2002_SICK(_leaveHours);
//				}else if(_type.equals(Constant.FAMILY)){//家庭假
//					_leaveHours = String.valueOf(_familyHours - _hours);
//					mp22.setMP2002_FAMILY_RESP(_leaveHours);
//				}else if(_type.equals(Constant.STUDY)){//学习假
//					_leaveHours = String.valueOf(_studyHours - _hours);
//					mp22.setMP2002_STUDY(_leaveHours);
//				}else if(_type.equals(Constant.MATERNITY)){//产假
//					_leaveHours = String.valueOf(_maternityHours - _hours);
//					mp22.setMP2002_MATERNITY(_leaveHours);
//				}
				
				Date now = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String addtime = sdf.format(now);
				
				mp21.setMP2001_APPROVAL_DATETIME(addtime);
				mp21.setMP2001_APPROVAL_USER(employeeData.getMP1001_EMPLOYEE_NUM());
				
				service.update(mp21);
				//serviceMp2002.update(mp22);
				
//				if(_type.equals(Constant.STUDY)){//学习假
//					MP2004 mp24 = serviceMp2004.findById(mp21.getMP2001_MAJOR_SEQ());
//					int _majorDays = Integer.parseInt(mp24.getMP2004_TIME());
//					_majorDays = _majorDays - _hours;
//					
//					mp24.setMP2004_TIME(String.valueOf(_majorDays));
//					
//					serviceMp2004.update(mp24);
//				}
				
				// Send Mail ------2011-10-31 Add by Tim------Start
				if(mp21.getMP2001_EMPLOYEE_NUM() != null && !mp21.getMP2001_EMPLOYEE_NUM().equals("")){
					MP1001 emp02 = serviceMP1001.findById(mp21.getMP2001_EMPLOYEE_NUM());
					Map<String,String> leaveTypeList = Constant.getLeaveTypeList();
					int _leaveDays = Integer.parseInt(mp21.getMP2001_DAYS());
					
					Mail mail = new Mail();
					mail.setSubject("Leave application has been approved");
					
					StringBuffer result = new StringBuffer(); 
					result.append("Dear " + emp02.getMP1001_PREFERED_NAME() + "(" + emp02.getMP1001_EMPLOYEE_NUM() + ")" + ",\r\n \r\n ");
					result.append("Please note that your leave application has been approved.\r\n \r\n ");
					result.append("Employee Number:" + emp02.getMP1001_EMPLOYEE_NUM() + " " + emp02.getMP1001_PREFERED_NAME() + "\r\n");
					result.append("Acting Person:" + mp21.getMP2001_ACTING_PERSON() + "\r\n");
					result.append("Type of Leave:" + leaveTypeList.get(mp21.getMP2001_LEAVE_TYPE()) + "\r\n");
					result.append("From Date:" + mp21.getMP2001_FROM_DATETIME().substring(0,16) + "\r\n");
					result.append("To Date:" + mp21.getMP2001_TO_DATETIME().substring(0,16) + "\r\n");
					result.append("Days:" + _leaveDays/8 + "D " + _leaveDays%8 + "H" + "\r\n");
					result.append("Comment:" + mp21.getMP2001_COMMENT() + "\r\n");
					
					result.append("Thank you very much! \r\n \r\n\r\n ");
					result.append("Your Faithfully, \r\n ");
					result.append("HRMS Administrator");
					
					String toList = emp02.getMP1001_COMPANY_EMAIL();
					mail.setTo(toList);
					mail.send();
				}
				// Send Mail ------2011-10-31 Add by Tim------End
			}
			session.put("LEAVE_NUM",mp21.getMP2001_NUM());
			
			StringBuffer sb = new StringBuffer();
			sb.append("<script type='text/javascript'>");
			sb.append("window.dialogArguments.document.getElementById('leaveRecord').click();");
			sb.append("window.close();");
			sb.append("</script>");
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
	        out.flush();
	        out.close();

			return "approve";
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	// 审批员工请假单(不批准)
	public String notApprovalLeaveInfo(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		listInit();
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Decline Leave Application, Key:{" + MP2001_NUM + "}");
			//----------------------------Operation History------------------
			
			MP2001 mp21 = service.findById(MP2001_NUM);
			mp21.setMP2001_COMMENT(mp2001.getMP2001_COMMENT());
			mp21.setMP2001_APPROVAL("2");
			
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addtime = sdf.format(now);
			
			mp21.setMP2001_EDIT_DATETIME(addtime);
			mp21.setMP2001_EDIT_USER(employeeData.getMP1001_EMPLOYEE_NUM());
			
			service.update(mp21);
			
			// 申请假期的同时会减去请假天数，批准后，不再减去请假天数；如果不批准，则会返还请假天数。--------------------2012-02-24 Start
			String _type = mp21.getMP2001_LEAVE_TYPE();
			int _hours = Integer.parseInt(mp21.getMP2001_DAYS());
			
			MP2002 mp22 = serviceMp2002.findById(mp21.getMP2001_EMPLOYEE_NUM());
			int _annualHours = Integer.parseInt(mp22.getMP2002_ANNUAL());
			int _sickHours = Integer.parseInt(mp22.getMP2002_SICK());
			int _familyHours = Integer.parseInt(mp22.getMP2002_FAMILY_RESP());
			int _studyHours = Integer.parseInt(mp22.getMP2002_STUDY());
			int _maternityHours = Integer.parseInt(mp22.getMP2002_MATERNITY());

			String _leaveHours = "0";
			if(_type.equals(Constant.ANNUAL)){//年假
				_leaveHours = String.valueOf(_annualHours + _hours);
			    mp22.setMP2002_ANNUAL(_leaveHours);
			}else if(_type.equals(Constant.SICK)){//病假
				_leaveHours = String.valueOf(_sickHours + _hours);
				mp22.setMP2002_SICK(_leaveHours);
			}else if(_type.equals(Constant.FAMILY)){//家庭假
				_leaveHours = String.valueOf(_familyHours + _hours);
				mp22.setMP2002_FAMILY_RESP(_leaveHours);
			}else if(_type.equals(Constant.STUDY)){//学习假
				_leaveHours = String.valueOf(_studyHours + _hours);
				mp22.setMP2002_STUDY(_leaveHours);
			}else if(_type.equals(Constant.MATERNITY)){//产假
				_leaveHours = String.valueOf(_maternityHours + _hours);
				mp22.setMP2002_MATERNITY(_leaveHours);
			}
			
			serviceMp2002.update(mp22);
			
			if(_type.equals(Constant.STUDY)){//学习假
				MP2004 mp24 = serviceMp2004.findById(mp21.getMP2001_MAJOR_SEQ());
				int _majorDays = Integer.parseInt(mp24.getMP2004_TIME());
				_majorDays = _majorDays + _hours;
				
				mp24.setMP2004_TIME(String.valueOf(_majorDays));
				
				serviceMp2004.update(mp24);
			}
			
			//---------------------------------------------------------------------------------2012-02-24 End

			// Send Mail ------2011-10-31 Add by Tim------Start
			if(mp21.getMP2001_EMPLOYEE_NUM() != null && !mp21.getMP2001_EMPLOYEE_NUM().equals("")){
				MP1001 emp02 = serviceMP1001.findById(mp21.getMP2001_EMPLOYEE_NUM());
				Map<String,String> leaveTypeList = Constant.getLeaveTypeList();
				int _leaveDays = Integer.parseInt(mp21.getMP2001_DAYS());
				
				Mail mail = new Mail();
				mail.setSubject("Leave application has not been approved");
				
				StringBuffer result = new StringBuffer(); 
				result.append("Dear " + emp02.getMP1001_PREFERED_NAME() + "(" + emp02.getMP1001_EMPLOYEE_NUM() + ")" + ",\r\n \r\n ");
				result.append("Please note that your leave application has been approved.\r\n \r\n ");
				result.append("Employee Number:" + emp02.getMP1001_EMPLOYEE_NUM() + " " + emp02.getMP1001_PREFERED_NAME() + "\r\n");
				result.append("Acting Person:" + mp21.getMP2001_ACTING_PERSON() + "\r\n");
				result.append("Type of Leave:" + leaveTypeList.get(mp21.getMP2001_LEAVE_TYPE()) + "\r\n");
				result.append("From Date:" + mp21.getMP2001_FROM_DATETIME().substring(0,16) + "\r\n");
				result.append("To Date:" + mp21.getMP2001_TO_DATETIME().substring(0,16) + "\r\n");
				result.append("Days:" + _leaveDays/8 + "D " + _leaveDays%8 + "H" + "\r\n");
				result.append("Comment:" + mp21.getMP2001_COMMENT() + "\r\n");
				
				result.append("Thank you very much! \r\n \r\n\r\n ");
				result.append("Your Faithfully, \r\n ");
				result.append("HRMS Administrator");
				
				String toList = emp02.getMP1001_COMPANY_EMAIL();
				mail.setTo(toList);
				mail.send();
			}
			// Send Mail ------2011-10-31 Add by Tim------End
			
			session.put("LEAVE_NUM",mp21.getMP2001_NUM());
			
			StringBuffer sb = new StringBuffer();
			sb.append("<script type='text/javascript'>");
			sb.append("window.dialogArguments.document.getElementById('leaveRecord').click();");
			sb.append("window.close();");
			sb.append("</script>");
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
	        out.flush();
	        out.close();
			
			return "approve";
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
		
	}
	
	// 请假单页面初始化
	public String applyLeaveInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Leave Application Page Init");
			//----------------------------Operation History------------------
			
			// 取得当年的Holiday
			String _year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
			List<HOLIDAY> _holidayList = serviceHoliday.findByProperty("HOLIDAY_YEAR", _year);
			
			holidayInfo = "";
			for(int i=0,j=_holidayList.size(); i<j; i++){
				HOLIDAY _holiday = _holidayList.get(i);
				
				if(holidayInfo.equals("")){
					holidayInfo += _holiday.getHOLIDAY_DATE();
				}else{
					holidayInfo += ";" + _holiday.getHOLIDAY_DATE();
				}
			}
			
			//add by Joe @10 Dec 2013, because people wanna apply leave across next year
			// 取得previous year的Holiday
			String _year_pre = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
			_year_pre = Integer.toString(Integer.parseInt(_year_pre) - 1);
			List<HOLIDAY> _holidayList_pre= serviceHoliday.findByProperty("HOLIDAY_YEAR", _year_pre);
			
			//holidayInfo = "";
			for(int i=0,j=_holidayList_pre.size(); i<j; i++){
				HOLIDAY _holiday = _holidayList_pre.get(i);
				
				if(holidayInfo.equals("")){
					holidayInfo += _holiday.getHOLIDAY_DATE();
				}else{
					holidayInfo += ";" + _holiday.getHOLIDAY_DATE();
				}
			}
			
			// 取得next year的Holiday
			String _year_next = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
			_year_next = Integer.toString(Integer.parseInt(_year_next)+1);
			List<HOLIDAY> _holidayList_next = serviceHoliday.findByProperty("HOLIDAY_YEAR", _year_next);
			
			//holidayInfo = "";
			for(int i=0,j=_holidayList_next.size(); i<j; i++){
				HOLIDAY _holiday = _holidayList_next.get(i);
				
				if(holidayInfo.equals("")){
					holidayInfo += _holiday.getHOLIDAY_DATE();
				}else{
					holidayInfo += ";" + _holiday.getHOLIDAY_DATE();
				}
			}
			
			listInit();
			positionLev = employeeData.getMP1001_POSITION(); 
			employeeNum = employeeData.getMP1001_EMPLOYEE_NUM();
			employeeName = employeeData.getMP1001_PREFERED_NAME();
			departmentID = employeeData.getMP1001_DEPARTMENT_ID();
			roleGroup = employeeData.getMP1001_GROUP();
			
			//majorList
			List<MP2004> mp2004List = serviceMp2004.findByProperty("MP2004_EMPLOYEE_NUM", employeeNum);
			majorList.put("0", "Please Select");
			for(int i=0,j=mp2004List.size(); i < j; i++){
				MP2004 mp24 = mp2004List.get(i);
				
				majorList.put(mp24.getMP2004_SEQ(), mp24.getMP2004_MAJOR_NAME());
			}
			
			// 初始请假时间默认为0
			days = "0";
			Hours = "0";
			Minutes = "0";
			
		    // 个人假期剩余天数
		    MP2002 mp2002 = serviceMp2002.findById(employeeData.getMP1001_EMPLOYEE_NUM());
		    if(mp2002 != null){
		    	annualDays = mp2002.getMP2002_ANNUAL();
		    	sickDays = mp2002.getMP2002_SICK();
		    	familyDays = mp2002.getMP2002_FAMILY_RESP();
		    	maternityDays = mp2002.getMP2002_MATERNITY();
		    	study = mp2002.getMP2002_STUDY();
		    }
			
		    if(type!=null && type.equals("approval")){
		    	type = "approval";
		    }

		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    if(type != null && (type.equals("edit") || type.equals("approval")) && !MP2001_NUM.equals("")){
		    	mp2001 = service.findById(MP2001_NUM);
		    	actingType = mp2001.getMP2001_APPLIY_TYPE();
		    	
				// 请假类型
				if(mp2001.getMP2001_LEAVE_TYPE()!=null && !mp2001.getMP2001_LEAVE_TYPE().equals("") && leaveTypeList.containsKey(mp2001.getMP2001_LEAVE_TYPE())){
					mp2001.setMP2001_LEAVE_TYPE_NAME(leaveTypeList.get(mp2001.getMP2001_LEAVE_TYPE()));
				}
				// 格式化时间
				if(mp2001.getMP2001_FROM_DATETIME() != null && !mp2001.getMP2001_FROM_DATETIME().equals("")){
					workingHours1 = mp2001.getMP2001_FROM_DATETIME().substring(11,13);
					workingMinute1 = mp2001.getMP2001_FROM_DATETIME().substring(14,16);
							
					Date startDate = sdf.parse(mp2001.getMP2001_FROM_DATETIME());
					mp2001.setMP2001_FROM_DATETIME(sdf.format(startDate));
				}
				if(mp2001.getMP2001_TO_DATETIME() != null && !mp2001.getMP2001_TO_DATETIME().equals("")){
					workingHours2 = mp2001.getMP2001_TO_DATETIME().substring(11,13);
					workingMinute2 = mp2001.getMP2001_TO_DATETIME().substring(14,16);
					
					Date finishDate = sdf.parse(mp2001.getMP2001_TO_DATETIME());
					mp2001.setMP2001_TO_DATETIME(sdf.format(finishDate));
				}
	//			if(mp2001.getMP2001_APPLIY_TYPE().equals("1")){
	//				mp2001.setMP2001_APPLIY_TYPE_NAME("self");
	//				actingType = "self";
	//			}else if(mp2001.getMP2001_APPLIY_TYPE().equals("2")){
	//				mp2001.setMP2001_APPLIY_TYPE_NAME("acting");
	//				actingType = "acting";
	//			}
				int _leaveHours = 0;
				int mp2001_days = Integer.parseInt(mp2001.getMP2001_DAYS());
				if(!mp2001.getMP2001_DAYS().equals("")){
					//_leaveHours = mp2001_days/60;
					_leaveHours = mp2001_days;
					//Minutes = String.valueOf(mp2001_days%60);
					Minutes = "00";
				}
				days = String.valueOf(_leaveHours / 8);
				Hours = String.valueOf(_leaveHours % 8);
				
				String _time = "";
				if(!days.equals("")){
					_time += days + "D ";
				}
				if(!Hours.equals("") && !Hours.equals("0")){
					_time += Hours + "H ";
				}
				if(mp2001_days%60 != 0){// 最小单位为小时，不可能出现分，这种情况不会出现
					//_time += mp2001_days%60 + "M"; 
				}
				mp2001.setMP2001_DAYS_D(_time);
				
			    MP1001 mp1 = serviceMP1001.findById(mp2001.getMP2001_EMPLOYEE_NUM());
			    mp2001.setMP2001_EMPLOYEE_NAME(mp1.getMP1001_PREFERED_NAME());
		    }
		    else{
		    	//mp2001.setMP2001_EMPLOYEE_NUM(employeeData.getMP1001_EMPLOYEE_NUM());
		    	//mp2001.setMP2001_EMPLOYEE_NAME(employeeData.getMP1001_PREFERED_NAME());
		    	mp2001.setMP2001_ACTING_APPLICATION_PERSON(employeeData.getMP1001_EMPLOYEE_NUM());
		    	mp2001.setMP2001_ACTING_APPLICATION_PERSON_NAME(employeeData.getMP1001_PREFERED_NAME());
		    	actingType = "self";
		    }
			
			// 取得部门信息
			//int departmentId = Integer.parseInt(employeeData.getMP1001_DEPARTMENT_ID());
			//MP0002 mp02 = serviceMp0002.findById(departmentId);
			
			// 取得请假单的当前编号
			//List<MP0006> mp06List = serviceMP0006.findByProperty("MP0006_CODE", "LEAVE_NUM");
		    //MP0006 mp6 = mp06List.get(0);
		    //int maxNum = mp6.getMP0006_VALUE() + 1;
		    //java.text.DecimalFormat format = new java.text.DecimalFormat("00");
		    
			//Date now = new Date();
			//SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
			//String addtime = sdf.format(now);
		    
		    // 请假单编号：日月年+部门编码+两位流水号
		    //String leaveNum = addtime + mp02.getMP0002_DEPARTMENT_NUM() + format.format(maxNum);
			
		    //mp2001.setMP2001_NUM(leaveNum);
			
		    //mp6.setMP0006_VALUE(maxNum);
		    //session.put("MP0006", mp6);
		    //session.put("LEAVE_NUM",leaveNum);
			//return SUCCESS;
		    if(type.equals("add") || type.equals("approval")){
		    	return "add";
		    }else if(type.equals("edit")){
		    	return "edit";
		    }else{
		    	return INPUT;
		    }
		}catch(Exception ex){
			log.info(ex.getMessage());
			
			return "error";
		}
	}

	// 判断请假日期是否跨年
	private boolean checkLeaveTime(){
		
/*		int _yearFrom = Integer.parseInt(mp2001.getMP2001_FROM_DATETIME().substring(0,4));
		int _monthFrom = Integer.parseInt(mp2001.getMP2001_FROM_DATETIME().substring(5,7));
		//int _dayFrom = Integer.parseInt(mp2001.getMP2001_FROM_DATETIME().substring(8,10));
		
		//int _yearTo = Integer.parseInt(mp2001.getMP2001_TO_DATETIME().substring(0,4));
		int _monthTo = Integer.parseInt(mp2001.getMP2001_TO_DATETIME().substring(5,7));
		//int _dayTo = Integer.parseInt(mp2001.getMP2001_TO_DATETIME().substring(8,10));		
		
		//Date now = new Date();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Calendar calendar = new GregorianCalendar();
		int _curYear = calendar.get(Calendar.YEAR);
		int _curMonth = calendar.get(Calendar.MONTH);
		//int _curDay = calendar.get(Calendar.DAY_OF_MONTH);
	    
		// 开始日期  《 3月 》结束日期  
		if( _monthFrom < 3 && _monthTo > 3){
			return true;
		}
		// 同一年内 并且当前 月份为1月 或2月但请假月份 >= 3月份 
		if((_curMonth == 0 || _curMonth == 1) && _yearFrom == _curYear && _monthFrom >= 3){
			return true;
		}
		// 当前为3~12月，请假日期为来年的三月以后
		if((_curMonth >= 2 && _curMonth <= 11) && (_yearFrom + 1 == _curYear) && _monthFrom >= 3){
			return true;
		}
		// 
		if((_curMonth >= 2 && _curMonth <= 11) && (_yearFrom == _curYear) && (_monthFrom == 1 || _monthFrom == 2 || _monthTo == 1 || _monthTo == 2)){
			return true;
		}*/
		
		String fromDate = mp2001.getMP2001_FROM_DATETIME().substring(0,10);
		//String toDate = mp2001.getMP2001_TO_DATETIME().substring(0,10);
		
		String fromYear = UtilCommon.getFinanceYear(fromDate);
		//String toYear = UtilCommon.getFinanceYear(toDate);
		String currentYear = UtilCommon.getFinanceYear();
		
		//if(!currentYear.equals(fromYear) || !currentYear.equals(toYear) || !fromYear.equals(toYear)){
		// 跨三月份请假：起始月份必须在3月份之前，结束月份不做限制
		if(!currentYear.equals(fromYear)){
			return true;
		}
		
		return false;
	}
	
	// 新增请假单信息
	public String addLeaveApply(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		listInit();
		
		try{
		    String _empNo = (employeeData.getMP1001_EMPLOYEE_NUM()).toUpperCase();//强制转换字母为大写字母
		    
			if(actingType.equals("self")){
				mp2001.setMP2001_EMPLOYEE_NUM(_empNo);
			}else{
				String _tempNum = mp2001.getMP2001_EMPLOYEE_NUM().toUpperCase();//强制转换字母为大写
				mp2001.setMP2001_EMPLOYEE_NUM(_tempNum);
			}
		    
			String _from = mp2001.getMP2001_FROM_DATETIME() + " " + String.format("%02d", Integer.parseInt(workingHours1)) + ":" + String.format("%02d", Integer.parseInt(workingMinute1))+":00";
			String _to = mp2001.getMP2001_TO_DATETIME() + " " + String.format("%02d", Integer.parseInt(workingHours2)) + ":" + String.format("%02d", Integer.parseInt(workingMinute2))+":00";
			String _id = "";
			
			if(actingType.equals("self")){
				_id = (employeeData.getMP1001_EMPLOYEE_NUM()).toUpperCase();
			}else{
				String _tempNum = mp2001.getMP2001_EMPLOYEE_NUM().toUpperCase();//强制转换字母为大写
				mp2001.setMP2001_EMPLOYEE_NUM(_tempNum);
			}
			log.info("ID:" + _id);
			// 判断请假时间是否有重复
			boolean checkValue = service.checkLeaveDay(_from, _to, mp2001.getMP2001_EMPLOYEE_NUM());
			// 判断是否跨年
			boolean checkValue2 = false;
			//年假、病假、家庭假不能跨年
			if(mp2001.getMP2001_LEAVE_TYPE().equals("1") || mp2001.getMP2001_LEAVE_TYPE().equals("2") || mp2001.getMP2001_LEAVE_TYPE().equals("3")){
				checkValue2 = checkLeaveTime();
			}
			
			if(true == checkValue){
				StringBuffer sb = new StringBuffer();

				sb.append("<script type='text/javascript'>");
				sb.append("alert('请假时间有重复，请重新填写起始时间。');");
				sb.append("window.location.href='applyLeaveInit.action?type=add&MP2001_NUM='");
				//sb.append("window.close();");
				sb.append("</script>");
			    
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html;charset=UTF-8");
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				out.println(sb.toString());
		        out.flush();
		        out.close();
		        
				//addActionMessage("<script>alert('保存失败，因为请假日期重复，请重新填写起始日期.');</script>");
				return null;
			}else if(true == checkValue2){
				StringBuffer sb = new StringBuffer();

				sb.append("<script type='text/javascript'>");
				sb.append("alert('不能跨年请假。');");
				sb.append("window.location.href='applyLeaveInit.action?type=add&MP2001_NUM='");
				//sb.append("window.close();");
				sb.append("</script>");
			    
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html;charset=UTF-8");
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				out.println(sb.toString());
		        out.flush();
		        out.close();

				return null;
			}
			else{				
				// 生成请假单编号
				// 取得部门信息
				int departmentId = Integer.parseInt(employeeData.getMP1001_DEPARTMENT_ID());
				MP0002 mp02 = serviceMp0002.findById(departmentId);
				
				//if(type.equals("add")){
					// 取得请假单的当前编号
					List<MP0006> mp06List = serviceMP0006.findByProperty("MP0006_CODE", "LEAVE_NUM");
				    MP0006 mp6 = mp06List.get(0);
				    int maxNum = Integer.parseInt(mp6.getMP0006_VALUE()) + 1;
				    java.text.DecimalFormat format = new java.text.DecimalFormat("00");
				    mp6.setMP0006_VALUE(String.valueOf(maxNum));
				    
					Date now = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String addtime = sdf.format(now);
					String addtime1 = sdf1.format(now);
				    
					//----------------------------------MP2001---------------------------------------
				    // 请假单编号：日月年+部门编码+两位流水号
				    String leaveNum = addtime + mp02.getMP0002_DEPARTMENT_NUM() + format.format(maxNum);
					
					mp2001.setMP2001_NUM(leaveNum);
					//mp2001.setMP2001_EMPLOYEE_NUM(employeeData.getMP1001_EMPLOYEE_NUM()); //设定员工编码
					mp2001.setMP2001_APPROVAL("1");
					mp2001.setMP2001_STATUS("0"); // 0:正常数据     1:废止数据
					mp2001.setMP2001_DAYS(getLeaveHours()); // 设定请假时间
					mp2001.setMP2001_ACTING_APPLICATION_PERSON(_empNo);
					mp2001.setMP2001_APPLIY_TYPE(actingType);
					mp2001.setMP2001_CREATE_DATETIME(addtime1);
					mp2001.setMP2001_CREATE_USER(employeeData.getMP1001_EMPLOYEE_NUM());
					mp2001.setMP2001_FROM_DATETIME(_from);
					mp2001.setMP2001_TO_DATETIME(_to);
					mp2001.setMP2001_SICK_CERTIFICATION("");
					//----------------------------------MP2001---------------------------------------
					
					// 申请假期的同时会减去请假天数，批准后，不再减去请假天数；如果不批准，则会返还请假天数。--------------------2012-02-24 Start
					String _type = mp2001.getMP2001_LEAVE_TYPE();
					int _hours = Integer.parseInt(mp2001.getMP2001_DAYS());
					
					MP2002 mp22 = serviceMp2002.findById(mp2001.getMP2001_EMPLOYEE_NUM());
					int _annualHours = Integer.parseInt(mp22.getMP2002_ANNUAL());
					int _sickHours = Integer.parseInt(mp22.getMP2002_SICK());
					int _familyHours = Integer.parseInt(mp22.getMP2002_FAMILY_RESP());
					int _studyHours = Integer.parseInt(mp22.getMP2002_STUDY());
					int _maternityHours = Integer.parseInt(mp22.getMP2002_MATERNITY());

					String _leaveHours = "0";
					if(_type.equals(Constant.ANNUAL)){//年假
						_leaveHours = String.valueOf(_annualHours - _hours);
					    mp22.setMP2002_ANNUAL(_leaveHours);
					}else if(_type.equals(Constant.SICK)){//病假
						_leaveHours = String.valueOf(_sickHours - _hours);
						mp22.setMP2002_SICK(_leaveHours);
					}else if(_type.equals(Constant.FAMILY)){//家庭假
						_leaveHours = String.valueOf(_familyHours - _hours);
						mp22.setMP2002_FAMILY_RESP(_leaveHours);
					}else if(_type.equals(Constant.STUDY)){//学习假
						_leaveHours = String.valueOf(_studyHours - _hours);
						mp22.setMP2002_STUDY(_leaveHours);
					}else if(_type.equals(Constant.MATERNITY)){//产假
						_leaveHours = String.valueOf(_maternityHours - _hours);
						mp22.setMP2002_MATERNITY(_leaveHours);
					}
					
					if(Integer.parseInt(_leaveHours) < 0){
						StringBuffer sb = new StringBuffer();

						sb.append("<script type='text/javascript'>");
						sb.append("alert('The number of vacation days is not enough。');");
						sb.append("window.location.href='applyLeaveInit.action?type=add&MP2001_NUM='");
						//sb.append("window.close();");
						sb.append("</script>");
					    
						HttpServletResponse response = ServletActionContext.getResponse();
						response.setContentType("text/html;charset=UTF-8");
						response.setCharacterEncoding("utf-8");
						PrintWriter out = response.getWriter();
						out.println(sb.toString());
				        out.flush();
				        out.close();

						return null;
					}
					
					service.save(mp2001);
					serviceMP0006.update(mp6);
					
					serviceMp2002.update(mp22);
					//----------------------------Operation History------------------
					LogUtil logUtil = new LogUtil();
					logUtil.setServiceMP0011(serviceMP0011);
					logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Leave Application, Key{" + mp2001.getMP2001_NUM() + "}");
					//----------------------------Operation History------------------
					
					if(_type.equals(Constant.STUDY)){//学习假
						MP2004 mp24 = serviceMp2004.findById(mp2001.getMP2001_MAJOR_SEQ());
						int _majorDays = Integer.parseInt(mp24.getMP2004_TIME());
						_majorDays = _majorDays - _hours;
						
						mp24.setMP2004_TIME(String.valueOf(_majorDays));
						
						serviceMp2004.update(mp24);
					}
					
					//---------------------------------------------------------------------------------2012-02-24 End
					
					// Send Mail ------2011-10-31 Add by Tim------Start
					if(mp2001.getMP2001_ACTING_PERSON()!=null && !mp2001.getMP2001_ACTING_PERSON().equals("")){
						MP1001 emp01 = serviceMP1001.findById(mp2001.getMP2001_ACTING_PERSON());
						MP1001 emp02 = serviceMP1001.findById(mp2001.getMP2001_EMPLOYEE_NUM());
						int _leaveDays = Integer.parseInt(mp2001.getMP2001_DAYS());
						
						Map<String, String> propertyMap = new LinkedHashMap<String, String>();
						propertyMap.put("DEPARTMENT", emp02.getMP1001_DEPARTMENT_ID());
						propertyMap.put("JOB_TITLE", "1");
						propertyMap.put("STATUS", "99");
						List<MP1001> emp11List = serviceMP1001.findByProperty(propertyMap);

						//String result = MessageFormat.format(Constant.MESSAGE_LEAVE_BODY,emp01.getMP1001_PREFERED_NAME(), emp02.getMP1001_PREFERED_NAME());

						Mail mail = new Mail();
						MP1001 mp11 = serviceMP1001.findById(mp2001.getMP2001_ACTING_PERSON());
						StringBuffer toList = new StringBuffer();
						toList.append(mp11.getMP1001_COMPANY_EMAIL());
						toList.append("," + emp02.getMP1001_COMPANY_EMAIL());
						
						//if(emp02.getMP1001_GROUP().equals("2") || emp02.getMP1001_GROUP().equals("3")){
						if(emp02.getMP1001_POSITION() != null && !emp02.getMP1001_POSITION().equals("") && emp02.getMP1001_POSITION().equals("1")){
							List<MP0006> directorMialList = serviceMP0006.findByProperty("MP0006_CODE", "DIRECTOR_LIST");
							if(directorMialList.size() >0){
								MP0006 mp06 = directorMialList.get(0);
								
								toList.append("," + mp06.getMP0006_VALUE());
							}
						}else{
							for(int i=0,j=emp11List.size(); i<j; i++){
								MP1001 obj1001 = emp11List.get(i);
								toList.append("," + obj1001.getMP1001_COMPANY_EMAIL());					
							}
						}
						
						mail.setSubject(Constant.MESSAGE_LEAVE_TITLE);
						
						StringBuffer result = new StringBuffer();
						//text/plain
						result.append("Dear " + emp01.getMP1001_PREFERED_NAME() + "(" + emp01.getMP1001_EMPLOYEE_NUM() + ")" + ",\r\n \r\n ");
						result.append("Please note that " + emp02.getMP1001_PREFERED_NAME() + " have apply for leave, you are the acting person.\r\n \r\n ");
						
						result.append("Employee Number:" + emp02.getMP1001_EMPLOYEE_NUM() + " " + emp02.getMP1001_PREFERED_NAME() + "\r\n ");
						result.append("Acting Person:" + mp2001.getMP2001_ACTING_PERSON() + " " + mp11.getMP1001_PREFERED_NAME() + "\r\n ");
						result.append("Type of Leave:" + leaveTypeList.get(mp2001.getMP2001_LEAVE_TYPE()) + "\r\n ");
						result.append("From Date:" + mp2001.getMP2001_FROM_DATETIME().substring(0,16) + "\r\n ");
						result.append("To Date:" + mp2001.getMP2001_TO_DATETIME().substring(0,16) + "\r\n ");
						result.append("Days:" + _leaveDays/8 + "D " + _leaveDays%8 + "H" + "\r\n ");
						result.append("Comment:" + mp2001.getMP2001_COMMENT() + "\r\n ");
						
						result.append("Thank you very much! \r\n \r\n\r\n ");
						result.append("Your Faithfully, \r\n ");
						result.append("HRMS Administrator");

						mail.setTo(toList.toString());
						mail.setContent(result.toString());
						mail.send();
					}
					// Send Mail ------2011-10-31 Add by Tim------End

					session.put("LEAVE_NUM", leaveNum);
	/*			}else if(type.equals("edit")){
					MP2001 editData = new MP2001();
					editData = service.findById(MP2001_NUM);
					
					editData.setMP2001_DAYS(getLeaveHours());// 设定请假时间
					editData.setMP2001_LEAVE_TYPE(mp2001.getMP2001_LEAVE_TYPE());// 设定请假类型
					editData.setMP2001_ACTING_PERSON(mp2001.getMP2001_ACTING_PERSON());// 设定代理人
					editData.setMP2001_FROM_DATETIME(mp2001.getMP2001_FROM_DATETIME());// 开始时间
					editData.setMP2001_TO_DATETIME(mp2001.getMP2001_TO_DATETIME());// 结束时间
					
					// 2011-10-28 Add by Tim
					editData.setMP2001_ACTING_PERSON(employeeData.getMP1001_EMPLOYEE_NUM());
					editData.setMP2001_APPLIY_TYPE(actingType);
					editData.setMP2001_COMMENT(mp2001.getMP2001_COMMENT());
					
					if(actingType == "self"){
						editData.setMP2001_EMPLOYEE_NUM(employeeData.getMP1001_EMPLOYEE_NUM());
					}else if(actingType == "acting"){
						editData.setMP2001_EMPLOYEE_NUM(mp2001.getMP2001_EMPLOYEE_NUM());
					}
					
					service.update(editData);
				}*/
				
				return SUCCESS;
			}
			
		}catch(Exception ex){
			System.out.println(ex.getLocalizedMessage());
			return INPUT;
		}
	}
	
	// 编辑请假单信息
	public String editLeaveApply(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			String _empNo = (employeeData.getMP1001_EMPLOYEE_NUM()).toUpperCase();//强制转换字母为大写字母
			// 生成请假单编号
			// 取得部门信息
			//int departmentId = Integer.parseInt(employeeData.getMP1001_DEPARTMENT_ID());
			//MP0002 mp02 = serviceMp0002.findById(departmentId);
			
	        MP2001 editData = new MP2001();
			editData = service.findById(MP2001_NUM);
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Edit Leave Application, Key{" + MP2001_NUM + "}");
			//----------------------------Operation History------------------
			
			int oldDays = Integer.parseInt(editData.getMP2001_DAYS());
			
			editData.setMP2001_DAYS(getLeaveHours());// 设定请假时间
			editData.setMP2001_LEAVE_TYPE(mp2001.getMP2001_LEAVE_TYPE());// 设定请假类型
			editData.setMP2001_ACTING_PERSON(mp2001.getMP2001_ACTING_PERSON());// 设定代理人	
			
			String _from = mp2001.getMP2001_FROM_DATETIME() + " " + String.format("%02d", Integer.parseInt(workingHours1)) + ":" + String.format("%02d", Integer.parseInt(workingMinute1))+":00";
			String _to = mp2001.getMP2001_TO_DATETIME() + " " + String.format("%02d", Integer.parseInt(workingHours2)) + ":" + String.format("%02d", Integer.parseInt(workingMinute2))+":00";
			editData.setMP2001_FROM_DATETIME(_from);  // 开始时间
			editData.setMP2001_TO_DATETIME(_to);  // 结束时间
			
			// 2011-10-28 Add by Tim
			editData.setMP2001_ACTING_PERSON(_empNo);
			editData.setMP2001_APPLIY_TYPE(actingType);
			editData.setMP2001_COMMENT(mp2001.getMP2001_COMMENT());
			
			if(actingType.equals("self")){
				editData.setMP2001_EMPLOYEE_NUM(_empNo);
			}else if(actingType.equals("acting")){
				String _tempNum = mp2001.getMP2001_EMPLOYEE_NUM().toUpperCase();//强制转换字母为大写
				editData.setMP2001_EMPLOYEE_NUM(_tempNum);
			}
			
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addtime = sdf.format(now);
			
			editData.setMP2001_EDIT_DATETIME(addtime);
			editData.setMP2001_EDIT_USER(employeeData.getMP1001_EMPLOYEE_NUM());
			
			service.update(editData);
			
			// 申请假期的同时会减去请假天数，批准后，不再减去请假天数；如果不批准，则会返还请假天数。--------------------2012-02-24 Start
			String _type = mp2001.getMP2001_LEAVE_TYPE();
			int _hours = Integer.parseInt(mp2001.getMP2001_DAYS());
			
			if(oldDays != _hours){
				_hours = _hours - oldDays;
				MP2002 mp22 = serviceMp2002.findById(mp2001.getMP2001_EMPLOYEE_NUM());
				int _annualHours = Integer.parseInt(mp22.getMP2002_ANNUAL());
				int _sickHours = Integer.parseInt(mp22.getMP2002_SICK());
				int _familyHours = Integer.parseInt(mp22.getMP2002_FAMILY_RESP());
				int _studyHours = Integer.parseInt(mp22.getMP2002_STUDY());
				int _maternityHours = Integer.parseInt(mp22.getMP2002_MATERNITY());

				String _leaveHours = "0";
				if(_type.equals(Constant.ANNUAL)){//年假
					_leaveHours = String.valueOf(_annualHours + _hours);
				    mp22.setMP2002_ANNUAL(_leaveHours);
				}else if(_type.equals(Constant.SICK)){//病假
					_leaveHours = String.valueOf(_sickHours + _hours);
					mp22.setMP2002_SICK(_leaveHours);
				}else if(_type.equals(Constant.FAMILY)){//家庭假
					_leaveHours = String.valueOf(_familyHours + _hours);
					mp22.setMP2002_FAMILY_RESP(_leaveHours);
				}else if(_type.equals(Constant.STUDY)){//学习假
					_leaveHours = String.valueOf(_studyHours + _hours);
					mp22.setMP2002_STUDY(_leaveHours);
				}else if(_type.equals(Constant.MATERNITY)){//产假
					_leaveHours = String.valueOf(_maternityHours + _hours);
					mp22.setMP2002_MATERNITY(_leaveHours);
				}
				
				serviceMp2002.update(mp22);
				
				if(_type.equals(Constant.STUDY)){//学习假
					MP2004 mp24 = serviceMp2004.findById(mp2001.getMP2001_MAJOR_SEQ());
					int _majorDays = Integer.parseInt(mp24.getMP2004_TIME());
					_majorDays = _majorDays + _hours;
					
					mp24.setMP2004_TIME(String.valueOf(_majorDays));
					
					serviceMp2004.update(mp24);
				}
			}
			
			//---------------------------------------------------------------------------------2012-02-24 End
			
			// Send Mail ------2011-10-31 Add by Tim------Start
//			if(mp2001.getMP2001_ACTING_PERSON()!=null && !mp2001.getMP2001_ACTING_PERSON().equals("")){
//				Mail mail = new Mail();
//				MP1001 mp11 = serviceMP1001.findById(mp2001.getMP2001_ACTING_PERSON());
//				String toList = mp11.getMP1001_COMPANY_EMAIL();
//				mail.setSubject(Constant.MESSAGE_TITLE);
//				mail.setContent(Constant.MESSAGE_BODY);
//				mail.setTo(toList);
//				mail.send();
//			}
			// Send Mail ------2011-10-31 Add by Tim------End
			session.put("LEAVE_NUM",MP2001_NUM);
			return SUCCESS;
		}catch(Exception ex){
			System.out.println(ex.getLocalizedMessage());
			return "error";
		}
	}
	
	// 请假单预览
	public String applyLeaveDis() {
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			listInit();
			String leaveNum = "";
			List<MP1001> mp11List = serviceMP1001.findAll();
			Map<String,String> mp11Map = new LinkedHashMap<String,String>();
			for(int i=0,j=mp11List.size(); i<j; i++){
				MP1001 mp11 = mp11List.get(i);
				String preferedName = mp11.getMP1001_PREFERED_NAME();
				mp11Map.put(mp11.getMP1001_EMPLOYEE_NUM(), preferedName);
			}
			
			// 取得登陆人信息
			//MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
			if(session.containsKey("LEAVE_NUM")){
				leaveNum = session.get("LEAVE_NUM").toString();
			}else if(type.equals("view") && !MP2001_NUM.equals("")){
				leaveNum = MP2001_NUM;
			}
			attachedDocumentList = serviceMP1009.findByProperty("MP1009_EMPLOYEE_NUM", leaveNum);
			
			mp2001 = service.findById(leaveNum);
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"View Leave Application, Key{" + leaveNum + "}");
			//----------------------------Operation History------------------
			
			mp2001.setMP2001_LEAVE_TYPE_NAME(leaveTypeList.get(mp2001.getMP2001_LEAVE_TYPE()));
			if(mp2001.getMP2001_APPROVAL().equals("1")){
				mp2001.setMP2001_APPROVAL_NAME("Pending");
			}else if(mp2001.getMP2001_APPROVAL().equals("2")){
				mp2001.setMP2001_APPROVAL_NAME("Declined");
			}else if(mp2001.getMP2001_APPROVAL().equals("3")){
				mp2001.setMP2001_APPROVAL_NAME("Approved");
			}
			
			int _leaveDays = Integer.parseInt(mp2001.getMP2001_DAYS());
			String showDays = _leaveDays/8 + "D " + _leaveDays%8 + "H"; 
			mp2001.setMP2001_DAYS(showDays);
			
			if(session.containsKey("LEAVE_NUM")){
				session.remove("LEAVE_NUM");
			}

			//申请人
			if(mp2001.getMP2001_EMPLOYEE_NUM() != null && !mp2001.getMP2001_EMPLOYEE_NUM().equals("")){
				if(mp11Map.containsKey(mp2001.getMP2001_EMPLOYEE_NUM())){
					mp2001.setMP2001_EMPLOYEE_NAME(mp11Map.get(mp2001.getMP2001_EMPLOYEE_NUM()));
				}
			}
			//代理人
			if(mp2001.getMP2001_ACTING_PERSON() != null && !mp2001.getMP2001_ACTING_PERSON().equals("")){
				if(mp11Map.containsKey(mp2001.getMP2001_ACTING_PERSON())){
					mp2001.setMP2001_ACTING_PERSON_NAME(mp11Map.get(mp2001.getMP2001_ACTING_PERSON()));
				}
			}
			//开始时间
			if(mp2001.getMP2001_FROM_DATETIME() != null && !mp2001.getMP2001_FROM_DATETIME().equals("")){
				mp2001.setMP2001_FROM_DATETIME(mp2001.getMP2001_FROM_DATETIME().substring(0,16));
			}
			//结束时间
			if(mp2001.getMP2001_TO_DATETIME() != null && !mp2001.getMP2001_TO_DATETIME().equals("")){
				mp2001.setMP2001_TO_DATETIME(mp2001.getMP2001_TO_DATETIME().substring(0,16));
			}
			// 创建人
			if(mp2001.getMP2001_CREATE_USER() != null && !mp2001.getMP2001_CREATE_USER().equals("")){
				if(mp11Map.containsKey(mp2001.getMP2001_CREATE_USER())){
					mp2001.setMP2001_CREATE_USER_NAME(mp11Map.get(mp2001.getMP2001_CREATE_USER()));
				}
			}
			// 创建时间
			if(mp2001.getMP2001_CREATE_DATETIME() != null && !mp2001.getMP2001_CREATE_DATETIME().equals("")){
				mp2001.setMP2001_CREATE_DATETIME(mp2001.getMP2001_CREATE_DATETIME().substring(0,16));
			}
			// 审批人
			if(mp2001.getMP2001_APPROVAL_USER() != null && !mp2001.getMP2001_APPROVAL_USER().equals("")){
				if(mp11Map.containsKey(mp2001.getMP2001_APPROVAL_USER())){
					mp2001.setMP2001_APPROVAL_USER_NAME(mp11Map.get(mp2001.getMP2001_APPROVAL_USER()));
				}
			}
			//审批时间
			if(mp2001.getMP2001_APPROVAL_DATETIME() != null && !mp2001.getMP2001_APPROVAL_DATETIME().equals("")){
				mp2001.setMP2001_APPROVAL_DATETIME(mp2001.getMP2001_APPROVAL_DATETIME().substring(0,16));
			}
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	// 计算请假时间
	private String getLeaveHours(){
		// 计算请假时间
		int tempDays = 0;
		if(!days.trim().equals("")){
			//tempDays = Integer.parseInt(days)*8*60;
			tempDays = Integer.parseInt(days)*8;
		}
		if(!Hours.equals("")){
			//tempDays += Integer.parseInt(Hours)*60;
			tempDays += Integer.parseInt(Hours);
		}
		//if(!Minutes.equals("")){
		//	tempDays += Integer.parseInt(Minutes);
		//}
		
		return String.valueOf(tempDays);
	}
	
	// 输入用户编码后自动带出用户名
	public String loadActingPerson(){
		HttpServletResponse response = ServletActionContext.getResponse();  
        response.setCharacterEncoding("utf-8"); // 务必，防止返回文件名是乱码
        PrintWriter out = null;
        
        System.out.print("-----debug into------");
        
        try {
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
			
			// 取得登陆人信息
			MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
        	MP1001 mp1 = serviceMP1001.findById(employeeNum);
        	out = response.getWriter();
        	
        	if(mp1 != null){       		
    			if(employeeData.getMP1001_DEPARTMENT_ID().equals(mp1.getMP1001_DEPARTMENT_ID())){
    				//out.print(mp1.getMP1001_PREFERED_NAME());
            		if(personType.equals("actingPerson")){// 代理人栏
            			if(actingType.equals("self")){ // 自己请假
                			if(!employeeData.getMP1001_EMPLOYEE_NUM().equals(employeeNum)){
                				out.print(mp1.getMP1001_PREFERED_NAME());
                			}else{
                				out.print("acting person can not be yourself");
                			}
            			}else if(actingType.equals("acting")){// 代理别人申请
            				if(null == actingPersionNum){actingPersionNum = "";}
            				if(null == employeeNum){employeeNum = "";}
            				if(!actingPersionNum.equals(employeeNum)){
            					out.print(mp1.getMP1001_PREFERED_NAME());
            				}else{
            					out.print("acting person and apply persion are the same.");
            				}
            			}
            		}else if(personType.equals("applyPerson")){ // 申请人栏
            			if(actingType.equals("self")){
            				out.print(mp1.getMP1001_PREFERED_NAME());
            			}else if(actingType.equals("acting")){
                			if(!employeeData.getMP1001_EMPLOYEE_NUM().equals(employeeNum)){
                				if(null == actingPersionNum){actingPersionNum = "";}
                				if(null == employeeNum){employeeNum = "";}
                				if(!actingPersionNum.equals(employeeNum)){
                					String _days = getVacationDays(employeeNum);
                					String _majorDays = getMajors(employeeNum);
                					out.print(mp1.getMP1001_PREFERED_NAME() + "|" + _days + "|" + _majorDays + "|" + mp1.getMP1001_DEPARTMENT_ID()  + "|" + mp1.getMP1001_POSITION());
                				}else{
                					out.print("acting person and apply persion are the same.");
                				}
                			}else{
                				out.print("acting person cant't be yourself");
                			}
            			}
            		}
    			}else{
    				out.print("must be in the same department");
    			}
        	}else{
        		out.print("invalid employee number");
        	}
        	
	        out.flush();
	        out.close();

        }catch (IOException e) {
            e.printStackTrace();
            log.info("upload failed.detail message：" + e.getMessage());
        }
		
		return null;
	}
	
	public String loadMajorDays(){
		HttpServletResponse response = ServletActionContext.getResponse();  
        response.setCharacterEncoding("utf-8"); // 务必，防止返回文件名是乱码
        PrintWriter out = null;
        
        try {
        	out = response.getWriter();
        	if(mp2004Seq != null && !mp2004Seq.equals("") && !mp2004Seq.equals("0")){
        		MP2004 mp24 = serviceMp2004.findById(mp2004Seq);
        		
        		if(mp24.getMP2004_TIME() != null && !mp24.getMP2004_TIME().equals("")){
        			out.print(mp24.getMP2004_TIME());
        		}else{
        			out.print("0");
        		}
        	}else{
        		out.print("0");
        	}
        }catch (IOException e) {
            e.printStackTrace();
            log.info("upload failed.detail message：" + e.getMessage());
        }
		
		return null;
	}
	
	private String getVacationDays(String empNum){
	    // 个人假期剩余天数
	    MP2002 mp2002 = serviceMp2002.findById(empNum);
	    String ret = "";
	    if(mp2002 != null){
	    	annualDays = mp2002.getMP2002_ANNUAL();
	    	sickDays = mp2002.getMP2002_SICK();
	    	familyDays = mp2002.getMP2002_FAMILY_RESP();
	    	maternityDays = mp2002.getMP2002_MATERNITY();
	    	study = mp2002.getMP2002_STUDY();
	    	
	    	ret = annualDays + "|" + sickDays + "|" + familyDays + "|" + maternityDays + "|" + study;
	    }
	    return ret;
	}
	
	private String getMajors(String empNum){
		List<MP2004> mp24List = serviceMp2004.findByProperty("MP2004_EMPLOYEE_NUM", empNum);
		StringBuffer sb = new StringBuffer();
		majorList.clear();
		majorList.put("0", "Please Select");
		for(int i=0,j=mp24List.size(); i < j; i++){
			MP2004 mp24 = mp24List.get(i);
			
			majorList.put(mp24.getMP2004_SEQ(), mp24.getMP2004_MAJOR_NAME());
			
			if(sb.toString().equals("")){
				sb.append(mp24.getMP2004_SEQ() + ":" + mp24.getMP2004_MAJOR_NAME());
			}else{
				sb.append(";" + mp24.getMP2004_SEQ() + ":" + mp24.getMP2004_MAJOR_NAME());
			}
		}
		
		return sb.toString();
	}
	
	// 校验编辑员工基本信息
	public void validateEditLeaveApply() throws Exception{
		validLeaveApply();
	}

	// 校验新增员工基本信息
	/**
	 * @throws Exception
	 */
	public void validateAddLeaveApply() throws Exception{
		//validLeaveApply();
	}
	
	private boolean validLeaveApply(){
		boolean ret = true;	
		try{
			// 验证请假天数
			if(days.equals("") && Hours.equals("")){
				addFieldError("Hours","No. of days is empty.");
				ret = false;
			}else{
				String check = "[0-9]{1,2}";
				Pattern p1 = Pattern.compile(check);
				Pattern p2 = Pattern.compile(check);
				
				Matcher matcher1 = p1.matcher(days);
				Matcher matcher2 = p2.matcher(Hours);
				
				if(!days.equals("") && !matcher1.matches()){
					addFieldError("days","days's format is incorrect");
					ret = false;
				}
				if(!Hours.equals("") && !matcher2.matches()){
					addFieldError("Hours","Hours's format is incorrect");
					ret = false;
				}
			}
			// 验证开始日
			if(mp2001.getMP2001_FROM_DATETIME().equals("")){
				addFieldError("mp2001.MP2001_FROM_DATETIME","From Date is empty.");
				ret = false;
			}
			// 验证结束日
			if(mp2001.getMP2001_TO_DATETIME().equals("")){
				addFieldError("mp2001.MP2001_TO_DATETIME","To Date is empty.");
				ret = false;
			}
			
			if(!mp2001.getMP2001_FROM_DATETIME().equals("") && !mp2001.getMP2001_TO_DATETIME().equals("")){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				
				Date from = df.parse(mp2001.getMP2001_FROM_DATETIME());
				Date to = df.parse(mp2001.getMP2001_TO_DATETIME());
				
				if(from.getTime() > to.getTime()){
					addFieldError("mp2001.MP2001_TO_DATETIME","开始日期大于结束日期.");
					ret = false;
				}else if(from.getTime() == to.getTime() && Integer.parseInt(workingHours1) > Integer.parseInt(workingHours2)){
					addFieldError("mp2001.MP2001_TO_DATETIME","开始时间大于结束时间.");
					ret = false;
				}else if(from.getTime() == to.getTime() && Integer.parseInt(workingHours1) == Integer.parseInt(workingHours2)&&Integer.parseInt(workingMinute1) >= Integer.parseInt(workingMinute2)){
					addFieldError("mp2001.MP2001_TO_DATETIME","开始分钟大于结束分钟.");
					ret = false;
				}
			}
			// 验证代理人
			if(mp2001.getMP2001_ACTING_PERSON().equals("")){
				addFieldError("mp2001.MP2001_ACTING_PERSON","Acting Person is empty.");
				ret = false;
			}
			// 验证假单类型
			if(mp2001.getMP2001_LEAVE_TYPE().equals("") || mp2001.getMP2001_LEAVE_TYPE().equals("0")){
				addFieldError("mp2001.MP2001_LEAVE_TYPE","Type of leave is empty.");
				ret = false;
			}else{
				String _type = mp2001.getMP2001_LEAVE_TYPE();
				int _hours = 0;//单位是分钟
				int _annualDays = 0;
				int _sickDays = 0;
				int _familyDays = 0;
				int _maternityDays = 0;
				int _study = 0;
				
				if(!getLeaveHours().equals("")){
					_hours = Integer.parseInt(getLeaveHours());//单位是分钟
				}
				if(!annualDays.equals("")){
					_annualDays = Integer.parseInt(annualDays);
				}
				if(!sickDays.equals("")){
					_sickDays = Integer.parseInt(sickDays);
				}
				if(!familyDays.equals("")){
					_familyDays = Integer.parseInt(familyDays);
				}
				if(!maternityDays.equals("")){
					_maternityDays = Integer.parseInt(maternityDays);
				}
				if(!study.equals("")){
					_study = Integer.parseInt(study);
				}
				
				if(_type.equals(Constant.ANNUAL)){// 年假
					if(_hours > _annualDays){
						//addFieldError("Hours","May apply for the number of days:" + _annualDays/8 + " days "+ _annualDays%8 + " hours"+ _annualDays%60 + "minute");
						showMessageInfo(_annualDays,"Hours");
					}else{
						//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						//Date beginDate = sdf.parse(mp2001.getMP2001_FROM_DATETIME());
						//Date nowDate = new Date();
						
						//long diffDays = (beginDate.getTime() - nowDate.getTime())/(24*60*60*1000);
						
	/*					// 年假1-3天必须提前5天申请；4天以上，须提前10天申请
						if(_hours >= 4*8*60){
							// 必须提前十天
							if(diffDays < 10){//提前10天申请
								addFieldError("Hours",Constant.ANNUAL_10);
							}
						}else{
							//必须提前5天
							if(diffDays < 5){//提前5天申请
								addFieldError("Hours",Constant.ANNUAL_5);
							}
						}*/
					}
				}else if(_type.equals(Constant.SICK)){// 病假
					if(_hours > _sickDays){
						//addFieldError("Hours","May apply for the number of days:" + _sickDays/8 + " days "+ _sickDays%8 + " hours "+ _sickDays%60 + "minute");
						showMessageInfo(_sickDays,"Hours");
					}
					// 判断Comment是否为空
					checkComment();
				}else if(_type.equals(Constant.FAMILY)){// 家庭假
					if(_hours > _familyDays){
						//addFieldError("Hours","May apply for the number of days:" + _annualDays/8 + " days "+ _annualDays%8 + " hours"+ _annualDays%60 + "minute");
						showMessageInfo(_familyDays,"Hours");
					}
					// 判断Comment是否为空
					checkComment();
				}else if(_type.equals(Constant.STUDY)){// 学习假
					if(_hours > _study){
						//addFieldError("Hours","May apply for the number of days:" + _annualDays/8 + " days "+ _annualDays%8 + " hours"+ _annualDays%60 + "minute");
						showMessageInfo(_study,"Hours");
					}
				}else if(_type.equals(Constant.MATERNITY)){// 产假
					if(_hours > _maternityDays){
						//addFieldError("Hours","May apply for the number of days:" + _annualDays/8 + " days "+ _annualDays%8 + " hours"+ _annualDays%60 + "minute");
						showMessageInfo(_maternityDays,"Hours");
					}
				}else if(_type.equals(Constant.UNPAID)){// 无薪假（暂不处理）
				}else if(_type.equals(Constant.OFFICAL_BUSINESS)){// 因公出差（暂不处理）
					// 判断Comment是否为空
					checkComment();
				}else if(_type.equals(Constant.OTHERS)){// 其它（暂不处理）
					// 判断Comment是否为空
					checkComment();
				}
			}
			
			//ActionContext context = ActionContext.getContext();
			//Map<String, Object> session = context.getSession();
			//MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
			
			//mp2001.setMP2001_EMPLOYEE_NUM(employeeData.getMP1001_EMPLOYEE_NUM());
			//mp2001.setMP2001_EMPLOYEE_NAME(employeeData.getMP1001_PREFERED_NAME());
			listInit();
			
		}catch(Exception ex){
			log.info(ex.getMessage());
			ret = false;
		}
		return ret;
	}
	
	// 判断Comment是否为空
	private void checkComment(){
		if(mp2001.getMP2001_COMMENT().equals("")){
			addFieldError("mp2001.MP2001_COMMENT","Comment can not be empty");
		}
	}

	// 显示错误信息
	private void showMessageInfo(int _time,String id) throws Exception{
		int _hour = _time/60;
		int _day = _hour/8;
		int _minute = _time%60;
		
		addFieldError(id,"May apply for the number of days:" + _day + " days "+ _hour%8 + " hours"+ _minute + "minute");
	}

	//请假一览
	@SuppressWarnings("unchecked")
	public String applyLeaveListInit(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			listInit();
		    // 部门信息在登录后就缓存在Session中了
		    departmentList = (List<MP0002>) session.get("DEPARTMENT_LIST");
			loginName = employeeData.getMP1001_EMPLOYEE_NUM().toUpperCase();
			//employeeNum = employeeData.getMP1001_EMPLOYEE_NUM();
			positionLev = employeeData.getMP1001_POSITION();
			roleGroup = employeeData.getMP1001_GROUP();
			departmentID = employeeData.getMP1001_DEPARTMENT_ID();
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Leave Application Information Page Init");
			//----------------------------Operation History------------------
			
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0002002,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_SEARCH)){
				optSearch = "1";
			}
			if(optMap.containsKey(Constant.OPT_DELETE)){
				optDel = "1";
			}
			if(optMap.containsKey(Constant.OPT_DELETE_APPROVE)){
				optDel2 = "1";
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
			if(optAll.equals("0") && optDepartment.equals("0") && optPersonal.equals("1")){
				employeeNum = employeeData.getMP1001_EMPLOYEE_NUM();
			}
			
//			leaveInfo = service.findByProperty("MP2001_EMPLOYEE_NUM", employeeData.getMP1001_EMPLOYEE_NUM(),false);
//			
//			if( 0 == leaveInfo.size()%Constant.PAGE_SIZE){
//				pageCount = leaveInfo.size()/Constant.PAGE_SIZE;
//			}else{
//				pageCount = leaveInfo.size()/Constant.PAGE_SIZE + 1;
//			}
//			
//			HashMap<String, String> propertyMap = new HashMap<String, String>();
//			propertyMap.put("PAGE_NUM", "1"); // 当前页
//			propertyMap.put("PAGE_COUNT", String.valueOf(Constant.PAGE_SIZE)); // 每页显示条数
//			
//			leaveInfo = service.findByPropertyPage(propertyMap,employeeData);
//			dataConvert2001(leaveInfo,employeeData);
			
			leaveInfo = new ArrayList<MP2001>(); 

			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	// 请假查询
	public String applyLeaveListSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Leave Application Information");
			//----------------------------Operation History------------------
//			
//			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0002002);
//			if(optMap.containsKey(Constant.OPT_SEARCH)){
//				optSearch = "1";
//			}
//			if(optMap.containsKey(Constant.OPT_DELETE)){
//				optDel = "1";
//			}
//			if(optMap.containsKey(Constant.OPT_EDIT)){
//				optEdit = "1";
//			}
//			if(optMap.containsKey(Constant.OPT_VIEW)){
//				optView = "1";
//			}
//			if(optMap.containsKey(Constant.OPT_APPROVAL)){
//				optApproval = "1";
//			}
//			
//			if(optMap.containsKey(Constant.OPT_PERSONAL)){
//				optPersonal = "1";
//			}
//			if(optMap.containsKey(Constant.OPT_DEPARTMENT)){
//				optDepartment = "1";
//			}
//			if(optMap.containsKey(Constant.OPT_ALL)){
//				optAll = "1";
//			}
//			if(optAll.equals("0") && optDepartment.equals("0") && optPersonal.equals("1")){
//				employeeNum = employeeData.getMP1001_EMPLOYEE_NUM();
//			}
			
			pageNum = 1;
			getLeaveListData();
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	//根据检索条件，查询请假信息
	@SuppressWarnings("unchecked")
	private void getLeaveListData() throws ParseException{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		listInit();
		
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		//employeeNum = employeeData.getMP1001_EMPLOYEE_NUM();
		loginName = employeeData.getMP1001_EMPLOYEE_NUM().toUpperCase();
		positionLev = employeeData.getMP1001_POSITION();
		roleGroup = employeeData.getMP1001_GROUP();
		departmentID = employeeData.getMP1001_DEPARTMENT_ID();
		
	    // 部门信息在登录后就缓存在Session中了
	    departmentList = (List<MP0002>) session.get("DEPARTMENT_LIST");
	    
		HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0002002,Constant.SYSTEM_NUM_HR);
		if(optMap.containsKey(Constant.OPT_SEARCH)){
			optSearch = "1";
		}
		if(optMap.containsKey(Constant.OPT_DELETE)){
			optDel = "1";
		}
		if(optMap.containsKey(Constant.OPT_DELETE_APPROVE)){
			optDel2 = "1";
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
		if(optAll.equals("0") && optDepartment.equals("0") && optPersonal.equals("1")){
			employeeNum = employeeData.getMP1001_EMPLOYEE_NUM();
		}
		
		HashMap<String, String> propertyMap = new HashMap<String, String>();
		propertyMap.put("EMP_NUM", employeeNum);
		propertyMap.put("NUM", MP2001_NUM);
		propertyMap.put("TYPE", leaveType);
		propertyMap.put("FROM", fromDate);
		propertyMap.put("TO", toDate);
		propertyMap.put("APPROVAL", approvalType);
		propertyMap.put("DEPARTMENT", depID);
		propertyMap.put("JOB_TITLE", jobTitle);
		
		propertyMap.put("OPT_PERSONAL", optPersonal);
		propertyMap.put("OPT_DEPARTMENT", optDepartment);
		propertyMap.put("OPT_ALL", optAll);
		
		leaveInfo = service.findByProperty(propertyMap,employeeData);
		if( 0 == leaveInfo.size()%Constant.PAGE_SIZE){
			pageCount = leaveInfo.size()/Constant.PAGE_SIZE;
		}else{
			pageCount = leaveInfo.size()/Constant.PAGE_SIZE + 1;
		}
		
		if(pageNum == 0){
			pageNum = 1;
		}
		propertyMap.put("PAGE_NUM", String.valueOf(pageNum)); // 当前页
		propertyMap.put("PAGE_COUNT", String.valueOf(Constant.PAGE_SIZE)); // 每页显示条数
		
		leaveInfo = service.findByPropertyPage(propertyMap,employeeData);
		
		if(leaveInfo != null && leaveInfo.size() > 0 ){
			dataConvert2001(leaveInfo,employeeData);
		}
	}

	public String  leaveInfoExcel(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Create Employee Attendance Information Excel Document");
			//----------------------------Operation History------------------
			
			// 新建EXCEL工作表
			Workbook wb = ExcelUtil.CreateHSSFWorkBook();
			// 新建一个SHEET页面
			Sheet sheet = ExcelUtil.CreateSheet(wb, "Leave Application");
			// 设置SHEET页面属性
			ExcelUtil.SetSheetPropertyHSSF(sheet);
			// 获取预定的样式
			Map<String, CellStyle> styles = ExcelUtil.CreateStyles(wb);
			
			// Header标题
			String[] titles = {"No", "EMPLOYEE NUMBER", "DAY", "FROM DATE","TO DATE","TYPE OF LEAVE","STATUS"};

			// 生成标题行
			float rowHeight = 27f;
			ExcelUtil.CreateHeadRow(sheet, titles, rowHeight, styles);
	        // 冻结第一行
	        sheet.createFreezePane(0, 1);
	        
	        int[] cellsWidth = {5,30,20,20,20,20,20};
	        // 设置单元格的宽度
	        ExcelUtil.SetCellsWidth(sheet, cellsWidth);
	        
	      //---------------------------主报表-----------------------------------------------------------------------------------------
	        leaveTypeList = Constant.getLeaveTypeList();
			HashMap<String, String> propertyMap = new HashMap<String, String>();
			propertyMap.put("EMP_NUM", param1);
			propertyMap.put("NUM", param2);
			propertyMap.put("TYPE", param3);
			propertyMap.put("FROM", param4);
			propertyMap.put("TO", param5);
			propertyMap.put("APPROVAL", param6);
			propertyMap.put("DEPARTMENT", param7);
			propertyMap.put("JOB_TITLE", param8);
			propertyMap.put("OPT_ALL", "1");
			propertyMap.put("PAGE_NUM", "0"); // 当前页
			propertyMap.put("PAGE_COUNT", "0"); // 每页显示条数
			
			// 取得报表数据
			leaveInfo = service.findByPropertyPage(propertyMap,employeeData);
			if(leaveInfo != null && leaveInfo.size() > 0 ){
				dataConvert2001(leaveInfo,employeeData);
			}
			
	        int count;
	        String[] datas = new String[7];
	        for(int i=0,j=leaveInfo.size(); i<j; i++){
	        	MP2001 mp2001Obj = leaveInfo.get(i);
	        	count = i + 1;
	        	datas = new String[7];
	        	datas[0] = String.valueOf(count);
	        	datas[1] = mp2001Obj.getMP2001_EMPLOYEE_NAME() + "(" + mp2001Obj.getMP2001_EMPLOYEE_NUM() + ")";
	        	datas[2] = mp2001Obj.getMP2001_DAYS();
	        	datas[3] = mp2001Obj.getMP2001_FROM_DATETIME();
	        	datas[4] = mp2001Obj.getMP2001_TO_DATETIME();
	        	datas[5] = mp2001Obj.getMP2001_LEAVE_TYPE_NAME();
	        	datas[6] = mp2001Obj.getMP2001_APPROVAL_NAME();
	        	
	        	ExcelUtil.SetCellsValue(count, sheet, styles, datas);
	        }
	        
			// 生成Excel文件
	        fileName = "AttendanceRecord.xls";
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
	
	// 分页
	@SuppressWarnings("unchecked")
	public String leaveDetailList() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			listInit();
		    // 部门信息在登录后就缓存在Session中了
		    departmentList = (List<MP0002>) session.get("DEPARTMENT_LIST");
			//employeeNum = employeeData.getMP1001_EMPLOYEE_NUM();
			loginName = employeeData.getMP1001_EMPLOYEE_NUM().toUpperCase();
			positionLev = employeeData.getMP1001_POSITION();
			roleGroup = employeeData.getMP1001_GROUP();
			departmentID = employeeData.getMP1001_DEPARTMENT_ID();
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Leave Detail Information Page Click");
			//----------------------------Operation History------------------
			
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0002002,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_SEARCH)){
				optSearch = "1";
			}
			if(optMap.containsKey(Constant.OPT_DELETE)){
				optDel = "1";
			}
			if(optMap.containsKey(Constant.OPT_DELETE_APPROVE)){
				optDel2 = "1";
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
			if(optAll.equals("0") && optDepartment.equals("0") && optPersonal.equals("1")){
				employeeNum = employeeData.getMP1001_EMPLOYEE_NUM();
			}
			
			log.info("loginName:" + loginName);
			
			HashMap<String, String> propertyMap = new HashMap<String, String>();
			propertyMap.put("PAGE_NUM", String.valueOf(pageNum)); // 当前页
			propertyMap.put("PAGE_COUNT", String.valueOf(pageCount)); // 每页显示条数
			propertyMap.put("EMP_NUM", employeeNum);
			propertyMap.put("NUM", MP2001_NUM);
			propertyMap.put("TYPE", leaveType);
			propertyMap.put("FROM", fromDate);
			propertyMap.put("TO", toDate);
			propertyMap.put("APPROVAL", approvalType);
			propertyMap.put("DEPARTMENT", depID.equals("undefined")?"":depID);
			propertyMap.put("JOB_TITLE", jobTitle.equals("undefined")?"":jobTitle);
			
			propertyMap.put("OPT_PERSONAL", optPersonal);
			propertyMap.put("OPT_DEPARTMENT", optDepartment);
			propertyMap.put("OPT_ALL", optAll);
			
			leaveInfo = service.findByPropertyPage(propertyMap,employeeData);
			dataConvert2001(leaveInfo,employeeData);
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	// 解析数据集
	private void dataConvert2001(List<MP2001> dataList, MP1001 employeeData) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		int _hour = 0;
		int _day = 0;
		//int _minute = 0;
		StringBuffer _sb = new StringBuffer();
		
		List<MP1001> mp11List = serviceMP1001.findAll();
		Map<String,String> mp11Map = new LinkedHashMap<String,String>();
		for(int i=0,j=mp11List.size(); i<j; i++){
			MP1001 mp11 = mp11List.get(i);
			String preferedName = mp11.getMP1001_PREFERED_NAME();
			mp11Map.put(mp11.getMP1001_EMPLOYEE_NUM(), preferedName);
		}
		
		for(int m=0,n=dataList.size(); m<n; m++){
			MP2001 obj = dataList.get(m);
			int _totalTime = 0;
			if(obj.getMP2001_DAYS() == null || obj.getMP2001_DAYS().equals("")){
				_totalTime = 0;
			}else{
				_totalTime = Integer.parseInt(obj.getMP2001_DAYS());
				_hour = _totalTime%8;
				_day = _totalTime/8;
				//_minute = _totalTime%60;
				_sb = new StringBuffer();
				if(_day != 0 ){
					_sb.append(_day + " D ");
				}
				if(_hour != 0){
					_sb.append(_hour%8 + " H");
				}
				//if(_minute != 0){
				//	_sb.append(_minute + "minute");
				//}
				
				obj.setMP2001_DAYS(_sb.toString());
			}
			
			if(mp11Map.containsKey(obj.getMP2001_ACTING_PERSON())){
				String actingPersonName = mp11Map.get(obj.getMP2001_ACTING_PERSON());
				obj.setMP2001_ACTING_PERSON_NAME(actingPersonName);
			}
			
			// 格式化时间
			if(obj.getMP2001_FROM_DATETIME() != null && !obj.getMP2001_FROM_DATETIME().equals("")){
				Date startDate = sdf.parse(obj.getMP2001_FROM_DATETIME());
				obj.setMP2001_FROM_DATETIME(sdf.format(startDate));
			}
			if(obj.getMP2001_TO_DATETIME() != null && !obj.getMP2001_TO_DATETIME().equals("")){
				Date finishDate = sdf.parse(obj.getMP2001_TO_DATETIME());
				obj.setMP2001_TO_DATETIME(sdf.format(finishDate));
			}
			// 经理批准
			if(obj.getMP2001_APPROVAL() != null && !obj.getMP2001_APPROVAL().equals("")){
				if(obj.getMP2001_APPROVAL().equals("1")){
					obj.setMP2001_APPROVAL_NAME("pending");
				}else if(obj.getMP2001_APPROVAL().equals("2")){
					obj.setMP2001_APPROVAL_NAME("declined");
				}else if(obj.getMP2001_APPROVAL().equals("3")){
					obj.setMP2001_APPROVAL_NAME("approved");
				}
			}
			// 请假类型
			if(obj.getMP2001_LEAVE_TYPE()!=null && !obj.getMP2001_LEAVE_TYPE().equals("") && leaveTypeList.containsKey(obj.getMP2001_LEAVE_TYPE())){
				obj.setMP2001_LEAVE_TYPE_NAME(leaveTypeList.get(obj.getMP2001_LEAVE_TYPE()));
			}
			// 员工名
			//if(obj.getMP2001_EMPLOYEE_NUM()!=null && !obj.getMP2001_EMPLOYEE_NUM().equals("")){
			//	obj.setMP2001_EMPLOYEE_NAME(employeeData.getMP1001_PREFERED_NAME());
			//}
		}
	}

	// 个人假期一览
	public String vacationDataList() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		mp1001 = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(mp1001.getMP1001_EMPLOYEE_NUM(),mp1001.getMP1001_PREFERED_NAME(),"Vacation Data List");
			//----------------------------Operation History------------------
			getExamQuestionInfoByPage(1);
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	// 个人假期一览分页处理
	public String vacationDataListPageClick() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		mp1001 = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(mp1001.getMP1001_EMPLOYEE_NUM(),mp1001.getMP1001_PREFERED_NAME(),"Vacation Data List Page Click, Page Number:" + pageNum);
			//----------------------------Operation History------------------
			getExamQuestionInfoByPage(pageNum);
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	// 取得权限
	private void getVationAuthority(){
		HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(mp1001.getMP1001_EMPLOYEE_NUM(), Constant.F0002003,Constant.SYSTEM_NUM_HR);
		mp1001.setPersonalAccess("0");
		mp1001.setDepartmentAccess("0");
		mp1001.setFullAccess("0");
		
		if(optMap.containsKey(Constant.OPT_EDIT)){
			optEdit = "1";
		}
		if(optMap.containsKey(Constant.OPT_PERSONAL)){
			mp1001.setPersonalAccess("1");
		}
		if(optMap.containsKey(Constant.OPT_DEPARTMENT)){
			mp1001.setDepartmentAccess("1");
		}
		if(optMap.containsKey(Constant.OPT_ALL)){
			mp1001.setFullAccess("1");
		}
	}
	
	// 重新取得分页数据
	private void getExamQuestionInfoByPage(int _pageNum){
		getVationAuthority();
		VacationInfoList = serviceMp2002.findByPropertysPage(mp1001,-1,-1);
		if( 0 == VacationInfoList.size()%Constant.PAGE_SIZE){
			pageCount = VacationInfoList.size()/Constant.PAGE_SIZE;
		}else{
			pageCount = VacationInfoList.size()/Constant.PAGE_SIZE + 1;
		}
		if(_pageNum > pageCount){
			_pageNum= pageCount;
		}
		if(_pageNum == 0){
			_pageNum = 1;
		}
		VacationInfoList = serviceMp2002.findByPropertysPage(mp1001,_pageNum,Constant.PAGE_SIZE);
		
		for(int i=0,j=VacationInfoList.size();i<j;i++){
			MP2002 mp22 = VacationInfoList.get(i);
			
			getPersonalLeave(mp22);
			
			//年假
			if(!mp22.getMP2002_ANNUAL().equals("")){
				int _annual = Integer.parseInt(mp22.getMP2002_ANNUAL());
				int _annual_t = Integer.parseInt(mp22.getMP2002_ANNUAL_T());
				mp22.setMP2002_ANNUAL(getTimeInfo(_annual));
				mp22.setMP2002_ANNUAL_T(getTimeInfo(_annual_t));
			}
			//病假
			if(!mp22.getMP2002_SICK().equals("")){
				int _sick = Integer.parseInt(mp22.getMP2002_SICK());
				mp22.setMP2002_SICK(getTimeInfo(_sick));
				int _sick_t = Integer.parseInt(mp22.getMP2002_SICK_T());
				mp22.setMP2002_SICK_T(getTimeInfo(_sick_t));
			}
			//家庭假
			if(!mp22.getMP2002_FAMILY_RESP().equals("")){
				int _family = Integer.parseInt(mp22.getMP2002_FAMILY_RESP());
				mp22.setMP2002_FAMILY_RESP(getTimeInfo(_family));
				int _family_t = Integer.parseInt(mp22.getMP2002_FAMILY_RESP_T());
				mp22.setMP2002_FAMILY_RESP_T(getTimeInfo(_family_t));
			}
			//产假
			if(!mp22.getMP2002_MATERNITY().equals("")){
				int _maternity = Integer.parseInt(mp22.getMP2002_MATERNITY());
				mp22.setMP2002_MATERNITY(getTimeInfo(_maternity));
				int _maternity_t = Integer.parseInt(mp22.getMP2002_MATERNITY_T());
				mp22.setMP2002_MATERNITY_T(getTimeInfo(_maternity_t));
			}
			//学习假
			if(!mp22.getMP2002_STUDY().equals("")){
				int _study = Integer.parseInt(mp22.getMP2002_STUDY());
				mp22.setMP2002_STUDY(getTimeInfo(_study));
				int _study_t = Integer.parseInt(mp22.getMP2002_STUDY_T());
				mp22.setMP2002_STUDY_T(getTimeInfo(_study_t));
			}
		}
	}
	
	// 求个人请假天数
	private void getPersonalLeave(MP2002 mp22){
		int _annual = 0;
		int _sick = 0;
		int _family = 0;
		int _study = 0;
		int _maternity = 0;
		int _unpay = 0;
		int _business = 0;
		int _other = 0;
		
		//log.info(_unpay + "|" + _business + "|" + _other);
				
		List<MP2001> mp21 = service.findByProperty("MP2001_EMPLOYEE_NUM", mp22.getMP2002_EMPLOYEE_NUM(),true);
		for(int i=0,j=mp21.size(); i<j; i++){
			MP2001 mp2 = mp21.get(i);
			
			if(mp2.getMP2001_LEAVE_TYPE().equals("1")){// 年假 Annual
				_annual += Integer.parseInt(mp2.getMP2001_DAYS());
			}else if(mp2.getMP2001_LEAVE_TYPE().equals("2")){// 病假 Sick
				_sick += Integer.parseInt(mp2.getMP2001_DAYS());
			}else if(mp2.getMP2001_LEAVE_TYPE().equals("3")){// 家庭假 Family Resp
				_family += Integer.parseInt(mp2.getMP2001_DAYS());
			}else if(mp2.getMP2001_LEAVE_TYPE().equals("4")){// 学习假 Study
				_study += Integer.parseInt(mp2.getMP2001_DAYS());
			}else if(mp2.getMP2001_LEAVE_TYPE().equals("5")){// 产假 Maternity
				_maternity += Integer.parseInt(mp2.getMP2001_DAYS());
			}else if(mp2.getMP2001_LEAVE_TYPE().equals("6")){// 无薪假 Unpay
				_unpay += Integer.parseInt(mp2.getMP2001_DAYS());
			}else if(mp2.getMP2001_LEAVE_TYPE().equals("7")){// 公出假 Official Business
				_business += Integer.parseInt(mp2.getMP2001_DAYS());
			}else if(mp2.getMP2001_LEAVE_TYPE().equals("8")){// 其它 Other
                // other的情况暂时不做处理
				//_other += Integer.parseInt(mp2.getMP2001_DAYS());
			}
		}
		
		mp22.setMP2002_ANNUAL_A(getTimeInfo(_annual));// 年假Annual
		mp22.setMP2002_SICK_A(getTimeInfo(_sick));// 病假Sick
		mp22.setMP2002_FAMILY_RESP_A(getTimeInfo(_family));// 家庭假Family Resp
		mp22.setMP2002_STUDY_A(getTimeInfo(_study));// 学习假Study
		mp22.setMP2002_MATERNITY_A(getTimeInfo(_maternity));// 产假Maternity
	}
	
	// 解析时间String.valueOf(
	private String getTimeInfo(int _totalTime){
		if( 0 == _totalTime){
			return "0";
		}
		int _hour = _totalTime%8;
		int _day = _totalTime/8;
		//int _minute = _totalTime%60;
		StringBuffer sb = new StringBuffer();
		
		if(_day != 0){
			sb.append(_day + " D ");
		}
		if(_hour%8 != 0){
			sb.append(_hour%8 + " H ");
		}
		//if(_minute != 0){
		//	sb.append(_minute + " M");
		//}
		return sb.toString();
	}
	
	// 个人上下班时间
	@SuppressWarnings("unchecked")
	public String workingHourRecordsListInit() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
//		session.put("action", this);
		
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			Date today = calendar.getTime();
			String _empNum  = "";
			String _fromDate = sdf.format(today);
			String _finishDate = sdf.format(today);
			attendenceStatusList = Constant.getAbnormalType();
			loginName = employeeData.getMP1001_EMPLOYEE_NUM().toUpperCase();
			roleGroup = employeeData.getMP1001_GROUP();
			depID = employeeData.getMP1001_DEPARTMENT_ID();
			if(null == departmentID ){
				departmentID ="";
			}
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Attendance Record Management Page Init");
			//----------------------------Operation History------------------
			
			// 权限检查
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0002004,Constant.SYSTEM_NUM_HR);
			employeeData.setPersonalAccess("0");
			employeeData.setDepartmentAccess("0");
			employeeData.setFullAccess("0");
			
			if(optMap.containsKey(Constant.OPT_SEARCH)){
				optSearch = "1";
			}
			if(optMap.containsKey(Constant.OPT_PDF)){
				optPdf = "1";
			}
			if(optMap.containsKey(Constant.OPT_APPROVAL)){
				optApproval = "1";
			}
			
			if(optMap.containsKey(Constant.OPT_PERSONAL)){
				optPersonal = "1";
				employeeData.setPersonalAccess("1");
			}
			if(optMap.containsKey(Constant.OPT_DEPARTMENT)){
				optDepartment = "1";
				employeeData.setDepartmentAccess("1");
			}
			if(optMap.containsKey(Constant.OPT_ALL)){
				optAll = "1";
				employeeData.setFullAccess("1");
			}

			int rowCount = serviceMp2003.getRowCountByPropertys(employeeData,_empNum,_fromDate,_finishDate,departmentID, attendenceStatus);
			
			if( 0 == rowCount%Constant.PAGE_SIZE){
				pageCount = rowCount/Constant.PAGE_SIZE;
			}else{
				pageCount = rowCount/Constant.PAGE_SIZE + 1;
			}
			
			HashMap<String, String> propertyMap = new HashMap<String, String>();
			propertyMap.put("PAGE_NUM", "1"); // 当前页
			propertyMap.put("PAGE_COUNT", String.valueOf(Constant.PAGE_SIZE)); // 每页显示条数
			
			int _pageNum = 1;
			int _pageCount = Constant.PAGE_SIZE;
			workingHourRecordList = serviceMp2003.findByPropertysPage(employeeData, _pageNum, _pageCount,_empNum,_fromDate,_finishDate,departmentID, attendenceStatus);
			//dataConvert2003(workingHourRecordList,employeeData);
			
		    // 部门信息在登录后就缓存在Session中了
		    departmentList = (List<MP0002>) session.get("DEPARTMENT_LIST");
		    
		    
		    //for compare date 
		    todayDate = sdf.format(today);
			return SUCCESS;
		}catch(Exception ex){
		    log.info(ex.getMessage());
		    return "error";
		}
	}
	
	// 上下班记录查询
	@SuppressWarnings("unchecked")
	public String workingRecordsListSearch() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
//		session.put("action", this);
		
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		
		try{
			roleGroup = employeeData.getMP1001_GROUP();
			loginName = employeeData.getMP1001_EMPLOYEE_NUM().toUpperCase();
			depID = employeeData.getMP1001_DEPARTMENT_ID();
			if(null == departmentID ){
				departmentID ="";
			}
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Working Records List Information");
			//----------------------------Operation History------------------
			
			// 权限检查
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0002004,Constant.SYSTEM_NUM_HR);
			employeeData.setPersonalAccess("0");
			employeeData.setDepartmentAccess("0");
			employeeData.setFullAccess("0");
			
			if(optMap.containsKey(Constant.OPT_SEARCH)){
				optSearch = "1";
			}
			if(optMap.containsKey(Constant.OPT_PDF)){
				optPdf = "1";
			}
			if(optMap.containsKey(Constant.OPT_APPROVAL)){
				optApproval = "1";
			}
			
			if(optMap.containsKey(Constant.OPT_PERSONAL)){
				optPersonal = "1";
				employeeData.setPersonalAccess("1");
			}
			if(optMap.containsKey(Constant.OPT_DEPARTMENT)){
				optDepartment = "1";
				employeeData.setDepartmentAccess("1");
			}
			if(optMap.containsKey(Constant.OPT_ALL)){
				optAll = "1";
				employeeData.setFullAccess("1");
			}
			
			int rowCount =  serviceMp2003.getRowCountByPropertys(employeeData,employeeNum,fromDate,toDate,departmentID,attendenceStatus);
			
			if( 0 == rowCount%Constant.PAGE_SIZE){
				pageCount = rowCount/Constant.PAGE_SIZE;
			}else{
				pageCount = rowCount/Constant.PAGE_SIZE + 1;
			}
			
			HashMap<String, String> propertyMap = new HashMap<String, String>();
			propertyMap.put("PAGE_NUM", "1"); // 当前页
			propertyMap.put("PAGE_COUNT", String.valueOf(Constant.PAGE_SIZE)); // 每页显示条数
			
			int _pageNum = 1;
			int _pageCount = Constant.PAGE_SIZE;
			workingHourRecordList = serviceMp2003.findByPropertysPage(employeeData, _pageNum, _pageCount,employeeNum,fromDate,toDate,departmentID,attendenceStatus);
			//dataConvert2003(workingHourRecordList,employeeData);
			
		    // 部门信息在登录后就缓存在Session中了
		    departmentList = (List<MP0002>) session.get("DEPARTMENT_LIST");
		    attendenceStatusList = Constant.getAbnormalType();
		    
		    

		    //for compare date 
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    Date today = new Date();
		    todayDate = sdf.format(today);
		    
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	// 个人上下班记录 分页处理
	public String workRecordList() throws Exception{
		log.info("---个人上下班记录分页处理---");
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			roleGroup = employeeData.getMP1001_GROUP();
			loginName = employeeData.getMP1001_EMPLOYEE_NUM().toUpperCase();
			depID = employeeData.getMP1001_DEPARTMENT_ID();
			if(null == departmentID || departmentID.equals("undefined") ){
				departmentID ="";
			}
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Work Record Information List");
			//----------------------------Operation History------------------
			
			// 权限检查
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0002004,Constant.SYSTEM_NUM_HR);
			employeeData.setPersonalAccess("0");
			employeeData.setDepartmentAccess("0");
			employeeData.setFullAccess("0");
			
			if(optMap.containsKey(Constant.OPT_SEARCH)){
				optSearch = "1";
			}
			if(optMap.containsKey(Constant.OPT_PDF)){
				optPdf = "1";
			}
			if(optMap.containsKey(Constant.OPT_APPROVAL)){
				optApproval = "1";
			}
			
			if(optMap.containsKey(Constant.OPT_PERSONAL)){
				optPersonal = "1";
				employeeData.setPersonalAccess("1");
			}
			if(optMap.containsKey(Constant.OPT_DEPARTMENT)){
				optDepartment = "1";
				employeeData.setDepartmentAccess("1");
			}
			if(optMap.containsKey(Constant.OPT_ALL)){
				optAll = "1";
				employeeData.setFullAccess("1");
			}
			
			workingHourRecordList = serviceMp2003.findByPropertysPage(employeeData, pageNum, pageCount,employeeNum,fromDate,toDate,departmentID, attendenceStatus);
			//dataConvert2003(workingHourRecordList,employeeData);
			log.info("Data Size:" + workingHourRecordList.size());
			
			
			//for compare date 
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    Date today = new Date();
		    todayDate = sdf.format(today);
		    
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public String workTimeConfirm(){
		//MP1001 mp11 = serviceMP1001.findById(employeeNum);
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//MP1001 mp11 = serviceMP1001.findById(employeeNum);
			roleGroup = employeeData.getMP1001_GROUP();
			loginName = employeeData.getMP1001_EMPLOYEE_NUM().toUpperCase();
			depID = employeeData.getMP1001_DEPARTMENT_ID();
			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Confrim Work Time");
			//----------------------------Operation History------------------
			
//			int rectifyTimes = Integer.parseInt(mp11.getMP1001_RECTIFY_TIMES());
//			if(rectifyTimes >= 1){
//				rectifyTimes = rectifyTimes -1;
//				mp11.setMP1001_RECTIFY_TIMES(String.valueOf(rectifyTimes));
//				
//				serviceMp2003.updateTimes(employeeNum, confirmDate);
//				serviceMP1001.update(mp11);
//			}
			//serviceMp2003.updateTimes(confirmNum, confirmDate);
//			serviceMP1001.update(mp11);
			if(null == departmentID ){
				departmentID ="";
			}
			
			// 权限检查
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0002004,Constant.SYSTEM_NUM_HR);
			employeeData.setPersonalAccess("0");
			employeeData.setDepartmentAccess("0");
			employeeData.setFullAccess("0");
			
			if(optMap.containsKey(Constant.OPT_SEARCH)){
				optSearch = "1";
			}
			if(optMap.containsKey(Constant.OPT_PDF)){
				optPdf = "1";
			}
			if(optMap.containsKey(Constant.OPT_APPROVAL)){
				optApproval = "1";
			}
			
			if(optMap.containsKey(Constant.OPT_PERSONAL)){
				optPersonal = "1";
				employeeData.setPersonalAccess("1");
			}
			if(optMap.containsKey(Constant.OPT_DEPARTMENT)){
				optDepartment = "1";
				employeeData.setDepartmentAccess("1");
			}
			if(optMap.containsKey(Constant.OPT_ALL)){
				optAll = "1";
				employeeData.setFullAccess("1");
			}
			
			workingHourRecordList = serviceMp2003.findByPropertysPage(employeeData, pageNum, pageCount,employeeNum,fromDate,toDate,departmentID, attendenceStatus);
			//dataConvert2003(workingHourRecordList,employeeData);
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public String workTimeConfirmInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Work Time Confrim Page Init");
			//----------------------------Operation History------------------
			
			MP2003 _data = new MP2003();
			_data.setMP2003_EMPLOYEE_NUM(confirmNum);
			_data.setMP2003_DATETIME(confirmDate);
			
			List<MP2003> _dataList = serviceMp2003.findByPropertys(_data);
			if(_dataList.size() > 0){
				mp2003 = _dataList.get(0);
				mp2003.setMP2003_DATETIME(mp2003.getMP2003_DATETIME().substring(0,10));
				if(mp2003.getMP2003_START_TIME() != null && !mp2003.getMP2003_START_TIME().equals("")){
					mp2003.setMP2003_START_TIME(mp2003.getMP2003_START_TIME().substring(0,19));
				}
				if(mp2003.getMP2003_FINISH_TIME() != null && !mp2003.getMP2003_FINISH_TIME().equals("")){
					mp2003.setMP2003_FINISH_TIME(mp2003.getMP2003_FINISH_TIME().substring(0,19));
				}
			}
			
			statusList = Constant.getInOutMsg();
			
			return SUCCESS;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return "error";
		}
	}
	
	public String workTimeConfirmSave(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Save Work Time Information");
			//----------------------------Operation History------------------
			
			Calendar calendar = Calendar.getInstance();
			Calendar nextDayCal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//String editDate = String.valueOf(calendar.get(Calendar.YEAR)) + "-" + String.format("%02d",calendar.get(Calendar.MONTH) + 1) + "-" + String.format("%02d",calendar.get(Calendar.DAY_OF_MONTH)) + "-" +  String.format("%02d",calendar.get(Calendar.HOUR_OF_DAY)) + "-" + String.format("%02d",calendar.get(Calendar.MINUTE));

			mp2003.setMP2003_EDIT_DATETIME(calendar.getTime().toString());
			mp2003.setMP2003_EDIT_USER(employeeData.getMP1001_EMPLOYEE_NUM());
			
			serviceMp2003.updateStatus(mp2003);
			try{
				CustomerContextHolder.setCustomerType("finger");
				int usrId =  serviceCHECKINOUT.findAll(mp2003.getMP2003_EMPLOYEE_NUM());
				if(usrId > 0 ){
					CHECKINOUT checkinout = new CHECKINOUT();
					checkinout.setUSERID(usrId);
					checkinout.setCHECKTYPE("I");
					checkinout.setVERIFYCODE(1);
					checkinout.setSENSORID("999");
					checkinout.setWORKCODE(0);
					checkinout.setSN("2364259440003");
					checkinout.setUSEREXTFMT(1);
					checkinout.setMEMOINFO("");
					
					CHECKINOUT checkinout_1 = new CHECKINOUT();
					checkinout_1.setUSERID(usrId);
					checkinout_1.setCHECKTYPE("I");
					checkinout_1.setVERIFYCODE(1);
					checkinout_1.setSENSORID("999");
					checkinout_1.setWORKCODE(0);
					checkinout_1.setSN("2364259440003");
					checkinout_1.setUSEREXTFMT(1);
					checkinout_1.setMEMOINFO("");
					
					String startTime = mp2003.getMP2003_DATETIME() + " 08:00";
					String finishTime = mp2003.getMP2003_DATETIME() + " 16:30";
					String shiftDStartTime = mp2003.getMP2003_DATETIME() + " 06:00";
					String shiftDFinishTime = mp2003.getMP2003_DATETIME() + " 18:00";
					String shiftNStartTime = mp2003.getMP2003_DATETIME() + " 18:00";
					nextDayCal.setTime(sdf.parse(mp2003.getMP2003_DATETIME()));
					nextDayCal.add(Calendar.DAY_OF_MONTH, 1); //next day
					String shiftNFinishTime = sdf.format(nextDayCal.getTime()) + " 06:00";
					if(null != mp2003.getMP2003_COMMENT() && -1 != mp2003.getMP2003_COMMENT().indexOf("N Abnormal")){
						if(null == mp2003.getMP2003_START_TIME() || mp2003.getMP2003_START_TIME().equalsIgnoreCase("")){
							checkinout.setCHECKTIME(shiftNStartTime);
							serviceCHECKINOUT.save(checkinout);
						}
						if(null == mp2003.getMP2003_FINISH_TIME() || mp2003.getMP2003_FINISH_TIME().equalsIgnoreCase("")){
							checkinout.setCHECKTIME(shiftNFinishTime);
							serviceCHECKINOUT.save(checkinout);
						}
					}
					else if(null != mp2003.getMP2003_COMMENT() && -1 != mp2003.getMP2003_COMMENT().indexOf("D Abnormal")){
						if(null == mp2003.getMP2003_START_TIME() || mp2003.getMP2003_START_TIME().equalsIgnoreCase("")){
							checkinout.setCHECKTIME(shiftDStartTime);
							serviceCHECKINOUT.save(checkinout);
						}
						if(null == mp2003.getMP2003_FINISH_TIME() || mp2003.getMP2003_FINISH_TIME().equalsIgnoreCase("")){
							checkinout.setCHECKTIME(shiftDFinishTime);
							serviceCHECKINOUT.save(checkinout);
						}
					}
					else if(mp2003.getMP2003_STATUS().equals("3")){ // In is null
						checkinout.setCHECKTIME(startTime);
						serviceCHECKINOUT.save(checkinout);
					}else if(mp2003.getMP2003_STATUS().equals("4")){ // Out is null
						checkinout.setCHECKTIME(finishTime);
						serviceCHECKINOUT.save(checkinout);
					}else if(mp2003.getMP2003_STATUS().equals("5")){ // In/Out is null
						checkinout.setCHECKTIME(startTime);
						serviceCHECKINOUT.save(checkinout);
						
//						checkinout.setCHECKTIME(finishTime);
//						serviceCHECKINOUT.save(checkinout);  //some times it doesn't work, because the flushMode problem(...I think so...)
						
						checkinout_1.setCHECKTIME(finishTime);
						serviceCHECKINOUT.save(checkinout_1);
					}
				}
				CustomerContextHolder.remove();
			}catch(Exception ex){
				throw ex;
			}finally{
				CustomerContextHolder.remove();
			}
			
			Overtime overtime = new Overtime();
			overtime.setEmployeeNum(mp2003.getMP2003_EMPLOYEE_NUM());
//			overtime.setCheckTime1(mp2003.getMP2003_DATETIME().substring(0, 10) + " 23:59");
			//change to next day, load two days records to system because
			if(null != mp2003.getMP2003_COMMENT() && -1 != mp2003.getMP2003_COMMENT().indexOf("N Abnormal")){//if it's night
				overtime.setCheckTime1(sdf.format(nextDayCal.getTime()) + " 23:59");
			}
			else{
				overtime.setCheckTime1(mp2003.getMP2003_DATETIME().substring(0, 10) + " 23:59");
			}
			overtime.setCheckTime2(mp2003.getMP2003_DATETIME().substring(0, 10));
		    CommonJobMethod.loadDataToHrSystem(false, overtime)	;
			
			StringBuffer sb = new StringBuffer();
			sb.append("<script type='text/javascript'>");
			sb.append("window.dialogArguments.document.getElementById('refreshData').click();");
			sb.append("window.close();");
			sb.append("</script>");
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
	        out.flush();
	        out.close();

			return SUCCESS;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return "error";
		}
	}
	
	// 数据解析
	@SuppressWarnings("unused")
	private void dataConvert2003(List<MP2003> dataList, MP1001 employeeData) throws ParseException{
		SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdf3 = new SimpleDateFormat("HH");
		SimpleDateFormat sdf4 = new SimpleDateFormat("mm");
		
		double startHour = 0;
		double startMinute = 0;
		double finishHour = 0;
		double finishMinute = 0;
		
		Calendar calendar = Calendar.getInstance();
	    Date _today = calendar.getTime();
		
		for(int m=0,n=dataList.size(); m<n; m++){
			MP2003 obj = dataList.get(m);
			
			String status = obj.getMP2003_STATUS();
			if(status.equals("3") || status.equals("4") || status.equals("5")){
				//continue;
			}else{
				obj.setMP2003_STATUS("2");
			}
			//obj.setMP2003_STATUS("2");
			// 格式化时间
			if(obj.getMP2003_START_TIME() != null && !obj.getMP2003_START_TIME().equals("")){
				Date startDate = sdf1.parse(obj.getMP2003_START_TIME());
				obj.setMP2003_START_TIME(sdf2.format(startDate));
				
				startHour = Integer.parseInt(sdf3.format(startDate));
				startMinute = Integer.parseInt(sdf4.format(startDate));
			}
			if(obj.getMP2003_FINISH_TIME() != null && !obj.getMP2003_FINISH_TIME().equals("")){
				Date finishDate = sdf1.parse(obj.getMP2003_FINISH_TIME());
				obj.setMP2003_FINISH_TIME(sdf2.format(finishDate));
				
				finishHour = Integer.parseInt(sdf3.format(finishDate));
				finishMinute = Integer.parseInt(sdf4.format(finishDate));
			}
			
			// 如果上班时间早于8点，则按照8点计算；若下班时间晚于四点半，则按四点半计算
			if(startHour < 8){
				startHour = 8;
				startMinute = 0;
			}
			if(finishHour > 16){
				finishHour = 16;
				finishMinute = 30;
			}else if(16 == finishHour && finishMinute > 30){
				finishMinute = 30;
			}
			
			if(obj.getMP2003_DATETIME() != null && !obj.getMP2003_DATETIME().equals("")){
				Date _dateTime = sdf0.parse(obj.getMP2003_DATETIME());
				obj.setMP2003_DATETIME(sdf0.format(_dateTime));
				
				String today = sdf0.format(_today);
				Calendar calendar1 = Calendar.getInstance();
				calendar1.setTime(_dateTime);
				int weekDay = calendar1.get(Calendar.DAY_OF_WEEK);
				
				// 周日：1 周六：7
				if(sdf0.format(_dateTime).equals(today) || 1 == weekDay || 7 == weekDay){
					// 暂无处理
				}
				else{
					double leaveHour = getLeaveHours(obj.getMP2003_EMPLOYEE_NUM(),sdf0.format(_dateTime));
					
					// 如果没有上下班的打卡记录，则显示数据异常；但，如果当天请假8个小时，则显示数据正常
					if(obj.getMP2003_START_TIME() == null || obj.getMP2003_FINISH_TIME() == null){
						if(leaveHour >= 8){
							// 如果请假时间为8小时，则不为异常
							obj.setMP2003_COMMENT("Leave");
							obj.setMP2003_STATUS("2");
						}else{
							// 如果请假时间为8小时，则不为异常
							obj.setMP2003_COMMENT("Abnormal");
							obj.setMP2003_STATUS("1");
						}
					}else if((startHour > 8 || 8 == startHour && startMinute > 0) || (finishHour < 16 || 16 == finishHour && finishMinute < 30)){
						List<MP2001> mp2001List = service.findByDate(obj.getMP2003_EMPLOYEE_NUM(),sdf0.format(_dateTime));
						String altMsg = "";
						
						MP0010 mp10 = serviceMp0010.findById(obj.getMP2003_DATETIME());
						int startWorkHour = 0;
						int startWorkMinutes = 0;
						int endWorkHour = 0;
						int endWorkMinutes = 0;
						
						double startWorkTime = 0;
						double endWorkTime = 0;
						
						if(mp10 != null){
							startWorkHour = Integer.parseInt(mp10.getMP0010_START_TIME().substring(0,mp10.getMP0010_START_TIME().indexOf(":")));
							startWorkMinutes = Integer.parseInt(mp10.getMP0010_START_TIME().substring(mp10.getMP0010_START_TIME().indexOf(":") +1,mp10.getMP0010_START_TIME().length()));
							startWorkTime = startWorkHour + startWorkMinutes/60;
							
							endWorkHour = Integer.parseInt(mp10.getMP0010_END_TIME().substring(0,mp10.getMP0010_END_TIME().indexOf(":")));
							endWorkMinutes = Integer.parseInt(mp10.getMP0010_END_TIME().substring(mp10.getMP0010_END_TIME().indexOf(":") +1,mp10.getMP0010_END_TIME().length()));
							endWorkTime = endWorkHour + endWorkMinutes/60;
						}
						
						// 迟到的情况
						if(startHour > 8 || 8 == startHour && startMinute > 0){
							double lateHour = 0;// 迟到时间
							double lateLeaveHour = getExactLeaveHours(mp2001List,startHour,startMinute,sdf0.format(_dateTime));//请假时间
							
							if(startHour < 13 || 13 == startHour && 0 == startMinute){
								lateHour = startHour - 8 + (startMinute>0?1:0);
							}
							if( 13 == startHour && startMinute > 0){
								lateHour = startHour - 8 + (startMinute>30?1:0);
							}
							if(startHour > 13){
								if(startMinute>30){
									lateHour = startHour - 8 + 1;
								}else if(startMinute <= 30){
									lateHour = startHour - 8;
								}
							}
							
							if(lateLeaveHour >= lateHour){
								altMsg += "Leave";
							}else{
								if(mp10 != null){
									double _outValue = Math.round(startWorkTime-8);
									
									if(lateLeaveHour >= lateHour - _outValue){
										//continue 暂时不做处理
									}else{
										altMsg += "Late";
									}
								}else{
									altMsg += "Late";
								}
							}
						}
						// 早退的情况
						if(finishHour < 16 || 16 == finishHour && finishMinute < 30){
							double earlyHour = 0;// 迟到时间
							double earlyLeaveHour = getExactLeaveHours(mp2001List,finishHour,finishMinute,sdf0.format(_dateTime));//请假时间
							
							if(finishHour <= 13){
								earlyHour = 16 - finishHour;
							}else{
								if(finishMinute < 30){
									earlyHour = 16 - finishHour + 1;
								}else if(finishMinute >= 30){
									earlyHour = 16 - finishHour;
								}
							}
							
							if(!altMsg.trim().equals("")){
								altMsg += "/";
							}
							if(earlyLeaveHour >= earlyHour){
								altMsg += "Leave";
							}else{
								if(mp10 != null){								
									double _outValue = Math.round(16.5-endWorkTime);
									
									if(earlyLeaveHour >= earlyHour - _outValue){
										//continue 暂时不做处理
									}else{
										altMsg += "Early";
									}
								}else{
									altMsg += "Early";
								}
							}
						}
						obj.setMP2003_COMMENT(altMsg);
//						double workHour = 0;
//						if(startHour <= 13 && finishHour > 13){
//							workHour = finishHour - startHour + (finishMinute - startMinute)/60 - 0.5;
//						}else{
//							workHour = finishHour - startHour + (finishMinute - startMinute)/60;
//						}
//						
//						double lateHour = startHour - 8 + (startMinute>0?1:0);
//						double earlyHour = 16 - finishHour - finishMinute/60 + 0.5;
//						double totalHour = workHour + leaveHour;
//						
//						if(totalHour < 8){
//							// 如果当天出勤时间小于8小时，则显示异常；如果有请假，则显示正常。
//							if(startHour > 8 || 8 == startHour && startMinute > 0){
//								if(leaveHour > lateHour){
//									obj.setMP2003_COMMENT("Leave");
//								}else{
//									obj.setMP2003_COMMENT("late");
//								}
//							}
//							if(finishHour < 16 || 16 == finishHour && finishMinute < 30){
//								if(leaveHour > earlyHour){
//									obj.setMP2003_COMMENT("Leave");
//								}else{
//									obj.setMP2003_COMMENT("Early");
//								}
//							}
//							if((startHour > 8 || 8 == startHour && startMinute > 0) && (finishHour < 16 || 16 == finishHour && finishMinute < 30)){
//								//obj.setMP2003_COMMENT("Late/Early");
//								String result = "";
//								if(leaveHour > lateHour){
//									result = "Leave/";
//								}else{
//									result = "Late/";
//								}
//								
//								if(leaveHour > earlyHour){
//									result += "Leave";
//								}else{
//									result += "Early";
//								}
//								obj.setMP2003_COMMENT(result);
//							}
//						    //obj.setMP2003_COMMENT("Abnormal");
//						    obj.setMP2003_STATUS("3");
//						}else{
//							// 如果请假时间为8小时，则不为异常
//							obj.setMP2003_COMMENT("Leave");
//							obj.setMP2003_STATUS("2");
//						}
					}
				}
			}else{
				obj.setMP2003_COMMENT("Abnormal");
				obj.setMP2003_STATUS("1");
			}
			
			//-------------2012-/01/05 Add by Tim 上下班异常
			if(obj.getMP2003_STATUS() != null){
				if(obj.getMP2003_STATUS().equals("3")){
					obj.setMP2003_COMMENT(Constant.IN_MSG);
				}else if(obj.getMP2003_STATUS().equals("4")){
					obj.setMP2003_COMMENT(Constant.OUT_MSG);
				}else if(obj.getMP2003_STATUS().equals("5")){
					obj.setMP2003_COMMENT(Constant.IN_OUT_MSG);
				}
			}else{
				obj.setMP2003_STATUS("2");
			}
			
			// 员工名
			//if(obj.getMP2003_EMPLOYEE_NUM()!=null && !obj.getMP2003_EMPLOYEE_NUM().equals("")){
			//	obj.setMP2003_EMPLOYEE_NAME(employeeData.getMP1001_PREFERED_NAME());
			//}
		}
	}
	
	private double getExactLeaveHours(List<MP2001> mp2001List, double _hour, double _minute, String date) throws ParseException{
		double result = 0;
		double retValue = 0;
		double startHour = 0;
		//double startMinute = 0;
		double finishHour = 0;
		double finishMinute = 0;
		
		SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH");
		SimpleDateFormat sdf2 = new SimpleDateFormat("mm");
		
		for(int i=0,j=mp2001List.size(); i<j; i++){
			MP2001 mp21 = mp2001List.get(i);
			
			String startTime = mp21.getMP2001_FROM_DATETIME();
			String finishTime = mp21.getMP2001_TO_DATETIME();
			
			Date startDate = sdf0.parse(startTime);
			Date finishDate = sdf0.parse(finishTime);
			
			startHour = Double.parseDouble(sdf1.format(startDate));
			//startMinute = Double.parseDouble(sdf2.format(startDate));
			finishHour = Double.parseDouble(sdf1.format(finishDate));
			finishMinute = Double.parseDouble(sdf2.format(finishDate));
			
			//String st = sdf3.format(startDate);
			String ft = sdf3.format(finishDate);
			//----------
			//1.2011-12-5 08:00 ~ 2011-12-5 16:30
			//2.2011-12-4 08:00 ~ 2011-12-5 08:00
			//如果查询2011-12-5的假期时，会把第二中情况的数据也查询出来，为避免这种情况，特加如下处理
			if(ft.equals(date) && 8 == finishHour && 0 == finishMinute ){
				continue;
			}
			
			if(startHour <= _hour && finishHour >= _hour){
				result = Double.parseDouble(mp21.getMP2001_DAYS());
				retValue = retValue + result;
			}
		}
			
		
		return retValue;
	}
	
	// 取得给定日期的请假时间
	private double getLeaveHours(String empNum,String date) throws ParseException{
		double result = 0;
		double retValue = 0;
		//double startHour = 0;
		//double startMinute = 0;
		double finishHour = 0;
		double finishMinute = 0;
		
		SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH");
		SimpleDateFormat sdf2 = new SimpleDateFormat("mm");		
		// 根据员工编码和日期，取得该员工的请假信息列表
		List<MP2001> mp21List = service.findByDate(empNum, date);
		
		// 循环判断请假信息
		for(int i=0,j=mp21List.size(); i<j; i++){
			MP2001 mp21 = mp21List.get(i);
			
			//String startTime = mp21.getMP2001_FROM_DATETIME();
			String finishTime = mp21.getMP2001_TO_DATETIME();
			
			//Date startDate = sdf0.parse(startTime);
			Date finishDate = sdf0.parse(finishTime);
			
			//startHour = Double.parseDouble(sdf1.format(startDate));
			//startMinute = Double.parseDouble(sdf2.format(startDate));
			finishHour = Double.parseDouble(sdf1.format(finishDate));
			finishMinute = Double.parseDouble(sdf2.format(finishDate));
			
			//String st = sdf3.format(startDate);
			String ft = sdf3.format(finishDate);
			//----------
			//1.2011-12-5 08:00 ~ 2011-12-5 16:30
			//2.2011-12-4 08:00 ~ 2011-12-5 08:00
			//如果查询2011-12-5的假期时，会把第二中情况的数据也查询出来，为避免这种情况，特加如下处理
			if(ft.equals(date) && 8 == finishHour && 0 == finishMinute ){
				continue;
			}
			
			//***********************************************************
			//*当天日期：2011-10-05
			//*① 2011-10-04 ~~~~2011-10-06
			//*② 2011-10-04 ~~~~2011-10-05 16:00
			//*③ 2011-10-05 10:30 ~~~~2011-10-05 16:00
			//*④ 2011-10-05 10:30 ~~~~2011-10-06
			//***********************************************************/
			
//			// 如果当天日期位于请假单的起始日期和结束日期的中间，则请假时间肯定超过8小时
//			if(!st.equals(date) && !ft.equals(date)){
//				result = 8;
//				return result;
//			}
//			// 开始日期不等于当天日期，但是结束日期为今天
//			if(!st.equals(date) && ft.equals(date)){
//				result = finishHour - 8 + finishMinute/60;
//				if(finishHour > 13){//减去中午午休半小时
//					result = result - 0.5;
//				}
//			}
//			// 开始日期等于今天，但是结束日期不等于今天
//			if(st.equals(date) && !ft.equals(date)){
//				result = startHour - 8 + startMinute/60;
//				result = 8 - result;
//				if(startHour > 13){
//					result = result + 0.5;
//				}
//			}
//			// 开始日期和结束日期都是当天
//			if(st.equals(date) && ft.equals(date)){
//				if(startHour <= 13 && finishHour > 13){
//					result = finishHour - startHour + (finishMinute - startMinute)/60 - 0.5;
//				}else{
//					result = finishHour - startHour + (finishMinute - startMinute)/60;
//				}
//			}
			
			result = Double.parseDouble(mp21.getMP2001_DAYS());
			//retValue = retValue + result/60;
			retValue = retValue + result;
		}
		
		return retValue;
	}	
	
	// Leave application Report
	@SuppressWarnings("unchecked")
	public String leaveApplyReportInit(){
		try{
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
			
		    // 部门信息在登录后就缓存在Session中了
		    departmentInfoList = UtilCommon.getDepartmentList();
		    commonMonthList = Constant.getMonthInfoList();
		    
			// 取得登陆人信息
			MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		    roleGroup = employeeData.getMP1001_GROUP();
		    
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Leave Application Information Report Init");
			//----------------------------Operation History------------------
		    
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0002005,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_SEARCH)){
				optSearch = "1";
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
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	@SuppressWarnings("unchecked")
	public String leaveApplyReportSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{			
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Leave Application Information");
			//----------------------------Operation History------------------
			
			roleGroup = employeeData.getMP1001_GROUP();
			
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0002005,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_SEARCH)){
				optSearch = "1";
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
			
			if(optAll.equals("0") && optDepartment.equals("0")){
				employeeNum = employeeData.getMP1001_EMPLOYEE_NUM();
				departmentID = employeeData.getMP1001_DEPARTMENT_ID();
			}
			if(optDepartment.equals("1") && optAll.equals("0")){
				departmentID = employeeData.getMP1001_DEPARTMENT_ID();
			}
			
//			if(roleGroup.equals("1")){
//				employeeNum = employeeData.getMP1001_EMPLOYEE_NUM();
//				departmentID = employeeData.getMP1001_DEPARTMENT_ID();
//			}else if(roleGroup.equals("2")){
//				departmentID = employeeData.getMP1001_DEPARTMENT_ID();
//			}		
			if(departmentID == null || departmentID.equals("undefined") || departmentID.equals("-1")){
				departmentID = "";
			}
			
/*			leaveReportList = service.getLeaveApplyReport(employeeNum, departmentID, fromDate, toDate, -1, -1);
			Map<String, MP2001> dataList = new LinkedHashMap<String, MP2001>();
			
			calcReportList(dataList);
			convertReportList(dataList);*/
			
			Map<String, String> propertyMap = new HashMap<String, String>();
			propertyMap.put("EMP_NUM", employeeNum);
			propertyMap.put("DEPARTMENT", departmentID);
			propertyMap.put("YEAR", fromDate);
			propertyMap.put("MONTH", toDate);
			leaveReportList2 = serviceMP2009.findByPropertyByPage(propertyMap, -1, -1);
			
		    // 部门信息在登录后就缓存在Session中了
		    //departmentList = (List<MP0002>) session.get("DEPARTMENT_LIST");
		    departmentInfoList = UtilCommon.getDepartmentList();
		    commonMonthList = Constant.getMonthInfoList();
		    
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}		
	}
	
	private void calcReportList(Map<String, MP2001> dataList) throws ParseException{
		for(int i=0,j=leaveReportList.size(); i < j; i++){
//System.out.println("line:[" + i + "]");
			MP2001 mp21 = leaveReportList.get(i);
			if(dataList.containsKey(mp21.getMP2001_EMPLOYEE_NUM())){
				MP2001 obj = dataList.get(mp21.getMP2001_EMPLOYEE_NUM());
				
				String _leaveType = mp21.getMP2001_LEAVE_TYPE();
				double _days = Double.parseDouble(mp21.getMP2001_DAYS());
				_days = getLeaveDays(mp21.getMP2001_FROM_DATETIME(),mp21.getMP2001_TO_DATETIME(),_days);
				
				if(_leaveType.equals("1")){//Annual
					double _annual = Double.parseDouble(obj.getMP2001_LEAVE_TYPE_ANNUAL()==null?"0":obj.getMP2001_LEAVE_TYPE_ANNUAL()) + _days;
					obj.setMP2001_LEAVE_TYPE_ANNUAL(String.valueOf(_annual));
				}else if(_leaveType.equals("2")){//Sick
					double _sick = Double.parseDouble(obj.getMP2001_LEAVE_TYPE_SICK()==null?"0":obj.getMP2001_LEAVE_TYPE_SICK()) + _days;
					obj.setMP2001_LEAVE_TYPE_SICK(String.valueOf(_sick));
				}else if(_leaveType.equals("3")){//Family
					double _family = Double.parseDouble(obj.getMP2001_LEAVE_TYPE_FAMILY()==null?"0":obj.getMP2001_LEAVE_TYPE_FAMILY()) + _days;
					obj.setMP2001_LEAVE_TYPE_FAMILY(String.valueOf(_family));
				}else if(_leaveType.equals("4")){//Study
					double _study = Double.parseDouble(obj.getMP2001_LEAVE_TYPE_STUDY()==null?"0":obj.getMP2001_LEAVE_TYPE_STUDY()) + _days;
					obj.setMP2001_LEAVE_TYPE_STUDY(String.valueOf(_study));
				}else if(_leaveType.equals("5")){//Maternity
					double _maternity = Double.parseDouble(obj.getMP2001_LEAVE_TYPE_MATERNITY()==null?"0":obj.getMP2001_LEAVE_TYPE_MATERNITY()) + _days;
					obj.setMP2001_LEAVE_TYPE_MATERNITY(String.valueOf(_maternity));
				}else if(_leaveType.equals("6")){//Unpaid
					double _unpaid = Double.parseDouble(obj.getMP2001_LEAVE_TYPE_UNPAID()==null?"0":obj.getMP2001_LEAVE_TYPE_UNPAID()) + _days;
					obj.setMP2001_LEAVE_TYPE_UNPAID(String.valueOf(_unpaid));
				}else if(_leaveType.equals("7")){//Official Business
					double _business = Double.parseDouble(obj.getMP2001_LEAVE_TYPE_OFFICIAL_BUSINESS()==null?"0":obj.getMP2001_LEAVE_TYPE_OFFICIAL_BUSINESS()) + _days;
					obj.setMP2001_LEAVE_TYPE_OFFICIAL_BUSINESS(String.valueOf(_business));
				}
			}else{
				String _leaveType = mp21.getMP2001_LEAVE_TYPE();
				if(_leaveType.equals("1")){//Annual
					mp21.setMP2001_LEAVE_TYPE_ANNUAL(mp21.getMP2001_DAYS());
				}else if(_leaveType.equals("2")){//Sick
					mp21.setMP2001_LEAVE_TYPE_SICK(mp21.getMP2001_DAYS());
				}else if(_leaveType.equals("3")){//Family
					mp21.setMP2001_LEAVE_TYPE_FAMILY(mp21.getMP2001_DAYS());
				}else if(_leaveType.equals("4")){//Study
					mp21.setMP2001_LEAVE_TYPE_STUDY(mp21.getMP2001_DAYS());
				}else if(_leaveType.equals("5")){//Maternity
					mp21.setMP2001_LEAVE_TYPE_MATERNITY(mp21.getMP2001_DAYS());
				}else if(_leaveType.equals("6")){//Unpaid
					mp21.setMP2001_LEAVE_TYPE_UNPAID(mp21.getMP2001_DAYS());
				}else if(_leaveType.equals("7")){//Official Business
					mp21.setMP2001_LEAVE_TYPE_OFFICIAL_BUSINESS(mp21.getMP2001_DAYS());
				}
				
				dataList.put(mp21.getMP2001_EMPLOYEE_NUM(), mp21);
			}// end else
		}// end for
	}
	
	private double getLeaveDays(String _startDate, String _finishDate, double leaveDays) throws ParseException{
		double _leaveDays = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH");
		SimpleDateFormat sdf2 = new SimpleDateFormat("mm");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		Date _start = sdf.parse(_startDate);  //请假开始日
		Date _finish = sdf.parse(_finishDate);//请假结束日
		Date _from = sdf.parse(fromDate);     //检索条件开始日期
		Date _to = sdf.parse(toDate);		  //检索条件结束日期
		
		double _startHour = Double.parseDouble(sdf1.format(sdf3.parse(_startDate)));
		double _startMinu = Double.parseDouble(sdf2.format(sdf3.parse(_startDate)));
		double _finishHour = Double.parseDouble(sdf1.format(sdf3.parse(_finishDate)));
		double _finishMinu = Double.parseDouble(sdf2.format(sdf3.parse(_finishDate)));
		
		Calendar calendarStart = new GregorianCalendar();
		Calendar calendarFinish = new GregorianCalendar();
		Calendar calendarFrom = new GregorianCalendar();
		Calendar calendarTo = new GregorianCalendar();
		
		calendarStart.setTime(_start);   //请假开始日
		calendarFinish.setTime(_finish); //请假结束日
		calendarFrom.setTime(_from);     //检索条件开始日期
		calendarTo.setTime(_to);         //检索条件结束日期
		
		//①、请假开始日<= 开始日期(检索参数) && 请假结束日 >= 开始日期(检索参数)  && 请假结束日 <= 结束日期(检索参数) 
		if(calendarStart.compareTo(calendarFrom) <= 0 && calendarFinish.compareTo(calendarFrom) >= 0 && calendarFinish.compareTo(calendarTo) <= 0){
			_leaveDays = UtilCommon.calDays(_from,_finish);
			if(_finishHour == 8 && _finishMinu == 0){
				_leaveDays = _leaveDays - 8;
			}else if(_finishHour < 13 ){
				//_leaveDays = _leaveDays - 16 + _finishHour + _finishMinu/60;
				_leaveDays = _leaveDays - 16 + _finishHour;
			}else if(_finishHour == 13 && _finishMinu == 0){
				_leaveDays = _leaveDays - 16 + _finishHour;
			}else if(_finishHour == 13 && _finishMinu == 30){
				_leaveDays = _leaveDays - 16 + _finishHour;
			}else if(_finishHour > 13){
				//_leaveDays = _leaveDays + _finishHour + _finishMinu/60 - 16.5;
				_leaveDays = _leaveDays - 16 + _finishHour;
			}
		}
		//②、请假开始日<= 开始日期(检索参数) && 请假结束日 >= 结束日期(检索参数) 
        if(calendarStart.compareTo(calendarFrom) <= 0 && calendarFinish.compareTo(calendarTo) >= 0 ){
        	_leaveDays = UtilCommon.calDays(_from,_to);
        }
		//③、请假开始日>= 开始日期(检索参数) && 请假开始日 <= 结束日期(检索参数) && 请假结束日 >= 结束日期(检索参数) 
		if(calendarStart.compareTo(calendarFrom) >= 0 && calendarStart.compareTo(calendarTo) <= 0 && calendarFinish.compareTo(calendarTo) >= 0){
			_leaveDays = UtilCommon.calDays(_start,_to);
			if(_startHour == 8 && _startMinu == 0){
				//_leaveDays = _leaveDays - 8;
			}else if(_startHour < 13 ){
				//_leaveDays = _leaveDays - 16 + _startHour + _startMinu/60;
				_leaveDays = _leaveDays - _startHour + 8;
			}else if(_startHour == 13 && _startMinu == 0){
				_leaveDays = _leaveDays -  _startHour + 8;
			}else if(_startHour == 13 && _startMinu == 30){
				//_leaveDays = _leaveDays - 16 + _startHour;
				_leaveDays = _leaveDays -  _startHour + 8;
			}else if(_startHour > 13){
				//_leaveDays = _leaveDays + _startHour + _startMinu/60 - 16.5;
				_leaveDays = _leaveDays - _startHour + 8;
			}
			
		}
		//④、请假开始日>= 开始日期(检索参数) && 请假开始日 <= 结束日期(检索参数) && 请假结束日 >= 开始日期(检索参数) && 请假结束日  <= 结束日期(检索参数)
		if(calendarStart.compareTo(calendarFrom) >= 0 && calendarStart.compareTo(calendarTo) <= 0 && calendarFinish.compareTo(calendarFrom) >= 0 && calendarFinish.compareTo(calendarTo) <= 0 ){
			_leaveDays = leaveDays;
		}		
		
		return _leaveDays;
	}
	
	private void convertReportList(Map<String, MP2001> dataList){
		leaveReportList = new ArrayList<MP2001>();
		
		Iterator<Entry<String, MP2001>> iter = dataList.entrySet().iterator();
		while(iter.hasNext()){
			Entry<String, MP2001> entry = (Entry<String, MP2001>)iter.next();
			//String _key = entry.getKey();
			MP2001 _value = entry.getValue();
			
			int _annualDays = new Double(_value.getMP2001_LEAVE_TYPE_ANNUAL()==null?"0":_value.getMP2001_LEAVE_TYPE_ANNUAL()).intValue();
			int _sickDays = new Double(_value.getMP2001_LEAVE_TYPE_SICK()==null?"0":_value.getMP2001_LEAVE_TYPE_SICK()).intValue();
			int _family = new Double(_value.getMP2001_LEAVE_TYPE_FAMILY()==null?"0":_value.getMP2001_LEAVE_TYPE_FAMILY()).intValue();
			int _study = new Double(_value.getMP2001_LEAVE_TYPE_STUDY()==null?"0":_value.getMP2001_LEAVE_TYPE_STUDY()).intValue();
			int _maternity = new Double(_value.getMP2001_LEAVE_TYPE_MATERNITY()==null?"0":_value.getMP2001_LEAVE_TYPE_MATERNITY()).intValue();
			int _unpaid = new Double(_value.getMP2001_LEAVE_TYPE_UNPAID()==null?"0":_value.getMP2001_LEAVE_TYPE_UNPAID()).intValue();
			int _business = new Double(_value.getMP2001_LEAVE_TYPE_OFFICIAL_BUSINESS()==null?"0":_value.getMP2001_LEAVE_TYPE_OFFICIAL_BUSINESS()).intValue();
			
			double _annualDaysD = Double.parseDouble(_value.getMP2001_LEAVE_TYPE_ANNUAL()==null?"0":_value.getMP2001_LEAVE_TYPE_ANNUAL());
			double _sickDaysD = Double.parseDouble(_value.getMP2001_LEAVE_TYPE_SICK()==null?"0":_value.getMP2001_LEAVE_TYPE_SICK());
			double _familyD = Double.parseDouble(_value.getMP2001_LEAVE_TYPE_FAMILY()==null?"0":_value.getMP2001_LEAVE_TYPE_FAMILY());
			double _studyD = Double.parseDouble(_value.getMP2001_LEAVE_TYPE_STUDY()==null?"0":_value.getMP2001_LEAVE_TYPE_STUDY());
			double _maternityD = Double.parseDouble(_value.getMP2001_LEAVE_TYPE_MATERNITY()==null?"0":_value.getMP2001_LEAVE_TYPE_MATERNITY());
			double _unpaidD = Double.parseDouble(_value.getMP2001_LEAVE_TYPE_UNPAID()==null?"0":_value.getMP2001_LEAVE_TYPE_UNPAID());
			double _businessD = Double.parseDouble(_value.getMP2001_LEAVE_TYPE_OFFICIAL_BUSINESS()==null?"0":_value.getMP2001_LEAVE_TYPE_OFFICIAL_BUSINESS());
			
			if(_annualDaysD > 0){
				String _cont1 = "";
				int _day1 = _annualDays/8;
				double _dayDecimal1 = _annualDaysD - _day1*8;
				if(_day1 > 0){
					_cont1 += _day1 + "D ";
				}
				if(_dayDecimal1 > 0){
					_cont1 += String.valueOf(_dayDecimal1) + "H";
				}
				_value.setMP2001_LEAVE_TYPE_ANNUAL(_cont1);
			}
			if(_sickDaysD > 0){
				String _cont2 = "";
				int _day2 = _sickDays/8;
				double _dayDecimal2 = _sickDaysD - _day2*8;
				if(_day2 > 0){
					_cont2 += _day2 + "D ";
				}
				if(_dayDecimal2 > 0){
					_cont2 += _dayDecimal2 + "H";
				}
				_value.setMP2001_LEAVE_TYPE_SICK(_cont2);
			}
			if(_familyD > 0){
				String _cont3 = "";
				int _day3 = _family/8;
				double _dayDecimal3 = _familyD - _day3*8;
				if(_day3 > 0){
					_cont3 += _day3 + "D ";
				}
				if(_dayDecimal3 > 0){
					_cont3 += _dayDecimal3 + "H";
				}
				_value.setMP2001_LEAVE_TYPE_FAMILY(_cont3);
			}
			if(_studyD > 0){
				String _cont4 = "";
				int _day4 = _study/8;
				double _dayDecimal4 = _studyD - _day4*8;
				if(_day4 > 0){
					_cont4 += _day4 + "D ";
				}
				if(_dayDecimal4 > 0){
					_cont4 += _dayDecimal4 + "H";
				}
				_value.setMP2001_LEAVE_TYPE_STUDY(_cont4);
			}
			if(_maternityD > 0){
				String _cont5 = "";
				int _day5 = _maternity/8;
				double _dayDecimal5 = _maternityD - _day5*8;
				if(_day5 > 0){
					_cont5 += _day5 + "D ";
				}
				if(_dayDecimal5 > 0){
					_cont5 += _dayDecimal5 + "H";
				}
				_value.setMP2001_LEAVE_TYPE_MATERNITY(_cont5);
			}
			if(_unpaidD > 0){
				String _cont6 = "";
				int _day6 = _unpaid/8;
				double _dayDecimal6 = _unpaidD - _day6*8;
				if(_day6 > 0){
					_cont6 += _day6 + "D ";
				}
				if(_dayDecimal6 > 0){
					_cont6 += _dayDecimal6 + "H";
				}
				_value.setMP2001_LEAVE_TYPE_UNPAID(_cont6);
			}
			if(_businessD > 0){
				String _cont7 = "";
				int _day7 = _business/8;
				double _dayDecimal7 = _businessD - _day7*8;
				if(_day7 > 0){
					_cont7 += _day7 + "D ";
				}
				if(_dayDecimal7 > 0){
					_cont7 += _dayDecimal7 + "H";
				}
				_value.setMP2001_LEAVE_TYPE_OFFICIAL_BUSINESS(_cont7);
			}
			
			leaveReportList.add(_value);
		}
	}
	
	public String vacationAddInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Vacation Information Page Init");
			//----------------------------Operation History------------------
			
			majorInfoList = serviceMp2004.findByProperty("MP2004_EMPLOYEE_NUM", mp2002EmpNum);
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public String majorInfoAdd(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Major Information");
			//----------------------------Operation History------------------
			
			MP2004 mp24 = new MP2004();
			int _addMajorDays = Integer.parseInt(majorDays)*8;
			
			Calendar calendar = Calendar.getInstance();			
			String gerSeq = String.valueOf(calendar.get(Calendar.YEAR)) + 
					String.format("%02d",calendar.get(Calendar.MONTH) + 1) + 
					String.format("%02d",calendar.get(Calendar.DAY_OF_MONTH)) + 
					String.format("%02d",calendar.get(Calendar.HOUR_OF_DAY)) + 
					String.format("%02d",calendar.get(Calendar.MINUTE)) + 
					String.format("%02d",calendar.get(Calendar.MILLISECOND));
			
			mp24.setMP2004_SEQ(gerSeq);
			mp24.setMP2004_EMPLOYEE_NUM(mp2002EmpNum);
			mp24.setMP2004_MAJOR_NAME(majorName);
			mp24.setMP2004_TIME(String.valueOf(_addMajorDays));
			mp24.setMP2004_STATUS("1");
			serviceMp2004.save(mp24);
			
			MP2002 mp22 = serviceMp2002.findById(mp2002EmpNum);
			
			int _studyDays = Integer.parseInt(mp22.getMP2002_STUDY()) + _addMajorDays;
			int _studyDaysT = Integer.parseInt(mp22.getMP2002_STUDY_T()) + _addMajorDays;
			
			mp22.setMP2002_STUDY(String.valueOf(_studyDays));
			mp22.setMP2002_STUDY_T(String.valueOf(_studyDaysT));
			serviceMp2002.update(mp22);
			
			majorInfoList = serviceMp2004.findByProperty("MP2004_EMPLOYEE_NUM", mp24.getMP2004_EMPLOYEE_NUM());
			
//			StringBuffer sb = new StringBuffer();
//			sb.append("<script type='text/javascript'>");
//			sb.append("window.dialogArguments.document.getElementById('refreshData').click();");
//			//sb.append("window.close();");
//			sb.append("</script>");
//			
//			HttpServletResponse response = ServletActionContext.getResponse();
//			response.setCharacterEncoding("utf-8");
//			PrintWriter out = response.getWriter();
//			out.println(sb.toString());
//	        out.flush();
//	        out.close();
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public String majorInfoDel(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Delete Major Information");
			//----------------------------Operation History------------------
			
			MP2004 mp24 = serviceMp2004.findById(mp2004Seq);
			int _delMajorDays = Integer.parseInt(mp24.getMP2004_TIME());
			serviceMp2004.delete(mp24);
			
			MP2002 mp22 = serviceMp2002.findById(mp2002EmpNum);
			
			int _studyDays = Integer.parseInt(mp22.getMP2002_STUDY()) - _delMajorDays;
			int _studyDaysT = Integer.parseInt(mp22.getMP2002_STUDY_T()) - _delMajorDays;
			
			mp22.setMP2002_STUDY(String.valueOf(_studyDays));
			mp22.setMP2002_STUDY_T(String.valueOf(_studyDaysT));
			serviceMp2002.update(mp22);
			
			majorInfoList = serviceMp2004.findByProperty("MP2004_EMPLOYEE_NUM", mp24.getMP2004_EMPLOYEE_NUM());
			
//			StringBuffer sb = new StringBuffer();
//			sb.append("<script type='text/javascript'>");
//			sb.append("window.dialogArguments.document.getElementById('refreshData').click();");
//			//sb.append("window.close();");
//			sb.append("</script>");
//			
//			HttpServletResponse response = ServletActionContext.getResponse();
//			response.setCharacterEncoding("utf-8");
//			PrintWriter out = response.getWriter();
//			out.println(sb.toString());
//	        out.flush();
//	        out.close();
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public String vacationSave(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Save Vacation");
			//----------------------------Operation History------------------
			
			StringBuffer sb = new StringBuffer();
			sb.append("<script type='text/javascript'>");
			sb.append("window.dialogArguments.document.getElementById('refreshData').click();");
			List<MP2002> dataList = serviceMp2002.findByProperty("MP2002_EMPLOYEE_NUM", mp2002EmpNum);
			if(dataList.size() > 0){
				MP2002 mp22 = dataList.get(0);
				
				int _studyAdd = Integer.parseInt(study)*8; // 增加的天数
				int _maternityDaysAdd = Integer.parseInt(maternityDays)*8; // 增加的天数
				
				int _study = Integer.parseInt(mp22.getMP2002_STUDY()) + _studyAdd;
				int _studyT = Integer.parseInt(mp22.getMP2002_STUDY_T()) + _studyAdd;
				int _maternity = Integer.parseInt(mp22.getMP2002_MATERNITY()) + _maternityDaysAdd;
				int _maternityT = Integer.parseInt(mp22.getMP2002_MATERNITY_T()) + _maternityDaysAdd;
				
				mp22.setMP2002_STUDY(String.valueOf(_study));
				mp22.setMP2002_STUDY_T(String.valueOf(_studyT));
				mp22.setMP2002_MATERNITY(String.valueOf(_maternity));
				mp22.setMP2002_MATERNITY_T(String.valueOf(_maternityT));
				
				serviceMp2002.update(mp22);
			}
			
			sb.append("window.close();");
			sb.append("</script>");
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
	        out.flush();
	        out.close();
	        
			return SUCCESS;
		}catch(Exception ex){
			return "error";
		}
	}
	
	public String vacationMaternityAddInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Vacation Maternity Page Init");
			//----------------------------Operation History------------------
			
			// 权限
			HashMap<String,String> optMap = serviceAC0006.functionOptAccessCheck(employeeData.getMP1001_EMPLOYEE_NUM(), Constant.F0002003,Constant.SYSTEM_NUM_HR);
			if(optMap.containsKey(Constant.OPT_EDIT)){
				optEdit = "1";
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
	
	
	// 清除SESSION
	private void clearSession(Map<String,Object> session){
		if(session.containsKey("SEQ_MAP")){
			session.remove("SEQ_MAP");
		}
		if(session.containsKey("OVERSEAS_ADDRESS_INFO")){
			session.remove("OVERSEAS_ADDRESS_INFO");
		}
	}
	// 临时生成序列号
	@SuppressWarnings("unchecked")
	private int gererateSeq(Map<String,Object> session){
		int intKey = getTempSeq();
		 String Strkey = Integer.toString(intKey);
		 
		if(session.containsKey("SEQ_MAP")){
			Map<String,String> seqMap = (Map<String, String>) session.get("SEQ_MAP");
			while(true){
				if(seqMap.containsKey(Strkey)){
					intKey = getTempSeq();
					Strkey = Integer.toString(intKey);
				}else{
					break;
				}
			}
			return intKey;
		}else{
			return intKey;
		}
	}
	// 生成随机数
	private int getTempSeq(){
		return new Double(Math.ceil(Math.random()*100000)).intValue();
	}
	// 取得出国申请管理页面权
	
	//-----------------个人假期追加申请管理--------------------------
	
	//Delete Leave 	
	public String leaveInfoDel(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get("EMPLOYEE_DATA");
		
		try{
        	//----------------------------Operation History------------------
        	LogUtil logUtil = new LogUtil();
        	logUtil.setServiceMP0011(serviceMP0011);
        	logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Delete Leave Information, KEY:{" + mp2007Seq + "}");
        	//----------------------------Operation History------------------
        	
			if(!mp2007Seq.equals("")){
				int _seq = Integer.parseInt(mp2007Seq);
				MP2007 mp27 = serviceMP2007.findById(_seq);
				serviceMP2007.delete(mp27);
			}
			pageNum = 1;
			getleaveDataList(pageNum);
	        
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
	
	//Approve Leave 
	public String ApproveLeave(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
//		session.put("action", this); //for after advice
		MP1001 employeeData = (MP1001)session.get("EMPLOYEE_DATA");
		
		try{
        	//----------------------------Operation History------------------
        	LogUtil logUtil = new LogUtil();
        	logUtil.setServiceMP0011(serviceMP0011);
        	logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Approve Leave Information, KEY:{" + mp2007Seq + "}");
        	//----------------------------Operation History------------------
        	
			if(!mp2007Seq.equals("")){
				int _seq = Integer.parseInt(mp2007Seq);
				MP2007 mp27 = serviceMP2007.findById(_seq);
				MP2002 mp22 = serviceMp2002.findById(mp27.getMP2007_EMPLOYEE_NUM());
				mp27.setMP2007_STATUS("3");
				mp27.setMP2007_EDIT_DATETIME(UtilDate.get24DateTime());
				mp27.setMP2007_EDIT_USER(employeeData.getMP1001_EMPLOYEE_NUM());
				
				
				int day1 = Integer.parseInt(mp27.getMP2007_DAYS());
				
			    if(mp27.getMP2007_TYPE().equals("1")){
			       int day2 = Integer.parseInt(mp22.getMP2002_ANNUAL());
			       int day3 = Integer.parseInt(mp22.getMP2002_ANNUAL_T());
			       int total = day1 + day2;
			       int total1 = day1 + day3;
			       mp22.setMP2002_ANNUAL(String.valueOf(total));
			       mp22.setMP2002_ANNUAL_T(String.valueOf(total1));
			       
			    }else if(mp27.getMP2007_TYPE().equals("2")){
			       int day2 = Integer.parseInt(mp22.getMP2002_SICK());
			       int day3 = Integer.parseInt(mp22.getMP2002_SICK_T());
			       int total = day1 + day2; 
			       int total1 = day1 + day3;
			       mp22.setMP2002_SICK(String.valueOf(total));
			       mp22.setMP2002_SICK_T(String.valueOf(total1));
			    
			    }else if(mp27.getMP2007_TYPE().equals("3")){
			       int day2 = Integer.parseInt(mp22.getMP2002_FAMILY_RESP());
			       int day3 = Integer.parseInt(mp22.getMP2002_FAMILY_RESP_T());
			       int total = day1 + day2; 
			       int total1 = day1 + day3;
			       mp22.setMP2002_FAMILY_RESP(String.valueOf(total));
			       mp22.setMP2002_FAMILY_RESP_T(String.valueOf(total1));
			    
			    }else if(mp27.getMP2007_TYPE().equals("4")){
			       int day2 = Integer.parseInt(mp22.getMP2002_STUDY());
			       int day3 = Integer.parseInt(mp22.getMP2002_STUDY_T());
			       int total = day1 + day2;
			       int total1 = day1 + day3;
			       mp22.setMP2002_STUDY(String.valueOf(total));
			       mp22.setMP2002_STUDY_T(String.valueOf(total1));
		    		
			       mp2004.setMP2004_SEQ(Constant.getSequence());
			 	   mp2004.setMP2004_EMPLOYEE_NUM(mp27.getMP2007_EMPLOYEE_NUM());
				   mp2004.setMP2004_MAJOR_NAME(mp27.getMP2007_MAJOR_NAME());
				   mp2004.setMP2004_TIME(mp27.getMP2007_DAYS());
				   mp2004.setMP2004_STATUS("1");
		    		
				   serviceMp2004.save(mp2004);
				   
				}else if(mp27.getMP2007_TYPE().equals("5")){
			       int day2 = Integer.parseInt(mp22.getMP2002_MATERNITY());
			       int day3 = Integer.parseInt(mp22.getMP2002_MATERNITY_T());
			       int total = day1 + day2;
			       int total1 = day1 + day3;
			       mp22.setMP2002_MATERNITY(String.valueOf(total));
			       mp22.setMP2002_MATERNITY_T(String.valueOf(total1));
			    }
				serviceMp2002.update(mp22);
				serviceMP2007.update(mp27);
			}
			
			getleaveDataList(pageNum);
			
		}catch(Exception ex){
			log.info(ex.getMessage());
			if(session.containsKey("ERR_MSG")){
				session.remove("ERR_MSG");
				session.put("ERR_MSG", ex.getMessage());
			}
			return "error";
		}
		
		return SUCCESS;
	}
		
	//Decline Leave
	public String DeclineLeave(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get("EMPLOYEE_DATA");
		try{	
        	//----------------------------Operation History------------------
        	LogUtil logUtil = new LogUtil();
        	logUtil.setServiceMP0011(serviceMP0011);
        	logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Decline Leave Information, KEY:{" + mp2007Seq + "}");
        	//----------------------------Operation History------------------
        	
			if(!mp2007Seq.equals("")){
				int _seq = Integer.parseInt(mp2007Seq);
				MP2007 mp27 = serviceMP2007.findById(_seq);
				mp27.setMP2007_STATUS("2");  //deline
				mp27.setMP2007_EDIT_DATETIME(UtilDate.get24DateTime());
				mp27.setMP2007_EDIT_USER(employeeData.getMP1001_EMPLOYEE_NUM());
				serviceMP2007.update(mp27);
			}
			
			getleaveDataList(pageNum);
						
		}catch(Exception ex){
			log.info(ex.getMessage());
			if(session.containsKey("ERR_MSG")){
				session.remove("ERR_MSG");
				session.put("ERR_MSG", ex.getMessage());
			}
			return "error";
		}
		
		return SUCCESS;
	}
		
	//Add and Edit Leave 
	public String leaveAdd(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get("EMPLOYEE_DATA");
		try{
        	//----------------------------Operation History------------------
        	LogUtil logUtil = new LogUtil();
        	logUtil.setServiceMP0011(serviceMP0011);
        	//----------------------------Operation History------------------
        	
			if(pageType.equals("add")){
				mp2007.setMP2007_EMPLOYEE_NUM(employeeData.getMP1001_EMPLOYEE_NUM());
				mp2007.setMP2007_EMPLOYEE_NAME(employeeData.getMP1001_PREFERED_NAME());
				mp2007.setMP2007_TYPE(leaveType);
				int days = 0;
				int hour = 0;
			    if(mp2007.getMP2007_DAYS_EXT_D().equals("")){
			       days = 0; 
			    }
			    else{
			       days = Integer.parseInt(mp2007.getMP2007_DAYS_EXT_D());
			    }
			    if(mp2007.getMP2007_DAYS_EXT_H().equals("")){
			    	hour = 0;
			    }else{
			    	hour = Integer.parseInt(mp2007.getMP2007_DAYS_EXT_H());		
			    }
			    int total = (days*8)+ hour;
			    mp2007.setMP2007_DAYS(Integer.toString(total));
				mp2007.setMP2007_STATUS("1");
/*				if(mp2007.getMP2007_MAJOR_NAME()!=null && !mp2007.getMP2007_MAJOR_NAME().equals("") && leaveType.equals("4")){
				   mp2007.setMP2007_MAJOR_NAME(mp2007.getMP2007_MAJOR_NAME());
				}else{
				   mp2007.setMP2007_MAJOR_NAME(null);
				}*/
				mp2007.setMP2007_CREATE_DATETIME(UtilDate.get24DateTime());
				mp2007.setMP2007_CREATE_USER(employeeData.getMP1001_EMPLOYEE_NUM());
				
				serviceMP2007.save(mp2007);
				
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Leave Information, Leave Number:{" + mp2007.getMP2007_SEQ() + " " + mp2007.getMP2007_EMPLOYEE_NUM() + "}");
			}else if(pageType.equals("edit") && mp2007Seq != null && !mp2007Seq.equals("")){
				
				MP2007 mp27 = serviceMP2007.findById(Integer.parseInt(mp2007Seq));
				int days = 0;
				int hour = 0;
			    if(mp2007.getMP2007_DAYS_EXT_D().equals("")){
			       days = 0; 
			    }
			    else{
			       days = Integer.parseInt(mp2007.getMP2007_DAYS_EXT_D());
			    }
			    if(mp2007.getMP2007_DAYS_EXT_H().equals("")){
			    	hour = 0;
			    }else{
			    	hour = Integer.parseInt(mp2007.getMP2007_DAYS_EXT_H());		
			    }
			    int total = (days*8)+ hour;
			    mp27.setMP2007_DAYS(Integer.toString(total));
				mp27.setMP2007_TYPE(leaveType);
				if(mp27.getMP2007_TYPE().equals("4")){
					mp27.setMP2007_MAJOR_NAME(mp2007.getMP2007_MAJOR_NAME());
				}
				mp27.setMP2007_EDIT_USER(employeeData.getMP1001_EMPLOYEE_NUM());
				mp27.setMP2007_EDIT_DATETIME(UtilDate.get24DateTime());
								
				serviceMP2007.update(mp27);
				logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Edit Leave Information, Leave Number:{" + mp27.getMP2007_SEQ() + " " + mp27.getMP2007_EMPLOYEE_NUM() + "}");
			}
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			
			StringBuffer sb = new StringBuffer();
			sb.append("<script type='text/javascript'>");
			sb.append("window.dialogArguments.document.getElementById('refreshData').click();");
			//sb.append("window.dialogArguments.document.getElementById('1').style.backgroundColor = '#666666';");
			sb.append("window.close();");
			sb.append("</script>");
			
			out.println(sb.toString());
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
	
	//Check Page Type
	public String leaveInfoAddInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);

		try{
        	//----------------------------Operation History------------------
        	LogUtil logUtil = new LogUtil();
        	logUtil.setServiceMP0011(serviceMP0011);
        	logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Leave Information Add Page Init");
        	//----------------------------Operation History------------------
        	
			leaveTypeList = Constant.getLeaveTypeList2();
			employeeNum = employeeData.getMP1001_EMPLOYEE_NUM();
			employeeName = employeeData.getMP1001_PREFERED_NAME();
			
			if(pageType != null && pageType.equals("edit")){
				mp2007 = serviceMP2007.findById(Integer.parseInt(mp2007Seq));
				leaveType = mp2007.getMP2007_TYPE();	
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
	
	//Click on Left Panel to View All
	public String leaveMngInit(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
        	//----------------------------Operation History------------------
        	LogUtil logUtil = new LogUtil();
        	logUtil.setServiceMP0011(serviceMP0011);
        	logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Leave Management Page Init");
        	//----------------------------Operation History------------------
        	
			leaveTypeList = Constant.getLeaveTypeList2();	
			leaveStatusList = Constant.getApprovalTypeList();
			departmentInfoList = UtilCommon.getDepartmentList();
			
			pageNum = 1;
			getleaveDataList(pageNum);
			
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
	
	//Search Leave 
	public String leaveInfoSearch() throws Exception{
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);

		try{
        	//----------------------------Operation History------------------
        	LogUtil logUtil = new LogUtil();
        	logUtil.setServiceMP0011(serviceMP0011);
        	logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Search Leave Information");
        	//----------------------------Operation History------------------
			
			leaveTypeList = Constant.getLeaveTypeList2();	
			leaveStatusList = Constant.getApprovalTypeList();
			departmentInfoList = UtilCommon.getDepartmentList();
		
			pageNum = 1;
			getleaveDataList(pageNum);
			
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
		
	//Refresh Info
	public String leaveInfoRefresh(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		try{
        	//----------------------------Operation History------------------
        	LogUtil logUtil = new LogUtil();
        	logUtil.setServiceMP0011(serviceMP0011);
        	logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Refresh Leave Information");
        	//----------------------------Operation History------------------
        	
			getleaveDataList(pageNum);
		    
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
	
    //Get List of ALL items in Sql Table
	private void getleaveDataList(int _pageNum) throws Exception{
		
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		// 取得操作权限
		getOverseasBusAppMngAuthority(employeeData.getMP1001_EMPLOYEE_NUM(),Constant.F0002009);
		
		if(departmentID == null || departmentID.equals("-1"))departmentID = "";
		if(leaveType == null || leaveType.equals("0"))leaveType = "";
		if(attendenceStatus == null || attendenceStatus.equals("0"))attendenceStatus = "";
		if(employeeNum == null)employeeNum ="";
		if(employeeName == null)employeeName = "";
		
		if(optDepartment.equals("1") && optAll.equals("0")){
			departmentID = employeeData.getMP1001_DEPARTMENT_ID();
		}
		if(optPersonal.equals("1") && optDepartment.equals("0") && optAll.equals("0")){
			employeeNum = employeeData.getMP1001_EMPLOYEE_NUM();
		}
	    			
		Map<String,String> propMap = new HashMap<String,String>();
		propMap.put("EMPLOYEE_NUM", employeeNum);
		propMap.put("EMPLOYEE_NAME", employeeName);
		propMap.put("DEPARTMENT_ID", departmentID);
		propMap.put("TYPE", leaveType);
		propMap.put("STATUS", attendenceStatus);
		
		int rowCount = serviceMP2007.getRowCountByPage(propMap);
		
		if( 0 == rowCount%Constant.PAGE_SIZE){
			pageCount = rowCount/Constant.PAGE_SIZE;
		}else{
			pageCount = rowCount/Constant.PAGE_SIZE + 1;
		}
		if(_pageNum > pageCount){
			_pageNum = pageCount;
		}

		mp2007InfoList = serviceMP2007.findByPropertyByPage(propMap, _pageNum, Constant.PAGE_SIZE);
		
		if(mp2007InfoList != null && mp2007InfoList.size() > 0 ){
			dataConvert2007(mp2007InfoList,employeeData);
		}
	}
		
	private void dataConvert2007(List<MP2007> dataList, MP1001 employeeData) throws ParseException{
	
		int _hour = 0;
		int _day = 0;
		//int _minute = 0;
		StringBuffer _sb = new StringBuffer();
		
		List<MP1001> mp11List = serviceMP1001.findAll();
		Map<String,String> mp11Map = new LinkedHashMap<String,String>();
		for(int i=0,j=mp11List.size(); i<j; i++){
			MP1001 mp11 = mp11List.get(i);
			String preferedName = mp11.getMP1001_PREFERED_NAME();
			mp11Map.put(mp11.getMP1001_EMPLOYEE_NUM(), preferedName);
		}
		
		for(int m=0,n=dataList.size(); m<n; m++){
			MP2007 obj = dataList.get(m);
			int _totalTime = 0;
			if(obj.getMP2007_DAYS() == null || obj.getMP2007_DAYS().equals("")){
				_totalTime = 0;
			}else{
				_totalTime = Integer.parseInt(obj.getMP2007_DAYS());
				_hour = _totalTime%8;
				_day = _totalTime/8;
				_sb = new StringBuffer();
				if(_day != 0 ){
					_sb.append(_day + " D ");
				}
				if(_hour != 0){
					_sb.append(_hour%8 + " H");
				}
							
				obj.setMP2007_DAYS(_sb.toString());
			}
				
			if(obj.getMP2007_TYPE()!=null && !obj.getMP2007_TYPE().equals("") && leaveTypeList.containsKey(obj.getMP2007_TYPE())){
				obj.setMP2007_TYPE_NAME(leaveTypeList.get(obj.getMP2007_TYPE()));
			}
		
		}
	}
	
	// 取得出国申请管理页面权限信息
		private void getOverseasBusAppMngAuthority(String _employeeNum,String functionNum){
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
	//-----------------个人假期追加申请管理--------------------------
	
	//-----------------加班申请单--------------------------	
	// 表(MP2008)新增、编辑页面初始化
	public String mp2008AddInit(){
	    ActionContext context = ActionContext.getContext();
	    Map<String,Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
	    departmentID = employeeData.getMP1001_DEPARTMENT_ID();
	    try{
	        //----------------------------Operation History------------------
	        LogUtil logUtil = new LogUtil();
	        logUtil.setServiceMP0011(serviceMP0011);
	        logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add MP2008 Information Page Init");
	        //----------------------------Operation History------------------
	        // 处理权限--请注意修改相应的权限
	        hourInfoList = Constant.getHourList();
	        workingMinuteList = Constant.getWorkingMinute2();
	        
	        if(pageType != null){
	            if(pageType.equals("edit") || pageType.equals("view")){
	                if(commonSeq != null && !commonSeq.equals("")){
	                    mp2008 = serviceMP2008.findById(commonSeq);

	                    param1 = mp2008.getMP2008_FROM_DATETIME();
	                    param2 = mp2008.getMP2008_TO_DATETIME();
	                    param3 = mp2008.getMP2008_FROM_MINUTE();
	                    param4 = mp2008.getMP2008_TO_MINUTE();
	                    
	                    Map<String,String> departmentMap = UtilCommon.getDepartmentList();
	                    if(departmentMap.containsKey(mp2008.getMP2008_DEPARTMENT())){
	                    	mp2008.setMP2008_DEPARTMENT_NAME(departmentMap.get(mp2008.getMP2008_DEPARTMENT()));
	                    }
	                    
	                	MP2003 mp23 = new MP2003();
	                	mp23.setMP2003_EMPLOYEE_NUM(mp2008.getMP2008_EMPLOYEE_NUM());
	                	mp23.setMP2003_DATETIME(mp2008.getMP2008_DATE());
	                	List<MP2003> mp23List = serviceMp2003.findByPropertys(mp23);
	                	if(mp23List!=null && mp23List.size() >0){
	                		mp23 = mp23List.get(0);
	                		
	                		if(mp23.getMP2003_START_TIME() != null && !mp23.getMP2003_START_TIME().equals("")){
	                			mp2008.setMP2008_CLOCK_IN(mp23.getMP2003_START_TIME().substring(0,16));
	                		}else{
	                			mp2008.setMP2008_CLOCK_IN("-");
	                		}
	                		if(mp23.getMP2003_FINISH_TIME() != null && !mp23.getMP2003_FINISH_TIME().equals("")){
	                			mp2008.setMP2008_CLOCK_OUT(mp23.getMP2003_FINISH_TIME().substring(0,16));
	                		}else{
	                			mp2008.setMP2008_CLOCK_OUT("-");
	                		}
	                	}else{
	                		mp2008.setMP2008_CLOCK_IN("-");
	                		mp2008.setMP2008_CLOCK_OUT("-");
	                	}
	                	
	                	if(pageType.equals("view")){
	                		MP1001 mp11 = null;
	                		MP1010 mp10 = null;
	                		if(!"".equals(mp2008.getMP2008_CREATE_USER()) && mp2008.getMP2008_CREATE_USER() != null){
	                			if(mp2008.getMP2008_CREATE_USER().indexOf("M") >= 0){
			                		mp11 = serviceMP1001.findById(mp2008.getMP2008_CREATE_USER());
			                		mp2008.setMP2008_CREATE_USER_NAME(mp11.getMP1001_PREFERED_NAME());
	                			}else{
	                				mp10 = serviceMP1010.findById(mp2008.getMP2008_CREATE_USER());
	                				mp2008.setMP2008_CREATE_USER_NAME(mp10.getMP1010_PREFERED_NAME());
	                			}
	                		}
	                		if(!"".equals(mp2008.getMP2008_EDIT_USER()) && mp2008.getMP2008_EDIT_USER() != null){
	                			if(mp2008.getMP2008_EDIT_USER().indexOf("M") >= 0){
			                		mp11 = serviceMP1001.findById(mp2008.getMP2008_EDIT_USER());
			                		mp2008.setMP2008_EDIT_USER_NAME(mp11.getMP1001_PREFERED_NAME());
	                			}else{
	                				mp10 = serviceMP1010.findById(mp2008.getMP2008_EDIT_USER());
	                				mp2008.setMP2008_EDIT_USER_NAME(mp10.getMP1010_PREFERED_NAME());
	                			}
	                		}
	                		if(!"".equals(mp2008.getMP2008_APPROVAL_USER()) && mp2008.getMP2008_APPROVAL_USER() != null){		                		
	                			if(mp2008.getMP2008_APPROVAL_USER().indexOf("M") >= 0){
			                		mp11 = serviceMP1001.findById(mp2008.getMP2008_APPROVAL_USER());
			                		mp2008.setMP2008_APPROVAL_USER_NAME(mp11.getMP1001_PREFERED_NAME());
	                			}else{
	                				mp10 = serviceMP1010.findById(mp2008.getMP2008_APPROVAL_USER());
	                				mp2008.setMP2008_APPROVAL_USER_NAME(mp10.getMP1010_PREFERED_NAME());
	                			}
	                		}
	                		// Create
	                		if("".equals(mp2008.getMP2008_CREATE_DATETIME()) || mp2008.getMP2008_CREATE_DATETIME() == null){
	                			mp2008.setMP2008_CREATE_DATETIME("-");
	                		}else{
	                			mp2008.setMP2008_CREATE_DATETIME(mp2008.getMP2008_CREATE_DATETIME().substring(0,16));
	                		}
	                		// Edit
	                		if("".equals(mp2008.getMP2008_EDIT_DATETIME()) || mp2008.getMP2008_EDIT_DATETIME() == null){
	                			mp2008.setMP2008_EDIT_DATETIME("-");
	                		}else{
	                			mp2008.setMP2008_EDIT_DATETIME(mp2008.getMP2008_EDIT_DATETIME().substring(0,16));
	                		}
	                		// Approve
	                		if("".equals(mp2008.getMP2008_APPROVAL_DATETIME()) || mp2008.getMP2008_APPROVAL_DATETIME() == null){
	                			mp2008.setMP2008_APPROVAL_DATETIME("-");
	                		}else{
	                			mp2008.setMP2008_APPROVAL_DATETIME(mp2008.getMP2008_APPROVAL_DATETIME().substring(0,16));
	                		}
	                		
	                		return "view";
	                	}
	                }else{
	                    return "error";
	                }
	            }else if(pageType.equals("add")){
	    	        mp2008.setMP2008_EMPLOYEE_NUM(empNum);
	    	        mp2008.setMP2008_EMPLOYEE_NAME(employeeData.getMP1001_PREFERED_NAME());
	    	        mp2008.setMP2008_DEPARTMENT_NAME(employeeData.getMP1001_DEPARTMENT_NAME());
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
	
	// MP2008管理页面初始化
	public String mp2008InfoMngInit(){
	    ActionContext context = ActionContext.getContext();
	    Map<String,Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
	    departmentID = employeeData.getMP1001_DEPARTMENT_ID();
	    try{
	    	employeeNum = empNum;
	        //----------------------------Operation History------------------
	        LogUtil logUtil = new LogUtil();
	        logUtil.setServiceMP0011(serviceMP0011);
	        logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"MP2008 Information Management Page Init");
	        //----------------------------Operation History------------------
			leaveStatusList = Constant.getApprovalTypeList2();
			departmentInfoList = UtilCommon.getDepartmentList();

	        Map<String,String> propMap = new HashMap<String,String>();
	        getMP2008InfoByPage(propMap, 1,empNum);

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

	// MP2008信息分页处理
	public String mp2008InfoSearch(){
	    ActionContext context = ActionContext.getContext();
	    Map<String,Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
	    departmentID = employeeData.getMP1001_DEPARTMENT_ID();
	    try{
	        //----------------------------Operation History------------------
	        LogUtil logUtil = new LogUtil();
	        logUtil.setServiceMP0011(serviceMP0011);
	        logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME()," SearchMP2008 Information");
	        //----------------------------Operation History------------------

	        Map<String,String> propMap = new HashMap<String,String>();
	        pageNum = 1;
	        getMP2008InfoByPage(propMap, pageNum,empNum);

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

	// MP2008信息分页处理
	public String mp2008InfoPageClick(){
	    ActionContext context = ActionContext.getContext();
	    Map<String,Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
	    departmentID = employeeData.getMP1001_DEPARTMENT_ID();
	    try{
	        //----------------------------Operation History------------------
	        LogUtil logUtil = new LogUtil();
	        logUtil.setServiceMP0011(serviceMP0011);
	        logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"MP2008 Information Page Click");
	        //----------------------------Operation History------------------

	        Map<String,String> propMap = new HashMap<String,String>();
	        getMP2008InfoByPage(propMap,pageNum,empNum);

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

	// MP2008信息删除处理
	public String mp2008InfoDelete(){
	    ActionContext context = ActionContext.getContext();
	    Map<String,Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
	    departmentID = employeeData.getMP1001_DEPARTMENT_ID();
	    try{
	        if (commonSeq != null && !commonSeq.equals(""))
	        {
	            MP2008 _mp2008= serviceMP2008.findById(commonSeq);
	            if (_mp2008 != null)
	            {
	                //不做物理删除，只须更新数据状态时需要用update方法替换delete方法
	            	_mp2008.setMP2008_DATA_STATUS("0");
	                serviceMP2008.update(_mp2008);
	                //----------------------------Operation History------------------
	                LogUtil logUtil = new LogUtil();
	                logUtil.setServiceMP0011(serviceMP0011);
	                logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Delete MP2008 Information");
	                //----------------------------Operation History------------------
	             }
	        }
	        Map<String,String> propMap = new HashMap<String,String>();

	        getMP2008InfoByPage(propMap,pageNum,empNum);

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

	// MP2008信息审核处理
	public String mp2008InfoApprove(){
	    ActionContext context = ActionContext.getContext();
	    Map<String,Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
	    departmentID = employeeData.getMP1001_DEPARTMENT_ID();
	    try{
	    	String[] commonSeqArray = commonSeq.split(",");
			 if (commonSeqArray != null && !commonSeqArray.equals("")){
					for(String comKey : commonSeqArray){
						MP2008 _mp2008= serviceMP2008.findById(comKey);
			            if (_mp2008 != null){
			            	// application status的判断
			            	if(_mp2008.getMP2008_APP_STATUS().equals("3")){
			            		continue;
			            	}
			            	if(_mp2008.getMP2008_APP_STATUS().equals("1")){
			            		_mp2008.setMP2008_APP_STATUS("2");
			            		_mp2008.setMP2008_EDIT_DATETIME(UtilDate.get12DateTime());
			            		_mp2008.setMP2008_EDIT_USER(empNum);
			            	}else if(_mp2008.getMP2008_APP_STATUS().equals("2")){
			            		_mp2008.setMP2008_APP_STATUS("3");
			            		_mp2008.setMP2008_APPROVAL_DATETIME(UtilDate.get12DateTime());
			            		_mp2008.setMP2008_APPROVAL_USER(empNum);
			            	}
				            _mp2008.setMP2008_APPROVAL_DATETIME(UtilDate.get12DateTime());
				            _mp2008.setMP2008_APPROVAL_USER(empNum);
				            
			                serviceMP2008.update(_mp2008);
			                mp2008SendMail(_mp2008, "Overtime application has been approved");
			             }
					}
	                //----------------------------Operation History------------------
	                LogUtil logUtil = new LogUtil();
	                logUtil.setServiceMP0011(serviceMP0011);
	                logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"overtime application has been approved");
	                //----------------------------Operation History------------------
			 }
/*	        if (commonSeq != null && !commonSeq.equals("")){
	            MP2008 _mp2008= serviceMP2008.findById(commonSeq);
	            if (_mp2008 != null){
	            	if(_mp2008.getMP2008_APP_STATUS().equals("1")){
	            		_mp2008.setMP2008_APP_STATUS("2");
	            		_mp2008.setMP2008_EDIT_DATETIME(UtilDate.get12DateTime());
	            		_mp2008.setMP2008_EDIT_USER(empNum);
	            	}else if(_mp2008.getMP2008_APP_STATUS().equals("2")){
	            		_mp2008.setMP2008_APP_STATUS("3");
	            		_mp2008.setMP2008_APPROVAL_DATETIME(UtilDate.get12DateTime());
	            		_mp2008.setMP2008_APPROVAL_USER(empNum);
	            	}
	                serviceMP2008.update(_mp2008);
	                
	                mp2008SendMail(mp2008, "Overtime application has been approved");
					
	                //----------------------------Operation History------------------
	                LogUtil logUtil = new LogUtil();
	                logUtil.setServiceMP0011(serviceMP0011);
	                logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"overtime application has been approved");
	                //----------------------------Operation History------------------
	             }
	        }*/
	        
	        Map<String,String> propMap = new HashMap<String,String>();
	        //propMap.put("KEY", commonSeq);
	        getMP2008InfoByPage(propMap,pageNum,empNum);

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
	
	// MP2008信息审核处理
	public String mp2008InfoDecline(){
	    ActionContext context = ActionContext.getContext();
	    Map<String,Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
	    departmentID = employeeData.getMP1001_DEPARTMENT_ID();
	    try{
	    	String[] commonSeqArray = commonSeq.split(",");	
			 if (commonSeqArray != null && !commonSeqArray.equals("")){
					for(String comKey : commonSeqArray){
						MP2008 _mp2008= serviceMP2008.findById(comKey);
			            if (_mp2008 != null){
			            	_mp2008.setMP2008_APP_STATUS("4");
				            _mp2008.setMP2008_APPROVAL_DATETIME(UtilDate.get12DateTime());
				            _mp2008.setMP2008_APPROVAL_USER(empNum);
				            
			                serviceMP2008.update(_mp2008);
			                mp2008SendMail(_mp2008, "Overtime application has been Declined");
			             }
					}
	                //----------------------------Operation History------------------
	                LogUtil logUtil = new LogUtil();
	                logUtil.setServiceMP0011(serviceMP0011);
	                logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"overtime application has been approved");
	                //----------------------------Operation History------------------
			 }
/*	        if (commonSeq != null && !commonSeq.equals("")){
	            MP2008 _mp2008= serviceMP2008.findById(commonSeq);
	            if (_mp2008 != null){
	                //不做物理删除，只须更新数据状态时需要用update方法替换delete方法
	            	_mp2008.setMP2008_APP_STATUS("4");
	                serviceMP2008.update(_mp2008);
	                //----------------------------Operation History------------------
	                LogUtil logUtil = new LogUtil();
	                logUtil.setServiceMP0011(serviceMP0011);
	                logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Delete MP2008 Information");
	                //----------------------------Operation History------------------
	             }
	            mp2008SendMail(mp2008, "Overtime application has been Declined");
	        }*/
			 
	        Map<String,String> propMap = new HashMap<String,String>();
	        //propMap.put("KEY", commonSeq);
	        getMP2008InfoByPage(propMap,pageNum,empNum);

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
	
	// 表(MP2008)新增、编辑页面初始化
	@SuppressWarnings("unchecked")
	public String mp2008InfoSave(){
	    ActionContext context = ActionContext.getContext();
	    Map<String,Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    Map<String,String> holidayMap = (Map<String,String>)session.get(Constant.HOLIDAY_DATE);
	    String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
	    boolean overTimeFlag = false;
	    departmentID = employeeData.getMP1001_DEPARTMENT_ID();
	    try{
	        //----------------------------Operation History------------------
	        LogUtil logUtil = new LogUtil();
	        logUtil.setServiceMP0011(serviceMP0011);
	        //----------------------------Operation History------------------
/*	        if(checkHoliday(mp2008.getMP2008_DATE(), holidayMap)){
	        	overTimeFlag = true; 
	        }*/
	        overTimeFlag = checkHoliday(mp2008.getMP2008_DATE(), holidayMap);
	        
	        if(pageType.equals("add")){
	        	mp2008.setMP2008_NUM(Constant.getSequence());
	        	mp2008.setMP2008_EMPLOYEE_NUM(empNum);
	        	mp2008.setMP2008_DEPARTMENT(employeeData.getMP1001_DEPARTMENT_ID());
	        	mp2008.setMP2008_FROM_DATETIME(param1);
	        	mp2008.setMP2008_TO_DATETIME(param2);
	        	mp2008.setMP2008_FROM_MINUTE(param3);
	        	mp2008.setMP2008_TO_MINUTE(param4);
	        	mp2008.setMP2008_APP_STATUS("1");
	        	mp2008.setMP2008_DATA_STATUS("1");
	        	mp2008.setMP2008_COMMENT("");
	        	mp2008.setMP2008_CREATE_USER(empNum);
	        	mp2008.setMP2008_CREATE_DATETIME(UtilDate.get12DateTime());
	        	mp2008.setMP2008_RATING(overTimeFlag ? "2X" : "1.5X");

	        	Overtime overHour = overTimeCalcute(param1,param3,param2,param4, overTimeFlag);
	        	mp2008.setMP2008_HOURS(overHour.getHourString());
	        	mp2008.setMP2008_HOURS_TOTAL(overHour.getHourInt());
	        	
	            serviceMP2008.save(mp2008);
	            //addFieldError("errMsg","数据保存成功");
	            // 记录操作日志
	            logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add MP2008 Information");
	            
	            mp2008SendMail(mp2008, "Overtime application");
	            //mp2008 = new MP2008();
	            
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
	            MP2008 _mp2008 = serviceMP2008.findById(commonSeq);
	            _mp2008.setMP2008_DATE(mp2008.getMP2008_DATE());
	            _mp2008.setMP2008_FROM_DATETIME(param1);
	            _mp2008.setMP2008_TO_DATETIME(param2);
	            _mp2008.setMP2008_FROM_MINUTE(param3);
	            _mp2008.setMP2008_TO_MINUTE(param4);
	            _mp2008.setMP2008_REASON(mp2008.getMP2008_REASON());
	            _mp2008.setMP2008_RATING(overTimeFlag?"2X":"1.5X");
	            _mp2008.setMP2008_EDIT_DATETIME(UtilDate.get12DateTime());
	            _mp2008.setMP2008_EDIT_USER(empNum);
	            
	        	Overtime overHour = overTimeCalcute(param1,param3,param2,param4, overTimeFlag);
	        	_mp2008.setMP2008_HOURS(overHour.getHourString());
	        	_mp2008.setMP2008_HOURS_TOTAL(overHour.getHourInt());

	            serviceMP2008.update(_mp2008);
	            // 记录操作日志
	            logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Save MP2008 Information");

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
	
	// 表(MP2008)新增、编辑页面初始化
	public String mp2008InfoSave2(){
	    ActionContext context = ActionContext.getContext();
	    Map<String,Object> session = context.getSession();
	    MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
	    @SuppressWarnings("unchecked")
		Map<String,String> holidayMap = (Map<String,String>)session.get(Constant.HOLIDAY_DATE);
	    String empNum = employeeData.getMP1001_EMPLOYEE_NUM();
	    departmentID = employeeData.getMP1001_DEPARTMENT_ID();
	    boolean overTimeFlag = false;
	    try{
	        //----------------------------Operation History------------------
	        LogUtil logUtil = new LogUtil();
	        logUtil.setServiceMP0011(serviceMP0011);
	        //----------------------------Operation History------------------
	        hourInfoList = Constant.getHourList();
	        workingMinuteList = Constant.getWorkingMinute2();
	        if(mp2008.getMP2008_DATE() != null && !mp2008.getMP2008_DATE().equals("") && checkHoliday(mp2008.getMP2008_DATE(), holidayMap)){
	        	overTimeFlag = true; 
	        }
	        
	        if(pageType.equals("add")){
	        	mp2008.setMP2008_NUM(Constant.getSequence());
	        	mp2008.setMP2008_EMPLOYEE_NUM(empNum);
	        	mp2008.setMP2008_DEPARTMENT(employeeData.getMP1001_DEPARTMENT_ID());
	        	mp2008.setMP2008_FROM_DATETIME(param1);
	        	mp2008.setMP2008_TO_DATETIME(param2);
	        	mp2008.setMP2008_FROM_MINUTE(param3);
	        	mp2008.setMP2008_TO_MINUTE(param4);
	        	mp2008.setMP2008_APP_STATUS("1");
	        	mp2008.setMP2008_DATA_STATUS("1");	        	
	        	mp2008.setMP2008_COMMENT("");
	        	mp2008.setMP2008_CREATE_USER(empNum);
	        	mp2008.setMP2008_CREATE_DATETIME(UtilDate.get12DateTime());
	        	mp2008.setMP2008_RATING(overTimeFlag ? "2X" : "1.5X");
	        	
	        	Overtime overHour = overTimeCalcute(param1,param3,param2,param4, overTimeFlag);
	        	mp2008.setMP2008_HOURS(overHour.getHourString());
	        	mp2008.setMP2008_HOURS_TOTAL(overHour.getHourInt());
	        	
	            serviceMP2008.save(mp2008);
	            // 记录操作日志
	            logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add MP2008 Information");
	            
	            mp2008SendMail(mp2008, "Overtime application");
	            mp2008 = new MP2008();
    	        mp2008.setMP2008_EMPLOYEE_NUM(empNum);
    	        mp2008.setMP2008_EMPLOYEE_NAME(employeeData.getMP1001_PREFERED_NAME());
    	        mp2008.setMP2008_DEPARTMENT_NAME(employeeData.getMP1001_DEPARTMENT_NAME());
	            addFieldError("errMsg","数据保存成功");
	            
	            //request.setAttribute("errMsg", "成功打印");
	            
	            return SUCCESS;
	        }else if(pageType.equals("edit")){
	            MP2008 _mp2008 = serviceMP2008.findById(commonSeq);
	            _mp2008.setMP2008_DATE(mp2008.getMP2008_DATE());
	            _mp2008.setMP2008_FROM_DATETIME(param1);
	            _mp2008.setMP2008_TO_DATETIME(param2);
	            _mp2008.setMP2008_FROM_MINUTE(param3);
	            _mp2008.setMP2008_TO_MINUTE(param4);
	            _mp2008.setMP2008_REASON(mp2008.getMP2008_REASON());
	            _mp2008.setMP2008_RATING(overTimeFlag ? "2X" : "1.5X");
	            _mp2008.setMP2008_EDIT_DATETIME(UtilDate.get12DateTime());
	            _mp2008.setMP2008_EDIT_USER(empNum);

	        	Overtime overHour = overTimeCalcute(param1,param3,param2,param4, overTimeFlag);
	        	_mp2008.setMP2008_HOURS(overHour.getHourString());
	        	_mp2008.setMP2008_HOURS_TOTAL(overHour.getHourInt());
	            
	            serviceMP2008.update(_mp2008);
	            // 记录操作日志
	            logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Save MP2008 Information");
	            mp2008 = new MP2008();
    	        mp2008.setMP2008_EMPLOYEE_NUM(empNum);
    	        mp2008.setMP2008_EMPLOYEE_NAME(employeeData.getMP1001_PREFERED_NAME());
    	        mp2008.setMP2008_DEPARTMENT_NAME(employeeData.getMP1001_DEPARTMENT_NAME());
	            addFieldError("errMsg","数据保存成功");

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
	
	private boolean checkHoliday(String targetDate, Map<String,String> holidayMap) throws ParseException{
		SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
		Date _dateTime = sdf0.parse(targetDate);
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(_dateTime);
		
		int weekDay = calendar1.get(Calendar.DAY_OF_WEEK);
		// 周日：1
		return (1 == weekDay) || holidayMap.containsKey(targetDate);
	}
	// 加班时间计算
	public Overtime overTimeCalcute(String fH, String fM, String tH, String tM, boolean overTimeFlag){
		int _fH = Integer.parseInt(fH);
		int _fM = Integer.parseInt(fM);
		int _tH = Integer.parseInt(tH);
		int _tM = Integer.parseInt(tM);
		int _totalFrom = 0;
		int _totalTo = 0;
		String _totalH = "0";
		Overtime overtime = new Overtime();
		if(_tH < _fH){
			_totalH = "0";
			overtime.setHourInt(0);
			overtime.setHourString("0");
		}else{
			//int _tempM = 60 - _fM + _tM;
			//int _tempH = _tH - _fH + _tempM/60 - 1;
			//int _tempM = (int) (overTimeFlag? (60 - _fM + _tM)*2 : (60 - _fM + _tM)*1.5);
			//int _tempH = (int) (overTimeFlag? (_tH - _fH)*2 : (_tH - _fH)*1.5 + _tempM/60 - 1);
/*			_totalFrom = (int) (overTimeFlag? (_fH*60 + _fM)*2 : (_fH*60 + _fM)*1.5);
			_totalTo =  (int) (overTimeFlag? (_tH*60 + _tM)*2 : (_tH*60 + _tM)*1.5);
			_totalH = String.valueOf((_totalTo - _totalFrom)/60) + "H" + (_totalTo - _totalFrom)%60 + "M";*/
			_totalFrom = _fH*60 + _fM;
			_totalTo = _tH*60 + _tM;
			_totalH = String.valueOf((_totalTo - _totalFrom)/60) + "H" + (_totalTo - _totalFrom)%60 + "M";
			
			overtime.setHourInt(_totalTo - _totalFrom);
			overtime.setHourString(_totalH);
		}
		
		return overtime;
	}
	// 发送邮件
	private void mp2008SendMail(MP2008 mp28, String mailTitle){
		if(mp28.getMP2008_EMPLOYEE_NUM() != null && !mp28.getMP2008_EMPLOYEE_NUM().equals("")){
			MP1001 emp02 = new MP1001();
			if(mp28.getMP2008_EMPLOYEE_NUM().indexOf("M") > 0){
				emp02 = serviceMP1001.findById(mp28.getMP2008_EMPLOYEE_NUM());
			}else{
				emp02.setMP1001_COMPANY_EMAIL("");
				emp02.setMP1001_DEPARTMENT_ID(mp28.getMP2008_DEPARTMENT());
				emp02.setMP1001_PREFERED_NAME("");
				emp02.setMP1001_EMPLOYEE_NUM(mp28.getMP2008_EMPLOYEE_NUM());
			}
			Map<String,MP1001> managerMap = UtilCommon.getAllManager(serviceMP1001);
			
			StringBuffer toList = new StringBuffer();
			StringBuffer result = new StringBuffer();
			
			toList.append(emp02.getMP1001_COMPANY_EMAIL());
			if(managerMap.containsKey(emp02.getMP1001_DEPARTMENT_ID())){
				MP1001 mp11Obj = managerMap.get(emp02.getMP1001_DEPARTMENT_ID());
				toList.append("," + mp11Obj.getMP1001_COMPANY_EMAIL());
				result.append("Dear " + mp11Obj.getMP1001_PREFERED_NAME() + "(" + mp11Obj.getMP1001_EMPLOYEE_NUM() + ")" + ",\r\n \r\n ");
			}else{
				result.append("Dear " + emp02.getMP1001_PREFERED_NAME() + "(" + emp02.getMP1001_EMPLOYEE_NUM() + ")" + ",\r\n \r\n");
			}
			//toList.append("," + managerMap.get("6").getMP1001_COMPANY_EMAIL()); //Shann require to unreceive overtime email @2013/09/18
			
			Mail mail = new Mail();
			mail.setSubject(mailTitle);
			result.append("\r\n\r\n ");
			result.append("Employee Number:" + emp02.getMP1001_EMPLOYEE_NUM() + " " + emp02.getMP1001_PREFERED_NAME() + "\r\n");
			result.append("Date:" + mp28.getMP2008_DATE() + "\r\n");
			result.append("From Time:" + mp28.getMP2008_FROM_DATETIME() + ":" + mp28.getMP2008_FROM_MINUTE()  + "\r\n");
			result.append("To Time:" + mp28.getMP2008_TO_DATETIME()  + ":" + mp28.getMP2008_TO_MINUTE() + "\r\n");
			result.append("Days:" + mp28.getMP2008_HOURS() + "\r\n");
			result.append("Comment:" + mp28.getMP2008_COMMENT() + "\r\n");
			
			result.append("Thank you very much! \r\n \r\n\r\n ");
			result.append("Your Faithfully, \r\n ");
			result.append("HRMS Administrator");
			
			mail.setTo(toList.toString());
			mail.setContent(result.toString());
			mail.send();
		}
	}

	// 数据查询
	private void getMP2008InfoByPage(Map<String,String> propMap, int _pageNum, String empId){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		departmentID = employeeData.getMP1001_DEPARTMENT_ID();
		// 取得操作权限
		getOverseasBusAppMngAuthority(employeeData.getMP1001_EMPLOYEE_NUM(),Constant.F0002010);
		leaveStatusList = Constant.getApprovalTypeList2();
		departmentInfoList = UtilCommon.getDepartmentList();
		
		if(optDepartment.equals("1") && optAll.equals("0")){
			if(empId.toUpperCase().equals("M0076") && param3 != null && (param3.equals("11") || param3.equals("21") || param3.equals("22") || param3.equals("23"))){
				// nothing to do
			}else{
				param3 = employeeData.getMP1001_DEPARTMENT_ID();
			}
		}
		
		if(optPersonal.equals("1") && optDepartment.equals("0") && optAll.equals("0")){
			param1 = employeeData.getMP1001_EMPLOYEE_NUM();
			param2 ="";
		}
		

		
        if(param3==null || param3.equals("-1")){
        	param3 = "";
        }
        if(param4==null || param4.equals("0")){
        	param4 = "";
        }
/*        if(param4.equals("2")){
        	param4 = "4";
        }*/
        
        propMap.put("PARAM1", param1);
        propMap.put("PARAM2", param2);
        propMap.put("PARAM3", param3);
        propMap.put("PARAM4", param4);
        propMap.put("PARAM5", param5);
        propMap.put("PARAM6", param6);
		
	    mp2008InfoList = serviceMP2008.findByPropertyByPage(propMap, -1, -1);
	    if( 0 == mp2008InfoList.size()%Constant.PAGE_SIZE){
	        pageCount = mp2008InfoList.size()/Constant.PAGE_SIZE;
	    }else{
	        pageCount = mp2008InfoList.size()/Constant.PAGE_SIZE + 1;
	    }
	    if(_pageNum > pageCount){
	        _pageNum= pageCount;
	    }
	    mp2008InfoList = serviceMP2008.findByPropertyByPage(propMap, _pageNum, Constant.PAGE_SIZE);
	    
	    for(MP2008 mp28 : mp2008InfoList){
        	MP2003 mp23 = new MP2003();
        	mp23.setMP2003_EMPLOYEE_NUM(mp28.getMP2008_EMPLOYEE_NUM());
        	mp23.setMP2003_DATETIME(mp28.getMP2008_DATE());
        	List<MP2003> mp23List = serviceMp2003.findByPropertys2(mp23);
        	if(mp23List!=null && mp23List.size() >0){
        		mp23 = mp23List.get(0);
        		
        		mp28.setMP2008_CLOCK_IN(mp23.getMP2003_START_TIME() == null || mp23.getMP2003_START_TIME().equals("")?"-":mp23.getMP2003_START_TIME());
        		mp28.setMP2008_CLOCK_OUT(mp23.getMP2003_FINISH_TIME() == null || mp23.getMP2003_FINISH_TIME().equals("")?"-":mp23.getMP2003_FINISH_TIME());
        	}else{
        		mp28.setMP2008_CLOCK_IN("-");
        		mp28.setMP2008_CLOCK_OUT("-");
        	}
        	
        	scoreArray5.add(mp28.getMP2008_NUM());
        	
        	if(mp28.getMP2008_PAYROLL_NUM() == null || mp28.getMP2008_PAYROLL_NUM().equals("")){
        		mp28.setMP2008_PAYROLL_NUM("-");
        	}
        	mp28.setMP2008_EMPLOYEE_2(empId);
	    }
	}
	
    private List<MP2008> getDataForExcel(){
    	Map<String,String> propMap = new HashMap<String, String>();
        if(param1==null || param1.equals("-1")){
        	param1 = "";
        }
        if(param2==null || param2.equals("-1")){
        	param2 = "";
        }
        if(param3==null || param3.equals("-1")){
        	param3 = "";
        }
        if(param4==null || param4.equals("0")){
        	param4 = "";
        }
        if(param4.equals("2")){
        	param4 = "4";
        }
        
        propMap.put("PARAM1", param1);
        propMap.put("PARAM2", param2);
        propMap.put("PARAM3", param3);
        propMap.put("PARAM4", param4);
        propMap.put("PARAM5", param5);
        propMap.put("PARAM6", param6);
        
        mp2008InfoList = serviceMP2008.findByPropertyByPage(propMap, -1, -1);
	    for(MP2008 mp28 : mp2008InfoList){
        	MP2003 mp23 = new MP2003();
        	mp23.setMP2003_EMPLOYEE_NUM(mp28.getMP2008_EMPLOYEE_NUM());
        	mp23.setMP2003_DATETIME(mp28.getMP2008_DATE());
        	List<MP2003> mp23List = serviceMp2003.findByPropertys2(mp23);
        	if(mp23List!=null && mp23List.size() >0){
        		mp23 = mp23List.get(0);
        		
        		mp28.setMP2008_CLOCK_IN(mp23.getMP2003_START_TIME());
        		mp28.setMP2008_CLOCK_OUT(mp23.getMP2003_FINISH_TIME());
        	}
	    }
        
        return mp2008InfoList;
    }
	
	// Excel文档生成
	public String createOvertimeExcel(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Create Overtime Information Excel Document");
			//----------------------------Operation History------------------
			
			// 新建EXCEL工作表
			Workbook wb = ExcelUtil.CreateHSSFWorkBook();
			createExcelSheet1(wb);
			createExcelSheet2(wb);
			
			// 生成Excel文件
	        fileName = "overtimelist.xls";
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
	private void  createExcelSheet1(Workbook wb){
		// 新建一个SHEET页面
		Sheet sheet = ExcelUtil.CreateSheet(wb, "Overtime Application");
		// 设置SHEET页面属性
		ExcelUtil.SetSheetPropertyHSSF(sheet);
		// 获取预定的样式
		Map<String, CellStyle> styles = ExcelUtil.CreateStyles(wb);
		
		// Header标题
		String[] titles = {"ID", "PAYROLL NUMBER", "EMPLOYEE NUMBER",  "NAME", "DEPARTMENT", "DATE","HOURS","RATE","FROM TIME","TO TIME","CLOCK IN","CLOCK OUT","COMMENT","STATUS","APPROVE TIME"};
		// 生成标题行
		float rowHeight = 27f;
		ExcelUtil.CreateHeadRow(sheet, titles, rowHeight, styles);
        // 冻结第一行
        sheet.createFreezePane(0, 1);
        
        int[] cellsWidth = {5,20,25,30,25,15,15,15,15,15,25,25,30,20,30};
        // 设置单元格的宽度
        ExcelUtil.SetCellsWidth(sheet, cellsWidth);
        
      //---------------------------主报表-----------------------------------------------------------------------------------------
        // 取得报表数据
        int count;
        mp2008InfoList = getDataForExcel();
        String[] datas = new String[15];
        for(int i=0,j=mp2008InfoList.size(); i<j; i++){
        	MP2008 mp2008Obj = mp2008InfoList.get(i);
        	count = i + 1;
        	datas = new String[15];
        	datas[0] = String.valueOf(count);
        	datas[1] = mp2008Obj.getMP2008_PAYROLL_NUM();
        	datas[2] = mp2008Obj.getMP2008_EMPLOYEE_NUM();
        	datas[3] = mp2008Obj.getMP2008_EMPLOYEE_NAME();
        	datas[4] = mp2008Obj.getMP2008_DEPARTMENT_NAME();
        	//add @20140211, just change name, for different company, if time is enough, need to add subcompany concept
        	if(datas[4].equalsIgnoreCase("TRANSPORT")){
        		datas[4] = "JAYCEPT";
        	}
        	datas[5] = mp2008Obj.getMP2008_DATE();
        	datas[6] = mp2008Obj.getMP2008_HOURS();
        	datas[7] = mp2008Obj.getMP2008_RATING();
        	datas[8] = mp2008Obj.getMP2008_FROM_DATETIME() + ":" + mp2008Obj.getMP2008_FROM_MINUTE();
        	datas[9] = mp2008Obj.getMP2008_TO_DATETIME() + ":" + mp2008Obj.getMP2008_TO_MINUTE();
        	datas[10] = mp2008Obj.getMP2008_CLOCK_IN();
        	datas[11] = mp2008Obj.getMP2008_CLOCK_OUT();
        	datas[12] = mp2008Obj.getMP2008_COMMENT();
        	datas[13] = mp2008Obj.getMP2008_APP_STATUS_NAME();
        	if(mp2008Obj.getMP2008_APPROVAL_DATETIME() != null && !mp2008Obj.getMP2008_APPROVAL_DATETIME().equals("")){
        		datas[14] = mp2008Obj.getMP2008_APPROVAL_DATETIME().substring(0,16);
        	}else{
        		datas[14] = "-";
        	}
        	
        	
        	ExcelUtil.SetCellsValue(count, sheet, styles, datas);
        }
	}
	private void  createExcelSheet2(Workbook wb){
		// 新建一个SHEET页面
		Sheet sheet = ExcelUtil.CreateSheet(wb, "SUM DATA(" + param5 + "~" + param6 + ")");
		// 设置SHEET页面属性
		ExcelUtil.SetSheetPropertyHSSF(sheet);
		// 获取预定的样式
		Map<String, CellStyle> styles = ExcelUtil.CreateStyles(wb);
		
		// Header标题
		String[] titles = {"ID", "PAYROLL NUMBER", "EMPLOYEE NUMBER",  "NAME", "DEPARTMENT", "STATUS","HOURS","RATE"};
		// 生成标题行
		float rowHeight = 27f;
		ExcelUtil.CreateHeadRow(sheet, titles, rowHeight, styles);
        // 冻结第一行
        sheet.createFreezePane(0, 1);
        
        int[] cellsWidth = {5,20,25,30,25,20,20,15};
        // 设置单元格的宽度
        ExcelUtil.SetCellsWidth(sheet, cellsWidth);
        
      //---------------------------主报表-----------------------------------------------------------------------------------------
        // 取得报表数据
        int count;
        mp2008InfoList = serviceMP2008.getSumDataForReport(param5, param6, param1, param2, param3, param4);
        mp2008InfoList.addAll(serviceMP2008.getSumDataForReport2(param5, param6, param1, param2, param3, param4));
        
        String[] datas = new String[8];
        for(int i=0,j=mp2008InfoList.size(); i<j; i++){
        	MP2008 mp2008Obj = mp2008InfoList.get(i);
        	count = i + 1;
        	datas = new String[8];
        	datas[0] = String.valueOf(count);
        	datas[1] = mp2008Obj.getMP2008_PAYROLL_NUM();
        	datas[2] = mp2008Obj.getMP2008_EMPLOYEE_NUM();
        	datas[3] = mp2008Obj.getMP2008_EMPLOYEE_NAME();
        	datas[4] = mp2008Obj.getMP2008_DEPARTMENT_NAME();
        	//add @20140211, just change name, for different company, if time is enough, need to add subcompany concept
        	if(datas[4].equalsIgnoreCase("TRANSPORT")){
        		datas[4] = "JAYCEPT";
        	}
        	datas[5] = mp2008Obj.getMP2008_APP_STATUS_NAME();
        	datas[6] = mp2008Obj.getMP2008_HOURS_TOTAL()/60 + "H" + mp2008Obj.getMP2008_HOURS_TOTAL()%60 + "M";        	
        	datas[7] = mp2008Obj.getMP2008_RATING();
        	
        	ExcelUtil.SetCellsValue(count, sheet, styles, datas);
        }
	}
	//-----------------加班申请单--------------------------
	
	public String  createLeaveInfoExcelDocument(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Create Employee Leave Information Excel Document");
			//----------------------------Operation History------------------
			
			// 新建EXCEL工作表
			Workbook wb = ExcelUtil.CreateHSSFWorkBook();
			// 新建一个SHEET页面
			Sheet sheet = ExcelUtil.CreateSheet(wb, "Employee Attendance Information");
			// 设置SHEET页面属性
			ExcelUtil.SetSheetPropertyHSSF(sheet);
			// 获取预定的样式
			Map<String, CellStyle> styles = ExcelUtil.CreateStyles(wb);
			
			// Header标题
			String[] titles = {"ID", "EMPLOYEE NUMBER", "NAME", "DEPARTMENT", "Annual","Sick","Family Resp","Study","Maternity","Unpaid","Official Business"};
			// 生成标题行
			float rowHeight = 27f;
			ExcelUtil.CreateHeadRow(sheet, titles, rowHeight, styles);
	        // 冻结第一行
	        sheet.createFreezePane(0, 1);
	        
	        int[] cellsWidth = {5,20,15,10,10,10,10,10,10,10,15};
	        // 设置单元格的宽度
	        ExcelUtil.SetCellsWidth(sheet, cellsWidth);
	        
	      //---------------------------主报表-----------------------------------------------------------------------------------------
	        // 取得报表数据
	        if(employeeNum == null || employeeNum.equals("undefined")){
	        	employeeNum = "";
	        }
	        if(departmentID == null || departmentID.equals("undefined") || departmentID.equals("-1")){
	        	departmentID = "";
	        }			
			Map<String, String> propertyMap = new HashMap<String, String>();
			propertyMap.put("EMP_NUM", employeeNum);
			propertyMap.put("DEPARTMENT", departmentID);
			propertyMap.put("YEAR", fromDate);
			propertyMap.put("MONTH", toDate);
			leaveReportList2 = serviceMP2009.findByPropertyByPage(propertyMap, -1, -1);
			
			
	        int count;
	        String[] datas = new String[11];
	        for(int i=0,j=leaveReportList2.size(); i<j; i++){
	        	MP2009 mp2009Obj = leaveReportList2.get(i);
	        	count = i + 1;
	        	datas = new String[11];
	        	datas[0] = String.valueOf(count);
	        	datas[1] = mp2009Obj.getMP2009_EMPLOYEE_NUM();
	        	datas[2] = mp2009Obj.getMP2009_EMPLOYEE_NAME();
	        	datas[3] = mp2009Obj.getMP2009_DEPARTMENT();
	        	datas[4] = mp2009Obj.getMP2009_ANNUAL_EXT();
	        	datas[5] = mp2009Obj.getMP2009_SICK_EXT();
	        	datas[6] = mp2009Obj.getMP2009_FAMILY_RESP_EXT();
	        	datas[7] = mp2009Obj.getMP2009_STUDY_EXT();
	        	datas[8] = mp2009Obj.getMP2009_MATERNITY_EXT();
	        	datas[9] = mp2009Obj.getMP2009_UNPAID_EXT();
	        	datas[10] = mp2009Obj.getMP2009_OFFICIAL_BUSINESS_EXT();
	        	
	        	ExcelUtil.SetCellsValue(count, sheet, styles, datas);
	        }
	        
			// 生成Excel文件
	        fileName = "Report.xls";
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
	
	//-------shift work----------
	public String shiftWorkMngInit(){
		try{
			branchSiteList = Constant.getBranchSiteList();
			actingType = "shiftwork";
			
			Map<String, String> columanNameMap = new HashMap<String, String>();
			columanNameMap.put("PAGE_NUM", "" + currentPageNum);
			columanNameMap.put("PAGE_COUNT", PER_PAGE_NUM);
			pageBean = serviceMP2010.queryForPage(columanNameMap);
			shiftworkScheduleList = pageBean.getList();
			
			for(MP2010 tmpRcd : shiftworkScheduleList){
				MP1001 mp11 = serviceMP1001.findById(tmpRcd.getMP2010_EMPLOYEE_NUM());	// 
				tmpRcd.setEmployeeInfo(mp11);
			}
			
			
			//
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public String shiftWorkSearch(){
		try{
			System.out.println("in shiftWordSearch function");
			//process employee name
			
			//shiftworkScheduleList = serviceMP2010.findAll();
			Map<String, String> columanNameMap = new HashMap<String, String>();
			if(null != employeeNum && !employeeNum.equalsIgnoreCase("")){
				columanNameMap.put("MP2010_EMPLOYEE_NUM", employeeNum);
			}
			if(null != fromDate && !fromDate.equalsIgnoreCase("")){
				columanNameMap.put("MP2010_FROM_DATETIME", fromDate);
			}
			if(null != toDate && !toDate.equalsIgnoreCase("")){
				columanNameMap.put("MP2010_END_DATETIME", toDate);
			}
			//columanNameMap.put("MP2010_TYPE", shiftWorkType);
			if(null != branchSiteId && !branchSiteId.equalsIgnoreCase("") && !branchSiteId.equalsIgnoreCase("---Please Select---")){
				columanNameMap.put("MP2010_BRANCH_SITE", branchSiteId);
			}
			columanNameMap.put("PAGE_NUM", "" + currentPageNum);
			columanNameMap.put("PAGE_COUNT", PER_PAGE_NUM);
			pageBean = serviceMP2010.queryForPage(columanNameMap);
			//shiftworkScheduleList = serviceMP2010.findByColumnName(columanNameMap);
			shiftworkScheduleList = pageBean.getList();
			branchSiteList = Constant.getBranchSiteList();
			
			for(MP2010 tmpRcd : shiftworkScheduleList){
				MP1001 mp11 = serviceMP1001.findById(tmpRcd.getMP2010_EMPLOYEE_NUM());
				tmpRcd.setEmployeeInfo(mp11);
			}
			
			
			return SUCCESS;
		}
		catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public void validateShiftWorkMngInit(){
		System.out.println("in validateShiftWorkMngInit() function");
	}
	
	public String exportShiftWorkExcelTemplate(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Create Shiftwork Excel Template Document");
			//----------------------------Operation History------------------
			
			// 新建EXCEL工作表
			Workbook wb = ExcelUtil.CreateHSSFWorkBook();
			//createExcelSheet1(wb);
			//createExcelSheet2(wb);
			createShiftWorkExcelTemplate(wb);
			
			// 生成Excel文件
	        fileName = "shiftWorkExcelTemplate.xls";
	        String _path = ServletActionContext.getServletContext().getRealPath("/") + "uploadfile\\" + fileName;
	        ExcelUtil.createExcelFile(_path, wb);

	        //analyseExcelTemplete();
	        
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
	
	public String importShiftworkScheduleExcelInit(){
		
		return SUCCESS;
	}
	
	public String uploadExcelFile(){
		//save upload file
		try{
			String _path = ServletActionContext.getServletContext().getRealPath("/") + "uploadFile\\" + "shiftWorkExcelTemplate.xls";
			File destFile = new File(_path);
			FileUtils.copyFile(shiftworkExcel, destFile);
			
			excelRecordsList = new ArrayList<ShiftworkExcelRecordDto>();
			
			
			
			analyseExcelTemplete();
			convertToMP2010Records();
			//shiftworkExcel = 
			
			HttpServletResponse response = ServletActionContext.getResponse();
	        response.setCharacterEncoding("utf-8");
	        PrintWriter out = response.getWriter();
	        out.append("S:success");
	        //out.println("S:success");
	        out.flush();
	        out.close();
			
			return NONE;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public String addShiftWorkLeaveInit(){
		//initialize leave part
		ActionContext ct = ActionContext.getContext();
		Map<String,Object> session = ct.getSession();

		//leaveTypeList
    	//listInit();
		shiftworkListinit();
		
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		
		mp2001.setMP2001_ACTING_APPLICATION_PERSON(employeeData.getMP1001_EMPLOYEE_NUM());
    	mp2001.setMP2001_ACTING_APPLICATION_PERSON_NAME(employeeData.getMP1001_PREFERED_NAME());
    	//actingType = "shiftwork";
    	actingType = "self";
    	
    	// 个人假期剩余天数
	    MP2002 mp2002 = serviceMp2002.findById(employeeData.getMP1001_EMPLOYEE_NUM());
	    if(mp2002 != null){
	    	annualDays = mp2002.getMP2002_ANNUAL();
	    	sickDays = mp2002.getMP2002_SICK();
	    	familyDays = mp2002.getMP2002_FAMILY_RESP();
	    	maternityDays = mp2002.getMP2002_MATERNITY();
	    	study = mp2002.getMP2002_STUDY();
	    }

		return SUCCESS;
	}
	
	
	public String analyseExcelTemplete(){
		System.out.println("in analyseExcelTemplete()");
		try{
			fileName = "shiftWorkExcelTemplate.xls";
	        String _path = ServletActionContext.getServletContext().getRealPath("/") + "uploadfile\\" + fileName;
			//String _path = "d:\\shiftWorkExcelTemplate.xls";
	        
	        System.out.println(_path);

	        Workbook wb = ExcelUtil.openExcelFile(_path);
	        
	        GetCellContent(wb);
	        
	        /*
		    Sheet sheet = wb.getSheetAt(0);
		    Row row = sheet.getRow(2);
		    Cell cell = row.getCell(3);
		    cell.getCellFormula();
		    if (cell == null)
		        cell = row.createCell(3);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    cell.setCellValue("a test");
		
		    // Write the output to a file
		    FileOutputStream fileOut = new FileOutputStream("workbook.xls");
		    wb.write(fileOut);
		    fileOut.close();
	        */
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public void convertToMP2010Records() throws Exception{
			JE0101 je11 = serviceJE0101.findByKey("shiftworkMonth");
			String workDateMonth = je11.getJE0101_VALUE().substring(5, 7); //which month
			serviceJE0101.delete(je11); //delete it
			
			Date d = new Date();
			SimpleDateFormat yearSDF = new SimpleDateFormat("yyyy");
			String thisYear = yearSDF.format(d);
			
			Calendar cal = Calendar.getInstance();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse("2014-04-01");
			cal.setTime(date);
			
			for(ShiftworkExcelRecordDto recordDto : excelRecordsList){
				/**
				 * get all days here
				 */
				int size = recordDto.getArrangementMap().size();
				for(int i = 1; i <= size;i++){
					MP2010 mp21 = new MP2010();
					mp21.setMP2010_BRANCH_SITE(recordDto.getBranchSite());
					mp21.setMP2010_EMPLOYEE_NUM(recordDto.getEmployeeNum());
					mp21.setMP2010_DATE(thisYear + "-" + workDateMonth + "-" + String.format("%02d", i));
					
					String shiftType = recordDto.getArrangementMap().get(i);
					mp21.setMP2010_TYPE(shiftType);
					
					if(shiftType.equalsIgnoreCase("N")){
						mp21.setMP2010_FROM_DATETIME(thisYear + "-" + workDateMonth + "-" + String.format("%02d", i) + " " + Constant.shiftWorkNightStartTime);
						//UtilDate.get12DateTime();
						Date nextDate = sdf.parse(thisYear + "-" + workDateMonth + "-" + String.format("%02d", i));
						nextDate = UtilDate.afterNDay(nextDate, 1);
						System.out.println(sdf.format(nextDate));
						mp21.setMP2010_END_DATETIME(sdf.format(nextDate) + " " + Constant.shiftWorkNightEndTime);
					}
					if(shiftType.equalsIgnoreCase("D")){
						mp21.setMP2010_FROM_DATETIME(thisYear + "-" + workDateMonth + "-" + String.format("%02d", i) + " " + Constant.shiftWorkDayStartTime);
						mp21.setMP2010_END_DATETIME(thisYear + "-" + workDateMonth + "-" + String.format("%02d", i) + " " + Constant.shiftWorkDayEndTime);
					}
					/*if(recordDto.getArrangementMap().get(i).equalsIgnoreCase("R")){ 
					  //if that day is rest, then insert one empty record(no from and end time) into that table
						
					}*/
					serviceMP2010.save(mp21);
				}
			}
//			System.out.println("in method : convertToMP2010Records()");
	}
	
	public String hibernateTest(){
		try{
			MP2010 mp21 = new MP2010();
			mp21.setMP2010_BRANCH_SITE("head office");
			mp21.setMP2010_DATE("2014-04-02");
			mp21.setMP2010_END_DATETIME("2014-04-03 06:00:00");
			mp21.setMP2010_FROM_DATETIME("2014-04-02 18:00:00");
			//mp21.setMP2010_ID(mP2010_ID);
			mp21.setMP2010_TYPE("N");
			MP1001 mp11 = new MP1001();
			mp11.setMP1001_EMPLOYEE_NUM("M0432");
			//mp21.setShiftEmployee(mp11);
			
			serviceMP2010.save(mp21);

			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}

	public String hibernateLoadTest(){
		try{
			MP2010 mp21 = serviceMP2010.findByKey("1");
			
			String _path = ServletActionContext.getServletContext().getRealPath("\\uploadfile");
			
			System.out.println("real path: " + _path);
			//System.out.println("employee name: " + mp21.getShiftEmployee().getMP1001_PREFERED_NAME());
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
		
	public void createShiftWorkExcelTemplate(Workbook wb){
		// 新建一个SHEET页面
		Sheet sheet = ExcelUtil.CreateSheet(wb, "Overtime Application");
		// 设置SHEET页面属性
		ExcelUtil.SetSheetPropertyHSSF(sheet);
		// 获取预定的样式
		Map<String, CellStyle> styles = ExcelUtil.CreateStyles(wb);

		//get next month template 
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int month = cal.get(Calendar.MONTH) + 1; //next month information
		month -= 1; //for test this month
		cal.set(Calendar.MONTH, month);
		int next_month = month + 1;  //next next month
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//save it into system dictionary
		JE0101 je11 = new JE0101();
		je11.setJE0101_PROPERTY("shiftworkMonth");
		serviceJE0101.delete(je11);
		je11.setJE0101_VALUE(new SimpleDateFormat("yyyy-MM").format(cal.getTime()));
		je11.setJE0101_TYPE("shiftwork");
		serviceJE0101.save(je11);
		
		List<String> headNameList = new ArrayList<String>();
		headNameList.add("BRANCH_SITE");
		headNameList.add("EMPLOYEE_NMAE");
		headNameList.add("EMPLOYEE_NUM");
		List<Integer> headCellWidth = new ArrayList<Integer>();
		headCellWidth.add(20);
		headCellWidth.add(20);
		headCellWidth.add(20);

		while(month != next_month){
			headNameList.add(sdf.format(cal.getTime()));
			headCellWidth.add(15);
			
			cal.add(Calendar.DAY_OF_MONTH, 1);
			month = cal.get(Calendar.MONTH);
		}
		// Header标题
		String[] titles = new String[headNameList.size()];
		int i = 0;
		for(String s : headNameList){
			titles[i] = s;
			i++;
		}
				
		// 生成标题行
		float rowHeight = 27f;
		ExcelUtil.CreateHeadRow(sheet, titles, rowHeight, styles);
        // 冻结第一行
        sheet.createFreezePane(0, 1);
        
        Integer[] cellsWidth = new Integer[headCellWidth.size()];
        i = 0;
        for(Integer it : headCellWidth){
        	cellsWidth[i] = it;
        	i++;
        }
        
        // 设置单元格的宽度
        ExcelUtil.SetCellsWidth(sheet, cellsWidth); //static binding
        
      //---------------------------主报表-----------------------------------------------------------------------------------------
        // 取得报表数据
        /*int count;
        mp2008InfoList = getDataForExcel();
        String[] datas = new String[15];
        for(int i=0,j=mp2008InfoList.size(); i<j; i++){
        	MP2008 mp2008Obj = mp2008InfoList.get(i);
        	count = i + 1;
        	datas = new String[15];
        	datas[0] = String.valueOf(count);
        	datas[1] = mp2008Obj.getMP2008_PAYROLL_NUM();
        	datas[2] = mp2008Obj.getMP2008_EMPLOYEE_NUM();
        	datas[3] = mp2008Obj.getMP2008_EMPLOYEE_NAME();
        	datas[4] = mp2008Obj.getMP2008_DEPARTMENT_NAME();
        	//add @20140211, just change name, for different company, if time is enough, need to add subcompany concept
        	if(datas[4].equalsIgnoreCase("TRANSPORT")){
        		datas[4] = "JAYCEPT";
        	}
        	datas[5] = mp2008Obj.getMP2008_DATE();
        	datas[6] = mp2008Obj.getMP2008_HOURS();
        	datas[7] = mp2008Obj.getMP2008_RATING();
        	datas[8] = mp2008Obj.getMP2008_FROM_DATETIME() + ":" + mp2008Obj.getMP2008_FROM_MINUTE();
        	datas[9] = mp2008Obj.getMP2008_TO_DATETIME() + ":" + mp2008Obj.getMP2008_TO_MINUTE();
        	datas[10] = mp2008Obj.getMP2008_CLOCK_IN();
        	datas[11] = mp2008Obj.getMP2008_CLOCK_OUT();
        	datas[12] = mp2008Obj.getMP2008_COMMENT();
        	datas[13] = mp2008Obj.getMP2008_APP_STATUS_NAME();
        	if(mp2008Obj.getMP2008_APPROVAL_DATETIME() != null && !mp2008Obj.getMP2008_APPROVAL_DATETIME().equals("")){
        		datas[14] = mp2008Obj.getMP2008_APPROVAL_DATETIME().substring(0,16);
        	}else{
        		datas[14] = "-";
        	}
        	
        	
        	ExcelUtil.SetCellsValue(count, sheet, styles, datas);
        }*/

	}
	
	public void GetCellContent(Workbook wb){
		Sheet sheet1 = wb.getSheetAt(0);
		//sheet1.iterator().next(); //skip header line
    	System.out.println("sheet1 " + sheet1.getLastRowNum());
    	int cellCount = 0;
    	ShiftworkExcelRecordDto recordDto = null;
    	Map<Integer, String> arrangeMap = null;
    	boolean firstFlag = false;
	    for (Row row : sheet1) {
	    	cellCount = 0;
	    	recordDto = new ShiftworkExcelRecordDto();
	    	arrangeMap = new HashMap<Integer, String>();
	    	
	       innerLoop: for (Cell cell : row) {
	            CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
	            System.out.print(cellRef.formatAsString());
	            System.out.print(" - ");

	            switch (cell.getCellType()) {
	                case Cell.CELL_TYPE_STRING:
	                	String cellString = cell.getRichStringCellValue().getString();
	                	cellCount++;
	                	//skip head row
	                	if(cellString.equalsIgnoreCase("BRANCH_SITE")){
	                		firstFlag = true;
	                		break innerLoop;
	                	}
	                	
	                	if(1 == cellCount){
	                		recordDto.setBranchSite(cellString);
	                		break ;
	                	}
	                	if(2 == cellCount){
	                		break ;
	                	}
	                	if(3 == cellCount){
	                		recordDto.setEmployeeNum(cellString);
	                		break ;
	                	}
	                	
	                	arrangeMap.put(cellCount - 3, cellString);
	                	
	                    break;
	                case Cell.CELL_TYPE_NUMERIC:
	                    if (DateUtil.isCellDateFormatted(cell)) {
	                        System.out.println(cell.getDateCellValue());
	                    } else {
	                        System.out.println(cell.getNumericCellValue());
	                    }
	                    break;
	                case Cell.CELL_TYPE_BOOLEAN:
	                    System.out.println(cell.getBooleanCellValue());
	                    break;
	                case Cell.CELL_TYPE_FORMULA:
	                    System.out.println(cell.getCellFormula());
	                    break;
	                default:
	                    System.out.println();
	            }
	        }
	    	if(true == firstFlag){
	    		firstFlag = false;
	    		continue ;
	    	}
	    	recordDto.setArrangementMap(arrangeMap);
	    	excelRecordsList.add(recordDto);
	    }
	}
	
	public String shiftworkAddLeaveApply(){
		/*
		try{
			HttpServletResponse resp = ServletActionContext.getResponse();
			PrintWriter out = resp.getWriter();
			
			StringBuffer sb = new StringBuffer();

			sb.append("<script type='text/javascript'>");
			sb.append("alert('From date and to date should be the same day! Please select again!');");
			sb.append("window.location.href='addShiftWorkLeaveInit.action?type=add'");
			//sb.append("window.close();");
			sb.append("</script>");

			out.append(sb.toString());
			out.flush();
			out.close();
			
			return NONE;
		}
		catch(Exception e){
			log.info(e.getMessage());
			return ERROR;
		}*/
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		// 取得登陆人信息
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		listInit();
		
		try{
		    String _empNo = (employeeData.getMP1001_EMPLOYEE_NUM()).toUpperCase();//强制转换字母为大写字母
		    
			if(actingType.equals("self")){
				mp2001.setMP2001_EMPLOYEE_NUM(_empNo);
			}else{
				String _tempNum = mp2001.getMP2001_EMPLOYEE_NUM().toUpperCase();//强制转换字母为大写
				mp2001.setMP2001_EMPLOYEE_NUM(_tempNum);
			}
		    
			if(null != dayTypeChoose && dayTypeChoose.equalsIgnoreCase("singleDay")){
				//mp2001.MP2001_TO_DATETIME = mp2001.MP2001_FROM_DATETIME;
				mp2001.setMP2001_TO_DATETIME(mp2001.getMP2001_FROM_DATETIME());
			}
			if(null != dayTypeChoose && dayTypeChoose.equalsIgnoreCase("multiDays")){
				workingHours1 = "0";
				workingMinute1 = "0";
				workingHours2 = "0";
				workingMinute2 = "0";
			}
			
			String _from = mp2001.getMP2001_FROM_DATETIME() + " " + String.format("%02d", Integer.parseInt(workingHours1)) + ":" + String.format("%02d", Integer.parseInt(workingMinute1))+":00";
			String _to = mp2001.getMP2001_TO_DATETIME() + " " + String.format("%02d", Integer.parseInt(workingHours2)) + ":" + String.format("%02d", Integer.parseInt(workingMinute2))+":00";
			String _id = "";
			
			if(actingType.equals("self")){
				_id = (employeeData.getMP1001_EMPLOYEE_NUM()).toUpperCase();
			}else{
				String _tempNum = mp2001.getMP2001_EMPLOYEE_NUM().toUpperCase();//强制转换字母为大写
				mp2001.setMP2001_EMPLOYEE_NUM(_tempNum);
			}
			log.info("ID:" + _id);
			// 判断请假时间是否有重复
			boolean checkValue = service.checkLeaveDay(_from, _to, mp2001.getMP2001_EMPLOYEE_NUM());
			// 判断是否跨年
			boolean checkValue2 = false;
			//年假、病假、家庭假不能跨年
			if(mp2001.getMP2001_LEAVE_TYPE().equals("1") || mp2001.getMP2001_LEAVE_TYPE().equals("2") || mp2001.getMP2001_LEAVE_TYPE().equals("3")){
				checkValue2 = checkLeaveTime();
			}
			
			if(true == checkValue){
				StringBuffer sb = new StringBuffer();

				sb.append("<script type='text/javascript'>");
				sb.append("alert('请假时间有重复，请重新填写起始时间。');");
				sb.append("window.location.href='applyLeaveInit.action?type=add&MP2001_NUM='");
				//sb.append("window.close();");
				sb.append("</script>");
			    
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html;charset=UTF-8");
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				out.println(sb.toString());
		        out.flush();
		        out.close();
		        
				//addActionMessage("<script>alert('保存失败，因为请假日期重复，请重新填写起始日期.');</script>");
				return null;
			}else if(true == checkValue2){
				StringBuffer sb = new StringBuffer();

				sb.append("<script type='text/javascript'>");
				sb.append("alert('不能跨年请假。');");
				sb.append("window.location.href='applyLeaveInit.action?type=add&MP2001_NUM='");
				//sb.append("window.close();");
				sb.append("</script>");
			    
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html;charset=UTF-8");
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				out.println(sb.toString());
		        out.flush();
		        out.close();

				return null;
			}
			else{				
				// 生成请假单编号
				// 取得部门信息
				int departmentId = Integer.parseInt(employeeData.getMP1001_DEPARTMENT_ID());
				MP0002 mp02 = serviceMp0002.findById(departmentId);
				
				//if(type.equals("add")){
					// 取得请假单的当前编号
					List<MP0006> mp06List = serviceMP0006.findByProperty("MP0006_CODE", "LEAVE_NUM");
				    MP0006 mp6 = mp06List.get(0);
				    int maxNum = Integer.parseInt(mp6.getMP0006_VALUE()) + 1;
				    java.text.DecimalFormat format = new java.text.DecimalFormat("00");
				    mp6.setMP0006_VALUE(String.valueOf(maxNum));
				    
					Date now = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String addtime = sdf.format(now);
					String addtime1 = sdf1.format(now);
				    
					//----------------------------------MP2001---------------------------------------
				    // 请假单编号：日月年+部门编码+两位流水号
				    String leaveNum = addtime + mp02.getMP0002_DEPARTMENT_NUM() + format.format(maxNum);
					
					mp2001.setMP2001_NUM(leaveNum);
					//mp2001.setMP2001_EMPLOYEE_NUM(employeeData.getMP1001_EMPLOYEE_NUM()); //设定员工编码
					mp2001.setMP2001_APPROVAL("1");
					mp2001.setMP2001_STATUS("0"); // 0:正常数据     1:废止数据
					mp2001.setMP2001_DAYS(getLeaveHours()); // 设定请假时间
					mp2001.setMP2001_ACTING_APPLICATION_PERSON(_empNo);
					//mp2001.setMP2001_APPLIY_TYPE(actingType);
					mp2001.setMP2001_APPLIY_TYPE("shiftwork"); //change to shift work type directly
					mp2001.setMP2001_CREATE_DATETIME(addtime1);
					mp2001.setMP2001_CREATE_USER(employeeData.getMP1001_EMPLOYEE_NUM());
					mp2001.setMP2001_FROM_DATETIME(_from);
					mp2001.setMP2001_TO_DATETIME(_to);
					mp2001.setMP2001_SICK_CERTIFICATION("");
					//----------------------------------MP2001---------------------------------------
					
					// 申请假期的同时会减去请假天数，批准后，不再减去请假天数；如果不批准，则会返还请假天数。--------------------2012-02-24 Start
					String _type = mp2001.getMP2001_LEAVE_TYPE();
					int _hours = Integer.parseInt(mp2001.getMP2001_DAYS());
					
					MP2002 mp22 = serviceMp2002.findById(mp2001.getMP2001_EMPLOYEE_NUM());
					int _annualHours = Integer.parseInt(mp22.getMP2002_ANNUAL());
					int _sickHours = Integer.parseInt(mp22.getMP2002_SICK());
					int _familyHours = Integer.parseInt(mp22.getMP2002_FAMILY_RESP());
					int _studyHours = Integer.parseInt(mp22.getMP2002_STUDY());
					int _maternityHours = Integer.parseInt(mp22.getMP2002_MATERNITY());

					String _leaveHours = "0";
					if(_type.equals(Constant.ANNUAL)){//年假
						_leaveHours = String.valueOf(_annualHours - _hours);
					    mp22.setMP2002_ANNUAL(_leaveHours);
					}else if(_type.equals(Constant.SICK)){//病假
						_leaveHours = String.valueOf(_sickHours - _hours);
						mp22.setMP2002_SICK(_leaveHours);
					}else if(_type.equals(Constant.FAMILY)){//家庭假
						_leaveHours = String.valueOf(_familyHours - _hours);
						mp22.setMP2002_FAMILY_RESP(_leaveHours);
					}else if(_type.equals(Constant.STUDY)){//学习假
						_leaveHours = String.valueOf(_studyHours - _hours);
						mp22.setMP2002_STUDY(_leaveHours);
					}else if(_type.equals(Constant.MATERNITY)){//产假
						_leaveHours = String.valueOf(_maternityHours - _hours);
						mp22.setMP2002_MATERNITY(_leaveHours);
					}
					
					if(Integer.parseInt(_leaveHours) < 0){
						StringBuffer sb = new StringBuffer();

						sb.append("<script type='text/javascript'>");
						sb.append("alert('The number of vacation days is not enough。');");
						sb.append("window.location.href='applyLeaveInit.action?type=add&MP2001_NUM='");
						//sb.append("window.close();");
						sb.append("</script>");
					    
						HttpServletResponse response = ServletActionContext.getResponse();
						response.setContentType("text/html;charset=UTF-8");
						response.setCharacterEncoding("utf-8");
						PrintWriter out = response.getWriter();
						out.println(sb.toString());
				        out.flush();
				        out.close();

						return null;
					}
					
					service.save(mp2001);
					serviceMP0006.update(mp6);
					
					serviceMp2002.update(mp22);
					//----------------------------Operation History------------------
					LogUtil logUtil = new LogUtil();
					logUtil.setServiceMP0011(serviceMP0011);
					logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Add Leave Application, Key{" + mp2001.getMP2001_NUM() + "}");
					//----------------------------Operation History------------------
					
					if(_type.equals(Constant.STUDY)){//学习假
						MP2004 mp24 = serviceMp2004.findById(mp2001.getMP2001_MAJOR_SEQ());
						int _majorDays = Integer.parseInt(mp24.getMP2004_TIME());
						_majorDays = _majorDays - _hours;
						
						mp24.setMP2004_TIME(String.valueOf(_majorDays));
						
						serviceMp2004.update(mp24);
					}
					
					//---------------------------------------------------------------------------------2012-02-24 End
					
					// Send Mail ------2011-10-31 Add by Tim------Start
					if(mp2001.getMP2001_ACTING_PERSON()!=null && !mp2001.getMP2001_ACTING_PERSON().equals("")){
						MP1001 emp01 = serviceMP1001.findById(mp2001.getMP2001_ACTING_PERSON());
						MP1001 emp02 = serviceMP1001.findById(mp2001.getMP2001_EMPLOYEE_NUM());
						int _leaveDays = Integer.parseInt(mp2001.getMP2001_DAYS());
						
						Map<String, String> propertyMap = new LinkedHashMap<String, String>();
						propertyMap.put("DEPARTMENT", emp02.getMP1001_DEPARTMENT_ID());
						propertyMap.put("JOB_TITLE", "1");
						propertyMap.put("STATUS", "99");
						List<MP1001> emp11List = serviceMP1001.findByProperty(propertyMap);

						//String result = MessageFormat.format(Constant.MESSAGE_LEAVE_BODY,emp01.getMP1001_PREFERED_NAME(), emp02.getMP1001_PREFERED_NAME());

						Mail mail = new Mail();
						MP1001 mp11 = serviceMP1001.findById(mp2001.getMP2001_ACTING_PERSON());
						StringBuffer toList = new StringBuffer();
						toList.append(mp11.getMP1001_COMPANY_EMAIL());
						toList.append("," + emp02.getMP1001_COMPANY_EMAIL());
						
						//if(emp02.getMP1001_GROUP().equals("2") || emp02.getMP1001_GROUP().equals("3")){
						if(emp02.getMP1001_POSITION() != null && !emp02.getMP1001_POSITION().equals("") && emp02.getMP1001_POSITION().equals("1")){
							List<MP0006> directorMialList = serviceMP0006.findByProperty("MP0006_CODE", "DIRECTOR_LIST");
							if(directorMialList.size() >0){
								MP0006 mp06 = directorMialList.get(0);
								
								toList.append("," + mp06.getMP0006_VALUE());
							}
						}else{
							for(int i=0,j=emp11List.size(); i<j; i++){
								MP1001 obj1001 = emp11List.get(i);
								toList.append("," + obj1001.getMP1001_COMPANY_EMAIL());					
							}
						}
						
						mail.setSubject(Constant.MESSAGE_LEAVE_TITLE);
						
						StringBuffer result = new StringBuffer();
						//text/plain
						result.append("Dear " + emp01.getMP1001_PREFERED_NAME() + "(" + emp01.getMP1001_EMPLOYEE_NUM() + ")" + ",\r\n \r\n ");
						result.append("Please note that " + emp02.getMP1001_PREFERED_NAME() + " have apply for leave, you are the acting person.\r\n \r\n ");
						
						result.append("Employee Number:" + emp02.getMP1001_EMPLOYEE_NUM() + " " + emp02.getMP1001_PREFERED_NAME() + "\r\n ");
						result.append("Acting Person:" + mp2001.getMP2001_ACTING_PERSON() + " " + mp11.getMP1001_PREFERED_NAME() + "\r\n ");
						result.append("Type of Leave:" + leaveTypeList.get(mp2001.getMP2001_LEAVE_TYPE()) + "\r\n ");
						result.append("From Date:" + mp2001.getMP2001_FROM_DATETIME().substring(0,16) + "\r\n ");
						result.append("To Date:" + mp2001.getMP2001_TO_DATETIME().substring(0,16) + "\r\n ");
						result.append("Days:" + _leaveDays/8 + "D " + _leaveDays%8 + "H" + "\r\n ");
						result.append("Comment:" + mp2001.getMP2001_COMMENT() + "\r\n ");
						
						result.append("Thank you very much! \r\n \r\n\r\n ");
						result.append("Your Faithfully, \r\n ");
						result.append("HRMS Administrator");

						mail.setTo(toList.toString());
						mail.setContent(result.toString());
						mail.send();
					}
					// Send Mail ------2011-10-31 Add by Tim------End

					session.put("LEAVE_NUM", leaveNum);
	/*			}else if(type.equals("edit")){
					MP2001 editData = new MP2001();
					editData = service.findById(MP2001_NUM);
					
					editData.setMP2001_DAYS(getLeaveHours());// 设定请假时间
					editData.setMP2001_LEAVE_TYPE(mp2001.getMP2001_LEAVE_TYPE());// 设定请假类型
					editData.setMP2001_ACTING_PERSON(mp2001.getMP2001_ACTING_PERSON());// 设定代理人
					editData.setMP2001_FROM_DATETIME(mp2001.getMP2001_FROM_DATETIME());// 开始时间
					editData.setMP2001_TO_DATETIME(mp2001.getMP2001_TO_DATETIME());// 结束时间
					
					// 2011-10-28 Add by Tim
					editData.setMP2001_ACTING_PERSON(employeeData.getMP1001_EMPLOYEE_NUM());
					editData.setMP2001_APPLIY_TYPE(actingType);
					editData.setMP2001_COMMENT(mp2001.getMP2001_COMMENT());
					
					if(actingType == "self"){
						editData.setMP2001_EMPLOYEE_NUM(employeeData.getMP1001_EMPLOYEE_NUM());
					}else if(actingType == "acting"){
						editData.setMP2001_EMPLOYEE_NUM(mp2001.getMP2001_EMPLOYEE_NUM());
					}
					
					service.update(editData);
				}*/
				
				return SUCCESS;
			}
			
		}catch(Exception ex){
			System.out.println(ex.getLocalizedMessage());
			return INPUT;
		}

	}
	
	public String abnormalEarlyLateReport(){
		try{
			System.out.println("absolute path : " + ServletActionContext.getServletContext().getRealPath("/"));
			
			fileName = "abnormalEarlyLate.xls";
			//String _path = ServletActionContext.getServletContext().getContextPath() + "/uploadfile/" + fileName;
			String _path = ServletActionContext.getServletContext().getRealPath("/") + "uploadfile\\" + fileName;
			fileName += "x";
			//File f = new File(_path);
			//f.createNewFile();
			
			Workbook wb = createReportWorkbook();
			ExcelUtil.createExcelFile(_path, wb);
			
			return SUCCESS;
		}catch(Exception ex){
			System.out.println(ex.getLocalizedMessage());
			return ERROR;
		}

	}
	
	public Workbook createReportWorkbook() throws Exception{
		Workbook wb = ExcelUtil.CreateXSSFWorkBook();
		
		pourTotalUnusualRecordsIntoWorkbook(wb);
		pourAbnormalRecordsIntoWorkbook(wb);
		pourEarlyLateRecordsIntoWorkbook(wb);
		
		return wb;
	}

	public void pourTotalUnusualRecordsIntoWorkbook(Workbook wb) throws Exception {
		//abnormal sheet, total unusual records
		Sheet totalUnusualSheet = ExcelUtil.CreateSheet(wb, "Total Unusual Record");
		// 设置SHEET页面属性
		ExcelUtil.SetSheetPropertyHSSF(totalUnusualSheet);
	
		// 获取预定的样式
		Map<String, CellStyle> styles = ExcelUtil.CreateStyles(wb);
		
		String[] titles = new String[8];
//employee number	employee name	department name	date	in	out	comments	
		titles[0] = "Employee Code";
		titles[1] = "Employee Name";
		titles[2] = "Surname";
		titles[3] = "Department Name";
		titles[4] = "Date";
		titles[5] = "In";
		titles[6] = "Out";
		titles[7] = "Comments";
		
		float rowHeight = 27;
		ExcelUtil.CreateHeadRow(totalUnusualSheet, titles, rowHeight, styles);
		totalUnusualSheet.createFreezePane(0, 1);
		
		int[] cellsWidth = new int[8];
		cellsWidth[0] = 30;
		cellsWidth[1] = 30;
		cellsWidth[2] = 30;
		cellsWidth[3] = 35;
		cellsWidth[4] = 30;
		cellsWidth[5] = 30;
		cellsWidth[6] = 30;
		cellsWidth[7] = 30;
		ExcelUtil.SetCellsWidth(totalUnusualSheet, cellsWidth);
		
		//get datas
		Map<String, String> propMap = new HashMap<String, String>();
		propMap.put("from", fromDate);
		propMap.put("to", toDate);
		List<MP2003> unusualList = serviceMp2003.getTotalUnusualRecords(propMap);

		int count = 1;
		String[] datas = null;
		for(int i = 0, j = unusualList.size(); i < j; i++, count++){
			datas = new String[8];
			datas[0] = unusualList.get(i).getMP2003_EMPLOYEE_NUM();
			datas[1] = unusualList.get(i).getMP2003_EMPLOYEE_NAME();
			datas[2] = unusualList.get(i).getMP2003_EMPLOYEE_SURNAME();
			datas[3] = unusualList.get(i).getMP2003_DEPARTMENT_NAME();
			if(unusualList.get(i).getMP2003_DATETIME().isEmpty()){
				datas[4] = "";
			}
			else{
				datas[4] = unusualList.get(i).getMP2003_DATETIME().substring(0, 19);
			}
			if(unusualList.get(i).getMP2003_START_TIME().isEmpty()){
				datas[5] = "";
			}
			else{
				datas[5] = unusualList.get(i).getMP2003_START_TIME().substring(0, 19);
			}
			if(unusualList.get(i).getMP2003_FINISH_TIME().isEmpty()){
				datas[6] = "";
			}
			else{
				datas[6] = unusualList.get(i).getMP2003_FINISH_TIME().substring(0, 19);
			}
			datas[7] = unusualList.get(i).getMP2003_COMMENT();
			
			ExcelUtil.SetCellsValue(count, totalUnusualSheet, styles, datas);
		}
	}
	
	public void pourAbnormalRecordsIntoWorkbook(Workbook wb) throws Exception {
		//only abnormal records report
		Sheet abnormalSheet = ExcelUtil.CreateSheet(wb, "Abnormal Record");
		ExcelUtil.SetSheetPropertyHSSF(abnormalSheet);
		
		// 获取预定的样式
		Map<String, CellStyle> styles = ExcelUtil.CreateStyles(wb);
		
		String[] titles = new String[5];
		titles[0] = "Employee Code";
		titles[1] = "Employee Name";
		titles[2] = "Surname";
		titles[3] = "Department Name";
		titles[4] = "Days";
		
		int[] cellsWidth = new int[5];
		cellsWidth[0] = 30;
		cellsWidth[1] = 30;
		cellsWidth[2] = 30;
		cellsWidth[3] = 35;
		cellsWidth[4] = 30;
		
		float rowHeight = 27;
		ExcelUtil.CreateHeadRow(abnormalSheet, titles, rowHeight, styles);
		abnormalSheet.createFreezePane(0, 1);
		
		ExcelUtil.SetCellsWidth(abnormalSheet, cellsWidth);

		Map<String, String> propMap = new HashMap<String, String>();
		propMap.put("from", fromDate);
		propMap.put("to", toDate);
		List<AbnormalReptRecdDto> dataList = serviceMp2003.getAbnormalReptData(propMap);
		
		int count = 1;
		String[] datas = null; 
		for(int i = 0, j = dataList.size(); i < j; i++, count++){
			datas = new String[5];
			datas[0] = dataList.get(i).getEmployeeCode();
			datas[1] = dataList.get(i).getEmployeeName();
			datas[2] = dataList.get(i).getSurname();
			datas[3] = dataList.get(i).getDepartmentName();
			datas[4] = Float.toString(dataList.get(i).getDay());
			
			ExcelUtil.SetCellsValue(count, abnormalSheet, styles, datas);
		}
		
	}
	
	public void pourEarlyLateRecordsIntoWorkbook(Workbook wb) throws Exception {
		// 新建一个SHEET页面
		Sheet sheet = ExcelUtil.CreateSheet(wb, "Late And Early");
		// 设置SHEET页面属性
		ExcelUtil.SetSheetPropertyHSSF(sheet);
		// 获取预定的样式
		Map<String, CellStyle> styles = ExcelUtil.CreateStyles(wb);

		List<String> headNameList = new ArrayList<String>();
		headNameList.add("EMPLOYEE_NUM");
		headNameList.add("DATE");
		headNameList.add("CLOCK IN");
		headNameList.add("CLOCK OUT");
		headNameList.add("EMPLOYEE NAME");
		headNameList.add("SURNAME");
		headNameList.add("DEPARTMENT");
		headNameList.add("STATUS");
		headNameList.add("LATE MINUTES");
		headNameList.add("EARLY MINUTES");
		headNameList.add("TOTAL MINUTES");

		List<Integer> headCellWidth = new ArrayList<Integer>();
		headCellWidth.add(20);
		headCellWidth.add(30);
		headCellWidth.add(30);
		headCellWidth.add(30);
		headCellWidth.add(20);
		headCellWidth.add(20);
		headCellWidth.add(35);
		headCellWidth.add(30);
		headCellWidth.add(18);
		headCellWidth.add(18);
		headCellWidth.add(18);

		// Header标题
		String[] titles = new String[headNameList.size()];
		int i = 0;
		for(String s : headNameList){
			titles[i] = s;
			i++;
		}
				
		// 生成标题行
		float rowHeight = 27f;
		ExcelUtil.CreateHeadRow(sheet, titles, rowHeight, styles);
        // 冻结第一行
        sheet.createFreezePane(0, 1);
        
        Integer[] cellsWidth = new Integer[headCellWidth.size()];
        i = 0;
        for(Integer it : headCellWidth){
        	cellsWidth[i] = it;
        	i++;
        }
        
        // 设置单元格的宽度
        ExcelUtil.SetCellsWidth(sheet, cellsWidth); //static binding
        
      //---------------------------主报表-----------------------------------------------------------------------------------------
        //List<lateEarlyDto> lateEarlyList = new ArrayList<lateEarlyDto>(); 
        Map<String, String> propMap = new HashMap<String, String>();
        propMap.put("from", fromDate);
		propMap.put("to", toDate);
        List<LateEarlyDto> lateEarlyList = serviceMp2003.getLateEarlyReptData(propMap); 

        
        // 取得报表数据
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] datas = new String[11];
        int total = 0;
        String dptName = null;
        String oldDptName = null;
        int rowNum = 0;
        for(int c = 0, j = lateEarlyList.size(); c < j; c++){
        	dptName = lateEarlyList.get(c).getDepartmentName();
        	if(lateEarlyList.get(c).getAllMins() > 480){
        		continue;
        	}
        	if(null != oldDptName && !dptName.equals(oldDptName)){
        		for(int k = 0; k < 9; k++){
        			datas[k] = "";
        		}
        		datas[9] = "Minutes";
        		datas[10] = Integer.toString(total);
        		oldDptName = dptName;
        		total = 0;
        		rowNum++;
        		c--; //must do this, back forwad one record
        	}
        	else{
	        	//lateEarlyList.get(c);
	        	datas[0] = lateEarlyList.get(c).getEmployeeNum();
	        	datas[1] = sdf.format(lateEarlyList.get(c).getDate());
	        	if(lateEarlyList.get(c).getClockInTime() == null){
	        		datas[2] = null;
	        	}
	        	else{
	        		datas[2] = sdf.format(lateEarlyList.get(c).getClockInTime());
	        	}
	        	if(lateEarlyList.get(c).getClockOutTime() == null){
	        		datas[3] = null;
	        	}
	        	else{
	        		datas[3] = sdf.format(lateEarlyList.get(c).getClockOutTime());
	        	}
	        	datas[4] = lateEarlyList.get(c).getPreferedName();
	        	datas[5] = lateEarlyList.get(c).getSurname();
	        	datas[6] = lateEarlyList.get(c).getDepartmentName();
	        	datas[7] = lateEarlyList.get(c).getStatus();
	        	datas[8] = Integer.toString(lateEarlyList.get(c).getLateMins());
	        	datas[9] = Integer.toString((lateEarlyList.get(c).getEarlyMins()));
	        	datas[10] = Integer.toString(lateEarlyList.get(c).getAllMins());

	        	rowNum++;
	        	total += lateEarlyList.get(c).getAllMins();
        		oldDptName = dptName;
        	}
        	
        	ExcelUtil.SetCellsValue(rowNum, sheet, styles, datas);
        }
        
        //last total minutes records
        for(int k = 0; k < 9; k++){
			datas[k] = "";
		}
		datas[9] = "Minutes";
		datas[10] = Integer.toString(total);
		rowNum++;
		ExcelUtil.SetCellsValue(rowNum, sheet, styles, datas);

		
	}
	
	public String validateShiftworkAddLeaveApply(){
		System.out.println("in validateShiftworkAddLeaveApply function");
		return SUCCESS;
	}
	
	
	public String shiftWorkCalculate(){
		try{
//			executeJobs jb = new executeJobs();
			//calculate shift work
			jb.executeJob11();
			
			
			//test part
//			jb.executeJob19();
//			jb.executeJob1();
			return NONE;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return NONE;
		}
	}
	
	/**
	 * @return the service
	 */
	public IMP2001Service getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(IMP2001Service service) {
		this.service = service;
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
	 * @return the leaveTypeList
	 */
	public Map<String, String> getLeaveTypeList() {
		return leaveTypeList;
	}

	/**
	 * @param leaveTypeList the leaveTypeList to set
	 */
	public void setLeaveTypeList(Map<String, String> leaveTypeList) {
		this.leaveTypeList = leaveTypeList;
	}

	/**
	 * @return the positionLev
	 */
	public String getPositionLev() {
		return positionLev;
	}

	/**
	 * @param positionLev the positionLev to set
	 */
	public void setPositionLev(String positionLev) {
		this.positionLev = positionLev;
	}

	public IMP2002Service getServiceMp2002() {
		return serviceMp2002;
	}

	public void setServiceMp2002(IMP2002Service serviceMp2002) {
		this.serviceMp2002 = serviceMp2002;
	}

	public String getAnnualDays() {
		return annualDays;
	}

	public void setAnnualDays(String annualDays) {
		this.annualDays = annualDays;
	}

	public String getSickDays() {
		return sickDays;
	}

	public void setSickDays(String sickDays) {
		this.sickDays = sickDays;
	}

	public String getFamilyDays() {
		return familyDays;
	}

	public void setFamilyDays(String familyDays) {
		this.familyDays = familyDays;
	}

	public String getMaternityDays() {
		return maternityDays;
	}

	public void setMaternityDays(String maternityDays) {
		this.maternityDays = maternityDays;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	/**
	 * @return the leaveInfo
	 */
	public List<MP2001> getLeaveInfo() {
		return leaveInfo;
	}

	/**
	 * @param leaveInfo the leaveInfo to set
	 */
	public void setLeaveInfo(List<MP2001> leaveInfo) {
		this.leaveInfo = leaveInfo;
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
	 * @return the serviceMp2003
	 */
	public IMP2003Service getServiceMp2003() {
		return serviceMp2003;
	}

	/**
	 * @param serviceMp2003 the serviceMp2003 to set
	 */
	public void setServiceMp2003(IMP2003Service serviceMp2003) {
		this.serviceMp2003 = serviceMp2003;
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
	 * @return the workingHourRecordList
	 */
	public List<MP2003> getWorkingHourRecordList() {
		return workingHourRecordList;
	}

	/**
	 * @param workingHourRecordList the workingHourRecordList to set
	 */
	public void setWorkingHourRecordList(List<MP2003> workingHourRecordList) {
		this.workingHourRecordList = workingHourRecordList;
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
	 * @return the mP2001_NUM
	 */
	public String getMP2001_NUM() {
		return MP2001_NUM;
	}

	/**
	 * @param mP2001_NUM the mP2001_NUM to set
	 */
	public void setMP2001_NUM(String mP2001_NUM) {
		MP2001_NUM = mP2001_NUM;
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
	 * @return the approvalTypeList
	 */
	public Map<String, String> getApprovalTypeList() {
		return approvalTypeList;
	}

	/**
	 * @param approvalTypeList the approvalTypeList to set
	 */
	public void setApprovalTypeList(Map<String, String> approvalTypeList) {
		this.approvalTypeList = approvalTypeList;
	}

	/**
	 * @return the leaveType
	 */
	public String getLeaveType() {
		return leaveType;
	}

	/**
	 * @param leaveType the leaveType to set
	 */
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
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
	 * @return the approvalType
	 */
	public String getApprovalType() {
		return approvalType;
	}

	/**
	 * @param approvalType the approvalType to set
	 */
	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}

	/**
	 * @return the vacationInfoList
	 */
	public List<MP2002> getVacationInfoList() {
		return VacationInfoList;
	}

	/**
	 * @param vacationInfoList the vacationInfoList to set
	 */
	public void setVacationInfoList(List<MP2002> vacationInfoList) {
		VacationInfoList = vacationInfoList;
	}

	/**
	 * @return the dateHoursList
	 */
	public Map<String, String> getDateHoursList() {
		return dateHoursList;
	}

	/**
	 * @param dateHoursList the dateHoursList to set
	 */
	public void setDateHoursList(Map<String, String> dateHoursList) {
		this.dateHoursList = dateHoursList;
	}

	/**
	 * @return the study
	 */
	public String getStudy() {
		return study;
	}

	/**
	 * @param study the study to set
	 */
	public void setStudy(String study) {
		this.study = study;
	}

	/**
	 * @return the days
	 */
	public String getDays() {
		return days;
	}

	/**
	 * @param days the days to set
	 */
	public void setDays(String days) {
		this.days = days;
	}

	/**
	 * @return the hours
	 */
	public String getHours() {
		return Hours;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(String hours) {
		Hours = hours;
	}

	/**
	 * @return the roleGroup
	 */
	public String getRoleGroup() {
		return roleGroup;
	}

	/**
	 * @param roleGroup the roleGroup to set
	 */
	public void setRoleGroup(String roleGroup) {
		this.roleGroup = roleGroup;
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
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the workingHoursList
	 */
	public Map<String, String> getWorkingHoursFromList() {
		return workingHoursFromList;
	}

	/**
	 * @param workingHoursList the workingHoursList to set
	 */
	public void setWorkingHoursFromList(Map<String, String> workingHoursFromList) {
		this.workingHoursFromList = workingHoursFromList;
	}

	/**
	 * @return the workingHours1
	 */
	public String getWorkingHours1() {
		return workingHours1;
	}

	/**
	 * @param workingHours1 the workingHours1 to set
	 */
	public void setWorkingHours1(String workingHours1) {
		this.workingHours1 = workingHours1;
	}

	/**
	 * @return the workingHours2
	 */
	public String getWorkingHours2() {
		return workingHours2;
	}

	/**
	 * @param workingHours2 the workingHours2 to set
	 */
	public void setWorkingHours2(String workingHours2) {
		this.workingHours2 = workingHours2;
	}

	/**
	 * @param serviceHoliday the serviceHoliday to set
	 */
	public void setServiceHoliday(IHOLIDAYService serviceHoliday) {
		this.serviceHoliday = serviceHoliday;
	}

	/**
	 * @return the holidayInfo
	 */
	public String getHolidayInfo() {
		return holidayInfo;
	}

	/**
	 * @param holidayInfo the holidayInfo to set
	 */
	public void setHolidayInfo(String holidayInfo) {
		this.holidayInfo = holidayInfo;
	}

	/**
	 * @return the serviceHoliday
	 */
	public IHOLIDAYService getServiceHoliday() {
		return serviceHoliday;
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
	 * @return the workingMinuteList
	 */
	public Map<String, String> getWorkingMinuteList() {
		return workingMinuteList;
	}

	/**
	 * @param workingMinuteList the workingMinuteList to set
	 */
	public void setWorkingMinuteList(Map<String, String> workingMinuteList) {
		this.workingMinuteList = workingMinuteList;
	}

	/**
	 * @return the workingMinute1
	 */
	public String getWorkingMinute1() {
		return workingMinute1;
	}

	/**
	 * @param workingMinute1 the workingMinute1 to set
	 */
	public void setWorkingMinute1(String workingMinute1) {
		this.workingMinute1 = workingMinute1;
	}

	/**
	 * @return the workingMinute2
	 */
	public String getWorkingMinute2() {
		return workingMinute2;
	}

	/**
	 * @param workingMinute2 the workingMinute2 to set
	 */
	public void setWorkingMinute2(String workingMinute2) {
		this.workingMinute2 = workingMinute2;
	}

	/**
	 * @return the minutes
	 */
	public String getMinutes() {
		return Minutes;
	}

	/**
	 * @param minutes the minutes to set
	 */
	public void setMinutes(String minutes) {
		Minutes = minutes;
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
	 * @return the actingType
	 */
	public String getActingType() {
		return actingType;
	}

	/**
	 * @param actingType the actingType to set
	 */
	public void setActingType(String actingType) {
		this.actingType = actingType;
	}

	/**
	 * @return the personType
	 */
	public String getPersonType() {
		return personType;
	}

	/**
	 * @param personType the personType to set
	 */
	public void setPersonType(String personType) {
		this.personType = personType;
	}

	/**
	 * @return the actingPersionNum
	 */
	public String getActingPersionNum() {
		return actingPersionNum;
	}

	/**
	 * @param actingPersionNum the actingPersionNum to set
	 */
	public void setActingPersionNum(String actingPersionNum) {
		this.actingPersionNum = actingPersionNum;
	}

	/**
	 * @return the confirmNum
	 */
	public String getConfirmNum() {
		return confirmNum;
	}

	/**
	 * @param confirmNum the confirmNum to set
	 */
	public void setConfirmNum(String confirmNum) {
		this.confirmNum = confirmNum;
	}

	/**
	 * @return the confirmDate
	 */
	public String getConfirmDate() {
		return confirmDate;
	}

	/**
	 * @param confirmDate the confirmDate to set
	 */
	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
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
	 * @return the leaveReportList
	 */
	public List<MP2001> getLeaveReportList() {
		return leaveReportList;
	}

	/**
	 * @param leaveReportList the leaveReportList to set
	 */
	public void setLeaveReportList(List<MP2001> leaveReportList) {
		this.leaveReportList = leaveReportList;
	}

	/**
	 * @return the mp2002EmpNum
	 */
	public String getMp2002EmpNum() {
		return mp2002EmpNum;
	}

	/**
	 * @param mp2002EmpNum the mp2002EmpNum to set
	 */
	public void setMp2002EmpNum(String mp2002EmpNum) {
		this.mp2002EmpNum = mp2002EmpNum;
	}

	/**
	 * @return the depID
	 */
	public String getDepID() {
		return depID;
	}

	/**
	 * @param depID the depID to set
	 */
	public void setDepID(String depID) {
		this.depID = depID;
	}

	/**
	 * @return the mp2002EmpName
	 */
	public String getMp2002EmpName() {
		return mp2002EmpName;
	}

	/**
	 * @param mp2002EmpName the mp2002EmpName to set
	 */
	public void setMp2002EmpName(String mp2002EmpName) {
		this.mp2002EmpName = mp2002EmpName;
	}

	/**
	 * @return the serviceMp2004
	 */
	public IMP2004Service getServiceMp2004() {
		return serviceMp2004;
	}

	/**
	 * @param serviceMp2004 the serviceMp2004 to set
	 */
	public void setServiceMp2004(IMP2004Service serviceMp2004) {
		this.serviceMp2004 = serviceMp2004;
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
	 * @return the majorInfoList
	 */
	public List<MP2004> getMajorInfoList() {
		return majorInfoList;
	}

	/**
	 * @param majorInfoList the majorInfoList to set
	 */
	public void setMajorInfoList(List<MP2004> majorInfoList) {
		this.majorInfoList = majorInfoList;
	}

	/**
	 * @return the majorName
	 */
	public String getMajorName() {
		return majorName;
	}

	/**
	 * @param majorName the majorName to set
	 */
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	/**
	 * @return the majorDays
	 */
	public String getMajorDays() {
		return majorDays;
	}

	/**
	 * @param majorDays the majorDays to set
	 */
	public void setMajorDays(String majorDays) {
		this.majorDays = majorDays;
	}

	/**
	 * @return the mp2004Seq
	 */
	public String getMp2004Seq() {
		return mp2004Seq;
	}

	/**
	 * @param mp2004Seq the mp2004Seq to set
	 */
	public void setMp2004Seq(String mp2004Seq) {
		this.mp2004Seq = mp2004Seq;
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
	 * @return the majorList
	 */
	public Map<String, String> getMajorList() {
		return majorList;
	}

	/**
	 * @param majorList the majorList to set
	 */
	public void setMajorList(Map<String, String> majorList) {
		this.majorList = majorList;
	}

	/**
	 * @return the mp2004MajorDays
	 */
	public String getMp2004MajorDays() {
		return mp2004MajorDays;
	}

	/**
	 * @param mp2004MajorDays the mp2004MajorDays to set
	 */
	public void setMp2004MajorDays(String mp2004MajorDays) {
		this.mp2004MajorDays = mp2004MajorDays;
	}

	/**
	 * @return the jobTitleList
	 */
	public Map<String, String> getJobTitleList() {
		return jobTitleList;
	}

	/**
	 * @param jobTitleList the jobTitleList to set
	 */
	public void setJobTitleList(Map<String, String> jobTitleList) {
		this.jobTitleList = jobTitleList;
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
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * @param jobTitle the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
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
	 * @return the optDel2
	 */
	public String getOptDel2() {
		return optDel2;
	}

	/**
	 * @param optDel2 the optDel2 to set
	 */
	public void setOptDel2(String optDel2) {
		this.optDel2 = optDel2;
	}

	/**
	 * @return the serviceMp0010
	 */
	public IMP0010Service getServiceMp0010() {
		return serviceMp0010;
	}

	/**
	 * @param serviceMp0010 the serviceMp0010 to set
	 */
	public void setServiceMp0010(IMP0010Service serviceMp0010) {
		this.serviceMp0010 = serviceMp0010;
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
	 * @return the mp2005ID
	 */
	public String getMp2005ID() {
		return mp2005ID;
	}

	/**
	 * @param mp2005id the mp2005ID to set
	 */
	public void setMp2005ID(String mp2005id) {
		mp2005ID = mp2005id;
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
	 * @return the stayAddress
	 */
	public String getStayAddress() {
		return stayAddress;
	}

	/**
	 * @param stayAddress the stayAddress to set
	 */
	public void setStayAddress(String stayAddress) {
		this.stayAddress = stayAddress;
	}

	/**
	 * @return the visitAddress
	 */
	public String getVisitAddress() {
		return visitAddress;
	}

	/**
	 * @param visitAddress the visitAddress to set
	 */
	public void setVisitAddress(String visitAddress) {
		this.visitAddress = visitAddress;
	}

	/**
	 * @return the purpose
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * @param purpose the purpose to set
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	/**
	 * @return the mp2006Seq
	 */
	public String getMp2006Seq() {
		return mp2006Seq;
	}

	/**
	 * @param mp2006Seq the mp2006Seq to set
	 */
	public void setMp2006Seq(String mp2006Seq) {
		this.mp2006Seq = mp2006Seq;
	}

	/**
	 * @return the isCommunicationCost
	 */
	public String getIsCommunicationCost() {
		return isCommunicationCost;
	}

	/**
	 * @param isCommunicationCost the isCommunicationCost to set
	 */
	public void setIsCommunicationCost(String isCommunicationCost) {
		this.isCommunicationCost = isCommunicationCost;
	}

	/**
	 * @return the mp2005Status
	 */
	public String getMp2005Status() {
		return mp2005Status;
	}

	/**
	 * @param mp2005Status the mp2005Status to set
	 */
	public void setMp2005Status(String mp2005Status) {
		this.mp2005Status = mp2005Status;
	}

	/**
	 * @return the mp2005JobID
	 */
	public String getMp2005JobID() {
		return mp2005JobID;
	}

	/**
	 * @param mp2005JobID the mp2005JobID to set
	 */
	public void setMp2005JobID(String mp2005JobID) {
		this.mp2005JobID = mp2005JobID;
	}

	/**
	 * @return the mp2005EmpNum
	 */
	public String getMp2005EmpNum() {
		return mp2005EmpNum;
	}

	/**
	 * @param mp2005EmpNum the mp2005EmpNum to set
	 */
	public void setMp2005EmpNum(String mp2005EmpNum) {
		this.mp2005EmpNum = mp2005EmpNum;
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
	 * @return the attendenceStatusList
	 */
	public Map<String, String> getAttendenceStatusList() {
		return attendenceStatusList;
	}

	/**
	 * @param attendenceStatusList the attendenceStatusList to set
	 */
	public void setAttendenceStatusList(Map<String, String> attendenceStatusList) {
		this.attendenceStatusList = attendenceStatusList;
	}

	/**
	 * @return the attendenceStatus
	 */
	public String getAttendenceStatus() {
		return attendenceStatus;
	}

	/**
	 * @param attendenceStatus the attendenceStatus to set
	 */
	public void setAttendenceStatus(String attendenceStatus) {
		this.attendenceStatus = attendenceStatus;
	}

	/**
	 * @return the serviceMP2007
	 */
	public IMP2007Service getServiceMP2007() {
		return serviceMP2007;
	}

	/**
	 * @param serviceMP2007 the serviceMP2007 to set
	 */
	public void setServiceMP2007(IMP2007Service serviceMP2007) {
		this.serviceMP2007 = serviceMP2007;
	}

	/**
	 * @return the mp2007
	 */
	public MP2007 getMp2007() {
		return mp2007;
	}

	/**
	 * @param mp2007 the mp2007 to set
	 */
	public void setMp2007(MP2007 mp2007) {
		this.mp2007 = mp2007;
	}

	/**
	 * @return the leaveStatusList
	 */
	public Map<String, String> getLeaveStatusList() {
		return leaveStatusList;
	}

	/**
	 * @param leaveStatusList the leaveStatusList to set
	 */
	public void setLeaveStatusList(Map<String, String> leaveStatusList) {
		this.leaveStatusList = leaveStatusList;
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
	 * @return the mp2007InfoList
	 */
	public List<MP2007> getMp2007InfoList() {
		return mp2007InfoList;
	}

	/**
	 * @param mp2007InfoList the mp2007InfoList to set
	 */
	public void setMp2007InfoList(List<MP2007> mp2007InfoList) {
		this.mp2007InfoList = mp2007InfoList;
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
	 * @return the mp2007Seq
	 */
	public String getMp2007Seq() {
		return mp2007Seq;
	}

	/**
	 * @param mp2007Seq the mp2007Seq to set
	 */
	public void setMp2007Seq(String mp2007Seq) {
		this.mp2007Seq = mp2007Seq;
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
	 * @return the func0030001
	 */
	public String getFunc0030001() {
		return func0030001;
	}

	/**
	 * @param func0030001 the func0030001 to set
	 */
	public void setFunc0030001(String func0030001) {
		this.func0030001 = func0030001;
	}

	/**
	 * @return the func0030002
	 */
	public String getFunc0030002() {
		return func0030002;
	}

	/**
	 * @param func0030002 the func0030002 to set
	 */
	public void setFunc0030002(String func0030002) {
		this.func0030002 = func0030002;
	}

	/**
	 * @return the func0030003
	 */
	public String getFunc0030003() {
		return func0030003;
	}

	/**
	 * @param func0030003 the func0030003 to set
	 */
	public void setFunc0030003(String func0030003) {
		this.func0030003 = func0030003;
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

	/**
	 * @return the serviceMP2008
	 */
	public IMP2008Service getServiceMP2008() {
		return serviceMP2008;
	}

	/**
	 * @param serviceMP2008 the serviceMP2008 to set
	 */
	public void setServiceMP2008(IMP2008Service serviceMP2008) {
		this.serviceMP2008 = serviceMP2008;
	}

	/**
	 * @return the mp2008
	 */
	public MP2008 getMp2008() {
		return mp2008;
	}

	/**
	 * @param mp2008 the mp2008 to set
	 */
	public void setMp2008(MP2008 mp2008) {
		this.mp2008 = mp2008;
	}

	/**
	 * @return the mp2008InfoList
	 */
	public List<MP2008> getMp2008InfoList() {
		return mp2008InfoList;
	}

	/**
	 * @param mp2008InfoList the mp2008InfoList to set
	 */
	public void setMp2008InfoList(List<MP2008> mp2008InfoList) {
		this.mp2008InfoList = mp2008InfoList;
	}

	/**
	 * @return the hourInfoList
	 */
	public Map<String, String> getHourInfoList() {
		return hourInfoList;
	}

	/**
	 * @param hourInfoList the hourInfoList to set
	 */
	public void setHourInfoList(Map<String, String> hourInfoList) {
		this.hourInfoList = hourInfoList;
	}

    //必须实现的方法,获得ServletRequest对象
    public void setServletRequest(HttpServletRequest request)
    {
        this.request = request ;
    }

	/**
	 * @return the serviceMP2009
	 */
	public IMP2009Service getServiceMP2009() {
		return serviceMP2009;
	}

	/**
	 * @param serviceMP2009 the serviceMP2009 to set
	 */
	public void setServiceMP2009(IMP2009Service serviceMP2009) {
		this.serviceMP2009 = serviceMP2009;
	}

	/**
	 * @return the workingHoursToList
	 */
	public Map<String, String> getWorkingHoursToList() {
		return workingHoursToList;
	}

	/**
	 * @param workingHoursToList the workingHoursToList to set
	 */
	public void setWorkingHoursToList(Map<String, String> workingHoursToList) {
		this.workingHoursToList = workingHoursToList;
	}

	/**
	 * @return the leaveReportList2
	 */
	public List<MP2009> getLeaveReportList2() {
		return leaveReportList2;
	}

	/**
	 * @param leaveReportList2 the leaveReportList2 to set
	 */
	public void setLeaveReportList2(List<MP2009> leaveReportList2) {
		this.leaveReportList2 = leaveReportList2;
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
	 * @return the scoreArray
	 */
	public List<String> getScoreArray() {
		return scoreArray5;
	}

	/**
	 * @param scoreArray the scoreArray to set
	 */
	public void setScoreArray(List<String> scoreArray) {
		this.scoreArray5 = scoreArray;
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

	/**
	 * @return the param6
	 */
	public String getParam6() {
		return param6;
	}

	/**
	 * @param param6 the param6 to set
	 */
	public void setParam6(String param6) {
		this.param6 = param6;
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
	 * @return the param7
	 */
	public String getParam7() {
		return param7;
	}

	/**
	 * @param param7 the param7 to set
	 */
	public void setParam7(String param7) {
		this.param7 = param7;
	}

	/**
	 * @return the param8
	 */
	public String getParam8() {
		return param8;
	}

	/**
	 * @param param8 the param8 to set
	 */
	public void setParam8(String param8) {
		this.param8 = param8;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Log getLog() {
		return log;
	}

	public IMP2010Service getServiceMP2010() {
		return serviceMP2010;
	}

	public void setServiceMP2010(IMP2010Service serviceMP2010) {
		this.serviceMP2010 = serviceMP2010;
	}

	public IJE0101Service getServiceJE0101() {
		return serviceJE0101;
	}

	public void setServiceJE0101(IJE0101Service serviceJE0101) {
		this.serviceJE0101 = serviceJE0101;
	}

	public List<ShiftworkExcelRecordDto> getExcelRecordsList() {
		return excelRecordsList;
	}

	public void setExcelRecordsList(List<ShiftworkExcelRecordDto> excelRecordsList) {
		this.excelRecordsList = excelRecordsList;
	}

	public List<String> getBranchSiteList() {
		return branchSiteList;
	}

	public void setBranchSiteList(List<String> branchSiteList) {
		this.branchSiteList = branchSiteList;
	}

	public String getBranchSiteId() {
		return branchSiteId;
	}

	public void setBranchSiteId(String branchSiteId) {
		this.branchSiteId = branchSiteId;
	}

	public File getShiftworkExcel() {
		return shiftworkExcel;
	}

	public void setShiftworkExcel(File shiftworkExcel) {
		this.shiftworkExcel = shiftworkExcel;
	}

	public String getShiftworkExcelName() {
		return shiftworkExcelName;
	}

	public void setShiftworkExcelName(String shiftworkExcelName) {
		this.shiftworkExcelName = shiftworkExcelName;
	}

	public String getShiftworkExcelContentType() {
		return shiftworkExcelContentType;
	}

	public void setShiftworkExcelContentType(String shiftworkExcelContentType) {
		this.shiftworkExcelContentType = shiftworkExcelContentType;
	}

	public String getShiftWorkRadio() {
		return shiftWorkRadio;
	}

	public void setShiftWorkRadio(String shiftWorkRadio) {
		this.shiftWorkRadio = shiftWorkRadio;
	}

	public String getDayTypeChoose() {
		return dayTypeChoose;
	}

	public void setDayTypeChoose(String dayTypeChoose) {
		this.dayTypeChoose = dayTypeChoose;
	}

	public List<MP2010> getShiftworkScheduleList() {
		return shiftworkScheduleList;
	}

	public void setShiftworkScheduleList(List<MP2010> shiftworkScheduleList) {
		this.shiftworkScheduleList = shiftworkScheduleList;
	}

	public String getShiftWorkType() {
		return shiftWorkType;
	}

	public void setShiftWorkType(String shiftWorkType) {
		this.shiftWorkType = shiftWorkType;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public String getPER_PAGE_NUM() {
		return PER_PAGE_NUM;
	}

	public void setPER_PAGE_NUM(String pER_PAGE_NUM) {
		PER_PAGE_NUM = pER_PAGE_NUM;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public executeJobs getJb() {
		return jb;
	}

	public void setJb(executeJobs jb) {
		this.jb = jb;
	}

	public String getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(String todayDate) {
		this.todayDate = todayDate;
	}
}