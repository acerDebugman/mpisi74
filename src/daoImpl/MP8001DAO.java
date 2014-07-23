package daoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import common.Constant;

import dao.IMP8001DAO;
import entity.MP8001;

public class MP8001DAO   implements IMP8001DAO{
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
    // 保存数据
    public void save(MP8001 mp8001) {
        if(mp8001 != null){
            sf.getCurrentSession().save(mp8001);
        }
    }
    // 删除数据
    public void delete(MP8001 mp8001) {
        if(mp8001 != null){
            sf.getCurrentSession().delete(mp8001);
        }
    }
    // 根据KEY检索数据
    public MP8001 findById(String key) {
        	return (MP8001)sf.getCurrentSession().get("entity.MP8001", key);
    }
    // 取得所有有效数据
    @SuppressWarnings("unchecked")
    public List<MP8001> findAll() {
        return sf.getCurrentSession().createQuery(" from MP8001 where 1=1  ").list();
    }
    // 更新数据
    public void update(MP8001 mp8001) {
        if(mp8001 != null){
            sf.getCurrentSession().update(mp8001);
        }
    }
    // 更新数据
    public void executeStatement(String statement){
        Session session = sf.getCurrentSession();
        Query query = session.createQuery(statement);
        query.executeUpdate();
//        session.close();
    }
    // 动态根据传入的参数，检索数据
    public List<MP8001> findByProperty(String name, String value) {
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();
        queryString.append(" and mp8001." + name + "='" + value + "' ");

        List<MP8001> retList = executeSqlStatement(queryString,-1,-1);

        return retList;
    }
    // 分页方法
    public List<MP8001> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT) {
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();

		// 此处追加检索条件
		if(propertyMap.containsKey("QUESTION_STATUS") && propertyMap.get("QUESTION_STATUS") != null && !propertyMap.get("QUESTION_STATUS").equals("") && !propertyMap.get("QUESTION_STATUS").equals("-1")){
			queryString.append(" and mp8001.MP8001_STATUS = '" + propertyMap.get("QUESTION_STATUS") + "' ");
		}else{
			queryString.append(" and mp8001.MP8001_STATUS = '1' ");
		}
		if(propertyMap.containsKey("QUESTION_NUM") && propertyMap.get("QUESTION_NUM") != null && !propertyMap.get("QUESTION_NUM").equals("")){
			queryString.append(" and mp8001.MP8001_SEQ = '" + propertyMap.get("QUESTION_NUM") + "' ");
		}
		if(propertyMap.containsKey("QUESTION_TITLE") && propertyMap.get("QUESTION_TITLE") != null && !propertyMap.get("QUESTION_TITLE").equals("")){
			queryString.append(" and mp8001.MP8001_TITLE like '%" + propertyMap.get("QUESTION_TITLE") + "%' ");
		}

        List<MP8001> retList = executeSqlStatement(queryString,PAGE_NUM,PAGE_COUNT);

        return retList;
    }
    // 分页方法，取得数据总条数
    public int getRowCountByPage(Map<String, String> propertyMap) {
        List<MP8001> retList = findByPropertyByPage(propertyMap,-1,-1);
        return retList.size();
    }
    // 自定义查询条件及排序方式
    public List<MP8001> findDataBySelfDefined(String search,String order, int pageNum, int pageCount){
        StringBuffer queryString = new StringBuffer();
        queryString = createSqlStatement();

        // 此处追加检索条件
        queryString.append(search);
        // 排序方式
        queryString.append(order);

        List<MP8001> retList = executeSqlStatement(queryString, pageNum, pageCount);
        return retList;
    }
    // 构建SQL语句
    private StringBuffer createSqlStatement(){
        StringBuffer queryString = new StringBuffer();

        queryString.append(" select ");
        queryString.append(" mp8001.MP8001_SEQ, ");
        queryString.append(" mp8001.MP8001_TITLE, ");
        queryString.append(" mp8001.MP8001_SUB_TITLE, ");
        queryString.append(" mp8001.MP8001_COMMENT, ");
        queryString.append(" mp8001.MP8001_STATUS, ");
        queryString.append(" mp8001.MP8001_CREATOR, ");
        queryString.append(" mp8001.MP8001_CREATE_TIME, ");
        queryString.append(" mp8001.MP8001_EDITOR, ");
        queryString.append(" mp8001.MP8001_EDIT_TIME ");
        queryString.append(" from MP8001 mp8001 ");
        queryString.append(" where 1=1 ");

        return queryString;
    }
    // 检索数据
    @SuppressWarnings("unchecked")
    private List<MP8001> executeSqlStatement(StringBuffer queryString,int PAGE_NUM, int PAGE_COUNT){
        Session session = sf.getCurrentSession();
        Query query = session.createQuery(queryString.toString());
        if( PAGE_NUM > 0 && PAGE_COUNT > 0){
            query.setFirstResult((PAGE_NUM -1)*PAGE_COUNT);
            query.setMaxResults(PAGE_COUNT);
        }
        List<Object[]> list = query.list();
//        session.close();

        List<MP8001> retList = getDataList(list);

        return retList;
    }
    // 解析数据
    private List<MP8001> getDataList(List<Object[]> list){
        MP8001 mp8001 = new MP8001();
        List<MP8001> retList = new ArrayList<MP8001>();

        for(int i=0,j=list.size(); i<j; i++){
            mp8001 = new MP8001();
            Object[] obj = list.get(i);

			if(obj[0] == null){
				continue;
			}else{
				mp8001.setMP8001_SEQ(obj[0].toString());
			}
            mp8001.setMP8001_TITLE(obj[1] == null ? "" : obj[1].toString());
            mp8001.setMP8001_SUB_TITLE(obj[2] == null ? "" : obj[2].toString());
            mp8001.setMP8001_COMMENT(obj[3] == null ? "" : obj[3].toString());
            mp8001.setMP8001_STATUS(obj[4] == null ? "" : obj[4].toString());
            mp8001.setMP8001_CREATOR(obj[5] == null ? "" : obj[5].toString());
            mp8001.setMP8001_CREATE_TIME(obj[6] == null ? "" : obj[6].toString());
            mp8001.setMP8001_EDITOR(obj[7] == null ? "" : obj[7].toString());
            mp8001.setMP8001_EDIT_TIME(obj[8] == null ? "" : obj[8].toString());
            
			// 状态
			if(mp8001.getMP8001_STATUS().equals("1")){
				mp8001.setMP8001_STATUS_NAME(Constant.VALID);
			}else{
				mp8001.setMP8001_STATUS_NAME(Constant.INVALID);
			}
			// 一级标题
			if(mp8001.getMP8001_TITLE() != null && !mp8001.getMP8001_TITLE().equals("")){
				if(mp8001.getMP8001_TITLE().length() > 200){
					mp8001.setMP8001_TITLE2(mp8001.getMP8001_TITLE().substring(0, 180) + "......");
				}else{
					mp8001.setMP8001_TITLE2(mp8001.getMP8001_TITLE());
				}
			}
			// 二级标题
			if(mp8001.getMP8001_SUB_TITLE() != null && !mp8001.getMP8001_SUB_TITLE().equals("")){
				if(mp8001.getMP8001_SUB_TITLE().length() > 200){
					mp8001.setMP8001_SUB_TITLE2(mp8001.getMP8001_SUB_TITLE().substring(0, 180) + "......");
				}else{
					mp8001.setMP8001_SUB_TITLE2(mp8001.getMP8001_SUB_TITLE());
				}
			}
			// 备注
			if(mp8001.getMP8001_COMMENT() != null && !mp8001.getMP8001_COMMENT().equals("")){
				if(mp8001.getMP8001_COMMENT().length() > 200){
					mp8001.setMP8001_COMMENT2(mp8001.getMP8001_COMMENT().substring(0, 180) + "......");
				}else{
					mp8001.setMP8001_COMMENT2(mp8001.getMP8001_COMMENT());
				}
			}

            retList.add(mp8001);
        }
        return retList;
    }
}

