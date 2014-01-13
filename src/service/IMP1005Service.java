package service;

import java.util.List;
import entity.MP1005;

public interface IMP1005Service {
	public abstract void save(MP1005 mp1005);

	public abstract void delete(MP1005 mp1005);

	public abstract MP1005 findById(int seq);

	public abstract List<MP1005> findAll();

	public abstract void update(MP1005 mp1005);
	
	public abstract List<MP1005> findByProperty(String name, String value);
}