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
<s:iterator id="holidayInfoList" value="holidayInfoList" status="st">
   <tr class="row">
       <td width="50px" align="center">${st.index + 1}</td>
       <td width="100px" align="left"><s:property value="HOLIDAY_DATE"/></td>
       <td width="300px" align="left"><s:property value="HOLIDAY_NAME"/></td>
       <s:if test="optDel==1">
       <td width="100px" align="center"><input type="button" onclick="Del2('<s:property value="HOLIDAY_SEQ"/>')" name="delBtn" value="Delete" id="delBtn"/></td>
       </s:if>
   </tr>
</s:iterator>
</table>

</body>
</html>