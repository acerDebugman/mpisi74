package com.joe.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MP1005 implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Fields
	private int id;
	private String MP1005_EMPLOYEE_NUM;
	private String MP1005_NAME;
	private String MP1005_RELATIONSHIP;
	private String MP1005_TELEPHONE;
	private String MP1005_EMAIL;
	private String MP1005_PHYSICAL_ADDRESS;
	private String MP1005_CREATE_USER;
	private Date MP1005_CREATE_DATETIME;
	private String MP1005_EDIT_USER;
	private Date MP1005_EDIT_DATETIME;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMP1005_EMPLOYEE_NUM() {
		return MP1005_EMPLOYEE_NUM;
	}
	public void setMP1005_EMPLOYEE_NUM(String mP1005_EMPLOYEE_NUM) {
		MP1005_EMPLOYEE_NUM = mP1005_EMPLOYEE_NUM;
	}
	public String getMP1005_NAME() {
		return MP1005_NAME;
	}
	public void setMP1005_NAME(String mP1005_NAME) {
		MP1005_NAME = mP1005_NAME;
	}
	public String getMP1005_RELATIONSHIP() {
		return MP1005_RELATIONSHIP;
	}
	public void setMP1005_RELATIONSHIP(String mP1005_RELATIONSHIP) {
		MP1005_RELATIONSHIP = mP1005_RELATIONSHIP;
	}
	public String getMP1005_TELEPHONE() {
		return MP1005_TELEPHONE;
	}
	public void setMP1005_TELEPHONE(String mP1005_TELEPHONE) {
		MP1005_TELEPHONE = mP1005_TELEPHONE;
	}
	public String getMP1005_EMAIL() {
		return MP1005_EMAIL;
	}
	public void setMP1005_EMAIL(String mP1005_EMAIL) {
		MP1005_EMAIL = mP1005_EMAIL;
	}
	public String getMP1005_PHYSICAL_ADDRESS() {
		return MP1005_PHYSICAL_ADDRESS;
	}
	public void setMP1005_PHYSICAL_ADDRESS(String mP1005_PHYSICAL_ADDRESS) {
		MP1005_PHYSICAL_ADDRESS = mP1005_PHYSICAL_ADDRESS;
	}
	public String getMP1005_CREATE_USER() {
		return MP1005_CREATE_USER;
	}
	public void setMP1005_CREATE_USER(String mP1005_CREATE_USER) {
		MP1005_CREATE_USER = mP1005_CREATE_USER;
	}
	public Date getMP1005_CREATE_DATETIME() {
		return MP1005_CREATE_DATETIME;
	}
	public void setMP1005_CREATE_DATETIME(Date mP1005_CREATE_DATETIME) {
		MP1005_CREATE_DATETIME = mP1005_CREATE_DATETIME;
	}
	public String getMP1005_EDIT_USER() {
		return MP1005_EDIT_USER;
	}
	public void setMP1005_EDIT_USER(String mP1005_EDIT_USER) {
		MP1005_EDIT_USER = mP1005_EDIT_USER;
	}
	public Date getMP1005_EDIT_DATETIME() {
		return MP1005_EDIT_DATETIME;
	}
	public void setMP1005_EDIT_DATETIME(Date mP1005_EDIT_DATETIME) {
		MP1005_EDIT_DATETIME = mP1005_EDIT_DATETIME;
	}
}