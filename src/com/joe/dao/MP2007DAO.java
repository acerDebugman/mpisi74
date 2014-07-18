package com.joe.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.joe.model.MP2007;

public class MP2007DAO extends HibernateDaoSupport implements IMP2007DAO{
	private static final Log log = LogFactory.getLog(MP2007DAO.class);
	
	// 保存数据
	public void save(MP2007 mp2007) {
		if(mp2007 != null){
			getHibernateTemplate().save(mp2007);
		}
	}
	// 删除数据
	public void delete(MP2007 mp2007) {
		if(mp2007 != null){
			getHibernateTemplate().delete(mp2007);
		}
	}
	// 根据KEY检索数据
	public MP2007 findById(int key) {
		MP2007 mp2007 = (MP2007)getHibernateTemplate().get("entity.MP2007", key);
		
		if(mp2007.getMP2007_DAYS() != null){
			int _days = Integer.parseInt(mp2007.getMP2007_DAYS());
			mp2007.setMP2007_DAYS_EXT_D(String.valueOf(_days/8));
			mp2007.setMP2007_DAYS_EXT_H(String.valueOf(_days%8));
		}
		return mp2007;
	}
	// 取得所有有效数据
	@SuppressWarnings("unchecked")
	public List<MP2007> findAll() {
		return getHibernateTemplate().find(" from MP2007 where 1=1 ");
	}
	// 更新数据
	public void update(MP2007 mp2007) {
		if(mp2007 != null){
			getHibernateTemplate().update(mp2007);
		}
	}
	// 动态根据传入的参数，检索数据
	public List<MP2007> findByProperty(String name, String value) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		queryString.append(" and mp27." + name + "='" + value + "' ");
		
		List<MP2007> retList = executeSqlStatement(queryString,-1,-1);
		
		return retList;
	}
	// 分页方法
	public List<MP2007> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		if(propertyMap.containsKey("DEPARTMENT_ID") && !propertyMap.get("DEPARTMENT_ID").equals("")){
			queryString.append(" and mp11.MP1001_DEPARTMENT_ID = '" + propertyMap.get("DEPARTMENT_ID") + "' ");
		}
		if(propertyMap.containsKey("EMPLOYEE_NUM") && !propertyMap.get("EMPLOYEE_NUM").equals("")){
			queryString.append(" and mp11.MP1001_EMPLOYEE_NUM = '" + propertyMap.get("EMPLOYEE_NUM") + "' ");
		}
		if(propertyMap.containsKey("EMPLOYEE_NAME") && !propertyMap.get("EMPLOYEE_NAME").equals("")){
			queryString.append(" and mp11.MP1001_PREFERED_NAME = '" + propertyMap.get("EMPLOYEE_NAME") + "' ");
		}
		if(propertyMap.containsKey("TYPE") && !propertyMap.get("TYPE").equals("")){
			queryString.append(" and mp27.MP2007_TYPE = '" + propertyMap.get("TYPE") + "' ");
		}
		if(propertyMap.containsKey("STATUS") && !propertyMap.get("STATUS").equals("")){
			queryString.append(" and mp27.MP2007_STATUS = '" + propertyMap.get("STATUS") + "' ");
		}
		
		List<MP2007> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);
		
		return retList;
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		List<MP2007> retList = findByPropertyByPage(propertyMap,-1,-1);
		return retList.size();
	}
	
	// 构建SQL语句
	private StringBuffer createSqlStatement(){
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" select ");
		queryString.append(" mp27.MP2007_SEQ, ");
		queryString.append(" mp27.MP2007_EMPLOYEE_NUM, ");
		queryString.append(" mp27.MP2007_DAYS, ");
		queryString.append(" mp27.MP2007_STATUS, ");		
		queryString.append(" mp27.MP2007_CREATE_USER, ");
		queryString.append(" mp27.MP2007_CREATE_DATETIME, ");
		queryString.append(" mp27.MP2007_EDIT_USER, ");
		queryString.append(" mp27.MP2007_EDIT_DATETIME, ");
		queryString.append(" mp11.MP1001_PREFERED_NAME, ");
		queryString.append(" mp27.MP2007_TYPE,");
		queryString.append(" mp02.MP0002_SEQ, ");
		queryString.append(" mp02.MP0002_DEPARTMENT_NAME ");
		queryString.append(" from MP2007 mp27, MP1001 mp11, MP0002 mp02 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and mp27.MP2007_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM ");
		queryString.append(" and mp11.MP1001_DEPARTMENT_ID = mp02.MP0002_SEQ ");		
		
		return queryString;
	}
	// 检索数据
	@SuppressWarnings("unchecked")
	private List<MP2007> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
		Session session = getHibernateTemplate().getSessionFactory().openSession();		
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		List<Object[]> list = query.list();
		session.close();
		
		List<MP2007> retList = getDataList(list);
		
		return retList;
	}
	// 解析数据
	private List<MP2007> getDataList(List<Object[]> list){
		MP2007 mp2007 = new MP2007();
		List<MP2007> retList = new ArrayList<MP2007>();

		for(int i=0,j=list.size(); i<j; i++){
			mp2007 = new MP2007();
			Object[] obj = list.get(i);
			
			if(obj[0] == null){
				continue;
			}else{
				mp2007.setMP2007_SEQ(Integer.parseInt(obj[0].toString()));
			}
			mp2007.setMP2007_EMPLOYEE_NUM(obj[1] == null ? "" : obj[1].toString());
			mp2007.setMP2007_DAYS(obj[2] == null ? "" : obj[2].toString());
			mp2007.setMP2007_STATUS(obj[3] == null ? "" : obj[3].toString());
			mp2007.setMP2007_CREATE_USER(obj[4] == null ? "" : obj[4].toString());
			mp2007.setMP2007_CREATE_DATETIME(obj[5] == null ? "" : obj[5].toString());
			mp2007.setMP2007_EDIT_USER(obj[6] == null ? "" : obj[6].toString());
			mp2007.setMP2007_EDIT_DATETIME(obj[7] == null ? "" : obj[7].toString());
            mp2007.setMP2007_EMPLOYEE_NAME(obj[8] == null ? "" : obj[8].toString());
            mp2007.setMP2007_TYPE(obj[9] == null ? "" : obj[9].toString());
            mp2007.setMP2007_DEPARTMENT_NUM(obj[10] == null ? "" : obj[10].toString());
            mp2007.setMP2007_DEPARTMENT_NAME(obj[11] == null ? "" : obj[11].toString());
            
			if(mp2007.getMP2007_STATUS() == null || mp2007.getMP2007_STATUS().equals("2")){
				mp2007.setMP2007_STATUS_NAME("Declined");
			}else if(mp2007.getMP2007_STATUS().equals("1")){
				mp2007.setMP2007_STATUS_NAME("Pending");
			}else if(mp2007.getMP2007_STATUS().equals("3")){
				mp2007.setMP2007_STATUS_NAME("Approved");
		    }
			
			if(mp2007.getMP2007_TYPE().equals("1")){
				mp2007.setMP2007_TYPE_NAME("Annual");
			}else if(mp2007.getMP2007_TYPE().equals("2")){
				mp2007.setMP2007_TYPE_NAME("Sick");
			}else if(mp2007.getMP2007_TYPE().equals("3")){
				mp2007.setMP2007_TYPE_NAME("Family resp");
		    }else if(mp2007.getMP2007_TYPE().equals("4")){
				mp2007.setMP2007_TYPE_NAME("Study");
		    }else if(mp2007.getMP2007_TYPE().equals("5")){
				mp2007.setMP2007_TYPE_NAME("Maternity");
		    }
			
			if(mp2007.getMP2007_DAYS() != null){
				int _days = Integer.parseInt(mp2007.getMP2007_DAYS());
				mp2007.setMP2007_DAYS_EXT_D(String.valueOf(_days/8));
				mp2007.setMP2007_DAYS_EXT_H(String.valueOf(_days%8));
			}

			retList.add(mp2007);
		}
		log.info("Count:" + retList.size());
		return retList;
	}
	
}
