package service;

import java.util.List;
import java.util.Map;

import common.PageBean;

import dao.JE0202DAO;
import entity.JE0202;

public class JE0202Service implements IJE0202Service {
	JE0202DAO dao;
	
	@Override
	public void save(JE0202 je0202) {
		// TODO Auto-generated method stub
		dao.save(je0202);
	}

	@Override
	public void delete(JE0202 je0202) {
		// TODO Auto-generated method stub
		dao.delete(je0202);
	}

	@Override
	public JE0202 findByKey(String key) {
		// TODO Auto-generated method stub
		return dao.findByKey(key);
	}

	@Override
	public JE0202 findByValue(String value) {
		// TODO Auto-generated method stub
		return dao.findByValue(value);
	}

	@Override
	public List<JE0202> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void update(JE0202 je0202) {
		// TODO Auto-generated method stub
		dao.update(je0202);
	}

	public JE0202DAO getDao() {
		return dao;
	}

	public void setDao(JE0202DAO dao) {
		this.dao = dao;
	}

	@Override
	public JE0202 findByType(String type) {
		// TODO Auto-generated method stub
		return dao.findByType(type);
	}

	@Override
	public List<JE0202> findByColumnName(Map<String, String> columnMap) {
		// TODO Auto-generated method stub
		return dao.findByColumnName(columnMap);
	}

	@Override
	public List<JE0202> findByColumnName(Map<String, String> columnMap, String strOrder){
		return dao.findByColumnName(columnMap, strOrder);
	}
	
	@Override
	public int getAllRowsCount(){
		return dao.getAllRowsCount();
	}

	@Override
	public List<JE0202> findByColumnName(Map<String, String> columnMap, String strOrder, boolean pageFlag){
		return dao.findByColumnName(columnMap, strOrder, pageFlag);
	}
	
	@Override
	public PageBean queryForPage(Map<String, String> pageProperty) {
		PageBean pageBean = new PageBean();
		
		int pageSize = Integer.parseInt(pageProperty.get("pageSize")); //page count is number of record per page
		int ctPage = Integer.parseInt(pageProperty.get("currentPageNum"));
		if(0 == ctPage){
			pageProperty.remove("currentPageNum");
			ctPage = PageBean.countCurrentPage(ctPage);
			pageProperty.put("currentPageNum", "" + ctPage);
		}
		String order = " order by JE0202_FROM_DATETIME DESC";
		List<JE0202> list = this.dao.findByColumnName(pageProperty, order, true); //per page data
		
		//int allRow = this.dao.findByColumnNames(pageProperty, false).size(); //all need data, total record count
		//int allRow = this.dao.getAllRowsCount(); //get all data records
		int allRow = this.dao.getSearchConditionRowsCount(pageProperty);
		final int totalPage =  PageBean.countTotalPage(pageSize, allRow);
		
		pageBean.setAllRow(allRow);
		pageBean.setCurrentPage(ctPage);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		
		return pageBean;
	}
}