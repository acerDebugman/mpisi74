package service;

import java.util.List;

import entity.MP1001;
import entity.MP2002;

public interface IMP2002Service {
	public abstract void save(MP2002 mp2002);

	public abstract void delete(MP2002 mp2002);

	public abstract MP2002 findById(String employeeNum);

	public abstract List<MP2002> findAll();

	public abstract void update(MP2002 mp2002);
	
	public abstract List<MP2002> findByProperty(String name, String value);
	
	public abstract List<MP2002> findByPropertysPage(MP1001 employeeData, int PAGE_NUM, int PAGE_COUNT);
	
	public abstract int getRowCountByPage(MP1001 employeeData);
	
	public abstract List<MP2002> findDataBySelfDefined(String search,String order, int pageNum, int pageCount);
}