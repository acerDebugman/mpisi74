package com.joe.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.joe.model.MP1005;

public class MP1005DAO extends HibernateDaoSupport implements IMP1005DAO {

	public void save(MP1005 mp1005) {
		if (mp1005 != null) {
			getHibernateTemplate().save(mp1005);
		}
	}

	public void delete(MP1005 mp1005) {
		getHibernateTemplate().delete(mp1005);
	}

	public MP1005 findById(int seq) {
		return (MP1005) getHibernateTemplate().get("entity.MP1005", seq);
	}

	@SuppressWarnings("unchecked")
	public List<MP1005> findAll() {
		return getHibernateTemplate().find("from MP1005");
	}

	public void update(MP1005 mp1005) {
		getHibernateTemplate().update(mp1005);
	}

	@SuppressWarnings("unchecked")
	public List<MP1005> findByProperty(String name, String value) {
		try{
			String queryString = " from MP1005 as mp5 where mp5." + name + " = ?";
			return getHibernateTemplate().find(queryString, value);
		}catch(RuntimeException ex){
			throw ex;
		}
	}
}