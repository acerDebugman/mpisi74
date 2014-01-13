package entity;

import java.io.Serializable;

public class MP1001 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Fields
	private String MP1001_EMPLOYEE_NUM;
	private String MP1001_EMPLOYEE_ID;
	private String MP1001_SURNAME;
	private String MP1001_FIRSTNAME;
	private String MP1001_PREFERED_NAME;
	private String MP1001_VISA_TYPE;
	private String MP1001_DEPARTMENT_ID;
	private String MP1001_DEPARTMENT_NAME;
	private String MP1001_POSITION;
	private String MP1001_POSITION_NAME;
	private String MP1001_GENDER;
	private String MP1001_GENDER_NAME;
	private String MP1001_RELIGION;
	private String MP1001_NATIONALITY;
	private String MP1001_BIRTHDAY;
	private String MP1001_MARRIAGE_STATUS;
	private String MP1001_MARRIAGE_STATUS_NAME;
	private String MP1001_RACE;
	private String MP1001_MOBILE_PHONE;
	private String MP1001_TELEPHONE;
	private String MP1001_OTHER_CONTACT;
	private String MP1001_EMAIL;
	private String MP1001_COMPANY_EMAIL;
	private String MP1001_DEGREE_LEVEL;
	private String MP1001_ENTRY_DATE;
	private String MP1001_STATUS;
	private String MP1001_RESIGN_DATE;
	private String MP1001_RESIGN_REASON; // Add by tim on 2011/12/14
	private String MP1001_RESIGN_TYPE;   // Add by tim on 2011/12/14
	private String MP1001_PICTURE;
	private String MP1001_PICTURE_NAME;
	private String MP1001_PHYSICAL_ADDRESS;
	private String MP1001_PASSWORD;
	private String MP1001_PASSWORD_DATE;
	private String MP1001_GROUP;
	private String MP1001_GROUP_NAME;
	private String MP1001_RECTIFY_TIMES;
	private String MP1001_CREATE_USER;
	private String MP1001_CREATE_DATE;
	private String MP1001_EDIT_USER;
	private String MP1001_EDIT_DATE;
	private String MP1001_FREE_MONEY;
	
	private String MP1001_ANNUAL_STATUS;
	
	private String CURRENT_PAGE_NUM;
	
	private String personalAccess;
	private String departmentAccess;
	private String fullAccess;
	
	private String MP1001_CHG_TIME;
	private String MP1001_CHG_EMPLOYE;
	
	private String MP1001_HEADCOUNT_TOTAL;
	private String MP1001_HEADCOUNT_EMPLOYEE;
	private String MP1001_HEADCOUNT_TMP_EMPLOYEE;
	private String MP1001_HEADCOUNT_DEPARTMENT;
	
	private String MP1001_APPRASIER;
	private String MP1001_APPRASIER_NAME;
	private String MP1001_EFFECTIVE_DATE_YEAR;
	private String MP1001_EFFECTIVE_DATE_MONTH;
	private String MP1001_PAYROLL_NUM;
	
	public MP1001(String MP1001_EMPLOYEE_NUM, String MP1001_PASSWORD){
		this.MP1001_EMPLOYEE_NUM = MP1001_EMPLOYEE_NUM;
		this.MP1001_PASSWORD = MP1001_PASSWORD;
	}
	
	public MP1001(){
		
	}

	/**
	 * @return the mP1001_EMPLOYEE_NUM
	 */
	public String getMP1001_EMPLOYEE_NUM() {
		return MP1001_EMPLOYEE_NUM;
	}

	/**
	 * @param mP1001_EMPLOYEE_NUM the mP1001_EMPLOYEE_NUM to set
	 */
	public void setMP1001_EMPLOYEE_NUM(String mP1001_EMPLOYEE_NUM) {
		MP1001_EMPLOYEE_NUM = mP1001_EMPLOYEE_NUM;
	}

	/**
	 * @return the mP1001_EMPLOYEE_ID
	 */
	public String getMP1001_EMPLOYEE_ID() {
		return MP1001_EMPLOYEE_ID;
	}

	/**
	 * @param mP1001_EMPLOYEE_ID the mP1001_EMPLOYEE_ID to set
	 */
	public void setMP1001_EMPLOYEE_ID(String mP1001_EMPLOYEE_ID) {
		MP1001_EMPLOYEE_ID = mP1001_EMPLOYEE_ID;
	}

	/**
	 * @return the mP1001_SURNAME
	 */
	public String getMP1001_SURNAME() {
		return MP1001_SURNAME;
	}

	/**
	 * @param mP1001_SURNAME the mP1001_SURNAME to set
	 */
	public void setMP1001_SURNAME(String mP1001_SURNAME) {
		MP1001_SURNAME = mP1001_SURNAME;
	}

	/**
	 * @return the mP1001_FIRSTNAME
	 */
	public String getMP1001_FIRSTNAME() {
		return MP1001_FIRSTNAME;
	}

	/**
	 * @param mP1001_FIRSTNAME the mP1001_FIRSTNAME to set
	 */
	public void setMP1001_FIRSTNAME(String mP1001_FIRSTNAME) {
		MP1001_FIRSTNAME = mP1001_FIRSTNAME;
	}

	/**
	 * @return the mP1001_PREFERED_NAME
	 */
	public String getMP1001_PREFERED_NAME() {
		return MP1001_PREFERED_NAME;
	}

	/**
	 * @param mP1001_PREFERED_NAME the mP1001_PREFERED_NAME to set
	 */
	public void setMP1001_PREFERED_NAME(String mP1001_PREFERED_NAME) {
		MP1001_PREFERED_NAME = mP1001_PREFERED_NAME;
	}

	/**
	 * @return the mP1001_VISA_TYPE
	 */
	public String getMP1001_VISA_TYPE() {
		return MP1001_VISA_TYPE;
	}

	/**
	 * @param mP1001_VISA_TYPE the mP1001_VISA_TYPE to set
	 */
	public void setMP1001_VISA_TYPE(String mP1001_VISA_TYPE) {
		MP1001_VISA_TYPE = mP1001_VISA_TYPE;
	}

	/**
	 * @return the mP1001_DEPARTMENT_ID
	 */
	public String getMP1001_DEPARTMENT_ID() {
		return MP1001_DEPARTMENT_ID;
	}

	/**
	 * @param mP1001_DEPARTMENT_ID the mP1001_DEPARTMENT_ID to set
	 */
	public void setMP1001_DEPARTMENT_ID(String mP1001_DEPARTMENT_ID) {
		MP1001_DEPARTMENT_ID = mP1001_DEPARTMENT_ID;
	}

	/**
	 * @return the mP1001_GENDER
	 */
	public String getMP1001_GENDER() {
		return MP1001_GENDER;
	}

	/**
	 * @param mP1001_GENDER the mP1001_GENDER to set
	 */
	public void setMP1001_GENDER(String mP1001_GENDER) {
		MP1001_GENDER = mP1001_GENDER;
	}

	/**
	 * @return the mP1001_RELIGION
	 */
	public String getMP1001_RELIGION() {
		return MP1001_RELIGION;
	}

	/**
	 * @param mP1001_RELIGION the mP1001_RELIGION to set
	 */
	public void setMP1001_RELIGION(String mP1001_RELIGION) {
		MP1001_RELIGION = mP1001_RELIGION;
	}

	/**
	 * @return the mP1001_NATIONALITY
	 */
	public String getMP1001_NATIONALITY() {
		return MP1001_NATIONALITY;
	}

	/**
	 * @param mP1001_NATIONALITY the mP1001_NATIONALITY to set
	 */
	public void setMP1001_NATIONALITY(String mP1001_NATIONALITY) {
		MP1001_NATIONALITY = mP1001_NATIONALITY;
	}

	/**
	 * @return the mP1001_BIRTHDAY
	 */
	public String getMP1001_BIRTHDAY() {
		return MP1001_BIRTHDAY;
	}

	/**
	 * @param mP1001_BIRTHDAY the mP1001_BIRTHDAY to set
	 */
	public void setMP1001_BIRTHDAY(String mP1001_BIRTHDAY) {
		MP1001_BIRTHDAY = mP1001_BIRTHDAY;
	}

	/**
	 * @return the mP1001_MARRIAGE_STATUS
	 */
	public String getMP1001_MARRIAGE_STATUS() {
		return MP1001_MARRIAGE_STATUS;
	}

	/**
	 * @param mP1001_MARRIAGE_STATUS the mP1001_MARRIAGE_STATUS to set
	 */
	public void setMP1001_MARRIAGE_STATUS(String mP1001_MARRIAGE_STATUS) {
		MP1001_MARRIAGE_STATUS = mP1001_MARRIAGE_STATUS;
	}

	/**
	 * @return the mP1001_RACE
	 */
	public String getMP1001_RACE() {
		return MP1001_RACE;
	}

	/**
	 * @param mP1001_RACE the mP1001_RACE to set
	 */
	public void setMP1001_RACE(String mP1001_RACE) {
		MP1001_RACE = mP1001_RACE;
	}

	/**
	 * @return the mP1001_MOBILE_PHONE
	 */
	public String getMP1001_MOBILE_PHONE() {
		return MP1001_MOBILE_PHONE;
	}

	/**
	 * @param mP1001_MOBILE_PHONE the mP1001_MOBILE_PHONE to set
	 */
	public void setMP1001_MOBILE_PHONE(String mP1001_MOBILE_PHONE) {
		MP1001_MOBILE_PHONE = mP1001_MOBILE_PHONE;
	}

	/**
	 * @return the mP1001_TELEPHONE
	 */
	public String getMP1001_TELEPHONE() {
		return MP1001_TELEPHONE;
	}

	/**
	 * @param mP1001_TELEPHONE the mP1001_TELEPHONE to set
	 */
	public void setMP1001_TELEPHONE(String mP1001_TELEPHONE) {
		MP1001_TELEPHONE = mP1001_TELEPHONE;
	}

	/**
	 * @return the mP1001_OTHER_CONTACT
	 */
	public String getMP1001_OTHER_CONTACT() {
		return MP1001_OTHER_CONTACT;
	}

	/**
	 * @param mP1001_OTHER_CONTACT the mP1001_OTHER_CONTACT to set
	 */
	public void setMP1001_OTHER_CONTACT(String mP1001_OTHER_CONTACT) {
		MP1001_OTHER_CONTACT = mP1001_OTHER_CONTACT;
	}

	/**
	 * @return the mP1001_EMAIL
	 */
	public String getMP1001_EMAIL() {
		return MP1001_EMAIL;
	}

	/**
	 * @param mP1001_EMAIL the mP1001_EMAIL to set
	 */
	public void setMP1001_EMAIL(String mP1001_EMAIL) {
		MP1001_EMAIL = mP1001_EMAIL;
	}

	/**
	 * @return the mP1001_ENTRY_DATE
	 */
	public String getMP1001_ENTRY_DATE() {
		return MP1001_ENTRY_DATE;
	}

	/**
	 * @param mP1001_ENTRY_DATE the mP1001_ENTRY_DATE to set
	 */
	public void setMP1001_ENTRY_DATE(String mP1001_ENTRY_DATE) {
		MP1001_ENTRY_DATE = mP1001_ENTRY_DATE;
	}

	/**
	 * @return the mP1001_STATUS
	 */
	public String getMP1001_STATUS() {
		return MP1001_STATUS;
	}

	/**
	 * @param mP1001_STATUS the mP1001_STATUS to set
	 */
	public void setMP1001_STATUS(String mP1001_STATUS) {
		MP1001_STATUS = mP1001_STATUS;
	}

	/**
	 * @return the mP1001_RESIGN_DATE
	 */
	public String getMP1001_RESIGN_DATE() {
		return MP1001_RESIGN_DATE;
	}

	/**
	 * @param mP1001_RESIGN_DATE the mP1001_RESIGN_DATE to set
	 */
	public void setMP1001_RESIGN_DATE(String mP1001_RESIGN_DATE) {
		MP1001_RESIGN_DATE = mP1001_RESIGN_DATE;
	}

	/**
	 * @return the mP1001_PICTURE
	 */
	public String getMP1001_PICTURE() {
		return MP1001_PICTURE;
	}

	/**
	 * @param mP1001_PICTURE the mP1001_PICTURE to set
	 */
	public void setMP1001_PICTURE(String mP1001_PICTURE) {
		MP1001_PICTURE = mP1001_PICTURE;
	}

	public String getMP1001_PICTURE_NAME() {
		return MP1001_PICTURE_NAME;
	}

	public void setMP1001_PICTURE_NAME(String mP1001_PICTURE_NAME) {
		MP1001_PICTURE_NAME = mP1001_PICTURE_NAME;
	}
	
	/**
	 * @return the mP1001_PHYSICAL_ADDRESS
	 */
	public String getMP1001_PHYSICAL_ADDRESS() {
		return MP1001_PHYSICAL_ADDRESS;
	}

	/**
	 * @param mP1001_PHYSICAL_ADDRESS the mP1001_PHYSICAL_ADDRESS to set
	 */
	public void setMP1001_PHYSICAL_ADDRESS(String mP1001_PHYSICAL_ADDRESS) {
		MP1001_PHYSICAL_ADDRESS = mP1001_PHYSICAL_ADDRESS;
	}

	/**
	 * @return the mP1001_PASSWORD
	 */
	public String getMP1001_PASSWORD() {
		return MP1001_PASSWORD;
	}

	/**
	 * @param mP1001_PASSWORD the mP1001_PASSWORD to set
	 */
	public void setMP1001_PASSWORD(String mP1001_PASSWORD) {
		MP1001_PASSWORD = mP1001_PASSWORD;
	}

	/**
	 * @return the mP1001_PASSWORD_DATE
	 */
	public String getMP1001_PASSWORD_DATE() {
		return MP1001_PASSWORD_DATE;
	}

	/**
	 * @param mP1001_PASSWORD_DATE the mP1001_PASSWORD_DATE to set
	 */
	public void setMP1001_PASSWORD_DATE(String mP1001_PASSWORD_DATE) {
		MP1001_PASSWORD_DATE = mP1001_PASSWORD_DATE;
	}

	/**
	 * @return the mP1001_CREATE_USER
	 */
	public String getMP1001_CREATE_USER() {
		return MP1001_CREATE_USER;
	}

	/**
	 * @param mP1001_CREATE_USER the mP1001_CREATE_USER to set
	 */
	public void setMP1001_CREATE_USER(String mP1001_CREATE_USER) {
		MP1001_CREATE_USER = mP1001_CREATE_USER;
	}

	/**
	 * @return the mP1001_CREATE_DATE
	 */
	public String getMP1001_CREATE_DATE() {
		return MP1001_CREATE_DATE;
	}

	/**
	 * @param mP1001_CREATE_DATE the mP1001_CREATE_DATE to set
	 */
	public void setMP1001_CREATE_DATE(String mP1001_CREATE_DATE) {
		MP1001_CREATE_DATE = mP1001_CREATE_DATE;
	}

	/**
	 * @return the mP1001_EDIT_USER
	 */
	public String getMP1001_EDIT_USER() {
		return MP1001_EDIT_USER;
	}

	/**
	 * @param mP1001_EDIT_USER the mP1001_EDIT_USER to set
	 */
	public void setMP1001_EDIT_USER(String mP1001_EDIT_USER) {
		MP1001_EDIT_USER = mP1001_EDIT_USER;
	}

	/**
	 * @return the mP1001_EDIT_DATE
	 */
	public String getMP1001_EDIT_DATE() {
		return MP1001_EDIT_DATE;
	}

	/**
	 * @param mP1001_EDIT_DATE the mP1001_EDIT_DATE to set
	 */
	public void setMP1001_EDIT_DATE(String mP1001_EDIT_DATE) {
		MP1001_EDIT_DATE = mP1001_EDIT_DATE;
	}

	/**
	 * @return the mP1001_POSITION
	 */
	public String getMP1001_POSITION() {
		return MP1001_POSITION;
	}

	/**
	 * @param mP1001_POSITION the mP1001_POSITION to set
	 */
	public void setMP1001_POSITION(String mP1001_POSITION) {
		MP1001_POSITION = mP1001_POSITION;
	}

	/**
	 * @return the mP1001_DEPARTMENT_NAME
	 */
	public String getMP1001_DEPARTMENT_NAME() {
		return MP1001_DEPARTMENT_NAME;
	}

	/**
	 * @param mP1001_DEPARTMENT_NAME the mP1001_DEPARTMENT_NAME to set
	 */
	public void setMP1001_DEPARTMENT_NAME(String mP1001_DEPARTMENT_NAME) {
		MP1001_DEPARTMENT_NAME = mP1001_DEPARTMENT_NAME;
	}

	/**
	 * @return the mP1001_POSITION_NAME
	 */
	public String getMP1001_POSITION_NAME() {
		return MP1001_POSITION_NAME;
	}

	/**
	 * @param mP1001_POSITION_NAME the mP1001_POSITION_NAME to set
	 */
	public void setMP1001_POSITION_NAME(String mP1001_POSITION_NAME) {
		MP1001_POSITION_NAME = mP1001_POSITION_NAME;
	}

	/**
	 * @return the mP1001_GENDER_NAME
	 */
	public String getMP1001_GENDER_NAME() {
		return MP1001_GENDER_NAME;
	}

	/**
	 * @param mP1001_GENDER_NAME the mP1001_GENDER_NAME to set
	 */
	public void setMP1001_GENDER_NAME(String mP1001_GENDER_NAME) {
		MP1001_GENDER_NAME = mP1001_GENDER_NAME;
	}

	/**
	 * @return the mP1001_MARRIAGE_STATUS_NAME
	 */
	public String getMP1001_MARRIAGE_STATUS_NAME() {
		return MP1001_MARRIAGE_STATUS_NAME;
	}

	/**
	 * @param mP1001_MARRIAGE_STATUS_NAME the mP1001_MARRIAGE_STATUS_NAME to set
	 */
	public void setMP1001_MARRIAGE_STATUS_NAME(String mP1001_MARRIAGE_STATUS_NAME) {
		MP1001_MARRIAGE_STATUS_NAME = mP1001_MARRIAGE_STATUS_NAME;
	}

	public String getMP1001_GROUP() {
		return MP1001_GROUP;
	}

	public void setMP1001_GROUP(String mP1001_GROUP) {
		MP1001_GROUP = mP1001_GROUP;
	}

	public String getMP1001_GROUP_NAME() {
		return MP1001_GROUP_NAME;
	}

	public void setMP1001_GROUP_NAME(String mP1001_GROUP_NAME) {
		MP1001_GROUP_NAME = mP1001_GROUP_NAME;
	}

	/**
	 * @return the mP1001_COMPANY_EMAIL
	 */
	public String getMP1001_COMPANY_EMAIL() {
		return MP1001_COMPANY_EMAIL;
	}

	/**
	 * @param mP1001_COMPANY_EMAIL the mP1001_COMPANY_EMAIL to set
	 */
	public void setMP1001_COMPANY_EMAIL(String mP1001_COMPANY_EMAIL) {
		MP1001_COMPANY_EMAIL = mP1001_COMPANY_EMAIL;
	}

	/**
	 * @return the mP1001_DEGREE_LEVEL
	 */
	public String getMP1001_DEGREE_LEVEL() {
		return MP1001_DEGREE_LEVEL;
	}

	/**
	 * @param mP1001_DEGREE_LEVEL the mP1001_DEGREE_LEVEL to set
	 */
	public void setMP1001_DEGREE_LEVEL(String mP1001_DEGREE_LEVEL) {
		MP1001_DEGREE_LEVEL = mP1001_DEGREE_LEVEL;
	}

	/**
	 * @return the mP1001_RECTIFY_TIMES
	 */
	public String getMP1001_RECTIFY_TIMES() {
		return MP1001_RECTIFY_TIMES;
	}

	/**
	 * @param mP1001_RECTIFY_TIMES the mP1001_RECTIFY_TIMES to set
	 */
	public void setMP1001_RECTIFY_TIMES(String mP1001_RECTIFY_TIMES) {
		MP1001_RECTIFY_TIMES = mP1001_RECTIFY_TIMES;
	}

	/**
	 * @return the mP1001_RESIGN_REASON
	 */
	public String getMP1001_RESIGN_REASON() {
		return MP1001_RESIGN_REASON;
	}

	/**
	 * @param mP1001_RESIGN_REASON the mP1001_RESIGN_REASON to set
	 */
	public void setMP1001_RESIGN_REASON(String mP1001_RESIGN_REASON) {
		MP1001_RESIGN_REASON = mP1001_RESIGN_REASON;
	}

	/**
	 * @return the mP1001_RESIGN_TYPE
	 */
	public String getMP1001_RESIGN_TYPE() {
		return MP1001_RESIGN_TYPE;
	}

	/**
	 * @param mP1001_RESIGN_TYPE the mP1001_RESIGN_TYPE to set
	 */
	public void setMP1001_RESIGN_TYPE(String mP1001_RESIGN_TYPE) {
		MP1001_RESIGN_TYPE = mP1001_RESIGN_TYPE;
	}

	/**
	 * @return the cURRENT_PAGE_NUM
	 */
	public String getCURRENT_PAGE_NUM() {
		return CURRENT_PAGE_NUM;
	}

	/**
	 * @param cURRENT_PAGE_NUM the cURRENT_PAGE_NUM to set
	 */
	public void setCURRENT_PAGE_NUM(String cURRENT_PAGE_NUM) {
		CURRENT_PAGE_NUM = cURRENT_PAGE_NUM;
	}

	/**
	 * @return the mP1001_FREE_MONEY
	 */
	public String getMP1001_FREE_MONEY() {
		return MP1001_FREE_MONEY;
	}

	/**
	 * @param mP1001_FREE_MONEY the mP1001_FREE_MONEY to set
	 */
	public void setMP1001_FREE_MONEY(String mP1001_FREE_MONEY) {
		MP1001_FREE_MONEY = mP1001_FREE_MONEY;
	}

	/**
	 * @return the personalAccess
	 */
	public String getPersonalAccess() {
		return personalAccess;
	}

	/**
	 * @param personalAccess the personalAccess to set
	 */
	public void setPersonalAccess(String personalAccess) {
		this.personalAccess = personalAccess;
	}

	/**
	 * @return the departmentAccess
	 */
	public String getDepartmentAccess() {
		return departmentAccess;
	}

	/**
	 * @param departmentAccess the departmentAccess to set
	 */
	public void setDepartmentAccess(String departmentAccess) {
		this.departmentAccess = departmentAccess;
	}

	/**
	 * @return the fullAccess
	 */
	public String getFullAccess() {
		return fullAccess;
	}

	/**
	 * @param fullAccess the fullAccess to set
	 */
	public void setFullAccess(String fullAccess) {
		this.fullAccess = fullAccess;
	}

	/**
	 * @return the mP1001_CHG_TIME
	 */
	public String getMP1001_CHG_TIME() {
		return MP1001_CHG_TIME;
	}

	/**
	 * @param mP1001_CHG_TIME the mP1001_CHG_TIME to set
	 */
	public void setMP1001_CHG_TIME(String mP1001_CHG_TIME) {
		MP1001_CHG_TIME = mP1001_CHG_TIME;
	}

	/**
	 * @return the mP1001_CHG_EMPLOYE
	 */
	public String getMP1001_CHG_EMPLOYE() {
		return MP1001_CHG_EMPLOYE;
	}

	/**
	 * @param mP1001_CHG_EMPLOYE the mP1001_CHG_EMPLOYE to set
	 */
	public void setMP1001_CHG_EMPLOYE(String mP1001_CHG_EMPLOYE) {
		MP1001_CHG_EMPLOYE = mP1001_CHG_EMPLOYE;
	}

	/**
	 * @return the mP1001_HEADCOUNT_TOTAL
	 */
	public String getMP1001_HEADCOUNT_TOTAL() {
		return MP1001_HEADCOUNT_TOTAL;
	}

	/**
	 * @param mP1001_HEADCOUNT_TOTAL the mP1001_HEADCOUNT_TOTAL to set
	 */
	public void setMP1001_HEADCOUNT_TOTAL(String mP1001_HEADCOUNT_TOTAL) {
		MP1001_HEADCOUNT_TOTAL = mP1001_HEADCOUNT_TOTAL;
	}

	/**
	 * @return the mP1001_HEADCOUNT_EMPLOYEE
	 */
	public String getMP1001_HEADCOUNT_EMPLOYEE() {
		return MP1001_HEADCOUNT_EMPLOYEE;
	}

	/**
	 * @param mP1001_HEADCOUNT_EMPLOYEE the mP1001_HEADCOUNT_EMPLOYEE to set
	 */
	public void setMP1001_HEADCOUNT_EMPLOYEE(String mP1001_HEADCOUNT_EMPLOYEE) {
		MP1001_HEADCOUNT_EMPLOYEE = mP1001_HEADCOUNT_EMPLOYEE;
	}

	/**
	 * @return the mP1001_HEADCOUNT_TMP_EMPLOYEE
	 */
	public String getMP1001_HEADCOUNT_TMP_EMPLOYEE() {
		return MP1001_HEADCOUNT_TMP_EMPLOYEE;
	}

	/**
	 * @param mP1001_HEADCOUNT_TMP_EMPLOYEE the mP1001_HEADCOUNT_TMP_EMPLOYEE to set
	 */
	public void setMP1001_HEADCOUNT_TMP_EMPLOYEE(
			String mP1001_HEADCOUNT_TMP_EMPLOYEE) {
		MP1001_HEADCOUNT_TMP_EMPLOYEE = mP1001_HEADCOUNT_TMP_EMPLOYEE;
	}

	/**
	 * @return the mP1001_HEADCOUNT_DEPARTMENT
	 */
	public String getMP1001_HEADCOUNT_DEPARTMENT() {
		return MP1001_HEADCOUNT_DEPARTMENT;
	}

	/**
	 * @param mP1001_HEADCOUNT_DEPARTMENT the mP1001_HEADCOUNT_DEPARTMENT to set
	 */
	public void setMP1001_HEADCOUNT_DEPARTMENT(String mP1001_HEADCOUNT_DEPARTMENT) {
		MP1001_HEADCOUNT_DEPARTMENT = mP1001_HEADCOUNT_DEPARTMENT;
	}

	/**
	 * @return the mP1001_ANNUAL_STATUS
	 */
	public String getMP1001_ANNUAL_STATUS() {
		return MP1001_ANNUAL_STATUS;
	}

	/**
	 * @param mP1001_ANNUAL_STATUS the mP1001_ANNUAL_STATUS to set
	 */
	public void setMP1001_ANNUAL_STATUS(String mP1001_ANNUAL_STATUS) {
		MP1001_ANNUAL_STATUS = mP1001_ANNUAL_STATUS;
	}

	/**
	 * @return the mP1001_APPRASIER
	 */
	public String getMP1001_APPRASIER() {
		return MP1001_APPRASIER;
	}

	/**
	 * @param mP1001_APPRASIER the mP1001_APPRASIER to set
	 */
	public void setMP1001_APPRASIER(String mP1001_APPRASIER) {
		MP1001_APPRASIER = mP1001_APPRASIER;
	}

	/**
	 * @return the mP1001_EFFECTIVE_DATE_YEAR
	 */
	public String getMP1001_EFFECTIVE_DATE_YEAR() {
		return MP1001_EFFECTIVE_DATE_YEAR;
	}

	/**
	 * @param mP1001_EFFECTIVE_DATE_YEAR the mP1001_EFFECTIVE_DATE_YEAR to set
	 */
	public void setMP1001_EFFECTIVE_DATE_YEAR(String mP1001_EFFECTIVE_DATE_YEAR) {
		MP1001_EFFECTIVE_DATE_YEAR = mP1001_EFFECTIVE_DATE_YEAR;
	}

	/**
	 * @return the mP1001_EFFECTIVE_DATE_MONTH
	 */
	public String getMP1001_EFFECTIVE_DATE_MONTH() {
		return MP1001_EFFECTIVE_DATE_MONTH;
	}

	/**
	 * @param mP1001_EFFECTIVE_DATE_MONTH the mP1001_EFFECTIVE_DATE_MONTH to set
	 */
	public void setMP1001_EFFECTIVE_DATE_MONTH(String mP1001_EFFECTIVE_DATE_MONTH) {
		MP1001_EFFECTIVE_DATE_MONTH = mP1001_EFFECTIVE_DATE_MONTH;
	}

	/**
	 * @return the mP1001_APPRASIER_NAME
	 */
	public String getMP1001_APPRASIER_NAME() {
		return MP1001_APPRASIER_NAME;
	}

	/**
	 * @param mP1001_APPRASIER_NAME the mP1001_APPRASIER_NAME to set
	 */
	public void setMP1001_APPRASIER_NAME(String mP1001_APPRASIER_NAME) {
		MP1001_APPRASIER_NAME = mP1001_APPRASIER_NAME;
	}

	/**
	 * @return the mP1001_PAYROLL_NUM
	 */
	public String getMP1001_PAYROLL_NUM() {
		return MP1001_PAYROLL_NUM;
	}

	/**
	 * @param mP1001_PAYROLL_NUM the mP1001_PAYROLL_NUM to set
	 */
	public void setMP1001_PAYROLL_NUM(String mP1001_PAYROLL_NUM) {
		MP1001_PAYROLL_NUM = mP1001_PAYROLL_NUM;
	}
}
