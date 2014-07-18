package com.joe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MP0008 implements Serializable {
	private static final long serialVersionUID = 8211088995831749729L;

	// Fields
	private int id;
	private int MP0008_DEPARTMENT_ID;
	private int MP0008_POSITION_ID;
	private String MP0008_POSITION_NAME_E;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMP0008_DEPARTMENT_ID() {
		return MP0008_DEPARTMENT_ID;
	}
	public void setMP0008_DEPARTMENT_ID(int mP0008_DEPARTMENT_ID) {
		MP0008_DEPARTMENT_ID = mP0008_DEPARTMENT_ID;
	}
	public int getMP0008_POSITION_ID() {
		return MP0008_POSITION_ID;
	}
	public void setMP0008_POSITION_ID(int mP0008_POSITION_ID) {
		MP0008_POSITION_ID = mP0008_POSITION_ID;
	}
	public String getMP0008_POSITION_NAME_E() {
		return MP0008_POSITION_NAME_E;
	}
	public void setMP0008_POSITION_NAME_E(String mP0008_POSITION_NAME_E) {
		MP0008_POSITION_NAME_E = mP0008_POSITION_NAME_E;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}