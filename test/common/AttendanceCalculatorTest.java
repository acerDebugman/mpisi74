package common;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import service.IMP1001Service;
import service.IMP2001Service;
import service.IStandardWorkTimeService;
import entity.MP1001;
import entity.MP2001;
import entity.StandardWorkTime;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class AttendanceCalculatorTest {
	
	private AttendanceCalculator ac;
	private IMP1001Service serviceMP1001;
	private IMP2001Service serviceMP2001;
	private IStandardWorkTimeService serviceStandardWorkTime;

	@BeforeTransaction
	public void beforeTransaction(){
		System.out.println("before transaction-----------------------------------");
	}
	
	@AfterTransaction
	public void afterTransaction(){
		System.out.println("after transaction------------------------------------");
	}

//	@Test
//	public void testProceed() {
//		
//		try{
//			MP1001 emp = serviceMP1001.findById("M0432");
//			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-07-20 00:00:00");
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(date);
//			cal.set(Calendar.HOUR_OF_DAY, 0);
//			cal.set(Calendar.MINUTE, 0);
//			cal.set(Calendar.SECOND, 0);
//			for(int i = 0; i < 15 ; i++){
//				ac.proceed(cal.getTime(), emp);
//				cal.add(Calendar.DAY_OF_MONTH, 1);
//			}
////			attendanceCalculator.transportAllEmployeeWorkTime();
//		}
//		catch(Exception ex){
//			ex.printStackTrace();
//		}
//		
////		fail("Not yet implemented");
//	}
	
//	@Test
//	public void testProceed_leaveApplication() {
//		
//		try{
//			MP1001 emp = serviceMP1001.findById("M0432");
//			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-08-01 00:00:00");
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(date);
//			cal.set(Calendar.HOUR_OF_DAY, 0);
//			cal.set(Calendar.MINUTE, 0);
//			cal.set(Calendar.SECOND, 0);
//			for(int i = 0; i < 5 ; i++){
//				ac.proceed(cal.getTime(), emp);
//				cal.add(Calendar.DAY_OF_MONTH, 1);
//			}
////			attendanceCalculator.transportAllEmployeeWorkTime();
//		}
//		catch(Exception ex){
//			ex.printStackTrace();
//		}
//		
////		fail("Not yet implemented");
//	}
	
	@Rollback(true)
	@Test
	public void testProceed_twoLeaveApplications() {
		try{
			MP2001 mp21 = new MP2001();
			mp21.setMP2001_NUM("test_0");
			mp21.setMP2001_EMPLOYEE_NUM("M0432");
			mp21.setMP2001_FROM_DATETIME("2014-07-31 09:00:00");
			mp21.setMP2001_TO_DATETIME("2014-07-31 10:00:00");
			mp21.setMP2001_APPROVAL("3");
			serviceMP2001.save(mp21);
			
			mp21 = new MP2001();
			mp21.setMP2001_NUM("test_1");
			mp21.setMP2001_EMPLOYEE_NUM("M0432");
			mp21.setMP2001_FROM_DATETIME("2014-07-31 10:00:00");
			mp21.setMP2001_TO_DATETIME("2014-07-31 11:00:00");
			mp21.setMP2001_APPROVAL("3");
			serviceMP2001.save(mp21);
			
			MP1001 emp = serviceMP1001.findById("M0432");
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-07-31 00:00:00");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			for(int i = 0; i < 2 ; i++){
				ac.proceed(cal.getTime(), emp);
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
			
			StandardWorkTime s = serviceStandardWorkTime.fetchSpecificDayWorkTime(emp, date);
			MP2001 m_0 = serviceMP2001.findById("test_0");
			MP2001 m_1 = serviceMP2001.findById("test_1");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			assertThat(1, is(1));
			assertThat("2014-07-31 08:00", is(equalTo(sdf.format(s.getStdClockInTime()))));
			assertThat("2014-07-31 16:30", is(equalTo(sdf.format(s.getStdClockOutTime()))));
//			assertThat(m_0.getMP2001_TO_DATETIME().substring(0, 16), is(m_1.getMP2001_FROM_DATETIME().substring(0, 16)));
			assertThat("2014-07-31 10:00", is(equalTo(m_0.getMP2001_TO_DATETIME().substring(0, 16))));
			assertThat("2014-07-31 10:00", is(equalTo(m_1.getMP2001_FROM_DATETIME().substring(0, 16))));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Rollback(true)
	@Test
	public void testProceed_twoLeaves_0() {
		try{
			MP2001 mp21 = new MP2001();
			mp21.setMP2001_NUM("test_0");
			mp21.setMP2001_EMPLOYEE_NUM("M0432");
			mp21.setMP2001_FROM_DATETIME("2014-07-31 09:00:00");
			mp21.setMP2001_TO_DATETIME("2014-07-31 11:00:00");
			mp21.setMP2001_APPROVAL("3");
			serviceMP2001.save(mp21);
			
			mp21 = new MP2001();
			mp21.setMP2001_NUM("test_1");
			mp21.setMP2001_EMPLOYEE_NUM("M0432");
			mp21.setMP2001_FROM_DATETIME("2014-07-31 11:00:00");
			mp21.setMP2001_TO_DATETIME("2014-07-31 16:30:00");
			mp21.setMP2001_APPROVAL("3");
			serviceMP2001.save(mp21);
			
			MP1001 emp = serviceMP1001.findById("M0432");
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-07-31 00:00:00");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			for(int i = 0; i < 2 ; i++){
				ac.proceed(cal.getTime(), emp);
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
			
			StandardWorkTime s = serviceStandardWorkTime.fetchSpecificDayWorkTime(emp, date);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			assertThat(1, is(1));
			assertThat("2014-07-31 08:00", is(sdf.format(s.getStdClockInTime())));
			assertThat("2014-07-31 09:00", is(sdf.format(s.getStdClockOutTime())));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Rollback(true)
	@Test
	public void testProceed_twoLeaves_1() {
		try{
			MP2001 mp21 = new MP2001();
			mp21.setMP2001_NUM("test_10");
			mp21.setMP2001_EMPLOYEE_NUM("M0432");
			mp21.setMP2001_FROM_DATETIME("2014-07-31 09:00:00");
			mp21.setMP2001_TO_DATETIME("2014-07-31 11:00:00");
			mp21.setMP2001_APPROVAL("3");
			serviceMP2001.save(mp21);
			
			mp21 = new MP2001();
			mp21.setMP2001_NUM("test_11");
			mp21.setMP2001_EMPLOYEE_NUM("M0432");
			mp21.setMP2001_FROM_DATETIME("2014-07-31 11:00:00");
			mp21.setMP2001_TO_DATETIME("2014-07-31 14:00:00");
			mp21.setMP2001_APPROVAL("3");
			serviceMP2001.save(mp21);
			
			mp21 = new MP2001();
			mp21.setMP2001_NUM("test_12");
			mp21.setMP2001_EMPLOYEE_NUM("M0432");
			mp21.setMP2001_FROM_DATETIME("2014-07-31 14:00:00");
			mp21.setMP2001_TO_DATETIME("2014-07-31 16:30:00");
			mp21.setMP2001_APPROVAL("3");
			serviceMP2001.save(mp21);
			
			MP1001 emp = serviceMP1001.findById("M0432");
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-07-31 00:00:00");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			for(int i = 0; i < 2 ; i++){
				ac.proceed(cal.getTime(), emp);
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
			
			StandardWorkTime s = serviceStandardWorkTime.fetchSpecificDayWorkTime(emp, date);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			assertThat(1, is(1));
			assertThat("2014-07-31 08:00", is(sdf.format(s.getStdClockInTime())));
			assertThat("2014-07-31 09:00", is(sdf.format(s.getStdClockOutTime())));
			
			MP2001 m_0 = serviceMP2001.findById("test_10");
			MP2001 m_1 = serviceMP2001.findById("test_11");
			MP2001 m_2 = serviceMP2001.findById("test_12");
			
			assertThat("2014-07-31 11:00", is(equalTo(m_0.getMP2001_TO_DATETIME().substring(0, 16))));
			assertThat("2014-07-31 11:00", is(equalTo(m_1.getMP2001_FROM_DATETIME().substring(0, 16))));
			assertThat("2014-07-31 14:00", is(equalTo(m_2.getMP2001_FROM_DATETIME().substring(0, 16))));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	//cross day
	@Rollback(true)
	@Test
	public void testProceed_twoLeaves_2() {
		try{
			MP2001 mp21 = new MP2001();
			mp21.setMP2001_NUM("test_10");
			mp21.setMP2001_EMPLOYEE_NUM("M0432");
			mp21.setMP2001_FROM_DATETIME("2014-07-31 09:00:00");
			mp21.setMP2001_TO_DATETIME("2014-08-01 11:00:00");
			mp21.setMP2001_APPROVAL("3");
			serviceMP2001.save(mp21);
			
//			mp21 = new MP2001();
//			mp21.setMP2001_NUM("test_11");
//			mp21.setMP2001_EMPLOYEE_NUM("M0432");
//			mp21.setMP2001_FROM_DATETIME("2014-07-31 11:00:00");
//			mp21.setMP2001_TO_DATETIME("2014-07-31 14:00:00");
//			mp21.setMP2001_APPROVAL("3");
//			serviceMP2001.save(mp21);
			
			MP1001 emp = serviceMP1001.findById("M0432");
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-07-31 00:00:00");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			for(int i = 0; i < 2 ; i++){
				ac.proceed(cal.getTime(), emp);
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
			
			StandardWorkTime s = serviceStandardWorkTime.fetchSpecificDayWorkTime(emp, date);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			assertThat("Leave", is(equalTo(s.getComments())));
			assertThat("2014-07-31 08:00", is(equalTo(sdf.format(s.getStdClockInTime()))));
			assertThat("2014-07-31 09:00", is(equalTo(sdf.format(s.getStdClockOutTime()))));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	//normal cross day application
	@Rollback(true)
	@Test
	public void testProceed_twoLeaves_3() {
		try{
			MP2001 mp21 = new MP2001();
			mp21.setMP2001_NUM("test_10");
			mp21.setMP2001_EMPLOYEE_NUM("M0432");
			mp21.setMP2001_FROM_DATETIME("2014-07-31 08:00:00");
			mp21.setMP2001_TO_DATETIME("2014-08-01 16:30:00");
			mp21.setMP2001_APPROVAL("3");
			serviceMP2001.save(mp21);
			
			MP1001 emp = serviceMP1001.findById("M0432");
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-07-31 00:00:00");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			for(int i = 0; i < 2 ; i++){
				ac.proceed(cal.getTime(), emp);
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
			
			StandardWorkTime s = serviceStandardWorkTime.fetchSpecificDayWorkTime(emp, date);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			assertThat("Leave", is(equalTo(s.getComments())));
			assertThat(new Date(0), is(equalTo(s.getStdClockInTime())));
			assertThat(new Date(0), is(equalTo(s.getStdClockOutTime())));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	//normal one day application
//	@Rollback(true)
	@Test
	public void testProceed_twoLeaves_4() {
		try{
//			MP2001 mp21 = new MP2001();
//			mp21.setMP2001_NUM("test_10");
//			mp21.setMP2001_EMPLOYEE_NUM("M0432");
//			mp21.setMP2001_FROM_DATETIME("2014-07-31 08:00:00");
//			mp21.setMP2001_TO_DATETIME("2014-07-31 16:30:00");
//			mp21.setMP2001_APPROVAL("3");
//			serviceMP2001.save(mp21);
			
			MP1001 emp = serviceMP1001.findById("M0432");
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-08-01 00:00:00");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			for(int i = 0; i < 2 ; i++){
				ac.proceed(cal.getTime(), emp);
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
			
			StandardWorkTime s = serviceStandardWorkTime.fetchSpecificDayWorkTime(emp, date);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			assertThat("Leave", is(equalTo(s.getComments())));
			assertThat(new Date(0), is(equalTo(s.getStdClockInTime())));
			assertThat(new Date(0), is(equalTo(s.getStdClockOutTime())));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
//	@Rollback(true)
	@Test
	public void testCalculatePresenceStatus(){
		try{
			MP2001 mp21 = new MP2001();
			mp21.setMP2001_NUM("test_10");
			mp21.setMP2001_EMPLOYEE_NUM("M0432");
			mp21.setMP2001_FROM_DATETIME("2014-07-31 08:00:00");
			mp21.setMP2001_TO_DATETIME("2014-07-31 16:30:00");
			mp21.setMP2001_APPROVAL("3");
			serviceMP2001.save(mp21);
			
			MP1001 emp = serviceMP1001.findById("M0432");
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-07-31 00:00:00");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			for(int i = 0; i < 2 ; i++){
				ac.proceed(cal.getTime(), emp);
				ac.calculatePresenceStatus(cal.getTime(), emp);
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
			
			StandardWorkTime s = serviceStandardWorkTime.fetchSpecificDayWorkTime(emp, date);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			assertThat("Leave", is(equalTo(s.getComments())));
			assertThat(new Date(0), is(equalTo(s.getStdClockInTime())));
			assertThat(new Date(0), is(equalTo(s.getStdClockOutTime())));
			
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Test
	public void testProceedLeaveApplication() {
//		fail("Not yet implemented");
		
	}

	@Test
	public void testProceedSpecialDay() {
//		fail("Not yet implemented");
	}

	@Test
	public void testProceedPublicHoliday() {
//		fail("Not yet implemented");
	}

	@Test
	public void testTransportAllEmployeeWorkTime() {
//		fail("Not yet implemented");
	}

	@Test
	public void testDatetimeCompare() {
//		fail("Not yet implemented");
	}

	
	
//-----------------------------------------------------	
	public AttendanceCalculator getAc() {
		return ac;
	}

	@Resource(name="AttendanceCalcualtor")
	public void setAc(AttendanceCalculator ac) {
		this.ac = ac;
	}

	public IMP1001Service getServiceMP1001() {
		return serviceMP1001;
	}

	@Resource(name="MP1001Service")
	public void setServiceMP1001(IMP1001Service serviceMP1001) {
		this.serviceMP1001 = serviceMP1001;
	}

	public IMP2001Service getServiceMP2001() {
		return serviceMP2001;
	}

	@Resource(name="MP2001Service")
	public void setServiceMP2001(IMP2001Service serviceMP2001) {
		this.serviceMP2001 = serviceMP2001;
	}

	public IStandardWorkTimeService getServiceStandardWorkTime() {
		return serviceStandardWorkTime;
	}

	@Resource(name="StandardWorkTimeService")
	public void setServiceStandardWorkTime(
			IStandardWorkTimeService serviceStandardWorkTime) {
		this.serviceStandardWorkTime = serviceStandardWorkTime;
	}

	
}
