package dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.JE0202;

public class JE0202DAO extends HibernateDaoSupport implements IJE0202DAO {

	@Override
	public void save(JE0202 je0202) {
		// TODO Auto-generated method stub
		if(null != je0202){
			getHibernateTemplate().save(je0202);
		}
	}

	@Override
	public void delete(JE0202 je0202) {
		// TODO Auto-generated method stub
		if(null != je0202){
			getHibernateTemplate().delete(je0202);
		}
	}

	@Override
	public JE0202 findByKey(String key) {
		// TODO Auto-generated method stub
		return (JE0202)getHibernateTemplate().get("entity.JE0202", key);
	}

	@Override
	public JE0202 findByValue(String value) {
		// TODO Auto-generated method stub
		//return (JE0202)getHibernateTemplate().get("entity.JE0202", value);
		return (JE0202)getHibernateTemplate().find(" from JE0202 where JE0202_VALUE='" + value + "'");
	}
	
	@Override
	public JE0202 findByType(String type) {
		// TODO Auto-generated method stub
		//return (JE0202)getHibernateTemplate().get("entity.JE0202", value);
		return (JE0202)getHibernateTemplate().find(" from JE0202 where JE0202_VALUE='" + type + "'");
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public List<JE0202> findAll() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from JE0202");
	}

	@Override
	public void update(JE0202 je0202) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(je0202);
	}

}
