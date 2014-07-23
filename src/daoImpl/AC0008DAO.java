package daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;

import dao.IAC0008DAO;
import entity.AC0008;

public class AC0008DAO   implements IAC0008DAO {
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	public void save(AC0008 ac0008) {
		if (ac0008 != null) {
			sf.getCurrentSession().save(ac0008);
		}
	}

	public void delete(AC0008 ac0008) {
		sf.getCurrentSession().delete(ac0008);
	}

	public AC0008 findById(String seq) {
		return (AC0008) sf.getCurrentSession().get("entity.AC0008", seq);
	}

	@SuppressWarnings("unchecked")
	public List<AC0008> findAll() {
		return sf.getCurrentSession().createQuery("from AC0008").list();
	}

	public void update(AC0008 ac0008) {
		sf.getCurrentSession().update(ac0008);
	}
	
	@SuppressWarnings("unchecked")
	public List<AC0008> findByProperty(String name, String value){
		try{
			String queryString = " from AC0008 as ac08 where ac08." + name + " = '" + value + "'";
			return sf.getCurrentSession().createQuery(queryString).list();
		}catch(RuntimeException ex){
			throw ex;
		}
	}
}