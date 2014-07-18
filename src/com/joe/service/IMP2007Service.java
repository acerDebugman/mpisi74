package com.joe.service;

import java.util.List;
import java.util.Map;

import com.joe.model.MP2007;

public interface IMP2007Service {
	public abstract void save(MP2007 mp2007);

	public abstract void delete(MP2007 mp2007);

	public abstract MP2007 findById(int key);

	public abstract List<MP2007> findAll();

	public abstract void update(MP2007 mp2007);
	
	public abstract List<MP2007> findByProperty(String name, String value);
	
	public abstract List<MP2007> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT);

	public abstract int getRowCountByPage(Map<String, String> propertyMap) ;
}
