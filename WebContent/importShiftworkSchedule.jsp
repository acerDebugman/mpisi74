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
<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery1.4.2.js" type="text/javascript" ></script>
<script src="js/jquery.form.js" type="text/javascript" ></script>

<title></title>
<script type="text/javascript">
$(document).ready(function(){
	
});

function uploadFile(){
	var options = {
		url : "uploadExcelFile.action",
		type : "post",
		dataType : "script",
		success : function(msg){
			var rs = msg.substring(0, 1);
			if(rs == "S"){
				alert("Upload success!");
				window.close();
			}
			else{
				alert("Please upload correct file !");
			}
		}
	};
	$("#form1").ajaxSubmit(options);
}
</script>
</head>
<body>
<form id="form1" name="form1" method="POST" enctype="multipart/form-data">
  File to upload: <input type="file" name="shiftworkExcel">
  <br/>
  <input type="button" value="Upload File" onclick="uploadFile()">
</form>
</body>
</html>