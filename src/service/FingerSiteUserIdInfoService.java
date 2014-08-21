package service;

import java.util.List;

import dao.FingerSiteUserIdInfoDAO;

import entity.FingerSiteUserIdInfo;

public class FingerSiteUserIdInfoService implements IFingerSiteUserIdInfoService {
	
	private FingerSiteUserIdInfoDAO dao;
	
	@Override
	public void delete(FingerSiteUserIdInfo item) {
		// TODO Auto-generated method stub
		dao.delete(item);
	}

	@Override
	public List<FingerSiteUserIdInfo> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public FingerSiteUserIdInfo findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public void save(FingerSiteUserIdInfo item) {
		// TODO Auto-generated method stub
		dao.save(item);
	}

	@Override
	public void update(FingerSiteUserIdInfo item) {
		// TODO Auto-generated method stub
		dao.update(item);
	}
	

	public FingerSiteUserIdInfoDAO getDao() {
		return dao;
	}

	public void setDao(FingerSiteUserIdInfoDAO dao) {
		this.dao = dao;
	}
}
