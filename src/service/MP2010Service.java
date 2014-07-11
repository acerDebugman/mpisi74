package service;

import java.util.List;
import java.util.Map;

import common.PageBean;

import dao.MP2010DAO;
import entity.MP2010;

public class MP2010Service implements IMP2010Service {
	MP2010DAO dao;
	@Override
	public void save(MP2010 mp2010) {
		// TODO Auto-generated method stub
		dao.save(mp2010);
	}

	@Override
	public void delete(MP2010 mp2010) {
		// TODO Auto-generated method stub
		dao.delete(mp2010);
	}

	@Override
	public MP2010 findByKey(String key) {
		// TODO Auto-generated method stub
		return dao.findByKey(key);
	}

	@Override
	public MP2010 findByValue(String value) {
		// TODO Auto-generated method stub
		return dao.findByValue(value);
	}

	@Override
	public List<MP2010> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void update(MP2010 mp2010) {
		// TODO Auto-generated method stub
		dao.update(mp2010);
	}

	@Override
	public MP2010 findByType(String type) {
		// TODO Auto-generated method stub
		return dao.findByType(type);
	}

	@Override
	public List<MP2010> findByColumnName(Map<String, String> columnMap) {
		// TODO Auto-generated method stub
		return dao.findByColumnName(columnMap);
	}

	@Override
	public List<MP2010> findByColumnName(Map<String, String> columnMap,
			String strOrder) {
		// TODO Auto-generated method stub
		return dao.findByColumnName(columnMap, strOrder);
	}

	@Override
	public int getAllRowsCount() {
		// TODO Auto-generated method stub
		return dao.getAllRowsCount();
	}

	@Override
	public List<MP2010> findByColumnName(Map<String, String> columnMap,
			String strOrder, boolean pageFlag) {
		// TODO Auto-generated method stub
		
		return this.dao.findByColumnName(columnMap);
	}

	@Override
	public int getSearchConditionRowsCount(Map<String, String> columnMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void getSubscriberInfo(MP2010 mp2010) {
		// TODO Auto-generated method stub

	}

	@Override
	public void analyzeExcelTemplate(MP2010 mp2010) {
		// TODO Auto-generated method stub
		
	}

	public MP2010DAO getDao() {
		return dao;
	}

	public void setDao(MP2010DAO dao) {
		this.dao = dao;
	}
	
	@Override
	public PageBean queryForPage(Map<String, String> pageProperty) {
		PageBean pageBean = new PageBean();
		
		int pageSize = Integer.parseInt(pageProperty.get("PAGE_COUNT")); //page count is number of record per page
		int ctPage = Integer.parseInt(pageProperty.get("PAGE_NUM"));
		if(0 == ctPage){
			pageProperty.remove("PAGE_NUM");
			ctPage = PageBean.countCurrentPage(ctPage);
			pageProperty.put("PAGE_NUM", "" + ctPage);
		}
		List<MP2010> list = this.dao.findByColumnNames(pageProperty, true); //per page data
		
		//int allRow = this.dao.findByColumnNames(pageProperty, false).size(); //all need data, total record count
		int allRow = (int)this.dao.findTotalSearchCount(pageProperty);
		final int totalPage =  PageBean.countTotalPage(pageSize, allRow);
		
		pageBean.setAllRow(allRow);
		pageBean.setCurrentPage(ctPage);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		
		return pageBean;
	}
}