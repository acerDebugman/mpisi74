package dao;

import java.util.List;
import java.util.Map;

import entity.MP4004;

public interface IMP4004DAO {
	public abstract void save(MP4004 mp4004);

	public abstract void delete(MP4004 mp4004);

	public abstract MP4004 findById(int key);

	public abstract List<MP4004> findAll();

	public abstract void update(MP4004 mp4004);

	public abstract List<MP4004> findByProperty(String name, String value);

	public abstract List<MP4004> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT);

	public abstract int getRowCountByPage(Map<String, String> propertyMap) ;
}
