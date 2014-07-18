package com.joe.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.joe.model.MP0010;

public class MP0010DAO extends HibernateDaoSupport implements IMP0010DAO{
	public void save(MP0010 mp0010) {
		if (mp0010 != null) {
			getHibernateTemplate().save(mp0010);
		}
	}

	public void delete(MP0010 mp0010) {
		getHibernateTemplate().delete(mp0010);
	}

	public MP0010 findById(String _key) {
		return (MP0010) getHibernateTemplate().get("entity.MP0010", _key);
	}

	@SuppressWarnings("unchecked")
	public List<MP0010> findAll() {
		return getHibernateTemplate().find("from MP0010 where 1=1 order by MP0010_DATETIME desc ");
	}

	public void update(MP0010 mp0010) {
		getHibernateTemplate().update(mp0010);
	}
	
	@SuppressWarnings("unchecked")
	public List<MP0010> findByProperty(String name, String value){
		try{
			String queryString = " from MP0010 as mp10 where mp10." + name + " = ?";
			return getHibernateTemplate().find(queryString, value);
		}catch(RuntimeException ex){
			throw ex;
		}
	}
}
