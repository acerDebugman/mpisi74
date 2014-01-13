<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Authority</title>
<style type="text/css">
body { margin:0px; background:#337ABB url("images/skins2/top_bg.gif"); font-size:12px; }
div { margin:0px; padding:0px; }
.system_logo { width:160px; float:left; text-align:left; margin-top:5px; margin-left:5px; }
/*- Menu Tabs 6--------------------------- */

#tabs {float:left;width:auto;line-height:normal;}
#tabs ul {margin:0;padding:26px 10px 0 0px !important;*padding:27px 10px 0 0px !important;padding:27px 10px 0 0px;list-style:none;}
#tabs li {display:inline;margin:0;padding:0;}
#tabs a {float:left;background:url("images/skins2/tableft6.gif") no-repeat left top;margin:0;padding:0 0 0 4px;text-decoration:none;}
#tabs a span {float:left;display:block;background:url("images/skins2/tabright6.gif") no-repeat right top;padding:8px 8px 6px 6px;color:#E9F4FF;}
/* Commented Backslash Hack hides rule from IE5-Mac \*/
#tabs a span {float:none;}
/* End IE5-Mac hack */
#tabs a:hover span {color:#fff;}
#tabs a:hover {background-position:0% -42px;}
#tabs a:hover span {background-position:100% -42px;color:#222;}
</style>
<script src = "js/skins2/admin.js" type="text/javascript"></script>

</head>

<body>
<div class="menu">
	<div class="system_logo"><img src="images/logo/logo.png" width="160px"/></div>
	<div id="tabs">
		<ul>
			<li><a href="examQuestion2InfoMngInit.action" onmouseover="parent.frmleft.disp(1);" target="frmright"><span>Appraisal Questionnaire</span></a></li>
			<li><a href="examPlan2InfoMngInit.action" onmouseover="parent.frmleft.disp(2);" target="frmright"><span>Performance Appraisal Plan</span></a></li>
			<li><a href="examEvaluationMonthly2InfoMngInit.action" onmouseover="parent.frmleft.disp(3);" target="frmright"><span>Appraisal Management</span></a></li>
		</ul>
	</div>
<div style="clear:both"></div>
</div>
</body>
</html>