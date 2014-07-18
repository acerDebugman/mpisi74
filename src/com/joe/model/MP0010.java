package com.joe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MP0010 implements Serializable{

	private static final long serialVersionUID = -8882938943277674284L;

	// Fields
	private int id;
	private String MP0010_DATETIME;
	private String MP0010_START_TIME;
	private String MP0010_END_TIME;
	private String MP0010_LUNCH_TIME;
	private String MP0010_TOTAL_TIME;
	private String MP0010_OTHER1;
	private String MP0010_OTHER2;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMP0010_DATETIME() {
		return MP0010_DATETIME;
	}
	public void setMP0010_DATETIME(String mP0010_DATETIME) {
		MP0010_DATETIME = mP0010_DATETIME;
	}
	public String getMP0010_START_TIME() {
		return MP0010_START_TIME;
	}
	public void setMP0010_START_TIME(String mP0010_START_TIME) {
		MP0010_START_TIME = mP0010_START_TIME;
	}
	public String getMP0010_END_TIME() {
		return MP0010_END_TIME;
	}
	public void setMP0010_END_TIME(String mP0010_END_TIME) {
		MP0010_END_TIME = mP0010_END_TIME;
	}
	public String getMP0010_LUNCH_TIME() {
		return MP0010_LUNCH_TIME;
	}
	public void setMP0010_LUNCH_TIME(String mP0010_LUNCH_TIME) {
		MP0010_LUNCH_TIME = mP0010_LUNCH_TIME;
	}
	public String getMP0010_TOTAL_TIME() {
		return MP0010_TOTAL_TIME;
	}
	public void setMP0010_TOTAL_TIME(String mP0010_TOTAL_TIME) {
		MP0010_TOTAL_TIME = mP0010_TOTAL_TIME;
	}
	public String getMP0010_OTHER1() {
		return MP0010_OTHER1;
	}
	public void setMP0010_OTHER1(String mP0010_OTHER1) {
		MP0010_OTHER1 = mP0010_OTHER1;
	}
	public String getMP0010_OTHER2() {
		return MP0010_OTHER2;
	}
	public void setMP0010_OTHER2(String mP0010_OTHER2) {
		MP0010_OTHER2 = mP0010_OTHER2;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}