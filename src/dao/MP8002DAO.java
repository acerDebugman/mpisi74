package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import common.Constant;

import entity.MP8002;

public class MP8002DAO extends HibernateDaoSupport implements IMP8002DAO{
    // 保存数据
    public void save(MP8002 mp8002) {
        if(mp8002 != null){
            getHibernateTemplate().save(mp8002);
        }
    }
    // 删除数据
    public void delete(MP8002 mp8002) {
        if(mp8002 != null){
            getHibernateTemplate().delete(mp8002);
        }
    }
    // 根据KEY检索数据
    public MP8002 findById(String key) {
        	return (MP8002)getHibernateTemplate().get("entity.MP8002", key);
    }
    // 取得所有有效数据
    @SuppressWarnings("unchecked")
    public List<MP8002> findAll() {
        return getHibernateTemplate().find(" from MP8002 where 1=1 and MP8002_STATUS = '1' ");
    }
    // 更新数据
    public void update(MP8002 mp8002) {
        if(mp8002 != null){
            getHibernateTemplate().update(mp8002);
        }
    }
    // 更新数据
    public void executeStatement(String statement){
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        Query query = session.createQuery(statement);
        query.executeUpdate();
        session.close();
    }
    // 动态根据传入的参数，检索数据
    public List<MP8002> findByProperty(String name, String value) {
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();
        queryString.append(" and mp8002." + name + "='" + value + "' ");

        List<MP8002> retList = executeSqlStatement(queryString,-1,-1);

        return retList;
    }
    // 分页方法
    public List<MP8002> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();

		// 此处追加检索条件
		if(propertyMap.containsKey("PLAN_STATUS") && propertyMap.get("PLAN_STATUS") != null && !propertyMap.get("PLAN_STATUS").equals("") && !propertyMap.get("PLAN_STATUS").equals("-1")){
			queryString.append(" and mp8002.MP8002_STATUS = '" + propertyMap.get("PLAN_STATUS") + "' ");
		}else{
			queryString.append(" and mp8002.MP8002_STATUS = '1' ");
		}
		if(propertyMap.containsKey("PLAN_NUM") && propertyMap.get("PLAN_NUM") != null && !propertyMap.get("PLAN_NUM").equals("")){
			queryString.append(" and mp8002.MP8002_SEQ = '" + propertyMap.get("PLAN_NUM") + "' ");
		}
		if(propertyMap.containsKey("PLAN_TITLE") && propertyMap.get("PLAN_TITLE") != null && !propertyMap.get("PLAN_TITLE").equals("")){
			queryString.append(" and mp8002.MP8002_TITLE like '%" + propertyMap.get("PLAN_TITLE") + "%' ");
		}
		if(propertyMap.containsKey("PLAN_YEAR") && propertyMap.get("PLAN_YEAR") != null && !propertyMap.get("PLAN_YEAR").equals("")){
			queryString.append(" and mp8002.MP8002_YEAR = '" + propertyMap.get("PLAN_YEAR") + "' ");
		}
		if(propertyMap.containsKey("PLAN_MONTH") && propertyMap.get("PLAN_MONTH") != null && !propertyMap.get("PLAN_MONTH").equals("") && !propertyMap.get("PLAN_MONTH").equals("-1")){
			queryString.append(" and mp8002.MP8002_MONTH = '" + propertyMap.get("PLAN_MONTH") + "' ");
		}
		
		// 此处追加数据排序
		//queryString.append(" order by mp72.MP8002_YEAR, cast(mp72.MP8002_MONTH as int), mp72.MP8002_SEQ ");
		queryString.append(" order by mp8002.MP8002_SEQ desc ");

        List<MP8002> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);

        return retList;
    }
    // 分页方法，取得数据总条数
    public int getRowCountByPage(Map<String, String> propertyMap) {
        List<MP8002> retList = findByPropertyByPage(propertyMap,-1,-1);
        return retList.size();
    }
    // 自定义查询条件及排序方式
    public List<MP8002> findDataBySelfDefined(String search,String order, int pageNum, int pageCount){
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();

        // 此处追加检索条件
        queryString.append(search);
        // 排序方式
        queryString.append(order);

        List<MP8002> retList = executeSqlStatement(queryString, pageNum, pageCount);
        return retList;
    }
    // 构建SQL语句
    private StringBuffer createSqlStatement(){
        StringBuffer queryString = new StringBuffer();

        queryString.append(" select ");
        queryString.append(" mp8002.MP8002_SEQ, ");
        queryString.append(" mp8002.MP8002_TITLE, ");
        queryString.append(" mp8002.MP8002_YEAR, ");
        queryString.append(" mp8002.MP8002_MONTH, ");
        queryString.append(" mp8002.MP8002_COMMENT, ");
        queryString.append(" mp8002.MP8002_CREATOR, ");
        queryString.append(" mp8002.MP8002_STATUS, ");
        queryString.append(" mp8002.MP8002_EXECUTE_STATUS, ");
        queryString.append(" mp8002.MP8002_FINISH_STATUS, ");
        queryString.append(" mp8002.MP8002_DEPARTMENT_NUM, ");
        queryString.append(" mp0002.MP0002_DEPARTMENT_NAME ");
        queryString.append(" from MP8002 mp8002, MP0002 mp0002 ");
        queryString.append(" where 1=1 ");
        queryString.append(" and mp8002.MP8002_DEPARTMENT_NUM = mp0002.MP0002_SEQ ");

        return queryString;
    }
    // 检索数据
    @SuppressWarnings("unchecked")
    private List<MP8002> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        Query query = session.createQuery(queryString.toString());
        if( PAGE_NUM > 0 && PAGE_COUNT > 0){
            query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
            query.setMaxResults(PAGE_COUNT);
        }
        List<Object[]> list = query.list();
        session.close();

        List<MP8002> retList = getDataList(list);

        return retList;
    }
    // 解析数据
    private List<MP8002> getDataList(List<Object[]> list){
        MP8002 mp8002 = new MP8002();
        List<MP8002> retList = new ArrayList<MP8002>();

        for(int i=0,j=list.size(); i<j; i++){
            mp8002 = new MP8002();
            Object[] obj = list.get(i);

			if(obj[0] == null){
				continue;
			}else{
				mp8002.setMP8002_SEQ(obj[0].toString());
			}
            mp8002.setMP8002_TITLE(obj[1] == null ? "" : obj[1].toString());
            mp8002.setMP8002_YEAR(obj[2] == null ? "" : obj[2].toString());
            mp8002.setMP8002_MONTH(obj[3] == null ? "" : obj[3].toString());
            mp8002.setMP8002_COMMENT(obj[4] == null ? "" : obj[4].toString());
            mp8002.setMP8002_CREATOR(obj[5] == null ? "" : obj[5].toString());
            mp8002.setMP8002_STATUS(obj[6] == null ? "" : obj[6].toString());
            mp8002.setMP8002_EXECUTE_STATUS(obj[7] == null ? "" : obj[7].toString());
            mp8002.setMP8002_FINISH_STATUS(obj[8] == null ? "" : obj[8].toString());
            mp8002.setMP8002_DEPARTMENT_NUM(obj[9] == null ? "" : obj[9].toString());
            mp8002.setMP8002_DEPARTMENT_NAME(obj[10] == null ? "" : obj[10].toString());

			// 状态
			if(mp8002.getMP8002_STATUS().equals("1")){
				mp8002.setMP8002_STATUS_NAME(Constant.VALID);
			}else{
				mp8002.setMP8002_STATUS_NAME(Constant.INVALID);
			}
			// 备注
			if(mp8002.getMP8002_COMMENT() != null && !mp8002.getMP8002_COMMENT().equals("")){
				if(mp8002.getMP8002_COMMENT().length() > 200){
					mp8002.setMP8002_COMMENT2(mp8002.getMP8002_COMMENT().substring(0, 180) + "......");
				}else{
					mp8002.setMP8002_COMMENT2(mp8002.getMP8002_COMMENT());
				}
			}else{
				mp8002.setMP8002_COMMENT2(" ");
			}
            
            retList.add(mp8002);
        }
        return retList;
    }
}

