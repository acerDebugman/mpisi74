package schedule;

import static org.junit.Assert.fail;

import java.sql.Connection;

import org.junit.Test;

import com.joe.schedule.CommonJobMethod;

public class CommonJobMethodTest {

	@Test
	public void testGetLeaveHours() throws Exception {
		Connection conn = CommonJobMethod.getDBConnection();
		Double dVal = CommonJobMethod.getLeaveHours("M0432", "2014-04-17", conn);
		System.out.print(dVal);
		
	//	fail("Not yet implemented");
	}

	@Test
	public void testGetExactLeaveHours() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAnnualLeaveHours() {
		fail("Not yet implemented");
	}

}
