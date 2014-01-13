package common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UtilDate {
	 public static final String FormatDayToChinaTime24H = "yyyy年MM月dd日";   
	 
	 /**  
	 * 将yyyy-mm-dd转为yyyy-m-d  
	 *   
	 * @param day  
	 *            距离现在之后的天数  
	  * @return Date:距离现在之后的若干天的日期;  
	  */  
	 public static String getYMDDays(String day){   
	     String dayTime=day;   
	     if(String.valueOf(day.charAt(8)).equals("0"))dayTime=day.substring(0,8)+day.substring(9);   
	     if(String.valueOf(day.charAt(5)).equals("0"))dayTime=dayTime.substring(0,5)+dayTime.substring(6);   
	     return dayTime;   
	 }   
	    
	 /**  
	  * 两个日期间的天数  
	  *   
	  * @param days  
	  *            距离现在之后的天数  
	  * @return Date:距离现在之后的若干天的日期;  
	  */  
	 public static int getIntervalDays(Date startday,Date endday){           
	     if(startday.after(endday)){   
	         Date cal=startday;   
	         startday=endday;   
	         endday=cal;   
	     }           
	     long sl=startday.getTime();   
	     long el=endday.getTime();          
	     long ei=el-sl;              
	     return (int)(ei/(1000*60*60*24));   
	 }   
	 
	 /**  
	  * 取得距离现在多少天（距离现在之后的若干天）  
	  *   
	  * @param days  
	  *            距离现在之后的天数  
	  * @return Date:距离现在之后的若干天的日期;  
	  */  
	 public static Date getDate(int days) {   
	     Date dateresult = new Date();   
	     try {   
	         //DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);   
	         // Create our Gregorian Calendar.   
	         GregorianCalendar cal = new GregorianCalendar();
	 
	         // Set the date and time of our calendar   
	         // to the system&s date and time   
	         cal.setTime(new Date());
	         cal.add(GregorianCalendar.DAY_OF_MONTH, days);
	         dateresult = cal.getTime();
	     } catch (Exception e) {   
	         System.out.println("exception" + e.toString());   
	     }   
	     return dateresult;   
	 }   
	 
	 /**  
	  * @根据当前日期计算n天后的日期  
	  * @return String  
	  */  
	 public static Date afterNDay(Date date, int n) {   
	     Calendar c = Calendar.getInstance();   
	     //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");   
	     c.setTime(date);   
	     c.add(Calendar.DAY_OF_MONTH, n);   
	     Date d2 = c.getTime();   
	     // String s=df.format(d2);   
	     return d2;   
	 }   
	    
	 /**  
	  * @根据当前日期计算n天后的日期  
	  * @return String  
	  */  
	 public static String afterDay(Date date, int n) {   
	     //Calendar c = Calendar.getInstance();   
	     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");   
	     // String s=df.format(d2);   
	     return df.toString();   
	 }   
	 
	 /**  
	  * @获取当前时间是星期几，“星期日”  
	  * @return String  
	  */  
	 public static String getDayInWeek() {   
	     Date today = new Date(System.currentTimeMillis());   
	     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E");   
	     return simpleDateFormat.format(today);   
	 }   
	 
	 /**  
	   *   
	   * @跟具字符串时间来获得星期几，(SimpleDateFormat("yyyy-MM-dd"))  
	   * @return  
	   */  
	  public static String getDayInWeek(String mydatestring) {   
	   SimpleDateFormat   dateFormat   =   new   SimpleDateFormat("yyyy-MM-dd");      
	   Date   date   =   null;    
	   String   showDate   ="";    
	   try{      
	           date   =   dateFormat.parse(mydatestring);      
	   }catch(ParseException e){      
	 
	   }      
	   Calendar   cd   =   Calendar.getInstance();      
	   cd.setTime(date);      
	   int   mydate   =   cd.get(Calendar.DAY_OF_WEEK);      
	     
	   switch   (mydate)   {      
	   case   1:      
	           showDate   =   "星期日";      
	           break;      
	   case   2:      
	           showDate   =   "星期一";      
	           break;      
	   case   3:      
	           showDate   =   "星期二";      
	           break;      
	   case   4:      
	           showDate   =   "星期三";      
	           break;      
	   case   5:      
	           showDate   =   "星期四";      
	           break;      
	   case   6:      
	           showDate   =   "星期五";      
	           break;      
	   default:      
	           showDate   =   "星期六";      
	           break;      
	   }
	 
	  return showDate;   
	     
	  }   

	  /**  
	   * @根据日期时间，获取当前时间是星期几，“星期日”  
	   * @return String  
	   */  
	  public static String getDayInWeek(Date date) {   
	      if (date == null)   
	          return "";   
	      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E");   
	      return simpleDateFormat.format(date);   
	  }   
	 
	  /**  
	   * @获取当前时间在这个月的天  
	   * @return String  
	   */  
	  public static String getDayInMonth() {   
	      Date today = new Date(System.currentTimeMillis());   
	      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d");   
	      return simpleDateFormat.format(today);   
	  }   
	 
	  /**  
	   * @根据日期，获取当前时间在这个月的天  
	   * @return String  
	   */  
	  public static String getDayInMonth(Date date) {   
	      if (date == null)   
	          return "";   
	      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d");   
	      return simpleDateFormat.format(date);   
	  }   
	 
	  /**  
	   * @根据在本年当中，获取月份格式"M"  
	   * @return String  
	   */  
	  public static String getMonthInYear() {   
	      Date today = new Date(System.currentTimeMillis());   
	      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M");   
	      return simpleDateFormat.format(today);   
	  }
	  
	  /**  
	   * @根据在本年当中，获取月份格式"M"  
	   * @return String  
	   */  
	  public static String getNextMonthInYear() {   
	      Date today = new Date(System.currentTimeMillis());   
	      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M");   
	      int currentMonth = Integer.parseInt(simpleDateFormat.format(today));
	      int nextMonth = currentMonth == 12 ? 1 : currentMonth + 1;
	      
	      return String.valueOf(nextMonth);   
	  }
	 
	  /**  
	   * @根据在本年当中，获取月份格式"MM"  
	   * @return String  
	   */  
	  public static String getMonthInYear2() {   
	      java.util.Date today = new Date(System.currentTimeMillis());   
	      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");   
	      return simpleDateFormat.format(today);   
	  }   
	 
	  /**  
	   * @根据日期，获取月份格式"MM"  
	   * @return String  
	   */  
	  public static String getMonthInYear2(String formatDate) {   
	      java.util.Date day = getDate(formatDate, "yyyy-MM-dd");   
	      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");   
	      return simpleDateFormat.format(day);   
	  }   
	 
	  /**  
	   * @获取当前时间的年，格式"yyyy"  
	   * @return String  
	   */  
	  public static String getYear() {   
	      java.util.Date today = new java.util.Date(System.currentTimeMillis());   
	      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");   
	      return simpleDateFormat.format(today);   
	  }   
	 
	  /**  
	   * @根据时间，获取年，格式"yyyy"  
	   * @return String  
	   */  
	  public static String getYear(String formatDate) {   
	      java.util.Date day = getDate(formatDate, "yyyy-MM-dd");   
	      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");   
	      return simpleDateFormat.format(day);   
	  }   
	 
	  /**  
	   * @根据日期以及日期格式，获取日期字符串表达  
	   * @return String  
	   */  
	  public static String getFormatDate(java.util.Date thisDate, String format) {   
	      if (thisDate == null)   
	          return "";   
	      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);   
	      return simpleDateFormat.format(thisDate);   
	  }   
	 
	  /**  
	   * @根据日期以及日期格式，获取日期  
	   * @return String  
	   */  
	  public static java.util.Date getDate(String date, String format) {   
	      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);   
	      try {   
	          return simpleDateFormat.parse(date);   
	      } catch (ParseException ex) {   
	          return null;   
	      }   
	  }   
	 
	  /**  
	   * @返回系统当前月的第一天所表示的日期  
	   * @param monthDate  
	   *            Date  
	   * @return Date  
	   */  
	  public static java.util.Date getFirstDateMonth() {   
	      try {   
	          String date_s = getYear() + "-" + getMonthInYear2() + "-01";   
	          return getDate(date_s, "yyyy-MM-dd");   
	      } catch (Exception er) {   
	          er.printStackTrace();   
	      }   
	      return null;   
	  }   
	 
	  /**  
	   * @返回系统当前月的第一天所表示的日期  
	   * @param monthDate  
	   *            Date  
	   * @return Date  
	   */  
	  public static java.util.Date getFirstDateMonth(String formatDate) {   
	      try {   
	          String date_s = getYear(formatDate) + "-"  
	                  + getMonthInYear2(formatDate) + "-01";   
	          return getDate(date_s, "yyyy-MM-dd");   
	      } catch (Exception er) {   
	          er.printStackTrace();   
	      }   
	      return null;   
	  }   
	 
	  /**  
	   * @返回系统当前月的第一天所表示的日期  
	   * @param monthDate  
	   *            Date  
	   * @return Date  
	   */  
	  public static String getFirstDateMonthToString() {   
	      try {   
	          return getYear() + "-" + getMonthInYear2() + "-01";   
	      } catch (Exception er) {   
	          er.printStackTrace();   
	      }   
	      return null;   
	  }   
	 
	  /**  
	   * @返回系统当前月的第一天所表示的日期  
	   * @param monthDate  
	   *            Date  
	   * @return Date  
	   */  
	  public static String getFirstDateMonthToString(String formatDate) {   
	      try {   
	          return getYear(formatDate) + "-" + getMonthInYear2(formatDate)   
	                  + "-01";   
	      } catch (Exception er) {   
	          er.printStackTrace();   
	      }   
	      return null;   
	  }   
	 
//	  /**  
//	   * @返回系统当前月的最后一天所表示的日期  
//	   * @return Date  
//	   */  
//	  public static java.util.Date getLastDateMonth() {   
//	      try {   
//	          int lastday = getDaySumInTheMonth();   
//	          String date_s;   
//	          if (lastday < 10)   
//	              date_s = getYear() + "-" + getMonthInYear2() + "-0" + lastday;   
//	          else  
//	              date_s = getYear() + "-" + getMonthInYear2() + "-" + lastday;   
//	          return getDate(date_s, "yyyy-MM-d");   
//	      } catch (Exception er) {   
//	          er.printStackTrace();   
//	      }   
//	      return null;   
//	  }
//	 
//	  /**  
//	   * @返回系统当前月的最后一天所表示的日期  
//	   * @return Date  
//	   */  
//	  public static String getLastDateMonthToString(String formatDate) {   
//	      try {   
//	          int lastday = getDaySumInTheMonth(formatDate);   
//	          if (lastday < 10)   
//	              return getYear(formatDate) + "-" + getMonthInYear2(formatDate)   
//	                      + "-0" + lastday;   
//	          else  
//	              return getYear(formatDate) + "-" + getMonthInYear2(formatDate)   
//	                      + "-" + lastday;   
//	      } catch (Exception er) {   
//	          er.printStackTrace();   
//	      }   
//	      return null;   
//	  }
//	 
//	  /**  
//	   * @根据时间，返回最后一天所表示的日期  
//	   * @param formatDate  
//	   *            String  
//	   * @return Date  
//	   */  
//	  public static java.util.Date getLastDateMonth(String formatDate) {   
//	      try {   
//	          int lastday = getDaySumInTheMonth(formatDate);   
//	          String month = getMonthInYear2(formatDate);   
//	          String year = getYear(formatDate);   
//	          String date_s;   
//	          if (lastday < 10)   
//	              date_s = year + "-" + month + "-0" + lastday;   
//	          else  
//	              date_s = year + "-" + month + "-" + lastday;   
//	 
//	          System.out.println(date_s);   
//	 
//	          return getDate(date_s, "yyyy-MM-d");   
//	      } catch (Exception er) {   
//	          er.printStackTrace();   
//	      }   
//	      return null;   
//	  }
	 
//	  /**  
//	   * @获得这个月的天数  
//	   * @return int  
//	   */  
//	  public static int getDaySumInTheMonth() {   
//	      String month = DayFormat.getMonthInYear();   
//	      if (month.equalsIgnoreCase("12"))   
//	          return 31;   
//	      int mon = Integer.parseInt(month);   
//	      java.util.Date date = DayFormat.getDate(DayFormat.getYear() + "-"  
//	              + (mon + 1) + "-" + "1", "yyyy-MM-d");   
//	      Calendar cal = Calendar.getInstance();   
//	      cal.setTime(date);   
//	      cal.add(Calendar.DATE, -1);   
//	      return Integer.parseInt(new SimpleDateFormat("dd").format(cal.getTime()));   
//	  }
//	 
//	  /**  
//	   * @获得所在日期月份的天数  
//	   * @return int  
//	   */  
//	  public static int getDaySumInTheMonth(java.util.Date date) {   
//	      String month = new SimpleDateFormat("MM").format(date);   
//	      if (month.equalsIgnoreCase("12"))   
//	          return 31;   
//	      int mon = Integer.parseInt(month);   
//	      java.util.Date date2 = DayFormat.getDate(new SimpleDateFormat("yyyy")   
//	              .format(date)   
//	              + "-" + (mon + 1) + "-" + "1", "yyyy-MM-d");   
//	      Calendar cal = Calendar.getInstance();   
//	      cal.setTime(date2);   
//	      cal.add(Calendar.DAY_OF_MONTH, -1);   
//	      return Integer.parseInt(new SimpleDateFormat("dd")   
//	              .format(cal.getTime()));   
//	  }
//	 
//	  /**  
//	   * @获得所在日期月份的天数  
//	   * @return int  
//	   */  
//	  public static int getDaySumInTheMonth(String formatDate) {   
//	      String month = DayFormat.getMonthInYear2(formatDate);   
//	      if (month.equalsIgnoreCase("12"))   
//	          return 31;   
//	      int mon = Integer.parseInt(month);   
//	      java.util.Date date = DayFormat.getDate(DayFormat.getYear(formatDate)   
//	              + "-" + (mon + 1) + "-" + "1", "yyyy-MM-d");   
//	      Calendar cal = Calendar.getInstance();   
//	      cal.setTime(date);   
//	      cal.add(Calendar.DAY_OF_MONTH, -1);   
//	      return Integer.parseInt(new SimpleDateFormat("dd")   
//	              .format(cal.getTime()));   
//	  }
	 
	  /**  
	   * @把日期格式转换成字符串格式,格式为'yyyy-MM-dd'  
	   * @param date  
	   *            Date  
	   * @return String  
	   */  
	  public static String getFormatDate(java.util.Date date) {   
	      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");   
	      return simpleDateFormat.format(date);   
	  }   
	 
	  public final static String FormatDay1 = "yyyy-MM-dd";   
	  public final static String FormatDay2 = "yyyy年MM月dd日";   
	  public final static String FormatDay3 = "yyyy年MM月dd日 HH:mm:ss";   
	  public final static String FormatDay4 = "yyyy-MM-dd HH:mm";   
	 
	  public static final String Format24Datetime1 = "yyyy-MM-dd HH:mm:ss";// 24   
	 
	  public static final String Format12Datetime1 = "yyyy-MM-dd hh:mm:ss";// 12   
	 
	  /**  
	   * 获取当前时间  
	   *   
	   * @return  
	   */  
	  public static String getToday() {   
	      return getToday(FormatDay1);   
	  }   
	 
	  public static java.sql.Timestamp getCurrentTimestamp() {   
	      return new Timestamp(System.currentTimeMillis());   
	  }   
	 
	  public static java.util.Date getCurrentUtilDate() {   
	      return new java.util.Date(System.currentTimeMillis());   
	  }   
	 
	  public static String getToday(String formatDay) {   
	      java.util.Date today = new java.util.Date();   
	      SimpleDateFormat df = new SimpleDateFormat(formatDay);   
	      return df.format(today).toString();   
	  }
	  
	  public static String getValue(Date today,String format){
		  SimpleDateFormat df = new SimpleDateFormat(format);
		  return df.format(today).toString();   
	  }
	 
	  public static String get24DateTime() {   
	      return get24DateTime(Format24Datetime1);   
	  }   
	 
	  public static String get24DateTime(String format24DateTime) {   
	      java.util.Date today = new java.util.Date();   
	      SimpleDateFormat df = new SimpleDateFormat(format24DateTime);   
	      return df.format(today).toString();   
	  }   
	 
	  public static String get12DateTime() {   
	      return get12DateTime(Format12Datetime1);   
	  }   
	 
	  public static String get12DateTime(String format12DateTime) {   
	      java.util.Date today = new java.util.Date();   
	      SimpleDateFormat df = new SimpleDateFormat(format12DateTime);   
	      return df.format(today).toString();   
	  }   
	 
	  public static String get24DateTime(Timestamp timestamp) {   
	      SimpleDateFormat df = new SimpleDateFormat(Format24Datetime1);   
	      return df.format(timestamp).toString();   
	  }   
	 
	  public static String get12DateTime(Timestamp timestamp) {   
	      SimpleDateFormat df = new SimpleDateFormat(Format12Datetime1);   
	      return df.format(timestamp).toString();   
	  }   
	 
	  // STRING到日期   
	  public static java.sql.Date stringToDate(String dateStr) {   
	      return java.sql.Date.valueOf(dateStr);   
	  }   
	 
	  public static java.sql.Timestamp stringToTimestamp(String timestampStr) {   
	      if (timestampStr == null || timestampStr.length() < 1)   
	          return null;   
	      return java.sql.Timestamp.valueOf(timestampStr);   
	  }   
	 
	  public static java.sql.Timestamp stringToTimestamp2(String dateStr) {   
	      if (dateStr == null || dateStr.length() < 1)   
	          return null;   
	      return java.sql.Timestamp.valueOf(dateStr + " 00:00:00.000000000");   
	  }   
	 
	  public static java.sql.Timestamp stringToTimestamp3(String dateStr) {   
	      if (dateStr == null || dateStr.length() < 1)   
	          return null;   
	      return java.sql.Timestamp.valueOf(dateStr + ":00.000000000");   
	  }   
	 
	  public static java.sql.Time stringToTime(String timeStr) {   
	      return java.sql.Time.valueOf(timeStr);   
	  }   
	 
	  // 日期到STRING   
	  public static String dateToString(java.sql.Date datee) {   
	      return datee.toString();   
	  }   
	 
	  public static String timestampToString(java.sql.Timestamp timestampe) {   
	      return timestampe.toString();   
	  }   
	 
	  public static String timestampToStringForFormat(Timestamp timestamp,   
	          String format) {   
	      if (timestamp == null)   
	          return "";   
	      SimpleDateFormat df = new SimpleDateFormat(format);   
	      return df.format(timestamp).toString();   
	  }   
	 
	  public static String getTimestampToDateTime15Len(   
	          java.sql.Timestamp timestampe) {   
	      if (timestampe == null || timestampe.toString().length() < 1)   
	          return "";   
	      return timestampe.toString().substring(0, 16);   
	  }   
	 
	  public static String timeToString(java.sql.Time timee) {   
	      return timee.toString();   
	  }   
	 
	  public static java.sql.Timestamp StringToTimestamp(String dateString)   
	          throws ParseException {   
	      java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(   
	              FormatDay1);   
	      formatter.setLenient(false);   
	      return new java.sql.Timestamp((formatter.parse(dateString).getTime()));   
	  }   
	 
	  public static java.sql.Timestamp StringToTimestampAll(String dateString)   
	          throws ParseException {   
	      return java.sql.Timestamp.valueOf(dateString);   
	  }   
	  //获得周日的日期    
	  public static String   getSunday(Date   date){      
	      Calendar c = Calendar.getInstance();      
	      c.setTime(date);      
	      c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);      
	      return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());      
	    }       
	    //获得周一的日期      
	    public static String getMonday(Date date){      
	      Calendar c = Calendar.getInstance();      
	      c.setTime(date);      
	      c.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);      
	      return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());      
	    }      
	    //获得周二的日期   
	    public static String getTuesday(Date date){      
	        Calendar c = Calendar.getInstance();      
	        c.setTime(date);      
	        c.set(Calendar.DAY_OF_WEEK,Calendar.TUESDAY);      
	        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());      
	      }    
	    //获得周三的日期    
	    public static String getWednesday(Date date){      
	        Calendar   c   =   Calendar.getInstance();      
	        c.setTime(date);      
	        c.set(Calendar.DAY_OF_WEEK,Calendar.WEDNESDAY);      
	        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());      
	      }    
	    //获得周四的日期   
	    public static String getThursday(Date date){      
	        Calendar   c   =   Calendar.getInstance();      
	        c.setTime(date);      
	        c.set(Calendar.DAY_OF_WEEK,Calendar.THURSDAY);      
	        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());      
	      }    
	    //获得周五的日期      
	    public static String getFriday(Date date){      
	      Calendar c = Calendar.getInstance();      
	      c.setTime(date);      
	      c.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY);            
	      return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());          
	    }      
	    //获得周六的日期    
	    public static String getSaturday(Date date){      
	        Calendar c = Calendar.getInstance();      
	        c.setTime(date);      
	        c.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);      
	        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());      
		}
}
