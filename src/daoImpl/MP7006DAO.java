package daoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import common.Constant;

import dao.IMP7006DAO;
import entity.MP7006;

public class MP7006DAO   implements IMP7006DAO{
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	// 保存数据
	public void save(MP7006 mp7006) {
		if(mp7006 != null){
			sf.getCurrentSession().save(mp7006);
		}
	}
	// 删除数据
	public void delete(MP7006 mp7006) {
		if(mp7006 != null){
			sf.getCurrentSession().delete(mp7006);
		}
	}
	// 根据KEY检索数据
	public MP7006 findById(String key) {
		//return (MP7006)sf.getCurrentSession().get("entity.MP7006", key);
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		queryString.append(" and mp76.MP7006_STATUS = '1' ");
		queryString.append(" and mp76.MP7006_SEQ = '" + key + "' ");
		
		List<MP7006> retList = executeSqlStatement(queryString, -1, -1);
		if(retList == null || retList.size() ==0){
			return null;
		}else{
			return retList.get(0);
		}
	}
	// 取得所有有效数据
	@SuppressWarnings("unchecked")
	public List<MP7006> findAll() {
		return sf.getCurrentSession().createQuery(" from MP7006 where 1=1 and MP7006_STATUS = '1' ").list();
	}
	// 更新数据
	public void update(MP7006 mp7006) {
		if(mp7006 != null){
			sf.getCurrentSession().update(mp7006);
		}
	}
	// 动态根据传入的参数，检索数据
	public List<MP7006> findByProperty(String name, String value) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		queryString.append(" and mp76." + name + "='" + value + "' ");
		
		List<MP7006> retList = executeSqlStatement(queryString,-1,-1);
		
		return retList;
	}
	// 分页方法
	public List<MP7006> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		if(propertyMap.containsKey("EVL_STATUS") && propertyMap.get("EVL_STATUS") != null && !propertyMap.get("EVL_STATUS").equals("") && !propertyMap.get("EVL_STATUS").equals("-1")){
			queryString.append(" and mp76.MP7006_STATUS = '" + propertyMap.get("EVL_STATUS") + "' ");
		}else{
			queryString.append(" and mp76.MP7006_STATUS = '1' ");
		}
		if(propertyMap.containsKey("EMP_NUM") && propertyMap.get("EMP_NUM") != null && !propertyMap.get("EMP_NUM").equals("")){
			queryString.append(" and mp76.MP7006_EMPLOYEE_NUM = '" + propertyMap.get("EMP_NUM") + "' ");
		}
		if(propertyMap.containsKey("REVIEW_PERIOD") && propertyMap.get("REVIEW_PERIOD") != null && !propertyMap.get("REVIEW_PERIOD").equals("")){
			queryString.append(" and mp76.MP7006_CURRENT_APPRAISAL_CYCLE = '" + propertyMap.get("REVIEW_PERIOD") + "' ");
		}
/*		if(propertyMap.containsKey("EMP_DEP") && propertyMap.get("EMP_DEP") != null && !propertyMap.get("EMP_DEP").equals("") && !propertyMap.get("EMP_DEP").equals("-1")){
			queryString.append(" and mp76.MP7006_DEPARTMENT = '" + propertyMap.get("EMP_DEP") + "' ");
		}
		if(propertyMap.containsKey("APPRAISER") && propertyMap.get("APPRAISER") != null && !propertyMap.get("APPRAISER").equals("")){
			queryString.append(" and (  ");
			queryString.append(" mp76.MP7006_APPRAISER = '" + propertyMap.get("APPRAISER") + "' ");
			queryString.append(" or mp76.MP7006_EMPLOYEE_NUM = '" + propertyMap.get("APPRAISER") + "' ");
			queryString.append(" )  ");
		}
		*/
		if(propertyMap.containsKey("EMP_DEP") && propertyMap.get("EMP_DEP") != null && !propertyMap.get("EMP_DEP").equals("") && !propertyMap.get("EMP_DEP").equals("-1")){
			// 全权限
			if(propertyMap.containsKey("AUT_ALL") && propertyMap.get("AUT_ALL") != null && propertyMap.get("AUT_ALL").equals("1")){
				queryString.append(" and mp76.MP7006_DEPARTMENT = '" + propertyMap.get("EMP_DEP") + "') ");
			}else{
				// 部门级权限
				if(propertyMap.containsKey("DEPARTMENT") && propertyMap.get("DEPARTMENT") != null && !propertyMap.get("DEPARTMENT").equals("")){
					String dep1 = propertyMap.get("EMP_DEP");
					String dep2 = propertyMap.get("DEPARTMENT");
					
					if(dep1.equals(dep2)){
						queryString.append(" and mp76.MP7006_DEPARTMENT = '" + dep2 + "') ");
					}else{
						queryString.append(" and mp76.MP7006_DEPARTMENT = '" + dep1 + "' ");
						if(propertyMap.containsKey("APPRAISER") && propertyMap.get("APPRAISER") != null && !propertyMap.get("APPRAISER").equals("")){
							queryString.append(" and  mp76.MP7006_APPRAISER = '" + propertyMap.get("APPRAISER") + "' ");
						}
					}
				}else{
					queryString.append(" and mp76.MP7006_DEPARTMENT = '" + propertyMap.get("EMP_DEP") + "' ");
					if(propertyMap.containsKey("APPRAISER") && propertyMap.get("APPRAISER") != null && !propertyMap.get("APPRAISER").equals("")){
						queryString.append(" and mp76.MP7006_APPRAISER = '" + propertyMap.get("APPRAISER") + "' ");
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
						queryString.append(" and ( mp76.MP7006_APPRAISER = '" + propertyMap.get("APPRAISER") + "' ");
						queryString.append(" or mp76.MP7006_DEPARTMENT = '" + propertyMap.get("DEPARTMENT") + "') ");
					}else{// 无指定绩效评定人
						queryString.append(" and mp76.MP7006_DEPARTMENT = '" + propertyMap.get("DEPARTMENT") + "' ");
					}
				}else{// 普通员工权限
					if(propertyMap.containsKey("APPRAISER") && propertyMap.get("APPRAISER") != null && !propertyMap.get("APPRAISER").equals("")){
						queryString.append(" and mp76.MP7006_APPRAISER = '" + propertyMap.get("APPRAISER") + "' ");
					}
				}
			}
		}
		
		List<MP7006> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);
		
		return retList;
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		List<MP7006> retList = findByPropertyByPage(propertyMap,-1,-1);
		return retList.size();
	}
	// 自定义查询条件及排序方式
	public List<MP7006> findDataBySelfDefined(String search,String order, int pageNum, int pageCount){
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		queryString.append(search);
		// 排序方式
		queryString.append(order);
		
		List<MP7006> retList = executeSqlStatement(queryString, pageNum, pageCount);
		return retList;
	}
	
	// 构建SQL语句
	private StringBuffer createSqlStatement(){
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" select ");
		queryString.append(" mp76.MP7006_SEQ, ");
		queryString.append(" mp76.MP7006_EMPLOYEE_NUM, ");
		queryString.append(" mp76.MP7006_EFFECTIVE_DATE_LAST_APPRAISAL, ");
		queryString.append(" mp76.MP7006_APPRAISER, ");
		queryString.append(" mp76.MP7006_REVIEWER, ");
		queryString.append(" mp76.MP7006_DESIGNATION, ");
		queryString.append(" mp76.MP7006_DEPARTMENT, ");
		queryString.append(" mp76.MP7006_CURRENT_APPRAISAL_CYCLE, ");
		queryString.append(" mp76.MP7006_APPRAISER_DESIGNATION, ");
		queryString.append(" mp76.MP7006_REVIEWER_DESIGNATION, ");
		queryString.append(" mp76.MP7006_COMPETENCE_TOTAL_SCORES, ");
		queryString.append(" mp76.MP7006_GRAND_TOTAL_SCORES, ");
		queryString.append(" mp76.MP7006_STATUS,  ");
		queryString.append(" mp02.MP0002_DEPARTMENT_NAME, ");
		queryString.append(" mp11.MP1001_PREFERED_NAME, ");
		queryString.append(" mp09.MP0009_POSITION_NAME_E, ");
		queryString.append(" mp76.MP7006_TOTAL_WEIGHTAGE, ");
		queryString.append(" mp76.MP7006_EFFECTIVE_DATE_APPRAISAL ");
		
		queryString.append(" from MP7006 mp76,MP1001 mp11,MP0002 mp02,MP0009 mp09 ");
		queryString.append(" where 1=1 ");
		//queryString.append(" and mp76.MP7006_STATUS = '1' ");
		queryString.append(" and mp76.MP7006_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM ");
		queryString.append(" and mp76.MP7006_DESIGNATION = mp09.MP0009_SEQ ");
		queryString.append(" and mp76.MP7006_DEPARTMENT = mp02.MP0002_SEQ ");
		
		return queryString;
	}
	// 检索数据
	@SuppressWarnings("unchecked")
	private List<MP7006> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
		Session session = sf.getCurrentSession();		
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		List<Object[]> list = query.list();
//		session.close();
		
		List<MP7006> retList = getDataList(list);
		
		return retList;
	}
	// 解析数据
	private List<MP7006> getDataList(List<Object[]> list){
		MP7006 mp7006 = new MP7006();
		List<MP7006> retList = new ArrayList<MP7006>();
		
		for(int i=0,j=list.size(); i<j; i++){
			mp7006 = new MP7006();
			Object[] obj = list.get(i);
			
			if(obj[0] == null){
				continue;
			}else{
				mp7006.setMP7006_SEQ(obj[0].toString());
			}
			mp7006.setMP7006_EMPLOYEE_NUM(obj[1] == null ? "" : obj[1].toString());
			mp7006.setMP7006_EFFECTIVE_DATE_LAST_APPRAISAL(obj[2] == null ? "" : obj[2].toString());
			mp7006.setMP7006_APPRAISER(obj[3] == null ? "" : obj[3].toString());
			mp7006.setMP7006_REVIEWER(obj[4] == null ? "" : obj[4].toString());
			mp7006.setMP7006_DESIGNATION(obj[5] == null ? "" : obj[5].toString());
			mp7006.setMP7006_DEPARTMENT(obj[6] == null ? "" : obj[6].toString());
			mp7006.setMP7006_CURRENT_APPRAISAL_CYCLE(obj[7] == null ? "" : obj[7].toString());
			mp7006.setMP7006_APPRAISER_DESIGNATION(obj[8] == null ? "" : obj[8].toString());
			mp7006.setMP7006_REVIEWER_DESIGNATION(obj[9] == null ? "" : obj[9].toString());
			mp7006.setMP7006_COMPETENCE_TOTAL_SCORES(obj[10] == null ? "" : obj[10].toString());
			mp7006.setMP7006_GRAND_TOTAL_SCORES(obj[11] == null ? "" : obj[11].toString());
			mp7006.setMP7006_STATUS(obj[12] == null ? "" : obj[12].toString());
			mp7006.setMP7006_DEPARTMENT_NAME(obj[13] == null ? "" : obj[13].toString());
			mp7006.setMP7006_EMPLOYEE_NAME(obj[14] == null ? "" : obj[14].toString());
			mp7006.setMP7006_JOB_TITLE(obj[15] == null ? "" : obj[15].toString());
			mp7006.setMP7006_TOTAL_WEIGHTAGE(obj[16] == null ? "" : obj[16].toString());
			mp7006.setMP7006_EFFECTIVE_DATE_APPRAISAL(obj[17] == null ? "" : obj[17].toString());
			
			// 状态
			if(mp7006.getMP7006_STATUS().equals("1")){
				mp7006.setMP7006_STATUS_NAME(Constant.VALID);
			}else{
				mp7006.setMP7006_STATUS_NAME(Constant.INVALID);
			}
			
			retList.add(mp7006);
		}
		return retList;
	}
}
