package dao;

import java.util.HashMap;
import java.util.List;

import entity.AC0011;

public interface IAC0011DAO {
	// 保存数据
	public abstract void save(AC0011 ac0011);
	// 删除数据
	public abstract void delete(AC0011 ac0011);
	// 根据关键字查询数据
	public abstract AC0011 findById(String seq);
	// 查询所有数据
	public abstract List<AC0011> findAll();
	// 更新数据
	public abstract void update(AC0011 ac0011);
	// 根据检索字段查询数据
	public abstract List<AC0011> findByProperty(String name, String value);
	// 功能权限检查
	public abstract HashMap<String,String> functionAccessCheck(String _empNum,String _systemNum);
}
