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

<script src="js/jquery1.4.2.js" type="text/javascript" ></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script src="js/jquery.pager.js" type="text/javascript"></script>

<title></title>
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
    
	var empCode = $("#employeeCode").val();
	var wtpId = $("#selectWorkTimePatternId").val();

	var param = {'employeeCode' : empCode,
				 'workTimePatternId' : wtpId,
				 'currentPageNum' : pageclickednumber
				 };
    
    document.getElementById("pageNum").value = pageclickednumber;
    $('#employeeWorkTimePatternList').load('employeeWorkTimePattern_R_Search.action', param);
}
</script>

<script type="text/javascript">
function searchSubmit(){
	var empCode = $("#employeeCode").val();
	var wtpId = $("#selectWorkTimePatternId").val();
	
	var param = {'employeeCode' : empCode,
				 'workTimePatternId' : wtpId
				 };
	
	$('#employeeWorkTimePatternList').load('employeeWorkTimePattern_R_Search.action', param);
}

function employeeWTPedit(id){
	//var popstyle="dialogTop:300px;dialogLeft:600px;dialogRight:600px;help:no;center:yes;dialogHeight:350px;dialogWidth:600px;status:no;resizable:no;scroll:no";
	var popstyle="dialogTop:300px;dialogLeft:350px;help:no;center:yes;dialogHeight:400px;dialogWidth:400px;status:no;resizable:yes;scroll:no;loacation:no;toolbar:no;";
	window.open("employeeWorkTimePattern_R_edit.action?empWorkTimePattern_RId=" + id, "_blank", popstyle, this);
	//window.showModalDialog("employeeWorkTimePattern_R_edit.action?empWorkTimePattern_RId=" + id, window, popstyle);
}
function pageFresh(){
	var empCode = $("#employeeCode").val();
	var wtpId = $("#selectWorkTimePatternId").val();
	
	var pageclickednumber = document.getElementById("pageNum").value;
	
	var param = {'employeeCode' : empCode,
				 'workTimePatternId' : wtpId,
				 'currentPageNum' : pageclickednumber       //here add more one click page button,the best way is to use pageclick function
				 };
	
	$('#employeeWorkTimePatternList').load('employeeWorkTimePattern_R_Search.action', param);
}
</script>


</head>
<body>
<input id="btnRefresh" name="btnRefresh" type="hidden" onclick="pageFresh()" />
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
         <td class="table_body table_body_NoWidth">Employee Number</td>
         <td class="table_none table_none_NoWidth">
         	<input id="employeeCode" name="employeeCode" type="text" class="text_input" />
         </td>
         <td class="table_body table_body_NoWidth">Work Time Pattern</td>
         <td class="table_none table_none_NoWidth">
         	<s:select id="selectWorkTimePatternId" name="workTimePatternId" value="workTimePattern.id" list="workTimePatternList" listKey="id" listValue="name" required="true" headerKey="-1" headerValue="-please select-" theme="simple"/>
         </td>
     </tr>
    <tr>
        <td colspan="4" align="right"></td>
    </tr>
</table>
<table width="100%" border="0" color="red" cellspacing="1" cellpadding="3" align="center">
	<tr>                                 
        <td align="right" width="30%">
        	<input type="button" name="searchBtn" value="Search" id="searchBtn" onclick="searchSubmit()"/>
            <input id="refreshData" name="refreshData" type="button" value="" onclick="refresh()" style="display:none;"/>
        </td>
    </tr>
</table>    
</fieldset>
           
<div id="employeeWorkTimePatternList">
<input id="pageCount" name="pageCount" value="${pageBean.totalPage}" type="hidden" />
<input id="pageNum" name="pageNum" value="${currentPageNum}" type="hidden" />
<table id="tb1" class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
    <tr class="table_title" align="center">
        <th scope="col" width="150px">Employee Number</th>
        <th scope="col" width="150px">Employee Name</th>
        <th scope="col" width="120px">Worktime Pattern</th>
        <th scope="col" width="120px">Start Date</th>
        <th scope="col" width="80px">Initial Circle Day</th>
        <th scope="col" width="80px">Operation</th>
    </tr>

<s:iterator value="empWorktimePatternList" status="st" var="obj">
    <tr id="<s:property value='#obj.id' />" class="row" align="center" style="height:28px;" >
    	<td><s:property value="#obj.employee.MP1001_EMPLOYEE_NUM"/></td>
        <td><s:property value="#obj.employee.MP1001_PREFERED_NAME" /></td>
        <td><s:property value="#obj.workTimePattern.name" /></td>
        <td><s:date name="#obj.startDate" format="yyyy-MM-dd"/></td>
        <td><s:property value="#obj.initialCircleDayIdx" /></td>
        <td><input id="itemEdit" name="itemEdit" value="edit" type="button" onclick="employeeWTPedit(<s:property value='#obj.id' />)"/></td>
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