package com.joe.dao;

import java.util.List;
import java.util.Map;

import com.joe.model.MP1010;

public interface IMP1010DAO {
    // 新增处理 
    public abstract void save(MP1010 mp1010);
    // 删除处理 
    public abstract void delete(MP1010 mp1010);
    // 根据Key查询数据 
    public abstract MP1010 findById(String key);
    // 取得所有数据 
    public abstract List<MP1010> findAll();
    // 更新数据处理 
    public abstract void update(MP1010 mp1010);
    // 自动以更新处理 
    public abstract void executeStatement(String statement);
    // 根据某个字段查询数据 
    public abstract List<MP1010> findByProperty(String name, String value);
    // 根据查询条件取得分页数据 
    public abstract List<MP1010> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT);
    // 取得数据条数 
    public abstract int getRowCountByPage(Map<String, String> propertyMap);
    // 自定义条件查询数据 
    public abstract List<MP1010> findDataBySelfDefined(String search,String order, int pageNum, int pageCount);
}

