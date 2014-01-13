package entity;

import java.io.Serializable;

public class MP0008VO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4337285475336802074L;
	
	// Fields
	private int MP0008_SEQ;
	private int MP0008_DEPARTMENT_ID;
	private int MP0008_POSITION_ID;
	private String MP0009_POSITION_NAME_E;
	private String MP0009_POSITION_NAME_C;
	
	public MP0008VO(){
		
	}
	
	/**
	 * @return the mP0008_SEQ
	 */
	public int getMP0008_SEQ() {
		return MP0008_SEQ;
	}
	/**
	 * @param mP0008_SEQ the mP0008_SEQ to set
	 */
	public void setMP0008_SEQ(int mP0008_SEQ) {
		MP0008_SEQ = mP0008_SEQ;
	}
	/**
	 * @return the mP0008_DEPARTMENT_ID
	 */
	public int getMP0008_DEPARTMENT_ID() {
		return MP0008_DEPARTMENT_ID;
	}
	/**
	 * @param mP0008_DEPARTMENT_ID the mP0008_DEPARTMENT_ID to set
	 */
	public void setMP0008_DEPARTMENT_ID(int mP0008_DEPARTMENT_ID) {
		MP0008_DEPARTMENT_ID = mP0008_DEPARTMENT_ID;
	}
	/**
	 * @return the mP0008_POSITION_ID
	 */
	public int getMP0008_POSITION_ID() {
		return MP0008_POSITION_ID;
	}
	/**
	 * @param mP0008_POSITION_ID the mP0008_POSITION_ID to set
	 */
	public void setMP0008_POSITION_ID(int mP0008_POSITION_ID) {
		MP0008_POSITION_ID = mP0008_POSITION_ID;
	}
	/**
	 * @return the mP0009_POSITION_NAME_E
	 */
	public String getMP0009_POSITION_NAME_E() {
		return MP0009_POSITION_NAME_E;
	}
	/**
	 * @param mP0009_POSITION_NAME_E the mP0009_POSITION_NAME_E to set
	 */
	public void setMP0009_POSITION_NAME_E(String mP0009_POSITION_NAME_E) {
		MP0009_POSITION_NAME_E = mP0009_POSITION_NAME_E;
	}
	/**
	 * @return the mP0009_POSITION_NAME_C
	 */
	public String getMP0009_POSITION_NAME_C() {
		return MP0009_POSITION_NAME_C;
	}
	/**
	 * @param mP0009_POSITION_NAME_C the mP0009_POSITION_NAME_C to set
	 */
	public void setMP0009_POSITION_NAME_C(String mP0009_POSITION_NAME_C) {
		MP0009_POSITION_NAME_C = mP0009_POSITION_NAME_C;
	}
}
