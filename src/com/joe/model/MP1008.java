package com.joe.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MP1008 implements Serializable{

	private static final long serialVersionUID = 23640459910310211L;
	
	private int id;
	private String MP1008_NAME;
	private String MP1008_PHONE;
	private Date MP1008_INTERVIEW_DATETIME;
	private String MP1008_INTERVIEWER;
	private String MP1008_CV_PATH;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMP1008_NAME() {
		return MP1008_NAME;
	}
	public void setMP1008_NAME(String mP1008_NAME) {
		MP1008_NAME = mP1008_NAME;
	}
	public String getMP1008_PHONE() {
		return MP1008_PHONE;
	}
	public void setMP1008_PHONE(String mP1008_PHONE) {
		MP1008_PHONE = mP1008_PHONE;
	}
	public Date getMP1008_INTERVIEW_DATETIME() {
		return MP1008_INTERVIEW_DATETIME;
	}
	public void setMP1008_INTERVIEW_DATETIME(Date mP1008_INTERVIEW_DATETIME) {
		MP1008_INTERVIEW_DATETIME = mP1008_INTERVIEW_DATETIME;
	}
	public String getMP1008_INTERVIEWER() {
		return MP1008_INTERVIEWER;
	}
	public void setMP1008_INTERVIEWER(String mP1008_INTERVIEWER) {
		MP1008_INTERVIEWER = mP1008_INTERVIEWER;
	}
	public String getMP1008_CV_PATH() {
		return MP1008_CV_PATH;
	}
	public void setMP1008_CV_PATH(String mP1008_CV_PATH) {
		MP1008_CV_PATH = mP1008_CV_PATH;
	}
}
