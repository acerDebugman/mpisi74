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

<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery1.4.2.js" type="text/javascript"></script>

<script type="text/javascript">
function chart(obj){
	var _type = document.getElementById("chartId");
	var _depObj = document.getElementById("departmentId");

	if(obj == "line"){
		var _depId = _depObj.value;
		_type.src="budgetChartReportLine.action?departmentId=" + _depId;
		_depObj.style.display = "block";
	}else if(obj == "bar"){
		_type.src="budgetChartReportBar.action";
		_depObj.style.display = "none";
	}else if(obj == "bar3D"){
		_type.src="budgetChartReportBar.action?pageType=3D";
		_depObj.style.display = "none";
	}
}
function departmentChange(){
	var _type = document.getElementById("chartId");
	var _depId = document.getElementById("departmentId").value;
	
	_type.src="budgetChartReportLine.action?departmentId=" + _depId;
}
</script>
</head>
<body>
<form method="post" action="">
<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
  <tr>
    <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Budget Management</td>
    <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
  </tr>
  <tr>
    <td height='27px' class='menubar_function_text'>Operation Function: Budget Addition Management</td>
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

<div style="border:solid 0px red;float:left">
    <input id="radio1" name="selectGroup" type="radio" onclick="chart('line')" value="" checked="checked"/>Line&nbsp;&nbsp;
    <input id="radio3" name="selectGroup" type="radio" onclick="chart('bar')" value="" />Bar&nbsp;&nbsp;
    <input id="radio4" name="selectGroup" type="radio" onclick="chart('bar3D')" value=""/>Bar(3D)&nbsp;&nbsp;
</div>

<div id="departmentDiv" style="border:solid 0px blue;float:left">
    <s:select id="departmentId" name="departmentId" value="departmentId" list="departmentInfoList" onchange="departmentChange()" theme="simple"/>
</div>

<div style="float:left;width:100%;border:solid 0px black;">
    <img id="chartId" alt="jfreechart" src="budgetChartReportLine.action" /> 
</div>

</form>
</body>
</html>