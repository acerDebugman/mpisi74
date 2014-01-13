<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base target="_self"/>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
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
function uploadImage(){
	$(document).ready(function() {
		var _empNum = $("#employeeNum").val();
		var _docName = $("#documentName").val();
        var options = {
            url : "uploadDocument.action?employeeNum=" + _empNum + "&documentName=" + _docName,
            type : "post",
            dataType : "script",
            success : function(msg) {
            	try{
                	$("#tipDiv").html(msg);
                	document.getElementById("fileupload").outerHTML += "";
            	}catch(Exception){}
            }
        };
        $("#form2").ajaxSubmit(options);
        return false;
    });
}
</script>

</head>
<body>

<form id="form2" name="form2" action="documentInfoSave" method="post" target="frame1" enctype="multipart/form-data">

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
  <tr>
    <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Employee Management</td>
    <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
  </tr>
  <tr>
    <td height='27px' class='menubar_function_text'>Operation Function: Add New Document</td>
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
        <td class="table_body">Employee Number/Overseas Business NO.</td>
        <td class="table_none"><input id="employeeNum" name="mp1009.MP1009_EMPLOYEE_NUM" value="${mp1009.MP1009_EMPLOYEE_NUM}" type="text" class="text_input"/></td>
    </tr>
    <tr>
        <td class="table_body">Document Name</td>
        <td class="table_none"><input id="documentName" name="mp1009.MP1009_DOCUMENT_NAME" value="${mp1009.MP1009_DOCUMENT_NAME}" type="text" class="text_input"/></td>
    </tr>
    <tr>
        <td class="table_body">Select File</td>
        <td class="table_none">
            <input id="fileupload" name="fileupload" type="file" />
            <input type="button" id="upload" class="right-button02" onclick="uploadImage()" value="Add Document" />
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="button" onclick="window.location.href='DocumentMngInit.action'" name="close" value="Close" />
        </td>
    </tr>
    
</table>

<div id="tipDiv"></div>

</form>

</body>
</html>