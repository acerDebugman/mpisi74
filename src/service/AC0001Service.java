package service;

import java.util.List;
import java.util.Map;

import dao.IAC0001DAO;
import entity.AC0001;

public class AC0001Service implements IAC0001Service {

	private IAC0001DAO dao;

	public void delete(AC0001 ac0001) {
		dao.delete(ac0001);
	}

	public List<AC0001> findAll() {
		return dao.findAll();
	}

	public AC0001 findById(String employeeNum) {
		return dao.findById(employeeNum);
	}

	public void save(AC0001 ac0001) {
		dao.save(ac0001);
	}

	public void update(AC0001 ac0001) {
		dao.update(ac0001);
	}
	
	public List<AC0001> findByProperty(String name, String value){
		return dao.findByProperty(name, value);
	}

	// 分页方法
	public List<AC0001> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
		return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
	}
	
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		return dao.getRowCountByPage(propertyMap);
	}
	
	public IAC0001DAO getDao() {
		return dao;
	}

	public void setDao(IAC0001DAO dao) {
		this.dao = dao;
	}
}