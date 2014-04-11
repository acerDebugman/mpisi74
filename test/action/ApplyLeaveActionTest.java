package action;



import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplyLeaveActionTest {

	@Test
	public void testAnalyseExcelTemplete() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		ApplyLeaveAction leaveAction = (ApplyLeaveAction)ctx.getBean("applyLeaveMngBean");
		
		//ApplyLeaveAction ala = new ApplyLeaveAction();
		
		
		
		leaveAction.analyseExcelTemplete();
		
		System.out.println("test system");
		
	//	fail("Not yet implemented");
	}
	
	@Test
	public void testHibernateTest(){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ApplyLeaveAction leaveAction = (ApplyLeaveAction)ctx.getBean("applyLeaveMngBean");
		
		//ApplyLeaveAction ala = new ApplyLeaveAction();
		
		
		String ret = leaveAction.hibernateTest();

		Assert.assertEquals("success", ret);
		//System.out.println("test system");
	}

	@Test
	public void testHibernateLoadTest(){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		ApplyLeaveAction leaveAction = (ApplyLeaveAction)ctx.getBean("applyLeaveMngBean");
		
		//ApplyLeaveAction ala = new ApplyLeaveAction();
		
		
		String ret = leaveAction.hibernateLoadTest();

		Assert.assertEquals("success", ret);
		//System.out.println("test system");
	}

}
