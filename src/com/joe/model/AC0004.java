package com.joe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AC0004 implements Serializable{
	private static final long serialVersionUID = 796759019338131688L;
	
	private int id;
	private String AC0004_FUN_NUM;
	private String AC0004_OPT_NUM;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAC0004_FUN_NUM() {
		return AC0004_FUN_NUM;
	}
	public void setAC0004_FUN_NUM(String aC0004_FUN_NUM) {
		AC0004_FUN_NUM = aC0004_FUN_NUM;
	}
	public String getAC0004_OPT_NUM() {
		return AC0004_OPT_NUM;
	}
	public void setAC0004_OPT_NUM(String aC0004_OPT_NUM) {
		AC0004_OPT_NUM = aC0004_OPT_NUM;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
