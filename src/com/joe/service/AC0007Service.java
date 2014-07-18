package com.joe.service;

import java.util.List;

import com.joe.dao.IAC0007DAO;
import com.joe.model.AC0007;

public class AC0007Service implements IAC0007Service {

	private IAC0007DAO dao;

	public void delete(AC0007 ac0007) {
		dao.delete(ac0007);
	}

	public List<AC0007> findAll() {
		return dao.findAll();
	}

	public AC0007 findById(String employeeNum) {
		return dao.findById(employeeNum);
	}

	public void save(AC0007 ac0007) {
		dao.save(ac0007);
	}

	public void update(AC0007 ac0007) {
		dao.update(ac0007);
	}
	
	public List<AC0007> findByProperty(String name, String value){
		return dao.findByProperty(name, value);
	}

	public IAC0007DAO getDao() {
		return dao;
	}

	public void setDao(IAC0007DAO dao) {
		this.dao = dao;
	}
}