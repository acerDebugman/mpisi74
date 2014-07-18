package com.joe.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.joe.model.MP1010;

public class MP1010DAO extends HibernateDaoSupport implements IMP1010DAO{
    // 保存数据
    public void save(MP1010 mp1010) {
        if(mp1010 != null){
            getHibernateTemplate().save(mp1010);
        }
    }
    // 删除数据
    public void delete(MP1010 mp1010) {
        if(mp1010 != null){
            getHibernateTemplate().delete(mp1010);
        }
    }
    // 根据KEY检索数据
    public MP1010 findById(String key) {
        	return (MP1010)getHibernateTemplate().get("entity.MP1010", key);
    }
    // 取得所有有效数据
    @SuppressWarnings("unchecked")
    public List<MP1010> findAll() {
        return getHibernateTemplate().find(" from MP1010 where 1=1  ");
    }
    // 更新数据
    public void update(MP1010 mp1010) {
        if(mp1010 != null){
            getHibernateTemplate().update(mp1010);
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
    public List<MP1010> findByProperty(String name, String value) {
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();
        queryString.append(" and mp1010." + name + "='" + value + "' ");

        List<MP1010> retList = executeSqlStatement(queryString,-1,-1);

        return retList;
    }
    // 分页方法
    public List<MP1010> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();

        // 此处追加检索条件
        if(propertyMap.containsKey("PARAM1") && propertyMap.get("PARAM1") != null && !propertyMap.get("PARAM1").equals("")){
            queryString.append(" and mp1010.MP1010_EMPLOYEE_NUM = '" + propertyMap.get("PARAM1") + "' ");
        }
        if(propertyMap.containsKey("PARAM2") && propertyMap.get("PARAM2") != null && !propertyMap.get("PARAM2").equals("")){
            queryString.append(" and mp1010.MP1010_PREFERED_NAME like '%" + propertyMap.get("PARAM2") + "%' ");
        }
        if(propertyMap.containsKey("PARAM3") && propertyMap.get("PARAM3") != null && !propertyMap.get("PARAM3").equals("")){
            queryString.append(" and mp1010.MP1010_DEPARTMENT = '" + propertyMap.get("PARAM3") + "' ");
        }
        if(propertyMap.containsKey("PARAM4") && propertyMap.get("PARAM4") != null && !propertyMap.get("PARAM4").equals("")){
            queryString.append(" and mp1010.MP1010_EMPLOYEE_ID = '" + propertyMap.get("PARAM4") + "' ");
        }
        
        queryString.append(" order by mp1010.MP1010_EMPLOYEE_NUM ");

        List<MP1010> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);

        return retList;
    }
    // 分页方法，取得数据总条数
    public int getRowCountByPage(Map<String, String> propertyMap) {
        List<MP1010> retList = findByPropertyByPage(propertyMap,-1,-1);
        return retList.size();
    }
    // 自定义查询条件及排序方式
    public List<MP1010> findDataBySelfDefined(String search,String order, int pageNum, int pageCount){
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();

        // 此处追加检索条件
        queryString.append(search);
        // 排序方式
        queryString.append(order);

        List<MP1010> retList = executeSqlStatement(queryString, pageNum, pageCount);
        return retList;
    }
    // 构建SQL语句
    private StringBuffer createSqlStatement(){
        StringBuffer queryString = new StringBuffer();

        queryString.append(" select ");
        queryString.append(" mp1010.MP1010_EMPLOYEE_NUM, ");
        queryString.append(" mp1010.MP1010_EMPLOYEE_ID, ");
        queryString.append(" mp1010.MP1010_PREFERED_NAME, ");
        queryString.append(" mp1010.MP1010_DEPARTMENT, ");
        queryString.append(" mp1010.MP1010_GENDER, ");
        queryString.append(" mp1010.MP1010_VISA_TYPE, ");
        queryString.append(" mp1010.MP1010_STATUS, ");
        queryString.append(" mp1010.MP1010_CREATE_USER, ");
        queryString.append(" mp1010.MP1010_CREATE_DATETIME, ");
        queryString.append(" mp1010.MP1010_EDIT_USER, ");
        queryString.append(" mp1010.MP1010_EDIT_DATETIME, ");
        queryString.append(" mp02.MP0002_DEPARTMENT_NAME, ");
		queryString.append(" case ");
		queryString.append("     when mp1010.MP1010_GENDER = '1' then 'Male' ");
		queryString.append("     when mp1010.MP1010_GENDER = '2' then 'Female' ");
		queryString.append(" end as MP1010_GENDER_NAME, ");
		
		queryString.append(" mp1010.MP1010_FIRST_NAME, ");
		queryString.append(" mp1010.MP1010_TEL, ");
		queryString.append(" mp1010.MP1010_NATIONAL, ");
		queryString.append(" mp1010.MP1010_PASSWORD, ");
		queryString.append(" mp1010.MP1010_PAYROLL_NUM ");
		
        queryString.append(" from MP1010 mp1010, MP0002 mp02 ");
        queryString.append(" where 1=1 ");
        queryString.append(" and mp1010.MP1010_DEPARTMENT = mp02.MP0002_SEQ ");
        queryString.append(" and mp1010.MP1010_STATUS = '1' ");
        

        return queryString;
    }
    // 检索数据
    @SuppressWarnings("unchecked")
    private List<MP1010> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        Query query = session.createQuery(queryString.toString());
        if( PAGE_NUM > 0 && PAGE_COUNT > 0){
            query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
            query.setMaxResults(PAGE_COUNT);
        }
        List<Object[]> list = query.list();
        session.close();

        List<MP1010> retList = getDataList(list);

        return retList;
    }
    // 解析数据
    private List<MP1010> getDataList(List<Object[]> list){
        MP1010 mp1010 = new MP1010();
        List<MP1010> retList = new ArrayList<MP1010>();

        for(int i=0,j=list.size(); i<j; i++){
            mp1010 = new MP1010();
            Object[] obj = list.get(i);

            mp1010.setMP1010_EMPLOYEE_NUM(obj[0] == null ? "" : obj[0].toString());
            mp1010.setMP1010_EMPLOYEE_ID(obj[1] == null ? "" : obj[1].toString());
            mp1010.setMP1010_PREFERED_NAME(obj[2] == null ? "" : obj[2].toString());
            mp1010.setMP1010_DEPARTMENT(obj[3] == null ? "" : obj[3].toString());
            mp1010.setMP1010_GENDER(obj[4] == null ? "" : obj[4].toString());
            mp1010.setMP1010_VISA_TYPE(obj[5] == null ? "" : obj[5].toString());
            mp1010.setMP1010_STATUS(obj[6] == null ? "" : obj[6].toString());
            mp1010.setMP1010_CREATE_USER(obj[7] == null ? "" : obj[7].toString());
            mp1010.setMP1010_CREATE_DATETIME(obj[8] == null ? "" : obj[8].toString());
            mp1010.setMP1010_EDIT_USER(obj[9] == null ? "" : obj[9].toString());
            mp1010.setMP1010_EDIT_DATETIME(obj[10] == null ? "" : obj[10].toString());
            mp1010.setMP1010_DEPARTMENT_NAME(obj[11] == null ? "" : obj[11].toString());
            mp1010.setMP1010_GENDER_NAME(obj[12] == null ? "" : obj[12].toString());
            mp1010.setMP1010_FIRST_NAME(obj[13] == null ? "" : obj[13].toString());
            mp1010.setMP1010_TEL(obj[14] == null ? "" : obj[14].toString());
            mp1010.setMP1010_NATIONAL(obj[15] == null ? "" : obj[15].toString());
            mp1010.setMP1010_PASSWORD(obj[16] == null ? "" : obj[16].toString());
            mp1010.setMP1010_PAYROLL_NUM(obj[17] == null ? "" : obj[17].toString());

            retList.add(mp1010);
        }
        return retList;
    }
}