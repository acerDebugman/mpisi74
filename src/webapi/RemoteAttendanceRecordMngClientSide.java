package webapi;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import service.MP0011Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Constant;

import dto.RemoteCheckInOutDto;
import entity.CHECKINOUT;
import entity.FingerSiteUserIdInfo;
import entity.MP0011;


public class RemoteAttendanceRecordMngClientSide {
	private EntityDtoConverter converter;
	private MP0011Service serviceMP0011;
	
	//this class for java batch scheduling
	private final static String Site_A_lookupUrl = "http://192.168.50.11:8080/mpisi74/lookupEmployeeById?empCode=M0432";
	private final static String Site_B_lookupUrl = "http://192.168.50.11:8080/mpisi74/lookupRecord?userId=1985&date=2014-08-18";
	private final static String Site_C_lookupUrl = "http://192.168.50.11:8080/mpisi74/lookupRecord?userId=1985&date=";
	
	SimpleDateFormat sdf_0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf_1 = new SimpleDateFormat("yyyy-MM-dd");
	
	public List<CHECKINOUT> findByFingerSiteUserIdInfo(FingerSiteUserIdInfo item, Date fromDate, Date endDate){
		List<CHECKINOUT> list = null;
		
		try{
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			
			URL url = new URL(Site_C_lookupUrl + sdf_1.format(new Date()));
			
			List<RemoteCheckInOutDto> rdList = mapper.readValue(url, new TypeReference<List<RemoteCheckInOutDto>>(){});
			
			List<CHECKINOUT> inoutList = new ArrayList<CHECKINOUT>();
			
			if(rdList.size() == 0){
				System.out.println("no records found");
			}
			else{
				for(RemoteCheckInOutDto dto : rdList){
//					System.out.println(dto.getUSERID() + "|" + dto.getCHECKTIME() + "|" + dto.getSENSORID());

					
					MP0011 tmp = new MP0011();
					tmp.setMP0011_SEQ(Constant.generateSeq());
					tmp.setMP0011_SYS_CODE("joeTest");
					tmp.setMP0011_LOGIN_TIME(sdf_0.format(new Date()));
					tmp.setMP0011_MEMO("remoteRd : " + dto.getUSERID() + "|" + dto.getCHECKTIME() + "|" + dto.getSENSORID());
					
					serviceMP0011.save(tmp);
					
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


	public MP0011Service getServiceMP0011() {
		return serviceMP0011;
	}


	public void setServiceMP0011(MP0011Service serviceMP0011) {
		this.serviceMP0011 = serviceMP0011;
	}
	
}
