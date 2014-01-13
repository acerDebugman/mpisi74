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
function rowClick(id){
	// nothing to do
}
</script>

</head>
<body>
<s:form action="examEvaluationMonthly2InfoSave" method="post" theme="simple">
<input id="pageType" name="pageType" value="${pageType}" type="hidden" />
<input id="commonSeq" name="commonSeq" value="${commonSeq}" type="hidden" />
<input id=param1 name="param1" value="${param1}" type="hidden" /><!--用来记录考核题目编码  -->
<input id=param2 name="param2" value="${param2}" type="hidden" />
<input id="optSave" name="optSave" value="${optSave}" type="hidden" />
<input id="optCancel" name="optCancel" value="${optCancel}" type="hidden" />

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
	<tr>
		<td class='menubar_title' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img border='0'src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Appraisal Management(Monthly)</td>
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
			<div id="tabLabel__1" onclick='tabClick(1,3)' style="float:left;padding-left:10px;padding-right:10px;background-image: url(images/menu/tab_unactive_bg.gif);">Item Information</div>
			<div style="float:left;"><img id='tabImgRight__1' height='22' src='images/menu/tab_unactive_right.gif' width="3px"></div>
        </td>
        <!--按钮 End-->
    </tr>
</table>

<div id='tabContent__0' style='display:block;'>
	<table cellpadding="2" cellspacing="1" border="0" width="100%" align="center">
		<tr>
			<td	width="26%"	align="right" class=td1>Department:</td>
			<td	width="74%"	class=td1><s:property value="mp8005.MP8005_DEPARTMENT_NAME"/></td>
		</tr>
		<tr>
			<td	width="26%"	align="right" class=td1>Manager:</td>
			<td	width="74%"	class=td1><s:property value="mp8005.MP8005_MANAGER_NAME"/></td>
		</tr>
		<tr>
			<td	width="26%"	align="right" class=td1>Review Period:</td>
			<td	width="74%"	class=td1><s:property value="mp8005.MP8005_REVIEW_PERIOD"/></td>
		</tr>
		<tr>
			<td	width="26%"	align="right" class=td1>Name of Appraiser:</td>
			<td	width="74%"	class=td1>
			    <input id="empName" name="mp8005.MP8005_APPRAISER_NAME" value="${mp8005.MP8005_APPRAISER_NAME}" type="text" style="border:none;" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td	width="26%"	align="right" class=td1>Comment:</td>
			<td	width="74%"	class=td1><s:textarea id="qt" name="mp8005.MP8005_COMMENT" style="width:100%;height:60px;" theme="simple"></s:textarea></td>
		</tr>
	</table>
</div>

<div id='tabContent__1' style='display:none;height:500px;overflow-y:scroll;'>
	<table cellspacing="0" cellpadding="0" style="width:100%;">
		<tr align="center">
			<td	class="td1" colspan="7" style="color:red;">(5)=Exceptional (4)=Exceeds Requirements (3)=Meet Requirement (2)=Gets By (1)=Needs Work</td>
		</tr>
		<tr align="center" style="background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px">
		    <th scope="col" class="tdBg">Seq</th>
			<th scope="col" class="tdBg">Title</th>
			<th scope="col" class="tdBg">Exceptional</th>
			<th scope="col" class="tdBg">Exceeds Requirements</th>
			<th scope="col" class="tdBg">Meet Requirement</th>
			<th scope="col" class="tdBg">Gets By</th>
			<th scope="col" class="tdBg">Needs Work</th>
		</tr>
<s:iterator value="evaluationMonthly2ScoresList" status="st">
		<tr id="<s:property value="MP8006_SEQ"/>" align="left" style="height:28px;background-color:green;" onmousedown="rowClick('<s:property value="MP8006_SEQ"/>')">
		    <td width="50px" align="center" class="tdBg1">${st.index + 1}</td>
		    <td class="tdBg1"><s:property value="MP8006_EXAM_NAME"/></td>
		    <td width="80px" class="tdBg1"><div class="checkdiv"><input id="sa5_${st.index + 1}" onclick="cbc('sa5_${st.index + 1}','${st.index + 1}')" type="checkbox" name="scoreArray5" value="${MP8006_EXAM_CODE}" <s:if test="scoreArray5.contains(MP8006_EXAM_CODE)">checked="checked"</s:if> class="checkbox"/></div></td>
		    <td width="150px" class="tdBg1"><div class="checkdiv"><input id="sa4_${st.index + 1}" onclick="cbc('sa4_${st.index + 1}','${st.index + 1}')" type="checkbox" name="scoreArray4" value="${MP8006_EXAM_CODE}" <s:if test="scoreArray4.contains(MP8006_EXAM_CODE)">checked="checked"</s:if> class="checkbox"/></div></td>
		    <td width="120px" class="tdBg1"><div class="checkdiv"><input id="sa3_${st.index + 1}" onclick="cbc('sa3_${st.index + 1}','${st.index + 1}')" type="checkbox" name="scoreArray3" value="${MP8006_EXAM_CODE}" <s:if test="scoreArray3.contains(MP8006_EXAM_CODE)">checked="checked"</s:if> class="checkbox"/></div></td>
		    <td width="50px" class="tdBg1"><div class="checkdiv"><input id="sa2_${st.index + 1}" onclick="cbc('sa2_${st.index + 1}','${st.index + 1}')" type="checkbox" name="scoreArray2" value="${MP8006_EXAM_CODE}" <s:if test="scoreArray2.contains(MP8006_EXAM_CODE)">checked="checked"</s:if> class="checkbox"/></div></td>
		    <td width="80px" class="tdBg1"><div class="checkdiv"><input id="sa1_${st.index + 1}" onclick="cbc('sa1_${st.index + 1}','${st.index + 1}')" type="checkbox" name="scoreArray1" value="${MP8006_EXAM_CODE}" <s:if test="scoreArray1.contains(MP8006_EXAM_CODE)">checked="checked"</s:if> class="checkbox"/></div></td>
		</tr>
</s:iterator>
	</table>
</div>

<div style="width:100%;margin-top:10px;" align="center"><input type="submit" class="button" name="Submit" value="Save" <s:if test="optSave == 0" >disabled="disabled"</s:if>/></div>

</s:form>
</body>
</html>