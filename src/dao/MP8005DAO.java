package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.MP8005;

public class MP8005DAO extends HibernateDaoSupport implements IMP8005DAO{
    // 保存数据
    public void save(MP8005 mp8005) {
        if(mp8005 != null){
            getHibernateTemplate().save(mp8005);
        }
    }
    // 删除数据
    public void delete(MP8005 mp8005) {
        if(mp8005 != null){
            getHibernateTemplate().delete(mp8005);
        }
    }
    // 根据KEY检索数据
    public MP8005 findById(String key) {
        	return (MP8005)getHibernateTemplate().get("entity.MP8005", key);
    }
    // 取得所有有效数据
    @SuppressWarnings("unchecked")
    public List<MP8005> findAll() {
        return getHibernateTemplate().find(" from MP8005 where 1=1  ");
    }
    // 更新数据
    public void update(MP8005 mp8005) {
        if(mp8005 != null){
            getHibernateTemplate().update(mp8005);
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
    public List<MP8005> findByProperty(String name, String value) {
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();
        queryString.append(" and mp8005." + name + "='" + value + "' ");

        List<MP8005> retList = executeSqlStatement(queryString,-1,-1);

        return retList;
    }
    // 分页方法
    public List<MP8005> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();

        // 此处追加检索条件
        queryString.append(" and mp8005.MP8005_STATUS = '1' ");
		if(propertyMap.containsKey("REVIEW_PERIOD") && propertyMap.get("REVIEW_PERIOD") != null && !propertyMap.get("REVIEW_PERIOD").equals("")){
			queryString.append(" and mp8005.MP8005_REVIEW_PERIOD = '" + propertyMap.get("REVIEW_PERIOD") + "' ");
		}
		if(propertyMap.containsKey("EMP_DEP") && propertyMap.get("EMP_DEP") != null && !propertyMap.get("EMP_DEP").equals("") && !propertyMap.get("EMP_DEP").equals("-1")){
			queryString.append(" and mp8005.MP8005_DEPARTMENT = '" + propertyMap.get("EMP_DEP") + "' ");
		}
		if(propertyMap.containsKey("APPRAISER") && propertyMap.get("APPRAISER") != null && !propertyMap.get("APPRAISER").equals("") && !propertyMap.get("APPRAISER").equals("-1")){
			queryString.append(" and mp8005.MP8005_APPRAISER = '" + propertyMap.get("APPRAISER") + "' ");
		}
/*		if(propertyMap.containsKey("EMP_DEP") && propertyMap.get("EMP_DEP") != null && !propertyMap.get("EMP_DEP").equals("") && !propertyMap.get("EMP_DEP").equals("-1")){
			// 全权限
			if(propertyMap.containsKey("AUT_ALL") && propertyMap.get("AUT_ALL") != null && propertyMap.get("AUT_ALL").equals("1")){
				queryString.append(" and mp8005.MP8005_DEPARTMENT = '" + propertyMap.get("EMP_DEP") + "') ");
			}else{
				// 部门级权限
				if(propertyMap.containsKey("DEPARTMENT") && propertyMap.get("DEPARTMENT") != null && !propertyMap.get("DEPARTMENT").equals("")){
					String dep1 = propertyMap.get("EMP_DEP");
					String dep2 = propertyMap.get("DEPARTMENT");
					
					if(dep1.equals(dep2)){
						queryString.append(" and mp8005.MP8005_DEPARTMENT = '" + dep2 + "') ");
					}else{
						queryString.append(" and mp8005.MP8005_DEPARTMENT = '" + dep1 + "' ");
						if(propertyMap.containsKey("APPRAISER") && propertyMap.get("APPRAISER") != null && !propertyMap.get("APPRAISER").equals("")){
							queryString.append(" and  mp8005.MP8005_APPRAISER = '" + propertyMap.get("APPRAISER") + "' ");
						}
					}
				}else{
					queryString.append(" and mp8005.MP8005_DEPARTMENT = '" + propertyMap.get("EMP_DEP") + "' ");
					if(propertyMap.containsKey("APPRAISER") && propertyMap.get("APPRAISER") != null && !propertyMap.get("APPRAISER").equals("")){
						queryString.append(" and mp8005.MP8005_APPRAISER = '" + propertyMap.get("APPRAISER") + "' ");
					}
				}
			}
		}else{
			// 全权限
			if(propertyMap.containsKey("AUT_ALL") && propertyMap.get("AUT_ALL") != null && propertyMap.get("AUT_ALL").equals("1")){
				// nothing to do
			}else{
				// 部门级权限
				if(propertyMap.containsKey("DEPARTMENT") && propertyMap.get("DEPARTMENT") != null && !propertyMap.get("DEPARTMENT").equals("")){
					// 有指定绩效评定人
					if(propertyMap.containsKey("APPRAISER") && propertyMap.get("APPRAISER") != null && !propertyMap.get("APPRAISER").equals("")){
						queryString.append(" and ( mp8005.MP8005_APPRAISER = '" + propertyMap.get("APPRAISER") + "' ");
						queryString.append(" or mp8005.MP8005_DEPARTMENT = '" + propertyMap.get("DEPARTMENT") + "') ");
					}else{// 无指定绩效评定人
						queryString.append(" and mp8005.MP8005_DEPARTMENT = '" + propertyMap.get("DEPARTMENT") + "' ");
					}
				}else{// 普通员工权限
					if(propertyMap.containsKey("APPRAISER") && propertyMap.get("APPRAISER") != null && !propertyMap.get("APPRAISER").equals("")){
						queryString.append(" and mp8005.MP8005_APPRAISER = '" + propertyMap.get("APPRAISER") + "' ");
					}
				}
			}
		}*/
		
		// 排序条件
		queryString.append(" order by MP8005_REVIEW_PERIOD desc, MP8005_DEPARTMENT asc ");
		
        List<MP8005> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);

        return retList;
    }
    // 分页方法，取得数据总条数
    public int getRowCountByPage(Map<String, String> propertyMap) {
        List<MP8005> retList = findByPropertyByPage(propertyMap,-1,-1);
        return retList.size();
    }
    // 自定义查询条件及排序方式
    public List<MP8005> findDataBySelfDefined(String search,String order, int pageNum, int pageCount){
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();

        // 此处追加检索条件
        queryString.append(search);
        // 排序方式
        queryString.append(order);

        List<MP8005> retList = executeSqlStatement(queryString, pageNum, pageCount);
        return retList;
    }
    
    @SuppressWarnings("unchecked")
	public List<MP8005> getReportList(String reviewPeriod, String DepId){
    	StringBuffer queryString = new StringBuffer();
    	queryString.append(" select mp8005.MP8005_DEPARTMENT, mp0002.MP0002_DEPARTMENT_NAME,mp8005.MP8005_REVIEW_PERIOD, ");
    	queryString.append(" convert(numeric(18,2), round(avg(convert(decimal(18,2),mp8005.MP8005_SCORES)),2)) as MP8005_SCORES ");
    	queryString.append(" from MP8005 mp8005, MP0002 mp0002 ");
    	queryString.append(" where 1=1 ");
    	queryString.append(" and mp8005.MP8005_DEPARTMENT = mp0002.MP0002_SEQ ");
    	if(reviewPeriod != null && !reviewPeriod.equals("")){
    		queryString.append(" and mp8005.MP8005_REVIEW_PERIOD = '" + reviewPeriod + "' ");
    	}
    	if(DepId != null && !DepId.equals("") && !DepId.equals("-1")){
    		queryString.append(" and mp8005.MP8005_DEPARTMENT = '" + DepId + "' ");
    	}
    	queryString.append(" group by mp8005.MP8005_DEPARTMENT, mp0002.MP0002_DEPARTMENT_NAME, mp8005.MP8005_REVIEW_PERIOD ");
    	
    	Session session = getHibernateTemplate().getSessionFactory().openSession();
    	Query query = session.createQuery(queryString.toString());
    	List<Object[]> list = query.list();
    	session.close();
    	
    	MP8005 mp8005 = new MP8005();
    	List<MP8005> retList = new ArrayList<MP8005>();
    	for(int i=0,j=list.size(); i<j; i++){
    		Object[] obj = list.get(i);
    		mp8005 = new MP8005();
    		mp8005.setMP8005_DEPARTMENT(obj[0] == null ? "" : obj[0].toString());
    		mp8005.setMP8005_DEPARTMENT_NAME(obj[1] == null ? "" : obj[1].toString());
    		mp8005.setMP8005_REVIEW_PERIOD(obj[2] == null ? "" : obj[2].toString());
    		mp8005.setMP8005_SCORES(obj[3] == null ? "" : obj[3].toString());
    		
    		retList.add(mp8005);
    	}
    	return retList;
    }
    
    // 构建SQL语句
    private StringBuffer createSqlStatement(){
        StringBuffer queryString = new StringBuffer();

        queryString.append(" select ");
        queryString.append(" mp8005.MP8005_SEQ, ");
        queryString.append(" mp8005.MP8005_EMPLOYEE_NUM, ");
        queryString.append(" mp8005.MP8005_JOB_TITLE, ");
        queryString.append(" mp8005.MP8005_DEPARTMENT, ");
        queryString.append(" mp8005.MP8005_MANAGER, ");
        queryString.append(" mp8005.MP8005_REVIEW_PERIOD, ");
        queryString.append(" mp8005.MP8005_SCORES, ");
        queryString.append(" mp8005.MP8005_COMMENT, ");
        queryString.append(" mp8005.MP8005_STATUS, ");
        queryString.append(" mp8005.MP8005_APPRAISER, ");
        queryString.append(" mp8005.MP8005_APPRAISER_DESIGNATION, ");
        queryString.append(" mp0002.MP0002_DEPARTMENT_NAME ");
        queryString.append(" from MP8005 mp8005, MP0002 mp0002 ");
        queryString.append(" where 1=1 ");
        queryString.append(" and mp8005.MP8005_DEPARTMENT = mp0002.MP0002_SEQ ");        

        return queryString;
    }
    // 检索数据
    @SuppressWarnings("unchecked")
    private List<MP8005> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        Query query = session.createQuery(queryString.toString());
        if( PAGE_NUM > 0 && PAGE_COUNT > 0){
            query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
            query.setMaxResults(PAGE_COUNT);
        }
        List<Object[]> list = query.list();
        session.close();

        List<MP8005> retList = getDataList(list);

        return retList;
    }
    // 解析数据
    private List<MP8005> getDataList(List<Object[]> list){
        MP8005 mp8005 = new MP8005();
        List<MP8005> retList = new ArrayList<MP8005>();

        for(int i=0,j=list.size(); i<j; i++){
            mp8005 = new MP8005();
            Object[] obj = list.get(i);

            mp8005.setMP8005_SEQ(obj[0] == null ? "" : obj[0].toString());
            mp8005.setMP8005_EMPLOYEE_NUM(obj[1] == null ? "" : obj[1].toString());
            mp8005.setMP8005_JOB_TITLE(obj[2] == null ? "" : obj[2].toString());
            mp8005.setMP8005_DEPARTMENT(obj[3] == null ? "" : obj[3].toString());
            mp8005.setMP8005_MANAGER(obj[4] == null ? "" : obj[4].toString());
            mp8005.setMP8005_REVIEW_PERIOD(obj[5] == null ? "" : obj[5].toString());
            mp8005.setMP8005_SCORES(obj[6] == null ? "" : obj[6].toString());
            mp8005.setMP8005_COMMENT(obj[7] == null ? "" : obj[7].toString());
            mp8005.setMP8005_STATUS(obj[8] == null ? "" : obj[8].toString());
            mp8005.setMP8005_APPRAISER(obj[9] == null ? "" : obj[9].toString());
            mp8005.setMP8005_APPRAISER_DESIGNATION(obj[10] == null ? "" : obj[10].toString());
            mp8005.setMP8005_DEPARTMENT_NAME(obj[11] == null ? "" : obj[11].toString());
            
            retList.add(mp8005);
        }
        return retList;
    }
}

