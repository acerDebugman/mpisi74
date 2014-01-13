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
        <th scope="col">Leave No.</th>
        <th scope="col">employee num</th>
        <th scope="col">employee name</th>
        <th scope="col">days(H)</th>
        <th scope="col">from date</th>
        <th scope="col">to date</th>
        <th scope="col">acting person</th>
        <th scope="col">type of leave</th>
        <th scope="col">approval</th>
        <th scope="col">&nbsp;</th>
    </tr>
<s:iterator value="leaveInfo" status="st">
    <tr class="row" align="center" style="height:28px;">
<s:if test="optView == 1" >
        <td><a style="text-decoration:underline;cursor:hand;" onclick="showApplyDetailInfo('<s:property value="MP2001_NUM"/>')"><s:property value="MP2001_NUM"></s:property></a></td>
</s:if>
<s:if test="optView == 0" >
        <td><s:property value="MP2001_NUM"/></td>
</s:if>
        <td><s:property value="MP2001_EMPLOYEE_NUM"/></td>
        <td><s:property value="MP2001_EMPLOYEE_NAME"/></td>
        <td><s:property value="MP2001_DAYS"/></td>
        <td><s:property value="MP2001_FROM_DATETIME"/></td>
        <td><s:property value="MP2001_TO_DATETIME"/></td>
        <td><s:property value="MP2001_ACTING_PERSON"/>(<s:property value="MP2001_ACTING_PERSON_NAME"/>)</td>
        <td><s:property value="MP2001_LEAVE_TYPE_NAME"/></td>
<s:if test="MP2001_APPROVAL == 1" >
        <td style="color:blue;"><s:property value="MP2001_APPROVAL_NAME"></s:property></td>
</s:if>
<s:elseif test="MP2001_APPROVAL == 2" >
        <td style="color:green;"><s:property value="MP2001_APPROVAL_NAME"></s:property></td>
</s:elseif>
<s:elseif test="MP2001_APPROVAL == 3" >
        <td style="color:red;"><s:property value="MP2001_APPROVAL_NAME"></s:property></td>
</s:elseif>
        <td width="140px" align="left">
<s:if test="MP2001_APPROVAL == 1 && (MP2001_EMPLOYEE_NUM == loginName || MP2001_ACTING_APPLICATION_PERSON == loginName)" >
    <input type="button" onclick="leaveRecordDel('<s:property value="MP2001_NUM"/>')" name="leaveRecordDelBtn" value="Del" id="leaveRecordDelBtn" />
   <!-- <a href="applyLeaveInit.action?type=edit&MP2001_NUM=<s:property value="MP2001_NUM"/>" >Edit</a> -->
</s:if>

<s:if test="optApproval == 1 && MP2001_APPROVAL == 1 && MP2001_EMPLOYEE_NUM != loginName && MP2001_DEPARTMENT_ID == departmentID || positionLev == 92 || positionLev == 93">
    <input type="button" onclick="leaveRecordApproval('<s:property value="MP2001_NUM"/>')" name="leaveRecordApprovalBtn" value="Approval" id="leaveRecordApprovalBtn" />
</s:if>
<s:if test="MP2001_APPROVAL == 3 && optDel2 == 1" >
    <input type="button" onclick="leaveRecordDel('<s:property value="MP2001_NUM"/>')" name="leaveRecordDelBtn" value="Del" id="leaveRecordDelBtn" />
</s:if>
<s:if test="(MP2001_EMPLOYEE_NUM == loginName || MP2001_ACTING_APPLICATION_PERSON == loginName) && MP2001_LEAVE_TYPE == 2" >
    <input type="button" onclick="uploadFile('<s:property value="MP2001_NUM"/>')" name="uploadBtn" value="Upload File" id="uploadBtn" />
</s:if>
        </td>
    </tr>
</s:iterator>
</table>

</body>

</html>