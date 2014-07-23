package daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;

import dao.IMP1002DAO;
import entity.MP1002;

public class MP1002DAO   implements IMP1002DAO {
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	public void save(MP1002 mp1002) {
		if (mp1002 != null) {
			sf.getCurrentSession().save(mp1002);
		}
	}

	public void delete(MP1002 mp1002) {
		sf.getCurrentSession().delete(mp1002);
	}

	public MP1002 findById(int seq) {
		return (MP1002) sf.getCurrentSession().get("entity.MP1002", seq);
	}

	@SuppressWarnings("unchecked")
	public List<MP1002> findAll() {
		return sf.getCurrentSession().createQuery("from MP1002").list();
	}

	public void update(MP1002 mp1002) {
		sf.getCurrentSession().update(mp1002);
	}

	@SuppressWarnings("unchecked")
	public List<MP1002> findByProperty(String name, String value) {
		try{
			String queryString = " from MP1002 as mp2 where mp2." + name + " = ?";
			return sf.getCurrentSession().createQuery(queryString).list();
		}catch(RuntimeException ex){
			throw ex;
		}
	}

}