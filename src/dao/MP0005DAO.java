package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import entity.MP0005;

public class MP0005DAO  implements IMP0005DAO {
	private SessionFactory sessionFactory;
	
	public void save(MP0005 mp0005) {
		if (mp0005 != null) {
			sessionFactory.getCurrentSession().save(mp0005);
		}
	}

	public void delete(MP0005 mp0005) {
		sessionFactory.getCurrentSession().delete(mp0005);
	}

	public MP0005 findById(int seq) {
		return (MP0005) sessionFactory.getCurrentSession().get("entity.MP0005", seq);
	}

	@SuppressWarnings("unchecked")
	public List<MP0005> findAll() {
//		return getHibernateTemplate().find("from MP0005");
		return sessionFactory.getCurrentSession().createQuery("from MP0005").list();
	}

	public void update(MP0005 mp0005) {
		sessionFactory.getCurrentSession().update(mp0005);
	}
	
	@SuppressWarnings("unchecked")
	public List<MP0005> findByProperty(String name, String value){
		try{
			String queryString = " from MP0005 as bulletin where bulletin." + name + " = '" + value + "'";
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
