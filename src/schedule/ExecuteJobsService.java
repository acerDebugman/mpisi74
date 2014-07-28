package schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import service.CHECKINOUTService;
import service.MP0002Service;
import service.MP1001Service;

import common.Constant;
import common.CustomerContextHolder;

import entity.CHECKINOUT;
import entity.MP0002;
import entity.MP1001;

public class ExecuteJobsService {
	private CHECKINOUTService serviceCheckInOut;
	private MP1001Service serviceMP1001;
	private MP0002Service serviceMP0002;

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

	public CHECKINOUTService getServiceCheckInOut() {
		return serviceCheckInOut;
	}

	public void setServiceCheckInOut(CHECKINOUTService serviceCheckInOut) {
		this.serviceCheckInOut = serviceCheckInOut;
	}

	public MP1001Service getServiceMP1001() {
		return serviceMP1001;
	}

	public void setServiceMP1001(MP1001Service serviceMP1001) {
		this.serviceMP1001 = serviceMP1001;
	}

	public MP0002Service getServiceMP0002() {
		return serviceMP0002;
	}

	public void setServiceMP0002(MP0002Service serviceMP0002) {
		this.serviceMP0002 = serviceMP0002;
	}
}
