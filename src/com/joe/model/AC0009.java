package com.joe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AC0009 implements Serializable{

	private static final long serialVersionUID = -669210079427711929L;

	private int id;
	private String AC0009_ROLE_NUM;
	private String AC0009_EMPLOYEE_NUM;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAC0009_ROLE_NUM() {
		return AC0009_ROLE_NUM;
	}
	public void setAC0009_ROLE_NUM(String aC0009_ROLE_NUM) {
		AC0009_ROLE_NUM = aC0009_ROLE_NUM;
	}
	public String getAC0009_EMPLOYEE_NUM() {
		return AC0009_EMPLOYEE_NUM;
	}
	public void setAC0009_EMPLOYEE_NUM(String aC0009_EMPLOYEE_NUM) {
		AC0009_EMPLOYEE_NUM = aC0009_EMPLOYEE_NUM;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}