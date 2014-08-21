package webapi;

import dto.MP1001Dto;
import dto.RemoteCheckInOutDto;
import entity.CHECKINOUT;
import entity.MP1001;

public class EntityDtoConverter {
	public MP1001Dto convertToDto(MP1001 emp){
		MP1001Dto dto = new MP1001Dto();
		dto.setMP1001_DEPARTMENT_ID(emp.getMP1001_DEPARTMENT_ID());
		dto.setMP1001_DEPARTMENT_NAME(emp.getMP1001_DEPARTMENT_NAME());
		dto.setMP1001_EMPLOYEE_NUM(emp.getMP1001_EMPLOYEE_NUM());
		dto.setMP1001_EMPLOYEE_ID(emp.getMP1001_EMPLOYEE_ID());
		dto.setMP1001_FIRSTNAME(emp.getMP1001_FIRSTNAME());
		dto.setMP1001_PREFERED_NAME(emp.getMP1001_PREFERED_NAME());
		dto.setMP1001_SURNAME(emp.getMP1001_SURNAME());
		return dto;
	}
	
	public RemoteCheckInOutDto convertToDto(CHECKINOUT rd){
		RemoteCheckInOutDto dto = new RemoteCheckInOutDto();
		
		dto.setUSERID(rd.getUSERID());
		dto.setCHECKTIME(rd.getCHECKTIME());
		dto.setSENSORID(rd.getSENSORID());
		
		return dto;
	}
	
	public MP1001 convertToEntity(MP1001Dto dto){
		MP1001 emp = new MP1001();
		
		return emp;
	}
	
	public CHECKINOUT convertToEntity(RemoteCheckInOutDto dto){
		CHECKINOUT rd = new CHECKINOUT();
		
		return rd;
	}
	
}
