package webapi;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import service.CHECKINOUTService;
import service.MP1001Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.CustomerContextHolder;

import dto.MP1001Dto;
import dto.RemoteCheckInOutDto;
import entity.CHECKINOUT;
import entity.MP1001;

public class RemoteCheckInOutMng {
	private String empCode;
	private String date;
	private String dateStr;
	private String userId;
	private String siteCode;
	private String fromDate;
	private String endDate;
	
	private MP1001Service serviceMP1001;
	private CHECKINOUTService serviceCHECKINOUT;
	private EntityDtoConverter dtoConverter;
	
	
	private SimpleDateFormat sdf_0 = new SimpleDateFormat("yyyy-MM-dd dd:mm:ss");
	private SimpleDateFormat sdf_1 = new SimpleDateFormat("yyyy-MM-dd");
	
	
	
	
	
	public String lookupEmployeeInfo(){
		try{
			HttpServletResponse response = ServletActionContext.getResponse();
//			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			String str = null;
			// List<MP1001> lst =
			MP1001 employee = serviceMP1001.findById(empCode);
//			MP1001Dto dto = convertMP1001ToDto(employee);
			MP1001Dto dto = dtoConverter.convertToDto(employee);
//			ObjectMapper mapper = new ObjectMapper();
//			str = mapper.writeValueAsString(dto);
			
//			JSONObject jsonObj = new JSONObject();
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, dto);
			
			System.out.println(str);
			out.print(str);
			out.flush();
			out.close();
		} catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		return "none";
	}
	/* parameters : userId, date
	 * userId=1985
	 * date=1985-08-18
	 */
	public String lookupRecord(){
		List<CHECKINOUT> recordsList = null;
		try{
			CustomerContextHolder.setCustomerType("finger");
			
			recordsList = serviceCHECKINOUT.fetchDailyRecordsByUserId(sdf_1.parse(date), Integer.parseInt(userId));
			
			CustomerContextHolder.remove();
		} catch(Exception ex){
			System.out.println(ex.getMessage());
			return null;
		} finally {
			CustomerContextHolder.remove();
		}
		
		try{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			
			ObjectMapper mapper = new ObjectMapper();
			
//			OutputStream outStream = new ByteArrayOutputStream();
			
			List<RemoteCheckInOutDto> lst = new ArrayList<RemoteCheckInOutDto>();
			
			for(CHECKINOUT rd : recordsList){
				RemoteCheckInOutDto dto = new RemoteCheckInOutDto();
				
				dto = dtoConverter.convertToDto(rd);
				
				lst.add(dto);
			}
			
			mapper.writeValue(out, lst);
			
			out.flush();
			out.close();
		} catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		return "none";
	}
	
	public String validlookupRecord(){
		if(null == date || null == userId){
			return "no records";
		}
		return "error";
	}
	
	
	/*
	 * parameters:
	 * fromDate : eg: 2014-08-19 
	 * endDate : eg: 2014-08-20
	 * userId : eg: 1985
	 */
	public String lookupRecordForDays(){
		List<CHECKINOUT> recordsList = null;
		try{
			CustomerContextHolder.setCustomerType("finger");
			
			recordsList = serviceCHECKINOUT.fetchDailyRecordsByUserIdForDays(sdf_1.parse(fromDate), sdf_1.parse(endDate), Integer.parseInt(userId));
			
			CustomerContextHolder.remove();
		} catch(Exception ex){
			System.out.println(ex.getMessage());
			return null;
		} finally {
			CustomerContextHolder.remove();
		}
		
		try{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			
			ObjectMapper mapper = new ObjectMapper();
			
//			OutputStream outStream = new ByteArrayOutputStream();
			
			List<RemoteCheckInOutDto> lst = new ArrayList<RemoteCheckInOutDto>();
			
			for(CHECKINOUT rd : recordsList){
				RemoteCheckInOutDto dto = new RemoteCheckInOutDto();
				
				dto = dtoConverter.convertToDto(rd);
				
				lst.add(dto);
			}
			
			mapper.writeValue(out, lst);
			
			out.flush();
			out.close();
		} catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		return "none";
	}
	
	//--------------
	
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public MP1001Service getServiceMP1001() {
		return serviceMP1001;
	}
	public void setServiceMP1001(MP1001Service serviceMP1001) {
		this.serviceMP1001 = serviceMP1001;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public CHECKINOUTService getServiceCHECKINOUT() {
		return serviceCHECKINOUT;
	}
	public void setServiceCHECKINOUT(CHECKINOUTService serviceCHECKINOUT) {
		this.serviceCHECKINOUT = serviceCHECKINOUT;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public EntityDtoConverter getDtoConverter() {
		return dtoConverter;
	}
	public void setDtoConverter(EntityDtoConverter dtoConverter) {
		this.dtoConverter = dtoConverter;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}