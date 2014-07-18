package com.joe.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MP1009 implements Serializable{

	private static final long serialVersionUID = -8183207487901905361L;
	
	private int id;
	private String MP1009_EMPLOYEE_NUM;
	private String MP1009_DOCUMENT_NAME;
	private String MP1009_PATH;
	private Date MP1009_UPLOAD_TIME;
	private String MP1009_UPLOADER;
	
	// extended properties
	private String MP1009_EMPLOYEE_NAME;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMP1009_EMPLOYEE_NUM() {
		return MP1009_EMPLOYEE_NUM;
	}

	public void setMP1009_EMPLOYEE_NUM(String mP1009_EMPLOYEE_NUM) {
		MP1009_EMPLOYEE_NUM = mP1009_EMPLOYEE_NUM;
	}

	public String getMP1009_DOCUMENT_NAME() {
		return MP1009_DOCUMENT_NAME;
	}

	public void setMP1009_DOCUMENT_NAME(String mP1009_DOCUMENT_NAME) {
		MP1009_DOCUMENT_NAME = mP1009_DOCUMENT_NAME;
	}

	public String getMP1009_PATH() {
		return MP1009_PATH;
	}

	public void setMP1009_PATH(String mP1009_PATH) {
		MP1009_PATH = mP1009_PATH;
	}

	public Date getMP1009_UPLOAD_TIME() {
		return MP1009_UPLOAD_TIME;
	}

	public void setMP1009_UPLOAD_TIME(Date mP1009_UPLOAD_TIME) {
		MP1009_UPLOAD_TIME = mP1009_UPLOAD_TIME;
	}

	public String getMP1009_UPLOADER() {
		return MP1009_UPLOADER;
	}

	public void setMP1009_UPLOADER(String mP1009_UPLOADER) {
		MP1009_UPLOADER = mP1009_UPLOADER;
	}

	public String getMP1009_EMPLOYEE_NAME() {
		return MP1009_EMPLOYEE_NAME;
	}

	public void setMP1009_EMPLOYEE_NAME(String mP1009_EMPLOYEE_NAME) {
		MP1009_EMPLOYEE_NAME = mP1009_EMPLOYEE_NAME;
	}
}
