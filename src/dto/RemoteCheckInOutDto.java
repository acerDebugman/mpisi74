package dto;

import java.io.Serializable;

public class RemoteCheckInOutDto implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8044108910381879430L;
	
	private int USERID;
    private String CHECKTIME;
    private String SENSORID;
    
	public int getUSERID() {
		return USERID;
	}
	public void setUSERID(int uSERID) {
		USERID = uSERID;
	}
	public String getCHECKTIME() {
		return CHECKTIME;
	}
	public void setCHECKTIME(String cHECKTIME) {
		CHECKTIME = cHECKTIME;
	}
	public String getSENSORID() {
		return SENSORID;
	}
	public void setSENSORID(String sENSORID) {
		SENSORID = sENSORID;
	}
}