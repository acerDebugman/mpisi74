<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="Style.css">
</head>
  
<body>

<table class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
    <tr class="table_title" align="center">
        <th scope="col">Employee Num</th>
        <th scope="col">Employee Name</th>
        <th scope="col">Document Name</th>
        <th scope="col">Upload Time</th>
        <th scope="col">Uploader</th>
        <th scope="col">&nbsp;</th>
    </tr>
<s:iterator value="documentInfo" status="st">
    <tr class="row" align="center" style="height:28px;">
        <td width="120px"><s:property value="MP1009_EMPLOYEE_NUM"/></td>
        <td width="200px"><s:property value="MP1009_EMPLOYEE_NAME"/></td>
        <td><a href="documentDownloadEmp.action?fileName=<s:property value='MP1009_PATH'/>" target="download"><s:property value="MP1009_DOCUMENT_NAME"/></a></td>
        <td width="180px"><s:property value="MP1009_UPLOAD_TIME"/></td>
        <td width="100px"><s:property value="MP1009_UPLOADER"/></td>
        <td width="100px" align="left">
            <!-- <input type="button" onclick="documentRecordEdit('<s:property value="MP1009_SEQ"/>')" name="documentRecordEditBtn" value="Edit" id="documentRecordEditBtn" /> -->
            <input type="button" onclick="documentRecordDel('<s:property value="MP1009_SEQ"/>')" name="documentRecordDelBtn" value="Del" id="documentRecordDelBtn" />
        </td>
    </tr>
</s:iterator>
</table>

</body>
</html>