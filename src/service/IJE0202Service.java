package service;

import java.util.List;
import java.util.Map;

import common.PageBean;

import entity.JE0202;

public interface IJE0202Service {
	public abstract void save(JE0202 je0202);

	public abstract void delete(JE0202 je0202);

	public abstract JE0202 findByKey(String key);
	
	public abstract JE0202 findByValue(String value);

	public abstract List<JE0202> findAll();

	public abstract void update(JE0202 je0202);
	
	public abstract JE0202 findByType(String type);
	
	public abstract List<JE0202> findByColumnName(Map<String, String> columnMap);
	
	public abstract List<JE0202> findByColumnName(Map<String, String> columnMap, String strOrder);
	
	public abstract int getAllRowsCount();
	
	public abstract List<JE0202> findByColumnName(Map<String, String> columnMap, String strOrder, boolean pageFlag);
	
	public abstract PageBean queryForPage(Map<String, String> pageProperty);
	
	public abstract void getSubscriberInfo(JE0202 je0202);
}
