package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import entity.FingerSiteUserIdInfo;

public class FingerSiteUserIdInfoDAO implements IFingerSiteUserIdInfoDAO{
	SessionFactory sessionFactory;
	
	public void delete(FingerSiteUserIdInfo item) {
		sessionFactory.getCurrentSession().delete(item);
	}

	public List<FingerSiteUserIdInfo> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from FingerSiteUserIdInfo").list();
	}

	public FingerSiteUserIdInfo findById(int id) {
		return (FingerSiteUserIdInfo)sessionFactory.getCurrentSession().get(FingerSiteUserIdInfo.class, id);
	}

	public void save(FingerSiteUserIdInfo item) {
		sessionFactory.getCurrentSession().save(item);
	}

	public void update(FingerSiteUserIdInfo item) {
		sessionFactory.getCurrentSession().update(item);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
