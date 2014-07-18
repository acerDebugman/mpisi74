package com.joe.service;

import java.util.HashMap;
import java.util.List;

import com.joe.model.AC0011;

public interface IAC0011Service {
	public abstract void save(AC0011 ac0011);

	public abstract void delete(AC0011 ac0011);

	public abstract AC0011 findById(String employeeNum);

	public abstract List<AC0011> findAll();
	
	public abstract void update(AC0011 ac0011);
	
	public List<AC0011> findByProperty(String name, String value);
	// 功能权限检查
	public abstract HashMap<String,String> functionAccessCheck(String _empNum,String _systemNum);
}
