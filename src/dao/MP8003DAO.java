package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.MP8003;

public class MP8003DAO  implements IMP8003DAO{
	private SessionFactory sessionFactory;
	
    // 保存数据
    public void save(MP8003 mp8003) {
        if(mp8003 != null){
            sessionFactory.getCurrentSession().save(mp8003);
        }
    }
    // 删除数据
    public void delete(MP8003 mp8003) {
        if(mp8003 != null){
            sessionFactory.getCurrentSession().delete(mp8003);
        }
    }
    // 根据KEY检索数据
    public MP8003 findById(String key) {
        	return (MP8003)sessionFactory.getCurrentSession().get("entity.MP8003", key);
    }
    // 取得所有有效数据
    @SuppressWarnings("unchecked")
    public List<MP8003> findAll() {
//        return getHibernateTemplate().find(" from MP8003 where 1=1  ");
    	return sessionFactory.getCurrentSession().createQuery("from MP8003 where 1=1 ").list();
    }
    // 更新数据
    public void update(MP8003 mp8003) {
        if(mp8003 != null){
            sessionFactory.getCurrentSession().update(mp8003);
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
    public List<MP8003> findByProperty(String name, String value) {
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();
        queryString.append(" and mp8003." + name + "='" + value + "' ");

        List<MP8003> retList = executeSqlStatement(queryString,-1,-1);

        return retList;
    }
    // 分页方法
    public List<MP8003> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();

        // 此处追加检索条件
        /*if(propertyMap.containsKey("EMP_NUM") && propertyMap.get("EMP_NUM") != null && !propertyMap.get("EMP_NUM").equals("")){
            queryString.append(" and mp61.MP6001_EMPLOYEE_NUM = '" + propertyMap.get("EMP_NUM") + "' ");
        }*/

        List<MP8003> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);

        return retList;
    }
    // 分页方法，取得数据总条数
    public int getRowCountByPage(Map<String, String> propertyMap) {
        List<MP8003> retList = findByPropertyByPage(propertyMap,-1,-1);
        return retList.size();
    }
    // 自定义查询条件及排序方式
    public List<MP8003> findDataBySelfDefined(String search,String order, int pageNum, int pageCount){
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();

        // 此处追加检索条件
        queryString.append(search);
        // 排序方式
        queryString.append(order);

        List<MP8003> retList = executeSqlStatement(queryString, pageNum, pageCount);
        return retList;
    }
    // 构建SQL语句
    private StringBuffer createSqlStatement(){
        StringBuffer queryString = new StringBuffer();

        queryString.append(" select ");
        queryString.append(" mp8003.MP8003_SEQ, ");
        queryString.append(" mp8003.MP8003_MASTER_KEY, ");
        queryString.append(" mp8003.MP8003_EXAM_CODE, ");
        queryString.append(" mp8003.MP8003_WEIGHTAGE, ");
        queryString.append(" mp8001.MP8001_TITLE ");
        queryString.append(" from MP8003 mp8003, MP8001 mp8001 ");
        queryString.append(" where 1=1 ");
        queryString.append(" and mp8003.MP8003_EXAM_CODE = mp8001.MP8001_SEQ ");
        
        return queryString;
    }
    // 检索数据
    @SuppressWarnings("unchecked")
    private List<MP8003> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString.toString());
        if( PAGE_NUM > 0 && PAGE_COUNT > 0){
            query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
            query.setMaxResults(PAGE_COUNT);
        }
        List<Object[]> list = query.list();
        // session.close();

        List<MP8003> retList = getDataList(list);

        return retList;
    }
    // 解析数据
    private List<MP8003> getDataList(List<Object[]> list){
        MP8003 mp8003 = new MP8003();
        List<MP8003> retList = new ArrayList<MP8003>();

        for(int i=0,j=list.size(); i<j; i++){
            mp8003 = new MP8003();
            Object[] obj = list.get(i);
            mp8003.setMP8003_SEQ(obj[0] == null ? "" : obj[0].toString());
            mp8003.setMP8003_MASTER_KEY(obj[1] == null ? "" : obj[1].toString());
            mp8003.setMP8003_EXAM_CODE(obj[2] == null ? "" : obj[2].toString());
            mp8003.setMP8003_WEIGHTAGE(obj[3] == null ? "" : obj[3].toString());
            mp8003.setMP8003_EXAM_TITLE(obj[4] == null ? "" : obj[4].toString());
            
            retList.add(mp8003);
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

