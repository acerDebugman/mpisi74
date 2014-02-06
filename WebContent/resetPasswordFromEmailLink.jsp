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
<link href="dist/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<script src="dist/jquery/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="dist/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>

<title>Password Center</title>
</head>
<body>
<center>
<br>
<br>
<br>
<h1>Mpisi74 Human Resource System</h1>
<p style="color:#888888">
	Reset Password Center
</p>
<hr>
<h5>Reset Password Success</h5>
<h5>Please use default password: mpisi123  to login HR system.</h5>
<a href="http://192.168.50.11:8080/mpisi74/">click me to <span style="font:20px;">Home Page</span></a>
</center>
</body>
</html>