<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>员工详细信息列表</title>
<link rel="stylesheet" type="text/css" href="Style.css">
</head>
<body>

<table cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
<s:iterator value="tmpEmployeeDataList" status="st">
    <tr class="row" align="center" style="height:28px;">
        <td width="150px"><s:property value="MP1001_EMPLOYEE_NUM"></s:property></td>
        <td width="200px"><s:property value="MP1001_PREFERED_NAME"></s:property> <s:property value="MP1001_SURNAME"></s:property></td>
        <td width="100px"><s:property value="MP1001_VISA_TYPE"></s:property></td>
        <td width="250px"><s:property value="MP1001_DEPARTMENT_NAME"></s:property></td>
        <td width="100px"><s:property value="MP1001_ENTRY_DATE"></s:property></td>
<s:if test="optApproval == 1" >
        <td align="center" width="100px">
            <input type="button" onclick="approveTmpEmp('<s:property value="MP1001_EMPLOYEE_NUM"/>')" name="tmpEmployeeBtn" value="Approve" id="tmpEmployeeBtn" />
        </td>
</s:if>
    </tr>
</s:iterator>
</table>

</body>
</html>