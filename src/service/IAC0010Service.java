package service;

import java.util.List;

import entity.AC0010;

public interface IAC0010Service {
	public abstract void save(AC0010 ac0010);

	public abstract void delete(AC0010 ac0010);

	public abstract AC0010 findById(String employeeNum);

	public abstract List<AC0010> findAll();
	
	public abstract void update(AC0010 ac0010);
	
	public List<AC0010> findByProperty(String name, String value);
	// 系统权限检查
	public abstract boolean systemAccessCheck(String _empNum,String _systemNum);
}
