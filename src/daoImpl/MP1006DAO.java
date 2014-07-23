package daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;

import dao.IMP1006DAO;
import entity.MP1006;

public class MP1006DAO   implements IMP1006DAO {
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	public void save(MP1006 mp1006) {
		if (mp1006 != null) {
			sf.getCurrentSession().save(mp1006);
		}
	}

	public void delete(MP1006 mp1006) {
		sf.getCurrentSession().delete(mp1006);
	}

	public MP1006 findById(String employeeNum) {
		return (MP1006) sf.getCurrentSession().get("entity.MP1006", employeeNum);
	}

	@SuppressWarnings("unchecked")
	public List<MP1006> findAll() {
		return sf.getCurrentSession().createQuery("from MP1006").list();
	}

	public void update(MP1006 mp1006) {
		sf.getCurrentSession().update(mp1006);
	}

}