package com.joe.dao;

import java.util.List;
import java.util.Map;

import com.joe.dto.LateEarlyDto;
import com.joe.model.CHECKINOUT;
import com.joe.model.MP1001;
import com.joe.model.MP2003;

public interface IMP2003DAO {
	public abstract void save(MP2003 mp2003);

	public abstract void delete(MP2003 mp2003);

	public abstract MP2003 findById(String employeeId);

	public abstract List<MP2003> findAll();

	public abstract void update(MP2003 mp2003);
	
	public abstract void updateStatus(MP2003 mp2003);

    public abstract void loadDataToHR(Map<String, CHECKINOUT> inoutMap, String condition);
	
	public abstract void updateTimes(String empNum, String dateTime);
	
	public abstract List<MP2003> findByProperty(String name, String value);
	
	public abstract List<MP2003> findByPropertyPage(Map<String, String> propertyMap);
	
	public abstract List<MP2003> findByPropertys(MP2003 _data);
	
	public abstract List<MP2003> findByPropertys2(MP2003 _data);
	
	public abstract List<MP2003> findByPropertysPage(MP1001 employeeData,int pageNum,int pageCount,String empNum, String fromDate, String FinishDate,String depId,String attendenceStatus);
	
	public abstract int getRowCountByPropertys(MP1001 employeeData,String empNum, String fromDate, String FinishDate,String depId,String attendenceStatus);
	
	public abstract List<MP2003> getPdfData(String empNum, String fromDate, String FinishDate,String depId,String attendenceStatus);
	
	public abstract List<MP2003> getTotalUnusualRecords(Map<String, String> propMap); //for abnormal/late/early records report
//	public abstract List<MP2003> getTotalUnusualRecords(Map<String, String> propMap); //for abnormal/late/early records report
	
	public abstract List<MP2003> getAbnormalReptData(Map<String, String> propMap);
	
	public abstract List<MP2003> getLateEarlyReptData(Map<String, String> propMap);
}
