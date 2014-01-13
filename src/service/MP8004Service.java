package service;

import java.util.List;
import java.util.Map;

import dao.IMP8004DAO;
import entity.MP8004;

public class MP8004Service implements IMP8004Service{
    private IMP8004DAO dao;
    // 保存数据
    public void save(MP8004 mp8004) {
        dao.save(mp8004);
    }
    // 删除数据
    public void delete(MP8004 mp8004) {
        dao.delete(mp8004);
    }
    // 根据KEY检索数据
    public MP8004 findById(String key) {
        return dao.findById(key);
    }
    // 取得所有有效数据
    public List<MP8004> findAll() {
        return dao.findAll();
    }
    // 更新数据
    public void update(MP8004 mp8004) {
        dao.update(mp8004);
    }
    // 更新数据
    public void executeStatement(String statement){
        dao.executeStatement(statement);
    }
    
    // 动态根据传入的参数，检索数据
    public List<MP8004> findByProperty(String name, String value) {
        return dao.findByProperty(name, value);
    }
    // 分页方法
    public List<MP8004> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT) {
        return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
    }
    // 分页方法，取得数据总条数
    public int getRowCountByPage(Map<String, String> propertyMap) {
        return dao.getRowCountByPage(propertyMap);
    }
    // 自定义查询条件及排序方式
    public List<MP8004> findDataBySelfDefined(String search, String order, int pageNum, int pageCount) {
        return dao.findDataBySelfDefined(search, order, pageNum, pageCount);
    }
    /**
     * @return the dao
     */
    public IMP8004DAO getDao() {
        return dao;
    }
    
    /**
         * @param dao the dao to set
     */
    public void setDao(IMP8004DAO dao) {
        this.dao = dao;
    }
}

