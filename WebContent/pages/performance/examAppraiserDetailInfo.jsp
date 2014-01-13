<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
</head>
<body>

<table cellspacing="0" cellpadding="0" style="border:0;width:100%;">
    <tr align="center" style="background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px">
        <th scope="col" class="tdBg">Seq</th>
        <th scope="col" class="tdBg">Number</th>
        <th scope="col" class="tdBg">Name</th>
        <th scope="col" class="tdBg">Department</th>
        <th scope="col" class="tdBg">Job Title</th>
        <th scope="col" class="tdBg">Appraiser</th>
        <th scope="col" class="tdBg">&nbsp;</th>
    </tr>
<s:iterator value="examAppraiserInfoList" status="st">
    <tr align="left" style="height:28px;">
        <td width="50px" align="center" class="tdBorder">${st.index + 1}</td>
        <td class="tdBorder"><s:property value="MP1001_EMPLOYEE_NUM"/></td>
        <td class="tdBorder"><s:property value="MP1001_PREFERED_NAME"/></td>
        <td class="tdBorder"><s:property value="MP1001_DEPARTMENT_NAME"/></td>
        <td class="tdBorder"><s:property value="MP1001_POSITION_NAME"/></td>
        <td class="tdBorder"><s:property value="MP1001_APPRASIER_NAME"/></td>
        <td width="50px" align="center" class="tdBorder">
            <input class="input0" type="button" onclick="edit('<s:property value="MP1001_EMPLOYEE_NUM"/>')" name="editBtn" value="Edit" id="editBtn" <s:if test="optEdit == 0" >disabled="disabled"</s:if>/>
        </td>
    </tr>
</s:iterator>
</table>

</body>
</html>