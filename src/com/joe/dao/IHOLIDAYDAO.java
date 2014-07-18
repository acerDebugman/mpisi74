package com.joe.dao;

import java.util.List;

import com.joe.model.HOLIDAY;

public interface IHOLIDAYDAO {
	public abstract void save(HOLIDAY holiday);

	public abstract void delete(HOLIDAY holiday);

	public abstract HOLIDAY findById(int seq);

	public abstract List<HOLIDAY> findAll();

	public abstract void update(HOLIDAY holiday);
	
	public abstract List<HOLIDAY> findByProperty(String name, String value);
}
