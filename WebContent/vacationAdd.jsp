<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base target="_self"/>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="shortcut icon" href="images/other/icon.ico" type="image/x-icon"></link>

<script type="text/javascript" src="js/checkform.js" charset="utf-8"></script>
<script type="text/javascript" src="js/jquery1.4.2.js"></script>

<script type="text/javascript">
var closeWinFunc = window.close;
window.close = function() {
    window.open("", "_self");
    closeWinFunc();
}
</script>

<script type="text/javascript">
//新增科目假期
function majorInfoAdd(){
	var msg = "Are you sure you want to Add this record?\n\n"; 
	if (confirm(msg)==true){
		var mp2002EmpNum = $("#mp2002EmpNum").val();
		var mp2002EmpName = $("#mp2002EmpName").val();
		var majorName = $("#majorName").val();
		var majorDays = $("#majorDays").val();
		var param = {"mp2002EmpNum" : mp2002EmpNum, "majorName" : majorName, "majorDays" : majorDays, "mp2002EmpName" : mp2002EmpName};
		$("#studyDaysList").load("majorInfoAdd.action",param);
	    return true;
	}else{ 
	    return false; 
	}
}
function majorInfoDel(seq){
	var msg = "Are you sure you want to Delete this record?\n\r";
	var mp2002EmpNum = $("#mp2002EmpNum").val();
	var mp2002EmpName = $("#mp2002EmpName").val();
	var param = {"mp2004Seq" : seq, "mp2002EmpNum" : mp2002EmpNum, "mp2002EmpName" : mp2002EmpName};
	if(confirm(msg) == true){
		$("#studyDaysList").load("majorInfoDel.action",param);
	}else{
		return false;
	}
}
</script>

<script type="text/javascript">
var DispClose = true;
function CloseEvent(){
    if (DispClose){
    	window.dialogArguments.document.getElementById('refreshData').click();
        //return "是否离开当前页面?";
    }
}
window.onbeforeunload=CloseEvent;
</script>

</head>
<body>
<form id="form2" action="vacationSave.action" method="post">
<input type="hidden" id="mp2002EmpNum" name="mp2002EmpNum" value="${mp2002EmpNum}"/>
<input type="hidden" id="mp2002EmpName" name="mp2002EmpName" value="${mp2002EmpName}"/>

<table style="margin-top:30px;margin-left:10px;" cellpadding='1' cellspacing='1'>
    <tr>
        <td align="right">Major</td>
        <td>
            <input id="majorName" name="majorName" value="${majorName}" type="text" style="border:solid 1px #000000;"/>
        </td>
        <td align="right">Days</td>
        <td>
            <input id="majorDays" name="majorDays" value="${majorDays}" type="text" style="border:solid 1px #000000;"/>
        </td>
        <td colspan="2" align="right">
            <input type="button" style="margin-left:30px;" value="Add" onclick="majorInfoAdd()"/>
        </td>
    </tr>
</table>

<div id="studyDaysList">
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
        <td width="120px"><s:property value="MP2004_TIME_DESC"/></td>
        <td>
            <input type="button" onclick="majorInfoDel(<s:property value="MP2004_SEQ"/>)" name="majorInfoDelBtn" value="Del" id="majorInfoDelBtn" />
        </td>
    </tr>
</s:iterator>
</table>
</div>

</form>
</body>
</html>
