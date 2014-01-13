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
        <th scope="col">Time</th>
        <th scope="col">Department</th>
        <th scope="col">Applicant</th>
        <th scope="col">Item</th>
        <th scope="col">Amount</th>
        <th scope="col">Status</th>
        <th scope="col">&nbsp;</th>
    </tr>
<s:iterator value="budgetAdditionInfoList" status="st">
    <tr class="row" align="center" style="height:28px;">
        <td width="50px">${st.index + 1}</td>
        <td width="120px"><s:property value="MP4004_MONTH_NAME"/>/<s:property value="MP4004_YEAR"/></td>
        <td width="100px"><s:property value="MP4004_DEPARTMENT_NAME"/></td>
        <td width="150px"><s:property value="MP4004_APPLICANT"/>(<s:property value="MP4004_USER"/>)</td>
        <td><s:property value="MP4004_ACCOUNTING_NUM_NAME"/></td>
        <td width="100px"><s:property value="MP4004_AMOUNT"/></td>

<s:if test="MP4004_STATUS == 1">
        <td width="100px"><s:property value="MP4004_STATUS_NAME"/></td>
</s:if>
<s:if test="MP4004_STATUS == 0">
        <td width="100px" style="color:red;"><s:property value="MP4004_STATUS_NAME"/></td>
</s:if>
<s:if test="MP4004_STATUS == 2">
        <td width="100px" style="color:green;"><s:property value="MP4004_STATUS_NAME"/></td>
</s:if>

        <td width="180px">
<s:if test="MP4004_STATUS == 1">
            <input type="button" onclick="edit('<s:property value="MP4004_SEQ"/>')" name="editBtn" value="Edit" id="editBtn"/>
            <input type="button" onclick="Del('<s:property value="MP4004_SEQ"/>')" name="delBtn" value="Del" id="delBtn"/>
</s:if>
<s:if test="MP4004_STATUS == 1 && optApproval == 1">
            <input type="button" onclick="approve('<s:property value="MP4004_SEQ"/>')" name="delBtn" value="Approve" id="delBtn" />
</s:if>
        </td>
    </tr>
</s:iterator>
</table>

</body>
</html>