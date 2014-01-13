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
<s:form action="examMonthlyMaxScoresSave" method="post" theme="simple">
<input id="pageType" name="pageType" value="${pageType}" type="hidden" />
<input id="commonSeq" name="commonSeq" value="${commonSeq}" type="hidden" />
<input id="optSave" name="optSave" value="${optSave}" type="hidden" />
<input id="optCancel" name="optCancel" value="${optCancel}" type="hidden" />

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
	<tr>
		<td class='menubar_title' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img border='0'src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;The number of department</td>
		<td class='menubar_readme_text' valign='bottom' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help?</td>
	</tr>
</table>
<!-- 头部菜单 End -->

<table cellpadding="2" cellspacing="1" border="0" width="100%" align="center" style="margin-top:5px;">
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>ACCOUNTING:</td>
		<td	width="74%"	class=td1><input name="DEP_1" value="${DEP_1}" type="text" style="width:80%;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>CHINA TEL:</td>
		<td	width="74%"	class=td1><input name="DEP_2" value="${DEP_2}" type="text" style="width:80%;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>DURBAN:</td>
		<td	width="74%"	class=td1><input name="DEP_3" value="${DEP_3}" type="text" style="width:80%;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>EXPORT:</td>
		<td	width="74%"	class=td1><input name="DEP_5" value="${DEP_5}" type="text" style="width:80%;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>HR:</td>
		<td	width="74%"	class=td1><input name="DEP_6" value="${DEP_6}" type="text" style="width:80%;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>IMPORTS:</td>
		<td	width="74%"	class=td1><input name="DEP_7" value="${DEP_7}" type="text" style="width:80%;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>IT:</td>
		<td	width="74%"	class=td1><input name="DEP_8" value="${DEP_8}" type="text" style="width:80%;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>PHIDISANANG:</td>
		<td	width="74%"	class=td1><input name="DEP_10" value="${DEP_10}" type="text" style="width:80%;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>PRUDENCE:</td>
		<td	width="74%"	class=td1><input name="DEP_11" value="${DEP_11}" type="text" style="width:80%;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>SECURITY:</td>
		<td	width="74%"	class=td1><input name="DEP_12" value="${DEP_12}" type="text" style="width:80%;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>TRANSPORT:</td>
		<td	width="74%"	class=td1><input name="DEP_14" value="${DEP_14}" type="text" style="width:80%;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>LEGAL:</td>
		<td	width="74%"	class=td1><input name="DEP_16" value="${DEP_16}" type="text" style="width:80%;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>PROCUREMENT:</td>
		<td	width="74%"	class=td1><input name="DEP_18" value="${DEP_18}" type="text" style="width:80%;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>MAINTENANCE:</td>
		<td	width="74%"	class=td1><input name="DEP_19" value="${DEP_19}" type="text" style="width:80%;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>WAREHOUSE(SHED10):</td>
		<td	width="74%"	class=td1><input name="DEP_15" value="${DEP_15}" type="text" style="width:80%;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>WAREHOUSE(CITY DEEP):</td>
		<td	width="74%"	class=td1><input name="DEP_21" value="${DEP_21}" type="text" style="width:80%;"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>WAREHOUSE(RDKOOP):</td>
		<td	width="74%"	class=td1><input name="DEP_22" value="${DEP_22}" type="text" style="width:80%;"/></td>
	</tr>
	<tr align="center">
		<td	colspan="2"	class=td1><input type="submit" class="button" name="Submit" value="Save" <s:if test="optSave == 0" >disabled="disabled"</s:if>/></td>
	</tr>
</table>

</s:form>
</body>
</html>