package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import entity.MP1007;

public class MP1007DAO  implements IMP1007DAO {
	private SessionFactory sessionFactory;
	
	public void save(MP1007 mp1007) {
		if (mp1007 != null) {
			sessionFactory.getCurrentSession().save(mp1007);
		}
	}

	public void delete(MP1007 mp1007) {
		sessionFactory.getCurrentSession().delete(mp1007);
	}

	public MP1007 findById(String employeeNum) {
		return (MP1007) sessionFactory.getCurrentSession().get("entity.MP1007", employeeNum);
	}

	@SuppressWarnings("unchecked")
	public List<MP1007> findAll() {
//		return getHibernateTemplate().find("from MP1007");
		return sessionFactory.getCurrentSession().createQuery("from MP1007").list();
	}

	public void update(MP1007 mp1007) {
		sessionFactory.getCurrentSession().update(mp1007);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}