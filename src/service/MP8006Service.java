package service;

import java.util.List;
import java.util.Map;

import dao.IMP8006DAO;
import entity.MP8006;

public class MP8006Service implements IMP8006Service{
    private IMP8006DAO dao;
    // 保存数据
    public void save(MP8006 mp8006) {
        dao.save(mp8006);
    }
    // 删除数据
    public void delete(MP8006 mp8006) {
        dao.delete(mp8006);
    }
    // 根据KEY检索数据
    public MP8006 findById(String key) {
        return dao.findById(key);
    }
    // 取得所有有效数据
    public List<MP8006> findAll() {
        return dao.findAll();
    }
    // 更新数据
    public void update(MP8006 mp8006) {
        dao.update(mp8006);
    }
	// 更新数据
	public void updateDataBySelf(List<String> list1, List<String> list2, List<String> list3, List<String> list4, List<String> list5, String key){
		dao.updateDataBySelf(list1, list2, list3, list4, list5, key);
	}
    // 更新数据
    public void executeStatement(String statement){
        dao.executeStatement(statement);
    }
    
    // 动态根据传入的参数，检索数据
    public List<MP8006> findByProperty(String name, String value) {
        return dao.findByProperty(name, value);
    }
    // 分页方法
    public List<MP8006> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT) {
        return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
    }
    // 分页方法，取得数据总条数
    public int getRowCountByPage(Map<String, String> propertyMap) {
        return dao.getRowCountByPage(propertyMap);
    }
    // 自定义查询条件及排序方式
    public List<MP8006> findDataBySelfDefined(String search, String order, int pageNum, int pageCount) {
        return dao.findDataBySelfDefined(search, order, pageNum, pageCount);
    }
    /**
     * @return the dao
     */
    public IMP8006DAO getDao() {
        return dao;
    }
    
    /**
         * @param dao the dao to set
     */
    public void setDao(IMP8006DAO dao) {
        this.dao = dao;
    }
}

