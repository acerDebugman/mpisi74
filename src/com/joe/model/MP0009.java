package com.joe.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MP0009 implements Serializable {

	private static final long serialVersionUID = 6452247580997416104L;
	
	// Fields
	private int id;
	private String MP0009_POSITION_NAME_E;
	private String MP0009_POSITION_NAME_C;
	private String MP0009_POSITION_STATUS;
	private String MP0009_POSITION_STATUS_NAME;
	private String MP0009_CREATE_USER;
	private Date MP0009_CREATE_DATETIME;
	private String MP0009_EDIT_USER;
	private Date MP0009_EDIT_DATETIME;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMP0009_POSITION_NAME_E() {
		return MP0009_POSITION_NAME_E;
	}
	public void setMP0009_POSITION_NAME_E(String mP0009_POSITION_NAME_E) {
		MP0009_POSITION_NAME_E = mP0009_POSITION_NAME_E;
	}
	public String getMP0009_POSITION_NAME_C() {
		return MP0009_POSITION_NAME_C;
	}
	public void setMP0009_POSITION_NAME_C(String mP0009_POSITION_NAME_C) {
		MP0009_POSITION_NAME_C = mP0009_POSITION_NAME_C;
	}
	public String getMP0009_POSITION_STATUS() {
		return MP0009_POSITION_STATUS;
	}
	public void setMP0009_POSITION_STATUS(String mP0009_POSITION_STATUS) {
		MP0009_POSITION_STATUS = mP0009_POSITION_STATUS;
	}
	public String getMP0009_POSITION_STATUS_NAME() {
		return MP0009_POSITION_STATUS_NAME;
	}
	public void setMP0009_POSITION_STATUS_NAME(String mP0009_POSITION_STATUS_NAME) {
		MP0009_POSITION_STATUS_NAME = mP0009_POSITION_STATUS_NAME;
	}
	public String getMP0009_CREATE_USER() {
		return MP0009_CREATE_USER;
	}
	public void setMP0009_CREATE_USER(String mP0009_CREATE_USER) {
		MP0009_CREATE_USER = mP0009_CREATE_USER;
	}
	public Date getMP0009_CREATE_DATETIME() {
		return MP0009_CREATE_DATETIME;
	}
	public void setMP0009_CREATE_DATETIME(Date mP0009_CREATE_DATETIME) {
		MP0009_CREATE_DATETIME = mP0009_CREATE_DATETIME;
	}
	public String getMP0009_EDIT_USER() {
		return MP0009_EDIT_USER;
	}
	public void setMP0009_EDIT_USER(String mP0009_EDIT_USER) {
		MP0009_EDIT_USER = mP0009_EDIT_USER;
	}
	public Date getMP0009_EDIT_DATETIME() {
		return MP0009_EDIT_DATETIME;
	}
	public void setMP0009_EDIT_DATETIME(Date mP0009_EDIT_DATETIME) {
		MP0009_EDIT_DATETIME = mP0009_EDIT_DATETIME;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}