package service;

import java.util.List;

import dao.IMP0008DAO;

import entity.MP0008;

public class MP0008Service implements IMP0008Service {
	private IMP0008DAO dao;
	
	public void save(MP0008 mp0008) {
		dao.save(mp0008);
	}

	public void delete(MP0008 mp0008) {
		dao.delete(mp0008);
	}

	public MP0008 findById(int seq) {
		return dao.findById(seq);
	}

	public List<MP0008> findAll() {
		return dao.findAll();
	}

	public void update(MP0008 mp0008) {
		dao.update(mp0008);
	}

	/**
	 * @return the dao
	 */
	public IMP0008DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IMP0008DAO dao) {
		this.dao = dao;
	}
	
	public List<MP0008> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
	
	public List<MP0008> findByDepartmentId(String value){
		return dao.findByDepartmentId(value);
	}
}
