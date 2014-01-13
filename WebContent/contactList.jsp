<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新建员工档案</title>
</head>

<body bgColor="#FFFFFF" topMargin="5" theme="simple">

<table id="contactList" width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
    <tr class="table_title" align="center">
        <th scope="col">Name</th>
        <th scope="col">Telephone</th>
        <th scope="col">Email Address</th>
        <th scope="col">Relationship</th>
        <th scope="col">Physical Address</th>
        <th scope="col" width="100px">-</th>
    </tr>
<s:iterator value="contactInfoList" status="st">
<s:if test="contactInfoList != null">
    <tr class="row" align="center" style="height:28px;">
        <td><s:property value="MP1005_NAME"></s:property></td>
        <td><s:property value="MP1005_TELEPHONE"></s:property></td>
        <td><s:property value="MP1005_EMAIL"></s:property></td>
        <td><s:property value="MP1005_RELATIONSHIP"></s:property></td>
        <td><s:property value="MP1005_PHYSICAL_ADDRESS"></s:property></td>
        <td width="100px">
		    <input type="button" onclick="conEdit(<s:property value="MP1005_SEQ"/>)" name="conEditBtn" value="Edit" id="conEditBtn" />
			<input type="button" onclick="conDel(<s:property value="MP1005_SEQ"/>)" name="conDelBtn" value="Del" id="conDelBtn" />
        </td>
    </tr>
</s:if>
</s:iterator>
</table>

</body>
</html>