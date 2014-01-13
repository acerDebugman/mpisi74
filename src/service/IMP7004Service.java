package service;

import java.util.List;
import java.util.Map;

import entity.MP7004;

public interface IMP7004Service {
	public abstract void save(MP7004 mp7004);

	public abstract void delete(MP7004 mp7004);

	public abstract MP7004 findById(String key);

	public abstract List<MP7004> findAll();

	public abstract void update(MP7004 mp7004);
	
	public abstract List<MP7004> findByProperty(String name, String value);
	
	public abstract List<MP7004> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT);
	
	public abstract int getRowCountByPage(Map<String, String> propertyMap) ;
	
	public abstract List<MP7004> findDataBySelfDefined(String search,String order, int pageNum, int pageCount);
	
	public abstract List<MP7004> getBarCHart1(String _from, String _to);
	public abstract List<MP7004> getBarCHart2(String _from, String _to, String department);
	public abstract List<MP7004> getLineCHart3(String _from, String _to, String department);
	public abstract List<MP7004> getLineCHart4(String _from, String _to, String department, String empNum);
}
