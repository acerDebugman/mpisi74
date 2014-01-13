package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import common.Constant;

import entity.MP7002;

public class MP7002DAO extends HibernateDaoSupport implements IMP7002DAO{
	// 保存数据
	public void save(MP7002 mp7002) {
		if(mp7002 != null){
			getHibernateTemplate().save(mp7002);
		}
	}
	// 删除数据
	public void delete(MP7002 mp7002) {
		if(mp7002 != null){
			getHibernateTemplate().delete(mp7002);
		}
	}
	// 根据KEY检索数据
	public MP7002 findById(String key) {
		return (MP7002)getHibernateTemplate().get("entity.MP7002", key);
	}
	// 取得所有有效数据
	@SuppressWarnings("unchecked")
	public List<MP7002> findAll() {
		return getHibernateTemplate().find(" from MP7002 where 1=1 and MP7002_STATUS = '1' ");
	}
	// 更新数据
	public void update(MP7002 mp7002) {
		if(mp7002 != null){
			getHibernateTemplate().update(mp7002);
		}
	}
	// 动态根据传入的参数，检索数据
	public List<MP7002> findByProperty(String name, String value) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		queryString.append(" and mp72." + name + "='" + value + "' ");
		
		List<MP7002> retList = executeSqlStatement(queryString,-1,-1);
		
		return retList;
	}
	// 分页方法
	public List<MP7002> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		if(propertyMap.containsKey("PLAN_STATUS") && propertyMap.get("PLAN_STATUS") != null && !propertyMap.get("PLAN_STATUS").equals("") && !propertyMap.get("PLAN_STATUS").equals("-1")){
			queryString.append(" and mp72.MP7002_STATUS = '" + propertyMap.get("PLAN_STATUS") + "' ");
		}else{
			queryString.append(" and mp72.MP7002_STATUS = '1' ");
		}
		if(propertyMap.containsKey("PLAN_NUM") && propertyMap.get("PLAN_NUM") != null && !propertyMap.get("PLAN_NUM").equals("")){
			queryString.append(" and mp72.MP7002_SEQ = '" + propertyMap.get("PLAN_NUM") + "' ");
		}
		if(propertyMap.containsKey("PLAN_TITLE") && propertyMap.get("PLAN_TITLE") != null && !propertyMap.get("PLAN_TITLE").equals("")){
			queryString.append(" and mp72.MP7002_TITLE like '%" + propertyMap.get("PLAN_TITLE") + "%' ");
		}
		if(propertyMap.containsKey("PLAN_YEAR") && propertyMap.get("PLAN_YEAR") != null && !propertyMap.get("PLAN_YEAR").equals("")){
			queryString.append(" and mp72.MP7002_YEAR = '" + propertyMap.get("PLAN_YEAR") + "' ");
		}
		if(propertyMap.containsKey("PLAN_MONTH") && propertyMap.get("PLAN_MONTH") != null && !propertyMap.get("PLAN_MONTH").equals("") && !propertyMap.get("PLAN_MONTH").equals("-1")){
			queryString.append(" and mp72.MP7002_MONTH = '" + propertyMap.get("PLAN_MONTH") + "' ");
		}
		
		// 此处追加数据排序
		//queryString.append(" order by mp72.MP7002_YEAR, cast(mp72.MP7002_MONTH as int), mp72.MP7002_SEQ ");
		queryString.append(" order by mp72.MP7002_SEQ desc ");
		
		List<MP7002> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);
		
		return retList;
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		List<MP7002> retList = findByPropertyByPage(propertyMap,-1,-1);
		return retList.size();
	}
	// 自定义查询条件及排序方式
	public List<MP7002> findDataBySelfDefined(String search,String order, int pageNum, int pageCount){
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		queryString.append(search);
		// 排序方式
		queryString.append(order);
		
		List<MP7002> retList = executeSqlStatement(queryString, pageNum, pageCount);
		return retList;
	}
	
	// 构建SQL语句
	private StringBuffer createSqlStatement(){
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" select ");
		queryString.append(" mp72.MP7002_SEQ, ");
		queryString.append(" mp72.MP7002_TITLE, ");
		queryString.append(" mp72.MP7002_YEAR, ");
		queryString.append(" mp72.MP7002_MONTH, ");
		queryString.append(" mp72.MP7002_COMMENT, ");
		queryString.append(" mp72.MP7002_CREATOR, ");
		queryString.append(" mp72.MP7002_STATUS, ");
		queryString.append(" mp72.MP7002_EXECUTE_STATUS, ");
		queryString.append(" mp72.MP7002_FINISH_STATUS, ");
		queryString.append(" mp72.MP7002_TYPE ");

		queryString.append(" from MP7002 mp72 ");
		queryString.append(" where 1=1 ");
		//queryString.append(" and mp72.MP7002_STATUS = '1' ");
		
		return queryString;
	}
	// 检索数据
	@SuppressWarnings("unchecked")
	private List<MP7002> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
		Session session = getHibernateTemplate().getSessionFactory().openSession();		
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		List<Object[]> list = query.list();
		session.close();
		
		List<MP7002> retList = getDataList(list);
		
		return retList;
	}
	// 解析数据
	private List<MP7002> getDataList(List<Object[]> list){
		MP7002 mp7002 = new MP7002();
		List<MP7002> retList = new ArrayList<MP7002>();
		
		for(int i=0,j=list.size(); i<j; i++){
			mp7002 = new MP7002();
			Object[] obj = list.get(i);
			
			if(obj[0] == null){
				continue;
			}else{
				mp7002.setMP7002_SEQ(obj[0].toString());
			}
			mp7002.setMP7002_TITLE(obj[1] == null ? "" : obj[1].toString());
			mp7002.setMP7002_YEAR(obj[2] == null ? "" : obj[2].toString());
			mp7002.setMP7002_MONTH(obj[3] == null ? "" : obj[3].toString());
			mp7002.setMP7002_COMMENT(obj[4] == null ? "" : obj[4].toString());
			mp7002.setMP7002_CREATOR(obj[5] == null ? "" : obj[5].toString());
			mp7002.setMP7002_STATUS(obj[6] == null ? "" : obj[6].toString());
			mp7002.setMP7002_EXECUTE_STATUS(obj[7] == null ? "" : obj[7].toString());
			mp7002.setMP7002_FINISH_STATUS(obj[8] == null ? "" : obj[8].toString());
			mp7002.setMP7002_TYPE(obj[9] == null ? "" : obj[9].toString());
			
			// 状态
			if(mp7002.getMP7002_STATUS().equals("1")){
				mp7002.setMP7002_STATUS_NAME(Constant.VALID);
			}else{
				mp7002.setMP7002_STATUS_NAME(Constant.INVALID);
			}
			// 计划类型
			if(mp7002.getMP7002_TYPE().equals("1")){
				mp7002.setMP7002_TYPE_NAME("Yearly");
			}else if(mp7002.getMP7002_TYPE().equals("2")){
				mp7002.setMP7002_TYPE_NAME("Monthly");
			}else{
				mp7002.setMP7002_TYPE_NAME("-");
			}
			// 备注
			if(mp7002.getMP7002_COMMENT() != null && !mp7002.getMP7002_COMMENT().equals("")){
				if(mp7002.getMP7002_COMMENT().length() > 200){
					mp7002.setMP7002_COMMENT2(mp7002.getMP7002_COMMENT().substring(0, 180) + "......");
				}else{
					mp7002.setMP7002_COMMENT2(mp7002.getMP7002_COMMENT());
				}
			}else{
				mp7002.setMP7002_COMMENT2(" ");
			}
			
			retList.add(mp7002);
		}
		return retList;
	}
}
