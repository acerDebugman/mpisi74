package service;

import java.util.List;
import java.util.Map;

import dao.IMP7007DAO;
import entity.MP7007;

public class MP7007Service implements IMP7007Service{
	private IMP7007DAO dao;
	// 保存数据
	public void save(MP7007 mp7007) {
		dao.save(mp7007);
	}
	// 删除数据
	public void delete(MP7007 mp7007) {
		dao.delete(mp7007);
	}
	// 根据KEY检索数据
	public MP7007 findById(String key) {
		return dao.findById(key);
	}
	// 取得所有有效数据
	public List<MP7007> findAll() {
		return dao.findAll();
	}
	// 更新数据
	public void update(MP7007 mp7007) {
		dao.update(mp7007);
	}
	// 动态根据传入的参数，检索数据
	public List<MP7007> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
	// 分页方法
	public List<MP7007> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT) {
		return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		return dao.getRowCountByPage(propertyMap);
	}
	// 自定义查询条件及排序方式
	public List<MP7007> findDataBySelfDefined(String search, String order, int pageNum, int pageCount) {
		return dao.findDataBySelfDefined(search, order, pageNum, pageCount);
	}
	/**
	 * @return the dao
	 */
	public IMP7007DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IMP7007DAO dao) {
		this.dao = dao;
	}
}
