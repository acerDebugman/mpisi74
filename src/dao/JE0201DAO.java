package dao;

import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.JE0201;

public class JE0201DAO extends HibernateDaoSupport implements IJE0201DAO {

	@Override
	public void save(JE0201 je0201) {
		// TODO Auto-generated method stub
		if(null != je0201){
			getHibernateTemplate().save(je0201);
		}
	}

	@Override
	public void delete(JE0201 je0201) {
		// TODO Auto-generated method stub
		if(null != je0201){
			getHibernateTemplate().delete(je0201);
		}
	}

	@Override
	public JE0201 findByKey(String key) {
		// TODO Auto-generated method stub
		return (JE0201)getHibernateTemplate().get("entity.JE0201", key);
	}

	@Override
	public JE0201 findByValue(String value) {
		// TODO Auto-generated method stub
		//return (JE0201)getHibernateTemplate().get("entity.JE0201", value);
		return (JE0201)getHibernateTemplate().find(" from JE0201 where JE0201_VALUE='" + value + "'");
	}
	
	@Override
	public JE0201 findByType(String type) {
		// TODO Auto-generated method stub
		//return (JE0201)getHibernateTemplate().get("entity.JE0201", value);
		return (JE0201)getHibernateTemplate().find(" from JE0201 where JE0201_VALUE='" + type + "'");
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public List<JE0201> findAll() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from JE0201");
	}

	@Override
	public void update(JE0201 je0201) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(je0201);
	}

	@Override
	public List<JE0201> findByColumnName(Map<String, String> columnMap) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("from JE0201 where 1 = 1 ");
		if(columnMap.containsKey("JE0201_ROOM_NAME") && !columnMap.get("JE0201_ROOM_NAME").equalsIgnoreCase("") && columnMap.get("JE0201_ROOM_NAME")!=null){
			sb.append(" and JE0201_ROOM_NAME='" + columnMap.get("JE0201_ROOM_NAME") + "' ");
		}
		if(columnMap.containsKey("JE0201_ROOM_FLOOR") && !columnMap.get("JE0201_ROOM_FLOOR").equalsIgnoreCase("") && columnMap.get("JE0201_ROOM_FLOOR")!=null){
			sb.append(" and JE0201_ROOM_FLOOR='" + columnMap.get("JE0201_ROOM_FLOOR") + "' ");
		}
		if(columnMap.containsKey("JE0201_ROOM_TYPE") && !columnMap.get("JE0201_ROOM_TYPE").equalsIgnoreCase("") && columnMap.get("JE0201_ROOM_TYPE")!=null){
			sb.append(" and JE0201_ROOM_TYPE='" + columnMap.get("JE0201_ROOM_TYPE") + "' ");
		}
		if(columnMap.containsKey("JE0201_ROOM_DES") && !columnMap.get("JE0201_ROOM_DES").equalsIgnoreCase("") && columnMap.get("JE0201_ROOM_DES")!=null){
			sb.append(" and JE0201_ROOM_DES='" + columnMap.get("JE0201_ROOM_DES") + "' ");
		}
		
		return getHibernateTemplate().find(sb.toString());
	}
		
}
