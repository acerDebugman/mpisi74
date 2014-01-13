package service;

import java.util.List;
import dao.IAC0008DAO;
import entity.AC0008;

public class AC0008Service implements IAC0008Service {

	private IAC0008DAO dao;

	public void delete(AC0008 ac0008) {
		dao.delete(ac0008);
	}

	public List<AC0008> findAll() {
		return dao.findAll();
	}

	public AC0008 findById(String employeeNum) {
		return dao.findById(employeeNum);
	}

	public void save(AC0008 ac0008) {
		dao.save(ac0008);
	}

	public void update(AC0008 ac0008) {
		dao.update(ac0008);
	}
	
	public List<AC0008> findByProperty(String name, String value){
		return dao.findByProperty(name, value);
	}

	public IAC0008DAO getDao() {
		return dao;
	}

	public void setDao(IAC0008DAO dao) {
		this.dao = dao;
	}
}