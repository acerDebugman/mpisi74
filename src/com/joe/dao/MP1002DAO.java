package com.joe.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.joe.model.MP1002;

public class MP1002DAO extends HibernateDaoSupport implements IMP1002DAO {

	public void save(MP1002 mp1002) {
		if (mp1002 != null) {
			getHibernateTemplate().save(mp1002);
		}
	}

	public void delete(MP1002 mp1002) {
		getHibernateTemplate().delete(mp1002);
	}

	public MP1002 findById(int seq) {
		return (MP1002) getHibernateTemplate().get("entity.MP1002", seq);
	}

	@SuppressWarnings("unchecked")
	public List<MP1002> findAll() {
		return getHibernateTemplate().find("from MP1002");
	}

	public void update(MP1002 mp1002) {
		getHibernateTemplate().update(mp1002);
	}

	@SuppressWarnings("unchecked")
	public List<MP1002> findByProperty(String name, String value) {
		try{
			String queryString = " from MP1002 as mp2 where mp2." + name + " = ?";
			return getHibernateTemplate().find(queryString, value);
		}catch(RuntimeException ex){
			throw ex;
		}
	}

}