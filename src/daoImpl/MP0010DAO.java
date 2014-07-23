package daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;

import dao.IMP0010DAO;
import entity.MP0010;

public class MP0010DAO   implements IMP0010DAO{
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	public void save(MP0010 mp0010) {
		if (mp0010 != null) {
			sf.getCurrentSession().save(mp0010);
		}
	}

	public void delete(MP0010 mp0010) {
		sf.getCurrentSession().delete(mp0010);
	}

	public MP0010 findById(String _key) {
		return (MP0010) sf.getCurrentSession().get("entity.MP0010", _key);
	}

	@SuppressWarnings("unchecked")
	public List<MP0010> findAll() {
		return sf.getCurrentSession().createQuery("from MP0010 where 1=1 order by MP0010_DATETIME desc ").list();
	}

	public void update(MP0010 mp0010) {
		sf.getCurrentSession().update(mp0010);
	}
	
	@SuppressWarnings("unchecked")
	public List<MP0010> findByProperty(String name, String value){
		try{
			String queryString = " from MP0010 as mp10 where mp10." + name + " = '" + value + "'";
			return sf.getCurrentSession().createQuery(queryString).list();
		}catch(RuntimeException ex){
			throw ex;
		}
	}
}
