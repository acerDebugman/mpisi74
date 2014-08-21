<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>IT service</title>
<link href="css/skins2/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

</script>
</head>
<body>
<h1>test</h1>
<s:form action="convertPdfToText" method="post" theme="simple">
	<s:submit value="convert" />
</s:form>
<hr />
<br />
<br />
<h1>download carina excel</h1>
<s:form action="downloadLateAndEarlyExcel" method="post" theme="simple">
	<s:submit value="down late/early excel" />
</s:form>

<hr />
<s:form action="lateEarlyJobTest" method="post" theme="simple">
	<s:submit value="Late early job execute" />
</s:form>

<s:form action="fetchAttendanceRcd" method="post" theme="simple">
	<s:submit value="10mins fetch date test" />
</s:form>

<s:form action="transportWorkTimePatter" method="post" theme="simple">
	<s:submit value="transport work time pattern" />
</s:form>

<s:form action="transportWorkTimePatter" method="post" theme="simple">
	<s:submit value="transport work time pattern" />
</s:form>
</body>
</html>