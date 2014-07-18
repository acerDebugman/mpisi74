package com.joe.dao;

import java.util.List;

import com.joe.model.MP0010;

public interface IMP0010DAO {
	public abstract void save(MP0010 mp0010);

	public abstract void delete(MP0010 mp0010);

	public abstract MP0010 findById(String _key);

	public abstract List<MP0010> findAll();

	public abstract void update(MP0010 mp0010);
	
	public abstract List<MP0010> findByProperty(String name, String value);
}
