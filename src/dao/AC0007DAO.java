package dao;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import entity.AC0007;

public class AC0007DAO extends HibernateDaoSupport implements IAC0007DAO {
	
	public void save(AC0007 ac0007) {
		if (ac0007 != null) {
			getHibernateTemplate().save(ac0007);
		}
	}

	public void delete(AC0007 ac0007) {
		getHibernateTemplate().delete(ac0007);
	}

	public AC0007 findById(String seq) {
		return (AC0007) getHibernateTemplate().get("entity.AC0007", seq);
	}

	@SuppressWarnings("unchecked")
	public List<AC0007> findAll() {
		return getHibernateTemplate().find("from AC0007");
	}

	public void update(AC0007 ac0007) {
		getHibernateTemplate().update(ac0007);
	}
	
	@SuppressWarnings("unchecked")
	public List<AC0007> findByProperty(String name, String value){
		try{
			String queryString = " from AC0007 as ac07 where ac07." + name + " = ?";
			return getHibernateTemplate().find(queryString, value);
		}catch(RuntimeException ex){
			throw ex;
		}
	}
}