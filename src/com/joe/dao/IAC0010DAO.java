package com.joe.dao;

import java.util.List;

import com.joe.model.AC0010;

public interface IAC0010DAO {
	// 保存数据
	public abstract void save(AC0010 ac0010);
	// 删除数据
	public abstract void delete(AC0010 ac0010);
	// 根据关键字查询数据
	public abstract AC0010 findById(String seq);
	// 查询所有数据
	public abstract List<AC0010> findAll();
	// 更新数据
	public abstract void update(AC0010 ac0010);
	// 根据检索字段查询数据
	public abstract List<AC0010> findByProperty(String name, String value);
	// 系统权限检查
	public abstract boolean systemAccessCheck(String _empNum,String _systemNum);
}
