package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import entity.AC0008;

public class AC0008DAO  implements IAC0008DAO {
	private SessionFactory sessionFactory;
	
	public void save(AC0008 ac0008) {
		if (ac0008 != null) {
			sessionFactory.getCurrentSession().save(ac0008);
		}
	}

	public void delete(AC0008 ac0008) {
		sessionFactory.getCurrentSession().delete(ac0008);
	}

	public AC0008 findById(String seq) {
		return (AC0008) sessionFactory.getCurrentSession().get("entity.AC0008", seq);
	}

	@SuppressWarnings("unchecked")
	public List<AC0008> findAll() {
//		return getHibernateTemplate().find("from AC0008");
		return sessionFactory.getCurrentSession().createQuery("from AC0008").list();
	}

	public void update(AC0008 ac0008) {
		sessionFactory.getCurrentSession().update(ac0008);
	}
	
	@SuppressWarnings("unchecked")
	public List<AC0008> findByProperty(String name, String value){
		try{
			String queryString = " from AC0008 as ac08 where ac08." + name + " = '" + value + "'";
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