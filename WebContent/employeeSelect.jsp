<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base target="_self"/>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />

<script src="js/calendar.js" type="text/javascript"></script>
<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery1.4.2.js" type="text/javascript"></script>
<script src="js/jquery.pager.js" type="text/javascript"></script>

<style type="text/css">
#pager ul.pages {display:block;border:none;text-transform:uppercase;font-size:10px;margin:1px 0 10px;padding:0;}
#pager ul.pages li {list-style:none;float:left;border:1px solid #65AB31;text-decoration:none;margin:0 5px 0 0;padding:5px;}
#pager ul.pages li:hover {border:1px solid #003f7e;}
#pager ul.pages li.pgEmpty {border:1px solid #000000;color:#000000;background-color:grey;}
#pager ul.pages li.pgCurrent {border:1px solid #003f7e;color:#000;font-weight:700;background-color:#65AB31;}
</style>

<script type="text/javascript">
var closeWinFunc = window.close;
window.close = function() {
    window.open("", "_self");
    closeWinFunc();
}
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
    var param1 = $("#param1").val();
    var param2 = $("#param2").val();
    var param3 = $("#param3").val();
    var _pt = $("#pageType").val();
    var param = {"pageNum" : pageclickednumber,"param1" : param1,"param2" : param2,"param3" : param3,"pageType" : _pt};
    document.getElementById("pageNum").value = pageclickednumber;
    $("#employeeInfoListDiv").load("employeeSelectRefresh.action",param);
}
</script>

<script type="text/javascript">
//局部刷新
function t(){
    var param1 = $("#param1").val();
    var param2 = $("#param2").val();
    var param3 = $("#param3").val();
    var _pt = $("pageType").val();
    var _pageNum = document.getElementById("pageNum").value;
    var param = {"pageNum" : _pageNum,"param1" : param1,"param2" : param2,"param3" : param3,"pageType" : _pt};

	$("#employeeInfoListDiv").load("employeeSelectRefresh.action",param);
}
function selectEmployee(param1,param2){
	var _pt = document.getElementById("pageType").value;
	if(_pt != '' && _pt != 'undefined' && _pt == 'performance'){
		window.dialogArguments.document.getElementById('empNum').value = param1;
		window.dialogArguments.document.getElementById('empName').value = param2;
		window.close();
	}else if(_pt != '' && _pt != 'undefined' && _pt == 'appraisalReport'){
		window.dialogArguments.document.getElementById('param4').value = param1;
		window.close();
	}
	else{
		// 设定员工编码
		if(window.dialogArguments.document.getElementById('empNum').value != ''){
			window.dialogArguments.document.getElementById('empNum').value += "," + param1;
		}else{
			window.dialogArguments.document.getElementById('empNum').value = param1;
		}
		// 设定员工名称
		if(window.dialogArguments.document.getElementById('empName').value != ''){
			window.dialogArguments.document.getElementById('empName').value += "," + param2;
		}else{
			window.dialogArguments.document.getElementById('empName').value = param2;
		}
	}
	//window.close();
}
</script>

</head>
<body bgColor="#FFFFFF" topMargin="5">
<form method="post" action="employeeSelectInit.action">
<input id="pageCount" name="pageCount" value="${pageCount}" type="hidden" />
<input id="pageNum" name="pageNum" value="${pageNum}" type="hidden" />
<input id="pageType" name="pageType" value="${pageType}" type="hidden" />

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
  <tr style="border-bottom:2px solid #000000;">
    <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Employee Information</td>
    <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
  </tr>
</table>
<!-- 头部菜单 End -->

<!--检索区域Start-->
<fieldset style="border-color:#999999;border-width:1px;border-style:Solid;margin-bottom:10px;">
<legend style="color:#333333;font-size:1.5em;font-weight:bold;">Query Condition</legend>
<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
    <tr>
        <td class="table_body table_body_NoWidth">Employee Number</td>
        <td class="table_none table_none_NoWidth"><input id="param1" name="param1" value="${param1}" type="text" class="text_input"/></td>
        <td class="table_body table_body_NoWidth">Employee Name</td>
        <td class="table_none table_none_NoWidth"><input id="param2" name="param2" value="${param2}" type="text" class="text_input"/></td>
    </tr>
    <tr>
        <td class="table_body table_body_NoWidth">Department</td>
        <td class="table_none table_none_NoWidth"><s:select id="param3" name="param3" value="param3" list="commonDepartmentList" theme="simple"/></td>
        <td colspan="2" align="right">
            <input type="submit" name="searchBtn" value="Search Employee" id="searchBtn" />
            <input type="button" onclick="t()" name="pageRefresh" value="refresh" id="pageRefresh" style="display:none;"/>
        </td>
    </tr>
</table>
</fieldset>
<!--检索区域End-->

<table class="table-box" cellspacing="1" style="border:0px solid blue;margin-bottom:0px;">
    <tr class="table_title" align="center" style="border:1px solid green;">
        <th width="50px">No.</th>
        <th width="150px">Employee Number</th>
        <th>Employee Name</th>
        <th width="150px">Department</th>
        <th width="50px"><input type="checkbox" /></th>
    </tr>
</table>

<div id="employeeInfoListDiv" style="height:480px;overflow:auto;margin-top:0px;border:0px solid red;">
<table class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px;margin-top:0px;">
<s:iterator value="employeeInfo" status="st">
    <tr class="row" align="left" style="height:28px;">
        <td width="50px">${st.index + 1}</td>
        <td width="150px"><s:property value="MP1001_EMPLOYEE_NUM"/></td>
        <td><s:property value="MP1001_PREFERED_NAME"/></td>
        <td width="150px"><s:property value="MP1001_DEPARTMENT_NAME"/></td>
       
        <td width="50px">
            <input type="button" onclick="selectEmployee('<s:property value="MP1001_EMPLOYEE_NUM"/>','<s:property value="MP1001_PREFERED_NAME"/>')" name="editBtn" value="Select" id="editBtn"/>
        </td>
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
</table>
</form>
</body>
</html>