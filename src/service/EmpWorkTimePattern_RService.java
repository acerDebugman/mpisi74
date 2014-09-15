package service;

import java.util.List;
import java.util.Map;

import common.PageBean;

import dao.IEmpWorkTimePattern_RDAO;
import entity.EmpWorkTimePattern_R;

public class EmpWorkTimePattern_RService implements IEmpWorkTimePattern_RService{
	private IEmpWorkTimePattern_RDAO dao;
	
	@Override
	public void save(EmpWorkTimePattern_R item) {
		// TODO Auto-generated method stub
		dao.save(item);
	}

	@Override
	public void delete(EmpWorkTimePattern_R item) {
		// TODO Auto-generated method stub
		dao.delete(item);
	}

	@Override
	public EmpWorkTimePattern_R findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<EmpWorkTimePattern_R> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void update(EmpWorkTimePattern_R item) {
		// TODO Auto-generated method stub
		dao.update(item);
	}

	public IEmpWorkTimePattern_RDAO getDao() {
		return dao;
	}

	public void setDao(IEmpWorkTimePattern_RDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<EmpWorkTimePattern_R> findByProperties(Map<String, String> map) {
		// TODO Auto-generated method stub
		return dao.findByProperties(map);
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
		List<EmpWorkTimePattern_R> list = this.dao.findByProperties(pageProperty); //per page data
		
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