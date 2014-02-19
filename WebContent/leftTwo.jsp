<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>人力资源管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="js/tree/resources/css/lTREE.default.css" charset="utf-8" />
<script type="text/javascript" src="js/tree/js/lTREE.js" charset="utf-8"></script>

<style type="text/css">
.ttl { CURSOR: pointer; COLOR: #ffffff; PADDING-TOP: 4px }
.ttl A:active{COLOR: #000000;TEXT-DECORATION: none}
.ttl A:hover{COLOR: #000000;TEXT-DECORATION: none}
.ttl A:link{COLOR: #000000;TEXT-DECORATION: none}
.ttl A:visited{COLOR: #000000;TEXT-DECORATION: none}

.table_body {BACKGROUND-COLOR: #EDF1F8;height:18px;CURSOR: pointer;}
.table_none {BACKGROUND-COLOR: #FFFFFF;height:18px;CURSOR: pointer;}
</style>

<script type="text/javascript">
function showHide(obj){
	if(document.getElementById("M_1") != null){
		document.getElementById("M_1").style.display = "none";
	}
	if(document.getElementById("M_10") != null){
		document.getElementById("M_10").style.display = "none";
	}
	if(document.getElementById("M_20") != null){
		document.getElementById("M_20").style.display = "none";
	}
	if(document.getElementById("M_30") != null){
		document.getElementById("M_30").style.display = "none";
	}
	if(document.getElementById("M_40") != null){
		document.getElementById("M_40").style.display = "none";
	}
	if(document.getElementById("M_50") != null){
		document.getElementById("M_50").style.display = "none";
	}
	
    var oStyle = document.getElementById(obj).style;
    oStyle.display == "none" ? oStyle.display = "block" : oStyle.display = "none";
}
function showDialog(){
	var popstyle="dialogTop:10px;dialogLeft:300px;help:no;center:yes;dialogHeight:800px;dialogWidth:1000px;status:yes;resizable:yes;scroll:yes;loacation:yes;toolbar:yes;";
	window.showModalDialog("overseasBusAppEditInit.action?type=add",window,popstyle);
}
function showAddDocument(){
	var popstyle="dialogTop:10px;dialogLeft:300px;help:no;center:yes;dialogHeight:350px;dialogWidth:500px;status:yes;resizable:yes;scroll:yes;loacation:yes;toolbar:yes;";
	window.showModalDialog("departmentAddInit.action?pageType=add",window,popstyle);	
}
</script>

</head>
<body bgcolor="#9aadcd" leftmargin="0" topmargin="0">
	<input id="group" name="group" value="${group}" type="hidden" />
	<input id="position" name="position" value="${position}" type="hidden" />
	<input id="departmentID" name="departmentID" value="${departmentID}" type="hidden" />
	
     <!-- 员工建档菜单 -->
     <table cellspacing="0" cellpadding="0" width="180" align="center" border="0" style="margin-top:10px;">
         <tr>
             <td width="23"><img height="25" src="images/menu/box_topleft.gif" width="23"></td>
             <td class="ttl" onclick="JavaScript:showHide('M_10');" width="180" background="images/menu/box_topbg.gif">Employee Management</td>
             <td width="7"><img height="25" src="images/menu/box_topright.gif" width="7"></td>
         </tr>
     </table>
     <table id="M_10" style="display: none" cellspacing="0" cellpadding="0" width="180" align="center" border="0">
         <tr>
             <td background='images/menu/box_bg.gif' height="0px" width='180' colspan='3'>
                 <table width="178" border="0" cellpadding="2" cellspacing="1">
                     <tbody>
<s:if test="func0001001 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:window.parent.frames['mainFrame'].location='employeeAddInit.action';" onmousemove="javascript:TDOverORIn('M_11');" onmouseout="javascript:TDOverOROut('M_11');" id="M_11">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Add New Employee
                             </td>
                         </tr>
</s:if>
<s:if test="func0001002 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:window.parent.frames['mainFrame'].location='tmpEmployeeView.action';" onmousemove="javascript:TDOverORIn('M_12');" onmouseout="javascript:TDOverOROut('M_12');" id="M_12">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">View Temporary Employee
                             </td>
                         </tr>
</s:if>
<s:if test="func0001005 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:window.parent.frames['mainFrame'].location='employeeDisplay.action?employeeNum=<s:property value="MP1001_EMPLOYEE_NUM"/>';" onmousemove="javascript:TDOverORIn('M_13');" onmouseout="javascript:TDOverOROut('M_13');" id="M_13">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">View Personal Info
                             </td>
                         </tr>
</s:if>
<s:if test="func0001003 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:window.parent.frames['mainFrame'].location='employeeList.action?pageInitType=first';" onmousemove="javascript:TDOverORIn('M_14');" onmouseout="javascript:TDOverOROut('M_14');" id="M_14">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">View Employee 
                             </td>
                         </tr>
</s:if>
<s:if test="func0001004 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:window.parent.frames['mainFrame'].location='telephoneListInit.action';" onmousemove="javascript:TDOverORIn('M_15');" onmouseout="javascript:TDOverOROut('M_15');" id="M_15">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Employee Telephone List 
                             </td>
                         </tr>
</s:if>
<s:if test="func0001006 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:window.parent.frames['mainFrame'].location='headcountInit.action';" onmousemove="javascript:TDOverORIn('M_16');" onmouseout="javascript:TDOverOROut('M_16');" id="M_16">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Headcount Report
                             </td>
                         </tr>
</s:if>
<s:if test="func0001007 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:window.parent.frames['mainFrame'].location='DocumentMngInit.action';" onmousemove="javascript:TDOverORIn('M_17');" onmouseout="javascript:TDOverOROut('M_17');" id="M_17">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Document Management
                             </td>
                         </tr>
</s:if>
<s:if test="func0001008 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:window.parent.frames['mainFrame'].location='InterviewMngInit.action';" onmousemove="javascript:TDOverORIn('M_18');" onmouseout="javascript:TDOverOROut('M_18');" id="M_18">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Interview Management
                             </td>
                         </tr>
</s:if>
<s:if test="func0001009 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:window.parent.frames['mainFrame'].location='changeDepartmentInit.action';" onmousemove="javascript:TDOverORIn('M_19');" onmouseout="javascript:TDOverOROut('M_19');" id="M_19">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Departmental Transfers
                             </td>
                         </tr>
</s:if>
                         <tr>
                             <td class="table_none" onclick="javascript:window.parent.frames['mainFrame'].location='employeeInfoStatistics.jsp';" onmousemove="javascript:TDOverORIn('M_191');" onmouseout="javascript:TDOverOROut('M_191');" id="M_191">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Employee Statistics
                             </td>
                         </tr>
                         <tr>
                             <td class="table_none" onclick="javascript:window.parent.frames['mainFrame'].location='mp1010InfoMngInit.action';" onmousemove="javascript:TDOverORIn('M_192');" onmouseout="javascript:TDOverOROut('M_192');" id="M_192">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Temporary Employee
                             </td>
                         </tr>
                         
                     </tbody>
                 </table>
             </td>
         </tr>
     </table>
     <table cellspacing="0" cellpadding="0" width="180" align="center" border="0">
         <tr>
             <td colspan="3">
                 <img height='10' src='images/menu/box_bottom.gif' width='180'>
             </td>
         </tr>
     </table>

     <!--考勤管理菜单 -->
     <table cellspacing="0" cellpadding="0" width="180" align="center" border="0">
         <tr>
             <td width="23"><img height="25" src="images/menu/box_topleft.gif" width="23"></td>
             <td class="ttl" onclick="JavaScript:showHide('M_20');" width="180" background="images/menu/box_topbg.gif">Attendance Management</td>
             <td width="7"><img height="25" src="images/menu/box_topright.gif" width="7"></td>
         </tr>
     </table>
     <table id="M_20" style="display: none" cellspacing="0" cellpadding="0" width="180" align="center" border="0">
         <tr>
             <td background='images/menu/box_bg.gif' height="0px" width='180' colspan='3'>
                 <table width="178" border="0" cellpadding="2" cellspacing="1">
                     <tbody>
<s:if test="func0002001 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_21','applyLeaveInit.action?type=add&MP2001_NUM=');" onmousemove="javascript:TDOverORIn('M_21');" onmouseout="javascript:TDOverOROut('M_21');" id="M_21">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Leave Application
                             </td>
                         </tr>
</s:if>
<s:if test="func0002002 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_22','applyLeaveListInit.action');" onmousemove="javascript:TDOverORIn('M_22');" onmouseout="javascript:TDOverOROut('M_22');" id="M_22">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">View Leave Application
                             </td>
                         </tr>
</s:if>
<s:if test="func0002003 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_23','vacationDataList.action');" onmousemove="javascript:TDOverORIn('M_23');" onmouseout="javascript:TDOverOROut('M_23');" id="M_23">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">View Leave Balance
                             </td>
                         </tr>
</s:if>
<s:if test="func0002004 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_24','workingHourRecordsListInit.action');" onmousemove="javascript:TDOverORIn('M_24');" onmouseout="javascript:TDOverOROut('M_24');" id="M_24">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Attendance Record
                             </td>
                         </tr>
</s:if>
<s:if test="func0002005 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_25','leaveApplyReportInit.action');" onmousemove="javascript:TDOverORIn('M_25');" onmouseout="javascript:TDOverOROut('M_25');" id="M_25">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Leave Application Report
                             </td>
                         </tr>
</s:if>
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_28','leaveMngInit.action');" onmousemove="javascript:TDOverORIn('M_28');" onmouseout="javascript:TDOverOROut('M_28');" id="M_28">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">vacation increase mng
                             </td>
                         </tr>
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_29','mp2008InfoMngInit.action');" onmousemove="javascript:TDOverORIn('M_29');" onmouseout="javascript:TDOverOROut('M_29');" id="M_29">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">OverTime Application
                             </td>
                         </tr>
<!-- 
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_201','shiftWorkMngInit.action');" onmousemove="javascript:TDOverORIn('M_201');" onmouseout="javascript:TDOverOROut('M_201');" id="M_201">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Shift Work Schedule
                             </td>
                         </tr>
 -->
                     </tbody>
                 </table>
             </td>
         </tr>
     </table>
     <table cellspacing="0" cellpadding="0" width="180" align="center" border="0">
         <tr>
             <td colspan="3">
                 <img height='10' src='images/menu/box_bottom.gif' width='180'>
             </td>
         </tr>
     </table>

     <!-- 系统管理菜单 -->
     <table cellspacing="0" cellpadding="0" width="180" align="center" border="0">
         <tr>
             <td width="23"><img height="25" src="images/menu/box_topleft.gif" width="23"></td>
             <td class="ttl" onclick="JavaScript:showHide('M_1');" width="180"background="images/menu/box_topbg.gif">System Management</td>
             <td width="7"><img height="25" src="images/menu/box_topright.gif" width="7"></td>
         </tr>
     </table>
     <table id="M_1" style="display: none" cellspacing="0" cellpadding="0" width="180" align="center" border="0">
         <tr>
             <td background='images/menu/box_bg.gif' height="0px" width='180' colspan='3'>
                 <table width="178" border="0" cellpadding="2" cellspacing="1">
                     <tbody>
<s:if test="func0003001 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_7','initBulletin.action?type=Add');" onmousemove="javascript:TDOverORIn('M_7');" onmouseout="javascript:TDOverOROut('M_7');" id="M_7">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Bulletin Management
                             </td>
                         </tr>
</s:if>
<s:if test="func0003002 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_8','bulletinList.action');" onmousemove="javascript:TDOverORIn('M_8');" onmouseout="javascript:TDOverOROut('M_8');" id="M_8">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">View Bulletin
                             </td>
                         </tr>
</s:if>
<s:if test="func0003003 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_9','changePassword.action');" onmousemove="javascript:TDOverORIn('M_9');" onmouseout="javascript:TDOverOROut('M_9');" id="M_9">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Change Password
                             </td>
                         </tr>
</s:if>
<s:if test="func0003005 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_101','resetPasswordInit.action');" onmousemove="javascript:TDOverORIn('M_101');" onmouseout="javascript:TDOverOROut('M_101');" id="M_101">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Reset Password
                             </td>
                         </tr>
</s:if>
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_102','pdf/OrganizationalChart201203.pdf');" onmousemove="javascript:TDOverORIn('M_102');" onmouseout="javascript:TDOverOROut('M_102');" id="M_102">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Organization Structure
                             </td>
                         </tr>
<s:if test="func0003006 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_103','mailSetting.jsp');" onmousemove="javascript:TDOverORIn('M_103');" onmouseout="javascript:TDOverOROut('M_103');" id="M_103">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Service Mail Setting
                             </td>
                         </tr>
</s:if>
<s:if test="func0003007 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_104','departmentMng.jsp');" onmousemove="javascript:TDOverORIn('M_104');" onmouseout="javascript:TDOverOROut('M_104');" id="M_104">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Department Management
                             </td>
                         </tr>
</s:if>
<s:if test="func0003008 == 1" >
                         <tr>
                             <td class="table_none" onclick="showAddDocument()" onmousemove="javascript:TDOverORIn('M_105');" onmouseout="javascript:TDOverOROut('M_105');" id="M_105">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Add New Department
                             </td>
                         </tr>
</s:if>
<s:if test="func0003009 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_106','departmentPositionMng.jsp');" onmousemove="javascript:TDOverORIn('M_106');" onmouseout="javascript:TDOverOROut('M_106');" id="M_106">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Department&Job Title
                             </td>
                         </tr>
</s:if>
<s:if test="func0003010 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_107','holidayMngInit.action');" onmousemove="javascript:TDOverORIn('M_107');" onmouseout="javascript:TDOverOROut('M_107');" id="M_107">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Holiday Management
                             </td>
                         </tr>
</s:if>
<s:if test="func0003011 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_108','loginHistoryInit.action');" onmousemove="javascript:TDOverORIn('M_108');" onmouseout="javascript:TDOverOROut('M_108');" id="M_108">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Login History
                             </td>
                         </tr>
</s:if>
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_109','checkinoutInfoMngInit.action');" onmousemove="javascript:TDOverORIn('M_109');" onmouseout="javascript:TDOverOROut('M_109');" id="M_109">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Auto Load Data
                             </td>
                         </tr>

                     </tbody>
                 </table>
             </td>
         </tr>
     </table>
     <table cellspacing="0" cellpadding="0" width="180" align="center" border="0">
         <tr>
             <td colspan="3">
                 <img height='10' src='images/menu/box_bottom.gif' width='180'>
             </td>
         </tr>
     </table>

     <!-- ALL Report -->
     <table cellspacing="0" cellpadding="0" width="180" align="center" border="0">
         <tr>
             <td width="23"><img height="25" src="images/menu/box_topleft.gif" width="23"></td>
             <td class="ttl" onclick="JavaScript:showHide('M_50');" width="180"background="images/menu/box_topbg.gif">Reports</td>
             <td width="7"><img height="25" src="images/menu/box_topright.gif" width="7"></td>
         </tr>
     </table>
     <table id="M_50" style="display: none" cellspacing="0" cellpadding="0" width="180" align="center" border="0">
         <tr>
             <td background='images/menu/box_bg.gif' height="0px" width='180' colspan='3'>
                 <table width="178" border="0" cellpadding="2" cellspacing="1">
                     <tbody>
<s:if test="func0005001 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_51','departmentalTransfersReportInit.action');" onmousemove="javascript:TDOverORIn('M_51');" onmouseout="javascript:TDOverOROut('M_51');" id="M_51">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Departmental Transfers
                             </td>
                         </tr>
</s:if>
<s:if test="func0005002 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_52','trunoverReportInit.action');" onmousemove="javascript:TDOverORIn('M_52');" onmouseout="javascript:TDOverOROut('M_52');" id="M_52">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Employee Dimission Report
                             </td>
                         </tr>
</s:if>
                     </tbody>
                 </table>
             </td>
         </tr>
     </table>
     <table cellspacing="0" cellpadding="0" width="180" align="center" border="0">
         <tr>
             <td colspan="3">
                 <img height='10' src='images/menu/box_bottom.gif' width='180'>
             </td>
         </tr>
     </table>
     
          <!-- ALL Report -->
     <table cellspacing="0" cellpadding="0" width="180" align="center" border="0">
         <tr>
             <td width="23"><img height="25" src="images/menu/box_topleft.gif" width="23"></td>
             <td class="ttl" onclick="JavaScript:showHide('M_50');" width="180"background="images/menu/box_topbg.gif">Boardroom Booking</td>
             <td width="7"><img height="25" src="images/menu/box_topright.gif" width="7"></td>
         </tr>
     </table>
     <table id="M_50" style="display: none" cellspacing="0" cellpadding="0" width="180" align="center" border="0">
         <tr>
             <td background='images/menu/box_bg.gif' height="0px" width='180' colspan='3'>
                 <table width="178" border="0" cellpadding="2" cellspacing="1">
                     <tbody>
<s:if test="func0005001 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_51','departmentalTransfersReportInit.action');" onmousemove="javascript:TDOverORIn('M_51');" onmouseout="javascript:TDOverOROut('M_51');" id="M_51">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Departmental Transfers
                             </td>
                         </tr>
</s:if>
<s:if test="func0005002 == 1" >
                         <tr>
                             <td class="table_none" onclick="javascript:NowShow('M_52','trunoverReportInit.action');" onmousemove="javascript:TDOverORIn('M_52');" onmouseout="javascript:TDOverOROut('M_52');" id="M_52">
                                 <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Employee Dimission Report
                             </td>
                         </tr>
</s:if>
                     </tbody>
                 </table>
             </td>
         </tr>
     </table>
     <table cellspacing="0" cellpadding="0" width="180" align="center" border="0">
         <tr>
             <td colspan="3">
                 <img height='10' src='images/menu/box_bottom.gif' width='180'>
             </td>
         </tr>
     </table>

</body>
</html>

<script type="text/javascript">
var NowClickName="";
function NowShow(TopMenuName,Url){
    document.getElementById(TopMenuName).className  = "table_body";
    if (NowClickName!="" &&NowClickName!=TopMenuName)
        document.getElementById(NowClickName).className  = "table_none"; 
    NowClickName = TopMenuName;
    //var o=window.open(url); 
   window.parent.frames["mainFrame"].location=Url;
   //parment.mainFrame.src=Url;
}
function TDOverOROut(iname){
    if (NowClickName!=iname){
        document.getElementById(iname).className = "table_none";
    }
}
function TDOverORIn(iname){
    if (NowClickName != iname){
        document.getElementById(iname).className = "table_body";
    }
}
</script>