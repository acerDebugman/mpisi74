<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新建员工档案</title>
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
function saveInfo(){
	form2.action = "employeeEducationAdd.action";
	form2.submit();
	
	window.dialogArguments.document.getElementById("refreshEdu").click();
	window.close();
}
</script>

</head>

<body bgColor="#FFFFFF" topMargin="5" theme="simple">
<form id="form2" name="form2" action="" method="post">
<input type="hidden" id="employeeNum" name="employeeNum" value="${employeeNum}"/>
<input type="hidden" id="MP1002_SEQ" name="mp1002.MP1002_SEQ" value="${mp1002.MP1002_SEQ}"/>
    <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" style="margin-top:20px;">
        <tr>
            <td class="" style="font-size:16px;font-weight:bold;" align="center" colspan="4">Education Information</td>
        </tr>
        <tr>
            <td class="table_body table_body_NoWidth">Type of Institution</td>
            <td class="table_none table_none_NoWidth">
                <s:select id="type" name="mp1002.MP1002_INSTITUTION_TYPE" list="institutionTypeList" theme="simple"/>
            </td>
            <td class="table_body table_body_NoWidth">Name of Institution</td>
            <td class="table_none table_none_NoWidth">
                <input id="name" name="mp1002.MP1002_INSTITUTION_NAME" value="${mp1002.MP1002_INSTITUTION_NAME}" type="text" class="text_input" />
            </td>
        </tr>
        <tr>
            <td class="table_body table_body_NoWidth">Level of Qualification</td>
            <td class="table_none table_none_NoWidth">
                <s:select id="level" name="mp1002.MP1002_QUALIFICATION_LEV" list="qualificationLevList" theme="simple"/>
            </td>
            <td class="table_body table_body_NoWidth">Major</td>
            <td class="table_none table_none_NoWidth">
                <input id="major" name="mp1002.MP1002_MAJOR" value="${mp1002.MP1002_MAJOR}" type="text" class="text_input" />
            </td>
        </tr>
        <tr>
            <td class="table_body table_body_NoWidth">Start Date</td>
            <td class="table_none table_none_NoWidth">
                <input id="start" name="mp1002.MP1002_START_DATETIME" value="${mp1002.MP1002_START_DATETIME}" type="text" class="text_input" onfocus="calendar(this);"/>
            </td>
            <td class="table_body table_body_NoWidth">Finish Date</td>
	             <td class="table_none table_none_NoWidth">
                <input id="finish" name="mp1002.MP1002_FINISH_DATETIME" value="${mp1002.MP1002_FINISH_DATETIME}" value="${mp1002.MP1002_FINISH_DATETIME}" type="text" class="text_input" onfocus="calendar(this);"/>
            </td>
        </tr>
        <tr>
            <td colspan="4" align="center">
                <input type="button" id="save" name="save" value="Save" onclick="saveInfo()" />
                <input type="button" id="cancel" name="cancel" value="Cancel" onclick="window.close()" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>