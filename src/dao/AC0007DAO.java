package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import entity.AC0007;

public class AC0007DAO  implements IAC0007DAO {
	private SessionFactory sessionFactory;
	
	public void save(AC0007 ac0007) {
		if (ac0007 != null) {
			sessionFactory.getCurrentSession().save(ac0007);
		}
	}

	public void delete(AC0007 ac0007) {
		sessionFactory.getCurrentSession().delete(ac0007);
	}

	public AC0007 findById(String seq) {
		return (AC0007) sessionFactory.getCurrentSession().get("entity.AC0007", seq);
	}

	@SuppressWarnings("unchecked")
	public List<AC0007> findAll() {
//		return getHibernateTemplate().find("from AC0007");
		return sessionFactory.getCurrentSession().createQuery("from AC0007").list();
	}

	public void update(AC0007 ac0007) {
		sessionFactory.getCurrentSession().update(ac0007);
	}
	
	@SuppressWarnings("unchecked")
	public List<AC0007> findByProperty(String name, String value){
		try{
			String queryString = " from AC0007 as ac07 where ac07." + name + " = '" + value + "'";
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