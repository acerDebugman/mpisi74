package com.joe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MP0002 implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Fields
	private int id;
	private String MP0002_DEPARTMENT_NUM;
	private String MP0002_DEPARTMENT_NAME;
	private String MP0002_DEPARTMENT_DESC;
	private String MP0002_DEPARTMENT_STATUS;
	private String MP0002_CREATE_USER;
	private String MP0002_CREATE_DATETIME;
	private String MP0002_EDIT_USER;
	private String MP0002_EDIT_DATETIME;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMP0002_DEPARTMENT_NUM() {
		return MP0002_DEPARTMENT_NUM;
	}
	public void setMP0002_DEPARTMENT_NUM(String mP0002_DEPARTMENT_NUM) {
		MP0002_DEPARTMENT_NUM = mP0002_DEPARTMENT_NUM;
	}
	public String getMP0002_DEPARTMENT_NAME() {
		return MP0002_DEPARTMENT_NAME;
	}
	public void setMP0002_DEPARTMENT_NAME(String mP0002_DEPARTMENT_NAME) {
		MP0002_DEPARTMENT_NAME = mP0002_DEPARTMENT_NAME;
	}
	public String getMP0002_DEPARTMENT_DESC() {
		return MP0002_DEPARTMENT_DESC;
	}
	public void setMP0002_DEPARTMENT_DESC(String mP0002_DEPARTMENT_DESC) {
		MP0002_DEPARTMENT_DESC = mP0002_DEPARTMENT_DESC;
	}
	public String getMP0002_DEPARTMENT_STATUS() {
		return MP0002_DEPARTMENT_STATUS;
	}
	public void setMP0002_DEPARTMENT_STATUS(String mP0002_DEPARTMENT_STATUS) {
		MP0002_DEPARTMENT_STATUS = mP0002_DEPARTMENT_STATUS;
	}
	public String getMP0002_CREATE_USER() {
		return MP0002_CREATE_USER;
	}
	public void setMP0002_CREATE_USER(String mP0002_CREATE_USER) {
		MP0002_CREATE_USER = mP0002_CREATE_USER;
	}
	public String getMP0002_CREATE_DATETIME() {
		return MP0002_CREATE_DATETIME;
	}
	public void setMP0002_CREATE_DATETIME(String mP0002_CREATE_DATETIME) {
		MP0002_CREATE_DATETIME = mP0002_CREATE_DATETIME;
	}
	public String getMP0002_EDIT_USER() {
		return MP0002_EDIT_USER;
	}
	public void setMP0002_EDIT_USER(String mP0002_EDIT_USER) {
		MP0002_EDIT_USER = mP0002_EDIT_USER;
	}
	public String getMP0002_EDIT_DATETIME() {
		return MP0002_EDIT_DATETIME;
	}
	public void setMP0002_EDIT_DATETIME(String mP0002_EDIT_DATETIME) {
		MP0002_EDIT_DATETIME = mP0002_EDIT_DATETIME;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}