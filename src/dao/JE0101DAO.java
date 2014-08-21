package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import entity.JE0101;

public class JE0101DAO  implements IJE0101DAO {
	private SessionFactory sessionFactory;
	
	
	@Override
	public void save(JE0101 je0101) {
		// TODO Auto-generated method stub
		if(null != je0101){
			sessionFactory.getCurrentSession().save(je0101);
		}
	}

	@Override
	public void delete(JE0101 je0101) {
		// TODO Auto-generated method stub
		if(null != je0101){
			sessionFactory.getCurrentSession().delete(je0101);
		}
	}

	@Override
	public JE0101 findByKey(String key) {
		// TODO Auto-generated method stub
		return (JE0101)sessionFactory.getCurrentSession().get("entity.JE0101", key);
	}

	@Override
	public JE0101 findByValue(String value) {
		// TODO Auto-generated method stub
		//return (JE0101)sessionFactory.getCurrentSession().get("entity.JE0101", value);
//		return (JE0101)getHibernateTemplate().find(" from JE0101 where JE0101_VALUE='" + value + "'");
		return (JE0101)sessionFactory.getCurrentSession().createQuery(" from JE0101 where JE0101_VALUE='" + value + "'");
	}
	
	@Override
	public JE0101 findByType(String type) {
		// TODO Auto-generated method stub
		//return (JE0101)sessionFactory.getCurrentSession().get("entity.JE0101", value);
//		return (JE0101)getHibernateTemplate().find(" from JE0101 where JE0101_VALUE='" + type + "'");
		return (JE0101)sessionFactory.getCurrentSession().createQuery(" from JE0101 where JE0101_VALUE='" + type + "'").list();
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public List<JE0101> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from JE0101").list();
	}

	@Override
	public void update(JE0101 je0101) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(je0101);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
