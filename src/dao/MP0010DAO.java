package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import entity.MP0010;

public class MP0010DAO  implements IMP0010DAO{
	private SessionFactory sessionFactory;
	
	
	public void save(MP0010 mp0010) {
		if (mp0010 != null) {
			sessionFactory.getCurrentSession().save(mp0010);
		}
	}

	public void delete(MP0010 mp0010) {
		sessionFactory.getCurrentSession().delete(mp0010);
	}

	public MP0010 findById(String _key) {
		return (MP0010) sessionFactory.getCurrentSession().get("entity.MP0010", _key);
	}

	@SuppressWarnings("unchecked")
	public List<MP0010> findAll() {
//		return getHibernateTemplate().find("from MP0010 where 1=1 order by MP0010_DATETIME desc ");
		return sessionFactory.getCurrentSession().createQuery("from MP0010 where 1=1 order by MP0010_DATETIME desc ").list();
	}

	public void update(MP0010 mp0010) {
		sessionFactory.getCurrentSession().update(mp0010);
	}
	
	@SuppressWarnings("unchecked")
	public List<MP0010> findByProperty(String name, String value){
		try{
//			String queryString = " from MP0010 as mp10 where mp10." + name + " = ?";
//			return getHibernateTemplate().find(queryString, value);
			String queryString = " from MP0010 as mp10 where mp10." + name + " = '" + value + "'";
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
