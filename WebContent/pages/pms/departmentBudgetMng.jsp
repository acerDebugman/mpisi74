<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../../images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="../../css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="../../css/Site_Css.css" rel="stylesheet" type="text/css" />

<title>Procurement Management System</title>

<style type="text/css"> 
.navPoint
{
    font-family: Webdings;
    font-size:9pt;
    color:white;
    cursor:pointer;
}
p{
   font-size:9pt;
}
.font_size {  font-family: "Verdana", "Arial", "Helvetica", "sans-serif"; font-size: 12px; font-weight: normal; font-variant: normal; text-transform: none}
</style>

</head>
<body scroll="no" leftmargin="0" topmargin="0" marginheight="0" marginwidth="0">
<form>
    <table border="0" cellspacing="0" cellpadding="0" width="100%" height="100%">
        <tr>
            <td width="100%" height="50" colspan="3" style="border-bottom: 3px solid #000000;margin-botton:1px;">
				<!-- 头部菜单 Start -->
				<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
				  <tr>
				    <td class='menubar_title'><img border='0' src='../../images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Budget Management</td>
				    <td class='menubar_readme_text' valign='bottom'><img src='../../images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
				  </tr>
				</table>
				<!-- 头部菜单 End -->
            </td>
        </tr>
        
        <tr>
            <td id="frmTitle" name="frmTitle" nowrap="nowrap" valign="middle" align="center" width="198" style="border-right: 1px solid #000000;">
                <iframe name="BoardTitle" style="padding-top:0px; height: 100%; visibility: inherit; width: 198; z-index: 2" scrolling="auto" frameborder="0" src="departmentList.action?parameterType=budget&statusType=true"></iframe>
            </td>
            <td style="width: 10pt" bgcolor="#7898A8" width="10" title="关闭/打开左栏" class="navPoint">
                <table border="0" cellpadding="0" cellspacing="0" width="11" height="100%" align="right">
                    <tr>
                        <td valign="middle" align="right"  class="middleCss"></td>
                    </tr>
                </table>
            </td>
            <td style="width: 100%">
                <iframe id="mainFrame" name="mainFrame" style="padding-top:0px; height: 100%; visibility: inherit; width: 100%; z-index: 1" scrolling="auto" frameborder="0" src="budgetSetInit.action?pageType=view"></iframe>
            </td>
        </tr>
    </table>
</form>
</body>
</html>