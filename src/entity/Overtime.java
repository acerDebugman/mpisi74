package entity;

import java.io.Serializable;

public class Overtime implements Serializable {
	private static final long serialVersionUID = -3120332423828593012L;

	private String hourString;
	private int hourInt;
	private String employeeNum;
	private String checkTime1;
	private String checkTime2;
	
	/**
	 * @return the hourString
	 */
	public String getHourString() {
		return hourString;
	}
	/**
	 * @param hourString the hourString to set
	 */
	public void setHourString(String hourString) {
		this.hourString = hourString;
	}
	/**
	 * @return the hourInt
	 */
	public int getHourInt() {
		return hourInt;
	}
	/**
	 * @param hourInt the hourInt to set
	 */
	public void setHourInt(int hourInt) {
		this.hourInt = hourInt;
	}
	/**
	 * @return the employeeNum
	 */
	public String getEmployeeNum() {
		return employeeNum;
	}
	/**
	 * @param employeeNum the employeeNum to set
	 */
	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}
	/**
	 * @return the checkTime1
	 */
	public String getCheckTime1() {
		return checkTime1;
	}
	/**
	 * @param checkTime1 the checkTime1 to set
	 */
	public void setCheckTime1(String checkTime1) {
		this.checkTime1 = checkTime1;
	}
	/**
	 * @return the checkTime2
	 */
	public String getCheckTime2() {
		return checkTime2;
	}
	/**
	 * @param checkTime2 the checkTime2 to set
	 */
	public void setCheckTime2(String checkTime2) {
		this.checkTime2 = checkTime2;
	}

}
