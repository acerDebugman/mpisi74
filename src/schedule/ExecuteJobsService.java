package schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import service.DetailDayWorkTimeService;
import service.ICHECKINOUTService;
import service.IFingerSiteUserIdInfoService;
import service.IMP0002Service;
import service.IMP0006Service;
import service.IMP1001Service;
import service.IMP2003Service;
import webapi.RemoteAttendanceRecordMngClientSide;
import webapi.RemoteCheckInOutMng;

import common.AttendanceCalculator;
import common.Constant;
import common.CustomerContextHolder;
import common.Mail;
import common.UtilCommon;

import dto.AttendanceRecordDto;
import dto.CheckInOutDto;
import entity.CHECKINOUT;
import entity.FingerSiteUserIdInfo;
import entity.MP0002;
import entity.MP0006;
import entity.MP1001;
import entity.MP2003;

public class ExecuteJobsService {
	private ICHECKINOUTService serviceCheckInOut;
	private IMP1001Service serviceMP1001;
	private IMP0002Service serviceMP0002;
	private IMP0006Service serviceMP0006;
	private IMP2003Service serviceMP2003;
	private RemoteCheckInOutMng remoteCheckRecordsMng;
	private IFingerSiteUserIdInfoService serviceFingerSiteUseridInfo;
	private RemoteAttendanceRecordMngClientSide serviceRemoteClient; 
	
	
	private DetailDayWorkTimeService serviceDetailDayWorkTime;
	private AttendanceCalculator attendanceCalculator;

	public List<CHECKINOUT> getManagerCheckInRcds(){
		String startTime = " 07:00:00";
		String endTime = " 09:00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		startTime = sdf.format(new Date()) + startTime;
		endTime = sdf.format(new Date()) + endTime;
		
		List<CHECKINOUT> lst = null;
		try{
			CustomerContextHolder.setCustomerType("finger");
			
			String sql = " and checkinout.SENSORID='200' and checkinout.CHECKTIME >='" + startTime + "' and checkinout.CHECKTIME <='" + endTime + "' " + 
			" and userinfo.SSN in (" + Constant.managerMeetingGroupCodeList + ")";
			
			lst = serviceCheckInOut.findDataBySelfDefined(sql, " order by checkinout.USERID,checkinout.CHECKTIME asc", 0, 0);
			
			CustomerContextHolder.remove();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			CustomerContextHolder.remove();
		}
		
		//only keep the earlist records
		CHECKINOUT oldRd = null;
		List<CHECKINOUT> newLst = new ArrayList<CHECKINOUT>();
		for(CHECKINOUT tmp : lst){
			if(null == oldRd){
				newLst.add(tmp);
			}
			if(null != oldRd && tmp.getUSERID() != oldRd.getUSERID()){
				newLst.add(tmp);
			}
			oldRd = tmp;
		}
		
		//order by time
		for(int i = 0, j = newLst.size(); i < j - 1; i++){
			for(int k = 0; k < j - i - 1; k++){
				CHECKINOUT  t = newLst.get(k);
				CHECKINOUT next = newLst.get(k + 1);
				
				if(t.getCHECKTIME().compareTo(next.getCHECKTIME()) > 0){
					newLst.remove(t);
					newLst.add(k + 1, t);
				}
			}
		}

		
		return newLst;
	}
	
	public String produceMngMeetingMailContent(List<CHECKINOUT> lst){
		MP1001 emp = null;
		MP0002 dept = null;
		StringBuffer sb = new StringBuffer();
		sb.append("<h3><b>&nbsp;&nbsp;&nbsp;<span style='color:red;font-size:40'>" + lst.size() 
				+ "</span>&nbsp;&nbsp;people are present today: </b></h3>");
		
		sb.append("<table cellspacing='1' cellpadding='3' align='left' width='100%'");
		for(CHECKINOUT rd : lst){
			String empCode = rd.getEMPLOYEE_NUM();
			if("A0001".equalsIgnoreCase(empCode)){
				emp = new MP1001();
				emp.setMP1001_EMPLOYEE_NUM("---");
				emp.setMP1001_PREFERED_NAME("L129");
				emp.setMP1001_SURNAME("---");
				dept.setMP0002_DEPARTMENT_NAME("---");
			}
			else{
				emp = serviceMP1001.findById(empCode);
				dept = serviceMP0002.findById(Integer.parseInt(emp.getMP1001_DEPARTMENT_ID()));
			}
			
			sb.append("<tr align='left'>");
			sb.append("<td width='8%'>" + dept.getMP0002_DEPARTMENT_NAME()  + "</td>");
			sb.append("<td width='4%'>" + emp.getMP1001_EMPLOYEE_NUM()  + "</td>");
			sb.append("<td width='7%'>" + emp.getMP1001_PREFERED_NAME() + "</td>");
			sb.append("<td width='7%'>" + emp.getMP1001_SURNAME() + "</td>");
			sb.append("<td width='15%'>" + rd.getCHECKTIME().substring(0, 19) + "</td>");
			sb.append("<td width='59%'></td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		sb.append("<br>");
		
		return sb.toString();
	}
	
	//every 5 minutes fetch data from finger DB,  
	public void fetchAttendanceRecords() throws Exception {
		//compare the records first, only today, time and amount
		Date today = new Date(); //today's date
		CHECKINOUT latestRd = null;
		long latestCounter = 0;
		try{
			CustomerContextHolder.setCustomerType("finger");
		
//			List<CHECKINOUT> lst = serviceCheckInOut.fetchAllDayRecords(new Date());
			latestRd = serviceCheckInOut.fetchLatestDayRecord(today);
			latestCounter = serviceCheckInOut.fetchTotalRecordsCounter();
			
			CustomerContextHolder.remove();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		finally{
			CustomerContextHolder.remove();
		}
		
		
		//1. compare the latest date, if they're not same, fetch the whole day data. if the same, do nothing~
		MP0006 mp06 = serviceMP0006.findByProperty("MP0006_CODE", "LATEST_CHECKTIME").get(0); //first one is ok
		MP0006 mp06Counter = serviceMP0006.findByProperty("MP0006_CODE", "LATEST_COUNTER").get(0); //first one is ok
		if(null == latestRd || 
			   (null != latestRd && attendanceCalculator.datetimeCompare(latestRd.getCHECKTIME(), mp06.getMP0006_VALUE(), "ss") <= 0)){
			
			long ct = Long.parseLong(mp06Counter.getMP0006_VALUE());
			if(latestCounter > ct){ //this means maybe some older data inserted to the database, such as download the data mannualy from print machine
				//here only calculate yester day data
				Calendar calFrom = Calendar.getInstance();;
				calFrom.add(Calendar.DAY_OF_MONTH, -1); //set to yesterday
				Calendar calEnd = Calendar.getInstance(); 
				calEnd.setTime(today);
				
				//blow part copy from mannually calculate
				List<MP1001> lst = new ArrayList<MP1001>();
	        
	        	List<MP1001> oldEmpList = serviceMP1001.pickUpRequiredPresentEmployeeList();
				Set<String> shiftWorkerSet = CommonJobMethod.getAllShiftWorkEmployeeNums(today);
				Iterator it = shiftWorkerSet.iterator();
				Map<String, Boolean> shiftWorkerMap = new HashMap<String, Boolean>();
				while(it.hasNext()){
					String code = (String)it.next();
					shiftWorkerMap.put(code, true);
				}
				for(MP1001 tmp : oldEmpList){ //transport to other list
					if(!shiftWorkerMap.containsKey(tmp.getMP1001_EMPLOYEE_NUM())){
						lst.add(tmp);
					}
				}
		        
		        //delete records of exempt employees
	        	Map<String, Boolean> exemptMap = Constant.pickUpPresentExemptList();
	        	
//	        	while(attendanceCalculator.datetimeCompare(calFrom.getTime(), calEnd.getTime(), "dd") <= 0){//can calculate today, //@here because this kind of problem always happen at night
	        	while(attendanceCalculator.datetimeCompare(calFrom.getTime(), calEnd.getTime(), "dd") < 0){//do calculte now, but calculate later,don't return
	        		for(MP1001 emp: lst){ //employee list should be inside, it's not easy to reverse the date again
		        		attendanceCalculator.proceed(calFrom.getTime(), emp);
		        		attendanceCalculator.calculatePresenceStatus(calFrom.getTime(), emp);
		        	}
	        		
	        		//delete
	        		for(Map.Entry<String, Boolean> entry : exemptMap.entrySet()){
		        		String s = entry.getKey();
		        		MP1001 emp = serviceMP1001.findById(s);
		        		MP2003 mp23 = serviceMP2003.findByDateAndEmp(calFrom.getTime(), emp);
		        		if(null != mp23){
		        			serviceMP2003.delete(mp23);
		        		}
	        		}
	        		
	        		calFrom.add(Calendar.DAY_OF_MONTH, 1); //add one day
		        }
	        	
	        	
	        	//update latest counter
	        	mp06Counter.setMP0006_VALUE(String.valueOf(latestCounter));
	        	serviceMP0006.update(mp06Counter);
			}
			else{
				return ;
			}
		}
		
		//get all employees
//		List<MP1001> empList = serviceMP1001.findAll();
		List<MP1001> oldEmpList = serviceMP1001.pickUpRequiredPresentEmployeeList();
		/*this part for seprate shift work employees temporary******/
		Set<String> shiftWorkerSet = CommonJobMethod.getAllShiftWorkEmployeeNums(today);
		List<MP1001> empList = new ArrayList<MP1001>();
		Iterator it = shiftWorkerSet.iterator();
		Map<String, Boolean> shiftWorkerMap = new HashMap<String, Boolean>();
		while(it.hasNext()){
			String code = (String)it.next();
			shiftWorkerMap.put(code, true);
		}
		for(MP1001 tmp : oldEmpList){ //transport to other list
			if(!shiftWorkerMap.containsKey(tmp.getMP1001_EMPLOYEE_NUM())){
				empList.add(tmp);
			}
		}
		/*<<<<<<<<<<<<<<<<<<<<*/
		
		//2. get all checktime records
		List<CheckInOutDto> checkInOutRecordsList = attendanceCalculator.getAllDayAttendanceRecords(empList, today);
		List<AttendanceRecordDto> dailyRecords = attendanceCalculator.separateIntoEachDay(checkInOutRecordsList); //get all checkInOut records
		
		
//		else update latest datetime first
		mp06.setMP0006_VALUE(latestRd.getCHECKTIME().substring(0, 19));
		serviceMP0006.update(mp06);
		
		Map<String, AttendanceRecordDto> mapCacheAttendanceRecord = new HashMap<String, AttendanceRecordDto>();
		for(AttendanceRecordDto recd : dailyRecords){
			String strKey = recd.getEmployeeNum() + "#" + recd.getWhichDate();
			mapCacheAttendanceRecord.put(strKey, recd);
		}

		//it's not good, here, every time need to calculate all, should pick out who need to calcualte
		//but every night still need to calculte standard work time for tomorrow
		for(MP1001 emp : empList){   
			attendanceCalculator.proceed(today, emp); //need calculate for instant change the status
			MP2003 mp23 = attendanceCalculator.calculateTodayPresenceStatus(today, emp, mapCacheAttendanceRecord);
			//fetch 
			MP2003 oldMp23 = attendanceCalculator.fetchMP2003Record(today, emp);
			
			if(null != oldMp23 && null != mp23){
				oldMp23.setMP2003_START_TIME(mp23.getMP2003_START_TIME());
				oldMp23.setMP2003_START_TIME_DOOR(mp23.getMP2003_START_TIME_DOOR());
				oldMp23.setMP2003_FINISH_TIME(mp23.getMP2003_FINISH_TIME());
				oldMp23.setMP2003_FINISH_TIME_DOOR(mp23.getMP2003_FINISH_TIME_DOOR());
				oldMp23.setMP2003_COMMENT(mp23.getMP2003_COMMENT());
				oldMp23.setMP2003_STATUS(mp23.getMP2003_STATUS());
				serviceMP2003.update(oldMp23);
			}
			else{
				if(null != mp23){
					serviceMP2003.save(mp23);
				}
			}
			
		}
		
		//delete exempt employees
//		Map<String, Boolean> exemptMap = Constant.pickUpPresentExemptList();
//
//		for(Map.Entry<String, Boolean> entry : exemptMap.entrySet()){
//    		String s = entry.getKey();
//    		MP1001 emp = serviceMP1001.findById(s);
//    		MP2003 mp23 = serviceMP2003.findByDateAndEmp(today, emp);
//    		serviceMP2003.delete(mp23);
//		}
		
	}

	
	//every 5 minutes fetch data from finger DB , this one only calculate who clock, can't calculate who't didn't clock
	public void fetchTodayIncrementAttendanceRecords() throws Exception {
		//compare the records first, only today, time and amount
		Date today = new Date(); //today's date
		CHECKINOUT latestRd = null;
		try{
			CustomerContextHolder.setCustomerType("finger");
			
//			List<CHECKINOUT> lst = serviceCheckInOut.fetchAllDayRecords(new Date());
			latestRd = serviceCheckInOut.fetchLatestDayRecord(today);
			
			CustomerContextHolder.remove();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		finally{
			CustomerContextHolder.remove();
		}
		
		//1. compare the latest date, if they're not same, fetch the whole day data. if the same, do nothing~
		MP0006 mp06 = serviceMP0006.findByProperty("MP0006_CODE", "LATEST_CHECKTIME").get(0); //first one is ok
		if(null == latestRd || 
				(null != latestRd && attendanceCalculator.datetimeCompare(latestRd.getCHECKTIME(), mp06.getMP0006_VALUE(), "ss") <= 0)){
			return ;
		}
		
		//2. get all checktime records
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentTimeStamp = sdf.parse(mp06.getMP0006_VALUE());
		List<CheckInOutDto> checkInOutRecordsList = attendanceCalculator.fetchTodayAttendanceRecords(currentTimeStamp);
		StringBuffer sb = new StringBuffer();
		for(CheckInOutDto dto : checkInOutRecordsList){
			sb.append("'" + dto.getEmployeeNum() + "',");
		}
		sb.delete(sb.length() - 1, sb.length());
		List<MP1001> empList = serviceMP1001.findByEmployeeStringList(sb.toString());
		
		List<AttendanceRecordDto> dailyRecords = attendanceCalculator.separateIntoEachDay(checkInOutRecordsList); //get all checkInOut records
		
		
//		else update latest datetime first
//		mp06.setMP0006_VALUE(latestRd.getCHECKTIME().substring(0, 19)); //this time stamp is not precise
		mp06.setMP0006_VALUE(checkInOutRecordsList.get(checkInOutRecordsList.size() - 1).getCheckTime().substring(0, 19));//shoud set to the lastest checktime
		serviceMP0006.update(mp06);
		
		Map<String, AttendanceRecordDto> mapCacheAttendanceRecord = new HashMap<String, AttendanceRecordDto>();
		for(AttendanceRecordDto recd : dailyRecords){
			String strKey = recd.getEmployeeNum() + "#" + recd.getWhichDate();
			mapCacheAttendanceRecord.put(strKey, recd);
		}

		//it's not good, here, every time need to calculate all, should pick out who need to calcualte
		//but every night still need to calculte standard work time for tomorrow
		for(MP1001 emp : empList){
			attendanceCalculator.proceed(today, emp); //need calculate for instant change the status
			MP2003 mp23 = attendanceCalculator.calculateTodayPresenceStatus(today, emp, mapCacheAttendanceRecord);
			//fetch 
			MP2003 oldMp23 = attendanceCalculator.fetchMP2003Record(today, emp);
			
			if(null != oldMp23 && null != mp23){
				oldMp23.setMP2003_START_TIME(mp23.getMP2003_START_TIME());
				oldMp23.setMP2003_START_TIME_DOOR(mp23.getMP2003_START_TIME_DOOR());
				oldMp23.setMP2003_FINISH_TIME(mp23.getMP2003_FINISH_TIME());
				oldMp23.setMP2003_FINISH_TIME_DOOR(mp23.getMP2003_FINISH_TIME_DOOR());
				oldMp23.setMP2003_COMMENT(mp23.getMP2003_COMMENT());
				oldMp23.setMP2003_STATUS(mp23.getMP2003_STATUS());
				serviceMP2003.update(oldMp23);
			}
			else{
				if(null != mp23){
					serviceMP2003.save(mp23);
				}
			}
			
		}
		
		//delete exempt employees
//		Map<String, Boolean> exemptMap = Constant.pickUpPresentExemptList();
//
//		for(Map.Entry<String, Boolean> entry : exemptMap.entrySet()){
//    		String s = entry.getKey();
//    		MP1001 emp = serviceMP1001.findById(s);
//    		MP2003 mp23 = serviceMP2003.findByDateAndEmp(today, emp);
//    		serviceMP2003.delete(mp23);
//		}
		
	}

	//every night 11:00 make standard work time for tomorow
	public void procudeTomorrowStandardWorktime(){
		try{
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date tomorrow = cal.getTime();
		
		List<MP1001> empList = serviceMP1001.findAll();
		for(MP1001 emp : empList){
			attendanceCalculator.proceed(today, emp); //test today
//			attendanceCalculator.proceed(tomorrow, emp); //
		}
		} catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public void test(){
//		attendanceCalculator.proceedSpecialDay(null, null);
		try{
			MP1001 emp = serviceMP1001.findById("M0432");
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-07-31 00:00:00");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			for(int i = 0; i < 5 ; i++){
				attendanceCalculator.proceed(cal.getTime(), emp);
				
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
//			attendanceCalculator.transportAllEmployeeWorkTime();
			System.out.println("finish calculator...");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void test_1(){
//		attendanceCalculator.proceedSpecialDay(null, null);
		try{
			MP1001 emp = serviceMP1001.findById("M0432");
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-08-01 00:00:00");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			for(int i = 0; i < 2 ; i++){
				attendanceCalculator.proceed(cal.getTime(), emp);
				attendanceCalculator.calculatePresenceStatus(cal.getTime(), emp);
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
//			attendanceCalculator.transportAllEmployeeWorkTime();
			System.out.println("finish calculator...");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	
	public void test_2(){
		procudeTomorrowStandardWorktime();
	}
	
	public void test_3(){
		try{
			fetchAttendanceRecords();
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	public void sendAbnormalEmailToEmployee(Date date){
		String to = "";
		
		List<MP2003> abnormalList = serviceMP2003.pickUpAbnormalRecordsList(date);
		
		for(MP2003 mp23 : abnormalList){
			if(!UtilCommon.checkDirector(mp23.getMP2003_EMPLOYEE_NUM().toUpperCase())){
				MP1001 emp = serviceMP1001.findById(mp23.getMP2003_EMPLOYEE_NUM());
			
				to = emp.getMP1001_COMPANY_EMAIL();
				
				Mail mail = new Mail();
				mail.setSubject("Attendance Records(Abnormal)");
				mail.setContent("Dear Colleagues,\r\n \r\n Please note that your attendance record is abnormal on " + mp23.getMP2003_DATETIME());
				mail.setTo(to);
				mail.send();
			}
		}
		
		return ;
	}
	
	
	//every 2 hours fetch data from other branch site, start from 06:00 to 22:00
	public void fetchOtherBranchSiteRecords(){
		
		List<FingerSiteUserIdInfo> lst = serviceFingerSiteUseridInfo.findAll();
		
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date fromDate = cal.getTime();
		Date endDate = today;
		
		for(FingerSiteUserIdInfo item : lst){
			serviceRemoteClient.findByFingerSiteUserIdInfo(item, fromDate, endDate);
		}

		//find first, 
		
		//if exist, do nothing
		
	}
	
	public void transportShiftWorkerToTimePattern(){
		
	}

	
//-------------------------------------------------------------------	
	public ICHECKINOUTService getServiceCheckInOut() {
		return serviceCheckInOut;
	}

	public void setServiceCheckInOut(ICHECKINOUTService serviceCheckInOut) {
		this.serviceCheckInOut = serviceCheckInOut;
	}

	public IMP1001Service getServiceMP1001() {
		return serviceMP1001;
	}

	public void setServiceMP1001(IMP1001Service serviceMP1001) {
		this.serviceMP1001 = serviceMP1001;
	}

	public IMP0002Service getServiceMP0002() {
		return serviceMP0002;
	}

	public void setServiceMP0002(IMP0002Service serviceMP0002) {
		this.serviceMP0002 = serviceMP0002;
	}

	public DetailDayWorkTimeService getServiceDetailDayWorkTime() {
		return serviceDetailDayWorkTime;
	}

	public void setServiceDetailDayWorkTime(
			DetailDayWorkTimeService serviceDetailDayWorkTime) {
		this.serviceDetailDayWorkTime = serviceDetailDayWorkTime;
	}

	public AttendanceCalculator getAttendanceCalculator() {
		return attendanceCalculator;
	}

	public void setAttendanceCalculator(AttendanceCalculator attendanceCalculator) {
		this.attendanceCalculator = attendanceCalculator;
	}

	public IMP0006Service getServiceMP0006() {
		return serviceMP0006;
	}

	public void setServiceMP0006(IMP0006Service serviceMP0006) {
		this.serviceMP0006 = serviceMP0006;
	}

	public IMP2003Service getServiceMP2003() {
		return serviceMP2003;
	}

	public void setServiceMP2003(IMP2003Service serviceMP2003) {
		this.serviceMP2003 = serviceMP2003;
	}

	public RemoteCheckInOutMng getRemoteCheckRecordsMng() {
		return remoteCheckRecordsMng;
	}

	public void setRemoteCheckRecordsMng(RemoteCheckInOutMng remoteCheckRecordsMng) {
		this.remoteCheckRecordsMng = remoteCheckRecordsMng;
	}

	public IFingerSiteUserIdInfoService getServiceFingerSiteUseridInfo() {
		return serviceFingerSiteUseridInfo;
	}

	public void setServiceFingerSiteUseridInfo(
			IFingerSiteUserIdInfoService serviceFingerSiteUseridInfo) {
		this.serviceFingerSiteUseridInfo = serviceFingerSiteUseridInfo;
	}

	public RemoteAttendanceRecordMngClientSide getServiceRemoteClient() {
		return serviceRemoteClient;
	}

	public void setServiceRemoteClient(
			RemoteAttendanceRecordMngClientSide serviceRemoteClient) {
		this.serviceRemoteClient = serviceRemoteClient;
	}
	
}