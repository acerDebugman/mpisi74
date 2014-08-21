package webapi;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.RemoteCheckInOutDto;
import entity.CHECKINOUT;
import entity.FingerSiteUserIdInfo;


public class RemoteAttendanceRecordMngClientSide {
	private EntityDtoConverter converter;
	//this class for java batch scheduling
	private final static String Site_A_lookupUrl = "http://localhost:8080/mpisi74/lookupEmployeeById?empCode=M0432";
	private final static String Site_B_lookupUrl = "http://localhost:8080/mpisi74/lookupRecord?userId=1985&date=2014-08-18";
	private final static String Site_C_lookupUrl = "";
	
	
	public List<CHECKINOUT> findByFingerSiteUserIdInfo(FingerSiteUserIdInfo item, Date fromDate, Date endDate){
		List<CHECKINOUT> list = null;
		
		try{
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			
			URL url = new URL(Site_B_lookupUrl);
			
			List<RemoteCheckInOutDto> rdList = mapper.readValue(url, new TypeReference<List<RemoteCheckInOutDto>>(){});
			
			List<CHECKINOUT> inoutList = new ArrayList<CHECKINOUT>();
			
			if(rdList.size() == 0){
				System.out.println("no records found");
			}
			else{
				for(RemoteCheckInOutDto dto : rdList){
					System.out.println(dto.getUSERID() + "|" + dto.getCHECKTIME() + "|" + dto.getSENSORID());
					
					inoutList.add(converter.convertToEntity(dto));
				}
			}
			
		} catch (Exception ex){
			System.out.println(ex.getMessage());
		}

		
		return list;
	}


	public EntityDtoConverter getConverter() {
		return converter;
	}


	public void setConverter(EntityDtoConverter converter) {
		this.converter = converter;
	}

	
}
