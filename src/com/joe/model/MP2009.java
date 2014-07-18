package com.joe.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class MP2009 implements Serializable{
    private static final long serialVersionUID = 1L;
    
    //fields
    private int id;
    private String MP2009_NUM;
	private String MP2009_EMPLOYEE_NUM;
	private String MP2009_YEAR;
	private String MP2009_MONTH;
	private BigDecimal MP2009_ANNUAL;
	private BigDecimal MP2009_SICK;
	private BigDecimal MP2009_FAMILY_RESP;
	private BigDecimal MP2009_STUDY;
	private BigDecimal MP2009_MATERNITY;
	private BigDecimal MP2009_UNPAID;
	private BigDecimal MP2009_OFFICIAL_BUSINESS;
	private BigDecimal MP2009_LEAVE_EARLY;
	private BigDecimal MP2009_LATE;
	private BigDecimal MP2009_ABSENTEEISM;
    
	//extended properties
    private String MP2009_ANNUAL_EXT;
    private String MP2009_SICK_EXT;;
    private String MP2009_FAMILY_RESP_EXT;
    private String MP2009_STUDY_EXT;
    private String MP2009_MATERNITY_EXT;
    private String MP2009_UNPAID_EXT;
    private String MP2009_OFFICIAL_BUSINESS_EXT;
    private String MP2009_LEAVE_EARLY_EXT;
    private String MP2009_LATE_EXT;
    private String MP2009_ABSENTEEISM_EXT;
    
    private String MP2009_EMPLOYEE_NAME;
    private String MP2009_DEPARTMENT;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMP2009_NUM() {
		return MP2009_NUM;
	}
	public void setMP2009_NUM(String mP2009_NUM) {
		MP2009_NUM = mP2009_NUM;
	}
	public String getMP2009_EMPLOYEE_NUM() {
		return MP2009_EMPLOYEE_NUM;
	}
	public void setMP2009_EMPLOYEE_NUM(String mP2009_EMPLOYEE_NUM) {
		MP2009_EMPLOYEE_NUM = mP2009_EMPLOYEE_NUM;
	}
	public String getMP2009_YEAR() {
		return MP2009_YEAR;
	}
	public void setMP2009_YEAR(String mP2009_YEAR) {
		MP2009_YEAR = mP2009_YEAR;
	}
	public String getMP2009_MONTH() {
		return MP2009_MONTH;
	}
	public void setMP2009_MONTH(String mP2009_MONTH) {
		MP2009_MONTH = mP2009_MONTH;
	}
	public BigDecimal getMP2009_ANNUAL() {
		return MP2009_ANNUAL;
	}
	public void setMP2009_ANNUAL(BigDecimal mP2009_ANNUAL) {
		MP2009_ANNUAL = mP2009_ANNUAL;
	}
	public BigDecimal getMP2009_SICK() {
		return MP2009_SICK;
	}
	public void setMP2009_SICK(BigDecimal mP2009_SICK) {
		MP2009_SICK = mP2009_SICK;
	}
	public BigDecimal getMP2009_FAMILY_RESP() {
		return MP2009_FAMILY_RESP;
	}
	public void setMP2009_FAMILY_RESP(BigDecimal mP2009_FAMILY_RESP) {
		MP2009_FAMILY_RESP = mP2009_FAMILY_RESP;
	}
	public BigDecimal getMP2009_STUDY() {
		return MP2009_STUDY;
	}
	public void setMP2009_STUDY(BigDecimal mP2009_STUDY) {
		MP2009_STUDY = mP2009_STUDY;
	}
	public BigDecimal getMP2009_MATERNITY() {
		return MP2009_MATERNITY;
	}
	public void setMP2009_MATERNITY(BigDecimal mP2009_MATERNITY) {
		MP2009_MATERNITY = mP2009_MATERNITY;
	}
	public BigDecimal getMP2009_UNPAID() {
		return MP2009_UNPAID;
	}
	public void setMP2009_UNPAID(BigDecimal mP2009_UNPAID) {
		MP2009_UNPAID = mP2009_UNPAID;
	}
	public BigDecimal getMP2009_OFFICIAL_BUSINESS() {
		return MP2009_OFFICIAL_BUSINESS;
	}
	public void setMP2009_OFFICIAL_BUSINESS(BigDecimal mP2009_OFFICIAL_BUSINESS) {
		MP2009_OFFICIAL_BUSINESS = mP2009_OFFICIAL_BUSINESS;
	}
	public BigDecimal getMP2009_LEAVE_EARLY() {
		return MP2009_LEAVE_EARLY;
	}
	public void setMP2009_LEAVE_EARLY(BigDecimal mP2009_LEAVE_EARLY) {
		MP2009_LEAVE_EARLY = mP2009_LEAVE_EARLY;
	}
	public BigDecimal getMP2009_LATE() {
		return MP2009_LATE;
	}
	public void setMP2009_LATE(BigDecimal mP2009_LATE) {
		MP2009_LATE = mP2009_LATE;
	}
	public BigDecimal getMP2009_ABSENTEEISM() {
		return MP2009_ABSENTEEISM;
	}
	public void setMP2009_ABSENTEEISM(BigDecimal mP2009_ABSENTEEISM) {
		MP2009_ABSENTEEISM = mP2009_ABSENTEEISM;
	}
	@Transient
	public String getMP2009_ANNUAL_EXT() {
		return MP2009_ANNUAL_EXT;
	}
	public void setMP2009_ANNUAL_EXT(String mP2009_ANNUAL_EXT) {
		MP2009_ANNUAL_EXT = mP2009_ANNUAL_EXT;
	}
	@Transient
	public String getMP2009_SICK_EXT() {
		return MP2009_SICK_EXT;
	}
	public void setMP2009_SICK_EXT(String mP2009_SICK_EXT) {
		MP2009_SICK_EXT = mP2009_SICK_EXT;
	}
	@Transient
	public String getMP2009_FAMILY_RESP_EXT() {
		return MP2009_FAMILY_RESP_EXT;
	}
	public void setMP2009_FAMILY_RESP_EXT(String mP2009_FAMILY_RESP_EXT) {
		MP2009_FAMILY_RESP_EXT = mP2009_FAMILY_RESP_EXT;
	}
	@Transient
	public String getMP2009_STUDY_EXT() {
		return MP2009_STUDY_EXT;
	}
	public void setMP2009_STUDY_EXT(String mP2009_STUDY_EXT) {
		MP2009_STUDY_EXT = mP2009_STUDY_EXT;
	}
	@Transient
	public String getMP2009_MATERNITY_EXT() {
		return MP2009_MATERNITY_EXT;
	}
	public void setMP2009_MATERNITY_EXT(String mP2009_MATERNITY_EXT) {
		MP2009_MATERNITY_EXT = mP2009_MATERNITY_EXT;
	}
	@Transient
	public String getMP2009_UNPAID_EXT() {
		return MP2009_UNPAID_EXT;
	}
	public void setMP2009_UNPAID_EXT(String mP2009_UNPAID_EXT) {
		MP2009_UNPAID_EXT = mP2009_UNPAID_EXT;
	}
	@Transient
	public String getMP2009_OFFICIAL_BUSINESS_EXT() {
		return MP2009_OFFICIAL_BUSINESS_EXT;
	}
	public void setMP2009_OFFICIAL_BUSINESS_EXT(String mP2009_OFFICIAL_BUSINESS_EXT) {
		MP2009_OFFICIAL_BUSINESS_EXT = mP2009_OFFICIAL_BUSINESS_EXT;
	}
	@Transient
	public String getMP2009_LEAVE_EARLY_EXT() {
		return MP2009_LEAVE_EARLY_EXT;
	}
	public void setMP2009_LEAVE_EARLY_EXT(String mP2009_LEAVE_EARLY_EXT) {
		MP2009_LEAVE_EARLY_EXT = mP2009_LEAVE_EARLY_EXT;
	}
	@Transient
	public String getMP2009_LATE_EXT() {
		return MP2009_LATE_EXT;
	}
	public void setMP2009_LATE_EXT(String mP2009_LATE_EXT) {
		MP2009_LATE_EXT = mP2009_LATE_EXT;
	}
	@Transient
	public String getMP2009_ABSENTEEISM_EXT() {
		return MP2009_ABSENTEEISM_EXT;
	}
	public void setMP2009_ABSENTEEISM_EXT(String mP2009_ABSENTEEISM_EXT) {
		MP2009_ABSENTEEISM_EXT = mP2009_ABSENTEEISM_EXT;
	}
	@Transient
	public String getMP2009_EMPLOYEE_NAME() {
		return MP2009_EMPLOYEE_NAME;
	}
	public void setMP2009_EMPLOYEE_NAME(String mP2009_EMPLOYEE_NAME) {
		MP2009_EMPLOYEE_NAME = mP2009_EMPLOYEE_NAME;
	}
	@Transient
	public String getMP2009_DEPARTMENT() {
		return MP2009_DEPARTMENT;
	}
	public void setMP2009_DEPARTMENT(String mP2009_DEPARTMENT) {
		MP2009_DEPARTMENT = mP2009_DEPARTMENT;
	}
}

