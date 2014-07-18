package com.joe.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.joe.model.AC0004;

public class AC0004DAO extends HibernateDaoSupport implements IAC0004DAO {
	
	public void save(AC0004 ac0004) {
		if (ac0004 != null) {
			getHibernateTemplate().save(ac0004);
		}
	}

	public void delete(AC0004 ac0004) {
		getHibernateTemplate().delete(ac0004);
	}

	public AC0004 findById(int seq) {
		return (AC0004) getHibernateTemplate().get("entity.AC0004", seq);
	}

	@SuppressWarnings("unchecked")
	public List<AC0004> findAll() {
		return getHibernateTemplate().find("from AC0004");
	}

	public void update(AC0004 ac0004) {
		getHibernateTemplate().update(ac0004);
	}
	
	@SuppressWarnings("unchecked")
	public List<AC0004> findByProperty(String name, String value){
		try{
			String queryString = " from AC0004 as ac04 where ac04." + name + " = ?";
			return getHibernateTemplate().find(queryString, value);
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<AC0004> getFunctionOperationData(String functionNum, String operationNum){
		try{
			String queryString = " from AC0004 as ac04 where ac04.AC0004_FUN_NUM = '" + functionNum + "' and ac04.AC0004_OPT_NUM = '" + operationNum +"' ";
			return getHibernateTemplate().find(queryString);
		}catch(RuntimeException ex){
			throw ex;
		}
	}
}