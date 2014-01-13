<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
</head>
<body>

<table cellspacing="0" cellpadding="0" style="border:0;width:100%;">
    <tr align="center" style="background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px">
        <th scope="col" class="tdBg">Seq</th>
        <th scope="col" class="tdBg">Title</th>
        <th scope="col" class="tdBg">Department</th>
        <th scope="col" class="tdBg">Year</th>
        <th scope="col" class="tdBg">Month</th>
        <th scope="col" class="tdBg">Comment</th>
        <th scope="col" class="tdBg">Status</th>
        <th scope="col" class="tdBg">&nbsp;</th>
    </tr>
<s:iterator value="examPlanInfoList3" status="st">
    <tr align="left" style="height:28px;">
        <td width="50px" align="center" class="tdBorder">${st.index + 1}</td>
        <td class="tdBorder"><s:property value="MP8002_TITLE"/></td>
        <td class="tdBorder"><s:property value="MP8002_DEPARTMENT_NAME"/></td>
        <td class="tdBorder"><s:property value="MP8002_YEAR"/></td>
        <td class="tdBorder"><s:property value="MP8002_MONTH"/></td>
        <td class="tdBorder"><s:property value="MP8002_COMMENT2"/></td>
        <td width="50px" class="tdBorder"><s:property value="MP8002_STATUS_NAME"/>&nbsp;</td>
        <td width="200px" align="center" class="tdBorder">
            <input class="input0" type="button" onclick="edit('<s:property value="MP8002_SEQ"/>')" name="editBtn" value="Edit" id="editBtn" <s:if test="optEdit == 0" >disabled="disabled"</s:if>/>
            <input class="input0" type="button" onclick="Del('<s:property value="MP8002_SEQ"/>')" name="delBtn" value="Del" id="delBtn" <s:if test="optDel == 0" >disabled="disabled"</s:if>/>
            <input class="input0" type="button" onclick="execute('<s:property value="MP8002_SEQ"/>')" name="selQuestionBtn" value="Execute" id="selQuestionBtn" <s:if test="optExecute == 0" >disabled="disabled"</s:if>/>
        </td>
    </tr>
</s:iterator>
</table>

</body>
</html>