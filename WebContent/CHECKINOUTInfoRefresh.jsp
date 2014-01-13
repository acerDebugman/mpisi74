<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
</head>
<body>

<table cellspacing="0" cellpadding="0" style="border:0;width:100%;">
    <tr align="center" style="background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px">
        <th scope="col" class="tdBg">Index</th>
        <th scope="col" class="tdBg">User ID</th>
        <th scope="col" class="tdBg">Employee Number</th>
        <th scope="col" class="tdBg">Check Time</th>
        <th scope="col" class="tdBg">Door</th>
    </tr>
<s:iterator value="checkinoutInfoList" status="st">
    <tr align="left" style="height:28px;">
        <td width="50px" align="center" class="tdBorder">${st.index + 1}</td>
        <td class="tdBorder"><s:property value="USERID"/></td>
        <td class="tdBorder"><s:property value="EMPLOYEE_NUM"/>&nbsp;</td>
        <td class="tdBorder"><s:property value="CHECKTIME"/>&nbsp;</td>
        <td width="50px" class="tdBorder"><s:property value="SENSORID"/>&nbsp;</td>
    </tr>
</s:iterator>
</table>

</body>
</html>
