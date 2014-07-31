package common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.security.auth.kerberos.ServicePermission;

import service.IHOLIDAYService;
import service.IMP0010Service;
import service.IMP2001Service;
import service.IStandardWorkTimeService;
import entity.DetailDayWorkTime;
import entity.EachCircleDay;
import entity.EmpWorkTimePattern_R;
import entity.MP1001;
import entity.StandardWorkTime;
import entity.WorkTimePattern;

public class AttendanceCalculator {
	private IStandardWorkTimeService serviceStandardWorkTime;
	private IHOLIDAYService serviceHoliday;
	private IMP2001Service serviceMP2001;
	private IMP0010Service serviceMP0010;
	
	private SimpleDateFormat sdf_0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat sdf_1 = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sdf_2 = new SimpleDateFormat("HH:mm:ss");
	
	
	//calculate which day and wich employee
	public void proceed(Date date, MP1001 emp){
		/*
		 *here need to check if it's first date or not
		 */
		
		StandardWorkTime item = new StandardWorkTime();
		//work out the specified day standard work time
		EmpWorkTimePattern_R ew_r = emp.getEmpWorkTimePattern_R();
		int circleDayIdx = ew_r.getInitialCircleDayIdx();
		Date initialDay = ew_r.getStartDate();
		WorkTimePattern wtp = ew_r.getWorkTimePattern();
		boolean crossDayFlag = false;
		
		EachCircleDay cd = null;
		EachCircleDay followingCd = null;
		while(wtp.getAllCircleDays().iterator().hasNext()){
			cd = wtp.getAllCircleDays().iterator().next();
			if(cd.getDaySeq() == circleDayIdx){
				followingCd = wtp.getAllCircleDays().iterator().next(); //get the following circle day
				break;
			}
		}
		List<DetailDayWorkTime> list = new ArrayList(cd.getDetailWorkTimeItems());
		item.setStdClockInTime(list.get(0).getFromTime());
		item.setStdClockOutTime(list.get(list.size() - 1).getToTime());
		
		//public holiday
		if(wtp.isCalPubHolidayFlag()){
			item = proceedPublicHoliday(date, item);
		}
		
		//get special day
		if(wtp.isCalSpecialDayFlag()){
			item = proceedSpecialDay(date, item, wtp);
		}
		
		//fetch leave application
		item = proceedLeaveApplication(date, item);
		
		
		//save to StandardWorkTime table
		//if(){
			serviceStandardWorkTime.save(item);
		//}
		
		//fetch attendance records and compare work time, then insert into mp2002 status table
		
		
	}
	
	//
	public void calculatePresenceStatus(Date date, MP1001 emp){
		
	}
	
	
	public StandardWorkTime proceedLeaveApplication(Date date, StandardWorkTime item){ //do not change date, return value is return addr or value ?
		if(item.getStdClockInTime().getTime() == 0 && item.getStdClockOutTime().getTime() == 0){
			return item; //have no need to do this
		}
		
		
		return item;
	}
	
	//work time pattern has many special day
	public StandardWorkTime proceedSpecialDay(Date date, StandardWorkTime item, WorkTimePattern wtp){
		if(item.getStdClockInTime().getTime() == 0 && item.getStdClockOutTime().getTime() == 0){
			return item; //have no need to do this
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
		}
		else if(Calendar.SATURDAY == cal.get(Calendar.DAY_OF_WEEK) || Calendar.SUNDAY == cal.get(Calendar.DAY_OF_WEEK)){ //Saturday
			item.setStdClockInTime(new Date(0)); //should be set to 1970-01-01 02:00:00, because the time zone, but the getTime() == 0
			item.setStdClockOutTime(new Date(0));
		}
		
		return item;
	}
	
}
