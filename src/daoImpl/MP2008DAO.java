package daoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.IMP2008DAO;
import entity.MP2008;

public class MP2008DAO   implements IMP2008DAO{
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
    // 保存数据
    public void save(MP2008 mp2008) {
        if(mp2008 != null){
            sf.getCurrentSession().save(mp2008);
        }
    }
    // 删除数据
    public void delete(MP2008 mp2008) {
        if(mp2008 != null){
            sf.getCurrentSession().delete(mp2008);
        }
    }
    // 根据KEY检索数据
    public MP2008 findById(String key) {
        	return (MP2008)sf.getCurrentSession().get("entity.MP2008", key);
    }
    // 取得所有有效数据
    @SuppressWarnings("unchecked")
    public List<MP2008> findAll() {
        return sf.getCurrentSession().createQuery(" from MP2008 where 1=1  ").list();
    }
    // 更新数据
    public void update(MP2008 mp2008) {
        if(mp2008 != null){
            sf.getCurrentSession().update(mp2008);
        }
    }
    // 更新数据
    public void executeStatement(String statement){
        Session session = sf.getCurrentSession();
        Query query = session.createQuery(statement);
        query.executeUpdate();
//        session.close();
    }
    // 动态根据传入的参数，检索数据
    public List<MP2008> findByProperty(String name, String value) {
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();
        queryString.append(" and mp78." + name + "='" + value + "' ");

        List<MP2008> retList = executeSqlStatement(queryString,-1,-1);

        return retList;
    }
    // 分页方法
    public List<MP2008> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
        StringBuffer queryString = new StringBuffer();
        StringBuffer queryString2 = new StringBuffer();
        queryString = createSqlStatement();
        queryString2 = createSqlStatement2();

        // 此处追加检索条件
        if(propertyMap.containsKey("KEY") && propertyMap.get("KEY") != null && !propertyMap.get("KEY").equals("")){
            queryString.append(" and mp2008.MP2008_NUM = '" + propertyMap.get("KEY") + "' ");
            queryString2.append(" and mp2008.MP2008_NUM = '" + propertyMap.get("KEY") + "' ");
        }
        if(propertyMap.containsKey("PARAM1") && propertyMap.get("PARAM1") != null && !propertyMap.get("PARAM1").equals("")){
            queryString.append(" and mp2008.MP2008_EMPLOYEE_NUM = '" + propertyMap.get("PARAM1") + "' ");
            queryString2.append(" and mp2008.MP2008_EMPLOYEE_NUM = '" + propertyMap.get("PARAM1") + "' ");
        }
        if(propertyMap.containsKey("PARAM2") && propertyMap.get("PARAM2") != null && !propertyMap.get("PARAM2").equals("")){
            queryString.append(" and mp11.MP1001_PREFERED_NAME like '%" + propertyMap.get("PARAM2") + "%' ");
            queryString2.append(" and mp11.MP1010_PREFERED_NAME like '%" + propertyMap.get("PARAM2") + "%' ");
        }
        if(propertyMap.containsKey("PARAM3") && propertyMap.get("PARAM3") != null && !propertyMap.get("PARAM3").equals("")){
            queryString.append(" and mp11.MP1001_DEPARTMENT_ID = '" + propertyMap.get("PARAM3") + "' ");
            queryString2.append(" and mp11.MP1010_DEPARTMENT = '" + propertyMap.get("PARAM3") + "' ");
/*        	if(propertyMap.get("PARAM3").equals("m0076")){
                queryString.append(" and mp11.MP1001_DEPARTMENT_ID in ('15','21','22','23') ");
                queryString2.append(" and mp11.MP1010_DEPARTMENT in ('15','21','22','23') ");
        	}else{
                queryString.append(" and mp11.MP1001_DEPARTMENT_ID = '" + propertyMap.get("PARAM3") + "' ");
                queryString2.append(" and mp11.MP1010_DEPARTMENT = '" + propertyMap.get("PARAM3") + "' ");
        	}*/
        }
        if(propertyMap.containsKey("PARAM4") && propertyMap.get("PARAM4") != null && !propertyMap.get("PARAM4").equals("")){
            queryString.append(" and mp2008.MP2008_APP_STATUS = '" + propertyMap.get("PARAM4") + "' ");
            queryString2.append(" and mp2008.MP2008_APP_STATUS = '" + propertyMap.get("PARAM4") + "' ");
        }
        
        if(propertyMap.containsKey("PARAM5") && propertyMap.get("PARAM5") != null && !propertyMap.get("PARAM5").equals("")){
            queryString.append(" and mp2008.MP2008_DATE >= '" + propertyMap.get("PARAM5") + "' ");
            queryString2.append(" and mp2008.MP2008_DATE >= '" + propertyMap.get("PARAM5") + "' ");
        }
        if(propertyMap.containsKey("PARAM6") && propertyMap.get("PARAM6") != null && !propertyMap.get("PARAM6").equals("")){
            queryString.append(" and mp2008.MP2008_DATE <= '" + propertyMap.get("PARAM6") + "' ");
            queryString2.append(" and mp2008.MP2008_DATE <= '" + propertyMap.get("PARAM6") + "' ");
        }
        
        queryString.append(" order by mp2008.MP2008_DATE desc ");
        queryString2.append(" order by mp2008.MP2008_DATE desc ");

        List<MP2008> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);
        List<MP2008> retList2 = executeSqlStatement(queryString2,PAGE_NUM,PAGE_COUNT);
        retList.addAll(0,retList2);

        return retList;
    }
    // 分页方法，取得数据总条数
    public int getRowCountByPage(Map<String, String> propertyMap) {
        List<MP2008> retList = findByPropertyByPage(propertyMap,-1,-1);
        return retList.size();
    }
    // 自定义查询条件及排序方式
    public List<MP2008> findDataBySelfDefined(String search,String order, int pageNum, int pageCount){
        StringBuffer queryString = new StringBuffer();
        StringBuffer queryString2 = new StringBuffer();
        queryString = createSqlStatement();
        queryString2 = createSqlStatement2();

        // 此处追加检索条件
        queryString.append(search);
        queryString2.append(search);
        // 排序方式
        queryString.append(order);
        queryString2.append(search);

        List<MP2008> retList = executeSqlStatement(queryString, pageNum, pageCount);
        List<MP2008> retList2 = executeSqlStatement(queryString2, pageNum, pageCount);
        retList.addAll(0,retList2);
        
        return retList;
    }
    // 构建SQL语句
    private StringBuffer createSqlStatement(){
        StringBuffer queryString = new StringBuffer();

        queryString.append(" select ");
        queryString.append(" mp2008.MP2008_NUM, ");
        queryString.append(" mp2008.MP2008_EMPLOYEE_NUM, ");
        queryString.append(" mp2008.MP2008_DATE, ");
        queryString.append(" mp2008.MP2008_HOURS, ");
        queryString.append(" mp2008.MP2008_FROM_DATETIME, ");
        queryString.append(" mp2008.MP2008_TO_DATETIME, ");
        queryString.append(" mp2008.MP2008_REASON, ");
        queryString.append(" mp2008.MP2008_APP_STATUS, ");
        queryString.append(" mp2008.MP2008_DATA_STATUS, ");        
        queryString.append(" mp2008.MP2008_COMMENT, ");
        queryString.append(" mp2008.MP2008_CREATE_USER, ");
        queryString.append(" mp2008.MP2008_CREATE_DATETIME, ");
        queryString.append(" mp2008.MP2008_EDIT_USER, ");
        queryString.append(" mp2008.MP2008_EDIT_DATETIME, ");
        queryString.append(" mp2008.MP2008_APPROVAL_USER, ");
        queryString.append(" mp2008.MP2008_APPROVAL_DATETIME, ");
        //queryString.append(" mp11.MP1001_PREFERED_NAME as MP2008_EMPLOYEE_NAME, ");
        //queryString.append(" mp11.MP1001_FIRSTNAME + ' ' + mp11.MP1001_PREFERED_NAME + ' ' +mp11.MP1001_SURNAME as MP2008_EMPLOYEE_NAME,  ");
        queryString.append(" mp11.MP1001_FIRSTNAME + ' ' + mp11.MP1001_SURNAME as MP2008_EMPLOYEE_NAME,  ");
		queryString.append(" case ");
		queryString.append("     when mp2008.MP2008_APP_STATUS = '1' then 'Pending' ");
		queryString.append("     when mp2008.MP2008_APP_STATUS = '2' then 'Pending(HR)' ");
		queryString.append("     when mp2008.MP2008_APP_STATUS = '3' then 'Approved' ");
		queryString.append("     when mp2008.MP2008_APP_STATUS = '4' then 'Declined' ");
		queryString.append(" end as MP2008_APP_STATUS_NAME, ");
		queryString.append(" case ");
		queryString.append("     when mp2008.MP2008_DATA_STATUS = '1' then 'Valid' ");
		queryString.append("     when mp2008.MP2008_DATA_STATUS = '2' then 'Invalid' ");
		queryString.append(" end as MP2008_DATA_STATUS_NAME, ");
		queryString.append(" mp2008.MP2008_DEPARTMENT, ");
		queryString.append(" mp02.MP0002_DEPARTMENT_NAME as MP2008_DEPARTMENT_NAME, ");
		queryString.append(" mp2008.MP2008_FROM_MINUTE, ");
        queryString.append(" mp2008.MP2008_TO_MINUTE,  ");
        queryString.append(" mp11.MP1001_PAYROLL_NUM  as  MP2008_PAYROLL_NUM,  ");
        queryString.append(" mp2008.MP2008_RATING,  ");
        queryString.append(" mp11.MP1001_GENDER,  ");
        queryString.append(" mp2008.MP2008_HOURS_TOTAL  ");
        
        queryString.append(" from MP2008 mp2008, MP1001 mp11, MP0002 mp02 ");
        queryString.append(" where 1=1 ");
        queryString.append(" and mp2008.MP2008_DATA_STATUS = '1' ");
        queryString.append(" and mp11.MP1001_STATUS <> '3' ");
        queryString.append(" and mp2008.MP2008_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM ");
        queryString.append(" and mp2008.MP2008_DEPARTMENT = mp02.MP0002_SEQ ");

        return queryString;
    }
    private StringBuffer createSqlStatement2(){
        StringBuffer queryString = new StringBuffer();

        queryString.append(" select  ");
        queryString.append(" mp2008.MP2008_NUM,  ");
        queryString.append(" mp2008.MP2008_EMPLOYEE_NUM,  ");
        queryString.append(" mp2008.MP2008_DATE,  ");
        queryString.append(" mp2008.MP2008_HOURS,  ");
        queryString.append(" mp2008.MP2008_FROM_DATETIME,  ");
        queryString.append(" mp2008.MP2008_TO_DATETIME,  ");
        queryString.append(" mp2008.MP2008_REASON,  ");
        queryString.append(" mp2008.MP2008_APP_STATUS,  ");
        queryString.append(" mp2008.MP2008_DATA_STATUS,  ");     
        queryString.append(" mp2008.MP2008_COMMENT,  ");
        queryString.append(" mp2008.MP2008_CREATE_USER,  ");
        queryString.append(" mp2008.MP2008_CREATE_DATETIME,  ");
        queryString.append(" mp2008.MP2008_EDIT_USER,  ");
        queryString.append(" mp2008.MP2008_EDIT_DATETIME,  ");
        queryString.append(" mp2008.MP2008_APPROVAL_USER,  ");
        queryString.append(" mp2008.MP2008_APPROVAL_DATETIME,  ");
        queryString.append(" mp11.MP1010_PREFERED_NAME as MP2008_EMPLOYEE_NAME,  ");
        queryString.append(" case  ");
        queryString.append("     when mp2008.MP2008_APP_STATUS = '1' then 'Pending'  ");
        queryString.append("     when mp2008.MP2008_APP_STATUS = '2' then 'Pending(HR)'  ");
        queryString.append("     when mp2008.MP2008_APP_STATUS = '3' then 'Approved'  ");
        queryString.append("     when mp2008.MP2008_APP_STATUS = '4' then 'Declined'  ");
        queryString.append(" end as MP2008_APP_STATUS_NAME,  ");
        queryString.append(" case  ");
        queryString.append("     when mp2008.MP2008_DATA_STATUS = '1' then 'Valid'  ");
        queryString.append("     when mp2008.MP2008_DATA_STATUS = '2' then 'Invalid'  ");
        queryString.append(" end as MP2008_DATA_STATUS_NAME,  ");
        queryString.append(" mp2008.MP2008_DEPARTMENT,  ");
        queryString.append(" mp02.MP0002_DEPARTMENT_NAME as MP2008_DEPARTMENT_NAME,  ");
        queryString.append(" mp2008.MP2008_FROM_MINUTE,  ");
        queryString.append(" mp2008.MP2008_TO_MINUTE,  ");
        queryString.append(" mp11.MP1010_PAYROLL_NUM as  MP2008_PAYROLL_NUM, ");
        queryString.append(" mp2008.MP2008_RATING,  ");
        queryString.append(" mp11.MP1010_GENDER,  ");
        queryString.append(" mp2008.MP2008_HOURS_TOTAL  ");
        
        queryString.append(" from MP2008 mp2008, MP1010 mp11, MP0002 mp02  ");
        queryString.append(" where 1=1  ");
        queryString.append(" and mp2008.MP2008_DATA_STATUS = '1'  ");
        queryString.append(" and mp2008.MP2008_EMPLOYEE_NUM = mp11.MP1010_EMPLOYEE_NUM  ");
        queryString.append(" and mp2008.MP2008_DEPARTMENT = mp02.MP0002_SEQ  ");

        return queryString;
    }
    // 检索数据
    @SuppressWarnings("unchecked")
    private List<MP2008> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
        Session session = sf.getCurrentSession();
        Query query = session.createQuery(queryString.toString());
        
        //Query query = session.createQuery("select mp2008.MP2008_NUM,mp2008.MP2008_EMPLOYEE_NUM,mp2008.MP2008_DATE,mp2008.MP2008_HOURS,mp2008.MP2008_FROM_DATETIME,mp2008.MP2008_TO_DATETIME,mp2008.MP2008_REASON,mp2008.MP2008_APP_STATUS, mp2008.MP2008_DATA_STATUS,mp2008.MP2008_COMMENT,mp2008.MP2008_CREATE_USER,mp2008.MP2008_CREATE_DATETIME,mp2008.MP2008_EDIT_USER,mp2008.MP2008_EDIT_DATETIME,mp2008.MP2008_APPROVAL_USER,mp2008.MP2008_APPROVAL_DATETIME  from MP2008 mp2008, MP1001 mp11 where 1=1 and mp2008.MP2008_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM");
        
        
        if( PAGE_NUM > 0 && PAGE_COUNT > 0){
            query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
            query.setMaxResults(PAGE_COUNT);
        }
        List<Object[]> list = query.list();
//        session.close();

        List<MP2008> retList = getDataList(list);

        return retList;
    }
    // 解析数据
    private List<MP2008> getDataList(List<Object[]> list){
        MP2008 mp2008 = new MP2008();
        List<MP2008> retList = new ArrayList<MP2008>();

        String empName = "";
        for(int i=0,j=list.size(); i<j; i++){
            mp2008 = new MP2008();
            Object[] obj = list.get(i);

            mp2008.setMP2008_NUM(obj[0] == null ? "" : obj[0].toString());
            mp2008.setMP2008_EMPLOYEE_NUM(obj[1] == null ? "" : obj[1].toString());
            mp2008.setMP2008_DATE(obj[2] == null ? "" : obj[2].toString());
            mp2008.setMP2008_HOURS(obj[3] == null ? "" : obj[3].toString());
            mp2008.setMP2008_FROM_DATETIME(obj[4] == null ? "" : obj[4].toString());
            mp2008.setMP2008_TO_DATETIME(obj[5] == null ? "" : obj[5].toString());
            mp2008.setMP2008_REASON(obj[6] == null ? "" : obj[6].toString());
            mp2008.setMP2008_APP_STATUS(obj[7] == null ? "" : obj[7].toString());
            mp2008.setMP2008_DATA_STATUS(obj[8] == null ? "" : obj[8].toString());
            mp2008.setMP2008_COMMENT(obj[9] == null ? "" : obj[9].toString());
            mp2008.setMP2008_CREATE_USER(obj[10] == null ? "" : obj[10].toString());
            mp2008.setMP2008_CREATE_DATETIME(obj[11] == null ? "" : obj[11].toString());
            mp2008.setMP2008_EDIT_USER(obj[12] == null ? "" : obj[12].toString());
            mp2008.setMP2008_EDIT_DATETIME(obj[13] == null ? "" : obj[13].toString());
            mp2008.setMP2008_APPROVAL_USER(obj[14] == null ? "" : obj[14].toString());
            mp2008.setMP2008_APPROVAL_DATETIME(obj[15] == null ? "" : obj[15].toString());
            //mp2008.setMP2008_EMPLOYEE_NAME(obj[16] == null ? "" : obj[16].toString());
            mp2008.setMP2008_APP_STATUS_NAME(obj[17] == null ? "" : obj[17].toString());
            mp2008.setMP2008_DATA_STATUS_NAME(obj[18] == null ? "" : obj[18].toString());
            mp2008.setMP2008_DEPARTMENT(obj[19] == null ? "" : obj[19].toString());
            mp2008.setMP2008_DEPARTMENT_NAME(obj[20] == null ? "" : obj[20].toString());
            mp2008.setMP2008_FROM_MINUTE(obj[21] == null ? "" : obj[21].toString());
            mp2008.setMP2008_TO_MINUTE(obj[22] == null ? "" : obj[22].toString());
            mp2008.setMP2008_PAYROLL_NUM(obj[23] == null ? "" : obj[23].toString());
            mp2008.setMP2008_RATING(obj[24] == null ? "" : obj[24].toString());
            mp2008.setMP2008_GENDER(obj[25] == null ? "" : obj[25].toString());
            mp2008.setMP2008_HOURS_TOTAL(Integer.parseInt(obj[26] == null ? "0" : obj[26].toString()));
            
            empName = (obj[16] == null ? "" : obj[16].toString());
            mp2008.setMP2008_EMPLOYEE_NAME(mp2008.getMP2008_GENDER().equals("1") ? "Mr " + empName : "Ms " + empName );
            
            retList.add(mp2008);
        }
        return retList;
    }
    // Report
    @SuppressWarnings("unchecked")
	public List<MP2008> getSumDataForReport(String formDate, String toDate, String empNum, String empName, String depId, String status){
    	// 生成SQL语句
    	StringBuffer queryString = new StringBuffer();
    	queryString.append(" select ");
    	queryString.append(" mp11.MP1001_PAYROLL_NUM  as  MP2008_PAYROLL_NUM, ");
    	queryString.append(" mp2008.MP2008_EMPLOYEE_NUM, ");
/*    	queryString.append(" case ");
    	queryString.append(" when mp11.MP1001_GENDER = '1' then 'Mr ' + mp11.MP1001_FIRSTNAME + ' ' + mp11.MP1001_SURNAME ");
    	queryString.append(" when mp11.MP1001_GENDER = '2' then 'Ms ' + mp11.MP1001_FIRSTNAME + ' ' + mp11.MP1001_SURNAME ");
    	queryString.append(" end AS MP2008_EMPLOYEE_NAME, ");*/
    	queryString.append(" mp11.MP1001_FIRSTNAME + ' ' + mp11.MP1001_SURNAME,  ");
    	
    	queryString.append(" mp02.MP0002_DEPARTMENT_NAME as MP2008_DEPARTMENT_NAME, ");
    	queryString.append(" case ");
    	queryString.append(" when mp2008.MP2008_APP_STATUS = '1' then 'Pending' ");
    	queryString.append(" when mp2008.MP2008_APP_STATUS = '2' then 'Pending(HR)' ");
    	queryString.append(" when mp2008.MP2008_APP_STATUS = '3' then 'Approved' ");
    	queryString.append(" when mp2008.MP2008_APP_STATUS = '4' then 'Declined' ");
    	queryString.append(" end as MP2008_APP_STATUS_NAME, ");
    	queryString.append(" sum(mp2008.MP2008_HOURS_TOTAL) as HOURS, ");
    	queryString.append(" mp11.MP1001_GENDER,  ");
    	queryString.append(" mp2008.MP2008_RATING  ");
    	
    	queryString.append(" from MP2008 mp2008, MP1001 mp11, MP0002 mp02 ");
    	queryString.append(" where 1=1 ");
    	queryString.append(" and MP2008_DATE >= '" + formDate + "' ");
    	queryString.append(" and MP2008_DATE <= '" + toDate + "' ");
        if(!empNum.equals("")){
        	queryString.append(" and mp2008.MP2008_EMPLOYEE_NUM = '" + empNum + "' ");
        }
        if(!empName.equals("")){
        	queryString.append(" and mp11.MP1001_PREFERED_NAME like '%" + empName + "%' ");
        }
        if(!depId.equals("")){
            queryString.append(" and mp11.MP1001_DEPARTMENT_ID = '" + depId + "' ");
        }
        if(!status.equals("")){
            queryString.append(" and mp2008.MP2008_APP_STATUS = '" + status + "' ");
        }
    	queryString.append(" and mp2008.MP2008_DATA_STATUS = '1' ");
    	queryString.append(" and mp11.MP1001_STATUS <> '3' ");
    	queryString.append(" and mp2008.MP2008_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM ");
    	queryString.append(" and mp2008.MP2008_DEPARTMENT = mp02.MP0002_SEQ ");
    	queryString.append(" group by mp2008.MP2008_RATING, mp2008.MP2008_EMPLOYEE_NUM,mp11.MP1001_PREFERED_NAME,mp11.MP1001_DEPARTMENT_ID,mp11.MP1001_FIRSTNAME,mp11.MP1001_SURNAME,mp02.MP0002_DEPARTMENT_NAME,mp11.MP1001_PAYROLL_NUM,mp11.MP1001_GENDER, MP2008_APP_STATUS ");
    	queryString.append(" order by MP2008_EMPLOYEE_NUM, MP2008_APP_STATUS ");
    	
    	// 执行SQL语句
        Session session = sf.getCurrentSession();
        Query query = session.createQuery(queryString.toString());
        
        List<Object[]> list = query.list();
//        session.close();
        
        // 解析SQL语句
        MP2008 mp2008 = new MP2008();
        List<MP2008> retList = new ArrayList<MP2008>();
        String employeeName = "";
        for(int i=0,j=list.size(); i<j; i++){
            mp2008 = new MP2008();
            Object[] obj = list.get(i);
            
            mp2008.setMP2008_PAYROLL_NUM(obj[0] == null ? "" : obj[0].toString());
            mp2008.setMP2008_EMPLOYEE_NUM(obj[1] == null ? "" : obj[1].toString());
            //mp2008.setMP2008_EMPLOYEE_NAME(obj[2] == null ? "" : obj[2].toString());
            mp2008.setMP2008_DEPARTMENT_NAME(obj[3] == null ? "" : obj[3].toString());
            mp2008.setMP2008_APP_STATUS_NAME(obj[4] == null ? "" : obj[4].toString());
            mp2008.setMP2008_HOURS_TOTAL(Integer.parseInt(obj[5] == null ? "0" : obj[5].toString()));
            mp2008.setMP2008_GENDER(obj[6] == null ? "" : obj[6].toString());
            mp2008.setMP2008_RATING(obj[7] == null ? "" : obj[7].toString());
            
            employeeName = (obj[2] == null ? "" : obj[2].toString());
            mp2008.setMP2008_EMPLOYEE_NAME(mp2008.getMP2008_GENDER().equals("1") ? "Mr " + employeeName : "Ms " + employeeName );
            
            retList.add(mp2008);
        }
        
        return  retList;
    }
    
    @SuppressWarnings("unchecked")
	public List<MP2008> getSumDataForReport2(String formDate, String toDate, String empNum, String empName, String depId, String status){
    	// 生成SQL语句
    	StringBuffer queryString = new StringBuffer();
    	queryString.append(" select ");
    	queryString.append(" mp11.MP1010_PAYROLL_NUM  as  MP2008_PAYROLL_NUM, ");
    	queryString.append(" mp2008.MP2008_EMPLOYEE_NUM, ");
/*    	queryString.append(" case ");
    	queryString.append(" when mp11.MP1001_GENDER = '1' then 'Mr ' + mp11.MP1001_FIRSTNAME + ' ' + mp11.MP1001_SURNAME ");
    	queryString.append(" when mp11.MP1001_GENDER = '2' then 'Ms ' + mp11.MP1001_FIRSTNAME + ' ' + mp11.MP1001_SURNAME ");
    	queryString.append(" end AS MP2008_EMPLOYEE_NAME, ");*/
    	queryString.append(" mp11.MP1010_PREFERED_NAME,  ");
    	
    	queryString.append(" mp02.MP0002_DEPARTMENT_NAME as MP2008_DEPARTMENT_NAME, ");
    	queryString.append(" case ");
    	queryString.append(" when mp2008.MP2008_APP_STATUS = '1' then 'Pending' ");
    	queryString.append(" when mp2008.MP2008_APP_STATUS = '2' then 'Pending(HR)' ");
    	queryString.append(" when mp2008.MP2008_APP_STATUS = '3' then 'Approved' ");
    	queryString.append(" when mp2008.MP2008_APP_STATUS = '4' then 'Declined' ");
    	queryString.append(" end as MP2008_APP_STATUS_NAME, ");
    	queryString.append(" sum(mp2008.MP2008_HOURS_TOTAL) as HOURS, ");
    	queryString.append(" mp11.MP1010_GENDER,  ");
    	queryString.append(" mp2008.MP2008_RATING  ");
    	
    	queryString.append(" from MP2008 mp2008, MP1010 mp11, MP0002 mp02 ");
    	queryString.append(" where 1=1 ");
    	queryString.append(" and MP2008_DATE >= '" + formDate + "' ");
    	queryString.append(" and MP2008_DATE <= '" + toDate + "' ");
        if(!empNum.equals("")){
        	queryString.append(" and mp2008.MP2008_EMPLOYEE_NUM = '" + empNum + "' ");
        }
        if(!empName.equals("")){
        	queryString.append(" and mp11.MP1010_PREFERED_NAME like '%" + empName + "%' ");
        }
        if(!depId.equals("")){
            queryString.append(" and mp11.MP1010_DEPARTMENT = '" + depId + "' ");
        }
        if(!status.equals("")){
            queryString.append(" and mp2008.MP2008_APP_STATUS = '" + status + "' ");
        }
    	queryString.append(" and mp2008.MP2008_DATA_STATUS = '1' ");
    	queryString.append(" and mp11.MP1010_STATUS <> '3' ");
    	queryString.append(" and mp2008.MP2008_EMPLOYEE_NUM = mp11.MP1010_EMPLOYEE_NUM ");
    	queryString.append(" and mp2008.MP2008_DEPARTMENT = mp02.MP0002_SEQ ");
    	queryString.append(" group by mp2008.MP2008_RATING, mp2008.MP2008_EMPLOYEE_NUM,mp11.MP1010_PREFERED_NAME,mp11.MP1010_DEPARTMENT,mp11.MP1010_PREFERED_NAME,mp02.MP0002_DEPARTMENT_NAME,mp11.MP1010_PAYROLL_NUM,mp11.MP1010_GENDER, MP2008_APP_STATUS ");
    	queryString.append(" order by MP2008_EMPLOYEE_NUM, MP2008_APP_STATUS ");
    	
    	// 执行SQL语句
        Session session = sf.getCurrentSession();
        Query query = session.createQuery(queryString.toString());
        
        List<Object[]> list = query.list();
//        session.close();
        
        // 解析SQL语句
        MP2008 mp2008 = new MP2008();
        List<MP2008> retList = new ArrayList<MP2008>();
        String employeeName = "";
        for(int i=0,j=list.size(); i<j; i++){
            mp2008 = new MP2008();
            Object[] obj = list.get(i);
            
            mp2008.setMP2008_PAYROLL_NUM(obj[0] == null ? "" : obj[0].toString());
            mp2008.setMP2008_EMPLOYEE_NUM(obj[1] == null ? "" : obj[1].toString());
            //mp2008.setMP2008_EMPLOYEE_NAME(obj[2] == null ? "" : obj[2].toString());
            mp2008.setMP2008_DEPARTMENT_NAME(obj[3] == null ? "" : obj[3].toString());
            mp2008.setMP2008_APP_STATUS_NAME(obj[4] == null ? "" : obj[4].toString());
            mp2008.setMP2008_HOURS_TOTAL(Integer.parseInt(obj[5] == null ? "0" : obj[5].toString()));
            mp2008.setMP2008_GENDER(obj[6] == null ? "" : obj[6].toString());
            mp2008.setMP2008_RATING(obj[7] == null ? "" : obj[7].toString());
            
            employeeName = (obj[2] == null ? "" : obj[2].toString());
            mp2008.setMP2008_EMPLOYEE_NAME(mp2008.getMP2008_GENDER().equals("1") ? "Mr " + employeeName : "Ms " + employeeName );
            
            retList.add(mp2008);
        }
        
        return  retList;
    }
    
}

