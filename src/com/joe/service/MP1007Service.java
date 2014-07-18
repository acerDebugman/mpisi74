package com.joe.service;

import java.util.List;

import com.joe.dao.IMP1007DAO;
import com.joe.model.MP1007;

public class MP1007Service implements IMP1007Service {

	private IMP1007DAO dao;

	public void delete(MP1007 mp1007) {
		dao.delete(mp1007);
	}

	public List<MP1007> findAll() {
		return dao.findAll();
	}

	public MP1007 findById(String employeeNum) {
		return dao.findById(employeeNum);
	}

	public void save(MP1007 mp1007) {
		dao.save(mp1007);
	}

	public void update(MP1007 mp1007) {
		dao.update(mp1007);
	}

	public IMP1007DAO getDao() {
		return dao;
	}

	public void setDao(IMP1007DAO dao) {
		this.dao = dao;
	}	

}
