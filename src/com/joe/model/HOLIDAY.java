package com.joe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HOLIDAY implements Serializable{
	private static final long serialVersionUID = 1653374236079089849L;
	
	// Fields
	private int id;
	private String HOLIDAY_NAME;
	private String HOLIDAY_DATE;
	private String HOLIDAY_YEAR;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHOLIDAY_NAME() {
		return HOLIDAY_NAME;
	}
	public void setHOLIDAY_NAME(String hOLIDAY_NAME) {
		HOLIDAY_NAME = hOLIDAY_NAME;
	}
	public String getHOLIDAY_DATE() {
		return HOLIDAY_DATE;
	}
	public void setHOLIDAY_DATE(String hOLIDAY_DATE) {
		HOLIDAY_DATE = hOLIDAY_DATE;
	}
	public String getHOLIDAY_YEAR() {
		return HOLIDAY_YEAR;
	}
	public void setHOLIDAY_YEAR(String hOLIDAY_YEAR) {
		HOLIDAY_YEAR = hOLIDAY_YEAR;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}