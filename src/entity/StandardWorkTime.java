package entity;

import java.util.Date;

public class StandardWorkTime {
	private int id;	
//	private String employeeNum;
	private Date dayDate;
//	private int circleDayIdx; //for which day in circle
	private Date stdClockInTime;
	private Date stdClockOutTime;
	
	
	private MP1001 employee;
	private EachCircleDay circleDay; 
	
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
}