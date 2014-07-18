package com.joe.service;

import java.util.List;

import com.joe.dao.IMP1003DAO;
import com.joe.model.MP1003;

public class MP1003Service implements IMP1003Service {

	private IMP1003DAO dao;

	public void delete(MP1003 mp1003) {
		dao.delete(mp1003);
	}

	public List<MP1003> findAll() {
		return dao.findAll();
	}

	public MP1003 findById(int seq) {
		return dao.findById(seq);
	}

	public void save(MP1003 mp1003) {
		dao.save(mp1003);
	}

	public void update(MP1003 mp1003) {
		dao.update(mp1003);
	}

	public IMP1003DAO getDao() {
		return dao;
	}

	public void setDao(IMP1003DAO dao) {
		this.dao = dao;
	}	

	public List<MP1003> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
}
