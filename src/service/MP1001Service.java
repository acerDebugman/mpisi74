package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import schedule.CommonJobMethod;

import common.Constant;

import dao.IMP1001DAO;
import entity.MP1001;
import entity.MP1005;

public class MP1001Service implements IMP1001Service {

	private IMP1001DAO dao;

	public void delete(MP1001 mp1001) {
		dao.delete(mp1001);
	}

	public List<MP1001> findAll() {
		return dao.findAll();
	}
	
	public List<MP1001> findAbsolutelyAll(){
		return dao.findAbsolutelyAll();
	}
	
	public List<MP1001> findbyDepartmentId(String departmentID){
		return dao.findbyDepartmentId(departmentID);
	}
	
	public List<MP1001> findAllResign(String _start, String _finish){
		return dao.findAllResign(_start, _finish);
	}
	
	public List<MP1001> findAllEmail() {
		return dao.findAll();
	}

	public MP1001 findById(String employeeNum) {
		return dao.findById(employeeNum);
	}

	public void save(MP1001 mp1001) {
		dao.save(mp1001);
	}

	public void update(MP1001 mp1001) {
		dao.update(mp1001);
	}
	
	public void updateEmployeeNum(String oldValue, String newValue, String curUser) throws Exception{
		dao.updateEmployeeNum(oldValue, newValue, curUser);
	}
	
	public List<MP1001> findByProperty(Map<String, String> propertyMap){
		return dao.findByProperty(propertyMap,false);
	}
	
	public List<MP1001> findByPropertyPage(Map<String, String> propertyMap){
		return dao.findByProperty(propertyMap,true);
	}
	
	public List<MP1001> getDepartmentHeadcount(){
		return dao.getDepartmentHeadcount();
	}
	
	// 自定义查询条件及排序方式
	public List<MP1001> findData(String search,String order, int pageNum, int pageCount){
		return dao.findData(search, order, pageNum, pageCount);
	}

	public IMP1001DAO getDao() {
		return dao;
	}

	public void setDao(IMP1001DAO dao) {
		this.dao = dao;
	}
	
	public void saveTransaction(MP1001 mp1001,MP1005 mp1005,String educationList,String workList){
		dao.saveTransaction(mp1001, mp1005, educationList, workList);
	}
	
	//all present employees
	public List<MP1001> pickUpRequiredPresentEmployeeList(){
		List<MP1001> lst = dao.findAll();
		Map<String, Boolean> exemptMap = Constant.pickUpPresentExemptList();
		
//		for(int i = 0, size = lst.size(); i < size; i++){
//			if(exemptMap.containsKey(lst.get(i).getMP1001_EMPLOYEE_NUM())){
//				lst.remove(i);
//				i--;
//				size--; //important,otherwise out of bound error
//			}
//		}
		List<MP1001> newList = new ArrayList<MP1001>();
		for(MP1001 emp : lst){
			if(!exemptMap.containsKey(emp.getMP1001_EMPLOYEE_NUM())){
				newList.add(emp);
			}
		}
		
		return newList;
	}
	
	public List<MP1001> findByEmployeeStringList(String strEmplyeeList){
		return dao.findByEmployeeStringList(strEmplyeeList);
	}
}
