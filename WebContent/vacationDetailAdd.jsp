<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="Style.css">
</head>
  
<body>

<table class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;width:600px;">
    <tr class="" style="FONT: bold 14px Tahoma, Verdana;COLOR: #ffffff;BACKGROUND-COLOR: #698cc3;HEIGHT: 35px;BACKGROUND: url(images/header_bg.gif) #2f589c repeat-x 0px 0px;PADDING-LEFT: 5px;PADDING-RIGHT:5px;" align="center">
        <th scope="col" width="100px">Employee Number</th>
        <th scope="col" width="100px">Employee Name</th>
        <th scope="col" width="150px">Major Name</th>
        <th scope="col" width="150px">Days</th>
        <th scope="col" width="100px">-</th>
    </tr>
<s:iterator value="majorInfoList" status="st">
    <tr class="row" align="center" style="height:28px;">
        <td width="120px"><s:property value="mp2002EmpNum"/></td>
        <td width="120px"><s:property value="mp2002EmpName"/></td>
        <td width="120px"><s:property value="MP2004_MAJOR_NAME"/></td>
        <td width="120px"><s:property value="MP2004_TIME"/></td>
        <td>
            <input type="button" onclick="majorInfoDel(<s:property value="MP2004_SEQ"/>)" name="majorInfoDelBtn" value="Del" id="majorInfoDelBtn" />
        </td>
    </tr>
</s:iterator>
</table>

</body>
</html>