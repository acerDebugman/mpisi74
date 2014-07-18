package com.joe.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class MP2008 implements Serializable{
    private static final long serialVersionUID = 1L;
    
    //fields
    private int id;
    private String MP2008_NUM;
	private String MP2008_EMPLOYEE_NUM;
	private String MP2008_DEPARTMENT;
	private String MP2008_DATE;
	private String MP2008_HOURS_TOTAL;
	private String MP2008_HOURS;
	private String MP2008_FROM_DATETIME;
	private String MP2008_TO_DATETIME;
	private String MP2008_FROM_MINUTE;
	private String MP2008_TO_MINUTE;
	private String MP2008_REASON;
	private String MP2008_APP_STATUS;
	private String MP2008_DATA_STATUS;
	private String MP2008_COMMENT;
	private String MP2008_RATING;
	private String MP2008_CREATE_USER;
	private Date MP2008_CREATE_DATETIME;
	private String MP2008_EDIT_USER;
	private Date MP2008_EDIT_DATETIME;
	private String MP2008_APPROVAL_USER;
	private Date MP2008_APPROVAL_DATETIME;
    
    //extended properties
    private String MP2008_EMPLOYEE_NAME;
    private String MP2008_DEPARTMENT_NAME;
    private String MP2008_APPROVAL_USER_NAME;
    private String MP2008_APP_STATUS_NAME;
    private String MP2008_DATA_STATUS_NAME;
    private String MP2008_CREATE_USER_NAME;
    private String MP2008_EDIT_USER_NAME;
    private String MP2008_CLOCK_IN;
    private String MP2008_CLOCK_OUT;
    private String MP2008_PAYROLL_NUM;
    private String MP2008_GENDER;
    private String MP2008_EMPLOYEE_2;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMP2008_NUM() {
		return MP2008_NUM;
	}
	public void setMP2008_NUM(String mP2008_NUM) {
		MP2008_NUM = mP2008_NUM;
	}
	public String getMP2008_EMPLOYEE_NUM() {
		return MP2008_EMPLOYEE_NUM;
	}
	public void setMP2008_EMPLOYEE_NUM(String mP2008_EMPLOYEE_NUM) {
		MP2008_EMPLOYEE_NUM = mP2008_EMPLOYEE_NUM;
	}
	public String getMP2008_DEPARTMENT() {
		return MP2008_DEPARTMENT;
	}
	public void setMP2008_DEPARTMENT(String mP2008_DEPARTMENT) {
		MP2008_DEPARTMENT = mP2008_DEPARTMENT;
	}
	public String getMP2008_DATE() {
		return MP2008_DATE;
	}
	public void setMP2008_DATE(String mP2008_DATE) {
		MP2008_DATE = mP2008_DATE;
	}
	public String getMP2008_HOURS_TOTAL() {
		return MP2008_HOURS_TOTAL;
	}
	public void setMP2008_HOURS_TOTAL(String mP2008_HOURS_TOTAL) {
		MP2008_HOURS_TOTAL = mP2008_HOURS_TOTAL;
	}
	public String getMP2008_HOURS() {
		return MP2008_HOURS;
	}
	public void setMP2008_HOURS(String mP2008_HOURS) {
		MP2008_HOURS = mP2008_HOURS;
	}
	public String getMP2008_FROM_DATETIME() {
		return MP2008_FROM_DATETIME;
	}
	public void setMP2008_FROM_DATETIME(String mP2008_FROM_DATETIME) {
		MP2008_FROM_DATETIME = mP2008_FROM_DATETIME;
	}
	public String getMP2008_TO_DATETIME() {
		return MP2008_TO_DATETIME;
	}
	public void setMP2008_TO_DATETIME(String mP2008_TO_DATETIME) {
		MP2008_TO_DATETIME = mP2008_TO_DATETIME;
	}
	public String getMP2008_FROM_MINUTE() {
		return MP2008_FROM_MINUTE;
	}
	public void setMP2008_FROM_MINUTE(String mP2008_FROM_MINUTE) {
		MP2008_FROM_MINUTE = mP2008_FROM_MINUTE;
	}
	public String getMP2008_TO_MINUTE() {
		return MP2008_TO_MINUTE;
	}
	public void setMP2008_TO_MINUTE(String mP2008_TO_MINUTE) {
		MP2008_TO_MINUTE = mP2008_TO_MINUTE;
	}
	public String getMP2008_REASON() {
		return MP2008_REASON;
	}
	public void setMP2008_REASON(String mP2008_REASON) {
		MP2008_REASON = mP2008_REASON;
	}
	public String getMP2008_APP_STATUS() {
		return MP2008_APP_STATUS;
	}
	public void setMP2008_APP_STATUS(String mP2008_APP_STATUS) {
		MP2008_APP_STATUS = mP2008_APP_STATUS;
	}
	public String getMP2008_DATA_STATUS() {
		return MP2008_DATA_STATUS;
	}
	public void setMP2008_DATA_STATUS(String mP2008_DATA_STATUS) {
		MP2008_DATA_STATUS = mP2008_DATA_STATUS;
	}
	public String getMP2008_COMMENT() {
		return MP2008_COMMENT;
	}
	public void setMP2008_COMMENT(String mP2008_COMMENT) {
		MP2008_COMMENT = mP2008_COMMENT;
	}
	public String getMP2008_RATING() {
		return MP2008_RATING;
	}
	public void setMP2008_RATING(String mP2008_RATING) {
		MP2008_RATING = mP2008_RATING;
	}
	public String getMP2008_CREATE_USER() {
		return MP2008_CREATE_USER;
	}
	public void setMP2008_CREATE_USER(String mP2008_CREATE_USER) {
		MP2008_CREATE_USER = mP2008_CREATE_USER;
	}
	public Date getMP2008_CREATE_DATETIME() {
		return MP2008_CREATE_DATETIME;
	}
	public void setMP2008_CREATE_DATETIME(Date mP2008_CREATE_DATETIME) {
		MP2008_CREATE_DATETIME = mP2008_CREATE_DATETIME;
	}
	public String getMP2008_EDIT_USER() {
		return MP2008_EDIT_USER;
	}
	public void setMP2008_EDIT_USER(String mP2008_EDIT_USER) {
		MP2008_EDIT_USER = mP2008_EDIT_USER;
	}
	public Date getMP2008_EDIT_DATETIME() {
		return MP2008_EDIT_DATETIME;
	}
	public void setMP2008_EDIT_DATETIME(Date mP2008_EDIT_DATETIME) {
		MP2008_EDIT_DATETIME = mP2008_EDIT_DATETIME;
	}
	public String getMP2008_APPROVAL_USER() {
		return MP2008_APPROVAL_USER;
	}
	public void setMP2008_APPROVAL_USER(String mP2008_APPROVAL_USER) {
		MP2008_APPROVAL_USER = mP2008_APPROVAL_USER;
	}
	public Date getMP2008_APPROVAL_DATETIME() {
		return MP2008_APPROVAL_DATETIME;
	}
	public void setMP2008_APPROVAL_DATETIME(Date mP2008_APPROVAL_DATETIME) {
		MP2008_APPROVAL_DATETIME = mP2008_APPROVAL_DATETIME;
	}
	@Transient
	public String getMP2008_EMPLOYEE_NAME() {
		return MP2008_EMPLOYEE_NAME;
	}
	public void setMP2008_EMPLOYEE_NAME(String mP2008_EMPLOYEE_NAME) {
		MP2008_EMPLOYEE_NAME = mP2008_EMPLOYEE_NAME;
	}
	@Transient
	public String getMP2008_DEPARTMENT_NAME() {
		return MP2008_DEPARTMENT_NAME;
	}
	public void setMP2008_DEPARTMENT_NAME(String mP2008_DEPARTMENT_NAME) {
		MP2008_DEPARTMENT_NAME = mP2008_DEPARTMENT_NAME;
	}
	@Transient
	public String getMP2008_APPROVAL_USER_NAME() {
		return MP2008_APPROVAL_USER_NAME;
	}
	public void setMP2008_APPROVAL_USER_NAME(String mP2008_APPROVAL_USER_NAME) {
		MP2008_APPROVAL_USER_NAME = mP2008_APPROVAL_USER_NAME;
	}
	@Transient
	public String getMP2008_APP_STATUS_NAME() {
		return MP2008_APP_STATUS_NAME;
	}
	public void setMP2008_APP_STATUS_NAME(String mP2008_APP_STATUS_NAME) {
		MP2008_APP_STATUS_NAME = mP2008_APP_STATUS_NAME;
	}
	@Transient
	public String getMP2008_DATA_STATUS_NAME() {
		return MP2008_DATA_STATUS_NAME;
	}
	public void setMP2008_DATA_STATUS_NAME(String mP2008_DATA_STATUS_NAME) {
		MP2008_DATA_STATUS_NAME = mP2008_DATA_STATUS_NAME;
	}
	@Transient
	public String getMP2008_CREATE_USER_NAME() {
		return MP2008_CREATE_USER_NAME;
	}
	public void setMP2008_CREATE_USER_NAME(String mP2008_CREATE_USER_NAME) {
		MP2008_CREATE_USER_NAME = mP2008_CREATE_USER_NAME;
	}
	@Transient
	public String getMP2008_EDIT_USER_NAME() {
		return MP2008_EDIT_USER_NAME;
	}
	public void setMP2008_EDIT_USER_NAME(String mP2008_EDIT_USER_NAME) {
		MP2008_EDIT_USER_NAME = mP2008_EDIT_USER_NAME;
	}
	@Transient
	public String getMP2008_CLOCK_IN() {
		return MP2008_CLOCK_IN;
	}
	public void setMP2008_CLOCK_IN(String mP2008_CLOCK_IN) {
		MP2008_CLOCK_IN = mP2008_CLOCK_IN;
	}
	@Transient
	public String getMP2008_CLOCK_OUT() {
		return MP2008_CLOCK_OUT;
	}
	public void setMP2008_CLOCK_OUT(String mP2008_CLOCK_OUT) {
		MP2008_CLOCK_OUT = mP2008_CLOCK_OUT;
	}
	@Transient
	public String getMP2008_PAYROLL_NUM() {
		return MP2008_PAYROLL_NUM;
	}
	public void setMP2008_PAYROLL_NUM(String mP2008_PAYROLL_NUM) {
		MP2008_PAYROLL_NUM = mP2008_PAYROLL_NUM;
	}
	@Transient
	public String getMP2008_GENDER() {
		return MP2008_GENDER;
	}
	public void setMP2008_GENDER(String mP2008_GENDER) {
		MP2008_GENDER = mP2008_GENDER;
	}
	@Transient
	public String getMP2008_EMPLOYEE_2() {
		return MP2008_EMPLOYEE_2;
	}
	public void setMP2008_EMPLOYEE_2(String mP2008_EMPLOYEE_2) {
		MP2008_EMPLOYEE_2 = mP2008_EMPLOYEE_2;
	}
}
