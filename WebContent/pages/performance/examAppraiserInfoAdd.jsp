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

<script type="text/javascript">
function selectEmployee(){
	var popstyle="dialogTop:50px;dialogLeft:450px;help:no;center:yes;dialogHeight:750px;dialogWidth:800px;status:no;resizable:no;scroll:no;loacation:no;toolbar:no;";
	window.showModalDialog("employeeSelectInit.action?pageType=performance",window,popstyle);
}
</script>

</head>
<body>
<s:form action="examAppraiserInfoSave" method="post" theme="simple">
<input id="pageType" name="pageType" value="${pageType}" type="hidden" />
<input id="commonSeq" name="commonSeq" value="${commonSeq}" type="hidden" />

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
	<tr>
		<td class='menubar_title' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img border='0'src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Appraiser Setting</td>
		<td class='menubar_readme_text' valign='bottom' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help?</td>
	</tr>
</table>
<!-- 头部菜单 End -->

<table cellpadding="2" cellspacing="1" border="0" width="100%" align="center" style="margin-top:5px;">
	<tr>
		<th colspan=2 style="text-align:center;">Performance Management－－ Appraiser Setting
	        <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
	            <s:param>errMsg</s:param>
	        </s:fielderror>
<!--  	        <s:property value="errMsg"/> -->
		</th>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Employee Number:</td>
		<td	width="74%"	class=td1><s:property value="mp1001.MP1001_EMPLOYEE_NUM"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Employee Name:</td>
		<td	width="74%"	class=td1><s:property value="mp1001.MP1001_PREFERED_NAME"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Appraiser:</td>
		<td	width="74%"	class=td1>
		    <input id="empNum" name="mp1001.MP1001_APPRASIER" value="${mp1001.MP1001_APPRASIER}" type="hidden" />
		    <input id="empName" name="mp1001.MP1001_APPRASIER_NAME" value="${mp1001.MP1001_APPRASIER_NAME}" type="text" style="width:80%;"/>  (Must Fill)
		    <input type="button" onclick="selectEmployee()" value="Please Select" />
	        <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
	            <s:param>mp1001.MP1001_APPRASIER_NAME</s:param>
	        </s:fielderror>
		</td>
	</tr>
	<tr align="center">
		<td	class="td1" colspan="2"><input type="submit" class="button" name="Submit" value="Save"></td>
	</tr>
</table>

</s:form>
</body>
</html>