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
<s:form action="serviceMailSetting" method="post" theme="simple">

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
                    <td height='27px' class='menubar_function_text'>Operation Function: Service Mail Setting</td>
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
                    <td class="table_body">IT<span class="errorcss">*</span></td>
                    <td class="table_none">
                        <input name="itServiceMail" value="${itServiceMail}" type="text" class="text_input" />
                    </td>
                </tr>
                <tr>
                    <td class="table_body">Director<span class="errorcss">*</span></td>
                    <td class="table_none">
                        <input name="directorServiceMail" value="${directorServiceMail}" type="password" class="text_input" />
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td class="menubar_button" id="button_0" align="center">
            <s:submit name="save" value="save" align="left" theme="simple"/>
            <input type="button" onclick="window.location.href='kpi.jsp'" name="cancel" value="Cancel" />
        </td>
    </tr>
</table>
</s:form>
</body>
</html>