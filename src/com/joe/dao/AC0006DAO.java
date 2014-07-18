package com.joe.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.joe.model.AC0006;

public class AC0006DAO extends HibernateDaoSupport implements IAC0006DAO {
	private static final Log log = LogFactory.getLog(AC0006DAO.class);
	
	public void save(AC0006 ac0006) {
		if (ac0006 != null) {
			getHibernateTemplate().save(ac0006);
		}
	}

	public void delete(AC0006 ac0006) {
		getHibernateTemplate().delete(ac0006);
	}

	public AC0006 findById(String seq) {
		return (AC0006) getHibernateTemplate().get("entity.AC0006", seq);
	}

	@SuppressWarnings("unchecked")
	public List<AC0006> findAll() {
		return getHibernateTemplate().find("from AC0006");
	}

	public void update(AC0006 ac0006) {
		getHibernateTemplate().update(ac0006);
	}
	
	@SuppressWarnings("unchecked")
	public List<AC0006> findByProperty(String name, String value){
		try{
			String queryString = " from AC0006 as ac06 where ac06." + name + " = ?";
			return getHibernateTemplate().find(queryString, value);
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
			session = getHibernateTemplate().getSessionFactory().openSession();
			
			queryString.append(" select ac06.AC0006_ROLE_NUM ");
			queryString.append(" from AC0009 ac09, AC0006 ac06 ");
			queryString.append(" where 1=1 ");
			queryString.append(" and ac06.AC0006_ROLE_NUM = ac09.AC0009_ROLE_NUM ");
			queryString.append(" and ac06.AC0006_SYS_NUM = '" + _systemNum+ "' ");
			
			if(!_empNum.equals("")){
				queryString.append(" and ac09.AC0009_EMPLOYEE_NUM = '" + _empNum + "' ");
				
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
			
			session.close();
		}catch(Exception ex){
			log.info(ex.getMessage());
		}
		finally{
			if(session.isConnected()){
				session.close();
			}
		}
		return resultValue;
	}
	@SuppressWarnings("unchecked")
	public HashMap<String,String> functionAccessCheck(String _empNum,String _systemNum){
		HashMap<String,String> functionMap = new HashMap<String,String>();
		Session session = null;
		
		try{
			StringBuffer queryString = new StringBuffer();
			session = getHibernateTemplate().getSessionFactory().openSession();
			
			queryString.append(" select ac09.AC0009_EMPLOYEE_NUM, ac06.AC0006_ROLE_NUM, ac06.AC0006_SYS_NUM, ac07.AC0007_FUN_NUM ");
			queryString.append(" from AC0009 ac09,AC0006 ac06,AC0007 ac07 ");
			queryString.append(" where 1=1 ");
			queryString.append(" and ac06.AC0006_ROLE_NUM = ac09.AC0009_ROLE_NUM ");
			queryString.append(" and ac06.AC0006_ROLE_NUM = ac07.AC0007_ROLE_NUM ");
			queryString.append(" and ac06.AC0006_SYS_NUM = ac07.AC0007_SYS_NUM ");
			queryString.append(" and ac09.AC0009_EMPLOYEE_NUM = '" + _empNum + "' ");
			queryString.append(" and ac06.AC0006_SYS_NUM = '" + _systemNum+ "' ");
			
			Query query = session.createQuery(queryString.toString());
			
			List<Object[]> list = query.list();
			
			for(int i=0,j=list.size();i<j;i++){
				Object[] obj = list.get(i);
				
				String _funNum = (obj[3]==null?"":obj[3].toString());
				
				functionMap.put(_funNum, String.valueOf(i));
			}
			
			session.close();
		}catch(Exception ex){
			log.info(ex.getMessage());
			functionMap = new HashMap<String,String>();
		}
		finally{
			if(session.isConnected()){
				session.close();
			}
		}
		
		return functionMap;
	}
	@SuppressWarnings("unchecked")
	public HashMap<String,String> functionOptAccessCheck(String _empNum,String _funcNum,String _systemNum){
		HashMap<String,String> optMap = new HashMap<String,String>();
		Session session = null;
		
		try{
			StringBuffer queryString = new StringBuffer();
			session = getHibernateTemplate().getSessionFactory().openSession();
			
			queryString.append(" select ac09.AC0009_EMPLOYEE_NUM, ac06.AC0006_ROLE_NUM, ac06.AC0006_SYS_NUM, ac07.AC0007_FUN_NUM,ac08.AC0008_OPT_NUM ");
			queryString.append(" from AC0009 ac09,AC0006 ac06,AC0007 ac07,AC0008 ac08 ");
			queryString.append(" where 1=1 ");
			queryString.append(" and ac06.AC0006_ROLE_NUM = ac09.AC0009_ROLE_NUM ");
			queryString.append(" and ac06.AC0006_ROLE_NUM = ac07.AC0007_ROLE_NUM ");
			queryString.append(" and ac06.AC0006_SYS_NUM = ac07.AC0007_SYS_NUM ");
			
			queryString.append(" and ac07.AC0007_ROLE_NUM = ac08.AC0008_ROLE_NUM ");
			queryString.append(" and ac07.AC0007_SYS_NUM = ac08.AC0008_SYS_NUM ");
			queryString.append(" and ac07.AC0007_FUN_NUM = ac08.AC0008_FUN_NUM ");
			
			queryString.append(" and ac09.AC0009_EMPLOYEE_NUM = '" + _empNum + "' ");
			queryString.append(" and ac06.AC0006_SYS_NUM = '" + _systemNum + "' ");
			queryString.append(" and ac07.AC0007_FUN_NUM = '" + _funcNum + "' ");
			
			Query query = session.createQuery(queryString.toString());
			
			List<Object[]> list = query.list();
			
			for(int i=0,j=list.size();i<j;i++){
				Object[] obj = list.get(i);
				
				String _optNum = (obj[4]==null?"":obj[4].toString());
				
				optMap.put(_optNum, String.valueOf(i));
			}
			
			session.close();
		}catch(Exception ex){
			log.info(ex.getMessage());
			optMap = new HashMap<String,String>();
		}
		finally{
			if(session.isConnected()){
				session.close();
			}
		}
		
		return optMap;
	}
	
	
}