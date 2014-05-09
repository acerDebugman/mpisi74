<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="Style.css">
</head>
  
<body>

<table id="tb1" class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
    <tr class="table_title" align="center">
        <th scope="col">Employee Number</th>
        <th scope="col">Employee Name</th>
        <th scope="col">Date</th>
        <th scope="col">Type</th>
        <th scope="col">Branch Site</th>
    </tr>

<s:iterator value="shiftworkScheduleList" status="st">
    <tr id="<s:property value="MP2010_ID"/>" class="row" align="center" style="height:28px;" >
        <td width="80px"><s:property value="MP2010_EMPLOYEE_NUM" /></td>
        <td width="80px"><s:property value="employeeInfo.MP1001_PREFERED_NAME" /></td>
        <td width="80px"><s:property value="MP2010_DATE.substring(0, 10)" /></td>
        <td width="40px"><s:property value="MP2010_TYPE" /></td>
        <td width="100px"><s:property value="MP2010_BRANCH_SITE" /></td>
    </tr>
</s:iterator>
</table>
</body>

</html>
