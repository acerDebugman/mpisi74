package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.CHECKINOUT;
import entity.MP1001;
import entity.USERINFO;

public class CHECKINOUTDAO extends HibernateDaoSupport implements ICHECKINOUTDAO{
    // 保存数据
    public void save(CHECKINOUT checkinout) {
        if(checkinout != null){
            getHibernateTemplate().save(checkinout);
        }
    }
    // 删除数据
    public void delete(CHECKINOUT checkinout) {
        if(checkinout != null){
            getHibernateTemplate().delete(checkinout);
        }
    }
    // 根据KEY检索数据
    public CHECKINOUT findById(String key) {
        	return (CHECKINOUT)getHibernateTemplate().get("entity.CHECKINOUT", key);
    }
    // 取得所有有效数据
    @SuppressWarnings("unchecked")
    public List<CHECKINOUT> findAll() {
        return getHibernateTemplate().find(" from CHECKINOUT where 1=1  ");
    }
    // 更新数据
    public void update(CHECKINOUT checkinout) {
        if(checkinout != null){
            getHibernateTemplate().update(checkinout);
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
    public List<CHECKINOUT> findByProperty(String name, String value) {
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();
        queryString.append(" and mp78." + name + "='" + value + "' ");

        List<CHECKINOUT> retList = executeSqlStatement(queryString,-1,-1);

        return retList;
    }
    // 分页方法
    public List<CHECKINOUT> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();

        // 此处追加检索条件
        if(propertyMap.containsKey("PARAM1") && propertyMap.get("PARAM1") != null && !propertyMap.get("PARAM1").equals("")){
            queryString.append(" and checkinout.CHECKTIME >= '" + propertyMap.get("PARAM1") + "' ");
        }
        if(propertyMap.containsKey("PARAM2") && propertyMap.get("PARAM2") != null && !propertyMap.get("PARAM2").equals("")){
            queryString.append(" and checkinout.CHECKTIME <= '" + propertyMap.get("PARAM2") + "' ");
        }
        if(propertyMap.containsKey("PARAM3") && propertyMap.get("PARAM3") != null && !propertyMap.get("PARAM3").equals("")){
            queryString.append(" and userinfo.SSN = '" + propertyMap.get("PARAM3") + "' ");
        }
        if(propertyMap.containsKey("PARAM4") && propertyMap.get("PARAM4") != null && !propertyMap.get("PARAM4").equals("")){
            queryString.append(" and userinfo.SSN like '%T%' ");
        }
        
        queryString.append(" order by checkinout.USERID,checkinout.CHECKTIME ");

        List<CHECKINOUT> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);

        return retList;
    }
    // 分页方法，取得数据总条数
    public int getRowCountByPage(Map<String, String> propertyMap) {
        List<CHECKINOUT> retList = findByPropertyByPage(propertyMap,-1,-1);
        return retList.size();
    }
    // 自定义查询条件及排序方式
    public List<CHECKINOUT> findDataBySelfDefined(String search,String order, int pageNum, int pageCount){
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();

        // 此处追加检索条件
        queryString.append(search);
        // 排序方式
        queryString.append(order);

        List<CHECKINOUT> retList = executeSqlStatement(queryString, pageNum, pageCount);
        return retList;
    }
    // 构建SQL语句
    private StringBuffer createSqlStatement(){
        StringBuffer queryString = new StringBuffer();

        queryString.append(" select ");
        queryString.append(" checkinout.USERID, ");
        queryString.append(" checkinout.CHECKTIME, ");
        queryString.append(" checkinout.CHECKTYPE, ");
        queryString.append(" checkinout.VERIFYCODE, ");
        queryString.append(" checkinout.SENSORID, ");
        queryString.append(" checkinout.MEMOINFO, ");
        queryString.append(" checkinout.WORKCODE, ");
        queryString.append(" checkinout.SN, ");
        queryString.append(" checkinout.USEREXTFMT, ");
        queryString.append(" userinfo.SSN ");
        queryString.append(" from CHECKINOUT checkinout, USERINFO userinfo ");
        queryString.append(" where 1=1 ");
        queryString.append(" and checkinout.USERID = userinfo.USERID ");

        return queryString;
    }
    // 检索数据
    @SuppressWarnings("unchecked")
    private List<CHECKINOUT> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        Query query = session.createQuery(queryString.toString());
        if( PAGE_NUM > 0 && PAGE_COUNT > 0){
            query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
            query.setMaxResults(PAGE_COUNT);
        }
        List<Object[]> list = query.list();
        session.close();

        List<CHECKINOUT> retList = getDataList(list);

        return retList;
    }
    // 解析数据
    private List<CHECKINOUT> getDataList(List<Object[]> list){
        CHECKINOUT checkinout = new CHECKINOUT();
        List<CHECKINOUT> retList = new ArrayList<CHECKINOUT>();

        for(int i=0,j=list.size(); i<j; i++){
            checkinout = new CHECKINOUT();
            Object[] obj = list.get(i);

            checkinout.setUSERID(obj[0] == null ? Integer.parseInt("0") : Integer.parseInt(obj[0].toString()));
            checkinout.setCHECKTIME(obj[1] == null ? "" : obj[1].toString());
            checkinout.setCHECKTYPE(obj[2] == null ? "" : obj[2].toString());
            checkinout.setVERIFYCODE(obj[3] == null ? Integer.parseInt("0") : Integer.parseInt(obj[3].toString()));
            checkinout.setSENSORID(obj[4] == null ? "" : obj[4].toString());
            checkinout.setMEMOINFO(obj[5] == null ? "" : obj[5].toString());
            checkinout.setWORKCODE(obj[6] == null ? Integer.parseInt("0") : Integer.parseInt(obj[6].toString()));
            checkinout.setSN(obj[7] == null ? "" : obj[7].toString());
            checkinout.setWORKCODE(obj[8] == null ? Integer.parseInt("0") : Integer.parseInt(obj[8].toString()));
            checkinout.setEMPLOYEE_NUM(obj[9] == null ? "" : obj[9].toString());
            
            retList.add(checkinout);
        }
        return retList;
    }
    
    
	@SuppressWarnings("unchecked")
	public int findAll(String employeeNum) {
		int usrId = 0;
        StringBuffer queryString = new StringBuffer();
        queryString.append(" select ");
        queryString.append(" userinfo.USERID, ");
        queryString.append(" userinfo.SSN ");
        queryString.append(" from USERINFO userinfo ");
        queryString.append(" where 1=1 ");
        queryString.append(" and userinfo.SSN = '" + employeeNum + "'");
		
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        Query query = session.createQuery(queryString.toString());
        List<Object[]> list = query.list();
        session.close();
        
        if(list != null && list.size() >0){
        	usrId = Integer.parseInt(list.get(0)[0].toString());
        }
        
        return usrId;
	}
	public void save(USERINFO userinfo) {
		if (userinfo != null) {
			getHibernateTemplate().save(userinfo);
		}
	}
    
    
    //
    public List<CHECKINOUT> fetchAllDayRecords(Date date){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.set(Calendar.HOUR_OF_DAY, 0);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.SECOND, 0);
    	Date startTime = cal.getTime();
    	cal.set(Calendar.HOUR_OF_DAY, 23);
    	cal.set(Calendar.MINUTE, 59);
    	cal.set(Calendar.SECOND, 59);
    	Date endTime = cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	
    	Session session = getHibernateTemplate().getSessionFactory().openSession();
    	session.beginTransaction();
    	
        Query query = session.createQuery("select c from CHECKINOUT c where c.CHECKTIME>=:startDateTime and c.CHECKTIME<=:endDateTime")
        				.setString("startDateTime", sdf.format(startTime))
        				.setString("endDateTime", sdf.format(endTime));
        List<CHECKINOUT> list = query.list();
        
        session.getTransaction().commit();
        session.close();
        
        return list;
    }
    
    public List<CHECKINOUT> fetchEmployeeDailyRecords(Date date, MP1001 emp){
    	Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	List<CHECKINOUT> list = session.createQuery("select c from CHECKINOUT c, USERINFO u where " +
    			" u.SSN=:empCode and c.USERID=u.USERID and convert(varchar(10), c.CHECKTIME, 120)=:theDate " +
    			" order by c.CHECKTIME ASC")
    			.setString("empCode", emp.getMP1001_EMPLOYEE_NUM())
    			.setString("theDate", sdf.format(date))
    			.list();
    	
    	return list;
    }
    
    public CHECKINOUT fetchLatestDayRecord(Date date){
    	CHECKINOUT rcd = null;
    	Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    	List<CHECKINOUT> list = session.createQuery("select c from CHECKINOUT c where convert(varchar(10), c.CHECKTIME, 120)=:theDate " +
    	rcd = (CHECKINOUT)session.createQuery("select c from CHECKINOUT c where convert(varchar(10), c.CHECKTIME, 120)=:theDate " +
    			" order by c.CHECKTIME DESC")
    			.setString("theDate", sdf.format(date))
    			.setMaxResults(1)
    			.uniqueResult();
    	
    	return rcd;
    }

    public long fetchTotalRecordsCounter(){
    	Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
    	long counter = (Long)session.createQuery("select count(*) from CHECKINOUT").uniqueResult();
    	
    	return counter;
    }
}

