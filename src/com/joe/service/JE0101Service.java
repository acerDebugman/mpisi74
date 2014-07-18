package com.joe.service;

import java.util.List;

import com.joe.dao.JE0101DAO;
import com.joe.model.SYSDICT;

public class JE0101Service implements IJE0101Service {
	JE0101DAO dao;
	
	@Override
	public void save(SYSDICT je0101) {
		// TODO Auto-generated method stub
		dao.save(je0101);
	}

	@Override
	public void delete(SYSDICT je0101) {
		// TODO Auto-generated method stub
		dao.delete(je0101);
	}

	@Override
	public SYSDICT findByKey(String key) {
		// TODO Auto-generated method stub
		return dao.findByKey(key);
	}

	@Override
	public SYSDICT findByValue(String value) {
		// TODO Auto-generated method stub
		return dao.findByValue(value);
	}

	@Override
	public List<SYSDICT> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void update(SYSDICT je0101) {
		// TODO Auto-generated method stub
		dao.update(je0101);
	}

	public JE0101DAO getDao() {
		return dao;
	}

	public void setDao(JE0101DAO dao) {
		this.dao = dao;
	}

	@Override
	public SYSDICT findByType(String type) {
		// TODO Auto-generated method stub
		return dao.findByType(type);
	}

}
