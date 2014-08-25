package action;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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

import schedule.ExecuteJobsService;
import service.DetailDayWorkTimeService;
import service.EachCircleDayService;
import service.MP1001Service;
import service.WorkTimePatternService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.LocationTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RegionTextRenderFilter;
import com.itextpdf.text.pdf.parser.RenderFilter;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import common.Constant;
import common.ExcelUtil;

import dto.LateEarlyDto;
import dto.MP1001Dto;
import entity.DetailDayWorkTime;
import entity.EachCircleDay;
import entity.MP1001;
import entity.WorkTimePattern;

public class ItService extends ActionSupport {
	private static final Log log = LogFactory.getLog(LoginAction.class);
	
	public static String pdfFile = "";
	public static String toTextFile = "";
	public static String textFile2 = "";
	
	public String filename;
	public String downloadFile;
	
	public ExecuteJobsService serviceExecuteJobs;
	public MP1001Service serviceMP1001;
	public String empCode;
	
	private List<LateEarlyDto> lateEarlyList = new ArrayList<LateEarlyDto>();
	
	private WorkTimePatternService serviceWorkTimePattern;
	private EachCircleDayService serviceEachCircleDay;
	private DetailDayWorkTimeService serviceDetailDayWorkTime;
	
	public String itServiceMngInit(){
		try{
			System.out.println("in itServiceMngInit()");
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public void parsePdf(String pdf, String txt) throws IOException {
		try{
			//PrintWriter out = new PrintWriter(new FileOutputStream(txt));
			PdfReader reader= new PdfReader(pdf);
			PdfReaderContentParser parser = new PdfReaderContentParser(reader);
			TextExtractionStrategy strategy;
			
			/*
			Rectangle rect = new Rectangle(0, 0, 20, 20);
			RenderFilter filter = new RegionTextRenderFilter(rect);
			strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
			System.out.println(PdfTextExtractor.getTextFromPage(reader, 1, strategy));
			
			System.out.println("-----segment------");
			rect = new Rectangle(20, 0, 40, 20);
			filter = new RegionTextRenderFilter(rect);
			strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
			System.out.println(PdfTextExtractor.getTextFromPage(reader, 1, strategy));
			
			System.out.println("-----segment2------");
			rect = new Rectangle(40, 0, 60, 20);
			filter = new RegionTextRenderFilter(rect);
			strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
			System.out.println(PdfTextExtractor.getTextFromPage(reader, 1, strategy));
			
			System.out.println("-----segment3------");
			rect = new Rectangle(60, 0, 150, 20);
			filter = new RegionTextRenderFilter(rect);
			strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
			System.out.println(PdfTextExtractor.getTextFromPage(reader, 1, strategy));
			
			System.out.println("-----segment4------");
			rect = new Rectangle(100, 0, 200, 20);
			filter = new RegionTextRenderFilter(rect);
			strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
			System.out.println(PdfTextExtractor.getTextFromPage(reader, 1, strategy));
			
			System.out.println("-----segment5------");
			rect = new Rectangle(200, 0, 300, 20);
			filter = new RegionTextRenderFilter(rect);
			strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
			System.out.println(PdfTextExtractor.getTextFromPage(reader, 1, strategy));
			
			System.out.println("-------whole line content-----------");
			rect = new Rectangle(0, 0, 700, 20);
			filter = new RegionTextRenderFilter(rect);
			strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
			System.out.println(PdfTextExtractor.getTextFromPage(reader, 1, strategy));
			*/
			/*for(int x = 20; x < 480; x+=20){
				for(int y = 20; y < 500; y+=20){
					rect.setBottom();
					rect.setRight(x);
					System.out.println("---------Y: " + i);
			}*/
			
			Rectangle rect = new Rectangle(0, 0, 700, 580);
			RenderFilter filter = new RegionTextRenderFilter(rect);
			for(int i = 1; i <= reader.getNumberOfPages(); i++){
				strategy = parser.processContent(i, new LocationTextExtractionStrategy());
				System.out.println(strategy.getResultantText());
				//System.out.println(strategy.getResultantText());
				//System.out.println("-----------first page-----------");
				//System.out.println(PdfTextExtractor.getTextFromPage(reader, 1, strategy));
				//System.out.println("-----------second page-----------");
				//System.out.println(PdfTextExtractor.getTextFromPage(reader, 2, strategy));
			}
			//out.flush();
			//out.close();
			/*byte bWrite [] = {11,21,3,4,5,100};
			OutputStream os = new FileOutputStream(txt);
			for(int x = 0; x < bWrite.length; x++){
				os.write(bWrite[x]);
			}
			os.close();
			*/
			
			reader.close();
			
		}catch(Exception ex){
			log.info(ex.getMessage());
		}
	}
	
	public String convertPdfToText(){
		try{
			System.out.println("in convertPdfToText()");
			/*
			String _path = ServletActionContext.getServletContext().getRealPath("/") + "uploadfile\\02.txt";
			InputStream is = new FileInputStream(_path);
			int size = is.available();
			for(int i = 0; i < size; i++){
				System.out.println((char)is.read() + " ");
			}
			is.close();
			*/
			String _contextPath = ServletActionContext.getServletContext().getRealPath("/");
			pdfFile = _contextPath + "uploadfile\\01.pdf";
			toTextFile = _contextPath + "uploadfile\\01.txt";
			parsePdf(pdfFile, toTextFile);
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	
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
	
	
	public String joeTools(){
		try{
			System.out.println("joe tools");
			
			return SUCCESS;
		}
		catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}

	public InputStream getDownloadFile(){
		try {
			//System.out.println("in getDownloadFile() function");
			//filename = "late-and-early2.xlsx";
			//InputStream is = ServletActionContext.getServletContext().getResourceAsStream("d:\\" + filename);
			InputStream is;
			
			is = new FileInputStream(new File("d:\\late-and-early3.xlsx"));
			return is;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String documentDownload(){
		System.out.println("in documentDownload() function");
		return SUCCESS;
	}
	
	public String downloadLateAndEarlyExcel(){
		try{
			System.out.println("in downloadLateAndEarlyExcel() function");
			String _path = "d:\\late-and-early2.xlsx";
			String _path_save = "d:\\late-and-early3.xls";
			//File f = new File("d:\\late-and-early2.xls");
			//InputStream is = new FileInputStream(f);
			
			
			Workbook wb = ExcelUtil.openExcelFile(_path);
			GetCellContent(wb);
			//is.close();
			
			Workbook wb2 = ExcelUtil.CreateXSSFWorkBook();
			createShiftWorkExcelTemplate(wb2);
	        ExcelUtil.createExcelFile(_path_save, wb2);
	        
			return SUCCESS;
		}
		catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public void GetCellContent(Workbook wb) throws ParseException {
		Sheet sheet1 = wb.getSheetAt(0);
		//sheet1.iterator().next(); //skip header line
    	System.out.println("sheet1 " + sheet1.getLastRowNum());
    	SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
    	Calendar cal = Calendar.getInstance();
    	Calendar calInOut = Calendar.getInstance();
    	int cellCount = 0;
    	LateEarlyDto recordDto = null;
    	boolean firstRowFlag = true;
	    outterLoop: for (Row row : sheet1) {
	    	cellCount = 0;
	    	recordDto = new LateEarlyDto();
	    	
	       innerLoop: for (Cell cell : row) {
	            CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
	            System.out.print(cellRef.formatAsString());
	            System.out.print(" - ");
	            cellCount++;
	            switch (cell.getCellType()) {
	                case Cell.CELL_TYPE_STRING:
	                	String cellString = cell.getRichStringCellValue().getString();
	                	//skip head row
	                	if(firstRowFlag){
	                		firstRowFlag = false;
	                		break innerLoop;
	                	}
	                	
	                	if(1 == cellCount){
	                		recordDto.setEmployeeNum(cellString);
	                		break ;
	                	}
	                	if(2 == cellCount){
	                		recordDto.setDate(sdfDate.parse(cellString));
	                		break ;
	                	}
	                	if(3 == cellCount){
	                		recordDto.setClockInTime(sdfDateTime.parse(cellString));
	                		break ;
	                	}
	                	if(4 == cellCount){
	                		recordDto.setClockOutTime(sdfDateTime.parse(cellString));
	                		break ;
	                	}
	                	if(5 == cellCount){
	                		recordDto.setPreferedName(cellString);
	                		break ;
	                	}
	                	if(6 == cellCount){
	                		recordDto.setSurname(cellString);
	                		break ;
	                	}
	                	if(7 == cellCount){
	                		recordDto.setDepartmentName(cellString);
	                		break ;
	                	}
	                	if(8 == cellCount){
	                		recordDto.setStatus(cellString);
	                		break ;
	                	}
	                	
	                    break;
	                case Cell.CELL_TYPE_NUMERIC:
		                    if (DateUtil.isCellDateFormatted(cell)) {
		                    	Date d = cell.getDateCellValue();
		                    	if(2 == cellCount){
		                		recordDto.setDate(d);
		                		break ;
		                	}
		                	if(3 == cellCount){
		                		recordDto.setClockInTime(d);
		                		break ;
		                	}
		                	if(4 == cellCount){
		                		recordDto.setClockOutTime(d);
		                		break ;
		                	}
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
	    	if(null != recordDto.getDate()){
		    	Date in = recordDto.getClockInTime();
		                		
	    		cal.setTime(recordDto.getDate()); //this is start
	    		cal.set(Calendar.HOUR_OF_DAY, 8);
	    		cal.set(Calendar.MINUTE, 0);
	    		cal.set(Calendar.SECOND, 0);
	    		
	    		calInOut.setTime(in);
	    		//Date startTime = cal.getTime();
	    		//if(in.after(startTime)){
	    		if(cal.before(calInOut)){
	    			Long s = cal.getTime().getTime();
	    			Long i = in.getTime();
	    			recordDto.setLateMins((int)((i - s)/(1000*60)));
	    		}
		    	
	    		Date out = recordDto.getClockOutTime();
		         
	    		calInOut.setTime(out);
	    		cal.setTime(recordDto.getDate()); //this is end
	    		cal.set(Calendar.HOUR_OF_DAY, 16);
	    		cal.set(Calendar.MINUTE, 30);
	    		cal.set(Calendar.SECOND, 0);
	    		//Date startTime = cal.getTime();
	    		//if(in.after(startTime)){
	    		if(cal.after(calInOut)){
	    			Long s = cal.getTime().getTime();
	    			Long o = out.getTime();
	    			recordDto.setEarlyMins((int)((s - o)/(1000*60)));
	    		}
	    		
	    		int e = recordDto.getEarlyMins();
	    		int l = recordDto.getLateMins();
	    		
	    		recordDto.setAllMins(l + e);
	    		
	    		lateEarlyList.add(recordDto);
	    	}
	    }
	}
	public void createShiftWorkExcelTemplate(Workbook wb){
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
		headCellWidth.add(20);
		headCellWidth.add(10);
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
	        	datas[2] = sdf.format(lateEarlyList.get(c).getClockInTime());
	        	datas[3] = sdf.format(lateEarlyList.get(c).getClockOutTime());
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
	
	public String lookupEmployeeById(){
		try{
			HttpServletResponse response = ServletActionContext.getResponse();
//			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			String str = null;
			// List<MP1001> lst =
			MP1001 employee = serviceMP1001.findById(empCode);
			MP1001Dto dto = convertMP1001ToDto(employee);
//			ObjectMapper mapper = new ObjectMapper();
//			str = mapper.writeValueAsString(dto);
			
//			JSONObject jsonObj = new JSONObject();
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, dto);
			
			System.out.println(str);
			out.print(str);
			out.flush();
			out.close();
		} catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return NONE;
	}
	public MP1001Dto convertMP1001ToDto(MP1001 emp){
		MP1001Dto dto = new MP1001Dto();
		dto.setMP1001_DEPARTMENT_ID(emp.getMP1001_DEPARTMENT_ID());
		dto.setMP1001_DEPARTMENT_NAME(emp.getMP1001_DEPARTMENT_NAME());
		dto.setMP1001_EMPLOYEE_NUM(emp.getMP1001_EMPLOYEE_NUM());
		dto.setMP1001_EMPLOYEE_ID(emp.getMP1001_EMPLOYEE_ID());
		dto.setMP1001_FIRSTNAME(emp.getMP1001_FIRSTNAME());
		dto.setMP1001_PREFERED_NAME(emp.getMP1001_PREFERED_NAME());
		dto.setMP1001_SURNAME(emp.getMP1001_SURNAME());
		return dto;
	}
	public String lookupAllEmployees(){
		try{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			List<MP1001> lst = serviceMP1001.findAll();
			// MP1001 employee = serviceMP1001.findById(empCode);
			ObjectMapper mapper = new ObjectMapper();
			OutputStream outStream = new ByteArrayOutputStream();
			mapper.writeValue(outStream, lst);
			// final byte[] data = outStream.toByteArray();
			String str = outStream.toString();
			// str = mapper.writeValueAsString();
			System.out.println(str);
			out.print(str);
			out.flush();
			out.close();
			return NONE;
		} catch(Exception ex){
			System.out.println(ex.getMessage());
			return ERROR;
		}
	}

	public String lateEarlyJobTest(){
		
//		executeJobs jobs = new executeJobs();
		
//		jobs.executeJob12();
		
//		jobs.executeJob20();
		
		serviceExecuteJobs.fetchOtherBranchSiteRecords();
		
		return NONE;
	}
	
	
	public String addDefaultShiftWorkerWTP(){
		try{
			WorkTimePattern wtp = new WorkTimePattern();
			wtp.setName("Shift Worker Time");
			wtp.setDescription("For security shift workers");
			wtp.setCircleDays(9);
			wtp.setCalPubHolidayFlag(false);
			wtp.setCalSpecialDayFlag(false);
			wtp.setApplyLeaveIgnorePublicHolidayFlag(false);
			wtp.setInitialAnnualLeaveDays(0.0);
			wtp.setAddAnnualHoursPM(1.75);
			wtp.setInitialSickLeaveDays(36.0);
			wtp.setAddSickHoursPM(0.0);
			wtp.setDayWorkHours(12.0);
			serviceWorkTimePattern.save(wtp);
			
			EachCircleDay cd = new EachCircleDay();
			cd.setDaySeq(1);
			cd.setName("First Day Shift");
			cd.setWorkTimePattern(wtp);
			cd.setOverTimeRate(1.5);
			serviceEachCircleDay.save(cd);
			
			EachCircleDay cd1 = new EachCircleDay();
			cd1.setDaySeq(2);
			cd1.setName("Second Day Shift");
			cd1.setWorkTimePattern(wtp);
			cd1.setOverTimeRate(1.5);
			serviceEachCircleDay.save(cd1);
			
			EachCircleDay cd2 = new EachCircleDay();
			cd2.setDaySeq(3);
			cd2.setName("Third Day Shift");
			cd2.setWorkTimePattern(wtp);
			cd2.setOverTimeRate(1.5);
			serviceEachCircleDay.save(cd2);
			
			EachCircleDay cd3 = new EachCircleDay();
			cd3.setDaySeq(4);
			cd3.setName("First Night Shift");
			cd3.setWorkTimePattern(wtp);
			cd3.setOverTimeRate(1.5);
			serviceEachCircleDay.save(cd3);
			
			EachCircleDay cd4 = new EachCircleDay();
			cd4.setDaySeq(5);
			cd4.setName("Second Night Shift");
			cd4.setWorkTimePattern(wtp);
			cd4.setOverTimeRate(1.5);
			serviceEachCircleDay.save(cd4);
			
			EachCircleDay cd5 = new EachCircleDay();
			cd5.setDaySeq(6);
			cd5.setName("Third Night Shift");
			cd5.setWorkTimePattern(wtp);
			cd5.setOverTimeRate(1.5);
			serviceEachCircleDay.save(cd5);
			
			EachCircleDay cd6 = new EachCircleDay();
			cd6.setDaySeq(7);
			cd6.setName("First Rest Shift");
			cd6.setWorkTimePattern(wtp);
			cd6.setOverTimeRate(1.5);
			serviceEachCircleDay.save(cd6);
			
			EachCircleDay cd7 = new EachCircleDay();
			cd7.setDaySeq(8);
			cd7.setName("Second Rest Shift");
			cd7.setWorkTimePattern(wtp);
			cd7.setOverTimeRate(1.5);
			serviceEachCircleDay.save(cd7);
			
			EachCircleDay cd8 = new EachCircleDay();
			cd8.setDaySeq(9);
			cd8.setName("Third Rest Shift");
			cd8.setWorkTimePattern(wtp);
			cd8.setOverTimeRate(1.5);
			serviceEachCircleDay.save(cd8);

			//set detail work time
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); //format date
			SimpleDateFormat sdf_0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //format date

			//first day shift
			DetailDayWorkTime dt = new DetailDayWorkTime();
//			dt.setFromTime(sdf.parse(Constant.shiftDayStartWorkTime_0));
//			dt.setToTime(sdf.parse(Constant.shiftDayEndWorkTime_0));
			dt.setStartTime(Constant.shiftDayStartWorkTime);
			dt.setDurationTime(720);
			dt.setName("First Day Shift");
			dt.setCircleDay(cd);
			serviceDetailDayWorkTime.save(dt);
			
			//second day shift
			dt = new DetailDayWorkTime();
//			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_0));
//			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_0));
			dt.setStartTime(Constant.shiftDayStartWorkTime);
			dt.setDurationTime(720);
			dt.setName("Second Day Shift");
			dt.setCircleDay(cd1);
			serviceDetailDayWorkTime.save(dt);
			
			//third day shift
			dt = new DetailDayWorkTime();
			dt.setStartTime(Constant.shiftDayStartWorkTime);
			dt.setDurationTime(720);
			dt.setName("Third Day Shift");
			dt.setCircleDay(cd2); //Wensday
			serviceDetailDayWorkTime.save(dt);
			
			//first night shift
			dt = new DetailDayWorkTime();
			dt.setStartTime(Constant.shiftNightStartWorkTime);
			dt.setDurationTime(720);
			dt.setName("First Night shift");
			dt.setCircleDay(cd3); //Thursday
			serviceDetailDayWorkTime.save(dt);

			//second night shift
			dt = new DetailDayWorkTime();
			dt.setStartTime(Constant.shiftNightStartWorkTime);
			dt.setDurationTime(720);
			dt.setName("second night shift");
			dt.setCircleDay(cd4); //Friday
			serviceDetailDayWorkTime.save(dt);
			
			//third night shift
			dt = new DetailDayWorkTime();
			dt.setStartTime(Constant.shiftNightStartWorkTime);
			dt.setDurationTime(720);
			dt.setName("Third night shift");
			dt.setCircleDay(cd5); //Friday
			serviceDetailDayWorkTime.save(dt);
			
			//firt rest shift
//			dt = new DetailDayWorkTime();
//			dt.setStartTime(Constant.shiftNightStartWorkTime);
//			dt.setDurationTime(720);
//			dt.setName("Third night shift");
//			dt.setCircleDay(cd4); //Friday
//			serviceDetailDayWorkTime.save(dt);
			

			
		} catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		return NONE;
	}
	
	public String transportShiftWorkTimePattern(){
		
		
		
		return NONE;
	}
	
	public String fetchAttendanceRcd() throws Exception {
		serviceExecuteJobs.fetchAttendanceRecords();
		return SUCCESS;
	}
	
	public String transportWorkTimePatter() throws Exception {
		serviceExecuteJobs.getAttendanceCalculator().transportAllEmployeeWorkTime();
		return SUCCESS;
	}
	
	public static Log getLog() {
		return log;
	}

	public static String getPdfFile() {
		return pdfFile;
	}

	public static void setPdfFile(String pdfFile) {
		ItService.pdfFile = pdfFile;
	}

	public static String getToTextFile() {
		return toTextFile;
	}

	public static void setToTextFile(String toTextFile) {
		ItService.toTextFile = toTextFile;
	}

	public static String getTextFile2() {
		return textFile2;
	}

	public static void setTextFile2(String textFile2) {
		ItService.textFile2 = textFile2;
	}

	

	public void setDownloadFile(String downloadFile) {
		this.downloadFile = downloadFile;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<LateEarlyDto> getLateEarlyList() {
		return lateEarlyList;
	}

	public void setLateEarlyList(List<LateEarlyDto> lateEarlyList) {
		this.lateEarlyList = lateEarlyList;
	}

	public ExecuteJobsService getServiceExecuteJobs() {
		return serviceExecuteJobs;
	}

	public void setServiceExecuteJobs(ExecuteJobsService serviceExecuteJobs) {
		this.serviceExecuteJobs = serviceExecuteJobs;
	}

	public MP1001Service getServiceMP1001() {
		return serviceMP1001;
	}

	public void setServiceMP1001(MP1001Service serviceMP1001) {
		this.serviceMP1001 = serviceMP1001;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public WorkTimePatternService getServiceWorkTimePattern() {
		return serviceWorkTimePattern;
	}

	public void setServiceWorkTimePattern(
			WorkTimePatternService serviceWorkTimePattern) {
		this.serviceWorkTimePattern = serviceWorkTimePattern;
	}

	public EachCircleDayService getServiceEachCircleDay() {
		return serviceEachCircleDay;
	}

	public void setServiceEachCircleDay(EachCircleDayService serviceEachCircleDay) {
		this.serviceEachCircleDay = serviceEachCircleDay;
	}

	public DetailDayWorkTimeService getServiceDetailDayWorkTime() {
		return serviceDetailDayWorkTime;
	}

	public void setServiceDetailDayWorkTime(
			DetailDayWorkTimeService serviceDetailDayWorkTime) {
		this.serviceDetailDayWorkTime = serviceDetailDayWorkTime;
	}
}