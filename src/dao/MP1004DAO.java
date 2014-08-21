package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.MP1004;

public class MP1004DAO  implements IMP1004DAO {
	private SessionFactory sessionFactory;
	
	public void save(MP1004 mp1004) {
		if (mp1004 != null) {
			sessionFactory.getCurrentSession().save(mp1004);
		}
	}

	public void delete(MP1004 mp1004) {
		sessionFactory.getCurrentSession().delete(mp1004);
	}

	public MP1004 findById(String employeeNum) {
		return (MP1004) sessionFactory.getCurrentSession().get("entity.MP1004", employeeNum);
	}

	@SuppressWarnings("unchecked")
	public List<MP1004> findAll() {
//		return getHibernateTemplate().find("from MP1004");
		return sessionFactory.getCurrentSession().createQuery("from MP1004").list();
	}

	public List<MP1004> findAll2(String employeeNum, String employeeName) {
		StringBuffer queryString = new StringBuffer();
		queryString = createSqlStatement();

		if(employeeNum != null && !employeeNum.equals("")){
			queryString.append(" and mp14.MP1004_EMPLOYEE_NUM = '" + employeeNum + "' ");
		}
		if(employeeName != null && !employeeName.equals("")){
			queryString.append(" and mp11.MP1001_PREFERED_NAME like '%" + employeeName + "%' ");
		}
		
		queryString.append(" order by mp14.MP1004_EMPLOYEE_NUM, mp14.MP1004_TRANSFER_DATETIME ");
		
		List<MP1004> retList = executeSqlStatement(queryString,-1,-1);
		return retList;
	}
	
	public void update(MP1004 mp1004) {
		sessionFactory.getCurrentSession().update(mp1004);
	}

	// 构建SQL语句
	private StringBuffer createSqlStatement(){
		StringBuffer queryString = new StringBuffer();
		
		queryString.append(" select ");
		queryString.append(" mp14.MP1004_SEQ, ");
		queryString.append(" mp14.MP1004_EMPLOYEE_NUM, ");
		queryString.append(" mp14.MP1004_DEPARTMENT_CODE, ");
		queryString.append(" mp14.MP1004_POSITION, ");
		queryString.append(" mp14.MP1004_TRANSFER_DATETIME, ");
		queryString.append(" mp14.MP1004_JOB_DESC, ");
		queryString.append(" mp14.MP1004_CREATE_USER, ");
		queryString.append(" mp14.MP1004_CREATE_DATETIME, ");
		queryString.append(" mp14.MP1004_EDIT_USER, ");
		queryString.append(" mp14.MP1004_EDIT_DATETIME, ");
		queryString.append(" mp11.MP1001_PREFERED_NAME, ");
		queryString.append(" mp02.MP0002_DEPARTMENT_NAME, ");
		queryString.append(" mp09.MP0009_POSITION_NAME_E, ");
		queryString.append(" mp14.MP1004_DEPARTMENT_CODE_OLD, ");
		queryString.append(" mp14.MP1004_POSITION_OLD,  ");
		queryString.append(" mp021.MP0002_DEPARTMENT_NAME, ");
		queryString.append(" mp091.MP0009_POSITION_NAME_E  ");
		queryString.append(" from MP1004 mp14, MP1001 mp11, MP0002 mp02, MP0009 mp09, MP0002 mp021, MP0009 mp091 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and mp14.MP1004_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM ");
		queryString.append(" and mp14.MP1004_DEPARTMENT_CODE = mp02.MP0002_SEQ ");
		queryString.append(" and mp14.MP1004_POSITION = mp09. MP0009_SEQ ");
		queryString.append(" and mp14.MP1004_DEPARTMENT_CODE_OLD = mp021.MP0002_SEQ ");
		queryString.append(" and mp14.MP1004_POSITION_OLD = mp091. MP0009_SEQ ");
		
		return queryString;
	}
	// 检索数据
	@SuppressWarnings("unchecked")
	private List<MP1004> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
//		Session session = sessionFactory.getCurrentSession();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(queryString.toString());
		if( PAGE_NUM > 0 && PAGE_COUNT > 0){
			query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
			query.setMaxResults(PAGE_COUNT);
		}
		List<Object[]> list = query.list();
		// session.close();
		
		List<MP1004> retList = getDataList(list);
		
		return retList;
	}
	// 解析数据
	private List<MP1004> getDataList(List<Object[]> list){	
		MP1004 mp14 = new MP1004();
		List<MP1004> retList = new ArrayList<MP1004>();
		for(int i=0,j=list.size();i<j;i++){
			mp14 = new MP1004();
			Object[] obj = list.get(i);
			
			mp14.setMP1004_SEQ(Integer.parseInt(obj[0].toString()));// Sequence
			mp14.setMP1004_EMPLOYEE_NUM(obj[1] == null ? "" : obj[1].toString());// Employee Number
			mp14.setMP1004_DEPARTMENT_CODE(obj[2] == null ? "" : obj[2].toString());// Department Code
			mp14.setMP1004_POSITION(obj[3] == null ? "" : obj[3].toString()); // Position
			mp14.setMP1004_TRANSFER_DATETIME(obj[4] == null ? "" : obj[4].toString().substring(0,10)); // Transfers Time
			mp14.setMP1004_JOB_DESC(obj[5] == null ? "" : obj[5].toString()); // Job Desc
			mp14.setMP1004_CREATE_USER(obj[6] == null ? "" : obj[6].toString()); // Create User
			mp14.setMP1004_CREATE_DATETIME(obj[7] == null ? "" : obj[7].toString()); // Create Time
			mp14.setMP1004_EDIT_USER(obj[8] == null ? "" : obj[8].toString()); // Edit User
			mp14.setMP1004_EDIT_DATETIME(obj[9] == null ? "" : obj[9].toString());// Edit Time
			mp14.setMP1004_EMPLOYEE_NAME(obj[10] == null ? "" : obj[10].toString());// Employee Name
			mp14.setMP1004_DEPARTMENT_NAME(obj[11] == null ? "" : obj[11].toString());// Department Name
			mp14.setMP1004_POSITION_NAME(obj[12] == null ? "" : obj[12].toString());// POSITION NAME
			mp14.setMP1004_DEPARTMENT_CODE_OLD(obj[13] == null ? "" : obj[13].toString());// POSITION old
			mp14.setMP1004_POSITION_OLD(obj[14] == null ? "" : obj[14].toString());// POSITION old
			mp14.setMP1004_DEPARTMENT_NAME_OLD(obj[15] == null ? "" : obj[15].toString());// Department Name
			mp14.setMP1004_POSITION_OLD_NAME(obj[16] == null ? "" : obj[16].toString());// POSITION NAME
			
			retList.add(mp14);
		}
		
		return retList;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}