package service;

import java.util.List;

import entity.StandardWorkTime;

public interface IStandardWorkTimeService {
	public abstract void save(StandardWorkTime item);

	public abstract void delete(StandardWorkTime item);

	public abstract StandardWorkTime findById(int id);

	public abstract List<StandardWorkTime> findAll();
	
	public abstract void update(StandardWorkTime item);
}
