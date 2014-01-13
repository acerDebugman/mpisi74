package service;

import java.util.List;
import java.util.Map;

import dao.IMP1010DAO;
import entity.MP1010;

public class MP1010Service implements IMP1010Service{
    private IMP1010DAO dao;
    // 保存数据
    public void save(MP1010 mp1010) {
        dao.save(mp1010);
    }
    // 删除数据
    public void delete(MP1010 mp1010) {
        dao.delete(mp1010);
    }
    // 根据KEY检索数据
    public MP1010 findById(String key) {
        return dao.findById(key);
    }
    // 取得所有有效数据
    public List<MP1010> findAll() {
        return dao.findAll();
    }
    // 更新数据
    public void update(MP1010 mp1010) {
        dao.update(mp1010);
    }
    // 更新数据
    public void executeStatement(String statement){
        dao.executeStatement(statement);
    }
    
    // 动态根据传入的参数，检索数据
    public List<MP1010> findByProperty(String name, String value) {
        return dao.findByProperty(name, value);
    }
    // 分页方法
    public List<MP1010> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT) {
        return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
    }
    // 分页方法，取得数据总条数
    public int getRowCountByPage(Map<String, String> propertyMap) {
        return dao.getRowCountByPage(propertyMap);
    }
    // 自定义查询条件及排序方式
    public List<MP1010> findDataBySelfDefined(String search, String order, int pageNum, int pageCount) {
        return dao.findDataBySelfDefined(search, order, pageNum, pageCount);
    }
    /**
     * @return the dao
     */
    public IMP1010DAO getDao() {
        return dao;
    }
    
    /**
         * @param dao the dao to set
     */
    public void setDao(IMP1010DAO dao) {
        this.dao = dao;
    }
}

