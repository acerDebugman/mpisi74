package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import entity.StandardWorkTime;

public class StandardWorkTimeDAO implements IStandardWorkTimeDAO {
	SessionFactory sessionFactory;
	
	@Override
	public void save(StandardWorkTime item) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(item);
	}

	@Override
	public void delete(StandardWorkTime item) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(item);
	}

	@Override
	public StandardWorkTime findById(int id) {
		// TODO Auto-generated method stub
		return (StandardWorkTime)sessionFactory.getCurrentSession().get(StandardWorkTime.class, id);
	}

	@Override
	public List<StandardWorkTime> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from StandardWorkTime").list();
	}

	@Override
	public void update(StandardWorkTime item) {
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
