package service;

import java.util.List;

import dao.IMP1004DAO;
import entity.MP1004;

public class MP1004Service implements IMP1004Service {

	private IMP1004DAO dao;

	public void delete(MP1004 mp1004) {
		dao.delete(mp1004);
	}

	public List<MP1004> findAll() {
		return dao.findAll();
	}
	
	public List<MP1004> findAll2(String employeeNum, String employeeName){
		return dao.findAll2(employeeNum, employeeName);
	}

	public MP1004 findById(String employeeNum) {
		return dao.findById(employeeNum);
	}

	public void save(MP1004 mp1004) {
		dao.save(mp1004);
	}

	public void update(MP1004 mp1004) {
		dao.update(mp1004);
	}

	public IMP1004DAO getDao() {
		return dao;
	}

	public void setDao(IMP1004DAO dao) {
		this.dao = dao;
	}	

}
