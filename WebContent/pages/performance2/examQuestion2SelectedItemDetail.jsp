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
        <th scope="col" class="tdBg">&nbsp;</th>
    </tr>
<s:iterator value="examPlanInfoList4" status="st">
    <tr align="left" style="height:28px;">
        <td width="50px" align="center">${st.index + 1}</td>
        <td><s:property value="MP8003_EXAM_TITLE"/></td>
        <td width="50px" align="center"><input class="input0" type="button" onclick="Del('<s:property value="MP8003_SEQ"/>','<s:property value="MP8003_EXAM_TITLE"/>')" name="delBtn" value="Del" id="delBtn"/></td>
    </tr>
</s:iterator>
</table>

</body>
</html>