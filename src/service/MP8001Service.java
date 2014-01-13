package service;

import java.util.List;
import java.util.Map;

import dao.IMP8001DAO;
import entity.MP8001;

public class MP8001Service implements IMP8001Service{
    private IMP8001DAO dao;
    // 保存数据
    public void save(MP8001 mp8001) {
        dao.save(mp8001);
    }
    // 删除数据
    public void delete(MP8001 mp8001) {
        dao.delete(mp8001);
    }
    // 根据KEY检索数据
    public MP8001 findById(String key) {
        return dao.findById(key);
    }
    // 取得所有有效数据
    public List<MP8001> findAll() {
        return dao.findAll();
    }
    // 更新数据
    public void update(MP8001 mp8001) {
        dao.update(mp8001);
    }
    // 更新数据
    public void executeStatement(String statement){
        dao.executeStatement(statement);
    }
    
    // 动态根据传入的参数，检索数据
    public List<MP8001> findByProperty(String name, String value) {
        return dao.findByProperty(name, value);
    }
    // 分页方法
    public List<MP8001> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT) {
        return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
    }
    // 分页方法，取得数据总条数
    public int getRowCountByPage(Map<String, String> propertyMap) {
        return dao.getRowCountByPage(propertyMap);
    }
    // 自定义查询条件及排序方式
    public List<MP8001> findDataBySelfDefined(String search, String order, int pageNum, int pageCount) {
        return dao.findDataBySelfDefined(search, order, pageNum, pageCount);
    }
    /**
     * @return the dao
     */
    public IMP8001DAO getDao() {
        return dao;
    }
    
    /**
         * @param dao the dao to set
     */
    public void setDao(IMP8001DAO dao) {
        this.dao = dao;
    }
}

