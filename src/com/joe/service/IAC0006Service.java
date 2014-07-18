package com.joe.service;

import java.util.HashMap;
import java.util.List;

import com.joe.model.AC0006;

public interface IAC0006Service {
	public abstract void save(AC0006 ac0006);

	public abstract void delete(AC0006 ac0006);

	public abstract AC0006 findById(String employeeNum);

	public abstract List<AC0006> findAll();
	
	public abstract void update(AC0006 ac0006);
	
	public List<AC0006> findByProperty(String name, String value);
	// 系统权限检查
	public abstract boolean systemAccessCheck(String _empNum,String _systemNum);
	// 功能权限检查
	public abstract HashMap<String,String> functionAccessCheck(String _empNum,String _systemNum);
	// 功能操作权限检查
	public abstract HashMap<String,String> functionOptAccessCheck(String _empNum,String _funcNum,String _systemNum);
}