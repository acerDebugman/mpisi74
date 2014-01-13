<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Leave Management</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />

<script src="js/jquery1.4.2.js" type="text/javascript" ></script>
<script type="text/javascript" src="js/checkform.js" charset="utf-8"></script>
<script type="text/javascript" src="js/jquery.pager.js"></script>
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

dl {width: 100px;font: 10pt 'Arial';line-height: 1.5em;position:relative;margin: 0px;padding: 0px;left:5px;}
dt {float: left;width: 20px;margin: 0px;padding: 0px;cursor:hand;margin-top:3px;margin-bottom:3px;}
dd {float: left;clear: none;width: 80px;margin: 0px;padding: 0px;cursor:hand;border-left:1px solid #E0E0E0;margin-top:3px;margin-bottom:3px;}
</style>
<script type="text/javascript">
$(document).ready(function() {
    $("#pager").pager({ pagenumber: 1, pagecount: $("#pageCount").val(), buttonClickCallback: PageClick });
});

PageClick = function(pageclickednumber) {
	
    $("#pager").pager({ pagenumber: pageclickednumber, pagecount: $("#pageCount").val(), buttonClickCallback: PageClick });
	var _enum = $("#employeeNum").val();
	var _ename = $("#employeeName").val();
	var _dept = $("#departmentID").val();
	var _status =$("#attendenceStatus").val();
	var _type = $("#leaveType").val();
	document.getElementById("pageNum").value = pageclickednumber;
    var param = {"pageNum" : pageclickednumber,"employeeNum" : _enum,"employeeName" : _ename,"leaveType" : _type,"departmentID" : _dept,"attendenceStatus":_status};
    $("#leaveInfoDiv").load("leaveInfoRefresh.action",param);
};
</script>
<script type="text/javascript">
function openAddWindow(){
	var popstyle="dialogTop:300px;dialogLeft:600px;dialogRight:600px;help:no;center:yes;dialogHeight:350px;dialogWidth:600px;status:no;resizable:no;scroll:no";
	window.showModalDialog("leaveInfoAddInit.action?pageType=add", window, popstyle);
}
function Editleave(){
	var id = document.getElementById("mp2007Seq").value;
	var popstyle="dialogTop:300px;dialogLeft:600px;dialogRight:600px;help:no;center:yes;dialogHeight:350px;dialogWidth:600px;status:no;resizable:no;scroll:no";
	window.showModalDialog("leaveInfoAddInit.action?pageType=edit&mp2007Seq=" + id, window, popstyle);

	//document.getElementById(id).style.backgroundColor = '#666666';
}
function refresh(){
	var _enum = document.getElementById("employeeNum").value;
	var _ename = document.getElementById("employeeName").value;
	var _dept = document.getElementById("departmentID").value;
	var _status =document.getElementById("attendenceStatus").value;
	var _type = document.getElementById("leaveType").value;
	var _pageNum = document.getElementById("pageNum").value;
	var _pageCount = document.getElementById("pageCount").value;
	var param = {"employeeNum" : _enum,"employeeName" : _ename,"departmentID" : _dept,"attendenceStatus":_status,"leaveType" : _type, "pageNum" : _pageNum, "pageCount" : _pageCount};
	$("#leaveInfoDiv").load("leaveInfoRefresh.action",param);
}
function leaveDel(){
	var id = document.getElementById("mp2007Seq").value;
	var _enum = document.getElementById("employeeNum").value;
	var _ename = document.getElementById("employeeName").value;
	var _dept = document.getElementById("departmentID").value;
	var _status =document.getElementById("attendenceStatus").value;
	var _type = document.getElementById("leaveType").value;
	var msg = "Are you sure you want to delete this record?\n\n"; 
	if (confirm(msg)==true){
		var _pageNum = document.getElementById("pageNum").value;
		var param = {"mp2007Seq" : id,"pageNum" : _pageNum ,"employeeNum" : _enum,"employeeName" : _ename,"departmentID" : _dept,"attendenceStatus":_status,"leaveType" : _type};
		$("#leaveInfoDiv").load("leaveInfoDel.action",param);
		return true; 
	}else{ 
	    return false; 
	}
}
</script>
<script type="text/javascript">
var mouseX;
var mouseY;
var mouseFlag = false;
$(document).mousemove( function(e) {
   mouseX = e.pageX + 10;
   mouseY = e.pageY;
});
function rowClick(id){
	//document.getElementById("testDiv").value = document.getElementById("leaveInfoDiv").innerHtml;
	// event.button 1: left button click 2:right button click
	if(event.button == 2){
/* 		document.getElementById("menuBar").style.display = 'block';
		document.getElementById("menuBar").style.position = 'absolute';
		document.getElementById("menuBar").style.left = mouseX;
		document.getElementById("menuBar").style.top = mouseY; */
	}else if(event.button == 1){
		var _oldIndex = document.getElementById("rowIndex").value;

		if(document.getElementById(_oldIndex) != null){
			document.getElementById(_oldIndex).style.backgroundColor = "#B5C7E3";
		}
		if(document.getElementById(id) != null){
			document.getElementById(id).style.backgroundColor = "#666666";
		}

		document.getElementById("rowIndex").value = id;
		document.getElementById("mp2007Seq").value = id;
	}
}
function ApprovalLeave(id,status){
	var _optApproval = document.getElementById("optApproval").value;
	var _optEdit = document.getElementById("optEdit").value;
	var _optDel = document.getElementById("optDel").value;

	document.getElementById("mp2007Seq").value = id;
	document.getElementById("menuBar").style.display = 'block';
	document.getElementById("menuBar").style.position = 'absolute';
	document.getElementById("menuBar").style.left = mouseX;
	document.getElementById("menuBar").style.top = mouseY;
	
	document.getElementById("dtApprove").disabled = true;
	document.getElementById("dtDecline").disabled = true;
	document.getElementById("dtModify").disabled = true;
	document.getElementById("dtDel").disabled = true;
	if(status == "1"){
		if(_optApproval == '1'){
			document.getElementById("dtApprove").disabled = false;
			document.getElementById("dtDecline").disabled = false;
		}
		if(_optEdit == '1'){
			document.getElementById("dtModify").disabled = false;
		}
		if(_optDel == '1'){
			document.getElementById("dtDel").disabled = false;
		}
	}
}
function mouseElement(){  
    var p = document.getElementById("menuBar");
    p.onmouseout = showMouseOut;
    p.onclick = onmouseClick;
    p.onmousedown = onmouseDown;
    p.onmouseup = onmouseUp;
};
function onmouseDown(){
	mouseFlag = true;
}
function onmouseUp(){
	mouseFlag = false;
}
function showMouseOut(){
	//document.getElementById("menuBar").style.display = 'none';
}
function onmouseClick(){
	document.getElementById("menuBar").style.display = 'none';
}
function Approved(){
	var _pageNum = document.getElementById("pageNum").value;
	var seq = document.getElementById("mp2007Seq").value;
	var _enum = $("#employeeNum").val();
	var _ename = $("#employeeName").val();
	var _dept = $("#departmentID").val();
	var _status =$("#attendenceStatus").val();
	var _type = $("#leaveType").val();
	var param = {"mp2007Seq" : seq,"pageNum" : _pageNum,"employeeNum" : _enum,"employeeName" : _ename,"leaveType" : _type,"departmentID" : _dept,"attendenceStatus":_status};
	$("#leaveInfoDiv").load("ApproveLeave.action",param);	
}
function Declined(){
	var _pageNum = document.getElementById("pageNum").value;
	var seq = document.getElementById("mp2007Seq").value;
	var _enum = $("#employeeNum").val();
	var _ename = $("#employeeName").val();
	var _dept = $("#departmentID").val();
	var _status =$("#attendenceStatus").val();
	var _type = $("#leaveType").val();
	var param = {"mp2007Seq" : seq,"pageNum" : _pageNum,"employeeNum" : _enum,"employeeName" : _ename,"leaveType" : _type,"departmentID" : _dept,"attendenceStatus":_status};
	$("#leaveInfoDiv").load("DeclineLeave.action",param);
}
</script>

</head>
<body onload="mouseElement();">
<form name="form2" action="leaveInfoSearch.action" method="post">
<input id="rowIndex" name="rowIndex" value="1" type="hidden" />
<input id="mp2007Seq" name="mp2007Seq" value="${mp2007Seq}" type="hidden" />
<input id="pageCount" name="pageCount" value="${pageCount}" type="hidden" />
<input id="pageNum" name="pageNum" value="${pageNum}" type="hidden" />
<input id="mp2007Status" value="" type="hidden" />
<input id="optAdd" name="optAdd" value="${optAdd}" type="hidden" />
<input id="optDel" name="optDel" value="${optDel}" type="hidden" />
<input id="optEdit" name="optEdit" value="${optEdit}" type="hidden" />
<input id="optApproval" name="optApproval" value="${optApproval}" type="hidden" />
<input id="optSearch" name="optSearch" value="${optSearch}" type="hidden" />
<input id="optView" name="optView" value="${optView}" type="hidden" />
<input id="optAll" name="optAll" value="${optAll}" type="hidden" />
<input id="optDepartment" name="optDepartment" value="${optDepartment}" type="hidden" />
<input id="optPersonal" name="optPersonal" value="${optPersonal}" type="hidden" />

<div id="menuBar" style="display:none;border:solid 1px #999999;background-color:#E9EFF6;width:102px;height:100px;" onmouseover="">
	<dl>
		<dt></dt>
		<dd id="dtApprove" onclick="Approved()"><span style="margin-left:5px;">Approve</span></dd>
		<dt></dt>
		<dd id="dtDecline" onclick="Declined()" style="border-bottom:1px solid #E0E0E0;"><span style="margin-left:5px;">Decline</span></dd>
		
		<dt></dt>
		<dd id="dtModify" onclick="Editleave()"><span style="margin-left:5px;">Modify</span></dd>
		<dt></dt>
		<dd id="dtDel" onclick="leaveDel()" style="border-bottom:1px solid #E0E0E0;"><span style="margin-left:5px;">Delete</span></dd>
		
		<dt></dt>
		<dd onclick="document.getElementById('menuBar').style.display = 'none';"><span style="margin-left:5px;">Close</span></dd>
	</dl>
</div>
 
<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
  <tr>
    <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Leave Management</td>
    <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
  </tr>
  <tr>
    <td height='27px' class='menubar_function_text'>Operation Function: Vacation Increase Management</td>
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
        <td class="table_body table_body_NoWidth">Employee Number:</td>
        <td class="table_none table_none_NoWidth">
            <input id="employeeNum" name="employeeNum" value="${employeeNum}" type="text" class="text_input" />
        </td>
        <td class="table_body table_body_NoWidth">Employee Name:</td>
        <td class="table_none table_none_NoWidth">
            <input id="employeeName" name="employeeName" value="${employeeName}" type="text" class="text_input" />
        </td>
    </tr>
    <tr>
        <td class="table_body table_body_NoWidth">Department Number:</td>
        <td class="table_none table_none_NoWidth"><s:select  id="departmentID" name="departmentID" name="departmentID"  list="departmentInfoList" theme="simple"/></td>
        <td class="table_body table_body_NoWidth">Type of Leave:</td>
        <td class="table_none table_none_NoWidth"><s:select  id="leaveType" name="leaveType" name="leaveType"  list="leaveTypeList" theme="simple"/></td>
    </tr>
     <tr>
        <td class="table_body table_body_NoWidth">Status of Leave:</td>
        <td class="table_none table_none_NoWidth"><s:select  id="attendenceStatus" name="attendenceStatus" name="attendenceStatus"  list="leaveStatusList" theme="simple"/></td>
    </tr>
    <tr>                                 
        <td colspan="6" align="right"> 
           
            <input id="Add" name="Add" type="button" value="Add Leave" onclick="openAddWindow()"/>
            <input type="submit" name="searchBtn" value="Search" id="searchBtn" />
             <input id="refreshData" name="refreshData" type="button" value="" onclick="refresh()" style="display:none;"/>
            
        </td>
    </tr>           
    <tr>
        <td colspan="4" align="right"></td>
    </tr>
</table>
</fieldset>
           
<div id="leaveInfoDiv">
<table id="tb1" class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
    <tr class="table_title" align="center">
        <th scope="col"></th>
        <th scope="col">ID</th>
        <th scope="col">Employee Number</th>
        <th scope="col">Employee Name</th>
        <th scope="col">Department Name</th>
         <th scope="col">Days(H)</th>
         <th scope="col">Status</th>
        <th scope="col">Type</th>
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




<div id="testDiv">


</div>






</form>
</body>
</html>
