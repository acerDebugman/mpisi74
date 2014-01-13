package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.MP2004;

public class MP2004DAO extends HibernateDaoSupport implements IMP2004DAO {

	public void save(MP2004 mp2004) {
		if (mp2004 != null) {
			getHibernateTemplate().save(mp2004);
		}
	}

	public void delete(MP2004 mp2004) {
		getHibernateTemplate().delete(mp2004);
	}

	public MP2004 findById(String seq) {
		return (MP2004) getHibernateTemplate().get("entity.MP2004", seq);
	}

	@SuppressWarnings("unchecked")
	public List<MP2004> findAll() {
		return getHibernateTemplate().find("from MP2004 where 1 = 1 ");
	}

	public void update(MP2004 mp2004) {
		getHibernateTemplate().update(mp2004);
	}
	
	@SuppressWarnings("unchecked")
	public List<MP2004> findByProperty(String name, String value) {
		try{
			StringBuffer queryString = new StringBuffer();
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			
			queryString.append(" from MP2004 ");
			queryString.append(" where 1=1 ");
			queryString.append(" and " + name + " = '" + value + "' ");

		    Query query = session.createQuery(queryString.toString());
		    @SuppressWarnings("rawtypes")
			List list = query.list();
		    
		    session.close();
		    
		    list = convertMP2004List(list);
		    
		    return list;
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	
	private List<MP2004> convertMP2004List(List<MP2004> list){
		for(int i=0,j=list.size(); i<j; i++){
			MP2004 mp24 = list.get(i);
			
			if(mp24.getMP2004_TIME() == null || mp24.getMP2004_TIME().equals("")){
				mp24.setMP2004_TIME("0");
			}
			
			int studyHour = Integer.parseInt(mp24.getMP2004_TIME());
			String _desc = studyHour/8 + "D";
			if(studyHour%8 > 0 ){
				_desc += studyHour%8 + "H";
			}
			mp24.setMP2004_TIME_DESC(_desc);
		}
		
		return list;
	}

}
