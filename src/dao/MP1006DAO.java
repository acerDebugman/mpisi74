package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import entity.MP1006;

public class MP1006DAO  implements IMP1006DAO {
	private SessionFactory sessionFactory;
	
	public void save(MP1006 mp1006) {
		if (mp1006 != null) {
			sessionFactory.getCurrentSession().save(mp1006);
		}
	}

	public void delete(MP1006 mp1006) {
		sessionFactory.getCurrentSession().delete(mp1006);
	}

	public MP1006 findById(String employeeNum) {
		return (MP1006) sessionFactory.getCurrentSession().get("entity.MP1006", employeeNum);
	}

	@SuppressWarnings("unchecked")
	public List<MP1006> findAll() {
//		return getHibernateTemplate().find("from MP1006");
		return sessionFactory.getCurrentSession().createQuery("from MP1006").list();
	}

	public void update(MP1006 mp1006) {
		sessionFactory.getCurrentSession().update(mp1006);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}