<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
</head>
<body>

<table cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
    <tr>
        <td colspan="12" style="font-size:14px;color:red;">D: Day  H: Hour</td>
    </tr>
    <tr class="" style="FONT: bold 14px Tahoma, Verdana;COLOR: #ffffff;BACKGROUND-COLOR: #698cc3;HEIGHT: 35px;BACKGROUND: url(images/header_bg.gif) #2f589c repeat-x 0px 0px;PADDING-LEFT: 5px;PADDING-RIGHT:5px;" align="center">
        <th scope="col" rowspan="2" width="100px">Employee Number</th>
        <th scope="col" rowspan="2" width="300px">Employee Name</th>
        <th scope="col" colspan="2" width="200px">Annual</th>
        <th scope="col" colspan="2" width="200px">Sick</th>
        <th scope="col" colspan="2" width="200px">Family Resp</th>
        <th scope="col" colspan="2" width="200px">Maternity</th>
        <th scope="col" colspan="2" width="200px">Study</th>
    </tr>
    <tr class="table_title" align="center">

        <td width="100px">Balance</td><!-- 剩余天数 -->
        <td width="100px">Taken</td><!-- 请假天数天数 -->

        <td width="100px">Balance</td>
        <td width="100px">Taken</td>

        <td width="100px">Balance</td>
        <td width="100px">Taken</td>

        <td width="100px">Balance</td>
        <td width="100px">Taken</td>

        <td width="100px">Balance</td>
        <td width="100px">Taken</td>
    </tr>

<s:iterator value="VacationInfoList" status="st">
    <tr class="row" align="center" style="height:28px;">
        <td width="100px"><s:property value="MP2002_EMPLOYEE_NUM"></s:property></td>
        <td width="300px"><s:property value="MP2002_EMPLOYEE_NAME"></s:property></td>
        
        <td width="100px"><s:property value="MP2002_ANNUAL"></s:property></td>
        <td width="100px"><s:property value="MP2002_ANNUAL_A"></s:property></td>
    
        <td width="100px"><s:property value="MP2002_SICK"></s:property></td>
        <td width="100px"><s:property value="MP2002_SICK_A"></s:property></td>
    
        <td width="100px"><s:property value="MP2002_FAMILY_RESP"></s:property></td>
        <td width="100px"><s:property value="MP2002_FAMILY_RESP_A"></s:property></td>
<s:if test="optEdit == 1" >
        <td width="100px" style="text-decoration:underline;" onclick="eidtMaternity('<s:property value="MP2002_EMPLOYEE_NUM"/>','<s:property value="MP2002_EMPLOYEE_NAME"/>')"><s:property value="MP2002_MATERNITY"/></td>
</s:if>
<s:else>
        <td width="100px"><s:property value="MP2002_MATERNITY"></s:property></td>
</s:else>
        <td width="100px"><s:property value="MP2002_MATERNITY_A"></s:property></td>
<s:if test="optEdit == 1" >
        <td width="100px" style="text-decoration:underline;" onclick="eidtVacation('<s:property value="MP2002_EMPLOYEE_NUM"/>','<s:property value="MP2002_EMPLOYEE_NAME"/>')"><s:property value="MP2002_STUDY"/></td>
</s:if>
<s:else>
        <td width="100px"><s:property value="MP2002_STUDY"/></td>
</s:else>
        <td width="100px"><s:property value="MP2002_STUDY_A"></s:property></td>
    </tr>
</s:iterator>
</table>


</body>
</html>