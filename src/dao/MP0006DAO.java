package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import entity.MP0006;

public class MP0006DAO  implements IMP0006DAO {
	private SessionFactory sessionFactory;
	
	public void save(MP0006 mp0006) {
		if (mp0006 != null) {
			sessionFactory.getCurrentSession().save(mp0006);
		}
	}

	public void delete(MP0006 mp0006) {
		sessionFactory.getCurrentSession().delete(mp0006);
	}

	public MP0006 findById(int seq) {
		return (MP0006) sessionFactory.getCurrentSession().get("entity.MP0006", seq);
	}

	@SuppressWarnings("unchecked")
	public List<MP0006> findAll() {
//		return getHibernateTemplate().find("from MP0006");
		return sessionFactory.getCurrentSession().createQuery("from MP0006").list();
	}

	public void update(MP0006 mp0006) {
		sessionFactory.getCurrentSession().update(mp0006);
	}
	
	@SuppressWarnings("unchecked")
	public List<MP0006> findByProperty(String name, String value){
		try{
//			String queryString = " from MP0006 as m6 where m6." + name + " = ?";
//			return getHibernateTemplate().find(queryString, value);
			String queryString = " from MP0006 as m6 where m6." + name + " = '" + value + "'";
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

