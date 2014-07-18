package com.joe.service;

import java.util.List;

import com.joe.dao.IHOLIDAYDAO;
import com.joe.model.HOLIDAY;

public class HOLIDAYService implements IHOLIDAYService{

	private IHOLIDAYDAO dao;
	
	@Override
	public void save(HOLIDAY holiday) {
		dao.save(holiday);
	}

	@Override
	public void delete(HOLIDAY holiday) {
		dao.delete(holiday);
	}

	@Override
	public HOLIDAY findById(int seq) {
		return dao.findById(seq);
	}

	@Override
	public List<HOLIDAY> findAll() {
		return dao.findAll();
	}

	@Override
	public void update(HOLIDAY holiday) {
		dao.update(holiday);
	}

	@Override
	public List<HOLIDAY> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}

	/**
	 * @return the dao
	 */
	public IHOLIDAYDAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IHOLIDAYDAO dao) {
		this.dao = dao;
	}

}
