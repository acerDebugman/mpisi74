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

<form name="form2" action="mp1010InfoSave.action" method="post">
<input id="pageType" name="pageType" value="${pageType}" type="hidden" />
<input id="commonSeq" name="commonSeq" value="${commonSeq}" type="hidden" />
<input id="optSave" name="optSave" value="${optSave}" type="hidden" />
<input id="optCancel" name="optCancel" value="${optCancel}" type="hidden" />

<!-- ͷ���˵� Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
	<tr>
		<td class='menubar_title' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img border='0'src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Temporary Employee</td>
		<td class='menubar_readme_text' valign='bottom' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help?</td>
	</tr>
</table>
<!-- ͷ���˵� End -->

<table cellpadding="2" cellspacing="1" border="0" width="100%" align="center" style="margin-top:5px;">
	<tr>
		<td	width="26%"	align="right" class=td1>Employee Number:</td>
        <td	width="74%"	class=td1><input name="mp1010.MP1010_EMPLOYEE_NUM" value="${mp1010.MP1010_EMPLOYEE_NUM}" type="text" style="border:0px;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>First Name:</td>
		<td	width="74%"	class=td1><input name="mp1010.MP1010_FIRST_NAME" value="${mp1010.MP1010_FIRST_NAME}" type="text" style="width:60%;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Surname:</td>
		<td	width="74%"	class=td1><input name="mp1010.MP1010_PREFERED_NAME" value="${mp1010.MP1010_PREFERED_NAME}" type="text" style="width:60%;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Employee ID:</td>
		<td	width="74%"	class=td1><input name="mp1010.MP1010_EMPLOYEE_ID" value="${mp1010.MP1010_EMPLOYEE_ID}" type="text" style="width:30%;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Department:</td>
		<td	width="74%"	class=td1><s:select  id="mp1010.MP1010_DEPARTMENT" name="mp1010.MP1010_DEPARTMENT" list="departmentInfoList" theme="simple"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Gender:</td>
		<td	width="74%"	class=td1><s:select  id="mp1010.MP1010_GENDER" name="mp1010.MP1010_GENDER" list="genderList" theme="simple"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Tel:</td>
		<td	width="74%"	class=td1><input name="mp1010.MP1010_TEL" value="${mp1010.MP1010_TEL}" type="text" style="width:30%;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Nationality:</td>
		<td	width="74%"	class=td1><input name="mp1010.MP1010_NATIONAL" value="${mp1010.MP1010_NATIONAL}" type="text" style="width:30%;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Payroll Number:</td>
		<td	width="74%"	class=td1><input name="mp1010.MP1010_PAYROLL_NUM" value="${mp1010.MP1010_PAYROLL_NUM}" type="text" style="width:30%;"/></td>
	</tr>
	
	<tr align="center">
		<td	colspan="2"	class=td1><input type="submit" class="button" name="Submit" value="Save" /></td>
	</tr>
</table>

</form>
</body>
</html>
