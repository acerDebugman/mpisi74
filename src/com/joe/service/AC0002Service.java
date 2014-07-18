package com.joe.service;

import java.util.List;
import java.util.Map;

import com.joe.dao.IAC0002DAO;
import com.joe.model.AC0002;

public class AC0002Service implements IAC0002Service {

	private IAC0002DAO dao;

	public void delete(AC0002 ac0002) {
		dao.delete(ac0002);
	}

	public List<AC0002> findAll() {
		return dao.findAll();
	}

	public AC0002 findById(String employeeNum) {
		return dao.findById(employeeNum);
	}

	public void save(AC0002 ac0002) {
		dao.save(ac0002);
	}

	public void update(AC0002 ac0002) {
		dao.update(ac0002);
	}
	
	public List<AC0002> findByProperty(String name, String value){
		return dao.findByProperty(name, value);
	}
	
	// 分页方法
	public List<AC0002> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
		return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
	}
	
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		return dao.getRowCountByPage(propertyMap);
	}

	public IAC0002DAO getDao() {
		return dao;
	}

	public void setDao(IAC0002DAO dao) {
		this.dao = dao;
	}
}