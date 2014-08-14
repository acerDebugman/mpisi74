package dao;

import java.util.Date;
import java.util.List;

import entity.MP0010;
import entity.MP1001;
import entity.StandardWorkTime;

public interface IStandardWorkTimeDAO {
	public abstract void save(StandardWorkTime item);

	public abstract void delete(StandardWorkTime item);

	public abstract StandardWorkTime findById(int id);

	public abstract List<StandardWorkTime> findAll();
	
	public abstract void update(StandardWorkTime item);

	public abstract List<MP0010> fetchAllPatternSpecialDays(StandardWorkTime item);
	
	public StandardWorkTime fetchSpecificDayWorkTime(MP1001 emp, Date date);
}
