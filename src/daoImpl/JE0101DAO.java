package daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;

import dao.IJE0101DAO;
import entity.JE0101;

public class JE0101DAO   implements IJE0101DAO {
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public void save(JE0101 je0101) {
		// TODO Auto-generated method stub
		if(null != je0101){
			sf.getCurrentSession().save(je0101);
		}
	}

	@Override
	public void delete(JE0101 je0101) {
		// TODO Auto-generated method stub
		if(null != je0101){
			sf.getCurrentSession().delete(je0101);
		}
	}

	@Override
	public JE0101 findByKey(String key) {
		// TODO Auto-generated method stub
		return (JE0101)sf.getCurrentSession().get("entity.JE0101", key);
	}

	@Override
	public JE0101 findByValue(String value) {
		// TODO Auto-generated method stub
		//return (JE0101)sf.getCurrentSession().get("entity.JE0101", value);
		return (JE0101)sf.getCurrentSession().createQuery(" from JE0101 where JE0101_VALUE='" + value + "'").uniqueResult();
	}
	
	@Override
	public JE0101 findByType(String type) {
		// TODO Auto-generated method stub
		//return (JE0101)sf.getCurrentSession().get("entity.JE0101", value);
		return (JE0101)sf.getCurrentSession().createQuery(" from JE0101 where JE0101_VALUE='" + type + "'").uniqueResult();
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public List<JE0101> findAll() {
		// TODO Auto-generated method stub
		return sf.getCurrentSession().createQuery("from JE0101").list();
	}

	@Override
	public void update(JE0101 je0101) {
		// TODO Auto-generated method stub
		sf.getCurrentSession().update(je0101);
	}

}
