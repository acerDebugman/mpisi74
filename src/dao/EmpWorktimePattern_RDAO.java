package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import entity.EmpWorkTimePattern_R;

public class EmpWorktimePattern_RDAO implements IEmpWorktimePattern_RDAO {
	SessionFactory sessionFactory;
	
	@Override
	public void save(EmpWorkTimePattern_R item) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(item);
	}

	@Override
	public void delete(EmpWorkTimePattern_R item) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(item);
	}

	@Override
	public EmpWorkTimePattern_R findById(int id) {
		// TODO Auto-generated method stub
		return (EmpWorkTimePattern_R)sessionFactory.getCurrentSession().get(EmpWorkTimePattern_R.class, id);
	}

	@Override
	public List<EmpWorkTimePattern_R> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from EmpWorktimePattern_R").list();
	}

	@Override
	public void update(EmpWorkTimePattern_R item) {
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
