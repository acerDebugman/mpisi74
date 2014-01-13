<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<base target="_self"/>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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

<form id="form2" name="form2" action="workTimeConfirmSave.action" method="post">

    <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" style="margin-top:20px;">
        <tr>
            <td class="table_body table_body_NoWidth">Employee num</td>
            <td class="table_none table_none_NoWidth">
                <input id="regNum" name="mp2003.MP2003_EMPLOYEE_NUM" value="${mp2003.MP2003_EMPLOYEE_NUM}" type="text" class="text_input" readonly="readonly" style="border:0px;"/>
            </td>
        </tr>
        <tr>
            <td class="table_body table_body_NoWidth">Employee name</td>
            <td class="table_none table_none_NoWidth">
                <input id="vinNum" name="mp2003.MP2003_EMPLOYEE_NAME" value="${mp2003.MP2003_EMPLOYEE_NAME}" type="text" class="text_input" readonly="readonly" style="border:0px;"/>
            </td>
        </tr>
        <tr>
            <td class="table_body table_body_NoWidth">Date</td>
            <td class="table_none table_none_NoWidth">
                <input id="startKm" name="mp2003.MP2003_DATETIME" value="${mp2003.MP2003_DATETIME}" type="text" class="text_input" readonly="readonly" style="border:0px;"/>
            </td>
        </tr>
        <tr>
            <td class="table_body table_body_NoWidth">In</td>
            <td class="table_none table_none_NoWidth">
                <input id="startKm" name="mp2003.MP2003_START_TIME" value="${mp2003.MP2003_START_TIME}" type="text" class="text_input" readonly="readonly" style="border:0px;"/>
            </td>
        </tr>
        <tr>
            <td class="table_body table_body_NoWidth">Location</td>
            <td class="table_none table_none_NoWidth">
                <input id="startKm" name="mp2003.MP2003_START_TIME_DOOR" value="${mp2003.MP2003_START_TIME_DOOR}" type="text" class="text_input" readonly="readonly" style="border:0px;"/>
            </td>
        </tr>
        <tr>
            <td class="table_body table_body_NoWidth">Out</td>
            <td class="table_none table_none_NoWidth">
                <input id="startKm" name="mp2003.MP2003_FINISH_TIME" value="${mp2003.MP2003_FINISH_TIME}" type="text" class="text_input" readonly="readonly" style="border:0px;"/>
            </td>
        </tr>
        <tr>
            <td class="table_body table_body_NoWidth">Location</td>
            <td class="table_none table_none_NoWidth">
                <input id="startKm" name="mp2003.MP2003_FINISH_TIME_DOOR" value="${mp2003.MP2003_FINISH_TIME_DOOR}" type="text" class="text_input" readonly="readonly" style="border:0px;"/>
            </td>
        </tr>
        <tr>
            <td class="table_body table_body_NoWidth">Comment<span class="errorcss">*</span></td>
            <td class="table_none table_none_NoWidth">
                <s:select id="status" name="mp2003.MP2003_STATUS" list="statusList" theme="simple"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" id="save" name="save" value="Save" />
                <input type="button" id="cancel" name="cancel" value="Cancel" onclick="window.close()" />
            </td>
        </tr>
    </table>

</form>
</body>
</html>