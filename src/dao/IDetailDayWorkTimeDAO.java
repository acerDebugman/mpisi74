package dao;

import java.util.List;

import entity.DetailDayWorkTime;

public interface IDetailDayWorkTimeDAO {
	public abstract void save(DetailDayWorkTime item);

	public abstract void delete(DetailDayWorkTime item);

	public abstract DetailDayWorkTime findById(int id);

	public abstract List<DetailDayWorkTime> findAll();
	
	public abstract void update(DetailDayWorkTime item);
}
