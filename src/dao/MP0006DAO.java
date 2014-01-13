package dao;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import entity.MP0006;

public class MP0006DAO extends HibernateDaoSupport implements IMP0006DAO {
	
	public void save(MP0006 mp0006) {
		if (mp0006 != null) {
			getHibernateTemplate().save(mp0006);
		}
	}

	public void delete(MP0006 mp0006) {
		getHibernateTemplate().delete(mp0006);
	}

	public MP0006 findById(int seq) {
		return (MP0006) getHibernateTemplate().get("entity.MP0006", seq);
	}

	@SuppressWarnings("unchecked")
	public List<MP0006> findAll() {
		return getHibernateTemplate().find("from MP0006");
	}

	public void update(MP0006 mp0006) {
		getHibernateTemplate().update(mp0006);
	}
	
	@SuppressWarnings("unchecked")
	public List<MP0006> findByProperty(String name, String value){
		try{
			String queryString = " from MP0006 as m6 where m6." + name + " = ?";
			return getHibernateTemplate().find(queryString, value);
		}catch(RuntimeException ex){
			throw ex;
		}
	}
}

