package daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.IMP0008DAO;
import entity.MP0008;

public class MP0008DAO   implements IMP0008DAO {
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	public void save(MP0008 mp0008) {
		if (mp0008 != null) {
			sf.getCurrentSession().save(mp0008);
		}
	}

	public void delete(MP0008 mp0008) {
		sf.getCurrentSession().delete(mp0008);
	}

	public MP0008 findById(int seq) {
		return (MP0008) sf.getCurrentSession().get("entity.MP0008", seq);
	}

	@SuppressWarnings("unchecked")
	public List<MP0008> findAll() {
		return sf.getCurrentSession().createQuery("from MP0008").list();
	}

	public void update(MP0008 mp0008) {
		sf.getCurrentSession().update(mp0008);
	}
	
	@SuppressWarnings("unchecked")
	public List<MP0008> findByProperty(String name, String value){
		try{
			String queryString = " from MP0008 as mp8 where mp8." + name + " = '" + value + "'";
			return sf.getCurrentSession().createQuery(queryString).list();
			
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	
	// 取得部门下的所有职位信息
	@SuppressWarnings("unchecked")
	public List<MP0008> findByDepartmentId(String value){
		try{
			Session session = sf.getCurrentSession();

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
//			session.close();
			
			
			
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
