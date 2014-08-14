package schedule;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class ExecuteJobsTest {

	
	private executeJobs jobs;
	
	@Test
	public void testExecuteJob18() {
//		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//		executeJobs jobs = (executeJobs)ctx.getBean("executeJobs");
		
		jobs.executeJob18();
		
//		fail("Not yet implemented");
	}
	
	@Test
	public void testExecuteFetchRecords(){
		try{
			jobs.getServiceExecuteJob().fetchAttendanceRecords();
		} catch (Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	@BeforeTransaction
	public void beforeTransaction(){
		System.out.println("in class ExecuteJobsTest beforeTransaction");
	}
	
	@AfterTransaction
	public void afterTransaction(){
		System.out.println("in class ExecuteJobsTest afterTransaction");
	}

	public executeJobs getJobs() {
		return jobs;
	}

	@Resource(name="executeJobs")
	public void setJobs(executeJobs jobs) {
		this.jobs = jobs;
	}
	
//	@Test
//	public void testExecuteTest() {
//		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//		executeJobs jobs = (executeJobs)ctx.getBean("executeJobs");
//		jobs.executeJob18();
//		
//		fail("Not yet implemented");
//	}

	
	
}
