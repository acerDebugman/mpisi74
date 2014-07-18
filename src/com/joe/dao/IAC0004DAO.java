package com.joe.dao;

import java.util.List;

import com.joe.model.AC0004;

public interface IAC0004DAO {
	// 保存数据
	public abstract void save(AC0004 ac0004);
	// 删除数据
	public abstract void delete(AC0004 ac0004);
	// 根据关键字查询数据
	public abstract AC0004 findById(int seq);
	// 查询所有数据
	public abstract List<AC0004> findAll();
	// 更新数据
	public abstract void update(AC0004 ac0004);
	// 根据检索字段查询数据
	public abstract List<AC0004> findByProperty(String name, String value);
	//
	public abstract List<AC0004> getFunctionOperationData(String functionNum, String operationNum);
}