package service;

import java.util.Date;
import java.util.List;

import dao.IStandardWorkTimeDAO;
import entity.MP0010;
import entity.MP1001;
import entity.StandardWorkTime;

public class StandardWorkTimeService implements IStandardWorkTimeService {
	private IStandardWorkTimeDAO dao;
	
	@Override
	public void save(StandardWorkTime item) {
		// TODO Auto-generated method stub
		dao.save(item);
	}

	@Override
	public void delete(StandardWorkTime item) {
		// TODO Auto-generated method stub
		dao.delete(item);
	}

	@Override
	public StandardWorkTime findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<StandardWorkTime> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void update(StandardWorkTime item) {
		// TODO Auto-generated method stub
		dao.update(item);
	}

	public IStandardWorkTimeDAO getDao() {
		return dao;
	}

	public void setDao(IStandardWorkTimeDAO dao) {
		this.dao = dao;
	}

	public List<MP0010> fetchAllPatternSpecialDays(StandardWorkTime item){
		return this.dao.fetchAllPatternSpecialDays(item);
	}
	
	public StandardWorkTime fetchSpecificDayWorkTime(MP1001 emp, Date date){
		return dao.fetchSpecificDayWorkTime(emp, date);
	}
}