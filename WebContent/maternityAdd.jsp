<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
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
<script src="js/jquery.form.js" type="text/javascript" ></script>

</head>

<body>
<form id="form2" name="form2" method="post">
<input type="hidden" id="mp2002EmpNum" name="mp2002EmpNum" value="${mp2002EmpNum}"/>
<input type="hidden" id="mp2002EmpName" name="mp2002EmpName" value="${mp2002EmpName}"/>
<input id="optEdit" name="optEdit" value="${optEdit}" type="hidden" />

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
  <tr>
    <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Vacation Management</td>
    <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
  </tr>
  <tr>
    <td height='27px' class='menubar_function_text'>Operation Function: Add Maternity Leave Days</td>
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
 
<table cellpadding="2" cellspacing="1" width="100%" border="0" align="left">	<tr align="center">
	<tr>
		<td style="background-color:#9AADCD;" width="150px" align="right" class=td1>Employee Number:</td>
		<td	class=td1><s:property value="mp2002EmpNum"/></td>
	</tr>
	<tr>
		<td style="background-color:#9AADCD;" width="150px" align="right" class=td1>Employee Name:</td>
		<td	class=td1><s:property value="mp2002EmpName"/></td>
	</tr>
	<tr>
		<td style="background-color:#9AADCD;" width="150px" align="right" class=td1 height=23>Budget Amount:</td>
		<td	class=td1><input name="bugdet" value="${bugdet}" type="text" style="width:80%;"/>(Must Fill)</td>
	</tr>
	<tr>
		<td style="background-color:#9AADCD;" width="150px" align="right" class=td1 height=23>Reason:</td>
		<td	class=td1><s:textarea id="reason" name="mp4004.MP4004_REASON" style="width:80%;height:100px;" theme="simple"></s:textarea>	(Must Fill)</td>
	</tr>
        <td	colspan="2"	class=td1 align="center">
<s:if test="pageType=='view'">
            <input type="button" onclick="approve('y')" class="button" name="Submit" value="Approve">	
            <input type="button" onclick="approve('n')" class="button" name="Submit" value="Not Approve">
</s:if>
<s:else>
            <input type="button" onclick="saveData()" class="button" name="Submit" value="Save Budget">
</s:else>
        </td>
	</tr>
</table>

</form>
</body>
</html>