package service;

import java.util.List;
import java.util.Map;

import dao.IMP4004DAO;

import entity.MP4004;

public class MP4004Service implements IMP4004Service{
	private IMP4004DAO dao;
	// 保存数据
	public void save(MP4004 mp4004) {
		dao.save(mp4004);
	}
	// 删除数据
	public void delete(MP4004 mp4004) {
		dao.delete(mp4004);
	}
	// 根据KEY检索数据
	public MP4004 findById(int key) {
		return dao.findById(key);
	}
	// 取得所有有效数据
	public List<MP4004> findAll() {
		return dao.findAll();
	}
	// 更新数据
	public void update(MP4004 mp4004) {
		dao.update(mp4004);
	}
	// 动态根据传入的参数，检索数据
	public List<MP4004> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
	// 分页方法
	public List<MP4004> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT) {
		return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		return dao.getRowCountByPage(propertyMap);
	}

	/**
	 * @return the dao
	 */
	public IMP4004DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IMP4004DAO dao) {
		this.dao = dao;
	}
}
