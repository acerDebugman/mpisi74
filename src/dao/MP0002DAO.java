package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.MP0002;

public class MP0002DAO  implements IMP0002DAO{
	private SessionFactory sessionFactory;
	
	public void save(MP0002 mp0002) {
		if (mp0002 != null) {
			sessionFactory.getCurrentSession().save(mp0002);
		}
	}

	public void delete(MP0002 mp0002) {
		sessionFactory.getCurrentSession().delete(mp0002);
	}

	public MP0002 findById(int seq) {
		return (MP0002) sessionFactory.getCurrentSession().get("entity.MP0002", seq);
	}

	@SuppressWarnings("unchecked")
	public List<MP0002> findAll(boolean flag) {
		String sql = "";
		if(flag == true){
			sql = " from MP0002 where 1=1 and MP0002_DEPARTMENT_STATUS = '1' order by MP0002_DEPARTMENT_NAME ";
		}else{
			sql = " from MP0002 where 1=1 order by MP0002_DEPARTMENT_NAME ";
		}
//		return getHibernateTemplate().find(sql);
		return sessionFactory.getCurrentSession().createQuery(sql).list();
	}

	public void update(MP0002 mp0002) {
		sessionFactory.getCurrentSession().update(mp0002);
	}

	public List<MP0002> findByProperty(String name, String value){
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();
		queryString.append(" and mp2." + name + "='" + value + "' ");
		
		List<MP0002> retList = executeSqlStatement(queryString,-1,-1);
		
		return retList;
	}
	// 构建SQL语句
	private StringBuffer createSqlStatement(){
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" select ");
		queryString.append(" mp02.MP0002_SEQ, ");
		queryString.append(" mp02.MP0002_DEPARTMENT_NUM, ");
		queryString.append(" mp02.MP0002_DEPARTMENT_NAME, ");
		queryString.append(" mp02.MP0002_DEPARTMENT_DESC, ");
		queryString.append(" mp02.MP0002_DEPARTMENT_STATUS, ");
		queryString.append(" mp02.MP0002_CREATE_USER, ");
		queryString.append(" mp02.MP0002_CREATE_DATETIME, ");
		queryString.append(" mp02.MP0002_EDIT_USER, ");
		queryString.append(" mp02.MP0002_EDIT_DATETIME ");
		
		queryString.append(" from MP0002 mp02 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and mp02.MP0002_DEPARTMENT_STATUS = '1' ");
		
		return queryString;
	}
	// 检索数据
	@SuppressWarnings("unchecked")
	private List<MP0002> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
//		Session session = sessionFactory.getCurrentSession();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		List<Object[]> list = query.list();
//		// session.close();
		
		List<MP0002> retList = getDataList(list);
		
		return retList;
	}
	// 解析数据
	private List<MP0002> getDataList(List<Object[]> list){
		MP0002 mp0002 = new MP0002();
		List<MP0002> retList = new ArrayList<MP0002>();
		
		for(int i=0,j=list.size(); i<j; i++){
			mp0002 = new MP0002();
			Object[] obj = list.get(i);
			
			if(obj[0] == null){
				continue;
			}else{
				mp0002.setMP0002_SEQ(Integer.parseInt(obj[0].toString()));
			}
			mp0002.setMP0002_DEPARTMENT_NUM(obj[1] == null ? "" : obj[1].toString());
			mp0002.setMP0002_DEPARTMENT_NAME(obj[2] == null ? "" : obj[2].toString());
			mp0002.setMP0002_DEPARTMENT_DESC(obj[3] == null ? "" : obj[3].toString());
			mp0002.setMP0002_DEPARTMENT_STATUS(obj[4] == null ? "" : obj[4].toString());
			mp0002.setMP0002_CREATE_USER(obj[5] == null ? "" : obj[5].toString());
			mp0002.setMP0002_CREATE_DATETIME(obj[6] == null ? "" : obj[6].toString());
			mp0002.setMP0002_EDIT_USER(obj[7] == null ? "" : obj[7].toString());
			mp0002.setMP0002_EDIT_DATETIME(obj[8] == null ? "" : obj[8].toString());
			
			retList.add(mp0002);
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
