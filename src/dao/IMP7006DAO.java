package dao;

import java.util.List;
import java.util.Map;
import entity.MP7006;

public interface IMP7006DAO {
	public abstract void save(MP7006 mp7006);

	public abstract void delete(MP7006 mp7006);

	public abstract MP7006 findById(String key);

	public abstract List<MP7006> findAll();

	public abstract void update(MP7006 mp7006);
	
	public abstract List<MP7006> findByProperty(String name, String value);
	
	public abstract List<MP7006> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT);
	
	public abstract int getRowCountByPage(Map<String, String> propertyMap) ;
	
	public abstract List<MP7006> findDataBySelfDefined(String search,String order, int pageNum, int pageCount);
}