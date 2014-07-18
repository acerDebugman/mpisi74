package com.joe.service;

import java.util.List;
import java.util.Map;

import com.joe.model.MP2009;

public interface IMP2009Service {
    // 新增处理 
    public abstract void save(MP2009 mp2009);
    // 删除处理 
    public abstract void delete(MP2009 mp2009);
    // 根据Key查询数据 
    public abstract MP2009 findById(String key);
    // 取得所有数据 
    public abstract List<MP2009> findAll();
    // 更新数据处理 
    public abstract void update(MP2009 mp2009);
    // 自动以更新处理 
    public abstract void executeStatement(String statement);
    // 根据某个字段查询数据 
    public abstract List<MP2009> findByProperty(String name, String value);
    // 根据查询条件取得分页数据 
    public abstract List<MP2009> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT);
    // 取得数据条数 
    public abstract int getRowCountByPage(Map<String, String> propertyMap);
    // 自定义条件查询数据 
    public abstract List<MP2009> findDataBySelfDefined(String search,String order, int pageNum, int pageCount);
}

