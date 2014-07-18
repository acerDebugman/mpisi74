package com.joe.service;

import java.util.List;

import com.joe.dao.IAC0010DAO;
import com.joe.model.AC0010;

public class AC0010Service implements IAC0010Service{
	private IAC0010DAO dao;

	public void delete(AC0010 ac0010) {
		dao.delete(ac0010);
	}

	public List<AC0010> findAll() {
		return dao.findAll();
	}

	public AC0010 findById(String employeeNum) {
		return dao.findById(employeeNum);
	}

	public void save(AC0010 ac0010) {
		dao.save(ac0010);
	}

	public void update(AC0010 ac0010) {
		dao.update(ac0010);
	}
	
	public List<AC0010> findByProperty(String name, String value){
		return dao.findByProperty(name, value);
	}
	
	public boolean systemAccessCheck(String _empNum,String _systemNum){
		return dao.systemAccessCheck(_empNum,_systemNum);
	}

	public IAC0010DAO getDao() {
		return dao;
	}

	public void setDao(IAC0010DAO dao) {
		this.dao = dao;
	}
}
