package service;

import java.util.List;

import dao.IMP1006DAO;
import entity.MP1006;

public class MP1006Service implements IMP1006Service {

	private IMP1006DAO dao;

	public void delete(MP1006 mp1006) {
		dao.delete(mp1006);
	}

	public List<MP1006> findAll() {
		return dao.findAll();
	}

	public MP1006 findById(String employeeNum) {
		return dao.findById(employeeNum);
	}

	public void save(MP1006 mp1006) {
		dao.save(mp1006);
	}

	public void update(MP1006 mp1006) {
		dao.update(mp1006);
	}

	public IMP1006DAO getDao() {
		return dao;
	}

	public void setDao(IMP1006DAO dao) {
		this.dao = dao;
	}	

}
