package com.joe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MP2010 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -981018218222153272L;
	
	private int id;
	private String MP2010_EMPLOYEE_NUM;
	private String MP2010_DATE;
	private String MP2010_FROM_DATETIME;
	private String MP2010_END_DATETIME;
	private String MP2010_TYPE;
	private String MP2010_BRANCH_SITE;

	private MP1001 employeeInfo;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
