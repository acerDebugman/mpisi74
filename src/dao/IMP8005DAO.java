package dao;

import java.util.List;
import java.util.Map;
import entity.MP8005;

public interface IMP8005DAO {
    // 新增处理 
    public abstract void save(MP8005 mp8005);
    // 删除处理 
    public abstract void delete(MP8005 mp8005);
    // 根据Key查询数据 
    public abstract MP8005 findById(String key);
    // 取得所有数据 
    public abstract List<MP8005> findAll();
    // 更新数据处理 
    public abstract void update(MP8005 mp8005);
    // 自动以更新处理 
    public abstract void executeStatement(String statement);
    // 根据某个字段查询数据 
    public abstract List<MP8005> findByProperty(String name, String value);
    // 根据查询条件取得分页数据 
    public abstract List<MP8005> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT);
    // 取得数据条数 
    public abstract int getRowCountByPage(Map<String, String> propertyMap);
    // 自定义条件查询数据 
    public abstract List<MP8005> findDataBySelfDefined(String search,String order, int pageNum, int pageCount);
    
    public abstract List<MP8005> getReportList(String reviewPeriod, String DepId);
}

