package daoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.IMP4001DAO;
import entity.MP4001;

public class MP4001DAO   implements IMP4001DAO{
	private static final Log log = LogFactory.getLog(MP4001DAO.class);
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	// 保存数据
	public void save(MP4001 mp4001) {
		if(mp4001 != null){
			sf.getCurrentSession().save(mp4001);
		}
	}
	// 删除数据
	public void delete(MP4001 mp4001) {
		if(mp4001 != null){
			sf.getCurrentSession().delete(mp4001);
		}
	}
	// 根据KEY检索数据
	public MP4001 findById(String key) {
		//return (PR1007)sf.getCurrentSession().get("entity.PR1007", key);
		StringBuffer queryString = new StringBuffer();

		queryString = createSqlStatement();
		queryString.append(" and mp41.MP4001_NUM='" + key + "' ");
		
		MP4001 mp4001 = new MP4001();

		List<MP4001> retList = executeSqlStatement(queryString,-1,-1);
		if(retList != null && retList.size() > 0){
			mp4001 = retList.get(0);
		}else{
			mp4001 = null;
		}
		
		return mp4001;
	}
	// 取得所有有效数据
	@SuppressWarnings("unchecked")
	public List<MP4001> findAll() {
		return sf.getCurrentSession().createQuery(" from MP4001 where 1=1 order by MP4001_NUM ").list();
	}
	// 更新数据
	public void update(MP4001 mp4001) {
		if(mp4001 != null){
			sf.getCurrentSession().update(mp4001);
		}
	}
	// 动态根据传入的参数，检索数据
	public List<MP4001> findByProperty(String name, String value) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		queryString.append(" and mp41." + name + "='" + value + "' ");
		queryString.append(" order by mp41.MP4001_NUM ");
		
		List<MP4001> retList = executeSqlStatement(queryString,-1,-1);
		
		return retList;
	}
	// 分页方法
	public List<MP4001> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		
		// 此处追加检索条件
		queryString.append(" order by mp41.MP4001_NUM ");
		
		List<MP4001> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);
		
		return retList;
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		List<MP4001> retList = findByPropertyByPage(propertyMap,-1,-1);
		return retList.size();
	}
	// 构建SQL语句
	private StringBuffer createSqlStatement(){
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" select ");
		queryString.append(" mp41.MP4001_NUM, ");
		queryString.append(" mp41.MP4001_NAME, ");
		queryString.append(" mp41.MP4001_PARENT_TYPE, ");
		queryString.append(" mp41.MP4001_STATUS, ");
		queryString.append(" mp41.MP4001_RELATED_DEPARTMENT, ");
		queryString.append(" mp02.MP0002_DEPARTMENT_NAME, ");
		queryString.append(" mp41.MP4001_DES, ");
		queryString.append(" mp41.MP4001_TYPE ");
		queryString.append(" from MP4001 mp41,MP0002 mp02 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and mp41.MP4001_RELATED_DEPARTMENT = mp02.MP0002_SEQ ");
		
		return queryString;
	}
	// 检索数据
	@SuppressWarnings("unchecked")
	private List<MP4001> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
		Session session = sf.getCurrentSession();		
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		List<Object[]> list = query.list();
//		session.close();
		
		List<MP4001> retList = getDataList(list);
		
		return retList;
	}
	// 解析数据
	private List<MP4001> getDataList(List<Object[]> list){
		MP4001 mp4001 = new MP4001();
		List<MP4001> retList = new ArrayList<MP4001>();
		
		for(int i=0,j=list.size(); i<j; i++){
			mp4001 = new MP4001();
			Object[] obj = list.get(i);
			
			mp4001.setMP4001_NUM(obj[0] == null ? "" : obj[0].toString());//
			mp4001.setMP4001_NAME(obj[1] == null ? "" : obj[1].toString());
			mp4001.setMP4001_PARENT_TYPE(obj[2] == null ? "" : obj[2].toString());
			mp4001.setMP4001_STATUS(obj[3] == null ? "" : obj[3].toString());
			mp4001.setMP4001_RELATED_DEPARTMENT(obj[4] == null ? "" : obj[4].toString());	
			mp4001.setMP4001_RELATED_DEPARTMENT_NAME(obj[5] == null ? "" : obj[5].toString());
			mp4001.setMP4001_DES(obj[6] == null ? "" : obj[6].toString());
			mp4001.setMP4001_TYPE(obj[7] == null ? "" : obj[7].toString());
			
			if(mp4001.getMP4001_TYPE().equals("1")){
				mp4001.setMP4001_TYPE_NAME("EXPENSE");
			}else if(mp4001.getMP4001_TYPE().equals("2")){
				mp4001.setMP4001_TYPE_NAME("INCOME");
			}else if(mp4001.getMP4001_TYPE().equals("3")){
				mp4001.setMP4001_TYPE_NAME("COSTS");
			}
			
			retList.add(mp4001);
		}
		log.info("Count:" + retList.size());
		return retList;
	}

}
