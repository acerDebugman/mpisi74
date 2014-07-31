package entity;

import java.io.Serializable;

public class MP0010 implements Serializable{

	private static final long serialVersionUID = -8882938943277674284L;

	// Fields
	private int id;
	private String MP0010_DATETIME;
	private String MP0010_START_TIME;
	private String MP0010_END_TIME;
	private String MP0010_LUNCH_TIME;
	private String MP0010_TOTAL_TIME;
	private String MP0010_OTHER1;
	private String MP0010_OTHER2;
	
	private WorkTimePattern workTimePattern;
	
	/**
	 * @return the mP0010_DATETIME
	 */
	public String getMP0010_DATETIME() {
		return MP0010_DATETIME;
	}
	/**
	 * @param mP0010_DATETIME the mP0010_DATETIME to set
	 */
	public void setMP0010_DATETIME(String mP0010_DATETIME) {
		MP0010_DATETIME = mP0010_DATETIME;
	}
	/**
	 * @return the mP0010_START_TIME
	 */
	public String getMP0010_START_TIME() {
		return MP0010_START_TIME;
	}
	/**
	 * @param mP0010_START_TIME the mP0010_START_TIME to set
	 */
	public void setMP0010_START_TIME(String mP0010_START_TIME) {
		MP0010_START_TIME = mP0010_START_TIME;
	}
	/**
	 * @return the mP0010_END_TIME
	 */
	public String getMP0010_END_TIME() {
		return MP0010_END_TIME;
	}
	/**
	 * @param mP0010_END_TIME the mP0010_END_TIME to set
	 */
	public void setMP0010_END_TIME(String mP0010_END_TIME) {
		MP0010_END_TIME = mP0010_END_TIME;
	}
	/**
	 * @return the mP0010_LUNCH_TIME
	 */
	public String getMP0010_LUNCH_TIME() {
		return MP0010_LUNCH_TIME;
	}
	/**
	 * @param mP0010_LUNCH_TIME the mP0010_LUNCH_TIME to set
	 */
	public void setMP0010_LUNCH_TIME(String mP0010_LUNCH_TIME) {
		MP0010_LUNCH_TIME = mP0010_LUNCH_TIME;
	}
	/**
	 * @return the mP0010_TOTAL_TIME
	 */
	public String getMP0010_TOTAL_TIME() {
		return MP0010_TOTAL_TIME;
	}
	/**
	 * @param mP0010_TOTAL_TIME the mP0010_TOTAL_TIME to set
	 */
	public void setMP0010_TOTAL_TIME(String mP0010_TOTAL_TIME) {
		MP0010_TOTAL_TIME = mP0010_TOTAL_TIME;
	}
	/**
	 * @return the mP0010_OTHER1
	 */
	public String getMP0010_OTHER1() {
		return MP0010_OTHER1;
	}
	/**
	 * @param mP0010_OTHER1 the mP0010_OTHER1 to set
	 */
	public void setMP0010_OTHER1(String mP0010_OTHER1) {
		MP0010_OTHER1 = mP0010_OTHER1;
	}
	/**
	 * @return the mP0010_OTHER2
	 */
	public String getMP0010_OTHER2() {
		return MP0010_OTHER2;
	}
	/**
	 * @param mP0010_OTHER2 the mP0010_OTHER2 to set
	 */
	public void setMP0010_OTHER2(String mP0010_OTHER2) {
		MP0010_OTHER2 = mP0010_OTHER2;
	}
	public WorkTimePattern getWorkTimePattern() {
		return workTimePattern;
	}
	public void setWorkTimePattern(WorkTimePattern workTimePattern) {
		this.workTimePattern = workTimePattern;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}