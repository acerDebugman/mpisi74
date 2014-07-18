package com.joe.dao;

import java.util.List;
import java.util.Map;

import com.joe.model.CHECKINOUT;
import com.joe.model.USERINFO;

public interface ICHECKINOUTDAO {
    // 新增处理 
    public abstract void save(CHECKINOUT checkinout);
    // 删除处理 
    public abstract void delete(CHECKINOUT checkinout);
    // 根据Key查询数据 
    public abstract CHECKINOUT findById(String key);
    // 取得所有数据 
    public abstract List<CHECKINOUT> findAll();
    // 更新数据处理 
    public abstract void update(CHECKINOUT checkinout);
    // 自动以更新处理 
    public abstract void executeStatement(String statement);
    // 根据某个字段查询数据 
    public abstract List<CHECKINOUT> findByProperty(String name, String value);
    // 根据查询条件取得分页数据 
    public abstract List<CHECKINOUT> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT);
    // 取得数据条数 
    public abstract int getRowCountByPage(Map<String, String> propertyMap);
    // 自定义条件查询数据 
    public abstract List<CHECKINOUT> findDataBySelfDefined(String search,String order, int pageNum, int pageCount);
    
    public abstract int findAll(String employeeNum);
    public abstract void save(USERINFO userinfo);
}

