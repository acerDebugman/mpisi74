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
<s:form action="examQuestionInfoSave" method="post" theme="simple">
<input id="pageType" name="pageType" value="${pageType}" type="hidden" />
<input id="commonSeq" name="commonSeq" value="${commonSeq}" type="hidden" />
<input id="optSave" name="optSave" value="${optSave}" type="hidden" />
<input id="optCancel" name="optCancel" value="${optCancel}" type="hidden" />

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
	<tr>
		<td class='menubar_title' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img border='0'src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Add New Appraisal Questionnaire</td>
		<td class='menubar_readme_text' valign='bottom' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help?</td>
	</tr>
</table>
<!-- 头部菜单 End -->

<table cellpadding="2" cellspacing="1" border="0" width="100%" align="center" style="margin-top:5px;">
	<tr>
		<th colspan=2 style="text-align:center;">Appraisal Questionnaire Management －－   Add New Appraisal Questionnaire
	        <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
	            <s:param>errMsg</s:param>
	        </s:fielderror>
<!--  	        <s:property value="errMsg"/> -->
		</th>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Appraisal Questionnaire Name:</td>
		<td	width="74%"	class=td1>
		    <input name="mp7001.MP7001_TITLE" value="${mp7001.MP7001_TITLE}" type="text" style="width:80%;"/>  (Must Fill)
	        <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
	            <s:param>mp7001.MP7001_TITLE</s:param>
	        </s:fielderror>
		</td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Sub Name(Year):</td>
		<td	width="74%"	class=td1><s:textarea name="mp7001.MP7001_SUB_TITLE" style="width:99%;height:100px;" theme="simple"></s:textarea></td>
	</tr>
<s:if test="pageType == 'edit'">
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>Status:</td>
		<td	width="74%"	class=td1><s:select id="commonStatus" name="commonStatus" value="commonStatus" list="commonStatusList" theme="simple"/></td>
	</tr>
</s:if>
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>Comment:</td>
		<td	width="74%"	class=td1><s:textarea name="mp7001.MP7001_COMMENT" style="width:99%;height:200px;" theme="simple"></s:textarea></td>
	</tr>
	<tr align="center">
		<td	colspan="2"	class=td1><input type="submit" class="button" name="Submit" value="Save" <s:if test="optSave == 0" >disabled="disabled"</s:if>/></td>
	</tr>
</table>

</s:form>
</body>
</html>