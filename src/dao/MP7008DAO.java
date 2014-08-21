package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.MP7008;

public class MP7008DAO  implements IMP7008DAO{
	private SessionFactory sessionFactory;
	
	// 保存数据
	public void save(MP7008 mp7008) {
		if(mp7008 != null){
			sessionFactory.getCurrentSession().save(mp7008);
		}
	}
	// 删除数据
	public void delete(MP7008 mp7008) {
		if(mp7008 != null){
			sessionFactory.getCurrentSession().delete(mp7008);
		}
	}
	// 根据KEY检索数据
	public MP7008 findById(String key) {
		return (MP7008)sessionFactory.getCurrentSession().get("entity.MP7008", key);
	}
	// 取得所有有效数据
	@SuppressWarnings("unchecked")
	public List<MP7008> findAll() {
//		return getHibernateTemplate().find(" from MP7008 where 1=1  ");
		return sessionFactory.getCurrentSession().createQuery(" from MP7008 where 1=1  ").list();
	}
	// 更新数据
	public void update(MP7008 mp7008) {
		if(mp7008 != null){
			sessionFactory.getCurrentSession().update(mp7008);
		}
	}
	// 更新数据
	public void executeStatement(String statement){
		Session session = sessionFactory.getCurrentSession();
	    Query query = session.createQuery(statement);
	    query.executeUpdate();
	    // session.close();
	}
	// 动态根据传入的参数，检索数据
	public List<MP7008> findByProperty(String name, String value) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		queryString.append(" and mp78." + name + "='" + value + "' ");
		queryString.append(" order by mp02.MP0002_SEQ, mp78.MP7008_EMPLOYEE_NUM ");
		
		List<MP7008> retList = executeSqlStatement(queryString,-1,-1);
		
		return retList;
	}
	// 分页方法
	public List<MP7008> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
/*		if(propertyMap.containsKey("EMP_NUM") && propertyMap.get("EMP_NUM") != null && !propertyMap.get("EMP_NUM").equals("")){
			queryString.append(" and mp61.MP6001_EMPLOYEE_NUM = '" + propertyMap.get("EMP_NUM") + "' ");
		}*/
		
		queryString.append(" order by mp02.MP0002_SEQ, mp78.MP7008_EMPLOYEE_NUM ");
		
		List<MP7008> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);
		
		return retList;
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		List<MP7008> retList = findByPropertyByPage(propertyMap,-1,-1);
		return retList.size();
	}
	// 自定义查询条件及排序方式
	public List<MP7008> findDataBySelfDefined(String search,String order, int pageNum, int pageCount){
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		queryString.append(search);
		// 排序方式
		queryString.append(order);
		
		List<MP7008> retList = executeSqlStatement(queryString, pageNum, pageCount);
		return retList;
	}
	// 构建SQL语句
	private StringBuffer createSqlStatement(){
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" select ");
		queryString.append(" mp78.MP7008_SEQ, ");
		queryString.append(" mp78.MP7008_PLAN_SEQ, ");
		queryString.append(" mp78.MP7008_EMPLOYEE_NUM, ");
		queryString.append(" mp11.MP1001_PREFERED_NAME, ");
		queryString.append(" mp02.MP0002_DEPARTMENT_NAME, ");
		queryString.append(" mp09.MP0009_POSITION_NAME_E ");
		
		queryString.append(" from MP7008 mp78, MP1001 mp11, MP0002 mp02, MP0009 mp09 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and mp78.MP7008_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM ");
		queryString.append(" and mp11.MP1001_DEPARTMENT_ID = mp02.MP0002_SEQ ");
		queryString.append(" and mp11.MP1001_POSITION = mp09.MP0009_SEQ ");
		
		return queryString;
	}
	// 检索数据
	@SuppressWarnings("unchecked")
	private List<MP7008> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		List<Object[]> list = query.list();
		// session.close();
		
		List<MP7008> retList = getDataList(list);
		
		return retList;
	}
	// 解析数据
	private List<MP7008> getDataList(List<Object[]> list){
		MP7008 mp7008 = new MP7008();
		List<MP7008> retList = new ArrayList<MP7008>();
		
		for(int i=0,j=list.size(); i<j; i++){
			mp7008 = new MP7008();
			Object[] obj = list.get(i);
			
			if(obj[0] == null){
				continue;
			}else{
				mp7008.setMP7008_SEQ(obj[0].toString());
			}

			mp7008.setMP7008_PLAN_SEQ(obj[1] == null ? "" : obj[1].toString());
			mp7008.setMP7008_EMPLOYEE_NUM(obj[2] == null ? "" : obj[2].toString());
			mp7008.setMP7008_EMPLOYEE_NAME(obj[3] == null ? "" : obj[3].toString());
			mp7008.setMP7008_DEPARTMENT(obj[4] == null ? "" : obj[4].toString());
			mp7008.setMP7008_JOB_TITLE(obj[5] == null ? "" : obj[5].toString());
			
			retList.add(mp7008);
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