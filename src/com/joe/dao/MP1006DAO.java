package com.joe.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.joe.model.MP1006;

public class MP1006DAO extends HibernateDaoSupport implements IMP1006DAO {

	public void save(MP1006 mp1006) {
		if (mp1006 != null) {
			getHibernateTemplate().save(mp1006);
		}
	}

	public void delete(MP1006 mp1006) {
		getHibernateTemplate().delete(mp1006);
	}

	public MP1006 findById(String employeeNum) {
		return (MP1006) getHibernateTemplate().get("entity.MP1006", employeeNum);
	}

	@SuppressWarnings("unchecked")
	public List<MP1006> findAll() {
		return getHibernateTemplate().find("from MP1006");
	}

	public void update(MP1006 mp1006) {
		getHibernateTemplate().update(mp1006);
	}

}