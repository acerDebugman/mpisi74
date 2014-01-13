package dao;

import java.util.List;
import entity.MP2004;

public interface IMP2004DAO {
	public abstract void save(MP2004 mp2004);

	public abstract void delete(MP2004 mp2004);

	public abstract MP2004 findById(String leaveNum);

	public abstract List<MP2004> findAll();

	public abstract void update(MP2004 mp2004);
	
	public abstract List<MP2004> findByProperty(String name, String value);
	
}
