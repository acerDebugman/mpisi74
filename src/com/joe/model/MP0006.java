package com.joe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MP0006 implements Serializable{
	private static final long serialVersionUID = 1L;
	// Fields
	private int id;
	private String MP0006_CODE;
	private String MP0006_VALUE;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMP0006_CODE() {
		return MP0006_CODE;
	}
	public void setMP0006_CODE(String mP0006_CODE) {
		MP0006_CODE = mP0006_CODE;
	}
	public String getMP0006_VALUE() {
		return MP0006_VALUE;
	}
	public void setMP0006_VALUE(String mP0006_VALUE) {
		MP0006_VALUE = mP0006_VALUE;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}