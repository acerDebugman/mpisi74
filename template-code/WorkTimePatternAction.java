package action;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import service.DetailDayWorkTimeService;
import service.EachCircleDayService;
import service.EmpWorkTimePattern_RService;
import service.StandardWorkTimeService;
import service.WorkTimePatternService;

import com.opensymphony.xwork2.ActionSupport;
import common.Constant;
import common.PageBean;

import entity.DetailDayWorkTime;
import entity.EachCircleDay;
import entity.EmpWorkTimePattern_R;
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
	
	private int empWorkTimePattern_RId;
//	private EmpWorkTimePattern_R empWorkTimePattern_R;
	private EmpWorkTimePattern_R ewtp_R;
	private String empWTP_RStartDate;
	
	private List<EmpWorkTimePattern_R> empWorktimePatternList;
	private String employeeCode;
//	private Map<Int, WorkTimePattern> workTimePatternMap;
	
	private int currentPageNum;
	private String PER_PAGE_NUM = "13";
	private PageBean pageBean; // total
	
	private SimpleDateFormat sdf_0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat sdf_1 = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sdf_2 = new SimpleDateFormat("HH:mm:ss");
	
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
//			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_0));
//			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_0));
			dt.setStartTime(Constant.officeStartWorkTime_0);
			dt.setDurationTime(300);
			dt.setName("Monday AM");
			dt.setCircleDay(cd); //Monday
			serviceDetailDayWorkTime.save(dt);
			dt = new DetailDayWorkTime();
//			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_1));
//			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_1));
			dt.setStartTime(Constant.officeStartWorkTime_1);
			dt.setDurationTime(150);
			dt.setName("Monday PM");
			dt.setCircleDay(cd); //Monday
			serviceDetailDayWorkTime.save(dt);
			
			//Tursday
			dt = new DetailDayWorkTime();
//			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_0));
//			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_0));
			dt.setStartTime(Constant.officeStartWorkTime_0);
			dt.setDurationTime(300);
			dt.setName("Tursday AM");
			dt.setCircleDay(cd1); //Tursday
			serviceDetailDayWorkTime.save(dt);
			dt = new DetailDayWorkTime();
//			dt.setFromTime(sdf.parse(Constant.officeStartWorkTime_1));
//			dt.setToTime(sdf.parse(Constant.officeEndWorkTime_1));
			dt.setStartTime(Constant.officeStartWorkTime_1);
			dt.setDurationTime(150);
			dt.setName("Tursday PM");
			dt.setCircleDay(cd1); //Tursday
			serviceDetailDayWorkTime.save(dt);

			//Wensday
			dt = new DetailDayWorkTime();
			dt.setStartTime(Constant.officeStartWorkTime_0);
			dt.setDurationTime(300);
			dt.setName("Wensday AM");
			dt.setCircleDay(cd2); //Wensday
			serviceDetailDayWorkTime.save(dt);
			dt = new DetailDayWorkTime();
			dt.setStartTime(Constant.officeStartWorkTime_1);
			dt.setDurationTime(150);
			dt.setName("Wensday PM");
			dt.setCircleDay(cd2); //Wensday
			serviceDetailDayWorkTime.save(dt);
			
			//Thursday
			dt = new DetailDayWorkTime();
			dt.setStartTime(Constant.officeStartWorkTime_0);
			dt.setDurationTime(300);
			dt.setName("Thursday AM");
			dt.setCircleDay(cd3); //Thursday
			serviceDetailDayWorkTime.save(dt);
			dt = new DetailDayWorkTime();
			dt.setStartTime(Constant.officeStartWorkTime_1);
			dt.setDurationTime(150);
			dt.setName("Thursday PM");
			dt.setCircleDay(cd3); //Thursday
			serviceDetailDayWorkTime.save(dt);
			

			//Friday
			dt = new DetailDayWorkTime();
			dt.setStartTime(Constant.officeStartWorkTime_0);
			dt.setDurationTime(300);
			dt.setName("Friday AM");
			dt.setCircleDay(cd4); //Friday
			serviceDetailDayWorkTime.save(dt);
			dt = new DetailDayWorkTime();
			dt.setStartTime(Constant.officeStartWorkTime_1);
			dt.setDurationTime(150);
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
			dt.setStartTime(Constant.cleanLadyStartWorkTime_0);
			dt.setDurationTime(330);
			dt.setName("Monday AM");
			dt.setCircleDay(cd); //Monday
			serviceDetailDayWorkTime.save(dt);
			dt = new DetailDayWorkTime();
			dt.setStartTime(Constant.cleanLadyStartWorkTime_1);
			dt.setDurationTime(120);
			dt.setName("Monday PM");
			dt.setCircleDay(cd); //Monday
			serviceDetailDayWorkTime.save(dt);
			
			//Tursday
			dt = new DetailDayWorkTime();
			dt.setStartTime(Constant.cleanLadyStartWorkTime_0);
			dt.setDurationTime(330);
			dt.setName("Tursday AM");
			dt.setCircleDay(cd1); //Tursday
			serviceDetailDayWorkTime.save(dt);
			dt = new DetailDayWorkTime();
			dt.setStartTime(Constant.cleanLadyStartWorkTime_1);
			dt.setDurationTime(120);
			dt.setName("Tursday PM");
			dt.setCircleDay(cd1); //Tursday
			serviceDetailDayWorkTime.save(dt);

			//Wensday
			dt = new DetailDayWorkTime();
			dt.setStartTime(Constant.cleanLadyStartWorkTime_0);
			dt.setDurationTime(330);
			dt.setName("Wensday AM");
			dt.setCircleDay(cd2); //Wensday
			serviceDetailDayWorkTime.save(dt);
			dt = new DetailDayWorkTime();
			dt.setStartTime(Constant.cleanLadyStartWorkTime_1);
			dt.setDurationTime(120);
			dt.setName("Wensday PM");
			dt.setCircleDay(cd2); //Wensday
			serviceDetailDayWorkTime.save(dt);
			
			//Thursday
			dt = new DetailDayWorkTime();
			dt.setStartTime(Constant.cleanLadyStartWorkTime_0);
			dt.setDurationTime(330);
			dt.setName("Thursday AM");
			dt.setCircleDay(cd3); //Thursday
			serviceDetailDayWorkTime.save(dt);
			dt = new DetailDayWorkTime();
			dt.setStartTime(Constant.cleanLadyStartWorkTime_1);
			dt.setDurationTime(120);
			dt.setName("Thursday PM");
			dt.setCircleDay(cd3); //Thursday
			serviceDetailDayWorkTime.save(dt);
			

			//Friday
			dt = new DetailDayWorkTime();
			dt.setStartTime(Constant.cleanLadyStartWorkTime_0);
			dt.setDurationTime(330);
			dt.setName("Friday AM");
			dt.setCircleDay(cd4); //Friday
			serviceDetailDayWorkTime.save(dt);
			dt = new DetailDayWorkTime();
			dt.setStartTime(Constant.cleanLadyStartWorkTime_1);
			dt.setDurationTime(120);
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

	public String initializeEmployeeWorkTimePattern(){
		try{
//			empWorktimePatternList = new ArrayList<EmpWorkTimePattern_R>();
			
//			empWorktimePatternList = serviceEmpWTP.findAll();
//			for(int i = 10, size = empWorktimePatternList.size(); i < size - 10; i++){
//				empWorktimePatternList.remove(10);
//			}
//			
//			workTimePatternList = serviceWorkTimePattern.findAll();
			
			//initialize part
			workTimePatternList = serviceWorkTimePattern.findAll();
			
			
			//find result
			Map<String, String> map = new HashMap<String, String>();
			if(employeeCode != null && !employeeCode.equalsIgnoreCase("")){
				map.put("empCode", employeeCode);
			}
			if(workTimePatternId != 0 && workTimePatternId != -1){
				map.put("workTimePatternId", String.valueOf(workTimePatternId));
			}
			
			map.put("PAGE_NUM", "" + currentPageNum);
			map.put("PAGE_COUNT", PER_PAGE_NUM);
			pageBean = serviceEmpWTP.queryForPage(map);
			//shiftworkScheduleList = serviceMP2010.findByColumnName(columanNameMap);
			empWorktimePatternList = pageBean.getList();
			
			
			return SUCCESS;
		} catch(Exception ex){
			System.out.println(ex.getMessage());
			return ERROR;
		}
	}

	public String employeeWorkTimePattern_R_Search(){
		try{
			//initialize part
			workTimePatternList = serviceWorkTimePattern.findAll();
			
			//find result
			Map<String, String> map = new HashMap<String, String>();
			if(employeeCode != null && !employeeCode.equalsIgnoreCase("")){
				map.put("empCode", employeeCode);
			}
			if(workTimePatternId != 0 && workTimePatternId != -1){
				map.put("workTimePatternId", String.valueOf(workTimePatternId));
			}
			
			map.put("PAGE_NUM", "" + currentPageNum);
			map.put("PAGE_COUNT", PER_PAGE_NUM);
			pageBean = serviceEmpWTP.queryForPage(map);
			//shiftworkScheduleList = serviceMP2010.findByColumnName(columanNameMap);
			empWorktimePatternList = pageBean.getList();
			
			return SUCCESS;
		} catch(Exception ex){
			System.out.println(ex.getMessage());
			return ERROR;	
		}
	}
	
	public String employeeWorkTimePattern_R_edit(){
		try{
			workTimePatternList = serviceWorkTimePattern.findAll();

			ewtp_R = serviceEmpWTP.findById(empWorkTimePattern_RId);
			workTimePattern = ewtp_R.getWorkTimePattern();
			
			//date
			empWTP_RStartDate = sdf_1.format(ewtp_R.getStartDate()); 
		
			return SUCCESS;
		} catch(Exception ex){
			System.out.println(ex.getMessage());
			return ERROR;	
		}
	}
	
	public String employeeWorkTimePattern_R_save(){
		try{
			
			workTimePattern = serviceWorkTimePattern.findById(workTimePattern.getId());
			
			EmpWorkTimePattern_R item = serviceEmpWTP.findById(ewtp_R.getId());
			item.setWorkTimePattern(workTimePattern);
			item.setStartDate(sdf_1.parse(empWTP_RStartDate));
			item.setInitialCircleDayIdx(ewtp_R.getInitialCircleDayIdx());
			serviceEmpWTP.update(item);
			
			//out put
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.append("<script type='text/javascript'>");
//			out.append("window.dialogArguments.document.getElementById('btnRefresh').click();"); //this works for window.showModalWindow();
			out.append("window.opener.document.getElementById('btnRefresh').click();");  //this works for window.open();
			out.append("window.close();");
			out.append("</script>");
	        out.flush();
	        out.close();
	        
			return NONE;
		} catch(Exception ex){
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
	public List<EmpWorkTimePattern_R> getEmpWorktimePatternList() {
		return empWorktimePatternList;
	}
	public void setEmpWorktimePatternList(
			List<EmpWorkTimePattern_R> empWorktimePatternList) {
		this.empWorktimePatternList = empWorktimePatternList;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public int getCurrentPageNum() {
		return currentPageNum;
	}
	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}
	public String getPER_PAGE_NUM() {
		return PER_PAGE_NUM;
	}
	public void setPER_PAGE_NUM(String pER_PAGE_NUM) {
		PER_PAGE_NUM = pER_PAGE_NUM;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public int getEmpWorkTimePattern_RId() {
		return empWorkTimePattern_RId;
	}
	public void setEmpWorkTimePattern_RId(int empWorkTimePattern_RId) {
		this.empWorkTimePattern_RId = empWorkTimePattern_RId;
	}
	public EmpWorkTimePattern_R getEwtp_R() {
		return ewtp_R;
	}
	public void setEwtp_R(EmpWorkTimePattern_R ewtp_R) {
		this.ewtp_R = ewtp_R;
	}
	public String getEmpWTP_RStartDate() {
		return empWTP_RStartDate;
	}
	public void setEmpWTP_RStartDate(String empWTP_RStartDate) {
		this.empWTP_RStartDate = empWTP_RStartDate;
	}
	
}