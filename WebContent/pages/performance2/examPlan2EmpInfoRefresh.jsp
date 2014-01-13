<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
</head>
<body>

<table cellspacing="0" cellpadding="0" style="border:0;width:100%;">
    <tr align="center" class="tdBg">
        <th scope="col" class="tdBg">Seq</th>
        <th scope="col" class="tdBg">Department</th>
        <th scope="col" class="tdBg">&nbsp;</th>
    </tr>
<s:iterator id="list" value="relatedDepartmentListInfo" status="st">
    <tr align="left" style="height:28px;">
        <td width="50px" align="center">${st.index + 1}</td>
        <td><s:property value="MP8004_DEPARTMENT"/></td>
        <td width="50px" align="center">
            <input class="input0" type="button" onclick="DelEmp('<s:property value="MP8004_SEQ"/>')" name="delEmpBtn" value="Del" id="delEmpBtn" />
        </td>
    </tr>
</s:iterator>
</table>

</body>
</html>