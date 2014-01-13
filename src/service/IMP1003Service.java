package service;

import java.util.List;
import entity.MP1003;

public interface IMP1003Service {
	public abstract void save(MP1003 mp1003);

	public abstract void delete(MP1003 mp1003);

	public abstract MP1003 findById(int seq);

	public abstract List<MP1003> findAll();

	public abstract void update(MP1003 mp1003);
	
	public abstract List<MP1003> findByProperty(String name, String value);
}