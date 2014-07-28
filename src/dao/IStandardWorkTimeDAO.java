package dao;

import java.util.List;

import entity.StandardWorkTime;

public interface IStandardWorkTimeDAO {
	public abstract void save(StandardWorkTime item);

	public abstract void delete(StandardWorkTime item);

	public abstract StandardWorkTime findById(int id);

	public abstract List<StandardWorkTime> findAll();
	
	public abstract void update(StandardWorkTime item);

}
