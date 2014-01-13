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
	form2.action = "employeeContactAdd.action";
	form2.submit();
	
	window.dialogArguments.document.getElementById("refreshCon").click();
	window.close();
}
</script>

</head>

<body bgColor="#FFFFFF" topMargin="5" theme="simple">
<form id="form2" name="form2" action="" method="post">
<input type="hidden" id="employeeNum" name="employeeNum" value="${employeeNum}"/>
<input type="hidden" id="MP1005_SEQ" name="mp1005.MP1005_SEQ" value="${mp1005.MP1005_SEQ}"/>
    <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" style="margin-top:20px;">
        <tr>
            <td class="" style="font-size:16px;font-weight:bold;" align="center" colspan="4">Work Experience Information</td>
        </tr>
        <tr>
            <td class="table_body table_body_NoWidth">Name</td>
            <td class="table_none table_none_NoWidth">
                <input name="mp1005.MP1005_NAME" value="${mp1005.MP1005_NAME}" type="text" class="text_input" style="width:99%;"/>
            </td>
            <td class="table_body table_body_NoWidth">Telephone</td>
            <td class="table_none table_none_NoWidth">
                <input name="mp1005.MP1005_TELEPHONE" value="${mp1005.MP1005_TELEPHONE}" type="text" class="text_input" style="width:99%;"/>
            </td>
        </tr>
        <tr>
            <td class="table_body table_body_NoWidth">Email Address</td>
            <td class="table_none table_none_NoWidth">
                <input name="mp1005.MP1005_EMAIL" value="${mp1005.MP1005_EMAIL}" type="text" class="text_input" style="width:99%;"/>
            </td>
            <td class="table_body table_body_NoWidth">Relationship</td>
            <td class="table_none table_none_NoWidth">
                <input name="mp1005.MP1005_RELATIONSHIP" value="${mp1005.MP1005_RELATIONSHIP}" type="text" class="text_input" style="width:99%;"/>
         </tr>
         <tr>
            <td class="table_body table_body_NoWidth">Physical Address</td>
            <td class="table_none table_none_NoWidth" COLSPAN="3">
                <input name="mp1005.MP1005_PHYSICAL_ADDRESS" value="${mp1005.MP1005_PHYSICAL_ADDRESS}" type="text" class="text_input" style="width:99.9%;height:60px;"/>
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