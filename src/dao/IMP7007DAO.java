package dao;

import java.util.List;
import java.util.Map;
import entity.MP7007;

public interface IMP7007DAO {
	public abstract void save(MP7007 mp7007);

	public abstract void delete(MP7007 mp7007);

	public abstract MP7007 findById(String key);

	public abstract List<MP7007> findAll();

	public abstract void update(MP7007 mp7007);

	public abstract List<MP7007> findByProperty(String name, String value);
	
	public abstract List<MP7007> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT);
	
	public abstract int getRowCountByPage(Map<String, String> propertyMap) ;
	
	public abstract List<MP7007> findDataBySelfDefined(String search,String order, int pageNum, int pageCount);
}