package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import common.Constant;

import entity.MP7001;

public class MP7001DAO extends HibernateDaoSupport implements IMP7001DAO{
	// 保存数据
	public void save(MP7001 mp7001) {
		if(mp7001 != null){
			getHibernateTemplate().save(mp7001);
		}
	}
	// 删除数据
	public void delete(MP7001 mp7001) {
		if(mp7001 != null){
			getHibernateTemplate().delete(mp7001);
		}
	}
	// 根据KEY检索数据
	public MP7001 findById(String key) {
		return (MP7001)getHibernateTemplate().get("entity.MP7001", key);
	}
	// 取得所有有效数据
	@SuppressWarnings("unchecked")
	public List<MP7001> findAll() {
		return getHibernateTemplate().find(" from MP7001 where 1=1 and MP7001_STATUS = '1' ");
	}
	// 更新数据
	public void update(MP7001 mp7001) {
		if(mp7001 != null){
			getHibernateTemplate().update(mp7001);
		}
	}
	// 动态根据传入的参数，检索数据
	public List<MP7001> findByProperty(String name, String value) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		queryString.append(" and mp71." + name + "='" + value + "' ");
		
		List<MP7001> retList = executeSqlStatement(queryString,-1,-1);
		
		return retList;
	}
	// 分页方法
	public List<MP7001> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		if(propertyMap.containsKey("QUESTION_STATUS") && propertyMap.get("QUESTION_STATUS") != null && !propertyMap.get("QUESTION_STATUS").equals("") && !propertyMap.get("QUESTION_STATUS").equals("-1")){
			queryString.append(" and mp71.MP7001_STATUS = '" + propertyMap.get("QUESTION_STATUS") + "' ");
		}else{
			queryString.append(" and mp71.MP7001_STATUS = '1' ");
		}
		if(propertyMap.containsKey("QUESTION_NUM") && propertyMap.get("QUESTION_NUM") != null && !propertyMap.get("QUESTION_NUM").equals("")){
			queryString.append(" and mp71.MP7001_SEQ = '" + propertyMap.get("QUESTION_NUM") + "' ");
		}
		if(propertyMap.containsKey("QUESTION_TITLE") && propertyMap.get("QUESTION_TITLE") != null && !propertyMap.get("QUESTION_TITLE").equals("")){
			queryString.append(" and mp71.MP7001_TITLE like '%" + propertyMap.get("QUESTION_TITLE") + "%' ");
		}
		
		List<MP7001> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);
		
		return retList;
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		List<MP7001> retList = findByPropertyByPage(propertyMap,-1,-1);
		return retList.size();
	}
	// 自定义查询条件及排序方式
	public List<MP7001> findDataBySelfDefined(String search,String order, int pageNum, int pageCount){
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		queryString.append(search);
		// 排序方式
		queryString.append(order);
		
		List<MP7001> retList = executeSqlStatement(queryString, pageNum, pageCount);
		return retList;
	}
	
	// 构建SQL语句
	private StringBuffer createSqlStatement(){
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" select ");
		queryString.append(" mp71.MP7001_SEQ, ");
		queryString.append(" mp71.MP7001_TITLE, ");
		queryString.append(" mp71.MP7001_SUB_TITLE, ");
		queryString.append(" mp71.MP7001_COMMENT, ");
		queryString.append(" mp71.MP7001_STATUS, ");
		queryString.append(" mp71.MP7001_CREATOR, ");
		queryString.append(" mp71.MP7001_CREATE_TIME, ");
		queryString.append(" mp71.MP7001_EDITOR, ");
		queryString.append(" mp71.MP7001_EDIT_TIME ");

		queryString.append(" from MP7001 mp71 ");
		queryString.append(" where 1=1 ");
		//queryString.append(" and mp71.MP7001_STATUS = '1' ");
		
		return queryString;
	}
	// 检索数据
	@SuppressWarnings("unchecked")
	private List<MP7001> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
		Session session = getHibernateTemplate().getSessionFactory().openSession();		
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		List<Object[]> list = query.list();
		session.close();
		
		List<MP7001> retList = getDataList(list);
		
		return retList;
	}
	// 解析数据
	private List<MP7001> getDataList(List<Object[]> list){
		MP7001 mp7001 = new MP7001();
		List<MP7001> retList = new ArrayList<MP7001>();
		
		for(int i=0,j=list.size(); i<j; i++){
			mp7001 = new MP7001();
			Object[] obj = list.get(i);
			
			if(obj[0] == null){
				continue;
			}else{
				mp7001.setMP7001_SEQ(obj[0].toString());
			}
			mp7001.setMP7001_TITLE(obj[1] == null ? "" : obj[1].toString());
			mp7001.setMP7001_SUB_TITLE(obj[2] == null ? "" : obj[2].toString());
			mp7001.setMP7001_COMMENT(obj[3] == null ? "" : obj[3].toString());
			mp7001.setMP7001_STATUS(obj[4] == null ? "" : obj[4].toString());
			mp7001.setMP7001_CREATOR(obj[5] == null ? "" : obj[5].toString());
			mp7001.setMP7001_CREATE_TIME(obj[6] == null ? "" : obj[6].toString());
			mp7001.setMP7001_EDITOR(obj[7] == null ? "" : obj[7].toString());
			mp7001.setMP7001_EDIT_TIME(obj[8] == null ? "" : obj[8].toString());
			
			// 状态
			if(mp7001.getMP7001_STATUS().equals("1")){
				mp7001.setMP7001_STATUS_NAME(Constant.VALID);
			}else{
				mp7001.setMP7001_STATUS_NAME(Constant.INVALID);
			}
			// 一级标题
			if(mp7001.getMP7001_TITLE() != null && !mp7001.getMP7001_TITLE().equals("")){
				if(mp7001.getMP7001_TITLE().length() > 200){
					mp7001.setMP7001_TITLE2(mp7001.getMP7001_TITLE().substring(0, 180) + "......");
				}else{
					mp7001.setMP7001_TITLE2(mp7001.getMP7001_TITLE());
				}
			}
			// 二级标题
			if(mp7001.getMP7001_SUB_TITLE() != null && !mp7001.getMP7001_SUB_TITLE().equals("")){
				if(mp7001.getMP7001_SUB_TITLE().length() > 200){
					mp7001.setMP7001_SUB_TITLE2(mp7001.getMP7001_SUB_TITLE().substring(0, 180) + "......");
				}else{
					mp7001.setMP7001_SUB_TITLE2(mp7001.getMP7001_SUB_TITLE());
				}
			}
			// 备注
			if(mp7001.getMP7001_COMMENT() != null && !mp7001.getMP7001_COMMENT().equals("")){
				if(mp7001.getMP7001_COMMENT().length() > 200){
					mp7001.setMP7001_COMMENT2(mp7001.getMP7001_COMMENT().substring(0, 180) + "......");
				}else{
					mp7001.setMP7001_COMMENT2(mp7001.getMP7001_COMMENT());
				}
			}
			
			retList.add(mp7001);
		}
		return retList;
	}
}
