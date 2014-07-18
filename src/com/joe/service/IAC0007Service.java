package com.joe.service;

import java.util.List;

import com.joe.model.AC0007;

public interface IAC0007Service {
	public abstract void save(AC0007 ac0007);

	public abstract void delete(AC0007 ac0007);

	public abstract AC0007 findById(String employeeNum);

	public abstract List<AC0007> findAll();
	
	public abstract void update(AC0007 ac0007);
	
	public List<AC0007> findByProperty(String name, String value);
}