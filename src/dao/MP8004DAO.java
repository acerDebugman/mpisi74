package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.MP8004;

public class MP8004DAO  implements IMP8004DAO{
	private SessionFactory sessionFactory;
	
    // 保存数据
    public void save(MP8004 mp8004) {
        if(mp8004 != null){
            sessionFactory.getCurrentSession().save(mp8004);
        }
    }
    // 删除数据
    public void delete(MP8004 mp8004) {
        if(mp8004 != null){
            sessionFactory.getCurrentSession().delete(mp8004);
        }
    }
    // 根据KEY检索数据
    public MP8004 findById(String key) {
        	return (MP8004)sessionFactory.getCurrentSession().get("entity.MP8004", key);
    }
    // 取得所有有效数据
    @SuppressWarnings("unchecked")
    public List<MP8004> findAll() {
//        return getHibernateTemplate().find(" from MP8004 where 1=1  ");
        return sessionFactory.getCurrentSession().createQuery("from MP8004 where 1=1  ").list();
    }
    // 更新数据
    public void update(MP8004 mp8004) {
        if(mp8004 != null){
            sessionFactory.getCurrentSession().update(mp8004);
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
    public List<MP8004> findByProperty(String name, String value) {
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();
        queryString.append(" and mp8004." + name + "='" + value + "' ");

        List<MP8004> retList = executeSqlStatement(queryString,-1,-1);

        return retList;
    }
    // 分页方法
    public List<MP8004> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();

        // 此处追加检索条件
        /*if(propertyMap.containsKey("EMP_NUM") && propertyMap.get("EMP_NUM") != null && !propertyMap.get("EMP_NUM").equals("")){
            queryString.append(" and mp61.MP6001_EMPLOYEE_NUM = '" + propertyMap.get("EMP_NUM") + "' ");
        }*/

        List<MP8004> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);

        return retList;
    }
    // 分页方法，取得数据总条数
    public int getRowCountByPage(Map<String, String> propertyMap) {
        List<MP8004> retList = findByPropertyByPage(propertyMap,-1,-1);
        return retList.size();
    }
    // 自定义查询条件及排序方式
    public List<MP8004> findDataBySelfDefined(String search,String order, int pageNum, int pageCount){
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();

        // 此处追加检索条件
        queryString.append(search);
        // 排序方式
        queryString.append(order);

        List<MP8004> retList = executeSqlStatement(queryString, pageNum, pageCount);
        return retList;
    }
    // 构建SQL语句
    private StringBuffer createSqlStatement(){
        StringBuffer queryString = new StringBuffer();

        queryString.append(" select ");
        queryString.append(" mp8004.MP8004_SEQ, ");
        queryString.append(" mp8004.MP8004_PLAN_SEQ, ");
        queryString.append(" mp8004.MP8004_DEPARTMENT_NUM, ");
        queryString.append(" mp0002.MP0002_DEPARTMENT_NAME ");        
        queryString.append(" from MP8004 mp8004, MP0002 mp0002 ");
        queryString.append(" where 1=1 ");
        queryString.append(" and mp8004.MP8004_DEPARTMENT_NUM = mp0002.MP0002_SEQ ");

        return queryString;
    }
    // 检索数据
    @SuppressWarnings("unchecked")
    private List<MP8004> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString.toString());
        if( PAGE_NUM > 0 && PAGE_COUNT > 0){
            query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
            query.setMaxResults(PAGE_COUNT);
        }
        List<Object[]> list = query.list();
        // session.close();

        List<MP8004> retList = getDataList(list);

        return retList;
    }
    // 解析数据
    private List<MP8004> getDataList(List<Object[]> list){
        MP8004 mp8004 = new MP8004();
        List<MP8004> retList = new ArrayList<MP8004>();

        for(int i=0,j=list.size(); i<j; i++){
            mp8004 = new MP8004();
            Object[] obj = list.get(i);

            mp8004.setMP8004_SEQ(obj[0] == null ? "" : obj[0].toString());
            mp8004.setMP8004_PLAN_SEQ(obj[1] == null ? "" : obj[1].toString());
            mp8004.setMP8004_DEPARTMENT_NUM(obj[2] == null ? "" : obj[2].toString());
            mp8004.setMP8004_DEPARTMENT(obj[3] == null ? "" : obj[3].toString());
            retList.add(mp8004);
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

