package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import entity.EachCircleDay;

public class EachCircleDayDAO implements IEachCircleDayDAO {
	SessionFactory sessionFactory;
	
	@Override
	public void save(EachCircleDay item) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(item);
	}

	@Override
	public void delete(EachCircleDay item) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(item);
	}

	@Override
	public EachCircleDay findById(int id) {
		// TODO Auto-generated method stub
		return (EachCircleDay)sessionFactory.getCurrentSession().get(EachCircleDay.class, id);
	}

	@Override
	public List<EachCircleDay> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from EachCircleDay").list();
	}

	@Override
	public void update(EachCircleDay item) {
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
