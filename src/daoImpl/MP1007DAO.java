package daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;

import dao.IMP1007DAO;
import entity.MP1007;

public class MP1007DAO   implements IMP1007DAO {
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	public void save(MP1007 mp1007) {
		if (mp1007 != null) {
			sf.getCurrentSession().save(mp1007);
		}
	}

	public void delete(MP1007 mp1007) {
		sf.getCurrentSession().delete(mp1007);
	}

	public MP1007 findById(String employeeNum) {
		return (MP1007) sf.getCurrentSession().get("entity.MP1007", employeeNum);
	}

	@SuppressWarnings("unchecked")
	public List<MP1007> findAll() {
		return sf.getCurrentSession().createQuery("from MP1007").list();
	}

	public void update(MP1007 mp1007) {
		sf.getCurrentSession().update(mp1007);
	}

}