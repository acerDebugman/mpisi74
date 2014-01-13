package service;

import java.util.List;
import java.util.Map;

import entity.MP7008;

public interface IMP7008Service {
	public abstract void save(MP7008 mp7008);

	public abstract void delete(MP7008 mp7008);

	public abstract MP7008 findById(String key);

	public abstract List<MP7008> findAll();

	public abstract void update(MP7008 mp7008);
	
	public abstract void executeStatement(String statement);
	
	public abstract List<MP7008> findByProperty(String name, String value);
	
	public abstract List<MP7008> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT);
	
	public abstract int getRowCountByPage(Map<String, String> propertyMap) ;
	
	public abstract List<MP7008> findDataBySelfDefined(String search,String order, int pageNum, int pageCount);
}
