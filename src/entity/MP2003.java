package entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class MP2003 implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Fields
	private String MP2003_EMPLOYEE_NUM;
	private String MP2003_EMPLOYEE_NAME;
	private String MP2003_DATETIME;
	private String MP2003_START_TIME;
	private String MP2003_FINISH_TIME;
	private String MP2003_COMMENT;
	
	//------2011-11-04 Add by Tim------Start
	private String MP2003_START_TIME_DOOR;
	private String MP2003_FINISH_TIME_DOOR;
	private String MP2003_START_TIME_E;
	private String MP2003_FINISH_TIME_E;
	private String MP2003_DES;
	private String MP2003_STATUS; // 如果该字段值为"1"，则表示该条数据异常
	//------2011-11-04 Add by Tim------End
	
	//------2011-11-10 Add by Tim------Start
	private String MP2003_RECTIFY_TIMES;
	//------2011-11-10 Add by Tim------End
	
	//------2011-11-10 Add by Tim------Start
	private String MP2003_DEPARTMENT_ID;
	//------2011-11-10 Add by Tim------End
	//private String MP2003_STATUS1;
	private String MP2003_EDIT_USER;
	private String MP2003_EDIT_DATETIME;
	
	//department name
	private String MP2003_DEPARTMENT_NAME;
	private String MP2003_EMPLOYEE_SURNAME; 

	/**
	 * @return the mP2003_EMPLOYEE_NUM
	 */
	public String getMP2003_EMPLOYEE_NUM() {
		return MP2003_EMPLOYEE_NUM;
	}
	/**
	 * @param mP2003_EMPLOYEE_NUM the mP2003_EMPLOYEE_NUM to set
	 */
	public void setMP2003_EMPLOYEE_NUM(String mP2003_EMPLOYEE_NUM) {
		MP2003_EMPLOYEE_NUM = mP2003_EMPLOYEE_NUM;
	}
	/**
	 * @return the mP2003_DATETIME
	 */
	public String getMP2003_DATETIME() {
		return MP2003_DATETIME;
	}
	/**
	 * @param mP2003_DATETIME the mP2003_DATETIME to set
	 */
	public void setMP2003_DATETIME(String mP2003_DATETIME) {
		MP2003_DATETIME = mP2003_DATETIME;
	}
	/**
	 * @return the mP2003_START_TIME
	 */
	public String getMP2003_START_TIME() {
		return MP2003_START_TIME;
	}
	/**
	 * @param mP2003_START_TIME the mP2003_START_TIME to set
	 */
	public void setMP2003_START_TIME(String mP2003_START_TIME) {
		MP2003_START_TIME = mP2003_START_TIME;
	}
	/**
	 * @return the mP2003_FINISH_TIME
	 */
	public String getMP2003_FINISH_TIME() {
		return MP2003_FINISH_TIME;
	}
	/**
	 * @param mP2003_FINISH_TIME the mP2003_FINISH_TIME to set
	 */
	public void setMP2003_FINISH_TIME(String mP2003_FINISH_TIME) {
		MP2003_FINISH_TIME = mP2003_FINISH_TIME;
	}
	/**
	 * @return the mP2003_COMMENT
	 */
	public String getMP2003_COMMENT() {
		return MP2003_COMMENT;
	}
	/**
	 * @param mP2003_COMMENT the mP2003_COMMENT to set
	 */
	public void setMP2003_COMMENT(String mP2003_COMMENT) {
		MP2003_COMMENT = mP2003_COMMENT;
	}
	/**
	 * @return the mP2003_EMPLOYEE_NAME
	 */
	public String getMP2003_EMPLOYEE_NAME() {
		return MP2003_EMPLOYEE_NAME;
	}
	/**
	 * @param mP2003_EMPLOYEE_NAME the mP2003_EMPLOYEE_NAME to set
	 */
	public void setMP2003_EMPLOYEE_NAME(String mP2003_EMPLOYEE_NAME) {
		MP2003_EMPLOYEE_NAME = mP2003_EMPLOYEE_NAME;
	}
	/**
	 * @return the mP2003_START_TIME_DOOR
	 */
	public String getMP2003_START_TIME_DOOR() {
		return MP2003_START_TIME_DOOR;
	}
	/**
	 * @param mP2003_START_TIME_DOOR the mP2003_START_TIME_DOOR to set
	 */
	public void setMP2003_START_TIME_DOOR(String mP2003_START_TIME_DOOR) {
		MP2003_START_TIME_DOOR = mP2003_START_TIME_DOOR;
	}
	/**
	 * @return the mP2003_FINISH_TIME_DOOR
	 */
	public String getMP2003_FINISH_TIME_DOOR() {
		return MP2003_FINISH_TIME_DOOR;
	}
	/**
	 * @param mP2003_FINISH_TIME_DOOR the mP2003_FINISH_TIME_DOOR to set
	 */
	public void setMP2003_FINISH_TIME_DOOR(String mP2003_FINISH_TIME_DOOR) {
		MP2003_FINISH_TIME_DOOR = mP2003_FINISH_TIME_DOOR;
	}
	/**
	 * @return the mP2003_START_TIME_E
	 */
	public String getMP2003_START_TIME_E() {
		return MP2003_START_TIME_E;
	}
	/**
	 * @param mP2003_START_TIME_E the mP2003_START_TIME_E to set
	 */
	public void setMP2003_START_TIME_E(String mP2003_START_TIME_E) {
		MP2003_START_TIME_E = mP2003_START_TIME_E;
	}
	/**
	 * @return the mP2003_FINISH_TIME_E
	 */
	public String getMP2003_FINISH_TIME_E() {
		return MP2003_FINISH_TIME_E;
	}
	/**
	 * @param mP2003_FINISH_TIME_E the mP2003_FINISH_TIME_E to set
	 */
	public void setMP2003_FINISH_TIME_E(String mP2003_FINISH_TIME_E) {
		MP2003_FINISH_TIME_E = mP2003_FINISH_TIME_E;
	}
	/**
	 * @return the mP2003_DES
	 */
	public String getMP2003_DES() {
		return MP2003_DES;
	}
	/**
	 * @param mP2003_DES the mP2003_DES to set
	 */
	public void setMP2003_DES(String mP2003_DES) {
		MP2003_DES = mP2003_DES;
	}
	// 必须重新定义equals()与hashCode()(equals()与hashCode()方法被用作两笔不同数据的识别依据)
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof MP2003)) {
            return false;
        }
        MP2003 mp2003 = (MP2003) obj;
        return new EqualsBuilder()
                 .append(this.MP2003_EMPLOYEE_NUM, mp2003.getMP2003_EMPLOYEE_NUM())
                 .append(this.MP2003_DATETIME, mp2003.getMP2003_DATETIME())
                 .isEquals();
    }
    public int hashCode() {
        return new HashCodeBuilder()
                 .append(this.MP2003_EMPLOYEE_NUM)
                 .append(this.MP2003_DATETIME)
                 .toHashCode();
    }
	/**
	 * @return the mP2003_STATUS
	 */
	public String getMP2003_STATUS() {
		return MP2003_STATUS;
	}
	/**
	 * @param mP2003_STATUS the mP2003_STATUS to set
	 */
	public void setMP2003_STATUS(String mP2003_STATUS) {
		MP2003_STATUS = mP2003_STATUS;
	}
	/**
	 * @return the mP2003_RECTIFY_TIMES
	 */
	public String getMP2003_RECTIFY_TIMES() {
		return MP2003_RECTIFY_TIMES;
	}
	/**
	 * @param mP2003_RECTIFY_TIMES the mP2003_RECTIFY_TIMES to set
	 */
	public void setMP2003_RECTIFY_TIMES(String mP2003_RECTIFY_TIMES) {
		MP2003_RECTIFY_TIMES = mP2003_RECTIFY_TIMES;
	}
	/**
	 * @return the mP2003_DEPARTMENT_ID
	 */
	public String getMP2003_DEPARTMENT_ID() {
		return MP2003_DEPARTMENT_ID;
	}
	/**
	 * @param mP2003_DEPARTMENT_ID the mP2003_DEPARTMENT_ID to set
	 */
	public void setMP2003_DEPARTMENT_ID(String mP2003_DEPARTMENT_ID) {
		MP2003_DEPARTMENT_ID = mP2003_DEPARTMENT_ID;
	}
	/**
	 * @return the mP2003_EDIT_USER
	 */
	public String getMP2003_EDIT_USER() {
		return MP2003_EDIT_USER;
	}
	/**
	 * @param mP2003_EDIT_USER the mP2003_EDIT_USER to set
	 */
	public void setMP2003_EDIT_USER(String mP2003_EDIT_USER) {
		MP2003_EDIT_USER = mP2003_EDIT_USER;
	}
	/**
	 * @return the mP2003_EDIT_DATETIME
	 */
	public String getMP2003_EDIT_DATETIME() {
		return MP2003_EDIT_DATETIME;
	}
	/**
	 * @param mP2003_EDIT_DATETIME the mP2003_EDIT_DATETIME to set
	 */
	public void setMP2003_EDIT_DATETIME(String mP2003_EDIT_DATETIME) {
		MP2003_EDIT_DATETIME = mP2003_EDIT_DATETIME;
	}
	public String getMP2003_DEPARTMENT_NAME() {
		return MP2003_DEPARTMENT_NAME;
	}
	public void setMP2003_DEPARTMENT_NAME(String mP2003_DEPARTMENT_NAME) {
		MP2003_DEPARTMENT_NAME = mP2003_DEPARTMENT_NAME;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getMP2003_EMPLOYEE_SURNAME() {
		return MP2003_EMPLOYEE_SURNAME;
	}
	public void setMP2003_EMPLOYEE_SURNAME(String mP2003_EMPLOYEE_SURNAME) {
		MP2003_EMPLOYEE_SURNAME = mP2003_EMPLOYEE_SURNAME;
	}
}
