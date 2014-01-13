<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="css/Site_Css.css" type="text/css" />
<link rel="shortcut icon" href="images/other/icon.ico" type="image/x-icon" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />

</head>
<body>

<form method="post" action="serviceRecordSearch.action">

<div id="systemFunctionListDiv" style="border:0px solid blue;">
    <table class="table-box" cellspacing="1" border="0" align="left" style="background-color:White;border-width:0px; height:22px;">
        <tr class="table_title" align="center">
            <th scope="col" width="180px">单据号</th>
            <th scope="col" width="180px">功能名称</th>
            <th scope="col" width="180px">申请人</th>
            <th scope="col" width="">负责人</th>
            <th scope="col" width="">申请部门</th>
            <th scope="col" width="">申请时间</th>
            <th scope="col" width="">预订完成日期</th>
            <th scope="col" width="100px">&nbsp;</th>
        </tr>
	<s:iterator value="systemFunctionList" status="st">
        <tr class="row" align="center" style="height:28px;">
            <td><s:property value="IT0002_NUM"></s:property></td>
            <td><s:property value="IT0002_FUNCTION_NAME"></s:property></td>
            <td><s:property value="IT0002_APPLY_PERSON"></s:property></td>
            <td><s:property value="IT0002_SUPERINTEND"></s:property></td>
            <td><s:property value="IT0002_APPLY_DEPARTMENT"></s:property></td>
            <td><s:property value="IT0002_APPLY_DATE"></s:property></td>
            <td><s:property value="IT0002_SCHEDULED_TIME"></s:property></td>
            <td align="left">
                <input type="button" onclick="serviceRecordDel('<s:property value="IT0001_NUM"/>')" name="serviceRecordDelBtn" value="Del" id="serviceRecordDelBtn" />
                <input type="button" onclick="serviceRecordEdit('<s:property value="IT0001_NUM"/>')" name="serviceRecordEditBtn" value="Edit" id="serviceRecordEditBtn" />
            </td>
        </tr>
	</s:iterator>
    </table>
</div>

</form>

</body>
</html>