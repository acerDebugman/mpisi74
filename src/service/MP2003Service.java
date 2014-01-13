package service;

import java.util.List;
import java.util.Map;
import dao.IMP2003DAO;
import entity.CHECKINOUT;
import entity.MP1001;
import entity.MP2003;

public class MP2003Service implements IMP2003Service {

	private IMP2003DAO dao;

	public void delete(MP2003 mp2003) {
		dao.delete(mp2003);
	}

	public List<MP2003> findAll() {
		return dao.findAll();
	}

	public MP2003 findById(String employeeNum) {
		return dao.findById(employeeNum);
	}

	public void save(MP2003 mp2003) {
		dao.save(mp2003);
	}

	public void update(MP2003 mp2003) {
		dao.update(mp2003);
	}
	
	public void updateStatus(MP2003 mp2003){
		dao.updateStatus(mp2003);
	}
	
    public void loadDataToHR(Map<String, CHECKINOUT> inoutMap, String condition){
    	dao.loadDataToHR(inoutMap, condition);
    }
    
	public void updateTimes(String empNum, String dateTime){
		dao.updateTimes(empNum, dateTime);
	}
	
	public List<MP2003> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
	
	public List<MP2003> findByPropertyPage(Map<String, String> propertyMap){
		return dao.findByPropertyPage(propertyMap);
	}

	public List<MP2003> findByPropertys(MP2003 _data) {
		return dao.findByPropertys(_data);
	}
	
	public List<MP2003> findByPropertys2(MP2003 _data) {
		return dao.findByPropertys2(_data);
	}

	public List<MP2003> findByPropertysPage(MP1001 employeeData,int pageNum,int pageCount,String empNum, String fromDate, String FinishDate,String depId,String attendenceStatus) {
		return dao.findByPropertysPage(employeeData,pageNum,pageCount, empNum, fromDate, FinishDate, depId, attendenceStatus);
	}
	
	public int getRowCountByPropertys(MP1001 employeeData,String empNum, String fromDate, String FinishDate,String depId,String attendenceStatus){
		return dao.getRowCountByPropertys(employeeData, empNum, fromDate, FinishDate, depId, attendenceStatus);
	}
	
	public List<MP2003> getPdfData(String empNum, String fromDate, String FinishDate,String depId,String attendenceStatus){
		return dao.getPdfData(empNum, fromDate, FinishDate, depId, attendenceStatus);
	}

	public IMP2003DAO getDao() {
		return dao;
	}

	public void setDao(IMP2003DAO dao) {
		this.dao = dao;
	}
}
