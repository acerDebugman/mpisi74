package service;

import java.util.List;
import java.util.Map;

import entity.AC0005;

public interface IAC0005Service {
	public abstract void save(AC0005 ac0005);

	public abstract void delete(AC0005 ac0005);

	public abstract AC0005 findById(String employeeNum);

	public abstract List<AC0005> findAll();
	
	public abstract void update(AC0005 ac0005);
	
	public List<AC0005> findByProperty(String name, String value);
	
	// 分页方法
	public abstract List<AC0005> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT);
	// 分页方法，取得数据总条数
	public abstract int getRowCountByPage(Map<String, String> propertyMap);
}