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

import dao.IMP4004DAO;
import entity.MP4004;

public class MP4004DAO   implements IMP4004DAO{
	private static final Log log = LogFactory.getLog(MP4004DAO.class);
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	// 保存数据
	public void save(MP4004 mp4004) {
		if(mp4004 != null){
			sf.getCurrentSession().save(mp4004);
		}
	}
	// 删除数据
	public void delete(MP4004 mp4004) {
		if(mp4004 != null){
			sf.getCurrentSession().delete(mp4004);
		}
	}
	// 根据KEY检索数据
	public MP4004 findById(int key) {
		return (MP4004)sf.getCurrentSession().get("entity.MP4004", key);
/*		StringBuffer queryString = new StringBuffer();

		queryString = createSqlStatement();
		queryString.append(" and mp44.MP4004_SEQ=" + key + " ");
		
		MP4004 mp4004 = new MP4004();

		List<MP4004> retList = executeSqlStatement(queryString,-1,-1);
		if(retList != null && retList.size() > 0){
			mp4004 = retList.get(0);
		}else{
			mp4004 = null;
		}
		
		return mp4004;*/
	}
	// 取得所有有效数据
	@SuppressWarnings("unchecked")
	public List<MP4004> findAll() {
		return sf.getCurrentSession().createQuery(" from MP4004 mp44 ").list();
	}
	// 更新数据
	public void update(MP4004 mp4004) {
		if(mp4004 != null){
			sf.getCurrentSession().update(mp4004);
		}
	}
	// 动态根据传入的参数，检索数据
	public List<MP4004> findByProperty(String name, String value) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		queryString.append(" and mp44." + name + "='" + value + "' ");
		
		List<MP4004> retList = executeSqlStatement(queryString,-1,-1);
		
		return retList;
	}
	// 分页方法
	public List<MP4004> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		// 部门
		if(propertyMap.containsKey("DEPARTMENT_ID") && propertyMap.get("DEPARTMENT_ID") != null && !propertyMap.get("DEPARTMENT_ID").equals("") && !propertyMap.get("DEPARTMENT_ID").equals("-1")){
			queryString.append(" and mp03.MP4003_DEPARTMENT_ID = '" + propertyMap.get("DEPARTMENT_ID")  + "' ");
		}
		// 年
		if(propertyMap.containsKey("YEAR") && propertyMap.get("YEAR") != null && !propertyMap.get("YEAR").equals("")){
			queryString.append(" and mp03.MP4003_YEAR = '" + propertyMap.get("YEAR")  + "' ");
		}
		// 月
		if(propertyMap.containsKey("MONTH") && propertyMap.get("MONTH") != null && !propertyMap.get("MONTH").equals("")){
			queryString.append(" and mp03.MP4003_MONTH = '" + propertyMap.get("MONTH")  + "' ");
		}
		
		queryString.append(" order by mp03.MP4003_YEAR desc, mp03.MP4003_MONTH desc");
		
		List<MP4004> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);
		
		return retList;
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		List<MP4004> retList = findByPropertyByPage(propertyMap,-1,-1);
		return retList.size();
	}
	// 构建SQL语句
	private StringBuffer createSqlStatement(){
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" select ");
		queryString.append(" mp04.MP4004_SEQ, ");
		queryString.append(" mp04.MP4004_KEY, ");
		queryString.append(" mp04.MP4004_AMOUNT, ");
		queryString.append(" mp04.MP4004_REASON, ");
		queryString.append(" mp04.MP4004_DATETIME, ");
		queryString.append(" mp04.MP4004_USER, ");
		queryString.append(" mp04.MP4004_STATUS, ");
		queryString.append(" mp03.MP4003_ACCOUNTING_NUM, ");
		queryString.append(" mp03.MP4003_DEPARTMENT_ID, ");
		queryString.append(" mp03.MP4003_YEAR, ");
		queryString.append(" mp03.MP4003_MONTH, ");
		queryString.append(" mp03.MP4003_AMOUNT, ");
		queryString.append(" mp03.MP4003_AMOUNT2, ");
		queryString.append(" mp03.MP4003_AMOUNT3, ");
		queryString.append(" mp02.MP0002_DEPARTMENT_NAME, ");
		queryString.append(" mp41.MP4001_NAME, ");
		queryString.append(" mp11.MP1001_PREFERED_NAME ");
		queryString.append(" from MP4004 mp04, MP4003 mp03, MP4001 mp41, MP0002 mp02, MP1001 mp11 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and mp04.MP4004_KEY = mp03.MP4003_SEQ ");
		queryString.append(" and mp03.MP4003_DEPARTMENT_ID = mp02.MP0002_SEQ ");
		queryString.append(" and mp03.MP4003_ACCOUNTING_NUM = mp41.MP4001_NUM ");
		queryString.append(" and mp04.MP4004_USER = mp11.MP1001_EMPLOYEE_NUM ");
		
		return queryString;
	}
	// 检索数据
	@SuppressWarnings("unchecked")
	private List<MP4004> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
		Session session = sf.getCurrentSession();		
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		List<Object[]> list = query.list();
//		session.close();
		
		List<MP4004> retList = getDataList(list);
		
		return retList;
	}
	// 解析数据
	private List<MP4004> getDataList(List<Object[]> list){
		MP4004 mp4004 = new MP4004();
		List<MP4004> retList = new ArrayList<MP4004>();
		Map<String,String> monthNameMap = Constant.getMonthNameList();
		
		for(int i=0,j=list.size(); i<j; i++){
			mp4004 = new MP4004();
			Object[] obj = list.get(i);
			
			if(obj[0] == null){
				continue;
			}else{
				mp4004.setMP4004_SEQ(Integer.parseInt(obj[0].toString()));
			}
			mp4004.setMP4004_KEY(obj[1] == null ? "" : obj[1].toString());
			mp4004.setMP4004_AMOUNT(obj[2] == null ? new BigDecimal("0") : new BigDecimal(obj[2].toString()));
			mp4004.setMP4004_REASON(obj[3] == null ? "" : obj[3].toString());
			mp4004.setMP4004_DATETIME(obj[4] == null ? "" : obj[4].toString());
			mp4004.setMP4004_USER(obj[5] == null ? "" : obj[5].toString());
			mp4004.setMP4004_STATUS(obj[6] == null ? "" : obj[6].toString());
			mp4004.setMP4004_ACCOUNTING_NUM(obj[7] == null ? "" : obj[7].toString());
			mp4004.setMP4004_DEPARTMENT(obj[8] == null ? "" : obj[8].toString());
			mp4004.setMP4004_YEAR(obj[9] == null ? "" : obj[9].toString());
			mp4004.setMP4004_MONTH(obj[10] == null ? "" : obj[10].toString());
			mp4004.setMP4004_AMOUNT1(obj[11] == null ? new BigDecimal("0") : new BigDecimal(obj[11].toString()));
			mp4004.setMP4004_AMOUNT2(obj[12] == null ? new BigDecimal("0") : new BigDecimal(obj[12].toString()));
			mp4004.setMP4004_AMOUNT3(obj[13] == null ? new BigDecimal("0") : new BigDecimal(obj[13].toString()));
			mp4004.setMP4004_DEPARTMENT_NAME(obj[14] == null ? "" : obj[14].toString());
			mp4004.setMP4004_ACCOUNTING_NUM_NAME(obj[15] == null ? "" : obj[15].toString());
			mp4004.setMP4004_APPLICANT(obj[16] == null ? "" : obj[16].toString());
			//System.out.println("Month(B):" + mp4004.getMP4004_MONTH());
			if(monthNameMap.containsKey(mp4004.getMP4004_MONTH())){
				mp4004.setMP4004_MONTH_NAME(monthNameMap.get(mp4004.getMP4004_MONTH()));
			}
			//System.out.println("Month(A):" + mp4004.getMP4004_MONTH() + "|" + mp4004.getMP4004_MONTH_NAME() + "\r\n" );
			if(mp4004.getMP4004_STATUS() == null || mp4004.getMP4004_STATUS().equals("0")){
				mp4004.setMP4004_STATUS_NAME("Declined");
			}else if(mp4004.getMP4004_STATUS().equals("1")){
					mp4004.setMP4004_STATUS_NAME("Pending");
			}else if(mp4004.getMP4004_STATUS().equals("2")){
				mp4004.setMP4004_STATUS_NAME("Approved");
		}
			
			retList.add(mp4004);
		}
		log.info("Count:" + retList.size());
		return retList;
	}
}
