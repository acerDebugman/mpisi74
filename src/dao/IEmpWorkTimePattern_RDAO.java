package dao;

import java.util.List;
import java.util.Map;

import entity.EmpWorkTimePattern_R;

public interface IEmpWorkTimePattern_RDAO {
	public abstract void save(EmpWorkTimePattern_R item);

	public abstract void delete(EmpWorkTimePattern_R item);

	public abstract EmpWorkTimePattern_R findById(int id);

	public abstract List<EmpWorkTimePattern_R> findAll();
	
	public abstract void update(EmpWorkTimePattern_R item);
	
	public List<EmpWorkTimePattern_R> findByProperties(Map<String, String> map) ;
	
	public long findTotalSearchCount(Map<String, String> map);
}
