package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import entity.AC0004;

public class AC0004DAO  implements IAC0004DAO {
	private SessionFactory sessionFactory;
	
	
	
	public void save(AC0004 ac0004) {
		if (ac0004 != null) {
			sessionFactory.getCurrentSession().save(ac0004);
		}
	}

	public void delete(AC0004 ac0004) {
		sessionFactory.getCurrentSession().delete(ac0004);
	}

	public AC0004 findById(int seq) {
		return (AC0004) sessionFactory.getCurrentSession().get("entity.AC0004", seq);
	}

	@SuppressWarnings("unchecked")
	public List<AC0004> findAll() {
//		return getHibernateTemplate().find("from AC0004");
		return sessionFactory.getCurrentSession().createQuery("from AC0004").list();
	}

	public void update(AC0004 ac0004) {
		sessionFactory.getCurrentSession().update(ac0004);
	}
	
	@SuppressWarnings("unchecked")
	public List<AC0004> findByProperty(String name, String value){
		try{
//			String queryString = " from AC0004 as ac04 where ac04." + name + " = ?";
			String queryString = " from AC0004 as ac04 where ac04." + name + " = '" + value +"'";
//			return getHibernateTemplate().find(queryString, value);
			return sessionFactory.getCurrentSession().createQuery(queryString).list();
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<AC0004> getFunctionOperationData(String functionNum, String operationNum){
		try{
			String queryString = " from AC0004 as ac04 where ac04.AC0004_FUN_NUM = '" + functionNum + "' and ac04.AC0004_OPT_NUM = '" + operationNum +"' ";
//			return getHibernateTemplate().find(queryString);
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