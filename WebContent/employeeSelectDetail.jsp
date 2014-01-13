<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
</head>
<body>

<table class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
    <tr class="table_title" align="center">
        <th scope="col">No.</th>
        <th scope="col">Employee Number</th>
        <th scope="col">Employee Name</th>
        <th scope="col">Department</th>
        <th scope="col">&nbsp;</th>
    </tr>
<s:iterator value="employeeInfo" status="st">
    <tr class="row" align="left" style="height:28px;">
        <td width="50px">${st.index + 1}</td>
        <td><s:property value="MP1001_EMPLOYEE_NUM"/></td>
        <td width="120px"><s:property value="MP1001_PREFERED_NAME"/></td>
        <td width="150px"><s:property value="MP1001_DEPARTMENT_NAME"/></td>
        <td width="50px">
            <input type="button" onclick="selectEmployee('<s:property value="MP1001_EMPLOYEE_NUM"/>','<s:property value="MP1001_PREFERED_NAME"/>')" name="editBtn" value="Select" id="editBtn"/>
        </td>
    </tr>
</s:iterator>
</table>

</body>
</html>