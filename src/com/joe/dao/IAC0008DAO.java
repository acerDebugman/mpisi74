package com.joe.dao;

import java.util.List;

import com.joe.model.AC0008;

public interface IAC0008DAO {
	// 保存数据
	public abstract void save(AC0008 ac0008);
	// 删除数据
	public abstract void delete(AC0008 ac0008);
	// 根据关键字查询数据
	public abstract AC0008 findById(String seq);
	// 查询所有数据
	public abstract List<AC0008> findAll();
	// 更新数据
	public abstract void update(AC0008 ac0008);
	// 根据检索字段查询数据
	public abstract List<AC0008> findByProperty(String name, String value);
}