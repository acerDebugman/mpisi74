package service;

import java.util.List;
import java.util.Map;
import entity.MP2008;

public interface IMP2008Service {
    // 新增处理 
    public abstract void save(MP2008 mp2008);
    // 删除处理 
    public abstract void delete(MP2008 mp2008);
    // 根据Key查询数据 
    public abstract MP2008 findById(String key);
    // 取得所有数据 
    public abstract List<MP2008> findAll();
    // 更新数据处理 
    public abstract void update(MP2008 mp2008);
    // 自动以更新处理 
    public abstract void executeStatement(String statement);
    // 根据某个字段查询数据 
    public abstract List<MP2008> findByProperty(String name, String value);
    // 根据查询条件取得分页数据 
    public abstract List<MP2008> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT);
    // 取得数据条数 
    public abstract int getRowCountByPage(Map<String, String> propertyMap);
    // 自定义条件查询数据 
    public abstract List<MP2008> findDataBySelfDefined(String search,String order, int pageNum, int pageCount);
    // Excel Report
    public abstract List<MP2008> getSumDataForReport(String formDate, String toDate, String empNum, String empName, String depId, String status);
    // Excel Report
    public abstract List<MP2008> getSumDataForReport2(String formDate, String toDate, String empNum, String empName, String depId, String status);
}

