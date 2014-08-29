package service;

import java.util.List;
import java.util.Map;

import entity.MP7002;

public interface IMP7002Service {
	public abstract void save(MP7002 mp7002);

	public abstract void delete(MP7002 mp7002);

	public abstract MP7002 findById(String key);

	public abstract List<MP7002> findAll();

	public abstract void update(MP7002 mp7002);
	
	public abstract List<MP7002> findByProperty(String name, String value);
	
	public abstract List<MP7002> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT);
	
	public abstract int getRowCountByPage(Map<String, String> propertyMap) ;
	
	public abstract List<MP7002> findDataBySelfDefined(String search,String order, int pageNum, int pageCount);
	
	public void evictObj(MP7002 mp72);
}
