package dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import entity.HOLIDAY;

public class HOLIDAYDAO  implements IHOLIDAYDAO {
	private SessionFactory sessionFactory;
	
	public void save(HOLIDAY holiday) {
		if (holiday != null) {
			sessionFactory.getCurrentSession().save(holiday);
		}
	}

	public void delete(HOLIDAY holiday) {
		sessionFactory.getCurrentSession().delete(holiday);
	}

	public HOLIDAY findById(int seq) {
		return (HOLIDAY) sessionFactory.getCurrentSession().get("entity.HOLIDAY", seq);
	}

	@SuppressWarnings("unchecked")
	public List<HOLIDAY> findAll() {
//		return getHibernateTemplate().find("from HOLIDAY order by HOLIDAY_DATE desc ");
		return sessionFactory.getCurrentSession().createQuery("from HOLIDAY order by HOLIDAY_DATE desc ").list();
	}

	public void update(HOLIDAY holiday) {
		sessionFactory.getCurrentSession().update(holiday);
	}
	
	@SuppressWarnings("unchecked")
	public List<HOLIDAY> findByProperty(String name, String value){
		try{
			String queryString = " from HOLIDAY as m6 where m6." + name + " = '" + value + "'";
			return sessionFactory.getCurrentSession().createQuery(queryString).list();
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	
	public boolean isPublicHoliday(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return !sessionFactory.getCurrentSession().createQuery("from HOLIDAY where HOLIDAY_DATE='" + sdf.format(date) + "'").list().isEmpty();		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
