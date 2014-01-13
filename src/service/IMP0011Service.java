package service;

import java.util.List;

import entity.MP0011;

public interface IMP0011Service {
	public abstract void save(MP0011 mp0011);

	public abstract void delete(MP0011 mp0011);

	public abstract MP0011 findById(String _key);

	public abstract List<MP0011> findAll();

	public abstract void update(MP0011 mp0011);

	public abstract List<MP0011> findByProperty(String name, String value);

	public abstract List<MP0011> findByProperty(String employeeNum,String employeeName,String loginTimeFrom, String loginTimeTo, int PAGE_NUM, int PAGE_COUNT);
}
