package dao;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import entity.MP1003;

public class MP1003DAO extends HibernateDaoSupport implements IMP1003DAO {

	public void save(MP1003 mp1003) {
		if (mp1003 != null) {
			getHibernateTemplate().save(mp1003);
		}
	}

	public void delete(MP1003 mp1003) {
		getHibernateTemplate().delete(mp1003);
	}

	public MP1003 findById(int seq) {
		return (MP1003) getHibernateTemplate().get("entity.MP1003", seq);
	}

	@SuppressWarnings("unchecked")
	public List<MP1003> findAll() {
		return getHibernateTemplate().find("from MP1003");
	}

	public void update(MP1003 mp1003) {
		getHibernateTemplate().update(mp1003);
	}

	@SuppressWarnings("unchecked")
	public List<MP1003> findByProperty(String name, String value) {
		try{
			String queryString = " from MP1003 as mp3 where mp3." + name + " = ?";
			return getHibernateTemplate().find(queryString, value);
		}catch(RuntimeException ex){
			throw ex;
		}
	}
}