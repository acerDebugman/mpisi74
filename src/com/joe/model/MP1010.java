package com.joe.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class MP1010 implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String MP1010_EMPLOYEE_NUM;
	private String MP1010_EMPLOYEE_ID;
	private String MP1010_FIRST_NAME;
	private String MP1010_PREFERED_NAME;
	private String MP1010_DEPARTMENT;
	private String MP1010_GENDER;
	private String MP1010_VISA_TYPE;
	private String MP1010_TEL;
	private String MP1010_NATIONAL;
	private String MP1010_PASSWORD;
	private String MP1010_STATUS;
	private String MP1010_PAYROLL_NUM;
	private String MP1010_CREATE_USER;
	private Date MP1010_CREATE_DATETIME;
	private String MP1010_EDIT_USER;
	private Date MP1010_EDIT_DATETIME;
    

	//extended properties
    private String MP1010_DEPARTMENT_NAME;
    private String MP1010_GENDER_NAME;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMP1010_EMPLOYEE_NUM() {
		return MP1010_EMPLOYEE_NUM;
	}
	public void setMP1010_EMPLOYEE_NUM(String mP1010_EMPLOYEE_NUM) {
		MP1010_EMPLOYEE_NUM = mP1010_EMPLOYEE_NUM;
	}
	public String getMP1010_EMPLOYEE_ID() {
		return MP1010_EMPLOYEE_ID;
	}
	public void setMP1010_EMPLOYEE_ID(String mP1010_EMPLOYEE_ID) {
		MP1010_EMPLOYEE_ID = mP1010_EMPLOYEE_ID;
	}
	public String getMP1010_FIRST_NAME() {
		return MP1010_FIRST_NAME;
	}
	public void setMP1010_FIRST_NAME(String mP1010_FIRST_NAME) {
		MP1010_FIRST_NAME = mP1010_FIRST_NAME;
	}
	public String getMP1010_PREFERED_NAME() {
		return MP1010_PREFERED_NAME;
	}
	public void setMP1010_PREFERED_NAME(String mP1010_PREFERED_NAME) {
		MP1010_PREFERED_NAME = mP1010_PREFERED_NAME;
	}
	public String getMP1010_DEPARTMENT() {
		return MP1010_DEPARTMENT;
	}
	public void setMP1010_DEPARTMENT(String mP1010_DEPARTMENT) {
		MP1010_DEPARTMENT = mP1010_DEPARTMENT;
	}
	public String getMP1010_GENDER() {
		return MP1010_GENDER;
	}
	public void setMP1010_GENDER(String mP1010_GENDER) {
		MP1010_GENDER = mP1010_GENDER;
	}
	public String getMP1010_VISA_TYPE() {
		return MP1010_VISA_TYPE;
	}
	public void setMP1010_VISA_TYPE(String mP1010_VISA_TYPE) {
		MP1010_VISA_TYPE = mP1010_VISA_TYPE;
	}
	public String getMP1010_TEL() {
		return MP1010_TEL;
	}
	public void setMP1010_TEL(String mP1010_TEL) {
		MP1010_TEL = mP1010_TEL;
	}
	public String getMP1010_NATIONAL() {
		return MP1010_NATIONAL;
	}
	public void setMP1010_NATIONAL(String mP1010_NATIONAL) {
		MP1010_NATIONAL = mP1010_NATIONAL;
	}
	public String getMP1010_PASSWORD() {
		return MP1010_PASSWORD;
	}
	public void setMP1010_PASSWORD(String mP1010_PASSWORD) {
		MP1010_PASSWORD = mP1010_PASSWORD;
	}
	public String getMP1010_STATUS() {
		return MP1010_STATUS;
	}
	public void setMP1010_STATUS(String mP1010_STATUS) {
		MP1010_STATUS = mP1010_STATUS;
	}
	public String getMP1010_PAYROLL_NUM() {
		return MP1010_PAYROLL_NUM;
	}
	public void setMP1010_PAYROLL_NUM(String mP1010_PAYROLL_NUM) {
		MP1010_PAYROLL_NUM = mP1010_PAYROLL_NUM;
	}
	public String getMP1010_CREATE_USER() {
		return MP1010_CREATE_USER;
	}
	public void setMP1010_CREATE_USER(String mP1010_CREATE_USER) {
		MP1010_CREATE_USER = mP1010_CREATE_USER;
	}
	public Date getMP1010_CREATE_DATETIME() {
		return MP1010_CREATE_DATETIME;
	}
	public void setMP1010_CREATE_DATETIME(Date mP1010_CREATE_DATETIME) {
		MP1010_CREATE_DATETIME = mP1010_CREATE_DATETIME;
	}
	public String getMP1010_EDIT_USER() {
		return MP1010_EDIT_USER;
	}
	public void setMP1010_EDIT_USER(String mP1010_EDIT_USER) {
		MP1010_EDIT_USER = mP1010_EDIT_USER;
	}
	public Date getMP1010_EDIT_DATETIME() {
		return MP1010_EDIT_DATETIME;
	}
	public void setMP1010_EDIT_DATETIME(Date mP1010_EDIT_DATETIME) {
		MP1010_EDIT_DATETIME = mP1010_EDIT_DATETIME;
	}
	@Transient
	public String getMP1010_DEPARTMENT_NAME() {
		return MP1010_DEPARTMENT_NAME;
	}
	public void setMP1010_DEPARTMENT_NAME(String mP1010_DEPARTMENT_NAME) {
		MP1010_DEPARTMENT_NAME = mP1010_DEPARTMENT_NAME;
	}
	@Transient
	public String getMP1010_GENDER_NAME() {
		return MP1010_GENDER_NAME;
	}
	public void setMP1010_GENDER_NAME(String mP1010_GENDER_NAME) {
		MP1010_GENDER_NAME = mP1010_GENDER_NAME;
	}
}

