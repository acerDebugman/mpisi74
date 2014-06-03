<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Apply Leave</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
</head>

<body>
<s:form action="savePassword" method="post" theme="simple">
<input id="optSave" name="optSave" value="${optSave}" type="hidden" />
<input id="optCancel" name="optCancel" value="${optCancel}" type="hidden" />
<input id="changePsdType" name="changePsdType" value="${changePsdType}" type="hidden" />


<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
        <td>
            <!-- 头部菜单 Start -->
            <table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
                <tr>
                    <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;System Management</td>
                    <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
                </tr>
                <tr>
                    <td height='27px' class='menubar_function_text'>Operation Function：Change password</td>
                    <td class='menubar_menu_td' align='right'>
                        <table border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="menubar_button" id="button_0"></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <!-- 头部菜单 End -->
            
            <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" style="padding-bottom:50px;">
                <tr>
                    <td class="table_body">Employee Num<span class="errorcss">*</span></td>
                    <td class="table_none">
                        <input name="MP1001_EMPLOYEE_NUM" value="${MP1001_EMPLOYEE_NUM}" type="text" class="text_input" style="border:solid 0 grey; background-color:transparent" readonly="readonly"/>
                        <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
	                        <s:param>MP1001_EMPLOYEE_NUM</s:param>
	                    </s:fielderror>
                    </td>
                </tr>
                <s:if test="changePsdType != 'loginChange'" >
	                <tr>
	                    <td class="table_body">Old Password<span class="errorcss">*</span></td>
	                    <td class="table_none">
	                        <input name="oldPassword" value="" maxlength="12" type="password" class="text_input" />
	                        <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
		                        <s:param>oldPassword</s:param>
		                    </s:fielderror>
	                    </td>
	                </tr>
                </s:if>
                <tr>
                    <td class="table_body">New Password<span class="errorcss">*</span></td>
                    <td class="table_none">
                        <input name="newPassword1" value="${newPassword1}" maxlength="12" type="password" class="text_input" />
                        <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
	                        <s:param>newPassword1</s:param>
	                    </s:fielderror>
                    </td>
                </tr>
                <tr>
                    <td class="table_body">New Password<span class="errorcss">*</span></td>
                    <td class="table_none">
                        <input name="newPassword2" value="${newPassword2}" maxlength="12" type="password" class="text_input" />
                        <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
	                        <s:param>newPassword2</s:param>
	                    </s:fielderror>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td class="menubar_button" id="button_0" align="center">
<s:if test="optSave==1">
            <s:submit name="save" value="save" align="left" theme="simple"/>
</s:if>
<s:else>
            <s:submit name="save" value="save" align="left" disabled="true" theme="simple"/>
</s:else>
<s:if test="optCancel==1">
            <input type="button" onclick="window.location.href='kpi.jsp'" name="cancel" value="Cancel" />
</s:if>
<s:else>
            <input type="button" onclick="window.location.href='login.action'" name="cancel" disabled="disabled" value="Cancel" />
</s:else>
            
        </td>
    </tr>
</table>
</s:form>
</body>
<s:debug/>
</html>