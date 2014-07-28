package service;

import java.util.List;

import dao.IJE0101DAO;
import dao.JE0101DAO;
import entity.JE0101;

public class JE0101Service implements IJE0101Service {
	private IJE0101DAO dao;
	
	@Override
	public void save(JE0101 je0101) {
		// TODO Auto-generated method stub
		dao.save(je0101);
	}

	@Override
	public void delete(JE0101 je0101) {
		// TODO Auto-generated method stub
		dao.delete(je0101);
	}

	@Override
	public JE0101 findByKey(String key) {
		// TODO Auto-generated method stub
		return dao.findByKey(key);
	}

	@Override
	public JE0101 findByValue(String value) {
		// TODO Auto-generated method stub
		return dao.findByValue(value);
	}

	@Override
	public List<JE0101> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void update(JE0101 je0101) {
		// TODO Auto-generated method stub
		dao.update(je0101);
	}

	@Override
	public JE0101 findByType(String type) {
		// TODO Auto-generated method stub
		return dao.findByType(type);
	}

	public IJE0101DAO getDao() {
		return dao;
	}

	public void setDao(IJE0101DAO dao) {
		this.dao = dao;
	}

}

