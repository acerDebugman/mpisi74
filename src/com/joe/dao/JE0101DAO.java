package com.joe.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.joe.model.SYSDICT;

public class JE0101DAO extends HibernateDaoSupport implements IJE0101DAO {

	@Override
	public void save(SYSDICT je0101) {
		// TODO Auto-generated method stub
		if(null != je0101){
			getHibernateTemplate().save(je0101);
		}
	}

	@Override
	public void delete(SYSDICT je0101) {
		// TODO Auto-generated method stub
		if(null != je0101){
			getHibernateTemplate().delete(je0101);
		}
	}

	@Override
	public SYSDICT findByKey(String key) {
		// TODO Auto-generated method stub
		return (SYSDICT)getHibernateTemplate().get("entity.JE0101", key);
	}

	@Override
	public SYSDICT findByValue(String value) {
		// TODO Auto-generated method stub
		//return (JE0101)getHibernateTemplate().get("entity.JE0101", value);
		return (SYSDICT)getHibernateTemplate().find(" from JE0101 where JE0101_VALUE='" + value + "'");
	}
	
	@Override
	public SYSDICT findByType(String type) {
		// TODO Auto-generated method stub
		//return (JE0101)getHibernateTemplate().get("entity.JE0101", value);
		return (SYSDICT)getHibernateTemplate().find(" from JE0101 where JE0101_VALUE='" + type + "'");
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public List<SYSDICT> findAll() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from JE0101");
	}

	@Override
	public void update(SYSDICT je0101) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(je0101);
	}

}
