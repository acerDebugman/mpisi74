package service;

import java.util.List;
import java.util.Map;

import dao.IMP7004DAO;
import entity.MP7004;

public class MP7004Service implements IMP7004Service{
	private IMP7004DAO dao;
	// 保存数据
	public void save(MP7004 mp7004) {
		dao.save(mp7004);
	}
	// 删除数据
	public void delete(MP7004 mp7004) {
		dao.delete(mp7004);
	}
	// 根据KEY检索数据
	public MP7004 findById(String key) {
		return dao.findById(key);
	}
	// 取得所有有效数据
	public List<MP7004> findAll() {
		return dao.findAll();
	}
	// 更新数据
	public void update(MP7004 mp7004) {
		dao.update(mp7004);
	}
	// 动态根据传入的参数，检索数据
	public List<MP7004> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
	// 分页方法
	public List<MP7004> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT) {
		return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		return dao.getRowCountByPage(propertyMap);
	}
	// 自定义查询条件及排序方式
	public List<MP7004> findDataBySelfDefined(String search, String order, int pageNum, int pageCount) {
		return dao.findDataBySelfDefined(search, order, pageNum, pageCount);
	}
	
	public List<MP7004> getBarCHart1(String _from, String _to){
		return dao.getBarCHart1(_from, _to);
	}
	public List<MP7004> getBarCHart2(String _from, String _to, String department){
		return dao.getBarCHart2(_from, _to, department);
	}
	public List<MP7004> getLineCHart3(String _from, String _to, String department){
		return dao.getLineCHart3(_from, _to, department);
	}
	public List<MP7004> getLineCHart4(String _from, String _to, String department, String empNum){
		return dao.getLineCHart4(_from, _to, department, empNum);
	}
	
	/**
	 * @return the dao
	 */
	public IMP7004DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IMP7004DAO dao) {
		this.dao = dao;
	}
}
