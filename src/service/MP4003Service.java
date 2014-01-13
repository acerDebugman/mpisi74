package service;

import java.util.List;
import java.util.Map;

import dao.IMP4003DAO;

import entity.MP4003;

public class MP4003Service implements IMP4003Service{
	private IMP4003DAO dao;
	// 保存数据
	public void save(MP4003 mp4003) {
		dao.save(mp4003);
	}
	// 删除数据
	public void delete(MP4003 mp4003) {
		dao.delete(mp4003);
	}
	// 根据KEY检索数据
	public MP4003 findById(int key) {
		return dao.findById(key);
	}
	// 取得所有有效数据
	public List<MP4003> findAll() {
		return dao.findAll();
	}
	// 更新数据
	public void update(MP4003 mp4003) {
		dao.update(mp4003);
	}
	// 更新数据
	public void update2(String departmentID, String year, String month,String status) {
		dao.update2(departmentID, year, month, status);
	}
	// 动态根据传入的参数，检索数据
	public List<MP4003> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
	// 分页方法
	public List<MP4003> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT) {
		return dao.findByPropertyByPage(propertyMap, PAGE_NUM, PAGE_COUNT);
	}
	// 分页方法，取得数据总条数
	public int getRowCountByPage(Map<String, String> propertyMap) {
		return dao.getRowCountByPage(propertyMap);
	}
	// 按部门、年分组统计预算
	public List<MP4003> getBudgetInfoListByDepYear(String mainClass,String departmentId, String year, String month, String currentMonth1,boolean monthFlag){
		return dao.getBudgetInfoListByDepYear(mainClass,departmentId, year, month, currentMonth1,monthFlag);
	}
	// 按科目、年、月分组统计预算
	public List<MP4003> getBudgetInfoListByAccYear(String mainClass,String departmentId,String year, String month, String month1,boolean monthFlag){
		return dao.getBudgetInfoListByAccYear(mainClass,departmentId,year, month, month1, monthFlag);
	}
	// 按年统计部门图形报表
	public List<MP4003> getBudgetChartData(String year, String month){
		return dao.getBudgetChartData(year, month);
	}
	// 图形报表(一个部门一年的预算折线图)
	public List<MP4003> getBudgetChartData2(String year, String departmentID){
		return dao.getBudgetChartData2(year, departmentID);
	}

	/**
	 * @return the dao
	 */
	public IMP4003DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IMP4003DAO dao) {
		this.dao = dao;
	}
}
