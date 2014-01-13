package service;

import java.util.List;

import dao.IMP0005DAO;

import entity.MP0005;

public class MP0005Service implements IMP0005Service{

	private IMP0005DAO dao;
	
	public void save(MP0005 mp0005) {
		dao.save(mp0005);
	}

	public void delete(MP0005 mp0005) {
		dao.delete(mp0005);
	}

	public MP0005 findById(int seq) {
		return dao.findById(seq);
	}

	public List<MP0005> findAll() {
		return dao.findAll();
	}

	public void update(MP0005 mp0005) {
		dao.update(mp0005);
	}

	/**
	 * @return the dao
	 */
	public IMP0005DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IMP0005DAO dao) {
		this.dao = dao;
	}
	
	public List<MP0005> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}

}
