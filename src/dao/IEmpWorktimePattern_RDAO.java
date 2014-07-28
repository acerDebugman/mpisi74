package dao;

import java.util.List;

import entity.EmpWorkTimePattern_R;

public interface IEmpWorktimePattern_RDAO {
	public abstract void save(EmpWorkTimePattern_R item);

	public abstract void delete(EmpWorkTimePattern_R item);

	public abstract EmpWorkTimePattern_R findById(int id);

	public abstract List<EmpWorkTimePattern_R> findAll();
	
	public abstract void update(EmpWorkTimePattern_R item);
}
