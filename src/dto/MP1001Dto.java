package dto;

import java.io.Serializable;

public class MP1001Dto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Fields
	private String MP1001_EMPLOYEE_NUM;
	private String MP1001_EMPLOYEE_ID;
	private String MP1001_SURNAME;
	private String MP1001_FIRSTNAME;
	private String MP1001_PREFERED_NAME;
	private String MP1001_DEPARTMENT_ID;
	private String MP1001_DEPARTMENT_NAME;
	
	public String getMP1001_EMPLOYEE_NUM() {
		return MP1001_EMPLOYEE_NUM;
	}
	public void setMP1001_EMPLOYEE_NUM(String mP1001_EMPLOYEE_NUM) {
		MP1001_EMPLOYEE_NUM = mP1001_EMPLOYEE_NUM;
	}
	public String getMP1001_EMPLOYEE_ID() {
		return MP1001_EMPLOYEE_ID;
	}
	public void setMP1001_EMPLOYEE_ID(String mP1001_EMPLOYEE_ID) {
		MP1001_EMPLOYEE_ID = mP1001_EMPLOYEE_ID;
	}
	public String getMP1001_SURNAME() {
		return MP1001_SURNAME;
	}
	public void setMP1001_SURNAME(String mP1001_SURNAME) {
		MP1001_SURNAME = mP1001_SURNAME;
	}
	public String getMP1001_FIRSTNAME() {
		return MP1001_FIRSTNAME;
	}
	public void setMP1001_FIRSTNAME(String mP1001_FIRSTNAME) {
		MP1001_FIRSTNAME = mP1001_FIRSTNAME;
	}
	public String getMP1001_PREFERED_NAME() {
		return MP1001_PREFERED_NAME;
	}
	public void setMP1001_PREFERED_NAME(String mP1001_PREFERED_NAME) {
		MP1001_PREFERED_NAME = mP1001_PREFERED_NAME;
	}
	public String getMP1001_DEPARTMENT_ID() {
		return MP1001_DEPARTMENT_ID;
	}
	public void setMP1001_DEPARTMENT_ID(String mP1001_DEPARTMENT_ID) {
		MP1001_DEPARTMENT_ID = mP1001_DEPARTMENT_ID;
	}
	public String getMP1001_DEPARTMENT_NAME() {
		return MP1001_DEPARTMENT_NAME;
	}
	public void setMP1001_DEPARTMENT_NAME(String mP1001_DEPARTMENT_NAME) {
		MP1001_DEPARTMENT_NAME = mP1001_DEPARTMENT_NAME;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}