<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<title></title>
</head>
<body>

<div style="border:1px solid black;">

<s:select id="leaveType" name="mp2001.MP2001_LEAVE_TYPE" list="leaveTypeList" onchange="showLeftDays()" theme="simple" disabled="true"/>



</div>

</body>
</html>