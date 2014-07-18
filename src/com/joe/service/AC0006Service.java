package com.joe.service;

import java.util.HashMap;
import java.util.List;

import com.joe.dao.IAC0006DAO;
import com.joe.model.AC0006;

public class AC0006Service implements IAC0006Service {

	private IAC0006DAO dao;

	public void delete(AC0006 ac0006) {
		dao.delete(ac0006);
	}

	public List<AC0006> findAll() {
		return dao.findAll();
	}

	public AC0006 findById(String employeeNum) {
		return dao.findById(employeeNum);
	}

	public void save(AC0006 ac0006) {
		dao.save(ac0006);
	}

	public void update(AC0006 ac0006) {
		dao.update(ac0006);
	}
	
	public List<AC0006> findByProperty(String name, String value){
		return dao.findByProperty(name, value);
	}
	
	public boolean systemAccessCheck(String _empNum,String _systemNum){
		return dao.systemAccessCheck(_empNum,_systemNum);
	}
	
	public HashMap<String,String> functionAccessCheck(String _empNum,String _systemNum){
		return dao.functionAccessCheck(_empNum,_systemNum);
	}
	
	public HashMap<String,String> functionOptAccessCheck(String _empNum,String _funcNum,String _systemNum){
		return dao.functionOptAccessCheck(_empNum, _funcNum,_systemNum);
	}

	public IAC0006DAO getDao() {
		return dao;
	}

	public void setDao(IAC0006DAO dao) {
		this.dao = dao;
	}
}