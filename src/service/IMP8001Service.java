package service;

import java.util.List;
import java.util.Map;
import entity.MP8001;

public interface IMP8001Service {
    // 新增处理 
    public abstract void save(MP8001 mp8001);
    // 删除处理 
    public abstract void delete(MP8001 mp8001);
    // 根据Key查询数据 
    public abstract MP8001 findById(String key);
    // 取得所有数据 
    public abstract List<MP8001> findAll();
    // 更新数据处理 
    public abstract void update(MP8001 mp8001);
    // 自动以更新处理 
    public abstract void executeStatement(String statement);
    // 根据某个字段查询数据 
    public abstract List<MP8001> findByProperty(String name, String value);
    // 根据查询条件取得分页数据 
    public abstract List<MP8001> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT);
    // 取得数据条数 
    public abstract int getRowCountByPage(Map<String, String> propertyMap);
    // 自定义条件查询数据 
    public abstract List<MP8001> findDataBySelfDefined(String search,String order, int pageNum, int pageCount);
}

