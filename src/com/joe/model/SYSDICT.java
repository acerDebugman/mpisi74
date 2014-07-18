package com.joe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SYSDICT implements Serializable {
	private int id;
	private String JE0101_PROPERTY;
	private String JE0101_VALUE;
	private String JE0101_TYPE;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJE0101_PROPERTY() {
		return JE0101_PROPERTY;
	}
	public void setJE0101_PROPERTY(String jE0101_PROPERTY) {
		JE0101_PROPERTY = jE0101_PROPERTY;
	}
	public String getJE0101_VALUE() {
		return JE0101_VALUE;
	}
	public void setJE0101_VALUE(String jE0101_VALUE) {
		JE0101_VALUE = jE0101_VALUE;
	}
	public String getJE0101_TYPE() {
		return JE0101_TYPE;
	}
	public void setJE0101_TYPE(String jE0101_TYPE) {
		JE0101_TYPE = jE0101_TYPE;
	}
}
