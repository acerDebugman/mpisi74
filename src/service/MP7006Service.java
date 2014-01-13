package service;

import java.util.List;
import java.util.Map;

import dao.IMP7006DAO;
import entity.MP7006;

public class MP7006Service implements IMP7006Service{
	private IMP7006DAO dao;
	// 保存数据
	public void save(MP7006 mp7006) {
		dao.save(mp7006);
	}
	// 删除数据
	public void delete(MP7006 mp7006) {
		dao.delete(mp7006);
	}
	// 根据KEY检索数据
	public MP7006 findById(String key) {
		return dao.findById(key);
	}
	// 取得所有有效数据
	public List<MP7006> findAll() {
		return dao.findAll();
	}
	// 更新数据
	public void update(MP7006 mp7006) {
		dao.update(mp7006);
	}
	// 动态根据传入的参数，检索数据
	public List<MP7006> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
	// 分页方法
	public List<MP7006> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT) {
		return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		return dao.getRowCountByPage(propertyMap);
	}
	// 自定义查询条件及排序方式
	public List<MP7006> findDataBySelfDefined(String search, String order, int pageNum, int pageCount) {
		return dao.findDataBySelfDefined(search, order, pageNum, pageCount);
	}
	/**
	 * @return the dao
	 */
	public IMP7006DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IMP7006DAO dao) {
		this.dao = dao;
	}
}
