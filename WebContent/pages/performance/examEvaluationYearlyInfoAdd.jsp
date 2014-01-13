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
.tdBg{
background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px;
color:#FFFFFF;
text-align:left;
margin:0px;
padding:0px;
}
.tdBg1{
border-bottom:solid black 1px;
border-left:solid black 1px;
}
.tdBg2{
border-bottom:solid black 1px;
border-left:solid black 1px;
border-right:solid black 1px;
}
.tdBg3{
border-bottom:solid black 1px;
border-left:solid black 1px;
background-color:#C4D8ED;
color:#000000;
}
.tdBg4{
border-bottom:solid black 1px;
border-left:solid black 1px;
border-right:solid black 1px;
background-color:#C4D8ED;
color:#000000;
}
</style>

<script type="text/javascript">
function sc(id){
	var agreedScore = document.getElementById("agree" + id).value;
	var oldFinalScore = document.getElementById("final" + id).value;
	var weightage = document.getElementById("weightage" + id).value;
	var totalScore = document.getElementById("totalScore").value;
	
	if(agreedScore != 1 && agreedScore != 2 && agreedScore != 3 && agreedScore != 4 && agreedScore != 5){
		alert("The number that you can fill out is only 1,2,3,4,5");
		document.getElementById("agree" + id).value = 1;
		agreedScore = 1;
	}
	var ret = (agreedScore*weightage)/5;
	ret = FormatDight(ret,1);
	document.getElementById("final" + id).value = ret;
	document.getElementById("totalScore").value = totalScore - oldFinalScore + ret; 
}
/*    
*  ForDight(Dight,How):数值格式化函数
*  Dight要格式化的  数字.
*  How要保留的小数位数。    
*/     
function  FormatDight(Dight,How){
   Dight = Math.round  (Dight*Math.pow(10,How))/Math.pow(10,How);
   return Dight;
}
</script>

</head>
<body>
<s:form action="examEvaluationYearlyInfoSave" method="post" theme="simple">
<input id="pageType" name="pageType" value="${pageType}" type="hidden" />
<input id="commonSeq" name="commonSeq" value="${commonSeq}" type="hidden" />
<input id=param1 name="param1" value="${param1}" type="hidden" /><!--用来记录考核题目编码  -->
<input id=param2 name="param2" value="${param2}" type="hidden" />

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
	<tr>
		<td class='menubar_title' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img border='0'src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;年度绩效考核表单</td>
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
        <td width="20%" style="background-color:#3566A7;color:#FFFFFF;">Employee Number</td>
        <td width="20%" style="background-color:#3566A7;color:#FFFFFF;">Department</td>
        <td width="20%" style="background-color:#3566A7;color:#FFFFFF;">Designation</td>
        <td width="20%" style="background-color:#3566A7;color:#FFFFFF;">Current Appraisal Cycle</td>
    </tr>
    <tr>
        <td width="20%"><s:property value="mp7006.MP7006_EMPLOYEE_NAME"/></td>
        <td width="20%"><s:property value="mp7006.MP7006_EMPLOYEE_NUM"/></td>
        <td width="20%"><s:property value="mp7006.MP7006_DEPARTMENT_NAME"/></td>
        <td width="20%"><s:property value="mp7006.MP7006_JOB_TITLE"/></td>
        <td width="20%"><s:property value="mp7006.MP7006_CURRENT_APPRAISAL_CYCLE"/></td>
    </tr>
    <tr>
        <td width="20%" style="background-color:#3566A7;color:#FFFFFF;">Effective Date Last Appraisal</td>
        <td width="20%" style="background-color:#3566A7;color:#FFFFFF;">Name of Appraiser</td>
        <td width="20%" style="background-color:#3566A7;color:#FFFFFF;">Designation of Appraiser</td>
        <td width="20%" style="background-color:#3566A7;color:#FFFFFF;">Name of Reviewer</td>
        <td width="20%" style="background-color:#3566A7;color:#FFFFFF;">Designation of Reviewer</td>
    </tr>
    <tr>
        <td width="20%"><s:property value="mp7006.MP7006_EFFECTIVE_DATE_LAST_APPRAISAL"/></td>
        <td width="20%"><s:property value="mp7006.MP7006_APPRAISER"/></td>
        <td width="20%"><s:property value="mp7006.MP7006_APPRAISER_DESIGNATION"/></td>
        <td width="20%"><s:property value="mp7006.MP7006_REVIEWER"/></td>
        <td width="20%"><s:property value="mp7006.MP7006_REVIEWER_DESIGNATION"/></td>
    </tr>
    <tr>
        <td colspan="5">
			<table cellspacing="0" cellpadding="0" style="width:100%;">
<s:iterator id="list" value="evaluationYearlyScoresList" status="st">
			    <tr>
			        <td colspan="7" class="tdBg" style="border:solid black 1px;">${st.index + 1}. <s:property value="MP7007_EXAM_NAME"/><input name="evaluationYearlyScoresList[<s:property value="#st.index"/>].MP7007_SEQ" value="${list.MP7007_SEQ}" type="hidden"/></td>
			    </tr>
			    <tr>
			        <td rowspan="3" class="tdBg3"><s:property value="MP7007_EXAM_SUB_TITLE"/></td>
			        <td colspan="3" class="tdBg3"><s:property value="MP7007_EXAM_CONTENT"/></td>
			        <td class="tdBg3">Remarks</br>(Pls give reasons for rating 1 or 5)</td>
			        <td class="tdBg3">Weightage</br>(in %)</td>
			        <td class="tdBg4">Score</br>(Rating*Weightage)</td>
			    </tr>
			    <tr>
			        <td class="tdBg3">Self Assessment</td>
			        <td class="tdBg3">Reporting Manager</td>
			        <td class="tdBg3">Agreed Score</td>
			        <td rowspan="2" class="tdBg1"><textarea name="evaluationYearlyScoresList[${st.index}].MP7007_REMARKS" style="width:100%;height:60px;" ><s:property value="MP7007_REMARKS"/></textarea></td>
			        <td rowspan="2" class="tdBg1"><input id="weightage${st.index + 1}" name="evaluationYearlyScoresList[<s:property value="#st.index"/>].MP7007_WEIGHTAGE" value="${list.MP7007_WEIGHTAGE}" type="text" readonly="readonly" style="border:none;"/></td>
			        <td rowspan="2" class="tdBg2"><input id="final${st.index + 1}" name="evaluationYearlyScoresList[<s:property value="#st.index"/>].MP7007_FINAL_SCORE" value="${list.MP7007_FINAL_SCORE}" type="text"/></td>
			    </tr>
			    <tr>
			        <td class="tdBg1"><input name="evaluationYearlyScoresList[<s:property value="#st.index"/>].MP7007_SELF_SCORE" value="${list.MP7007_SELF_SCORE}" type="text"/></td>
			        <td class="tdBg1"><input name="evaluationYearlyScoresList[<s:property value="#st.index"/>].MP7007_MANAGER_SCORE" value="${list.MP7007_MANAGER_SCORE}" type="text"/></td>
			        <td class="tdBg1"><input id="agree${st.index + 1}" name="evaluationYearlyScoresList[<s:property value="#st.index"/>].MP7007_AGREED_SCORE" value="${list.MP7007_AGREED_SCORE}" type="text" onKeyup="sc('${st.index + 1}')"/></td>
			    </tr>
</s:iterator>
			    <tr>
			        <td colspan="5">&nbsp;</td>
			        <td align="left"><input id="totalWeightage" name="mp7006.MP7006_TOTAL_WEIGHTAGE" value="${mp7006.MP7006_TOTAL_WEIGHTAGE}" type="text" style="border:none;" readonly="readonly"/></td>
			        <td align="left"><input id="totalScore" name="mp7006.MP7006_COMPETENCE_TOTAL_SCORES" value="${mp7006.MP7006_COMPETENCE_TOTAL_SCORES}" type="text" style="border:none;" readonly="readonly"/></td>
			    </tr>
			</table>
        </td>
    </tr>
	<tr align="center">
		<td	class="td1" colspan="5"><input type="submit" class="button" name="Submit" value="保 存"></td>
	</tr>
</table>
<s:debug />
</s:form>
</body>
</html>