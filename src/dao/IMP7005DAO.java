package dao;

import java.util.List;
import java.util.Map;
import entity.MP7005;

public interface IMP7005DAO {
	public abstract void save(MP7005 mp7005);

	public abstract void delete(MP7005 mp7005);

	public abstract MP7005 findById(String key);

	public abstract List<MP7005> findAll();

	public abstract void update(MP7005 mp7005);
	
	public abstract void updateDataBySelf(List<String> list1, List<String> list2, List<String> list3, List<String> list4, List<String> list5, String key);
	
	public abstract List<MP7005> findByProperty(String name, String value);
	
	public abstract List<MP7005> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT);
	
	public abstract int getRowCountByPage(Map<String, String> propertyMap) ;
	
	public abstract List<MP7005> findDataBySelfDefined(String search,String order, int pageNum, int pageCount);
}