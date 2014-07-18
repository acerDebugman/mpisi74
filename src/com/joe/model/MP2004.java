package com.joe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class MP2004 implements Serializable{

	private static final long serialVersionUID = 66206734071949518L;

	// Fields
	private int id;
	private String MP2004_EMPLOYEE_NUM;
	private String MP2004_MAJOR_NAME;
	private String MP2004_TIME;
	private String MP2004_STATUS;

	//dextended properties
	private String MP2004_EMPLOYEE_NAME;
	private String MP2004_TIME_DESC;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMP2004_EMPLOYEE_NUM() {
		return MP2004_EMPLOYEE_NUM;
	}
	public void setMP2004_EMPLOYEE_NUM(String mP2004_EMPLOYEE_NUM) {
		MP2004_EMPLOYEE_NUM = mP2004_EMPLOYEE_NUM;
	}
	public String getMP2004_MAJOR_NAME() {
		return MP2004_MAJOR_NAME;
	}
	public void setMP2004_MAJOR_NAME(String mP2004_MAJOR_NAME) {
		MP2004_MAJOR_NAME = mP2004_MAJOR_NAME;
	}
	public String getMP2004_TIME() {
		return MP2004_TIME;
	}
	public void setMP2004_TIME(String mP2004_TIME) {
		MP2004_TIME = mP2004_TIME;
	}
	public String getMP2004_STATUS() {
		return MP2004_STATUS;
	}
	public void setMP2004_STATUS(String mP2004_STATUS) {
		MP2004_STATUS = mP2004_STATUS;
	}
	@Transient
	public String getMP2004_EMPLOYEE_NAME() {
		return MP2004_EMPLOYEE_NAME;
	}
	public void setMP2004_EMPLOYEE_NAME(String mP2004_EMPLOYEE_NAME) {
		MP2004_EMPLOYEE_NAME = mP2004_EMPLOYEE_NAME;
	}
	@Transient
	public String getMP2004_TIME_DESC() {
		return MP2004_TIME_DESC;
	}
	public void setMP2004_TIME_DESC(String mP2004_TIME_DESC) {
		MP2004_TIME_DESC = mP2004_TIME_DESC;
	}
}
