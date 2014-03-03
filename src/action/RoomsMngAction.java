package action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import service.IJE0201Service;
import service.IJE0202Service;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import common.Constant;
import common.PageBean;
import common.UtilCommon;
import common.UtilDate;

import entity.JE0201;
import entity.JE0202;
import entity.MP1001;

public class RoomsMngAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(RoomsMngAction.class);
	
	private IJE0201Service serviceJE0201;
	private IJE0202Service serviceJE0202;
	
	private String currentPageNum;
	private String pageSize;
	
	private String roomName;
	private String floor;
	private String roomType;
	private String roomDes;
	private String strBookDes;
	private String strRoomCode;

	private String strWhichDay;
	private String strFromTime;
	private String strToTime;
	
	private String strSubscriberCode;
	private String strSubscriberName;
	
	private List<String> floorList;
	private List<String> roomTypeList;
	private List<JE0201> allRoomsList;
	private List<String> listTimeList;
	private List<JE0202> listBookedRoomRecords;
	
	private List<String> listAllRoomNames;
	private List<String> listAllRoomCodes;
	private Map<String, String> mapRoomCodeName;
	private Map<String, JE0201> mapRoomCodeObj;
	private List<String> listFromTime;
	private List<String> listToTime;
	
	private Map<String, String> mapProperties;
	private List<Map> listMap;

	public String roomsMngInit(){
		try{
			/*
			 * log part
			 */
			
			allRoomsList = serviceJE0201.findAll();
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public String roomSearch(){
		try{
			/*
			 * log part
			 * */

			System.out.println(roomName + "|" + floor);
			
			Map<String, String> columnMap = new HashMap<String, String>();
			columnMap.put("JE0201_ROOM_NAME", roomName);
			columnMap.put("JE0201_ROOM_FLOOR", floor);
			
			allRoomsList = serviceJE0201.findByColumnName(columnMap);
			
			return SUCCESS;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return "error";
		}
	}
	
	
	public String roomAddInit(){
		try{
			floorList = new ArrayList<String>();
			floorList.add("joe");
			floorList.add("shit");
			
			roomTypeList = new ArrayList<String>();
			roomTypeList.add("boardroom");
			roomTypeList.add("bedding room");
			
			
			
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public String roomAdd(){
		try{
			System.out.println(roomName + "|" + floor + "|" + roomType + "|" + roomDes);
			
			String seq = Constant.generateSeq();
			int number = UtilCommon.getTempSeq();
			seq = seq + Integer.toString(number);
			
			JE0201 room0201 = new JE0201();
			
			room0201.setJE0201_ROOM_CODE(seq);
			room0201.setJE0201_ROOM_NAME(roomName);
			room0201.setJE0201_ROOM_FLOOR(floor);
			room0201.setJE0201_ROOM_TYPE(roomType);
			room0201.setJE0201_ROOM_DES(roomDes);
			serviceJE0201.save(room0201);


			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			
			StringBuffer sb = new StringBuffer();
			sb.append("<script type='text/javascript'>");
			//sb.append("window.dialogArguments.document.getElementById('refreshData').click();");
			//sb.append("window.dialogArguments.document.getElementById('1').style.backgroundColor = '#666666';");
			sb.append("window.close();");
			sb.append("</script>");
			
			out.println(sb.toString());
	        out.flush();
	        out.close();
	        
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}

	public String bookBoardroomMngInit(){
		try{
			listTimeList = Constant.getHalfAnHourIntervalWorkTime();
			
			String order = " order by JE0202_FROM_DATETIME "; 
			listBookedRoomRecords = serviceJE0202.findByColumnName(null, order);
			
			
			/*mapProperties = new HashMap<String, String>();
			listMap.add(e);*/
			
			
			allRoomsList = serviceJE0201.findAll();
			mapRoomCodeObj = new HashMap<String, JE0201>();
			for(JE0201 je0201 : allRoomsList){
				mapRoomCodeObj.put(je0201.getJE0201_ROOM_CODE(), je0201);
			}
			
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}

	public String searchBookedRoom(){
		try{
			System.out.println(strWhichDay + "|" + strFromTime + "|" + strToTime + "|" + roomName + "|" + floor);
			
			System.out.println("all row counts: " + serviceJE0202.getAllRowsCount());
			
			Map<String, String> propMap = new HashMap<String, String>();
			propMap.put("pageSize", "10");
			propMap.put("currentPageNum", "1");
			propMap.put("JE0202_DATE", strWhichDay);
			propMap.put("JE0202_FROM_DATETIME", strFromTime);
			propMap.put("JE0202_END_DATETIME", strToTime);
			propMap.put("JE0202_ROOM_CODE", strRoomCode);
			propMap.put("JE0202_FLOOR", floor);
			PageBean pb = serviceJE0202.queryForPage(propMap);
			listBookedRoomRecords = pb.getList();
			
			
			//room
			allRoomsList = serviceJE0201.findAll();
			mapRoomCodeObj = new HashMap<String, JE0201>();
			for(JE0201 je0201 : allRoomsList){
				mapRoomCodeObj.put(je0201.getJE0201_ROOM_CODE(), je0201);
			}
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public String bookOneRoomInit(){
		try{
			allRoomsList = serviceJE0201.findAll();
			mapRoomCodeName = new HashMap<String, String>();
			JE0201 old0201 = null;
			for(JE0201 je0201 : allRoomsList){
				if(null != old0201){
					if(!old0201.getJE0201_ROOM_NAME().equalsIgnoreCase(je0201.getJE0201_ROOM_NAME())){
						mapRoomCodeName.put(je0201.getJE0201_ROOM_CODE(), je0201.getJE0201_ROOM_NAME());
					}
				}
				else{
					mapRoomCodeName.put(je0201.getJE0201_ROOM_CODE(), je0201.getJE0201_ROOM_NAME());
				}
				old0201 = je0201;
			}
			
			/*strSubscriberCode = "m0432";
			strSubscriberName = "joe_zhang";
			strWhichDay = "2014-01-01";*/
						
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
			
			MP1001 employeeData = (MP1001)session.get("EMPLOYEE_DATA");
			strSubscriberCode = employeeData.getMP1001_EMPLOYEE_NUM();
			strSubscriberName = employeeData.getMP1001_PREFERED_NAME();
			
			//listFromTime
			listTimeList = Constant.getHalfAnHourIntervalWorkTime();
			
			
			

			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public String saveOneBookedRoom(){
		try{
			System.out.println(strRoomCode + "|" + strSubscriberCode + "|" + strWhichDay + "|" + strFromTime + "|" + strToTime + "|" + strBookDes);
			
			JE0202 je0202 = new JE0202();
			
			String seq = Constant.generateSeq();
			int number = UtilCommon.getTempSeq();
			seq = seq + Integer.toString(number);
			
			je0202.setJE0202_SEQ(seq);
			je0202.setJE0202_DATE(strWhichDay);
			strFromTime = strWhichDay + " " + strFromTime;
			je0202.setJE0202_FROM_DATETIME(strFromTime);
			strToTime = strWhichDay + " " + strToTime;
			je0202.setJE0202_END_DATETIME(strToTime);
			je0202.setJE0202_CREATE_DATETIME(UtilDate.get24DateTime());
			je0202.setJE0202_ROOM_CODE(strRoomCode);
			je0202.setJE0202_DES(strBookDes);
			je0202.setJE0202_USER_NUM(strSubscriberCode);
			je0202.setJE0202_USER_TYPE("subscriber");
			
			/*judge if time overlapped*/
			//find which day it is
			Map<String, String> columnMap = new HashMap<String, String>();
			columnMap.put("JE0202_DATE", strWhichDay);
			listBookedRoomRecords = serviceJE0202.findByColumnName(columnMap);
			for(JE0202 bookedRoom : listBookedRoomRecords){
				String bookedStartTime = bookedRoom.getJE0202_FROM_DATETIME();
				bookedStartTime = bookedStartTime.substring(0, 16);//"2014-02-28 10:45" is 16 letters
				String bookedEndTime = bookedRoom.getJE0202_END_DATETIME();
				bookedEndTime = bookedEndTime.substring(0, 16);
				
				if(null!=bookedStartTime && null!=bookedEndTime &&
						((strFromTime.compareToIgnoreCase(bookedStartTime) >= 0 && strFromTime.compareToIgnoreCase(bookedEndTime) < 0) ||
						(strToTime.compareToIgnoreCase(bookedStartTime) > 0 && strToTime.compareToIgnoreCase(bookedEndTime) <= 0)) ||
						(strFromTime.compareToIgnoreCase(bookedStartTime) <= 0 && strToTime.compareToIgnoreCase(bookedEndTime) >=0)
						){
					HttpServletResponse resp = ServletActionContext.getResponse();
					resp.setCharacterEncoding("utf-8");
					PrintWriter out = resp.getWriter();
					StringBuffer sb = new StringBuffer();
					sb.append("<script type='text/javascript'>");
					sb.append("alert('Time overlapped!Please check if the room is occupied between that period!');");
					sb.append("window.close();");
					sb.append("</script>");
					out.print(sb.toString());
					out.flush();
					out.close();
					return NONE;
				}
			}

			serviceJE0202.save(je0202);
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			
			StringBuffer sb = new StringBuffer();
			sb.append("<script type='text/javascript'>");
			sb.append("window.close();");
			sb.append("</script>");
			
			out.println(sb.toString());
			out.flush();
			out.close();
			
			return NONE;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	

	public IJE0201Service getServiceJE0201() {
		return serviceJE0201;
	}


	public void setServiceJE0201(IJE0201Service serviceJE0201) {
		this.serviceJE0201 = serviceJE0201;
	}


	public IJE0202Service getServiceJE0202() {
		return serviceJE0202;
	}


	public void setServiceJE0202(IJE0202Service serviceJE0202) {
		this.serviceJE0202 = serviceJE0202;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public static Log getLog() {
		return log;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomDes() {
		return roomDes;
	}

	public void setRoomDes(String roomDes) {
		this.roomDes = roomDes;
	}

	public List<String> getFloorList() {
		return floorList;
	}

	public void setFloorList(List<String> floorList) {
		this.floorList = floorList;
	}

	public List<String> getRoomTypeList() {
		return roomTypeList;
	}

	public void setRoomTypeList(List<String> roomTypeList) {
		this.roomTypeList = roomTypeList;
	}

	public List<JE0201> getAllRoomsList() {
		return allRoomsList;
	}

	public void setAllRoomsList(List<JE0201> allRoomsList) {
		this.allRoomsList = allRoomsList;
	}

	public String getStrWhichDay() {
		return strWhichDay;
	}

	public void setStrWhichDay(String strWhichDay) {
		this.strWhichDay = strWhichDay;
	}

	public String getStrFromTime() {
		return strFromTime;
	}

	public void setStrFromTime(String strFromTime) {
		this.strFromTime = strFromTime;
	}

	public String getStrToTime() {
		return strToTime;
	}

	public void setStrToTime(String strToTime) {
		this.strToTime = strToTime;
	}

	public List<String> getListTimeList() {
		return listTimeList;
	}

	public void setListTimeList(List<String> listTimeList) {
		this.listTimeList = listTimeList;
	}

	public String getStrSubscriberCode() {
		return strSubscriberCode;
	}

	public void setStrSubscriberCode(String strSubscriberCode) {
		this.strSubscriberCode = strSubscriberCode;
	}

	public String getStrSubscriberName() {
		return strSubscriberName;
	}

	public void setStrSubscriberName(String strSubscriberName) {
		this.strSubscriberName = strSubscriberName;
	}

	public List<String> getListAllRoomNames() {
		return listAllRoomNames;
	}

	public void setListAllRoomNames(List<String> listAllRoomNames) {
		this.listAllRoomNames = listAllRoomNames;
	}

	public String getStrBookDes() {
		return strBookDes;
	}

	public void setStrBookDes(String strBookDes) {
		this.strBookDes = strBookDes;
	}

	public List<String> getListFromTime() {
		return listFromTime;
	}

	public void setListFromTime(List<String> listFromTime) {
		this.listFromTime = listFromTime;
	}

	public List<String> getListToTime() {
		return listToTime;
	}

	public void setListToTime(List<String> listToTime) {
		this.listToTime = listToTime;
	}

	public String getStrRoomCode() {
		return strRoomCode;
	}

	public void setStrRoomCode(String strRoomCode) {
		this.strRoomCode = strRoomCode;
	}

	public List<String> getListAllRoomCodes() {
		return listAllRoomCodes;
	}

	public void setListAllRoomCodes(List<String> listAllRoomCodes) {
		this.listAllRoomCodes = listAllRoomCodes;
	}

	public Map<String, String> getMapRoomCodeName() {
		return mapRoomCodeName;
	}

	public void setMapRoomCodeName(Map<String, String> mapRoomCodeName) {
		this.mapRoomCodeName = mapRoomCodeName;
	}

	public List<JE0202> getListBookedRoomRecords() {
		return listBookedRoomRecords;
	}

	public void setListBookedRoomRecords(List<JE0202> listBookedRoomRecords) {
		this.listBookedRoomRecords = listBookedRoomRecords;
	}

	public Map<String, JE0201> getMapRoomCodeObj() {
		return mapRoomCodeObj;
	}

	public void setMapRoomCodeObj(Map<String, JE0201> mapRoomCodeObj) {
		this.mapRoomCodeObj = mapRoomCodeObj;
	}

	public Map<String, String> getMapProperties() {
		return mapProperties;
	}

	public void setMapProperties(Map<String, String> mapProperties) {
		this.mapProperties = mapProperties;
	}

	public List<Map> getListMap() {
		return listMap;
	}

	public void setListMap(List<Map> listMap) {
		this.listMap = listMap;
	}

	public String getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(String currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
}
