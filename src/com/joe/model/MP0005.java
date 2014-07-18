package com.joe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class MP0005 implements Serializable {
	private static final long serialVersionUID = 1L;

	//Fields
	private int id;
	private int MP0005_DEPART_ID;
	private String MP0005_TITLE;
	private String MP0005_CONTENT;
	private String MP0005_AUTHOR;
	private String MP0005_ADDTIME;
	private String MP0005_TYPE;
	
	//extended properties
	private String MP0005_DEPART_NAME;
	private String MP0005_AUTHOR_NAME;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMP0005_DEPART_ID() {
		return MP0005_DEPART_ID;
	}
	public void setMP0005_DEPART_ID(int mP0005_DEPART_ID) {
		MP0005_DEPART_ID = mP0005_DEPART_ID;
	}
	public String getMP0005_TITLE() {
		return MP0005_TITLE;
	}
	public void setMP0005_TITLE(String mP0005_TITLE) {
		MP0005_TITLE = mP0005_TITLE;
	}
	public String getMP0005_CONTENT() {
		return MP0005_CONTENT;
	}
	public void setMP0005_CONTENT(String mP0005_CONTENT) {
		MP0005_CONTENT = mP0005_CONTENT;
	}
	public String getMP0005_AUTHOR() {
		return MP0005_AUTHOR;
	}
	public void setMP0005_AUTHOR(String mP0005_AUTHOR) {
		MP0005_AUTHOR = mP0005_AUTHOR;
	}
	public String getMP0005_ADDTIME() {
		return MP0005_ADDTIME;
	}
	public void setMP0005_ADDTIME(String mP0005_ADDTIME) {
		MP0005_ADDTIME = mP0005_ADDTIME;
	}
	public String getMP0005_TYPE() {
		return MP0005_TYPE;
	}
	public void setMP0005_TYPE(String mP0005_TYPE) {
		MP0005_TYPE = mP0005_TYPE;
	}
	@Transient
	public String getMP0005_DEPART_NAME() {
		return MP0005_DEPART_NAME;
	}
	public void setMP0005_DEPART_NAME(String mP0005_DEPART_NAME) {
		MP0005_DEPART_NAME = mP0005_DEPART_NAME;
	}
	@Transient
	public String getMP0005_AUTHOR_NAME() {
		return MP0005_AUTHOR_NAME;
	}
	public void setMP0005_AUTHOR_NAME(String mP0005_AUTHOR_NAME) {
		MP0005_AUTHOR_NAME = mP0005_AUTHOR_NAME;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}