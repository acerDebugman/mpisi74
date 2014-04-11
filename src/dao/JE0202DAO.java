package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.JE0202;
import entity.MP0002;
import entity.MP1001;

public class JE0202DAO extends HibernateDaoSupport implements IJE0202DAO {
	private static final Log log = LogFactory.getLog(JE0202DAO.class);
	@Override
	public void save(JE0202 je0202) {
		// TODO Auto-generated method stub
		if(null != je0202){
			getHibernateTemplate().save(je0202);
		}
	}

	@Override
	public void delete(JE0202 je0202) {
		// TODO Auto-generated method stub
		if(null != je0202){
			getHibernateTemplate().delete(je0202);
		}
	}

	@Override
	public JE0202 findByKey(String key) {
		// TODO Auto-generated method stub
		return (JE0202)getHibernateTemplate().get("entity.JE0202", key);
	}

	@Override
	public JE0202 findByValue(String value) {
		// TODO Auto-generated method stub
		//return (JE0202)getHibernateTemplate().get("entity.JE0202", value);
		return (JE0202)getHibernateTemplate().find(" from JE0202 where JE0202_VALUE='" + value + "'");
	}
	
	@Override
	public JE0202 findByType(String type) {
		// TODO Auto-generated method stub
		//return (JE0202)getHibernateTemplate().get("entity.JE0202", value);
		return (JE0202)getHibernateTemplate().find(" from JE0202 where JE0202_VALUE='" + type + "'");
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public List<JE0202> findAll() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from JE0202");
	}

	@Override
	public void update(JE0202 je0202) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(je0202);
	}
	

	@Override
	public List<JE0202> findByColumnName(Map<String, String> columnMap) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("from JE0202 where 1 = 1 ");
		if(null != columnMap && columnMap.get("JE0202_DATE")!=null && columnMap.containsKey("JE0202_DATE") && !columnMap.get("JE0202_DATE").equalsIgnoreCase("") ){
			sb.append(" and JE0202_DATE='" + columnMap.get("JE0202_DATE") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_CASE_CODE")!=null && columnMap.containsKey("JE0202_CASE_CODE") && !columnMap.get("JE0202_CASE_CODE").equalsIgnoreCase("") ){
			sb.append(" and JE0202_CASE_CODE='" + columnMap.get("JE0202_CASE_CODE") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_FROM_DATETIME")!=null && columnMap.containsKey("JE0202_FROM_DATETIME") && !columnMap.get("JE0202_FROM_DATETIME").equalsIgnoreCase("") && !columnMap.get("JE0202_FROM_DATETIME").equalsIgnoreCase("-1")){
			sb.append(" and JE0202_FROM_DATETIME<='" + columnMap.get("JE0202_FROM_DATETIME") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_END_DATETIME")!=null && columnMap.containsKey("JE0202_END_DATETIME") && !columnMap.get("JE0202_END_DATETIME").equalsIgnoreCase("") && !columnMap.get("JE0202_END_DATETIME").equalsIgnoreCase("-1")){
			sb.append(" and JE0202_END_DATETIME>='" + columnMap.get("JE0202_END_DATETIME") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_ROOM_CODE")!=null && columnMap.containsKey("JE0202_ROOM_CODE") && !columnMap.get("JE0202_ROOM_CODE").equalsIgnoreCase("") && !columnMap.get("JE0202_ROOM_CODE").equalsIgnoreCase("-1")){
			sb.append(" and JE0202_ROOM_CODE='" + columnMap.get("JE0202_ROOM_CODE") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_ROOM_NAME")!=null && columnMap.containsKey("JE0202_ROOM_NAME") && !columnMap.get("JE0202_ROOM_NAME").equalsIgnoreCase("") ){
			sb.append(" and JE0202_ROOM_NAME='" + columnMap.get("JE0202_ROOM_NAME") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_ROOM_FLOOR")!=null && columnMap.containsKey("JE0202_ROOM_FLOOR") && !columnMap.get("JE0202_ROOM_FLOOR").equalsIgnoreCase("") ){
			sb.append(" and JE0202_ROOM_FLOOR='" + columnMap.get("JE0202_ROOM_FLOOR") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_USER_TYPE")!=null && columnMap.containsKey("JE0202_USER_TYPE") && !columnMap.get("JE0202_USER_TYPE").equalsIgnoreCase("") ){
			sb.append(" and JE0202_USER_TYPE='" + columnMap.get("JE0202_USER_TYPE") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_DES")!=null && columnMap.containsKey("JE0202_DES") && !columnMap.get("JE0202_DES").equalsIgnoreCase("") ){
			sb.append(" and JE0202_DES='" + columnMap.get("JE0202_DES") + "' ");
		}
	
		return getHibernateTemplate().find(sb.toString());
	}
	
	@Override
	public List<JE0202> findByColumnName(Map<String, String> columnMap, String strOrder) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("from JE0202 where 1 = 1 ");
		if(null != columnMap && columnMap.get("JE0202_DATE")!=null && columnMap.containsKey("JE0202_DATE") && !columnMap.get("JE0202_DATE").equalsIgnoreCase("") ){
			sb.append(" and JE0202_DATE='" + columnMap.get("JE0202_DATE") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_CASE_CODE")!=null && columnMap.containsKey("JE0202_CASE_CODE") && !columnMap.get("JE0202_CASE_CODE").equalsIgnoreCase("") ){
			sb.append(" and JE0202_CASE_CODE='" + columnMap.get("JE0202_CASE_CODE") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_FROM_DATETIME")!=null && columnMap.containsKey("JE0202_FROM_DATETIME") && !columnMap.get("JE0202_FROM_DATETIME").equalsIgnoreCase("") && !columnMap.get("JE0202_FROM_DATETIME").equalsIgnoreCase("-1")){
			sb.append(" and JE0202_FROM_DATETIME<='" + columnMap.get("JE0202_FROM_DATETIME") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_END_DATETIME")!=null && columnMap.containsKey("JE0202_END_DATETIME") && !columnMap.get("JE0202_END_DATETIME").equalsIgnoreCase("") && !columnMap.get("JE0202_END_DATETIME").equalsIgnoreCase("-1")){
			sb.append(" and JE0202_END_DATETIME>='" + columnMap.get("JE0202_END_DATETIME") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_ROOM_CODE")!=null && columnMap.containsKey("JE0202_ROOM_CODE") && !columnMap.get("JE0202_ROOM_CODE").equalsIgnoreCase("") && !columnMap.get("JE0202_ROOM_CODE").equalsIgnoreCase("-1")){
			sb.append(" and JE0202_ROOM_CODE='" + columnMap.get("JE0202_ROOM_CODE") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_ROOM_NAME")!=null && columnMap.containsKey("JE0202_ROOM_NAME") && !columnMap.get("JE0202_ROOM_NAME").equalsIgnoreCase("") ){
			sb.append(" and JE0202_ROOM_NAME='" + columnMap.get("JE0202_ROOM_NAME") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_ROOM_FLOOR")!=null && columnMap.containsKey("JE0202_ROOM_FLOOR") && !columnMap.get("JE0202_ROOM_FLOOR").equalsIgnoreCase("") ){
			sb.append(" and JE0202_ROOM_FLOOR='" + columnMap.get("JE0202_ROOM_FLOOR") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_USER_TYPE")!=null && columnMap.containsKey("JE0202_USER_TYPE") && !columnMap.get("JE0202_USER_TYPE").equalsIgnoreCase("") ){
			sb.append(" and JE0202_USER_TYPE='" + columnMap.get("JE0202_USER_TYPE") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_DES")!=null && columnMap.containsKey("JE0202_DES") && !columnMap.get("JE0202_DES").equalsIgnoreCase("") ){
			sb.append(" and JE0202_DES='" + columnMap.get("JE0202_DES") + "' ");
		}
		if(null != strOrder && !strOrder.equalsIgnoreCase("")){
			sb.append(strOrder);
		}
		
		return getHibernateTemplate().find(sb.toString());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<JE0202> findByColumnName(Map<String, String> columnMap, String strOrder, boolean pageFlag) {
		try{
		final StringBuffer sb = new StringBuffer();
		
		sb.append("from JE0202 where 1 = 1 ");
		if(null != columnMap && columnMap.get("JE0202_DATE")!=null && columnMap.containsKey("JE0202_DATE") && !columnMap.get("JE0202_DATE").equalsIgnoreCase("") ){
			sb.append(" and JE0202_DATE='" + columnMap.get("JE0202_DATE") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_CASE_CODE")!=null && columnMap.containsKey("JE0202_CASE_CODE") && !columnMap.get("JE0202_CASE_CODE").equalsIgnoreCase("") ){
			sb.append(" and JE0202_CASE_CODE='" + columnMap.get("JE0202_CASE_CODE") + "' ");
		}
		
		if(null != columnMap && columnMap.get("JE0202_FROM_DATETIME")!=null && columnMap.containsKey("JE0202_FROM_DATETIME") && !columnMap.get("JE0202_FROM_DATETIME").equalsIgnoreCase("") && !columnMap.get("JE0202_FROM_DATETIME").equalsIgnoreCase("-1")){
			if(null != columnMap && columnMap.get("JE0202_END_DATETIME")!=null && columnMap.containsKey("JE0202_END_DATETIME") && !columnMap.get("JE0202_END_DATETIME").equalsIgnoreCase("") && !columnMap.get("JE0202_END_DATETIME").equalsIgnoreCase("-1")){
				sb.append(" and ((JE0202_FROM_DATETIME <= '" + columnMap.get("JE0202_FROM_DATETIME") + "' ");
				sb.append(" and JE0202_END_DATETIME>='" + columnMap.get("JE0202_END_DATETIME") + "') ");
				sb.append(" or ");
				sb.append(" (JE0202_FROM_DATETIME<='" + columnMap.get("JE0202_FROM_DATETIME") + "' ");
				sb.append(" and JE0202_END_DATETIME>='" + columnMap.get("JE0202_FROM_DATETIME") + "') ");
				sb.append(" or ");
				sb.append(" (JE0202_FROM_DATETIME<='" + columnMap.get("JE0202_END_DATETIME") + "' ");
				sb.append(" and JE0202_END_DATETIME>='" + columnMap.get("JE0202_END_DATETIME") + "')) ");
			}
			else{
				sb.append(" and JE0202_FROM_DATETIME<='" + columnMap.get("JE0202_FROM_DATETIME") + "' ");
				sb.append(" and JE0202_END_DATETIME>='" + columnMap.get("JE0202_FROM_DATETIME") + "' ");
			}
		}
		else if(null != columnMap && columnMap.get("JE0202_END_DATETIME")!=null && columnMap.containsKey("JE0202_END_DATETIME") && !columnMap.get("JE0202_END_DATETIME").equalsIgnoreCase("") && !columnMap.get("JE0202_END_DATETIME").equalsIgnoreCase("-1")){
			sb.append(" and JE0202_FROM_DATETIME<='" + columnMap.get("JE0202_END_DATETIME") + "' ");
			sb.append(" and JE0202_END_DATETIME>='" + columnMap.get("JE0202_END_DATETIME") + "' ");
		}
		
		if(null != columnMap && columnMap.get("JE0202_ROOM_CODE")!=null && columnMap.containsKey("JE0202_ROOM_CODE") && !columnMap.get("JE0202_ROOM_CODE").equalsIgnoreCase("") && !columnMap.get("JE0202_ROOM_CODE").equalsIgnoreCase("-1") ){
			sb.append(" and JE0202_ROOM_CODE='" + columnMap.get("JE0202_ROOM_CODE") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_ROOM_NAME")!=null && columnMap.containsKey("JE0202_ROOM_NAME") && !columnMap.get("JE0202_ROOM_NAME").equalsIgnoreCase("") && !columnMap.get("JE0202_ROOM_NAME").equalsIgnoreCase("-1") ){
			sb.append(" and JE0202_ROOM_NAME='" + columnMap.get("JE0202_ROOM_NAME") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_ROOM_FLOOR")!=null && columnMap.containsKey("JE0202_ROOM_FLOOR") && !columnMap.get("JE0202_ROOM_FLOOR").equalsIgnoreCase("") && !columnMap.get("JE0202_ROOM_FLOOR").equalsIgnoreCase("-1")){
			sb.append(" and JE0202_ROOM_FLOOR='" + columnMap.get("JE0202_ROOM_FLOOR") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_USER_TYPE")!=null && columnMap.containsKey("JE0202_USER_TYPE") && !columnMap.get("JE0202_USER_TYPE").equalsIgnoreCase("") ){
			sb.append(" and JE0202_USER_TYPE='" + columnMap.get("JE0202_USER_TYPE") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_DES")!=null && columnMap.containsKey("JE0202_DES") && !columnMap.get("JE0202_DES").equalsIgnoreCase("") ){
			sb.append(" and JE0202_DES='" + columnMap.get("JE0202_DES") + "' ");
		}
		
		if(null != strOrder && !strOrder.equalsIgnoreCase("")){
			sb.append(strOrder);
		}
		
		if(pageFlag){
			final int pageSize = Integer.parseInt(columnMap.get("pageSize"));
			final int currentPageNum = Integer.parseInt(columnMap.get("currentPageNum"));
			
			return getHibernateTemplate().executeFind(new HibernateCallback(){
				@Override
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					// TODO Auto-generated method stub
					Query query = session.createQuery(sb.toString());
					query.setFirstResult((currentPageNum - 1)*pageSize);
					query.setMaxResults(pageSize);
					
					List<JE0202> list = query.list();
					
					return list;
				}
				
			});
		}
		else{
			return getHibernateTemplate().find(sb.toString());
		}
		}catch(RuntimeException e){
			log.info(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public int getAllRowsCount(){ //for page
		//Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		session.beginTransaction();
		int count = ((Long)session.createQuery("select count(*) from JE0202").iterate().next()).intValue();
		session.getTransaction().commit();
		session.close();
		return count;
	}
	
	@Override
	public int getSearchConditionRowsCount(Map<String, String> columnMap){
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from JE0202 where 1 = 1 ");
		if(null != columnMap && columnMap.get("JE0202_DATE")!=null && columnMap.containsKey("JE0202_DATE") && !columnMap.get("JE0202_DATE").equalsIgnoreCase("") ){
			sb.append(" and JE0202_DATE='" + columnMap.get("JE0202_DATE") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_CASE_CODE")!=null && columnMap.containsKey("JE0202_CASE_CODE") && !columnMap.get("JE0202_CASE_CODE").equalsIgnoreCase("") ){
			sb.append(" and JE0202_CASE_CODE='" + columnMap.get("JE0202_CASE_CODE") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_FROM_DATETIME")!=null && columnMap.containsKey("JE0202_FROM_DATETIME") && !columnMap.get("JE0202_FROM_DATETIME").equalsIgnoreCase("") && !columnMap.get("JE0202_FROM_DATETIME").equalsIgnoreCase("-1")){
			sb.append(" and JE0202_FROM_DATETIME<='" + columnMap.get("JE0202_FROM_DATETIME") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_END_DATETIME")!=null && columnMap.containsKey("JE0202_END_DATETIME") && !columnMap.get("JE0202_END_DATETIME").equalsIgnoreCase("") && !columnMap.get("JE0202_END_DATETIME").equalsIgnoreCase("-1")){
			sb.append(" and JE0202_END_DATETIME>='" + columnMap.get("JE0202_END_DATETIME") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_ROOM_CODE")!=null && columnMap.containsKey("JE0202_ROOM_CODE") && !columnMap.get("JE0202_ROOM_CODE").equalsIgnoreCase("") && !columnMap.get("JE0202_ROOM_CODE").equalsIgnoreCase("-1")){
			sb.append(" and JE0202_ROOM_CODE='" + columnMap.get("JE0202_ROOM_CODE") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_ROOM_NAME")!=null && columnMap.containsKey("JE0202_ROOM_NAME") && !columnMap.get("JE0202_ROOM_NAME").equalsIgnoreCase("") ){
			sb.append(" and JE0202_ROOM_NAME='" + columnMap.get("JE0202_ROOM_NAME") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_ROOM_FLOOR")!=null && columnMap.containsKey("JE0202_ROOM_FLOOR") && !columnMap.get("JE0202_ROOM_FLOOR").equalsIgnoreCase("") ){
			sb.append(" and JE0202_ROOM_FLOOR='" + columnMap.get("JE0202_ROOM_FLOOR") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_USER_TYPE")!=null && columnMap.containsKey("JE0202_USER_TYPE") && !columnMap.get("JE0202_USER_TYPE").equalsIgnoreCase("") ){
			sb.append(" and JE0202_USER_TYPE='" + columnMap.get("JE0202_USER_TYPE") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0202_DES")!=null && columnMap.containsKey("JE0202_DES") && !columnMap.get("JE0202_DES").equalsIgnoreCase("") ){
			sb.append(" and JE0202_DES='" + columnMap.get("JE0202_DES") + "' ");
		}
		
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		session.beginTransaction();
		int count = ((Long)session.createQuery(sb.toString()).iterate().next()).intValue();
		session.getTransaction().commit();
		session.close();
		return count;
	}
	
	@Override
	public void getSubscriberInfo(JE0202 je0202){
		//get employee information
		Session ss = getHibernateTemplate().getSessionFactory().openSession();
		Query query = ss.createQuery(" from MP1001 m where m.MP1001_EMPLOYEE_NUM=:employeeNum");
		query.setParameter("employeeNum", je0202.getJE0202_USER_NUM());
		MP1001 mp11 = (MP1001)query.uniqueResult();
		je0202.setSubscriberInfo(mp11);
		
		//get department informationa
		Query query2 = ss.createQuery(" from MP0002 m where m.MP0002_SEQ=:departmentId");
		query2.setParameter("departmentId", Integer.parseInt(mp11.getMP1001_DEPARTMENT_ID()));
		MP0002 mp02 = (MP0002)query2.uniqueResult();
		je0202.setSubscriberDepartmentInfo(mp02);
		
		ss.close();
	}
}