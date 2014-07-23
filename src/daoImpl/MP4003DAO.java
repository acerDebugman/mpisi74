package daoImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import common.Constant;

import dao.IMP4003DAO;
import entity.MP4003;

public class MP4003DAO   implements IMP4003DAO{
	private static final Log log = LogFactory.getLog(MP4003DAO.class);
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	// 保存数据
	public void save(MP4003 mp4003) {
		if(mp4003 != null){
			sf.getCurrentSession().save(mp4003);
		}
	}
	// 删除数据
	public void delete(MP4003 mp4003) {
		if(mp4003 != null){
			sf.getCurrentSession().delete(mp4003);
		}
	}
	// 根据KEY检索数据
	public MP4003 findById(int key) {
		//return (PR1007)sf.getCurrentSession().get("entity.PR1007", key);
		StringBuffer queryString = new StringBuffer();

		queryString = createSqlStatement();
		queryString.append(" and mp43.MP4003_SEQ=" + key + " ");
		
		MP4003 mp4003 = new MP4003();

		List<MP4003> retList = executeSqlStatement(queryString,-1,-1);
		if(retList != null && retList.size() > 0){
			mp4003 = retList.get(0);
		}else{
			mp4003 = null;
		}
		
		return mp4003;
	}
	// 取得所有有效数据
	@SuppressWarnings("unchecked")
	public List<MP4003> findAll() {
		return sf.getCurrentSession()
				 .createQuery(" from MP4003 mp43 where 1=1 order by mp43.MP4003_DEPARTMENT_ID,mp43.MP4003_ACCOUNTING_NUM ")
				 .list();
	}
	// 更新数据
	public void update(MP4003 mp4003) {
		if(mp4003 != null){
			sf.getCurrentSession().update(mp4003);
		}
	}
	public void update2(String departmentID, String year, String month,String status){
		Session session = sf.getCurrentSession();
		
		StringBuffer queryString = new StringBuffer();
		queryString.append(" update MP4003 set MP4003_STATUS = '" + status + "' ");
		queryString.append(" where 1=1 ");
		queryString.append(" and MP4003_DEPARTMENT_ID = '" + departmentID + "' ");
		queryString.append(" and MP4003_YEAR = '" + year + "' ");
		queryString.append(" and MP4003_MONTH = '" + month + "' ");
		
	    Query query = session.createQuery(queryString.toString());
	    query.executeUpdate();
	    
//	    session.close();
	}
	// 动态根据传入的参数，检索数据
	public List<MP4003> findByProperty(String name, String value) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		queryString.append(" and mp43." + name + "='" + value + "' ");
		queryString.append(" order by mp43.MP4003_DEPARTMENT_ID,mp41.MP4001_TYPE,mp43.MP4003_ACCOUNTING_NUM ");
		
		List<MP4003> retList = executeSqlStatement(queryString,-1,-1);
		
		return retList;
	}
	// 分页方法
	public List<MP4003> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		if(propertyMap.containsKey("DEPARTMENT_ID") && propertyMap.get("DEPARTMENT_ID") != null && !propertyMap.get("DEPARTMENT_ID").equals("")){
			queryString.append(" and mp43.MP4003_DEPARTMENT_ID = '" + propertyMap.get("DEPARTMENT_ID")  + "' ");
		}
		if(propertyMap.containsKey("PARENT_TYPE") && propertyMap.get("PARENT_TYPE") != null && !propertyMap.get("PARENT_TYPE").equals("")){
			queryString.append(" and mp42.MP4002_NUM = '" + propertyMap.get("PARENT_TYPE")  + "' ");
		}
		if(propertyMap.containsKey("YEAR") && propertyMap.get("YEAR") != null && !propertyMap.get("YEAR").equals("")){
			queryString.append(" and mp43.MP4003_YEAR = '" + propertyMap.get("YEAR")  + "' ");
		}
		if(propertyMap.containsKey("MONTH") && propertyMap.get("MONTH") != null && !propertyMap.get("MONTH").equals("")){
			queryString.append(" and mp43.MP4003_MONTH = '" + propertyMap.get("MONTH")  + "' ");
		}
		if(propertyMap.containsKey("MAIN_CLASS") && propertyMap.get("MAIN_CLASS") != null && !propertyMap.get("MAIN_CLASS").equals("")){
			queryString.append(" and mp41.MP4001_TYPE = '" + propertyMap.get("MAIN_CLASS")  + "' ");
		}
		if(propertyMap.containsKey("ACCOUNT_NUM") && propertyMap.get("ACCOUNT_NUM") != null && !propertyMap.get("ACCOUNT_NUM").equals("")){
			queryString.append(" and mp43.MP4003_ACCOUNTING_NUM = '" + propertyMap.get("ACCOUNT_NUM")  + "' ");
		}
		
		queryString.append(" order by mp43.MP4003_DEPARTMENT_ID,mp41.MP4001_TYPE,mp43.MP4003_ACCOUNTING_NUM ");
		
		List<MP4003> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);
		
		return retList;
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		List<MP4003> retList = findByPropertyByPage(propertyMap,-1,-1);
		return retList.size();
	}
	
	
	// 按部门、年、月分组统计预算
	@SuppressWarnings("unchecked")
	public List<MP4003> getBudgetInfoListByDepYear(String mainClass,String departmentId, String year, String month, String month1,boolean monthFlag){
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" select  ");
		queryString.append(" mp43.MP4003_DEPARTMENT_ID,  ");
		queryString.append(" mp02.MP0002_DEPARTMENT_NAME,  ");
		queryString.append(" mp43.MP4003_YEAR,  ");
		if(monthFlag == true){
			queryString.append(" mp43.MP4003_MONTH,  ");
		}
		queryString.append(" sum(mp43.MP4003_AMOUNT) ,  ");
		queryString.append(" sum(mp43.MP4003_AMOUNT2) ,  ");
		queryString.append(" sum(mp43.MP4003_AMOUNT3)   ");
		queryString.append(" from MP4003 mp43,MP0002 mp02, MP4001 mp41 ");
		queryString.append(" where 1=1  ");
		queryString.append(" and mp43.MP4003_DEPARTMENT_ID = mp02.MP0002_SEQ  ");
		queryString.append(" and mp43.MP4003_ACCOUNTING_NUM = mp41.MP4001_NUM  ");
		if(departmentId != null && !departmentId.equals("")){
			queryString.append(" and mp43.MP4003_DEPARTMENT_ID = '" + departmentId + "'  ");
		}
		if(year != null && !year.equals("")){
			queryString.append(" and mp43.MP4003_YEAR = '" + year + "'  ");
		}

		
		if(month1 != null && !month1.equals("")){
			if(month != null && !month.equals("")){
				queryString.append(" and cast(mp43.MP4003_MONTH as int) >= '" + month + "'  ");
				queryString.append(" and cast(mp43.MP4003_MONTH as int) <= '" + month1 + "'  ");
			}
		}else{
			if(month != null && !month.equals("")){
				queryString.append(" and mp43.MP4003_MONTH = '" + month + "'  ");
			}
		}
		if(!mainClass.equals("")){
			queryString.append(" and mp41.MP4001_TYPE = '" + mainClass  + "' ");
		}

		if(monthFlag == true){
			queryString.append(" group by mp43.MP4003_DEPARTMENT_ID,mp02.MP0002_DEPARTMENT_NAME,mp43.MP4003_YEAR,mp43.MP4003_MONTH  ");
			queryString.append(" order by mp43.MP4003_DEPARTMENT_ID,mp43.MP4003_YEAR,mp43.MP4003_MONTH  ");
		}else{
			queryString.append(" group by mp43.MP4003_DEPARTMENT_ID,mp02.MP0002_DEPARTMENT_NAME,mp43.MP4003_YEAR  ");
			queryString.append(" order by mp02.MP0002_DEPARTMENT_NAME,mp43.MP4003_YEAR  ");
		}
		
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(queryString.toString());
		List<Object[]> list = query.list();
//		session.close();
		
		MP4003 mp4003 = new MP4003();
		List<MP4003> retList = new ArrayList<MP4003>();
		Map<String,String> monthNameMap = Constant.getMonthNameList();
		
		for(int i=0,j=list.size(); i<j; i++){
			mp4003 = new MP4003();
			Object[] obj = list.get(i);
			
			mp4003.setMP4003_DEPARTMENT_ID(obj[0] == null ? "" : obj[0].toString());
			mp4003.setMP4003_DEPARTMENT_NAME(obj[1] == null ? "" : obj[1].toString());
			mp4003.setMP4003_YEAR(obj[2] == null ? "" : obj[2].toString());
			if(monthFlag == true){
				mp4003.setMP4003_MONTH(obj[3] == null ? "" : obj[3].toString());
				mp4003.setMP4003_AMOUNT(obj[4] == null ? new BigDecimal("0") : new BigDecimal(obj[4].toString()));
				mp4003.setMP4003_AMOUNT2(obj[5] == null ? new BigDecimal("0") : new BigDecimal(obj[5].toString()));
				mp4003.setMP4003_AMOUNT3(obj[6] == null ? new BigDecimal("0") : new BigDecimal(obj[6].toString()));
			}else{
				mp4003.setMP4003_AMOUNT(obj[3] == null ? new BigDecimal("0") : new BigDecimal(obj[3].toString()));
				mp4003.setMP4003_AMOUNT2(obj[4] == null ? new BigDecimal("0") : new BigDecimal(obj[4].toString()));
				mp4003.setMP4003_AMOUNT3(obj[5] == null ? new BigDecimal("0") : new BigDecimal(obj[5].toString()));
			}
			
			BigDecimal _totalAmount = mp4003.getMP4003_AMOUNT().add(mp4003.getMP4003_AMOUNT3());
			BigDecimal _usedAmount = _totalAmount.subtract(mp4003.getMP4003_AMOUNT2());
			mp4003.setMP4003_AMOUNT4(_usedAmount);
			
			if(monthNameMap.containsKey(mp4003.getMP4003_MONTH())){
				mp4003.setMP4003_MONTH_NAME(monthNameMap.get(mp4003.getMP4003_MONTH()));
			}
			
/*			if(mp4003.getMP4003_TYPE().equals("1")){
				mp4003.setMP4003_TYPE_NAME("EXPENSE");
			}else if(mp4003.getMP4003_TYPE().equals("2")){
				mp4003.setMP4003_TYPE_NAME("INCOME");
			}else if(mp4003.getMP4003_TYPE().equals("3")){
				mp4003.setMP4003_TYPE_NAME("COSTS");
			}*/
			
			retList.add(mp4003);
		}
		
		return retList;
	}
	// 按科目、年、月分组统计预算
	@SuppressWarnings("unchecked")
	public List<MP4003> getBudgetInfoListByAccYear(String mainClass,String departmentId,String year, String month, String month1,boolean monthFlag){
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" select  ");
		queryString.append(" mp43.MP4003_ACCOUNTING_NUM,  ");
		queryString.append(" mp41.MP4001_NAME,  ");
		queryString.append(" mp43.MP4003_YEAR,  ");
		if(monthFlag == true){
			queryString.append(" mp43.MP4003_MONTH,  ");
		}
		queryString.append(" sum(mp43.MP4003_AMOUNT) ,  ");
		queryString.append(" sum(mp43.MP4003_AMOUNT2) ,  ");
		queryString.append(" sum(mp43.MP4003_AMOUNT3)   ");
		queryString.append(" from MP4003 mp43,MP4001 mp41 ");
		queryString.append(" where 1=1  ");
		queryString.append(" and mp43.MP4003_ACCOUNTING_NUM = mp41.MP4001_NUM  ");
		if(departmentId != null && !departmentId.equals("")){
			queryString.append(" and mp43.MP4003_DEPARTMENT_ID = '" + departmentId + "'  ");
		}
		if(year != null && !year.equals("")){
			queryString.append(" and mp43.MP4003_YEAR = '" + year + "'  ");
		}

		if(month1 != null && !month1.equals("")){
			if(month != null && !month.equals("")){
				queryString.append(" and cast(mp43.MP4003_MONTH as int) >= '" + month + "'  ");
				queryString.append(" and cast(mp43.MP4003_MONTH as int) <= '" + month1 + "'  ");
			}
		}else{
			if(month != null && !month.equals("")){
				queryString.append(" and mp43.MP4003_MONTH = '" + month + "'  ");
			}
		}
		if(!mainClass.equals("")){
			queryString.append(" and mp41.MP4001_TYPE = '" + mainClass  + "' ");
		}
		
		if(monthFlag == true){
			queryString.append(" group by mp43.MP4003_ACCOUNTING_NUM,mp41.MP4001_NAME,mp43.MP4003_YEAR,mp43.MP4003_MONTH  ");
			queryString.append(" order by MP4003_ACCOUNTING_NUM,mp43.MP4003_YEAR,mp43.MP4003_MONTH  ");
		}else{
			queryString.append(" group by mp43.MP4003_ACCOUNTING_NUM,mp41.MP4001_NAME,mp43.MP4003_YEAR  ");
			queryString.append(" order by MP4003_ACCOUNTING_NUM  ");
		}
		
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(queryString.toString());
		List<Object[]> list = query.list();
//		session.close();
		
		MP4003 mp4003 = new MP4003();
		List<MP4003> retList = new ArrayList<MP4003>();
		Map<String,String> monthNameMap = Constant.getMonthNameList();
		
		for(int i=0,j=list.size(); i<j; i++){
			mp4003 = new MP4003();
			Object[] obj = list.get(i);
			
			mp4003.setMP4003_ACCOUNTING_NUM(obj[0] == null ? "" : obj[0].toString());
			mp4003.setMP4003_ACCOUNTING_NUM_NAME(obj[1] == null ? "" : obj[1].toString());
			mp4003.setMP4003_YEAR(obj[2] == null ? "" : obj[2].toString());
			if(monthFlag == true){
				mp4003.setMP4003_MONTH(obj[3] == null ? "" : obj[3].toString());
				mp4003.setMP4003_AMOUNT(obj[4] == null ? new BigDecimal("0") : new BigDecimal(obj[4].toString()));
				mp4003.setMP4003_AMOUNT2(obj[5] == null ? new BigDecimal("0") : new BigDecimal(obj[5].toString()));
				mp4003.setMP4003_AMOUNT3(obj[6] == null ? new BigDecimal("0") : new BigDecimal(obj[6].toString()));
			}else{
				mp4003.setMP4003_AMOUNT(obj[3] == null ? new BigDecimal("0") : new BigDecimal(obj[3].toString()));
				mp4003.setMP4003_AMOUNT2(obj[4] == null ? new BigDecimal("0") : new BigDecimal(obj[4].toString()));
				mp4003.setMP4003_AMOUNT3(obj[5] == null ? new BigDecimal("0") : new BigDecimal(obj[5].toString()));
			}
			
			BigDecimal _totalAmount = mp4003.getMP4003_AMOUNT().add(mp4003.getMP4003_AMOUNT3());
			BigDecimal _usedAmount = _totalAmount.subtract(mp4003.getMP4003_AMOUNT2());
			mp4003.setMP4003_AMOUNT4(_usedAmount);
			
			if(monthNameMap.containsKey(mp4003.getMP4003_MONTH())){
				mp4003.setMP4003_MONTH_NAME(monthNameMap.get(mp4003.getMP4003_MONTH()));
			}
			
			retList.add(mp4003);
		}
		
		return retList;
	}
	// 图形报表(所有部门当月的总预算)
	@SuppressWarnings("unchecked")
	public List<MP4003> getBudgetChartData(String year, String month){
		StringBuffer queryString = new StringBuffer();
		queryString.append(" select  ");
		queryString.append(" mp43.MP4003_DEPARTMENT_ID,   ");
		queryString.append(" mp02.MP0002_DEPARTMENT_NAME,   ");
		queryString.append(" sum(mp43.MP4003_AMOUNT),  ");
		queryString.append(" sum(mp43.MP4003_AMOUNT3)  ");
		queryString.append(" from MP4003 mp43, MP0002 mp02  ");
		queryString.append(" where 1=1  ");
		queryString.append(" and mp43.MP4003_DEPARTMENT_ID = mp02.MP0002_SEQ  ");
		queryString.append(" and mp02.MP0002_DEPARTMENT_STATUS = '1'  ");
		
		queryString.append(" and mp43.MP4003_YEAR = '" + year + "'  ");
		queryString.append(" and mp43.MP4003_MONTH = '" + month + "'  ");
		
		queryString.append(" group by mp43.MP4003_DEPARTMENT_ID,mp02.MP0002_DEPARTMENT_NAME  ");
		queryString.append(" order by mp02.MP0002_DEPARTMENT_NAME  ");

		Session session = sf.getCurrentSession();
		Query query = session.createQuery(queryString.toString());
		List<Object[]> list = query.list();
//		session.close();
		
		MP4003 mp4003 = new MP4003();
		List<MP4003> retList = new ArrayList<MP4003>();
		
		for(int i=0,j=list.size(); i<j; i++){
			mp4003 = new MP4003();
			Object[] obj = list.get(i);
		
			mp4003.setMP4003_DEPARTMENT_ID(obj[0] == null ? "" : obj[0].toString());
			mp4003.setMP4003_DEPARTMENT_NAME(obj[1] == null ? "" : obj[1].toString());
			mp4003.setMP4003_AMOUNT(obj[2] == null ? new BigDecimal("0") : new BigDecimal(obj[2].toString()));
			mp4003.setMP4003_AMOUNT3(obj[3] == null ? new BigDecimal("0") : new BigDecimal(obj[3].toString()));
			
			retList.add(mp4003);
		}
		
		return retList;
	}
	// 图形报表(一个部门一年的预算折线图)
	@SuppressWarnings("unchecked")
	public List<MP4003> getBudgetChartData2(String year, String departmentID){
		StringBuffer queryString = new StringBuffer();
		queryString.append(" select  ");
		queryString.append(" mp43.MP4003_MONTH,   ");
		queryString.append(" sum(mp43.MP4003_AMOUNT),  ");
		queryString.append(" sum(mp43.MP4003_AMOUNT3)  ");
		queryString.append(" from MP4003 mp43  ");
		queryString.append(" where 1=1  ");
		queryString.append(" and mp43.MP4003_YEAR = '" + year + "'  ");
		queryString.append(" and mp43.MP4003_DEPARTMENT_ID = '" + departmentID + "'  ");
		
		queryString.append(" group by mp43.MP4003_MONTH  ");
		//queryString.append(" order by mp43.MP4003_MONTH  ");
		queryString.append(" order by CAST(mp43.MP4003_MONTH as int)  ");
		

		Session session = sf.getCurrentSession();
		Query query = session.createQuery(queryString.toString());
		List<Object[]> list = query.list();
//		session.close();
		
		MP4003 mp4003 = new MP4003();
		List<MP4003> retList = new ArrayList<MP4003>();
		Map<String,String> monthNameMap = Constant.getMonthNameList();
		
		for(int i=0,j=list.size(); i<j; i++){
			mp4003 = new MP4003();
			Object[] obj = list.get(i);
		
			mp4003.setMP4003_MONTH(obj[0] == null ? "" : obj[0].toString());
			mp4003.setMP4003_AMOUNT(obj[1] == null ? new BigDecimal("0") : new BigDecimal(obj[1].toString()));
			mp4003.setMP4003_AMOUNT3(obj[2] == null ? new BigDecimal("0") : new BigDecimal(obj[2].toString()));
			
			if(monthNameMap.containsKey(mp4003.getMP4003_MONTH())){
				mp4003.setMP4003_MONTH_NAME(monthNameMap.get(mp4003.getMP4003_MONTH()));
			}
			
			retList.add(mp4003);
		}
		
		return retList;
	}
	
	// 构建SQL语句
	private StringBuffer createSqlStatement(){
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" select ");
		queryString.append(" mp43.MP4003_SEQ, ");
		queryString.append(" mp43.MP4003_DEPARTMENT_ID, ");
		queryString.append(" mp43.MP4003_ACCOUNTING_NUM, ");
		queryString.append(" mp43.MP4003_AMOUNT, ");
		queryString.append(" mp02.MP0002_DEPARTMENT_NAME, ");
		queryString.append(" mp41.MP4001_NAME, ");
		queryString.append(" mp42.MP4002_NAME_E, ");
		queryString.append(" mp43.MP4003_YEAR, ");
		queryString.append(" mp43.MP4003_MONTH, ");
		queryString.append(" mp43.MP4003_AMOUNT2, ");
		queryString.append(" mp43.MP4003_AMOUNT3, ");
		queryString.append(" mp43.MP4003_STATUS, ");
		queryString.append(" mp41.MP4001_DES, ");
		queryString.append(" mp41.MP4001_TYPE, ");
		queryString.append(" mp43.MP4003_AMOUNT_MAX ");
		
		queryString.append(" from MP4003 mp43,MP4001 mp41,MP0002 mp02,MP4002 mp42 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and mp43.MP4003_ACCOUNTING_NUM = mp41.MP4001_NUM ");
		queryString.append(" and mp43.MP4003_DEPARTMENT_ID = mp02.MP0002_SEQ ");
		queryString.append(" and mp41.MP4001_PARENT_TYPE = mp42.MP4002_NUM ");
		queryString.append(" and mp41.MP4001_STATUS = '1' ");
		queryString.append(" and mp42.MP4002_STATUS = '1' ");
		
		return queryString;
	}
	// 检索数据
	@SuppressWarnings("unchecked")
	private List<MP4003> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		List<Object[]> list = query.list();
//		session.close();
		
		List<MP4003> retList = getDataList(list);
		
		return retList;
	}
	// 解析数据
	private List<MP4003> getDataList(List<Object[]> list){
		MP4003 mp4003 = new MP4003();
		List<MP4003> retList = new ArrayList<MP4003>();
		Map<String,String> monthNameMap = Constant.getMonthNameList();
		
		for(int i=0,j=list.size(); i<j; i++){
			mp4003 = new MP4003();
			Object[] obj = list.get(i);
			
			if(obj[0] == null){
				continue;
			}else{
				mp4003.setMP4003_SEQ(Integer.parseInt(obj[0].toString()));
			}
			mp4003.setMP4003_DEPARTMENT_ID(obj[1] == null ? "" : obj[1].toString());
			mp4003.setMP4003_ACCOUNTING_NUM(obj[2] == null ? "" : obj[2].toString());
			mp4003.setMP4003_AMOUNT(obj[3] == null ? new BigDecimal("0") : new BigDecimal(obj[3].toString()));
			mp4003.setMP4003_DEPARTMENT_NAME(obj[4] == null ? "" : obj[4].toString());
			mp4003.setMP4003_ACCOUNTING_NAME(obj[5] == null ? "" : obj[5].toString());
			mp4003.setMP4003_ACCOUNT_TYPE_NAME(obj[6] == null ? "" : obj[6].toString());
			mp4003.setMP4003_YEAR(obj[7] == null ? "" : obj[7].toString());
			mp4003.setMP4003_MONTH(obj[8] == null ? "" : obj[8].toString());
			mp4003.setMP4003_AMOUNT2(obj[9] == null ? new BigDecimal("0") : new BigDecimal(obj[9].toString()));
			mp4003.setMP4003_AMOUNT3(obj[10] == null ? new BigDecimal("0") : new BigDecimal(obj[10].toString()));
			mp4003.setMP4003_STATUS(obj[11] == null ? "" : obj[11].toString());
			mp4003.setMP4003_ACCOUNT_DESC(obj[12] == null ? "" : obj[12].toString());
			mp4003.setMP4003_TYPE(obj[13] == null ? "" : obj[13].toString());
			mp4003.setMP4003_AMOUNT_MAX(obj[14] == null ? new BigDecimal("0") : new BigDecimal(obj[14].toString()));
			
			BigDecimal _totalAmount = mp4003.getMP4003_AMOUNT().add(mp4003.getMP4003_AMOUNT3());
			BigDecimal _usedAmount = _totalAmount.subtract(mp4003.getMP4003_AMOUNT2());
			mp4003.setMP4003_AMOUNT4(_usedAmount);
			
			if(monthNameMap.containsKey(mp4003.getMP4003_MONTH())){
				mp4003.setMP4003_MONTH_NAME(monthNameMap.get(mp4003.getMP4003_MONTH()));
			}
			if(mp4003.getMP4003_TYPE().equals("1")){
				mp4003.setMP4003_TYPE_NAME("EXPENSE");
			}else if(mp4003.getMP4003_TYPE().equals("2")){
				mp4003.setMP4003_TYPE_NAME("INCOME");
			}else if(mp4003.getMP4003_TYPE().equals("3")){
				mp4003.setMP4003_TYPE_NAME("COSTS");
			}
			
			if(mp4003.getMP4003_STATUS().equals("0")){
				mp4003.setMP4003_STATUS_NAME("Pending");
			}else if(mp4003.getMP4003_STATUS().equals("1")){
				mp4003.setMP4003_STATUS_NAME("Pending(AC)");
			}else if(mp4003.getMP4003_STATUS().equals("2")){
				mp4003.setMP4003_STATUS_NAME("Approved");
			}
			
			retList.add(mp4003);
		}
		log.info("Count:" + retList.size());
		return retList;
	}
}
