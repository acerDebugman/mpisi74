package service;

import java.util.List;
import entity.AC0008;

public interface IAC0008Service {
	public abstract void save(AC0008 ac0008);

	public abstract void delete(AC0008 ac0008);

	public abstract AC0008 findById(String employeeNum);

	public abstract List<AC0008> findAll();
	
	public abstract void update(AC0008 ac0008);
	
	public List<AC0008> findByProperty(String name, String value);
}