package daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;

import dao.IMP0006DAO;
import entity.MP0006;

public class MP0006DAO   implements IMP0006DAO {
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	public void save(MP0006 mp0006) {
		if (mp0006 != null) {
			sf.getCurrentSession().save(mp0006);
		}
	}

	public void delete(MP0006 mp0006) {
		sf.getCurrentSession().delete(mp0006);
	}

	public MP0006 findById(int seq) {
		return (MP0006) sf.getCurrentSession().get("entity.MP0006", seq);
	}

	@SuppressWarnings("unchecked")
	public List<MP0006> findAll() {
		return sf.getCurrentSession().createQuery("from MP0006").list();
	}

	public void update(MP0006 mp0006) {
		sf.getCurrentSession().update(mp0006);
	}
	
	@SuppressWarnings("unchecked")
	public List<MP0006> findByProperty(String name, String value){
		try{
			String queryString = " from MP0006 as m6 where m6." + name + " = '" + value + "'";
			return sf.getCurrentSession().createQuery(queryString).list();
		}catch(RuntimeException ex){
			throw ex;
		}
	}
}

