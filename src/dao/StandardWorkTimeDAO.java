package dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import entity.MP0010;
import entity.MP1001;
import entity.StandardWorkTime;

public class StandardWorkTimeDAO implements IStandardWorkTimeDAO {
	SessionFactory sessionFactory;
	
	@Override
	public void save(StandardWorkTime item) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(item);
	}

	@Override
	public void delete(StandardWorkTime item) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(item);
	}

	@Override
	public StandardWorkTime findById(int id) {
		// TODO Auto-generated method stub
		return (StandardWorkTime)sessionFactory.getCurrentSession().get(StandardWorkTime.class, id);
	}

	@Override
	public List<StandardWorkTime> findAll() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("from StandardWorkTime").list();
	}

	@Override
	public void update(StandardWorkTime item) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(item);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<MP0010> fetchAllPatternSpecialDays(StandardWorkTime item){
		return sessionFactory.getCurrentSession()
				.createQuery("select m from MP0010 m where m.workTimePattern=:wtp")
				.setParameter("wtp", item.getCircleDay().getWorkTimePattern())
				.list();
//		List<EachCircleDay> list = sessionFactory.getCurrentSession()
//				.createQuery("select m from MP0010 m, StandardWorkTime s where m.workTimePattern=s.circleDay.workTimePattern and s.circleDay=:swtItem")
//		List<StandardWorkTime> list = sessionFactory.getCurrentSession()
//				.createQuery("from StandardWorkTime")
//				.list();
//		
//		return null;
	}

	public StandardWorkTime fetchSpecificDayWorkTime(MP1001 emp, Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return (StandardWorkTime)sessionFactory.getCurrentSession().createQuery("from StandardWorkTime s where convert(varchar(10), s.dayDate, 120)=:theDate and s.employee=:emp")
				.setParameter("theDate", sdf.format(date))
				.setParameter("emp", emp)
				.uniqueResult();

	}
}
