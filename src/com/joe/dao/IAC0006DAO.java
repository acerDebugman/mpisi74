package com.joe.dao;

import java.util.HashMap;
import java.util.List;

import com.joe.model.AC0006;

public interface IAC0006DAO {
	// 保存数据
	public abstract void save(AC0006 ac0006);
	// 删除数据
	public abstract void delete(AC0006 ac0006);
	// 根据关键字查询数据
	public abstract AC0006 findById(String seq);
	// 查询所有数据
	public abstract List<AC0006> findAll();
	// 更新数据
	public abstract void update(AC0006 ac0006);
	// 根据检索字段查询数据
	public abstract List<AC0006> findByProperty(String name, String value);
	// 系统权限检查
	public abstract boolean systemAccessCheck(String _empNum,String _systemNum);
	// 功能权限检查
	public abstract HashMap<String,String> functionAccessCheck(String _empNum,String _systemNum);
	// 功能操作权限检查
	public abstract HashMap<String,String> functionOptAccessCheck(String _empNum,String _funcNum,String _systemNum);
}