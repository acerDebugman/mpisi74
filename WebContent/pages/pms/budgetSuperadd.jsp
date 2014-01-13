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
<meta http-equiv="Expires" CONTENT="0">
<meta http-equiv="Cache-Control" CONTENT="no-cache">
<meta http-equiv="Pragma" CONTENT="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />

<script src="js/jquery1.4.2.js" type="text/javascript"></script>
<script type="text/javascript">
var closeWinFunc = window.close;
window.close = function() {
    window.open("", "_self");
    closeWinFunc();
}
</script>
</head>

<body>
<form method="post" action="superaddBudgetInfoSave.action">
<input id="mp4003Seq" name="mp4003Seq" value="${mp4003Seq}" type="hidden" />
<input id="budgetCode" name="budgetCode" value="${budgetCode}" type="hidden" />
<input id="budgetName" name="budgetName" value="${budgetName}" type="hidden" />

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
  <tr>
    <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Budget Management</td>
    <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
  </tr>
  <tr>
    <td height='27px' class='menubar_function_text'>Operation Function: Superadd the Budget</td>
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

<table cellpadding="2" cellspacing="1" border="0" width="100%" align="center">
	<tr>
		<td	width="26%"	align="right" class=td1>Budget Code:</td>
		<td	width="74%"	class=td1><s:property value="budgetCode"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Budget Name:</td>
        <td	width="74%"	class=td1><s:property value="budgetName"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>Budget Amount:</td>
		<td	width="74%"	class=td1><input name="bugdet" value="${bugdet}" type="text" style="width:80%;"/>(Must Fill)</td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1 height=23>Reason:</td>
		<td	width="74%"	class=td1><s:textarea name="mp4004.MP4004_REASON" style="width:80%;height:100px;" theme="simple"></s:textarea>	(Must Fill)</td>
	</tr>
	<tr align="center">
		<td	colspan="2"	class=td1><input type="submit" class="button" name="Submit" value="Save Budget">	</td>
	</tr>
</table>

</form>
</body>
</html>