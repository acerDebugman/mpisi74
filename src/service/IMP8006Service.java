package service;

import java.util.List;
import java.util.Map;
import entity.MP8006;

public interface IMP8006Service {
    // 新增处理 
    public abstract void save(MP8006 mp8006);
    // 删除处理 
    public abstract void delete(MP8006 mp8006);
    // 根据Key查询数据 
    public abstract MP8006 findById(String key);
    // 取得所有数据 
    public abstract List<MP8006> findAll();
    // 更新数据处理 
    public abstract void update(MP8006 mp8006);
    
    public abstract void updateDataBySelf(List<String> list1, List<String> list2, List<String> list3, List<String> list4, List<String> list5, String key);
    // 自动以更新处理 
    public abstract void executeStatement(String statement);
    // 根据某个字段查询数据 
    public abstract List<MP8006> findByProperty(String name, String value);
    // 根据查询条件取得分页数据 
    public abstract List<MP8006> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT);
    // 取得数据条数 
    public abstract int getRowCountByPage(Map<String, String> propertyMap);
    // 自定义条件查询数据 
    public abstract List<MP8006> findDataBySelfDefined(String search,String order, int pageNum, int pageCount);
}

