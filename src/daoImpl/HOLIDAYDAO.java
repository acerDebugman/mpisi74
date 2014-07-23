package daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;

import dao.IHOLIDAYDAO;
import entity.HOLIDAY;

public class HOLIDAYDAO   implements IHOLIDAYDAO {
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	public void save(HOLIDAY holiday) {
		if (holiday != null) {
			sf.getCurrentSession().save(holiday);
		}
	}

	public void delete(HOLIDAY holiday) {
		sf.getCurrentSession().delete(holiday);
	}

	public HOLIDAY findById(int seq) {
		return (HOLIDAY) sf.getCurrentSession().get("entity.HOLIDAY", seq);
	}

	@SuppressWarnings("unchecked")
	public List<HOLIDAY> findAll() {
		return sf.getCurrentSession().createQuery("from HOLIDAY order by HOLIDAY_DATE desc ").list();
	}

	public void update(HOLIDAY holiday) {
		sf.getCurrentSession().update(holiday);
	}
	
	@SuppressWarnings("unchecked")
	public List<HOLIDAY> findByProperty(String name, String value){
		try{
			String queryString = " from HOLIDAY as m6 where m6." + name + " = '" + value + "'";
			return sf.getCurrentSession().createQuery(queryString).list();
		}catch(RuntimeException ex){
			throw ex;
		}
	}
}
