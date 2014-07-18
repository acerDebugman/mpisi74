package com.joe.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.joe.model.MP1009;

public class MP1009DAO extends HibernateDaoSupport implements IMP1009DAO{
	public void save(MP1009 mp1009) {
		if (mp1009 != null) {
			getHibernateTemplate().save(mp1009);
		}
	}

	public void delete(MP1009 mp1009) {
		getHibernateTemplate().delete(mp1009);
	}

	public MP1009 findById(int _key) {
		return (MP1009) getHibernateTemplate().get("entity.MP1009", _key);
	}

	@SuppressWarnings("unchecked")
	public List<MP1009> findAll() {
		return getHibernateTemplate().find("from MP1009 where 1=1 ");
	}

	public void update(MP1009 mp1009) {
		getHibernateTemplate().update(mp1009);
	}
	
	@SuppressWarnings("unchecked")
	public List<MP1009> findByProperty(String name, String value){
		try{
			String queryString = " from MP1009 as mp19 where mp19." + name + " = ?";
			return getHibernateTemplate().find(queryString, value);
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	
	public List<MP1009> findByProperty(String employeeNum,String employeeName,String documentName, int PAGE_NUM, int PAGE_COUNT){
		StringBuffer queryString = new StringBuffer();
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		
		queryString.append(" select ");
		queryString.append(" mp19.MP1009_SEQ, ");
		queryString.append(" mp19.MP1009_EMPLOYEE_NUM, ");
		queryString.append(" mp19.MP1009_DOCUMENT_NAME, ");
		queryString.append(" mp19.MP1009_PATH, ");
		queryString.append(" mp19.MP1009_UPLOAD_TIME, ");
		queryString.append(" mp19.MP1009_UPLOADER, ");
		queryString.append(" mp11.MP1001_PREFERED_NAME ");
		queryString.append(" from MP1009 mp19 , MP1001 mp11 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and mp19.MP1009_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM ");
		
		if(employeeNum != null && !employeeNum.equals("")){
			queryString.append(" and mp19.MP1009_EMPLOYEE_NUM = '" + employeeNum + "' ");
		}
		if(employeeName != null && !employeeName.equals("")){
			queryString.append(" and mp11.MP1001_PREFERED_NAME = '" + employeeName + "' ");
		}
		if(documentName != null && !documentName.equals("")){
			queryString.append(" and mp19.MP1009_DOCUMENT_NAME = '" + documentName + "' ");
		}
		
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		session.close();
		
		MP1009 mp19 = new MP1009();
		List<MP1009> retList = new ArrayList<MP1009>();
		for(int i=0,j=list.size();i<j;i++){
			mp19 = new MP1009();
			Object[] obj = list.get(i);
			
			mp19.setMP1009_SEQ(Integer.parseInt(obj[0].toString()));
			// Employee Number
			if(obj[1] == null){
				mp19.setMP1009_EMPLOYEE_NUM("");
			}else{
				mp19.setMP1009_EMPLOYEE_NUM(obj[1].toString());
			}
			// Document Name
			if(obj[2] == null){
				mp19.setMP1009_DOCUMENT_NAME("");
			}else{
				mp19.setMP1009_DOCUMENT_NAME(obj[2].toString());
			}
			// Path
			if(obj[3] == null){
				mp19.setMP1009_PATH("");
			}else{
				mp19.setMP1009_PATH(obj[3].toString());
			}
			// Upload Time
			if(obj[4] == null){
				mp19.setMP1009_UPLOAD_TIME("");
			}else{
				mp19.setMP1009_UPLOAD_TIME(obj[4].toString().substring(0,19));
			}
			// Uploader
			if(obj[5] == null){
				mp19.setMP1009_UPLOADER("");
			}else{
				mp19.setMP1009_UPLOADER(obj[5].toString());
			}
			// Employee Name
			if(obj[6] == null){
				mp19.setMP1009_EMPLOYEE_NAME("");
			}else{
				mp19.setMP1009_EMPLOYEE_NAME(obj[6].toString());
			}
			
			retList.add(mp19);
		}

		return retList;
	}
	
	
	
	
	
	
}
