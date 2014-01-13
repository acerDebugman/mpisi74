package dao;

import java.util.List;

import entity.MP1002;

public interface IMP1002DAO {
	public abstract void save(MP1002 mp1002);

	public abstract void delete(MP1002 mp1002);

	public abstract MP1002 findById(int seq);

	public abstract List<MP1002> findAll();

	public abstract void update(MP1002 mp1002);
	
	public abstract List<MP1002> findByProperty(String name, String value);
}
