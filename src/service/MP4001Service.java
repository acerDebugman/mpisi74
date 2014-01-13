package service;

import java.util.List;
import java.util.Map;

import dao.IMP4001DAO;

import entity.MP4001;

public class MP4001Service implements IMP4001Service{
	private IMP4001DAO dao;
	// 保存数据
	public void save(MP4001 mp4001) {
		dao.save(mp4001);
	}
	// 删除数据
	public void delete(MP4001 mp4001) {
		dao.delete(mp4001);
	}
	// 根据KEY检索数据
	public MP4001 findById(String key) {
		return dao.findById(key);
	}
	// 取得所有有效数据
	public List<MP4001> findAll() {
		return dao.findAll();
	}
	// 更新数据
	public void update(MP4001 mp4001) {
		dao.update(mp4001);
	}
	// 动态根据传入的参数，检索数据
	public List<MP4001> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
	// 分页方法
	public List<MP4001> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT) {
		return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		return dao.getRowCountByPage(propertyMap);
	}

	/**
	 * @return the dao
	 */
	public IMP4001DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IMP4001DAO dao) {
		this.dao = dao;
	}
}
