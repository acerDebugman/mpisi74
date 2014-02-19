package schedule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Constant;
import common.Mail;
import common.UtilCommon;
import common.UtilDate;

import entity.CHECKINOUT;
import entity.MP0010;
import entity.MP1001;
import entity.MP2001;
import entity.MP2003;
import entity.MP2008;
import entity.Overtime;

public class CommonJobMethod {
	// 取得人资系统数据库的链接
	public static Connection getDBConnection() throws ClassNotFoundException, SQLException{
		//String sqlUrl = "jdbc:jtds:sqlserver://192.168.50.172:1433/mpisiHr";
		String sqlUrl = "jdbc:jtds:sqlserver://192.168.50.141:1433/mpisiHr;instance=JOE";
		//String sqlUrl = "jdbc:jtds:sqlserver://192.168.50.141:1433/mpisiHr_test;instance=JOE";
		String classforname = "net.sourceforge.jtds.jdbc.Driver";
		String uid = "sa";
		String pwd = "IDpt463";
		Connection connSql = null;
		
		Class.forName(classforname);
		connSql = DriverManager.getConnection( sqlUrl, uid, pwd);
		
		return connSql;
	}
	public static Connection getDBConnection2() throws ClassNotFoundException, SQLException{
		String sqlUrl = "jdbc:jtds:sqlserver://192.168.50.172:1433/finger";
		String classforname = "net.sourceforge.jtds.jdbc.Driver";
		String uid = "sa";
		String pwd = "IDpt463";
		Connection connSql = null;
		
		Class.forName(classforname);
		connSql = DriverManager.getConnection( sqlUrl, uid, pwd);
		
		return connSql;
	}
	// 取得所有员工编号
	public static ArrayList<MP2003> getAllData(Connection con,String parameter) throws SQLException{
		ArrayList<MP2003> mp2003List = new ArrayList<MP2003>();
		
		Statement statement = con.createStatement();
		String sql = "";
		if(parameter.equals("year")){
			//sql = "select * from MP2003 where MP2003_DATETIME < '" + UtilDate.getToday()  + "' and  MP2003_DATETIME >= DATEADD(DD, DATEDIFF(DD,0,getdate()), -365) order by MP2003_DATETIME ";
		}else if(parameter.equals("all")){
			//sql = "select * from MP2003 where 1=1 and MP2003_DATETIME < '" + UtilDate.getToday()  + "'  order by MP2003_DATETIME ";
		}else{
			sql = "select mp23.*,mp11.MP1001_DEPARTMENT_ID  from MP2003 mp23, MP1001 mp11 where 1=1 and mp23.MP2003_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM and mp23.MP2003_DATETIME < '" + UtilDate.getToday()  + "' and  mp23.MP2003_DATETIME >= DATEADD(DD, DATEDIFF(DD,0,getdate()), -62) order by mp23.MP2003_DATETIME ";
			//sql = "select mp23.*,mp11.MP1001_DEPARTMENT_ID  from MP2003 mp23, MP1001 mp11 where 1=1 and MP2003_EMPLOYEE_NUM = 'M0289' and MP2003_DATETIME='2013-12-27' and mp23.MP2003_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM and mp23.MP2003_DATETIME < '" + UtilDate.getToday()  + "' and  mp23.MP2003_DATETIME >= DATEADD(DD, DATEDIFF(DD,0,getdate()), -62) order by mp23.MP2003_DATETIME ";
			//sql = "select * from MP2003 where 1=1 and MP2003_EMPLOYEE_NUM = 'M0180' and  MP2003_DATETIME >= '2013-04-01' and MP2003_DATETIME <= '2013-05-12'  order by MP2003_DATETIME ";
			//sql = "select * from MP2003 where 1=1 and MP2003_DATETIME >= '2013-04-01' and MP2003_DATETIME <= '2013-04-10'  order by MP2003_DATETIME ";
		}
		
		MP2003 mp23 = new MP2003();
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()){
			mp23 = new MP2003();
			
			mp23.setMP2003_EMPLOYEE_NUM(rs.getString("MP2003_EMPLOYEE_NUM")); // 员工编码
			mp23.setMP2003_DATETIME(rs.getString("MP2003_DATETIME"));
			mp23.setMP2003_START_TIME(rs.getString("MP2003_START_TIME"));
			mp23.setMP2003_FINISH_TIME(rs.getString("MP2003_FINISH_TIME"));
			mp23.setMP2003_START_TIME_DOOR(rs.getString("MP2003_START_TIME_DOOR"));
			mp23.setMP2003_FINISH_TIME_DOOR(rs.getString("MP2003_FINISH_TIME_DOOR"));
			mp23.setMP2003_START_TIME_E(rs.getString("MP2003_START_TIME_E"));
			mp23.setMP2003_FINISH_TIME_E(rs.getString("MP2003_FINISH_TIME_E"));
			mp23.setMP2003_COMMENT(rs.getString("MP2003_COMMENT"));
			mp23.setMP2003_DES(rs.getString("MP2003_DES"));
			mp23.setMP2003_STATUS(rs.getString("MP2003_STATUS"));
			mp23.setMP2003_EDIT_USER(rs.getString("MP2003_EDIT_USER"));
			mp23.setMP2003_EDIT_DATETIME(rs.getString("MP2003_EDIT_DATETIME"));
			mp23.setMP2003_DEPARTMENT_ID(rs.getString("MP1001_DEPARTMENT_ID"));
			
			mp2003List.add(mp23);
		}
		rs.close();
		return mp2003List;
	}
	// 取得所有员工编号
	public static Map<String,String> getPublicHolidayMap(Connection con) throws SQLException{
		Map<String,String> holidayMap = new HashMap<String,String>();
		
		Statement statement = con.createStatement();
		String sql ="select HOLIDAY_DATE,HOLIDAY_NAME from HOLIDAY ";
		
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()){
			String holidayDate = rs.getString("HOLIDAY_DATE");
			String holidayName = rs.getString("HOLIDAY_NAME");
			
			if(!holidayMap.containsKey(holidayDate)){
				holidayMap.put(holidayDate, holidayName);
			}
		}
		rs.close();
		return holidayMap;
	}
	// 数据解析
	public static void dataConvert2003(List<MP2003> dataList,Connection con) throws ParseException, SQLException{
		SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdf3 = new SimpleDateFormat("HH");
		SimpleDateFormat sdf4 = new SimpleDateFormat("mm");
		
		double startHour = 0;
		double startMinute = 0;
		double finishHour = 0;
		double finishMinute = 0;
		
		Calendar calendar = Calendar.getInstance();
	    Date _today = calendar.getTime();
	    
	    Map<String,String> publicHolidayMap = getPublicHolidayMap(con);
		
		for(int m=0,n=dataList.size(); m<n; m++){
			MP2003 obj = dataList.get(m);
			String mp2003Date = obj.getMP2003_DATETIME().substring(0,10);
			String status = obj.getMP2003_STATUS();
			
			startHour = 0;
			startMinute = 0;
			finishHour = 0;
			finishMinute = 0;
			
			if(status != null && (status.equals("3") || status.equals("4") || status.equals("5"))){
				//continue;
			}else{
				obj.setMP2003_STATUS("2");
			}
			// 公共假日判断
			if(publicHolidayMap.containsKey(mp2003Date)){
				obj.setMP2003_COMMENT(publicHolidayMap.get(mp2003Date));
				continue;
			}
			//obj.setMP2003_STATUS("2");
			// 格式化时间
			if(obj.getMP2003_START_TIME() != null && !obj.getMP2003_START_TIME().equals("")){
				Date startDate = sdf1.parse(obj.getMP2003_START_TIME());
				obj.setMP2003_START_TIME(sdf2.format(startDate));
				
				startHour = Integer.parseInt(sdf3.format(startDate));
				startMinute = Integer.parseInt(sdf4.format(startDate));
			}
			if(obj.getMP2003_FINISH_TIME() != null && !obj.getMP2003_FINISH_TIME().equals("")){
				Date finishDate = sdf1.parse(obj.getMP2003_FINISH_TIME());
				obj.setMP2003_FINISH_TIME(sdf2.format(finishDate));
				
				finishHour = Integer.parseInt(sdf3.format(finishDate));
				finishMinute = Integer.parseInt(sdf4.format(finishDate));
			}
			
			// 如果上班时间早于8点，则按照8点计算；若下班时间晚于四点半，则按四点半计算
			if(startHour < 8){
				startHour = 8;
				startMinute = 0;
			}
			if(finishHour > 16){
				finishHour = 16;
				finishMinute = 30;
			}else if(16 == finishHour && finishMinute > 30){
				finishMinute = 30;
			}
			
			if(obj.getMP2003_DATETIME() != null && !obj.getMP2003_DATETIME().equals("")){
				Date _dateTime = sdf0.parse(obj.getMP2003_DATETIME());
				obj.setMP2003_DATETIME(sdf0.format(_dateTime));
				
				String today = sdf0.format(_today);
				Calendar calendar1 = Calendar.getInstance();
				calendar1.setTime(_dateTime);
				int weekDay = calendar1.get(Calendar.DAY_OF_WEEK);
				
				// 周日：1 周六：7
				if(sdf0.format(_dateTime).equals(today) || 1 == weekDay || 7 == weekDay){
					// 暂无处理
				}else{
					double leaveHour = getLeaveHours(obj.getMP2003_EMPLOYEE_NUM(),sdf0.format(_dateTime),con);
					
					// 如果没有上下班的打卡记录，则显示数据异常；但，如果当天请假8个小时，则显示数据正常
					if(obj.getMP2003_START_TIME() == null || obj.getMP2003_FINISH_TIME() == null){
						if(leaveHour >= 8){
							// 如果请假时间为8小时，则不为异常
							obj.setMP2003_COMMENT("Leave");
							obj.setMP2003_STATUS("2");
						}else{
							//if that day work time less than 8 hours.
							MP0010 mp10 = getDataFromMP0010(obj.getMP2003_DATETIME(),con);
							if(null != mp10){
								int startTimeHour = Integer.parseInt(mp10.getMP0010_START_TIME().substring(0, 2));
								int startTimeMinutes  = Integer.parseInt(mp10.getMP0010_START_TIME().substring(3,5));
								int endTimeHour = Integer.parseInt(mp10.getMP0010_END_TIME().substring(0, 2));
								int endTimeMinutes = Integer.parseInt(mp10.getMP0010_END_TIME().substring(3, 5));
								int workTimeHour = 8;
								int workTimeMinutes = 0;
								if(endTimeHour <= 13){
									workTimeHour = endTimeHour - startTimeHour;
									workTimeMinutes = endTimeMinutes - startTimeMinutes;
									if(workTimeMinutes < 0){
										workTimeMinutes += 60;
										workTimeHour -= 1;
									}
								}
								else{
									workTimeHour = endTimeHour - startTimeHour;
									workTimeMinutes = endTimeMinutes - startTimeMinutes;
									if(workTimeMinutes < 0){
										workTimeMinutes += 60;
										workTimeHour -= 1;
									}
									workTimeMinutes -= 30; //only 30 minutes for noon
									if(workTimeMinutes < 0){
										workTimeMinutes += 60;
										workTimeHour -= 1;
									}
								}
								if(leaveHour >= workTimeHour){
									obj.setMP2003_COMMENT("Leave");
									obj.setMP2003_STATUS("2");
								}
								else{
									//if that day he didn't come and didn't apply for leave
									obj.setMP2003_COMMENT("Abnormal");
									obj.setMP2003_STATUS("1");
								}
							}
							else{
								//if that day he didn't come and didn't apply for leave
								obj.setMP2003_COMMENT("Abnormal");
								obj.setMP2003_STATUS("1");
							}
							
						}
					}else if((startHour > 8 || 8 == startHour && startMinute > 0) || (finishHour < 16 || 16 == finishHour && finishMinute < 30)){
						if(leaveHour >= 8){
							// 如果请假时间为8小时，则不为异常
							obj.setMP2003_COMMENT("Leave");
							obj.setMP2003_STATUS("2");
						}else{
							List<MP2001> mp2001List = findByDate(obj.getMP2003_EMPLOYEE_NUM(),sdf0.format(_dateTime),con);
							String altMsg = "";
							
							//MP0010 mp10 = serviceMp0010.findById(obj.getMP2003_DATETIME());
							MP0010 mp10 = getDataFromMP0010(obj.getMP2003_DATETIME(),con);
							int startWorkHour = 0;
							int startWorkMinutes = 0;
							int endWorkHour = 0;
							int endWorkMinutes = 0;
							
							double startWorkTime = 0;
							double endWorkTime = 0;
							
							if(mp10 != null){
								startWorkHour = Integer.parseInt(mp10.getMP0010_START_TIME().substring(0,mp10.getMP0010_START_TIME().indexOf(":")));
								startWorkMinutes = Integer.parseInt(mp10.getMP0010_START_TIME().substring(mp10.getMP0010_START_TIME().indexOf(":") +1,mp10.getMP0010_START_TIME().length()));
								startWorkTime = startWorkHour + startWorkMinutes/60;
								
								endWorkHour = Integer.parseInt(mp10.getMP0010_END_TIME().substring(0,mp10.getMP0010_END_TIME().indexOf(":")));
								endWorkMinutes = Integer.parseInt(mp10.getMP0010_END_TIME().substring(mp10.getMP0010_END_TIME().indexOf(":") +1,mp10.getMP0010_END_TIME().length()));
								endWorkTime = endWorkHour + endWorkMinutes/60;
							}
							// 迟到的情况
							if(startHour > 8 || 8 == startHour && startMinute > 0){
								double lateHour = 0;// 迟到时间
								double lateLeaveHour = getExactLeaveHours(mp2001List,startHour,startMinute,sdf0.format(_dateTime));//请假时间, diff from getLeaveHours() function
								
								if(startHour < 13 || 13 == startHour && 0 == startMinute){
									lateHour = startHour - 8 + (startMinute>0?1:0);
								}
								if( 13 == startHour && startMinute > 0){
									lateHour = startHour - 8 + (startMinute>30?1:0);
								}
								if(startHour > 13){
									if(startMinute>30){
										lateHour = startHour - 8 + 1;
									}else if(startMinute <= 30){
										lateHour = startHour - 8;
									}
								}
								
								if(lateLeaveHour >= lateHour){
									altMsg += "Leave";
								}else{
									if(mp10 != null){
										double _outValue = Math.round(startWorkTime-8);
										
										if(lateLeaveHour >= lateHour - _outValue){
											//continue 暂时不做处理
										}else{
											altMsg += "Late";
										}
									}else{
										altMsg += "Late";
									}
								}
							}
							// 早退的情况
							if(finishHour < 16 || 16 == finishHour && finishMinute < 30){
								double earlyHour = 0;// 迟到时间
								double earlyLeaveHour = getExactLeaveHours(mp2001List,finishHour,finishMinute,sdf0.format(_dateTime));//请假时间
								
								if(finishHour <= 13){
									earlyHour = 16 - finishHour;
								}else{
									if(finishMinute < 30){
										earlyHour = 16 - finishHour + 1;
									}else if(finishMinute >= 30){
										earlyHour = 16 - finishHour;
									}
								}
								
								if(!altMsg.trim().equals("")){
									altMsg += "/";
								}
								if(earlyLeaveHour >= earlyHour){
									altMsg += "Leave";
								}else{
									if(mp10 != null){
										double _outValue = Math.round(16.5-endWorkTime);
										
										if(earlyLeaveHour >= earlyHour - _outValue){
											//continue 暂时不做处理
										}else{
											altMsg += "Early";
										}
									}else{
										altMsg += "Early";
									}
								}
							}
							obj.setMP2003_COMMENT(altMsg);
						}
					}
				}
			}else{
				obj.setMP2003_COMMENT("Abnormal");
				obj.setMP2003_STATUS("1");
			}
			
			//-------------2012-/01/05 Add by Tim 上下班异常
			if(obj.getMP2003_STATUS() != null){
				if(obj.getMP2003_STATUS().equals("3")){
					obj.setMP2003_COMMENT(Constant.IN_MSG);
				}else if(obj.getMP2003_STATUS().equals("4")){
					obj.setMP2003_COMMENT(Constant.OUT_MSG);
				}else if(obj.getMP2003_STATUS().equals("5")){
					obj.setMP2003_COMMENT(Constant.IN_OUT_MSG);
				}
			}else{
				obj.setMP2003_STATUS("2");
			}
		}
	}
	
	public static void mergeShiftWorkSchedule(List<MP2003> mp2003list, Connection con) throws SQLException {
		try{
			//delete all shift work employee old data in mp2003list and reload their attendance data
			
			//go normal procedure
			
			/* normal procedure
			//normal work time: 8:00 - 16:30
			//apply for leave
			//weekend and holiday
			//MP0010 special day, work off early
			*/
			//shift work
			
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	// 取得给定日期的请假时间
	public static double getLeaveHours(String empNum,String date,Connection con) throws ParseException, SQLException{
		double result = 0;
		double retValue = 0;
		//double startHour = 0;
		//double startMinute = 0;
		double finishHour = 0;
		double finishMinute = 0;
		
		SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH");
		SimpleDateFormat sdf2 = new SimpleDateFormat("mm");		
		// 根据员工编码和日期，取得该员工的请假信息列表
		List<MP2001> mp21List = findByDate(empNum, date,con);
		
		// 循环判断请假信息
		for(int i=0,j=mp21List.size(); i<j; i++){
			MP2001 mp21 = mp21List.get(i);
			
			//String startTime = mp21.getMP2001_FROM_DATETIME();
			String finishTime = mp21.getMP2001_TO_DATETIME();
			
			//Date startDate = sdf0.parse(startTime);
			Date finishDate = sdf0.parse(finishTime);
			
			//startHour = Double.parseDouble(sdf1.format(startDate));
			//startMinute = Double.parseDouble(sdf2.format(startDate));
			finishHour = Double.parseDouble(sdf1.format(finishDate));
			finishMinute = Double.parseDouble(sdf2.format(finishDate));
			
			//String st = sdf3.format(startDate);
			String ft = sdf3.format(finishDate);
			//----------
			//1.2011-12-5 08:00 ~ 2011-12-5 16:30
			//2.2011-12-4 08:00 ~ 2011-12-5 08:00
			//如果查询2011-12-5的假期时，会把第二中情况的数据也查询出来，为避免这种情况，特加如下处理
			if(ft.equals(date) && 8 == finishHour && 0 == finishMinute ){
				continue;
			}

			result = Double.parseDouble(mp21.getMP2001_DAYS());
			//retValue = retValue + result/60;
			retValue = retValue + result;
		}
		
		return retValue;
	}	
	
	public static List<MP2001> findByDate(String empNum,String date,Connection con) throws SQLException{
		ArrayList<MP2001> mp2001List = new ArrayList<MP2001>();
		StringBuffer queryString = new StringBuffer();
		
		Statement statement = con.createStatement();
		
		queryString.append(" select * ");
		queryString.append(" from MP2001 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and MP2001_STATUS = '0' " );
		queryString.append(" and MP2001_APPROVAL = '3' " );
		queryString.append(" and MP2001_EMPLOYEE_NUM = '" + empNum + "' ");
		queryString.append(" and Convert(char(10),MP2001_FROM_DATETIME,120) <= '" + date + "' ");
		queryString.append(" and Convert(char(10),MP2001_TO_DATETIME,120) >= '" + date + "' ");
		queryString.append(" order by MP2001_FROM_DATETIME ASC"); //here is important, so I can merge them later.

		MP2001 mp21 = new MP2001();
		ResultSet rs = statement.executeQuery(queryString.toString());
		while(rs.next()){
			mp21 = new MP2001();
			
			mp21.setMP2001_NUM(rs.getString("MP2001_NUM"));
			mp21.setMP2001_EMPLOYEE_NUM(rs.getString("MP2001_EMPLOYEE_NUM"));
			mp21.setMP2001_ACTING_APPLICATION_PERSON(rs.getString("MP2001_ACTING_APPLICATION_PERSON"));
			mp21.setMP2001_APPLIY_TYPE(rs.getString("MP2001_APPLIY_TYPE"));
			mp21.setMP2001_DAYS(rs.getString("MP2001_DAYS"));
			mp21.setMP2001_FROM_DATETIME(rs.getString("MP2001_FROM_DATETIME"));
			mp21.setMP2001_TO_DATETIME(rs.getString("MP2001_TO_DATETIME"));
			mp21.setMP2001_ACTING_PERSON(rs.getString("MP2001_ACTING_PERSON"));
			mp21.setMP2001_LEAVE_TYPE(rs.getString("MP2001_LEAVE_TYPE"));
			mp21.setMP2001_APPROVAL(rs.getString("MP2001_APPROVAL"));
			mp21.setMP2001_STATUS(rs.getString("MP2001_STATUS"));
			mp21.setMP2001_COMMENT(rs.getString("MP2001_COMMENT"));
			mp21.setMP2001_MAJOR_SEQ(rs.getString("MP2001_MAJOR_SEQ"));
			mp21.setMP2001_CREATE_USER(rs.getString("MP2001_CREATE_USER"));
			mp21.setMP2001_CREATE_DATETIME(rs.getString("MP2001_CREATE_DATETIME"));
			mp21.setMP2001_EDIT_USER(rs.getString("MP2001_EDIT_USER"));
			mp21.setMP2001_EDIT_DATETIME(rs.getString("MP2001_EDIT_DATETIME"));
			mp21.setMP2001_APPROVAL_USER(rs.getString("MP2001_APPROVAL_USER"));
			mp21.setMP2001_APPROVAL_DATETIME(rs.getString("MP2001_APPROVAL_DATETIME"));
			
			mp2001List.add(mp21);
		}		
		
	    return mp2001List;
	}
	
	public static double getExactLeaveHours(List<MP2001> mp2001List, double _hour, double _minute, String date) throws ParseException{
		double result = 0;
		double retValue = 0;
		double startHour = 0;
		//double startMinute = 0;
		double finishHour = 0;
		double finishMinute = 0;
		
		SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH");
		SimpleDateFormat sdf2 = new SimpleDateFormat("mm");
		
		//add by Joe, mp2001List already order by MP2001_FROM_DATETIME
		for(int i=0,j=mp2001List.size(); i<j; i++){
			if(i + 1 >= j){
				break;
			}
			MP2001 mp21 = mp2001List.get(i);
			String endTime_0 = mp21.getMP2001_TO_DATETIME();
			String startTime_1 = mp2001List.get(i+1).getMP2001_FROM_DATETIME();
			Date endDate_0 = sdf0.parse(endTime_0);
			Date startDate_1 = sdf0.parse(startTime_1);
			if(startDate_1.compareTo(endDate_0) < 0){ //startDate_1 is before endDate_0
				mp21.setMP2001_TO_DATETIME(mp2001List.get(i+1).getMP2001_TO_DATETIME()); //the only reason is I only need total time
				//merge hours
				int a = Integer.parseInt(mp2001List.get(i).getMP2001_DAYS());
				int b = Integer.parseInt(mp2001List.get(i + 1).getMP2001_DAYS());
				mp2001List.get(i).setMP2001_DAYS(Integer.toString(a+b));
				
				mp2001List.remove(i+1);
				i--;
				j--;
			}
		}//END JOE
		
		for(int i=0,j=mp2001List.size(); i<j; i++){
			MP2001 mp21 = mp2001List.get(i);
			
			String startTime = mp21.getMP2001_FROM_DATETIME();
			String finishTime = mp21.getMP2001_TO_DATETIME();
			
			Date startDate = sdf0.parse(startTime);
			Date finishDate = sdf0.parse(finishTime);
			
			startHour = Double.parseDouble(sdf1.format(startDate));
			//startMinute = Double.parseDouble(sdf2.format(startDate));
			finishHour = Double.parseDouble(sdf1.format(finishDate));
			finishMinute = Double.parseDouble(sdf2.format(finishDate));
			
			//String st = sdf3.format(startDate);
			String ft = sdf3.format(finishDate);
			//----------
			//1.2011-12-5 08:00 ~ 2011-12-5 16:30
			//2.2011-12-4 08:00 ~ 2011-12-5 08:00
			//如果查询2011-12-5的假期时，会把第二中情况的数据也查询出来，为避免这种情况，特加如下处理
			if(ft.equals(date) && 8 == finishHour && 0 == finishMinute ){
				continue;
			}
			
			/*
			if(startHour <= _hour && finishHour >= _hour){
				result = Double.parseDouble(mp21.getMP2001_DAYS());
				//result = Integer.parseInt(sdf1.parse(mp21.getMP2001_FROM_DATETIME()).toString()) - Integer.parseInt(sdf1.parse(mp21.getMP2001_TO_DATETIME()).toString());
				retValue = retValue + result;
			}
			*/
			
			//changed by joe
			result = Double.parseDouble(mp21.getMP2001_DAYS());
			retValue = retValue + result;
			
		}

		return retValue;
	}
	
	// 取得所有员工编号
	public static MP0010 getDataFromMP0010(String date,Connection con) throws SQLException{
		Statement statement = con.createStatement();
		String sql = "select * from MP0010 where MP0010_DATETIME = '" + date + "'";
		
		MP0010 mp0010 = null;
		ResultSet rs = statement.executeQuery(sql);
		
		while(rs.next()){
			mp0010 = new MP0010();
			mp0010.setMP0010_DATETIME(rs.getString("MP0010_DATETIME")); // 员工编码
			mp0010.setMP0010_START_TIME(rs.getString("MP0010_START_TIME"));
			mp0010.setMP0010_END_TIME(rs.getString("MP0010_END_TIME"));
			mp0010.setMP0010_LUNCH_TIME(rs.getString("MP0010_LUNCH_TIME"));
			mp0010.setMP0010_TOTAL_TIME(rs.getString("MP0010_TOTAL_TIME"));
			mp0010.setMP0010_OTHER1(rs.getString("MP0010_OTHER1"));
			mp0010.setMP0010_OTHER2(rs.getString("MP0010_OTHER2"));
			break;
		}
		rs.close();
		return mp0010;
	}
	
	public static void update2003(List<MP2003> dataList,Connection con) throws SQLException{
		StringBuffer sb = new StringBuffer();
		Statement stSql = con.createStatement();
		
		// 发邮件通知本人密码已经重置
		Mail mail = new Mail();
		String to = "";		
		for(int i=0,j=dataList.size(); i<j; i++){
			MP2003 mp23 = dataList.get(i);
			String mp23Comment = mp23.getMP2003_COMMENT();
			String mp23Status = mp23.getMP2003_STATUS();
			
/*			if(mp23Comment == null || mp23Comment.trim().equals("")){
				continue;
			}*/
			if(mp23Comment == null || mp23Comment.trim().equals("null")){
				mp23Comment = "";
		    }
			
			sb = new StringBuffer();
			sb.append(" update MP2003 ");
			sb.append(" set MP2003_COMMENT = '" + mp23Comment  + "', ");
			sb.append(" MP2003_STATUS = '" + mp23Status +"', ");
			sb.append(" MP2003_EDIT_DATETIME = '" + UtilDate.get24DateTime() +"' ");
			sb.append(" where 1=1");
			sb.append(" and MP2003_EMPLOYEE_NUM ='" + mp23.getMP2003_EMPLOYEE_NUM() + "' ");
			sb.append(" and MP2003_DATETIME = '" + mp23.getMP2003_DATETIME() + "' ");
			try{
				stSql.executeUpdate(sb.toString());
				if(mp23Status.equals("1") && !UtilCommon.checkDirector(mp23.getMP2003_EMPLOYEE_NUM().toUpperCase()) && mp23.getMP2003_DEPARTMENT_ID() != null && !mp23.getMP2003_DEPARTMENT_ID().equals("12")){
					to = getEmployeeEmail(con, mp23.getMP2003_EMPLOYEE_NUM());
					mail.setSubject("Attendance Records(Abnormal)");
					mail.setContent("Dear Colleagues,\r\n \r\n Please note that your attendance record is abnormal on " + mp23.getMP2003_DATETIME());
					mail.setTo(to);
					mail.send();
				}
			}catch(Exception ex){
				System.out.println("Update failed:" + mp23.getMP2003_EMPLOYEE_NUM() + "|" + mp23.getMP2003_DATETIME());
				System.out.println(ex.getMessage() + ex.getStackTrace());
				continue;
			}
		}
		stSql.close();
	}
	
	// 重置部门预算数据
	public static void updateMP4003Template(Connection con,String year,String month) throws SQLException{
		StringBuffer sb = new StringBuffer();
		Statement stSql = con.createStatement();
		
		sb.append(" update MP4003_TEMPLATE ");
		sb.append(" set MP4003_TEMPLATE_YEAR = '" + year  + "', ");
		sb.append(" MP4003_TEMPLATE_MONTH = '" + month  + "', ");
		sb.append(" MP4003_TEMPLATE_AMOUNT = 0, ");
		sb.append(" MP4003_TEMPLATE_AMOUNT2 = 0, ");
		sb.append(" MP4003_TEMPLATE_AMOUNT3 = 0, ");
		sb.append(" MP4003_TEMPLATE_STATUS = '0' ");
		
		stSql.executeUpdate(sb.toString());
		stSql.close();
	}
	// 进入下一个月各个部门的预算初始化数据
	public static void insertMP4003(Connection con) throws SQLException{
		StringBuffer sb = new StringBuffer();
		Statement stSql = con.createStatement();
		
		sb.append(" insert into MP4003 (MP4003_ACCOUNTING_NUM,MP4003_AMOUNT,MP4003_DEPARTMENT_ID,MP4003_MONTH,MP4003_YEAR,MP4003_AMOUNT2,MP4003_AMOUNT3,MP4003_AMOUNT_MAX,MP4003_STATUS) ");
		sb.append(" select MP4003_TEMPLATE_ACCOUNTING_NUM,MP4003_TEMPLATE_AMOUNT,MP4003_TEMPLATE_DEPARTMENT_ID,MP4003_TEMPLATE_MONTH,MP4003_TEMPLATE_YEAR,MP4003_TEMPLATE_AMOUNT2,MP4003_TEMPLATE_AMOUNT3,MP4003_TEMPLATE_AMOUNT_MAX,MP4003_TEMPLATE_STATUS  from MP4003_TEMPLATE ");
		
		stSql.executeUpdate(sb.toString());
		stSql.close();
	}
	// 取得保安人员信息
	public static String getSecurityInfoList(Connection con) throws SQLException{
		StringBuffer employeeStr = new StringBuffer();
		Statement stSql = con.createStatement();
		
		employeeStr.append(" select mp11.MP1001_EMPLOYEE_NUM ");
		employeeStr.append(" from MP1001 mp11 ");
		employeeStr.append(" where 1=1 ");
		employeeStr.append(" and mp11.MP1001_STATUS in ('1','2') "); // 1:临时员工 2:正式员工
		employeeStr.append(" and mp11.MP1001_ANNUAL_STATUS = '1' ");
		employeeStr.append(" and (mp11.MP1001_DEPARTMENT_ID = '12' or mp11.MP1001_POSITION = '136' ) ");
		
		ResultSet rs = stSql.executeQuery(employeeStr.toString());
		
		StringBuffer employeeInfoList = new StringBuffer();
		while(rs.next()){
			String mp1001EmployeeNum = rs.getString("MP1001_EMPLOYEE_NUM"); // 员工编码
			
			if(employeeInfoList.toString().equals("")){
				employeeInfoList.append( "'" + mp1001EmployeeNum + "' " );
			}else{
				employeeInfoList.append( ",'" + mp1001EmployeeNum + "' " );
			}
		}
		rs.close();
		
		return employeeInfoList.toString();
	}
	// 取得所有不需要累加年假的员工列表
	public static String getAnnualStatusList(Connection con) throws SQLException{
		StringBuffer employeeStr = new StringBuffer();
		Statement stSql = con.createStatement();
		
		employeeStr.append(" select mp11.MP1001_EMPLOYEE_NUM ");
		employeeStr.append(" from MP1001 mp11 ");
		employeeStr.append(" where 1=1 ");
		employeeStr.append(" and mp11.MP1001_STATUS in ('1','2') "); // 1:临时员工 2:正式员工
		employeeStr.append(" and mp11.MP1001_ANNUAL_STATUS = '0' ");
		
		ResultSet rs = stSql.executeQuery(employeeStr.toString());
		
		StringBuffer employeeInfoList = new StringBuffer();
		while(rs.next()){
			String mp1001EmployeeNum = rs.getString("MP1001_EMPLOYEE_NUM"); // 员工编码
			
			if(employeeInfoList.toString().equals("")){
				employeeInfoList.append( "'" + mp1001EmployeeNum + "' " );
			}else{
				employeeInfoList.append( ",'" + mp1001EmployeeNum + "' " );
			}
		}
		rs.close();
		
		return employeeInfoList.toString();
	}
	// 取得部门Manager邮箱
	public static Map<String,String> getDepartmentManagerEmail(Connection con) throws SQLException{
		StringBuffer employeeStr = new StringBuffer();
		Statement stSql = con.createStatement();
		
		employeeStr.append(" select MP0002_SEQ,MP1001_EMPLOYEE_NUM,MP1001_PREFERED_NAME,MP0002_DEPARTMENT_NAME,MP1001_COMPANY_EMAIL ");
		employeeStr.append(" from MP0002 mp02,MP1001 mp11 ");
		employeeStr.append(" where mp11.MP1001_DEPARTMENT_ID = mp02.MP0002_SEQ ");
		employeeStr.append(" and mp11.MP1001_POSITION = '1' ");
		employeeStr.append(" and mp11.MP1001_STATUS != '3' ");
		//employeeStr.append(" and mp11.MP1001_DEPARTMENT_ID = '11' ");
		
		ResultSet rs = stSql.executeQuery(employeeStr.toString());

		Map<String,String> departmentInfoMap = new HashMap<String,String>();
		while(rs.next()){
			String mp1001EmployeeMail = rs.getString("MP1001_COMPANY_EMAIL");
			String mp0002Seq =  rs.getString("MP0002_SEQ"); 
			
			if(departmentInfoMap.containsKey(mp0002Seq)){
				String _value = departmentInfoMap.get(mp0002Seq) + "," + mp1001EmployeeMail;
				departmentInfoMap.remove(mp0002Seq);
				departmentInfoMap.put(mp0002Seq, _value);
			}else{
				departmentInfoMap.put(mp0002Seq, mp1001EmployeeMail);
			}
		}
		rs.close();
		
		return departmentInfoMap;
	}
	// 取得Employee邮箱
	public static String getEmployeeEmail(Connection con,String employeeNum) throws SQLException{
		StringBuffer employeeStr = new StringBuffer();
		String mp1001EmployeeMail = "";
		Statement stSql = con.createStatement();
		
		employeeStr.append(" select MP1001_EMPLOYEE_NUM,MP1001_PREFERED_NAME,MP1001_COMPANY_EMAIL ");
		employeeStr.append(" from MP1001 mp11 ");
		employeeStr.append(" where 1=1 ");
		employeeStr.append(" and mp11.MP1001_EMPLOYEE_NUM = '" + employeeNum + "' ");
		employeeStr.append(" and mp11.MP1001_STATUS in ('1','2') ");
		
		
		ResultSet rs = stSql.executeQuery(employeeStr.toString());
		while(rs.next()){
			mp1001EmployeeMail = rs.getString("MP1001_COMPANY_EMAIL");
			break;
		}
		rs.close();
		
		return mp1001EmployeeMail;
	}
	// 取得部门Manager邮箱
	public static Map<String,String> getDepartmentScheduleInfo(Connection con,String _year, String _month ,String _day) throws SQLException{
		StringBuffer employeeStr = new StringBuffer();
		Statement stSql = con.createStatement();
		
		employeeStr.append(" select mp61.MP6001_TITLE,mp11.MP1001_EMPLOYEE_NUM,mp11.MP1001_PREFERED_NAME,mp11.MP1001_DEPARTMENT_ID ");
		employeeStr.append(" from MP6001 mp61, MP1001 mp11 ");
		employeeStr.append(" where mp61.MP6001_EMPLOYEE_NUM = mp11.MP1001_EMPLOYEE_NUM ");
		employeeStr.append(" and mp61.MP6001_COMPLETE_STATUS = '0' ");
/*		employeeStr.append(" and mp61.MP6001_YEAR = '" + _year + "' ");
		employeeStr.append(" and mp61.MP6001_MONTH = '" + _month + "' ");
		employeeStr.append(" and mp61.MP6001_DAY = '" + _day + "' ");*/
		employeeStr.append(" order by mp11.MP1001_DEPARTMENT_ID,mp11.MP1001_EMPLOYEE_NUM ");
		
		ResultSet rs = stSql.executeQuery(employeeStr.toString());
		
		Map<String,String> scheduleInfoMap = new HashMap<String,String>();
		while(rs.next()){
			String _departmentId = rs.getString("MP1001_DEPARTMENT_ID");
			String _empNum = rs.getString("MP1001_EMPLOYEE_NUM");
			String _empName = rs.getString("MP1001_PREFERED_NAME");
			String _title = rs.getString("MP6001_TITLE");
			
			if(scheduleInfoMap.containsKey(_departmentId)){
				String _value = scheduleInfoMap.get(_departmentId) + "\r\n" + _empNum + "    " + _empName + "    " + _title;
				scheduleInfoMap.remove(_departmentId);
				scheduleInfoMap.put(_departmentId, _value);
			}else{
				String _value = _empNum + "    " + _empName + "    " + _title ; 
				scheduleInfoMap.put(_departmentId, _value);
			}
		}
		rs.close();
		
		return scheduleInfoMap;
	}
	// 取得部门Manager邮箱
	public static Map<String,String> getDepartmentBirthdayInfo(Connection con,String _month) throws SQLException{
		StringBuffer employeeStr = new StringBuffer();
		Statement stSql = con.createStatement();
		
		employeeStr.append(" select MP0002_SEQ,MP1001_EMPLOYEE_NUM,MP1001_PREFERED_NAME,MP0002_DEPARTMENT_NAME,MP1001_BIRTHDAY ");
		employeeStr.append(" from MP0002 mp02,MP1001 mp11 ");
		employeeStr.append(" where mp11.MP1001_DEPARTMENT_ID = mp02.MP0002_SEQ ");
		employeeStr.append(" and mp11.MP1001_STATUS != '3' ");
		
		String month = String.format("%02d", Integer.parseInt(_month));
		employeeStr.append(" and substring(convert(varchar,mp11.MP1001_BIRTHDAY,120),6,2) = '" + month + "'");	
		
		ResultSet rs = stSql.executeQuery(employeeStr.toString());

		Map<String,String> departmentInfoMap = new HashMap<String,String>();
		while(rs.next()){
			String mp1001EmployeeBirthday = rs.getString("MP1001_EMPLOYEE_NUM") + "    " + rs.getString("MP1001_PREFERED_NAME") + "    " + rs.getString("MP1001_BIRTHDAY").substring(0,10) + "\r\n";
			String mp0002Seq =  rs.getString("MP0002_SEQ"); 
			
			if(departmentInfoMap.containsKey(mp0002Seq)){
				String _value = departmentInfoMap.get(mp0002Seq) + "\r\n" + mp1001EmployeeBirthday;
				departmentInfoMap.remove(mp0002Seq);
				departmentInfoMap.put(mp0002Seq, _value);
			}else{
				departmentInfoMap.put(mp0002Seq, mp1001EmployeeBirthday);
			}
		}
		rs.close();
		
		return departmentInfoMap;
	}
	// 发邮件通知
	public static void sedMailToAdmin(String content,String title){
		// 发邮件通知本人密码已经重置
		Mail mail = new Mail();
		String to = Constant.ADMIN_MAIL;
		mail.setSubject(title);
		mail.setContent(content);
		mail.setTo(to);
		mail.send();
	}
	// 取得日期
	//get all work day, do not include holday, saturday and sunday 
	public static List<String> getFormatDate( int value,Connection con) throws SQLException{ 
		 List<String> dateList = new ArrayList<String>();
	     Calendar c = Calendar.getInstance();
	     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	     c.setTime(new Date());
	     Date d2 = c.getTime();
	     int weekDay = c.get(Calendar.DAY_OF_WEEK);
	     boolean isHoliday = checkHoliday(df.format(d2), con);
	     
	     if(weekDay != 1 && weekDay != 7 && isHoliday != true){
	    	 //dateList.add(df.format(d2));
	     }
	     
	     int count = -1;
	     
	     while(count >= value){
	    	 c.add(Calendar.DAY_OF_MONTH, -1);
	    	 d2 = c.getTime();
	    	 weekDay = c.get(Calendar.DAY_OF_WEEK);
	    	 if(weekDay != 1 && weekDay != 7 && isHoliday != true){
		    	 dateList.add(df.format(d2));
		     }
	    	 count --;
	     }
	     
	     return dateList;
	}
	// 判断是否公共假日
	private static boolean checkHoliday(String checkDate,Connection con) throws SQLException{
		boolean ret = false;
		
		Statement statement = con.createStatement();
		String sql = "select count(*) as count from HOLIDAY where 1=1 and HOLIDAY_DATE = '" + checkDate + "' ";;
		
		ResultSet rs = statement.executeQuery(sql);
		rs.next();
		String holidayDate = rs.getString("count");
		if(!holidayDate.equals("0")){
			ret = true;
		}
		rs.close();
		return ret;
	}
	// 取得在职人员信息
	public static Map<String, MP1001> getEmployeeInfoList(Connection con) throws SQLException{
		StringBuffer employeeStr = new StringBuffer();
		Statement stSql = con.createStatement();
		
		employeeStr.append(" select mp11.MP1001_EMPLOYEE_NUM, mp11.MP1001_ENTRY_DATE ");
		employeeStr.append(" from MP1001 mp11 ");
		employeeStr.append(" where 1=1 ");
		employeeStr.append(" and mp11.MP1001_STATUS in ('1','2') "); // 1:临时员工 2:正式员工
		
		ResultSet rs = stSql.executeQuery(employeeStr.toString());
		
		MP1001 mp1 =null;
		List<String> dateList = getFormatDate(-62,con); //get all work day, 
		Map<String, MP1001> empDateMap = new HashMap<String, MP1001>();
		while(rs.next()){
			mp1 = new MP1001();
			mp1.setMP1001_EMPLOYEE_NUM(rs.getString("MP1001_EMPLOYEE_NUM"));
			mp1.setMP1001_ENTRY_DATE(rs.getString("MP1001_ENTRY_DATE"));
			
			for(String _de : dateList){
				empDateMap.put(mp1.getMP1001_EMPLOYEE_NUM().toUpperCase() + "#@" + _de, mp1);
			}
		}
		rs.close();
		
		return empDateMap;
	}
	// 插入空考勤记录
	public static void insertEmptyAttendanceRecord(Map<String, MP1001> empDateMap, Connection con) throws SQLException{
		StringBuffer queryString = new StringBuffer();
		Statement stSql = con.createStatement();
		
		for(Map.Entry<String, MP1001> entry : empDateMap.entrySet()){
			queryString = new StringBuffer();
        	queryString.append(" Insert into MP2003( ");
        	queryString.append(" MP2003_EMPLOYEE_NUM, ");
        	queryString.append(" MP2003_DATETIME) ");
        	queryString.append(" Values ( ");
        	queryString.append("'" + entry.getKey().split("#@")[0] + "', " );
        	queryString.append("'" + entry.getKey().split("#@")[1] + "' " );
        	queryString.append(")");
        	
        	stSql.executeUpdate(queryString.toString());
		}
		
		stSql.close();
	}
	
	public static void loadDataToHrSystem(boolean checkFlag, Overtime overtime) throws ClassNotFoundException, SQLException{
		Connection connSqlFinger = CommonJobMethod.getDBConnection2();
		
        StringBuffer queryString = new StringBuffer();
        queryString.append(" select ");
        queryString.append(" checkinout.USERID, ");
        queryString.append(" checkinout.CHECKTIME, ");
        queryString.append(" checkinout.CHECKTYPE, ");
        queryString.append(" checkinout.VERIFYCODE, ");
        queryString.append(" checkinout.SENSORID, ");
        queryString.append(" checkinout.MEMOINFO, ");
        queryString.append(" checkinout.WORKCODE, ");
        queryString.append(" checkinout.SN, ");
        queryString.append(" checkinout.USEREXTFMT, ");
        queryString.append(" userinfo.SSN ");
        queryString.append(" from CHECKINOUT checkinout, USERINFO userinfo ");
        queryString.append(" where 1=1 ");
        queryString.append(" and checkinout.USERID = userinfo.USERID ");
        if(checkFlag){
            queryString.append(" and checkinout.CHECKTIME < '" + UtilDate.getToday() + "' ");
            queryString.append(" and checkinout.CHECKTIME >= DATEADD(DD, DATEDIFF(DD,0,getdate()), -31) ");
        }else{
        	queryString.append(" and userinfo.ssn = '" + overtime.getEmployeeNum() + "' ");
        	queryString.append(" and checkinout.CHECKTIME <= '" + overtime.getCheckTime1() + "' ");
        	queryString.append(" and checkinout.CHECKTIME >= '" + overtime.getCheckTime2() + "' ");
        }
        
        Statement statement = connSqlFinger.createStatement();
        ResultSet rs = statement.executeQuery(queryString.toString());
        CHECKINOUT checkInout = null;
        ArrayList<CHECKINOUT> checkInoutList = new ArrayList<CHECKINOUT>();
		while(rs.next()){
			checkInout = new CHECKINOUT();
			checkInout.setUSERID(Integer.parseInt(rs.getString("USERID")));
			checkInout.setCHECKTIME(rs.getString("CHECKTIME"));
			checkInout.setCHECKTYPE(rs.getString("CHECKTYPE"));
			checkInout.setVERIFYCODE(Integer.parseInt(rs.getString("VERIFYCODE")));
			checkInout.setSENSORID(rs.getString("SENSORID"));
			checkInout.setMEMOINFO(rs.getString("MEMOINFO"));
			checkInout.setWORKCODE(Integer.parseInt(rs.getString("WORKCODE")));
			checkInout.setSN(rs.getString("SN"));
			checkInout.setUSEREXTFMT(Integer.parseInt(rs.getString("USEREXTFMT")));
			checkInout.setSSN(rs.getString("SSN"));
			checkInout.setEMPLOYEE_NUM(rs.getString("SSN"));
			
			checkInoutList.add(checkInout);
		}
		rs.close();
		
        Map<String, CHECKINOUT> inoutMap = new HashMap<String, CHECKINOUT>();
        CHECKINOUT checkObj = null;
        for(CHECKINOUT checkinout : checkInoutList){   //insert data
        	checkObj = new CHECKINOUT();
        	checkObj.setKey(checkinout.getEMPLOYEE_NUM() + "#@" + checkinout.getCHECKTIME().substring(0,10));
        	if(inoutMap.containsKey(checkObj.getKey())){
        		checkObj = inoutMap.get(checkObj.getKey());
        		checkObj.setDate2(checkinout.getCHECKTIME());
        		checkObj.setDoor2(checkinout.getSENSORID());
        		
        		inoutMap.remove(checkObj.getKey());
        	}else{
        		checkObj.setDate1(checkinout.getCHECKTIME());
        		checkObj.setDoor1(checkinout.getSENSORID());
        	}
        	inoutMap.put(checkObj.getKey(), checkObj);
        }
        connSqlFinger.close();
        
        Connection connSqlHr = CommonJobMethod.getDBConnection();  //connect to database
        connSqlHr.setAutoCommit(false);
        statement = connSqlHr.createStatement();
        
        queryString = new StringBuffer();
        queryString.append(" DELETE FROM  MP2003 WHERE 1=1 ");
        if(checkFlag){
            queryString.append(" and MP2003_DATETIME >= DATEADD(DD, DATEDIFF(DD,0,getdate()), -31) ");
            queryString.append(" and MP2003_DATETIME <= '" + UtilDate.getToday() + "' ");
        }else{
        	queryString.append(" and MP2003_EMPLOYEE_NUM = '" + overtime.getEmployeeNum() + "' ");
        	queryString.append(" and MP2003_DATETIME <= '" + overtime.getCheckTime1() + "' ");
        	queryString.append(" and MP2003_DATETIME >= '" + overtime.getCheckTime2() + "' ");
        }
        
        statement.executeUpdate(queryString.toString());  //delete records from MP2003
        
        for(Map.Entry<String, CHECKINOUT> entry : inoutMap.entrySet()){  //insert into MP2003
        	checkObj = entry.getValue();
        	queryString = new StringBuffer();
        	queryString.append(" Insert into MP2003( ");
        	queryString.append(" MP2003_EMPLOYEE_NUM, ");
        	queryString.append(" MP2003_DATETIME, ");
        	queryString.append(" MP2003_START_TIME, ");
        	queryString.append(" MP2003_FINISH_TIME, ");
        	queryString.append(" MP2003_START_TIME_DOOR, ");
        	queryString.append(" MP2003_FINISH_TIME_DOOR, ");
        	queryString.append(" MP2003_START_TIME_E, ");
        	queryString.append(" MP2003_FINISH_TIME_E) ");
        	queryString.append(" Values ( ");
        	queryString.append("'" + entry.getKey().split("#@")[0] + "', " );
        	queryString.append("'" + entry.getKey().split("#@")[1] + "', " );
        	
        	// MP2003_START_TIME
        	if(checkObj.getDate1() == null || checkObj.getDate1().equals("")){
        		queryString.append("null, " );
        	}else{
        		queryString.append("'" + checkObj.getDate1() + "', " );
        	}
        	// MP2003_FINISH_TIME
        	if(checkObj.getDate2() == null || checkObj.getDate2().equals("")){
        		queryString.append("null, " );
        	}else{
        		queryString.append("'" + checkObj.getDate2() + "', " );
        	}
        	// MP2003_START_TIME_DOOR
        	if(checkObj.getDoor1() == null || checkObj.getDoor1().equals("")){
        		queryString.append("null, " );
        	}else{
        		queryString.append("'" + checkObj.getDoor1() + "', " );
        	}
        	// MP2003_FINISH_TIME_DOOR
        	if(checkObj.getDoor2() == null || checkObj.getDoor2().equals("")){
        		queryString.append("null, " );
        	}else{
        		queryString.append("'" + checkObj.getDoor2() + "', " );
        	}
        	// MP2003_START_TIME_E
        	if(checkObj.getDate1() == null || checkObj.getDate1().equals("")){
        		queryString.append("null, " );
        	}else{
        		queryString.append("'" + checkObj.getDate1() + "', " );
        	}
        	// MP2003_FINISH_TIME_E
        	if(checkObj.getDate2() == null || checkObj.getDate2().equals("")){
        		queryString.append("null " );
        	}else{
        		queryString.append("'" + checkObj.getDate2() + "' " );
        	}
        	queryString.append(")");
        	
        	statement.executeUpdate(queryString.toString());
        }
        
        connSqlHr.commit();
        connSqlHr.close();
	}
	
	public static void tempJob() throws ClassNotFoundException, SQLException, ParseException{
		Connection connection = CommonJobMethod.getDBConnection();
		
        StringBuffer queryString = new StringBuffer();
        
        queryString.append(" select *  ");
        queryString.append(" from MP2008  ");
        queryString.append(" where 1=1 ");
        queryString.append(" and MP2008_DATA_STATUS = '1' ");
        queryString.append(" and MP2008_DATE >= '2013-06-01' ");
        queryString.append(" order by MP2008_DATE");
        
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(queryString.toString());
        MP2008 mp2008 = null;
        ArrayList<MP2008> mp2008List = new ArrayList<MP2008>();
		while(rs.next()){
			mp2008 = new MP2008();
			mp2008.setMP2008_NUM(rs.getString("MP2008_NUM"));
			mp2008.setMP2008_DATE(rs.getString("MP2008_DATE"));
			mp2008.setMP2008_FROM_DATETIME(rs.getString("MP2008_FROM_DATETIME"));
			mp2008.setMP2008_TO_DATETIME(rs.getString("MP2008_TO_DATETIME"));
			mp2008.setMP2008_FROM_MINUTE(rs.getString("MP2008_FROM_MINUTE"));
			mp2008.setMP2008_TO_MINUTE(rs.getString("MP2008_TO_MINUTE"));
			mp2008.setMP2008_RATING(rs.getString("MP2008_RATING"));
			
			mp2008List.add(mp2008);
		}
		rs.close();
		connection.close();
		
		Map<String,String> holidayMap = new HashMap<String, String>();
		holidayMap.put("2013-06-17", "2013-06-17");
		
		for(MP2008 mp28 : mp2008List){
			int overHour = overTimeCalcute(mp28.getMP2008_FROM_DATETIME(),mp28.getMP2008_FROM_MINUTE(),mp28.getMP2008_TO_DATETIME(),mp28.getMP2008_TO_MINUTE(), checkHoliday(mp28.getMP2008_DATE(), holidayMap));
			
			System.out.println(mp28.getMP2008_NUM() + "|" + overHour);		
		}
	}
	private static boolean checkHoliday(String targetDate, Map<String,String> holidayMap) throws ParseException{
		SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
		Date _dateTime = sdf0.parse(targetDate);
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(_dateTime);
		
		int weekDay = calendar1.get(Calendar.DAY_OF_WEEK);
		// 周日：1
		return (1 == weekDay) || holidayMap.containsKey(targetDate);
	}
	// 加班时间计算
	private static int overTimeCalcute(String fH, String fM, String tH, String tM, boolean overTimeFlag){
		int _fH = Integer.parseInt(fH);
		int _fM = Integer.parseInt(fM);
		int _tH = Integer.parseInt(tH);
		int _tM = Integer.parseInt(tM);
		int _totalFrom = 0;
		int _totalTo = 0;
		String _totalH = "0";
		
		if(_tH < _fH){
			_totalH = "0";
		}else{
			_totalFrom = (int) (overTimeFlag? (_fH*60 + _fM)*2 : (_fH*60 + _fM)*1.5);
			_totalTo =  (int) (overTimeFlag? (_tH*60 + _tM)*2 : (_tH*60 + _tM)*1.5);
			_totalH = String.valueOf((_totalTo - _totalFrom)/60) + "H" + (_totalTo - _totalFrom)%60 + "M";
		}
		
		System.out.println(_totalH);
		return _totalTo - _totalFrom;
	}
	
	
}
