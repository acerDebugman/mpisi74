package dao;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;

import entity.EmpWorkTimePattern_R;

public class EmpWorkTimePattern_RDAO implements IEmpWorkTimePattern_RDAO {
	SessionFactory sessionFactory;
	
	@Override
	public void save(EmpWorkTimePattern_R item) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(item);
	}

	@Override
	public void delete(EmpWorkTimePattern_R item) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(item);
	}

	@Override
	public EmpWorkTimePattern_R findById(int id) {
		// TODO Auto-generated method stub
		return (EmpWorkTimePattern_R)sessionFactory.getCurrentSession().get(EmpWorkTimePattern_R.class, id);
	}

	@Override
	public List<EmpWorkTimePattern_R> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from EmpWorkTimePattern_R").list();
	}

	@Override
	public void update(EmpWorkTimePattern_R item) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(item);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<EmpWorkTimePattern_R> findByProperties(Map<String, String> map) {
		try{
			// TODOAuto-generated method stub
			StringBuffer sb = new StringBuffer();
			sb.append("from EmpWorkTimePattern_R r where 1=1 ");
			if(map.containsKey("empCode")){
				sb.append(" and r.employee.MP1001_EMPLOYEE_NUM='" + map.get("empCode") + "' ");
			}
			if(map.containsKey("workTimePatternId")){
				sb.append(" and r.workTimePattern.id=" + map.get("workTimePatternId"));
			}
			
			if(map.containsKey("PAGE_NUM") && map.containsKey("PAGE_COUNT")){
				int pageNum = Integer.parseInt(map.get("PAGE_NUM"));
				int pageSize = Integer.parseInt(map.get("PAGE_COUNT"));
				return sessionFactory.getCurrentSession().createQuery(sb.toString())
						.setFirstResult((pageNum - 1) * pageSize)
						.setMaxResults(pageSize)
						.list();
			}
			else{
				return sessionFactory.getCurrentSession().createQuery(sb.toString()).list();
			}
		} catch (Exception ex){
			System.out.println(ex.getMessage());
			return null;
		}
		
	}

	public long findTotalSearchCount(Map<String, String> map){
		try{
			final StringBuffer queryString = new StringBuffer();
			
			queryString.append("select count(*) from EmpWorkTimePattern_R r where 1=1 ");
			
			if(map.containsKey("empCode")){
				queryString.append(" and r.employee.MP1001_EMPLOYEE_NUM='" + map.get("empCode") + "' ");
			}
			if(map.containsKey("workTimePatternId")){
				queryString.append(" and r.workTimePattern.id=" + map.get("workTimePatternId"));
			}
				
//			return (Long)getHibernateTemplate().find(queryString.toString()).iterator().next();
			return (Long)sessionFactory.getCurrentSession().createQuery(queryString.toString()).iterate().next();
		}catch(RuntimeException e){
			throw e;
		}
	}
	
}
