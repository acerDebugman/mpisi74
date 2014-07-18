package com.joe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class MP0011 implements Serializable{

	private static final long serialVersionUID = 5868355963307324749L;

	// Fields
	private int id;
	private String MP0011_SYS_CODE;
	private String MP0011_USR_ID;
	private String MP0011_USR_NAME;
	private String MP0011_LOGIN_TIME;
	private String MP0011_LOGIN_IP;
	private String MP0011_MEMO;
	
	//extended properties
	private String MP0011_DEPARTMENT;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMP0011_SYS_CODE() {
		return MP0011_SYS_CODE;
	}

	public void setMP0011_SYS_CODE(String mP0011_SYS_CODE) {
		MP0011_SYS_CODE = mP0011_SYS_CODE;
	}

	public String getMP0011_USR_ID() {
		return MP0011_USR_ID;
	}

	public void setMP0011_USR_ID(String mP0011_USR_ID) {
		MP0011_USR_ID = mP0011_USR_ID;
	}

	public String getMP0011_USR_NAME() {
		return MP0011_USR_NAME;
	}

	public void setMP0011_USR_NAME(String mP0011_USR_NAME) {
		MP0011_USR_NAME = mP0011_USR_NAME;
	}

	public String getMP0011_LOGIN_TIME() {
		return MP0011_LOGIN_TIME;
	}

	public void setMP0011_LOGIN_TIME(String mP0011_LOGIN_TIME) {
		MP0011_LOGIN_TIME = mP0011_LOGIN_TIME;
	}

	public String getMP0011_LOGIN_IP() {
		return MP0011_LOGIN_IP;
	}

	public void setMP0011_LOGIN_IP(String mP0011_LOGIN_IP) {
		MP0011_LOGIN_IP = mP0011_LOGIN_IP;
	}

	public String getMP0011_MEMO() {
		return MP0011_MEMO;
	}

	public void setMP0011_MEMO(String mP0011_MEMO) {
		MP0011_MEMO = mP0011_MEMO;
	}

	@Transient
	public String getMP0011_DEPARTMENT() {
		return MP0011_DEPARTMENT;
	}

	public void setMP0011_DEPARTMENT(String mP0011_DEPARTMENT) {
		MP0011_DEPARTMENT = mP0011_DEPARTMENT;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}