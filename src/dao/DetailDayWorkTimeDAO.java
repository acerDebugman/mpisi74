package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import entity.DetailDayWorkTime;

public class DetailDayWorkTimeDAO implements IDetailDayWorkTimeDAO {
	SessionFactory sessionFactory;
		
	@Override
	public void save(DetailDayWorkTime item) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(item);
	}

	@Override
	public void delete(DetailDayWorkTime item) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(item);
	}

	@Override
	public DetailDayWorkTime findById(int id) {
		// TODO Auto-generated method stub
		return (DetailDayWorkTime)sessionFactory.getCurrentSession().get(DetailDayWorkTime.class, id);
	}

	@Override
	public List<DetailDayWorkTime> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from DetailDayWorkTime").list();
	}

	@Override
	public void update(DetailDayWorkTime item) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(item);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
