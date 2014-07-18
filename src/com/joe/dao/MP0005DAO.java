package com.joe.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.joe.model.MP0005;

public class MP0005DAO extends HibernateDaoSupport implements IMP0005DAO {
	
	public void save(MP0005 mp0005) {
		if (mp0005 != null) {
			getHibernateTemplate().save(mp0005);
		}
	}

	public void delete(MP0005 mp0005) {
		getHibernateTemplate().delete(mp0005);
	}

	public MP0005 findById(int seq) {
		return (MP0005) getHibernateTemplate().get("entity.MP0005", seq);
	}

	@SuppressWarnings("unchecked")
	public List<MP0005> findAll() {
		return getHibernateTemplate().find("from MP0005");
	}

	public void update(MP0005 mp0005) {
		getHibernateTemplate().update(mp0005);
	}
	
	@SuppressWarnings("unchecked")
	public List<MP0005> findByProperty(String name, String value){
		try{
			String queryString = " from MP0005 as bulletin where bulletin." + name + " = ?";
			return getHibernateTemplate().find(queryString, value);
		}catch(RuntimeException ex){
			throw ex;
		}
	}
}
