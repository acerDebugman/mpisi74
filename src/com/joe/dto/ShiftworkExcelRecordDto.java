package com.joe.dto;

import java.util.Map;

public class ShiftworkExcelRecordDto {
	private String branchSite;
	private String employeeNum;
	private Map<Integer, String> arrangementMap;
	
	public String getBranchSite() {
		return branchSite;
	}
	public void setBranchSite(String branchSite) {
		this.branchSite = branchSite;
	}
	public String getEmployeeNum() {
		return employeeNum;
	}
	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}
	public Map<Integer, String> getArrangementMap() {
		return arrangementMap;
	}
	public void setArrangementMap(Map<Integer, String> arrangementMap) {
		this.arrangementMap = arrangementMap;
	}
}
