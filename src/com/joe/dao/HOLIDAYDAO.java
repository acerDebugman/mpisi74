package com.joe.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.joe.model.HOLIDAY;

public class HOLIDAYDAO extends HibernateDaoSupport implements IHOLIDAYDAO {
	public void save(HOLIDAY holiday) {
		if (holiday != null) {
			getHibernateTemplate().save(holiday);
		}
	}

	public void delete(HOLIDAY holiday) {
		getHibernateTemplate().delete(holiday);
	}

	public HOLIDAY findById(int seq) {
		return (HOLIDAY) getHibernateTemplate().get("entity.HOLIDAY", seq);
	}

	@SuppressWarnings("unchecked")
	public List<HOLIDAY> findAll() {
		return getHibernateTemplate().find("from HOLIDAY order by HOLIDAY_DATE desc ");
	}

	public void update(HOLIDAY holiday) {
		getHibernateTemplate().update(holiday);
	}
	
	@SuppressWarnings("unchecked")
	public List<HOLIDAY> findByProperty(String name, String value){
		try{
			String queryString = " from HOLIDAY as m6 where m6." + name + " = ?";
			return getHibernateTemplate().find(queryString, value);
		}catch(RuntimeException ex){
			throw ex;
		}
	}
}
