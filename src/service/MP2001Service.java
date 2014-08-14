package service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import dao.IMP2001DAO;
import entity.MP1001;
import entity.MP2001;

public class MP2001Service implements IMP2001Service {

	private IMP2001DAO dao;

	public void delete(MP2001 mp2001) {
		dao.delete(mp2001);
	}

	public List<MP2001> findAll() {
		return dao.findAll();
	}

	public MP2001 findById(String leaveNum) {
		return dao.findById(leaveNum);
	}

	public void save(MP2001 mp2001) {
		dao.save(mp2001);
	}

	public void update(MP2001 mp2001) {
		dao.update(mp2001);
	}
	
	public List<MP2001> findByProperty(String name, String value, boolean flag) {
		return dao.findByProperty(name, value, flag);
	}
	
	public List<MP2001> findByDate(String empNum,String date){
		return dao.findByDate(empNum, date);
	}
	
	public boolean checkLeaveDay(String _fromDate, String _toDate, String _empNum){
		return dao.checkLeaveDay(_fromDate, _toDate, _empNum);
	}
	
	public List<MP2001> findByProperty(Map<String, String> propertyMap,MP1001 employeeData) {
		return dao.findByProperty(propertyMap, employeeData);
	}
	
	public List<MP2001> findByPropertyPage(Map<String, String> propertyMap,MP1001 employeeData){
		return dao.findByPropertyPage(propertyMap, employeeData);
	}
	
	public int getLeaveApplyReportRowCount(String employeeNum, String departmentId, String fromDate, String toDate){
		return dao.getLeaveApplyReportRowCount(employeeNum, departmentId, fromDate, toDate);
	}
	
	public List<MP2001> getLeaveApplyReport(String employeeNum, String departmentId, String fromDate, String toDate, int PAGE_NUM, int PAGE_COUNT){
		return dao.getLeaveApplyReport(employeeNum, departmentId, fromDate, toDate, PAGE_NUM, PAGE_COUNT);
	}

	public IMP2001DAO getDao() {
		return dao;
	}

	public void setDao(IMP2001DAO dao) {
		this.dao = dao;
	}
	
	public List<MP2001> locateAppliationByOneDate(Date date, MP1001 emp){
		List<MP2001> lst = dao.locateAppliationByOneDate(date, emp);

		MP2001 old = null;
		for(MP2001 mp21 : lst){
			if(null == old){
				old = mp21;
			}
			else{
				if(old.getMP2001_APPROVAL().equals("3") && mp21.getMP2001_APPROVAL().equals("3")){
					if(old.getMP2001_TO_DATETIME().substring(0, 16).equals(mp21.getMP2001_FROM_DATETIME().substring(0, 16))){
						//change time to the same one~
						mp21.setMP2001_FROM_DATETIME(old.getMP2001_FROM_DATETIME());
						old.setMP2001_TO_DATETIME(mp21.getMP2001_TO_DATETIME());  //here need to detach the objects first, did it in Dao layer
					}
				}
				
				old = mp21;
			}
		}
		
		return lst;
	}
}
