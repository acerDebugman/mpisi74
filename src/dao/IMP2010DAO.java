package dao;

import java.util.List;
import java.util.Map;

import entity.MP2010;

public interface IMP2010DAO {
	
	public abstract void save(MP2010 mp2010);

	public abstract void delete(MP2010 mp2010);

	public abstract MP2010 findByKey(String key);
	
	public abstract MP2010 findByValue(String value);

	public abstract List<MP2010> findAll();

	public abstract void update(MP2010 mp2010);
	
	public abstract MP2010 findByType(String type);
	
	public abstract List<MP2010> findByColumnName(Map<String, String> columnMap);
	
	public abstract List<MP2010> findByColumnName(Map<String, String> columnMap, String strOrder);

	public abstract int getAllRowsCount();

	public abstract List<MP2010> findByColumnName(Map<String, String> columnMap, String strOrder, boolean pageFlag);
	
	public abstract int getSearchConditionRowsCount(Map<String, String> columnMap);
	
	public abstract void getSubscriberInfo(MP2010 mp2010);
	
	public List<MP2010> findByColumnNames(Map<String, String> columnNames, boolean pageFlag);
	
	public long findTotalSearchCount(Map<String, String> columnNames);
}
