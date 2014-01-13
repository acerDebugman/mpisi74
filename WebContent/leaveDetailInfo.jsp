<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
</head>
<body>

<table id="tb1" class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
    <tr class="table_title" align="center">
        <th scope="col"></th>
        <th scope="col">ID</th>
        <th scope="col">Employee Number</th>
        <th scope="col">Employee Name</th>
        <th scope="col">Department Name</th>
         <th scope="col">Days(H)</th>
         <th scope="col">Status</th>
        <th scope="col">Type</th>
        
    </tr>
<s:iterator value="mp2007InfoList" status="st">
    <tr id="<s:property value="MP2007_SEQ"/>" class="row" align="center" style="height:28px;" onmousedown="rowClick('<s:property value="MP2007_SEQ"/>')">
        <td width="30px">
            <input id="Approval" name="Approval" type="button" value="▼" style="width:30px;border:none; background-color:transparent" onclick="ApprovalLeave('<s:property value="MP2007_SEQ"/>','<s:property value="MP2007_STATUS"/>')"/>
        </td>
        <td width="30px">${st.index+1}</td>
        <td width="150px"><s:property value="MP2007_EMPLOYEE_NUM"/></td>
        <td><s:property value="MP2007_EMPLOYEE_NAME"/></td>
        <td width="200px"><s:property value="MP2007_DEPARTMENT_NAME"/></td>
        <td width=100px"><s:property value="MP2007_DAYS"/></td>
<s:if test="MP2007_STATUS == 1" >
        <td style="color:blue;width:100px;"><s:property value="MP2007_STATUS_NAME"></s:property></td>
</s:if>
<s:elseif test="MP2007_STATUS == 2" >
        <td style="color:green;width:100px;"><s:property value="MP2007_STATUS_NAME"></s:property></td>
</s:elseif>
<s:elseif test="MP2007_STATUS == 3" >
        <td style="color:red;width:100px;"><s:property value="MP2007_STATUS_NAME"></s:property></td>
</s:elseif>
        <td width="100px"><s:property value="MP2007_TYPE_NAME"/></td>
    </tr>
    
</s:iterator>
</table>

</body>
</html>

<script type="text/javascript">
var id = document.getElementById("mp2007Seq").value;
document.getElementById(id).style.backgroundColor = '#666666';
</script>