package com.joe.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class MP2007 implements Serializable{
	private static final long serialVersionUID = 2056988306577080729L;
	
	//fields
	private int id;
	private String MP2007_EMPLOYEE_NUM;
	private String MP2007_DAYS;
	private String MP2007_STATUS;
	private String MP2007_TYPE;
	private String MP2007_MAJOR_NAME;
	private String MP2007_CREATE_USER;
	private Date MP2007_CREATE_DATETIME;
	private String MP2007_EDIT_USER;
	private Date MP2007_EDIT_DATETIME;
	
	//extended properties
	private String MP2007_TYPE_NAME;
	private String MP2007_EMPLOYEE_NAME;
	private String MP2007_STATUS_NAME;
	private String MP2007_DEPARTMENT_NUM;
	private String MP2007_DEPARTMENT_NAME;
	
	private String MP2007_DAYS_EXT_D;
	private String MP2007_DAYS_EXT_H;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMP2007_EMPLOYEE_NUM() {
		return MP2007_EMPLOYEE_NUM;
	}
	public void setMP2007_EMPLOYEE_NUM(String mP2007_EMPLOYEE_NUM) {
		MP2007_EMPLOYEE_NUM = mP2007_EMPLOYEE_NUM;
	}
	public String getMP2007_DAYS() {
		return MP2007_DAYS;
	}
	public void setMP2007_DAYS(String mP2007_DAYS) {
		MP2007_DAYS = mP2007_DAYS;
	}
	public String getMP2007_STATUS() {
		return MP2007_STATUS;
	}
	public void setMP2007_STATUS(String mP2007_STATUS) {
		MP2007_STATUS = mP2007_STATUS;
	}
	public String getMP2007_TYPE() {
		return MP2007_TYPE;
	}
	public void setMP2007_TYPE(String mP2007_TYPE) {
		MP2007_TYPE = mP2007_TYPE;
	}
	public String getMP2007_MAJOR_NAME() {
		return MP2007_MAJOR_NAME;
	}
	public void setMP2007_MAJOR_NAME(String mP2007_MAJOR_NAME) {
		MP2007_MAJOR_NAME = mP2007_MAJOR_NAME;
	}
	public String getMP2007_CREATE_USER() {
		return MP2007_CREATE_USER;
	}
	public void setMP2007_CREATE_USER(String mP2007_CREATE_USER) {
		MP2007_CREATE_USER = mP2007_CREATE_USER;
	}
	public Date getMP2007_CREATE_DATETIME() {
		return MP2007_CREATE_DATETIME;
	}
	public void setMP2007_CREATE_DATETIME(Date mP2007_CREATE_DATETIME) {
		MP2007_CREATE_DATETIME = mP2007_CREATE_DATETIME;
	}
	public String getMP2007_EDIT_USER() {
		return MP2007_EDIT_USER;
	}
	public void setMP2007_EDIT_USER(String mP2007_EDIT_USER) {
		MP2007_EDIT_USER = mP2007_EDIT_USER;
	}
	public Date getMP2007_EDIT_DATETIME() {
		return MP2007_EDIT_DATETIME;
	}
	public void setMP2007_EDIT_DATETIME(Date mP2007_EDIT_DATETIME) {
		MP2007_EDIT_DATETIME = mP2007_EDIT_DATETIME;
	}
	@Transient
	public String getMP2007_TYPE_NAME() {
		return MP2007_TYPE_NAME;
	}
	public void setMP2007_TYPE_NAME(String mP2007_TYPE_NAME) {
		MP2007_TYPE_NAME = mP2007_TYPE_NAME;
	}
	@Transient
	public String getMP2007_EMPLOYEE_NAME() {
		return MP2007_EMPLOYEE_NAME;
	}
	public void setMP2007_EMPLOYEE_NAME(String mP2007_EMPLOYEE_NAME) {
		MP2007_EMPLOYEE_NAME = mP2007_EMPLOYEE_NAME;
	}
	@Transient
	public String getMP2007_STATUS_NAME() {
		return MP2007_STATUS_NAME;
	}
	public void setMP2007_STATUS_NAME(String mP2007_STATUS_NAME) {
		MP2007_STATUS_NAME = mP2007_STATUS_NAME;
	}
	@Transient
	public String getMP2007_DEPARTMENT_NUM() {
		return MP2007_DEPARTMENT_NUM;
	}
	public void setMP2007_DEPARTMENT_NUM(String mP2007_DEPARTMENT_NUM) {
		MP2007_DEPARTMENT_NUM = mP2007_DEPARTMENT_NUM;
	}
	@Transient
	public String getMP2007_DEPARTMENT_NAME() {
		return MP2007_DEPARTMENT_NAME;
	}
	public void setMP2007_DEPARTMENT_NAME(String mP2007_DEPARTMENT_NAME) {
		MP2007_DEPARTMENT_NAME = mP2007_DEPARTMENT_NAME;
	}
	@Transient
	public String getMP2007_DAYS_EXT_D() {
		return MP2007_DAYS_EXT_D;
	}
	public void setMP2007_DAYS_EXT_D(String mP2007_DAYS_EXT_D) {
		MP2007_DAYS_EXT_D = mP2007_DAYS_EXT_D;
	}
	@Transient
	public String getMP2007_DAYS_EXT_H() {
		return MP2007_DAYS_EXT_H;
	}
	public void setMP2007_DAYS_EXT_H(String mP2007_DAYS_EXT_H) {
		MP2007_DAYS_EXT_H = mP2007_DAYS_EXT_H;
	}
}
