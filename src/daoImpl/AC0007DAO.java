package daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;

import dao.IAC0007DAO;
import entity.AC0007;

public class AC0007DAO   implements IAC0007DAO {
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	public void save(AC0007 ac0007) {
		if (ac0007 != null) {
			sf.getCurrentSession().save(ac0007);
		}
	}

	public void delete(AC0007 ac0007) {
		sf.getCurrentSession().delete(ac0007);
	}

	public AC0007 findById(String seq) {
		return (AC0007) sf.getCurrentSession().get("entity.AC0007", seq);
	}

	@SuppressWarnings("unchecked")
	public List<AC0007> findAll() {
		return sf.getCurrentSession().createQuery("from AC0007").list();
	}

	public void update(AC0007 ac0007) {
		sf.getCurrentSession().update(ac0007);
	}
	
	@SuppressWarnings("unchecked")
	public List<AC0007> findByProperty(String name, String value){
		try{
			String queryString = " from AC0007 as ac07 where ac07." + name + " = '" + value + "'";
			return sf.getCurrentSession().createQuery(queryString).list();
		}catch(RuntimeException ex){
			throw ex;
		}
	}
}