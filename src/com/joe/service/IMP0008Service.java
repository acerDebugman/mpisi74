package com.joe.service;

import java.util.List;

import com.joe.model.MP0008;

public interface IMP0008Service {
	public abstract void save(MP0008 mp0008);

	public abstract void delete(MP0008 mp0008);

	public abstract MP0008 findById(int seq);

	public abstract List<MP0008> findAll();

	public abstract void update(MP0008 mp0008);

	public abstract List<MP0008> findByProperty(String name, String value);
	
	public abstract List<MP0008> findByDepartmentId(String value);
}
