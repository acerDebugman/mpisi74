package service;

import java.util.List;

import dao.IMP1009DAO;
import entity.MP1009;

public class MP1009Service implements IMP1009Service {
	private IMP1009DAO dao;
	
	public void save(MP1009 mp1009) {
		dao.save(mp1009);
	}

	public void delete(MP1009 mp1009) {
		dao.delete(mp1009);
	}

	public MP1009 findById(int _key) {
		return dao.findById(_key);
	}

	public List<MP1009> findAll() {
		return dao.findAll();
	}

	public void update(MP1009 mp1009) {
		dao.update(mp1009);
	}
	
	public List<MP1009> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
	
	public List<MP1009> findByProperty(String employeeNum,String employeeName,String documentName, int PAGE_NUM, int PAGE_COUNT){
		return dao.findByProperty(employeeNum, employeeName, documentName, PAGE_NUM, PAGE_COUNT);
	}

	/**
	 * @return the dao
	 */
	public IMP1009DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IMP1009DAO dao) {
		this.dao = dao;
	}
}
