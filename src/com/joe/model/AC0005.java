package com.joe.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class AC0005 implements Serializable{

	private static final long serialVersionUID = -656724818340529255L;

	private int id;
	private String name;
//	private String ;
	
	//extended value
	private String AC0005_ROLE_STATUS_NAME;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAC0005_ROLE_NUM() {
		return AC0005_ROLE_NUM;
	}

	public void setAC0005_ROLE_NUM(String aC0005_ROLE_NUM) {
		AC0005_ROLE_NUM = aC0005_ROLE_NUM;
	}

	public String getAC0005_ROLE_NAME() {
		return AC0005_ROLE_NAME;
	}

	public void setAC0005_ROLE_NAME(String aC0005_ROLE_NAME) {
		AC0005_ROLE_NAME = aC0005_ROLE_NAME;
	}

	public String getAC0005_ROLE_STATUS() {
		return AC0005_ROLE_STATUS;
	}

	public void setAC0005_ROLE_STATUS(String aC0005_ROLE_STATUS) {
		AC0005_ROLE_STATUS = aC0005_ROLE_STATUS;
	}

	public String getAC0005_ROLE_DESC() {
		return AC0005_ROLE_DESC;
	}

	public void setAC0005_ROLE_DESC(String aC0005_ROLE_DESC) {
		AC0005_ROLE_DESC = aC0005_ROLE_DESC;
	}

	public String getAC0005_ROLE_COMMENT() {
		return AC0005_ROLE_COMMENT;
	}

	public void setAC0005_ROLE_COMMENT(String aC0005_ROLE_COMMENT) {
		AC0005_ROLE_COMMENT = aC0005_ROLE_COMMENT;
	}

	public String getAC0005_CREATE_USER() {
		return AC0005_CREATE_USER;
	}

	public void setAC0005_CREATE_USER(String aC0005_CREATE_USER) {
		AC0005_CREATE_USER = aC0005_CREATE_USER;
	}

	public Date getAC0005_CREATE_DATETIME() {
		return AC0005_CREATE_DATETIME;
	}

	public void setAC0005_CREATE_DATETIME(Date aC0005_CREATE_DATETIME) {
		AC0005_CREATE_DATETIME = aC0005_CREATE_DATETIME;
	}

	public String getAC0005_EDIT_USER() {
		return AC0005_EDIT_USER;
	}

	public void setAC0005_EDIT_USER(String aC0005_EDIT_USER) {
		AC0005_EDIT_USER = aC0005_EDIT_USER;
	}

	public Date getAC0005_EDIT_DATETIME() {
		return AC0005_EDIT_DATETIME;
	}

	public void setAC0005_EDIT_DATETIME(Date aC0005_EDIT_DATETIME) {
		AC0005_EDIT_DATETIME = aC0005_EDIT_DATETIME;
	}

	@Transient
	public String getAC0005_ROLE_STATUS_NAME() {
		return AC0005_ROLE_STATUS_NAME;
	}

	public void setAC0005_ROLE_STATUS_NAME(String aC0005_ROLE_STATUS_NAME) {
		AC0005_ROLE_STATUS_NAME = aC0005_ROLE_STATUS_NAME;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
