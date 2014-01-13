<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
</head>
<body>

<table class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;width:1300px;">
    <tr class="table_title" align="center">
        <th scope="col">No.</th>
        <th scope="col">System No.</th>
        <th scope="col">Employee Name</th>
        <th scope="col">Department</th>
        <th scope="col">Time</th>
        <th scope="col">IP</th>
        <th scope="col">Memo</th>
    </tr>
<s:iterator value="loginHistoryInfo" status="st">
    <tr class="row" align="center" style="height:28px;">
        <td width="100px">${st.index + 1}</td>
        <td width="100px"><s:property value="MP0011_SYS_CODE"/></td>
        <td width="200px"><s:property value="MP0011_USR_NAME"/>(<s:property value="MP0011_USR_ID"/>)</td>
        <td width="200px"><s:property value="MP0011_DEPARTMENT"/></td>
        <td width="200px" align="left"><s:property value="MP0011_LOGIN_TIME"/></td>
        <td width="180px"><s:property value="MP0011_LOGIN_IP"/></td>
        <td width="100px" align="right"><s:property value="MP0011_MEMO"/></td>
    </tr>
</s:iterator>
</table>

</body>
</html>
</html>