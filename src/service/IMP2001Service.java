package service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import entity.MP1001;
import entity.MP2001;

public interface IMP2001Service {
	public abstract void save(MP2001 mp2001);

	public abstract void delete(MP2001 mp2001);

	public abstract MP2001 findById(String leaveNum);

	public abstract List<MP2001> findAll();

	public abstract void update(MP2001 mp2001);
	
	public abstract List<MP2001> findByProperty(String name, String value, boolean flag);
	
	public abstract List<MP2001> findByDate(String empNum,String date);
	
	public abstract boolean checkLeaveDay(String _fromDate, String _toDate, String _empNum);
	
	public abstract List<MP2001> findByProperty(Map<String, String> propertyMap,MP1001 employeeData);
	
	public abstract List<MP2001> findByPropertyPage(Map<String, String> propertyMap,MP1001 employeeData);
	
	public abstract int getLeaveApplyReportRowCount(String employeeNum, String departmentId, String fromDate, String toDate);
	
	public abstract List<MP2001> getLeaveApplyReport(String employeeNum, String departmentId, String fromDate, String toDate, int PAGE_NUM, int PAGE_COUNT);
	
	public abstract List<MP2001> locateAppliationByOneDate(Date date, MP1001 emp);
}