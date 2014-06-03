package dto;

import java.util.Date;

public class LateEarlyDto {
	private String employeeNum; //key
	private Date date;
	private Date clockInTime;
	private Date clockOutTime;
	private String preferedName;
	private String surname;
	private String departmentName;
	private String status;
	
	private int lateMins;
	private int earlyMins;
	private int allMins;
	
	
	public String getEmployeeNum() {
		return employeeNum;
	}
	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getClockInTime() {
		return clockInTime;
	}
	public void setClockInTime(Date clockInTime) {
		this.clockInTime = clockInTime;
	}
	public Date getClockOutTime() {
		return clockOutTime;
	}
	public void setClockOutTime(Date clockOutTime) {
		this.clockOutTime = clockOutTime;
	}
	public String getPreferedName() {
		return preferedName;
	}
	public void setPreferedName(String preferedName) {
		this.preferedName = preferedName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getLateMins() {
		return lateMins;
	}
	public void setLateMins(int lateMins) {
		this.lateMins = lateMins;
	}
	public int getEarlyMins() {
		return earlyMins;
	}
	public void setEarlyMins(int earlyMins) {
		this.earlyMins = earlyMins;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public int getAllMins() {
		return allMins;
	}
	public void setAllMins(int allMins) {
		this.allMins = allMins;
	}
}