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


<title>test</title>
<script type="text/javascript">
$(document).ready(function(){
	
});
function downloadMonthlyExcel(){
	//alert("download monthly excel !");
	//var param = {};
	//alert("ajax start");
	/*$.ajax({
		url:"exportShiftWorkExcelTemplate.action",
		dataType:"html",
		success:function(msg){}
	});*/
	var options = {
		url : "exportShiftWorkExcelTemplate.action",
		type : "post",
		dataType : "html",
		success : function(msg){}
	};
	$("#form0").ajaxSubmit(options);
	//alert("ajax end");
}
</script>
</head>
<body>

<form id="form0" name="form0" enctype="multipart/form-data">
	<input type="button" value="Export Month Excel" onclick="downloadMonthlyExcel()"/>
</form>

<form method="POST" enctype="multipart/form-data" action="">
  File to upload: <input type="file" name="upfile"><br/>
  Notes about the file: <input type="text" name="note"><br/>
  <br/>
  <input type="submit" value="Press"> to upload the file!
</form>

</body>
</html>
