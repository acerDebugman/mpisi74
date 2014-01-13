package service;

import java.util.List;
import java.util.Map;

import dao.IMP7008DAO;
import entity.MP7008;

public class MP7008Service implements IMP7008Service{
	private IMP7008DAO dao;
	// 保存数据
	public void save(MP7008 mp7008) {
		dao.save(mp7008);
	}
	// 删除数据
	public void delete(MP7008 mp7008) {
		dao.delete(mp7008);
	}
	// 根据KEY检索数据
	public MP7008 findById(String key) {
		return dao.findById(key);
	}
	// 取得所有有效数据
	public List<MP7008> findAll() {
		return dao.findAll();
	}
	// 更新数据
	public void update(MP7008 mp7008) {
		dao.update(mp7008);
	}
	// 更新数据
	public void executeStatement(String statement){
		dao.executeStatement(statement);
	}
	
	// 动态根据传入的参数，检索数据
	public List<MP7008> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
	// 分页方法
	public List<MP7008> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT) {
		return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		return dao.getRowCountByPage(propertyMap);
	}
	// 自定义查询条件及排序方式
	public List<MP7008> findDataBySelfDefined(String search, String order, int pageNum, int pageCount) {
		return dao.findDataBySelfDefined(search, order, pageNum, pageCount);
	}
	/**
	 * @return the dao
	 */
	public IMP7008DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IMP7008DAO dao) {
		this.dao = dao;
	}
}
