package dao;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;

import entity.JE0201;

public class JE0201DAO  implements IJE0201DAO {
	private SessionFactory sessionFactory;
	
	@Override
	public void save(JE0201 je0201) {
		// TODO Auto-generated method stub
		if(null != je0201){
			sessionFactory.getCurrentSession().save(je0201);
		}
	}

	@Override
	public void delete(JE0201 je0201) {
		// TODO Auto-generated method stub
		if(null != je0201){
			sessionFactory.getCurrentSession().delete(je0201);
		}
	}

	@Override
	public JE0201 findByKey(String key) {
		// TODO Auto-generated method stub
		return (JE0201)sessionFactory.getCurrentSession().get("entity.JE0201", key);
	}

	@Override
	public JE0201 findByValue(String value) {
		// TODO Auto-generated method stub
		//return (JE0201)sessionFactory.getCurrentSession().get("entity.JE0201", value);
//		return (JE0201)getHibernateTemplate().find(" from JE0201 where JE0201_VALUE='" + value + "'");
		return (JE0201)sessionFactory.getCurrentSession().createQuery(" from JE0201 where JE0201_VALUE='" + value + "'").list();
	}
	
	@Override
	public JE0201 findByType(String type) {
		// TODO Auto-generated method stub
		//return (JE0201)sessionFactory.getCurrentSession().get("entity.JE0201", value);
//		return (JE0201)getHibernateTemplate().find(" from JE0201 where JE0201_VALUE='" + type + "'");
		return (JE0201)sessionFactory.getCurrentSession().createQuery(" from JE0201 where JE0201_VALUE='" + type + "'").list();
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public List<JE0201> findAll() {
		// TODO Auto-generated method stub
//		return getHibernateTemplate().find("from JE0201");
		return sessionFactory.getCurrentSession().createQuery("from JE0201").list();
	}

	@Override
	public void update(JE0201 je0201) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(je0201);
	}

	@Override
	public List<JE0201> findByColumnName(Map<String, String> columnMap) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("from JE0201 where 1 = 1 and JE0201_STATUS='1' ");
		if(null != columnMap && columnMap.get("JE0201_ROOM_NAME")!=null && columnMap.containsKey("JE0201_ROOM_NAME") && !columnMap.get("JE0201_ROOM_NAME").equalsIgnoreCase("") ){
			sb.append(" and JE0201_ROOM_NAME='" + columnMap.get("JE0201_ROOM_NAME") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0201_ROOM_FLOOR")!=null && columnMap.containsKey("JE0201_ROOM_FLOOR") && !columnMap.get("JE0201_ROOM_FLOOR").equalsIgnoreCase("") ){
			sb.append(" and JE0201_ROOM_FLOOR='" + columnMap.get("JE0201_ROOM_FLOOR") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0201_ROOM_TYPE")!=null && columnMap.containsKey("JE0201_ROOM_TYPE") && !columnMap.get("JE0201_ROOM_TYPE").equalsIgnoreCase("") ){
			sb.append(" and JE0201_ROOM_TYPE='" + columnMap.get("JE0201_ROOM_TYPE") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0201_ROOM_DES")!=null && columnMap.containsKey("JE0201_ROOM_DES") && !columnMap.get("JE0201_ROOM_DES").equalsIgnoreCase("") ){
			sb.append(" and JE0201_ROOM_DES='" + columnMap.get("JE0201_ROOM_DES") + "' ");
		}
		
//		return getHibernateTemplate().find(sb.toString());
		return sessionFactory.getCurrentSession().createQuery(sb.toString()).list();
	}

	@Override
	public List<JE0201> findByColumnName(Map<String, String> columnMap, String order) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("from JE0201 where 1 = 1 and JE0201_STATUS='1' ");
		if(null != columnMap && columnMap.get("JE0201_ROOM_NAME")!=null && columnMap.containsKey("JE0201_ROOM_NAME") && !columnMap.get("JE0201_ROOM_NAME").equalsIgnoreCase("") ){
			sb.append(" and JE0201_ROOM_NAME='" + columnMap.get("JE0201_ROOM_NAME") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0201_ROOM_FLOOR")!=null && columnMap.containsKey("JE0201_ROOM_FLOOR") && !columnMap.get("JE0201_ROOM_FLOOR").equalsIgnoreCase("") ){
			sb.append(" and JE0201_ROOM_FLOOR='" + columnMap.get("JE0201_ROOM_FLOOR") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0201_ROOM_TYPE")!=null && columnMap.containsKey("JE0201_ROOM_TYPE") && !columnMap.get("JE0201_ROOM_TYPE").equalsIgnoreCase("") ){
			sb.append(" and JE0201_ROOM_TYPE='" + columnMap.get("JE0201_ROOM_TYPE") + "' ");
		}
		if(null != columnMap && columnMap.get("JE0201_ROOM_DES")!=null && columnMap.containsKey("JE0201_ROOM_DES") && !columnMap.get("JE0201_ROOM_DES").equalsIgnoreCase("") ){
			sb.append(" and JE0201_ROOM_DES='" + columnMap.get("JE0201_ROOM_DES") + "' ");
		}
		
		if(null != order && !order.equalsIgnoreCase("") && !order.equalsIgnoreCase("-1")){
			sb.append(order);
		}
		
//		return getHibernateTemplate().find(sb.toString());
		return sessionFactory.getCurrentSession().createQuery(sb.toString()).list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
