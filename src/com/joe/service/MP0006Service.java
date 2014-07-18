package com.joe.service;

import java.util.List;

import com.joe.dao.IMP0006DAO;
import com.joe.model.MP0006;

public class MP0006Service implements IMP0006Service{

	private IMP0006DAO dao;
	
	public void save(MP0006 mp0006) {
		dao.save(mp0006);
	}

	public void delete(MP0006 mp0006) {
		dao.delete(mp0006);
	}

	public MP0006 findById(int seq) {
		return dao.findById(seq);
	}

	public List<MP0006> findAll() {
		return dao.findAll();
	}

	public void update(MP0006 mp0006) {
		dao.update(mp0006);
	}

	/**
	 * @return the dao
	 */
	public IMP0006DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IMP0006DAO dao) {
		this.dao = dao;
	}
	
	public List<MP0006> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}

}
