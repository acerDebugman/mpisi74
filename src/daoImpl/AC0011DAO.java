package daoImpl;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.IAC0011DAO;
import entity.AC0011;

public class AC0011DAO   implements IAC0011DAO {
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	public void save(AC0011 ac0011) {
		if (ac0011 != null) {
			sf.getCurrentSession().save(ac0011);
		}
	}

	public void delete(AC0011 ac0011) {
		sf.getCurrentSession().delete(ac0011);
	}

	public AC0011 findById(String seq) {
		return (AC0011) sf.getCurrentSession().get("entity.AC0011", seq);
	}

	@SuppressWarnings("unchecked")
	public List<AC0011> findAll() {
		return sf.getCurrentSession().createQuery("from AC0011").list();
	}

	public void update(AC0011 ac0011) {
		sf.getCurrentSession().update(ac0011);
	}
	
	@SuppressWarnings("unchecked")
	public List<AC0011> findByProperty(String name, String value){
		try{
			String queryString = " from AC0011 as ac11 where ac11." + name + " = '" + value + "'";
			return sf.getCurrentSession().createQuery(queryString).list();
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String,String> functionAccessCheck(String _empNum,String _systemNum){
		HashMap<String,String> functionMap = new HashMap<String,String>();
		Session session = null;
		
		try{
			StringBuffer queryString = new StringBuffer();
			session = sf.getCurrentSession();
			
			queryString.append(" select ac11.AC0011_EMPLOYEE_NUM, ac11.AC0011_FUN_NUM, ac11.AC0011_SYS_NUM ");
			queryString.append(" from AC0011 ac11 ");
			queryString.append(" where 1=1 ");
			queryString.append(" and ac11.AC0011_EMPLOYEE_NUM = '" + _empNum + "' ");
			queryString.append(" and ac11.AC0011_SYS_NUM = '" + _systemNum+ "' ");
			
			Query query = session.createQuery(queryString.toString());
			
			List<Object[]> list = query.list();
			
			for(int i=0,j=list.size();i<j;i++){
				Object[] obj = list.get(i);
				
				String _funNum = (obj[1]==null?"":obj[1].toString());
				
				functionMap.put(_funNum, String.valueOf(i));
			}
			
//			session.close();
		}catch(Exception ex){
			functionMap = new HashMap<String,String>();
		}
//		finally{
//			if(session.isConnected()){
//				session.close();
//			}
//		}
		
		return functionMap;
	}
}
