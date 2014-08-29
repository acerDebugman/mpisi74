package dao;

import java.util.List;
import java.util.Map;

import entity.MP1001;
import entity.MP1005;

public interface IMP1001DAO {
	// 保存数据
	public abstract void save(MP1001 mp1001);
	// 删除数据
	public abstract void delete(MP1001 mp1001);
	// 根据关键字查询数据
	public abstract MP1001 findById(String employeeId);
	// 查询所有数据
	public abstract List<MP1001> findAll();
	// 取得指定部门的员工
	public abstract List<MP1001> findbyDepartmentId(String departmentID);
	// 取得所有离职人员数据
	public abstract List<MP1001> findAllResign(String _start, String _finish);
	// 查询所有数据
	public abstract List<MP1001> findAllEmail();
	// 更新数据
	public abstract void update(MP1001 mp1001);
	// 更新数据
	public abstract void updateEmployeeNum(String oldValue, String newValue, String curUser) throws Exception;
	// 根据检索字段查询数据
	public abstract List<MP1001> findByProperty(Map<String, String> propertyMap,boolean pageFlag);
	// 获取部门人数
	public abstract List<MP1001> getDepartmentHeadcount();
	
	// 保存数据(事务)
	public void saveTransaction(MP1001 mp1001,MP1005 mp1005,String educationList,String workList);
	
	// 自定义查询条件及排序方式
	public abstract List<MP1001> findData(String search,String order, int pageNum, int pageCount);
	
	//public abstract MP1001 findByEmployeeName(String name);
	
	public List<MP1001> findByEmployeeStringList(String strEmplyeeList);
	
	public List<MP1001> findAbsolutelyAll();
	
	public void merge(MP1001 mp11);
}
