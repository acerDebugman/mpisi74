package com.joe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AC0008 implements Serializable{

	private static final long serialVersionUID = 9044134601662198536L;

	private int id;
	private String AC0008_ROLE_NUM;
	private String AC0008_SYS_NUM;
	private String AC0008_FUN_NUM;
	private String AC0008_OPT_NUM;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAC0008_ROLE_NUM() {
		return AC0008_ROLE_NUM;
	}
	public void setAC0008_ROLE_NUM(String aC0008_ROLE_NUM) {
		AC0008_ROLE_NUM = aC0008_ROLE_NUM;
	}
	public String getAC0008_SYS_NUM() {
		return AC0008_SYS_NUM;
	}
	public void setAC0008_SYS_NUM(String aC0008_SYS_NUM) {
		AC0008_SYS_NUM = aC0008_SYS_NUM;
	}
	public String getAC0008_FUN_NUM() {
		return AC0008_FUN_NUM;
	}
	public void setAC0008_FUN_NUM(String aC0008_FUN_NUM) {
		AC0008_FUN_NUM = aC0008_FUN_NUM;
	}
	public String getAC0008_OPT_NUM() {
		return AC0008_OPT_NUM;
	}
	public void setAC0008_OPT_NUM(String aC0008_OPT_NUM) {
		AC0008_OPT_NUM = aC0008_OPT_NUM;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}