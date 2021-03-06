package service;

import java.util.List;
import java.util.Map;

import dao.IMP7005DAO;
import entity.MP7005;

public class MP7005Service implements IMP7005Service{
	private IMP7005DAO dao;
	// 保存数据
	public void save(MP7005 mp7005) {
		dao.save(mp7005);
	}
	// 删除数据
	public void delete(MP7005 mp7005) {
		dao.delete(mp7005);
	}
	// 根据KEY检索数据
	public MP7005 findById(String key) {
		return dao.findById(key);
	}
	// 取得所有有效数据
	public List<MP7005> findAll() {
		return dao.findAll();
	}
	// 更新数据
	public void update(MP7005 mp7005) {
		dao.update(mp7005);
	}
	// 更新数据
	public void updateDataBySelf(List<String> list1, List<String> list2, List<String> list3, List<String> list4, List<String> list5, String key){
		dao.updateDataBySelf(list1, list2, list3, list4, list5, key);
	}
	// 动态根据传入的参数，检索数据
	public List<MP7005> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
	// 分页方法
	public List<MP7005> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT) {
		return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		return dao.getRowCountByPage(propertyMap);
	}
	// 自定义查询条件及排序方式
	public List<MP7005> findDataBySelfDefined(String search, String order, int pageNum, int pageCount) {
		return dao.findDataBySelfDefined(search, order, pageNum, pageCount);
	}
	/**
	 * @return the dao
	 */
	public IMP7005DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IMP7005DAO dao) {
		this.dao = dao;
	}
}
