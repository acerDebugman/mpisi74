package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.MP4002;

public class MP4002DAO  implements IMP4002DAO{
	private static final Log log = LogFactory.getLog(MP4002DAO.class);
	private SessionFactory sessionFactory;
	
	// 保存数据
	public void save(MP4002 mp4002) {
		if(mp4002 != null){
			sessionFactory.getCurrentSession().save(mp4002);
		}
	}
	// 删除数据
	public void delete(MP4002 mp4002) {
		if(mp4002 != null){
			sessionFactory.getCurrentSession().delete(mp4002);
		}
	}
	// 根据KEY检索数据
	public MP4002 findById(String key) {
		//return (PR1007)sessionFactory.getCurrentSession().get("entity.PR1007", key);
		StringBuffer queryString = new StringBuffer();

		queryString = createSqlStatement();
		queryString.append(" and mp42.MP4002_NUM='" + key + "' ");
		
		MP4002 mp4002 = new MP4002();

		List<MP4002> retList = executeSqlStatement(queryString,-1,-1);
		if(retList != null && retList.size() > 0){
			mp4002 = retList.get(0);
		}else{
			mp4002 = null;
		}
		
		return mp4002;
	}
	// 取得所有有效数据
	@SuppressWarnings("unchecked")
	public List<MP4002> findAll() {
//		return getHibernateTemplate().find(" from MP4002 where 1=1 order by MP4002_NUM ");
		return sessionFactory.getCurrentSession().createQuery("from MP4002 where 1=1 order by MP4002_NUM ").list();
	}
	// 更新数据
	public void update(MP4002 mp4002) {
		if(mp4002 != null){
			sessionFactory.getCurrentSession().update(mp4002);
		}
	}
	// 动态根据传入的参数，检索数据
	public List<MP4002> findByProperty(String name, String value) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		queryString.append(" and mp42." + name + "='" + value + "' ");
		queryString.append(" order by mp42.MP4002_NUM ");
		
		List<MP4002> retList = executeSqlStatement(queryString,-1,-1);
		
		return retList;
	}
	// 分页方法
	public List<MP4002> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		queryString.append(" order by mp42.MP4002_NUM ");
		
		List<MP4002> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);
		
		return retList;
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		List<MP4002> retList = findByPropertyByPage(propertyMap,-1,-1);
		return retList.size();
	}
	// 构建SQL语句
	private StringBuffer createSqlStatement(){
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" select ");
		queryString.append(" mp42.MP4002_NUM, ");
		queryString.append(" mp42.MP4002_NAME_E, ");
		queryString.append(" mp42.MP4002_NAME_C, ");
		queryString.append(" mp42.MP4002_STATUS ");
		queryString.append(" from MP4002 mp42 ");
		queryString.append(" where 1=1 ");
		
		return queryString;
	}
	// 检索数据
	@SuppressWarnings("unchecked")
	private List<MP4002> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
		Session session = sessionFactory.getCurrentSession();		
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		List<Object[]> list = query.list();
		// session.close();
		
		List<MP4002> retList = getDataList(list);
		
		return retList;
	}
	// 解析数据
	private List<MP4002> getDataList(List<Object[]> list){
		MP4002 mp4002 = new MP4002();
		List<MP4002> retList = new ArrayList<MP4002>();
		
		for(int i=0,j=list.size(); i<j; i++){
			mp4002 = new MP4002();
			Object[] obj = list.get(i);
			
			mp4002.setMP4002_NUM(obj[0] == null ? "" : obj[0].toString());//
			mp4002.setMP4002_NAME_E(obj[1] == null ? "" : obj[1].toString());
			mp4002.setMP4002_NAME_C(obj[2] == null ? "" : obj[2].toString());
			mp4002.setMP4002_STATUS(obj[3] == null ? "" : obj[3].toString());			
			
			retList.add(mp4002);
		}
		log.info("Count:" + retList.size());
		return retList;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
