<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />

</head>
<body>

<form id="form2">

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
  <tr>
    <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Employee Management</td>
    <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
  </tr>
  <tr>
    <td height='27px' class='menubar_function_text'>Operation Function: Headcount Report</td>
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

<table border='0' cellpadding='0' cellspacing='1' width='600px' align="left">
    <tr>
        <td style="width:300px;" class="table_body">Total Number of Staff</td>
        <td style="width:300px;" class="table_none"><s:property value="mp1001.MP1001_HEADCOUNT_TOTAL"/></td>
    </tr>
    
    <tr>
        <td style="width:300px;" class="table_body">Total Number of Regular Employees</td>
        <td style="width:300px;" class="table_none"><s:property value="mp1001.MP1001_HEADCOUNT_EMPLOYEE"/></td>
    </tr>
    
    <tr>
        <td style="width:300px;" class="table_body">Total Number of Temporary Employees</td>
        <td style="width:300px;" class="table_none"><s:property value="mp1001.MP1001_HEADCOUNT_TMP_EMPLOYEE"/></td>
    </tr>
    
    <tr>
        <td colspan="2">
<div id="departmentSumDiv">
            <table class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;width:600px">
                <tr class="table_title" align="center">
                    <th scope="col" width="200px">Department</th>
                    <th scope="col" width="200px">Headcount</th>
                </tr>
<s:iterator value="depSumList" status="st">
    <s:if test="#st.last == true">
                <tr class="row" align="center" style="height:28px;">
                    <td><s:property value="MP1001_DEPARTMENT_NAME"></s:property></td>
                    <td style="color:red;"><s:property value="MP1001_HEADCOUNT_DEPARTMENT"></s:property></td>
                </tr>
    </s:if>
    <s:else>
                <tr class="row" align="center" style="height:28px;">
                    <td><s:property value="MP1001_DEPARTMENT_NAME"></s:property></td>
                    <td><s:property value="MP1001_HEADCOUNT_DEPARTMENT"></s:property></td>
                </tr>
    </s:else>
</s:iterator>
            </table>
</div>
        </td>
    </tr>
</table>

</form>

</body>
</html>