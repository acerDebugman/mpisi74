package entity;

import java.io.Serializable;

public class MP2010 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -981018218222153272L;
	
	private int MP2010_ID;
	private String MP2010_EMPLOYEE_NUM;
	private String MP2010_DATE;
	private String MP2010_FROM_DATETIME;
	private String MP2010_END_DATETIME;
	private String MP2010_TYPE;
	private String MP2010_BRANCH_SITE;
	
	private MP1001 employeeInfo;
	
	public int getMP2010_ID() {
		return MP2010_ID;
	}
	public void setMP2010_ID(int mp2010_ID) {
		MP2010_ID = mp2010_ID;
	}
	
	public String getMP2010_DATE() {
		return MP2010_DATE;
	}
	public void setMP2010_DATE(String mp2010_DATE) {
		MP2010_DATE = mp2010_DATE;
	}
	public String getMP2010_FROM_DATETIME() {
		return MP2010_FROM_DATETIME;
	}
	public void setMP2010_FROM_DATETIME(String mp2010_FROM_DATETIME) {
		MP2010_FROM_DATETIME = mp2010_FROM_DATETIME;
	}
	public String getMP2010_END_DATETIME() {
		return MP2010_END_DATETIME;
	}
	public void setMP2010_END_DATETIME(String mp2010_END_DATETIME) {
		MP2010_END_DATETIME = mp2010_END_DATETIME;
	}
	public String getMP2010_TYPE() {
		return MP2010_TYPE;
	}
	public void setMP2010_TYPE(String mp2010_TYPE) {
		MP2010_TYPE = mp2010_TYPE;
	}
	public String getMP2010_BRANCH_SITE() {
		return MP2010_BRANCH_SITE;
	}
	public void setMP2010_BRANCH_SITE(String mp2010_BRANCH_SITE) {
		MP2010_BRANCH_SITE = mp2010_BRANCH_SITE;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getMP2010_EMPLOYEE_NUM() {
		return MP2010_EMPLOYEE_NUM;
	}
	public void setMP2010_EMPLOYEE_NUM(String mP2010_EMPLOYEE_NUM) {
		MP2010_EMPLOYEE_NUM = mP2010_EMPLOYEE_NUM;
	}
	public MP1001 getEmployeeInfo() {
		return employeeInfo;
	}
	public void setEmployeeInfo(MP1001 employeeInfo) {
		this.employeeInfo = employeeInfo;
	}
	
}
