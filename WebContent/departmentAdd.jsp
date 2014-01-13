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
<meta http-equiv="Expires" CONTENT="0"> 
<meta http-equiv="Cache-Control" CONTENT="no-cache"> 
<meta http-equiv="Pragma" CONTENT="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Add New Department</title>
<link href="<%=basePath%>/images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="<%=basePath%>/css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>/css/Site_Css.css" rel="stylesheet" type="text/css" />

<base href="<%=basePath%>">

<script type="text/javascript">
var closeWinFunc = window.close;
window.close = function() {
    window.open("", "_self");
    closeWinFunc();
}
</script>

</head>
<body>
<form id="form2" name="form2" action="departmentInfoSave" method="post">
<input id="pageType" name="pageType" value="${pageType}" type="hidden" />
<input id="mp0002Seq" name="mp0002Seq" value="${mp0002Seq}" type="hidden" />
<input id="optSave" name="optSave" value="${optSave}" type="hidden" />

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
  <tr>
    <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Department Management</td>
    <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
  </tr>
  <tr>
    <td height='27px' class='menubar_function_text'>Operation Function:Add New Department</td>
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

<table width="100%" border="0" cellspacing="1" cellpadding="1" align="center" style="padding-bottom:50px;">
    <tr>
        <td style="color:red;"><s:property value="errMsg"/></td>
    </tr>
    <tr>
        <td class="table_body">Department Code<span class="errorcss">*</span></td>
        <td class="table_none">
            <input name="mp0002.MP0002_DEPARTMENT_NUM" value="${mp0002.MP0002_DEPARTMENT_NUM}" type="text" class="text_input" />
            <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
                <s:param>MP0002_DEPARTMENT_NUM</s:param>
            </s:fielderror>
        </td>
    </tr>
    <tr>
        <td class="table_body">Department Name<span class="errorcss">*</span></td>
        <td class="table_none">
            <input name="mp0002.MP0002_DEPARTMENT_NAME" value="${mp0002.MP0002_DEPARTMENT_NAME}" type="text" class="text_input" />
            <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
                <s:param>oldPassword</s:param>
            </s:fielderror>
        </td>
    </tr>
    <tr>
        <td class="table_body">Comment<span class="errorcss">*</span></td>
        <td class="table_none">
            <input name="mp0002.MP0002_DEPARTMENT_DESC" value="${mp0002.MP0002_DEPARTMENT_DESC}" type="text" class="text_input" />
            <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
                <s:param>newPassword1</s:param>
            </s:fielderror>
        </td>
    </tr>
    <tr>
        <td class="table_body">Status<span class="errorcss">*</span></td>
        <td class="table_none">
            <s:select id="mp0002Status" name="mp0002Status" value="mp0002Status" list="mp0002StatusList" theme="simple"/>
            <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
                <s:param>newPassword2</s:param>
            </s:fielderror>
        </td>
    </tr>
<s:if test="optSave == 1">
    <tr>
        <td><s:submit name="save" value="save" align="left" theme="simple"/></td>
    </tr>
</s:if>
</table>

</form>
</body>
</html>