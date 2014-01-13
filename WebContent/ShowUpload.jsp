<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>struts2 file upload</title>
</head>
<body>
   <div style ="padding: 3px; border: solid 1px #cccccc; text-align: center" >
        <img src ='UploadImages/<s:property value ="imageFileName" width="50px" height="100px"/>' />
        <br/> 
        < s:property value ="caption" /> 
    </div> 

</body>
</html>