package service;

import java.util.List;

import dao.IWorkTimePatternDAO;
import entity.WorkTimePattern;

public class WorkTimePatternService implements IWorkTimePatternService {
	private IWorkTimePatternDAO dao;
	
	@Override
	public void save(WorkTimePattern item) {
		// TODO Auto-generated method stub
		dao.save(item);
	}

	@Override
	public void delete(WorkTimePattern item) {
		// TODO Auto-generated method stub
		dao.delete(item);
	}

	@Override
	public WorkTimePattern findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<WorkTimePattern> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void update(WorkTimePattern item) {
		// TODO Auto-generated method stub
		dao.update(item);
	}

	public IWorkTimePatternDAO getDao() {
		return dao;
	}

	public void setDao(IWorkTimePatternDAO dao) {
		this.dao = dao;
	}

}
