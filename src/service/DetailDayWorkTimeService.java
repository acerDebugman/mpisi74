package service;

import java.util.List;

import dao.IDetailDayWorkTimeDAO;
import entity.DetailDayWorkTime;

public class DetailDayWorkTimeService implements IDetailDayWorkTimeService {
	private IDetailDayWorkTimeDAO dao;
	
	@Override
	public void save(DetailDayWorkTime item) {
		// TODO Auto-generated method stub
		dao.save(item);
	}

	@Override
	public void delete(DetailDayWorkTime item) {
		// TODO Auto-generated method stub
		dao.delete(item);
	}

	@Override
	public DetailDayWorkTime findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<DetailDayWorkTime> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void update(DetailDayWorkTime item) {
		// TODO Auto-generated method stub
		dao.update(item);
	}

	public IDetailDayWorkTimeDAO getDao() {
		return dao;
	}

	public void setDao(IDetailDayWorkTimeDAO dao) {
		this.dao = dao;
	}

}
