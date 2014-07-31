<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
</head>

<body bgColor="#FFFFFF" topMargin="5" theme="simple">

<s:select name="ewtp_R.initialCircleDayIdx" value="ewtp_R.initialCircleDayIdx" list="workTimePattern.allCircleDays" listKey="daySeq" listValue="name" required="true" headerKey="-1" headerValue="-select pattern first-" theme="simple"/>

</body>
</html>
