package com.joe.service;

import java.util.List;
import java.util.Map;

import com.joe.dao.IMP2009DAO;
import com.joe.model.MP2009;

public class MP2009Service implements IMP2009Service{
    private IMP2009DAO dao;
    // 保存数据
    public void save(MP2009 mp2009) {
        dao.save(mp2009);
    }
    // 删除数据
    public void delete(MP2009 mp2009) {
        dao.delete(mp2009);
    }
    // 根据KEY检索数据
    public MP2009 findById(String key) {
        return dao.findById(key);
    }
    // 取得所有有效数据
    public List<MP2009> findAll() {
        return dao.findAll();
    }
    // 更新数据
    public void update(MP2009 mp2009) {
        dao.update(mp2009);
    }
    // 更新数据
    public void executeStatement(String statement){
        dao.executeStatement(statement);
    }
    
    // 动态根据传入的参数，检索数据
    public List<MP2009> findByProperty(String name, String value) {
        return dao.findByProperty(name, value);
    }
    // 分页方法
    public List<MP2009> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT) {
        return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
    }
    // 分页方法，取得数据总条数
    public int getRowCountByPage(Map<String, String> propertyMap) {
        return dao.getRowCountByPage(propertyMap);
    }
    // 自定义查询条件及排序方式
    public List<MP2009> findDataBySelfDefined(String search, String order, int pageNum, int pageCount) {
        return dao.findDataBySelfDefined(search, order, pageNum, pageCount);
    }
    /**
     * @return the dao
     */
    public IMP2009DAO getDao() {
        return dao;
    }
    
    /**
         * @param dao the dao to set
     */
    public void setDao(IMP2009DAO dao) {
        this.dao = dao;
    }
}

