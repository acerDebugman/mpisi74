package service;

import java.util.List;

import entity.MP0006;

public interface IMP0006Service {
	public abstract void save(MP0006 mp0006);

	public abstract void delete(MP0006 mp0006);

	public abstract MP0006 findById(int seq);

	public abstract List<MP0006> findAll();

	public abstract void update(MP0006 mp0006);
	
	public abstract List<MP0006> findByProperty(String name, String value);
}
