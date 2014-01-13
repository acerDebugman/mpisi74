<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<base target="_self"/>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="css/wbox.css" rel="stylesheet" type="text/css"></link>

<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery1.4.2.js" type="text/javascript" ></script>
<script src="js/jquery.form.js" type="text/javascript" ></script>
<script src="js/jquery.pager.js" type="text/javascript" ></script>

<script type="text/javascript">
var closeWinFunc = window.close;
window.close = function() {
    window.open("", "_self");
    closeWinFunc();
}
</script>

<script type="text/javascript">
function uploadCV(){
	$(document).ready(function() {
		var _intervieweeID = document.getElementById("intervieweeID").value;
		var _intervieweeName = document.getElementById("intervieweeName").value;
		var _interviewPhone = document.getElementById("interviewPhone").value;
		var _interviewTime = document.getElementById("interviewTime").value;
		var _interviewer = document.getElementById("interviewer").value;
		var _pageType = document.getElementById("pageType").value;
        var options = {
            url : "uploadCV.action?intervieweeID=" + _intervieweeID + "&intervieweeName=" + _intervieweeName + "&interviewPhone=" + _interviewPhone + "&interviewTime=" + _interviewTime + "&interviewer=" + _interviewer + "&pageType=" + _pageType,
            type : "post",
            dataType : "script",
            success : function(msg) {
            	$("#tipDiv").html(msg);
            	document.getElementById("fileupload").outerHTML += "";
            }
        };
        $("#form2").ajaxSubmit(options);
        return false;
    });
}
function save(){
	$(document).ready(function() {
		var _intervieweeID = document.getElementById("intervieweeID").value;
		var _intervieweeName = document.getElementById("intervieweeName").value;
		var _interviewPhone = document.getElementById("interviewPhone").value;
		var _interviewTime = document.getElementById("interviewTime").value;
		var _interviewer = document.getElementById("interviewer").value;
		var _pageType = document.getElementById("pageType").value;
        var options = {
            url : "interviewInfoSave.action?intervieweeID=" + _intervieweeID + "&intervieweeName=" + _intervieweeName + "&interviewPhone=" + _interviewPhone + "&interviewTime=" + _interviewTime + "&interviewer=" + _interviewer + "&pageType=" + _pageType,
            type : "post",
            dataType : "script",
            success : function(msg) {
            	$("#tipDiv").html(msg);
            	document.getElementById("fileupload").outerHTML += "";
            }
        };
        $("#form2").ajaxSubmit(options);
        return false;
    });
}
</script>

</head>
<body>

<form id="form2" name="form2" method="post" target="frame1" enctype="multipart/form-data">
<input id="pageType" name="pageType" value="${pageType}" type="hidden" />
<input id="intervieweeSelectedID" name="intervieweeSelectedID" value="${intervieweeSelectedID}" type="hidden" />

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
  <tr>
    <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Employee Management</td>
    <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
  </tr>
  <tr>
    <td height='27px' class='menubar_function_text'>Operation Function: Add New CV Document</td>
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

<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" style="padding-bottom:50px;">
    <tr>
        <td class="table_body">ID</td>
        <td class="table_none"><input id="intervieweeID" name="mp1008.MP1008_ID" value="${mp1008.MP1008_ID}" type="text" class="text_input"/></td>
    </tr>
    <tr>
        <td class="table_body">Name</td>
        <td class="table_none"><input id="intervieweeName" name="mp1008.MP1008_NAME" value="${mp1008.MP1008_NAME}" type="text" class="text_input"/></td>
    </tr>
    <tr>
        <td class="table_body">Phone</td>
        <td class="table_none"><input id="interviewPhone" name="mp1008.MP1008_PHONE" value="${mp1008.MP1008_PHONE}" type="text" class="text_input"/></td>
    </tr>
    <tr>
        <td class="table_body">Interview Time</td>
        <td class="table_none"><input id="interviewTime" name="mp1008.MP1008_INTERVIEW_DATETIME" alt="" value="${mp1008.MP1008_INTERVIEW_DATETIME}" type="text" class="text_input"/><font color="red">(YYYY-MM-DD HH:MM)</font></td>
    </tr>
    <tr>
        <td class="table_body">Interviewer</td>
        <td class="table_none"><input id="interviewer" name="mp1008.MP1008_INTERVIEWER" value="${mp1008.MP1008_INTERVIEWER}" type="text" class="text_input"/></td>
    </tr>
    <tr>
        <td class="table_body">Select File</td>
        <td class="table_none">
            <input id="fileupload" name="fileupload" type="file" />
            <input type="button" id="upload" class="right-button02" onclick="uploadCV()" value="Add CV" />
        </td>
    </tr>
    <tr>
        <td colspan="2">
<s:if test="pageType=='EDIT'">
            <input type="button" onclick="save()" name="saveBtn" value="Save" id="saveBtn" class="" />
</s:if>
            <input type="button" onclick="window.close()" name="close" value="Close" />
        </td>
    </tr>
    
</table>

<div id="tipDiv"></div>

</form>

</body>
</html>