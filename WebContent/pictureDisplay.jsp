<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>文件上传成功</title>
<link rel="stylesheet" type="text/css" href="Style.css">
</head>
  
<body>
    <div id="pictureDiv">
        <img src="images/employeePic/<s:property value="fileName"/>"/>
    </div>
</body>

</html>