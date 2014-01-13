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
	form2.action = "employeeExperienceAdd.action";
	form2.submit();
	
	window.dialogArguments.document.getElementById("refreshExp").click();
	window.close();
}
</script>

</head>

<body bgColor="#FFFFFF" topMargin="5" theme="simple">
<form id="form2" name="form2" action="" method="post">
<input type="hidden" id="employeeNum" name="employeeNum" value="${employeeNum}"/>
<input type="hidden" id="MP1003_SEQ" name="mp1003.MP1003_SEQ" value="${mp1003.MP1003_SEQ}"/>
    <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" style="margin-top:20px;">
        <tr>
            <td class="" style="font-size:16px;font-weight:bold;" align="center" colspan="4">Work Experience Information</td>
        </tr>
        <tr>
            <td class="table_body table_body_NoWidth">Company Name</td>
            <td class="table_none table_none_NoWidth" colspan="3">
                <input id="companyName" name="mp1003.MP1003_COMPANY_NAME" value="${mp1003.MP1003_COMPANY_NAME}" type="text" class="text_input" style="width:62%"/>
            </td>
        </tr>
        <tr>
            <td class="table_body table_body_NoWidth">Department</td>
            <td class="table_none table_none_NoWidth">
                <input id="department" name="mp1003.MP1003_DEPARTMENT_ID" value="${mp1003.MP1003_DEPARTMENT_ID}" type="text" class="text_input" style="width:99.5%"/>
            </td>
            <td class="table_body table_body_NoWidth">Position</td>
            <td class="table_none table_none_NoWidth">
                <input id="position" name="mp1003.MP1003_POSITION" value="${mp1003.MP1003_POSITION}" type="text" class="text_input" style="width:99.5%"/>
            </td>
        </tr>
        <tr>
            <td class="table_body table_body_NoWidth">Date of Entry</td>
            <td class="table_none table_none_NoWidth">
                <input id="entryDate" name="mp1003.MP1003_ENTRY_DATETIME" value="${mp1003.MP1003_ENTRY_DATETIME}" type="text" class="text_input" onfocus="calendar(this);" style="width:120px"/>
            </td>
            <td class="table_body table_body_NoWidth">Date of Termination</td>
            <td class="table_none table_none_NoWidth">
                <input id="terminationDate" name="mp1003.MP1003_TERMINATION_DATETIME" value="${mp1003.MP1003_TERMINATION_DATETIME}" type="text" class="text_input" onfocus="calendar(this);" style="width:120px"/>
            </td>
        </tr>
        <tr>
            <td class="table_body table_body_NoWidth">Job Description</td>
            <td class="table_none table_none_NoWidth" colspan="3">
                <input id="description" name="mp1003.MP1003_JOB_DESC" value="${mp1003.MP1003_JOB_DESC}" type="text" class="text_input" style="width:99.5%;height:60px;"/>
            </td>
        </tr>
        <tr>
            <td class="table_body table_body_NoWidth">Reason of Termination</td>
            <td class="table_none table_none_NoWidth" colspan="3">
                <input id="reason" name="mp1003.MP1003_TERMINATION_REASON" value="${mp1003.MP1003_TERMINATION_REASON}" type="text" class="text_input" style="width:99.5%;height:60px;"/>
            </td>
        </tr>
        <tr>
            <td class="table_body table_body_NoWidth">Contact Person Information</td>
            <td class="table_none table_none_NoWidth" colspan="3">
                <input id="contact" name="mp1003.MP1003_CONTACT_PERSON_INFO" value="${mp1003.MP1003_CONTACT_PERSON_INFO}" type="text" class="text_input" style="width:99.5%;height:60px;"/>
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