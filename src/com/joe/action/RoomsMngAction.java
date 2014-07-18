package com.joe.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.joe.common.Constant;
import com.joe.common.LogUtil;
import com.joe.common.PageBean;
import com.joe.common.UtilCommon;
import com.joe.common.UtilDate;
import com.joe.model.JE0201;
import com.joe.model.JE0202;
import com.joe.model.MP1001;
import com.joe.service.IJE0201Service;
import com.joe.service.IJE0202Service;
import com.joe.service.IMP0011Service;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RoomsMngAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(RoomsMngAction.class);
	
	private IJE0201Service serviceJE0201;
	private IJE0202Service serviceJE0202;
	private IMP0011Service serviceMP0011;
	
	private String currentPageNum = "1";
	private String pageSize = "4";
	private PageBean pb; 
	
	private String roomName;
	private String floor;
	private String roomType;
	private String roomDes;
	private String strBookDes;
	private String strRoomCode;
	private String strRoomName;

	private String strWhichDay;
	private String strFromTime;
	private String strToTime;
	
	private String bookedRoomSeq;
	
	private String strSubscriberCode;
	private String strSubscriberName;
	
	private List<String> floorList;
	private List<String> roomTypeList;
	private List<JE0201> allRoomsList;
	private List<String> listTimeList;
	private List<JE0202> listBookedRoomRecords;
	
	private List<String> listAllRoomNames;
	private List<String> listAllRoomCodes;
	private List<String> listRoomName;
	private Map<String, JE0201> mapRoomCodeObj;
	private List<String> listFromTime;
	private List<String> listToTime;
	
	private Map<String, String> mapProperties;
	private List<Map> listMap;
	private MP1001 mp1001;
	private MP1001 subscriberInfo;

	public String roomsMngInit(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Enter function roomsMngInit");
			//----------------------------Operation History------------------
			
			//allRoomsList = serviceJE0201.findAll();
			allRoomsList = serviceJE0201.findByColumnName(null, " order by JE0201_ROOM_NAME ");
			floorList = Constant.getRoomFloorList();
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public String roomSearch(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"operation roomSearch");
			//----------------------------Operation History------------------
			
			Map<String, String> columnMap = new HashMap<String, String>();
			if(!floor.equalsIgnoreCase("---please select---")){
				columnMap.put("JE0201_ROOM_FLOOR", floor);	
			}
			columnMap.put("JE0201_ROOM_NAME", roomName);
			
			allRoomsList = serviceJE0201.findByColumnName(columnMap);
			
			//initial
			floorList = Constant.getRoomFloorList();
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public String roomAddInit(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"roomAddInit");
			//----------------------------Operation History------------------
			
			floorList = Constant.getRoomFloorList();
			roomTypeList = Constant.getRoomTypeList();

			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public String roomAdd(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"add one room");
			//----------------------------Operation History------------------
			
			JE0201 room0201 = new JE0201();

			room0201.setJE0201_ROOM_NAME(roomName);
			room0201.setJE0201_ROOM_FLOOR(floor);
			room0201.setJE0201_ROOM_TYPE(roomType);
			room0201.setJE0201_ROOM_DES(roomDes);
			room0201.setJE0201_STATUS("1");
			serviceJE0201.save(room0201);

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			
			StringBuffer sb = new StringBuffer();
			sb.append("Room add success!");
			out.println(sb.toString());
	        out.flush();
	        out.close();
	        
			return NONE;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}

	public String bookBoardroomMngInit(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Enter function Booking Boardroom");
			//----------------------------Operation History------------------
		
			listTimeList = Constant.getHalfAnHourIntervalWorkTime();
			floorList = Constant.getRoomFloorList();
			
			Map<String, String> pageProperty = new HashMap<String, String>();
			pageProperty.put("currentPageNum", currentPageNum);
			pageProperty.put("pageSize", pageSize);
			pb = serviceJE0202.queryForPage(pageProperty);
			listBookedRoomRecords = pb.getList();

			for(JE0202 je0202 : listBookedRoomRecords){
				serviceJE0202.getSubscriberInfo(je0202);
			}
			
			allRoomsList = serviceJE0201.findAll();
			listAllRoomNames = new ArrayList<String>();
			for(JE0201 je0201 : allRoomsList){
				listAllRoomNames.add(je0201.getJE0201_ROOM_NAME());
			}

			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}

	public String searchBookedRoom(){
		try{
			Map<String, String> propMap = new HashMap<String, String>();
			propMap.put("pageSize", pageSize);
			propMap.put("currentPageNum", currentPageNum);
			propMap.put("JE0202_DATE", strWhichDay);
			if(null != strFromTime && !strFromTime.equalsIgnoreCase("") && !strFromTime.equalsIgnoreCase("-1")){
				strFromTime = strWhichDay + " " + strFromTime;
				propMap.put("JE0202_FROM_DATETIME", strFromTime);
			}
			if(null != strToTime && !strToTime.equalsIgnoreCase("") && !strToTime.equalsIgnoreCase("-1")){
				strToTime = strWhichDay + " " + strToTime;
				propMap.put("JE0202_END_DATETIME", strToTime);
			}
			propMap.put("JE0202_ROOM_NAME", strRoomCode); //strRoomCode is room name, room code already deleted
			propMap.put("JE0202_ROOM_FLOOR", floor);
			propMap.put("JE0202_USER_TYPE", "S");//only for

			pb = serviceJE0202.queryForPage(propMap);
			listBookedRoomRecords = pb.getList();
			
			for(JE0202 je0202 : listBookedRoomRecords){
				serviceJE0202.getSubscriberInfo(je0202);
			}
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
			mp1001 = (MP1001)session.get(Constant.EMPLOYEE_DATA);
			
			//room
			allRoomsList = serviceJE0201.findAll();
			listAllRoomNames = new ArrayList<String>();
			for(JE0201 je0201 : allRoomsList){
				listAllRoomNames.add(je0201.getJE0201_ROOM_NAME());
			}
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public String bookOneRoomInit(){
		try{
			allRoomsList = serviceJE0201.findByColumnName(null, " order by JE0201_ROOM_NAME ASC ");
			listAllRoomNames = new ArrayList<String>();
			for(JE0201 je0201 : allRoomsList){
				listAllRoomNames.add(je0201.getJE0201_ROOM_NAME());
			}

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
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		MP1001 employeeData = (MP1001)session.get(Constant.EMPLOYEE_DATA);
		try{
			//----------------------------Operation History------------------
			LogUtil logUtil = new LogUtil();
			logUtil.setServiceMP0011(serviceMP0011);
			logUtil.writeOperationLog(employeeData.getMP1001_EMPLOYEE_NUM(),employeeData.getMP1001_PREFERED_NAME(),"Operation save one booked room");
			//----------------------------Operation History------------------

			JE0202 je0202 = new JE0202();
			
			String seq = Constant.generateSeq();
			int number = UtilCommon.getTempSeq();
			seq = seq + Integer.toString(number);
			je0202.setJE0202_SEQ(seq);
			
			//here also should generate a case code, for late if any one want to use add email function.
			
			je0202.setJE0202_DATE(strWhichDay);
			strFromTime = strWhichDay + " " + strFromTime;
			je0202.setJE0202_FROM_DATETIME(strFromTime);
			strToTime = strWhichDay + " " + strToTime;
			je0202.setJE0202_END_DATETIME(strToTime);
			je0202.setJE0202_CREATE_DATETIME(UtilDate.get24DateTime());
			je0202.setJE0202_DES(strBookDes);
			je0202.setJE0202_USER_NUM(strSubscriberCode);
			je0202.setJE0202_USER_TYPE("S");
			JE0201 roomInfo = serviceJE0201.findByKey(strRoomCode);
			je0202.setJE0202_ROOM_NAME(roomInfo.getJE0201_ROOM_NAME());
			je0202.setJE0202_ROOM_FLOOR(roomInfo.getJE0201_ROOM_FLOOR());
			je0202.setJE0202_STATUS("1");
			
			/*judge if time overlapped*/
			//find which day it is
			Map<String, String> columnMap = new HashMap<String, String>();
			columnMap.put("JE0202_DATE", strWhichDay);
			if(null != strRoomCode && !strRoomCode.equalsIgnoreCase("") && !strRoomCode.equalsIgnoreCase("-1")){
				columnMap.put("JE0202_ROOM_NAME", strRoomCode);
			}
			//.if(null != strSubscriberCode && !strSubscriberCode.equalsIgnoreCase("") && !strSubscriberCode.equalsIgnoreCase("-1")){
				//columnMap.put("JE0202_USER_NUM", strSubscriberCode);
			columnMap.put("JE0202_USER_TYPE", "S");
			//}
			/*
			if(null != strFromTime && !strFromTime.equalsIgnoreCase("") && !strFromTime.equalsIgnoreCase("-1")){
				columnMap.put("JE0202_FROM_DATETIME", strFromTime);
			}
			if(null != strToTime && !strToTime.equalsIgnoreCase("") && !strToTime.equalsIgnoreCase("-1")){
				columnMap.put("JE0202_END_DATETIME", strToTime);
			}
			*/
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
					//sb.append("<script type='text/javascript'>");
					//sb.append("alert('Time overlapped!Please check if the room is occupied between that period!');");
					//sb.append("window.close();");
					//sb.append("</script>");
					sb.append("E:Time overlapped!Please check if the room is occupied between that period!");
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
			//sb.append("<script type='text/javascript'>");
			//sb.append("window.close();");
			//sb.append("</script>");
			sb.append("S:book success!");
			
			out.println(sb.toString());
			out.flush();
			out.close();
			
			return NONE;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public String delBookedRoomRecord(){
		try{
			JE0202 je0202 = new JE0202();
			je0202.setJE0202_SEQ(bookedRoomSeq);
			serviceJE0202.delete(je0202);

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

	/*public Map<String, String> getMapRoomCodeName() {
		return mapRoomCodeName;
	}

	public void setMapRoomCodeName(Map<String, String> mapRoomCodeName) {
		this.mapRoomCodeName = mapRoomCodeName;
	}*/

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

	public PageBean getPb() {
		return pb;
	}

	public void setPb(PageBean pb) {
		this.pb = pb;
	}

	public String getBookedRoomSeq() {
		return bookedRoomSeq;
	}

	public void setBookedRoomSeq(String bookedRoomSeq) {
		this.bookedRoomSeq = bookedRoomSeq;
	}

	public String getStrRoomName() {
		return strRoomName;
	}

	public void setStrRoomName(String strRoomName) {
		this.strRoomName = strRoomName;
	}

	public List<String> getListRoomName() {
		return listRoomName;
	}

	public void setListRoomName(List<String> listRoomName) {
		this.listRoomName = listRoomName;
	}

	public MP1001 getMp1001() {
		return mp1001;
	}

	public void setMp1001(MP1001 mp1001) {
		this.mp1001 = mp1001;
	}

	public MP1001 getSubscriberInfo() {
		return subscriberInfo;
	}

	public void setSubscriberInfo(MP1001 subscriberInfo) {
		this.subscriberInfo = subscriberInfo;
	}

	public IMP0011Service getServiceMP0011() {
		return serviceMP0011;
	}

	public void setServiceMP0011(IMP0011Service serviceMP0011) {
		this.serviceMP0011 = serviceMP0011;
	}
}
