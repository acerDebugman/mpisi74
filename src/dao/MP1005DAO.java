package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import entity.MP1005;

public class MP1005DAO  implements IMP1005DAO {
	private SessionFactory sessionFactory;
	
	public void save(MP1005 mp1005) {
		if (mp1005 != null) {
			sessionFactory.getCurrentSession().save(mp1005);
		}
	}

	public void delete(MP1005 mp1005) {
		sessionFactory.getCurrentSession().delete(mp1005);
	}

	public MP1005 findById(int seq) {
		return (MP1005) sessionFactory.getCurrentSession().get("entity.MP1005", seq);
	}

	@SuppressWarnings("unchecked")
	public List<MP1005> findAll() {
//		return getHibernateTemplate().find("from MP1005");
		return sessionFactory.getCurrentSession().createQuery("from MP1005").list();
	}

	public void update(MP1005 mp1005) {
		sessionFactory.getCurrentSession().update(mp1005);
	}

	@SuppressWarnings("unchecked")
	public List<MP1005> findByProperty(String name, String value) {
		try{
//			String queryString = " from MP1005 as mp5 where mp5." + name + " = ?";
//			return getHibernateTemplate().find(queryString, value);
			String queryString = " from MP1005 as mp5 where mp5." + name + " = '" + value + "'";
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