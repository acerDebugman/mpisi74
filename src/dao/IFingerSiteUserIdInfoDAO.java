package dao;

import java.util.List;

import entity.FingerSiteUserIdInfo;

public interface IFingerSiteUserIdInfoDAO {
	
	public void delete(FingerSiteUserIdInfo item);

	public List<FingerSiteUserIdInfo> findAll();
	
	public FingerSiteUserIdInfo findById(int id);

	public void save(FingerSiteUserIdInfo item);

	public void update(FingerSiteUserIdInfo item);
}
