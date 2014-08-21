package dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.AC0010;

public class AC0010DAO  implements IAC0010DAO {
	private static final Log log = LogFactory.getLog(AC0006DAO.class);
	
	private SessionFactory sessionFactory;
	
	public void save(AC0010 ac0010) {
		if (ac0010 != null) {
			sessionFactory.getCurrentSession().save(ac0010);
		}
	}

	public void delete(AC0010 ac0010) {
		sessionFactory.getCurrentSession().delete(ac0010);
	}

	public AC0010 findById(String seq) {
		return (AC0010) sessionFactory.getCurrentSession().get("entity.AC0010", seq);
	}

	@SuppressWarnings("unchecked")
	public List<AC0010> findAll() {
//		return getHibernateTemplate().find("from AC0010");
		return sessionFactory.getCurrentSession().createQuery("from AC0010").list();
	}

	public void update(AC0010 ac0010) {
		sessionFactory.getCurrentSession().update(ac0010);
	}
	
	@SuppressWarnings("unchecked")
	public List<AC0010> findByProperty(String name, String value){
		try{
//			String queryString = " from AC0010 as ac10 where ac10." + name + " = ?";
//			return getHibernateTemplate().find(queryString, value);
			String queryString = " from AC0010 as ac10 where ac10." + name + " = '" + value + "'";
			return sessionFactory.getCurrentSession().createQuery(queryString).list();
		}catch(RuntimeException ex){
			throw ex;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean systemAccessCheck(String _empNum,String _systemNum){
		boolean resultValue = false;
		Session session = null;
		try{
			StringBuffer queryString = new StringBuffer();
//			session = sessionFactory.getCurrentSession();
			session = sessionFactory.getCurrentSession();
			
			queryString.append(" select ac10.AC0010_EMPLOYEE_NUM, ac10.AC0010_SYS_NUM ");
			queryString.append(" from AC0010 ac10 ");
			queryString.append(" where 1=1 ");
			queryString.append(" and ac10.AC0010_SYS_NUM = '" + _systemNum+ "' ");
			
			if(!_empNum.equals("")){
				queryString.append(" and ac10.AC0010_EMPLOYEE_NUM = '" + _empNum + "' ");
				
				Query query = session.createQuery(queryString.toString());
				
				List<Object[]> list = query.list();
				
				if( list != null && list.size() > 0 ){
					resultValue = true;
				}else{
					resultValue = false;
				}
			}else{
				resultValue = false;
			}
			
//			// session.close();
		}catch(Exception ex){
			log.info(ex.getMessage());
		}
		finally{
			if(session.isConnected()){
//				// session.close();
			}
		}
		return resultValue;
	}

	public static Log getLog() {
		return log;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
