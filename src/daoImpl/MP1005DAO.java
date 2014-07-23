package daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;

import dao.IMP1005DAO;
import entity.MP1005;

public class MP1005DAO   implements IMP1005DAO {
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	public void save(MP1005 mp1005) {
		if (mp1005 != null) {
			sf.getCurrentSession().save(mp1005);
		}
	}

	public void delete(MP1005 mp1005) {
		sf.getCurrentSession().delete(mp1005);
	}

	public MP1005 findById(int seq) {
		return (MP1005) sf.getCurrentSession().get("entity.MP1005", seq);
	}

	@SuppressWarnings("unchecked")
	public List<MP1005> findAll() {
		return sf.getCurrentSession().createQuery("from MP1005").list();
	}

	public void update(MP1005 mp1005) {
		sf.getCurrentSession().update(mp1005);
	}

	@SuppressWarnings("unchecked")
	public List<MP1005> findByProperty(String name, String value) {
		try{
			String queryString = " from MP1005 as mp5 where mp5." + name + " = '" + value + "'";
			return sf.getCurrentSession().createQuery(queryString).list();
		}catch(RuntimeException ex){
			throw ex;
		}
	}
}