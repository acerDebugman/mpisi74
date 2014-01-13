<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<base target="_self"/>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<title></title>
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<link href="css/skins2/main.css" rel="stylesheet" type="text/css" />

<script src = "js/skins2/Main82.js" type="text/javascript"></script>

<style type="text/css">
.tdBg{background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px;color:white;text-align:center;margin:0px;padding:0px;}
.tdBg1{border-bottom:solid 1px gray;}
.checkdiv{ float:left; margin:4px 2px 0 7px; height:20px; width:20px; border:1px solid green; overflow:hidden; }
.checkbox{ height:32px; width:32px; border:1px solid green; margin-left:-8px; margin-top:-8px; text-align:center; line-height:32px;}
</style>

<script type="text/javascript">
function s(){
	var popstyle="dialogTop:80px;dialogLeft:650px;help:no;center:yes;dialogHeight:650px;dialogWidth:550px;status:no;resizable:yes;scroll:no;loacation:no;toolbar:no;";
	window.showModalDialog("examQuestionSelectInit.action",window,popstyle);
}
function e(){
	var key = document.getElementById("commonSeq").value;
	var popstyle="dialogTop:80px;dialogLeft:650px;help:no;center:yes;dialogHeight:450px;dialogWidth:550px;status:no;resizable:yes;scroll:no;loacation:no;toolbar:no;";
	window.showModalDialog("examQuestionSelectMngInit.action?commonSeq=" + key,window,popstyle);
}
function cbc(id,idx){
	var o1 = "sa1_" + idx;
	var o2 = "sa2_" + idx;
	var o3 = "sa3_" + idx;
	var o4 = "sa4_" + idx;
	var o5 = "sa5_" + idx;
	
	if(o1 != id){
		document.getElementById("sa1_" + idx).checked = false;
	}
	if(o2 != id){
		document.getElementById("sa2_" + idx).checked = false;
	}
	if(o3 != id){
		document.getElementById("sa3_" + idx).checked = false;
	}
	if(o4 != id){
		document.getElementById("sa4_" + idx).checked = false;
	}
	if(o5 != id){
		document.getElementById("sa5_" + idx).checked = false;
	}
}
</script>

</head>
<body>
<s:form action="examEvaluationMonthlyInfoSave" method="post" theme="simple">
<input id="pageType" name="pageType" value="${pageType}" type="hidden" />
<input id="commonSeq" name="commonSeq" value="${commonSeq}" type="hidden" />
<input id=param1 name="param1" value="${param1}" type="hidden" /><!--用来记录考核题目编码  -->
<input id=param2 name="param2" value="${param2}" type="hidden" />

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
	<tr>
		<td class='menubar_title' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img border='0'src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Appraisal Management(Monthly)</td>
		<td class='menubar_readme_text' valign='bottom' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help?</td>
	</tr>
</table>
<!-- 头部菜单 End -->

<table cellpadding="0" cellspacing="0" border="0" width="100%" align="center" style="margin-top:5px;">
	<tr align="center">
		<td	class="td1" colspan="5" style="color:red;">(5)=Exceptional (4)=Exceeds Requirements (3)=Meet Requirement (2)=Gets By (1)=Needs Work</td>
	</tr>
    <tr>
        <td width="20%" style="background-color:#3566A7;color:#FFFFFF;">Employee Name</td>
        <td width="20%" style="background-color:#3566A7;color:#FFFFFF;">Job Title</td>
        <td width="20%" style="background-color:#3566A7;color:#FFFFFF;">Department</td>
        <td width="20%" style="background-color:#3566A7;color:#FFFFFF;">Manager</td>
        <td width="20%" style="background-color:#3566A7;color:#FFFFFF;">Review Period</td>
    </tr>
    <tr>
        <td width="20%"><s:property value="mp7004.MP7004_EMPLOYEE_NAME"/></td>
        <td width="20%"><s:property value="mp7004.MP7004_JOB_NAME"/></td>
        <td width="20%"><s:property value="mp7004.MP7004_DEPARTMENT_NAME"/></td>
        <td width="20%"><s:property value="mp7004.MP7004_MANAGER_NAME"/></td>
        <td width="20%"><s:property value="mp7004.MP7004_REVIEW_PERIOD"/></td>
    </tr>
    <tr>
        <td colspan="5"><s:textarea id="qt" name="mp7004.MP7004_COMMENT" style="width:100%;height:60px;" theme="simple"></s:textarea></td>
    </tr>
    <tr>
        <td colspan="5">
			<table cellspacing="0" cellpadding="0" style="border:0;width:100%;">
			    <tr align="center" style="background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px">
			        <th scope="col" class="tdBg">Seq</th>
			        <th scope="col" class="tdBg">Title</th>
			        <th scope="col" class="tdBg">Exceptional</th>
			        <th scope="col" class="tdBg">Exceeds Requirements</th>
			        <th scope="col" class="tdBg">Meet Requirement</th>
			        <th scope="col" class="tdBg">Gets By</th>
			        <th scope="col" class="tdBg">Needs Work</th>
			    </tr>
			<s:iterator value="evaluationMonthlyScoresList" status="st">
			    <tr align="left" style="height:28px;">
			        <td width="50px" align="center" class="tdBg1">${st.index + 1}</td>
			        <td class="tdBg1"><s:property value="MP7005_EXAM_NAME"/></td>
			        <td width="80px" class="tdBg1"><div class="checkdiv"><input id="sa5_${st.index + 1}" onclick="cbc('sa5_${st.index + 1}','${st.index + 1}')" type="checkbox" name="scoreArray5" value="${MP7005_EXAM_CODE}" <s:if test="scoreArray5.contains(MP7005_EXAM_CODE)">checked="checked"</s:if> class="checkbox"/></div></td>
			        <td width="150px" class="tdBg1"><div class="checkdiv"><input id="sa4_${st.index + 1}" onclick="cbc('sa4_${st.index + 1}','${st.index + 1}')" type="checkbox" name="scoreArray4" value="${MP7005_EXAM_CODE}" <s:if test="scoreArray4.contains(MP7005_EXAM_CODE)">checked="checked"</s:if> class="checkbox"/></div></td>
			        <td width="120px" class="tdBg1"><div class="checkdiv"><input id="sa3_${st.index + 1}" onclick="cbc('sa3_${st.index + 1}','${st.index + 1}')" type="checkbox" name="scoreArray3" value="${MP7005_EXAM_CODE}" <s:if test="scoreArray3.contains(MP7005_EXAM_CODE)">checked="checked"</s:if> class="checkbox"/></div></td>
			        <td width="50px" class="tdBg1"><div class="checkdiv"><input id="sa2_${st.index + 1}" onclick="cbc('sa2_${st.index + 1}','${st.index + 1}')" type="checkbox" name="scoreArray2" value="${MP7005_EXAM_CODE}" <s:if test="scoreArray2.contains(MP7005_EXAM_CODE)">checked="checked"</s:if> class="checkbox"/></div></td>
			        <td width="80px" class="tdBg1"><div class="checkdiv"><input id="sa1_${st.index + 1}" onclick="cbc('sa1_${st.index + 1}','${st.index + 1}')" type="checkbox" name="scoreArray1" value="${MP7005_EXAM_CODE}" <s:if test="scoreArray1.contains(MP7005_EXAM_CODE)">checked="checked"</s:if> class="checkbox"/></div></td>
			    </tr>
			</s:iterator>
			</table>
        </td>
    </tr>
	<tr align="center">
		<td	class="td1" colspan="5"><input type="submit" class="button" name="Submit" value="Save"></td>
	</tr>
</table>

</s:form>
</body>
</html>