package dto;

import java.util.List;

public class AttendanceRecordDto {
	private String employeeNum; //key
	private String whichDate;			//key
	private List<CheckInOutDto> checkRecordsList = null;

	public String getEmployeeNum() {
		return employeeNum;
	}
	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}
	public List<CheckInOutDto> getCheckRecordsList() {
		return checkRecordsList;
	}
	public void setCheckRecordsList(List<CheckInOutDto> checkRecordsList) {
		this.checkRecordsList = checkRecordsList;
	}
	public String getWhichDate() {
		return whichDate;
	}
	public void setWhichDate(String whichDate) {
		this.whichDate = whichDate;
	}
}