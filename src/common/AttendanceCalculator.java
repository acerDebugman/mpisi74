package common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import schedule.CommonJobMethod;
import service.ICHECKINOUTService;
import service.IEmpWorkTimePattern_RService;
import service.IHOLIDAYService;
import service.IMP0010Service;
import service.IMP1001Service;
import service.IMP2001Service;
import service.IMP2003Service;
import service.IStandardWorkTimeService;
import service.IWorkTimePatternService;
import dto.AttendanceRecordDto;
import dto.CheckInOutDto;
import entity.CHECKINOUT;
import entity.DetailDayWorkTime;
import entity.EachCircleDay;
import entity.EmpWorkTimePattern_R;
import entity.MP0010;
import entity.MP1001;
import entity.MP2001;
import entity.MP2003;
import entity.StandardWorkTime;
import entity.WorkTimePattern;

public class AttendanceCalculator {
	private IStandardWorkTimeService serviceStandardWorkTime;
	private IHOLIDAYService serviceHoliday;
	private IMP2001Service serviceMP2001;
	private IMP0010Service serviceMP0010;
	private IMP1001Service serviceMP1001;
	private IWorkTimePatternService serviceWorkTimePattern;
	private IEmpWorkTimePattern_RService serviceEWTP_R;
	private ICHECKINOUTService serviceCHECKINOUT;
	private IMP2003Service serviceMP2003;

	
	private SimpleDateFormat sdf_0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat sdf_1 = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sdf_2 = new SimpleDateFormat("HH:mm:ss");
	
	
	//calculate which day and wich employee
	public void proceed(Date date, MP1001 emp) throws ParseException {
		/*
		 *here need to check if it's first date or not
		 */
		Calendar calDate = Calendar.getInstance();
		calDate.setTime(date);
		calDate.set(Calendar.HOUR_OF_DAY, 0);
		calDate.set(Calendar.MINUTE, 0);
		calDate.set(Calendar.SECOND, 0);
		
		StandardWorkTime item = new StandardWorkTime();
//		item.setComments("Normal");
		item.setComments(" ");
		
		//work out the specified day standard work time
		EmpWorkTimePattern_R ew_r = emp.getEmpWorkTimePattern_R();
		if(null == ew_r){
			return ;
		}
		WorkTimePattern wtp = ew_r.getWorkTimePattern();
		if(null == wtp){
			return ;
		}
		boolean crossDayFlag = false;
		
		//the speical is which circle day ?
		int initialCircleDayIdx = ew_r.getInitialCircleDayIdx();
		Date initialDay = ew_r.getStartDate();
		Calendar initialCal = Calendar.getInstance();
		initialCal.setTime(initialDay);
		initialCal.set(Calendar.HOUR_OF_DAY, 0);
		initialCal.set(Calendar.MINUTE, 0);
		initialCal.set(Calendar.SECOND, 0);
		
		if(sdf_1.format(date).compareTo(sdf_1.format(initialDay)) < 0){
			System.out.println("Work time pattern start day is later than the specified day !");
			return ;
		}
		
		int dayCounter = 0;
//		while(calDate.equals(initialCal)){ //can't use this, because of the mili-second is not right~
		while(!sdf_1.format(date).equals(sdf_1.format(initialCal.getTime()))){
			initialCal.add(Calendar.DAY_OF_MONTH, 1);
			dayCounter ++;
		}
		//start date to the specified date plus the initial circle day index
		int circleDayIdx = (dayCounter + initialCircleDayIdx) % wtp.getCircleDays();
		
		EachCircleDay cd = null;
		EachCircleDay followingCd = null;
		Iterator<EachCircleDay> it = wtp.getAllCircleDays().iterator();
		while(it.hasNext()){
//			cd = wtp.getAllCircleDays().iterator().next();
			cd = it.next();
			if(cd.getDaySeq() == circleDayIdx){
				followingCd = wtp.getAllCircleDays().iterator().next(); //get the following circle day
				break;
			}
		}
		
		
		List<DetailDayWorkTime> list = new ArrayList(cd.getDetailWorkTimeItems());
		if(0 == list.size()){ //can't find DetailDayRecords, it should be Saturday or Sunday 
			if(Calendar.SATURDAY == calDate.get(Calendar.DAY_OF_WEEK)){ //
				item.setComments("Sat");
			}
			else if(Calendar.SUNDAY == calDate.get(Calendar.DAY_OF_WEEK)){ //
				item.setComments("Sun");
			}
			//if it's not Saturday or Sunday...
			item.setStdClockInTime(new Date(0));
			item.setStdClockOutTime(new Date(0));
		}
		else{
			//here need to compare the mid night or non midnight, currently only support shiftwork mode
//			if(sdf_2.format(list.get(0).getFromTime()).equals("00:00:00")){
////				item.setStdClockInTime();
//				item.setStdClockOutTime(sdf_0.parse(sdf_1.format(date) + " " + sdf_2.format(list.get(0).getToTime())));
//			}
			
			item.setStdClockInTime(sdf_0.parse(sdf_1.format(date) + " " + sdf_2.format(list.get(0).getFromTime())));  //pattern's first start time
			item.setStdClockOutTime(sdf_0.parse(sdf_1.format(date) + " " + sdf_2.format(list.get(list.size() - 1).getToTime()))); //pattern's last end time
		}
		item.setCircleDay(cd); //set to that circle day
		item.setDayDate(calDate.getTime());
		item.setEmployee(emp);
		
		
		//public holiday
		if(wtp.isCalPubHolidayFlag()){
			item = proceedPublicHoliday(date, item);
		}
		
		//get special day
		if(wtp.isCalSpecialDayFlag()){
			item = proceedSpecialDay(date, item);
		}
		
		//fetch leave application
		item = proceedLeaveApplication(date, emp, item);
		
		
		//delete old one if exist
		StandardWorkTime oldOne = serviceStandardWorkTime.fetchSpecificDayWorkTime(emp, date);
		if(null != oldOne){
			if(null == oldOne.getManuallyChangeFlag() || !oldOne.getManuallyChangeFlag()){
				serviceStandardWorkTime.delete(oldOne);
				serviceStandardWorkTime.save(item);
			}
			else{
				System.out.println("can't change the records because it has been mannualy changed!");
			}
		}
		else{
			serviceStandardWorkTime.save(item);	
		}
		//save to StandardWorkTime table
		//if(){
			
		//}
		
		//fetch attendance records and compare work time, then insert into mp2002 status table
		
	}
	

	public StandardWorkTime proceedLeaveApplication(Date date, MP1001 emp, StandardWorkTime item) throws ParseException { //do not change date, return value is return addr or value ?
		if(item.getStdClockInTime().getTime() == 0 && item.getStdClockOutTime().getTime() == 0){
			return item; //have no need to do this
		}
		
//		MP1001 m = serviceMP1001.findById("M0432");
//		Date d = sdf_0.parse("2014-05-28 00:00:00");
//		List<MP2001> lst = serviceMP2001.locateAppliationByOneDate(d, m);
		List<MP2001> lst = serviceMP2001.locateAppliationByOneDate(date, emp);
		
		Iterator<MP2001> it = lst.iterator();
		while(it.hasNext()){
			MP2001 app = it.next();
			if(app.getMP2001_APPROVAL().equals("3")){ //only calculate approved application
				//leave application form - end time
				Date fromDatetime = sdf_0.parse(app.getMP2001_FROM_DATETIME());
				Calendar calAppFrom = Calendar.getInstance();
				calAppFrom.setTime(fromDatetime);
				Date endDatetime = sdf_0.parse(app.getMP2001_TO_DATETIME());
				Calendar calAppEnd = Calendar.getInstance();
				calAppEnd.setTime(endDatetime);
				//standard clock in/out time
				Calendar calDate = Calendar.getInstance();
				calDate.setTime(date);
				Calendar calIn = Calendar.getInstance();
				calIn.setTime(item.getStdClockInTime());
				Calendar calOut = Calendar.getInstance();
				calOut.setTime(item.getStdClockOutTime());
				
				if(datetimeCompare(calAppFrom.getTime(), calAppEnd.getTime(), "dd") == 0){
					if((datetimeCompare(calAppFrom.getTime(), calIn.getTime(), "mm") == 0 && datetimeCompare(calAppEnd.getTime(), calOut.getTime(), "mm") == 0) ||
							(datetimeCompare(calAppFrom.getTime(), calIn.getTime(), "mm") < 0 && datetimeCompare(calAppEnd.getTime(), calOut.getTime(), "mm") > 0) ||
							(datetimeCompare(calAppFrom.getTime(), calIn.getTime(), "mm") == 0 && datetimeCompare(calAppEnd.getTime(), calOut.getTime(), "mm") > 0) ||
							(datetimeCompare(calAppFrom.getTime(), calIn.getTime(), "mm") < 0 && datetimeCompare(calAppEnd.getTime(), calOut.getTime(), "mm") == 0)){
						calIn.setTime(new Date(0));
						calOut.setTime(new Date(0));
					}
					else if(datetimeCompare(calAppFrom.getTime(), calIn.getTime(), "mm") == 0 && 
							datetimeCompare(calAppEnd.getTime(), calOut.getTime(), "mm") < 0){
						EachCircleDay cd = item.getCircleDay();
						List<DetailDayWorkTime> dList = new ArrayList(cd.getDetailWorkTimeItems());
						
						if(2 == dList.size()){
							if(datetimeCompare(calAppEnd.getTime(), dList.get(0).getToTime(), "mm") > 0 && 
									datetimeCompare(calAppEnd.getTime(), dList.get(1).getFromTime(), "mm") < 0){
//								calOut.setTime(dList.get(0).getToTime());
								calIn.setTime(dList.get(1).getFromTime());
							}
							else{
//								calOut.setTime(calAppEnd.getTime());
								calIn.setTime(calAppEnd.getTime());
							}
						}
						else if(3 == dList.size()){ //only support 3 work piece time
							if(datetimeCompare(calAppEnd.getTime(), dList.get(0).getToTime(), "mm") > 0 && 
									datetimeCompare(calAppEnd.getTime(), dList.get(1).getFromTime(), "mm") < 0){
//								calOut.setTime(dList.get(0).getToTime());
								calIn.setTime(dList.get(1).getFromTime());
							}
							else if(datetimeCompare(calAppEnd.getTime(), dList.get(1).getToTime(), "mm") > 0 && 
									datetimeCompare(calAppEnd.getTime(), dList.get(2).getFromTime(), "mm") < 0){
//								calOut.setTime(dList.get(1).getToTime());
								calIn.setTime(dList.get(2).getFromTime());
							}
							else{
//								calOut.setTime(calAppEnd.getTime());
								calIn.setTime(calAppEnd.getTime());
							}
						}
						else{
//							calOut.setTime(calAppEnd.getTime());
							calIn.setTime(calAppEnd.getTime());
						}
						
					}
					else if(datetimeCompare(calAppFrom.getTime(), calIn.getTime(), "mm") > 0 && 
							datetimeCompare(calAppEnd.getTime(), calOut.getTime(), "mm") == 0){
						EachCircleDay cd = item.getCircleDay();
						List<DetailDayWorkTime> dList = new ArrayList(cd.getDetailWorkTimeItems());
						if(2 == dList.size()){
							if(datetimeCompare(calAppFrom.getTime(), dList.get(0).getToTime(), "mm") > 0 && 
									datetimeCompare(calAppFrom.getTime(), dList.get(1).getToTime(), "mm") < 0){
//								calIn.setTime(dList.get(1).getFromTime());
								calOut.setTime(dList.get(0).getToTime());
							}
							else{
//								calIn.setTime(calAppFrom.getTime());
								calOut.setTime(calAppFrom.getTime());
							}
							
						}
						else if(3 == dList.size()){ //only support 3 work piece time
							if(datetimeCompare(calAppFrom.getTime(), dList.get(0).getToTime(), "mm") > 0 && 
									datetimeCompare(calAppFrom.getTime(), dList.get(1).getToTime(), "mm") < 0){
//								calIn.setTime(dList.get(1).getFromTime());
								calOut.setTime(dList.get(0).getToTime());
							}
							else if(datetimeCompare(calAppFrom.getTime(), dList.get(1).getToTime(), "mm") > 0 && 
									datetimeCompare(calAppFrom.getTime(), dList.get(2).getToTime(), "mm") < 0){
//								calIn.setTime(dList.get(2).getFromTime());
								calOut.setTime(dList.get(1).getToTime());
							}
							else{
//								calIn.setTime(calAppFrom.getTime());
								calOut.setTime(calAppFrom.getTime());
							}
						}
						else{
//							calIn.setTime(calAppFrom.getTime());
							calOut.setTime(calAppFrom.getTime());
						}
					}
					//else{} //application between standard clock in/out time 
				}
				else{ //cross days
					if(calAppFrom.get(Calendar.YEAR) == calDate.get(Calendar.YEAR) && 
							calAppFrom.get(Calendar.MONTH) == calDate.get(Calendar.MONTH) &&
							calAppFrom.get(Calendar.DAY_OF_MONTH) == calDate.get(Calendar.DAY_OF_MONTH)){ //first day of leave application equals this day  
//						if(calAppFrom.after(calIn)){ //if fist day after standard clock in time, then change to the leave from time
						if(datetimeCompare(calAppFrom.getTime(), calIn.getTime(), "mm") > 0){
//							calIn.setTime(calAppFrom.getTime());
							calOut.setTime(calAppFrom.getTime());
						}
						else{
							calIn.setTime(new Date(0));
							calOut.setTime(new Date(0));
						}
					}
					else if(calAppEnd.get(Calendar.YEAR) == calDate.get(Calendar.YEAR) && 
							calAppEnd.get(Calendar.MONTH) == calDate.get(Calendar.MONTH) &&
							calAppEnd.get(Calendar.DAY_OF_MONTH) == calDate.get(Calendar.DAY_OF_MONTH)){ //last day of leave application equals this day  
//						if(calAppEnd.before(calOut)){
						if(datetimeCompare(calAppEnd.getTime(), calOut.getTime(), "mm") < 0){
//							calOut.setTime(calAppEnd.getTime());
							calIn.setTime(calAppEnd.getTime());
						}
						else{
							calIn.setTime(new Date(0));
							calOut.setTime(new Date(0));
						}
					}
					else{ //the day is in the between the application's start day and end day  
						calIn.setTime(new Date(0));
						calOut.setTime(new Date(0));
					}
				}
				
				item.setStdClockInTime(calIn.getTime());
				item.setStdClockOutTime(calOut.getTime());
				item.setComments("Leave");
			}
		}

		return item;
	}
	
	//work time pattern has many special day, according to StandardWorkTime.circleDay can get WorkTimePattern
	public StandardWorkTime proceedSpecialDay(Date date, StandardWorkTime item) throws ParseException {
		if(item.getStdClockInTime().getTime() == 0 && item.getStdClockOutTime().getTime() == 0){
			return item; //have no need to do this
		}
//		StandardWorkTime item1 = serviceStandardWorkTime.findById(1);

		List<MP0010> allSpecialDays = serviceStandardWorkTime.fetchAllPatternSpecialDays(item); //get all special days of specified worktimepattern
		Iterator<MP0010> it = allSpecialDays.iterator();
		while(it.hasNext()){
			MP0010 sd = it.next();
			if(sdf_1.format(date).equalsIgnoreCase(sd.getMP0010_DATETIME())){ //find the special day, set to special day
				String spStart = sdf_1.format(date) + " " + sd.getMP0010_START_TIME() + ":00";
				String spEnd = sdf_1.format(date) + " " + sd.getMP0010_END_TIME() + ":00";
				
				item.setStdClockInTime(sdf_0.parse(spStart));
				item.setStdClockOutTime(sdf_0.parse(spEnd));
				item.setComments("Special Day");
			}
		}
		return item;
	}
	
	public StandardWorkTime proceedPublicHoliday(Date date, StandardWorkTime item){
		if(item.getStdClockInTime().getTime() == 0 && item.getStdClockOutTime().getTime() == 0){
			return item; //have no need to do this
		}
		
		//is Saturday or Sunday
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		//is national public holiday
		if(serviceHoliday.isPublicHoliday(date)){
			item.setStdClockInTime(new Date(0)); //should be set to 1970-01-01 02:00:00, because the time zone, but the getTime() == 0
			item.setStdClockOutTime(new Date(0));
			item.setComments("Public Holiday");
		}
		else if(Calendar.SATURDAY == cal.get(Calendar.DAY_OF_WEEK)){
			item.setStdClockInTime(new Date(0)); //should be set to 1970-01-01 02:00:00, because the time zone, but the getTime() == 0
			item.setStdClockOutTime(new Date(0));
			item.setComments("Sat");
		}
		else if(Calendar.SUNDAY == cal.get(Calendar.DAY_OF_WEEK)){ //Saturday
			item.setStdClockInTime(new Date(0)); //should be set to 1970-01-01 02:00:00, because the time zone, but the getTime() == 0
			item.setStdClockOutTime(new Date(0));
			item.setComments("Sun");
		}
		
		return item;
	}

	
	//calculate present status
	//this function can't calculate today's presence
	public void calculatePresenceStatus(Date date, MP1001 emp) throws ParseException {
		//get standard work time 
		StandardWorkTime stdItem = serviceStandardWorkTime.fetchSpecificDayWorkTime(emp, date);
		if(null == stdItem){ //no standard work time, then return;
			return ;
		}
		Date epochTime = new Date(0);
//		if(!hasItStartedWorking(stdItem)){ //if it has stared work, begin to calculute
//			return ; //if right now is   
//		}

		List<CHECKINOUT> recordsList = null;
		//get that day attendance records
		try{
			CustomerContextHolder.setCustomerType("finger");
			
			recordsList = serviceCHECKINOUT.fetchEmployeeDailyRecords(date, emp);
					
			CustomerContextHolder.remove();
		} catch(Exception ex){
			System.out.println(ex.getMessage());
			return ;
		} finally {
			CustomerContextHolder.remove();
		}

		
//------------same logic-------------------------------------------------------------------		
		StringBuffer sb = new StringBuffer();
		sb.append(" ");
//		sb.append(stdItem.getComments());
		String startTime = null;
		String startTimeDoor = "";
		String finishTime = null;
		String finishTimeDoor = ""; 
		String status = "";
		
		MP2003 mp23 = new MP2003();
		mp23.setMP2003_DATETIME(sdf_1.format(date));
		mp23.setMP2003_EMPLOYEE_NAME(emp.getMP1001_PREFERED_NAME());
		mp23.setMP2003_EMPLOYEE_NUM(emp.getMP1001_EMPLOYEE_NUM());
		
		int size = recordsList.size();
//		if(stdItem.getStdClockInTime().equals(epochTime) || stdItem.getStdClockOutTime().equals(epochTime)){ //no work today,holiday, special day, or leave
		if(datetimeCompare(stdItem.getStdClockInTime(), epochTime, "ss") == 0 || 
				datetimeCompare(stdItem.getStdClockOutTime(), epochTime, "ss") == 0){ //no work today,holiday, special day, or leave. this approach is better
			if(0 == size){//and no records
				sb.append(" ");
			}
			else if(1 == size){
				startTime = recordsList.get(0).getCHECKTIME().substring(0, 19); //holiday , so default put them in start time position
				startTimeDoor = recordsList.get(0).getSENSORID();
				sb.append(" / Present");
			}
			else {
				startTime = recordsList.get(0).getCHECKTIME().substring(0, 19);
				startTimeDoor = recordsList.get(0).getSENSORID();
				finishTime = recordsList.get(size - 1).getCHECKTIME().substring(0, 19);
				finishTimeDoor = recordsList.get(size - 1).getSENSORID(); 
				sb.append(" / Present");
			}
		}
		else{
			if(0 == size){//and no records
				sb.append(" / No Records Abnormal");
				status = "1";
			}
			else if(1 == size){
				CHECKINOUT clockInRd = pickUpClockInRecord(stdItem, recordsList);
				
				if(null != clockInRd && isLateClockIn(clockInRd, stdItem)){
					int mins = deliverLateMinutes(clockInRd, stdItem);
					stdItem.setLateMinutes(mins);
					serviceStandardWorkTime.update(stdItem);
					sb.append(" / Late " + translateTimeToHumanLikeWay(mins) + " & Abnormal");
					status = "1";
				}
				
				//get last record
				CHECKINOUT clockOutRd = pickUpClockOutRecord(stdItem, recordsList);
				if(null != clockOutRd && isEarlyClockOut(clockOutRd, stdItem)){
					int mins = deliverEarlyMinutes(clockOutRd, stdItem);
					stdItem.setEarlyMinutes(mins);
					serviceStandardWorkTime.update(stdItem);
					sb.append(" / Early " + translateTimeToHumanLikeWay(mins) + " & Abnormal");
					status = "1";
				}
				
				if(null != clockInRd){
					startTime = clockInRd.getCHECKTIME().substring(0, 19);
					startTimeDoor = clockInRd.getSENSORID();
					sb.append(" / Abnormal No Clock Out"); //only have clock in records, no clockout records
					status = "1";
				}
				if(null != clockOutRd){
					finishTime = clockOutRd.getCHECKTIME().substring(0, 19);
					finishTimeDoor = clockOutRd.getSENSORID();
					sb.append(" / Abnormal No Clock In");
					status = "1";
				}
				
			}
			else {
				//sort out 
				
				//get first record
				CHECKINOUT clockInRd = pickUpClockInRecord(stdItem, recordsList);
				if(null != clockInRd && isLateClockIn(clockInRd, stdItem)){
					int mins = deliverLateMinutes(clockInRd, stdItem);
					stdItem.setLateMinutes(mins);
					serviceStandardWorkTime.update(stdItem);
					sb.append(" / Late " + translateTimeToHumanLikeWay(mins));
				}
				
				//get last record
				CHECKINOUT clockOutRd = pickUpClockOutRecord(stdItem, recordsList);
				if(null != clockOutRd && isEarlyClockOut(clockOutRd, stdItem)){
					int mins = deliverEarlyMinutes(clockOutRd, stdItem);
					stdItem.setEarlyMinutes(mins);
					serviceStandardWorkTime.update(stdItem);
					sb.append(" / Early " + translateTimeToHumanLikeWay(mins));
				}
				
				if(null != clockInRd){
					startTime = clockInRd.getCHECKTIME().substring(0, 19);
					startTimeDoor = clockInRd.getSENSORID();
				}
				else{
					sb.append(" / Abnormal No Clock In");
					status = "1";
				}
				if(null != clockOutRd){
					finishTime = clockOutRd.getCHECKTIME().substring(0, 19);
					finishTimeDoor = clockOutRd.getSENSORID();
				}
				else{
					sb.append(" / Abnormal No Clock Out");
					status = "1";
				}
			}
		}

//------------same logic-------------------------------------------------------------------		
		
		mp23.setMP2003_START_TIME(startTime);
		mp23.setMP2003_START_TIME_DOOR(startTimeDoor);
		mp23.setMP2003_FINISH_TIME(finishTime);
		mp23.setMP2003_FINISH_TIME_DOOR(finishTimeDoor);
		if(stdItem.getComments().equals(" ") || stdItem.getComments().equals("")){
			sb = sb.delete(0, 3); //delete "/" 
		}
		mp23.setMP2003_COMMENT(stdItem.getComments() + sb.toString());
		mp23.setMP2003_STATUS(status);
//		serviceMP2003.save(mp23);
		
		//logic add to here
		MP2003 oldMp23 = fetchMP2003Record(date, emp);
		
		
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
	
	
	//calculate with given data
	public MP2003 calculateTodayPresenceStatus(Date date, MP1001 emp, Map<String, AttendanceRecordDto> dataMap) throws ParseException {
//		dataMap
		List<CHECKINOUT> recordsList = new ArrayList<CHECKINOUT>();
		if(dataMap.containsKey(emp.getMP1001_EMPLOYEE_NUM() + "#" + sdf_1.format(date))){
			List<CheckInOutDto> lst = null; 
			lst = dataMap.get(emp.getMP1001_EMPLOYEE_NUM() + "#" + sdf_1.format(date)).getCheckRecordsList();
			for(CheckInOutDto dto : lst){
				CHECKINOUT c = new CHECKINOUT();
				c.setCHECKTIME(dto.getCheckTime());
				c.setSENSORID(dto.getDoorId());
				recordsList.add(c);
			}
		}
		
		//get standard work time 
		StandardWorkTime stdItem = serviceStandardWorkTime.fetchSpecificDayWorkTime(emp, date);
		Date epochTime = new Date(0);
		
		if(!hasItStartedWorking(stdItem)){ //if it has stared work, begin to calculute
			return null; //if right now is   
		}

		
		//get that day attendance records
//		try{
//			CustomerContextHolder.setCustomerType("finger");
//			
//			recordsList = serviceCHECKINOUT.fetchEmployeeDailyRecords(date, emp);
//					
//			CustomerContextHolder.remove();
//		} catch(Exception ex){
//			System.out.println(ex.getMessage());
//		} finally {
//			CustomerContextHolder.remove();
//		}

		
//------------same logic-------------------------------------------------------------------		
		StringBuffer sb = new StringBuffer();
		sb.append(" ");
//		sb.append(stdItem.getComments());
//		sb.append("/");
		String startTime = null;
		String startTimeDoor = "";
		String finishTime = null;
		String finishTimeDoor = ""; 
		String status = "";
		
		MP2003 mp23 = new MP2003();
		mp23.setMP2003_DATETIME(sdf_1.format(date));
		mp23.setMP2003_EMPLOYEE_NAME(emp.getMP1001_PREFERED_NAME());
		mp23.setMP2003_EMPLOYEE_NUM(emp.getMP1001_EMPLOYEE_NUM());
		
		int size = recordsList.size();
//		if(stdItem.getStdClockInTime().equals(epochTime) || stdItem.getStdClockOutTime().equals(epochTime)){ //no work today,holiday, special day, or leave
		if(datetimeCompare(stdItem.getStdClockInTime(), epochTime, "ss") == 0 || 
				datetimeCompare(stdItem.getStdClockOutTime(), epochTime, "ss") == 0){ //no work today,holiday, special day, or leave
			if(0 == size){//and no records
				sb.append(" ");
			}
			else if(1 == size){
				startTime = recordsList.get(0).getCHECKTIME().substring(0, 19); //holiday , so default put them in start time position
				startTimeDoor = recordsList.get(0).getSENSORID();
				sb.append(" / Present");
			}
			else {
				startTime = recordsList.get(0).getCHECKTIME().substring(0, 19);
				startTimeDoor = recordsList.get(0).getSENSORID();
				finishTime = recordsList.get(size - 1).getCHECKTIME().substring(0, 19);
				finishTimeDoor = recordsList.get(size - 1).getSENSORID(); 
				sb.append(" / Present");
			}
		}
		else{
			
//			if(-1 != stdItem.getComments().indexOf("Leave") 
//							|| -1 != stdItem.getComments().indexOf("Special") 
//							|| -1 != stdItem.getComments().indexOf("Sat") 
//							|| -1 != stdItem.getComments().indexOf("Sun") 
//							|| -1 != stdItem.getComments().indexOf("Public Holiday")){
//				//do nothing~
//				sb.append(" /"); //later will sb.delete(0,3)
//			}
//			else {
				if(0 == size){//and no records
					if(!hasItStartedWorking(stdItem)){
						sb.append(" ");
					}
					else if(hasItStartedWorking(stdItem) && !hasItStoppedWorking(stdItem)){
						sb.append(" / No Clock In Records");
					}
					else { //no @ holiday
						sb.append(" / No Records Abnormal");
						status = "1";
					}
				}
				else if(1 == size){
					CHECKINOUT clockInRd = pickUpClockInRecord(stdItem, recordsList);
					
					if(null != clockInRd && isLateClockIn(clockInRd, stdItem)){
						int mins = deliverLateMinutes(clockInRd, stdItem);
						stdItem.setLateMinutes(mins);
						serviceStandardWorkTime.update(stdItem);
						//should save to stadard work item
						sb.append(" / Late " + translateTimeToHumanLikeWay(mins));
					}
					
					//get last record
					CHECKINOUT clockOutRd = pickUpClockOutRecord(stdItem, recordsList);
					if(null != clockOutRd && isEarlyClockOut(clockOutRd, stdItem)){
						int mins = deliverEarlyMinutes(clockOutRd, stdItem);
						stdItem.setEarlyMinutes(mins);
						serviceStandardWorkTime.update(stdItem);
						sb.append(" / Early " + translateTimeToHumanLikeWay(mins));
					}
					
					if(null != clockInRd){
						startTime = clockInRd.getCHECKTIME().substring(0, 19);
						startTimeDoor = clockInRd.getSENSORID();
						if(!hasItStoppedWorking(stdItem)){
							sb.append(" ");
						}
						else{
							sb.append(" / Abnormal No Clock Out"); //only have clock in records, no clockout records
							status = "1";
						}
					}
					if(null != clockOutRd){
						finishTime = clockOutRd.getCHECKTIME().substring(0, 19);
						finishTimeDoor = clockOutRd.getSENSORID();
						sb.append(" / Abnormal No Clock In"); 
						status = "1";
					}
					
				}
				else {
	
					//sort out
					
					//get first record
					CHECKINOUT clockInRd = pickUpClockInRecord(stdItem, recordsList);
					if(null != clockInRd && isLateClockIn(clockInRd, stdItem)){
						int mins = deliverLateMinutes(clockInRd, stdItem);
						stdItem.setLateMinutes(mins); //actually have no need to service.update, because it's Persistence object
						serviceStandardWorkTime.update(stdItem);
						sb.append(" / Late " + translateTimeToHumanLikeWay(mins));
					}
					
					//get last record
					CHECKINOUT clockOutRd = pickUpClockOutRecord(stdItem, recordsList);
					if(null != clockOutRd && isEarlyClockOut(clockOutRd, stdItem)){
						int mins = deliverEarlyMinutes(clockOutRd, stdItem);
						stdItem.setEarlyMinutes(mins);
						serviceStandardWorkTime.update(stdItem);
						sb.append(" / Early " + translateTimeToHumanLikeWay(mins));
					}
					
					if(null != clockInRd){
						startTime = clockInRd.getCHECKTIME().substring(0, 19);
						startTimeDoor = clockInRd.getSENSORID();
					}
					else{
						sb.append(" / Abnormal No Clock In");
						status = "1";
					}
					if(null != clockOutRd){
						finishTime = clockOutRd.getCHECKTIME().substring(0, 19);
						finishTimeDoor = clockOutRd.getSENSORID();
					}
					else{
						if(!hasItStoppedWorking(stdItem)){ //if didn't work off, have no need to add comment
							sb.append(" ");
						}
						else{
							sb.append(" / Abnormal No Clock Out");
							status = "1";
						}
					}
//				}
			}
		}
//------------same logic-------------------------------------------------------------------		
		
		mp23.setMP2003_START_TIME(startTime);
		mp23.setMP2003_START_TIME_DOOR(startTimeDoor);
		mp23.setMP2003_FINISH_TIME(finishTime);
		mp23.setMP2003_FINISH_TIME_DOOR(finishTimeDoor);
		if(stdItem.getComments().equals(" ") || stdItem.getComments().equals("")){
			sb = sb.delete(0, 3); //delete "/" 
		}
		mp23.setMP2003_COMMENT(stdItem.getComments() + sb.toString());
		mp23.setMP2003_STATUS(status);
//		serviceMP2003.save(mp23);
		return mp23;
	}
	
	public String translateTimeToHumanLikeWay(int mins){
		StringBuffer sb = new StringBuffer();
		
		int d = mins / (60 * 24);
		int h = mins / 60;
		int m = mins % 60;
		
		if(0 != d){
			sb.append(d + " day ");
		}
		if(0 != h){
			sb.append(h + " hours ");
		}
		if(0 != m){
			sb.append(m + " minutes ");
		}
		
		return sb.toString();
	}
	
	public boolean hasItStoppedWorking(StandardWorkTime stdItem){
		Date now = new Date();
		return datetimeCompare(now, stdItem.getStdClockOutTime(), "mm") > 0;
	}
	public boolean hasItStartedWorking(StandardWorkTime stdItem){
		Date now = new Date();
		return datetimeCompare(now, stdItem.getStdClockInTime(), "mm") >= 0;
	}
	
	public MP2003 fetchMP2003Record(Date date, MP1001 emp){
		MP2003 mp23 = new MP2003();
		mp23.setMP2003_EMPLOYEE_NUM(emp.getMP1001_EMPLOYEE_NUM());
		mp23.setMP2003_DATETIME(sdf_0.format(date));
//		return serviceMP2003.findByPropertys2(mp23).get(0);
		return serviceMP2003.findByDateAndEmp(date, emp);
	}
	
	//transport all employee time workTime pattern to default offiece work time and let them start @their join date 
	public void transportAllEmployeeWorkTime() throws ParseException {
		
		Map<String, Boolean> cleanLadyMap = new HashMap<String, Boolean>();
		cleanLadyMap.put("M0456", true);
		cleanLadyMap.put("M0105", true);
		cleanLadyMap.put("M0066", true);
		cleanLadyMap.put("M0265", true);
		cleanLadyMap.put("M0514", true);
		
//		List<MP1001> lst = serviceMP1001.findAll();
		List<MP1001> lst = serviceMP1001.findAbsolutelyAll();
		WorkTimePattern wtp = serviceWorkTimePattern.findById(1);
		WorkTimePattern wtp_1 = serviceWorkTimePattern.findById(2);
		for(MP1001 emp : lst){
			EmpWorkTimePattern_R ew_r = new EmpWorkTimePattern_R();
			ew_r.setEmployee(emp);
			if(cleanLadyMap.containsKey(emp.getMP1001_EMPLOYEE_NUM())){
				ew_r.setWorkTimePattern(wtp_1);
			}
			else{
				ew_r.setWorkTimePattern(wtp);
			}
			
			Date entryDate = sdf_1.parse(emp.getMP1001_ENTRY_DATE());
			Calendar cal = Calendar.getInstance();
			cal.setTime(entryDate);
			ew_r.setInitialCircleDayIdx(cal.get(Calendar.DAY_OF_WEEK) - 1); //Monday start @ 1
			ew_r.setStartDate(cal.getTime());
			
			serviceEWTP_R.save(ew_r);
		}
	}
	
	//according to standardWorkTime to pick up record, do not get data through records list
	public CHECKINOUT pickUpClockInRecord(StandardWorkTime stdItem, List<CHECKINOUT> recordsList) throws ParseException {
		CHECKINOUT rd = null;
		
		Date headPeriod = stdItem.getStdClockInTime();
		Calendar calHeader = Calendar.getInstance();
		calHeader.setTime(headPeriod);
		calHeader.add(Calendar.HOUR_OF_DAY, -3); //three hours before
//		Date endPeriod = new Date(0);
		Calendar calTailer = Calendar.getInstance();
//		calTailer.setTime(endPeriod);
		if(stdItem.getStdClockInTime().after(stdItem.getStdClockOutTime())){ //shift work
			Date d = sdf_0.parse(sdf_1.format(stdItem.getDayDate()) + " 23:59:59"); //set to mid-night
			calTailer.setTime(d);
		}
		else{//normal one
			Long stdOut = stdItem.getStdClockOutTime().getTime();
			Long stdIn = headPeriod.getTime();
			
			Long midTime = (stdOut + stdIn) / 2;
			calTailer.setTimeInMillis(midTime);
		}

		for(CHECKINOUT c : recordsList){ //list already in order
			Date d = sdf_0.parse(c.getCHECKTIME());
			if(datetimeCompare(d, calHeader.getTime(), "ss") >= 0 && datetimeCompare(d, calTailer.getTime(), "ss") <= 0){
				rd = c;
				break; //find the first records is ok~
			}
		}

		return rd;
	}

		//according to standardWorkTime to pick up record, do not get data through records list
	public CHECKINOUT pickUpClockOutRecord(StandardWorkTime stdItem, List<CHECKINOUT> recordsList) throws ParseException {
		CHECKINOUT rd = null;
		
		Calendar calTailer = Calendar.getInstance();
		calTailer.setTime(stdItem.getStdClockOutTime());
		calTailer .add(Calendar.HOUR_OF_DAY, 7); //three hours later

		Calendar calHeader = Calendar.getInstance();
		
		if(stdItem.getStdClockInTime().after(stdItem.getStdClockOutTime())){ //shift work
//			Date d = sdf_0.parse(sdf_1.format(stdItem.getDayDate()) + " 23:59:59"); //set to mid-night
			Date d = sdf_0.parse(sdf_1.format(stdItem.getDayDate()) + " 00:00:00");
			calHeader.setTime(d);
		}
		else{//normal one
			Long stdOut = stdItem.getStdClockOutTime().getTime();
			Long stdIn = stdItem.getStdClockInTime().getTime();
			
			Long midTime = (stdOut + stdIn) / 2;
			calHeader.setTimeInMillis(midTime);
		}

		for(CHECKINOUT c : recordsList){ //list already in order
			Date d = sdf_0.parse(c.getCHECKTIME());
			if(datetimeCompare(d, calHeader.getTime(), "ss") > 0 && datetimeCompare(d, calTailer.getTime(), "ss") <= 0){
				rd = c; //the last one
			}
		}

		return rd;
	}
	public Boolean isLateClockIn(CHECKINOUT clockInRd, StandardWorkTime stdItem) {
		return clockInRd.getCHECKTIME().substring(0, 16).compareTo(sdf_0.format(stdItem.getStdClockInTime()).substring(0, 16)) > 0;
	}
	
	public int deliverLateMinutes(CHECKINOUT clockInRd, StandardWorkTime stdItem) throws ParseException {
		Date d = sdf_0.parse(clockInRd.getCHECKTIME());
		Date d2 = stdItem.getStdClockInTime();
		int lateMins = 0;
		if(datetimeCompare(d, d2, "mm") > 0){
			Calendar cal = Calendar.getInstance();
			cal.setTime(d2);
			
			while(datetimeCompare(cal.getTime(), d, "mm") != 0){
				lateMins++;
				cal.add(Calendar.MINUTE, 1);
			}
		}
		
		return lateMins;
	}
	
	public Boolean isEarlyClockOut(CHECKINOUT clockOutRd, StandardWorkTime stdItem){
//		System.out.println("in isEarlyClockOut func: " + sdf_0.format(stdItem.getDayDate()));
		return clockOutRd.getCHECKTIME().substring(0, 16).compareTo(sdf_0.format(stdItem.getStdClockOutTime()).substring(0, 16)) < 0;
	}
	
	public int deliverEarlyMinutes(CHECKINOUT clockOutRd, StandardWorkTime stdItem) throws ParseException {
		Date d = sdf_0.parse(clockOutRd.getCHECKTIME());
		Date d2 = stdItem.getStdClockOutTime();
		int earlyMins = 0;
		if(datetimeCompare(d, d2, "mm") < 0){
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			
			while(datetimeCompare(cal.getTime(), d2, "mm") != 0){
				earlyMins++;
				cal.add(Calendar.MINUTE, 1);
			}
		}
		
		return earlyMins;
	}
	
	//time compare
	/*
	 * if d1 before d2 return < 0
	 * if d1 equal d2 return 0
	 * if d1 after d2 return > 0
	 */
	public int datetimeCompare(Date d1, Date d2, String precision){
		SimpleDateFormat sdf_y = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf_M = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sdf_d = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf_H = new SimpleDateFormat("yyyy-MM-dd HH");
		SimpleDateFormat sdf_m = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf_s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int rs = 0; //defualt 
		if(precision.equals("yyyy")){
			String sd1 = sdf_y.format(d1);
			String sd2 = sdf_y.format(d2);
			rs = sd1.compareTo(sd2);
		}
		else if(precision.equals("MM")){
			String sd1 = sdf_M.format(d1);
			String sd2 = sdf_M.format(d2);
			rs = sd1.compareTo(sd2);
		}
		else if(precision.equals("dd")){
			String sd1 = sdf_d.format(d1);
			String sd2 = sdf_d.format(d2);
			rs = sd1.compareTo(sd2);
		}
		else if(precision.equals("HH")){
			String sd1 = sdf_H.format(d1);
			String sd2 = sdf_H.format(d2);
			rs = sd1.compareTo(sd2);
		}
		else if(precision.equals("mm")){
			String sd1 = sdf_m.format(d1);
			String sd2 = sdf_m.format(d2);
			rs = sd1.compareTo(sd2);
		}
		else if(precision.equals("ss")){
			String sd1 = sdf_s.format(d1);
			String sd2 = sdf_s.format(d2);
			rs = sd1.compareTo(sd2);
		}
		return rs;
	}
	/*
	 * d2 format must be yyyy-MM-dd HH:mm:ss, length is 19
	 */
	public int datetimeCompare(Date d1, String d2, String precision){
		SimpleDateFormat sdf_y = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf_M = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sdf_d = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf_H = new SimpleDateFormat("yyyy-MM-dd HH");
		SimpleDateFormat sdf_m = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf_s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int rs = 0; //defualt 
		if(precision.equals("yyyy")){
			String sd1 = sdf_y.format(d1);
//			String sd2 = sdf_y.format(d2);
			String sd2 = d2.substring(0, 4);
			rs = sd1.compareTo(sd2);
		}
		else if(precision.equals("MM")){
			String sd1 = sdf_M.format(d1);
//			String sd2 = sdf_M.format(d2);
			String sd2 = d2.substring(0, 7);
			rs = sd1.compareTo(sd2);
		}
		else if(precision.equals("dd")){
			String sd1 = sdf_d.format(d1);
//			String sd2 = sdf_d.format(d2);
			String sd2 = d2.substring(0, 10);
			rs = sd1.compareTo(sd2);
		}
		else if(precision.equals("HH")){
			String sd1 = sdf_H.format(d1);
//			String sd2 = sdf_H.format(d2);
			String sd2 = d2.substring(0, 13);
			rs = sd1.compareTo(sd2);
		}
		else if(precision.equals("mm")){
			String sd1 = sdf_m.format(d1);
//			String sd2 = sdf_m.format(d2);
			String sd2 = d2.substring(0, 16);
			rs = sd1.compareTo(sd2);
		}
		else if(precision.equals("ss")){
			String sd1 = sdf_s.format(d1);
//			String sd2 = sdf_s.format(d2);
			String sd2 = d2.substring(0, 19);
			rs = sd1.compareTo(sd2);
		}
		return rs;
	}
	
	/*
	 * d1 format must be yyyy-MM-dd HH:mm:ss, length is 19
	 */
	public int datetimeCompare(String d1, Date d2, String precision){
		SimpleDateFormat sdf_y = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf_M = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sdf_d = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf_H = new SimpleDateFormat("yyyy-MM-dd HH");
		SimpleDateFormat sdf_m = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf_s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int rs = 0; //defualt 
		if(precision.equals("yyyy")){
//			String sd1 = sdf_y.format(d1);
			String sd1 = d1.substring(0, 4);
			String sd2 = sdf_y.format(d2);
			rs = sd1.compareTo(sd2);
		}
		else if(precision.equals("MM")){
//			String sd1 = sdf_M.format(d1);
			String sd1 = d1.substring(0, 7);
			String sd2 = sdf_M.format(d2);
//			String sd2 = d2.substring(0, 7);
			rs = sd1.compareTo(sd2);
		}
		else if(precision.equals("dd")){
//			String sd1 = sdf_d.format(d1);
			String sd1 = d1.substring(0, 10);
			String sd2 = sdf_d.format(d2);
//			String sd2 = d2.substring(0, 10);
			rs = sd1.compareTo(sd2);
		}
		else if(precision.equals("HH")){
//			String sd1 = sdf_H.format(d1);
			String sd1 = d1.substring(0, 13);
			String sd2 = sdf_H.format(d2);
//			String sd2 = d2.substring(0, 13);
			rs = sd1.compareTo(sd2);
		}
		else if(precision.equals("mm")){
//			String sd1 = sdf_m.format(d1);
			String sd1 = d1.substring(0, 16);
			String sd2 = sdf_m.format(d2);
//			String sd2 = d2.substring(0, 16);
			rs = sd1.compareTo(sd2);
		}
		else if(precision.equals("ss")){
//			String sd1 = sdf_s.format(d1);
			String sd1 = d1.substring(0, 19);
			String sd2 = sdf_s.format(d2);
//			String sd2 = d2.substring(0, 19);
			rs = sd1.compareTo(sd2);
		}
		return rs;
	}
	
	/*
	 * d1 and d2's format must be yyyy-MM-dd HH:mm:ss, length is 19
	 */
	public int datetimeCompare(String d1, String d2, String precision){
		SimpleDateFormat sdf_y = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf_M = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sdf_d = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf_H = new SimpleDateFormat("yyyy-MM-dd HH");
		SimpleDateFormat sdf_m = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf_s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int rs = 0; //defualt 
		if(precision.equals("yyyy")){
//			String sd1 = sdf_y.format(d1);
			String sd1 = d1.substring(0, 4);
//			String sd2 = sdf_y.format(d2);
			String sd2 = d2.substring(0, 4);
			rs = sd1.compareTo(sd2);
		}
		else if(precision.equals("MM")){
//			String sd1 = sdf_M.format(d1);
			String sd1 = d1.substring(0, 7);
//			String sd2 = sdf_M.format(d2);
			String sd2 = d2.substring(0, 7);
			rs = sd1.compareTo(sd2);
		}
		else if(precision.equals("dd")){
//			String sd1 = sdf_d.format(d1);
			String sd1 = d1.substring(0, 10);
//			String sd2 = sdf_d.format(d2);
			String sd2 = d2.substring(0, 10);
			rs = sd1.compareTo(sd2);
		}
		else if(precision.equals("HH")){
//			String sd1 = sdf_H.format(d1);
			String sd1 = d1.substring(0, 13);
//			String sd2 = sdf_H.format(d2);
			String sd2 = d2.substring(0, 13);
			rs = sd1.compareTo(sd2);
		}
		else if(precision.equals("mm")){
//			String sd1 = sdf_m.format(d1);
			String sd1 = d1.substring(0, 16);
//			String sd2 = sdf_m.format(d2);
			String sd2 = d2.substring(0, 16);
			rs = sd1.compareTo(sd2);
		}
		else if(precision.equals("ss")){
//			String sd1 = sdf_s.format(d1);
			String sd1 = d1.substring(0, 19);
//			String sd2 = sdf_s.format(d2);
			String sd2 = d2.substring(0, 19);
			rs = sd1.compareTo(sd2);
		}
		return rs;
	}
	
	
	public List<CheckInOutDto> getAllDayAttendanceRecords(List<MP1001> empList, Date date) throws ClassNotFoundException, SQLException, ParseException {
		List<CheckInOutDto> recordsList = new ArrayList<CheckInOutDto>();
		
		if(null == empList || 0 == empList.size()){
			System.out.println("Employee set is null !");
			return null;
		}
		Connection conn = CommonJobMethod.getDBConnection2();
		
		StringBuffer sb = new StringBuffer();
//		convert(varchar(10), c.CHECKTIME, 120)=:theDate
//		sb.append("select u.SSN, c.CHECKTIME, c.SENSORID from CHECKINOUT c, USERINFO u where 1=1 and c.CHECKTIME>=DATEADD(DD, DATEDIFF(DD,0,getdate()), -45) and c.USERID=u.USERID and u.SSN in (");
		sb.append("select u.SSN, c.CHECKTIME, c.SENSORID from CHECKINOUT c, USERINFO u " +
				  " where 1=1 and convert(varchar(10), c.CHECKTIME, 120)='" + sdf_1.format(date)  + "' and c.USERID=u.USERID and u.SSN in (");
		for(MP1001 emp : empList){
			sb.append("'" + emp.getMP1001_EMPLOYEE_NUM() + "',");
		}
		
		//sb.append(")");
		//sb.append(" order by c.CHECKTIME ASC;"); //here records are ordered, from morning to night
		
		String str = sb.substring(0,sb.length() - 1) + ") order by c.CHECKTIME ASC;";
		
		ResultSet rs = conn.createStatement().executeQuery(str);
		while(rs.next()){
			CheckInOutDto cioDto = new CheckInOutDto();
			
			cioDto.setEmployeeNum(rs.getString("SSN"));
			cioDto.setCheckTime(rs.getString("CHECKTIME"));
			cioDto.setDoorId(rs.getString("SENSORID"));
			
			recordsList.add(cioDto);
		}
		rs.close();
		conn.close();
		
		return recordsList;
	}
	
	public List<CheckInOutDto> fetchTodayAttendanceRecords(Date timeStamp) throws ClassNotFoundException, SQLException, ParseException {
		List<CheckInOutDto> recordsList = new ArrayList<CheckInOutDto>();
		
		Connection conn = CommonJobMethod.getDBConnection2();
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("select u.SSN, c.CHECKTIME, c.SENSORID from CHECKINOUT c, USERINFO u " +
				  " where 1=1 and u.userid=c.userid and convert(varchar(19), c.CHECKTIME, 120)>='" + sdf_0.format(timeStamp)  + "' ");
		sb.append(" order by c.CHECKTIME ASC;"); //here records are ordered, from morning to night
		
		String str = sb.toString();
		
		ResultSet rs = conn.createStatement().executeQuery(str);
		while(rs.next()){
			CheckInOutDto cioDto = new CheckInOutDto();
			
			cioDto.setEmployeeNum(rs.getString("SSN"));
			cioDto.setCheckTime(rs.getString("CHECKTIME"));
			cioDto.setDoorId(rs.getString("SENSORID"));
			
			recordsList.add(cioDto);
		}
		rs.close();
		conn.close();
		
		return recordsList;
	}
	
	//if that day no any records, then no AttendanceRecordDto in the list
	public List<AttendanceRecordDto> separateIntoEachDay(List<CheckInOutDto> checkInOutRecords){
		List<AttendanceRecordDto> dailyRecordslist = new ArrayList<AttendanceRecordDto>();
		//Map<String, AttendanceRecordDto> mapCacheAttendanceRecord = new HashMap<String, AttendanceRecordDto>();		
		
		for(CheckInOutDto dto : checkInOutRecords){
			String checkDateTime = dto.getCheckTime();
			String whichDay = checkDateTime.substring(0, 10);
			String employeeNum = dto.getEmployeeNum();

			boolean flag = false;
			for(AttendanceRecordDto arDto : dailyRecordslist){
				//if find that day and employee, then add this record
				if(arDto.getWhichDate().equalsIgnoreCase(whichDay) && arDto.getEmployeeNum().equalsIgnoreCase(employeeNum)){
					arDto.getCheckRecordsList().add(dto);
					flag = true;
				}
			}
			
			if(false == flag){
				AttendanceRecordDto newArDto = new AttendanceRecordDto();
				
				newArDto.setEmployeeNum(employeeNum);
				newArDto.setWhichDate(whichDay);
				newArDto.setCheckRecordsList(new ArrayList<CheckInOutDto>());
				newArDto.getCheckRecordsList().add(dto);
				
				dailyRecordslist.add(newArDto);
			}
		}
		//if the records are not sorted, sort it from morning to night
		return dailyRecordslist;
	}

	
	public Boolean isStartRecord(Date time, StandardWorkTime item){
		return true;
	}
	
	public Boolean isFinishRecord(Date time, StandardWorkTime item){
		return true;
	}
	
	public IStandardWorkTimeService getServiceStandardWorkTime() {
		return serviceStandardWorkTime;
	}

	public void setServiceStandardWorkTime(
			IStandardWorkTimeService serviceStandardWorkTime) {
		this.serviceStandardWorkTime = serviceStandardWorkTime;
	}

	public IHOLIDAYService getServiceHoliday() {
		return serviceHoliday;
	}

	public void setServiceHoliday(IHOLIDAYService serviceHoliday) {
		this.serviceHoliday = serviceHoliday;
	}

	public IMP2001Service getServiceMP2001() {
		return serviceMP2001;
	}

	public void setServiceMP2001(IMP2001Service serviceMP2001) {
		this.serviceMP2001 = serviceMP2001;
	}

	public IMP0010Service getServiceMP0010() {
		return serviceMP0010;
	}

	public void setServiceMP0010(IMP0010Service serviceMP0010) {
		this.serviceMP0010 = serviceMP0010;
	}

	public SimpleDateFormat getSdf_0() {
		return sdf_0;
	}

	public void setSdf_0(SimpleDateFormat sdf_0) {
		this.sdf_0 = sdf_0;
	}

	public SimpleDateFormat getSdf_1() {
		return sdf_1;
	}

	public void setSdf_1(SimpleDateFormat sdf_1) {
		this.sdf_1 = sdf_1;
	}

	public SimpleDateFormat getSdf_2() {
		return sdf_2;
	}

	public void setSdf_2(SimpleDateFormat sdf_2) {
		this.sdf_2 = sdf_2;
	}

	public IWorkTimePatternService getServiceWorkTimePattern() {
		return serviceWorkTimePattern;
	}

	public void setServiceWorkTimePattern(
			IWorkTimePatternService serviceWorkTimePattern) {
		this.serviceWorkTimePattern = serviceWorkTimePattern;
	}

	public IEmpWorkTimePattern_RService getServiceEWTP_R() {
		return serviceEWTP_R;
	}

	public void setServiceEWTP_R(IEmpWorkTimePattern_RService serviceEWTP_R) {
		this.serviceEWTP_R = serviceEWTP_R;
	}

	public IMP1001Service getServiceMP1001() {
		return serviceMP1001;
	}

	public void setServiceMP1001(IMP1001Service serviceMP1001) {
		this.serviceMP1001 = serviceMP1001;
	}

	public ICHECKINOUTService getServiceCHECKINOUT() {
		return serviceCHECKINOUT;
	}
	public void setServiceCHECKINOUT(ICHECKINOUTService serviceCHECKINOUT) {
		this.serviceCHECKINOUT = serviceCHECKINOUT;
	}
	public IMP2003Service getServiceMP2003() {
		return serviceMP2003;
	}
	public void setServiceMP2003(IMP2003Service serviceMP2003) {
		this.serviceMP2003 = serviceMP2003;
	}
}