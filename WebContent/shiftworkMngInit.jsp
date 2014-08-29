\<%@ page language="java" pageEncoding="UTF-8"%>
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
<script src="js/jquery.pager.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>

<script type="text/javascript" src="js/calendar.js"></script>

<title>test</title>
<style type="text/css">
#pager ul.pages {
display:block;
border:none;
text-transform:uppercase;
font-size:10px;
margin:1px 0 10px;
padding:0;
}

#pager ul.pages li {
list-style:none;
float:left;
border:1px solid #65AB31;
text-decoration:none;
margin:0 5px 0 0;
padding:5px;
}

#pager ul.pages li:hover {
border:1px solid #003f7e;
}

#pager ul.pages li.pgEmpty {
border:1px solid #000000;
color:#000000;
background-color:grey;
}

#pager ul.pages li.pgCurrent {
border:1px solid #003f7e;
color:#000;
font-weight:700;
background-color:#65AB31;
}
.tdBg{
background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px;
color:white;
text-align:center;
margin:0px;
padding:0px;
}
</style>

<script type="text/javascript">
window.document.onkeydown = keyStroke;
function keyStroke(){
	if (window.event.keyCode==13){
		window.event.keyCode=9;
	}
}
$(document).ready(function() {
    var _pageNum = $("#pageNum").val();
    if(_pageNum == 0){
    	_pageNum = 1;
    }
    $("#pager").pager({ pagenumber: _pageNum, pagecount: $("#pageCount").val(), buttonClickCallback: PageClick });
});

PageClick = function(pageclickednumber) {
    $("#pager").pager({ pagenumber: pageclickednumber, pagecount: $("#pageCount").val(), buttonClickCallback: PageClick });
    
    var fromDate = $('#fromDate').val();
	var toDate = $('#toDate').val();
	var employeeNum = $('#employeeNum').val();
	var employeeName = $('#employeeName').val();
	var branchSiteId = $('#branchSiteId').val();
	
    var param = {'fromDate' : fromDate,
				 'toDate' : toDate,
				 'employeeNum' : employeeNum,
				 'employeeName' : employeeName,
				 'branchSiteId' : branchSiteId,
				 'currentPageNum' : pageclickednumber
				 };
    
    document.getElementById("pageNum").value = pageclickednumber;
    $('#shiftWorkInfoList').load('shiftWorkSearch.action', param);
}
</script>

<script type="text/javascript">

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
	var popstyle="dialogTop:300px;dialogLeft:600px;dialogRight:600px;help:no;center:yes;dialogHeight:550px;dialogWidth:600px;status:no;resizable:no;scroll:no";
	//window.showModalDialog("addShiftWorkLeaveInit.action?pageType=add", window, popstyle);
	window.open("addShiftWorkLeaveInit.action?pageType=add", "_blank", popstyle);
}

function openImportExcelWindow(){
	var popstyle="dialogTop:300px;dialogLeft:600px;dialogRight:600px;help:no;center:yes;dialogHeight:350px;dialogWidth:600px;status:no;resizable:no;scroll:no";
	window.showModalDialog("importShiftworkScheduleExcelInit.action", window, popstyle);
}

function searchSubmit(){
	var fromDate = $('#fromDate').val();
	var toDate = $('#toDate').val();
	var employeeNum = $('#employeeNum').val();
	var employeeName = $('#employeeName').val();
	var branchSiteId = $('#branchSiteId').val();
	//var shiftWorkType = $('shiftWorkType').val();
	var currentPageNum = $('currentPageNum').val();
	
	var param = {'fromDate' : fromDate,
				 'toDate' : toDate,
				 'employeeNum' : employeeNum,
				 'employeeName' : employeeName,
				 'branchSiteId' : branchSiteId,
				 'currentPageNum' : currentPageNum
				 };
	
	$('#shiftWorkInfoList').load('shiftWorkSearch.action', param);
}
function shiftWorkCalculate(){
	var options = {
			url: 'shiftWorkCalculate.action',
			data : 'POST',
			dataType : 'HTML',
			cache : false
	};
	$.ajax(options);
}

function exportSchedule(){
	
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
        <td class="table_body table_body_NoWidth">Branch Site:</td>
        <td class="table_none table_none_NoWidth"><s:select id="branchSiteId" name="branchSiteId"  list="branchSiteList" theme="simple"/></td>
    </tr>
    <%-- <tr>
        <td class="table_body table_body_NoWidth">Branch Site:</td>
        <td class="table_none table_none_NoWidth"><s:select id="branchSiteId" name="branchSiteId"  list="branchSiteList" theme="simple"/></td>
    </tr> --%>
    <tr>
        <td colspan="4" align="right"></td>
    </tr>
</table>
<table width="100%" border="0" color="red" cellspacing="1" cellpadding="3" align="center">
	<tr>                                 
       	<td align="left" width="38%">
       		<input type="button" value="Download Next Month Excel Template" onclick="downloadMonthlyExcel()"/>
            <input id="ImportExcel" name="ImportExcel" value="Import Excel" onclick="openImportExcelWindow()" type="button"/>
		</td>
		<!-- <td align="left" width="30%">
            <input id="shiftWorkAddLeave" name="shiftWorkAddLeave" type="button" value="Add Shiftwork Leave" onclick="openAddWindow()" style="float:left"/>
        </td> -->
        <td align="left" width="30%">
            <input id="shiftWorkCalculate" name="shiftWorkCalculate" type="button" value="implement calculate" onclick="shiftWorkCalculate()" style="float:left"/>
        </td>
        <!-- <td align="left" width="30%">
            <input id="exportShiftworkSchedule" name="exportShiftworkSchedule" type="button" value="export schedule" onclick="exportSchedule()" style="float:left"/>
        </td> -->
        <td align="right" width="30%">
        	<input type="button" name="searchBtn" value="Search" id="searchBtn" onclick="searchSubmit()"/>
            <input id="refreshData" name="refreshData" type="button" value="" onclick="refresh()" style="display:none;"/>
        </td>
    </tr>
</table>    
</fieldset>
           
<div id="shiftWorkInfoList">
<input id="pageCount" name="pageCount" value="${pageBean.totalPage}" type="hidden" />
<input id="pageNum" name="pageNum" value="${currentPageNum}" type="hidden" />
<table id="tb1" class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
    <tr class="table_title" align="center">
        <th scope="col" width="150px">Employee Number</th>
        <th scope="col" width="150px">Employee Name</th>
        <th scope="col" width="120px">Date</th>
        <th scope="col" width="80px">Type</th>
        <th scope="col" width="120px">Branch Site</th>
    </tr>

<s:iterator value="shiftworkScheduleList" status="st">
    <tr id="<s:property value="MP2010_ID"/>" class="row" align="center" style="height:28px;" >
        <td><s:property value="MP2010_EMPLOYEE_NUM" /></td>
        <td><s:property value="employeeInfo.MP1001_PREFERED_NAME" /></td>
        <td><s:property value="MP2010_DATE.substring(0, 10)" /></td>
        <td><s:property value="MP2010_TYPE" /></td>
        <td><s:property value="MP2010_BRANCH_SITE" /></td>
    </tr>
</s:iterator>
</table>

<table cellspacing="1" border="0" style="background-color:White;border-width:0px;" align="center">
    <tr class="">
      <td>
          <div id="pager" style="border: 1px solid #FFFFFF;"></div>
      </td>
    </tr>
<tr><td height="5"></td></tr>
</table>
</div>

<!-- For Download -->
<form id="form0" name="form0" enctype="multipart/form-data">
</form>
</body>
</html>