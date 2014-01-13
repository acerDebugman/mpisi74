<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
</head>
<body>

<table cellspacing="0" cellpadding="0" style="border:0;width:100%;">
    <tr align="center" class="tdBg">
        <th scope="col" class="tdBg">Seq</th>
        <th scope="col" class="tdBg">Appraisal Questionnaire</th>
        <th scope="col" class="tdBg">&nbsp;</th>
    </tr>
<s:iterator id="list" value="examItemsInfo2" status="st">
    <tr align="left" style="height:28px;">
        <td width="50px" align="center">${st.index + 1}<input name="examItemsInfo[<s:property value="#st.index"/>].MP8003_SEQ" value="${list.MP8003_SEQ}" type="hidden"/></td>
        <td><s:property value="MP8003_EXAM_TITLE"/></td>
        <td width="50px" align="center">
            <input type="button" onclick="Del('<s:property value="MP8003_SEQ"/>')" name="delBtn" value="Del" id="delBtn" />
        </td>
    </tr>
</s:iterator>
</table>

</body>
</html>