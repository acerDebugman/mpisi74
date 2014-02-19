package service;

import java.util.List;

import dao.JE0202DAO;
import entity.JE0202;

public class JE0202Service implements IJE0202Service {
	JE0202DAO dao;
	
	@Override
	public void save(JE0202 je0202) {
		// TODO Auto-generated method stub
		dao.save(je0202);
	}

	@Override
	public void delete(JE0202 je0202) {
		// TODO Auto-generated method stub
		dao.delete(je0202);
	}

	@Override
	public JE0202 findByKey(String key) {
		// TODO Auto-generated method stub
		return dao.findByKey(key);
	}

	@Override
	public JE0202 findByValue(String value) {
		// TODO Auto-generated method stub
		return dao.findByValue(value);
	}

	@Override
	public List<JE0202> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void update(JE0202 je0202) {
		// TODO Auto-generated method stub
		dao.update(je0202);
	}

	public JE0202DAO getDao() {
		return dao;
	}

	public void setDao(JE0202DAO dao) {
		this.dao = dao;
	}

	@Override
	public JE0202 findByType(String type) {
		// TODO Auto-generated method stub
		return dao.findByType(type);
	}

}
