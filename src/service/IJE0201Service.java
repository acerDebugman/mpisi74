package service;

import java.util.List;

import entity.JE0201;

public interface IJE0201Service {
	public abstract void save(JE0201 je0201);

	public abstract void delete(JE0201 je0201);

	public abstract JE0201 findByKey(String key);
	
	public abstract JE0201 findByValue(String value);

	public abstract List<JE0201> findAll();

	public abstract void update(JE0201 je0201);
	
	public JE0201 findByType(String type);
}
