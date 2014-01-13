package dao;

import java.util.List;
import java.util.Map;
import entity.MP7003;

public interface IMP7003DAO {
	public abstract void save(MP7003 mp7003);

	public abstract void delete(MP7003 mp7003);

	public abstract MP7003 findById(String key);

	public abstract List<MP7003> findAll();

	public abstract void update(MP7003 mp7003);
	
	public abstract List<MP7003> findByProperty(String name, String value);
	
	public abstract List<MP7003> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT);
	
	public abstract int getRowCountByPage(Map<String, String> propertyMap) ;
	
	public abstract List<MP7003> findDataBySelfDefined(String search,String order, int pageNum, int pageCount);
}