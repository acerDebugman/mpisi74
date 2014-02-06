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

<script type="text/javascript">

</script>
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
<h2>Reset Password</h2>
<h5>please input your employee number</h5>
<s:form class="well" role="form" action="forgetPassword" method="post">
	<center>
	<div class="form-group">
		<div class="row">
			<div class="col-sm-offset-5 col-sm-2">
				<input type="text" class="form-control" id="MP1001_EMPLOYEE_NUM" name="MP1001_EMPLOYEE_NUM" placeholder="Employee Num" required="required" value=""/>
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="row">
			<div class="col-sm-5">
			</div>
		</div>
	</div>
	<button type="submit" class="btn btn-default">Submit</button>
	</center>
</s:form>
<hr style="width:300px;">
<p style="color:#888888">
	IT department & Joe
</p>
</center>
</body>
</html>