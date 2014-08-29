package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.AC0009;

public class AC0009DAO  implements IAC0009DAO {
	private SessionFactory sessionFactory;
	
	public void save(AC0009 ac0009) {
		if (ac0009 != null) {
			sessionFactory.getCurrentSession().save(ac0009);
		}
	}

	public void delete(AC0009 ac0009) {
		sessionFactory.getCurrentSession().delete(ac0009);
	}

	public AC0009 findById(String seq) {
		return (AC0009) sessionFactory.getCurrentSession().get("entity.AC0009", seq);
	}

	@SuppressWarnings("unchecked")
	public List<AC0009> findAll() {
//		return getHibernateTemplate().find("from AC0009");
		return sessionFactory.getCurrentSession().createQuery("from AC0009").list();
	}

	public void update(AC0009 ac0009) {
		sessionFactory.getCurrentSession().update(ac0009);
	}
	
	@SuppressWarnings("unchecked")
	public List<AC0009> findByProperty(String name, String value){
		try{
			String queryString = " from AC0009 as bulletin where bulletin." + name + " = '" + value + "'";
//			return getHibernateTemplate().find(queryString, value);
			return sessionFactory.getCurrentSession().createQuery(queryString).list();
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	@SuppressWarnings("unchecked")
	public String getMailInfoByAccountApprove(){
		Session session = null;
		String mailList ="";
		
		try{
			StringBuffer queryString = new StringBuffer();
			session = sessionFactory.getCurrentSession();
			
			queryString.append(" select mp11.MP1001_COMPANY_EMAIL ");
			queryString.append(" from AC0009 ac09,AC0008 ac08,MP1001 mp11 ");
			queryString.append(" where 1=1 ");
			queryString.append(" and ac09.AC0009_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM ");
			queryString.append(" and ac09.AC0009_ROLE_NUM = ac08.AC0008_ROLE_NUM ");
			
			queryString.append(" and ac08.AC0008_SYS_NUM = 'SYS0004' ");
			queryString.append(" and ac08.AC0008_FUN_NUM = 'F0020004' ");
			queryString.append(" and ac08.AC0008_OPT_NUM = 'OPT_APPROVAL_ACCOUNTING' ");
			
			Query query = session.createQuery(queryString.toString());
			
			List<Object[]> list = query.list();
			
			for(int i=0,j=list.size();i<j;i++){
				Object[] obj = list.get(i);
				
				String companyMail = (obj[0]==null?"":obj[0].toString());
				
				mailList =mailList + companyMail + ",";
			}
			
//			// session.close();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		finally{
			if(session.isConnected()){
//				// session.close();
			}
		}
		
		return mailList;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
	
}