package dao;

import java.util.List;

import entity.MP1004;

public interface IMP1004DAO {
	public abstract void save(MP1004 mp1004);

	public abstract void delete(MP1004 mp1004);

	public abstract MP1004 findById(String employeeNum);

	public abstract List<MP1004> findAll();
	
	public abstract List<MP1004> findAll2(String employeeNum, String employeeName);

	public abstract void update(MP1004 mp1004);
}
