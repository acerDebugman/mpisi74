package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.MP1001;
import entity.MP1005;

public class MP1001DAO extends HibernateDaoSupport implements IMP1001DAO {
	
	private static final Log log = LogFactory.getLog(MP1001DAO.class);
	private Session session;
	private Transaction transaction;

    // 保存数据
	public void save(MP1001 mp1001) {
		if (mp1001 != null) {
			getHibernateTemplate().save(mp1001);
		}
	}
    // 删除数据
	public void delete(MP1001 mp1001) {
		getHibernateTemplate().delete(mp1001);
	}
    // 根据关键字查询数据
	public MP1001 findById(String employeeNum) {
		return (MP1001) getHibernateTemplate().get("entity.MP1001", employeeNum);
	}
    // 查询所有数据
	@SuppressWarnings("unchecked")
	public List<MP1001> findAll() {
		return getHibernateTemplate().find("from MP1001 where MP1001_STATUS = '1' or MP1001_STATUS = '2' ");
	}
	
	@SuppressWarnings("unchecked")
	public List<MP1001> findbyDepartmentId(String departmentID) {
		return getHibernateTemplate().find("from MP1001 where (MP1001_STATUS = '1' or MP1001_STATUS = '2') and MP1001_DEPARTMENT_ID = '" + departmentID + "' " );
	}
	
	@SuppressWarnings("unchecked")
	public List<MP1001> findAllResign(String _start, String _finish){
		StringBuffer sb = new StringBuffer();
		sb.append(" from MP1001 ");
		sb.append(" where 1=1 ");
		sb.append(" and MP1001_STATUS = '3' ");
		
		if(!_start.equals("")){
			sb.append(" and MP1001_RESIGN_DATE >= '" + _start + "'");
		}
		if(!_finish.equals("")){
			sb.append(" and MP1001_RESIGN_DATE <= '" + _finish + " 23:59:59" + "'");
		}
		
		sb.append(" order by MP1001_RESIGN_DATE desc ");
		
		//return getHibernateTemplate().find("from MP1001 where MP1001_STATUS = '3' order by MP1001_RESIGN_DATE desc ");
		
		return getHibernateTemplate().find(sb.toString());
	}
	
	// 查询所有email
	@SuppressWarnings("unchecked")
	public List<MP1001> findAllEmail(){
		return getHibernateTemplate().find("from MP1001 where 1=1 and MP1001_STATUS = '2' and MP1001_COMPANY_EMAIL is not NULL and MP1001_COMPANY_EMAIL <> '' ");
	}
    // 更新数据
	public void update(MP1001 mp1001) {
		getHibernateTemplate().update(mp1001);
	}
	// 更新数据
	public void updateEmployeeNum(String oldValue, String newValue, String curUser) throws Exception{
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		session.beginTransaction();
		
		try{
			//String queryString = " update MP1001 set MP1001_CHG_TIME = GETDATE(), MP1001_CHG_EMPLOYE= '" + curUser + "', MP1001_STATUS = '2', MP1001_EMPLOYEE_NUM = '" + newValue + "' where MP1001_EMPLOYEE_NUM = '" + oldValue + "'";
			String queryString = " update MP1001 set MP1001_CHG_TIME = GETDATE(), MP1001_CHG_EMPLOYE= '" + curUser + "', MP1001_STATUS = '2' where MP1001_EMPLOYEE_NUM = '" + oldValue + "'";
		    Query query = session.createQuery(queryString);
		    query.executeUpdate();
		    
/*		    queryString = " update MP1002 set MP1002_EMPLOYEE_NUM = '" + newValue + "' where MP1002_EMPLOYEE_NUM = '" + oldValue + "'";
		    query = session.createQuery(queryString);
		    query.executeUpdate();
		    
		    queryString = " update MP1003 set MP1003_EMPLOYEE_NUM = '" + newValue + "' where MP1003_EMPLOYEE_NUM = '" + oldValue + "'";
		    query = session.createQuery(queryString);
		    query.executeUpdate();
		    
		    queryString = " update MP1004 set MP1004_EMPLOYEE_NUM = '" + newValue + "' where MP1004_EMPLOYEE_NUM = '" + oldValue + "'";
		    query = session.createQuery(queryString);
		    query.executeUpdate();
		    
		    queryString = " update MP1005 set MP1005_EMPLOYEE_NUM = '" + newValue + "' where MP1005_EMPLOYEE_NUM = '" + oldValue + "'";
		    query = session.createQuery(queryString);
		    query.executeUpdate();
		    
		    queryString = " update MP1009 set MP1009_EMPLOYEE_NUM = '" + newValue + "' where MP1009_EMPLOYEE_NUM = '" + oldValue + "'";
		    query = session.createQuery(queryString);
		    query.executeUpdate();
		    
		    queryString = " update MP2001 set MP2001_EMPLOYEE_NUM = '" + newValue + "' where MP2001_EMPLOYEE_NUM = '" + oldValue + "'";
		    query = session.createQuery(queryString);
		    query.executeUpdate();
		    
		    queryString = " update MP2002 set MP2002_EMPLOYEE_NUM = '" + newValue + "' where MP2002_EMPLOYEE_NUM = '" + oldValue + "'";
		    query = session.createQuery(queryString);
		    query.executeUpdate();
		    
		    queryString = " update MP2003 set MP2003_EMPLOYEE_NUM = '" + newValue + "' where MP2003_EMPLOYEE_NUM = '" + oldValue + "'";
		    query = session.createQuery(queryString);
		    query.executeUpdate();
		    
		    queryString = " update MP2004 set MP2004_EMPLOYEE_NUM = '" + newValue + "' where MP2004_EMPLOYEE_NUM = '" + oldValue + "'";
		    query = session.createQuery(queryString);
		    query.executeUpdate();
		    
		    queryString = " update MP2005 set MP2005_APPLICANT = '" + newValue + "' where MP2005_APPLICANT = '" + oldValue + "'";
		    query = session.createQuery(queryString);
		    query.executeUpdate();
		    
		    queryString = " update MP0011 set MP0011_USR_ID = '" + newValue + "' where MP0011_USR_ID = '" + oldValue + "'";
		    query = session.createQuery(queryString);
		    query.executeUpdate();
		    
		    queryString = " update PR1001 set PR1001_APPLICATION_PERSON = '" + newValue + "' where PR1001_APPLICATION_PERSON = '" + oldValue + "'";
		    query = session.createQuery(queryString);
		    query.executeUpdate();
		    
		    queryString = " update PR1007 set PR1007_APPLICANT = '" + newValue + "' where PR1007_APPLICANT = '" + oldValue + "'";
		    query = session.createQuery(queryString);
		    query.executeUpdate();
		    
		    queryString = " update AC0009 set AC0009_EMPLOYEE_NUM = '" + newValue + "' where AC0009_EMPLOYEE_NUM = '" + oldValue + "'";
		    query = session.createQuery(queryString);
		    query.executeUpdate();*/
		    
		    session.getTransaction().commit();	    
		    session.close();
		}catch(Exception ex){
			log.info(ex.getMessage());
			session.getTransaction().rollback();
			throw ex;
		}
	}
	
    // 根据检索字段查询数据
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<MP1001> findByProperty(Map<String, String> propertyMap,boolean pageFlag) {
		try{
			final StringBuffer queryString = new StringBuffer();
			queryString.append(" from MP1001 as mp1 ");
			queryString.append(" where 1=1 ");
			// 判断“员工编码”
			if(propertyMap.containsKey("NUM") && propertyMap.get("NUM") != null && !propertyMap.get("NUM").equals("")){
				queryString.append(" and mp1.MP1001_EMPLOYEE_NUM like '%" + propertyMap.get("NUM") + "%'");
			}
			// 判断“员工名称”
			if(propertyMap.containsKey("NAME") && propertyMap.get("NAME") != null && !propertyMap.get("NAME").equals("")){
				queryString.append(" and ( mp1.MP1001_SURNAME like '%" + propertyMap.get("NAME") + "%'");
				queryString.append(" or MP1001_FIRSTNAME like '%" + propertyMap.get("NAME") + "%'");
				queryString.append(" or MP1001_PREFERED_NAME like '%" + propertyMap.get("NAME") + "%')");
			}
			// 判断“部门”
			if(propertyMap.containsKey("DEPARTMENT") && propertyMap.get("DEPARTMENT") != null && !propertyMap.get("DEPARTMENT").equals("")){
				queryString.append(" and mp1.MP1001_DEPARTMENT_ID = '" + propertyMap.get("DEPARTMENT") + "'");
			}
			// 判断“类型”
			if(propertyMap.containsKey("TYPE") && propertyMap.get("TYPE") != null && !propertyMap.get("TYPE").equals("") && !propertyMap.get("TYPE").equals("0")){
				queryString.append(" and mp1.MP1001_VISA_TYPE = '" + propertyMap.get("TYPE") + "'");
			}
			// 判断临时员工(用于查询已过试用期的员工)
			if(propertyMap.containsKey("EMP_STATUS") && propertyMap.get("EMP_STATUS") != null && !propertyMap.get("EMP_STATUS").equals("")){
				queryString.append(" and mp1.MP1001_STATUS = '1'");
				queryString.append(" and DATEDIFF(day, mp1.MP1001_ENTRY_DATE, GETDATE()) >= 90");
			}
			if(propertyMap.containsKey("STATUS") && propertyMap.get("STATUS") != null && !propertyMap.get("STATUS").equals("") && !propertyMap.get("STATUS").equals("0")){
				if(propertyMap.get("STATUS").equals("99")){
					queryString.append(" and mp1.MP1001_STATUS <> '3'");
				}else{
					queryString.append(" and mp1.MP1001_STATUS = '" + propertyMap.get("STATUS") + "'");
				}
			}
			

			// Add by Tim 2011/12/29--Start
			// 判断“性别”
			if(propertyMap.containsKey("gender") && propertyMap.get("gender") != null && !propertyMap.get("gender").equals("") && !propertyMap.get("gender").equals("0")){
				queryString.append(" and mp1.MP1001_GENDER = '" + propertyMap.get("gender") + "'");
			}
			// 判断“国籍”
			if(propertyMap.containsKey("nationality") && propertyMap.get("nationality") != null && !propertyMap.get("nationality").equals("")){
				queryString.append(" and mp1.MP1001_NATIONALITY = '" + propertyMap.get("nationality") + "'");
			}
			// 判断“宗教”
			if(propertyMap.containsKey("religion") && propertyMap.get("religion") != null && !propertyMap.get("religion").equals("")){
				queryString.append(" and mp1.MP1001_RELIGION = '" + propertyMap.get("religion") + "'");
			}
			// 判断“民族”
			if(propertyMap.containsKey("race") && propertyMap.get("race") != null && !propertyMap.get("race").equals("")){
				queryString.append(" and mp1.MP1001_RACE = '" + propertyMap.get("race") + "'");
			}
			// 判断“护照号码/ID”
			if(propertyMap.containsKey("passportNum") && propertyMap.get("passportNum") != null && !propertyMap.get("passportNum").equals("")){
				queryString.append(" and mp1.MP1001_EMPLOYEE_ID = '" + propertyMap.get("passportNum") + "'");
			}
			// 判断“学历”
			if(propertyMap.containsKey("qualification") && propertyMap.get("qualification") != null && !propertyMap.get("qualification").equals("") && !propertyMap.get("qualification").equals("0")){
				queryString.append(" and mp1.MP1001_DEGREE_LEVEL = '" + propertyMap.get("qualification") + "'");
			}
			// 判断“入职日期”
			if(propertyMap.containsKey("startingDate") && propertyMap.get("startingDate") != null && !propertyMap.get("startingDate").equals("")){
				queryString.append(" and mp1.MP1001_ENTRY_DATE = '" + propertyMap.get("startingDate") + "'");
			}
			// 判断“生日”
			if(propertyMap.containsKey("birth") && propertyMap.get("birth") != null && !propertyMap.get("birth").equals("") && !propertyMap.get("birth").equals("0")){
				if(propertyMap.containsKey("birthday") && propertyMap.get("birthday") != null && !propertyMap.get("birthday").equals("")){
					String _birth = propertyMap.get("birth");
					
					if(_birth.equals("1")){ // Year
						queryString.append(" and substring(convert(varchar,mp1.MP1001_BIRTHDAY,120),1,4) = '" + propertyMap.get("birthday") + "'");
					}else if(_birth.equals("2")){ // Month
						String _month = String.format("%02d", Integer.parseInt(propertyMap.get("birthday")));
						queryString.append(" and substring(convert(varchar,mp1.MP1001_BIRTHDAY,120),6,2) = '" + _month + "'");
					}else if(_birth.equals("3")){ // Day
						String _day = String.format("%02d", Integer.parseInt(propertyMap.get("birthday")));
						queryString.append(" and substring(convert(varchar,mp1.MP1001_BIRTHDAY,120),9,2) = '" + _day + "'");
					}
				}
			}
			
			// Add by Tim 2011/12/29--End
			
			// Add by Tim 2012/01/18--Start
			if(propertyMap.containsKey("JOB_TITLE") && propertyMap.get("JOB_TITLE") != null && !propertyMap.get("JOB_TITLE").equals("")){
				queryString.append(" and mp1.MP1001_POSITION = '" + propertyMap.get("JOB_TITLE") + "'");
			}
			// Add by Tim 2012/01/18--End
			
			if(pageFlag){
				final int pageNum = Integer.parseInt(propertyMap.get("PAGE_NUM"));
				final int pageCount = Integer.parseInt(propertyMap.get("PAGE_COUNT"));
				
				return getHibernateTemplate().executeFind(new HibernateCallback(){
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createQuery(queryString.toString());
						query.setFirstResult((pageNum -1)*pageCount);
						query.setMaxResults(pageCount);
						
						List<MP1001> list = query.list();
						
						return list;
					}});
			}
			else{
				return getHibernateTemplate().find(queryString.toString());
			}
			//return getHibernateTemplate().find(queryString.toString());
		}catch(RuntimeException ex){
			log.info(ex.getMessage());
			throw ex;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<MP1001> getDepartmentHeadcount(){
		StringBuffer queryString = new StringBuffer();
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		
		queryString.append(" select ");
		queryString.append(" mp02.MP0002_DEPARTMENT_NAME as MP1001_DEPARTMENT_NAME, ");
		queryString.append(" COUNT(*) as MP1001_HEADCOUNT_DEPARTMENT ");
		queryString.append(" from MP1001 mp11,MP0002 mp02 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and mp11.MP1001_STATUS in ('1','2') ");
		queryString.append(" and mp11.MP1001_DEPARTMENT_ID = mp02.MP0002_SEQ ");
		queryString.append(" group by mp11.MP1001_DEPARTMENT_ID,mp02.MP0002_DEPARTMENT_NAME ");
		
		Query query = session.createQuery(queryString.toString());
		List<Object[]> list = query.list();
		session.close();
		
		MP1001 mp11 = new MP1001();
		List<MP1001> retList = new ArrayList<MP1001>();
		for(int i=0,j=list.size();i<j;i++){
			mp11 = new MP1001();
			
			Object[] obj = list.get(i);
			
			// Department
			if(null == obj[0]){
				mp11.setMP1001_DEPARTMENT_NAME("");
			}else{
				mp11.setMP1001_DEPARTMENT_NAME(obj[0].toString());
			}
			// Count
			if(null == obj[1]){
				mp11.setMP1001_HEADCOUNT_DEPARTMENT("0");
			}else{
				mp11.setMP1001_HEADCOUNT_DEPARTMENT(obj[1].toString());
			}
			
			retList.add(mp11);
		}
		return retList;
	}
	
	// 保存数据(事务)
	public void saveTransaction(MP1001 mp1001,MP1005 mp1005,String educationList,String workList){
		//session = HibernateSessionFactory.getSession();
		try{
			transaction = session.beginTransaction();
			//session.save(“”);
			transaction.commit();
		}catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}
		//HibernateSessionFactory.closeSession();
	}

	// 自定义查询条件及排序方式
	public List<MP1001> findData(String search,String order, int pageNum, int pageCount){
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		queryString.append(search);
		// 排序方式
		queryString.append(order);
		
		List<MP1001> retList = executeSqlStatement(queryString, pageNum, pageCount);
		return retList;
	}
	
	// 构建SQL语句
	private StringBuffer createSqlStatement(){
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" select ");
		queryString.append(" mp11.MP1001_EMPLOYEE_NUM, ");
		queryString.append(" mp11.MP1001_EMPLOYEE_ID, ");
		queryString.append(" mp11.MP1001_SURNAME, ");
		queryString.append(" mp11.MP1001_FIRSTNAME, ");
		queryString.append(" mp11.MP1001_PREFERED_NAME, ");
		queryString.append(" mp11.MP1001_VISA_TYPE, ");
		queryString.append(" mp11.MP1001_DEPARTMENT_ID, ");
		queryString.append(" mp11.MP1001_POSITION, ");
		queryString.append(" mp11.MP1001_GENDER, ");
		queryString.append(" mp11.MP1001_RELIGION, ");
		queryString.append(" mp11.MP1001_NATIONALITY, ");
		queryString.append(" mp11.MP1001_BIRTHDAY, ");
		queryString.append(" mp11.MP1001_MARRIAGE_STATUS, ");
		queryString.append(" mp11.MP1001_RACE, ");
		queryString.append(" mp11.MP1001_MOBILE_PHONE, ");
		queryString.append(" mp11.MP1001_TELEPHONE, ");
		queryString.append(" mp11.MP1001_OTHER_CONTACT, ");
		queryString.append(" mp11.MP1001_EMAIL, ");
		queryString.append(" mp11.MP1001_COMPANY_EMAIL, ");
		queryString.append(" mp11.MP1001_DEGREE_LEVEL, ");
		queryString.append(" mp11.MP1001_ENTRY_DATE, ");
		queryString.append(" mp11.MP1001_STATUS, ");
		queryString.append(" mp11.MP1001_ANNUAL_STATUS, ");
		queryString.append(" mp11.MP1001_RESIGN_DATE, ");
		queryString.append(" mp11.MP1001_RESIGN_REASON, ");
		queryString.append(" mp11.MP1001_RESIGN_TYPE, ");
		queryString.append(" mp11.MP1001_PICTURE, ");
		queryString.append(" mp11.MP1001_PICTURE_NAME, ");
		queryString.append(" mp11.MP1001_PHYSICAL_ADDRESS, ");
		queryString.append(" mp11.MP1001_PASSWORD, ");
		queryString.append(" mp11.MP1001_PASSWORD_DATE, ");
		queryString.append(" mp11.MP1001_GROUP, ");
		queryString.append(" mp11.MP1001_RECTIFY_TIMES, ");
		queryString.append(" mp11.MP1001_FREE_MONEY, ");
		queryString.append(" mp11.MP1001_CHG_TIME, ");
		queryString.append(" mp11.MP1001_CHG_EMPLOYE, ");
		queryString.append(" mp11.MP1001_CREATE_USER, ");
		queryString.append(" mp11.MP1001_CREATE_DATE, ");
		queryString.append(" mp11.MP1001_EDIT_USER, ");
		queryString.append(" mp11.MP1001_EDIT_DATE, ");
		queryString.append(" mp02.MP0002_DEPARTMENT_NAME, ");
		queryString.append(" mp09.MP0009_POSITION_NAME_E, ");
		queryString.append(" mp11.MP1001_APPRASIER, ");
		queryString.append(" mp11.MP1001_EFFECTIVE_DATE_YEAR, ");
		queryString.append(" mp11.MP1001_EFFECTIVE_DATE_MONTH, ");
		queryString.append(" mp11.MP1001_PAYROLL_NUM ");
		
		queryString.append(" from MP1001 mp11, MP0002 mp02, MP0009 mp09 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and mp11.MP1001_DEPARTMENT_ID = mp02.MP0002_SEQ ");
		queryString.append(" and mp11.MP1001_POSITION = mp09.MP0009_SEQ ");
		
		return queryString;
	}
	// 检索数据
	@SuppressWarnings("unchecked")
	private List<MP1001> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
		Session session = getHibernateTemplate().getSessionFactory().openSession();		
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		List<Object[]> list = query.list();
		session.close();
		
		List<MP1001> retList = getDataList(list);
		
		return retList;
	}
	// 解析数据
	private List<MP1001> getDataList(List<Object[]> list){
		MP1001 mp11 = new MP1001();
		List<MP1001> retList = new ArrayList<MP1001>();
	
		for(int i=0,j=list.size(); i<j; i++){
			mp11 = new MP1001();
			Object[] obj = list.get(i);
			
			mp11.setMP1001_EMPLOYEE_NUM(obj[0] == null ? "" : obj[0].toString());
			mp11.setMP1001_EMPLOYEE_ID(obj[1] == null ? "" : obj[1].toString());
			mp11.setMP1001_SURNAME(obj[2] == null ? "" : obj[2].toString());
			mp11.setMP1001_FIRSTNAME(obj[3] == null ? "" : obj[3].toString());
			mp11.setMP1001_PREFERED_NAME(obj[4] == null ? "" : obj[4].toString());
			mp11.setMP1001_VISA_TYPE(obj[5] == null ? "" : obj[5].toString());
			mp11.setMP1001_DEPARTMENT_ID(obj[6] == null ? "" : obj[6].toString());
			mp11.setMP1001_POSITION(obj[7] == null ? "" : obj[7].toString());
			mp11.setMP1001_GENDER(obj[8] == null ? "" : obj[8].toString());
			mp11.setMP1001_RELIGION(obj[9] == null ? "" : obj[9].toString());
			mp11.setMP1001_NATIONALITY(obj[10] == null ? "" : obj[10].toString());
			mp11.setMP1001_BIRTHDAY(obj[11] == null ? "" : obj[11].toString());
			mp11.setMP1001_MARRIAGE_STATUS(obj[12] == null ? "" : obj[12].toString());
			mp11.setMP1001_RACE(obj[13] == null ? "" : obj[13].toString());
			mp11.setMP1001_MOBILE_PHONE(obj[14] == null ? "" : obj[14].toString());
			mp11.setMP1001_TELEPHONE(obj[15] == null ? "" : obj[15].toString());
			mp11.setMP1001_OTHER_CONTACT(obj[16] == null ? "" : obj[16].toString());
			mp11.setMP1001_EMAIL(obj[17] == null ? "" : obj[17].toString());
			mp11.setMP1001_COMPANY_EMAIL(obj[18] == null ? "" : obj[18].toString());
			mp11.setMP1001_DEGREE_LEVEL(obj[19] == null ? "" : obj[19].toString());
			mp11.setMP1001_ENTRY_DATE(obj[20] == null ? "" : obj[20].toString());
			mp11.setMP1001_STATUS(obj[21] == null ? "" : obj[21].toString());
			mp11.setMP1001_ANNUAL_STATUS(obj[22] == null ? "" : obj[22].toString());
			mp11.setMP1001_RESIGN_DATE(obj[23] == null ? "" : obj[23].toString());
			mp11.setMP1001_RESIGN_REASON(obj[24] == null ? "" : obj[24].toString());
			mp11.setMP1001_RESIGN_TYPE(obj[25] == null ? "" : obj[25].toString());
			mp11.setMP1001_PICTURE(obj[26] == null ? "" : obj[26].toString());
			mp11.setMP1001_PICTURE_NAME(obj[27] == null ? "" : obj[27].toString());
			mp11.setMP1001_PHYSICAL_ADDRESS(obj[28] == null ? "" : obj[28].toString());
			mp11.setMP1001_PASSWORD(obj[29] == null ? "" : obj[29].toString());
			mp11.setMP1001_PASSWORD_DATE(obj[30] == null ? "" : obj[30].toString());
			mp11.setMP1001_GROUP(obj[31] == null ? "" : obj[31].toString());
			mp11.setMP1001_RECTIFY_TIMES(obj[32] == null ? "" : obj[32].toString());
			mp11.setMP1001_FREE_MONEY(obj[33] == null ? "0" : obj[33].toString());
			mp11.setMP1001_CHG_TIME(obj[34] == null ? "" : obj[34].toString());
			mp11.setMP1001_CHG_EMPLOYE(obj[35] == null ? "" : obj[35].toString());
			mp11.setMP1001_CREATE_USER(obj[36] == null ? "" : obj[36].toString());
			mp11.setMP1001_CREATE_DATE(obj[37] == null ? "" : obj[37].toString());
			mp11.setMP1001_EDIT_USER(obj[38] == null ? "" : obj[38].toString());
			mp11.setMP1001_EDIT_DATE(obj[39] == null ? "" : obj[39].toString());
			mp11.setMP1001_DEPARTMENT_NAME(obj[40] == null ? "" : obj[40].toString());
			mp11.setMP1001_POSITION_NAME(obj[41] == null ? "" : obj[41].toString());
			mp11.setMP1001_APPRASIER(obj[42] == null ? "" : obj[42].toString());
			mp11.setMP1001_EFFECTIVE_DATE_YEAR(obj[43] == null ? "" : obj[43].toString());
			mp11.setMP1001_EFFECTIVE_DATE_MONTH(obj[44] == null ? "" : obj[44].toString());
			mp11.setMP1001_PAYROLL_NUM(obj[45] == null ? "" : obj[45].toString());
			
			retList.add(mp11);
		}
		log.info("Count:" + retList.size());
		return retList;
	}
	
}