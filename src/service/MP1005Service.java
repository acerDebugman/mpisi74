package service;

import java.util.List;
import dao.IMP1005DAO;
import entity.MP1005;

public class MP1005Service implements IMP1005Service {

	private IMP1005DAO dao;

	public void delete(MP1005 mp1005) {
		dao.delete(mp1005);
	}

	public List<MP1005> findAll() {
		return dao.findAll();
	}

	public MP1005 findById(int seq) {
		return dao.findById(seq);
	}

	public void save(MP1005 mp1005) {
		dao.save(mp1005);
	}

	public void update(MP1005 mp1005) {
		dao.update(mp1005);
	}

	public IMP1005DAO getDao() {
		return dao;
	}

	public void setDao(IMP1005DAO dao) {
		this.dao = dao;
	}	

	public List<MP1005> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
}
