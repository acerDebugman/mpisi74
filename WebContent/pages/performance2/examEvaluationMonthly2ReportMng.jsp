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
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<!-- <link href="css/skins2/main.css" rel="stylesheet" type="text/css" /> -->

<script src = "js/skins2/Main82.js" type="text/javascript"></script>
<script src="js/jquery1.4.2.js" type="text/javascript"></script>
<script src="js/jquery.form.js" type="text/javascript" ></script>

<style type="text/css">
body,th,td{font:normal 12px Arial;}
body  {	margin:0px; background:#C4D8ED; margin:0px; padding:10px;	SCROLLBAR-FACE-COLOR: #72A3D0; SCROLLBAR-HIGHLIGHT-COLOR: #337ABB;	SCROLLBAR-SHADOW-COLOR: #337ABB; SCROLLBAR-DARKSHADOW-COLOR: #337ABB;	SCROLLBAR-3DLIGHT-COLOR: #337ABB; SCROLLBAR-ARROW-COLOR: #FFFFFF;	SCROLLBAR-TRACK-COLOR: #337EC0;}
td { height:24px; line-height:20px;  font-size:12px; border:1px solid #fff; color:#135294; padding:2px; }
input { border:1px solid #999; }
input { border-bottom:1px solid #BDC5CA; border-right:1px solid #BDC5CA; border-top:1px solid #6F787E; border-left:1px solid #6F787E; padding:3px 2px; font-size:12px; }
button { height:28px; line-height:28px; border:1px solid #C6D2E3; background:url("images/skins2/button_bg3.gif"); font-size:12px; }
.button { color: #135294; border:1px solid #666; height:21px; line-height:21px; background:url("images/skins2/button_bg.gif")}
.input0{background:url("images/skins2/button_bg3.gif");border:1px solid #BDC5CA}
.button_on { background:url("images/skins2/button_bg2.gif"); border:1px solid #c3a336; }
.input_on { background:url("images/skins2/button_bg2.gif"); border:1px solid #c3a336; }

.tdBg{background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px;color:white;text-align:center;margin:0px;padding:0px;}
.tdBorder{border-bottom:1px solid #2F589C;}
</style>

<script type="text/javascript">
window.document.onkeydown = keyStroke;
function keyStroke(){
	if (window.event.keyCode==13){
		window.event.keyCode=9;
	}
}
</script>
<script type="text/javascript">
function rowClick(id,period,depId){
	if(event.button == 2){
		// nothing to do
	}else if(event.button == 1){
		var _oldIndex = document.getElementById("rowIndex").value;
 		if(document.getElementById(_oldIndex) != null){
			document.getElementById(_oldIndex).style.backgroundColor = "#C4D8ED";
		}
		if(document.getElementById(id) != null){
			document.getElementById(id).style.backgroundColor = "#FFFFFF";
		}
		document.getElementById("rowIndex").value = id;
		
		var popstyle="dialogTop:50px;dialogLeft:300px;help:no;center:yes;dialogHeight:450px;dialogWidth:1000px;status:no;resizable:yes;scroll:yes;loacation:no;toolbar:no;";
		window.showModalDialog("examEvaluationMonthly2Detail.action?param1=" + depId + "&param2=" + period,window,popstyle);
	}
}
function ce(){
    var param2 = $("#param2").val(); //yyyy
    var param3 = $("#param3").val(); //season
    var param1 = $("#param1").val(); //department ID
    var reviewPeriod = param2 + "-" + param3;
    
    if(param2 == "" || param3 == "-1"){
    	reviewPeriod = "";
    }
    var param = "?reviewPeriod=" + reviewPeriod + "&param1=" + param1;
 	var options = {
	    url : "createMonthlyAppraisalFormInfoExcelDocument3.action" + param,
	    type : "post",
        dataType : "html",
		success : function(msg) {
		    //alert("Download Success");
		}
	};
	$("#from1").ajaxSubmit(options);
}
</script>

</head>
<body>
<form id="from1" name="from1" action="examEvaluationMonthly2ReportMngSearch" method="post" enctype="multipart/form-data">
<input id="pageCount" name="pageCount" value="${pageCount}" type="hidden" />
<input id="pageNum" name="pageNum" value="${pageNum}" type="hidden" />
<input id="rowIndex" name="rowIndex" value="1" type="hidden" />
<input id="optSearch" name="optSearch" value="${optSearch}" type="hidden" />
<input id="optPdf" name="optPdf" value="${optPdf}" type="hidden" />
<input id="A006009001002" name="A006009001002" value="${A006009001002}" type="hidden"/> <!-- row click access -->

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
	<tr>
		<td class='menubar_title' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img border='0'src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Appraisal Management(Monthly)</td>
		<td class='menubar_readme_text' valign='bottom' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help?</td>
	</tr>
</table>
<!-- 头部菜单 End -->

<!--检索区域Start-->
<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" style="margin-top:5px;">
    <tr>
        <td width="20%">Department</td>
        <td width="30%"><s:select id="param1" name="param1" value="param1" list="commonDepartmentList" style="width:98%;" theme="simple" /></td>
        <td width="20%">Review Year&nbsp;&nbsp;<span style="color:red;">*</span></td>
        <td width="30%">
            <input id="param2" name="param2" value="${param2}" type="text" style="width:50%;"/>(YYYY)
            <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
	            <s:param>errMsg</s:param>
	        </s:fielderror>
        </td>
    </tr>
    <tr>
        <td width="20%"></td>
        <td width="30%"></td>
        <td width="20%">Season&nbsp;&nbsp;<span style="color:red;">*</span></td>
        <td width="30%">
            <s:select id="param3" name="param3" value="param3" list="commonSeasonList" theme="simple" style="width:80%;"/>
            <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
	            <s:param>errMsg</s:param>
	        </s:fielderror>
        </td>
    </tr>
    <tr>
        <td colspan="4" align="right">
            <input type="button" onclick="ce()" value="Download(Excel)" <s:if test="optPdf == 0" >disabled="disabled"</s:if>/>
            <input type="submit" name="searchBtn" value="Search" id="searchBtn" class=""/>
        </td>
    </tr>
</table>
<!--检索区域End-->

<div id="examEvaluationInfoListDiv" style="border: 0px solid #000000;margin-top:20px;">
<table cellspacing="0" cellpadding="0" style="border:0;width:100%;">
    <tr align="center" style="background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px">
        <th scope="col" class="tdBg">Seq</th>
        <th scope="col" class="tdBg">Department</th>
        <th scope="col" class="tdBg">Period</th>
        <th scope="col" class="tdBg">Scores</th>
    </tr>
<s:iterator value="evaluationMonthly2InfoList" status="st">
    <tr id="${st.index + 1}" align="left" style="height:28px;" <s:if test="A006009001002 == 1">onmousedown="rowClick('${st.index + 1}','<s:property value="MP8005_REVIEW_PERIOD"/>','<s:property value="MP8005_DEPARTMENT"/>')"</s:if>>
        <td width="50px" align="center" class="tdBorder">${st.index + 1}</td>
        <td class="tdBorder"><s:property value="MP8005_DEPARTMENT_NAME"/></td>
        <td class="tdBorder"><s:property value="MP8005_REVIEW_PERIOD"/></td>
        <td class="tdBorder"><s:property value="MP8005_SCORES"/></td>
    </tr>
</s:iterator>
</table>
</div>

</form>
</body>
</html>