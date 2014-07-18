package com.joe.service;

import java.util.List;

import com.joe.dao.IMP2002DAO;
import com.joe.model.MP1001;
import com.joe.model.MP2002;

public class MP2002Service implements IMP2002Service {

	private IMP2002DAO dao;

	public void delete(MP2002 mp2002) {
		dao.delete(mp2002);
	}

	public List<MP2002> findAll() {
		return dao.findAll();
	}

	public MP2002 findById(String employeeNum) {
		return dao.findById(employeeNum);
	}

	public void save(MP2002 mp2002) {
		dao.save(mp2002);
	}

	public void update(MP2002 mp2002) {
		dao.update(mp2002);
	}
	
	public List<MP2002> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
	
	public List<MP2002> findByPropertysPage(MP1001 employeeData, int PAGE_NUM, int PAGE_COUNT){
		return dao.findByPropertysPage(employeeData, PAGE_NUM, PAGE_COUNT);
	}
	
	public int getRowCountByPage(MP1001 employeeData){
		return dao.getRowCountByPage(employeeData);
	}
	
	public List<MP2002> findDataBySelfDefined(String search,String order, int pageNum, int pageCount){
		return dao.findDataBySelfDefined(search, order, pageNum, pageCount);
	}

	public IMP2002DAO getDao() {
		return dao;
	}

	public void setDao(IMP2002DAO dao) {
		this.dao = dao;
	}	

}
