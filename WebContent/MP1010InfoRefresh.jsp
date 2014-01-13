<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
</head>
<body>

<table cellspacing="0" cellpadding="0" style="border:0;width:100%;">
    <tr align="center" style="background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px;height:20px;">
        <th scope="col" class="tdBg">Seq</th>
        <th scope="col" class="tdBg">Employee Number</th>
        <th scope="col" class="tdBg">Employee Name</th>
        <th scope="col" class="tdBg">ID</th>
        <th scope="col" class="tdBg">Department</th>
        <th scope="col" class="tdBg">Gender</th>
    </tr>
<s:iterator value="mp1010InfoList" status="st">
    <tr id="<s:property value="MP1010_EMPLOYEE_NUM"/>" align="left" style="height:28px;" onmousedown="rowClick('<s:property value="MP1010_EMPLOYEE_NUM"/>')">
        <td width="50px" align="center" class="tdBorder">${st.index + 1}</td>
        <td class="tdBorder"><s:property value="MP1010_EMPLOYEE_NUM"/></td>
        <td class="tdBorder"><s:property value="MP1010_PREFERED_NAME"/></td>
        <td class="tdBorder"><s:property value="MP1010_EMPLOYEE_ID"/></td>
        
        <td class="tdBorder"><s:property value="MP1010_DEPARTMENT_NAME"/></td>
        <td class="tdBorder"><s:property value="MP1010_GENDER_NAME"/></td>
    </tr>
</s:iterator>
</table>

</body>
</html>

