package dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.MP0011;

public class MP0011DAO extends HibernateDaoSupport implements IMP0011DAO{
	public void save(MP0011 mp0011) {
		if (mp0011 != null) {
			getHibernateTemplate().save(mp0011);
		}
	}

	public void delete(MP0011 mp0011) {
		getHibernateTemplate().delete(mp0011);
	}

	public MP0011 findById(String _key) {
		return (MP0011) getHibernateTemplate().get("entity.MP0011", _key);
	}

	@SuppressWarnings("unchecked")
	public List<MP0011> findAll() {
		return getHibernateTemplate().find("from MP0011 where 1=1 ");
	}

	public void update(MP0011 mp0011) {
		getHibernateTemplate().update(mp0011);
	}
	
	@SuppressWarnings("unchecked")
	public List<MP0011> findByProperty(String name, String value){
		try{
			String queryString = " from MP0011 as mp19 where mp19." + name + " = ?";
			return getHibernateTemplate().find(queryString, value);
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	
	public List<MP0011> findByProperty(String employeeNum,String employeeName,String loginTimeFrom, String loginTimeTo, int PAGE_NUM, int PAGE_COUNT){
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		if(employeeNum != null && !employeeNum.equals("")){
			queryString.append(" and mp11.MP0011_USR_ID = '" + employeeNum + "' ");
		}
		if(employeeName != null && !employeeName.equals("")){
			queryString.append(" and mp11.MP0011_USR_NAME like '%" + employeeName + "'% ");
		}
		if(loginTimeFrom != null && !loginTimeFrom.equals("")){
			queryString.append(" and mp11.MP0011_LOGIN_TIME >= '" + loginTimeFrom + "' ");
		}
		if(loginTimeTo != null && !loginTimeTo.equals("")){
			queryString.append(" and mp11.MP0011_LOGIN_TIME <= '" + loginTimeTo + " 23:59:59" + "' ");
		}
		
		queryString.append(" order by mp11.MP0011_LOGIN_TIME ");
		
		List<MP0011> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);
		
		return retList;
	}
	
	// 构建SQL语句
	private StringBuffer createSqlStatement(){
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" select ");
		queryString.append(" mp11.MP0011_SEQ, ");
		queryString.append(" mp11.MP0011_SYS_CODE, ");
		queryString.append(" mp11.MP0011_USR_ID, ");
		queryString.append(" mp11.MP0011_USR_NAME, ");
		queryString.append(" mp11.MP0011_LOGIN_TIME, ");
		queryString.append(" mp11.MP0011_LOGIN_IP, ");
		queryString.append(" mp11.MP0011_MEMO, ");
		queryString.append(" mp02.MP0002_DEPARTMENT_NAME ");
		queryString.append(" from MP0011 mp11,MP1001 mp01,MP0002 mp02 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and mp11.MP0011_USR_ID = mp01.MP1001_EMPLOYEE_NUM ");
		queryString.append(" and mp01.MP1001_DEPARTMENT_ID = mp02.MP0002_SEQ ");
		
		return queryString;
	}
	// 检索数据
	@SuppressWarnings("unchecked")
	private List<MP0011> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
		Session session = getHibernateTemplate().getSessionFactory().openSession();		
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		List<Object[]> list = query.list();
		session.close();
		
		List<MP0011> retList = getDataList(list);
		
		return retList;
	}
	// 解析数据
	private List<MP0011> getDataList(List<Object[]> list){
		MP0011 mp11 = new MP0011();
		List<MP0011> retList = new ArrayList<MP0011>();
		
		for(int i=0,j=list.size();i<j;i++){
			mp11 = new MP0011();
			Object[] obj = list.get(i);
			
			mp11.setMP0011_SEQ(obj[0].toString());
			// System Code
			if(obj[1] == null){
				mp11.setMP0011_SYS_CODE("");
			}else{
				mp11.setMP0011_SYS_CODE(obj[1].toString());
			}
			// User ID
			if(obj[2] == null){
				mp11.setMP0011_USR_ID("");
			}else{
				mp11.setMP0011_USR_ID(obj[2].toString());
			}
			// User Name
			if(obj[3] == null){
				mp11.setMP0011_USR_NAME("");
			}else{
				mp11.setMP0011_USR_NAME(obj[3].toString());
			}
			// Login Time
			if(obj[4] == null){
				mp11.setMP0011_LOGIN_TIME("");
			}else{
				mp11.setMP0011_LOGIN_TIME(obj[4].toString());
			}
			// Login IP
			if(obj[5] == null){
				mp11.setMP0011_LOGIN_IP("");
			}else{
				mp11.setMP0011_LOGIN_IP(obj[5].toString());
			}
			// Memo
			if(obj[6] == null){
				mp11.setMP0011_MEMO("");
			}else{
				mp11.setMP0011_MEMO(obj[6].toString());
			}
			// Department
			if(obj[7] == null){
				mp11.setMP0011_DEPARTMENT("");
			}else{
				mp11.setMP0011_DEPARTMENT(obj[7].toString());
			}			

			retList.add(mp11);
		}

		return retList;
	}
	
}
