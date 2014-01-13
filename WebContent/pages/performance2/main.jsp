<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Performance Appraisal Management System</title>
<link href="css/skins2/style.css" rel="stylesheet" type="text/css" />
<style>
.main_left {table-layout:auto; background:url(skins/images/left_bg.gif)}
.main_left_top{ background:url(skins/images/left_menu_bg.gif); padding-top:2px !important; padding-top:5px;}
.main_left_title{text-align:left; padding-left:15px; font-size:14px; font-weight:bold; color:#fff;}
.left_iframe{HEIGHT: 92%; VISIBILITY: inherit;WIDTH: 180px; background:transparent;}
.main_iframe{HEIGHT: 92%; VISIBILITY: inherit; WIDTH:100%; Z-INDEX: 1}
table { font-size:12px; font-family : tahoma, 宋体, fantasy; }
td { font-size:12px; font-family : tahoma, 宋体, fantasy;}
</style>

<script src = "js/skins2/admin.js" type="text/javascript"></script>
<script>
var status = 1;
var Menus = new DvMenuCls;
document.onclick=Menus.Clear;
function switchSysBar(){
     if (1 == window.status){
		  window.status = 0;
          switchPoint.innerHTML = '<img src="images/skins2/left.gif">';
          document.all("frmTitle").style.display="none"
     }
     else{
		  window.status = 1;
          switchPoint.innerHTML = '<img src="images/skins2/right.gif">';
          document.all("frmTitle").style.display=""
     }
}
</script>
</head>
<body scroll="no" leftmargin="0" topmargin="0" marginheight="0" marginwidth="0">
    


<table border=0 cellpadding=0 cellspacing=0 height="100%" width="100%" style="background:#C3DAF9;">
<tr>
	<td height="58" colspan="3">
	    <iframe frameborder="0" id="top" name="top" scrolling="no" src="pages/performance2/top.jsp" style="height: 100%; visibility: inherit;width: 100%;"></iframe>
	</td>
</tr>
<tr>
	<td height="30" colspan="3">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr height="32">
			  <td background="images/skins2/bg2.gif"width="28" style="padding-left:30px;"><img src="images/skins2/arrow.gif" alt="" align="absmiddle" /></td>
			  <td background="images/skins2/bg2.gif"><span style="float:left">Company Announcement：</span><span style="color:#c00; font-weight:bold; float:left;width:300px;" id="dvbbsannounce"></span></td>
			  <td background="images/skins2/bg2.gif" style="text-align:right; color: #135294; "><s:property value="employeeName"/> (<s:property value="employeeNum"/>) | <a href="login.jsp" target="_self">退出</a> </td>
		  </tr>
		</table>
	</td>
</tr>
<tr>
	<td align="middle" id="frmTitle" valign="top" name="fmtitle" style="background:#c9defa">
	    <iframe frameborder="0" id="frmleft" name="frmleft" src="pages/performance2/left.jsp" style="height: 100%; visibility: inherit;width: 185px;background:url(images/skins2/leftop.gif) no-repeat" allowtransparency="true"></iframe>
	</td>
	<td style="width:0px;" valign="middle">
		<div onclick="switchSysBar()">
		    <span class="navpoint" id="switchPoint" title="关闭/打开左栏"><img src="images/skins2/right.gif" alt="" /></span>
		</div>
	</td>
	<td style="width: 100%" valign="top">
		<iframe frameborder="0" id="frmright" name="frmright" scrolling="yes" src="" style="height: 100%; visibility: inherit; width:100%; z-index: 1"></iframe>
	</td>
</tr>
<tr>
	<td height="30" colspan="3">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" style="background:url(images/skins2/botbg.gif)">
		  <tr height="32">
			<td style="text-align:right; padding-left:30px; font-family:arial; font-size:11px;">Copyright Right &copy; 2012 Mpisi74   Powered By Mpisi74 Version 2.0</td>
			<td style="text-align:right; color:#91B1C6;"></td>
			<td style="text-align:right; color: #135294; padding-right:20px;"><a href="reloadforumcache.jsp" target="frmright">Clear Caches</a> | <a href="../recycle.jsp" target="_blank">Recycle Bin</a> | <a href="update.jsp?action=updat&amp;submit=清空在线用户" target="frmright">Clear Online List</a></td>
		  </tr>
		</table>
	</td>
</tr>
</table>


</body>
</html>