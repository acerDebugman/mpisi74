<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />

<script src="js/calendar.js" type="text/javascript" ></script>

<script type="text/javascript">
var closeWinFunc = window.close;
window.close = function() {
    window.open("", "_self");
    closeWinFunc();
}
</script>

</head>

<body bgColor="#FFFFFF" topMargin="5" theme="simple">
<form id="form2" name="form2" action="mp0010InfoSave" method="post">

<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" style="margin-top:20px;">
    <tr>
        <td class="" style="font-size:16px;font-weight:bold;" align="center" colspan="4">Add New Holiday</td>
    </tr>
    <tr>
        <td style="background-color:#B5C7E3;">Date</td>
        <td><input name="mp0010.MP0010_DATETIME" value="${mp0010.MP0010_DATETIME}" type="text" class="text_input" onclick="calendar(this);"/></td>
    </tr>
    <tr>
        <td style="background-color:#B5C7E3;">Start Time</td>
        <td><input name="mp0010.MP0010_START_TIME" value="${mp0010.MP0010_START_TIME}" type="text" class="text_input" />(HH:MM)</td>
    </tr>
    <tr>
        <td style="background-color:#B5C7E3;">Finish Time</td>
        <td><input name="mp0010.MP0010_END_TIME" value="${mp0010.MP0010_END_TIME}" type="text" class="text_input" />(HH:MM)</td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <input type="submit" id="save" name="save" value="Save"/>
            <input type="button" id="cancel" name="cancel" value="Cancel" onclick="window.close()" />
        </td>
    </tr>
</table>

</form>
</body>
</html>