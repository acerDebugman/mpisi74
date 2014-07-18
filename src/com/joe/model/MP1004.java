package com.joe.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class MP1004 implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Fields
	private int id; //[MP1004_SEQ] [int] IDENTITY(1,1) NOT NULL,
	private String MP1004_EMPLOYEE_NUM;
	private String MP1004_DEPARTMENT_CODE;
	private String MP1004_POSITION;
	private Date MP1004_TRANSFER_DATETIME;
	private String MP1004_JOB_DESC;
	private String MP1004_DEPARTMENT_CODE_OLD;
	private String MP1004_POSITION_OLD;
	private String MP1004_CREATE_USER;
	private Date MP1004_CREATE_DATETIME;
	private String MP1004_EDIT_USER;
	private Date MP1004_EDIT_DATETIME;
	
	// extended properties
	private String MP1004_EMPLOYEE_NAME;
	private String MP1004_DEPARTMENT_NAME;
	private String MP1004_DEPARTMENT_NAME_OLD;
	private String MP1004_POSITION_NAME;
	private String MP1004_POSITION_OLD_NAME;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMP1004_EMPLOYEE_NUM() {
		return MP1004_EMPLOYEE_NUM;
	}
	public void setMP1004_EMPLOYEE_NUM(String mP1004_EMPLOYEE_NUM) {
		MP1004_EMPLOYEE_NUM = mP1004_EMPLOYEE_NUM;
	}
	public String getMP1004_DEPARTMENT_CODE() {
		return MP1004_DEPARTMENT_CODE;
	}
	public void setMP1004_DEPARTMENT_CODE(String mP1004_DEPARTMENT_CODE) {
		MP1004_DEPARTMENT_CODE = mP1004_DEPARTMENT_CODE;
	}
	public String getMP1004_POSITION() {
		return MP1004_POSITION;
	}
	public void setMP1004_POSITION(String mP1004_POSITION) {
		MP1004_POSITION = mP1004_POSITION;
	}
	public Date getMP1004_TRANSFER_DATETIME() {
		return MP1004_TRANSFER_DATETIME;
	}
	public void setMP1004_TRANSFER_DATETIME(Date mP1004_TRANSFER_DATETIME) {
		MP1004_TRANSFER_DATETIME = mP1004_TRANSFER_DATETIME;
	}
	public String getMP1004_JOB_DESC() {
		return MP1004_JOB_DESC;
	}
	public void setMP1004_JOB_DESC(String mP1004_JOB_DESC) {
		MP1004_JOB_DESC = mP1004_JOB_DESC;
	}
	public String getMP1004_DEPARTMENT_CODE_OLD() {
		return MP1004_DEPARTMENT_CODE_OLD;
	}
	public void setMP1004_DEPARTMENT_CODE_OLD(String mP1004_DEPARTMENT_CODE_OLD) {
		MP1004_DEPARTMENT_CODE_OLD = mP1004_DEPARTMENT_CODE_OLD;
	}
	public String getMP1004_POSITION_OLD() {
		return MP1004_POSITION_OLD;
	}
	public void setMP1004_POSITION_OLD(String mP1004_POSITION_OLD) {
		MP1004_POSITION_OLD = mP1004_POSITION_OLD;
	}
	public String getMP1004_CREATE_USER() {
		return MP1004_CREATE_USER;
	}
	public void setMP1004_CREATE_USER(String mP1004_CREATE_USER) {
		MP1004_CREATE_USER = mP1004_CREATE_USER;
	}
	public Date getMP1004_CREATE_DATETIME() {
		return MP1004_CREATE_DATETIME;
	}
	public void setMP1004_CREATE_DATETIME(Date mP1004_CREATE_DATETIME) {
		MP1004_CREATE_DATETIME = mP1004_CREATE_DATETIME;
	}
	public String getMP1004_EDIT_USER() {
		return MP1004_EDIT_USER;
	}
	public void setMP1004_EDIT_USER(String mP1004_EDIT_USER) {
		MP1004_EDIT_USER = mP1004_EDIT_USER;
	}
	public Date getMP1004_EDIT_DATETIME() {
		return MP1004_EDIT_DATETIME;
	}
	public void setMP1004_EDIT_DATETIME(Date mP1004_EDIT_DATETIME) {
		MP1004_EDIT_DATETIME = mP1004_EDIT_DATETIME;
	}
	@Transient
	public String getMP1004_EMPLOYEE_NAME() {
		return MP1004_EMPLOYEE_NAME;
	}
	public void setMP1004_EMPLOYEE_NAME(String mP1004_EMPLOYEE_NAME) {
		MP1004_EMPLOYEE_NAME = mP1004_EMPLOYEE_NAME;
	}
	@Transient
	public String getMP1004_DEPARTMENT_NAME() {
		return MP1004_DEPARTMENT_NAME;
	}
	public void setMP1004_DEPARTMENT_NAME(String mP1004_DEPARTMENT_NAME) {
		MP1004_DEPARTMENT_NAME = mP1004_DEPARTMENT_NAME;
	}
	@Transient
	public String getMP1004_DEPARTMENT_NAME_OLD() {
		return MP1004_DEPARTMENT_NAME_OLD;
	}
	public void setMP1004_DEPARTMENT_NAME_OLD(String mP1004_DEPARTMENT_NAME_OLD) {
		MP1004_DEPARTMENT_NAME_OLD = mP1004_DEPARTMENT_NAME_OLD;
	}
	@Transient
	public String getMP1004_POSITION_NAME() {
		return MP1004_POSITION_NAME;
	}
	public void setMP1004_POSITION_NAME(String mP1004_POSITION_NAME) {
		MP1004_POSITION_NAME = mP1004_POSITION_NAME;
	}
	@Transient
	public String getMP1004_POSITION_OLD_NAME() {
		return MP1004_POSITION_OLD_NAME;
	}
	public void setMP1004_POSITION_OLD_NAME(String mP1004_POSITION_OLD_NAME) {
		MP1004_POSITION_OLD_NAME = mP1004_POSITION_OLD_NAME;
	}
}
