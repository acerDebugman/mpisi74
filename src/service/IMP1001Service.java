package service;

import java.util.List;
import java.util.Map;

import entity.MP1001;
import entity.MP1005;

public interface IMP1001Service {
	public abstract void save(MP1001 mp1001);

	public abstract void delete(MP1001 mp1001);

	public abstract MP1001 findById(String employeeNum);

	public abstract List<MP1001> findAll();
	
	public abstract List<MP1001> findbyDepartmentId(String departmentID);
	
	public abstract List<MP1001> findAllResign(String _start, String _finish);
	
	public abstract List<MP1001> findAllEmail();

	public abstract void update(MP1001 mp1001);
	
	public abstract void updateEmployeeNum(String oldValue, String newValue, String curUser) throws Exception;
	
	public List<MP1001> findByProperty(Map<String, String> propertyMap);
	
	public List<MP1001> findByPropertyPage(Map<String, String> propertyMap);
	
	// 获取部门人数
	public abstract List<MP1001> getDepartmentHeadcount();
	
	public void saveTransaction(MP1001 mp1001,MP1005 mp1005,String educationList,String workList);
	
	// 自定义查询条件及排序方式
	public abstract List<MP1001> findData(String search,String order, int pageNum, int pageCount);
	
	public List<MP1001> pickUpRequiredPresentEmployeeList();

	public List<MP1001> findByEmployeeStringList(String strEmplyeeList);
	
	public List<MP1001> findAbsolutelyAll();
}
