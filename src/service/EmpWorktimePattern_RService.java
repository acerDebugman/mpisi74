package service;

import java.util.List;

import dao.IEmpWorkTimePattern_RDAO;
import entity.EmpWorkTimePattern_R;

public class EmpWorkTimePattern_RService implements IEmpWorkTimePattern_RService{
	private IEmpWorkTimePattern_RDAO dao;
	
	@Override
	public void save(EmpWorkTimePattern_R item) {
		// TODO Auto-generated method stub
		dao.save(item);
	}

	@Override
	public void delete(EmpWorkTimePattern_R item) {
		// TODO Auto-generated method stub
		dao.delete(item);
	}

	@Override
	public EmpWorkTimePattern_R findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<EmpWorkTimePattern_R> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void update(EmpWorkTimePattern_R item) {
		// TODO Auto-generated method stub
		dao.update(item);
	}

	public IEmpWorkTimePattern_RDAO getDao() {
		return dao;
	}

	public void setDao(IEmpWorkTimePattern_RDAO dao) {
		this.dao = dao;
	}

}
