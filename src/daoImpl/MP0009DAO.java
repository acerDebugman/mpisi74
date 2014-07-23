package daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;

import dao.IMP0009DAO;
import entity.MP0009;

public class MP0009DAO   implements IMP0009DAO {
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	public void save(MP0009 mp0009) {
		if (mp0009 != null) {
			sf.getCurrentSession().save(mp0009);
		}
	}

	public void delete(MP0009 mp0009) {
		sf.getCurrentSession().delete(mp0009);
	}

	public MP0009 findById(int seq) {
		return (MP0009) sf.getCurrentSession().get("entity.MP0009", seq);
	}

	@SuppressWarnings("unchecked")
	public List<MP0009> findAll() {
		return sf.getCurrentSession().createQuery("from MP0009 where 1=1 and MP0009_POSITION_STATUS = '1' ").list();
	}

	public void update(MP0009 mp0009) {
		sf.getCurrentSession().update(mp0009);
	}
	
	@SuppressWarnings("unchecked")
	public List<MP0009> findByProperty(String name, String value){
		try{
			String queryString = " from MP0009 as mp9 where mp9." + name + " = '" + value + "'";
			return sf.getCurrentSession().createQuery(queryString).list();
		}catch(RuntimeException ex){
			throw ex;
		}
	}
}
