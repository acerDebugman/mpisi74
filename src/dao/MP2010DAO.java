package dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.MP2010;

public class MP2010DAO extends HibernateDaoSupport implements IMP2010DAO {
	private static final Log log = LogFactory.getLog(JE0202DAO.class);

	@Override
	public void save(MP2010 mp2010) {
		// TODO Auto-generated method stub
		if(null != mp2010){
			getHibernateTemplate().save(mp2010);
		}
	}

	@Override
	public void delete(MP2010 mp2010) {
		// TODO Auto-generated method stub
		if(null != mp2010){
			getHibernateTemplate().delete(mp2010);
		}
	}

	@Override
	public MP2010 findByKey(String key) {
		// TODO Auto-generated method stub
		//return (MP2010)getHibernateTemplate().find("entity.MP2010", Integer.parseInt(key));
		//return (MP2010)getHibernateTemplate().find(" from entity.MP2010 p where p.MP2010_ID=?", Integer.parseInt(key)).;
		return (MP2010)getHibernateTemplate().get(MP2010.class, Integer.parseInt(key));
	}

	@Override
	public MP2010 findByValue(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MP2010> findAll() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from MP2010");
	}

	@Override
	public void update(MP2010 mp2010) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(mp2010);
	}

	@Override
	public MP2010 findByType(String type) {
		// TODO Auto-generated method stub
		return null;
	}
/*
 * 
 * private MP1001 shiftEmployee;
 * private String 
	private String MP2010_DATE;
	private String MP2010_FROM_DATETIME;
	private String MP2010_END_DATETIME;
	private String MP2010_TYPE;
	private String MP2010_BRANCH_SITE;
 * 
 */
	
	
	@Override
	public List<MP2010> findByColumnName(Map<String, String> columnMap) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("from MP2010 where 1 = 1 ");
		if(null != columnMap && columnMap.get("MP2010_EMPLOYEE_NUM")!=null && columnMap.containsKey("MP2010_EMPLOYEE_NUM") && !columnMap.get("MP2010_EMPLOYEE_NUM").equalsIgnoreCase("") && !columnMap.get("MP2010_EMPLOYEE_NUM").equalsIgnoreCase("-1")){
			sb.append(" and MP2010_EMPLOYEE_NUM='" + columnMap.get("MP2010_EMPLOYEE_NUM") + "' ");
		}
		if(null != columnMap && columnMap.get("MP2010_FROM_DATETIME")!=null && columnMap.containsKey("MP2010_FROM_DATETIME") && !columnMap.get("MP2010_FROM_DATETIME").equalsIgnoreCase("") && !columnMap.get("MP2010_FROM_DATETIME").equalsIgnoreCase("-1")){
			sb.append(" and MP2010_FROM_DATETIME='" + columnMap.get("MP2010_FROM_DATETIME") + "' ");
		}
		if(null != columnMap && columnMap.get("MP2010_END_DATETIME")!=null && columnMap.containsKey("MP2010_END_DATETIME") && !columnMap.get("MP2010_END_DATETIME").equalsIgnoreCase("") && !columnMap.get("MP2010_END_DATETIME").equalsIgnoreCase("-1")){
			sb.append(" and MP2010_END_DATETIME='" + columnMap.get("MP2010_END_DATETIME") + "' ");
		}
		if(null != columnMap && columnMap.get("MP2010_END_DATETIME")!=null && columnMap.containsKey("MP2010_END_DATETIME") && !columnMap.get("MP2010_END_DATETIME").equalsIgnoreCase("") && !columnMap.get("MP2010_END_DATETIME").equalsIgnoreCase("-1")){
			sb.append(" and MP2010_END_DATETIME='" + columnMap.get("MP2010_END_DATETIME") + "' ");
		}
		if(null != columnMap && columnMap.get("MP2010_TYPE")!=null && columnMap.containsKey("MP2010_TYPE") && !columnMap.get("MP2010_TYPE").equalsIgnoreCase("") && !columnMap.get("MP2010_TYPE").equalsIgnoreCase("-1")){
			sb.append(" and MP2010_TYPE='" + columnMap.get("MP2010_TYPE") + "' ");
		}
		if(null != columnMap && columnMap.get("MP2010_BRANCH_SITE")!=null && columnMap.containsKey("MP2010_BRANCH_SITE") && !columnMap.get("MP2010_BRANCH_SITE").equalsIgnoreCase("") && !columnMap.get("MP2010_BRANCH_SITE").equalsIgnoreCase("-1")){
			sb.append(" and MP2010_BRANCH_SITE='" + columnMap.get("MP2010_BRANCH_SITE") + "' ");
		}

		return getHibernateTemplate().find(sb.toString());
	}

	@Override
	public List<MP2010> findByColumnName(Map<String, String> columnMap,
			String strOrder) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public int getAllRowsCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MP2010> findByColumnName(Map<String, String> columnMap,
			String strOrder, boolean pageFlag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSearchConditionRowsCount(Map<String, String> columnMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void getSubscriberInfo(MP2010 mp2010) {
		// TODO Auto-generated method stub
		
	}
}