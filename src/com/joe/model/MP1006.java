package com.joe.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MP1006 implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Fields	
	private int id;
	private String MP1006_EMPLOYEE_NUM;
	private String MP1006_RESIGN_TYPE;
	private Date MP1006_RESIGN_DATETIME;
	private String MP1006_REASON;
	private String MP1006_CREATE_USER;
	private Date MP1006_CREATE_DATETIME;
	private String MP1006_EDIT_USER;
	private Date MP1006_EDIT_DATETIME;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMP1006_EMPLOYEE_NUM() {
		return MP1006_EMPLOYEE_NUM;
	}
	public void setMP1006_EMPLOYEE_NUM(String mP1006_EMPLOYEE_NUM) {
		MP1006_EMPLOYEE_NUM = mP1006_EMPLOYEE_NUM;
	}
	public String getMP1006_RESIGN_TYPE() {
		return MP1006_RESIGN_TYPE;
	}
	public void setMP1006_RESIGN_TYPE(String mP1006_RESIGN_TYPE) {
		MP1006_RESIGN_TYPE = mP1006_RESIGN_TYPE;
	}
	public Date getMP1006_RESIGN_DATETIME() {
		return MP1006_RESIGN_DATETIME;
	}
	public void setMP1006_RESIGN_DATETIME(Date mP1006_RESIGN_DATETIME) {
		MP1006_RESIGN_DATETIME = mP1006_RESIGN_DATETIME;
	}
	public String getMP1006_REASON() {
		return MP1006_REASON;
	}
	public void setMP1006_REASON(String mP1006_REASON) {
		MP1006_REASON = mP1006_REASON;
	}
	public String getMP1006_CREATE_USER() {
		return MP1006_CREATE_USER;
	}
	public void setMP1006_CREATE_USER(String mP1006_CREATE_USER) {
		MP1006_CREATE_USER = mP1006_CREATE_USER;
	}
	public Date getMP1006_CREATE_DATETIME() {
		return MP1006_CREATE_DATETIME;
	}
	public void setMP1006_CREATE_DATETIME(Date mP1006_CREATE_DATETIME) {
		MP1006_CREATE_DATETIME = mP1006_CREATE_DATETIME;
	}
	public String getMP1006_EDIT_USER() {
		return MP1006_EDIT_USER;
	}
	public void setMP1006_EDIT_USER(String mP1006_EDIT_USER) {
		MP1006_EDIT_USER = mP1006_EDIT_USER;
	}
	public Date getMP1006_EDIT_DATETIME() {
		return MP1006_EDIT_DATETIME;
	}
	public void setMP1006_EDIT_DATETIME(Date mP1006_EDIT_DATETIME) {
		MP1006_EDIT_DATETIME = mP1006_EDIT_DATETIME;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
