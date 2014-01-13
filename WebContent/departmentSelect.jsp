<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base target="_self"/>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />

<script src="js/calendar.js" type="text/javascript"></script>
<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery1.4.2.js" type="text/javascript"></script>

<style type="text/css">
.checkdiv{ float:left; margin:4px 2px 0 7px; height:20px; width:20px; border:1px solid green; overflow:hidden; }
.checkbox{ height:32px; width:32px; border:1px solid green; margin-left:-8px; margin-top:-8px; text-align:center; line-height:32px;}
</style>

<script type="text/javascript">
var closeWinFunc = window.close;
window.close = function() {
    window.open("", "_self");
    closeWinFunc();
}
window.document.onkeydown = keyStroke;
function keyStroke(){
	if (window.event.keyCode==13){
		window.event.keyCode=9;
	}
}
</script>

<script type="text/javascript">
function cbc(_id, _index){
	//alert(_id + "|" + _index);
}
function sa(){
	var count = 0;
	var _id;
	var _all = true;
	if(document.getElementById("all").checked == true){
		_all = true;
	}else{
		_all = false;
	}
	while(1==1){
		count = count + 1;
		_id = "emp_" + count;
		var obj = document.getElementById(_id);
		if(obj != null && obj != "undefined"){
			obj.checked = _all;
		}else{
			break;
		}
	}
}
</script>

</head>
<body bgColor="#FFFFFF" topMargin="5">
<form method="post" action="departmentSelectSave.action">
<input id="pageType" name="pageType" value="${pageType}" type="hidden" />
<input id="commonSeq" name="commonSeq" value="${commonSeq}" type="hidden" />

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
  <tr style="border-bottom:2px solid #000000;">
    <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Department Select</td>
    <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
  </tr>
</table>
<!-- 头部菜单 End -->

<div id="departmentInfoListDiv" style="overflow:auto;margin-top:0px;height:480px;border:0px solid red;">
<table class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px;margin-top:0px;">
    <tr class="table_title" align="center">
        <th width="50px">No.</th>
        <th width="150px">Department Number</th>
        <th>Department Name</th>
        <th width="150px">Description</th>
        <th width="50px"><input id="all" type="checkbox" onclick="sa()" /></th>
    </tr>
<s:iterator id="info" value="departmentList" status="st">
    <tr class="row" align="left" style="height:28px;">
        <td width="50px">${st.index + 1}</td>
        <td width="150px"><s:property value="MP0002_DEPARTMENT_NUM"/></td>
        <td><s:property value="MP0002_DEPARTMENT_NAME"/></td>
        <td width="150px"><s:property value="MP0002_DEPARTMENT_DESC"/></td>
        <td width="50px">
<div class="checkdiv">
    <input id="emp_${st.index + 1}" onclick="cbc('emp_${st.index + 1}','${st.index + 1}')" type="checkbox" name="depArray" value="${MP0002_SEQ}" <s:if test="depArray.contains(MP0002_SEQ)">checked="checked"</s:if> class="checkbox"/>
</div>
        </td>
    </tr>
</s:iterator>
</table>
</div>

<div style="margin:0 auto;text-align:center;margin-top:10px;">
<input type="submit" name="saveBtn" value="Save Information" id="saveBtn"/>
</div>

</form>
</body>
</html>