package service;

import java.util.List;

import entity.JE0101;

public interface IJE0101Service {
	public abstract void save(JE0101 je0101);

	public abstract void delete(JE0101 je0101);

	public abstract JE0101 findByKey(String key);
	
	public abstract JE0101 findByValue(String value);

	public abstract List<JE0101> findAll();

	public abstract void update(JE0101 je0101);
	
	public JE0101 findByType(String type);
}
