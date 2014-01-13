package service;

import java.util.List;
import entity.AC0009;

public interface IAC0009Service {
	public abstract void save(AC0009 ac0009);

	public abstract void delete(AC0009 ac0009);

	public abstract AC0009 findById(String employeeNum);

	public abstract List<AC0009> findAll();
	
	public abstract void update(AC0009 ac0009);
	
	public List<AC0009> findByProperty(String name, String value);
	
	// 检索所有具有会计部审批权限的人员邮件列表
	public abstract String getMailInfoByAccountApprove();
}