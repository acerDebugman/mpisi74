package service;

import java.util.List;
import entity.MP0002;

public interface IMP0002Service {
	public abstract void save(MP0002 mp0002);

	public abstract void delete(MP0002 mp0002);

	public abstract MP0002 findById(int seq);

	public abstract List<MP0002> findAll(boolean flag);

	public abstract void update(MP0002 mp0002);
	
	public abstract List<MP0002> findByProperty(String name, String value);
}
