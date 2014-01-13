package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.MP7003;

public class MP7003DAO extends HibernateDaoSupport implements IMP7003DAO{
	// 保存数据
	public void save(MP7003 mp7003) {
		if(mp7003 != null){
			getHibernateTemplate().save(mp7003);
		}
	}
	// 删除数据
	public void delete(MP7003 mp7003) {
		if(mp7003 != null){
			getHibernateTemplate().delete(mp7003);
		}
	}
	// 根据KEY检索数据
	public MP7003 findById(String key) {
		return (MP7003)getHibernateTemplate().get("entity.MP7003", key);
	}
	// 取得所有有效数据
	@SuppressWarnings("unchecked")
	public List<MP7003> findAll() {
		return getHibernateTemplate().find(" from MP7003 where 1=1 and MP7003_STATUS = '1' ");
	}
	// 更新数据
	public void update(MP7003 mp7003) {
		if(mp7003 != null){
			getHibernateTemplate().update(mp7003);
		}
	}
	// 动态根据传入的参数，检索数据
	public List<MP7003> findByProperty(String name, String value) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		queryString.append(" and mp73." + name + "='" + value + "' ");
		
		List<MP7003> retList = executeSqlStatement(queryString,-1,-1);
		
		return retList;
	}
	// 分页方法
	public List<MP7003> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		if(propertyMap.containsKey("MASTER_KEY") && propertyMap.get("MASTER_KEY") != null && !propertyMap.get("MASTER_KEY").equals("")){
			queryString.append(" and mp73.MP7003_MASTER_KEY = '" + propertyMap.get("MASTER_KEY") + "' ");
		}
		
		List<MP7003> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);
		
		return retList;
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		List<MP7003> retList = findByPropertyByPage(propertyMap,-1,-1);
		return retList.size();
	}
	// 自定义查询条件及排序方式
	public List<MP7003> findDataBySelfDefined(String search,String order, int pageNum, int pageCount){
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		queryString.append(search);
		// 排序方式
		queryString.append(order);
		
		List<MP7003> retList = executeSqlStatement(queryString, pageNum, pageCount);
		return retList;
	}
	
	// 构建SQL语句
	private StringBuffer createSqlStatement(){
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" select ");
		queryString.append(" mp73.MP7003_SEQ, ");
		queryString.append(" mp73.MP7003_MASTER_KEY, ");
		queryString.append(" mp73.MP7003_EXAM_CODE, ");
		queryString.append(" mp71.MP7001_TITLE, ");
		queryString.append(" mp73.MP7003_WEIGHTAGE ");

		queryString.append(" from MP7003 mp73, MP7001 mp71 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and mp73.MP7003_EXAM_CODE = mp71.MP7001_SEQ ");
		
		return queryString;
	}
	// 检索数据
	@SuppressWarnings("unchecked")
	private List<MP7003> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
		Session session = getHibernateTemplate().getSessionFactory().openSession();		
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		List<Object[]> list = query.list();
		session.close();
		
		List<MP7003> retList = getDataList(list);
		
		return retList;
	}
	// 解析数据
	private List<MP7003> getDataList(List<Object[]> list){
		MP7003 mp7003 = new MP7003();
		List<MP7003> retList = new ArrayList<MP7003>();
		
		for(int i=0,j=list.size(); i<j; i++){
			mp7003 = new MP7003();
			Object[] obj = list.get(i);
			
			if(obj[0] == null){
				continue;
			}else{
				mp7003.setMP7003_SEQ(obj[0].toString());
			}
			mp7003.setMP7003_MASTER_KEY(obj[1] == null ? "" : obj[1].toString());
			mp7003.setMP7003_EXAM_CODE(obj[2] == null ? "" : obj[2].toString());
			mp7003.setMP7003_EXAM_TITLE(obj[3] == null ? "" : obj[3].toString());
			mp7003.setMP7003_WEIGHTAGE(obj[4] == null ? "" : obj[4].toString());
			
			retList.add(mp7003);
		}
		return retList;
	}
}
