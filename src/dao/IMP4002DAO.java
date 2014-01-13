package dao;

import java.util.List;
import java.util.Map;

import entity.MP4002;

public interface IMP4002DAO {
	public abstract void save(MP4002 mp4002);

	public abstract void delete(MP4002 mp4002);

	public abstract MP4002 findById(String key);

	public abstract List<MP4002> findAll();

	public abstract void update(MP4002 mp4002);

	public abstract List<MP4002> findByProperty(String name, String value);

	public abstract List<MP4002> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT);

	public abstract int getRowCountByPage(Map<String, String> propertyMap) ;
}
