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
function tabClick(idx,count) {
    for (var i_tr=0; i_tr < count; i_tr++) {
	    if (i_tr == idx) {
		    var tabImgLeft = document.getElementById('tabImgLeft__' + idx);
		    var tabImgRight = document.getElementById('tabImgRight__' + idx);
		    var tabLabel = document.getElementById('tabLabel__' + idx);
		    var tabContent = document.getElementById('tabContent__' + idx);
		    
		    tabImgLeft.src = 'images/menu/tab_active_left.gif';
		    tabImgRight.src = 'images/menu/tab_active_right.gif';
		    tabLabel.style.backgroundImage = "url(images/menu/tab_active_bg.gif)";
		    tabContent.style.visibility = 'visible';
		    tabContent.style.display = 'block';
		    continue;
	    }
	    var tabImgLeft = document.getElementById('tabImgLeft__' + i_tr);
	    var tabImgRight = document.getElementById('tabImgRight__' + i_tr);
	    var tabLabel = document.getElementById('tabLabel__' + i_tr);
	    var tabContent = document.getElementById('tabContent__' + i_tr);

	    if(tabImgLeft!=null){
	    	tabImgLeft.src = 'images/menu/tab_unactive_left.gif';
	    }
	    if(tabImgRight!=null){
	    	tabImgRight.src = 'images/menu/tab_unactive_right.gif';
	    }
	    if(tabLabel!=null){
	    	tabLabel.style.backgroundImage = "url(images/menu/tab_unactive_bg.gif)";
	    }
 	    if(tabContent!=null){
	    	tabContent.style.visibility = 'hidden';
	    	tabContent.style.display = 'none';
	    }
    }
}
function selectEmployee(){
	var popstyle="dialogTop:50px;dialogLeft:450px;help:no;center:yes;dialogHeight:750px;dialogWidth:800px;status:no;resizable:no;scroll:no;loacation:no;toolbar:no;";
	window.showModalDialog("employeeSelectInit.action?pageType=performance",window,popstyle);
}
</script>

</head>
<body>
<s:form action="examEvaluationYearlyInfoSave" method="post" theme="simple">
<input id="pageType" name="pageType" value="${pageType}" type="hidden" />
<input id="commonSeq" name="commonSeq" value="${commonSeq}" type="hidden" />
<input id=param1 name="param1" value="${param1}" type="hidden" /><!--用来记录考核题目编码  -->
<input id=param2 name="param2" value="${param2}" type="hidden" />
<input id="optSave" name="optSave" value="${optSave}" type="hidden" />
<input id="optCancel" name="optCancel" value="${optCancel}" type="hidden" />

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
	<tr>
		<td class='menubar_title' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img border='0'src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;年度绩效考核表单</td>
		<td class='menubar_readme_text' valign='bottom' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help?</td>
	</tr>
</table>
<!-- 头部菜单 End -->

<table cellpadding="0" cellspacing="0" border="0" style="margin-bottom:5px;width:100%;">
    <tr>
        <!--按钮　Start-->
        <td>
			<div style="float:left;"><img id='tabImgLeft__0' height='22' src='images/menu/tab_active_left.gif' width="3px"></div>
			<div id="tabLabel__0" onclick='tabClick(0,3)' style="float:left;padding-left:10px;padding-right:10px;background-image: url(images/menu/tab_active_bg.gif);">Employee Profiles</div>
			<div style="float:left;"><img id='tabImgRight__0' height='22' src='images/menu/tab_active_right.gif' width="3px"></div>
			        
			<div style="float:left;"><img id='tabImgLeft__1' height='22' src='images/menu/tab_unactive_left.gif' width="3px"></div>
			<div id="tabLabel__1" onclick='tabClick(1,3)' style="float:left;padding-left:10px;padding-right:10px;background-image: url(images/menu/tab_unactive_bg.gif);">Monthly Appraisal Scores(Six Months)</div>
			<div style="float:left;"><img id='tabImgRight__1' height='22' src='images/menu/tab_unactive_right.gif' width="3px"></div>
			
			<div style="float:left;"><img id='tabImgLeft__2' height='22' src='images/menu/tab_unactive_left.gif' width="3px"></div>
			<div id="tabLabel__2" onclick='tabClick(2,3)' style="float:left;padding-left:10px;padding-right:10px;background-image: url(images/menu/tab_unactive_bg.gif);">Item Information</div>
			<div style="float:left;"><img id='tabImgRight__2' height='22' src='images/menu/tab_unactive_right.gif' width="3px"></div>
        </td>
        <!--按钮 End-->
    </tr>
</table>

<div id='tabContent__0' style='display:block;'>
	<table cellpadding="2" cellspacing="1" border="0" width="100%" align="center">
		<tr>
			<td	width="26%"	align="right" class=td1>Employee Number:</td>
			<td	width="74%"	class=td1><s:property value="mp7006.MP7006_EMPLOYEE_NAME"/></td>
		</tr>
		<tr>
			<td	width="26%"	align="right" class=td1>Employee Name:</td>
			<td	width="74%"	class=td1><s:property value="mp7006.MP7006_EMPLOYEE_NUM"/></td>
		</tr>
		<tr>
			<td	width="26%"	align="right" class=td1>Department:</td>
			<td	width="74%"	class=td1><s:property value="mp7006.MP7006_DEPARTMENT_NAME"/></td>
		</tr>
		<tr>
			<td	width="26%"	align="right" class=td1>Designation:</td>
			<td	width="74%"	class=td1><s:property value="mp7006.MP7006_JOB_TITLE"/></td>
		</tr>
		<tr>
			<td	width="26%"	align="right" class=td1>Current Appraisal Cycle:</td>
			<td	width="74%"	class=td1><s:property value="mp7006.MP7006_CURRENT_APPRAISAL_CYCLE"/></td>
		</tr>
		<tr>
			<td	width="26%"	align="right" class=td1>Effective Date Last Appraisal:</td>
			<td	width="74%"	class=td1><s:property value="mp7006.MP7006_EFFECTIVE_DATE_LAST_APPRAISAL"/></td>
		</tr>
		<tr>
			<td	width="26%"	align="right" class=td1>Name of Appraiser:</td>
			<td	width="74%"	class=td1>
			    <input id="empName" name="mp7006.MP7006_APPRAISER_NAME" value="${mp7006.MP7006_APPRAISER_NAME}" type="text" style="border:none;" readonly="readonly"/>
			    <input id="empNum" name="mp7006.MP7006_APPRAISER" value="${mp7006.MP7006_APPRAISER}" type="text" style="border:none;" readonly="readonly"/>
			    <input type="button" onclick="selectEmployee()" value="Please Select" />
			</td>
		</tr>
		<tr>
			<td	width="26%"	align="right" class=td1>Designation of Appraiser:</td>
			<td	width="74%"	class=td1><s:property value="mp7006.MP7006_APPRAISER_DESIGNATION_NAME"/></td>
		</tr>
		<tr>
			<td	width="26%"	align="right" class=td1>Name of Reviewer:</td>
			<td	width="74%"	class=td1><s:property value="mp7006.MP7006_REVIEWER_NAME"/></td>
		</tr>
		<tr>
			<td	width="26%"	align="right" class=td1>Designation of Reviewer:</td>
			<td	width="74%"	class=td1><s:property value="mp7006.MP7006_REVIEWER_DESIGNATION_NAME"/></td>
		</tr>
	</table>
</div>

<div id='tabContent__1' style='display:none'>
	<table cellspacing="0" cellpadding="0" style="width:100%;">
		<tr align="left">
			<td	class="td1" colspan="3">
			    Individual Rating Scale:(this is used to arrive at the final scores)<br/>
			    5. Outstanding(OS)<br/>
			    4. Sometimes Exceeds Expectations(SEE)<br/>
			    3. Meets Expectations(ME)<br/>
			    2. Partly Meets Expectations(PME)<br/>
			    1. Needs Improvement(NI)
            </td>
		</tr>
		<tr align="center">
			<td	class="td1" colspan="3">Montyly Appraisal Scores of the last 6 months</td>
		</tr>
	    <tr align="center" style="background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px">
	        <th scope="col" width="100px" class="tdBg">Month</th>
	        <th scope="col" width="100px" class="tdBg">Score</th>
	        <th scope="col" class="tdBg">Remarks from Manager</th>
	    </tr>
	<s:iterator id="list" value="monthlyHistoryScoresList" status="st">
	    <tr>
	        <td><s:property value="MP7004_REVIEW_MONTH_NAME"/></td>
	        <td align="center"><s:property value="MP7004_SCORES"/></td>
	        <td><s:property value="MP7004_COMMENT"/></td>
	    </tr>
	</s:iterator>
	    <tr>
	        <td align="right">Subtotal</td>
	        <td colspan="2" align="left"><span style="margin-left:30px;"><input id="totalMonthScore" name="mp7006.MP7006_MONTH_TOTAL_SCORES" value="${mp7006.MP7006_MONTH_TOTAL_SCORES}" type="text" style="border:none;" readonly="readonly"/></span></td>
	    </tr>
	</table>
</div>

<div id='tabContent__2' style='display:none;height:650px;overflow:auto;'>
	<table cellspacing="0" cellpadding="0" style="width:100%;">
		<tr align="center">
			<td	class="td1" colspan="7" style="color:red;">(5)=Exceptional (4)=Exceeds Requirements (3)=Meet Requirement (2)=Gets By (1)=Needs Work</td>
		</tr>
	<s:iterator id="list" value="evaluationYearlyScoresList" status="st">
	    <tr>
	        <td colspan="7" class="tdBg" style="border:solid black 1px;">${st.index + 1}. <s:property value="MP7007_EXAM_NAME"/><input name="evaluationYearlyScoresList[<s:property value="#st.index"/>].MP7007_SEQ" value="${list.MP7007_SEQ}" type="hidden"/></td>
	    </tr>
	    <tr>
	        <td rowspan="3" class="tdBg3"><textarea name="evaluationYearlyScoresList[${st.index}].MP7007_EXAM_SUB_TITLE" style="width:100%;height:100%;background-color:transparent;border:none;overFlow:auto;" ><s:property value="MP7007_EXAM_SUB_TITLE"/></textarea></td>
	        <td colspan="3" class="tdBg3"><textarea name="evaluationYearlyScoresList[${st.index}].MP7007_EXAM_CONTENT" style="width:100%;height:100%;background-color:transparent;border:none;overFlow:auto;" ><s:property value="MP7007_EXAM_CONTENT"/></textarea></td>
	        <td class="tdBg3">Remarks</br>(Pls give reasons for rating 1 or 5)</td>
	        <td class="tdBg3">Weightage</br>(in %)</td>
	        <td class="tdBg4">Score</br>(Rating*Weightage)</td>
	    </tr>
	    <tr>
	        <td class="tdBg3">Self Assessment</td>
	        <td class="tdBg3">Reporting Manager</td>
	        <td class="tdBg3">Agreed Score</td>
	        <td rowspan="2" class="tdBg1"><textarea name="evaluationYearlyScoresList[${st.index}].MP7007_REMARKS" style="width:100%;height:60px;overFlow:auto;" ><s:property value="MP7007_REMARKS"/></textarea></td>
	        <td rowspan="2" class="tdBg1"><input id="weightage${st.index + 1}" name="evaluationYearlyScoresList[<s:property value="#st.index"/>].MP7007_WEIGHTAGE" value="${list.MP7007_WEIGHTAGE}" type="text" onKeyup="sc('${st.index + 1}')" style="border:none;"/></td>
	        <td rowspan="2" class="tdBg2"><input id="final${st.index + 1}" name="evaluationYearlyScoresList[<s:property value="#st.index"/>].MP7007_FINAL_SCORE" value="${list.MP7007_FINAL_SCORE}" type="text" style="border:none;" readonly="readonly"/></td>
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
</div>

<div style="width:100%;" align="center"><input type="submit" class="button" name="Submit" value="Save" <s:if test="optSave == 0" >disabled="disabled"</s:if>></div>

</s:form>
</body>
</html>