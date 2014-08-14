package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import dao.IMP2003DAO;
import dto.AbnormalReptRecdDto;
import dto.LateEarlyDto;
import entity.CHECKINOUT;
import entity.MP1001;
import entity.MP2003;

public class MP2003Service implements IMP2003Service {

	private IMP2003DAO dao;

	public void delete(MP2003 mp2003) {
		dao.delete(mp2003);
	}

	public List<MP2003> findAll() {
		return dao.findAll();
	}

	public MP2003 findById(String employeeNum) {
		return dao.findById(employeeNum);
	}

	public void save(MP2003 mp2003) {
		dao.save(mp2003);
	}

	public void update(MP2003 mp2003) {
		dao.update(mp2003);
	}
	
	public void updateStatus(MP2003 mp2003){
		dao.updateStatus(mp2003);
	}
	
    public void loadDataToHR(Map<String, CHECKINOUT> inoutMap, String condition){
    	dao.loadDataToHR(inoutMap, condition);
    }
    
	public void updateTimes(String empNum, String dateTime){
		dao.updateTimes(empNum, dateTime);
	}
	
	public List<MP2003> findByProperty(String name, String value) {
		return dao.findByProperty(name, value);
	}
	
	public List<MP2003> findByPropertyPage(Map<String, String> propertyMap){
		return dao.findByPropertyPage(propertyMap);
	}

	public List<MP2003> findByPropertys(MP2003 _data) {
		return dao.findByPropertys(_data);
	}
	
	public List<MP2003> findByPropertys2(MP2003 _data) {
		return dao.findByPropertys2(_data);
	}

	public List<MP2003> findByPropertysPage(MP1001 employeeData,int pageNum,int pageCount,String empNum, String fromDate, String FinishDate,String depId,String attendenceStatus) {
		return dao.findByPropertysPage(employeeData,pageNum,pageCount, empNum, fromDate, FinishDate, depId, attendenceStatus);
	}
	
	public int getRowCountByPropertys(MP1001 employeeData,String empNum, String fromDate, String FinishDate,String depId,String attendenceStatus){
		return dao.getRowCountByPropertys(employeeData, empNum, fromDate, FinishDate, depId, attendenceStatus);
	}
	
	public List<MP2003> getPdfData(String empNum, String fromDate, String FinishDate,String depId,String attendenceStatus){
		return dao.getPdfData(empNum, fromDate, FinishDate, depId, attendenceStatus);
	}

	public IMP2003DAO getDao() {
		return dao;
	}

	public void setDao(IMP2003DAO dao) {
		this.dao = dao;
	}

	public List<MP2003> getTotalUnusualRecords(Map<String, String> propMap){ //for abnormal/late/early records report
		return this.dao.getTotalUnusualRecords(propMap);
	}
	
	public List<AbnormalReptRecdDto> getAbnormalReptData(Map<String, String> propMap){
		List<AbnormalReptRecdDto> rs = new ArrayList<AbnormalReptRecdDto>();

		List<MP2003> list = dao.getAbnormalReptData(propMap);
		if(0 == list.size()){
			return rs;
		}
		//calculate days
		float day = 0;
		MP2003 currentMp23 = null;
		MP2003 preMp23 = null;
		for(int i = 0, j = list.size(); i < j; i++){
			currentMp23 = list.get(i);
			if(null == preMp23){
				preMp23 = currentMp23;
			}
			if(!currentMp23.getMP2003_EMPLOYEE_NUM().equalsIgnoreCase(preMp23.getMP2003_EMPLOYEE_NUM())){
				AbnormalReptRecdDto recd = new AbnormalReptRecdDto();
				recd.setEmployeeCode(preMp23.getMP2003_EMPLOYEE_NUM());
				recd.setEmployeeName(preMp23.getMP2003_EMPLOYEE_NAME());
				recd.setSurname(preMp23.getMP2003_EMPLOYEE_SURNAME());
				recd.setDepartmentName(preMp23.getMP2003_DEPARTMENT_NAME());
				recd.setDay(day);
				
				rs.add(recd);
				day = 0;
				preMp23 = currentMp23;
				i--; //need backforward one record
			}
			else{
				if(currentMp23.getMP2003_START_TIME().isEmpty()){
					day += 0.5;
				}
				if(currentMp23.getMP2003_FINISH_TIME().isEmpty()){
					day += 0.5;
				}
			}
		}		
		{//last record
			AbnormalReptRecdDto recd = new AbnormalReptRecdDto();
			recd.setEmployeeCode(preMp23.getMP2003_EMPLOYEE_NUM());
			recd.setEmployeeName(preMp23.getMP2003_EMPLOYEE_NAME());
			recd.setSurname(preMp23.getMP2003_EMPLOYEE_SURNAME());
			recd.setDepartmentName(preMp23.getMP2003_DEPARTMENT_NAME());
			recd.setDay(day);
			rs.add(recd);
		}
		return rs;
	}
	
	public List<LateEarlyDto> getLateEarlyReptData(Map<String, String> propMap) throws ParseException {
		List<LateEarlyDto> rs = new ArrayList<LateEarlyDto>();
		
		List<MP2003> mp23List = dao.getLateEarlyReptData(propMap);
		if(0 == mp23List.size()){
			return rs;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
    	Calendar calInOut = Calendar.getInstance();
    	
		for(MP2003 mp23 : mp23List){
			LateEarlyDto recordDto = new LateEarlyDto();
			Date in = sdf.parse(mp23.getMP2003_START_TIME());
	                		
    		cal.setTime(sdf.parse(mp23.getMP2003_DATETIME()));//this is start
    		cal.set(Calendar.HOUR_OF_DAY, 8);
    		cal.set(Calendar.MINUTE, 0);
    		cal.set(Calendar.SECOND, 0);
    		
    		calInOut.setTime(in);
    		//Date startTime = cal.getTime();
    		//if(in.after(startTime)){
    		if(cal.before(calInOut)){
    			Long s = cal.getTime().getTime();
    			Long i = in.getTime();
    			recordDto.setLateMins((int)((i - s)/(1000*60)));
    		}
	    	
    		Date out = sdf.parse(mp23.getMP2003_FINISH_TIME());
	         
    		calInOut.setTime(out);
    		cal.setTime(sdf.parse(mp23.getMP2003_DATETIME())); //this is end
    		cal.set(Calendar.HOUR_OF_DAY, 16);
    		cal.set(Calendar.MINUTE, 30);
    		cal.set(Calendar.SECOND, 0);
    		//Date startTime = cal.getTime();
    		//if(in.after(startTime)){
    		if(cal.after(calInOut)){
    			Long s = cal.getTime().getTime();
    			Long o = out.getTime();
    			recordDto.setEarlyMins((int)((s - o)/(1000*60)));
    		}
    		
    		int e = recordDto.getEarlyMins();
    		int l = recordDto.getLateMins();
    		
    		recordDto.setAllMins(l + e);
    		
    		recordDto.setEmployeeNum(mp23.getMP2003_EMPLOYEE_NUM());
    		recordDto.setPreferedName(mp23.getMP2003_EMPLOYEE_NAME());
    		recordDto.setSurname(mp23.getMP2003_EMPLOYEE_SURNAME());
    		recordDto.setDepartmentName(mp23.getMP2003_DEPARTMENT_NAME());
    		recordDto.setStatus(mp23.getMP2003_COMMENT());
    		recordDto.setDate(sdf.parse(mp23.getMP2003_DATETIME()));
    		recordDto.setClockInTime(sdf.parse(mp23.getMP2003_START_TIME()));
    		recordDto.setClockOutTime(sdf.parse(mp23.getMP2003_FINISH_TIME()));
    		rs.add(recordDto);
		}
		
		return rs;
	}
	
	public MP2003 findByDateAndEmp(Date date, MP1001 emp){
		return dao.findByDateAndEmp(date, emp);
	}
	
	public List<MP2003> pickUpAbnormalRecordsList(Date date){
		return dao.pickUpAbnormalRecordsList(date);
	}
}