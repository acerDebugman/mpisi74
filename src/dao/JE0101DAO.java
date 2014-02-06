package dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.JE0101;

public class JE0101DAO extends HibernateDaoSupport implements IJE0101DAO {

	@Override
	public void save(JE0101 je0101) {
		// TODO Auto-generated method stub
		if(null != je0101){
			getHibernateTemplate().save(je0101);
		}
	}

	@Override
	public void delete(JE0101 je0101) {
		// TODO Auto-generated method stub
		if(null != je0101){
			getHibernateTemplate().delete(je0101);
		}
	}

	@Override
	public JE0101 findByKey(String key) {
		// TODO Auto-generated method stub
		return (JE0101)getHibernateTemplate().get("entity.JE0101", key);
	}

	@Override
	public JE0101 findByValue(String value) {
		// TODO Auto-generated method stub
		//return (JE0101)getHibernateTemplate().get("entity.JE0101", value);
		return (JE0101)getHibernateTemplate().find(" from JE0101 where JE0101_VALUE='" + value + "'");
	}
	
	@Override
	public JE0101 findByType(String type) {
		// TODO Auto-generated method stub
		//return (JE0101)getHibernateTemplate().get("entity.JE0101", value);
		return (JE0101)getHibernateTemplate().find(" from JE0101 where JE0101_VALUE='" + type + "'");
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public List<JE0101> findAll() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from JE0101");
	}

	@Override
	public void update(JE0101 je0101) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(je0101);
	}

}
