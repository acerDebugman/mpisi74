package service;

import java.util.List;
import entity.MP1008;

public interface IMP1008Service {
	public abstract void save(MP1008 mp1008);

	public abstract void delete(MP1008 mp1008);

	public abstract MP1008 findById(String _key);

	public abstract List<MP1008> findAll();

	public abstract void update(MP1008 mp1008);
	
	public abstract void update(MP1008 mp1008, String oldKey);

	public abstract List<MP1008> findByProperty(String name, String value);
	
	public abstract List<MP1008> findByProperty(String intervieweeID,String intervieweeName, int PAGE_NUM, int PAGE_COUNT);
}
