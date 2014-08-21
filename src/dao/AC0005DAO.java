package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import common.Constant;

import entity.AC0005;

public class AC0005DAO  implements IAC0005DAO {
	
	private SessionFactory sessionFactory;
	
	public void save(AC0005 ac0005) {
		if (ac0005 != null) {
			sessionFactory.getCurrentSession().save(ac0005);
		}
	}

	public void delete(AC0005 ac0005) {
		sessionFactory.getCurrentSession().delete(ac0005);
	}

	public AC0005 findById(String seq) {
		return (AC0005) sessionFactory.getCurrentSession().get("entity.AC0005", seq);
	}

	@SuppressWarnings("unchecked")
	public List<AC0005> findAll() {
//		return getHibernateTemplate().find("from AC0005");
		return sessionFactory.getCurrentSession().createQuery("from AC0005").list();
	}

	public void update(AC0005 ac0005) {
		sessionFactory.getCurrentSession().update(ac0005);
	}
	
	@SuppressWarnings("unchecked")
	public List<AC0005> findByProperty(String name, String value){
		try{
//			String queryString = " from AC0005 as bulletin where bulletin." + name + " = ?";
			String queryString = " from AC0005 as bulletin where bulletin." + name + " = '" + value + "'";
//			return getHibernateTemplate().find(queryString, value);
			return sessionFactory.getCurrentSession().createQuery(queryString).list();
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	
	// 分页方法
	public List<AC0005> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		//queryString.append(" and ac01.AC0001_STATUS = '1' ");
		if(propertyMap.containsKey("ROLE_NUM") && propertyMap.get("ROLE_NUM") != null && !propertyMap.get("ROLE_NUM").equals("")){
			queryString.append(" and ac05.AC0005_ROLE_NUM = '" + propertyMap.get("ROLE_NUM")  + "' ");
		}
		if(propertyMap.containsKey("ROLE_NAME") && propertyMap.get("ROLE_NAME") != null && !propertyMap.get("ROLE_NAME").equals("")){
			queryString.append(" and ac05.AC0005_ROLE_NAME = '" + propertyMap.get("ROLE_NAME")  + "' ");
		}
		if(propertyMap.containsKey("ROLE_STATUS") && propertyMap.get("ROLE_STATUS") != null && !propertyMap.get("ROLE_STATUS").equals("") && !propertyMap.get("ROLE_STATUS").equals("-1")){
			queryString.append(" and ac05.AC0005_ROLE_STATUS = '" + propertyMap.get("ROLE_STATUS")  + "' ");
		}
		// 排序条件
		queryString.append(" order by ac05.AC0005_ROLE_NUM ");
		
		List<AC0005> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);
		
		return retList;
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		List<AC0005> retList = findByPropertyByPage(propertyMap,-1,-1);
		return retList.size();
	}

	// 构建SQL语句
	private StringBuffer createSqlStatement(){
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" select ");
		
		queryString.append(" AC0005_ROLE_NUM, ");
		queryString.append(" AC0005_ROLE_NAME, ");
		queryString.append(" AC0005_ROLE_STATUS, ");
		queryString.append(" AC0005_ROLE_DESC, ");
		queryString.append(" AC0005_ROLE_COMMENT, ");
		queryString.append(" AC0005_CREATE_USER, ");
		queryString.append(" AC0005_CREATE_DATETIME, ");
		queryString.append(" AC0005_EDIT_USER, ");
		queryString.append(" AC0005_EDIT_DATETIME ");

		queryString.append(" from AC0005 ac05 ");
		queryString.append(" where 1=1 ");
		
		return queryString;
	}
	// 检索数据
	@SuppressWarnings("unchecked")
	private List<AC0005> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
//		Session session = sessionFactory.getCurrentSession();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		List<Object[]> list = query.list();
//		// session.close();
		
		List<AC0005> retList = getDataList(list);
		
		return retList;
	}
	// 解析数据
	private List<AC0005> getDataList(List<Object[]> list){
		AC0005 ac0005 = new AC0005();
		List<AC0005> retList = new ArrayList<AC0005>();
		
		for(int i=0,j=list.size(); i<j; i++){
			ac0005 = new AC0005();
			Object[] obj = list.get(i);
			
			ac0005.setAC0005_ROLE_NUM(obj[0] == null ? "" : obj[0].toString());
			ac0005.setAC0005_ROLE_NAME(obj[1] == null ? "" : obj[1].toString());
			ac0005.setAC0005_ROLE_STATUS(obj[2] == null ? "" : obj[2].toString());
			ac0005.setAC0005_ROLE_DESC(obj[3] == null ? "" : obj[3].toString());
			ac0005.setAC0005_ROLE_COMMENT(obj[4] == null ? "" : obj[4].toString());
			ac0005.setAC0005_CREATE_USER(obj[5] == null ? "" : obj[5].toString());
			ac0005.setAC0005_CREATE_DATETIME(obj[6] == null ? "" : obj[6].toString());
			ac0005.setAC0005_EDIT_USER(obj[7] == null ? "" : obj[7].toString());
			ac0005.setAC0005_EDIT_DATETIME(obj[8] == null ? "" : obj[8].toString());

			if(ac0005.getAC0005_ROLE_STATUS().equals("1")){
				ac0005.setAC0005_ROLE_STATUS_NAME(Constant.VALID);
			}else{
				ac0005.setAC0005_ROLE_STATUS_NAME(Constant.INVALID);
			}
			
			retList.add(ac0005);
		}
		return retList;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}