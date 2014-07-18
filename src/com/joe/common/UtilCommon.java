package com.joe.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.joe.model.AC0001;
import com.joe.model.AC0002;
import com.joe.model.HOLIDAY;
import com.joe.model.MP0002;
import com.joe.model.MP1001;
import com.joe.service.IAC0001Service;
import com.joe.service.IAC0002Service;
import com.joe.service.IHOLIDAYService;
import com.joe.service.IMP0002Service;
import com.joe.service.IMP1001Service;
import com.opensymphony.xwork2.ActionContext;

public class UtilCommon {
	// 发邮件通知
	public static void sendMailToManager(String content,String title,String toList){
		// 发邮件通知本人密码已经重置
		Mail mail = new Mail();
		mail.setSubject(title);
		mail.setContent(content);
		mail.setTo(toList);
		mail.send();
	}
	// 按年月日时分秒生成字符串
	public static String getSequenceByYearMonthMinute(){
		Calendar calendar = Calendar.getInstance();	
		StringBuffer sb = new StringBuffer();
		sb.append(String.valueOf(calendar.get(Calendar.YEAR)));
		sb.append(String.format("%02d",calendar.get(Calendar.MONTH) + 1) );
		sb.append(String.format("%02d",calendar.get(Calendar.DAY_OF_MONTH)));
		sb.append(String.format("%02d",calendar.get(Calendar.HOUR_OF_DAY)));
		sb.append(String.format("%02d",calendar.get(Calendar.MINUTE)) );
		sb.append(String.format("%02d",calendar.get(Calendar.SECOND)) );
		
		return sb.toString();
	}
	// 临时生成序列号
	@SuppressWarnings("unchecked")
	public static int gererateSeq(Map<String,Object> session){
		int intKey = getTempSeq();
		 String Strkey = Integer.toString(intKey);
		 
		if(session.containsKey("SEQ_MAP")){
			Map<String,String> seqMap = (Map<String, String>) session.get("SEQ_MAP");
			while(true){
				if(seqMap.containsKey(Strkey)){
					intKey = getTempSeq();
					Strkey = Integer.toString(intKey);
				}else{
					break;
				}
			}
			return intKey;
		}else{
			return intKey;
		}
	}
	// 生成随机数
	public static int getTempSeq(){
		return new Double(Math.ceil(Math.random()*100000)).intValue();
	}
	public static boolean checkDirector(String empId){
		boolean retFlg = false;
		
		//M0058 is Jason Huang, 20140305 added by Joe Zhang
		if(empId.equals("M0001") || empId.equals("M0002") || empId.equals("M0003") || empId.equals("M0011") || empId.equals("M0198") || empId.equals("M0058")){
			retFlg = true;
		}
		return retFlg;
	}
	// 取得相关部门信息
	public static Map<String,String> getRelatedDepartmentList(){
		Map<String,String> relatedDepartmentInfoList = new LinkedHashMap<String,String>();
		
		relatedDepartmentInfoList.put("-1", "---Please Select---");
		relatedDepartmentInfoList.put("1", "ACCOUNTING");
		relatedDepartmentInfoList.put("6", "HR");
		relatedDepartmentInfoList.put("8", "IT");
		
		return relatedDepartmentInfoList;
	}
	// 取得预算类别大类
//	public static Map<String,String> getBugdetTypeLevel1(IMP4002Service serviceMP4002){
//		Map<String,String> retsult = new LinkedHashMap<String,String>();
//		
//		List<MP4002> mp4002List = serviceMP4002.findAll();
//		retsult.put("-1", "---Please Select---");
//		for(int i=0,j=mp4002List.size(); i<j; i++){
//			MP4002 mp42 = mp4002List.get(i);
//			
//			if(mp42.getMP4002_NUM().equals("0")){
//				continue;
//			}
//			retsult.put(mp42.getMP4002_NUM(), mp42.getMP4002_NAME_E());
//		}
//
//		return retsult;
//	}
	// 取得预算类别明细
//	public static Map<String,String> getBugdetTypeLevel2(String year, String month, String departmentId,String parentType,IMP4003Service serviceMP4003){
//		Map<String,String> retsult = new LinkedHashMap<String,String>();
//		Map<String, String> propertyMap = new HashMap<String, String>();
//		propertyMap.put("DEPARTMENT_ID", departmentId);
//		propertyMap.put("PARENT_TYPE", parentType);
//		propertyMap.put("YEAR", year);
//		propertyMap.put("MONTH", month);
//		
//		List<MP4003> mp43List = serviceMP4003.findByPropertyByPage(propertyMap, -1, -1);
//		retsult.put("-1", "---Please Select---");
//		for(int i=0,j=mp43List.size(); i<j; i++){
//			MP4003 mp43 = mp43List.get(i);
//			retsult.put(String.valueOf(mp43.getMP4003_SEQ()), mp43.getMP4003_ACCOUNTING_NUM() + "-" + mp43.getMP4003_ACCOUNTING_NAME());
//		}
//		
//		return retsult;
//	}
	// 取得部门列表
	public static Map<String,String> getDepartmentList(IMP0002Service serviceMP0002){
		Map<String,String> departmentInfoList = new LinkedHashMap<String,String>();
		
		List<MP0002> departmentObjList = serviceMP0002.findAll(true);
		departmentInfoList.put("-1", "---Please Select---");
		for(int i=0,j=departmentObjList.size(); i<j; i++){
			MP0002 mp02 = departmentObjList.get(i);
			departmentInfoList.put(String.valueOf(mp02.getMP0002_SEQ()), mp02.getMP0002_DEPARTMENT_NAME());
		}
		return departmentInfoList;
	}
	// 取得部门列表
	@SuppressWarnings("unchecked")
	public static Map<String,String> getDepartmentList(){
		Map<String,String> departmentInfoList = new LinkedHashMap<String,String>();
		List<MP0002> objList = new ArrayList<MP0002>();
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		if(session.containsKey("DEPARTMENT_LIST")){
			objList = (List<MP0002>) session.get("DEPARTMENT_LIST");
		}
		departmentInfoList.put("-1", "---Please Select---");
		for(int i=0,j=objList.size(); i<j; i++){
			MP0002 mp02 = objList.get(i);
			
			departmentInfoList.put(String.valueOf(mp02.getMP0002_SEQ()), mp02.getMP0002_DEPARTMENT_NAME());
		}

		return departmentInfoList;
	}
	// 取得部门列表(采购及预算用)
	@SuppressWarnings("unchecked")
	public static Map<String,String> getBudgetDepartmentList(){
		Map<String,String> departmentInfoList = new LinkedHashMap<String,String>();
		List<MP0002> objList = new ArrayList<MP0002>();
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		if(session.containsKey("DEPARTMENT_LIST")){
			objList = (List<MP0002>) session.get("DEPARTMENT_LIST");
		}
		departmentInfoList.put("-1", "---Please Select---");
		for(int i=0,j=objList.size(); i<j; i++){
			MP0002 mp02 = objList.get(i);
			
			if(mp02.getMP0002_SEQ() == 4 || mp02.getMP0002_SEQ() == 16){
				continue;
			}
			departmentInfoList.put(String.valueOf(mp02.getMP0002_SEQ()), mp02.getMP0002_DEPARTMENT_NAME());
		}

		return departmentInfoList;
	}
	// 取得部门列表
	@SuppressWarnings("unchecked")
	public static String[] getDepartmentArrayInfo(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		List<MP0002> objList = new ArrayList<MP0002>();
		
		if(session.containsKey("DEPARTMENT_LIST")){
			objList = (List<MP0002>) session.get("DEPARTMENT_LIST");
		}
		String[] columnKeys = new String[objList.size()];
		int count = 0;
		for(int i=0,j=objList.size(); i<j; i++){
			MP0002 mp02 = objList.get(i);
			
			if(mp02.getMP0002_SEQ() == 4 || mp02.getMP0002_SEQ() == 16){
				continue;
			}
			
			columnKeys[count] = mp02.getMP0002_DEPARTMENT_NAME();
			count ++;
		}
		
		return columnKeys;
	}
	// 取得系统信息
	public static Map<String,String> getSystemInfoMap(IAC0001Service serviceAC0001){
		Map<String,String> systemInfoMap = new LinkedHashMap<String,String>();
		List<AC0001> ac0001ObjList = serviceAC0001.findByProperty("AC0001_STATUS", "1");
		systemInfoMap.put("-1", "---Please Select---");
		for(int i=0,j=ac0001ObjList.size(); i<j; i++){
			AC0001 ac01 = ac0001ObjList.get(i);
			systemInfoMap.put(ac01.getAC0001_SYS_NUM(), ac01.getAC0001_SYS_NAME());
		}

		return systemInfoMap;
	}
	// 取得系统功能列表
	public static Map<String,String> getSystemFunctionInfoMap(IAC0002Service serviceAC0002,String systemNum,String systemName){
		Map<String,String> functionInfoMap = new LinkedHashMap<String,String>();
		List<AC0002> ac0002ObjList = serviceAC0002.findByProperty("AC0002_PARENT_NUM", systemNum);
		functionInfoMap.put("-1", "---Please Select---");
		functionInfoMap.put(systemNum, systemName);
		for(int i=0,j=ac0002ObjList.size(); i<j; i++){
			AC0002 ac02 = ac0002ObjList.get(i);
			functionInfoMap.put(ac02.getAC0002_FUN_NUM(), ac02.getAC0002_FUN_NAME());
		}

		return functionInfoMap;
	}
	// 获取客户端的IP地址
	public static  String getIPAddress(HttpServletRequest req){
		//HttpServletRequest req=(HttpServletRequest)ServletActionContext.getRequest();

		 String visitorIp=req.getRemoteAddr();
		 //String visitorhost = req.getRemoteHost();
		 //int visitorPort=req.getRemotePort();
		 //String visitorUsername=req.getRemoteUser();
		 //String visitorUrl=req.getRequestURI();
		 
		 return visitorIp;
	}
	// 制定精度的四舍五入
	public static double getRoundByValue(double inValue,int length){
		BigDecimal bd = new BigDecimal(inValue);
		bd = bd.setScale(length,BigDecimal.ROUND_HALF_EVEN);
		double d = bd.doubleValue();
		
		return d;
	}
	// 制定精度的四舍五入
	public static String getRoundByValue2(double inValue,int length){
	      java.text.NumberFormat formate = java.text.NumberFormat.getNumberInstance(); 
	      formate.setMaximumFractionDigits(length);//设定小数最大为数   ，那么显示的最后会四舍五入的 
	      String m = formate.format(inValue);
	      
	      return m;
	}
	// 生成如下格式的序列号MMddyyyyHHmmssSS
	public static String getSerial(Date date, int index) {
		long msel = date.getTime();
		SimpleDateFormat fm = new SimpleDateFormat("MMddyyyyHHmmssSS");
		msel += index;
		date.setTime(msel);
		String serials = fm.format(date);
		return serials;
	}
	// 检查是否是图片格式
	public static boolean checkIsImage(String imgStr) {
		boolean flag = false;
		if (imgStr != null) {
			if (imgStr.equalsIgnoreCase(".gif")
					|| imgStr.equalsIgnoreCase(".jpg")
					|| imgStr.equalsIgnoreCase(".jpeg")
					|| imgStr.equalsIgnoreCase(".png")) {
				flag = true;
			}
		}
		return flag;
	}
    public static Date StrToDate(String str) throws ParseException{
    	return new SimpleDateFormat("MM/dd/yyyy").parse(str);
    }
    // 取得邮件地址
    public static String getEmployeeMail(IMP1001Service serviceMP1001){
    	StringBuffer mailInfo = new StringBuffer();
    	
    	List<MP1001> mp1001List = serviceMP1001.findAllEmail();
    	for(int i=0,j=mp1001List.size(); i<j; i++){
    		MP1001 mp11 = mp1001List.get(i);
    		
    		if(null == mp11.getMP1001_COMPANY_EMAIL() || mp11.getMP1001_COMPANY_EMAIL().equals("")){
    			continue;
    		}
    		
    		// 如果不是第一个，则补加逗号
    		if(!mailInfo.toString().equals("")){
    			mailInfo.append(",");
    		}
    		mailInfo.append(mp11.getMP1001_COMPANY_EMAIL());
    	}
    	
    	return mailInfo.toString();
    }
	// 判断是否周六日
    public static boolean isWeekEnd(Calendar calendar){
		boolean ret = false;
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
		if(1 == weekDay || 7 == weekDay){
			ret = true;
		}
		
		return ret;
	}
	// 判断是否国家法定假日
	public static boolean isHoliday(Calendar calFrom){
		boolean ret = false;
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		if(session.containsKey("HOLIDAY_DATE")){
			@SuppressWarnings("unchecked")
			Map<String,String> holidayMap = (Map<String, String>) session.get("HOLIDAY_DATE");
			String currentDate = calFrom.get(Calendar.YEAR) + "-" + String.format("%02d",calFrom.get(Calendar.MONTH) + 1) +  "-" + String.format("%02d",calFrom.get(Calendar.DAY_OF_MONTH));
			if(holidayMap.containsKey(currentDate)){
				ret = true;
			}
		}
		
		return ret;
	}
	// 取得财务年份(每年三月~来年的二月为一年)
	public static String getFinanceYear(){
		int _year = Integer.parseInt(UtilDate.getYear());
		String _month = UtilDate.getMonthInYear();
		
		if(_month.equals("1") || _month.equals("2")){
			_year = _year - 1;
		}
		
		return String.valueOf(_year);
	}
	public static String getFinanceYear(String date){
		int _year = Integer.parseInt(date.substring(0,4));
		int _month = Integer.parseInt(date.substring(5,7));
		
		if(_month == 1 || _month == 2){
			_year = _year - 1;
		}
		
		return String.valueOf(_year);
	}
	public static String getPublicHolidayList(IHOLIDAYService serviceHoliday){
		// 取得当年的Holiday
		String _year = getFinanceYear();
		List<HOLIDAY> _holidayList = serviceHoliday.findByProperty("HOLIDAY_YEAR", _year);
		
		String holidayInfo = "";
		for(int i=0,j=_holidayList.size(); i<j; i++){
			HOLIDAY _holiday = _holidayList.get(i);
			
			if(holidayInfo.equals("")){
				holidayInfo += _holiday.getHOLIDAY_DATE();
			}else{
				holidayInfo += ";" + _holiday.getHOLIDAY_DATE();
			}
		}
		
		return holidayInfo;
	}
	// 根据员工编码取得该部门主管的
	public static Map<String,MP1001> getAllManager(IMP1001Service serviceMP1001){
		StringBuffer searchStr = new StringBuffer();
		String orderStr = " order by mp11.MP1001_EMPLOYEE_NUM ";
		Map<String,MP1001> managerMap = new HashMap<String,MP1001>();
		
		searchStr.append(" and mp11.MP1001_POSITION = '1' ");
		searchStr.append(" and  (mp11.MP1001_STATUS='1' or mp11.MP1001_STATUS='2') ");
		
		List<MP1001> mp1001List =  serviceMP1001.findData(searchStr.toString(), orderStr,-1,-1);
		if(mp1001List != null && mp1001List.size() > 0){
			for(int i=0,j=mp1001List.size(); i<j; i++){
				MP1001 mp11 = mp1001List.get(i);
				
				// 如果一个部门有多个人只为Manager，则只取第一个Manager的数据
				if(!managerMap.containsKey(mp11.getMP1001_DEPARTMENT_ID())){
					managerMap.put(mp11.getMP1001_DEPARTMENT_ID(), mp11);
				}
			}
		}
		
		return managerMap;
	}
	public static int calDays(Date dateFrom, Date dateTo){
		int ret = 0;	
		Calendar calFrom = Calendar.getInstance();
		Calendar calTo = Calendar.getInstance();
		
		calFrom.setTime(dateFrom);
		calTo.setTime(dateTo);
		
		while(calFrom.compareTo(calTo) <= 0){
			// 判断是否周六日
			if(true == UtilCommon.isWeekEnd(calFrom)){
				calFrom.add(Calendar.DAY_OF_MONTH,1);//向后推迟一天
				continue;
			}
			// 判断是否国家法定假日
			if(true == UtilCommon.isHoliday(calFrom)){
				calFrom.add(Calendar.DAY_OF_MONTH,1);//向后推迟一天
				continue;
			}
			calFrom.add(Calendar.DAY_OF_MONTH,1);//向后推迟一天
			ret += 8;
		}
		
		return ret;
	}
	// Double转换
	public static String doubleConvertString(BigDecimal bd){
		StringBuffer ret = new StringBuffer();
		int bd1 = bd.divide(new BigDecimal("8")).intValue();
		if(bd1 > 0){
			ret.append(String.valueOf(bd1) + "D");
		}
		BigDecimal bd2 = bd.subtract(new BigDecimal(String.valueOf(bd1*8)));
		if(bd2.compareTo(new BigDecimal("0")) > 0){
			bd2 = bd2.setScale(1, BigDecimal.ROUND_HALF_UP);
			ret.append(bd2.toString() + "H");
		}
		if(ret.toString().trim().equals("")){
			ret.append("-");
		}
		return ret.toString();	
	}
	
	/**
     * 对double数据进行取精度.
     * @param value  double数据.
     * @param scale  精度位数(保留的小数位数).
     * @param roundingMode  精度取值方式.
     * @return 精度计算后的数据.
     */
    public static double round(double value, int scale, int roundingMode) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, roundingMode);
        double d = bd.doubleValue();
        bd = null;
        return d;
    }
     /**
     * double 相加
     * @param d1
     * @param d2
     * @return
     */
    public static double sum(double d1,double d2){
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.add(bd2).doubleValue();
    }
    /**
     * double 相减
     * @param d1
     * @param d2
     * @return
     */
    public static double sub(double d1,double d2){
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.subtract(bd2).doubleValue();
    }
    /**
     * double 乘法
     * @param d1
     * @param d2
     * @return
     */
    public static double mul(double d1,double d2){
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.multiply(bd2).doubleValue();
    }
    /**
     * double 除法
     * @param d1
     * @param d2
     * @param scale 四舍五入 小数点位数
     * @return
     */ 
    public static double div(double d1,double d2,int scale){
        //  当然在此之前，你要判断分母是否为0
        //  为0你可以根据实际需求做相应的处理
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.divide(bd2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
