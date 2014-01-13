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

<script src="js/calendar.js" type="text/javascript" ></script>
<script src = "js/skins2/Main82.js" type="text/javascript"></script>
</head>
<body>

<form name="form2" action="mp2008Save.action" method="post">
<input id="pageType" name="pageType" value="${pageType}" type="hidden" />
<input id="commonSeq" name="commonSeq" value="${commonSeq}" type="hidden" />
<input id="optSave" name="optSave" value="${optSave}" type="hidden" />
<input id="optCancel" name="optCancel" value="${optCancel}" type="hidden" />

<!-- ͷ���˵� Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
	<tr>
		<th colspan=2 style="text-align:center;">Overtime Application
	        <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
	            <s:param>errMsg</s:param>
	        </s:fielderror>
		</th>
	</tr>
	<tr>
		<td class='menubar_title' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img border='0'src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Overtime Application</td>
		<td class='menubar_readme_text' valign='bottom' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help?</td>
	</tr>
</table>
<!-- ͷ���˵� End -->

<table cellpadding="2" cellspacing="1" border="0" width="100%" align="center" style="margin-top:5px;">
	<tr>
		<td	width="26%"	align="right" class=td1>Employee Name:</td>
		<td	width="74%"	class=td1>
		    <input name="mp2008.MP2008_EMPLOYEE_NUM" value="${mp2008.MP2008_EMPLOYEE_NUM}" disabled="disabled" type="text" style="border:0px;"/>  <input name="mp2008.MP2008_EMPLOYEE_NAME" value="${mp2008.MP2008_EMPLOYEE_NAME}" disabled="disabled" type="text" style="border:0px;"/>
		</td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Department:</td>
		<td	width="74%"	class=td1><input name="mp2008.MP2008_DEPARTMENT_NAME" value="${mp2008.MP2008_DEPARTMENT_NAME}" disabled="disabled" type="text" style="border:0px;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Clock In:</td>
		<td	width="74%"	class=td1><input name="mp2008.MP2008_CLOCK_IN" value="${mp2008.MP2008_CLOCK_IN}" disabled="disabled" type="text" style="border:0px;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Clock Out:</td>
		<td	width="74%"	class=td1><input name="mp2008.MP2008_CLOCK_OUT" value="${mp2008.MP2008_CLOCK_OUT}" disabled="disabled" type="text" style="border:0px;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Date:</td>
		<td	width="74%"	class=td1>
		    <input id="dateId" name="mp2008.MP2008_DATE" value="${mp2008.MP2008_DATE}" type="text" onclick="calendar(this);" readonly="readonly" style="width:120px"/>  (Must Fill)
	        <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
	            <s:param>mp2008.MP2008_DATE</s:param>
	        </s:fielderror>
		</td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>From Time:</td>
		<td	width="74%"	class=td1><s:select  id="param1" name="param1"  list="hourInfoList" theme="simple" style="width:120px"/> : <s:select  id="param3" name="param3"  list="workingMinuteList" theme="simple" style="width:120px"/>  (Must Fill)</td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>To Time:</td>
        <td	width="74%"	class=td1><s:select  id="param2" name="param2"  list="hourInfoList" theme="simple" style="width:120px"/> : <s:select  id="param4" name="param4"  list="workingMinuteList" theme="simple" style="width:120px"/>   (Must Fill)</td>
	</tr>
<%-- 	<tr>
		<td	width="26%"	align="right" class=td1>Hours:</td>
        <td	width="74%"	class=td1><input name="mp2008.MP2008_HOURS" value="${mp2008.MP2008_HOURS}" type="text" style="width:120px"/>  (Must Fill)</td>
	</tr> --%>
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>Comment:</td>
		<td	width="74%"	class=td1><s:textarea name="mp2008.MP2008_REASON" style="width:99%;height:100px;" theme="simple"></s:textarea></td>
	</tr>
	<tr align="center">
		<td	colspan="2"	class=td1>
<!-- 		    <input type="submit" name="mp2008InfoSave" method="mp2008InfoSave" class="button" name="Submit" value="Save" />
		    <input type="submit" name="mp2008InfoSave2" method="mp2008InfoSave2" class="button" name="Submit" value="Save & Add New Application" /> -->
		    
		    <s:submit onclick="if(document.getElementById('dateId').value == ''){alert('Date cannot be empty');return false;}" name="mp2008InfoSave" value="Save" method="mp2008InfoSave" theme="simple" />
		    <s:submit onclick="if(document.getElementById('dateId').value == ''){alert('Date cannot be empty');return false;}" name="mp2008InfoSave2" value="Save & Add New Application" method="mp2008InfoSave2" theme="simple" />
		</td>
	</tr>
</table>

</form>
</body>
</html>
