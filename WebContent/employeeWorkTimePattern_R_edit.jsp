<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<base target="_self"/>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<title></title>
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<link href="css/skins2/main.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/calendar.js"></script>
<script src = "js/skins2/Main82.js" type="text/javascript"></script>
<script src="js/jquery1.4.2.js" type="text/javascript" ></script>
<script type="text/javascript" src="js/jquery.form.js"></script>


<style type="text/css">
.tdBg{background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px;color:#FFFFFF;text-align:left;margin:0px;padding:0px;}
.tdBg1{border-bottom:solid black 1px;border-left:solid black 1px;}
.tdBg2{border-bottom:solid black 1px;border-left:solid black 1px;border-right:solid black 1px;}
.tdBg3{border-bottom:solid black 1px;border-left:solid black 1px;background-color:#C4D8ED;color:#000000;}
.tdBg4{border-bottom:solid black 1px;border-left:solid black 1px;border-right:solid black 1px;background-color:#C4D8ED;color:#000000;}
</style>

<script type="text/javascript">

//----------work time pattern------------
//fetch work time pattern circle days
function fetchPatternCircleDayList(){
	var patternId = $("#selectWorkPatternId").val();
	if(-1 == patternId){
		return ;
	}
	var param = {"workTimePatternId" : patternId};
//	alert(patternId);
	$("#patternCircleDayList").load("fetchWorkTimePatternCircleDays.action", param);
}

</script>

</head>
<body>
<s:form action="employeeWorkTimePattern_R_save.action" method="post" theme="simple">
<input type="hidden" name="ewtp_R.id" value="${ewtp_R.id}"  />

<!-- 头部菜单 Start -->
<!-- <table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
	<tr>
		<td class='menubar_title' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img border='0'src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Performance Appraisal Plan Management</td>
		<td class='menubar_readme_text' valign='bottom' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help?</td>
	</tr>
</table> -->
<!-- 头部菜单 End -->

<table cellpadding="0" cellspacing="0" border="0" style="margin-bottom:5px;width:100%;">
	<tr>
		<th colspan=2 style="text-align:center;">Employee Work Time Pattern Edit
	        <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
	            <s:param>errMsg</s:param>
	        </s:fielderror>
<!--  	        <s:property value="errMsg"/> -->
		</th>
	</tr>
</table>

<div id='tabContent__0' style='display:block;'>
	<table cellpadding="2" cellspacing="1" border="0" width="100%" align="center" style="margin-top:5px;">
		<tr>
			<td	width="26%"	align="right" class=td1>Employee Code:</td>
			<td	width="74%"	class=td1>
				<s:property value="ewtp_R.employee.MP1001_EMPLOYEE_NUM" />
			</td>
		</tr>
		<tr>
			<td	width="26%"	align="right" class=td1>Employee Name:</td>
			<td	width="74%"	class=td1>
				<s:property value="ewtp_R.employee.MP1001_PREFERED_NAME" />
			</td>
		</tr>
		<tr>
			<td	width="26%"	align="right" class=td1>Start Date:
			<td	width="74%"	class=td1>
				<%-- <input id="startDate" name="empWTP_RStartDate" value="<s:date name='ewtp_R.startDate' format='yyyy-MM-dd'/>" type="text" class="text_input" onfocus="calendar(this);"/> --%>
				<input name="empWTP_RStartDate" value="${empWTP_RStartDate}" type="text" class="text_input" onfocus="calendar(this);"/>
			</td>
		</tr>
		<tr>
			<td	width="26%"	align="right" class=td1>Work Time Pattern:
			<td	width="74%"	class=td1>
			    <s:select id="selectWorkPatternId" name="workTimePattern.id" value="workTimePattern.id" list="workTimePatternList" listKey="id" listValue="name" required="true" headerKey="-1" headerValue="-please select-" onchange="fetchPatternCircleDayList()" theme="simple"/>
               	<s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
                       <s:param>MP1001.workTimePatternError</s:param>
                </s:fielderror>
			</td>
		</tr>
		<tr>
			<td	width="26%"	align="right" class=td1 height=23>Choose Start Day:</td>
			<td	width="74%"	class=td1>
				<span id="patternCircleDayList">
					<s:select name="ewtp_R.initialCircleDayIdx" value="ewtp_R.initialCircleDayIdx" list="workTimePattern.allCircleDays" listKey="daySeq" listValue="name" required="true" headerKey="-1" headerValue="-select pattern first-" theme="simple"/>
				</span>
			</td>
		</tr>
		
		<tr align="center">
			<td	class="td1" colspan="2">
				<input type="submit" class="button" name="Submit1" value="Save" />
			</td>
		</tr>
	</table>
</div>

<!-- <div style="width:100%;margin-top:10px;" align="center"><input type="submit" class="button" name="Submit" value="Save" <s:if test="optSave == 0" >disabled="disabled"</s:if>/></div> -->

</s:form>
</body>
</html>