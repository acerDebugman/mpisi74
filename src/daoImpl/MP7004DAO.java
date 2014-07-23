package daoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import common.Constant;

import dao.IMP7004DAO;
import entity.MP7004;

public class MP7004DAO   implements IMP7004DAO{
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	// 保存数据
	public void save(MP7004 mp7004) {
		if(mp7004 != null){
			sf.getCurrentSession().save(mp7004);
		}
	}
	// 删除数据
	public void delete(MP7004 mp7004) {
		if(mp7004 != null){
			sf.getCurrentSession().delete(mp7004);
		}
	}
	// 根据KEY检索数据
	public MP7004 findById(String key) {
		//return (MP7004)sf.getCurrentSession().get("entity.MP7004", key);
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		queryString.append(" and mp74.MP7004_STATUS = '1' ");
		queryString.append(" and mp74.MP7004_SEQ = '" + key + "' ");
		
		List<MP7004> retList = executeSqlStatement(queryString, -1, -1);
		if(retList == null || retList.size() ==0){
			return null;
		}else{
			return retList.get(0);
		}
	}
	// 取得所有有效数据
	@SuppressWarnings("unchecked")
	public List<MP7004> findAll() {
		return sf.getCurrentSession().createQuery(" from MP7004 where 1=1 and MP7004_STATUS = '1' ").list();
	}
	// 更新数据
	public void update(MP7004 mp7004) {
		if(mp7004 != null){
			sf.getCurrentSession().update(mp7004);
		}
	}
	// 动态根据传入的参数，检索数据
	public List<MP7004> findByProperty(String name, String value) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		queryString.append(" and mp74." + name + "='" + value + "' ");
		
		List<MP7004> retList = executeSqlStatement(queryString,-1,-1);
		
		return retList;
	}
	// 分页方法
	public List<MP7004> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		if(propertyMap.containsKey("EVL_STATUS") && propertyMap.get("EVL_STATUS") != null && !propertyMap.get("EVL_STATUS").equals("") && !propertyMap.get("EVL_STATUS").equals("-1")){
			queryString.append(" and mp74.MP7004_STATUS = '" + propertyMap.get("EVL_STATUS") + "' ");
		}else{
			queryString.append(" and mp74.MP7004_STATUS = '1' ");
		}
		if(propertyMap.containsKey("EMP_NUM") && propertyMap.get("EMP_NUM") != null && !propertyMap.get("EMP_NUM").equals("")){
			queryString.append(" and ( mp74.MP7004_EMPLOYEE_NUM = '" + propertyMap.get("EMP_NUM") + "' ");
			queryString.append(" or mp11.MP1001_PREFERED_NAME like '%" + propertyMap.get("EMP_NUM") + "%' ");
			queryString.append(" or mp11.MP1001_SURNAME like '%" + propertyMap.get("EMP_NUM") + "%' ");
			queryString.append(" or mp11.MP1001_FIRSTNAME like '%" + propertyMap.get("EMP_NUM") + "%' ) ");
		}
		if(propertyMap.containsKey("REVIEW_PERIOD") && propertyMap.get("REVIEW_PERIOD") != null && !propertyMap.get("REVIEW_PERIOD").equals("")){
			if(propertyMap.containsKey("REVIEW_PERIOD_2") && propertyMap.get("REVIEW_PERIOD_2") != null && !propertyMap.get("REVIEW_PERIOD_2").equals("")){
				queryString.append(" and mp74.MP7004_REVIEW_PERIOD >= '" + propertyMap.get("REVIEW_PERIOD") + "' ");
				queryString.append(" and mp74.MP7004_REVIEW_PERIOD <= '" + propertyMap.get("REVIEW_PERIOD_2") + "' ");
			}else{
				queryString.append(" and mp74.MP7004_REVIEW_PERIOD = '" + propertyMap.get("REVIEW_PERIOD") + "' ");
			}
		}
		if(propertyMap.containsKey("EMP_DEP") && propertyMap.get("EMP_DEP") != null && !propertyMap.get("EMP_DEP").equals("") && !propertyMap.get("EMP_DEP").equals("-1")){
			// 全权限
			if(propertyMap.containsKey("AUT_ALL") && propertyMap.get("AUT_ALL") != null && propertyMap.get("AUT_ALL").equals("1")){
				queryString.append(" and mp74.MP7004_DEPARTMENT = '" + propertyMap.get("EMP_DEP") + "') ");
			}else{
				// 部门级权限
				if(propertyMap.containsKey("DEPARTMENT") && propertyMap.get("DEPARTMENT") != null && !propertyMap.get("DEPARTMENT").equals("")){
					String dep1 = propertyMap.get("EMP_DEP");
					String dep2 = propertyMap.get("DEPARTMENT");
					
					if(dep1.equals(dep2)){
						queryString.append(" and mp74.MP7004_DEPARTMENT = '" + dep2 + "') ");
					}else{
						queryString.append(" and mp74.MP7004_DEPARTMENT = '" + dep1 + "' ");
						if(propertyMap.containsKey("APPRAISER") && propertyMap.get("APPRAISER") != null && !propertyMap.get("APPRAISER").equals("")){
							queryString.append(" and  mp74.MP7004_APPRAISER = '" + propertyMap.get("APPRAISER") + "' ");
						}
					}
				}else{
					queryString.append(" and mp74.MP7004_DEPARTMENT = '" + propertyMap.get("EMP_DEP") + "' ");
					if(propertyMap.containsKey("APPRAISER") && propertyMap.get("APPRAISER") != null && !propertyMap.get("APPRAISER").equals("")){
						queryString.append(" and mp74.MP7004_APPRAISER = '" + propertyMap.get("APPRAISER") + "' ");
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
						queryString.append(" and ( mp74.MP7004_APPRAISER = '" + propertyMap.get("APPRAISER") + "' ");
						queryString.append(" or mp74.MP7004_DEPARTMENT = '" + propertyMap.get("DEPARTMENT") + "') ");
					}else{// 无指定绩效评定人
						queryString.append(" and mp74.MP7004_DEPARTMENT = '" + propertyMap.get("DEPARTMENT") + "' ");
					}
				}else{// 普通员工权限
					if(propertyMap.containsKey("APPRAISER") && propertyMap.get("APPRAISER") != null && !propertyMap.get("APPRAISER").equals("")){
						queryString.append(" and mp74.MP7004_APPRAISER = '" + propertyMap.get("APPRAISER") + "' ");
					}
				}
			}
		}
		
		// 排序条件
		queryString.append(" order by MP7004_REVIEW_PERIOD desc, MP7004_EMPLOYEE_NUM asc ");
		
		List<MP7004> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);
		
		return retList;
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		List<MP7004> retList = findByPropertyByPage(propertyMap,-1,-1);
		return retList.size();
	}
	// 自定义查询条件及排序方式
	public List<MP7004> findDataBySelfDefined(String search,String order, int pageNum, int pageCount){
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		queryString.append(search);
		// 排序方式
		queryString.append(order);
		
		List<MP7004> retList = executeSqlStatement(queryString, pageNum, pageCount);
		return retList;
	}
    
	@SuppressWarnings("unchecked")
	public List<MP7004> getBarCHart1(String _from, String _to){
		StringBuffer queryString = new StringBuffer();
		queryString.append(" select mp02.MP0002_DEPARTMENT_NAME,   ");
		//queryString.append(" cast(round(AVG(CONVERT(decimal(18,2),mp74.MP7004_SCORES)),2) as numeric(18,2)) as SCORES "); 
		queryString.append(" convert(numeric(18,2), round(avg(convert(decimal(18,2),mp74.MP7004_SCORES)),2)) as SCORES ");
		queryString.append(" from MP7004 mp74, MP0002 mp02  ");
		queryString.append(" where 1=1  ");
		queryString.append(" and mp74.MP7004_DEPARTMENT = mp02.MP0002_SEQ  ");
		queryString.append(" and mp74.MP7004_REVIEW_PERIOD >= '" + _from + "'  ");
		queryString.append(" and mp74.MP7004_REVIEW_PERIOD <= '" + _to + "'  ");
		queryString.append(" group by mp02.MP0002_DEPARTMENT_NAME  ");
		queryString.append(" order by mp02.MP0002_DEPARTMENT_NAME  ");

		Session session = sf.getCurrentSession();
		Query query = session.createQuery(queryString.toString());
		List<Object[]> list = query.list();
//		session.close();
		
		MP7004 mp7004 = null;
		List<MP7004> retList = new ArrayList<MP7004>();
		for(int i=0,j=list.size(); i<j; i++){
			mp7004 = new MP7004();
			Object[] obj = list.get(i);
			
		    mp7004.setMP7004_DEPARTMENT_NAME(obj[0] == null ? "" : obj[0].toString());
		    mp7004.setMP7004_SCORES(obj[1] == null ? "" : obj[1].toString());
			
			retList.add(mp7004);
		}
		
		return retList;
	}

	@SuppressWarnings("unchecked")
	public List<MP7004> getBarCHart2(String _from, String _to, String department){
		StringBuffer queryString = new StringBuffer();
		queryString.append(" select mp11.MP1001_PREFERED_NAME,  ");
		//queryString.append(" cast(round(AVG(CONVERT(decimal(18,2), mp74.MP7004_SCORES)),2) as numeric(18,2)) as SCORES   ");
		queryString.append(" convert(numeric(18,2), round(avg(convert(decimal(18,2),mp74.MP7004_SCORES)),2)) as SCORES ");
		queryString.append(" from MP7004 mp74, MP1001 mp11 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and mp74.MP7004_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM ");
		queryString.append(" and mp74.MP7004_REVIEW_PERIOD >= '" + _from + "' ");
		queryString.append(" and mp74.MP7004_REVIEW_PERIOD <= '" + _to + "' ");
		queryString.append(" and mp74.MP7004_DEPARTMENT = '" + department + "' ");
		queryString.append(" group by mp11.MP1001_PREFERED_NAME ");
		queryString.append(" order by mp11.MP1001_PREFERED_NAME ");

		Session session = sf.getCurrentSession();
		Query query = session.createQuery(queryString.toString());
		List<Object[]> list = query.list();
//		session.close();
		
		MP7004 mp7004 = null;
		List<MP7004> retList = new ArrayList<MP7004>();
		for(int i=0,j=list.size(); i<j; i++){
			mp7004 = new MP7004();
			Object[] obj = list.get(i);
			
		    mp7004.setMP7004_EMPLOYEE_NAME(obj[0] == null ? "" : obj[0].toString());
		    mp7004.setMP7004_SCORES(obj[1] == null ? "" : obj[1].toString());
			
			retList.add(mp7004);
		}
		
		return retList;
	}
	@SuppressWarnings("unchecked")
	public List<MP7004> getLineCHart3(String _from, String _to, String department){
		StringBuffer queryString = new StringBuffer();
		queryString.append(" select mp74.MP7004_REVIEW_PERIOD,  ");
		//queryString.append(" cast(round(AVG(CONVERT(decimal(18,2), mp74.MP7004_SCORES)),2) as numeric(18,2)) as SCORES   ");
		queryString.append(" convert(numeric(18,2), round(avg(convert(decimal(18,2),mp74.MP7004_SCORES)),2)) as SCORES ");
		queryString.append(" from MP7004 mp74, MP0002 mp02 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and mp74.MP7004_DEPARTMENT = mp02.MP0002_SEQ ");
		queryString.append(" and mp74.MP7004_REVIEW_PERIOD >= '" + _from + "' ");
		queryString.append(" and mp74.MP7004_REVIEW_PERIOD <= '" + _to + "' ");
		queryString.append(" and mp74.MP7004_DEPARTMENT = '" + department + "' ");
		queryString.append(" group by mp74.MP7004_REVIEW_PERIOD ");
		queryString.append(" order by mp74.MP7004_REVIEW_PERIOD ");

		Session session = sf.getCurrentSession();
		Query query = session.createQuery(queryString.toString());
		List<Object[]> list = query.list();
//		session.close();
		
		MP7004 mp7004 = null;
		List<MP7004> retList = new ArrayList<MP7004>();
		for(int i=0,j=list.size(); i<j; i++){
			mp7004 = new MP7004();
			Object[] obj = list.get(i);
			
		    mp7004.setMP7004_REVIEW_PERIOD(obj[0] == null ? "" : obj[0].toString());
		    mp7004.setMP7004_SCORES(obj[1] == null ? "" : obj[1].toString());
			
			retList.add(mp7004);
		}
		
		return retList;
	}
	@SuppressWarnings("unchecked")
	public List<MP7004> getLineCHart4(String _from, String _to, String department, String empNum){
		StringBuffer queryString = new StringBuffer();
		queryString.append(" select mp74.MP7004_REVIEW_PERIOD,  ");
		//queryString.append(" cast(round(AVG(CONVERT(decimal(18,2),mp74.MP7004_SCORES)),2) as numeric(18,2)) as SCORES   ");
		queryString.append(" convert(numeric(18,2), round(avg(convert(decimal(18,2),mp74.MP7004_SCORES)),2)) as SCORES ");
		queryString.append(" from MP7004 mp74, MP1001 mp11 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and mp74.MP7004_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM ");
		queryString.append(" and mp74.MP7004_REVIEW_PERIOD >= '" + _from + "' ");
		queryString.append(" and mp74.MP7004_REVIEW_PERIOD <= '" + _to + "' ");
		queryString.append(" and mp74.MP7004_DEPARTMENT = '" + department + "' ");
		queryString.append(" and mp74.MP7004_EMPLOYEE_NUM = '" + empNum + "' ");
		queryString.append(" group by mp74.MP7004_REVIEW_PERIOD ");
		queryString.append(" order by mp74.MP7004_REVIEW_PERIOD ");

		Session session = sf.getCurrentSession();
		Query query = session.createQuery(queryString.toString());
		List<Object[]> list = query.list();
//		session.close();
		
		MP7004 mp7004 = null;
		List<MP7004> retList = new ArrayList<MP7004>();
		for(int i=0,j=list.size(); i<j; i++){
			mp7004 = new MP7004();
			Object[] obj = list.get(i);
			
		    mp7004.setMP7004_REVIEW_PERIOD(obj[0] == null ? "" : obj[0].toString());
		    mp7004.setMP7004_SCORES(obj[1] == null ? "" : obj[1].toString());
			
			retList.add(mp7004);
		}
		
		return retList;
	}

	// 构建SQL语句
	private StringBuffer createSqlStatement(){
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" select ");
		queryString.append(" mp74.MP7004_SEQ, ");
		queryString.append(" mp74.MP7004_EMPLOYEE_NUM, ");
		queryString.append(" mp74.MP7004_JOB_TITLE, ");
		queryString.append(" mp74.MP7004_DEPARTMENT, ");
		queryString.append(" mp74.MP7004_MANAGER, ");
		queryString.append(" mp74.MP7004_REVIEW_PERIOD, ");
		queryString.append(" mp74.MP7004_SCORES, ");
		queryString.append(" mp74.MP7004_COMMENT, ");
		queryString.append(" mp74.MP7004_STATUS, ");
		queryString.append(" mp02.MP0002_DEPARTMENT_NAME, ");
		queryString.append(" mp09.MP0009_POSITION_NAME_E, ");
		queryString.append(" mp11.MP1001_PREFERED_NAME, ");
		queryString.append(" mp74.MP7004_APPRAISER, ");
		queryString.append(" mp74.MP7004_APPRAISER_DESIGNATION ");
		
		queryString.append(" from MP7004 mp74,MP1001 mp11,MP0002 mp02,MP0009 mp09 ");
		queryString.append(" where 1=1 ");
		//queryString.append(" and mp74.MP7004_STATUS = '1' ");
		queryString.append(" and mp74.MP7004_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM ");
		queryString.append(" and mp74.MP7004_JOB_TITLE = mp09.MP0009_SEQ ");
		queryString.append(" and mp74.MP7004_DEPARTMENT = mp02.MP0002_SEQ ");
		
		return queryString;
	}
	// 检索数据
	@SuppressWarnings("unchecked")
	private List<MP7004> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
		Session session = sf.getCurrentSession();		
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		List<Object[]> list = query.list();
//		session.close();
		
		List<MP7004> retList = getDataList(list);
		
		return retList;
	}
	// 解析数据
	private List<MP7004> getDataList(List<Object[]> list){
		MP7004 mp7004 = new MP7004();
		List<MP7004> retList = new ArrayList<MP7004>();
		
		for(int i=0,j=list.size(); i<j; i++){
			mp7004 = new MP7004();
			Object[] obj = list.get(i);
			
			if(obj[0] == null){
				continue;
			}else{
				mp7004.setMP7004_SEQ(obj[0].toString());
			}
			mp7004.setMP7004_EMPLOYEE_NUM(obj[1] == null ? "" : obj[1].toString());
			mp7004.setMP7004_JOB_TITLE(obj[2] == null ? "" : obj[2].toString());
			mp7004.setMP7004_DEPARTMENT(obj[3] == null ? "" : obj[3].toString());
			mp7004.setMP7004_MANAGER(obj[4] == null ? "" : obj[4].toString());
			mp7004.setMP7004_REVIEW_PERIOD(obj[5] == null ? "" : obj[5].toString());
			mp7004.setMP7004_SCORES(obj[6] == null ? "" : obj[6].toString());
			mp7004.setMP7004_COMMENT(obj[7] == null ? "" : obj[7].toString());
			mp7004.setMP7004_STATUS(obj[8] == null ? "" : obj[8].toString());
			mp7004.setMP7004_DEPARTMENT_NAME(obj[9] == null ? "" : obj[9].toString());
			mp7004.setMP7004_JOB_NAME(obj[10] == null ? "" : obj[10].toString());
			mp7004.setMP7004_EMPLOYEE_NAME(obj[11] == null ? "" : obj[11].toString());
			mp7004.setMP7004_APPRAISER(obj[12] == null ? "" : obj[12].toString());
			mp7004.setMP7004_APPRAISER_DESIGNATION(obj[13] == null ? "" : obj[13].toString());
			
			// 状态
			if(mp7004.getMP7004_STATUS().equals("1")){
				mp7004.setMP7004_STATUS_NAME(Constant.VALID);
			}else{
				mp7004.setMP7004_STATUS_NAME(Constant.INVALID);
			}
			
			retList.add(mp7004);
		}
		return retList;
	}
}
