package dao;

import java.util.List;

import entity.MP1006;

public interface IMP1006DAO {
	public abstract void save(MP1006 mp1006);

	public abstract void delete(MP1006 mp1006);

	public abstract MP1006 findById(String employeeId);

	public abstract List<MP1006> findAll();

	public abstract void update(MP1006 mp1006);
}
