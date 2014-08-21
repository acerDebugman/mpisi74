package schedule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import common.Constant;
import common.Mail;
import common.UtilCommon;
import common.UtilDate;

import dto.AttendanceRecordDto;
import dto.CheckInOutDto;
import entity.CHECKINOUT;
import entity.MP1001;
import entity.MP2003;
import entity.MP2010;

public class executeJobs {
	//private static final Log log = LogFactory.getLog(AuthorityAction.class);
	
	private ExecuteJobsService serviceExecuteJob;
	
	// 每天凌晨一点自动计算员工上下班异常
//	public void executeJob1() throws SQLException {
//		String parameter ="";
//		Connection connSql = null;
//		//log.info("------Execute Job1 Start------");
//		try {
//			CommonJobMethod.loadDataToHrSystem(true, null);
//			
//			connSql = CommonJobMethod.getDBConnection();
//			List<MP2003> mp2003List =CommonJobMethod.getAllData(connSql,parameter);
//			
//			Map<String,String> mp23Map = new HashMap<String,String>();
//			for(MP2003 mp23 : mp2003List){
//				mp23Map.put(mp23.getMP2003_EMPLOYEE_NUM().toUpperCase() + "#@" +mp23.getMP2003_DATETIME().substring(0,10).trim(), "");
//			}
//			
//			Map<String, MP1001> empDateMap =  CommonJobMethod.getEmployeeInfoList(connSql); //get all employees of normal work day
//			Map<String, MP1001> newEmpDateMap = new HashMap<String, MP1001>();
//			int date1, date2;
//			for(Map.Entry<String, MP1001> entry : empDateMap.entrySet()){ //empDataMap contains all normal records,but it's empty 
//				date1 = Integer.parseInt(entry.getKey().split("#@")[1].replace("-", ""));
//				date2 = Integer.parseInt(entry.getValue().getMP1001_ENTRY_DATE().substring(0,10).replace("-", ""));
//				if(!mp23Map.containsKey(entry.getKey()) && date1 >= date2 ){ //mp23Map contains all the clock records,if it doesn't contains the normal records,the empty record will insert into MP2003 table;   
//					newEmpDateMap.put(entry.getKey(), entry.getValue());
//					System.out.println("Line: key:[" + entry.getKey() + "]  date1: [" + date1 + "] date2: [" + date2 + "]");
//				}
//			}
//			
//			CommonJobMethod.insertEmptyAttendanceRecord(newEmpDateMap, connSql); //insert into empty records
//			System.out.println("insert finish");
//			mp2003List =CommonJobMethod.getAllData(connSql,parameter); //this time will get all mp2003 records
//			CommonJobMethod.dataConvert2003(mp2003List,connSql);
//			//CommonJobMethod.mergeShiftWorkSchedule(mp2003List, connSql);
//			System.out.println("dataConvert finish");
//			CommonJobMethod.update2003(mp2003List,connSql);
//			System.out.println("update finish");
//			
//			//job 11
//			executeJob11();
//			
//			//add one new shift work schedule records 
//			executeJob14();
//			
//			//delete some employuee's attendance information, exclude all shift worker's
//			CommonJobMethod.deleteSpecialEmployeesAbnormal(connSql);
//
//			connSql.close();
//			//send abnormal emails, as shift workers have no emails, so send to their manager. 
//			//get all yesterday Abnormal email to manager, not only for shiftworker
//			CommonJobMethod.sendAbnormalEmails();
//			
//			//job 12
////			executeJob12();
//			
//			//email info all success did
//			Mail mail = new Mail();
//			String to = Constant.ADMIN_MAIL;
//			mail.setSubject("Auto Calculate Attendance Records");
//			mail.setContent("Dear Colleagues,\r\n \r\n Please note that The batch has been executed at " + UtilDate.get24DateTime());
//			mail.setTo(to);
//			mail.send();
//			
//			//log.info("------Execute Job1 Finish------");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			//log.info(e.getMessage());
//		} catch (SQLException e) {
//			e.printStackTrace();
//			//log.info(e.getMessage());
//		}catch(ParseException pe){
//			pe.printStackTrace();
//			//log.info(pe.getMessage());
//		}
//		finally{
//			if(connSql!=null){
//				connSql.close();
//			}
//		}
//	}
//	
	//only for send email now~
	public void executeJob1() throws SQLException {
		String parameter ="";
		Connection connSql = null;
		//log.info("------Execute Job1 Start------");
		try {
			
			executeJob11(); //execute shift worker calcuale here (temporary)
			
//			serviceExecuteJob.
			Date today = new Date();
			serviceExecuteJob.sendAbnormalEmailToEmployee(today);
			CommonJobMethod.sendAbnormalEmailsToManager();
			
			//email info all success did
			Mail mail = new Mail();
			String to = Constant.ADMIN_MAIL;
			mail.setSubject("Joe New Test Abnormal Attendance Records");
			mail.setContent("Dear Colleagues,\r\n \r\n Please note that The batch has been executed at " + UtilDate.get24DateTime());
			mail.setTo(to);
//			mail.send();
			mail.sendTextHtml();
			
			//log.info("------Execute Job1 Finish------");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//log.info(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			//log.info(e.getMessage());
//		}catch(ParseException pe){
//			pe.printStackTrace();
			//log.info(pe.getMessage());
		}
		finally{
			if(connSql!=null){
				connSql.close();
			}
		}
	}
	// 每个月20日的凌晨1点，自动重置下个月各个部门的预算数据
	public void executeJob2() throws SQLException{
		String currentYear = UtilDate.getYear();
		//String currentMonth = UtilDate.getMonthInYear();
		String currentMonth = UtilDate.getNextMonthInYear();
		//int preMonth = (currentMonth == 1 ? 12 : currentMonth - 1);
		if(!currentMonth.equals("") && currentMonth.equals("1")){
			currentYear = String.valueOf(Integer.parseInt(currentYear) + 1);
		}
		Connection connSql = null;
		
		try {
			connSql = CommonJobMethod.getDBConnection();
			
			CommonJobMethod.updateMP4003Template(connSql, currentYear, currentMonth);
			CommonJobMethod.insertMP4003(connSql);
			
			connSql.close();
			
			// 发邮件通知本人密码已经重置
			Mail mail = new Mail();
			String to = Constant.ADMIN_MAIL;
			mail.setSubject("Auto Reset Department Budget");
			mail.setContent("Dear Colleagues,\r\n \r\n Please note that The batch has been executed at " + UtilDate.get24DateTime());
			mail.setTo(to);
			mail.send();
			
			System.out.println("自动重置部门预算成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception ex){
		    System.out.println(ex.getMessage());
		}
		finally{
			if(connSql!=null){
				connSql.close();
			}
		}
	}
	// 测试任务
	public void executeJob3() throws ClassNotFoundException, SQLException, ParseException{
		//System.out.println("Job3: " + UtilDate.get24DateTime());
		CommonJobMethod.tempJob();
	}
	// 重置请假单序列号(每天凌晨重置序列号为0)
	public void executeJob4() throws SQLException{
		Connection connSql = null;
		
		try {
			connSql = CommonJobMethod.getDBConnection();
			
			StringBuffer sb = new StringBuffer();
			Statement stSql = connSql.createStatement();
			
			sb.append(" Update  MP0006  Set  MP0006_VALUE = 0  Where MP0006_CODE='LEAVE_NUM' ");
			stSql.executeUpdate(sb.toString());
			stSql.close();
			
			connSql.close();
			
			// 发邮件通知该处理已经执行
			Mail mail = new Mail();
			String to = Constant.ADMIN_MAIL;
			mail.setSubject("Auto Reset Leave Sequence Number");
			mail.setContent("Dear Colleagues,\r\n \r\n Please note that The batch has been executed at " + UtilDate.get24DateTime());
			mail.setTo(to);
			mail.send();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			if(connSql!=null){
				connSql.close();
			}
		}
	}
	// 重置采购单序列号(每月的最后一天凌晨重置序列号为0)
	public void executeJob5() throws SQLException{
		Connection connSql = null;
		
		try {
			connSql = CommonJobMethod.getDBConnection();
			
			StringBuffer sb = new StringBuffer();
			Statement stSql = connSql.createStatement();
			
			sb.append(" Update  MP0006  Set  MP0006_VALUE = 0  Where MP0006_CODE='REQUISITION_NUM' ");
			stSql.executeUpdate(sb.toString());
			stSql.close();
			
			connSql.close();
			
			// 发邮件通知该处理已经执行
			Mail mail = new Mail();
			String to = Constant.ADMIN_MAIL;
			mail.setSubject("Auto Reset Purchase Sequence Number");
			mail.setContent("Dear Colleagues,\r\n \r\n Please note that The batch has been executed at " + UtilDate.get24DateTime());
			mail.setTo(to);
			mail.send();
			
			System.out.println("自动重置采购单编码成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		finally{
			if(connSql!=null){
				connSql.close();
			}
		}
	}
	// 每月累加年假(每月的最后一天凌晨追加本月的年假)
	public void executeJob6() throws SQLException{
		Connection connSql = null;
		String cm = UtilDate.getMonthInYear();
		try {
			connSql = CommonJobMethod.getDBConnection();
			String empInfoList = CommonJobMethod.getSecurityInfoList(connSql);
			String annualStatusList = CommonJobMethod.getAnnualStatusList(connSql);
			
			StringBuffer sb = new StringBuffer();
			StringBuffer employeeStr = new StringBuffer();
			StringBuffer annualStatusStr = new StringBuffer();
			Statement stSql = connSql.createStatement();
			
			// 普通员工每个月年假为：1天2小时=10小时where MP2002_YEAR = '" + UtilCommon.getFinanceYear() + "' ");
			// 公司保安每个月年假为：1天6小时=14小时
			//if(cm.equals("3") || cm.equals("4") || cm.equals("5") || cm.equals("6") || cm.equals("7") || cm.equals("8") || cm.equals("9") || cm.equals("10") || cm.equals("11")){
			if(cm.equals("3") || cm.equals("4") || cm.equals("5") || cm.equals("6") || cm.equals("7") || cm.equals("8") || cm.equals("9") || cm.equals("10")){
				sb.append(" update MP2002 set MP2002_ANNUAL = MP2002_ANNUAL + 10, MP2002_ANNUAL_T = MP2002_ANNUAL_T + 10 ");
				employeeStr.append(" update MP2002 set MP2002_ANNUAL = MP2002_ANNUAL + 4, MP2002_ANNUAL_T = MP2002_ANNUAL_T + 4 ");
				annualStatusStr.append(" update MP2002 set MP2002_ANNUAL = MP2002_ANNUAL - 10, MP2002_ANNUAL_T = MP2002_ANNUAL_T - 10 ");
			}else if(cm.equals("11")){
				//sb.append(" update MP2002 set MP2002_ANNUAL = MP2002_ANNUAL + 30,MP2002_ANNUAL_T = MP2002_ANNUAL_T + 30 ");
				sb.append(" update MP2002 set MP2002_ANNUAL = MP2002_ANNUAL + 40,MP2002_ANNUAL_T = MP2002_ANNUAL_T + 40 ");
				employeeStr.append(" update MP2002 set MP2002_ANNUAL = MP2002_ANNUAL + 16, MP2002_ANNUAL_T = MP2002_ANNUAL_T + 16 ");
				annualStatusStr.append(" update MP2002 set MP2002_ANNUAL = MP2002_ANNUAL - 40, MP2002_ANNUAL_T = MP2002_ANNUAL_T - 40 ");
			}
			sb.append(" where MP2002_YEAR = '" + UtilCommon.getFinanceYear() + "' ");
			stSql.executeUpdate(sb.toString());

			if(empInfoList != null && !empInfoList.equals("")){
				employeeStr.append(" where MP2002_EMPLOYEE_NUM in (" + empInfoList + ") " );
				employeeStr.append(" and MP2002_YEAR = '" + UtilCommon.getFinanceYear() + "' ");
				stSql.executeUpdate(employeeStr.toString());
			}
			if(annualStatusList != null && !annualStatusList.equals("")){
				annualStatusStr.append(" where MP2002_EMPLOYEE_NUM in (" + annualStatusList + ") " );
				annualStatusStr.append(" and MP2002_YEAR = '" + UtilCommon.getFinanceYear() + "' ");
				stSql.executeUpdate(annualStatusStr.toString());
			}
			stSql.close();
			
			connSql.close();
			
			// 发邮件通知该处理已经执行
			Mail mail = new Mail();
			String to = Constant.ADMIN_MAIL;
			mail.setSubject("Auto Add Annual Leave Days");
			mail.setContent("Dear Colleagues,\r\n \r\n Please note that The batch has been executed at " + UtilDate.get24DateTime());
			mail.setTo(to);
			mail.send();
			
			//System.out.println("自动累加年假成功");
			System.out.println("add annual success~");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		finally{
			if(connSql!=null){
				connSql.close();
			}
		}
	}
	// 每年三月份重置员工假期
	public void executeJob7() throws SQLException{
		Connection connSql = null;
		try {
			connSql = CommonJobMethod.getDBConnection();
			
			StringBuffer sb = new StringBuffer();
			Statement statement = connSql.createStatement();
			
			sb.append(" select mp11.MP1001_EMPLOYEE_NUM ");
			sb.append(" from MP1001 mp11 ");
			sb.append(" where 1=1 ");
			sb.append(" and mp11.MP1001_STATUS in ('1','2') "); // 1:临时员工 2:正式员工

			ResultSet rs = statement.executeQuery(sb.toString());
			
			StringBuffer updateSql = new StringBuffer();
			StringBuffer failInfoList = new StringBuffer();
			List<String> employeeNumList = new ArrayList<String>();
			while(rs.next()){
				String mp1001EmployeeNum = rs.getString("MP1001_EMPLOYEE_NUM"); // 员工编码
				employeeNumList.add(mp1001EmployeeNum);
			}
			rs.close();
			
			for(int i=0,j=employeeNumList.size(); i<j; i++){
				updateSql = new StringBuffer();
				updateSql.append(" insert into MP2002 ");
				updateSql.append(" ( ");
				updateSql.append(" MP2002_EMPLOYEE_NUM,  ");
				updateSql.append(" MP2002_YEAR,  ");
				updateSql.append(" MP2002_ANNUAL,  ");
				updateSql.append(" MP2002_ANNUAL_T,  ");
				updateSql.append(" MP2002_SICK,  ");
				updateSql.append(" MP2002_SICK_T,  ");
				updateSql.append(" MP2002_FAMILY_RESP,  ");
				updateSql.append(" MP2002_FAMILY_RESP_T,  ");
				updateSql.append(" MP2002_MATERNITY,  ");
				updateSql.append(" MP2002_MATERNITY_T,  ");
				updateSql.append(" MP2002_STUDY,  ");
				updateSql.append(" MP2002_STUDY_T ");
				updateSql.append(" ) ");
				updateSql.append(" values( ");
				updateSql.append(" '" + employeeNumList.get(i) + "', "); // MP2002_EMPLOYEE_NUM
				updateSql.append(" '" + UtilCommon.getFinanceYear() + "', "); // MP2002_YEAR
				updateSql.append(" '0', "); // MP2002_ANNUAL
				updateSql.append(" '0', "); // MP2002_ANNUAL_T
				updateSql.append(" '96', "); // MP2002_SICK
				updateSql.append(" '96', "); // MP2002_SICK_T
				updateSql.append(" '24', "); // MP2002_FAMILY_RESP
				updateSql.append(" '24', "); // MP2002_FAMILY_RESP_T
				updateSql.append(" '0', "); // MP2002_MATERNITY
				updateSql.append(" '0', "); // MP2002_MATERNITY_T
				updateSql.append(" '0', "); // MP2002_STUDY
				updateSql.append(" '0' "); // MP2002_STUDY_T
				updateSql.append(" ) ");
				
				try{
					statement.executeUpdate(updateSql.toString());
				}catch(Exception ex){
					failInfoList.append(" " + employeeNumList.get(i) + "\r\n " );
				}
			}
			
			// 所有员工的年假累加状态重置为1
			statement.executeUpdate("update MP1001 set MP1001_ANNUAL_STATUS = '1' ");
			statement.close();
			
			connSql.close();
			
			// 发邮件通知该处理已经执行
			Mail mail = new Mail();
			String to = Constant.ADMIN_MAIL;
			StringBuffer content = new StringBuffer();
			content.append("Dear Colleagues,\r\n \r\n Please note that The batch has been executed at " + UtilDate.get24DateTime() + " \r\n");
			// 未能成功插入人员列表
			content.append(failInfoList.toString());
			
			mail.setSubject("Auto Add Annual Leave Days");
			mail.setContent(content.toString());
			mail.setTo(to);
			mail.send();
			
			System.out.println("每年三月第一天凌晨，自动重置年假成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		finally{
			if(connSql!=null){
				connSql.close();
			}
		}
	}
	// 每天早晨8点钟自动发邮件给部门主管，汇报本部门所有员工工作进度
	public void executeJob8() throws SQLException{
		Connection connSql = null;
		try {
			connSql = CommonJobMethod.getDBConnection();
			
			String _year = UtilDate.getYear();
			String _month = UtilDate.getMonthInYear();
			String _day = UtilDate.getDayInMonth();
			
			if(_month.length() == 1){
				_month = "0" + _month;
			}
			if(_day.length() == 1){
				_day = "0" + _day;
			}
			
			Map<String,String> departmentManagerEmailMap = CommonJobMethod.getDepartmentManagerEmail(connSql);
			Map<String,String> departmentScheduleInfoMap = CommonJobMethod.getDepartmentScheduleInfo(connSql, _year, _month, _day);
			
			StringBuffer sb = new StringBuffer();
			Statement statement = connSql.createStatement();
			
			sb.append(" select MP0002_SEQ ");
			sb.append(" from MP0002 ");
			sb.append(" where 1=1 ");
			sb.append(" and MP0002_DEPARTMENT_STATUS = '1' "); // 0:无效部门 1:有效部门

			ResultSet rs = statement.executeQuery(sb.toString());

			while(rs.next()){
				String mp0002Seq = rs.getString("MP0002_SEQ"); // 员工编码
				String mailList = "";
				String titleList = "";
				if(departmentManagerEmailMap.containsKey(mp0002Seq)){
					mailList = departmentManagerEmailMap.get(mp0002Seq);
				}
				if(departmentScheduleInfoMap.containsKey(mp0002Seq)){
					titleList = departmentScheduleInfoMap.get(mp0002Seq);
				}
				//mailList += ",tim@mpisi.com";
				if(!mailList.equals("") && !titleList.equals("")){
					// 发邮件通知该处理已经执行
					Mail mail = new Mail();
					mail.setSubject("Employee Schedule(unfinished)");
					mail.setContent(titleList);
					mail.setTo(mailList);
					mail.send();
				}
			}
			rs.close();
			
			// 发邮件通知任务已完成
			CommonJobMethod.sedMailToAdmin("Dear Colleagues,\r\n \r\n Please note that The batch has been executed at " + UtilDate.get24DateTime(),"Schedul Management System's Message");
			
			statement.close();
			connSql.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		finally{
			if(connSql!=null){
				connSql.close();
			}
		}
	}
	// 每月自动发邮件给部门主管，通知各部门本月生日员工列表
	public void executeJob9() throws SQLException{
		Connection connSql = null;
		try {
			connSql = CommonJobMethod.getDBConnection();
			String _month = UtilDate.getMonthInYear();
			
			Map<String,String> departmentBirthdayMap = CommonJobMethod.getDepartmentBirthdayInfo(connSql,_month);
			Map<String,String> departmentManagerEmailMap = CommonJobMethod.getDepartmentManagerEmail(connSql);
			
			StringBuffer sb = new StringBuffer();
			Statement statement = connSql.createStatement();
			
			sb.append(" select MP0002_SEQ ");
			sb.append(" from MP0002 ");
			sb.append(" where 1=1 ");
			sb.append(" and MP0002_DEPARTMENT_STATUS = '1' "); // 0:无效部门 1:有效部门

			ResultSet rs = statement.executeQuery(sb.toString());

			while(rs.next()){
				String mp0002Seq = rs.getString("MP0002_SEQ");
				String mailList = "";
				String titleList = "";
				if(departmentManagerEmailMap.containsKey(mp0002Seq)){
					mailList = departmentManagerEmailMap.get(mp0002Seq);
				}
				if(departmentBirthdayMap.containsKey(mp0002Seq)){
					titleList = departmentBirthdayMap.get(mp0002Seq);
				}
				if(!mailList.equals("") && !titleList.equals("")){
					// 发邮件通知该处理已经执行
					Mail mail = new Mail();
					mail.setSubject("Birthday List");
					mail.setContent(titleList);
					mail.setTo(mailList);
					mail.send();
				}
			}
			rs.close();
			
			// 发邮件通知任务已完成
			CommonJobMethod.sedMailToAdmin("Dear Colleagues,\r\n \r\n Please note that The batch has been executed at " + UtilDate.get24DateTime(),"Employee Birthday Mail");
			
			statement.close();
			connSql.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		finally{
			if(connSql!=null){
				connSql.close();
			}
		}
	}
	
	public void executeJob10() throws SQLException{
		Connection connSql = null;
		try{
			connSql = CommonJobMethod.getDBConnection();
			StringBuffer sb = new StringBuffer();
			sb.append(" delete from JE0101 where JE0101_TYPE='FORGETPSWD' ");
			Statement st = connSql.createStatement();
			//st.executeQuery(sb.toString());
			st.executeUpdate(sb.toString());
			
			st.close();
			
			// 发邮件通知本人密码已经修改
			Mail mail = new Mail();
			String to = "joe_zhang@mpisi.com";
			mail.setSubject("Tempory Password Clean");
			mail.setContent("Dear Colleagues,<br/>&nbsp;&nbsp;&nbsp;All tempory password has been cleaned! <br />");
			mail.setTo(to);
			//mail.send();
			mail.sendTextHtml();
			
		} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		finally{
			if(connSql!=null){
				connSql.close();
			}
		}
	}
	
	//executeJob11 for shift work calculate
	public void executeJob11() throws SQLException {
		try{
			//get employee number set, and which date
			Date date = new Date();
			
			Set<String> employeeNumSet = CommonJobMethod.getAllShiftWorkEmployeeNums(date);
			
			//get specify day's employee attendance records from CHECKINOUT table
			List<CheckInOutDto> checkInOutRecordsList = CommonJobMethod.getAllAttendanceRecords(employeeNumSet);
			
			Map<String, MP2010> mapShiftworkRecords = CommonJobMethod.getAllShiftArrangeRecords(); //get all mp2010 records
			List<AttendanceRecordDto> dailyRecords = CommonJobMethod.separateIntoEachDays(checkInOutRecordsList); //get all checkInOut records
			
			//compare time and calculate attendance status
			List<MP2003> calculatedAttendanceRcdList= CommonJobMethod.calculateAttendanceRecordStatus(dailyRecords, employeeNumSet, mapShiftworkRecords); //compare all daily clock in out records 
			
			//listMP2003
			
			//delete old records
			CommonJobMethod.deleteOldAttendanceRcd(employeeNumSet);
			
			//insert new records
			CommonJobMethod.insertCalculatedAttendanceRcd(calculatedAttendanceRcdList);
		
		} catch (ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	
	//only for test
	public void executeJob12(){
		try{
			//CommonJobMethod.sendAbnormalEmails();
			System.out.println("abnormal test!");
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}

	//copy of executeJob1,but can not send email, only for autoload data function use 
	public void executeJob13() throws SQLException {
		String parameter ="";
		Connection connSql = null;
		//log.info("------Execute Job1 Start------");
		try {
			CommonJobMethod.loadDataToHrSystem(true, null);
			
			connSql = CommonJobMethod.getDBConnection();
			List<MP2003> mp2003List =CommonJobMethod.getAllData(connSql,parameter);
			
			Map<String,String> mp23Map = new HashMap<String,String>();
			for(MP2003 mp23 : mp2003List){
				mp23Map.put(mp23.getMP2003_EMPLOYEE_NUM().toUpperCase() + "#@" +mp23.getMP2003_DATETIME().substring(0,10).trim(), "");
			}
			
			Map<String, MP1001> empDateMap =  CommonJobMethod.getEmployeeInfoList(connSql); //get all employees of normal work day
			Map<String, MP1001> newEmpDateMap = new HashMap<String, MP1001>();
			int date1, date2;
			for(Map.Entry<String, MP1001> entry : empDateMap.entrySet()){ //empDataMap contains all normal records,but it's empty 
				date1 = Integer.parseInt(entry.getKey().split("#@")[1].replace("-", ""));
				date2 = Integer.parseInt(entry.getValue().getMP1001_ENTRY_DATE().substring(0,10).replace("-", ""));
				if(!mp23Map.containsKey(entry.getKey()) && date1 >= date2 ){ //mp23Map contains all the clock records,if it doesn't contains the normal records,the empty record will insert into MP2003 table;   
					newEmpDateMap.put(entry.getKey(), entry.getValue());
//					System.out.println("Line: key:[" + entry.getKey() + "]  date1: [" + date1 + "] date2: [" + date2 + "]");
				}
			}
			
			CommonJobMethod.insertEmptyAttendanceRecord(newEmpDateMap, connSql); //insert into empty records
			System.out.println("insert finish");
			mp2003List =CommonJobMethod.getAllData(connSql,parameter); //this time will get all mp2003 records
			CommonJobMethod.dataConvert2003(mp2003List,connSql);
			//CommonJobMethod.mergeShiftWorkSchedule(mp2003List, connSql);
			System.out.println("dataConvert finish");
			
			CommonJobMethod.update2003WithoutSendEmail(mp2003List,connSql);
			
			System.out.println("update finish");
			
			//job 11
			executeJob11();
			
			//delete some employuee's attendance information, exclude all shift worker's
			CommonJobMethod.deleteSpecialEmployeesAbnormal(connSql);

			connSql.close();
			//send abnormal emails, as shift workers have no emails, so send to their manager. 
			//get all yesterday Abnormal email to manager, not only for shiftworker
//			CommonJobMethod.sendAbnormalEmails();
			
			//job 12
//			executeJob12();
			
			//email info all success did
			Mail mail = new Mail();
			String to = Constant.ADMIN_MAIL;
			mail.setSubject("Auto Calculate Attendance Records");
			mail.setContent("Dear Colleagues,\r\n \r\n Please note that The batch has been executed at " + UtilDate.get24DateTime());
			mail.setTo(to);
			mail.send();
			
			//log.info("------Execute Job1 Finish------");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//log.info(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			//log.info(e.getMessage());
		}catch(ParseException pe){
			pe.printStackTrace();
			//log.info(pe.getMessage());
		}
		finally{
			if(connSql!=null){
				connSql.close();
			}
		}		
	}
	
	public void executeJob14(){
		try{
			Connection conn = CommonJobMethod.getDBConnection();
			Statement st = conn.createStatement();
			
			StringBuffer sb = new StringBuffer();
			List<String> employeeList = new ArrayList<String>();
			
			sb.append("select distinct(MP2010_EMPLOYEE_NUM) from MP2010 ");
			ResultSet rs = st.executeQuery(sb.toString());
			while(rs.next()){
				employeeList.add(rs.getString("MP2010_EMPLOYEE_NUM"));
			}

			//set time to next day
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			
			//iterator every shift worker
			List<MP2010> tmpList = new ArrayList<MP2010>();
			for(int i = 0, j = employeeList.size(); i < j; i++){
				
				sb.delete(0, sb.length());
				sb.append("select top 1 MP2010_DATE from MP2010 where MP2010_EMPLOYEE_NUM='" + employeeList.get(i)  + "' " + 
						"order by MP2010_DATE desc");
				rs = st.executeQuery(sb.toString());
				rs.next();
				cal.setTime(sdf.parse(rs.getString(1))); //the lastest day
				cal.add(Calendar.DAY_OF_MONTH, 1); //set to next day
				
				sb.delete(0, sb.length());
				sb.append("select top 3 * from MP2010 where mp2010_EMPLOYEE_NUM='");
				sb.append(employeeList.get(i) + "' order by MP2010_DATE desc;");

				rs = st.executeQuery(sb.toString());
				while(rs.next()){
					MP2010 mp21 = new MP2010();
					mp21.setMP2010_TYPE(rs.getString("MP2010_TYPE"));
					mp21.setMP2010_BRANCH_SITE(rs.getString("MP2010_BRANCH_SITE"));
					mp21.setMP2010_DATE(rs.getString("MP2010_DATE"));
					mp21.setMP2010_EMPLOYEE_NUM(rs.getString("MP2010_EMPLOYEE_NUM"));
					mp21.setMP2010_FROM_DATETIME(rs.getString("MP2010_FROM_DATETIME"));
					mp21.setMP2010_END_DATETIME(rs.getString("MP2010_END_DATETIME"));
					mp21.setMP2010_ID(rs.getInt("MP2010_ID"));
					
					tmpList.add(mp21);
				}
				
				MP2010 nextRcd = new MP2010();
				nextRcd.setMP2010_DATE(sdf.format(cal.getTime()));
				nextRcd.setMP2010_BRANCH_SITE(tmpList.get(0).getMP2010_BRANCH_SITE());
				nextRcd.setMP2010_EMPLOYEE_NUM(tmpList.get(0).getMP2010_EMPLOYEE_NUM());
				nextRcd.setMP2010_FROM_DATETIME(tmpList.get(0).getMP2010_FROM_DATETIME());
				nextRcd.setMP2010_END_DATETIME(tmpList.get(0).getMP2010_END_DATETIME());
				if(tmpList.get(0).getMP2010_TYPE().equalsIgnoreCase("D") && 		//tmpList.get(0) is the latest one
						tmpList.get(1).getMP2010_TYPE().equalsIgnoreCase("D") && 
							tmpList.get(2).getMP2010_TYPE().equalsIgnoreCase("D")){
						nextRcd.setMP2010_TYPE("N");
						nextRcd.setMP2010_FROM_DATETIME(sdf2.format(cal.getTime()) + " " + Constant.shiftWorkNightStartTime);
						cal.add(Calendar.DAY_OF_MONTH, 1);
						nextRcd.setMP2010_END_DATETIME(sdf2.format(cal.getTime()) + " " + Constant.shiftWorkNightEndTime);
						cal.add(Calendar.DAY_OF_MONTH, -1);
				}
				if(tmpList.get(0).getMP2010_TYPE().equalsIgnoreCase("R") && 
						tmpList.get(1).getMP2010_TYPE().equalsIgnoreCase("R") && 
							tmpList.get(2).getMP2010_TYPE().equalsIgnoreCase("R")){
						nextRcd.setMP2010_TYPE("D");
						nextRcd.setMP2010_FROM_DATETIME(sdf2.format(cal.getTime()) + " " + Constant.shiftWorkDayStartTime);
						nextRcd.setMP2010_END_DATETIME(sdf2.format(cal.getTime()) + " " + Constant.shiftWorkDayEndTime);
				}
				if(tmpList.get(0).getMP2010_TYPE().equalsIgnoreCase("N") && 
						tmpList.get(1).getMP2010_TYPE().equalsIgnoreCase("N") && 
							tmpList.get(2).getMP2010_TYPE().equalsIgnoreCase("N")){
						nextRcd.setMP2010_TYPE("R");
						nextRcd.setMP2010_FROM_DATETIME(sdf2.format(cal.getTime()));
						nextRcd.setMP2010_END_DATETIME(sdf2.format(cal.getTime()));
				}
				if(tmpList.get(0).getMP2010_TYPE().equalsIgnoreCase("D") && 
						tmpList.get(1).getMP2010_TYPE().equalsIgnoreCase("D") && 
							tmpList.get(2).getMP2010_TYPE().equalsIgnoreCase("R")){
						nextRcd.setMP2010_TYPE("D");
						nextRcd.setMP2010_FROM_DATETIME(sdf2.format(cal.getTime()) + " " + Constant.shiftWorkDayStartTime);
						nextRcd.setMP2010_END_DATETIME(sdf2.format(cal.getTime()) + " " + Constant.shiftWorkDayEndTime);
				}
				if(tmpList.get(0).getMP2010_TYPE().equalsIgnoreCase("D") && 
						tmpList.get(1).getMP2010_TYPE().equalsIgnoreCase("R") && 
							tmpList.get(2).getMP2010_TYPE().equalsIgnoreCase("R")){
						nextRcd.setMP2010_TYPE("D");
						nextRcd.setMP2010_FROM_DATETIME(sdf2.format(cal.getTime()) + " " + Constant.shiftWorkDayStartTime);
						nextRcd.setMP2010_END_DATETIME(sdf2.format(cal.getTime()) + " " + Constant.shiftWorkDayEndTime);
				}
				if(tmpList.get(0).getMP2010_TYPE().equalsIgnoreCase("R") && 
						tmpList.get(1).getMP2010_TYPE().equalsIgnoreCase("N") && 
							tmpList.get(2).getMP2010_TYPE().equalsIgnoreCase("N")){
						nextRcd.setMP2010_TYPE("R");
						nextRcd.setMP2010_FROM_DATETIME(sdf2.format(cal.getTime()));
						nextRcd.setMP2010_END_DATETIME(sdf2.format(cal.getTime()));
				}
				if(tmpList.get(0).getMP2010_TYPE().equalsIgnoreCase("R") && 
						tmpList.get(1).getMP2010_TYPE().equalsIgnoreCase("R") && 
							tmpList.get(2).getMP2010_TYPE().equalsIgnoreCase("N")){
						nextRcd.setMP2010_TYPE("R");
						nextRcd.setMP2010_FROM_DATETIME(sdf2.format(cal.getTime()));
						nextRcd.setMP2010_END_DATETIME(sdf2.format(cal.getTime()));
				}
				if(tmpList.get(0).getMP2010_TYPE().equalsIgnoreCase("N") && 
						tmpList.get(1).getMP2010_TYPE().equalsIgnoreCase("D") && 
							tmpList.get(2).getMP2010_TYPE().equalsIgnoreCase("D")){
						nextRcd.setMP2010_TYPE("N");
						nextRcd.setMP2010_FROM_DATETIME(sdf2.format(cal.getTime()) + " " + Constant.shiftWorkNightStartTime);
						cal.add(Calendar.DAY_OF_MONTH, 1);
						nextRcd.setMP2010_END_DATETIME(sdf2.format(cal.getTime()) + " " + Constant.shiftWorkNightEndTime);
						cal.add(Calendar.DAY_OF_MONTH, -1);
				}
				if(tmpList.get(0).getMP2010_TYPE().equalsIgnoreCase("N") && 
						tmpList.get(1).getMP2010_TYPE().equalsIgnoreCase("N") && 
							tmpList.get(2).getMP2010_TYPE().equalsIgnoreCase("D")){
						nextRcd.setMP2010_TYPE("N");
						nextRcd.setMP2010_FROM_DATETIME(sdf2.format(cal.getTime()) + " " + Constant.shiftWorkNightStartTime);
						cal.add(Calendar.DAY_OF_MONTH, 1);
						nextRcd.setMP2010_END_DATETIME(sdf2.format(cal.getTime()) + " " + Constant.shiftWorkNightEndTime);
						cal.add(Calendar.DAY_OF_MONTH, -1);
				}
				
				sb.delete(0, sb.length());
				sb.append("insert into MP2010(MP2010_EMPLOYEE_NUM, MP2010_TYPE, MP2010_DATE, MP2010_BRANCH_SITE, MP2010_FROM_DATETIME, MP2010_END_DATETIME) values(");
				sb.append("'" + nextRcd.getMP2010_EMPLOYEE_NUM() + "',");
				sb.append("'" + nextRcd.getMP2010_TYPE() + "',");
				sb.append("'" + nextRcd.getMP2010_DATE() + "',");
				sb.append("'" + nextRcd.getMP2010_BRANCH_SITE() + "',");
				sb.append("'" + nextRcd.getMP2010_FROM_DATETIME() + "',");
				sb.append("'" + nextRcd.getMP2010_END_DATETIME() + "')");
				
				st.execute(sb.toString()); //insert into MP2010
				
				tmpList.clear(); //clear all records
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//for change shift worker's status 
	public void executeJob15(){
		try{
//			Connection conn = CommonJobMethod.getDBConnection();
//			Statement st = conn.createStatement();
//			StringBuffer sb = new StringBuffer();
			
			//get 
//			sb.append("");

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
//	//temporary work, adjust MP2010_night_end time 
//	public void executeJob16(){
//		try{
//			Connection conn = CommonJobMethod.getDBConnection();
//			Statement st = conn.createStatement();
//			StringBuffer sb = new StringBuffer();
//			sb.append("select * from MP2010 where MP2010_TYPE='N'");
//			
//			ResultSet rs = st.executeQuery(sb.toString());
//			List<MP2010> rcdList = new ArrayList<MP2010>();
//			while(rs.next()){
//				MP2010 mp21 = new MP2010();
//				mp21.setMP2010_TYPE(rs.getString("MP2010_TYPE"));
//				mp21.setMP2010_BRANCH_SITE(rs.getString("MP2010_BRANCH_SITE"));
//				mp21.setMP2010_DATE(rs.getString("MP2010_DATE"));
//				mp21.setMP2010_EMPLOYEE_NUM(rs.getString("MP2010_EMPLOYEE_NUM"));
//				mp21.setMP2010_FROM_DATETIME(rs.getString("MP2010_FROM_DATETIME"));
//				mp21.setMP2010_END_DATETIME(rs.getString("MP2010_END_DATETIME"));
//				mp21.setMP2010_ID(rs.getInt("MP2010_ID"));
//				
//				rcdList.add(mp21);
//			}
//			
//			Calendar cal = Calendar.getInstance();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			for(int i = 0, j = rcdList.size(); i < j; i++){
//				MP2010 mp21 = rcdList.get(i);
//				Date d1 = sdf.parse(mp21.getMP2010_DATE());
//				Date d2 = sdf.parse(mp21.getMP2010_END_DATETIME());
//				if(d1.equals(d2)){
//					cal.setTime(d1);
//					cal.add(Calendar.DAY_OF_MONTH, 1);
//					mp21.setMP2010_END_DATETIME(sdf.format(cal.getTime()) + " " + Constant.shiftWorkNightEndTime);
//				}
//				
//				sb.delete(0, sb.length());
//				sb.append("update mp2010 set mp2010_end_datetime='" + mp21.getMP2010_END_DATETIME() + "' where mp2010_id=" + mp21.getMP2010_ID());
//				st.executeUpdate(sb.toString());
//			}
//			
////			st.executeUpdate();
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	
	public void executeJob17(){
		List<CHECKINOUT> lst = serviceExecuteJob.getManagerCheckInRcds();
		String str = serviceExecuteJob.produceMngMeetingMailContent(lst);
		
		Mail mail = new Mail();
//		mail.setTo(Constant.managerMeetingGroupEmailList);
		mail.setTo("ManagementGroup@mpisi.com"); //to ManagementGroup, can configure all receiver mail addr at exchange server
//		mail.setTo("joe_zhang@mpisi.com"); 
		mail.setSubject("Manager Meeting Attendance");
		mail.setContent(str);
		mail.sendTextHtml();
	}
	
	
	//fetch data every 5 miniuts
	public void executeJob18(){
		try{
//			serviceExecuteJob.fetchAttendanceRecords();
//			serviceExecuteJob.test();
			serviceExecuteJob.test_2();
//			serviceExecuteJob.procudeTomorrowStandardWorktime()
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public void executeJob19(){
		try{
			serviceExecuteJob.fetchAttendanceRecords();
//			serviceExecuteJob.fetchTodayIncrementAttendanceRecords();
			
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}

	//every 2 hours fetch data from other branch site, start from 06:00 to 22:00 
	public void executeJob20(){
		try{
			serviceExecuteJob.fetchOtherBranchSiteRecords();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	
	//----------------------------------------------------------------------------
	
	public ExecuteJobsService getServiceExecuteJob() {
		return serviceExecuteJob;
	}
	public void setServiceExecuteJob(ExecuteJobsService serviceExecuteJob) {
		this.serviceExecuteJob = serviceExecuteJob;
	}
	
	
}