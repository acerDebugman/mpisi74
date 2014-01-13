<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>struts2 file upload</title>
</head>
<body>
    <s:form action="fileUpload" method="post" enctype="multipart/form-data">
        <s:file name="myFile" label="Image File"></s:file>
        <s:textfield name="caption" label="Caption"></s:textfield>
        <s:submit/>
    </s:form>
</body>
</html>
