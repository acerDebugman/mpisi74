package service;

import java.util.Date;
import java.util.List;

import entity.HOLIDAY;

public interface IHOLIDAYService {
	public abstract void save(HOLIDAY holiday);

	public abstract void delete(HOLIDAY holiday);

	public abstract HOLIDAY findById(int seq);

	public abstract List<HOLIDAY> findAll();

	public abstract void update(HOLIDAY holiday);
	
	public abstract List<HOLIDAY> findByProperty(String name, String value);
	
	public abstract boolean isPublicHoliday(Date date);
}
