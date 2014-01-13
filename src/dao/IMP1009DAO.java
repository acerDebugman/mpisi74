package dao;

import java.util.List;
import entity.MP1009;

public interface IMP1009DAO {
	public abstract void save(MP1009 mp1009);

	public abstract void delete(MP1009 mp1009);

	public abstract MP1009 findById(int _key);

	public abstract List<MP1009> findAll();

	public abstract void update(MP1009 mp1009);
	
	public abstract List<MP1009> findByProperty(String name, String value);
	
	public abstract List<MP1009> findByProperty(String employeeNum,String employeeName,String documentName, int PAGE_NUM, int PAGE_COUNT);
}
