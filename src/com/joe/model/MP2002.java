package com.joe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class MP2002 implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Fields
	private int id;
	private String MP2002_EMPLOYEE_NUM;
	private String MP2002_YEAR;
	private String MP2002_ANNUAL;
	private String MP2002_ANNUAL_T;
	private String MP2002_ANNUAL_MAX;
	private String MP2002_SICK;
	private String MP2002_SICK_T;
	private String MP2002_FAMILY_RESP;
	private String MP2002_FAMILY_RESP_T;
	private String MP2002_MATERNITY;
	private String MP2002_MATERNITY_T;
	private String MP2002_STUDY;
	private String MP2002_STUDY_T;
	
	//extended properties
	private String MP2002_EMPLOYEE_NAME;
	private String MP2002_ANNUAL_A;
	private String MP2002_SICK_A;
	private String MP2002_FAMILY_RESP_A;
	private String MP2002_MATERNITY_A;
	private String MP2002_STUDY_A;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMP2002_EMPLOYEE_NUM() {
		return MP2002_EMPLOYEE_NUM;
	}
	public void setMP2002_EMPLOYEE_NUM(String mP2002_EMPLOYEE_NUM) {
		MP2002_EMPLOYEE_NUM = mP2002_EMPLOYEE_NUM;
	}
	public String getMP2002_YEAR() {
		return MP2002_YEAR;
	}
	public void setMP2002_YEAR(String mP2002_YEAR) {
		MP2002_YEAR = mP2002_YEAR;
	}
	public String getMP2002_ANNUAL() {
		return MP2002_ANNUAL;
	}
	public void setMP2002_ANNUAL(String mP2002_ANNUAL) {
		MP2002_ANNUAL = mP2002_ANNUAL;
	}
	public String getMP2002_ANNUAL_T() {
		return MP2002_ANNUAL_T;
	}
	public void setMP2002_ANNUAL_T(String mP2002_ANNUAL_T) {
		MP2002_ANNUAL_T = mP2002_ANNUAL_T;
	}
	public String getMP2002_ANNUAL_MAX() {
		return MP2002_ANNUAL_MAX;
	}
	public void setMP2002_ANNUAL_MAX(String mP2002_ANNUAL_MAX) {
		MP2002_ANNUAL_MAX = mP2002_ANNUAL_MAX;
	}
	public String getMP2002_SICK() {
		return MP2002_SICK;
	}
	public void setMP2002_SICK(String mP2002_SICK) {
		MP2002_SICK = mP2002_SICK;
	}
	public String getMP2002_SICK_T() {
		return MP2002_SICK_T;
	}
	public void setMP2002_SICK_T(String mP2002_SICK_T) {
		MP2002_SICK_T = mP2002_SICK_T;
	}
	public String getMP2002_FAMILY_RESP() {
		return MP2002_FAMILY_RESP;
	}
	public void setMP2002_FAMILY_RESP(String mP2002_FAMILY_RESP) {
		MP2002_FAMILY_RESP = mP2002_FAMILY_RESP;
	}
	public String getMP2002_FAMILY_RESP_T() {
		return MP2002_FAMILY_RESP_T;
	}
	public void setMP2002_FAMILY_RESP_T(String mP2002_FAMILY_RESP_T) {
		MP2002_FAMILY_RESP_T = mP2002_FAMILY_RESP_T;
	}
	public String getMP2002_MATERNITY() {
		return MP2002_MATERNITY;
	}
	public void setMP2002_MATERNITY(String mP2002_MATERNITY) {
		MP2002_MATERNITY = mP2002_MATERNITY;
	}
	public String getMP2002_MATERNITY_T() {
		return MP2002_MATERNITY_T;
	}
	public void setMP2002_MATERNITY_T(String mP2002_MATERNITY_T) {
		MP2002_MATERNITY_T = mP2002_MATERNITY_T;
	}
	public String getMP2002_STUDY() {
		return MP2002_STUDY;
	}
	public void setMP2002_STUDY(String mP2002_STUDY) {
		MP2002_STUDY = mP2002_STUDY;
	}
	public String getMP2002_STUDY_T() {
		return MP2002_STUDY_T;
	}
	public void setMP2002_STUDY_T(String mP2002_STUDY_T) {
		MP2002_STUDY_T = mP2002_STUDY_T;
	}
	@Transient
	public String getMP2002_EMPLOYEE_NAME() {
		return MP2002_EMPLOYEE_NAME;
	}
	public void setMP2002_EMPLOYEE_NAME(String mP2002_EMPLOYEE_NAME) {
		MP2002_EMPLOYEE_NAME = mP2002_EMPLOYEE_NAME;
	}
	@Transient
	public String getMP2002_ANNUAL_A() {
		return MP2002_ANNUAL_A;
	}
	public void setMP2002_ANNUAL_A(String mP2002_ANNUAL_A) {
		MP2002_ANNUAL_A = mP2002_ANNUAL_A;
	}
	@Transient
	public String getMP2002_SICK_A() {
		return MP2002_SICK_A;
	}
	public void setMP2002_SICK_A(String mP2002_SICK_A) {
		MP2002_SICK_A = mP2002_SICK_A;
	}
	@Transient
	public String getMP2002_FAMILY_RESP_A() {
		return MP2002_FAMILY_RESP_A;
	}
	public void setMP2002_FAMILY_RESP_A(String mP2002_FAMILY_RESP_A) {
		MP2002_FAMILY_RESP_A = mP2002_FAMILY_RESP_A;
	}
	@Transient
	public String getMP2002_MATERNITY_A() {
		return MP2002_MATERNITY_A;
	}
	public void setMP2002_MATERNITY_A(String mP2002_MATERNITY_A) {
		MP2002_MATERNITY_A = mP2002_MATERNITY_A;
	}
	@Transient
	public String getMP2002_STUDY_A() {
		return MP2002_STUDY_A;
	}
	public void setMP2002_STUDY_A(String mP2002_STUDY_A) {
		MP2002_STUDY_A = mP2002_STUDY_A;
	}
}
