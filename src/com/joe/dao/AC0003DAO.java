package com.joe.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.joe.common.Constant;
import com.joe.model.AC0003;

public class AC0003DAO extends HibernateDaoSupport implements IAC0003DAO {
	
	public void save(AC0003 ac0003) {
		if (ac0003 != null) {
			getHibernateTemplate().save(ac0003);
		}
	}

	public void delete(AC0003 ac0003) {
		getHibernateTemplate().delete(ac0003);
	}

	public AC0003 findById(String seq) {
		return (AC0003) getHibernateTemplate().get("entity.AC0003", seq);
	}

	@SuppressWarnings("unchecked")
	public List<AC0003> findAll() {
		return getHibernateTemplate().find("from AC0003");
	}

	public void update(AC0003 ac0003) {
		getHibernateTemplate().update(ac0003);
	}
	
	@SuppressWarnings("unchecked")
	public List<AC0003> findByProperty(String name, String value){
		try{
			String queryString = " from AC0003 as bulletin where bulletin." + name + " = ?";
			return getHibernateTemplate().find(queryString, value);
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	// 取得系统功能操作
	public List<AC0003> getFunctionOperationInfoData(String functionNum){
		StringBuffer queryString = new StringBuffer();
		queryString.append(" select ");
		queryString.append(" ac03.AC0003_OPT_NUM, ");
		queryString.append(" ac03.AC0003_OPT_NAME, ");
		queryString.append(" ac03.AC0003_STATUS, ");
		queryString.append(" ac03.AC0003_DES, ");
		queryString.append(" ac03.AC0003_COMMENT, ");
		queryString.append(" ac03.AC0003_CREATE_USER, ");
		queryString.append(" ac03.AC0003_CREATE_DATE, ");
		queryString.append(" ac03.AC0003_EDIT_USER, ");
		queryString.append(" ac03.AC0003_EDIT_DATE ");
		queryString.append(" from AC0003 ac03,AC0004 ac04 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and ac03.AC0003_OPT_NUM = ac04.AC0004_OPT_NUM ");
		queryString.append(" and ac04.AC0004_FUN_NUM ='" + functionNum + "' ");
		
		// 排序条件
		queryString.append(" order by ac03.AC0003_OPT_NUM ");
		List<AC0003> retList = executeSqlStatement(queryString,-1,-1);
		return retList;
	}
	// 分页方法
	public List<AC0003> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		//queryString.append(" and ac01.AC0001_STATUS = '1' ");
		if(propertyMap.containsKey("OPERATION_NUM") && propertyMap.get("OPERATION_NUM") != null && !propertyMap.get("OPERATION_NUM").equals("")){
			queryString.append(" and ac03.AC0003_OPT_NUM = '" + propertyMap.get("OPERATION_NUM")  + "' ");
		}
		if(propertyMap.containsKey("OPERATION_NAME") && propertyMap.get("OPERATION_NAME") != null && !propertyMap.get("OPERATION_NAME").equals("")){
			queryString.append(" and ac03.AC0003_OPT_NAME = '" + propertyMap.get("OPERATION_NAME")  + "' ");
		}
		if(propertyMap.containsKey("OPERATION_STATUS") && propertyMap.get("OPERATION_STATUS") != null && !propertyMap.get("OPERATION_STATUS").equals("") && !propertyMap.get("OPERATION_STATUS").equals("-1")){
			queryString.append(" and ac03.AC0003_STATUS = '" + propertyMap.get("OPERATION_STATUS")  + "' ");
		}
		// 排序条件
		queryString.append(" order by ac03.AC0003_OPT_NUM ");
		
		List<AC0003> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);
		
		return retList;
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		List<AC0003> retList = findByPropertyByPage(propertyMap,-1,-1);
		return retList.size();
	}

	// 构建SQL语句
	private StringBuffer createSqlStatement(){
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" select ");
		queryString.append(" ac03.AC0003_OPT_NUM, ");
		queryString.append(" ac03.AC0003_OPT_NAME, ");
		queryString.append(" ac03.AC0003_STATUS, ");
		queryString.append(" ac03.AC0003_DES, ");
		queryString.append(" ac03.AC0003_COMMENT, ");
		queryString.append(" ac03.AC0003_CREATE_USER, ");
		queryString.append(" ac03.AC0003_CREATE_DATE, ");
		queryString.append(" ac03.AC0003_EDIT_USER, ");
		queryString.append(" ac03.AC0003_EDIT_DATE ");
		queryString.append(" from AC0003 ac03 ");
		queryString.append(" where 1=1 ");
		
		return queryString;
	}
	// 检索数据
	@SuppressWarnings("unchecked")
	private List<AC0003> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
		Session session = getHibernateTemplate().getSessionFactory().openSession();		
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		List<Object[]> list = query.list();
		session.close();
		
		List<AC0003> retList = getDataList(list);
		
		return retList;
	}
	// 解析数据
	private List<AC0003> getDataList(List<Object[]> list){
		AC0003 ac0003 = new AC0003();
		List<AC0003> retList = new ArrayList<AC0003>();
		
		for(int i=0,j=list.size(); i<j; i++){
			ac0003 = new AC0003();
			Object[] obj = list.get(i);
			
			
			ac0003.setAC0003_OPT_NUM(obj[0] == null ? "" : obj[0].toString());
			ac0003.setAC0003_OPT_NAME(obj[1] == null ? "" : obj[1].toString());
			ac0003.setAC0003_STATUS(obj[2] == null ? "" : obj[2].toString());
			ac0003.setAC0003_DES(obj[3] == null ? "" : obj[3].toString());
			ac0003.setAC0003_COMMENT(obj[4] == null ? "" : obj[4].toString());
			ac0003.setAC0003_CREATE_USER(obj[5] == null ? "" : obj[5].toString());
			ac0003.setAC0003_CREATE_DATE(obj[6] == null ? "" : obj[6].toString());
			ac0003.setAC0003_EDIT_USER(obj[7] == null ? "" : obj[7].toString());
			ac0003.setAC0003_EDIT_DATE(obj[8] == null ? "" : obj[8].toString());

			if(ac0003.getAC0003_STATUS().equals("1")){
				ac0003.setAC0003_STATUS_NAME(Constant.VALID);
			}else{
				ac0003.setAC0003_STATUS_NAME(Constant.INVALID);
			}
			
			retList.add(ac0003);
		}
		return retList;
	}
}