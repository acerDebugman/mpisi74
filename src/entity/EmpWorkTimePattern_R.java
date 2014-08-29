package entity;

import java.io.Serializable;
import java.util.Date;

public class EmpWorkTimePattern_R implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4931924087699620945L;
	private int id;
//	private String employeeCode;
//	private int workTimePatterId;
	private Date startDate; //according to this date to add annual/sick leave days per month
	private int initialCircleDayIdx;
	
	private MP1001 employee;
	private WorkTimePattern workTimePattern;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/*public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public int getWorkTimePatterId() {
		return workTimePatterId;
	}
	public void setWorkTimePatterId(int workTimePatterId) {
		this.workTimePatterId = workTimePatterId;
	}*/
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public MP1001 getEmployee() {
		return employee;
	}
	public void setEmployee(MP1001 employee) {
		this.employee = employee;
	}
	public WorkTimePattern getWorkTimePattern() {
		return workTimePattern;
	}
	public void setWorkTimePattern(WorkTimePattern workTimePattern) {
		this.workTimePattern = workTimePattern;
	}
	public int getInitialCircleDayIdx() {
		return initialCircleDayIdx;
	}
	public void setInitialCircleDayIdx(int initialCircleDayIdx) {
		this.initialCircleDayIdx = initialCircleDayIdx;
	}
}