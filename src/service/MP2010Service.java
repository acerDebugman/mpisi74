package service;

import java.util.List;
import java.util.Map;

import dao.MP2010DAO;
import entity.MP2010;

public class MP2010Service implements IMP2010Service {
	MP2010DAO dao;
	@Override
	public void save(MP2010 mp2010) {
		// TODO Auto-generated method stub
		dao.save(mp2010);
	}

	@Override
	public void delete(MP2010 mp2010) {
		// TODO Auto-generated method stub
		dao.delete(mp2010);
	}

	@Override
	public MP2010 findByKey(String key) {
		// TODO Auto-generated method stub
		return dao.findByKey(key);
	}

	@Override
	public MP2010 findByValue(String value) {
		// TODO Auto-generated method stub
		return dao.findByValue(value);
	}

	@Override
	public List<MP2010> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void update(MP2010 mp2010) {
		// TODO Auto-generated method stub
		dao.update(mp2010);
	}

	@Override
	public MP2010 findByType(String type) {
		// TODO Auto-generated method stub
		return dao.findByType(type);
	}

	@Override
	public List<MP2010> findByColumnName(Map<String, String> columnMap) {
		// TODO Auto-generated method stub
		return dao.findByColumnName(columnMap);
	}

	@Override
	public List<MP2010> findByColumnName(Map<String, String> columnMap,
			String strOrder) {
		// TODO Auto-generated method stub
		return dao.findByColumnName(columnMap, strOrder);
	}

	@Override
	public int getAllRowsCount() {
		// TODO Auto-generated method stub
		return dao.getAllRowsCount();
	}

	@Override
	public List<MP2010> findByColumnName(Map<String, String> columnMap,
			String strOrder, boolean pageFlag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSearchConditionRowsCount(Map<String, String> columnMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void getSubscriberInfo(MP2010 mp2010) {
		// TODO Auto-generated method stub

	}

	@Override
	public void analyzeExcelTemplate(MP2010 mp2010) {
		// TODO Auto-generated method stub
		
	}

	public MP2010DAO getDao() {
		return dao;
	}

	public void setDao(MP2010DAO dao) {
		this.dao = dao;
	}
}