package com.joe.service;

import java.util.HashMap;
import java.util.List;

import com.joe.dao.IAC0012DAO;
import com.joe.model.AC0012;

public class AC0012Service implements IAC0012Service{
	private IAC0012DAO dao;

	public void delete(AC0012 ac0012) {
		dao.delete(ac0012);
	}

	public List<AC0012> findAll() {
		return dao.findAll();
	}

	public AC0012 findById(String employeeNum) {
		return dao.findById(employeeNum);
	}

	public void save(AC0012 ac0012) {
		dao.save(ac0012);
	}

	public void update(AC0012 ac0012) {
		dao.update(ac0012);
	}
	
	public List<AC0012> findByProperty(String name, String value){
		return dao.findByProperty(name, value);
	}
	
	public HashMap<String,String> functionOptAccessCheck(String _empNum,String _funcNum,String _systemNum){
		return dao.functionOptAccessCheck(_empNum, _funcNum,_systemNum);
	}

	public IAC0012DAO getDao() {
		return dao;
	}

	public void setDao(IAC0012DAO dao) {
		this.dao = dao;
	}
}
