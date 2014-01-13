package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import common.Constant;

import entity.AC0002;

public class AC0002DAO extends HibernateDaoSupport implements IAC0002DAO {
	
	public void save(AC0002 ac0002) {
		if (ac0002 != null) {
			getHibernateTemplate().save(ac0002);
		}
	}

	public void delete(AC0002 ac0002) {
		getHibernateTemplate().delete(ac0002);
	}

	public AC0002 findById(String seq) {
		return (AC0002) getHibernateTemplate().get("entity.AC0002", seq);
	}

	@SuppressWarnings("unchecked")
	public List<AC0002> findAll() {
		return getHibernateTemplate().find("from AC0002");
	}

	public void update(AC0002 ac0002) {
		getHibernateTemplate().update(ac0002);
	}
	
	public List<AC0002> findByProperty(String name, String value){
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();

		queryString.append(" and ac02." + name + "='" + value + "' ");
		queryString.append(" order by ac02.AC0002_SYS_NUM,ac02.AC0002_FUN_NUM,ac02.AC0002_PARENT_NUM ");
		
		List<AC0002> retList = executeSqlStatement(queryString,-1,-1);
		
		return retList;
	}
	
	// 分页方法
	public List<AC0002> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		//queryString.append(" and ac01.AC0001_STATUS = '1' ");
		if(propertyMap.containsKey("FUNCTION_NUM") && propertyMap.get("FUNCTION_NUM") != null && !propertyMap.get("FUNCTION_NUM").equals("")){
			queryString.append(" and ac02.AC0002_FUN_NUM = '" + propertyMap.get("FUNCTION_NUM")  + "' ");
		}
		if(propertyMap.containsKey("FUNCTION_NAME") && propertyMap.get("FUNCTION_NAME") != null && !propertyMap.get("FUNCTION_NAME").equals("")){
			queryString.append(" and ac02.AC0002_FUN_NAME = '" + propertyMap.get("FUNCTION_NAME")  + "' ");
		}
		if(propertyMap.containsKey("FUNCTION_STATUS") && propertyMap.get("FUNCTION_STATUS") != null && !propertyMap.get("FUNCTION_STATUS").equals("") && !propertyMap.get("FUNCTION_STATUS").equals("-1")){
			queryString.append(" and ac02.AC0002_STATUS = '" + propertyMap.get("FUNCTION_STATUS")  + "' ");
		}
		// 排序条件
		queryString.append(" order by ac02.AC0002_SYS_NUM,ac02.AC0002_FUN_NUM,ac02.AC0002_PARENT_NUM ");
		
		List<AC0002> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);
		
		return retList;
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		List<AC0002> retList = findByPropertyByPage(propertyMap,-1,-1);
		return retList.size();
	}

	// 构建SQL语句
	private StringBuffer createSqlStatement(){
		StringBuffer queryString = new StringBuffer();
		queryString.append(" select ");
		queryString.append(" ac02.AC0002_FUN_NUM, ");
		queryString.append(" ac02.AC0002_FUN_NAME, ");
		queryString.append(" ac02.AC0002_SYS_NUM, ");
		queryString.append(" ac02.AC0002_STATUS, ");
		queryString.append(" ac02.AC0002_PARENT_NUM, ");
		queryString.append(" ac02.AC0002_ORDER_NUM, ");
		queryString.append(" ac02.AC0002_DES, ");
		queryString.append(" ac02.AC0002_COMMENT, ");
		queryString.append(" ac02.AC0002_CREATE_USER, ");
		queryString.append(" ac02.AC0002_CREATE_DATE, ");
		queryString.append(" ac02.AC0002_EDIT_USER, ");
		queryString.append(" ac02.AC0002_EDIT_DATE, ");
		queryString.append(" ac01.AC0001_SYS_NAME ");
		queryString.append(" from AC0002 ac02,AC0001 ac01 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and ac02.AC0002_SYS_NUM = ac01.AC0001_SYS_NUM ");

		return queryString;
	}
	// 检索数据
	@SuppressWarnings("unchecked")
	private List<AC0002> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
		Session session = getHibernateTemplate().getSessionFactory().openSession();		
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		List<Object[]> list = query.list();
		session.close();
		
		List<AC0002> retList = getDataList(list);
		
		return retList;
	}
	// 解析数据
	private List<AC0002> getDataList(List<Object[]> list){
		AC0002 ac0002 = new AC0002();
		List<AC0002> retList = new ArrayList<AC0002>();
		
		for(int i=0,j=list.size(); i<j; i++){
			ac0002 = new AC0002();
			Object[] obj = list.get(i);
			
			ac0002.setAC0002_FUN_NUM(obj[0] == null ? "" : obj[0].toString());
			ac0002.setAC0002_FUN_NAME(obj[1] == null ? "" : obj[1].toString());
			ac0002.setAC0002_SYS_NUM(obj[2] == null ? "" : obj[2].toString());
			ac0002.setAC0002_STATUS(obj[3] == null ? "" : obj[3].toString());
			ac0002.setAC0002_PARENT_NUM(obj[4] == null ? "" : obj[4].toString());
			ac0002.setAC0002_ORDER_NUM(Integer.parseInt( obj[5] == null ? "0":obj[5].toString()));
			ac0002.setAC0002_DES(obj[6] == null ? "" : obj[6].toString());
			ac0002.setAC0002_COMMENT(obj[7] == null ? "" : obj[7].toString());
			ac0002.setAC0002_CREATE_USER(obj[8] == null ? "" : obj[8].toString());
			ac0002.setAC0002_CREATE_DATE(obj[9] == null ? "" : obj[9].toString());
			ac0002.setAC0002_EDIT_USER(obj[10] == null ? "" : obj[10].toString());
			ac0002.setAC0002_EDIT_DATE(obj[11] == null ? "" : obj[11].toString());
			ac0002.setAC0002_SYS_NAME(obj[12] == null ? "" : obj[12].toString());
			//ac0002.setAC0002_PARENT_NAME(obj[13] == null ? "" : obj[13].toString());
			
			if(ac0002.getAC0002_STATUS().equals("1")){
				ac0002.setAC0002_STATUS_NAME(Constant.VALID);
			}else{
				ac0002.setAC0002_STATUS_NAME(Constant.INVALID);
			}
			
			retList.add(ac0002);
		}
		return retList;
	}
}