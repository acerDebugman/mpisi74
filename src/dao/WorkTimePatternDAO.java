package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import entity.WorkTimePattern;

public class WorkTimePatternDAO implements IWorkTimePatternDAO {
	SessionFactory sessionFactory;
	
	@Override
	public void save(WorkTimePattern item) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(item);
	}

	@Override
	public void delete(WorkTimePattern item) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(item);
	}

	@Override
	public WorkTimePattern findById(int id) {
		// TODO Auto-generated method stub
		return (WorkTimePattern)sessionFactory.getCurrentSession().get(WorkTimePattern.class, id);
//		return (WorkTimePattern)sessionFactory.getCurrentSession()
//				.createQuery("from WorkTimePattern w, EachCircleDay e, DetailDayWorkTime d where d.circleDay=e and e.workTimePattern=w and w.id=:wId order by e.daySeq, d.id")
//				.createQuery("from WorkTimePattern w where w.id=:wId ")
//				.setParameter("wId", id)
//				.uniqueResult();
	}
	
	public WorkTimePattern findByName(String wtpName){
		return (WorkTimePattern)sessionFactory.getCurrentSession().createQuery("from WorkTimePattern w where w.name like '%:wtpName%'")
				.setString("wtpName", wtpName)
				.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<WorkTimePattern> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from entity.WorkTimePattern").list();
	}

	@Override
	public void update(WorkTimePattern item) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(item);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
