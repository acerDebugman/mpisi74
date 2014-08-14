package service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import dao.ICHECKINOUTDAO;
import entity.CHECKINOUT;
import entity.MP1001;
import entity.USERINFO;

public class CHECKINOUTService implements ICHECKINOUTService{
    private ICHECKINOUTDAO dao;
    // 保存数据
    public void save(CHECKINOUT checkinout) {
        dao.save(checkinout);
    }
    // 删除数据
    public void delete(CHECKINOUT checkinout) {
        dao.delete(checkinout);
    }
    // 根据KEY检索数据
    public CHECKINOUT findById(String key) {
        return dao.findById(key);
    }
    // 取得所有有效数据
    public List<CHECKINOUT> findAll() {
        return dao.findAll();
    }
    // 更新数据
    public void update(CHECKINOUT checkinout) {
        dao.update(checkinout);
    }
    // 更新数据
    public void executeStatement(String statement){
        dao.executeStatement(statement);
    }
    // 动态根据传入的参数，检索数据
    public List<CHECKINOUT> findByProperty(String name, String value) {
        return dao.findByProperty(name, value);
    }
    // 分页方法
    public List<CHECKINOUT> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT) {
        return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
    }
    // 分页方法，取得数据总条数
    public int getRowCountByPage(Map<String, String> propertyMap) {
        return dao.getRowCountByPage(propertyMap);
    }
    // 自定义查询条件及排序方式
    public List<CHECKINOUT> findDataBySelfDefined(String search, String order, int pageNum, int pageCount) {
        return dao.findDataBySelfDefined(search, order, pageNum, pageCount);
    }
    
    public int findAll(String employeeNum){
    	return dao.findAll(employeeNum);
    }
    public void save(USERINFO userinfo){
    	dao.save(userinfo);
    }
    
    public List<CHECKINOUT> fetchAllDayRecords(Date date){
    	return dao.fetchAllDayRecords(date);
    }
    
    public List<CHECKINOUT> fetchEmployeeDailyRecords(Date date, MP1001 emp){
    	return dao.fetchEmployeeDailyRecords(date, emp);
    }
    
    public CHECKINOUT fetchLatestDayRecord(Date date){
    	return dao.fetchLatestDayRecord(date);
    }
    
    /**
     * @return the dao
     */
    public ICHECKINOUTDAO getDao() {
        return dao;
    }
    
    /**
         * @param dao the dao to set
     */
    public void setDao(ICHECKINOUTDAO dao) {
        this.dao = dao;
    }
    
    public long fetchTotalRecordsCounter(){
    	return dao.fetchTotalRecordsCounter();
    }
}

