package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import entity.MP1003;

public class MP1003DAO  implements IMP1003DAO {
	private SessionFactory sessionFactory;
	
	public void save(MP1003 mp1003) {
		if (mp1003 != null) {
			sessionFactory.getCurrentSession().save(mp1003);
		}
	}

	public void delete(MP1003 mp1003) {
		sessionFactory.getCurrentSession().delete(mp1003);
	}

	public MP1003 findById(int seq) {
		return (MP1003) sessionFactory.getCurrentSession().get("entity.MP1003", seq);
	}

	@SuppressWarnings("unchecked")
	public List<MP1003> findAll() {
//		return getHibernateTemplate().find("from MP1003");
		return sessionFactory.getCurrentSession().createQuery("from MP1003").list();
	}

	public void update(MP1003 mp1003) {
		sessionFactory.getCurrentSession().update(mp1003);
	}

	@SuppressWarnings("unchecked")
	public List<MP1003> findByProperty(String name, String value) {
		try{
//			String queryString = " from MP1003 as mp3 where mp3." + name + " = ?";
//			return getHibernateTemplate().find(queryString, value);
			String queryString = " from MP1003 as mp3 where mp3." + name + " = '" + value + "'";
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