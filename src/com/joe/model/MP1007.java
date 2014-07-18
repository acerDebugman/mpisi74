package com.joe.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MP1007 implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id; //[MP1007_SEQ] [int] IDENTITY(1,1) NOT NULL,
	private String MP1007_EMPLOYEE_NUM;
	private String MP1007_RESRORATION_TYPE;
	private Date MP1007_RESRORATION_DATETIME;
	private String MP1007_REASON;
	private String MP1007_CREATE_USER;
	private Date MP1007_CREATE_DATETIME;
	private String MP1007_EDIT_USER;
	private Date MP1007_EDIT_DATETIME;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMP1007_EMPLOYEE_NUM() {
		return MP1007_EMPLOYEE_NUM;
	}
	public void setMP1007_EMPLOYEE_NUM(String mP1007_EMPLOYEE_NUM) {
		MP1007_EMPLOYEE_NUM = mP1007_EMPLOYEE_NUM;
	}
	public String getMP1007_RESRORATION_TYPE() {
		return MP1007_RESRORATION_TYPE;
	}
	public void setMP1007_RESRORATION_TYPE(String mP1007_RESRORATION_TYPE) {
		MP1007_RESRORATION_TYPE = mP1007_RESRORATION_TYPE;
	}
	public Date getMP1007_RESRORATION_DATETIME() {
		return MP1007_RESRORATION_DATETIME;
	}
	public void setMP1007_RESRORATION_DATETIME(Date mP1007_RESRORATION_DATETIME) {
		MP1007_RESRORATION_DATETIME = mP1007_RESRORATION_DATETIME;
	}
	public String getMP1007_REASON() {
		return MP1007_REASON;
	}
	public void setMP1007_REASON(String mP1007_REASON) {
		MP1007_REASON = mP1007_REASON;
	}
	public String getMP1007_CREATE_USER() {
		return MP1007_CREATE_USER;
	}
	public void setMP1007_CREATE_USER(String mP1007_CREATE_USER) {
		MP1007_CREATE_USER = mP1007_CREATE_USER;
	}
	public Date getMP1007_CREATE_DATETIME() {
		return MP1007_CREATE_DATETIME;
	}
	public void setMP1007_CREATE_DATETIME(Date mP1007_CREATE_DATETIME) {
		MP1007_CREATE_DATETIME = mP1007_CREATE_DATETIME;
	}
	public String getMP1007_EDIT_USER() {
		return MP1007_EDIT_USER;
	}
	public void setMP1007_EDIT_USER(String mP1007_EDIT_USER) {
		MP1007_EDIT_USER = mP1007_EDIT_USER;
	}
	public Date getMP1007_EDIT_DATETIME() {
		return MP1007_EDIT_DATETIME;
	}
	public void setMP1007_EDIT_DATETIME(Date mP1007_EDIT_DATETIME) {
		MP1007_EDIT_DATETIME = mP1007_EDIT_DATETIME;
	}
	
	// Fields
//	private int MP1007_SEQ;
//	private String MP1007_EMPLOYEE_NUM;
//	private String MP1007_RESRORATION_TYPE;
//	private String MP1007_RESRORATION_DATETIME;
//	private String MP1007_REASON;
//	private String MP1007_CREATE_USER;
//	private String MP1007_CREATE_DATETIME;
//	private String MP1007_EDIT_USER;
//	private String MP1007_EDIT_DATETIME;

}
