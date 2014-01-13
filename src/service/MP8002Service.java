package service;

import java.util.List;
import java.util.Map;

import dao.IMP8002DAO;
import entity.MP8002;

public class MP8002Service implements IMP8002Service{
    private IMP8002DAO dao;
    // 保存数据
    public void save(MP8002 mp8002) {
        dao.save(mp8002);
    }
    // 删除数据
    public void delete(MP8002 mp8002) {
        dao.delete(mp8002);
    }
    // 根据KEY检索数据
    public MP8002 findById(String key) {
        return dao.findById(key);
    }
    // 取得所有有效数据
    public List<MP8002> findAll() {
        return dao.findAll();
    }
    // 更新数据
    public void update(MP8002 mp8002) {
        dao.update(mp8002);
    }
    // 更新数据
    public void executeStatement(String statement){
        dao.executeStatement(statement);
    }
    
    // 动态根据传入的参数，检索数据
    public List<MP8002> findByProperty(String name, String value) {
        return dao.findByProperty(name, value);
    }
    // 分页方法
    public List<MP8002> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT) {
        return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
    }
    // 分页方法，取得数据总条数
    public int getRowCountByPage(Map<String, String> propertyMap) {
        return dao.getRowCountByPage(propertyMap);
    }
    // 自定义查询条件及排序方式
    public List<MP8002> findDataBySelfDefined(String search, String order, int pageNum, int pageCount) {
        return dao.findDataBySelfDefined(search, order, pageNum, pageCount);
    }
    /**
     * @return the dao
     */
    public IMP8002DAO getDao() {
        return dao;
    }
    
    /**
         * @param dao the dao to set
     */
    public void setDao(IMP8002DAO dao) {
        this.dao = dao;
    }
}

