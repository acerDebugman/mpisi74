package dao;

import java.util.List;
import entity.AC0009;

public interface IAC0009DAO {
	// 保存数据
	public abstract void save(AC0009 ac0009);
	// 删除数据
	public abstract void delete(AC0009 ac0009);
	// 根据关键字查询数据
	public abstract AC0009 findById(String seq);
	// 查询所有数据
	public abstract List<AC0009> findAll();
	// 更新数据
	public abstract void update(AC0009 ac0009);
	// 根据检索字段查询数据
	public abstract List<AC0009> findByProperty(String name, String value);
	// 检索所有具有会计部审批权限的人员邮件列表
	public abstract String getMailInfoByAccountApprove();
}