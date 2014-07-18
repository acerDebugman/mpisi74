package com.joe.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class MP1003 implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//Fields
	private int id;
	private String MP1003_EMPLOYEE_NUM;
	private String MP1003_COMPANY_NAME;
	private String MP1003_DEPARTMENT_ID;
	private String MP1003_POSITION;
	private Date MP1003_ENTRY_DATETIME;
	private Date MP1003_TERMINATION_DATETIME;
	private String MP1003_JOB_DESC;
	private String MP1003_TERMINATION_REASON;
	private String MP1003_CONTACT_PERSON_INFO;
	private String MP1003_CREATE_USER;
	private Date MP1003_CREATE_DATETIME;
	private String MP1003_EDIT_USER;
	private Date MP1003_EDIT_DATETIME;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMP1003_EMPLOYEE_NUM() {
		return MP1003_EMPLOYEE_NUM;
	}
	public void setMP1003_EMPLOYEE_NUM(String mP1003_EMPLOYEE_NUM) {
		MP1003_EMPLOYEE_NUM = mP1003_EMPLOYEE_NUM;
	}
	public String getMP1003_COMPANY_NAME() {
		return MP1003_COMPANY_NAME;
	}
	public void setMP1003_COMPANY_NAME(String mP1003_COMPANY_NAME) {
		MP1003_COMPANY_NAME = mP1003_COMPANY_NAME;
	}
	public String getMP1003_DEPARTMENT_ID() {
		return MP1003_DEPARTMENT_ID;
	}
	public void setMP1003_DEPARTMENT_ID(String mP1003_DEPARTMENT_ID) {
		MP1003_DEPARTMENT_ID = mP1003_DEPARTMENT_ID;
	}
	public String getMP1003_POSITION() {
		return MP1003_POSITION;
	}
	public void setMP1003_POSITION(String mP1003_POSITION) {
		MP1003_POSITION = mP1003_POSITION;
	}
	public Date getMP1003_ENTRY_DATETIME() {
		return MP1003_ENTRY_DATETIME;
	}
	public void setMP1003_ENTRY_DATETIME(Date mP1003_ENTRY_DATETIME) {
		MP1003_ENTRY_DATETIME = mP1003_ENTRY_DATETIME;
	}
	public Date getMP1003_TERMINATION_DATETIME() {
		return MP1003_TERMINATION_DATETIME;
	}
	public void setMP1003_TERMINATION_DATETIME(Date mP1003_TERMINATION_DATETIME) {
		MP1003_TERMINATION_DATETIME = mP1003_TERMINATION_DATETIME;
	}
	public String getMP1003_JOB_DESC() {
		return MP1003_JOB_DESC;
	}
	public void setMP1003_JOB_DESC(String mP1003_JOB_DESC) {
		MP1003_JOB_DESC = mP1003_JOB_DESC;
	}
	public String getMP1003_TERMINATION_REASON() {
		return MP1003_TERMINATION_REASON;
	}
	public void setMP1003_TERMINATION_REASON(String mP1003_TERMINATION_REASON) {
		MP1003_TERMINATION_REASON = mP1003_TERMINATION_REASON;
	}
	public String getMP1003_CONTACT_PERSON_INFO() {
		return MP1003_CONTACT_PERSON_INFO;
	}
	public void setMP1003_CONTACT_PERSON_INFO(String mP1003_CONTACT_PERSON_INFO) {
		MP1003_CONTACT_PERSON_INFO = mP1003_CONTACT_PERSON_INFO;
	}
	public String getMP1003_CREATE_USER() {
		return MP1003_CREATE_USER;
	}
	public void setMP1003_CREATE_USER(String mP1003_CREATE_USER) {
		MP1003_CREATE_USER = mP1003_CREATE_USER;
	}
	public Date getMP1003_CREATE_DATETIME() {
		return MP1003_CREATE_DATETIME;
	}
	public void setMP1003_CREATE_DATETIME(Date mP1003_CREATE_DATETIME) {
		MP1003_CREATE_DATETIME = mP1003_CREATE_DATETIME;
	}
	public String getMP1003_EDIT_USER() {
		return MP1003_EDIT_USER;
	}
	public void setMP1003_EDIT_USER(String mP1003_EDIT_USER) {
		MP1003_EDIT_USER = mP1003_EDIT_USER;
	}
	public Date getMP1003_EDIT_DATETIME() {
		return MP1003_EDIT_DATETIME;
	}
	public void setMP1003_EDIT_DATETIME(Date mP1003_EDIT_DATETIME) {
		MP1003_EDIT_DATETIME = mP1003_EDIT_DATETIME;
	}
	@Transient
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
