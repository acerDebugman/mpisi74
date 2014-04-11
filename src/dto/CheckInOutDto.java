package dto;

public class CheckInOutDto {
	private String employeeNum;
	private String checkTime;
	private String doorId;
	
	private String recordStatus; //identify which one is clock in item, which one is clock out item.
	private String comments; //late or early or abnormal
	
	
	public String getEmployeeNum() {
		return employeeNum;
	}
	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	public String getDoorId() {
		return doorId;
	}
	public void setDoorId(String doorId) {
		this.doorId = doorId;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}