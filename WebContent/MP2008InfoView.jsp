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
		<td class='menubar_title' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img border='0'src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Overtime Application</td>
		<td class='menubar_readme_text' valign='bottom' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help?</td>
	</tr>
</table>
<!-- ͷ���˵� End -->

<table cellpadding="2" cellspacing="1" border="0" width="100%" align="center" style="margin-top:5px;">
	<tr>
		<td	width="26%"	align="right" class=td1>Employee Name:</td>
		<td	width="74%"	class=td1><s:property value="mp2008.MP2008_EMPLOYEE_NUM"/> <s:property value="mp2008.MP2008_CREATE_USER_NAME"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Department:</td>
		<td	width="74%"	class=td1><s:property value="mp2008.MP2008_DEPARTMENT_NAME"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Clock In:</td>
		<td	width="74%"	class=td1><s:property value="mp2008.MP2008_CLOCK_IN"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Clock Out:</td>
		<td	width="74%"	class=td1><s:property value="mp2008.MP2008_CLOCK_OUT"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Date:</td>
		<td	width="74%"	class=td1><s:property value="mp2008.MP2008_DATE"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>From Time:</td>
		<td	width="74%"	class=td1><s:property value="param1"/>:<s:property value="param3"/> </td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>To Time:</td>
        <td	width="74%"	class=td1><s:property value="param2"/>:<s:property value="param4"/> </td>
	</tr>
 	<tr>
		<td	width="26%"	align="right" class=td1>Hours:</td>
        <td	width="74%"	class=td1><s:property value="mp2008.MP2008_HOURS"/></td>
	</tr>
	
 	<tr>
		<td	width="26%"	align="right" class=td1>Create Time:</td>
        <td	width="74%"	class=td1><s:property value="mp2008.MP2008_CREATE_USER"/> <s:property value="mp2008.MP2008_CREATE_USER_NAME"/> <s:property value="mp2008.MP2008_CREATE_DATETIME"/></td>
	</tr>
 	<tr>
		<td	width="26%"	align="right" class=td1>Edit Time:</td>
        <td	width="74%"	class=td1><s:property value="mp2008.MP2008_EDIT_USER"/> <s:property value="mp2008.MP2008_EDIT_USER_NAME"/> <s:property value="mp2008.MP2008_EDIT_DATETIME"/></td>
	</tr>
 	<tr>
		<td	width="26%"	align="right" class=td1>Approve Time:</td>
        <td	width="74%"	class=td1><s:property value="mp2008.MP2008_APPROVAL_USER"/> <s:property value="mp2008.MP2008_APPROVAL_USER_NAME"/> <s:property value="mp2008.MP2008_APPROVAL_DATETIME"/></td>
	</tr>
	
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>Comment:</td>
		<td	width="74%"	class=td1><s:textarea name="mp2008.MP2008_REASON" style="width:99%;height:100px;" theme="simple"></s:textarea></td>
	</tr>
</table>

</form>
</body>
</html>
