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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />

<script src="js/calendar.js" type="text/javascript" ></script>
<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery1.4.2.js" type="text/javascript"></script>

<script type="text/javascript">
function s(){
	var param1 = document.getElementById("param1").value;
	var param2 = document.getElementById("param2").value;
	var param3 = document.getElementById("param3").value;
	var _type = document.getElementById("chartId");
	if(param1 == "" || param2 == ""){
		alert("Date cannot be empty");
	}else{
		_type.src="appraisalChartReport2.action?param1=" + param1 + "&param2=" + param2 + "&param3=" + param3;
	}
}
</script>
</head>
<body>
<form method="post" action="">

<div style="float:left;width:100%;border:solid 0px black;margin-bottom:10px;">
    From Date: <input id="param1" name="param1" value="${param1}" type="text" onclick="calendar(this);" />
    To Date: <input id="param2" name="param2" value="${param2}" type="text" onclick="calendar(this);" />
    Department: <s:select id="param3" name="param3" value="param3" list="commonDepartmentList" theme="simple"/>
    <input class="input0" type="button" name="searchBtn" value="Search" id="searchBtn" onclick="s()" />
</div>

<div style="float:left;width:100%;border:solid 0px black;">
    <img id="chartId" alt="jfreechart" src="appraisalChartReport2.action" /> 
</div>

</form>
</body>
</html>