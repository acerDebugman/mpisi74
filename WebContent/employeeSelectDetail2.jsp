<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
</head>
<body>

<table class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px;margin-top:0px;">
    <tr class="table_title" align="center">
        <th width="50px">No.</th>
        <th width="150px">Employee Number</th>
        <th>Employee Name</th>
        <th width="150px">Department</th>
        <th width="50px"><input id="all" type="checkbox" onclick="sa()" /></th>
    </tr>
<s:iterator id="info" value="employeeInfo" status="st">
    <tr class="row" align="left" style="height:28px;">
        <td width="50px">${st.index + 1}</td>
        <td width="150px"><s:property value="MP1001_EMPLOYEE_NUM"/></td>
        <td><s:property value="MP1001_PREFERED_NAME"/></td>
        <td width="150px"><s:property value="MP1001_DEPARTMENT_NAME"/></td>
        <td width="50px">
<div class="checkdiv">
    <input id="emp_${st.index + 1}" onclick="cbc('emp_${st.index + 1}','${st.index + 1}')" type="checkbox" name="empArray" value="${MP1001_EMPLOYEE_NUM}" <s:if test="empArray.contains(MP1001_EMPLOYEE_NUM)">checked="checked"</s:if> class="checkbox"/>
</div>
        </td>
    </tr>
</s:iterator>
</table>

</body>
</html>