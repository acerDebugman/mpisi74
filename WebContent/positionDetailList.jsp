<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title></title>
</head>
<body>

<table cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
<s:iterator value="positionDetailList" status="st">
    <tr class="row" align="center" style="height:28px;">
        <td width="50px" align="center">${st.index + 1}</td>
        <td width="500px"><s:property value="MP0009_POSITION_NAME_E"></s:property></td>
        <td width="100px">
<s:if test="optEdit==1">
            <input type="button" onclick="positionEdit(<s:property value="MP0009_SEQ"/>)" name="positionEditBtn" value="Edit" id="positionEditBtn" />
</s:if>
<s:else>
            <input type="button" onclick="positionEdit(<s:property value="MP0009_SEQ"/>)" name="positionEditBtn" value="Edit" id="positionEditBtn" disabled="disabled" />
</s:else>
<s:if test="optDel==1">
            <input type="button" onclick="positionDel(<s:property value="MP0009_SEQ"/>)" name="positionDelBtn" value="Del" id="positionDelBtn" />
</s:if>
<s:else>
            <input type="button" onclick="positionDel(<s:property value="MP0009_SEQ"/>)" name="positionDelBtn" value="Del" id="positionDelBtn" disabled="disabled" />
</s:else>
        </td>
    </tr>
</s:iterator>
</table>

</body>
</html>