package service;

import java.util.List;

import dao.IMP2004DAO;
import entity.MP2004;

public class MP2004Service implements IMP2004Service{
	private IMP2004DAO dao;

	public void delete(MP2004 mp2004) {
		dao.delete(mp2004);
	}

	public List<MP2004> findAll() {
		return dao.findAll();
	}

	public MP2004 findById(String leaveNum) {
		return dao.findById(leaveNum);
	}

	public void save(MP2004 mp2004) {
		dao.save(mp2004);
	}

	public void update(MP2004 mp2004) {
		dao.update(mp2004);
	}
	
	public List<MP2004> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
	
	public IMP2004DAO getDao() {
		return dao;
	}

	public void setDao(IMP2004DAO dao) {
		this.dao = dao;
	}	
}
