package com.joe.service;

import java.util.List;
import java.util.Map;

import com.joe.dao.IMP2007DAO;
import com.joe.model.MP2007;

public class MP2007Service implements IMP2007Service {
	private IMP2007DAO dao;
	// 保存数据
	public void save(MP2007 mp2007) {
		dao.save(mp2007);
	}
	// 删除数据
	public void delete(MP2007 mp2007) {
		dao.delete(mp2007);
	}
	// 根据KEY检索数据
	public MP2007 findById(int key) {
		return dao.findById(key);
	}
	// 取得所有有效数据
	public List<MP2007> findAll() {
		return dao.findAll();
	}
	// 更新数据
	public void update(MP2007 mp2007) {
		dao.update(mp2007);
	}
	// 动态根据传入的参数，检索数据
	public List<MP2007> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
	// 分页方法
	public List<MP2007> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT) {
		return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		return dao.getRowCountByPage(propertyMap);
	}

	/**
	 * @return the dao
	 */
	public IMP2007DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IMP2007DAO dao) {
		this.dao = dao;
	}
}
