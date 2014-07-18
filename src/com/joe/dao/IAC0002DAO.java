package com.joe.dao;

import java.util.List;
import java.util.Map;

import com.joe.model.AC0002;

public interface IAC0002DAO {
	// 保存数据
	public abstract void save(AC0002 ac0002);
	// 删除数据
	public abstract void delete(AC0002 ac0002);
	// 根据关键字查询数据
	public abstract AC0002 findById(String seq);
	// 查询所有数据
	public abstract List<AC0002> findAll();
	// 更新数据
	public abstract void update(AC0002 ac0002);
	// 根据检索字段查询数据
	public abstract List<AC0002> findByProperty(String name, String value);
	// 分页方法
	public abstract List<AC0002> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT);
	// 分页方法，取得数据总条数
	public abstract int getRowCountByPage(Map<String, String> propertyMap);
}