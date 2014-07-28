package dao;

import java.util.List;

import entity.WorkTimePattern;

public interface IWorkTimePatternDAO {
	public abstract void save(WorkTimePattern item);

	public abstract void delete(WorkTimePattern item);

	public abstract WorkTimePattern findById(int id);

	public abstract List<WorkTimePattern> findAll();
	
	public abstract void update(WorkTimePattern item);
}
