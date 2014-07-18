package com.joe.service;

import java.util.List;
import java.util.Map;

import com.joe.dao.IAC0003DAO;
import com.joe.model.AC0003;

public class AC0003Service implements IAC0003Service {

	private IAC0003DAO dao;

	public void delete(AC0003 ac0003) {
		dao.delete(ac0003);
	}
	public List<AC0003> findAll() {
		return dao.findAll();
	}
	public AC0003 findById(String employeeNum) {
		return dao.findById(employeeNum);
	}
	public void save(AC0003 ac0003) {
		dao.save(ac0003);
	}
	public void update(AC0003 ac0003) {
		dao.update(ac0003);
	}
	public List<AC0003> findByProperty(String name, String value){
		return dao.findByProperty(name, value);
	}
	// 取得系统功能操作
	public List<AC0003> getFunctionOperationInfoData(String functionNum){
		return dao.getFunctionOperationInfoData(functionNum);
	}
	// 分页方法
	public List<AC0003> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
		return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		return dao.getRowCountByPage(propertyMap);
	}
	public IAC0003DAO getDao() {
		return dao;
	}
	public void setDao(IAC0003DAO dao) {
		this.dao = dao;
	}
}