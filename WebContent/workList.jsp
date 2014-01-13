<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新建员工档案</title>
</head>

<body bgColor="#FFFFFF" topMargin="5">

	<table id="workList" width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
		<tr class="table_title" align="center">
            <th scope="col">Company Name</th>
            <th scope="col">Department</th>
            <th scope="col">Position</th>
            <th scope="col">Date of Entry</th>
            <th scope="col">Date of Termination</th>
            <th scope="col">Job Description</th>
            <th scope="col">Reason of Termination</th>
            <th scope="col">Contact Person Information</th>
            <th scope="col" width="50px">-</th>
		</tr>
<s:iterator value="companyInfoList" status="st">
<s:if test="companyInfoList != null">
		<tr class="row" align="center" style="height:28px;">
            <td><s:property value="MP1003_COMPANY_NAME"></s:property></td>
            <td><s:property value="MP1003_DEPARTMENT_ID"></s:property></td>
            <td><s:property value="MP1003_POSITION"></s:property></td>
            <td><s:property value="MP1003_ENTRY_DATETIME"></s:property></td>
            <td><s:property value="MP1003_TERMINATION_DATETIME"></s:property></td>
            <td><s:property value="MP1003_JOB_DESC"></s:property></td>
            <td><s:property value="MP1003_TERMINATION_REASON"></s:property></td>
            <td><s:property value="MP1003_CONTACT_PERSON_INFO"></s:property></td>
            <td width="100px">
                <input type="button" onclick="expEdit(<s:property value="MP1003_SEQ"/>)" name="expEditBtn" value="Edit" id="expEditBtn" />
                <input type="button" onclick="expDel(<s:property value="MP1003_SEQ"/>)" name="expDelBtn" value="Del" id="expDelBtn" />
            </td>
		</tr>
</s:if>
</s:iterator>
	</table>
	
</body>
</html>