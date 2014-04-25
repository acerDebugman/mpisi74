<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">

<link rel='stylesheet' type='text/css' href='dist/fullcalendar-1.6.4/fullcalendar/fullcalendar.css' />
<script type='text/javascript' src='dist/fullcalendar-1.6.4/lib/jquery.min.js'></script>
<script type='text/javascript' src='dist/fullcalendar-1.6.4/fullcalendar/fullcalendar.js'></script>
<script type="text/javascript" src="js/jquery.form.js"></script>

<title></title>
<script type="text/javascript">
$(document).ready(function(){
	
});
</script>
</head>
<body>
<form method="POST" enctype="multipart/form-data" action="importShiftworkScheduleExcelInit.action">
  File to upload: <input type="file" name="upfile"><br/>
  Notes about the file: <input type="text" name="note"><br/>
  <br/>
  <input type="submit" value="Press"> to upload the file!
</form>
</body>
</html>