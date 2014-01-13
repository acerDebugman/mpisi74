package dao;

import java.util.List;

import entity.MP1007;

public interface IMP1007DAO {
	public abstract void save(MP1007 mp1007);

	public abstract void delete(MP1007 mp1007);

	public abstract MP1007 findById(String employeeId);

	public abstract List<MP1007> findAll();

	public abstract void update(MP1007 mp1007);
}
