package dao;

import java.util.List;
import entity.AC0007;

public interface IAC0007DAO {
	// 保存数据
	public abstract void save(AC0007 ac0007);
	// 删除数据
	public abstract void delete(AC0007 ac0007);
	// 根据关键字查询数据
	public abstract AC0007 findById(String seq);
	// 查询所有数据
	public abstract List<AC0007> findAll();
	// 更新数据
	public abstract void update(AC0007 ac0007);
	// 根据检索字段查询数据
	public abstract List<AC0007> findByProperty(String name, String value);
}