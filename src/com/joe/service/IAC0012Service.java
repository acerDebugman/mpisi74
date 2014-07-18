package com.joe.service;

import java.util.HashMap;
import java.util.List;

import com.joe.model.AC0012;

public interface IAC0012Service {
	public abstract void save(AC0012 ac0012);

	public abstract void delete(AC0012 ac0012);

	public abstract AC0012 findById(String employeeNum);

	public abstract List<AC0012> findAll();
	
	public abstract void update(AC0012 ac0012);
	
	public List<AC0012> findByProperty(String name, String value);
	
	// 功能操作权限检查
	public abstract HashMap<String,String> functionOptAccessCheck(String _empNum,String _funcNum,String _systemNum);
}
