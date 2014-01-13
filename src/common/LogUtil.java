package common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import service.IMP0011Service;
import entity.MP0011;

public class LogUtil {
	private IMP0011Service serviceMP0011;
	
	// 系统操作历史记录
	public void writeOperationLog(String empNum,String empName,String option){
		MP0011 mp0011 = new MP0011();
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		// 生成主KEY
		String key = sdf.format(now) + Integer.toString(new Double(Math.ceil(Math.random()*100000 )).intValue()) ;
		// 取得登陆时间
		String loginTime = sdf1.format(now);
		
		// 取得Request
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		// 取得IP地址
		String ip = UtilCommon.getIPAddress(req);
		
		mp0011.setMP0011_SEQ(key);//设置KEY
		mp0011.setMP0011_SYS_CODE(Constant.SYSTEM_NUM_HR);//设置系统编码
		mp0011.setMP0011_USR_ID(empNum);//设置员工编码
		mp0011.setMP0011_USR_NAME(empName);//设置员工名称
		mp0011.setMP0011_LOGIN_TIME(loginTime);//设置登陆时间
		mp0011.setMP0011_LOGIN_IP(ip);//设置IP地址
		mp0011.setMP0011_MEMO(option);//操作
		
		serviceMP0011.save(mp0011);		
	}
	// 系统登录历史记录
	public void writeLoginLog(String empNum,String empName,String type){
		MP0011 mp0011 = new MP0011();
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		// 生成主KEY
		String key = sdf.format(now) + Integer.toString(new Double(Math.ceil(Math.random()*100000 )).intValue()) ;
		// 取得登陆时间
		String loginTime = sdf1.format(now);
		
		// 取得Request
		HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();
		// 取得IP地址
		String ip = UtilCommon.getIPAddress(req);
		
		mp0011.setMP0011_SEQ(key);//设置KEY
		mp0011.setMP0011_SYS_CODE(Constant.SYSTEM_NUM_HR);//设置系统编码
		mp0011.setMP0011_USR_ID(empNum);//设置员工编码
		mp0011.setMP0011_USR_NAME(empName);//设置员工名称
		mp0011.setMP0011_LOGIN_TIME(loginTime);//设置登陆时间
		mp0011.setMP0011_LOGIN_IP(ip);//设置IP地址
		
		if(type.equals("Y")){//备注
			mp0011.setMP0011_MEMO(Constant.LOGIN_SUCCESS);
		}else if(type.equals("N")){
			mp0011.setMP0011_MEMO(Constant.LOGIN_FAILURE);
		}else if(type.equals("P")){
			mp0011.setMP0011_MEMO(Constant.RESET_PWD);
		}else{
			mp0011.setMP0011_MEMO("");
		}
		
		serviceMP0011.save(mp0011);		
	}

	/**
	 * @return the serviceMP0011
	 */
	public IMP0011Service getServiceMP0011() {
		return serviceMP0011;
	}

	/**
	 * @param serviceMP0011 the serviceMP0011 to set
	 */
	public void setServiceMP0011(IMP0011Service serviceMP0011) {
		this.serviceMP0011 = serviceMP0011;
	}
	
}
