package entity;

import java.io.Serializable;
import java.util.Date;

public class StandardWorkTime implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8446753098493041723L;
	private int id;	
//	private String employeeNum;
	private Date dayDate;
//	private int circleDayIdx; //for which day in circle
	private Date stdClockInTime;
	private Date stdClockOutTime;
	private Boolean manuallyChangeFlag; /*change flag, if true, no need to calcualte the time, because it's changed manually by people*/
	private String comments; //1. normal, 2.Public Holiday, 3.Speical Day, 4.Leave
	private Integer lateMinutes;
	private Integer earlyMinutes;
	
	
	private MP1001 employee;
	private EachCircleDay circleDay;
	private MP0011 detailChangeLog; //if it manually changed, need to find the log 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/*public String getEmployeeNum() {
		return employeeNum;
	}
	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}*/
	public Date getDayDate() {
		return dayDate;
	}
	public void setDayDate(Date dayDate) {
		this.dayDate = dayDate;
	}
	public Date getStdClockInTime() {
		return stdClockInTime;
	}
	public void setStdClockInTime(Date stdClockInTime) {
		this.stdClockInTime = stdClockInTime;
	}
	public Date getStdClockOutTime() {
		return stdClockOutTime;
	}
	public void setStdClockOutTime(Date stdClockOutTime) {
		this.stdClockOutTime = stdClockOutTime;
	}
	public MP1001 getEmployee() {
		return employee;
	}
	public void setEmployee(MP1001 employee) {
		this.employee = employee;
	}
	public EachCircleDay getCircleDay() {
		return circleDay;
	}
	public void setCircleDay(EachCircleDay circleDay) {
		this.circleDay = circleDay;
	}
	public MP0011 getDetailChangeLog() {
		return detailChangeLog;
	}
	public void setDetailChangeLog(MP0011 detailChangeLog) {
		this.detailChangeLog = detailChangeLog;
	}
	public Boolean getManuallyChangeFlag() {
		return manuallyChangeFlag;
	}
	public void setManuallyChangeFlag(Boolean manuallyChangeFlag) {
		this.manuallyChangeFlag = manuallyChangeFlag;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Integer getLateMinutes() {
		return lateMinutes;
	}
	public void setLateMinutes(Integer lateMinutes) {
		this.lateMinutes = lateMinutes;
	}
	public Integer getEarlyMinutes() {
		return earlyMinutes;
	}
	public void setEarlyMinutes(Integer earlyMinutes) {
		this.earlyMinutes = earlyMinutes;
	}
}