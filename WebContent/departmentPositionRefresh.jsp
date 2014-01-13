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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="style/images/ico/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="style/css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="style/css/Site_Css.css" rel="stylesheet" type="text/css" />

</head>
<body>

<table cellspacing="1" border="0" style="background-color:White;border-width:0px;">
<s:iterator id="mp0008InfoList" value="mp0008InfoList" status="st">
   <tr class="row">
       <td width="50px" align="center">${st.index + 1}</td>
       <td width="400px" align="left"><s:property value="MP0008_POSITION_NAME_E"/></td>
   </tr>
</s:iterator>
</table>

</body>
</html>