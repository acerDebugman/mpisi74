package service;

import java.util.List;
import dao.IMP0002DAO;
import entity.MP0002;

public class MP0002Service implements IMP0002Service {
	private IMP0002DAO dao;
	
	public void save(MP0002 mp0002) {
		dao.save(mp0002);
	}

	public void delete(MP0002 mp0002) {
		dao.delete(mp0002);
	}

	public MP0002 findById(int seq) {
		return dao.findById(seq);
	}

	public List<MP0002> findAll(boolean flag) {
		return dao.findAll(flag);
	}

	public void update(MP0002 mp0002) {
		dao.update(mp0002);
	}

	/**
	 * @return the dao
	 */
	public IMP0002DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IMP0002DAO dao) {
		this.dao = dao;
	}
	
	public List<MP0002> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
}
