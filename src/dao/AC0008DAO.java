package dao;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import entity.AC0008;

public class AC0008DAO extends HibernateDaoSupport implements IAC0008DAO {
	
	public void save(AC0008 ac0008) {
		if (ac0008 != null) {
			getHibernateTemplate().save(ac0008);
		}
	}

	public void delete(AC0008 ac0008) {
		getHibernateTemplate().delete(ac0008);
	}

	public AC0008 findById(String seq) {
		return (AC0008) getHibernateTemplate().get("entity.AC0008", seq);
	}

	@SuppressWarnings("unchecked")
	public List<AC0008> findAll() {
		return getHibernateTemplate().find("from AC0008");
	}

	public void update(AC0008 ac0008) {
		getHibernateTemplate().update(ac0008);
	}
	
	@SuppressWarnings("unchecked")
	public List<AC0008> findByProperty(String name, String value){
		try{
			String queryString = " from AC0008 as ac08 where ac08." + name + " = ?";
			return getHibernateTemplate().find(queryString, value);
		}catch(RuntimeException ex){
			throw ex;
		}
	}
}