package service;

import java.util.List;
import java.util.Map;

import entity.MP7001;

public interface IMP7001Service {
	public abstract void save(MP7001 mp7001);

	public abstract void delete(MP7001 mp7001);

	public abstract MP7001 findById(String key);

	public abstract List<MP7001> findAll();

	public abstract void update(MP7001 mp7001);
	
	public abstract List<MP7001> findByProperty(String name, String value);
	
	public abstract List<MP7001> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT);
	
	public abstract int getRowCountByPage(Map<String, String> propertyMap) ;
	
	public abstract List<MP7001> findDataBySelfDefined(String search,String order, int pageNum, int pageCount);
}
