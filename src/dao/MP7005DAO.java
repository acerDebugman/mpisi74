package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.MP7005;

public class MP7005DAO extends HibernateDaoSupport implements IMP7005DAO{
	// 保存数据
	public void save(MP7005 mp7005) {
		if(mp7005 != null){
			getHibernateTemplate().save(mp7005);
		}
	}
	// 删除数据
	public void delete(MP7005 mp7005) {
		if(mp7005 != null){
			getHibernateTemplate().delete(mp7005);
		}
	}
	// 根据KEY检索数据
	public MP7005 findById(String key) {
		return (MP7005)getHibernateTemplate().get("entity.MP7005", key);
	}
	// 取得所有有效数据
	@SuppressWarnings("unchecked")
	public List<MP7005> findAll() {
		return getHibernateTemplate().find(" from MP7005 where 1=1 and MP7005_STATUS = '1' ");
	}
	// 更新数据
	public void update(MP7005 mp7005) {
		if(mp7005 != null){
			getHibernateTemplate().update(mp7005);
		}
	}
	// 更新数据
	public void updateDataBySelf(List<String> list1, List<String> list2, List<String> list3, List<String> list4, List<String> list5, String key){
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		session.beginTransaction();
		
		// 先把所有考题分数置空
		updateData(session, "", key, "");

        for(int i=0;i<list1.size();i++){
            updateData(session, "1", key, list1.get(i));
        }	
        for(int i=0;i<list2.size();i++){
        	updateData(session, "2", key, list2.get(i));
        }	
        for(int i=0;i<list3.size();i++){
        	updateData(session, "3", key, list3.get(i));
        }	
        for(int i=0;i<list4.size();i++){
        	updateData(session, "4", key, list4.get(i));
        }	
        for(int i=0;i<list5.size();i++){
        	updateData(session, "5", key, list5.get(i));
        }
        
	    session.getTransaction().commit();	    
	    session.close();
		
	}
	// 动态根据传入的参数，检索数据
	public List<MP7005> findByProperty(String name, String value) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		queryString.append(" and mp75." + name + "='" + value + "' ");
		
		List<MP7005> retList = executeSqlStatement(queryString,-1,-1);
		
		return retList;
	}
	// 分页方法
	public List<MP7005> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
/*		if(propertyMap.containsKey("EMP_NUM") && propertyMap.get("EMP_NUM") != null && !propertyMap.get("EMP_NUM").equals("")){
			queryString.append(" and mp61.MP6001_EMPLOYEE_NUM = '" + propertyMap.get("EMP_NUM") + "' ");
		}*/
		
		List<MP7005> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);
		
		return retList;
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		List<MP7005> retList = findByPropertyByPage(propertyMap,-1,-1);
		return retList.size();
	}
	// 自定义查询条件及排序方式
	public List<MP7005> findDataBySelfDefined(String search,String order, int pageNum, int pageCount){
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		queryString.append(search);
		// 排序方式
		queryString.append(order);
		
		List<MP7005> retList = executeSqlStatement(queryString, pageNum, pageCount);
		return retList;
	}
	
	// 更新数据
	private void updateData(Session session, String score, String key, String examCode){
		//String queryString = " update MP7005 set MP7005_SCORES = '" + score + "' where MP7005_MASTER_KEY = '" + key + "' and MP7005_EXAM_CODE ='" +  examCode + "' ";
		StringBuffer queryString = new StringBuffer();
		queryString.append(" update MP7005 set ");
		queryString.append("  MP7005_SCORES = '"  + score + "' ");
		queryString.append("  where 1=1 ");
		if(key != null && !key.equals("")){
			queryString.append("  and MP7005_MASTER_KEY = '" + key + "' ");
		}
		if(examCode != null && !examCode.equals("")){
			queryString.append("  and MP7005_EXAM_CODE = '" + examCode + "' ");
		}
		
	    Query query = session.createQuery(queryString.toString());
	    query.executeUpdate();
	}
	// 构建SQL语句
	private StringBuffer createSqlStatement(){
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" select ");
		queryString.append(" mp75.MP7005_SEQ, ");
		queryString.append(" mp75.MP7005_MASTER_KEY, ");
		queryString.append(" mp75.MP7005_EXAM_CODE, ");
		queryString.append(" mp75.MP7005_SCORES, ");
		queryString.append(" mp71.MP7001_TITLE ");

		queryString.append(" from MP7005 mp75, MP7001 mp71 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and mp75.MP7005_EXAM_CODE = mp71.MP7001_SEQ ");
		
		return queryString;
	}
	// 检索数据
	@SuppressWarnings("unchecked")
	private List<MP7005> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
		Session session = getHibernateTemplate().getSessionFactory().openSession();		
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		List<Object[]> list = query.list();
		session.close();
		
		List<MP7005> retList = getDataList(list);
		
		return retList;
	}
	// 解析数据
	private List<MP7005> getDataList(List<Object[]> list){
		MP7005 mp7005 = new MP7005();
		List<MP7005> retList = new ArrayList<MP7005>();
		
		for(int i=0,j=list.size(); i<j; i++){
			mp7005 = new MP7005();
			Object[] obj = list.get(i);
			
			if(obj[0] == null){
				continue;
			}else{
				mp7005.setMP7005_SEQ(obj[0].toString());
			}
			mp7005.setMP7005_MASTER_KEY(obj[1] == null ? "" : obj[1].toString());
			mp7005.setMP7005_EXAM_CODE(obj[2] == null ? "" : obj[2].toString());
			mp7005.setMP7005_SCORES(obj[3] == null ? "" : obj[3].toString());
			mp7005.setMP7005_EXAM_NAME(obj[4] == null ? "" : obj[4].toString());
			
			retList.add(mp7005);
		}
		return retList;
	}
}
