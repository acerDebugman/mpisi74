package service;

import java.util.List;
import java.util.Map;

import dao.IAC0005DAO;
import entity.AC0005;

public class AC0005Service implements IAC0005Service {

	private IAC0005DAO dao;

	public void delete(AC0005 ac0005) {
		dao.delete(ac0005);
	}

	public List<AC0005> findAll() {
		return dao.findAll();
	}

	public AC0005 findById(String employeeNum) {
		return dao.findById(employeeNum);
	}

	public void save(AC0005 ac0005) {
		dao.save(ac0005);
	}

	public void update(AC0005 ac0005) {
		dao.update(ac0005);
	}
	
	public List<AC0005> findByProperty(String name, String value){
		return dao.findByProperty(name, value);
	}
	
	// 分页方法
	public List<AC0005> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
		return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
	}
	
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		return dao.getRowCountByPage(propertyMap);
	}

	public IAC0005DAO getDao() {
		return dao;
	}

	public void setDao(IAC0005DAO dao) {
		this.dao = dao;
	}
}