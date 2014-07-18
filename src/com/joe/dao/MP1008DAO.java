package com.joe.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.joe.model.MP1008;

public class MP1008DAO extends HibernateDaoSupport implements IMP1008DAO{
	public void save(MP1008 mp1008) {
		if (mp1008 != null) {
			getHibernateTemplate().save(mp1008);
		}
	}

	public void delete(MP1008 mp1008) {
		getHibernateTemplate().delete(mp1008);
	}

	public MP1008 findById(String _key) {
		return (MP1008) getHibernateTemplate().get("entity.MP1008", _key);
	}

	@SuppressWarnings("unchecked")
	public List<MP1008> findAll() {
		return getHibernateTemplate().find("from MP1008 where 1=1 ");
	}

	public void update(MP1008 mp1008) {
		getHibernateTemplate().update(mp1008);
	}
	
	public void update(MP1008 mp1008, String oldKey) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" update MP1008 ");
		queryString.append(" set MP1008_ID = '" + mp1008.getMP1008_ID() + "', ");
		queryString.append(" MP1008_NAME = '" + mp1008.getMP1008_NAME() + "', ");
		queryString.append(" MP1008_PHONE = '" + mp1008.getMP1008_PHONE() + "', ");
		queryString.append(" MP1008_INTERVIEW_DATETIME = '" + mp1008.getMP1008_INTERVIEW_DATETIME() + "', ");
		queryString.append(" MP1008_INTERVIEWER = '" + mp1008.getMP1008_INTERVIEWER() + "', ");
		queryString.append(" MP1008_CV_PATH = '" + mp1008.getMP1008_CV_PATH() + "' ");
		queryString.append(" where 1=1 ");
		queryString.append(" and MP1008_ID = '" + oldKey + "' ");
		
		Query query = session.createQuery(queryString.toString());
		query.executeUpdate();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<MP1008> findByProperty(String name, String value){
		try{
			String queryString = " from MP1008 as mp18 where mp18." + name + " = ?";
			return getHibernateTemplate().find(queryString, value);
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	
	public List<MP1008> findByProperty(String intervieweeID,String intervieweeName, int PAGE_NUM, int PAGE_COUNT){
		StringBuffer queryString = new StringBuffer();
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		
		queryString.append(" select ");
		queryString.append(" mp18.MP1008_ID, ");
		queryString.append(" mp18.MP1008_NAME, ");
		queryString.append(" mp18.MP1008_PHONE, ");
		queryString.append(" mp18.MP1008_INTERVIEW_DATETIME, ");
		queryString.append(" mp18.MP1008_INTERVIEWER, ");
		queryString.append(" mp18.MP1008_CV_PATH ");
		queryString.append(" from MP1008 mp18 ");
		queryString.append(" where 1=1 ");
		
		if(intervieweeID != null && !intervieweeID.equals("")){
			queryString.append(" and mp18.MP1008_ID = '" + intervieweeID + "' ");
		}
		if(intervieweeName != null && !intervieweeName.equals("")){
			queryString.append(" and mp18.MP1008_NAME like '%" + intervieweeName + "%' ");
		}
		
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		session.close();
		
		MP1008 mp18 = new MP1008();
		List<MP1008> retList = new ArrayList<MP1008>();
		for(int i=0,j=list.size();i<j;i++){
			mp18 = new MP1008();
			Object[] obj = list.get(i);
			
			// 身份证号码/护照号码
			if(obj[0] == null){
				mp18.setMP1008_ID("");
			}else{
				mp18.setMP1008_ID(obj[0].toString());
			}
			// 姓名
			if(obj[1] == null){
				mp18.setMP1008_NAME("");
			}else{
				mp18.setMP1008_NAME(obj[1].toString());
			}
			// 电话
			if(obj[2] == null){
				mp18.setMP1008_PHONE("");
			}else{
				mp18.setMP1008_PHONE(obj[2].toString());
			}
			// 面试时间
			if(obj[3] == null){
				mp18.setMP1008_INTERVIEW_DATETIME("");
			}else{
				mp18.setMP1008_INTERVIEW_DATETIME(obj[3].toString().substring(0,19));
			}
			// 面试负责人
			if(obj[4] == null){
				mp18.setMP1008_INTERVIEWER("");
			}else{
				mp18.setMP1008_INTERVIEWER(obj[4].toString());
			}
			// 简历保存路径
			if(obj[5] == null){
				mp18.setMP1008_CV_PATH("");
			}else{
				mp18.setMP1008_CV_PATH(obj[5].toString());
			}
			
			retList.add(mp18);
		}

		return retList;
	}
}
