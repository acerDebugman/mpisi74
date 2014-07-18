package com.joe.service;

import java.util.List;

import com.joe.dao.IMP0011DAO;
import com.joe.model.MP0011;

public class MP0011Service implements IMP0011Service {

	private IMP0011DAO dao;
	
	public void save(MP0011 mp0011) {
		dao.save(mp0011);
	}

	public void delete(MP0011 mp0011) {
		dao.delete(mp0011);
	}

	public MP0011 findById(String _key) {
		return dao.findById(_key);
	}

	public List<MP0011> findAll() {
		return dao.findAll();
	}

	public void update(MP0011 mp0011) {
		dao.update(mp0011);
	}

	public List<MP0011> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}

	public List<MP0011> findByProperty(String employeeNum, String employeeName,String loginTimeFrom, String loginTimeTo, int PAGE_NUM, int PAGE_COUNT) {
		return dao.findByProperty(employeeNum, employeeName, loginTimeFrom, loginTimeTo, PAGE_NUM, PAGE_COUNT);
	}

	/**
	 * @return the dao
	 */
	public IMP0011DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IMP0011DAO dao) {
		this.dao = dao;
	}

}
