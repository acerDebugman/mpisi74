<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />

<link rel='stylesheet' type='text/css' href='dist/fullcalendar-1.6.4/fullcalendar/fullcalendar.css' />
<script type='text/javascript' src='dist/fullcalendar-1.6.4/lib/jquery.min.js'></script>
<script type='text/javascript' src='dist/fullcalendar-1.6.4/fullcalendar/fullcalendar.js'></script>

<script type="text/javascript" src="js/jquery.form.js"></script>

<script type="text/javascript" src="js/calendar.js"></script>

<title>test</title>
<script type="text/javascript">
$(document).ready(function(){
	//page now is ready, Initialize the calendar
	$('#calendar').fullCalendar({
		dayClick:function(){
			//alert("a day has been clicked!");
			//$("#calendar").fullCalendar('next');
		},
		header:{
			left:   'today prev,next',
    		center: 'title',
    		right:  'Week,Month'
		},
		defaultView:'Month',
		weekends:true,
//		weekNumbers:true,
		weekNumberCalculation:"iso",
		aspectRatio: 2.0,
		viewRender:function(view, element){
			//alert("viewRender");
		},
		selectable:true,
		//agenda
		allDaySlot:true,
		allDayText:'all-day',
		firstHour:6,
		minTime:6,
		maxTime:30,
		//event
		timeFormat:'H:mm{ - H:mm}',
		events:[
			{
				id:1,
				title:"joe",
				start:"2014-03-20 06:00:00",
				end:"2014-03-20 18:00:00",
				allDay:false
			},
			{
				id:2,
				title:"tst2",
				start:"2014-03-21 18:00:00",
				end:"2014-03-22 06:00:00",
				allDay:false
			},
			{
				id:3,
				title:"test 3",
				start:"2014-03-22 18:00:00",
				end:"2014-03-23 06:00:00",
				url:"www.google.com",
				allDay:false
			}
		],
		color:'yellow',
		textColor:'red',
		eventClick:function(calEvent, jsEvent, view){
			alert('Event | ' + calEvent.title);
			alert('jsEvent | ' + jsEvent.pageX + ',' + jsEvent.pageY);
			alert("View: " + view.name);
			
			$(this).css("border-color", "red");
			
			if(event.url){
				window.open(calEvent.url);
				return false;
			}
		},
		dayClick: function(date, allDay, jsEvent, view) {

			//window.open
			
	        if (allDay) {
	            alert('Clicked on the entire day: ' + date);
	        }else{
	            alert('Clicked on the slot: ' + date);
	        }
	
	        //alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
	
	        //alert('Current view: ' + view.name);
	
	        // change the day's background color just for fun
	        //$(this).css('background-color', 'red');
	
	    }
		
	});
});
function downloadMonthlyExcel(){
	//alert("download monthly excel !");
	//var param = {};
	//alert("ajax start");
	/*$.ajax({
		url:"exportShiftWorkExcelTemplate.action",
		dataType:"html",
		success:function(msg){}
	});*/
	var options = {
		url : "exportShiftWorkExcelTemplate.action",
		type : "post",
		//dataType : "none",
		dataType : "none",
		mimeType : "application/vnd.ms-excel",
		success : function(msg){}
	};
	$("#form0").ajaxSubmit(options); //why ajax can't work
	
	//$.ajax(options);
	//alert("ajax end");
}

function openAddWindow(){
	var popstyle="dialogTop:300px;dialogLeft:600px;dialogRight:600px;help:no;center:yes;dialogHeight:350px;dialogWidth:600px;status:no;resizable:no;scroll:no";
	window.showModalDialog("addShiftWorkLeaveInit.action?pageType=add", window, popstyle);
}

function openImportExcelWindow(){
	var popstyle="dialogTop:300px;dialogLeft:600px;dialogRight:600px;help:no;center:yes;dialogHeight:350px;dialogWidth:600px;status:no;resizable:no;scroll:no";
	window.showModalDialog("importShiftworkScheduleExcelInit.action?pageType=add", window, popstyle);
}

</script>
</head>
<body>
<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
  <tr>
    <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Leave Management</td>
    <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
  </tr>
  <tr>
    <td height='27px' class='menubar_function_text'>Operation Function: Shift Work Schedule</td>
    <td class='menubar_menu_td' align='right'>
      <table border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="menubar_button" id="button_0"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td height='5px' colspan='2'></td>
  </tr>
</table>
<!-- 头部菜单 End -->   
<fieldset style="border-color:#999999;border-width:1px;border-style:Solid;margin-bottom:10px;">
<legend style="color:#333333;font-size:1.5em;font-weight:bold;">Query Condition</legend>&nbsp;
<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
	<tr>
         <td class="table_body table_body_NoWidth">From Date</td>
         <td class="table_none table_none_NoWidth">
         	<input id="fromDate" name="fromDate" value="${fromDate}" type="text" class="text_input" onfocus="calendar(this);"/>
         </td>
         <td class="table_body table_body_NoWidth">To Date</td>
         <td class="table_none table_none_NoWidth">
             <input id="toDate" name="toDate" value="${toDate}" type="text" class="text_input" onfocus="calendar(this);"/>
         </td>
     </tr>
    <tr>
        <td class="table_body table_body_NoWidth">Employee Number:</td>
        <td class="table_none table_none_NoWidth">
            <input id="employeeNum" name="employeeNum" value="${employeeNum}" type="text" class="text_input" />
        </td>
        <td class="table_body table_body_NoWidth">Shift Work Type:</td>
        <td class="table_none table_none_NoWidth">
            <input id="employeeName" name="employeeName" value="${employeeName}" type="text" class="text_input" />
        </td>
    </tr>
    <tr>
        <td class="table_body table_body_NoWidth">Branch Site:</td>
        <td class="table_none table_none_NoWidth"><s:select id="branchSiteId" name="branchSiteId"  list="branchSiteList" theme="simple"/></td>
    </tr>
    <tr>
        <td colspan="4" align="right"></td>
    </tr>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
	<tr>                                 
        <td align="left" width="100px">
            <input id="shiftWorkAddLeave" name="shiftWorkAddLeave" type="button" value="Add Shiftwork Leave" onclick="openAddWindow()" style="float:left"/>
        </td>
       	<td align="center">
       		<input type="button" value="Download Next Month Excel Template" onclick="downloadMonthlyExcel()"/>
            <input id="ImportExcel" name="ImportExcel" value="Import Excel" onclick="openImportExcelWindow()" type="button"/>
		</td>
        <td align="center">
        	<input type="submit" name="searchBtn" value="Search" id="searchBtn" />
            <input id="refreshData" name="refreshData" type="button" value="" onclick="refresh()" style="display:none;"/>
        </td>
    </tr>
</table>    
</fieldset>
           
<div id="leaveInfoDiv">
<table id="tb1" class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
    <tr class="table_title" align="center">
        <th scope="col">Employee Number</th>
        <th scope="col">Employee Name</th>
        <th scope="col">Date</th>
        <th scope="col">Shift Work Type</th>
        <th scope="col">Branch Site</th>
    </tr>

<s:iterator value="mp2007InfoList" status="st">
    <tr id="<s:property value="MP2007_SEQ"/>" class="row" align="center" style="height:28px;" onmousedown="rowClick('<s:property value="MP2007_SEQ"/>')">
        <td width="30px">
            <input id="Approval" name="Approval" type="button" value="▼" style="width:30px;border:none; background-color:transparent" onclick="ApprovalLeave('<s:property value="MP2007_SEQ"/>','<s:property value="MP2007_STATUS"/>')"/>
        </td>
        <td width="30px">${st.index+1}</td>
        <td width="150px"><s:property value="MP2007_EMPLOYEE_NUM"/></td>
        <td><s:property value="MP2007_EMPLOYEE_NAME"/></td>
        <td width="200px"><s:property value="MP2007_DEPARTMENT_NAME"/></td>
        <td width=100px"><s:property value="MP2007_DAYS"/></td>
<s:if test="MP2007_STATUS == 1" >
        <td style="color:blue;width:100px;"><s:property value="MP2007_STATUS_NAME"></s:property></td>
</s:if>
<s:elseif test="MP2007_STATUS == 2" >
        <td style="color:green;width:100px;"><s:property value="MP2007_STATUS_NAME"></s:property></td>
</s:elseif>
<s:elseif test="MP2007_STATUS == 3" >
        <td style="color:red;width:100px;"><s:property value="MP2007_STATUS_NAME"></s:property></td>
</s:elseif>
        <td width="100px"><s:property value="MP2007_TYPE_NAME"/></td>
    </tr>
    
</s:iterator>
</table>
</div>

<table cellspacing="1" border="0" style="background-color:White;border-width:0px;" align="center">
    <tr class="">
      <td>
          <div id="pager" style="border: 1px solid #FFFFFF;"></div>
      </td>
    </tr>
<tr><td height="5"></td></tr>
</table>

<!-- For Download -->
<form id="form0" name="form0" enctype="multipart/form-data">
</form>
</body>
</html>