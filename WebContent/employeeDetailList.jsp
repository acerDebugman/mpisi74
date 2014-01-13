<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>员工详细信息列表</title>
<link rel="stylesheet" type="text/css" href="Style.css">
</head>
  
<body>
<table class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
    <tr class="table_title" align="center">
        <th scope="col">Employee Number</th>
        <th scope="col">Employee Name</th>
        <th scope="col">Visa Type</th>
        <th scope="col">Department</th>
        <th scope="col">Mobile Phone</th>
        <th scope="col">Telephone</th>
        <th scope="col">Email</th>
<s:if test="optEdit == 1 && optView == 1">
                        <th scope="col" colspan="2">&nbsp;</th>
</s:if>
<s:elseif test="optEdit == 1 || optView == 1">
                        <th scope="col">&nbsp;</th>
</s:elseif>
    </tr>
<s:iterator value="employeeInfo" status="st">
    <tr class="row" align="center" style="height:28px;">
        <td><s:property value="MP1001_EMPLOYEE_NUM"></s:property></td>
        <td><s:property value="MP1001_PREFERED_NAME"></s:property> <s:property value="MP1001_SURNAME"></s:property></td>
        <td><s:property value="MP1001_VISA_TYPE"></s:property></td>
        <td><s:property value="MP1001_DEPARTMENT_NAME"></s:property></td>
        <td><s:property value="MP1001_MOBILE_PHONE"></s:property></td>
        <td><s:property value="MP1001_TELEPHONE"></s:property></td>
        <td><s:property value="MP1001_COMPANY_EMAIL"></s:property></td>
<s:if test="optEdit == 1">
        <td width="35px"><a href="employeeEditInit.action?type=Edit&employeeNum=<s:property value="MP1001_EMPLOYEE_NUM"/>&pageNum=<s:property value="CURRENT_PAGE_NUM"/>" >Edit</a></td>
</s:if>
<s:if test="optView == 1">
        <td width="35px"><a href="employeeDisplay.action?employeeNum=<s:property value="MP1001_EMPLOYEE_NUM"/>" >Browse</a></td>
</s:if>
    </tr>
</s:iterator>
</table>
</body>
</html>