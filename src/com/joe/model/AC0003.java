package com.joe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class AC0003 implements Serializable{

	private static final long serialVersionUID = 1935516665930648504L;

	private int id;
	private String AC0003_OPT_NUM;
	private String AC0003_OPT_NAME;
	private String AC0003_STATUS;
	private String AC0003_DES;
	private String AC0003_COMMENT;
	private String AC0003_CREATE_USER;
	private String AC0003_CREATE_DATE;
	private String AC0003_EDIT_USER;
	private String AC0003_EDIT_DATE;
	
	//extended properties
	private String AC0003_STATUS_NAME;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAC0003_OPT_NUM() {
		return AC0003_OPT_NUM;
	}

	public void setAC0003_OPT_NUM(String aC0003_OPT_NUM) {
		AC0003_OPT_NUM = aC0003_OPT_NUM;
	}

	public String getAC0003_OPT_NAME() {
		return AC0003_OPT_NAME;
	}

	public void setAC0003_OPT_NAME(String aC0003_OPT_NAME) {
		AC0003_OPT_NAME = aC0003_OPT_NAME;
	}

	public String getAC0003_STATUS() {
		return AC0003_STATUS;
	}

	public void setAC0003_STATUS(String aC0003_STATUS) {
		AC0003_STATUS = aC0003_STATUS;
	}

	public String getAC0003_DES() {
		return AC0003_DES;
	}

	public void setAC0003_DES(String aC0003_DES) {
		AC0003_DES = aC0003_DES;
	}

	public String getAC0003_COMMENT() {
		return AC0003_COMMENT;
	}

	public void setAC0003_COMMENT(String aC0003_COMMENT) {
		AC0003_COMMENT = aC0003_COMMENT;
	}

	public String getAC0003_CREATE_USER() {
		return AC0003_CREATE_USER;
	}

	public void setAC0003_CREATE_USER(String aC0003_CREATE_USER) {
		AC0003_CREATE_USER = aC0003_CREATE_USER;
	}

	public String getAC0003_CREATE_DATE() {
		return AC0003_CREATE_DATE;
	}

	public void setAC0003_CREATE_DATE(String aC0003_CREATE_DATE) {
		AC0003_CREATE_DATE = aC0003_CREATE_DATE;
	}

	public String getAC0003_EDIT_USER() {
		return AC0003_EDIT_USER;
	}

	public void setAC0003_EDIT_USER(String aC0003_EDIT_USER) {
		AC0003_EDIT_USER = aC0003_EDIT_USER;
	}

	public String getAC0003_EDIT_DATE() {
		return AC0003_EDIT_DATE;
	}

	public void setAC0003_EDIT_DATE(String aC0003_EDIT_DATE) {
		AC0003_EDIT_DATE = aC0003_EDIT_DATE;
	}
	@Transient
	public String getAC0003_STATUS_NAME() {
		return AC0003_STATUS_NAME;
	}

	public void setAC0003_STATUS_NAME(String aC0003_STATUS_NAME) {
		AC0003_STATUS_NAME = aC0003_STATUS_NAME;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
