package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.MP8006;

public class MP8006DAO extends HibernateDaoSupport implements IMP8006DAO{
    // 保存数据
    public void save(MP8006 mp8006) {
        if(mp8006 != null){
            getHibernateTemplate().save(mp8006);
        }
    }
    // 删除数据
    public void delete(MP8006 mp8006) {
        if(mp8006 != null){
            getHibernateTemplate().delete(mp8006);
        }
    }
    // 根据KEY检索数据
    public MP8006 findById(String key) {
        	return (MP8006)getHibernateTemplate().get("entity.MP8006", key);
    }
    // 取得所有有效数据
    @SuppressWarnings("unchecked")
    public List<MP8006> findAll() {
        return getHibernateTemplate().find(" from MP8006 where 1=1  ");
    }
    // 更新数据
    public void update(MP8006 mp8006) {
        if(mp8006 != null){
            getHibernateTemplate().update(mp8006);
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
	// 更新数据
	private void updateData(Session session, String score, String key, String examCode){
		StringBuffer queryString = new StringBuffer();
		queryString.append(" update MP8006 set ");
		queryString.append("  MP8006_SCORES = '"  + score + "' ");
		queryString.append("  where 1=1 ");
		if(key != null && !key.equals("")){
			queryString.append("  and MP8006_MASTER_KEY = '" + key + "' ");
		}
		if(examCode != null && !examCode.equals("")){
			queryString.append("  and MP8006_EXAM_CODE = '" + examCode + "' ");
		}
		
	    Query query = session.createQuery(queryString.toString());
	    query.executeUpdate();
	}
    // 更新数据
    public void executeStatement(String statement){
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        Query query = session.createQuery(statement);
        query.executeUpdate();
        session.close();
    }
    // 动态根据传入的参数，检索数据
    public List<MP8006> findByProperty(String name, String value) {
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();
        queryString.append(" and mp8006." + name + "='" + value + "' ");

        List<MP8006> retList = executeSqlStatement(queryString,-1,-1);

        return retList;
    }
    // 分页方法
    public List<MP8006> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();

        // 此处追加检索条件
        /*if(propertyMap.containsKey("EMP_NUM") && propertyMap.get("EMP_NUM") != null && !propertyMap.get("EMP_NUM").equals("")){
            queryString.append(" and mp61.MP6001_EMPLOYEE_NUM = '" + propertyMap.get("EMP_NUM") + "' ");
        }*/

        List<MP8006> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);

        return retList;
    }
    // 分页方法，取得数据总条数
    public int getRowCountByPage(Map<String, String> propertyMap) {
        List<MP8006> retList = findByPropertyByPage(propertyMap,-1,-1);
        return retList.size();
    }
    // 自定义查询条件及排序方式
    public List<MP8006> findDataBySelfDefined(String search,String order, int pageNum, int pageCount){
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();

        // 此处追加检索条件
        queryString.append(search);
        // 排序方式
        queryString.append(order);

        List<MP8006> retList = executeSqlStatement(queryString, pageNum, pageCount);
        return retList;
    }
    // 构建SQL语句
    private StringBuffer createSqlStatement(){
        StringBuffer queryString = new StringBuffer();

        queryString.append(" select ");
        queryString.append(" mp8006.MP8006_SEQ, ");
        queryString.append(" mp8006.MP8006_MASTER_KEY, ");
        queryString.append(" mp8006.MP8006_EXAM_CODE, ");
        queryString.append(" mp8006.MP8006_SCORES, ");
        queryString.append(" mp8001.MP8001_TITLE ");
        queryString.append(" from MP8006 mp8006, MP8001 mp8001 ");
        queryString.append(" where 1=1 ");
        queryString.append(" and mp8006.MP8006_EXAM_CODE = mp8001.MP8001_SEQ ");

        return queryString;
    }
    // 检索数据
    @SuppressWarnings("unchecked")
    private List<MP8006> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        Query query = session.createQuery(queryString.toString());
        if( PAGE_NUM > 0 && PAGE_COUNT > 0){
            query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
            query.setMaxResults(PAGE_COUNT);
        }
        List<Object[]> list = query.list();
        session.close();

        List<MP8006> retList = getDataList(list);

        return retList;
    }
    // 解析数据
    private List<MP8006> getDataList(List<Object[]> list){
        MP8006 mp8006 = new MP8006();
        List<MP8006> retList = new ArrayList<MP8006>();

        for(int i=0,j=list.size(); i<j; i++){
            mp8006 = new MP8006();
            Object[] obj = list.get(i);

            mp8006.setMP8006_SEQ(obj[0] == null ? "" : obj[0].toString());
            mp8006.setMP8006_MASTER_KEY(obj[1] == null ? "" : obj[1].toString());
            mp8006.setMP8006_EXAM_CODE(obj[2] == null ? "" : obj[2].toString());
            mp8006.setMP8006_SCORES(obj[3] == null ? "" : obj[3].toString());
            mp8006.setMP8006_EXAM_NAME(obj[4] == null ? "" : obj[4].toString());
            retList.add(mp8006);
        }
        return retList;
    }
}

