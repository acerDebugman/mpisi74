package com.joe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AC0006 implements Serializable{

	private static final long serialVersionUID = -5952236167689197131L;
	
	private int id;
	private String AC0006_ROLE_NUM;
	private String AC0006_SYS_NUM;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAC0006_ROLE_NUM() {
		return AC0006_ROLE_NUM;
	}
	public void setAC0006_ROLE_NUM(String aC0006_ROLE_NUM) {
		AC0006_ROLE_NUM = aC0006_ROLE_NUM;
	}
	public String getAC0006_SYS_NUM() {
		return AC0006_SYS_NUM;
	}
	public void setAC0006_SYS_NUM(String aC0006_SYS_NUM) {
		AC0006_SYS_NUM = aC0006_SYS_NUM;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
