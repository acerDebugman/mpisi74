package com.joe.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.joe.common.Constant;
import com.joe.common.UtilDate;
import com.joe.dto.AbnormalReptRecdDto;
import com.joe.dto.LateEarlyDto;
import com.joe.model.CHECKINOUT;
import com.joe.model.MP1001;
import com.joe.model.MP2003;

public class MP2003DAO extends HibernateDaoSupport implements IMP2003DAO {

	public void save(MP2003 mp2003) {
		if (mp2003 != null) {
			getHibernateTemplate().save(mp2003);
		}
	}

	public void delete(MP2003 mp2003) {
		getHibernateTemplate().delete(mp2003);
	}

	public MP2003 findById(String employeeNum) {
		return (MP2003) getHibernateTemplate().get("entity.MP2003", employeeNum);
	}

	@SuppressWarnings("unchecked")
	public List<MP2003> findAll() {
		return getHibernateTemplate().find("from MP2003");
	}

	public void update(MP2003 mp2003) {
		getHibernateTemplate().update(mp2003);
	}
	
    public void loadDataToHR(Map<String, CHECKINOUT> inoutMap, String condition){
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		session.beginTransaction();
		StringBuffer queryString = new StringBuffer();
		
		// 删除数据
		queryString.append(" DELETE FROM  MP2003 WHERE 1=1 " + condition);
		Query query = session.createQuery(queryString.toString());
	    query.executeUpdate();
		
        //MP2003 mp23 = null;
        CHECKINOUT checkObj = null;
        for(Map.Entry<String, CHECKINOUT> entry : inoutMap.entrySet()){
        	//mp23 = new MP2003();
        	checkObj = entry.getValue();        	
        	queryString = new StringBuffer();
        	queryString.append(" Insert into MP2003( ");
        	queryString.append(" MP2003_EDIT_DATETIME, ");
        	queryString.append(" MP2003_EMPLOYEE_NUM, ");
        	queryString.append(" MP2003_DATETIME, ");
        	queryString.append(" MP2003_START_TIME, ");
        	queryString.append(" MP2003_FINISH_TIME, ");
        	queryString.append(" MP2003_START_TIME_DOOR, ");
        	queryString.append(" MP2003_FINISH_TIME_DOOR, ");
        	queryString.append(" MP2003_START_TIME_E, ");
        	queryString.append(" MP2003_FINISH_TIME_E) ");
        	queryString.append(" Values ( ");
        	queryString.append("'" + UtilDate.get24DateTime() + "', " );
        	queryString.append("'" + entry.getKey().split("#@")[0] + "', " );
        	queryString.append("'" + entry.getKey().split("#@")[1] + "', " );
        	
        	if(checkObj.getDate1() == null || checkObj.getDate1().equals("")){
        		queryString.append("null, " );
        	}else{
        		queryString.append("'" + checkObj.getDate1() + "', " );
        	}
        	if(checkObj.getDate2() == null || checkObj.getDate2().equals("")){
        		queryString.append("null, " );
        	}else{
        		queryString.append("'" + checkObj.getDate2() + "', " );
        	}
        	if(checkObj.getDoor1() == null || checkObj.getDoor1().equals("")){
        		queryString.append("null, " );
        	}else{
        		queryString.append("'" + checkObj.getDoor1() + "', " );
        	}
        	if(checkObj.getDoor2() == null || checkObj.getDoor2().equals("")){
        		queryString.append("null, " );
        	}else{
        		queryString.append("'" + checkObj.getDoor2() + "', " );
        	}
        	if(checkObj.getDate1() == null || checkObj.getDate1().equals("")){
        		queryString.append("null, " );
        	}else{
        		queryString.append("'" + checkObj.getDate1() + "', " );
        	}
        	if(checkObj.getDate2() == null || checkObj.getDate2().equals("")){
        		queryString.append("null " );
        	}else{
        		queryString.append("'" + checkObj.getDate2() + "' " );
        	}
        	queryString.append(")");
        	
        	query = session.createSQLQuery(queryString.toString());
        	query.executeUpdate();
        	
/*        	mp23.setMP2003_EMPLOYEE_NUM(entry.getKey().split("#@")[0]);
        	mp23.setMP2003_DATETIME(entry.getKey().split("#@")[1]);
        	
        	mp23.setMP2003_START_TIME(checkObj.getDate1());
        	mp23.setMP2003_START_TIME_DOOR(checkObj.getDoor1());
        	mp23.setMP2003_START_TIME_E(checkObj.getDate1());
        	
        	mp23.setMP2003_FINISH_TIME(checkObj.getDate2());
        	mp23.setMP2003_FINISH_TIME_DOOR(checkObj.getDoor2());
        	mp23.setMP2003_FINISH_TIME_E(checkObj.getDate2());
        	getHibernateTemplate().save(mp23);*/
        	
        }
		
	    session.getTransaction().commit();	    
	    session.close();
    }
	
	public void updateStatus(MP2003 mp2003){
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		String status = mp2003.getMP2003_STATUS();
		//String startTime = mp2003.getMP2003_DATETIME() + " 08:00";
		//String finishTime = mp2003.getMP2003_DATETIME() + " 16:30";
		StringBuffer updateSql = new StringBuffer();

			updateSql.append(" update MP2003 ");
			updateSql.append(" set MP2003_STATUS = '" + mp2003.getMP2003_STATUS() + "' ");
			if(status.equals("3")){ // In is null
				// updateSql.append(" , MP2003_START_TIME = '" + startTime + "' ");
				// updateSql.append(" , MP2003_FINISH_TIME = '" + mp2003.getMP2003_START_TIME() + "' ");
				updateSql.append(" , MP2003_COMMENT = '" + Constant.IN_MSG + "' ");
			}else if(status.equals("4")){ // Out is null
				// updateSql.append(" , MP2003_FINISH_TIME = '" + finishTime + "' ");
				updateSql.append(" , MP2003_COMMENT = '" + Constant.OUT_MSG + "' ");
			}else if(status.equals("5")){ // In/Out is null
				// updateSql.append(" , MP2003_START_TIME = '" + startTime + "' ");
				// updateSql.append(" , MP2003_FINISH_TIME = '" + finishTime + "' ");
				updateSql.append(" , MP2003_COMMENT = '" + Constant.IN_OUT_MSG + "' ");
			}
			updateSql.append(" where 1 = 1 ");
			updateSql.append(" and MP2003_EMPLOYEE_NUM = '" + mp2003.getMP2003_EMPLOYEE_NUM() + "' ");
			updateSql.append(" and MP2003_DATETIME = '" + mp2003.getMP2003_DATETIME() + "' ");
		
		Query query = session.createQuery(updateSql.toString());
		query.executeUpdate();
		
		session.close();
	}
	
	public void updateTimes(String empNum, String dateTime){
		try{
			String startTime = dateTime + " 08:00";
			String finishTime = dateTime + " 16:30";
			
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			StringBuffer updateSql = new StringBuffer();
			updateSql.append("update MP2003 ");
			updateSql.append(" set MP2003_START_TIME = '" + startTime + "', ");
			updateSql.append(" MP2003_FINISH_TIME = '" + finishTime + "' ");
			updateSql.append(" where 1=1");
			updateSql.append(" and MP2003_EMPLOYEE_NUM = '" + empNum + "' ");
			updateSql.append(" and MP2003_DATETIME = '" + dateTime + "' ");
			
			Query query = session.createQuery(updateSql.toString());
			query.executeUpdate();
			
			session.close();
			
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<MP2003> findByProperty(String name, String value) {
		try{
			String queryString = " from MP2003 as mp3 where mp3." + name + " = ?";
			return getHibernateTemplate().find(queryString, value);
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<MP2003> findByPropertyPage(Map<String, String> propertyMap){
		final int pageNum = Integer.parseInt(propertyMap.get("PAGE_NUM"));
		final int pageCount = Integer.parseInt(propertyMap.get("PAGE_COUNT"));
		
		final StringBuffer queryString = new StringBuffer();
		queryString.append(" from MP2003 as mp3 ");
		queryString.append(" where 1=1 ");
		
		return getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(queryString.toString());
				query.setFirstResult((pageNum -1)*pageCount);
				query.setMaxResults(pageCount);
				
				List<MP2003> list = query.list();
				
				return list;
			}});
	}

	@SuppressWarnings("unchecked")
	public List<MP2003> findByPropertys2(MP2003 _data) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		StringBuffer queryString = new StringBuffer();
		queryString.append("select ");
		//queryString.append(" mp11.MP1001_EMPLOYEE_NUM,");
		//queryString.append(" mp11.MP1001_PREFERED_NAME,");
		queryString.append(" mp23.MP2003_DATETIME,");
		queryString.append(" mp23.MP2003_START_TIME,");
		queryString.append(" mp23.MP2003_FINISH_TIME,");
		queryString.append(" mp23.MP2003_COMMENT, ");
		queryString.append(" mp23.MP2003_START_TIME_DOOR, ");
		queryString.append(" mp23.MP2003_FINISH_TIME_DOOR, ");
		//queryString.append(" mp11.MP1001_RECTIFY_TIMES, ");
		queryString.append(" mp23.MP2003_STATUS ");
		//queryString.append(" from MP2003 mp23,MP1001 mp11 ");
		queryString.append(" from MP2003 mp23 ");
		queryString.append(" where 1=1 ");
		//queryString.append(" and mp23.MP2003_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM ");
		if(_data.getMP2003_EMPLOYEE_NUM()!= null && !_data.getMP2003_EMPLOYEE_NUM().equals("")){
			queryString.append(" and mp23.MP2003_EMPLOYEE_NUM = '" + _data.getMP2003_EMPLOYEE_NUM() + "' ");
		}
		if(_data.getMP2003_DATETIME() != null && !_data.getMP2003_DATETIME().equals("")){
			queryString.append(" and mp23.MP2003_DATETIME = '" + _data.getMP2003_DATETIME() + "' ");
		}
		
		Query query = session.createSQLQuery(queryString.toString());
		
		List<Object[]> list = query.list();
		session.close();
		
		MP2003 mp23 = new MP2003();
		List<MP2003> resultList = new ArrayList<MP2003>();
		for(int i=0,j=list.size();i<j;i++){
			mp23 = new MP2003();
			Object[] obj = list.get(i);
			
			// EMPLOYEE_NUM
/*			if(null == obj[0]){
				mp23.setMP2003_EMPLOYEE_NUM(null);
			}else{
				mp23.setMP2003_EMPLOYEE_NUM(obj[0].toString());
			}
			// EMPLOYEE_NAME
			if(null == obj[1]){
				mp23.setMP2003_EMPLOYEE_NAME(null);
			}else{
				mp23.setMP2003_EMPLOYEE_NAME(obj[1].toString());
			}*/
			// DATETIME
			if(null == obj[0]){
				mp23.setMP2003_DATETIME(null);
			}else{
				mp23.setMP2003_DATETIME(obj[0].toString());
			}
			// FINISH_TIME
			if(null == obj[1]){
				mp23.setMP2003_START_TIME(null);
			}else{
				mp23.setMP2003_START_TIME(obj[1].toString());
			}
			// FINISH_TIME
			if(null == obj[2]){
				mp23.setMP2003_FINISH_TIME(null);
			}else{
				mp23.setMP2003_FINISH_TIME(obj[2].toString());
			}
			// COMMENT
			if(null == obj[3]){
				mp23.setMP2003_COMMENT("");
			}else{
				mp23.setMP2003_COMMENT(obj[3].toString());
			}
			
			if(null == obj[4]){
				mp23.setMP2003_START_TIME_DOOR("");
			}else{
				mp23.setMP2003_START_TIME_DOOR(obj[4].toString());
			}
			if(null == obj[5]){
				mp23.setMP2003_FINISH_TIME_DOOR("");
			}else{
				mp23.setMP2003_FINISH_TIME_DOOR(obj[5].toString());
			}
			
/*			// RECTIFY TIMES
			if(null == obj[8]){
				mp23.setMP2003_RECTIFY_TIMES("0");
			}else{
				mp23.setMP2003_RECTIFY_TIMES(obj[8].toString());
			}*/
			
			//Status1
			if(null == obj[6]){
				mp23.setMP2003_STATUS("2");
			}else{
				mp23.setMP2003_STATUS(obj[6].toString());
			}
			
			resultList.add(mp23);
		}
		
		return resultList;
	}
	
	public List<MP2003> findByPropertys(MP2003 _data) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		StringBuffer queryString = new StringBuffer();
		queryString.append("select ");
		queryString.append(" mp11.MP1001_EMPLOYEE_NUM,");
		queryString.append(" mp11.MP1001_PREFERED_NAME,");
		queryString.append(" mp23.MP2003_DATETIME,");
		queryString.append(" mp23.MP2003_START_TIME,");
		queryString.append(" mp23.MP2003_FINISH_TIME,");
		queryString.append(" mp23.MP2003_COMMENT, ");
		queryString.append(" mp23.MP2003_START_TIME_DOOR, ");
		queryString.append(" mp23.MP2003_FINISH_TIME_DOOR, ");
		queryString.append(" mp11.MP1001_RECTIFY_TIMES, ");
		queryString.append(" mp23.MP2003_STATUS ");
		queryString.append(" from MP2003 mp23,MP1001 mp11 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and mp23.MP2003_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM ");
		if(_data.getMP2003_EMPLOYEE_NUM()!= null && !_data.getMP2003_EMPLOYEE_NUM().equals("")){
			queryString.append(" and mp23.MP2003_EMPLOYEE_NUM = '" + _data.getMP2003_EMPLOYEE_NUM() + "' ");
		}
		if(_data.getMP2003_DATETIME() != null && !_data.getMP2003_DATETIME().equals("")){
			queryString.append(" and mp23.MP2003_DATETIME = '" + _data.getMP2003_DATETIME() + "' ");
		}
		
		Query query = session.createSQLQuery(queryString.toString());
		
		List<Object[]> list = query.list();
		session.close();
		
		MP2003 mp23 = new MP2003();
		List<MP2003> resultList = new ArrayList<MP2003>();
		for(int i=0,j=list.size();i<j;i++){
			mp23 = new MP2003();
			Object[] obj = list.get(i);
			
			// EMPLOYEE_NUM
			if(null == obj[0]){
				mp23.setMP2003_EMPLOYEE_NUM(null);
			}else{
				mp23.setMP2003_EMPLOYEE_NUM(obj[0].toString());
			}
			// EMPLOYEE_NAME
			if(null == obj[1]){
				mp23.setMP2003_EMPLOYEE_NAME(null);
			}else{
				mp23.setMP2003_EMPLOYEE_NAME(obj[1].toString());
			}
			// DATETIME
			if(null == obj[2]){
				mp23.setMP2003_DATETIME(null);
			}else{
				mp23.setMP2003_DATETIME(obj[2].toString());
			}
			// FINISH_TIME
			if(null == obj[3]){
				mp23.setMP2003_START_TIME(null);
			}else{
				mp23.setMP2003_START_TIME(obj[3].toString());
			}
			// FINISH_TIME
			if(null == obj[4]){
				mp23.setMP2003_FINISH_TIME(null);
			}else{
				mp23.setMP2003_FINISH_TIME(obj[4].toString());
			}
			// COMMENT
			if(null == obj[5]){
				mp23.setMP2003_COMMENT("");
			}else{
				mp23.setMP2003_COMMENT(obj[5].toString());
			}
			
			if(null == obj[6]){
				mp23.setMP2003_START_TIME_DOOR("");
			}else{
				mp23.setMP2003_START_TIME_DOOR(obj[6].toString());
			}
			if(null == obj[7]){
				mp23.setMP2003_FINISH_TIME_DOOR("");
			}else{
				mp23.setMP2003_FINISH_TIME_DOOR(obj[7].toString());
			}
			
			// RECTIFY TIMES
			if(null == obj[8]){
				mp23.setMP2003_RECTIFY_TIMES("0");
			}else{
				mp23.setMP2003_RECTIFY_TIMES(obj[8].toString());
			}
			
			//Status1
			if(null == obj[9]){
				mp23.setMP2003_STATUS("2");
			}else{
				mp23.setMP2003_STATUS(obj[9].toString());
			}
			
			resultList.add(mp23);
		}
		
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<MP2003> getPdfData(String empNum, String fromDate, String FinishDate,String depId,String attendenceStatus){
		Session session = getHibernateTemplate().getSessionFactory().openSession();

		StringBuffer queryString = new StringBuffer();
		queryString.append("select ");
		queryString.append(" mp11.MP1001_EMPLOYEE_NUM,");
		queryString.append(" mp11.MP1001_PREFERED_NAME,");
		queryString.append(" mp23.MP2003_DATETIME,");
		queryString.append(" mp23.MP2003_START_TIME,");
		queryString.append(" mp23.MP2003_FINISH_TIME,");
		queryString.append(" mp23.MP2003_COMMENT, ");
		queryString.append(" mp23.MP2003_START_TIME_DOOR, ");
		queryString.append(" mp23.MP2003_FINISH_TIME_DOOR, ");
		
		queryString.append(" mp11.MP1001_RECTIFY_TIMES, ");
		queryString.append(" mp23.MP2003_STATUS, ");

		queryString.append(" mp02.MP0002_DEPARTMENT_NAME"); //add by joe
		
		queryString.append(" from MP2003 mp23,MP1001 mp11,MP0002 mp02 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and mp02.MP0002_SEQ = mp11.MP1001_DEPARTMENT_ID ");
		queryString.append(" and mp23.MP2003_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM ");
		
		if(!empNum.equals("")){
			queryString.append(" and mp23.MP2003_EMPLOYEE_NUM = '" + empNum + "' ");
		}
		if(!fromDate.equals("")){
			queryString.append(" and mp23.MP2003_DATETIME >= '" + fromDate + "' ");
		}
		if(!FinishDate.equals("")){
			queryString.append(" and mp23.MP2003_DATETIME <= '" + FinishDate + "' ");
		}
		if(!depId.equals("")){
			queryString.append(" and mp11.MP1001_DEPARTMENT_ID = '" + depId + "' ");
		}
		if(attendenceStatus != null && !attendenceStatus.equals("") && !attendenceStatus.equals("-1")){
			Map<String,String> abnormalTypeMap = Constant.getAbnormalType();
			if(attendenceStatus.equals("1")){
				queryString.append(" and mp23.MP2003_COMMENT like '%" +abnormalTypeMap.get("1") + "%' ");
			}else if(attendenceStatus.equals("2")){
				queryString.append(" and mp23.MP2003_COMMENT like '%" +abnormalTypeMap.get("2") + "%' ");
			}else if(attendenceStatus.equals("3")){
				queryString.append(" and mp23.MP2003_COMMENT like '%" +abnormalTypeMap.get("3") + "%' ");
			}else if(attendenceStatus.equals("4")){
				queryString.append(" and mp23.MP2003_COMMENT like '%" +abnormalTypeMap.get("4") + "%' ");
			}else if(attendenceStatus.equals("5")){
				queryString.append(" and mp23.MP2003_COMMENT like '%In%' ");
			}else if(attendenceStatus.equals("6")){
				queryString.append(" and mp23.MP2003_COMMENT like '%Out%' ");
			}else if(attendenceStatus.equals("7")){
				queryString.append(" and mp23.MP2003_COMMENT like '%" +abnormalTypeMap.get("7") + "%' ");
			}
		}

		queryString.append(" order by mp11.MP1001_EMPLOYEE_NUM,mp23.MP2003_DATETIME ");
		Query query = session.createSQLQuery(queryString.toString());
		
		List<Object[]> list = query.list();
		session.close();
		
		MP2003 mp23 = new MP2003();
		List<MP2003> resultList = new ArrayList<MP2003>();
		for(int i=0,j=list.size();i<j;i++){
			mp23 = new MP2003();
			Object[] obj = list.get(i);
			
			// EMPLOYEE_NUM
			if(null == obj[0]){
				mp23.setMP2003_EMPLOYEE_NUM(null);
			}else{
				mp23.setMP2003_EMPLOYEE_NUM(obj[0].toString());
			}
			// EMPLOYEE_NAME
			if(null == obj[1]){
				mp23.setMP2003_EMPLOYEE_NAME(null);
			}else{
				mp23.setMP2003_EMPLOYEE_NAME(obj[1].toString());
			}
			// DATETIME
			if(null == obj[2]){
				mp23.setMP2003_DATETIME(null);
			}else{
				mp23.setMP2003_DATETIME(obj[2].toString().substring(0,10));
			}
			// FINISH_TIME
			if(null == obj[3]){
				mp23.setMP2003_START_TIME(null);
			}else{
				mp23.setMP2003_START_TIME(obj[3].toString().substring(11,16));
			}
			// FINISH_TIME
			if(null == obj[4]){
				mp23.setMP2003_FINISH_TIME(null);
			}else{
				mp23.setMP2003_FINISH_TIME(obj[4].toString().substring(11,16));
			}
			// COMMENT
			if(null == obj[5]){
				mp23.setMP2003_COMMENT("");
			}else{
				mp23.setMP2003_COMMENT(obj[5].toString());
			}
			
			if(null == obj[6]){
				mp23.setMP2003_START_TIME_DOOR("");
			}else{
				mp23.setMP2003_START_TIME_DOOR(obj[6].toString());
			}
			if(null == obj[7]){
				mp23.setMP2003_FINISH_TIME_DOOR("");
			}else{
				mp23.setMP2003_FINISH_TIME_DOOR(obj[7].toString());
			}
			
			// RECTIFY TIMES
			if(null == obj[8]){
				mp23.setMP2003_RECTIFY_TIMES("0");
			}else{
				mp23.setMP2003_RECTIFY_TIMES(obj[8].toString());
			}
			//Status1
			if(null == obj[9]){
				mp23.setMP2003_STATUS("2");
			}else{
				mp23.setMP2003_STATUS(obj[9].toString());
			}
			
			//Department Name
			if(null == obj[10]){
				mp23.setMP2003_DEPARTMENT_NAME("");
			}else{
				mp23.setMP2003_DEPARTMENT_NAME(obj[10].toString());
			}
			
			resultList.add(mp23);
		}
		
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public List<MP2003> findByPropertysPage(MP1001 employeeData,int pageNum,int pageCount,String empNum, String fromDate, String FinishDate,String depId,String attendenceStatus) {
		try{
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			//String role = employeeData.getMP1001_GROUP();

			StringBuffer queryString = new StringBuffer();
			queryString.append("select ");
			queryString.append(" mp11.MP1001_EMPLOYEE_NUM,");
			queryString.append(" mp11.MP1001_PREFERED_NAME,");
			queryString.append(" mp23.MP2003_DATETIME,");
			queryString.append(" mp23.MP2003_START_TIME,");
			queryString.append(" mp23.MP2003_FINISH_TIME,");
			queryString.append(" mp23.MP2003_COMMENT, ");
			queryString.append(" mp23.MP2003_START_TIME_DOOR, ");
			queryString.append(" mp23.MP2003_FINISH_TIME_DOOR, ");
			
			queryString.append(" mp11.MP1001_RECTIFY_TIMES, ");
			queryString.append(" mp11.MP1001_SURNAME, ");
			queryString.append(" mp11.MP1001_DEPARTMENT_ID, ");
			queryString.append(" mp23.MP2003_STATUS ");
			
			queryString.append(" from MP2003 mp23,MP1001 mp11 ");
			queryString.append(" where 1=1 ");
			queryString.append(" and mp23.MP2003_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM ");
			
			//if(!empNum.equals("") && !role.equals("1")){
			if(!empNum.equals("") && (employeeData.getDepartmentAccess().equals("1")||employeeData.getFullAccess().equals("1"))){
				queryString.append(" and mp23.MP2003_EMPLOYEE_NUM = '" + empNum + "' ");
			}
			if(!fromDate.equals("")){
				queryString.append(" and mp23.MP2003_DATETIME >= '" + fromDate + "' ");
			}
			if(!FinishDate.equals("")){
				queryString.append(" and mp23.MP2003_DATETIME <= '" + FinishDate + "' ");
			}
			
//			if(role.equals("1")){// 一般人员
//				queryString.append(" and mp23.MP2003_EMPLOYEE_NUM = '" + employeeData.getMP1001_EMPLOYEE_NUM() + "' ");
//			}else if(role.equals("2")){//部门主管
//				queryString.append(" and mp11.MP1001_DEPARTMENT_ID = '" + employeeData.getMP1001_DEPARTMENT_ID() + "' ");
//			}else if(role.equals("3") || role.equals("4") || role.equals("5")){//HR主管、Director、超级管理员
//				if(!depId.equals("")){
//					queryString.append(" and mp11.MP1001_DEPARTMENT_ID = '" + depId + "' ");
//				}
//			}			
			
			if(employeeData.getFullAccess().equals("1")){//全权限
				if(!depId.equals("")){
					queryString.append(" and mp11.MP1001_DEPARTMENT_ID = '" + depId + "' ");
				}
			}else{
				if(employeeData.getDepartmentAccess().equals("1")){//部门权限
					queryString.append(" and mp11.MP1001_DEPARTMENT_ID = '" + employeeData.getMP1001_DEPARTMENT_ID() + "' ");
				}else{//个人权限
					queryString.append(" and mp23.MP2003_EMPLOYEE_NUM = '" + employeeData.getMP1001_EMPLOYEE_NUM() + "' ");
				}
			}
			if(attendenceStatus != null && !attendenceStatus.equals("") && !attendenceStatus.equals("-1")){
				Map<String,String> abnormalTypeMap = Constant.getAbnormalType();
				if(attendenceStatus.equals("1")){
					queryString.append(" and mp23.MP2003_COMMENT like '%" +abnormalTypeMap.get("1") + "%' ");
				}else if(attendenceStatus.equals("2")){
					queryString.append(" and mp23.MP2003_COMMENT like '%" +abnormalTypeMap.get("2") + "%' ");
				}else if(attendenceStatus.equals("3")){
					queryString.append(" and mp23.MP2003_COMMENT like '%" +abnormalTypeMap.get("3") + "%' ");
				}else if(attendenceStatus.equals("4")){
					queryString.append(" and mp23.MP2003_COMMENT like '%" +abnormalTypeMap.get("4") + "%' ");
				}else if(attendenceStatus.equals("5")){
					queryString.append(" and mp23.MP2003_COMMENT like '%In%' ");
				}else if(attendenceStatus.equals("6")){
					queryString.append(" and mp23.MP2003_COMMENT like '%Out%' ");
				}else if(attendenceStatus.equals("7")){
					queryString.append(" and mp23.MP2003_COMMENT like '%" +abnormalTypeMap.get("7") + "%' ");
				}
			}

			queryString.append(" order by mp23.MP2003_DATETIME desc,mp11.MP1001_EMPLOYEE_NUM asc ");
			Query query = session.createSQLQuery(queryString.toString());
			
			if(pageNum > 0 && pageCount > 0){
				query.setFirstResult((pageNum -1)*pageCount);
				query.setMaxResults(pageCount);
			}
			
			List<Object[]> list = query.list();
			session.close();
			
			MP2003 mp23 = new MP2003();
			List<MP2003> resultList = new ArrayList<MP2003>();
			for(int i=0,j=list.size();i<j;i++){
				mp23 = new MP2003();
				Object[] obj = list.get(i);
				
				// EMPLOYEE_NUM
				if(null == obj[0]){
					mp23.setMP2003_EMPLOYEE_NUM(null);
				}else{
					mp23.setMP2003_EMPLOYEE_NUM(obj[0].toString());
				}
				// EMPLOYEE_NAME
				if(null == obj[1]){
					mp23.setMP2003_EMPLOYEE_NAME(null);
				}else{
					mp23.setMP2003_EMPLOYEE_NAME(obj[1].toString() + " " + obj[9].toString() );
				}
				// DATETIME
				if(null == obj[2]){
					mp23.setMP2003_DATETIME(null);
				}else{
					mp23.setMP2003_DATETIME(obj[2].toString().substring(0,10));
				}
				// FINISH_TIME
				if(null == obj[3]){
					mp23.setMP2003_START_TIME(null);
				}else{
					mp23.setMP2003_START_TIME(obj[3].toString().substring(11,16));
				}
				// FINISH_TIME
				if(null == obj[4]){
					mp23.setMP2003_FINISH_TIME(null);
				}else{
					mp23.setMP2003_FINISH_TIME(obj[4].toString().substring(11,16));
				}
				// COMMENT
				if(null == obj[5]){	
					mp23.setMP2003_COMMENT("");
				}else{
					mp23.setMP2003_COMMENT(obj[5].toString());
				}
				
				if(null == obj[6]){
					mp23.setMP2003_START_TIME_DOOR("");
				}else{
					mp23.setMP2003_START_TIME_DOOR(obj[6].toString());
				}
				if(null == obj[7]){
					mp23.setMP2003_FINISH_TIME_DOOR("");
				}else{
					mp23.setMP2003_FINISH_TIME_DOOR(obj[7].toString());
				}
				
				// RECTIFY TIMES
				if(null == obj[8]){
					mp23.setMP2003_RECTIFY_TIMES("0");
				}else{
					mp23.setMP2003_RECTIFY_TIMES(obj[8].toString());
				}
				
				// DEPARTMENT ID
				if(null == obj[10]){
					mp23.setMP2003_DEPARTMENT_ID("");
				}else{
					mp23.setMP2003_DEPARTMENT_ID(obj[10].toString());
				}
				//Status1
				if(null == obj[11]){
					mp23.setMP2003_STATUS("2");
				}else{
					mp23.setMP2003_STATUS(obj[11].toString());
				}
				
				resultList.add(mp23);
			}
			
			return resultList;
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	
	@SuppressWarnings("unchecked")
	public int getRowCountByPropertys(MP1001 employeeData,String empNum, String fromDate, String FinishDate,String depId,String attendenceStatus){
		try{
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			//String role = employeeData.getMP1001_GROUP();

			StringBuffer queryString = new StringBuffer();
			queryString.append("select ");
			queryString.append(" mp23.MP2003_EMPLOYEE_NUM ");
			
//			if(role.equals("1")){// 一般人员
//				queryString.append(" from MP2003 mp23 ");
//				queryString.append(" where 1=1 ");
//				queryString.append(" and mp23.MP2003_EMPLOYEE_NUM = '" + employeeData.getMP1001_EMPLOYEE_NUM() + "' ");
//			}else if(role.equals("2")){//部门主管
//				queryString.append(" from MP2003 mp23,MP1001 mp11 ");
//				queryString.append(" where 1=1 ");
//				queryString.append(" and mp23.MP2003_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM");
//				queryString.append(" and mp11.MP1001_DEPARTMENT_ID = '" + employeeData.getMP1001_DEPARTMENT_ID() + "' ");
//			}else if(role.equals("3") || role.equals("4") || role.equals("5")){//HR主管、Director、超级管理员
//				queryString.append(" from MP2003 mp23,MP1001 mp11 ");
//				queryString.append(" where 1=1 ");
//				queryString.append(" and mp23.MP2003_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM");
//				if(!depId.equals("")){
//					queryString.append(" and mp11.MP1001_DEPARTMENT_ID = '" + depId + "' ");
//				}
//			}
			
			if(employeeData.getFullAccess().equals("1")){//全权限
				queryString.append(" from MP2003 mp23,MP1001 mp11 ");
				queryString.append(" where 1=1 ");
				queryString.append(" and mp23.MP2003_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM");
				if(!depId.equals("")){
					queryString.append(" and mp11.MP1001_DEPARTMENT_ID = '" + depId + "' ");
				}
			}else{
				if(employeeData.getDepartmentAccess().equals("1")){//部门级别权限
					queryString.append(" from MP2003 mp23,MP1001 mp11 ");
					queryString.append(" where 1=1 ");
					queryString.append(" and mp23.MP2003_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM");
					queryString.append(" and mp11.MP1001_DEPARTMENT_ID = '" + employeeData.getMP1001_DEPARTMENT_ID() + "' ");
				}else{//个人权限
					queryString.append(" from MP2003 mp23 ");
					queryString.append(" where 1=1 ");
					queryString.append(" and mp23.MP2003_EMPLOYEE_NUM = '" + employeeData.getMP1001_EMPLOYEE_NUM() + "' ");
				}
			}
			
			//if(!empNum.equals("") && !role.equals("1")){
			if(!empNum.equals("") && (employeeData.getDepartmentAccess().equals("1")||employeeData.getFullAccess().equals("1"))){
				queryString.append(" and mp23.MP2003_EMPLOYEE_NUM = '" + empNum + "' ");
			}
			if(!fromDate.equals("")){
				queryString.append(" and mp23.MP2003_DATETIME >= '" + fromDate + "' ");
			}
			if(!FinishDate.equals("")){
				queryString.append(" and mp23.MP2003_DATETIME <= '" + FinishDate + "' ");
			}
			
			if(attendenceStatus != null && !attendenceStatus.equals("") && !attendenceStatus.equals("-1")){
				Map<String,String> abnormalTypeMap = Constant.getAbnormalType();
				if(attendenceStatus.equals("1")){
					queryString.append(" and mp23.MP2003_COMMENT like '%" +abnormalTypeMap.get("1") + "%' ");
				}else if(attendenceStatus.equals("2")){
					queryString.append(" and mp23.MP2003_COMMENT like '%" +abnormalTypeMap.get("2") + "%' ");
				}else if(attendenceStatus.equals("3")){
					queryString.append(" and mp23.MP2003_COMMENT like '%" +abnormalTypeMap.get("3") + "%' ");
				}else if(attendenceStatus.equals("4")){
					queryString.append(" and mp23.MP2003_COMMENT like '%" +abnormalTypeMap.get("4") + "%' ");
				}else if(attendenceStatus.equals("5")){
					queryString.append(" and mp23.MP2003_COMMENT like '%In%' ");
				}else if(attendenceStatus.equals("6")){
					queryString.append(" and mp23.MP2003_COMMENT like '%Out%' ");
				}else if(attendenceStatus.equals("7")){
					queryString.append(" and mp23.MP2003_COMMENT like '%" +abnormalTypeMap.get("7") + "%' ");
				}
			}
					
			Query query = session.createSQLQuery(queryString.toString());
			
			List<Object[]> list = query.list();
			session.close();
			
			return list.size();
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	
	@Override
	public List<MP2003> getTotalUnusualRecords(Map<String, String> propMap){ //for abnormal/late/early records report
		List<MP2003> rs = new ArrayList<MP2003>();
		
		StringBuffer sb = new StringBuffer();
		/**
		 * select MP1001_EMPLOYEE_NUM,MP1001_PREFERED_NAME,MP1001_SURNAME,MP0002_DEPARTMENT_NAME, MP2003_DATETIME, MP2003_START_TIME, MP2003_FINISH_TIME, MP2003_COMMENT
 from MP2003, MP0002, MP1001 
 where MP1001_EMPLOYEE_NUM=MP2003_EMPLOYEE_NUM and MP1001_DEPARTMENT_ID=MP0002_SEQ and 
  (MP2003_COMMENT like '%Early%' or 
  MP2003_COMMENT like '%Abnormal%' or 
  MP2003_COMMENT like '%Late%' ) and
  MP2003_DATETIME >= '2014-05-01' and 
  MP2003_DATETIME <= '2014-05-30'
 order by MP1001_EMPLOYEE_NUM ASC;
		 */
		sb.append("select MP1001_EMPLOYEE_NUM,MP1001_PREFERED_NAME,MP1001_SURNAME,MP0002_DEPARTMENT_NAME, MP2003_DATETIME, MP2003_START_TIME, MP2003_FINISH_TIME, MP2003_COMMENT ");
		sb.append(" from MP2003, MP0002, MP1001 ");
		sb.append(" where MP1001_EMPLOYEE_NUM=MP2003_EMPLOYEE_NUM and MP1001_DEPARTMENT_ID=MP0002_SEQ and ");
		sb.append(" MP1001_STATUS in ('2', '1') and ");
		sb.append(" (MP2003_COMMENT like '%Early%' or ");
		sb.append(" MP2003_COMMENT like '%Abnormal%' or ");
		sb.append(" MP2003_COMMENT like '%Late%' ) and ");
		if(propMap.containsKey("from")){
			sb.append(" MP2003_DATETIME >= '" + propMap.get("from") + "' and ");
		}
		if(propMap.containsKey("to")){
			sb.append(" MP2003_DATETIME <= '" + propMap.get("to") + "' ");
		}
//		if(propMap.containsKey()){
//			//MP1001_EMPLOYEE_NUM not in ('M0001')
//		}
		sb.append(" order by MP1001_EMPLOYEE_NUM, MP0002_DEPARTMENT_NAME ASC");
		
		//Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		session.beginTransaction();
//		Query query = session.createQuery(sb.toString());
		Query query = session.createSQLQuery(sb.toString());
		List<Object[]> objList = query.list();
		session.getTransaction().commit();
		session.close();		
		
		for(int i = 0, j = objList.size(); i < j; i++){
			MP2003 mp23 = new MP2003();
			Object[] obj = objList.get(i);
			
			if(null == obj[0]){
				mp23.setMP2003_EMPLOYEE_NUM("");
			}
			else{
				mp23.setMP2003_EMPLOYEE_NUM(obj[0].toString());
			}
			if(null == obj[1]){
				mp23.setMP2003_EMPLOYEE_NAME("");
			}
			else{
				mp23.setMP2003_EMPLOYEE_NAME(obj[1].toString());	
			}
			if(null == obj[2]){
				mp23.setMP2003_EMPLOYEE_SURNAME("");
			}
			else{
				mp23.setMP2003_EMPLOYEE_SURNAME(obj[2].toString());	
			}
			if(null == obj[3]){
				mp23.setMP2003_DEPARTMENT_NAME("");
			}
			else{
				mp23.setMP2003_DEPARTMENT_NAME(obj[3].toString());
			}
			if(null == obj[4]){
				mp23.setMP2003_DATETIME("");
			}
			else{
				mp23.setMP2003_DATETIME(obj[4].toString());
			}
			if(null == obj[5]){
				mp23.setMP2003_START_TIME("");
			}
			else{
				mp23.setMP2003_START_TIME(obj[5].toString());
			}
			if(null == obj[6]){
				mp23.setMP2003_FINISH_TIME("");
			}
			else{
				mp23.setMP2003_FINISH_TIME(obj[6].toString());
			}
			if(null == obj[7]){
				mp23.setMP2003_COMMENT("");
			}
			else{
				mp23.setMP2003_COMMENT(obj[7].toString());	
			}

			rs.add(mp23);
		}


		return rs;
	}

	@Override
	public List<MP2003> getAbnormalReptData(Map<String, String> propMap) {
		// TODO Auto-generated method stub
		List<MP2003> rs = new ArrayList<MP2003>();
		
		StringBuffer sb = new StringBuffer();
		/**
		 * select MP1001_EMPLOYEE_NUM,MP1001_PREFERED_NAME,MP1001_SURNAME,MP0002_DEPARTMENT_NAME, MP2003_DATETIME, MP2003_START_TIME, MP2003_FINISH_TIME, MP2003_COMMENT
 from MP2003, MP0002, MP1001 
 where MP1001_EMPLOYEE_NUM=MP2003_EMPLOYEE_NUM and MP1001_DEPARTMENT_ID=MP0002_SEQ and 
  (MP2003_COMMENT like '%Early%' or 
  MP2003_COMMENT like '%Abnormal%' or 
  MP2003_COMMENT like '%Late%' ) and
  MP2003_DATETIME >= '2014-05-01' and 
  MP2003_DATETIME <= '2014-05-30'
 order by MP1001_EMPLOYEE_NUM ASC;
		 */
		sb.append("select MP1001_EMPLOYEE_NUM,MP1001_PREFERED_NAME,MP1001_SURNAME,MP0002_DEPARTMENT_NAME, MP2003_DATETIME, MP2003_START_TIME, MP2003_FINISH_TIME, MP2003_COMMENT ");
		sb.append(" from MP2003, MP0002, MP1001 ");
		sb.append(" where MP1001_EMPLOYEE_NUM=MP2003_EMPLOYEE_NUM and MP1001_DEPARTMENT_ID=MP0002_SEQ and ");
		sb.append(" MP1001_STATUS in ('2', '1') and ");
		sb.append(" MP2003_COMMENT like '%Abnormal%' and ");
		if(propMap.containsKey("from")){
			sb.append(" MP2003_DATETIME >= '" + propMap.get("from") + "' and ");
		}
		if(propMap.containsKey("to")){
			sb.append(" MP2003_DATETIME <= '" + propMap.get("to") + "' ");
		}
//		if(propMap.containsKey()){
//			//MP1001_EMPLOYEE_NUM not in ('M0001')
//		}
		sb.append(" order by MP1001_EMPLOYEE_NUM, MP0002_DEPARTMENT_NAME ASC");
		
		//Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		session.beginTransaction();
//		Query query = session.createQuery(sb.toString());
		Query query = session.createSQLQuery(sb.toString());
		List<Object[]> objList = query.list();
		session.getTransaction().commit();
		session.close();		
		
		for(int i = 0, j = objList.size(); i < j; i++){
			MP2003 mp23 = new MP2003();
			Object[] obj = objList.get(i);
			
			if(null == obj[0]){
				mp23.setMP2003_EMPLOYEE_NUM("");
			}
			else{
				mp23.setMP2003_EMPLOYEE_NUM(obj[0].toString());
			}
			if(null == obj[1]){
				mp23.setMP2003_EMPLOYEE_NAME("");
			}
			else{
				mp23.setMP2003_EMPLOYEE_NAME(obj[1].toString());	
			}
			if(null == obj[2]){
				mp23.setMP2003_EMPLOYEE_SURNAME("");
			}
			else{
				mp23.setMP2003_EMPLOYEE_SURNAME(obj[2].toString());	
			}
			if(null == obj[3]){
				mp23.setMP2003_DEPARTMENT_NAME("");
			}
			else{
				mp23.setMP2003_DEPARTMENT_NAME(obj[3].toString());
			}
			if(null == obj[4]){
				mp23.setMP2003_DATETIME("");
			}
			else{
				mp23.setMP2003_DATETIME(obj[4].toString());
			}
			if(null == obj[5]){
				mp23.setMP2003_START_TIME("");
			}
			else{
				mp23.setMP2003_START_TIME(obj[5].toString());
			}
			if(null == obj[6]){
				mp23.setMP2003_FINISH_TIME("");
			}
			else{
				mp23.setMP2003_FINISH_TIME(obj[6].toString());
			}
			if(null == obj[7]){
				mp23.setMP2003_COMMENT("");
			}
			else{
				mp23.setMP2003_COMMENT(obj[7].toString());	
			}

			rs.add(mp23);
		}

		return rs;
	}
	@SuppressWarnings("unchecked")
	public List<MP2003> getLateEarlyReptData(Map<String, String> propMap){
		List<MP2003> rs = new ArrayList<MP2003>();
		StringBuffer sb = new StringBuffer();
		sb.append("select MP2003_EMPLOYEE_NUM, MP1001_PREFERED_NAME, MP1001_SURNAME, MP0002_DEPARTMENT_NAME, MP2003_DATETIME, " +
				"MP2003_START_TIME, MP2003_FINISH_TIME,MP2003_COMMENT ");
		sb.append(" from MP2003 m23, MP1001 mp11,MP0002 m02 ");
		sb.append(" where (MP2003_COMMENT like '%Late%' or MP2003_COMMENT like '%Early%') ");
		sb.append(" and MP1001_STATUS in ('2', '1') ");
		sb.append(" and MP1001_EMPLOYEE_NUM=MP2003_EMPLOYEE_NUM and MP1001_DEPARTMENT_ID=MP0002_SEQ ");
		sb.append(" and MP2003_EMPLOYEE_NUM not in ('M0207','M0066','M0265','M0514','M0531','M0049'," +
				"'M0249','M0105','M0456','M0078','M0453','m0482') and "); //not include specified employees
		if(propMap.containsKey("from")){
			sb.append(" MP2003_DATETIME >= '" + propMap.get("from") + "' and ");
		}
		if(propMap.containsKey("to")){
			sb.append(" MP2003_DATETIME <= '" + propMap.get("to") + "' ");
		}
		sb.append(" order by MP0002_DEPARTMENT_NAME, MP2003_DATETIME ");
		
		Session ss = getHibernateTemplate().getSessionFactory().openSession();
		ss.beginTransaction();

		Query query = ss.createSQLQuery(sb.toString());
		List<Object[]> objList = query.list();
		ss.getTransaction().commit();
		ss.close();
		
		for(int i = 0, j = objList.size(); i < j; i++){
			MP2003 mp23 = new MP2003();
			Object[] obj = objList.get(i);
			
			if(null == obj[0]){
				mp23.setMP2003_EMPLOYEE_NUM("");
			}
			else{
				mp23.setMP2003_EMPLOYEE_NUM(obj[0].toString());
			}
			if(null == obj[1]){
				mp23.setMP2003_EMPLOYEE_NAME("");
			}
			else{
				mp23.setMP2003_EMPLOYEE_NAME(obj[1].toString());	
			}
			if(null == obj[2]){
				mp23.setMP2003_EMPLOYEE_SURNAME("");
			}
			else{
				mp23.setMP2003_EMPLOYEE_SURNAME(obj[2].toString());	
			}
			if(null == obj[3]){
				mp23.setMP2003_DEPARTMENT_NAME("");
			}
			else{
				mp23.setMP2003_DEPARTMENT_NAME(obj[3].toString());
			}
			if(null == obj[4]){
				mp23.setMP2003_DATETIME("");
			}
			else{
				mp23.setMP2003_DATETIME(obj[4].toString());
			}
			if(null == obj[5]){
				mp23.setMP2003_START_TIME("");
			}
			else{
				mp23.setMP2003_START_TIME(obj[5].toString());
			}
			if(null == obj[6]){
				mp23.setMP2003_FINISH_TIME("");
			}
			else{
				mp23.setMP2003_FINISH_TIME(obj[6].toString());
			}
			if(null == obj[7]){
				mp23.setMP2003_COMMENT("");
			}
			else{
				mp23.setMP2003_COMMENT(obj[7].toString());	
			}
			
			rs.add(mp23);
		}
		
		return rs;
	}
}