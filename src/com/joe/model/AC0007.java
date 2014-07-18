package com.joe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AC0007 implements Serializable{

	private static final long serialVersionUID = 9029312484247874602L;

	private int id;
	private String AC0007_ROLE_NUM;
	private String AC0007_FUN_NUM;
	private String AC0007_SYS_NUM;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAC0007_ROLE_NUM() {
		return AC0007_ROLE_NUM;
	}
	public void setAC0007_ROLE_NUM(String aC0007_ROLE_NUM) {
		AC0007_ROLE_NUM = aC0007_ROLE_NUM;
	}
	public String getAC0007_FUN_NUM() {
		return AC0007_FUN_NUM;
	}
	public void setAC0007_FUN_NUM(String aC0007_FUN_NUM) {
		AC0007_FUN_NUM = aC0007_FUN_NUM;
	}
	public String getAC0007_SYS_NUM() {
		return AC0007_SYS_NUM;
	}
	public void setAC0007_SYS_NUM(String aC0007_SYS_NUM) {
		AC0007_SYS_NUM = aC0007_SYS_NUM;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
