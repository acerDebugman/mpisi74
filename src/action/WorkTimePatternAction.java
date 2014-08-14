package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import service.DetailDayWorkTimeService;
import service.EachCircleDayService;
import service.EmpWorkTimePattern_RService;
import service.StandardWorkTimeService;
import service.WorkTimePatternService;

import com.opensymphony.xwork2.ActionSupport;
import common.Constant;

import entity.DetailDayWorkTime;
import entity.EachCircleDay;
import entity.WorkTimePattern;


public class WorkTimePatternAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private WorkTimePatternService serviceWorkTimePattern;
	private EachCircleDayService serviceEachCircleDay;
	private DetailDayWorkTimeService serviceDetailDayWorkTime;
	private EmpWorkTimePattern_RService serviceEmpWTP;
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
//				System.out.println(d.getDetailWorkTimeItems().iterator().next().getFromTime() + "|" + d.getDetailWorkTimeItems().iterator().next().getCircleDay().getId());
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
	
	//use to add default pattern, test part
	public String addDefaultPattern(){ 
		try{
			
			defaultHeaderOfficePattern();
			defaultCleanLeadyPattern();
			
			return SUCCESS;
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			return ERROR;
		}
	}
	
	public void defaultHeaderOfficePattern() throws ParseException {
			WorkTimePattern wtp = new WorkTimePattern();
			wtp.setName("Office Work Time");
			wtp.setDescription("For normal office work time");
			wtp.setCircleDays(7);
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
			cd.setName("Monday");
			cd.setWorkTimePattern(wtp);
			cd.setOverTimeRate(1.5);
			serviceEachCircleDay.save(cd);
			
			EachCircleDay cd1 = new EachCircleDay();
			cd1.setDaySeq(2);
			cd1.setName("Tursday");
			cd1.setWorkTimePattern(wtp);
			cd1.setOverTimeRate(1.5);
			serviceEachCircleDay.save(cd1);
			
			EachCircleDay cd2 = new EachCircleDay();
			cd2.setDaySeq(3);
			cd2.setName("Wensday");
			cd2.setWorkTimePattern(wtp);
			cd2.setOverTimeRate(1.5);
			serviceEachCircleDay.save(cd2);
			
			EachCircleDay cd3 = new EachCircleDay();
			cd3.setDaySeq(4);
			cd3.setName("Thursday");
			cd3.setWorkTimePattern(wtp);
			cd3.setOverTimeRate(1.5);
			serviceEachCircleDay.save(cd3);
			
			EachCircleDay cd4 = new EachCircleDay();
			cd4.setDaySeq(5);
			cd4.setName("Friday");
			cd4.setWorkTimePattern(wtp);
			cd4.setOverTimeRate(1.5);
			serviceEachCircleDay.save(cd4);
			
			EachCircleDay cd5 = new EachCircleDay();
			cd5.setDaySeq(6);
			cd5.setName("Saturday");
			cd5.setWorkTimePattern(wtp);
			cd5.setOverTimeRate(1.5);
			serviceEachCircleDay.save(cd5);
			
			EachCircleDay cd6 = new EachCircleDay();
			cd6.setDaySeq(7);
			cd6.setName("Sunday");
			cd6.setWorkTimePattern(wtp);
			cd6.setOverTimeRate(2.0);
			serviceEachCircleDay.save(cd6);

			//set detail work time
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); //format date
			SimpleDateFormat sdf_0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //format date

			//Monday
			DetailDayWorkTime dt = new DetailDayWorkTime();
			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_0));
			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_0));
			dt.setName("Monday AM");
			dt.setCircleDay(cd); //Monday
			serviceDetailDayWorkTime.save(dt);
			dt = new DetailDayWorkTime();
			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_1));
			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_1));
			dt.setName("Monday PM");
			dt.setCircleDay(cd); //Monday
			serviceDetailDayWorkTime.save(dt);
			
			//Tursday
			dt = new DetailDayWorkTime();
			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_0));
			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_0));
			dt.setName("Tursday AM");
			dt.setCircleDay(cd1); //Tursday
			serviceDetailDayWorkTime.save(dt);
			dt = new DetailDayWorkTime();
			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_1));
			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_1));
			dt.setName("Tursday PM");
			dt.setCircleDay(cd1); //Tursday
			serviceDetailDayWorkTime.save(dt);

			//Wensday
			dt = new DetailDayWorkTime();
			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_0));
			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_0));
			dt.setName("Wensday AM");
			dt.setCircleDay(cd2); //Wensday
			serviceDetailDayWorkTime.save(dt);
			dt = new DetailDayWorkTime();
			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_1));
			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_1));
			dt.setName("Wensday PM");
			dt.setCircleDay(cd2); //Wensday
			serviceDetailDayWorkTime.save(dt);
			
			//Thursday
			dt = new DetailDayWorkTime();
			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_0));
			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_0));
			dt.setName("Thursday AM");
			dt.setCircleDay(cd3); //Thursday
			serviceDetailDayWorkTime.save(dt);
			dt = new DetailDayWorkTime();
			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_1));
			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_1));
			dt.setName("Thursday PM");
			dt.setCircleDay(cd3); //Thursday
			serviceDetailDayWorkTime.save(dt);
			

			//Friday
			dt = new DetailDayWorkTime();
			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_0));
			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_0));
			dt.setName("Friday AM");
			dt.setCircleDay(cd4); //Friday
			serviceDetailDayWorkTime.save(dt);
			dt = new DetailDayWorkTime();
			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_1));
			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_1));
			dt.setName("Friday PM");
			dt.setCircleDay(cd4); //Friday
			serviceDetailDayWorkTime.save(dt);


			//Saturday and Sunday have no need add
			//Saturday
//			dt = new DetailDayWorkTime();
////			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_0));
////			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_0));
////			dt.setFromTime(sdf_0.parse(Constant.startEpoch));
////			dt.setToTime(sdf_0.parse(Constant.startEpoch));
//			dt.setFromTime(null);
//			dt.setToTime(null);
//			dt.setName("Saturday AM");
//			dt.setCircleDay(cd5); //Saturday
//			serviceDetailDayWorkTime.save(dt);
//			dt = new DetailDayWorkTime();
////			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_1));
////			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_1));
////			dt.setFromTime(sdf_0.parse(Constant.startEpoch));
////			dt.setToTime(sdf_0.parse(Constant.startEpoch));
//			dt.setFromTime(null);
//			dt.setToTime(null);
//			dt.setName("Saturday PM");
//			dt.setCircleDay(cd5); //Saturday
//			serviceDetailDayWorkTime.save(dt);
//
//			//Sunday
//			dt = new DetailDayWorkTime();
////			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_0));
////			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_0));
////			dt.setFromTime(sdf_0.parse(Constant.startEpoch));
////			dt.setToTime(sdf_0.parse(Constant.startEpoch));
//			dt.setFromTime(null);
//			dt.setToTime(null);
//			dt.setName("Sunday AM");
//			dt.setCircleDay(cd6); //Sunday
//			serviceDetailDayWorkTime.save(dt);
//			dt = new DetailDayWorkTime();
////			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_1));
////			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_1));
////			dt.setFromTime(sdf_0.parse(Constant.startEpoch));
////			dt.setToTime(sdf_0.parse(Constant.startEpoch));
//			dt.setFromTime(null);
//			dt.setToTime(null);
//			dt.setName("Sunday PM");
//			dt.setCircleDay(cd6); //Sunday
//			serviceDetailDayWorkTime.save(dt);

	}
	
	public void defaultCleanLeadyPattern() throws ParseException {
			WorkTimePattern wtp = new WorkTimePattern();
			wtp.setName("Clean Lady Work Time");
			wtp.setDescription("For HR clean lady work time");
			wtp.setCircleDays(7);
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
			cd.setName("Monday");
			cd.setWorkTimePattern(wtp);
			cd.setOverTimeRate(1.5);
			serviceEachCircleDay.save(cd);
			
			EachCircleDay cd1 = new EachCircleDay();
			cd1.setDaySeq(2);
			cd1.setName("Tursday");
			cd1.setWorkTimePattern(wtp);
			cd1.setOverTimeRate(1.5);
			serviceEachCircleDay.save(cd1);
			
			EachCircleDay cd2 = new EachCircleDay();
			cd2.setDaySeq(3);
			cd2.setName("Wensday");
			cd2.setWorkTimePattern(wtp);
			cd2.setOverTimeRate(1.5);
			serviceEachCircleDay.save(cd2);
			
			EachCircleDay cd3 = new EachCircleDay();
			cd3.setDaySeq(4);
			cd3.setName("Thursday");
			cd3.setWorkTimePattern(wtp);
			cd3.setOverTimeRate(1.5);
			serviceEachCircleDay.save(cd3);
			
			EachCircleDay cd4 = new EachCircleDay();
			cd4.setDaySeq(5);
			cd4.setName("Friday");
			cd4.setWorkTimePattern(wtp);
			cd4.setOverTimeRate(1.5);
			serviceEachCircleDay.save(cd4);
			
			EachCircleDay cd5 = new EachCircleDay();
			cd5.setDaySeq(6);
			cd5.setName("Saturday");
			cd5.setWorkTimePattern(wtp);
			cd5.setOverTimeRate(1.5);
			serviceEachCircleDay.save(cd5);
			
			EachCircleDay cd6 = new EachCircleDay();
			cd6.setDaySeq(7);
			cd6.setName("Sunday");
			cd6.setWorkTimePattern(wtp);
			cd6.setOverTimeRate(2.0);
			serviceEachCircleDay.save(cd6);

			//set detail work time
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); //format date
			SimpleDateFormat sdf_0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //format date

			//Monday
			DetailDayWorkTime dt = new DetailDayWorkTime();
			dt.setFromTime(sdf.parse(Constant.cleanLadyStartWorkTime_0));
			dt.setToTime(sdf.parse(Constant.cleanLadyEndWorkTime_0));
			dt.setName("Monday AM");
			dt.setCircleDay(cd); //Monday
			serviceDetailDayWorkTime.save(dt);
			dt = new DetailDayWorkTime();
			dt.setFromTime(sdf.parse(Constant.cleanLadyStartWorkTime_1));
			dt.setToTime(sdf.parse(Constant.cleanLadyEndWorkTime_1));
			dt.setName("Monday PM");
			dt.setCircleDay(cd); //Monday
			serviceDetailDayWorkTime.save(dt);
			
			//Tursday
			dt = new DetailDayWorkTime();
			dt.setFromTime(sdf.parse(Constant.cleanLadyStartWorkTime_0));
			dt.setToTime(sdf.parse(Constant.cleanLadyEndWorkTime_0));
			dt.setName("Tursday AM");
			dt.setCircleDay(cd1); //Tursday
			serviceDetailDayWorkTime.save(dt);
			dt = new DetailDayWorkTime();
			dt.setFromTime(sdf.parse(Constant.cleanLadyStartWorkTime_1));
			dt.setToTime(sdf.parse(Constant.cleanLadyEndWorkTime_1));
			dt.setName("Tursday PM");
			dt.setCircleDay(cd1); //Tursday
			serviceDetailDayWorkTime.save(dt);

			//Wensday
			dt = new DetailDayWorkTime();
			dt.setFromTime(sdf.parse(Constant.cleanLadyStartWorkTime_0));
			dt.setToTime(sdf.parse(Constant.cleanLadyEndWorkTime_0));
			dt.setName("Wensday AM");
			dt.setCircleDay(cd2); //Wensday
			serviceDetailDayWorkTime.save(dt);
			dt = new DetailDayWorkTime();
			dt.setFromTime(sdf.parse(Constant.cleanLadyStartWorkTime_1));
			dt.setToTime(sdf.parse(Constant.cleanLadyEndWorkTime_1));
			dt.setName("Wensday PM");
			dt.setCircleDay(cd2); //Wensday
			serviceDetailDayWorkTime.save(dt);
			
			//Thursday
			dt = new DetailDayWorkTime();
			dt.setFromTime(sdf.parse(Constant.cleanLadyStartWorkTime_0));
			dt.setToTime(sdf.parse(Constant.cleanLadyEndWorkTime_0));
			dt.setName("Thursday AM");
			dt.setCircleDay(cd3); //Thursday
			serviceDetailDayWorkTime.save(dt);
			dt = new DetailDayWorkTime();
			dt.setFromTime(sdf.parse(Constant.cleanLadyStartWorkTime_1));
			dt.setToTime(sdf.parse(Constant.cleanLadyEndWorkTime_1));
			dt.setName("Thursday PM");
			dt.setCircleDay(cd3); //Thursday
			serviceDetailDayWorkTime.save(dt);
			

			//Friday
			dt = new DetailDayWorkTime();
			dt.setFromTime(sdf.parse(Constant.cleanLadyStartWorkTime_0));
			dt.setToTime(sdf.parse(Constant.cleanLadyEndWorkTime_0));
			dt.setName("Friday AM");
			dt.setCircleDay(cd4); //Friday
			serviceDetailDayWorkTime.save(dt);
			dt = new DetailDayWorkTime();
			dt.setFromTime(sdf.parse(Constant.cleanLadyStartWorkTime_1));
			dt.setToTime(sdf.parse(Constant.cleanLadyEndWorkTime_1));
			dt.setName("Friday PM");
			dt.setCircleDay(cd4); //Friday
			serviceDetailDayWorkTime.save(dt);


			//Saturday and Sunday have no need add
			//Saturday
//			dt = new DetailDayWorkTime();
////			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_0));
////			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_0));
////			dt.setFromTime(sdf_0.parse(Constant.startEpoch));
////			dt.setToTime(sdf_0.parse(Constant.startEpoch));
//			dt.setFromTime(null);
//			dt.setToTime(null);
//			dt.setName("Saturday AM");
//			dt.setCircleDay(cd5); //Saturday
//			serviceDetailDayWorkTime.save(dt);
//			dt = new DetailDayWorkTime();
////			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_1));
////			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_1));
////			dt.setFromTime(sdf_0.parse(Constant.startEpoch));
////			dt.setToTime(sdf_0.parse(Constant.startEpoch));
//			dt.setFromTime(null);
//			dt.setToTime(null);
//			dt.setName("Saturday PM");
//			dt.setCircleDay(cd5); //Saturday
//			serviceDetailDayWorkTime.save(dt);
//
//			//Sunday
//			dt = new DetailDayWorkTime();
////			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_0));
////			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_0));
////			dt.setFromTime(sdf_0.parse(Constant.startEpoch));
////			dt.setToTime(sdf_0.parse(Constant.startEpoch));
//			dt.setFromTime(null);
//			dt.setToTime(null);
//			dt.setName("Sunday AM");
//			dt.setCircleDay(cd6); //Sunday
//			serviceDetailDayWorkTime.save(dt);
//			dt = new DetailDayWorkTime();
////			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_1));
////			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_1));
////			dt.setFromTime(sdf_0.parse(Constant.startEpoch));
////			dt.setToTime(sdf_0.parse(Constant.startEpoch));
//			dt.setFromTime(null);
//			dt.setToTime(null);
//			dt.setName("Sunday PM");
//			dt.setCircleDay(cd6); //Sunday
//			serviceDetailDayWorkTime.save(dt);
		
	}
	
	public String fetchWorkTimePatternCircleDays(){
		try{
			workTimePattern = serviceWorkTimePattern.findById(workTimePatternId);
			
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

	public EmpWorkTimePattern_RService getServiceEmpWTP() {
		return serviceEmpWTP;
	}

	public void setServiceEmpWTP(EmpWorkTimePattern_RService serviceEmpWTP) {
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