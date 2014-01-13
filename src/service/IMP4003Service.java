package service;

import java.util.List;
import java.util.Map;

import entity.MP4003;

public interface IMP4003Service {
	public abstract void save(MP4003 mp4003);

	public abstract void delete(MP4003 mp4003);

	public abstract MP4003 findById(int key);

	public abstract List<MP4003> findAll();

	public abstract void update(MP4003 mp4003);
	
	public abstract void update2(String departmentID, String year, String month,String status);

	public abstract List<MP4003> findByProperty(String name, String value);

	public abstract List<MP4003> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT);

	public abstract int getRowCountByPage(Map<String, String> propertyMap) ;
	
	public abstract List<MP4003> getBudgetInfoListByDepYear(String mainClass,String departmentId, String year, String month, String currentMonth1,boolean monthFlag);
	
	public abstract List<MP4003> getBudgetInfoListByAccYear(String mainClass,String departmentId,String year, String month, String month1,boolean monthFlag);
	
	public abstract List<MP4003> getBudgetChartData(String year, String month);
	
	public abstract List<MP4003> getBudgetChartData2(String year, String departmentID);
}
