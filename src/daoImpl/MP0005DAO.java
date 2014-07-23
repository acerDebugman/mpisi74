package daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;

import dao.IMP0005DAO;
import entity.MP0005;

public class MP0005DAO   implements IMP0005DAO {
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	public void save(MP0005 mp0005) {
		if (mp0005 != null) {
			sf.getCurrentSession().save(mp0005);
		}
	}

	public void delete(MP0005 mp0005) {
		sf.getCurrentSession().delete(mp0005);
	}

	public MP0005 findById(int seq) {
		return (MP0005) sf.getCurrentSession().get("entity.MP0005", seq);
	}

	@SuppressWarnings("unchecked")
	public List<MP0005> findAll() {
		return sf.getCurrentSession().createQuery("from MP0005").list();
	}

	public void update(MP0005 mp0005) {
		sf.getCurrentSession().update(mp0005);
	}
	
	@SuppressWarnings("unchecked")
	public List<MP0005> findByProperty(String name, String value){
		try{
			String queryString = " from MP0005 as bulletin where bulletin." + name + " = '" + value + "'";
			return sf.getCurrentSession().createQuery(queryString).list();
		}catch(RuntimeException ex){
			throw ex;
		}
	}
}
