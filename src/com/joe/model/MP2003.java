package com.joe.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MP2003 implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	private int id;
	private String MP2003_EMPLOYEE_NUM;
	private Date MP2003_DATETIME;
	private Date MP2003_START_TIME;
	private Date MP2003_FINISH_TIME;
	private String MP2003_START_TIME_DOOR;
	private String MP2003_FINISH_TIME_DOOR;
	private Date MP2003_START_TIME_E;
	private Date MP2003_FINISH_TIME_E;
	private String MP2003_COMMENT;
	private String MP2003_DES;
	private String MP2003_STATUS;
	private String MP2003_EDIT_USER;
	private Date MP2003_EDIT_DATETIME;
	

	//extended properties
	private String MP2003_EMPLOYEE_NAME;
	
//	private String MP2003_STATUS; // 如果该字段值为"1"，则表示该条数据异常
	//------2011-11-04 Add by Tim------End
	
	//------2011-11-10 Add by Tim------Start
	private String MP2003_RECTIFY_TIMES;
	//------2011-11-10 Add by Tim------End
	
	//------2011-11-10 Add by Tim------Start
	private String MP2003_DEPARTMENT_ID;
	//------2011-11-10 Add by Tim------End
	//private String MP2003_STATUS1;
	//department name
	private String MP2003_DEPARTMENT_NAME;
	private String MP2003_EMPLOYEE_SURNAME;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMP2003_EMPLOYEE_NUM() {
		return MP2003_EMPLOYEE_NUM;
	}
	public void setMP2003_EMPLOYEE_NUM(String mP2003_EMPLOYEE_NUM) {
		MP2003_EMPLOYEE_NUM = mP2003_EMPLOYEE_NUM;
	}
	public Date getMP2003_DATETIME() {
		return MP2003_DATETIME;
	}
	public void setMP2003_DATETIME(Date mP2003_DATETIME) {
		MP2003_DATETIME = mP2003_DATETIME;
	}
	public Date getMP2003_START_TIME() {
		return MP2003_START_TIME;
	}
	public void setMP2003_START_TIME(Date mP2003_START_TIME) {
		MP2003_START_TIME = mP2003_START_TIME;
	}
	public Date getMP2003_FINISH_TIME() {
		return MP2003_FINISH_TIME;
	}
	public void setMP2003_FINISH_TIME(Date mP2003_FINISH_TIME) {
		MP2003_FINISH_TIME = mP2003_FINISH_TIME;
	}
	public String getMP2003_START_TIME_DOOR() {
		return MP2003_START_TIME_DOOR;
	}
	public void setMP2003_START_TIME_DOOR(String mP2003_START_TIME_DOOR) {
		MP2003_START_TIME_DOOR = mP2003_START_TIME_DOOR;
	}
	public String getMP2003_FINISH_TIME_DOOR() {
		return MP2003_FINISH_TIME_DOOR;
	}
	public void setMP2003_FINISH_TIME_DOOR(String mP2003_FINISH_TIME_DOOR) {
		MP2003_FINISH_TIME_DOOR = mP2003_FINISH_TIME_DOOR;
	}
	public Date getMP2003_START_TIME_E() {
		return MP2003_START_TIME_E;
	}
	public void setMP2003_START_TIME_E(Date mP2003_START_TIME_E) {
		MP2003_START_TIME_E = mP2003_START_TIME_E;
	}
	public Date getMP2003_FINISH_TIME_E() {
		return MP2003_FINISH_TIME_E;
	}
	public void setMP2003_FINISH_TIME_E(Date mP2003_FINISH_TIME_E) {
		MP2003_FINISH_TIME_E = mP2003_FINISH_TIME_E;
	}
	public String getMP2003_COMMENT() {
		return MP2003_COMMENT;
	}
	public void setMP2003_COMMENT(String mP2003_COMMENT) {
		MP2003_COMMENT = mP2003_COMMENT;
	}
	public String getMP2003_DES() {
		return MP2003_DES;
	}
	public void setMP2003_DES(String mP2003_DES) {
		MP2003_DES = mP2003_DES;
	}
	public String getMP2003_STATUS() {
		return MP2003_STATUS;
	}
	public void setMP2003_STATUS(String mP2003_STATUS) {
		MP2003_STATUS = mP2003_STATUS;
	}
	public String getMP2003_EDIT_USER() {
		return MP2003_EDIT_USER;
	}
	public void setMP2003_EDIT_USER(String mP2003_EDIT_USER) {
		MP2003_EDIT_USER = mP2003_EDIT_USER;
	}
	public Date getMP2003_EDIT_DATETIME() {
		return MP2003_EDIT_DATETIME;
	}
	public void setMP2003_EDIT_DATETIME(Date mP2003_EDIT_DATETIME) {
		MP2003_EDIT_DATETIME = mP2003_EDIT_DATETIME;
	}
	public String getMP2003_EMPLOYEE_NAME() {
		return MP2003_EMPLOYEE_NAME;
	}
	public void setMP2003_EMPLOYEE_NAME(String mP2003_EMPLOYEE_NAME) {
		MP2003_EMPLOYEE_NAME = mP2003_EMPLOYEE_NAME;
	}
	public String getMP2003_RECTIFY_TIMES() {
		return MP2003_RECTIFY_TIMES;
	}
	public void setMP2003_RECTIFY_TIMES(String mP2003_RECTIFY_TIMES) {
		MP2003_RECTIFY_TIMES = mP2003_RECTIFY_TIMES;
	}
	public String getMP2003_DEPARTMENT_ID() {
		return MP2003_DEPARTMENT_ID;
	}
	public void setMP2003_DEPARTMENT_ID(String mP2003_DEPARTMENT_ID) {
		MP2003_DEPARTMENT_ID = mP2003_DEPARTMENT_ID;
	}
	public String getMP2003_DEPARTMENT_NAME() {
		return MP2003_DEPARTMENT_NAME;
	}
	public void setMP2003_DEPARTMENT_NAME(String mP2003_DEPARTMENT_NAME) {
		MP2003_DEPARTMENT_NAME = mP2003_DEPARTMENT_NAME;
	}
	public String getMP2003_EMPLOYEE_SURNAME() {
		return MP2003_EMPLOYEE_SURNAME;
	}
	public void setMP2003_EMPLOYEE_SURNAME(String mP2003_EMPLOYEE_SURNAME) {
		MP2003_EMPLOYEE_SURNAME = mP2003_EMPLOYEE_SURNAME;
	} 	
}
