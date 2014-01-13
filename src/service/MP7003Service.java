package service;

import java.util.List;
import java.util.Map;

import dao.IMP7003DAO;
import entity.MP7003;

public class MP7003Service implements IMP7003Service{
	private IMP7003DAO dao;
	// 保存数据
	public void save(MP7003 mp7003) {
		dao.save(mp7003);
	}
	// 删除数据
	public void delete(MP7003 mp7003) {
		dao.delete(mp7003);
	}
	// 根据KEY检索数据
	public MP7003 findById(String key) {
		return dao.findById(key);
	}
	// 取得所有有效数据
	public List<MP7003> findAll() {
		return dao.findAll();
	}
	// 更新数据
	public void update(MP7003 mp7003) {
		dao.update(mp7003);
	}
	// 动态根据传入的参数，检索数据
	public List<MP7003> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
	// 分页方法
	public List<MP7003> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT) {
		return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		return dao.getRowCountByPage(propertyMap);
	}
	// 自定义查询条件及排序方式
	public List<MP7003> findDataBySelfDefined(String search, String order, int pageNum, int pageCount) {
		return dao.findDataBySelfDefined(search, order, pageNum, pageCount);
	}
	/**
	 * @return the dao
	 */
	public IMP7003DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IMP7003DAO dao) {
		this.dao = dao;
	}
}
