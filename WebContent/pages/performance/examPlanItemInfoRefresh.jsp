<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
</head>
<body>

<table cellspacing="0" cellpadding="0" style="border:0;width:100%;">
    <tr align="center" class="tdBg">
        <th scope="col" class="tdBg">Seq</th>
        <th scope="col" class="tdBg">Item Name</th>
        <th scope="col" class="tdBg">Weightage</th>
        <th scope="col" class="tdBg">&nbsp;</th>
    </tr>
<s:iterator id="list" value="examItemsInfo" status="st">
    <tr align="left" style="height:28px;">
        <td width="50px" align="center">${st.index + 1}<input name="examItemsInfo[<s:property value="#st.index"/>].MP7003_SEQ" value="${list.MP7003_SEQ}" type="hidden"/></td>
        <td><s:property value="MP7003_EXAM_TITLE"/></td>
        <td width="120px"><input name="examItemsInfo[<s:property value="#st.index"/>].MP7003_WEIGHTAGE" value="${list.MP7003_WEIGHTAGE}" type="text"/></td>
        <td width="50px" align="center">
            <input class="input0" type="button" onclick="Del('<s:property value="MP7003_SEQ"/>')" name="delBtn" value="Del" id="delBtn" />
        </td>
    </tr>
</s:iterator>
</table>

</body>
</html>