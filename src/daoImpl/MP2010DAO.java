package daoImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import dao.IMP2010DAO;
import entity.MP2010;

public class MP2010DAO   implements IMP2010DAO {
	private static final Log log = LogFactory.getLog(JE0202DAO.class);
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public void save(MP2010 mp2010) {
		// TODO Auto-generated method stub
		if(null != mp2010){
			sf.getCurrentSession().save(mp2010);
		}
	}

	@Override
	public void delete(MP2010 mp2010) {
		// TODO Auto-generated method stub
		if(null != mp2010){
			sf.getCurrentSession().delete(mp2010);
		}
	}

	@Override
	public MP2010 findByKey(String key) {
		// TODO Auto-generated method stub
		//return (MP2010)sf.getCurrentSession().find("entity.MP2010", Integer.parseInt(key));
		//return (MP2010)sf.getCurrentSession().find(" from entity.MP2010 p where p.MP2010_ID=?", Integer.parseInt(key)).;
		return (MP2010)sf.getCurrentSession().get(MP2010.class, Integer.parseInt(key));
	}

	@Override
	public MP2010 findByValue(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MP2010> findAll() {
		// TODO Auto-generated method stub
		return sf.getCurrentSession().createQuery("from MP2010").list();
	}

	@Override
	public void update(MP2010 mp2010) {
		// TODO Auto-generated method stub
		sf.getCurrentSession().update(mp2010);
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

		return sf.getCurrentSession().createQuery(sb.toString()).list();
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
		
		return sf.getCurrentSession().createQuery(sb.toString()).list();
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
			return (Long)sf.getCurrentSession().createQuery(queryString.toString()).iterate().next();
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
					
//					return sf.getCurrentSession().executeFind(new HibernateCallback(){
//						public Object doInHibernate(org.hibernate.Session session) throws HibernateException, SQLException {
							Query query = sf.getCurrentSession().createQuery(queryString.toString());
							query.setFirstResult((pageNum - 1) * pageCount);
							query.setMaxResults(pageCount);
							
							List<MP2010> list = query.list();
							
							return list;
//						}});
			}
			else{
				return sf.getCurrentSession().createQuery(queryString.toString()).list();
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
}