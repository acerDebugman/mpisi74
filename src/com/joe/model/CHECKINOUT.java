package com.joe.model;

import java.io.Serializable;

public class CHECKINOUT implements Serializable{
    private static final long serialVersionUID = 1L;
    private int USERID;
    private String CHECKTIME;
    private String CHECKTYPE;
    private int VERIFYCODE;
    private String SENSORID;
    private String MEMOINFO;
    private int WORKCODE;
    private String SN;
    private int USEREXTFMT;
    
    private String EMPLOYEE_NUM;
    private String date1;
    private String date2;
    private String door1;
    private String door2;
    private String key;
    
    private String EMPLOYEE_NAME;
    private String DEPARTMENT_NAME;
    private String SSN;

    /**
     * @return the _USERID
     */
    public int getUSERID() {
        return USERID;
    }
    /**
     * @param _USERID the _USERID to set
     */
    public void setUSERID(int _USERID) {
        USERID = _USERID;
    }

    /**
     * @return the _CHECKTIME
     */
    public String getCHECKTIME() {
        return CHECKTIME;
    }
    /**
     * @param _CHECKTIME the _CHECKTIME to set
     */
    public void setCHECKTIME(String _CHECKTIME) {
        CHECKTIME = _CHECKTIME;
    }

    /**
     * @return the _CHECKTYPE
     */
    public String getCHECKTYPE() {
        return CHECKTYPE;
    }
    /**
     * @param _CHECKTYPE the _CHECKTYPE to set
     */
    public void setCHECKTYPE(String _CHECKTYPE) {
        CHECKTYPE = _CHECKTYPE;
    }

    /**
     * @return the _VERIFYCODE
     */
    public int getVERIFYCODE() {
        return VERIFYCODE;
    }
    /**
     * @param _VERIFYCODE the _VERIFYCODE to set
     */
    public void setVERIFYCODE(int _VERIFYCODE) {
        VERIFYCODE = _VERIFYCODE;
    }

    /**
     * @return the _SENSORID
     */
    public String getSENSORID() {
        return SENSORID;
    }
    /**
     * @param _SENSORID the _SENSORID to set
     */
    public void setSENSORID(String _SENSORID) {
        SENSORID = _SENSORID;
    }

    /**
     * @return the _MEMOINFO
     */
    public String getMEMOINFO() {
        return MEMOINFO;
    }
    /**
     * @param _MEMOINFO the _MEMOINFO to set
     */
    public void setMEMOINFO(String _MEMOINFO) {
        MEMOINFO = _MEMOINFO;
    }

    /**
     * @return the _WORKCODE
     */
    public int getWORKCODE() {
        return WORKCODE;
    }
    /**
     * @param _WORKCODE the _WORKCODE to set
     */
    public void setWORKCODE(int _WORKCODE) {
        WORKCODE = _WORKCODE;
    }

    /**
     * @return the _SN
     */
    public String getSN() {
        return SN;
    }
    /**
     * @param _SN the _SN to set
     */
    public void setSN(String _SN) {
        SN = _SN;
    }

    /**
     * @return the _USEREXTFMT
     */
    public int getUSEREXTFMT() {
        return USEREXTFMT;
    }
    /**
     * @param _USEREXTFMT the _USEREXTFMT to set
     */
    public void setUSEREXTFMT(int _USEREXTFMT) {
        USEREXTFMT = _USEREXTFMT;
    }
	/**
	 * @return the eMPLOYEE_NUM
	 */
	public String getEMPLOYEE_NUM() {
		return EMPLOYEE_NUM;
	}
	/**
	 * @param eMPLOYEE_NUM the eMPLOYEE_NUM to set
	 */
	public void setEMPLOYEE_NUM(String eMPLOYEE_NUM) {
		EMPLOYEE_NUM = eMPLOYEE_NUM;
	}
	/**
	 * @return the date1
	 */
	public String getDate1() {
		return date1;
	}
	/**
	 * @param date1 the date1 to set
	 */
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	/**
	 * @return the date2
	 */
	public String getDate2() {
		return date2;
	}
	/**
	 * @param date2 the date2 to set
	 */
	public void setDate2(String date2) {
		this.date2 = date2;
	}
	/**
	 * @return the door1
	 */
	public String getDoor1() {
		return door1;
	}
	/**
	 * @param door1 the door1 to set
	 */
	public void setDoor1(String door1) {
		this.door1 = door1;
	}
	/**
	 * @return the door2
	 */
	public String getDoor2() {
		return door2;
	}
	/**
	 * @param door2 the door2 to set
	 */
	public void setDoor2(String door2) {
		this.door2 = door2;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the eMPLOYEE_NAME
	 */
	public String getEMPLOYEE_NAME() {
		return EMPLOYEE_NAME;
	}
	/**
	 * @param eMPLOYEE_NAME the eMPLOYEE_NAME to set
	 */
	public void setEMPLOYEE_NAME(String eMPLOYEE_NAME) {
		EMPLOYEE_NAME = eMPLOYEE_NAME;
	}
	/**
	 * @return the dEPARTMENT_NAME
	 */
	public String getDEPARTMENT_NAME() {
		return DEPARTMENT_NAME;
	}
	/**
	 * @param dEPARTMENT_NAME the dEPARTMENT_NAME to set
	 */
	public void setDEPARTMENT_NAME(String dEPARTMENT_NAME) {
		DEPARTMENT_NAME = dEPARTMENT_NAME;
	}
	/**
	 * @return the sSN
	 */
	public String getSSN() {
		return SSN;
	}
	/**
	 * @param sSN the sSN to set
	 */
	public void setSSN(String sSN) {
		SSN = sSN;
	}


}

