package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.MP7007;

public class MP7007DAO  implements IMP7007DAO{
	private SessionFactory sessionFactory;

	// 保存数据
	public void save(MP7007 mp7007) {
		if(mp7007 != null){
			sessionFactory.getCurrentSession().save(mp7007);
		}
	}
	// 删除数据
	public void delete(MP7007 mp7007) {
		if(mp7007 != null){
			sessionFactory.getCurrentSession().delete(mp7007);
		}
	}
	// 根据KEY检索数据
	public MP7007 findById(String key) {
		return (MP7007)sessionFactory.getCurrentSession().get("entity.MP7007", key);
	}
	// 取得所有有效数据
	@SuppressWarnings("unchecked")
	public List<MP7007> findAll() {
//		return getHibernateTemplate().find(" from MP7007 where 1=1 and MP7007_STATUS = '1' ");
		return sessionFactory.getCurrentSession().createQuery("from MP7007 where 1=1 and MP7007_STATUS = '1' ").list();
	}
	// 更新数据
	public void update(MP7007 mp7007) {
		if(mp7007 != null){
			sessionFactory.getCurrentSession().update(mp7007);
		}
	}
	// 动态根据传入的参数，检索数据
	public List<MP7007> findByProperty(String name, String value) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		queryString.append(" and mp77." + name + "='" + value + "' ");
		
		List<MP7007> retList = executeSqlStatement(queryString,-1,-1);
		
		return retList;
	}
	// 分页方法
	public List<MP7007> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
/*		if(propertyMap.containsKey("EMP_NUM") && propertyMap.get("EMP_NUM") != null && !propertyMap.get("EMP_NUM").equals("")){
			queryString.append(" and mp61.MP6001_EMPLOYEE_NUM = '" + propertyMap.get("EMP_NUM") + "' ");
		}*/
		
		List<MP7007> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);
		
		return retList;
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		List<MP7007> retList = findByPropertyByPage(propertyMap,-1,-1);
		return retList.size();
	}
	// 自定义查询条件及排序方式
	public List<MP7007> findDataBySelfDefined(String search,String order, int pageNum, int pageCount){
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		queryString.append(search);
		// 排序方式
		queryString.append(order);
		
		List<MP7007> retList = executeSqlStatement(queryString, pageNum, pageCount);
		return retList;
	}
	// 构建SQL语句
	private StringBuffer createSqlStatement(){
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" select ");
		queryString.append(" mp77.MP7007_SEQ, ");
		queryString.append(" mp77.MP7007_MASTER_KEY, ");
		queryString.append(" mp77.MP7007_EXAM_CODE, ");
		queryString.append(" mp77.MP7007_SELF_SCORE, ");
		queryString.append(" mp77.MP7007_MANAGER_SCORE, ");
		queryString.append(" mp77.MP7007_AGREED_SCORE, ");
		queryString.append(" mp77.MP7007_REMARKS, ");
		queryString.append(" mp77.MP7007_WEIGHTAGE, ");
		queryString.append(" mp77.MP7007_FINAL_SCORE, ");
		queryString.append(" mp71.MP7001_TITLE, ");
		queryString.append(" mp71.MP7001_SUB_TITLE, ");
		queryString.append(" mp71.MP7001_COMMENT ");
		queryString.append(" from MP7007 mp77, MP7001 mp71 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and mp77.MP7007_EXAM_CODE = mp71.MP7001_SEQ ");
		
		return queryString;
	}
	// 检索数据
	@SuppressWarnings("unchecked")
	private List<MP7007> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
		Session session = sessionFactory.getCurrentSession();		
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		List<Object[]> list = query.list();
		// session.close();
		
		List<MP7007> retList = getDataList(list);
		
		return retList;
	}
	// 解析数据
	private List<MP7007> getDataList(List<Object[]> list){
		MP7007 mp7007 = new MP7007();
		List<MP7007> retList = new ArrayList<MP7007>();
		
		for(int i=0,j=list.size(); i<j; i++){
			mp7007 = new MP7007();
			Object[] obj = list.get(i);
			
			if(obj[0] == null){
				continue;
			}else{
				mp7007.setMP7007_SEQ(obj[0].toString());
			}
			mp7007.setMP7007_MASTER_KEY(obj[1] == null ? "" : obj[1].toString());
			mp7007.setMP7007_EXAM_CODE(obj[2] == null ? "" : obj[2].toString());
			mp7007.setMP7007_SELF_SCORE(obj[3] == null ? "" : obj[3].toString());
			mp7007.setMP7007_MANAGER_SCORE(obj[4] == null ? "" : obj[4].toString());
			mp7007.setMP7007_AGREED_SCORE(obj[5] == null ? "" : obj[5].toString());
			mp7007.setMP7007_REMARKS(obj[6] == null ? "" : obj[6].toString());
			mp7007.setMP7007_WEIGHTAGE(obj[7] == null ? "" : obj[7].toString());
			mp7007.setMP7007_FINAL_SCORE(obj[8] == null ? "" : obj[8].toString());
			mp7007.setMP7007_EXAM_NAME(obj[9] == null ? "" : obj[9].toString());
			mp7007.setMP7007_EXAM_SUB_TITLE(obj[10] == null ? "" : obj[10].toString());
			mp7007.setMP7007_EXAM_CONTENT(obj[11] == null ? "" : obj[11].toString());
			
			retList.add(mp7007);
		}
		return retList;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
