package service;

import java.util.List;

import dao.IMP0010DAO;
import entity.MP0010;

public class MP0010Service implements IMP0010Service{
	private IMP0010DAO dao;
	
	public void save(MP0010 mp0010) {
		dao.save(mp0010);
	}

	public void delete(MP0010 mp0010) {
		dao.delete(mp0010);
	}

	public MP0010 findById(String _key) {
		return dao.findById(_key);
	}

	public List<MP0010> findAll() {
		return dao.findAll();
	}

	public void update(MP0010 mp0010) {
		dao.update(mp0010);
	}
	
	public List<MP0010> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}

	/**
	 * @return the dao
	 */
	public IMP0010DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IMP0010DAO dao) {
		this.dao = dao;
	}
}
