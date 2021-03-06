package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import common.Constant;

import entity.AC0001;

public class AC0001DAO extends HibernateDaoSupport implements IAC0001DAO {
	
	public void save(AC0001 ac0001) {
		if (ac0001 != null) {
			getHibernateTemplate().save(ac0001);
		}
	}

	public void delete(AC0001 ac0001) {
		getHibernateTemplate().delete(ac0001);
	}

	public AC0001 findById(String seq) {
		return (AC0001) getHibernateTemplate().get("entity.AC0001", seq);
	}

	@SuppressWarnings("unchecked")
	public List<AC0001> findAll() {
		return getHibernateTemplate().find("from AC0001");
	}

	public void update(AC0001 ac0001) {
		getHibernateTemplate().update(ac0001);
	}
	
	public List<AC0001> findByProperty(String name, String value){
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		queryString.append(" and ac01." + name + "='" + value + "' ");
		queryString.append(" order by ac01.AC0001_SYS_NUM ");
		
		List<AC0001> retList = executeSqlStatement(queryString,-1,-1);
		
		return retList;
	}
	// 分页方法
	public List<AC0001> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		//queryString.append(" and ac01.AC0001_STATUS = '1' ");
		if(propertyMap.containsKey("SYSTEM_NUM") && propertyMap.get("SYSTEM_NUM") != null && !propertyMap.get("SYSTEM_NUM").equals("")){
			queryString.append(" and ac01.AC0001_SYS_NUM = '" + propertyMap.get("SYSTEM_NUM")  + "' ");
		}
		if(propertyMap.containsKey("SYSTEM_NAME") && propertyMap.get("SYSTEM_NAME") != null && !propertyMap.get("SYSTEM_NAME").equals("")){
			queryString.append(" and ac01.AC0001_SYS_NAME = '" + propertyMap.get("SYSTEM_NAME")  + "' ");
		}
		// 排序条件
		queryString.append(" order by ac01.AC0001_SYS_NUM ");
		
		List<AC0001> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);
		
		return retList;
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		List<AC0001> retList = findByPropertyByPage(propertyMap,-1,-1);
		return retList.size();
	}

	// 构建SQL语句
	private StringBuffer createSqlStatement(){
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" select ");
		queryString.append(" ac01.AC0001_SYS_NUM, ");
		queryString.append(" ac01.AC0001_SYS_NAME, ");
		queryString.append(" ac01.AC0001_STATUS, ");
		queryString.append(" ac01.AC0001_PARENT_NUM, ");
		queryString.append(" ac01.AC0001_ORDER_NUM, ");
		queryString.append(" ac01.AC0001_DES, ");
		queryString.append(" ac01.AC0001_COMMENT, ");
		queryString.append(" ac01.AC0001_CREATE_USER, ");
		queryString.append(" ac01.AC0001_CREATE_DATE, ");
		queryString.append(" ac01.AC0001_EDIT_USER, ");
		queryString.append(" ac01.AC0001_EDIT_DATE ");
		queryString.append(" from AC0001 ac01 ");
		queryString.append(" where 1=1 ");
		
		return queryString;
	}
	// 检索数据
	@SuppressWarnings("unchecked")
	private List<AC0001> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
		Session session = getHibernateTemplate().getSessionFactory().openSession();		
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		List<Object[]> list = query.list();
		session.close();
		
		List<AC0001> retList = getDataList(list);
		
		return retList;
	}
	// 解析数据
	private List<AC0001> getDataList(List<Object[]> list){
		AC0001 ac0001 = new AC0001();
		List<AC0001> retList = new ArrayList<AC0001>();
		
		for(int i=0,j=list.size(); i<j; i++){
			ac0001 = new AC0001();
			Object[] obj = list.get(i);
			
			ac0001.setAC0001_SYS_NUM(obj[0] == null ? "" : obj[0].toString());
			ac0001.setAC0001_SYS_NAME(obj[1] == null ? "" : obj[1].toString());
			ac0001.setAC0001_STATUS(obj[2] == null ? "" : obj[2].toString());			
			ac0001.setAC0001_PARENT_NUM(obj[3] == null ? "" : obj[3].toString());
			ac0001.setAC0001_ORDER_NUM(Integer.parseInt( obj[4] == null ? "0":obj[4].toString()));
			ac0001.setAC0001_DES(obj[5] == null ? "" : obj[5].toString());
			ac0001.setAC0001_COMMENT(obj[6] == null ? "" : obj[6].toString());
			ac0001.setAC0001_CREATE_USER(obj[7] == null ? "" : obj[7].toString());
			ac0001.setAC0001_CREATE_DATE(obj[8] == null ? "" : obj[8].toString());
			ac0001.setAC0001_EDIT_USER(obj[9] == null ? "" : obj[9].toString());
			ac0001.setAC0001_EDIT_DATE(obj[10] == null ? "" : obj[10].toString());
			
			if(ac0001.getAC0001_STATUS().equals("1")){
				ac0001.setAC0001_STATUS_NAME(Constant.VALID);
			}else{
				ac0001.setAC0001_STATUS_NAME(Constant.INVALID);
			}
			
			retList.add(ac0001);
		}
		return retList;
	}
}