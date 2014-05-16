package schedule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import java.util.HashSet;
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
import entity.MP0010;
import entity.MP1001;
import entity.MP2001;
import entity.MP2003;
import entity.MP2008;
import entity.MP2010;
import entity.Overtime;

public class CommonJobMethod {
	// 取得人资系统数据库的链接
	public static Connection getDBConnection() throws ClassNotFoundException, SQLException{
		//String sqlUrl = "jdbc:jtds:sqlserver://192.168.50.172:1433/mpisiHr";
		//String sqlUrl = "jdbc:jtds:sqlserver://192.168.53.71:1433/mpisiHr;instance=JOE";
		//String sqlUrl = "jdbc:jtds:sqlserver://192.168.53.71:1433/mpisiHr_test;instance=JOE";
		String sqlUrl = "jdbc:jtds:sqlserver://192.168.53.66:1433/mpisiHr;instance=JOE";
		//String sqlUrl = "jdbc:jtds:sqlserver://MPC050141:1433/mpisiHr;instance=JOE";
		String classforname = "net.sourceforge.jtds.jdbc.Driver";
		String uid = "sa";
		String pwd = "IDpt463";
		Connection connSql = null;
		
		Class.forName(classforname);
		connSql = DriverManager.getConnection( sqlUrl, uid, pwd);
		
		return connSql;
	}
	public static Connection getDBConnection2() throws ClassNotFoundException, SQLException{
		//String sqlUrl = "jdbc:jtds:sqlserver://192.168.50.172:1433/finger";
		String sqlUrl = "jdbc:jtds:sqlserver://192.168.53.66:1433/testFinger;instance=JOE";
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
		
		//test
		System.out.println(queryString);

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

	//{ //here for shift execute jobs
	public static Set<String> getAllShiftWorkEmployeeNums(Date date) throws ClassNotFoundException, SQLException{
		Set<String> employeeNumSet = new HashSet<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Connection conn = getDBConnection();
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct(MP2010_EMPLOYEE_NUM) from MP2010 where 1=1");
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sb.toString());
		
		while(rs.next()){
			employeeNumSet.add(rs.getString("MP2010_EMPLOYEE_NUM"));
		}
		
		rs.close();
		conn.close();
		
		return employeeNumSet;
	}
	
	public static List<CheckInOutDto> getAllAttendanceRecords(Set<String> employeeNums) throws ClassNotFoundException, SQLException, ParseException {
		List<CheckInOutDto> recordsList = new ArrayList<CheckInOutDto>();
		
		if(null == employeeNums || 0 == employeeNums.size()){
			System.out.println("Employee set is null !");
			return null;
		}
		Connection conn = getDBConnection2();
		
		StringBuffer sb = new StringBuffer();
		sb.append("select u.SSN, c.CHECKTIME, c.SENSORID from CHECKINOUT c, USERINFO u where 1=1 and c.CHECKTIME>=DATEADD(DD, DATEDIFF(DD,0,getdate()), -14) and c.USERID=u.USERID and u.SSN in (");
		for(String employeeNum : employeeNums){
			sb.append("'" + employeeNum + "',");
		}
		
		//sb.append(")");
		//sb.append(" order by c.CHECKTIME ASC;"); //here records are ordered, from morning to night
		
		String str = sb.substring(0,sb.length() - 1) + ") order by c.CHECKTIME ASC;";
		
		ResultSet rs = conn.createStatement().executeQuery(str);
		while(rs.next()){
			CheckInOutDto cioDto = new CheckInOutDto();
			
			cioDto.setEmployeeNum(rs.getString("SSN"));
			cioDto.setCheckTime(rs.getString("CHECKTIME"));
			cioDto.setDoorId(rs.getString("SENSORID"));
			
			recordsList.add(cioDto);
		}
		rs.close();
		conn.close();
		
		return recordsList;
	}
	
	//if that day no any records, then no AttendanceRecordDto in the list
	public static List<AttendanceRecordDto> separateIntoEachDays(List<CheckInOutDto> checkInOutRecords){
		List<AttendanceRecordDto> dailyRecordslist = new ArrayList<AttendanceRecordDto>();
		//Map<String, AttendanceRecordDto> mapCacheAttendanceRecord = new HashMap<String, AttendanceRecordDto>();		
		
		for(CheckInOutDto dto : checkInOutRecords){
			String checkDateTime = dto.getCheckTime();
			String whichDay = checkDateTime.substring(0, 10);
			String employeeNum = dto.getEmployeeNum();

			boolean flag = false;
			for(AttendanceRecordDto arDto : dailyRecordslist){
				//if find that day and employee, then add this record
				if(arDto.getWhichDate().equalsIgnoreCase(whichDay) && arDto.getEmployeeNum().equalsIgnoreCase(employeeNum)){
					arDto.getCheckRecordsList().add(dto);
					flag = true;
				}
			}
			
			if(false == flag){
				AttendanceRecordDto newArDto = new AttendanceRecordDto();
				
				newArDto.setEmployeeNum(employeeNum);
				newArDto.setWhichDate(whichDay);
				newArDto.setCheckRecordsList(new ArrayList<CheckInOutDto>());
				newArDto.getCheckRecordsList().add(dto);
				
				dailyRecordslist.add(newArDto);
			}
		}
		//if the records are not sorted, sort it from morning to night
		return dailyRecordslist;
	}

	//didn't change mp2003_status, so you need to change it's status
	public static List<MP2003> calculateAttendanceRecordStatus(List<AttendanceRecordDto> dailyRecords, 
			Set<String> employeeNumSet, Map<String, MP2010> mapAllShiftRecords) throws ClassNotFoundException, SQLException, ParseException {
		//this method will execute at 2:00AM
		//get all work day first!
		/*
		 * compare each work day with their check records
		 */
		/*for(employeeNum : employeeNumSet){
			for(day:workday){ //shift work day is permanent
				for(AttendanceRecordDto dto : dailyRecords){
					if(dto.getWhichDate().equalsIgnoreCase(day) && dto.getEmployeeNum().equalsIgnoreCase(employeeNum)){ //find that day and employee
						//compare shift work time
						
					}
					else{ //insert into an empty one, comments is abnormal
						//just need to confirm did he apply for leave ? 
					}
				}
				
			}	
		}*/
		
		List<MP2003> mp2003ShiftWorkRecordList = new ArrayList<MP2003>(); //for all updated records
		Map<String, AttendanceRecordDto> mapCacheAttendanceRecord = new HashMap<String, AttendanceRecordDto>();
		for(AttendanceRecordDto recd : dailyRecords){
			String strKey = recd.getEmployeeNum() + "#" + recd.getWhichDate();
			mapCacheAttendanceRecord.put(strKey, recd);
		}
		//cache all leave application records
		
		

		//compare
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfGetTime = new SimpleDateFormat("HH:mm:ss");
		
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, -3); //the past weeks
		
		
		while(!cal.getTime().equals(today)){  //which day
			for(String employeeNum : employeeNumSet){ //employee number
				
				String strKey_0 = employeeNum + "#" + sdf.format(cal.getTime());
				MP2003 mp23 = new MP2003();
				mp23.setMP2003_EMPLOYEE_NUM(employeeNum);
				//mp23.setMP2003_DATETIME(sdf.format(today));
				mp23.setMP2003_DATETIME(sdf.format(cal.getTime()));
				
				if(mapCacheAttendanceRecord.containsKey(strKey_0)){
						AttendanceRecordDto dto = mapCacheAttendanceRecord.get(strKey_0);
						//java
						//String strKey_1 = employeeNum + "#" + sdf.format(cal.getTime());
						if(mapAllShiftRecords.containsKey(strKey_0)){
							MP2010 mp21 = mapAllShiftRecords.get(strKey_0);
							String type = mp21.getMP2010_TYPE();
							
							if(type.equalsIgnoreCase("N")){
								List<CheckInOutDto> listDailyCheckTime = dto.getCheckRecordsList();
								
								cal.add(Calendar.DAY_OF_MONTH, 1); //get tomorrow day information
								String nextDayKey = employeeNum + "#" + sdf.format(cal.getTime());
								cal.add(Calendar.DAY_OF_MONTH, -1); //recover
								AttendanceRecordDto nextDayDto = null;
								if(mapCacheAttendanceRecord.containsKey(nextDayKey)){
									nextDayDto = mapCacheAttendanceRecord.get(nextDayKey);	
								}
								List<CheckInOutDto> listNextDayCheckTime = null; 
								if(null != nextDayDto){
									listNextDayCheckTime = nextDayDto.getCheckRecordsList();
								}
								
								//that night attendance records
								List<CheckInOutDto> theNightCheckTimeList = new ArrayList<CheckInOutDto>(); 
								if(null != listDailyCheckTime && 0 != listDailyCheckTime.size()){
									for(CheckInOutDto tmpCheckInOutTime : listDailyCheckTime){
										//listDailyCheckTime is already ordered by time
										if(tmpCheckInOutTime.getCheckTime().substring(11, 19).compareToIgnoreCase("12:00:00") > 0){
											//if two checktime interval less than 2 minutes, then it's check in, then only get the earlier one, then get the later one.
											
											theNightCheckTimeList.add(tmpCheckInOutTime); //add to whole list
											break;
										}
									}	
								}
								if(null != listNextDayCheckTime && 0 != listNextDayCheckTime.size()){ 
									//******** there some problems about night leave, if leave start at 01:00:00 next day mornig, then it doesn't work any more 
									for(CheckInOutDto tmpCheckInOutTime : listNextDayCheckTime){
										if(tmpCheckInOutTime.getCheckTime().substring(11, 19).compareToIgnoreCase("12:00:00") < 0){ //next day before noon
											theNightCheckTimeList.add(tmpCheckInOutTime); //add to whole list
											break;
										}
									}
								}
								
								//analyze whole night records, the records start from first day 12:00:00 to following day 12:00:00
								if(0 == theNightCheckTimeList.size()){
									//get leave application
									Integer minutes = getNightLeaveMinutes(employeeNum, sdf.format(cal.getTime()));
									if(minutes < 480){
										mp23.setMP2003_COMMENT("Abnormal");
										mp23.setMP2003_STATUS("1");
									}
								}
								else if(1 == theNightCheckTimeList.size()){
									//get leave application, and tell
									Integer minutes = getNightLeaveMinutes(employeeNum, sdf.format(cal.getTime()));
									if(minutes < 720){ //12 hours
										mp23.setMP2003_COMMENT("Abnormal");
										mp23.setMP2003_STATUS("1");
									}
									//tell it's in or out
									CheckInOutDto c = theNightCheckTimeList.get(0);
									if(c.getCheckTime().substring(11, 19).compareToIgnoreCase("23:59:59") <= 0){//"23:59:59" is the middle night
										mp23.setMP2003_START_TIME(c.getCheckTime());
										mp23.setMP2003_START_TIME_DOOR(c.getDoorId());
									}
									else{ //next day morning
										mp23.setMP2003_FINISH_TIME(c.getCheckTime());
										mp23.setMP2003_FINISH_TIME_DOOR(c.getDoorId());
									}
								}
								else{
									//
									long el = cal.getTimeInMillis();
									el += 1000*60*60*24;
									Date followingDay = new Date();
									followingDay.setTime(el);
									String firstDay = sdf.format(cal.getTime());
									String strFollowingDay = sdf.format(followingDay);
									 
									CheckInOutDto firstCheckRcd = theNightCheckTimeList.get(0); //get first record
									CheckInOutDto lastCheckRcd = theNightCheckTimeList.get(theNightCheckTimeList.size() - 1);
									
									mp23.setMP2003_START_TIME(firstCheckRcd.getCheckTime());
									mp23.setMP2003_START_TIME_DOOR(firstCheckRcd.getDoorId());
									mp23.setMP2003_FINISH_TIME(lastCheckRcd.getCheckTime());
									mp23.setMP2003_FINISH_TIME_DOOR(lastCheckRcd.getDoorId());
									
									if(firstCheckRcd.getCheckTime().compareToIgnoreCase(firstDay + " " + Constant.shiftWorkNightStartTime) > 0 &&
											lastCheckRcd.getCheckTime().compareToIgnoreCase(strFollowingDay + " " + Constant.shiftWorkNightEndTime) >= 0){
										List<MP2001> listRcd = getNightLeavesByDate(employeeNum, firstDay);
										MP2001 tmpFirstLeaveRcd = listRcd.get(0);
										if(tmpFirstLeaveRcd.getMP2001_FROM_DATETIME().substring(0, 19).equalsIgnoreCase(firstDay + " " + Constant.shiftWorkNightStartTime)){
											if(firstCheckRcd.getCheckTime().substring(0, 19).compareToIgnoreCase(tmpFirstLeaveRcd.getMP2001_TO_DATETIME().substring(0, 19)) > 0){
												mp23.setMP2003_COMMENT("Late");
												mp23.setMP2003_STATUS("3");
											}
											/*else{//it's working normally
												
											}*/
										}
										else{ //leave application doesn't start from shiftwork time, so it's Abnormal
											mp23.setMP2003_COMMENT("Late");
											mp23.setMP2003_STATUS("3");
										}
									}
									else if(firstCheckRcd.getCheckTime().compareToIgnoreCase(firstDay + " " + Constant.shiftWorkNightStartTime) <= 0 &&
												lastCheckRcd.getCheckTime().compareToIgnoreCase(strFollowingDay + " " + Constant.shiftWorkNightEndTime) < 0){
										List<MP2001> listRcd = getNightLeavesByDate(employeeNum, firstDay);
										MP2001 tmpLastLeaveRcd = listRcd.get(listRcd.size() - 1);
										if(tmpLastLeaveRcd.getMP2001_TO_DATETIME().substring(0, 19).equalsIgnoreCase(followingDay + " " + Constant.shiftWorkNightEndTime)){
											if(lastCheckRcd.getCheckTime().substring(0, 19).compareToIgnoreCase(tmpLastLeaveRcd.getMP2001_FROM_DATETIME().substring(0, 19)) < 0){
												mp23.setMP2003_COMMENT("Early");
												mp23.setMP2003_STATUS("2");
											}
											/*else{//it's working normally
												
											}*/
										}
										else{ //leave application doesn't start from shiftwork time, so it's Abnormal
											mp23.setMP2003_COMMENT("Early");
											mp23.setMP2003_STATUS("2");
										}
									}
									else if(firstCheckRcd.getCheckTime().compareToIgnoreCase(firstDay + " " + Constant.shiftWorkNightStartTime) > 0 &&
												lastCheckRcd.getCheckTime().compareToIgnoreCase(strFollowingDay + " " + Constant.shiftWorkNightEndTime) < 0){
										List<MP2001> listRcd = getNightLeavesByDate(employeeNum, firstDay);
										if(0 == listRcd.size()){
											mp23.setMP2003_COMMENT("Late/Early");
										}
										else{
											MP2001 tmpFirstLeaveRcd = listRcd.get(0);
											MP2001 tmpLastLeaveRcd = listRcd.get(listRcd.size() - 1);
											if(tmpFirstLeaveRcd.getMP2001_FROM_DATETIME().substring(0, 19).equalsIgnoreCase(firstDay + " " + Constant.shiftWorkNightStartTime)){
												if(firstCheckRcd.getCheckTime().substring(0, 19).compareToIgnoreCase(tmpFirstLeaveRcd.getMP2001_TO_DATETIME().substring(0, 19)) > 0){
													mp23.setMP2003_COMMENT("Late");
													mp23.setMP2003_STATUS("3");
													if(lastCheckRcd.getCheckTime().substring(0, 19).compareToIgnoreCase(followingDay + " " + Constant.shiftWorkNightEndTime) < 0){
														mp23.setMP2003_COMMENT("Late/Early");
													}
												}
											}
											
											if(tmpLastLeaveRcd.getMP2001_TO_DATETIME().equalsIgnoreCase(followingDay + " " + Constant.shiftWorkNightEndTime)){
												if(lastCheckRcd.getCheckTime().substring(0, 19).compareToIgnoreCase(tmpLastLeaveRcd.getMP2001_FROM_DATETIME().substring(0, 19)) < 0){
													mp23.setMP2003_COMMENT("Early");
													mp23.setMP2003_STATUS("2");
													if(firstCheckRcd.getCheckTime().substring(0, 19).compareToIgnoreCase(firstDay + " " + Constant.shiftWorkNightStartTime) > 0){
														mp23.setMP2003_COMMENT("Late/Early");
													}
												}
											}
										}		
									}
									
								}
								
								
/*								//get first record after 12:00 AM
								CheckInOutDto firstCheckInOutRcd = null;
								for(CheckInOutDto tmpCheckInOutTime : listDailyCheckTime){
									//listDailyCheckTime is already ordered by time
									if(tmpCheckInOutTime.getCheckTime().substring(11, 19).compareToIgnoreCase("12:00:00") > 0){
										firstCheckInOutRcd = tmpCheckInOutTime; //get first clock in record
										break;
									}
								}
								if(null == firstCheckInOutRcd){ //didn't find the record
									//leave application
									List<MP2001> tmpMP2001List = getLeaveApplications(employeeNum, sdf.format(cal.getTime()));
									if(null == tmpMP2001List || 0 == tmpMP2001List.size()){
										mp23.setMP2003_COMMENT("Abnormal");	
									}
									else{
										MP2001 firstMP2001 = tmpMP2001List.get(0);
										String startTime = firstMP2001.getMP2001_FROM_DATETIME();
										String endTime = firstMP2001.getMP2001_TO_DATETIME();
										if(startTime.substring(11, 19).equalsIgnoreCase(Constant.shiftWorkNightStartTime)){
											if(endTime.substring(11, 19).compareToIgnoreCase()){
												
											}
										}
									}
									
									//set abnormal
									
								}
								else{
									mp23.setMP2003_START_TIME(firstCheckInOutRcd.getCheckTime());
									mp23.setMP2003_START_TIME_DOOR(firstCheckInOutRcd.getDoorId());
									//compare date time
									if(firstCheckInOutRcd.getCheckTime().compareToIgnoreCase(Constant.shiftWorkNightStartTime) >= 0){
										//get leave application and calculate them. 
										if(){
											
										}
										
										mp23.setMP2003_COMMENT("Late clock in");
										//here can add late minutes, 
									}
								}
								
								
								
								//get next day first checkin record
								CheckInOutDto lastCheckInOutRcd = null;
								for(CheckInOutDto tmpCheckInOutTime : listDailyCheckTime){
									//listDailyCheckTime is already ordered by time
									if(tmpCheckInOutTime.getCheckTime().substring(11, 19).compareToIgnoreCase("12:00:00") >= 0){
										break; //if find the one is 
									}
									lastCheckInOutRcd = tmpCheckInOutTime; //get last clock out record in the morning
								}
								if(null == lastCheckInOutRcd){
									mp23.setMP2003_FINISH_TIME("");
									mp23.setMP2003_COMMENT("Abnormal");
								}
								else{
									mp23.setMP2003_FINISH_TIME(lastCheckInOutRcd.getCheckTime());
									mp23.setMP2003_FINISH_TIME_DOOR(lastCheckInOutRcd.getDoorId());
									
									if(lastCheckInOutRcd.getCheckTime().compareToIgnoreCase(Constant.shiftWorkNightEndTime) >= 0){
										//calculate if it applies for early application
										
										
										mp23.setMP2003_COMMENT("Early leave");
										//can calcuate minutes differential
									}

								}
*/
							}
							else if(type.equalsIgnoreCase("D")){
								List<CheckInOutDto> listDailyCheckTime = dto.getCheckRecordsList();
								
								if(null == listDailyCheckTime || 0 == listDailyCheckTime.size()){
									mp23.setMP2003_START_TIME("");
									mp23.setMP2003_START_TIME_DOOR("");
									mp23.setMP2003_FINISH_TIME("");
									mp23.setMP2003_FINISH_TIME_DOOR("");
								}
								else if(1 == listDailyCheckTime.size()){
									CheckInOutDto tmp = listDailyCheckTime.get(0);
									
									//String strTime = tmp.getCheckTime().substring(11, tmp.getCheckTime().length());
									String strTime = tmp.getCheckTime().substring(11, 19);
									if(strTime.compareToIgnoreCase("16:00:00") > 0){//here best is to compare application time
										mp23.setMP2003_FINISH_TIME(strTime);
										mp23.setMP2003_FINISH_TIME_DOOR(tmp.getDoorId());
									}
									else{
										mp23.setMP2003_START_TIME(strTime);
										mp23.setMP2003_START_TIME_DOOR(tmp.getDoorId());
									}
									
								}
								else { //listDailyCheckTime.size() >= 2
									//get first record after 12:00 AM
									CheckInOutDto firstCheckInOutRcd = null;
									CheckInOutDto lastCheckInOutRcd = null;
									firstCheckInOutRcd = listDailyCheckTime.get(0);
									lastCheckInOutRcd = listDailyCheckTime.get(listDailyCheckTime.size() - 1);
									
									//compare date time
									mp23.setMP2003_START_TIME(firstCheckInOutRcd.getCheckTime());
									mp23.setMP2003_START_TIME_DOOR(firstCheckInOutRcd.getDoorId());
									mp23.setMP2003_FINISH_TIME(lastCheckInOutRcd.getCheckTime());
									mp23.setMP2003_FINISH_TIME_DOOR(lastCheckInOutRcd.getDoorId());
									
									if(firstCheckInOutRcd.getCheckTime().substring(11, 19).compareToIgnoreCase(Constant.shiftWorkDayStartTime) > 0 &&
											lastCheckInOutRcd.getCheckTime().substring(11, 19).compareToIgnoreCase(Constant.shiftWorkDayEndTime) < 0){
										List<MP2001> leaveApplications = getLeaveApplications(employeeNum, sdf.format(cal.getTime()));
										if(0 == leaveApplications.size()){
											mp23.setMP2003_COMMENT("Late/Early");
										}
										else{
											MP2001 tmpMP21 = leaveApplications.get(0); //first record, it's the earliest record
											String fromTime = tmpMP21.getMP2001_FROM_DATETIME().substring(11, 19);
											String endTime = tmpMP21.getMP2001_TO_DATETIME().substring(11, 19);
											
											if(fromTime.equalsIgnoreCase(Constant.shiftWorkDayStartTime)){
												if(firstCheckInOutRcd.getCheckTime().substring(11, 19).compareToIgnoreCase(endTime) > 0){
													mp23.setMP2003_COMMENT("Late");
													mp23.setMP2003_STATUS("3");
													if(lastCheckInOutRcd.getCheckTime().substring(11, 19).compareToIgnoreCase(Constant.shiftWorkDayEndTime) < 0){
														mp23.setMP2003_COMMENT("Late/Early");
													}
												}
												else{ //normal
													if(lastCheckInOutRcd.getCheckTime().substring(11, 19).compareToIgnoreCase(Constant.shiftWorkDayEndTime) < 0){
														mp23.setMP2003_COMMENT("Early");
														mp23.setMP2003_STATUS("2");
													}
												}
											}
											
											tmpMP21 = leaveApplications.get(leaveApplications.size() - 1); //last record, it's the latest record
											fromTime = tmpMP21.getMP2001_FROM_DATETIME().substring(11, 19);
											endTime = tmpMP21.getMP2001_TO_DATETIME().substring(11, 19);
											if(endTime.equalsIgnoreCase(Constant.shiftWorkDayEndTime)){
												if(lastCheckInOutRcd.getCheckTime().substring(11, 19).compareToIgnoreCase(fromTime) < 0){
													mp23.setMP2003_COMMENT("Early");
													mp23.setMP2003_STATUS("2");
													if(firstCheckInOutRcd.getCheckTime().substring(11, 19).compareToIgnoreCase(Constant.shiftWorkDayStartTime) > 0){
														mp23.setMP2003_COMMENT("Late/Early");
													}
												}
												else{
													if(firstCheckInOutRcd.getCheckTime().substring(11, 19).compareToIgnoreCase(Constant.shiftWorkDayStartTime) > 0){
														mp23.setMP2003_COMMENT("Late");
														mp23.setMP2003_STATUS("3");
													}
												}
											}
										}
									}
									else if(firstCheckInOutRcd.getCheckTime().substring(11, 19).compareToIgnoreCase(Constant.shiftWorkDayStartTime) > 0 &&
											lastCheckInOutRcd.getCheckTime().substring(11, 19).compareToIgnoreCase(Constant.shiftWorkDayEndTime) >= 0){
										//calculate application time
										List<MP2001> leaveApplications = getLeaveApplications(employeeNum, sdf.format(cal.getTime()));
										if(0 == leaveApplications.size()){
											mp23.setMP2003_COMMENT("Late");
											mp23.setMP2003_STATUS("3");
										}
										else{
											MP2001 tmpMP21 = leaveApplications.get(0); //first record, it's the earliest record
											String fromTime = tmpMP21.getMP2001_FROM_DATETIME().substring(11, 19);
											String endTime = tmpMP21.getMP2001_TO_DATETIME().substring(11, 19);
											
											if(fromTime.equalsIgnoreCase(Constant.shiftWorkDayStartTime)){
												if(firstCheckInOutRcd.getCheckTime().substring(11, 19).compareToIgnoreCase(endTime) > 0){
													mp23.setMP2003_COMMENT("Late");
													mp23.setMP2003_STATUS("3");
												}
												else{ //normal
													mp23.setMP2003_COMMENT("");
												}
											}
										}
										//here can add minutes
									}
									else if(firstCheckInOutRcd.getCheckTime().substring(11, 19).compareToIgnoreCase(Constant.shiftWorkDayStartTime) <= 0 &&
											lastCheckInOutRcd.getCheckTime().substring(11, 19).compareToIgnoreCase(Constant.shiftWorkDayEndTime) < 0){
										
										List<MP2001> leaveApplications = getLeaveApplications(employeeNum, sdf.format(cal.getTime()));
										if(0 == leaveApplications.size()){
											mp23.setMP2003_COMMENT("Early");
											mp23.setMP2003_STATUS("2");
										}
										else{
											MP2001 tmpMP21 = leaveApplications.get(leaveApplications.size() - 1); //get last record, it's the earliest record
											String fromTime = tmpMP21.getMP2001_FROM_DATETIME().substring(11, 19);
											String endTime = tmpMP21.getMP2001_TO_DATETIME().substring(11, 19);
											
											if(endTime.equalsIgnoreCase(Constant.shiftWorkDayEndTime)){
												if(lastCheckInOutRcd.getCheckTime().substring(11, 19).compareToIgnoreCase(fromTime) < 0){
													mp23.setMP2003_COMMENT("Early");
													mp23.setMP2003_STATUS("2");
												}
												else{
													mp23.setMP2003_COMMENT("");
												}
											}
										}
										//calculate application time
									}
									else{
										mp23.setMP2003_COMMENT("");
									}
								}
							}
							else if(type.equalsIgnoreCase("R")){
									
							}
						}
						else{ //can't find shiftwork arrangement record
							mp23.setMP2003_COMMENT("No arrangement");
						}
				} 
				else { //no check in/out records
					//check if he applys for leave
					if(mapAllShiftRecords.containsKey(strKey_0)){ //but can find the arrangement records, every have a record no matter is D,N or R
						MP2010 mp21 = mapAllShiftRecords.get(strKey_0);
						String type = mp21.getMP2010_TYPE();
						//find leave application records
						
						//the granule is day, can't calculate hours and minutes now. 2014/04/16
						if(type.equalsIgnoreCase("N")){
							//find the whole day records
							double hours = getThatDayLeaveHours(employeeNum, cal.getTime());
							if(hours >= 12){ //the whole day.
								//tmpRecord.
								mp23.setMP2003_COMMENT("Night ShiftWork Leave 12H");
								mp23.setMP2003_STATUS("2");
							}
							else if(hours < 12 && hours > 0){
								mp23.setMP2003_COMMENT("Abnormal"); //also set to abnormal current
								mp23.setMP2003_STATUS("1");
							}
							else{ //because no records and didn't apply for whole day, , if he applies
								mp23.setMP2003_COMMENT("Abnormal");
								mp23.setMP2003_STATUS("1");
							}
							
						}
						else if(type.equalsIgnoreCase("D")){
							//find the whole day records
							double hours = getThatDayLeaveHours(employeeNum, cal.getTime());
							if(hours >= 12){ //the whole day.
								//tmpRecord.
								mp23.setMP2003_COMMENT("Shiftwork Leave 12H");
								mp23.setMP2003_STATUS("2");
							}
							else if(hours < 12 && hours > 0){
								mp23.setMP2003_COMMENT("Abnormal"); //also set to abnormal
								mp23.setMP2003_STATUS("1");
							}
							else{ //because no records and didn't apply for whole day, , if he applies
								mp23.setMP2003_COMMENT("Abnormal");
								mp23.setMP2003_STATUS("1");
							}
							
						}
						else if(type.equalsIgnoreCase("R")){ //no R records right now
							mp23.setMP2003_COMMENT("Shiftwork Rest");
						}
						else{
							System.out.print("error records!");
						}
						//
					}
					else{//no arrangement find, this part is useless, because I have no time 
						mp23.setMP2003_COMMENT("No arrangement");
					}
				}
				mp2003ShiftWorkRecordList.add(mp23);
			}

			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		return mp2003ShiftWorkRecordList;
	}
	
	public static Map<String, MP2010> getAllShiftArrangeRecords() throws ClassNotFoundException, SQLException {
		Map<String, MP2010> dataMap = new HashMap<String, MP2010>();
		
		Connection conn = getDBConnection();
		String query = "select * from MP2010;"; //add a time limitation get
		PreparedStatement pst =  conn.prepareStatement(query);
		
		ResultSet rs = pst.executeQuery();
		MP2010 shiftworkRecord = null;
		while(rs.next()){
			shiftworkRecord = new MP2010();
			shiftworkRecord.setMP2010_BRANCH_SITE(rs.getString("MP2010_BRANCH_SITE"));
			String strDate = rs.getString("MP2010_DATE");
			shiftworkRecord.setMP2010_DATE(strDate);
			String employeeNum = rs.getString("MP2010_EMPLOYEE_NUM");
			shiftworkRecord.setMP2010_EMPLOYEE_NUM(employeeNum);
			shiftworkRecord.setMP2010_END_DATETIME(rs.getString("MP2010_END_DATETIME"));
			shiftworkRecord.setMP2010_FROM_DATETIME(rs.getString("MP2010_FROM_DATETIME"));
			shiftworkRecord.setMP2010_ID(rs.getInt("MP2010_ID"));
			shiftworkRecord.setMP2010_TYPE(rs.getString("MP2010_TYPE"));
			
			String strKey = employeeNum + "#" + strDate.substring(0, 10);
			dataMap.put(strKey, shiftworkRecord);
		}

		return dataMap;
	}
	
	public static List<MP2001> getLeaveApplications(String empNum, String date) throws ClassNotFoundException, SQLException {
	 	//judge if his leave application overlap
		Connection conn = getDBConnection();

		List<MP2001> listMP2001 = findByDate(empNum, date, conn);
		//MP2001 tmpRecord = listMP2001.get(0); //remeber to one day only have one type of leave!!sick, annual leave or other one.
		
		/*for(){
			
		}*/
		
		conn.close();
		return listMP2001;
	}
	public static double getThatDayLeaveHours(String empNum, Date date) throws ClassNotFoundException, SQLException, ParseException{ //Day application, not for the night
		double rt = 0;
		Connection conn = getDBConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//SimpleDateFormat sdf_1 = new SimpleDateFormat("hh");
		//SimpleDateFormat sdf_2 = new SimpleDateFormat("mm");
		Calendar cal_day = Calendar.getInstance();
		Calendar cal_from = Calendar.getInstance();
		Calendar cal_to = Calendar.getInstance();
		cal_day.setTime(date);
		
		List<MP2001> mp21List = findByDate(empNum, new SimpleDateFormat("yyyy-MM-dd").format(date), conn);
		for(MP2001 mp21 : mp21List){
			cal_from.setTime(sdf.parse(mp21.getMP2001_FROM_DATETIME()));
			cal_to.setTime(sdf.parse(mp21.getMP2001_TO_DATETIME()));
			if(cal_from.get(Calendar.DAY_OF_YEAR) == cal_to.get(Calendar.DAY_OF_YEAR)){ // the same day
				int h = cal_to.get(Calendar.HOUR_OF_DAY) - cal_from.get(Calendar.HOUR_OF_DAY);
				int toMin = cal_to.get(Calendar.MINUTE);
				int fromMin = cal_from.get(Calendar.MINUTE);
				if(toMin - fromMin < 0){
					h--;
					h += 0.5;
				}
				if(toMin - fromMin > 0){
					h += 0.5;
				}
				rt += h;
			}
			else{ //not the same day
				if(cal_from.get(Calendar.DAY_OF_YEAR) == cal_day.get(Calendar.DAY_OF_YEAR)){
					int h = cal_from.get(Calendar.HOUR_OF_DAY);
					rt += h - 8; //8 clock
				}
				if(cal_to.get(Calendar.DAY_OF_YEAR) == cal_day.get(Calendar.DAY_OF_YEAR)){
					int h = cal_to.get(Calendar.HOUR_OF_DAY);
					rt += h - 8;
				}
				else{
					rt = 12;
					break;
				}
			}
		}
		return rt;
	}
	public static int getThatDayLeaveHours(String empNum, String date, List<MP2001> mp2001List){ //from day is that day
		int rt = 0;
		for(MP2001 mp21 : mp2001List){
			
		}
		return rt;
	}
	
	public static List<MP2001> findNightLeaveByDate(String empNum, String date, Connection conn) throws ClassNotFoundException, SQLException, ParseException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse(date);
		cal.setTime(d);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String followDay = sdf.format(cal.getTime()); //get the following date 
		
		ArrayList<MP2001> mp2001List = new ArrayList<MP2001>();
		StringBuffer queryString = new StringBuffer();
		
		Statement statement = conn.createStatement();
		
		queryString.append(" select * ");
		queryString.append(" from MP2001 ");
		queryString.append(" where 1=1 ");
		queryString.append(" and MP2001_STATUS = '0' " );
		queryString.append(" and MP2001_APPROVAL = '3' " );
		queryString.append(" and MP2001_EMPLOYEE_NUM = '" + empNum + "' ");
		queryString.append(" and ((Convert(char(10),MP2001_FROM_DATETIME,120) <= '" + date + "' ");
		queryString.append(" and Convert(char(10),MP2001_TO_DATETIME,120) >= '" + date + "') ");
		//and next day leave application
		queryString.append(" or (Convert(char(10),MP2001_FROM_DATETIME,120) <='" + followDay + "' ");
		queryString.append(" and Convert(char(10),MP2001_TO_DATETIME,120) >='" + followDay + "')) ");
		queryString.append(" order by MP2001_FROM_DATETIME ASC"); //here is important, so I can merge them later.
		
		//test
		System.out.println(queryString);

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
	
	public static List<MP2001> getNightLeavesByDate(String empNum, String date) throws ClassNotFoundException, SQLException, ParseException {
		List<MP2001> mp2001List = null;
		Connection conn = getDBConnection();
		
		mp2001List = findNightLeaveByDate(empNum, date, conn);
		
		conn.close();
		return mp2001List;
	}
	
	
	public static Integer getNightLeaveMinutes(String empNum, String date) throws ClassNotFoundException, SQLException, ParseException { //return minutes
		Connection conn  = getDBConnection();
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfM = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse(date);
		cal.setTime(d);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String followingDay = sdf.format(cal.getTime());
		
		List<MP2001> mp2001List = findNightLeaveByDate(empNum, date, conn);
		
		double rt = 0;
		//mp2001List =
		for(MP2001 mp21 : mp2001List){
			String workStartDateTime = date + " " + Constant.shiftWorkNightStartTime;
			String workEndDateTime = followingDay + " " + Constant.shiftWorkNightEndTime; 
			if(mp21.getMP2001_FROM_DATETIME().compareToIgnoreCase(workStartDateTime) <= 0 && 
					mp21.getMP2001_TO_DATETIME().compareToIgnoreCase(workEndDateTime) >= 0){
				rt = 12 * 60;
				break;
			}
			else if(mp21.getMP2001_FROM_DATETIME().compareToIgnoreCase(workStartDateTime) > 0 && 
					mp21.getMP2001_TO_DATETIME().compareToIgnoreCase(workEndDateTime) >= 0){
				//mp21.getMP2001_FROM_DATETIME();
				long startEl = sdfM.parse(mp21.getMP2001_FROM_DATETIME()).getTime();
				long endEl = sdfM.parse(workEndDateTime).getTime();
				double db = endEl - startEl;
				double mins = db/(1000*60);
				rt += mins;
			}
			else if(mp21.getMP2001_FROM_DATETIME().compareToIgnoreCase(workStartDateTime) <= 0 && 
					mp21.getMP2001_TO_DATETIME().compareToIgnoreCase(workEndDateTime) < 0){
				long startEl = sdfM.parse(workStartDateTime).getTime();
				long endEl = sdfM.parse(mp21.getMP2001_TO_DATETIME()).getTime();
				double db = endEl - startEl;
				double mins = db/(1000*60);
				
				rt += mins;
			}
			else{
				long startEl = sdfM.parse(mp21.getMP2001_FROM_DATETIME()).getTime();
				long endEl = sdfM.parse(mp21.getMP2001_TO_DATETIME()).getTime();
				double db = endEl - startEl;
				double mins = db/(1000*60);
				
				rt += mins;
			}
		}
		
		conn.close();
		return (int)rt;
	}
	
	public static void deleteOldAttendanceRcd(Set<String> employeeSet) throws ClassNotFoundException, SQLException{// two weeks records
		Connection conn = getDBConnection();
		
		StringBuffer sb = new StringBuffer();
		sb.append("delete from MP2003 where MP2003_DATETIME>=DATEADD(DD, DATEDIFF(DD,0,getdate()), -14) and MP2003_EMPLOYEE_NUM in (");
		for(String num : employeeSet){
			sb.append("'");
			sb.append(num);
			sb.append("',");
		}
		String sql = sb.substring(0, sb.length() - 1);
		
		PreparedStatement st = conn.prepareStatement(sql + ")");
		st.executeUpdate();
		st.close();
		conn.close();
	}
	
	public static void insertCalculatedAttendanceRcd(List<MP2003> calculatedAttendanceRcdList) throws SQLException, ClassNotFoundException {
		Connection conn = getDBConnection();
		Statement st = conn.createStatement();
		
		for(MP2003 mp23 : calculatedAttendanceRcdList){
			StringBuffer sb = new StringBuffer();
			sb.append("insert into MP2003(");
			sb.append("MP2003_EMPLOYEE_NUM,");
			sb.append("MP2003_DATETIME,");
			sb.append("MP2003_START_TIME,");
			sb.append("MP2003_FINISH_TIME,");
			sb.append("MP2003_START_TIME_DOOR,");
			sb.append("MP2003_FINISH_TIME_DOOR,");
			sb.append("MP2003_START_TIME_E,");
			sb.append("MP2003_FINISH_TIME_E,");
			sb.append("MP2003_COMMENT,");
			sb.append("MP2003_STATUS)");
			
			sb.append(" values('");
			sb.append(mp23.getMP2003_EMPLOYEE_NUM());
			sb.append("','");
			sb.append(mp23.getMP2003_DATETIME());
			sb.append("','");
			if("" == mp23.getMP2003_START_TIME() || null == mp23.getMP2003_START_TIME()){
				sb.delete(sb.length() - 1, sb.length());
				sb.append("null");
				sb.append(",'");
			}
			else{
				sb.append(mp23.getMP2003_START_TIME());
				sb.append("','");
			}
			
			if("" == mp23.getMP2003_FINISH_TIME() || null == mp23.getMP2003_FINISH_TIME()){
				sb.delete(sb.length() - 1, sb.length());
				sb.append("null");
				sb.append(",'");
			}
			else{
				sb.append(mp23.getMP2003_FINISH_TIME());
				sb.append("','");
			}
			
			if("" == mp23.getMP2003_START_TIME_DOOR() || null == mp23.getMP2003_START_TIME_DOOR()){
				sb.delete(sb.length() - 1, sb.length());
				sb.append("null");
				sb.append(",'");
			}
			else{
				sb.append(mp23.getMP2003_START_TIME_DOOR());
				sb.append("','");
			}
			
			if("" == mp23.getMP2003_FINISH_TIME_DOOR() || null == mp23.getMP2003_FINISH_TIME_DOOR()){
				sb.delete(sb.length() - 1, sb.length());
				sb.append("null");
				sb.append(",'");
			}
			else{
				sb.append(mp23.getMP2003_FINISH_TIME_DOOR());
				sb.append("','");
			}
			
			//sb.append(mp23.getMP2003_START_TIME()); //the  MP2003_START_TIME_E is the same as MP2003_START_TIME
			if("" == mp23.getMP2003_START_TIME_E() || null == mp23.getMP2003_START_TIME_E()){
				sb.delete(sb.length() - 1, sb.length());
				sb.append("null");
				sb.append(",'");
			}
			else{
				sb.append(mp23.getMP2003_START_TIME());	//the  MP2003_START_TIME_E is the same as MP2003_START_TIME
				sb.append("','");
			}
			
			//sb.append(mp23.getMP2003_FINISH_TIME());
			if("" == mp23.getMP2003_FINISH_TIME_E() || null == mp23.getMP2003_FINISH_TIME_E()){
				sb.delete(sb.length() - 1, sb.length());
				sb.append("null");
				sb.append(",'");
			}
			else{
				sb.append(mp23.getMP2003_FINISH_TIME());	//the  MP2003_START_TIME_E is the same as MP2003_START_TIME
				sb.append("','");
			}
			
			if("" == mp23.getMP2003_COMMENT() || null == mp23.getMP2003_COMMENT()){
				sb.delete(sb.length() - 1, sb.length());
				sb.append("null");
				sb.append(",'");
			}
			else{
				sb.append(mp23.getMP2003_COMMENT()); //the  MP2003_START_TIME_E is the same as MP2003_START_TIME
				sb.append("','");
			}
			
			if("" == mp23.getMP2003_STATUS() || null == mp23.getMP2003_STATUS()){
				sb.delete(sb.length() - 1, sb.length());
				sb.append("null");
				//sb.append(",");
			}
			else{
				sb.append(mp23.getMP2003_STATUS()); 
				sb.append("'");
			}
			sb.append(")");
			st.executeUpdate(sb.toString());
		}
		
		st.close();
		conn.close();
	}
	
	//for whole company
	//MP2003_STATUS: == 1 : Abnormal, == 2 : Leave
	public static void sendAbnormalEmails() throws ClassNotFoundException, SQLException{
		Connection conn = getDBConnection();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from MP2003 where MP2003_STATUS='1' and MP2003_DATETIME='");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, -1); //yesterday
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sb.append(sdf.format(cal.getTime()));
		sb.append("'");
		
		List<MP1001> manangerList = new ArrayList<MP1001>();
		List<MP1001> employeeList = new ArrayList<MP1001>();
		List<MP2003> attendanceRecordList = new ArrayList<MP2003>();
		//PreparedStatement st = conn.prepareStatement(sb.toString());
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sb.toString());
		while(rs.next()){
			MP2003 mp23 = new MP2003();
			mp23.setMP2003_EMPLOYEE_NUM(rs.getString("MP2003_EMPLOYEE_NUM"));
			mp23.setMP2003_DATETIME(rs.getString("MP2003_DATETIME"));
			mp23.setMP2003_COMMENT(rs.getString("MP2003_COMMENT"));
			mp23.setMP2003_START_TIME(rs.getString("MP2003_START_TIME"));
			mp23.setMP2003_FINISH_TIME(rs.getString("MP2003_FINISH_TIME"));
			mp23.setMP2003_START_TIME_DOOR(rs.getString("MP2003_START_TIME_DOOR"));
			mp23.setMP2003_FINISH_TIME_DOOR(rs.getString("MP2003_FINISH_TIME_DOOR"));
			mp23.setMP2003_EDIT_DATETIME(rs.getString("MP2003_EDIT_DATETIME"));
			mp23.setMP2003_EDIT_USER(rs.getString("MP2003_EDIT_USER"));
			
			attendanceRecordList.add(mp23);
		}
		
		if(0 == attendanceRecordList.size()){
			return ; //no abnormal records found
		}
		
		sb.delete(0, sb.length());
		sb.append("select * from MP1001 where MP1001_EMPLOYEE_NUM in (");
		for(MP2003 mp23 : attendanceRecordList){
			sb.append("'");
			sb.append(mp23.getMP2003_EMPLOYEE_NUM());
			sb.append("',");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		rs = st.executeQuery(sb.toString());
		while(rs.next()){
			MP1001 mp11 = new MP1001();
			mp11.setMP1001_EMPLOYEE_NUM(rs.getString("MP1001_EMPLOYEE_NUM"));
			mp11.setMP1001_SURNAME(rs.getString("MP1001_SURNAME"));
			mp11.setMP1001_PREFERED_NAME(rs.getString("MP1001_PREFERED_NAME"));
			mp11.setMP1001_DEPARTMENT_ID(rs.getString("MP1001_DEPARTMENT_ID"));
			employeeList.add(mp11);
		}
		
		sb.delete(0, sb.length());
		sb.append("select * from MP1001 where MP1001_POSITION='1' and MP1001_STATUS in ('1', '2')");
		rs = st.executeQuery(sb.toString());
		while(rs.next()){
			MP1001 mnger = new MP1001();
			mnger.setMP1001_EMPLOYEE_NUM(rs.getString("MP1001_EMPLOYEE_NUM"));
			mnger.setMP1001_DEPARTMENT_ID(rs.getString("MP1001_DEPARTMENT_ID"));
			mnger.setMP1001_COMPANY_EMAIL(rs.getString("MP1001_COMPANY_EMAIL"));
			manangerList.add(mnger);
		}
		
		st.close();
		conn.close();


		Mail mail = new Mail();
		mail.setSubject("Department Abnormal");
		sb.delete(0, sb.length());
		sb.append("As attendance records hook up with employee's salary, please pay more attention !");
		sb.append("Date : " + sdf.format(cal.getTime()));
		sb.append("\n");
		sb.append("Abnormal employee list : ");
		sb.append("\n");
		int length = sb.length();
		
		List<MP1001> dptEmps = new ArrayList<MP1001>();
		boolean flag = true;
		for(MP1001 mger : manangerList){
			for(MP1001 emp : employeeList){
				if(mger.getMP1001_DEPARTMENT_ID().equalsIgnoreCase((emp.getMP1001_DEPARTMENT_ID()))){
					sb.append(emp.getMP1001_EMPLOYEE_NUM());
					sb.append("\t");
					sb.append(emp.getMP1001_PREFERED_NAME());
					sb.append("    ");
					sb.append(emp.getMP1001_SURNAME());
					sb.append("\n");
					flag = false;
				}
			}

			if(!flag){
				mail.setContent(sb.toString());
				mail.setTo(mger.getMP1001_COMPANY_EMAIL());
				mail.send();
				System.out.println("abnormal email send!");
				dptEmps.clear();
				sb.delete(length, sb.length());
				flag = true;
			}
		}
	}
}