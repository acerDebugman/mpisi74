<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base target="_self"/>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<title>Add Leave</title>
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<script src="js/jquery1.4.2.js" type="text/javascript" ></script>

<script type="text/javascript">
var closeWinFunc = window.close;
window.close = function() {
    window.open("", "_self");
    closeWinFunc();
};
function leaveTypeChange(){
	var _type = document.getElementById("leaveType").value;
	
	if(_type == '4'){
		document.getElementById("majorId").style.display = "block";
	}else{
		document.getElementById("majorId").style.display = "none";
	}
	
	if(_type != '0'){
		document.getElementById("mp2007.MP2007_DAYS_EXT_D").disabled = false;
		document.getElementById("mp2007.MP2007_DAYS_EXT_H").disabled = false;
		document.getElementById("mp2007.MP2007_DAYS_EXT_D").value = '';
		document.getElementById("mp2007.MP2007_DAYS_EXT_H").value = '';
		document.getElementById("mp2007.MP2007_MAJOR_NAME").value ='';
		
	}else{
		document.getElementById("mp2007.MP2007_DAYS_EXT_D").disabled = true;
		document.getElementById("mp2007.MP2007_DAYS_EXT_H").disabled = true;
		document.getElementById("mp2007.MP2007_DAYS_EXT_D").value = '';
		document.getElementById("mp2007.MP2007_DAYS_EXT_H").value ='';
		document.getElementById("mp2007.MP2007_MAJOR_NAME").value ='';
		document.getElementById("submit").disabled = true;
	}
}
function daychange(){
    var day = document.getElementById("mp2007.MP2007_DAYS_EXT_D").value.length;
    var _type = document.getElementById("leaveType").value;
}
function hourchange(){
	var hour = document.getElementById("mp2007.MP2007_DAYS_EXT_H").value.length;
    var _type = document.getElementById("leaveType").value;
}
$(document).mousemove(function(e){
	var day = document.getElementById("mp2007.MP2007_DAYS_EXT_D").value.length;
	var hour = document.getElementById("mp2007.MP2007_DAYS_EXT_H").value.length;
	var major = document.getElementById("mp2007.MP2007_MAJOR_NAME").value.length;
    var _type = document.getElementById("leaveType").value;
	
    if(_type != '0' && _type != '4' && hour > 0 && day > 0){
    	document.getElementById("submit").disabled = false;
    }else if(_type != '0' && _type == '4'&& hour > 0 && day > 0 && major > 0){
    	document.getElementById("submit").disabled = false;
    }else{
    	document.getElementById("submit").disabled = true;
    }
});
</script>
</head>
<body>
<form action="leaveAdd.action" method="post">
<input id="mp2007Seq" name="mp2007Seq" value="${mp2007Seq}" type="hidden" />
<input id="pageType" name="pageType" value="${pageType}" type="hidden" />
<input id="pageNum" name="pageNum" value="${pageNum}" type="hidden" />

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
  <tr>
    <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Add Leave</td>
    <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
  </tr>
  <tr>
    <td height='27px' class='menubar_function_text'>Operation Function: Add Leave</td>
    <td class='menubar_menu_td' align='right'>
      <table border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="menubar_button" id="button_0"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td height='5px' colspan='2'></td>
  </tr>
</table>
<!-- 头部菜单 End -->

<table width="100%" border="0" cellspacing="1" cellpadding="3" align="left">
	<tr>
		<td class="table_body table_body_NoWidth">Employee Number:</td>
		<td class="table_none table_none_NoWidth"><s:property value="employeeNum"/></td>
	</tr>
	<tr>
		<td class="table_body table_body_NoWidth">Employee Name:</td>
		<td class="table_none table_none_NoWidth"><s:property value="employeeName"/></td>
	</tr>
	<tr>
		<td class="table_body table_body_NoWidth">Type:<span class="errorcss">*</span></td>
		<td class="table_none table_none_NoWidth"><s:select id="leaveType" name="leaveType" list="leaveTypeList" onchange="leaveTypeChange()"  theme="simple"/></td>
	</tr>
	<tr>
		<td class="table_body table_body_NoWidth">Days:<span class="errorcss">*</span></td>
		<td class="table_none table_none_NoWidth">
		    <input id="mp2007.MP2007_DAYS_EXT_D" name="mp2007.MP2007_DAYS_EXT_D" value="${mp2007.MP2007_DAYS_EXT_D}" type="text" onchange="daychange()" class="text_input" style="width:50px;"/>Days
		    <input id="mp2007.MP2007_DAYS_EXT_H" name="mp2007.MP2007_DAYS_EXT_H" value="${mp2007.MP2007_DAYS_EXT_H}" type="text" onchange="hourchange()" class="text_input" style="width:50px;"/>Hours
		</td>
	</tr>
<s:if test="leaveType == 4">
	 <tr id="majorId">
		<td class="table_body table_body_NoWidth">Major Name:<span class="errorcss">*</span></td>
		<td class="table_none table_none_NoWidth"><input id="mp2007.MP2007_MAJOR_NAME" name="mp2007.MP2007_MAJOR_NAME" value="${mp2007.MP2007_MAJOR_NAME}" type="text" class="text_input" /></td>
	</tr>
</s:if>
<s:else>
	 <tr id="majorId" style="display:none;">
		<td class="table_body table_body_NoWidth">Major Name:<span class="errorcss">*</span></td>
		<td class="table_none table_none_NoWidth"><input id="mp2007.MP2007_MAJOR_NAME" name="mp2007.MP2007_MAJOR_NAME" value="${mp2007.MP2007_MAJOR_NAME}" type="text" class="text_input"/></td>
	</tr>
</s:else>
	<tr>
	    <td colspan="2">
	        <div style="width:100%;color:red;border:solid 0px red;font-weight:bold;" align="left">Tips:<br/>※  When the type of application is Study.Please fill in the Major Subject.<br/>※  If Days/Hours is blank,Fill in a Zero(0).</div>
	    </td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" style="padding-bottom:20px;">
    <tr>
        <td align="center" colspan="2">
            <input id="submit" type="submit" value="Save" onclick="" />
            <input type="button" onclick="window.close()" value="Close"/>
        </td>
    </tr>
</table>

</form>
</body>
</html>