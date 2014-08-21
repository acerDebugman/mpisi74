package interceptor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import service.MP1001Service;
import service.MP2001Service;
import service.MP2003Service;

import common.AttendanceCalculator;

import entity.MP1001;
import entity.MP2001;
import entity.MP2003;

public class AttendanceCalculatorInterceptor implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -447445173151336294L;
	
	private AttendanceCalculator attendanceCalculator;
	private MP2001Service serviceMP2001;
	private MP1001Service serviceMP1001;
	private MP2003Service serviceMP2003;
	
	public void afterLeaveApplicationApproval(){
		System.out.println("after leave aplication approval~");
	}
	
	public void beforeLeaveApplicationApproval(){
		
		System.out.println("before method ~");
	}
	
	public void afterMethod(){
//		ActionContext context = ActionContext.getContext();
//		Map<String, Object> session = context.getSession();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
//		System.out.println("after method ~ : " + str + "|" + ala.getEmployeeNum());
		System.out.println("after method ~ : " + request.getParameter("employeeNum") + request.getParameter("employeeNum"));
	}
	
	public void afterApproveLeave(){
		
		
		System.out.println("in afterApproveLeave() function ~ after ");
	}
	
	public void afterApprovalLeaveInfo(){
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
	
			String leaveApplicationSeq = request.getParameter("MP2001_NUM");
			
			MP2001 mp21 = serviceMP2001.findById(leaveApplicationSeq);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calFrom = Calendar.getInstance();
			calFrom.setTime(sdf.parse(mp21.getMP2001_FROM_DATETIME()));
			Calendar calEnd = Calendar.getInstance();
			calEnd.setTime(sdf.parse(mp21.getMP2001_TO_DATETIME()));
			
			List<MP1001> lst = new ArrayList<MP1001>();
			MP1001 mp11 = serviceMP1001.findById(mp21.getMP2001_EMPLOYEE_NUM());
			lst.add(mp11);  //only one here
			
			while(attendanceCalculator.datetimeCompare(calFrom.getTime(), calEnd.getTime(), "dd") <= 0){//do calculte now, but calculate later,don't return
	    		for(MP1001 emp: lst){ //employee list should be inside, it's not easy to reverse the date again
	        		attendanceCalculator.proceed(calFrom.getTime(), emp);
	        		attendanceCalculator.calculatePresenceStatus(calFrom.getTime(), emp);
	        	}
	    		
	    		calFrom.add(Calendar.DAY_OF_MONTH, 1); //add one day
	        }
		} catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		
		System.out.println("in afterApprovalLeaveInfo() function ~ after ");
	}

	public AttendanceCalculator getAttendanceCalculator() {
		return attendanceCalculator;
	}

	public void setAttendanceCalculator(AttendanceCalculator attendanceCalculator) {
		this.attendanceCalculator = attendanceCalculator;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public MP2001Service getServiceMP2001() {
		return serviceMP2001;
	}

	public void setServiceMP2001(MP2001Service serviceMP2001) {
		this.serviceMP2001 = serviceMP2001;
	}

	public MP1001Service getServiceMP1001() {
		return serviceMP1001;
	}

	public void setServiceMP1001(MP1001Service serviceMP1001) {
		this.serviceMP1001 = serviceMP1001;
	}

	public MP2003Service getServiceMP2003() {
		return serviceMP2003;
	}

	public void setServiceMP2003(MP2003Service serviceMP2003) {
		this.serviceMP2003 = serviceMP2003;
	}
	
}