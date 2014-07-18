package com.joe.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class MP1002 implements Serializable{
	private static final long serialVersionUID = 1L;
	// Fields
	private int id;
	private String MP1002_EMPLOYEE_NUM;
	private String MP1002_INSTITUTION_TYPE;
	private String MP1002_INSTITUTION_NAME;
	private String MP1002_QUALIFICATION_LEV;
	private String MP1002_MAJOR;
	private String MP1002_START_DATETIME;
	private Date MP1002_FINISH_DATETIME;
	private String MP1002_CREATE_USER;
	private Date MP1002_CREATE_DATETIME;
	private String MP1002_EDIT_USER;
	private Date MP1002_EDIT_DATETIME;
	
	
//	private int MP1002_SEQ;
	private String MP1002_INSTITUTION_TYPE_NAME;
	private String MP1002_QUALIFICATION_LEV_NAME;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMP1002_EMPLOYEE_NUM() {
		return MP1002_EMPLOYEE_NUM;
	}
	public void setMP1002_EMPLOYEE_NUM(String mP1002_EMPLOYEE_NUM) {
		MP1002_EMPLOYEE_NUM = mP1002_EMPLOYEE_NUM;
	}
	public String getMP1002_INSTITUTION_TYPE() {
		return MP1002_INSTITUTION_TYPE;
	}
	public void setMP1002_INSTITUTION_TYPE(String mP1002_INSTITUTION_TYPE) {
		MP1002_INSTITUTION_TYPE = mP1002_INSTITUTION_TYPE;
	}
	public String getMP1002_INSTITUTION_NAME() {
		return MP1002_INSTITUTION_NAME;
	}
	public void setMP1002_INSTITUTION_NAME(String mP1002_INSTITUTION_NAME) {
		MP1002_INSTITUTION_NAME = mP1002_INSTITUTION_NAME;
	}
	public String getMP1002_QUALIFICATION_LEV() {
		return MP1002_QUALIFICATION_LEV;
	}
	public void setMP1002_QUALIFICATION_LEV(String mP1002_QUALIFICATION_LEV) {
		MP1002_QUALIFICATION_LEV = mP1002_QUALIFICATION_LEV;
	}
	public String getMP1002_MAJOR() {
		return MP1002_MAJOR;
	}
	public void setMP1002_MAJOR(String mP1002_MAJOR) {
		MP1002_MAJOR = mP1002_MAJOR;
	}
	public String getMP1002_START_DATETIME() {
		return MP1002_START_DATETIME;
	}
	public void setMP1002_START_DATETIME(String mP1002_START_DATETIME) {
		MP1002_START_DATETIME = mP1002_START_DATETIME;
	}
	public Date getMP1002_FINISH_DATETIME() {
		return MP1002_FINISH_DATETIME;
	}
	public void setMP1002_FINISH_DATETIME(Date mP1002_FINISH_DATETIME) {
		MP1002_FINISH_DATETIME = mP1002_FINISH_DATETIME;
	}
	public String getMP1002_CREATE_USER() {
		return MP1002_CREATE_USER;
	}
	public void setMP1002_CREATE_USER(String mP1002_CREATE_USER) {
		MP1002_CREATE_USER = mP1002_CREATE_USER;
	}
	public Date getMP1002_CREATE_DATETIME() {
		return MP1002_CREATE_DATETIME;
	}
	public void setMP1002_CREATE_DATETIME(Date mP1002_CREATE_DATETIME) {
		MP1002_CREATE_DATETIME = mP1002_CREATE_DATETIME;
	}
	public String getMP1002_EDIT_USER() {
		return MP1002_EDIT_USER;
	}
	public void setMP1002_EDIT_USER(String mP1002_EDIT_USER) {
		MP1002_EDIT_USER = mP1002_EDIT_USER;
	}
	public Date getMP1002_EDIT_DATETIME() {
		return MP1002_EDIT_DATETIME;
	}
	public void setMP1002_EDIT_DATETIME(Date mP1002_EDIT_DATETIME) {
		MP1002_EDIT_DATETIME = mP1002_EDIT_DATETIME;
	}
	@Transient
	public String getMP1002_INSTITUTION_TYPE_NAME() {
		return MP1002_INSTITUTION_TYPE_NAME;
	}
	public void setMP1002_INSTITUTION_TYPE_NAME(String mP1002_INSTITUTION_TYPE_NAME) {
		MP1002_INSTITUTION_TYPE_NAME = mP1002_INSTITUTION_TYPE_NAME;
	}
	@Transient
	public String getMP1002_QUALIFICATION_LEV_NAME() {
		return MP1002_QUALIFICATION_LEV_NAME;
	}
	public void setMP1002_QUALIFICATION_LEV_NAME(
			String mP1002_QUALIFICATION_LEV_NAME) {
		MP1002_QUALIFICATION_LEV_NAME = mP1002_QUALIFICATION_LEV_NAME;
	}
	
	@Transient
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
