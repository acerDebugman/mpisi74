package entity;

import java.io.Serializable;

public class HOLIDAY implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1653374236079089849L;
	
	// Fields
	private int HOLIDAY_SEQ;
	private String HOLIDAY_NAME;
	private String HOLIDAY_DATE;
	private String HOLIDAY_YEAR;
	
	/**
	 * @return the hOLIDAY_SEQ
	 */
	public int getHOLIDAY_SEQ() {
		return HOLIDAY_SEQ;
	}
	/**
	 * @param hOLIDAY_SEQ the hOLIDAY_SEQ to set
	 */
	public void setHOLIDAY_SEQ(int hOLIDAY_SEQ) {
		HOLIDAY_SEQ = hOLIDAY_SEQ;
	}
	/**
	 * @return the hOLIDAY_NAME
	 */
	public String getHOLIDAY_NAME() {
		return HOLIDAY_NAME;
	}
	/**
	 * @param hOLIDAY_NAME the hOLIDAY_NAME to set
	 */
	public void setHOLIDAY_NAME(String hOLIDAY_NAME) {
		HOLIDAY_NAME = hOLIDAY_NAME;
	}
	/**
	 * @return the hOLIDAY_DATE
	 */
	public String getHOLIDAY_DATE() {
		return HOLIDAY_DATE;
	}
	/**
	 * @param hOLIDAY_DATE the hOLIDAY_DATE to set
	 */
	public void setHOLIDAY_DATE(String hOLIDAY_DATE) {
		HOLIDAY_DATE = hOLIDAY_DATE;
	}
	/**
	 * @return the hOLIDAY_YEAR
	 */
	public String getHOLIDAY_YEAR() {
		return HOLIDAY_YEAR;
	}
	/**
	 * @param hOLIDAY_YEAR the hOLIDAY_YEAR to set
	 */
	public void setHOLIDAY_YEAR(String hOLIDAY_YEAR) {
		HOLIDAY_YEAR = hOLIDAY_YEAR;
	}

}
