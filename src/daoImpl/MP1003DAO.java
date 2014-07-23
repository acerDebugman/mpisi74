package daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;

import dao.IMP1003DAO;
import entity.MP1003;

public class MP1003DAO   implements IMP1003DAO {
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	public void save(MP1003 mp1003) {
		if (mp1003 != null) {
			sf.getCurrentSession().save(mp1003);
		}
	}

	public void delete(MP1003 mp1003) {
		sf.getCurrentSession().delete(mp1003);
	}

	public MP1003 findById(int seq) {
		return (MP1003) sf.getCurrentSession().get("entity.MP1003", seq);
	}

	@SuppressWarnings("unchecked")
	public List<MP1003> findAll() {
		return sf.getCurrentSession().createQuery("from MP1003").list();
	}

	public void update(MP1003 mp1003) {
		sf.getCurrentSession().update(mp1003);
	}

	@SuppressWarnings("unchecked")
	public List<MP1003> findByProperty(String name, String value) {
		try{
			String queryString = " from MP1003 as mp3 where mp3." + name + " = '" + value + "'";
			return sf.getCurrentSession().createQuery(queryString).list();
		}catch(RuntimeException ex){
			throw ex;
		}
	}
}