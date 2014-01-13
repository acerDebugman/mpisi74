package service;

import java.util.List;
import java.util.Map;

import dao.IMP8003DAO;
import entity.MP8003;

public class MP8003Service implements IMP8003Service{
    private IMP8003DAO dao;
    // 保存数据
    public void save(MP8003 mp8003) {
        dao.save(mp8003);
    }
    // 删除数据
    public void delete(MP8003 mp8003) {
        dao.delete(mp8003);
    }
    // 根据KEY检索数据
    public MP8003 findById(String key) {
        return dao.findById(key);
    }
    // 取得所有有效数据
    public List<MP8003> findAll() {
        return dao.findAll();
    }
    // 更新数据
    public void update(MP8003 mp8003) {
        dao.update(mp8003);
    }
    // 更新数据
    public void executeStatement(String statement){
        dao.executeStatement(statement);
    }
    
    // 动态根据传入的参数，检索数据
    public List<MP8003> findByProperty(String name, String value) {
        return dao.findByProperty(name, value);
    }
    // 分页方法
    public List<MP8003> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT) {
        return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
    }
    // 分页方法，取得数据总条数
    public int getRowCountByPage(Map<String, String> propertyMap) {
        return dao.getRowCountByPage(propertyMap);
    }
    // 自定义查询条件及排序方式
    public List<MP8003> findDataBySelfDefined(String search, String order, int pageNum, int pageCount) {
        return dao.findDataBySelfDefined(search, order, pageNum, pageCount);
    }
    /**
     * @return the dao
     */
    public IMP8003DAO getDao() {
        return dao;
    }
    
    /**
         * @param dao the dao to set
     */
    public void setDao(IMP8003DAO dao) {
        this.dao = dao;
    }
}

