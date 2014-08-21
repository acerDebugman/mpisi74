package dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.MP2010;

public class MP2010DAO  implements IMP2010DAO {
	private static final Log log = LogFactory.getLog(JE0202DAO.class);
	private SessionFactory sessionFactory;
	
	@Override
	public void save(MP2010 mp2010) {
		// TODO Auto-generated method stub
		if(null != mp2010){
			sessionFactory.getCurrentSession().save(mp2010);
		}
	}

	@Override
	public void delete(MP2010 mp2010) {
		// TODO Auto-generated method stub
		if(null != mp2010){
			sessionFactory.getCurrentSession().delete(mp2010);
		}
	}

	@Override
	public MP2010 findByKey(String key) {
		// TODO Auto-generated method stub
		//return (MP2010)getHibernateTemplate().find("entity.MP2010", Integer.parseInt(key));
		//return (MP2010)getHibernateTemplate().find(" from entity.MP2010 p where p.MP2010_ID=?", Integer.parseInt(key)).;
		return (MP2010)sessionFactory.getCurrentSession().get(MP2010.class, Integer.parseInt(key));
	}

	@Override
	public MP2010 findByValue(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MP2010> findAll() {
		// TODO Auto-generated method stub
//		return getHibernateTemplate().find("from MP2010");
		return sessionFactory.getCurrentSession().createQuery("from MP2010").list();
	}

	@Override
	public void update(MP2010 mp2010) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(mp2010);
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

//		return getHibernateTemplate().find(sb.toString());
		return sessionFactory.getCurrentSession().createQuery(sb.toString()).list();
	}

	@Override
	public List<MP2010> findByColumnName(Map<String, String> columnMap,
			String strOrder) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("from MP2010 where 1 = 1 ");
		if(null != columnMap && columnMap.get("MP2010_EMPLOYEE_NUM")!=null && columnMap.containsKey("MP2010_EMPLOYEE_NUM") && !columnMap.get("MP2010_EMPLOYEE_NUM").equalsIgnoreCase("") && !columnMap.get("MP2010_EMPLOYEE_NUM").equalsIgnoreCase("-1")){
			sb.append(" and MP2010_EMPLOYEE_NUM='" + columnMap.get("MP2010_EMPLOYEE_NUM") + "' ");
		}
		if(null != columnMap && columnMap.get("MP2010_FROM_DATETIME")!=null && columnMap.containsKey("MP2010_FROM_DATETIME") && !columnMap.get("MP2010_FROM_DATETIME").equalsIgnoreCase("") && !columnMap.get("MP2010_FROM_DATETIME").equalsIgnoreCase("-1")){
			sb.append(" and MP2010_DATE>='" + columnMap.get("MP2010_FROM_DATETIME") + "' ");
		}
		if(null != columnMap && columnMap.get("MP2010_END_DATETIME")!=null && columnMap.containsKey("MP2010_END_DATETIME") && !columnMap.get("MP2010_END_DATETIME").equalsIgnoreCase("") && !columnMap.get("MP2010_END_DATETIME").equalsIgnoreCase("-1")){
			sb.append(" and MP2010_DATE<='" + columnMap.get("MP2010_END_DATETIME") + "' ");
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
		sb.append(" " + strOrder);
		
//		return getHibernateTemplate().find(sb.toString());
		return sessionFactory.getCurrentSession().createQuery(sb.toString()).list();
	}
	
	@SuppressWarnings("unchecked")
	public long findTotalSearchCount(Map<String, String> columnNames){
		// TODO Auto-generated method stub
		try{
			final StringBuffer queryString = new StringBuffer();
			
			queryString.append("select count(*) from MP2010 where 1=1 ");
			Iterator it = columnNames.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry pairs = (Map.Entry)it.next();
				String columnName = (String)pairs.getKey();
				String columnValue = (String)pairs.getValue();
				if("" != columnName && null != columnName && null != columnValue && !columnName.equalsIgnoreCase("PAGE_NUM") && !columnName.equalsIgnoreCase("PAGE_COUNT")){
					if(columnName.equalsIgnoreCase("MP2010_FROM_DATETIME")){
						queryString.append(" and MP2010_DATE >= '" + columnValue + "'");	
					}
					else if(columnName.equalsIgnoreCase("MP2010_END_DATETIME")){
						queryString.append(" and MP2010_DATE <= '" + columnValue + "'");
					}
					else{
						queryString.append(" and " + columnName + "='" + columnValue + "' " );
					}
				}
				
			}
//			return (Long)getHibernateTemplate().find(queryString.toString()).iterator().next();
			return (Long)sessionFactory.getCurrentSession().createQuery(queryString.toString()).iterate().next();
		}catch(RuntimeException e){
			log.info(e.getMessage());
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<MP2010> findByColumnNames(Map<String, String> columnNames, boolean pageFlag) {
		// TODO Auto-generated method stub
		try{
			final StringBuffer queryString = new StringBuffer();
			
			queryString.append("from MP2010 m where 1=1 ");
			Iterator it = columnNames.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry pairs = (Map.Entry)it.next();
				String columnName = (String)pairs.getKey();
				String columnValue = (String)pairs.getValue();
				if("" != columnName && null != columnName && null != columnValue && !columnName.equalsIgnoreCase("PAGE_NUM") && !columnName.equalsIgnoreCase("PAGE_COUNT")){
					if(columnName.equalsIgnoreCase("MP2010_FROM_DATETIME")){
						queryString.append(" and m.MP2010_DATE >= '" + columnValue + "'");	
					}
					else if(columnName.equalsIgnoreCase("MP2010_END_DATETIME")){
						queryString.append(" and m.MP2010_DATE <= '" + columnValue + "'");
					}
					else{
						queryString.append(" and m." + columnName + "='" + columnValue + "' " );
					}
				}
				
			}
			
			if(pageFlag){
					final int pageNum = Integer.parseInt(columnNames.get("PAGE_NUM")); //which page
					final int pageCount = Integer.parseInt(columnNames.get("PAGE_COUNT")); //per page numbers
					
//					return getHibernateTemplate().executeFind(new HibernateCallback(){
//						public Object doInHibernate(org.hibernate.Session session) throws HibernateException, SQLException {
					
							Session session = sessionFactory.getCurrentSession();
					
							Query query = session.createQuery(queryString.toString());
							query.setFirstResult((pageNum - 1) * pageCount);
							query.setMaxResults(pageCount);
							
							List<MP2010> list = query.list();
							
							return list;
//						}});
			}
			else{
//				return getHibernateTemplate().find(queryString.toString());
				return sessionFactory.getCurrentSession().createQuery(queryString.toString()).list();
			}

		}catch(RuntimeException e){
			log.info(e.getMessage());
			throw e;
		}
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

	public static Log getLog() {
		return log;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}