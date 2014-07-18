package com.joe.service;

import java.util.List;
import java.util.Map;

import com.joe.dao.JE0201DAO;
import com.joe.model.JE0201;

public class JE0201Service implements IJE0201Service {
	JE0201DAO dao;
	
	@Override
	public void save(JE0201 je0201) {
		// TODO Auto-generated method stub
		dao.save(je0201);
	}

	@Override
	public void delete(JE0201 je0201) {
		// TODO Auto-generated method stub
		dao.delete(je0201);
	}

	@Override
	public JE0201 findByKey(String key) {
		// TODO Auto-generated method stub
		return dao.findByKey(key);
	}

	@Override
	public JE0201 findByValue(String value) {
		// TODO Auto-generated method stub
		return dao.findByValue(value);
	}

	@Override
	public List<JE0201> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void update(JE0201 je0201) {
		// TODO Auto-generated method stub
		dao.update(je0201);
	}

	public JE0201DAO getDao() {
		return dao;
	}

	public void setDao(JE0201DAO dao) {
		this.dao = dao;
	}

	@Override
	public JE0201 findByType(String type) {
		// TODO Auto-generated method stub
		return dao.findByType(type);
	}

	@Override
	public List<JE0201> findByColumnName(Map<String, String> columnMap){
		return dao.findByColumnName(columnMap);
	}
	
	@Override
	public List<JE0201> findByColumnName(Map<String, String> columnMap, String order){
		return dao.findByColumnName(columnMap, order);
	}
}
