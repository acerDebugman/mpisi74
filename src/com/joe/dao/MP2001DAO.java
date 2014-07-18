package com.joe.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.joe.model.MP1001;
import com.joe.model.MP2001;

public class MP2001DAO extends HibernateDaoSupport implements IMP2001DAO {

	public void save(MP2001 mp2001) {
		if (mp2001 != null) {
			getHibernateTemplate().save(mp2001);
		}
	}

	public void delete(MP2001 mp2001) {
		getHibernateTemplate().delete(mp2001);
	}

	public MP2001 findById(String leaveNum) {
		return (MP2001) getHibernateTemplate().get("entity.MP2001", leaveNum);
	}

	@SuppressWarnings("unchecked")
	public List<MP2001> findAll() {
		return getHibernateTemplate().find("from MP2001 where MP2001_STATUS = '0' ");
	}

	public void update(MP2001 mp2001) {
		getHibernateTemplate().update(mp2001);
	}
	
	public List<MP2001> findByProperty(String name, String value, boolean flag) {
		try{
			Calendar calendar = new GregorianCalendar();
			int month = calendar.get(Calendar.MONTH);
			int year1 = calendar.get(Calendar.YEAR);
			int year2 = 0;
			
			StringBuffer queryString = new StringBuffer();
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			
			queryString.append(" from MP2001 mp2, MP1001 mp11 ");
			queryString.append(" where 1=1 ");
			queryString.append(" and mp2.MP2001_STATUS = '0' " );
			queryString.append(" and mp2. MP2001_APPROVAL in ('1','3') " );
			queryString.append(" and mp2." + name + " = '" + value + "' ");
			queryString.append(" and mp11.MP1001_STATUS in('1','2') " );
			queryString.append(" and mp11.MP1001_EMPLOYEE_NUM = mp2.MP2001_EMPLOYEE_NUM ");
			
			String date1 = "";
			String date2 = "";
			if(0 == month || 1 == month){
				year2 = year1 - 1;			
				
				date1 = String.valueOf(year1) + "-" + "03-01";
				date2 = String.valueOf(year2) + "-" + "03-01";
				queryString.append(" and MP2001_FROM_DATETIME >= '" + date2 + "' ");
				queryString.append(" and MP2001_FROM_DATETIME < '" + date1 + "' ");
			}else{
				year2 = year1 + 1;
				
				date1 = String.valueOf(year1) + "-" + "03-01";
				date2 = String.valueOf(year2) + "-" + "03-01";
				queryString.append(" and MP2001_FROM_DATETIME >= '" + date1 + "' ");
				queryString.append(" and MP2001_FROM_DATETIME < '" + date2 + "' ");
			}
// 此段逻辑废除原因：只要请假不管有没有批准都会扣除假期，之前是批准后才会扣除假期
//			if(flag == true){
//				queryString.append(" and MP2001_APPROVAL = '3' ");
//				
//			}
		    Query query = session.createQuery(queryString.toString());
		    @SuppressWarnings("rawtypes")
			List list = query.list();
		        
			List<MP2001> retList = new ArrayList<MP2001>();

			for(int m=0,n=list.size(); m < n; m++){
				Object[] obj = (Object[]) list.get(m);
				MP2001 mp2001 = (MP2001)obj[0];
				MP1001 mp1001 = (MP1001)obj[1];
				mp2001.setMP2001_DEPARTMENT_ID(mp1001.getMP1001_DEPARTMENT_ID());
				mp2001.setMP2001_GROUP(mp1001.getMP1001_GROUP());
				retList.add(mp2001);
			}

			session.close();
			
		    return retList;
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<MP2001> findByDate(String empNum,String date){
		StringBuffer queryString = new StringBuffer();
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		
		queryString.append(" from MP2001 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and MP2001_STATUS = '0' " );
		queryString.append(" and MP2001_APPROVAL = '3' " );
		queryString.append(" and MP2001_EMPLOYEE_NUM = '" + empNum + "' ");
		queryString.append(" and Convert(char(10),MP2001_FROM_DATETIME,120) <= '" + date + "' ");
		queryString.append(" and Convert(char(10),MP2001_TO_DATETIME,120) >= '" + date + "' ");
		//queryString.append(" and left(Convert(varchar,MP2001_TO_DATETIME,120),10) >= '" + date + "' ");

		Query query = session.createQuery(queryString.toString());
	    @SuppressWarnings("rawtypes")
		List list = query.list();
	    
	    session.close();
		
	    return list;
	}
	
	@SuppressWarnings("unchecked")
	public boolean checkLeaveDay(String _fromDate, String _toDate, String _empNum){
		boolean ret = false;
		try{
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			
			StringBuffer queryString = new StringBuffer();
			queryString.append("select *  ");
			queryString.append(" from MP2001 ");
			queryString.append(" where 1=1 ");
			queryString.append(" and MP2001_STATUS = '0' " );
			queryString.append(" and MP2001_APPROVAL in ('1','3') ");
			queryString.append(" and MP2001_EMPLOYEE_NUM = '" + _empNum + "'");
			queryString.append(" and ( MP2001_FROM_DATETIME <'" + _fromDate + "' ");
			queryString.append(" and MP2001_TO_DATETIME > '" + _fromDate + "' ");
			queryString.append(" or MP2001_FROM_DATETIME <'" + _toDate + "'");
			queryString.append(" and MP2001_TO_DATETIME > '" + _toDate + "'");
			queryString.append(" ) ");
			
			Query query = session.createSQLQuery(queryString.toString());
			List<Object[]> list = query.list();
			session.close();
			
			if(list.size() > 0){
				ret = true;
			}
			return ret;
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	
	public List<MP2001> findByProperty(Map<String, String> propertyMap,MP1001 employeeData){
		try{
			StringBuffer queryString = new StringBuffer();
			String role = employeeData.getMP1001_GROUP();

			queryString.append(" from MP2001 as mp2, MP1001 mp11 ");
			queryString.append(" where 1=1 ");
			queryString.append(" and mp11.MP1001_STATUS in('1','2') " );
			queryString.append(" and mp2.MP2001_STATUS = '0' " );
			queryString.append(" and mp11.MP1001_EMPLOYEE_NUM = mp2.MP2001_EMPLOYEE_NUM ");
			
			String optDepartment = "0";
			String optAll = "0";

			if(propertyMap.containsKey("OPT_DEPARTMENT") && propertyMap.get("OPT_DEPARTMENT") != null){
				optDepartment = propertyMap.get("OPT_DEPARTMENT");
			}
			if(propertyMap.containsKey("OPT_ALL") && propertyMap.get("OPT_ALL") != null){
				optAll = propertyMap.get("OPT_ALL");
			}
			
			if(!optAll.equals("1")){
				if(optDepartment.equals("1")){
					queryString.append(" and mp11.MP1001_DEPARTMENT_ID = '" + employeeData.getMP1001_DEPARTMENT_ID() + "'");
				}else{
					queryString.append(" and (mp2.MP2001_EMPLOYEE_NUM = '" + employeeData.getMP1001_EMPLOYEE_NUM() + "' or mp2.MP2001_ACTING_APPLICATION_PERSON = '" + employeeData.getMP1001_EMPLOYEE_NUM() + "' )");
				}
			}

//            if(role.equals("1")){
//            	//queryString.append(" and (mp2.MP2001_EMPLOYEE_NUM = '" + employeeData.getMP1001_EMPLOYEE_NUM() + "'");
//            	queryString.append(" and (mp2.MP2001_EMPLOYEE_NUM = '" + employeeData.getMP1001_EMPLOYEE_NUM() + "' or mp2.MP2001_ACTING_APPLICATION_PERSON = '" + employeeData.getMP1001_EMPLOYEE_NUM() + "' )");
//            }else if(role.equals("2")){
//            	queryString.append(" and mp11.MP1001_DEPARTMENT_ID = '" + employeeData.getMP1001_DEPARTMENT_ID() + "'");
//            }else if(role.equals("3") || role.equals("4") || role.equals("5")){
//            	// 无限制条件
//            }

			// 判断“编码”
			if(propertyMap.containsKey("NUM") && propertyMap.get("NUM") != null && !propertyMap.get("NUM").equals("")){
				queryString.append(" and mp2.MP2001_NUM like '%" + propertyMap.get("NUM") + "%'");
			}
			// 判断“编码”
			if(propertyMap.containsKey("EMP_NUM") && propertyMap.get("EMP_NUM") != null && !propertyMap.get("EMP_NUM").equals("") && !role.equals("1")){
			//if(propertyMap.containsKey("EMP_NUM") && propertyMap.get("EMP_NUM") != null && !propertyMap.get("EMP_NUM").equals("")){
				queryString.append(" and mp2.MP2001_EMPLOYEE_NUM = '" + propertyMap.get("EMP_NUM") + "' ");
			}
			// 判断“请假类型”
			if(propertyMap.containsKey("TYPE") && propertyMap.get("TYPE") != null && !propertyMap.get("TYPE").equals("") && !propertyMap.get("TYPE").equals("0")){
				queryString.append(" and mp2.MP2001_LEAVE_TYPE = '" + propertyMap.get("TYPE") + "'");
			}
			// 判断“批准状态”
			if(propertyMap.containsKey("APPROVAL") && propertyMap.get("APPROVAL") != null && !propertyMap.get("APPROVAL").equals("") && !propertyMap.get("APPROVAL").equals("0")){
				queryString.append(" and mp2.MP2001_APPROVAL = '" + propertyMap.get("APPROVAL") + "'");
			}
			// 判断“开始日期”、“结束日期”
			if(propertyMap.containsKey("FROM") && !propertyMap.get("FROM").equals("")){
				queryString.append(" and mp2.MP2001_FROM_DATETIME >= '" + propertyMap.get("FROM") + "'");
			}
			if(propertyMap.containsKey("TO") && !propertyMap.get("TO").equals("")){
				queryString.append(" and mp2.MP2001_FROM_DATETIME <= '" + propertyMap.get("TO") + "'");
			}
			if(propertyMap.containsKey("DEPARTMENT") && propertyMap.get("DEPARTMENT") != null && !propertyMap.get("DEPARTMENT").equals("")){
				queryString.append(" and mp11.MP1001_DEPARTMENT_ID = '" + propertyMap.get("DEPARTMENT") + "'");
			}
			if(propertyMap.containsKey("JOB_TITLE") && propertyMap.get("JOB_TITLE") != null && !propertyMap.get("JOB_TITLE").equals("")){
				if(propertyMap.get("JOB_TITLE").equals("1")){
					queryString.append(" and mp11.MP1001_POSITION = '1'");
				}else if(propertyMap.get("JOB_TITLE").equals("2")){
					queryString.append(" and mp11.MP1001_POSITION not in ('1','92','93') ");	
				}
			}
			
			List<MP2001> retList = new ArrayList<MP2001>();
			@SuppressWarnings("rawtypes")
			List temp = getHibernateTemplate().find(queryString.toString());
			for(int m=0,n=temp.size(); m < n; m++){
				Object[] obj = (Object[]) temp.get(m);
				MP2001 mp2001 = (MP2001)obj[0];
				MP1001 mp1001 = (MP1001)obj[1];
				mp2001.setMP2001_DEPARTMENT_ID(mp1001.getMP1001_DEPARTMENT_ID());
				mp2001.setMP2001_GROUP(mp1001.getMP1001_GROUP());
				retList.add(mp2001);
			}
			return retList;
			//return getHibernateTemplate().find(queryString.toString());
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<MP2001> findByPropertyPage(Map<String, String> propertyMap, MP1001 employeeData){
		final int pageNum = Integer.parseInt(propertyMap.get("PAGE_NUM"));
		final int pageCount = Integer.parseInt(propertyMap.get("PAGE_COUNT"));
		
		final StringBuffer queryString = new StringBuffer();
		String role = employeeData.getMP1001_GROUP();
		
		queryString.append(" from MP2001 as mp2, MP1001 mp11 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and mp11.MP1001_STATUS in('1','2') " );
		queryString.append(" and mp2.MP2001_STATUS = '0' " );
		queryString.append(" and mp11.MP1001_EMPLOYEE_NUM = mp2.MP2001_EMPLOYEE_NUM ");
		
		String optDepartment = "0";
		String optAll = "0";

		if(propertyMap.containsKey("OPT_DEPARTMENT") && propertyMap.get("OPT_DEPARTMENT") != null){
			optDepartment = propertyMap.get("OPT_DEPARTMENT");
		}
		if(propertyMap.containsKey("OPT_ALL") && propertyMap.get("OPT_ALL") != null){
			optAll = propertyMap.get("OPT_ALL");
		}
		
		if(!optAll.equals("1")){
			if(optDepartment.equals("1")){
				queryString.append(" and mp11.MP1001_DEPARTMENT_ID = '" + employeeData.getMP1001_DEPARTMENT_ID() + "'");
			}else{
				queryString.append(" and (mp2.MP2001_EMPLOYEE_NUM = '" + employeeData.getMP1001_EMPLOYEE_NUM() + "' or mp2.MP2001_ACTING_APPLICATION_PERSON = '" + employeeData.getMP1001_EMPLOYEE_NUM() + "' )");
			}
		}

//        if(role.equals("1")){
//        	queryString.append(" and (mp2.MP2001_EMPLOYEE_NUM = '" + employeeData.getMP1001_EMPLOYEE_NUM() + "' or mp2.MP2001_ACTING_APPLICATION_PERSON = '" + employeeData.getMP1001_EMPLOYEE_NUM() + "' )");
//        }else if(role.equals("2")){
//        	queryString.append(" and mp11.MP1001_DEPARTMENT_ID = '" + employeeData.getMP1001_DEPARTMENT_ID() + "'");
//        }else if(role.equals("3") || role.equals("4") || role.equals("5")){
//        	// 无限制条件
//        }
		
		// 判断“编码”
		if(propertyMap.containsKey("NUM") && propertyMap.get("NUM") != null && !propertyMap.get("NUM").equals("")){
			queryString.append(" and mp2.MP2001_NUM like '%" + propertyMap.get("NUM") + "%'");
		}
		// 判断“编码”
		if(propertyMap.containsKey("EMP_NUM") && propertyMap.get("EMP_NUM") != null && !propertyMap.get("EMP_NUM").equals("") && !role.equals("1")){
		//if(propertyMap.containsKey("EMP_NUM") && propertyMap.get("EMP_NUM") != null && !propertyMap.get("EMP_NUM").equals("")){
			queryString.append(" and mp2.MP2001_EMPLOYEE_NUM like '%" + propertyMap.get("EMP_NUM") + "%'");
		}
		// 判断“请假类型”
		if(propertyMap.containsKey("TYPE") && propertyMap.get("TYPE") != null && !propertyMap.get("TYPE").equals("") && !propertyMap.get("TYPE").equals("0")){
			queryString.append(" and mp2.MP2001_LEAVE_TYPE = '" + propertyMap.get("TYPE") + "'");
		}
		// 判断“批准状态”
		if(propertyMap.containsKey("APPROVAL") && propertyMap.get("APPROVAL") != null && !propertyMap.get("APPROVAL").equals("") && !propertyMap.get("APPROVAL").equals("0")){
			queryString.append(" and mp2.MP2001_APPROVAL = '" + propertyMap.get("APPROVAL") + "'");
		}
		// 判断“开始日期”、“结束日期”
		if(propertyMap.containsKey("FROM") && !propertyMap.get("FROM").equals("")){
			queryString.append(" and mp2.MP2001_FROM_DATETIME >= '" + propertyMap.get("FROM") + "'");
		}
		if(propertyMap.containsKey("TO") && !propertyMap.get("TO").equals("")){
			queryString.append(" and mp2.MP2001_FROM_DATETIME <= '" + propertyMap.get("TO") + " 23:59:59'"); //must append 23:59:59, it's means at the end of the day
//			queryString.append(" and mp2.MP2001_FROM_DATETIME <= '" + propertyMap.get("TO") + "'"); //must append 23:59:59, it's means at the end of the day
		}
		if(propertyMap.containsKey("DEPARTMENT") && propertyMap.get("DEPARTMENT") != null && !propertyMap.get("DEPARTMENT").equals("")){
			queryString.append(" and mp11.MP1001_DEPARTMENT_ID = '" + propertyMap.get("DEPARTMENT") + "'");
		}
		if(propertyMap.containsKey("JOB_TITLE") && propertyMap.get("JOB_TITLE") != null && !propertyMap.get("JOB_TITLE").equals("")){
			if(propertyMap.get("JOB_TITLE").equals("1")){// 一般员工
				queryString.append(" and mp11.MP1001_POSITION not in ('1','92','93') ");	
			}else if(propertyMap.get("JOB_TITLE").equals("2")){// Manager
				queryString.append(" and mp11.MP1001_POSITION = '1'");
			}
		}
		
		// 排序字段
		queryString.append(" order by mp2.MP2001_NUM desc");
		
		return getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(queryString.toString());
				if(pageNum>0){
					query.setFirstResult((pageNum -1)*pageCount);
					query.setMaxResults(pageCount);
				}
				
				List list = query.list();
				List<MP2001> retList = new ArrayList<MP2001>();
				for(int m=0,n=list.size(); m < n; m++){
					Object[] obj = (Object[]) list.get(m);
					MP2001 mp2001 = (MP2001)obj[0];
					MP1001 mp1001 = (MP1001)obj[1];
					mp2001.setMP2001_DEPARTMENT_ID(mp1001.getMP1001_DEPARTMENT_ID());
					mp2001.setMP2001_EMPLOYEE_NAME(mp1001.getMP1001_PREFERED_NAME() + " " + mp1001.getMP1001_SURNAME());
					mp2001.setMP2001_GROUP(mp1001.getMP1001_GROUP());
					mp2001.setMP2001_JOB_TITLE(mp1001.getMP1001_POSITION());
					
					retList.add(mp2001);
				}
				return retList;
			}
		});
	}
	
	public int getLeaveApplyReportRowCount(String employeeNum, String departmentId, String fromDate, String toDate){
		List<MP2001> dataList = getLeaveApplyReport(employeeNum, departmentId, fromDate, toDate, -1, -1);
		return dataList.size();
	}
	
	@SuppressWarnings("unchecked")
	public List<MP2001> getLeaveApplyReport(String employeeNum, String departmentId, String fromDate, String toDate, int PAGE_NUM, int PAGE_COUNT){
		StringBuffer queryString = new StringBuffer();
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		
		queryString.append(" select  ");
		queryString.append(" mp21.MP2001_NUM, ");
		queryString.append(" mp21.MP2001_EMPLOYEE_NUM, ");
		queryString.append(" mp21.MP2001_ACTING_APPLICATION_PERSON, ");
		queryString.append(" mp21.MP2001_APPLIY_TYPE, ");
		queryString.append(" mp21.MP2001_DAYS, ");
		queryString.append(" mp21.MP2001_FROM_DATETIME, ");
		queryString.append(" mp21.MP2001_TO_DATETIME, ");
		queryString.append(" mp21.MP2001_ACTING_PERSON, ");
		queryString.append(" mp21.MP2001_LEAVE_TYPE, ");
		queryString.append(" mp21.MP2001_APPROVAL, ");
		queryString.append(" mp21.MP2001_STATUS, ");
		queryString.append(" mp21.MP2001_COMMENT, ");
		queryString.append(" mp02.MP0002_SEQ as MP2001_DEPARTMENT_ID, ");
		queryString.append(" mp02.MP0002_DEPARTMENT_NAME as MP2001_DEPARTMENT_NAME, ");
		queryString.append(" mp11.MP1001_PREFERED_NAME as MP2001_PREFERED_NAME, ");
		queryString.append(" mp11.MP1001_SURNAME as MP2001_SURNAME, ");
		queryString.append(" case ");
		queryString.append("     when mp21.MP2001_LEAVE_TYPE = 1 then 'Annual' ");
		queryString.append("     when mp21.MP2001_LEAVE_TYPE = 2 then 'Sick' ");
		queryString.append("     when mp21.MP2001_LEAVE_TYPE = 3 then 'Family Resp' ");
		queryString.append("     when mp21.MP2001_LEAVE_TYPE = 4 then 'Study' ");
		queryString.append("     when mp21.MP2001_LEAVE_TYPE = 5 then 'Maternity' ");
		queryString.append("     when mp21.MP2001_LEAVE_TYPE = 6 then 'Unpaid' ");
		queryString.append("     when mp21.MP2001_LEAVE_TYPE = 7 then 'Official Business' ");
		queryString.append("     when mp21.MP2001_LEAVE_TYPE = 8 then 'Other' ");
		queryString.append(" end as MP2001_LEAVE_TYPE_NAME ");
		queryString.append(" from MP2001 mp21 ,MP0002 mp02,MP1001 mp11 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and mp11.MP1001_STATUS in('1','2') " );
		queryString.append(" and mp21.MP2001_APPROVAL = '3' ");
		queryString.append(" and mp21.MP2001_STATUS = '0' ");
		queryString.append(" and mp21.MP2001_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM ");
		queryString.append(" and mp11.MP1001_DEPARTMENT_ID = mp02.MP0002_SEQ ");
		// 判断员工编码是否存在
		if(employeeNum != null && !employeeNum.equals("")){
			queryString.append(" and mp21.MP2001_EMPLOYEE_NUM = '" + employeeNum + "' ");
		}
		// 判断部门编码是否存在
		if(departmentId != null && !departmentId.equals("")){
			queryString.append(" and mp02.MP0002_SEQ = '" + departmentId + "' ");
		}
		// 判断起始日期否存在
		if(fromDate != null && !fromDate.equals("")){
			queryString.append(" and mp21.MP2001_FROM_DATETIME <= '" + toDate + "' ");
		}
		// 判断结束日期是否存在
		if(toDate != null && !toDate.equals("")){
			queryString.append(" and mp21.MP2001_TO_DATETIME >= '" + fromDate + "' ");
		}
		
		queryString.append(" order by mp02.MP0002_SEQ, mp21.MP2001_EMPLOYEE_NUM ");
		
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		
		List<Object[]> list = query.list();
		session.close();
		
		MP2001 mp21 = new MP2001();
		List<MP2001> retList = new ArrayList<MP2001>();
		for(int i=0,j=list.size();i<j;i++){
			mp21 = new MP2001();
			Object[] obj = list.get(i);
			
			// MP2001_NUM
			mp21.setMP2001_NUM(obj[0]==null? "": obj[0].toString());
			// MP2001_EMPLOYEE_NUM
			mp21.setMP2001_EMPLOYEE_NUM(obj[1]==null? "": obj[1].toString());
			// MP2001_ACTING_APPLICATION_PERSON
			mp21.setMP2001_ACTING_APPLICATION_PERSON(obj[2]==null? "": obj[2].toString());
			// MP2001_APPLIY_TYPE
			mp21.setMP2001_APPLIY_TYPE(obj[3]==null? "": obj[3].toString());
			// MP2001_DAYS
			mp21.setMP2001_DAYS(obj[4]==null? "": obj[4].toString());
			// MP2001_FROM_DATETIME
			mp21.setMP2001_FROM_DATETIME(obj[5]==null? "": obj[5].toString());
			// MP2001_TO_DATETIME
			mp21.setMP2001_TO_DATETIME(obj[6]==null? "": obj[6].toString());
			// MP2001_ACTING_PERSON
			mp21.setMP2001_ACTING_PERSON(obj[7]==null? "": obj[7].toString());
			// MP2001_LEAVE_TYPE
			mp21.setMP2001_LEAVE_TYPE(obj[8]==null? "": obj[8].toString());
			// MP2001_APPROVAL
			mp21.setMP2001_APPROVAL(obj[9]==null? "": obj[9].toString());
			// MP2001_STATUS
			mp21.setMP2001_STATUS(obj[10]==null? "": obj[10].toString());
			// MP2001_COMMENT
			mp21.setMP2001_COMMENT(obj[11]==null? "": obj[11].toString());
			// MP2001_DEPARTMENT_ID
			mp21.setMP2001_DEPARTMENT_ID(obj[12]==null? "": obj[12].toString());
			// MP2001_DEPARTMENT_NAME
			mp21.setMP2001_DEPARTMENT_NAME(obj[13]==null? "": obj[13].toString());
			// MP2001_PREFERED_NAME
			mp21.setMP2001_PREFERED_NAME(obj[14]==null? "": obj[14].toString());
			// MP2001_SURNAME
			mp21.setMP2001_SURNAME(obj[15]==null? "": obj[15].toString());
			// MP2001_LEAVE_TYPE_NAME
			mp21.setMP2001_LEAVE_TYPE_NAME(obj[16]==null? "": obj[16].toString());
			
			retList.add(mp21);				
		}
		return retList;
	}
}