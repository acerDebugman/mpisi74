package service;

import java.util.HashMap;
import java.util.List;

import dao.IAC0011DAO;
import entity.AC0011;

public class AC0011Service implements IAC0011Service{
	private IAC0011DAO dao;

	public void delete(AC0011 ac0011) {
		dao.delete(ac0011);
	}

	public List<AC0011> findAll() {
		return dao.findAll();
	}

	public AC0011 findById(String employeeNum) {
		return dao.findById(employeeNum);
	}

	public void save(AC0011 ac0011) {
		dao.save(ac0011);
	}

	public void update(AC0011 ac0011) {
		dao.update(ac0011);
	}
	
	public List<AC0011> findByProperty(String name, String value){
		return dao.findByProperty(name, value);
	}
	
	public HashMap<String,String> functionAccessCheck(String _empNum,String _systemNum){
		return dao.functionAccessCheck(_empNum,_systemNum);
	}

	public IAC0011DAO getDao() {
		return dao;
	}

	public void setDao(IAC0011DAO dao) {
		this.dao = dao;
	}
}
