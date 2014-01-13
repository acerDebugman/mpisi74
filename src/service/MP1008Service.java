package service;

import java.util.List;

import dao.IMP1008DAO;
import entity.MP1008;

public class MP1008Service implements IMP1008Service {
	private IMP1008DAO dao;
	
	public void save(MP1008 mp1008) {
		dao.save(mp1008);
	}

	public void delete(MP1008 mp1008) {
		dao.delete(mp1008);
	}

	public MP1008 findById(String _key) {
		return dao.findById(_key);
	}

	public List<MP1008> findAll() {
		return dao.findAll();
	}

	public void update(MP1008 mp1008) {
		dao.update(mp1008);
	}
	
	public void update(MP1008 mp1008, String oldKey) {
		dao.update(mp1008,oldKey);
	}
	
	public List<MP1008> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
	
	public List<MP1008> findByProperty(String intervieweeID,String intervieweeName, int PAGE_NUM, int PAGE_COUNT){
		return dao.findByProperty(intervieweeID, intervieweeName, PAGE_NUM, PAGE_COUNT);
	}

	/**
	 * @return the dao
	 */
	public IMP1008DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IMP1008DAO dao) {
		this.dao = dao;
	}
}
