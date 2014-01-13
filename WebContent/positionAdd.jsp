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

<script type="text/javascript">
var closeWinFunc = window.close;
window.close = function() {
    window.open("", "_self");
    closeWinFunc();
}
</script>

</head>

<body bgColor="#FFFFFF" topMargin="5" theme="simple">
<form id="form2" name="form2" action="positionInfoAdd" method="post">
<input type="hidden" id="MP0009_SEQ" name="mp0009.MP0009_SEQ" value="${mp0009.MP0009_SEQ}"/>
<input type="hidden" id="mp0002Seq" name="mp0002Seq" value="${mp0002Seq}"/>

<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" style="margin-top:20px;">
    <tr>
        <td class="" style="font-size:16px;font-weight:bold;" align="center" colspan="4">Add New Job Title</td>
    </tr>
    <tr>
        <td style="background-color:#B5C7E3;">Job Title</td>
        <td><input id="name" name="mp0009.MP0009_POSITION_NAME_E" value="${mp0009.MP0009_POSITION_NAME_E}" type="text" class="text_input" /></td>
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