package service;

import java.util.List;
import java.util.Map;

import dao.IMP8005DAO;
import entity.MP8005;

public class MP8005Service implements IMP8005Service{
    private IMP8005DAO dao;
    // 保存数据
    public void save(MP8005 mp8005) {
        dao.save(mp8005);
    }
    // 删除数据
    public void delete(MP8005 mp8005) {
        dao.delete(mp8005);
    }
    // 根据KEY检索数据
    public MP8005 findById(String key) {
        return dao.findById(key);
    }
    // 取得所有有效数据
    public List<MP8005> findAll() {
        return dao.findAll();
    }
    // 更新数据
    public void update(MP8005 mp8005) {
        dao.update(mp8005);
    }
    // 更新数据
    public void executeStatement(String statement){
        dao.executeStatement(statement);
    }
    
    // 动态根据传入的参数，检索数据
    public List<MP8005> findByProperty(String name, String value) {
        return dao.findByProperty(name, value);
    }
    // 分页方法
    public List<MP8005> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT) {
        return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
    }
    // 分页方法，取得数据总条数
    public int getRowCountByPage(Map<String, String> propertyMap) {
        return dao.getRowCountByPage(propertyMap);
    }
    
    public List<MP8005> getReportList(String reviewPeriod, String DepId){
    	return dao.getReportList(reviewPeriod, DepId);
    }
    
    // 自定义查询条件及排序方式
    public List<MP8005> findDataBySelfDefined(String search, String order, int pageNum, int pageCount) {
        return dao.findDataBySelfDefined(search, order, pageNum, pageCount);
    }
    /**
     * @return the dao
     */
    public IMP8005DAO getDao() {
        return dao;
    }
    
    /**
         * @param dao the dao to set
     */
    public void setDao(IMP8005DAO dao) {
        this.dao = dao;
    }
}

