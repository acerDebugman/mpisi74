package service;

import java.util.List;
import entity.AC0004;

public interface IAC0004Service {
	public abstract void save(AC0004 ac0004);

	public abstract void delete(AC0004 ac0004);

	public abstract AC0004 findById(int seq);

	public abstract List<AC0004> findAll();
	
	public abstract void update(AC0004 ac0004);
	
	public List<AC0004> findByProperty(String name, String value);
	
	public abstract List<AC0004> getFunctionOperationData(String functionNum, String operationNum);
}