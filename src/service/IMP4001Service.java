package service;

import java.util.List;
import java.util.Map;

import entity.MP4001;

public interface IMP4001Service {
	public abstract void save(MP4001 mp4001);

	public abstract void delete(MP4001 mp4001);

	public abstract MP4001 findById(String key);

	public abstract List<MP4001> findAll();

	public abstract void update(MP4001 mp4001);

	public abstract List<MP4001> findByProperty(String name, String value);

	public abstract List<MP4001> findByPropertyByPage(Map<String, String> propertyMap,int PAGE_NUM, int PAGE_COUNT);

	public abstract int getRowCountByPage(Map<String, String> propertyMap) ;
}
