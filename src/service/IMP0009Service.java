package service;

import java.util.List;
import entity.MP0009;

public interface IMP0009Service {
	public abstract void save(MP0009 mp0009);

	public abstract void delete(MP0009 mp0009);

	public abstract MP0009 findById(int seq);

	public abstract List<MP0009> findAll();

	public abstract void update(MP0009 mp0009);

	public abstract List<MP0009> findByProperty(String name, String value);
	
}
