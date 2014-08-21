package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import entity.MP0009;

public class MP0009DAO  implements IMP0009DAO {
	private SessionFactory sessionFactory;
	
	public void save(MP0009 mp0009) {
		if (mp0009 != null) {
			sessionFactory.getCurrentSession().save(mp0009);
		}
	}

	public void delete(MP0009 mp0009) {
		sessionFactory.getCurrentSession().delete(mp0009);
	}

	public MP0009 findById(int seq) {
		return (MP0009) sessionFactory.getCurrentSession().get("entity.MP0009", seq);
	}

	@SuppressWarnings("unchecked")
	public List<MP0009> findAll() {
//		return getHibernateTemplate().find("from MP0009 where 1=1 and MP0009_POSITION_STATUS = '1' ");
		return sessionFactory.getCurrentSession().createQuery("from MP0009 where 1=1 and MP0009_POSITION_STATUS = '1' ").list();
	}

	public void update(MP0009 mp0009) {
		sessionFactory.getCurrentSession().update(mp0009);
	}
	
	@SuppressWarnings("unchecked")
	public List<MP0009> findByProperty(String name, String value){
		try{
			String queryString = " from MP0009 as mp9 where mp9." + name + " = '" + value + "'";
//			return getHibernateTemplate().find(queryString, value);
			return sessionFactory.getCurrentSession().createQuery(queryString).list();
		}catch(RuntimeException ex){
			throw ex;
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
