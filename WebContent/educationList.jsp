<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新建员工档案</title>
</head>

<body bgColor="#FFFFFF" topMargin="5" theme="simple">

	<table id="universityList" width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
        <tr class="table_title" align="center">
            <td scope="col" style="display:none;"></td>
            <td scope="col" style="display:none;"></td>
            <td scope="col">Type of Institution</td>
            <td scope="col">Name of Institution</td>
            <td scope="col">Level of Qualification</td>
            <td scope="col">Major</td>
            <td scope="col">Start Date</td>
            <td scope="col">Finish Date</td>
            <td scope="col" width="50px">-</td>
        </tr>
<s:iterator value="educationInfoList" status="st">
<s:if test="educationInfoList != null">
		<tr class="row" align="center" style="height: 28px;">
            <td style="display:none;"><s:property value="MP1002_INSTITUTION_TYPE"></s:property></td>
            <td style="display:none;"><s:property value="MP1002_QUALIFICATION_LEV"></s:property></td>
            <td><s:property value="MP1002_INSTITUTION_TYPE_NAME"></s:property></td>
            <td><s:property value="MP1002_INSTITUTION_NAME"></s:property></td>
            <td><s:property value="MP1002_QUALIFICATION_LEV_NAME"></s:property></td>
            <td><s:property value="MP1002_MAJOR"></s:property></td>
            <td><s:property value="MP1002_START_DATETIME"></s:property></td>
            <td><s:property value="MP1002_FINISH_DATETIME"></s:property></td>
            <td width="100px">
                <input type="button" onclick="eduEdit(<s:property value="MP1002_SEQ"/>)" name="eduEditBtn" value="Edit" id="eduEditBtn" />
                <input type="button" onclick="eduDel(<s:property value="MP1002_SEQ"/>)" name="eduDelBtn" value="Del" id="eduDelBtn" />
            </td>
		</tr>
</s:if>
</s:iterator>
	</table>

</body>
</html>