package com.joe.dao;

import java.util.HashMap;
import java.util.List;

import com.joe.model.AC0012;

public interface IAC0012DAO {
	// 保存数据
	public abstract void save(AC0012 ac0012);
	// 删除数据
	public abstract void delete(AC0012 ac0012);
	// 根据关键字查询数据
	public abstract AC0012 findById(String seq);
	// 查询所有数据
	public abstract List<AC0012> findAll();
	// 更新数据
	public abstract void update(AC0012 ac0012);
	// 根据检索字段查询数据
	public abstract List<AC0012> findByProperty(String name, String value);
	// 功能操作权限检查
	public abstract HashMap<String,String> functionOptAccessCheck(String _empNum,String _funcNum,String _systemNum);
}
