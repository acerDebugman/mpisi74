package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import common.UtilCommon;

import entity.MP1001;
import entity.MP2002;

public class MP2002DAO  implements IMP2002DAO {
	private SessionFactory sessionFactory;
	
	public void save(MP2002 mp2002) {
		if (mp2002 != null) {
			sessionFactory.getCurrentSession().save(mp2002);
		}
	}

	public void delete(MP2002 mp2002) {
		sessionFactory.getCurrentSession().delete(mp2002);
	}

	@SuppressWarnings("unchecked")
	public MP2002 findById(String employeeNum) {
		//return (MP2002) sessionFactory.getCurrentSession().get("entity.MP2002", employeeNum);
		try{
			Session session = sessionFactory.getCurrentSession();

			StringBuffer queryString = new StringBuffer();
			queryString.append("select ");
			queryString.append(" mp11.MP1001_EMPLOYEE_NUM,");
			queryString.append(" mp11.MP1001_PREFERED_NAME,");
			queryString.append(" mp22.MP2002_ANNUAL,");
			queryString.append(" mp22.MP2002_ANNUAL_T,");
			queryString.append(" mp22.MP2002_SICK,");
			queryString.append(" mp22.MP2002_SICK_T,");
			queryString.append(" mp22.MP2002_FAMILY_RESP,");
			queryString.append(" mp22.MP2002_FAMILY_RESP_T,");
			queryString.append(" mp22.MP2002_MATERNITY,");
			queryString.append(" mp22.MP2002_MATERNITY_T,");
			queryString.append(" mp22.MP2002_STUDY,");
			queryString.append(" mp22.MP2002_STUDY_T, ");
			queryString.append(" mp11.MP1001_SURNAME, ");
			queryString.append(" mp22.MP2002_YEAR, ");
			queryString.append(" mp22.MP2002_ANNUAL_MAX ");
			
			queryString.append(" from MP2002 mp22,MP1001 mp11 ");
			queryString.append(" where 1=1 ");
			queryString.append(" and mp22.MP2002_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM ");
			queryString.append(" and mp11.MP1001_STATUS in ('1','2') ");
			queryString.append(" and mp22.MP2002_YEAR = '" + UtilCommon.getFinanceYear() + "'");
			queryString.append(" and mp22.MP2002_EMPLOYEE_NUM = '" + employeeNum + "' ");
			
			Query query = session.createSQLQuery(queryString.toString());
			
			List<Object[]> list = query.list();
			// session.close();
			
			MP2002 mp22 = new MP2002();
			if(list != null && list.size() > 0){
				Object[] obj = list.get(0);
				
			    mp22.setMP2002_EMPLOYEE_NUM(obj[0] == null ? "" : obj[0].toString());
			    mp22.setMP2002_EMPLOYEE_NAME(obj[1] == null ? "" : obj[1].toString() + " " + obj[12].toString() );
			    mp22.setMP2002_ANNUAL(obj[2] == null ? "0" : obj[2].toString());
			    mp22.setMP2002_ANNUAL_T(obj[3] == null ? "0" : obj[3].toString());
			    mp22.setMP2002_SICK(obj[4] == null ? "0" : obj[4].toString());
			    mp22.setMP2002_SICK_T(obj[5] == null ? "0" : obj[5].toString());
			    mp22.setMP2002_FAMILY_RESP(obj[6] == null ? "0" : obj[6].toString());
			    mp22.setMP2002_FAMILY_RESP_T(obj[7] == null ? "0" : obj[7].toString());
			    mp22.setMP2002_MATERNITY(obj[8] == null ? "0" : obj[8].toString());
			    mp22.setMP2002_MATERNITY_T(obj[9] == null ? "0" : obj[9].toString());
			    mp22.setMP2002_STUDY(obj[10] == null ? "0" : obj[10].toString());
			    mp22.setMP2002_STUDY_T(obj[11] == null ? "0" : obj[11].toString());
				mp22.setMP2002_YEAR(obj[13] == null ? "0" : obj[13].toString());
				mp22.setMP2002_ANNUAL_MAX(obj[14] == null ? "0" : obj[14].toString());
			}
			
			return mp22;
		}catch(RuntimeException ex){
			throw ex;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MP2002> findAll() {
//		return getHibernateTemplate().find("from MP2002");
		return sessionFactory.getCurrentSession().createQuery("from MP2002").list();
	}

	public void update(MP2002 mp2002) {
		sessionFactory.getCurrentSession().update(mp2002);
	}
	
	@SuppressWarnings("unchecked")
	public List<MP2002> findByProperty(String name, String value) {
		try{
//			String queryString = " from MP2002 as mp2 where mp2." + name + " = ?";
//			return getHibernateTemplate().find(queryString, value);
			String queryString = " from MP2002 as mp2 where mp2." + name + " = '" + value + "'";
			return sessionFactory.getCurrentSession().createQuery(queryString).list();
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	
	public List<MP2002> findByPropertysPage(MP1001 employeeData, int PAGE_NUM, int PAGE_COUNT){
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		queryString.append(" and mp22.MP2002_YEAR = '" + UtilCommon.getFinanceYear() + "'");
	    if(employeeData.getFullAccess().equals("1")){//全权限
	    	//无限制
	    }else{
	    	if(employeeData.getDepartmentAccess().equals("1")){//部门级别权限
	    		queryString.append(" and mp11.MP1001_DEPARTMENT_ID = '" + employeeData.getMP1001_DEPARTMENT_ID() + "' ");
	    	}else{//个人级别权限
	    		queryString.append(" and mp22.MP2002_EMPLOYEE_NUM = '" + employeeData.getMP1001_EMPLOYEE_NUM() + "' ");
	    	}
	    }
	    queryString.append(" order by mp11.MP1001_EMPLOYEE_NUM");
//			if(role.equals("1")){// 一般人员
//				queryString.append(" and mp22.MP2002_EMPLOYEE_NUM = '" + employeeData.getMP1001_EMPLOYEE_NUM() + "' ");
//			}else if(role.equals("2")){//部门主管
//				queryString.append(" and mp11.MP1001_DEPARTMENT_ID = '" + employeeData.getMP1001_DEPARTMENT_ID() + "' ");
//			}else if(role.equals("3") || role.equals("4") || role.equals("5")){//HR主管、Director、超级管理员
//				// 没有过滤条件
//			}
	    List<MP2002> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);
	    
	    return retList;
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(MP1001 employeeData) {
		List<MP2002> retList = findByPropertysPage(employeeData,-1,-1);
		return retList.size();
	}
	// 自定义查询条件及排序方式
	public List<MP2002> findDataBySelfDefined(String search,String order, int pageNum, int pageCount){
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		queryString.append(search);
		// 排序方式
		queryString.append(order);
		
		List<MP2002> retList = executeSqlStatement(queryString, pageNum, pageCount);
		return retList;
	}
	
	// 构建SQL语句
	private StringBuffer createSqlStatement(){
		StringBuffer queryString = new StringBuffer();
		queryString.append("select ");
		queryString.append(" mp11.MP1001_EMPLOYEE_NUM,");
		queryString.append(" mp11.MP1001_PREFERED_NAME,");
		queryString.append(" mp22.MP2002_ANNUAL,");
		queryString.append(" mp22.MP2002_ANNUAL_T,");
		queryString.append(" mp22.MP2002_SICK,");
		queryString.append(" mp22.MP2002_SICK_T,");
		queryString.append(" mp22.MP2002_FAMILY_RESP,");
		queryString.append(" mp22.MP2002_FAMILY_RESP_T,");
		queryString.append(" mp22.MP2002_MATERNITY,");
		queryString.append(" mp22.MP2002_MATERNITY_T,");
		queryString.append(" mp22.MP2002_STUDY,");
		queryString.append(" mp22.MP2002_STUDY_T, ");
		queryString.append(" mp11.MP1001_SURNAME, ");
		queryString.append(" mp22.MP2002_YEAR, ");
		queryString.append(" mp22.MP2002_ANNUAL_MAX ");
		
		queryString.append(" from MP2002 mp22,MP1001 mp11 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and mp22.MP2002_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM ");
		queryString.append(" and mp11.MP1001_STATUS in ('1','2') ");
		
		return queryString;
	}
	// 检索数据
	@SuppressWarnings("unchecked")
	private List<MP2002> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
		Session session = sessionFactory.getCurrentSession();		
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		List<Object[]> list = query.list();
		// session.close();
		
		List<MP2002> retList = getDataList(list);
		
		return retList;
	}
	// 解析数据
	private List<MP2002> getDataList(List<Object[]> list){
		MP2002 mp22 = new MP2002();
		List<MP2002> resultList = new ArrayList<MP2002>();
		for(int i=0,j=list.size();i<j;i++){
			mp22 = new MP2002();
			Object[] obj = list.get(i);
			
		    mp22.setMP2002_EMPLOYEE_NUM(obj[0] == null ? "" : obj[0].toString());
		    mp22.setMP2002_EMPLOYEE_NAME(obj[1] == null ? "" : obj[1].toString() + " " + obj[12].toString() );
		    mp22.setMP2002_ANNUAL(obj[2] == null ? "0" : obj[2].toString());
		    mp22.setMP2002_ANNUAL_T(obj[3] == null ? "0" : obj[3].toString());
		    mp22.setMP2002_SICK(obj[4] == null ? "0" : obj[4].toString());
		    mp22.setMP2002_SICK_T(obj[5] == null ? "0" : obj[5].toString());
		    mp22.setMP2002_FAMILY_RESP(obj[6] == null ? "0" : obj[6].toString());
		    mp22.setMP2002_FAMILY_RESP_T(obj[7] == null ? "0" : obj[7].toString());
		    mp22.setMP2002_MATERNITY(obj[8] == null ? "0" : obj[8].toString());
		    mp22.setMP2002_MATERNITY_T(obj[9] == null ? "0" : obj[9].toString());
		    mp22.setMP2002_STUDY(obj[10] == null ? "0" : obj[10].toString());
		    mp22.setMP2002_STUDY_T(obj[11] == null ? "0" : obj[11].toString());
			mp22.setMP2002_YEAR(obj[13] == null ? "0" : obj[13].toString());
			mp22.setMP2002_ANNUAL_MAX(obj[14] == null ? "0" : obj[14].toString());
		    
			resultList.add(mp22);
		}
		return resultList;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}