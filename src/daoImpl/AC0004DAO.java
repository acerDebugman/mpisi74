package daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;

import dao.IAC0004DAO;
import entity.AC0004;

public class AC0004DAO   implements IAC0004DAO {
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	public void save(AC0004 ac0004) {
		if (ac0004 != null) {
			sf.getCurrentSession().save(ac0004);
		}
	}

	public void delete(AC0004 ac0004) {
		sf.getCurrentSession().delete(ac0004);
	}

	public AC0004 findById(int seq) {
		return (AC0004) sf.getCurrentSession().get("entity.AC0004", seq);
	}

	@SuppressWarnings("unchecked")
	public List<AC0004> findAll() {
		return sf.getCurrentSession().createQuery("from AC0004").list();
	}

	public void update(AC0004 ac0004) {
		sf.getCurrentSession().update(ac0004);
	}
	
	@SuppressWarnings("unchecked")
	public List<AC0004> findByProperty(String name, String value){
		try{
			String queryString = " from AC0004 as ac04 where ac04." + name + " = :ac04Name";
			return sf.getCurrentSession().createQuery(queryString).setString("ac04Name", value).list();
		}catch(RuntimeException ex){
			throw ex;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<AC0004> getFunctionOperationData(String functionNum, String operationNum){
		try{
			String queryString = " from AC0004 as ac04 where ac04.AC0004_FUN_NUM = '" + functionNum + "' and ac04.AC0004_OPT_NUM = '" + operationNum +"' ";
			return sf.getCurrentSession().createQuery(queryString).list();
		}catch(RuntimeException ex){
			throw ex;
		}
	}
}