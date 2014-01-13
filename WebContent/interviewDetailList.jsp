<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="Style.css">
</head>
<body>

<table class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
    <tr class="table_title" align="center">
        <th scope="col">Passport Number/ID</th>
        <th scope="col">Name</th>
        <th scope="col">Phone</th>
        <th scope="col">Time</th>
        <th scope="col">Interviewer</th>
        <th scope="col">CV</th>
        <th scope="col">&nbsp;</th>
    </tr>
<s:iterator value="interviewInfo" status="st">
    <tr class="row" align="center" style="height:28px;">
        <td width="200px"><s:property value="MP1008_ID"/></td>
        <td ><s:property value="MP1008_NAME"/></td>
        <td width="200px"><s:property value="MP1008_PHONE"/></td>
        <td width="180px"><s:property value="MP1008_INTERVIEW_DATETIME"/></td>
        <td width="100px"><s:property value="MP1008_INTERVIEWER"/></td>
        <td width="100px"> <a href="<s:property value="MP1008_CV_PATH"/>" >View</a></td>
        <td width="100px" align="left">
            <input type="button" onclick="interviewRecordEdit('<s:property value="MP1008_ID"/>')" name="interviewRecordEditBtn" value="Edit" id="interviewRecordEditBtn" />
            <input type="button" onclick="interviewRecordDel('<s:property value="MP1008_ID"/>')" name="interviewRecordDelBtn" value="Del" id="interviewRecordDelBtn" />
        </td>
    </tr>
</s:iterator>
</table>

</body>
</html>