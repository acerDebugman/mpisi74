package com.joe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JE0201 implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String JE0201_ROOM_NAME;
	private String JE0201_ROOM_FLOOR;
	private String JE0201_ROOM_TYPE;
	private String JE0201_ROOM_DES;
	private String JE0201_STATUS;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getJE0201_ROOM_NAME() {
		return JE0201_ROOM_NAME;
	}
	public void setJE0201_ROOM_NAME(String jE0201_ROOM_NAME) {
		JE0201_ROOM_NAME = jE0201_ROOM_NAME;
	}
	public String getJE0201_ROOM_FLOOR() {
		return JE0201_ROOM_FLOOR;
	}
	public void setJE0201_ROOM_FLOOR(String jE0201_ROOM_FLOOR) {
		JE0201_ROOM_FLOOR = jE0201_ROOM_FLOOR;
	}
	public String getJE0201_ROOM_TYPE() {
		return JE0201_ROOM_TYPE;
	}
	public void setJE0201_ROOM_TYPE(String jE0201_ROOM_TYPE) {
		JE0201_ROOM_TYPE = jE0201_ROOM_TYPE;
	}
	public String getJE0201_ROOM_DES() {
		return JE0201_ROOM_DES;
	}
	public void setJE0201_ROOM_DES(String jE0201_ROOM_DES) {
		JE0201_ROOM_DES = jE0201_ROOM_DES;
	}
	public String getJE0201_STATUS() {
		return JE0201_STATUS;
	}
	public void setJE0201_STATUS(String jE0201_STATUS) {
		JE0201_STATUS = jE0201_STATUS;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}