package schedule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
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
import entity.MP1001;
import entity.MP2003;

public class executeJobs {
	//private static final Log log = LogFactory.getLog(AuthorityAction.class);
	
	// 每天凌晨一点自动计算员工上下班异常
	public void executeJob1() throws SQLException {
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
					System.out.println("Line: key:[" + entry.getKey() + "]  date1: [" + date1 + "] date2: [" + date2 + "]");
				}
			}
			
			CommonJobMethod.insertEmptyAttendanceRecord(newEmpDateMap, connSql); //insert into empty records
			System.out.println("insert finish");
			mp2003List =CommonJobMethod.getAllData(connSql,parameter); //this time will get all mp2003 records
			CommonJobMethod.dataConvert2003(mp2003List,connSql);
			//CommonJobMethod.mergeShiftWorkSchedule(mp2003List, connSql);
			System.out.println("dataConvert finish");
			CommonJobMethod.update2003(mp2003List,connSql);
			System.out.println("update finish");
			
			connSql.close();
			
			// 发邮件通知本人密码已经重置
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
		
		List<AttendanceRecordDto> dailyRecords = CommonJobMethod.separateIntoEachDays(checkInOutRecordsList);
		
		//compare time and calculate attendance status
		
		
		//delete old records
		
		//insert new records
		
		//send abnormal emails
		
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
}