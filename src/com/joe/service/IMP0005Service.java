package com.joe.service;

import java.util.List;

import com.joe.model.MP0005;

public interface IMP0005Service {
	public abstract void save(MP0005 mp0005);

	public abstract void delete(MP0005 mp0005);

	public abstract MP0005 findById(int seq);

	public abstract List<MP0005> findAll();

	public abstract void update(MP0005 mp0005);
	
	public abstract List<MP0005> findByProperty(String name, String value);
}
