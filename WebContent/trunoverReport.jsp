<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />

<script src="js/calendar.js" type="text/javascript" ></script>
<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery1.4.2.js" type="text/javascript" ></script>
<script src="js/jquery.form.js" type="text/javascript" ></script>


</head>
<body>

<form id="form2" name="form2" action="turnoverSearch.action" method="post" enctype="multipart/form-data">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
        <td>
            <!-- 头部菜单 Start -->
            <table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
                <tr>
                    <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Employee Dimission Report</td>
                    <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;Help？</td>
                </tr>
                <tr>
                    <td height='27px' class='menubar_function_text' style="width:35%">Operation Function：Employee Dimission Report</td> 
                    <td class='menubar_menu_td' align='left'></td>
                </tr>
                <tr><td height='5px' colspan='2'></td></tr>
            </table>
            <!-- 头部菜单 End -->
        </td>
    </tr>
    <!-- 检索条件Start -->
    <tr>
        <td>
            <fieldset style="border-color:#999999;border-width:1px;border-style:Solid;margin-bottom:10px;">
            <legend style="color:#333333;font-size:1.5em;font-weight:bold;">Query Condition</legend>
            <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                <tr>
                    <td class="table_body table_body_NoWidth">From Date</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="employeeNum" name="employeeNum" value="${employeeNum}" type="text" class="text_input" onfocus="calendar(this);"/>
                    </td>
                    <td class="table_body table_body_NoWidth">To Date</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="employeeName" name="employeeName" value="${employeeName}" type="text" class="text_input" onfocus="calendar(this);"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="right">
                        <input type="submit" name="searchBtn" value="Search" id="searchBtn" class="" />
                    </td>
                </tr>
            </table>
            </fieldset>
        </td>
    </tr>
    <!-- 检索条件End -->

    <!-- 内容部分Start -->
    <tr>
        <td>
            <table cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
                <tr class="table_title" align="center">
                    <th scope="col" width="200px">Employee Number</th>
                    <th scope="col" width="200px">Employee Name</th>
                    <th scope="col" width="200px">Department Name</th>
                    <th scope="col" width="200px">Date</th>
                </tr>
            </table>
<div id="turnoverInfoList" style="overflow:auto;border:solid 0px red;height:600px;">
            <table cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
<s:iterator value="turnoverInfo" status="st">
                <tr class="row" align="center" style="height:28px;">
                    <td width="200px"><s:property value="MP1001_EMPLOYEE_NUM"/></td>
                    <td width="200px"><s:property value="MP1001_PREFERED_NAME"/></td>
                    <td width="200px"><s:property value="MP1001_DEPARTMENT_NAME"/></td>
                    <td width="200px"><s:property value="MP1001_RESIGN_DATE"/></td>
                </tr>
</s:iterator>
            </table>
</div>
        </td>
    </tr>
    <!-- 内容部分End -->
    
    <tr><td height="5"></td></tr>
</table>

</form>

</body>
</html>