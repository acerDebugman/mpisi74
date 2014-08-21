package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import entity.MP1002;

public class MP1002DAO  implements IMP1002DAO {
	private SessionFactory sessionFactory;
	
	public void save(MP1002 mp1002) {
		if (mp1002 != null) {
			sessionFactory.getCurrentSession().save(mp1002);
		}
	}

	public void delete(MP1002 mp1002) {
		sessionFactory.getCurrentSession().delete(mp1002);
	}

	public MP1002 findById(int seq) {
		return (MP1002) sessionFactory.getCurrentSession().get("entity.MP1002", seq);
	}

	@SuppressWarnings("unchecked")
	public List<MP1002> findAll() {
//		return getHibernateTemplate().find("from MP1002");
		return sessionFactory.getCurrentSession().createQuery("from MP1002").list();
	}

	public void update(MP1002 mp1002) {
		sessionFactory.getCurrentSession().update(mp1002);
	}

	@SuppressWarnings("unchecked")
	public List<MP1002> findByProperty(String name, String value) {
		try{
//			String queryString = " from MP1002 as mp2 where mp2." + name + " = ?";
//			return getHibernateTemplate().find(queryString, value);
			String queryString = " from MP1002 as mp2 where mp2." + name + " = '" + value + "'";
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