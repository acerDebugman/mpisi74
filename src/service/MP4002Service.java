package service;

import java.util.List;
import java.util.Map;

import dao.IMP4002DAO;

import entity.MP4002;

public class MP4002Service implements IMP4002Service{
	private IMP4002DAO dao;
	// 保存数据
	public void save(MP4002 mp4002) {
		dao.save(mp4002);
	}
	// 删除数据
	public void delete(MP4002 mp4002) {
		dao.delete(mp4002);
	}
	// 根据KEY检索数据
	public MP4002 findById(String key) {
		return dao.findById(key);
	}
	// 取得所有有效数据
	public List<MP4002> findAll() {
		return dao.findAll();
	}
	// 更新数据
	public void update(MP4002 mp4002) {
		dao.update(mp4002);
	}
	// 动态根据传入的参数，检索数据
	public List<MP4002> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
	// 分页方法
	public List<MP4002> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT) {
		return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		return dao.getRowCountByPage(propertyMap);
	}

	/**
	 * @return the dao
	 */
	public IMP4002DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IMP4002DAO dao) {
		this.dao = dao;
	}
}
