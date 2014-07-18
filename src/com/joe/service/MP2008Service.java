package com.joe.service;

import java.util.List;
import java.util.Map;

import com.joe.dao.IMP2008DAO;
import com.joe.model.MP2008;

public class MP2008Service implements IMP2008Service{
    private IMP2008DAO dao;
    // 保存数据
    public void save(MP2008 mp2008) {
        dao.save(mp2008);
    }
    // 删除数据
    public void delete(MP2008 mp2008) {
        dao.delete(mp2008);
    }
    // 根据KEY检索数据
    public MP2008 findById(String key) {
        return dao.findById(key);
    }
    // 取得所有有效数据
    public List<MP2008> findAll() {
        return dao.findAll();
    }
    // 更新数据
    public void update(MP2008 mp2008) {
        dao.update(mp2008);
    }
    // 更新数据
    public void executeStatement(String statement){
        dao.executeStatement(statement);
    }
    // 动态根据传入的参数，检索数据
    public List<MP2008> findByProperty(String name, String value) {
        return dao.findByProperty(name, value);
    }
    // 分页方法
    public List<MP2008> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT) {
        return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
    }
    // 分页方法，取得数据总条数
    public int getRowCountByPage(Map<String, String> propertyMap) {
        return dao.getRowCountByPage(propertyMap);
    }
    // 自定义查询条件及排序方式
    public List<MP2008> findDataBySelfDefined(String search, String order, int pageNum, int pageCount) {
        return dao.findDataBySelfDefined(search, order, pageNum, pageCount);
    }
    // Excel Report
    public List<MP2008> getSumDataForReport(String formDate, String toDate, String empNum, String empName, String depId, String status){
    	return dao.getSumDataForReport(formDate, toDate, empNum, empName, depId, status);
    }
    // Excel Report
    public List<MP2008> getSumDataForReport2(String formDate, String toDate, String empNum, String empName, String depId, String status){
    	return dao.getSumDataForReport2(formDate, toDate, empNum, empName, depId, status);
    }
    /**
     * @return the dao
     */
    public IMP2008DAO getDao() {
        return dao;
    }
    
    /**
         * @param dao the dao to set
     */
    public void setDao(IMP2008DAO dao) {
        this.dao = dao;
    }
}

