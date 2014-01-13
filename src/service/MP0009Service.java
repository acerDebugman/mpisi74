package service;

import java.util.List;

import dao.IMP0009DAO;
import entity.MP0009;

public class MP0009Service implements IMP0009Service{
	private IMP0009DAO dao;
	
	public void save(MP0009 mp0009) {
		dao.save(mp0009);
	}

	public void delete(MP0009 mp0009) {
		dao.delete(mp0009);
	}

	public MP0009 findById(int seq) {
		return dao.findById(seq);
	}

	public List<MP0009> findAll() {
		return dao.findAll();
	}

	public void update(MP0009 mp0009) {
		dao.update(mp0009);
	}

	/**
	 * @return the dao
	 */
	public IMP0009DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IMP0009DAO dao) {
		this.dao = dao;
	}
	
	public List<MP0009> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
}
