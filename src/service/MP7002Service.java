package service;

import java.util.List;
import java.util.Map;

import dao.IMP7002DAO;
import entity.MP7002;

public class MP7002Service implements IMP7002Service{
	private IMP7002DAO dao;
	// 保存数据
	public void save(MP7002 mp7002) {
		dao.save(mp7002);
	}
	// 删除数据
	public void delete(MP7002 mp7002) {
		dao.delete(mp7002);
	}
	// 根据KEY检索数据
	public MP7002 findById(String key) {
		return dao.findById(key);
	}
	// 取得所有有效数据
	public List<MP7002> findAll() {
		return dao.findAll();
	}
	// 更新数据
	public void update(MP7002 mp7002) {
		dao.update(mp7002);
	}
	// 动态根据传入的参数，检索数据
	public List<MP7002> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
	// 分页方法
	public List<MP7002> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT) {
		return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		return dao.getRowCountByPage(propertyMap);
	}
	// 自定义查询条件及排序方式
	public List<MP7002> findDataBySelfDefined(String search, String order, int pageNum, int pageCount) {
		return dao.findDataBySelfDefined(search, order, pageNum, pageCount);
	}
	/**
	 * @return the dao
	 */
	public IMP7002DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IMP7002DAO dao) {
		this.dao = dao;
	}
	@Override
	public void evictObj(MP7002 mp72) {
		// TODO Auto-generated method stub
		dao.evictObj(mp72);
	}
	
	
}
