package com.joe.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.joe.model.MP0009;

public class MP0009DAO extends HibernateDaoSupport implements IMP0009DAO {
	public void save(MP0009 mp0009) {
		if (mp0009 != null) {
			getHibernateTemplate().save(mp0009);
		}
	}

	public void delete(MP0009 mp0009) {
		getHibernateTemplate().delete(mp0009);
	}

	public MP0009 findById(int seq) {
		return (MP0009) getHibernateTemplate().get("entity.MP0009", seq);
	}

	@SuppressWarnings("unchecked")
	public List<MP0009> findAll() {
		return getHibernateTemplate().find("from MP0009 where 1=1 and MP0009_POSITION_STATUS = '1' ");
	}

	public void update(MP0009 mp0009) {
		getHibernateTemplate().update(mp0009);
	}
	
	@SuppressWarnings("unchecked")
	public List<MP0009> findByProperty(String name, String value){
		try{
			String queryString = " from MP0009 as mp9 where mp9." + name + " = ?";
			return getHibernateTemplate().find(queryString, value);
		}catch(RuntimeException ex){
			throw ex;
		}
	}
}
