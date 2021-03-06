package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.MP0008;

public class MP0008DAO extends HibernateDaoSupport implements IMP0008DAO {
	public void save(MP0008 mp0008) {
		if (mp0008 != null) {
			getHibernateTemplate().save(mp0008);
		}
	}

	public void delete(MP0008 mp0008) {
		getHibernateTemplate().delete(mp0008);
	}

	public MP0008 findById(int seq) {
		return (MP0008) getHibernateTemplate().get("entity.MP0008", seq);
	}

	@SuppressWarnings("unchecked")
	public List<MP0008> findAll() {
		return getHibernateTemplate().find("from MP0008");
	}

	public void update(MP0008 mp0008) {
		getHibernateTemplate().update(mp0008);
	}
	
	@SuppressWarnings("unchecked")
	public List<MP0008> findByProperty(String name, String value){
		try{
			String queryString = " from MP0008 as mp8 where mp8." + name + " = ?";
			return getHibernateTemplate().find(queryString, value);
			
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	
	// 取得部门下的所有职位信息
	@SuppressWarnings("unchecked")
	public List<MP0008> findByDepartmentId(String value){
		try{
			Session session = getHibernateTemplate().getSessionFactory().openSession();

			StringBuffer queryString = new StringBuffer();
			queryString.append("select ");
			queryString.append(" mp8.MP0008_SEQ, ");
			queryString.append(" mp8.MP0008_DEPARTMENT_ID, ");
			queryString.append(" mp8.MP0008_POSITION_ID, ");
			queryString.append(" mp9.MP0009_POSITION_NAME_E, ");
			queryString.append(" mp9.MP0009_POSITION_NAME_C ");
			queryString.append(" from MP0008 mp8,MP0009 mp9 ");
			queryString.append(" where 1=1 ");
			queryString.append(" and mp8.MP0008_POSITION_ID = mp9.MP0009_SEQ");
			queryString.append(" and mp9.MP0009_POSITION_STATUS = '1'");
			queryString.append(" and mp8.MP0008_DEPARTMENT_ID = " + value);
			
			Query query = session.createSQLQuery(queryString.toString());
			List<Object[]> list = query.list();
			session.close();
			
			
			
			MP0008 mp1008 = new MP0008();
			List<MP0008> retList = new ArrayList<MP0008>();
			
			for(int i=0,j=list.size(); i<j; i++){
				mp1008 = new MP0008();
				Object[] obj = list.get(i);

				//SEQ
				if(obj[0] == null){
					continue;
				}else{
					mp1008.setMP0008_SEQ(Integer.parseInt(obj[0].toString()));
				}
				//Department ID
				if(obj[1] == null){
					continue;
				}else{
					mp1008.setMP0008_DEPARTMENT_ID(Integer.parseInt(obj[1].toString()));
				}
				// Position ID
				if(obj[2] == null){
					continue;
				}else{	
					mp1008.setMP0008_POSITION_ID(Integer.parseInt(obj[2].toString()));
				}
				// Position Name
				mp1008.setMP0008_POSITION_NAME_E(obj[3] == null ? "" : obj[3].toString());
				
				retList.add(mp1008);
			}
			
			return retList;
		}catch(RuntimeException ex){
			throw ex;
		}
	}
}
