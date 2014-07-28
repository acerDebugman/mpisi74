package action;

import java.util.Date;
import java.util.List;

import service.DetailDayWorkTimeService;
import service.EachCircleDayService;
import service.EmpWorktimePattern_RService;
import service.StandardWorkTimeService;
import service.WorkTimePatternService;

import com.opensymphony.xwork2.ActionSupport;

import entity.DetailDayWorkTime;
import entity.EachCircleDay;
import entity.WorkTimePattern;


public class WorkTimePatternAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private WorkTimePatternService serviceWorkTimePattern;
	private EachCircleDayService serviceEachCircleDay;
	private DetailDayWorkTimeService serviceDetailDayWorkTime;
	private EmpWorktimePattern_RService serviceEmpWTP;
	private StandardWorkTimeService serviceStdWorkTime;
	
	private List<WorkTimePattern> workTimePatternList;

	private int workTimePatternId;
	private WorkTimePattern workTimePattern;
	
	
	public String tesSave(){
		
		return SUCCESS;
	}
/*private int circleDays;
        private String description;
        private String name;
        private boolean calPubHolidayFlag; //true calculate public holiday
        private boolean calSpecialDayFlag; //true calculate special day
        private boolean applyLeaveIgnorePublicHolidayFlag; //apply leave ignore public holiday
        private Double initialAnnualLeaveDays; //calcualte annual day from employee start day
        private Double addAnnualHoursPM;
        private Double initialSickLeaveDays;
        private Double addSickHoursPM;
        private Double dayWorkHours; //how many hours a day? use this parameter to convert day and hours
*/
	public String workTimePatternMngInit(){
		try{
			WorkTimePattern wtp = new WorkTimePattern();
			wtp.setName("joe zhang");
	//		wtp.set
	//		
			wtp.setDescription("zhang jojo");
			wtp.setCircleDays(3);
			wtp.setCalPubHolidayFlag(true);
			wtp.setCalSpecialDayFlag(true);
			wtp.setApplyLeaveIgnorePublicHolidayFlag(false);
			wtp.setInitialAnnualLeaveDays(0.0);
			wtp.setAddAnnualHoursPM(1.4);
			wtp.setInitialSickLeaveDays(36.0);
			wtp.setAddSickHoursPM(1.395);
			wtp.setDayWorkHours(7.5);
			serviceWorkTimePattern.save(wtp);
			
			EachCircleDay cd = new EachCircleDay();
			cd.setDaySeq(1);
			cd.setName("test");
			cd.setWorkTimePattern(wtp);
			serviceEachCircleDay.save(cd);
			
			EachCircleDay cd1 = new EachCircleDay();
			cd1.setDaySeq(2);
			cd1.setName("test 2");
			cd1.setWorkTimePattern(wtp);
			serviceEachCircleDay.save(cd1);
			
			EachCircleDay cd2 = new EachCircleDay();
			cd2.setDaySeq(3);
			cd2.setName("test 3");
			cd2.setWorkTimePattern(wtp);
			serviceEachCircleDay.save(cd2);
			
			DetailDayWorkTime dt = new DetailDayWorkTime();
			dt.setFromTime(new Date());
			dt.setToTime(new Date());
			dt.setDescription("Moring");
			dt.setCircleDay(cd);
			serviceDetailDayWorkTime.save(dt);
			
			DetailDayWorkTime dt1 = new DetailDayWorkTime();
			dt1.setFromTime(new Date());
			dt1.setToTime(new Date());
			dt1.setDescription("afternoon");
			dt1.setCircleDay(cd);
			serviceDetailDayWorkTime.save(dt1);
			
			return SUCCESS;
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			return ERROR;
		}
	}
	
	public String displayPatternDetail(){
		try{
			workTimePattern = serviceWorkTimePattern.findById(workTimePatternId);
			for(EachCircleDay d : workTimePattern.getAllCircleDays()){
				System.out.println(d.getDaySeq() + "|" + d.getName());
				System.out.println(d.getDetailWorkTimeItems().iterator().next().getFromTime() + "|" + d.getDetailWorkTimeItems().iterator().next().getCircleDay().getId());
			}
			
			return SUCCESS;
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			return ERROR;
		}
	}
	
	public String fetchWorkTimePatternList(){
		try{
			workTimePatternList = serviceWorkTimePattern.findAll();
			
			return SUCCESS;
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			return ERROR;
		}
	}
	
	public WorkTimePatternService getServiceWorkTimePattern() {
		return serviceWorkTimePattern;
	}

	public void setServiceWorkTimePattern(
			WorkTimePatternService serviceWorkTimePattern) {
		this.serviceWorkTimePattern = serviceWorkTimePattern;
	}

	public EachCircleDayService getServiceEachCircleDay() {
		return serviceEachCircleDay;
	}

	public void setServiceEachCircleDay(EachCircleDayService serviceEachCircleDay) {
		this.serviceEachCircleDay = serviceEachCircleDay;
	}

	public DetailDayWorkTimeService getServiceDetailDayWorkTime() {
		return serviceDetailDayWorkTime;
	}

	public void setServiceDetailDayWorkTime(
			DetailDayWorkTimeService serviceDetailDayWorkTime) {
		this.serviceDetailDayWorkTime = serviceDetailDayWorkTime;
	}

	public EmpWorktimePattern_RService getServiceEmpWTP() {
		return serviceEmpWTP;
	}

	public void setServiceEmpWTP(EmpWorktimePattern_RService serviceEmpWTP) {
		this.serviceEmpWTP = serviceEmpWTP;
	}

	public StandardWorkTimeService getServiceStdWorkTime() {
		return serviceStdWorkTime;
	}

	public void setServiceStdWorkTime(StandardWorkTimeService serviceStdWorkTime) {
		this.serviceStdWorkTime = serviceStdWorkTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	public void setWorkTimePatternList(List<WorkTimePattern> workTimePatternList) {
		this.workTimePatternList = workTimePatternList;
	}

	public WorkTimePattern getWorkTimePattern() {
		return workTimePattern;
	}

	public void setWorkTimePattern(WorkTimePattern workTimePattern) {
		this.workTimePattern = workTimePattern;
	}

	public List<WorkTimePattern> getWorkTimePatternList() {
		return workTimePatternList;
	}

	public int getWorkTimePatternId() {
		return workTimePatternId;
	}

	public void setWorkTimePatternId(int workTimePatternId) {
		this.workTimePatternId = workTimePatternId;
	}
	
}