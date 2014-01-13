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
<title></title>

<style type="text/css">
body { margin:0px; background:transparent; overflow:hidden; background:url("images/skins2/leftbg.gif"); }
.left_color { text-align:right; }
.left_color a { color: #083772; text-decoration: none; font-size:12px; display:block !important; display:inline; width:175px !important; width:180px; text-align:right; background:url("images/skins2/menubg.gif") right no-repeat; height:23px; line-height:23px; padding-right:10px; margin-bottom:2px;}
.left_color a:hover { color: #7B2E00;  background:url("images/skins2/menubg_hover.gif") right no-repeat; }
img { float:none; vertical-align:middle; }
#on { background:#fff url("images/skins2/menubg_on.gif") right no-repeat; color:#f20; font-weight:bold; }
hr { width:90%; text-align:left; size:0; height:0px; border-top:1px solid #46A0C8;}
</style>
<script type="text/javascript">
<!--
	function disp(n){
		for (var i=0;i<6;i++)
		{
			if (!document.getElementById("left"+i)) return;			
			document.getElementById("left"+i).style.display="none";
		}
		document.getElementById("left"+n).style.display="";
	}
//-->
</script>
</head>
<body>

<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top" style="padding-top:10px;" class="left_color" id="menubar">
<!-- 默认功能菜单 -->
	    <div id="left0" style="display:"> 
	    	<a href="examQuestionAddInit.action?pageType=add" target="frmright">Add Appraisal Questionnaire</a>
	    	<a href="examQuestionInfoMngInit.action" target="frmright">Appraisal Questionnaire</a>
	    </div>
<!-- 绩效考核题库管理菜单 -->
	    <div id="left1" style="display:none"> 
	    	<a href="examQuestionAddInit.action?pageType=add" target="frmright">Add Appraisal Questionnaire</a>
	    	<a href="examQuestionInfoMngInit.action" target="frmright">Appraisal Questionnaire</a>
	    </div>
<!-- 绩效考核计划管理菜单 -->
	    <div id="left2" style="display:none"> 
	    	<a href="examPlanAddInit.action?pageType=add" target="frmright">Add New Plan</a>
	    	<a href="examPlanInfoMngInit.action" target="frmright">Appraisal Plan Management</a>
	    	<a href="examAppraiserInfoMngInit.action" target="frmright">Appraiser Management</a>
	    </div>
<!-- 月度绩效考核管理菜单 -->
	    <div id="left3" style="display:none">
	    	<a href="examEvaluationMonthlyInfoMngInit.action" target="frmright">Appraisal Management(Monthly)</a>
	    	<a href="pages/performance/appraisalChartReport1.jsp" target="frmright">Appraisal Chart Report1</a>
	    	<a href="appraisalChartReport2Init.action" target="frmright">Appraisal Chart Report2</a>
	    	<a href="appraisalChartReport3Init.action" target="frmright">Appraisal Chart Report3</a>
	    	<a href="appraisalChartReport4Init.action" target="frmright">Appraisal Chart Report4</a>
	    </div>
<!-- 年度绩效考核管理菜单 -->
	    <div id="left4" style="display:none"> 
	    	<a href="examEvaluationYearlyInfoMngInit.action" target="frmright">Appraisal Management(Yearly)</a>
	    </div>
        
	    <div id="left5" style="display:none"> 
	    	<a href="pages/performance/examEvaluationYearlyInfoAdd2.jsp" target="frmright">Test1</a>
	    	<a href="" target="frmright">Test2</a>
	    </div>
        
	    <div id="left6" style="display:none"> 
	    	<a href="" target="frmright" alt="">Menu Name</a>
	    	<a href="" target="frmright" alt="">Menu Name</a>
	    	<a href="" target="frmright" alt="">Menu Name</a>
	    	<a href="" target="frmright" alt="">Menu Name</a>
	    	<a href="" target="frmright" alt="">Menu Name </a>
	    </div>
        
	    <div id="left7" style="display:none"> 
	    	<a href="" target="frmright" alt="">Menu Name</a>
	    	<a href="" target="frmright" alt="">Menu Name</a>
	    	<a href="" target="frmright" alt="">Menu Name</a>
	    	<a href="" target="frmright" alt="">Menu Name</a>
	    	<a href="" target="frmright" alt="">Menu Name</a>
	    </div>
        
	    <div id="left8" style="display:none"> 
	    	<a href="plus.asp" target="frmright" alt="">Menu Name</a>				
	    	
	    	<a href="" target="frmright" alt="">Menu Name</a>
	    	
	    	<a href="" target="frmright" alt="">Menu Name</a>
	    	<a href="" target="frmright" alt="">Menu Name</a>
	    	<a href="" target="frmright" alt="">Menu Name</a>
	    	<a href="" target="frmright" alt="">Menu Name</a>
	    	<a href="" target="frmright" alt="">Menu Name</a>
	    	<a href="" target="frmright" alt="">Menu Name</a>
	    	<a href="" target='frmright'>Menu Name</a>
	    	<a href="" target="frmright" alt="">Menu Name</a>
	    	<a href="" target='frmright'>Menu Name</a>				
	    </div>
        
	    <div id="left9" style="display:none"> 
	    	<a href="" target="frmright" alt="">Menu Name</a>
	    	<a href="" target="frmright" alt="">Menu Name</a>
	    	<a href="" target="frmright" alt="">Menu Name</a>
	    	<a href="" target="frmright" alt="">Menu Name</a>
	    </div>
	</td>
 </tr>
</table>
</body>
</html>