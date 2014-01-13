package service;

import java.util.List;
import java.util.Map;

import entity.AC0002;

public interface IAC0002Service {
	public abstract void save(AC0002 ac0002);

	public abstract void delete(AC0002 ac0002);

	public abstract AC0002 findById(String employeeNum);

	public abstract List<AC0002> findAll();
	
	public abstract void update(AC0002 ac0002);
	
	public List<AC0002> findByProperty(String name, String value);
	// 分页方法
	public abstract List<AC0002> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT);
	// 分页方法，取得数据总条数
	public abstract int getRowCountByPage(Map<String, String> propertyMap);
}