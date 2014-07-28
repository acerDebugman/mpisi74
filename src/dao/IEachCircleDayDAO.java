package dao;

import java.util.List;

import entity.EachCircleDay;

public interface IEachCircleDayDAO {
	public abstract void save(EachCircleDay item);

	public abstract void delete(EachCircleDay item);

	public abstract EachCircleDay findById(int id);

	public abstract List<EachCircleDay> findAll();
	
	public abstract void update(EachCircleDay item);
}
