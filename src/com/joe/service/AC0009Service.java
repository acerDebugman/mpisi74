package com.joe.service;

import java.util.List;

import com.joe.dao.IAC0009DAO;
import com.joe.model.AC0009;

public class AC0009Service implements IAC0009Service {

	private IAC0009DAO dao;

	public void delete(AC0009 ac0009) {
		dao.delete(ac0009);
	}

	public List<AC0009> findAll() {
		return dao.findAll();
	}

	public AC0009 findById(String employeeNum) {
		return dao.findById(employeeNum);
	}

	public void save(AC0009 ac0009) {
		dao.save(ac0009);
	}

	public void update(AC0009 ac0009) {
		dao.update(ac0009);
	}
	
	public List<AC0009> findByProperty(String name, String value){
		return dao.findByProperty(name, value);
	}
	
	public String getMailInfoByAccountApprove(){
		return dao.getMailInfoByAccountApprove();
	}

	public IAC0009DAO getDao() {
		return dao;
	}

	public void setDao(IAC0009DAO dao) {
		this.dao = dao;
	}
}