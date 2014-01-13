<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
</head>
<body>

<table cellspacing="0" cellpadding="0" style="border:0;width:100%;">
    <tr align="center" style="background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px;height:20px;">
        <th scope="col" class="tdBg">
            <input id="allSel" onclick="selAll()" type="checkbox" style="height:20px; width:20px; margin-left:-15px; border:1px solid transparent; "/>
        </th>
        <th scope="col" class="tdBg">Seq</th>
        <th scope="col" class="tdBg">Payroll Num</th>
        <th scope="col" class="tdBg">Employee Name</th>
        <th scope="col" class="tdBg">Department</th>        
        <th scope="col" class="tdBg">Date</th>
        <th scope="col" class="tdBg">Clock In</th>
        <th scope="col" class="tdBg">Clock Out</th>
        <th scope="col" class="tdBg">From Time</th>
        <th scope="col" class="tdBg">To Time</th>
        <th scope="col" class="tdBg">Hours</th>
        <th scope="col" class="tdBg">Type</th>
        <th scope="col" class="tdBg">Status</th>
    </tr>
<s:iterator value="mp2008InfoList" status="st">
    <tr id="<s:property value="MP2008_NUM"/>" align="left" style="height:28px;" onmousedown="rowClick('<s:property value="MP2008_NUM"/>','<s:property value="MP2008_EMPLOYEE_NUM"/>','<s:property value="MP2008_APP_STATUS"/>','<s:property value="MP2008_DEPARTMENT"/>')">
        <td class="tdBorder" >
<s:if test="MP2008_APP_STATUS == 3">-</s:if>
<s:elseif test="optAll == 1 && MP2008_APP_STATUS == 2 || optDepartment == 1 && MP2008_APP_STATUS == 1 && departmentID == MP2008_DEPARTMENT ">
            <div class="checkdiv">
                <input id="sa_${st.index + 1}" onclick="" type="checkbox" name="scoreArray5" value="${MP2008_NUM}" class="checkbox" />
            </div>
</s:elseif>
<s:elseif test=" optDepartment == 1 && MP2008_APP_STATUS == 1 && (MP2008_EMPLOYEE_2=='M0076' || MP2008_EMPLOYEE_2=='m0076') && (MP2008_DEPARTMENT==15 || MP2008_DEPARTMENT==21 || MP2008_DEPARTMENT==22 || MP2008_DEPARTMENT==23)">
            <div class="checkdiv">
                <input id="sa_${st.index + 1}" onclick="" type="checkbox" name="scoreArray5" value="${MP2008_NUM}" class="checkbox" />
            </div>
</s:elseif>
<s:else>-</s:else>
        </td>
        <td width="50px" align="center" class="tdBorder">${st.index + 1}</td>
        <td class="tdBorder"><s:property value="MP2008_PAYROLL_NUM"/></td>
        <td class="tdBorder"><s:property value="MP2008_EMPLOYEE_NAME"/></td>
        <td class="tdBorder"><s:property value="MP2008_DEPARTMENT_NAME"/></td>
        <td class="tdBorder"><s:property value="MP2008_DATE"/></td>
        <td class="tdBorder"><s:property value="MP2008_CLOCK_IN"/></td>
        <td class="tdBorder"><s:property value="MP2008_CLOCK_OUT"/></td>
        <td class="tdBorder"><s:property value="MP2008_FROM_DATETIME"/>:<s:property value="MP2008_FROM_MINUTE"/></td>
        <td class="tdBorder"><s:property value="MP2008_TO_DATETIME"/>:<s:property value="MP2008_TO_MINUTE"/></td>
        <td class="tdBorder"><s:property value="MP2008_HOURS"/></td>
        <td class="tdBorder"><s:property value="MP2008_RATING"/></td>
        <td class="tdBorder"><s:property value="MP2008_APP_STATUS_NAME"/></td>
    </tr>
</s:iterator>
</table>

</body>
</html>