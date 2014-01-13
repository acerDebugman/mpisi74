package dao;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import entity.MP1007;

public class MP1007DAO extends HibernateDaoSupport implements IMP1007DAO {

	public void save(MP1007 mp1007) {
		if (mp1007 != null) {
			getHibernateTemplate().save(mp1007);
		}
	}

	public void delete(MP1007 mp1007) {
		getHibernateTemplate().delete(mp1007);
	}

	public MP1007 findById(String employeeNum) {
		return (MP1007) getHibernateTemplate().get("entity.MP1007", employeeNum);
	}

	@SuppressWarnings("unchecked")
	public List<MP1007> findAll() {
		return getHibernateTemplate().find("from MP1007");
	}

	public void update(MP1007 mp1007) {
		getHibernateTemplate().update(mp1007);
	}

}