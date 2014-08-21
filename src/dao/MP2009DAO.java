package dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import common.UtilCommon;

import entity.MP2009;

public class MP2009DAO  implements IMP2009DAO{
	private SessionFactory sessionFactory;
	
    // 保存数据
    public void save(MP2009 mp2009) {
        if(mp2009 != null){
            sessionFactory.getCurrentSession().save(mp2009);
        }
    }
    // 删除数据
    public void delete(MP2009 mp2009) {
        if(mp2009 != null){
            sessionFactory.getCurrentSession().delete(mp2009);
        }
    }
    // 根据KEY检索数据
    public MP2009 findById(String key) {
        	return (MP2009)sessionFactory.getCurrentSession().get("entity.MP2009", key);
    }
    // 取得所有有效数据
    @SuppressWarnings("unchecked")
    public List<MP2009> findAll() {
//        return getHibernateTemplate().find(" from MP2009 where 1=1  ");
    	return sessionFactory.getCurrentSession().createQuery("from MP2009 where 1=1").list();
    }
    // 更新数据
    public void update(MP2009 mp2009) {
        if(mp2009 != null){
            sessionFactory.getCurrentSession().update(mp2009);
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
    public List<MP2009> findByProperty(String name, String value) {
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();
        queryString.append(" and mp78." + name + "='" + value + "' ");

        List<MP2009> retList = executeSqlStatement(queryString,-1,-1);

        return retList;
    }
    // 分页方法
    public List<MP2009> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();

        // 此处追加检索条件
        if(propertyMap.containsKey("EMP_NUM") && propertyMap.get("EMP_NUM") != null && !propertyMap.get("EMP_NUM").equals("")){
            queryString.append(" and mp2009.MP2009_EMPLOYEE_NUM = '" + propertyMap.get("EMP_NUM") + "' ");
        }
        if(propertyMap.containsKey("YEAR") && propertyMap.get("YEAR") != null && !propertyMap.get("YEAR").equals("")){
            queryString.append(" and mp2009.MP2009_YEAR = '" + propertyMap.get("YEAR") + "' ");
        }
        if(propertyMap.containsKey("MONTH") && propertyMap.get("MONTH") != null && !propertyMap.get("MONTH").equals("")){
            queryString.append(" and mp2009.MP2009_MONTH = '" + propertyMap.get("MONTH") + "' ");
        }
        if(propertyMap.containsKey("DEPARTMENT") && propertyMap.get("DEPARTMENT") != null && !propertyMap.get("DEPARTMENT").equals("")){
            queryString.append(" and mp0002.MP0002_SEQ = '" + propertyMap.get("DEPARTMENT") + "' ");
        }

        List<MP2009> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);

        return retList;
    }
    // 分页方法，取得数据总条数
    public int getRowCountByPage(Map<String, String> propertyMap) {
        List<MP2009> retList = findByPropertyByPage(propertyMap,-1,-1);
        return retList.size();
    }
    // 自定义查询条件及排序方式
    public List<MP2009> findDataBySelfDefined(String search,String order, int pageNum, int pageCount){
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();

        // 此处追加检索条件
        queryString.append(search);
        // 排序方式
        queryString.append(order);

        List<MP2009> retList = executeSqlStatement(queryString, pageNum, pageCount);
        return retList;
    }
    // 构建SQL语句
    private StringBuffer createSqlStatement(){
        StringBuffer queryString = new StringBuffer();

        queryString.append(" select ");
        queryString.append(" mp2009.MP2009_NUM, ");
        queryString.append(" mp2009.MP2009_EMPLOYEE_NUM, ");
        queryString.append(" mp2009.MP2009_YEAR, ");
        queryString.append(" mp2009.MP2009_MONTH, ");
        queryString.append(" mp2009.MP2009_ANNUAL, ");
        queryString.append(" mp2009.MP2009_SICK, ");
        queryString.append(" mp2009.MP2009_FAMILY_RESP, ");
        queryString.append(" mp2009.MP2009_STUDY, ");
        queryString.append(" mp2009.MP2009_MATERNITY, ");
        queryString.append(" mp2009.MP2009_UNPAID, ");
        queryString.append(" mp2009.MP2009_OFFICIAL_BUSINESS, ");
        queryString.append(" mp2009.MP2009_LEAVE_EARLY, ");
        queryString.append(" mp2009.MP2009_LATE, ");
        queryString.append(" mp2009.MP2009_ABSENTEEISM, ");
        queryString.append(" mp1001.MP1001_PREFERED_NAME, ");
        queryString.append(" mp0002.MP0002_DEPARTMENT_NAME ");
        queryString.append(" from MP2009 mp2009, MP1001 mp1001, MP0002 mp0002 ");
        queryString.append(" where 1=1 ");
        queryString.append(" and mp2009.MP2009_EMPLOYEE_NUM = mp1001.MP1001_EMPLOYEE_NUM ");
        queryString.append(" and mp1001.MP1001_DEPARTMENT_ID = mp0002.MP0002_SEQ ");

        return queryString;
    }
    // 检索数据
    @SuppressWarnings("unchecked")
    private List<MP2009> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(queryString.toString());
        if( PAGE_NUM > 0 && PAGE_COUNT > 0){
            query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
            query.setMaxResults(PAGE_COUNT);
        }
        List<Object[]> list = query.list();
        // session.close();

        List<MP2009> retList = getDataList(list);

        return retList;
    }
    // 解析数据
    private List<MP2009> getDataList(List<Object[]> list){
        MP2009 mp2009 = new MP2009();
        List<MP2009> retList = new ArrayList<MP2009>();

        for(int i=0,j=list.size(); i<j; i++){
            mp2009 = new MP2009();
            Object[] obj = list.get(i);

            mp2009.setMP2009_NUM(obj[0] == null ? "" : obj[0].toString());
            mp2009.setMP2009_EMPLOYEE_NUM(obj[1] == null ? "" : obj[1].toString());
            mp2009.setMP2009_YEAR(obj[2] == null ? "" : obj[2].toString());
            mp2009.setMP2009_MONTH(obj[3] == null ? "" : obj[3].toString());
            mp2009.setMP2009_ANNUAL(obj[4] == null ? new BigDecimal("0") : new BigDecimal(obj[4].toString()));
            mp2009.setMP2009_SICK(obj[5] == null ? new BigDecimal("0") : new BigDecimal(obj[5].toString()));
            mp2009.setMP2009_FAMILY_RESP(obj[6] == null ? new BigDecimal("0") : new BigDecimal(obj[6].toString()));
            mp2009.setMP2009_STUDY(obj[7] == null ? new BigDecimal("0") : new BigDecimal(obj[7].toString()));
            mp2009.setMP2009_MATERNITY(obj[8] == null ? new BigDecimal("0") : new BigDecimal(obj[8].toString()));
            mp2009.setMP2009_UNPAID(obj[9] == null ? new BigDecimal("0") : new BigDecimal(obj[9].toString()));
            mp2009.setMP2009_OFFICIAL_BUSINESS(obj[10] == null ? new BigDecimal("0") : new BigDecimal(obj[10].toString()));
            mp2009.setMP2009_LEAVE_EARLY(obj[11] == null ? new BigDecimal("0") : new BigDecimal(obj[11].toString()));
            mp2009.setMP2009_LATE(obj[12] == null ? new BigDecimal("0") : new BigDecimal(obj[12].toString()));
            mp2009.setMP2009_ABSENTEEISM(obj[13] == null ? new BigDecimal("0") : new BigDecimal(obj[13].toString()));
            mp2009.setMP2009_EMPLOYEE_NAME(obj[14] == null ? "" : obj[14].toString());
            mp2009.setMP2009_DEPARTMENT(obj[15] == null ? "" : obj[15].toString());
            
            mp2009.setMP2009_ANNUAL_EXT(obj[4] == null ? "" : UtilCommon.doubleConvertString(new BigDecimal(obj[4].toString())));
            mp2009.setMP2009_SICK_EXT(obj[5] == null ? "" : UtilCommon.doubleConvertString(new BigDecimal(obj[5].toString())));
            mp2009.setMP2009_FAMILY_RESP_EXT(obj[6] == null ? "" : UtilCommon.doubleConvertString(new BigDecimal(obj[6].toString())));
            mp2009.setMP2009_STUDY_EXT(obj[7] == null ? "" : UtilCommon.doubleConvertString(new BigDecimal(obj[7].toString())));
            mp2009.setMP2009_MATERNITY_EXT(obj[8] == null ? "" : UtilCommon.doubleConvertString(new BigDecimal(obj[8].toString())));
            mp2009.setMP2009_UNPAID_EXT(obj[9] == null ? "" : UtilCommon.doubleConvertString(new BigDecimal(obj[9].toString())));
            mp2009.setMP2009_OFFICIAL_BUSINESS_EXT(obj[10] == null ? "" : UtilCommon.doubleConvertString(new BigDecimal(obj[10].toString())));
            mp2009.setMP2009_LEAVE_EARLY_EXT(obj[11] == null ? "" : UtilCommon.doubleConvertString(new BigDecimal(obj[11].toString())));
            mp2009.setMP2009_LATE_EXT(obj[12] == null ? "" : UtilCommon.doubleConvertString(new BigDecimal(obj[12].toString())));
            mp2009.setMP2009_ABSENTEEISM_EXT(obj[13] == null ? "" : UtilCommon.doubleConvertString(new BigDecimal(obj[13].toString())));
            
            retList.add(mp2009);
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
