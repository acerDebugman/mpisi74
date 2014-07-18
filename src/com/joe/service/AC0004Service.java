package com.joe.service;

import java.util.List;

import com.joe.dao.IAC0004DAO;
import com.joe.model.AC0004;

public class AC0004Service implements IAC0004Service {

	private IAC0004DAO dao;

	public void delete(AC0004 ac0004) {
		dao.delete(ac0004);
	}

	public List<AC0004> findAll() {
		return dao.findAll();
	}

	public AC0004 findById(int seq) {
		return dao.findById(seq);
	}

	public void save(AC0004 ac0004) {
		dao.save(ac0004);
	}

	public void update(AC0004 ac0004) {
		dao.update(ac0004);
	}
	
	public List<AC0004> findByProperty(String name, String value){
		return dao.findByProperty(name, value);
	}
	
	public List<AC0004> getFunctionOperationData(String functionNum, String operationNum){
		return dao.getFunctionOperationData(functionNum, operationNum);
	}

	public IAC0004DAO getDao() {
		return dao;
	}

	public void setDao(IAC0004DAO dao) {
		this.dao = dao;
	}
}