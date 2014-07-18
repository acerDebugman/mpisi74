package com.joe.service;

import java.util.List;

import com.joe.dao.IMP1002DAO;
import com.joe.model.MP1002;

public class MP1002Service implements IMP1002Service {

	private IMP1002DAO dao;

	public void delete(MP1002 mp1002) {
		dao.delete(mp1002);
	}

	public List<MP1002> findAll() {
		return dao.findAll();
	}

	public MP1002 findById(int seq) {
		return dao.findById(seq);
	}

	public void save(MP1002 mp1002) {
		dao.save(mp1002);
	}

	public void update(MP1002 mp1002) {
		dao.update(mp1002);
	}

	public IMP1002DAO getDao() {
		return dao;
	}

	public void setDao(IMP1002DAO dao) {
		this.dao = dao;
	}	

	public List<MP1002> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
}
