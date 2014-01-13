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
        <th scope="col" class="tdBg">Status</th>
        <th scope="col" class="tdBg">&nbsp;</th>
    </tr>
<s:iterator value="examQuestionInfoList" status="st">
    <tr align="left" style="height:28px;">
        <td width="50px" align="center">${st.index + 1}</td>
        <td><s:property value="MP7001_TITLE2"/></td>
        <td width="50px"><s:property value="MP7001_STATUS_NAME"/>&nbsp;</td>
        <td width="50px" align="center">
            <input class="input0" type="button" onclick="sel('<s:property value="MP7001_SEQ"/>','<s:property value="MP7001_TITLE"/>')" name="selBtn" value="Select" id="selBtn"/>
        </td>
    </tr>
</s:iterator>
</table>

</body>
</html>