package dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.AC0012;

public class AC0012DAO  implements IAC0012DAO {
	private SessionFactory sessionFactory;
	
	public void save(AC0012 ac0012) {
		if (ac0012 != null) {
			sessionFactory.getCurrentSession().save(ac0012);
		}
	}

	public void delete(AC0012 ac0012) {
		sessionFactory.getCurrentSession().delete(ac0012);
	}

	public AC0012 findById(String seq) {
		return (AC0012) sessionFactory.getCurrentSession().get("entity.AC0012", seq);
	}

	@SuppressWarnings("unchecked")
	public List<AC0012> findAll() {
//		return getHibernateTemplate().find("from AC0012");
		return sessionFactory.getCurrentSession().createQuery("from AC0012").list();
	}

	public void update(AC0012 ac0012) {
		sessionFactory.getCurrentSession().update(ac0012);
	}
	
	@SuppressWarnings("unchecked")
	public List<AC0012> findByProperty(String name, String value){
		try{
//			String queryString = " from AC0012 as ac12 where ac12." + name + " = ?";
//			return getHibernateTemplate().find(queryString, value);
			String queryString = " from AC0012 as ac12 where ac12." + name + " = '" + value + "'";
			return sessionFactory.getCurrentSession().createQuery(queryString).list();
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	@SuppressWarnings("unchecked")
	public HashMap<String,String> functionOptAccessCheck(String _empNum,String _funcNum,String _systemNum){
		HashMap<String,String> optMap = new HashMap<String,String>();
		Session session = null;
		
		try{
			StringBuffer queryString = new StringBuffer();
//			session = sessionFactory.getCurrentSession();
			session = sessionFactory.getCurrentSession();
			
			queryString.append(" select ac12.AC0012_EMPLOYEE_NUM, ac12.AC0012_SYS_NUM, ac12.AC0012_FUN_NUM, ac12.AC0012_OPT_NUM ");
			queryString.append(" from AC0012 ac12 ");
			queryString.append(" where 1=1 ");			
			queryString.append(" and ac12.AC0012_EMPLOYEE_NUM = '" + _empNum + "' ");
			queryString.append(" and ac12.AC0012_SYS_NUM = '" + _systemNum + "' ");
			queryString.append(" and ac12.AC0012_FUN_NUM = '" + _funcNum + "' ");
			
			Query query = session.createQuery(queryString.toString());
			
			List<Object[]> list = query.list();
			
			for(int i=0,j=list.size();i<j;i++){
				Object[] obj = list.get(i);
				
				String _optNum = (obj[3]==null?"":obj[3].toString());
				
				optMap.put(_optNum, String.valueOf(i));
			}
			
//			// session.close();
		}catch(Exception ex){
			optMap = new HashMap<String,String>();
		}
		finally{
			if(session.isConnected()){
//				// session.close();
			}
		}
		
		return optMap;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
