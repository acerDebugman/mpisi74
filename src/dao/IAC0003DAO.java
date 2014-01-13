package dao;

import java.util.List;
import java.util.Map;

import entity.AC0003;

public interface IAC0003DAO {
	// 保存数据
	public abstract void save(AC0003 ac0003);
	// 删除数据
	public abstract void delete(AC0003 ac0003);
	// 根据关键字查询数据
	public abstract AC0003 findById(String seq);
	// 查询所有数据
	public abstract List<AC0003> findAll();
	// 更新数据
	public abstract void update(AC0003 ac0003);
	// 根据检索字段查询数据
	public abstract List<AC0003> findByProperty(String name, String value);
	// 分页方法
	public abstract List<AC0003> findByPropertyByPage(Map<String, String> propertyMap, int PAGE_NUM, int PAGE_COUNT);
	// 分页方法，取得数据总条数
	public abstract int getRowCountByPage(Map<String, String> propertyMap);
	// 取得系统功能操作
	public abstract List<AC0003> getFunctionOperationInfoData(String functionNum);
}