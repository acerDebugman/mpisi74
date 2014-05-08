<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="Style.css">
</head>
  
<body>

<table id="tb1" class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
    <tr class="table_title" align="center">
        <th scope="col">Employee Number</th>
        <th scope="col">Employee Name</th>
        <th scope="col">Date</th>
        <th scope="col">Type</th>
        <th scope="col">Branch Site</th>
    </tr>

<s:iterator value="mp2007InfoList" status="st">
        
</s:iterator>
</table>

</body>

</html>
