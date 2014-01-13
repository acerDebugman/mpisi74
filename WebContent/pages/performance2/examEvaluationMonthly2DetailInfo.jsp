<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
</head>
<body>

<table cellspacing="0" cellpadding="0" style="border:0;width:100%;">
    <tr align="center" style="background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px">
        <th scope="col" class="tdBg">Seq</th>
        <th scope="col" class="tdBg">Department</th>
        <th scope="col" class="tdBg">Period</th>
        <th scope="col" class="tdBg">Scores</th>
        <th scope="col" class="tdBg">Appraiser</th>
        <th scope="col" class="tdBg">&nbsp;</th>
    </tr>
<s:iterator value="evaluationMonthly2InfoList" status="st">
    <tr align="left" style="height:28px;">
        <td width="50px" align="center" class="tdBorder">${st.index + 1}</td>
        <td class="tdBorder"><s:property value="MP8005_DEPARTMENT_NAME"/></td>
        <td class="tdBorder"><s:property value="MP8005_REVIEW_PERIOD"/>&nbsp;</td>
        <td class="tdBorder"><s:property value="MP8005_SCORES"/>&nbsp;</td>
        <td width="150px" class="tdBorder"><s:property value="MP8005_APPRAISER_NAME"/>&nbsp;</td>
        <td width="100px" align="center" class="tdBorder">
            <input class="input0" type="button" onclick="edit('<s:property value="MP8005_SEQ"/>')" name="editBtn" value="Edit" id="editBtn" <s:if test="optEdit == 0" >disabled="disabled"</s:if>/>
            <input class="input0" type="button" onclick="Del('<s:property value="MP8005_SEQ"/>')" name="delBtn" value="Del" id="delBtn" <s:if test="optDel == 0" >disabled="disabled"</s:if>/>
        </td>
    </tr>
</s:iterator>
</table>

</body>
</html>