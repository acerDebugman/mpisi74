package service;

import java.util.List;

import dao.IEachCircleDayDAO;
import entity.EachCircleDay;

public class EachCircleDayService implements IEachCircleDayService {
	private IEachCircleDayDAO dao;
	
	@Override
	public void save(EachCircleDay item) {
		// TODO Auto-generated method stub
		dao.save(item);
	}

	@Override
	public void delete(EachCircleDay item) {
		// TODO Auto-generated method stub
		dao.delete(item);
	}

	@Override
	public EachCircleDay findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<EachCircleDay> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void update(EachCircleDay item) {
		// TODO Auto-generated method stub
		dao.update(item);
	}

	public IEachCircleDayDAO getDao() {
		return dao;
	}

	public void setDao(IEachCircleDayDAO dao) {
		this.dao = dao;
	}

}
