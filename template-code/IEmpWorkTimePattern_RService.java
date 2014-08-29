package service;

import java.util.List;
import java.util.Map;

import common.PageBean;

import entity.EmpWorkTimePattern_R;

public interface IEmpWorkTimePattern_RService {
	public abstract void save(EmpWorkTimePattern_R item);

	public abstract void delete(EmpWorkTimePattern_R item);

	public abstract EmpWorkTimePattern_R findById(int id);

	public abstract List<EmpWorkTimePattern_R> findAll();
	
	public abstract void update(EmpWorkTimePattern_R item);
	
	public List<EmpWorkTimePattern_R> findByProperties(Map<String, String> map);
	
	public PageBean queryForPage(Map<String, String> pageProperty);
}