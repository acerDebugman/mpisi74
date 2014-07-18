package com.joe.service;

import java.util.List;
import java.util.Map;

import com.joe.model.AC0003;

public interface IAC0003Service {
	public abstract void save(AC0003 ac0003);

	public abstract void delete(AC0003 ac0003);

	public abstract AC0003 findById(String employeeNum);

	public abstract List<AC0003> findAll();
	
	public abstract void update(AC0003 ac0003);
	
	public List<AC0003> findByProperty(String name, String value);
	// 取得系统功能操作
	public abstract List<AC0003> getFunctionOperationInfoData(String functionNum);
	// 分页方法
	public abstract List<AC0003> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT);
	// 分页方法，取得数据总条数
	public abstract int getRowCountByPage(Map<String, String> propertyMap);
}