package com.joe.service;

import java.util.List;

import com.joe.model.SYSDICT;

public interface IJE0101Service {
	public abstract void save(SYSDICT je0101);

	public abstract void delete(SYSDICT je0101);

	public abstract SYSDICT findByKey(String key);
	
	public abstract SYSDICT findByValue(String value);

	public abstract List<SYSDICT> findAll();

	public abstract void update(SYSDICT je0101);
	
	public SYSDICT findByType(String type);
}
