<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>员工详细信息列表</title>
<link rel="stylesheet" type="text/css" href="Style.css">
</head>
  
<body>

<table class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;width:1000px">
    <tr class="table_title" align="center">
        <th scope="col" width="150px">Employee num</th>
        <th scope="col" width="200px">Employee name</th>
        <th scope="col" width="150px">Date</th>
        <th scope="col" width="150px">In</th>
		<th scope="col" width="150px">Location</th>
        <th scope="col" width="150px">Out</th>
		<th scope="col" width="150px">Location</th>
        <th scope="col" width="100px">Status</th>
        <th scope="col" width="100px"></th>
    </tr>
<s:iterator value="workingHourRecordList" status="st">
    <tr class="row" align="center" style="height:28px;">
        <td><s:property value="MP2003_EMPLOYEE_NUM"></s:property></td>
        <td><s:property value="MP2003_EMPLOYEE_NAME"></s:property></td>
        <td><s:property value="MP2003_DATETIME"></s:property></td>
        <td><s:property value="MP2003_START_TIME"></s:property></td>
        <td><s:property value="MP2003_START_TIME_DOOR"></s:property></td>
        <td><s:property value="MP2003_FINISH_TIME"></s:property></td>
        <td><s:property value="MP2003_FINISH_TIME_DOOR"></s:property></td>
        <td style="color:red;"><s:property value="MP2003_COMMENT"></s:property></td>
        <td>
<s:if test="MP2003_STATUS==1 &&  optApproval==1 && MP2003_EMPLOYEE_NUM != loginName && MP2003_DEPARTMENT_ID == depID">
            <input type="button" onclick="workTimeConfirm('<s:property value="MP2003_EMPLOYEE_NUM"/>','<s:property value="MP2003_DATETIME"/>')" id="confrimBtn" name="confrimBtn" value="Confirm" />
</s:if>
<s:if test="MP2003_STATUS==1 && roleGroup==3">
            <input type="button" onclick="workTimeConfirm('<s:property value="MP2003_EMPLOYEE_NUM"/>','<s:property value="MP2003_DATETIME"/>')" id="confrimBtn" name="confrimBtn" value="Confirm" />
</s:if>
        </td>
    </tr>
</s:iterator>
</table>
    
</body>

</html>