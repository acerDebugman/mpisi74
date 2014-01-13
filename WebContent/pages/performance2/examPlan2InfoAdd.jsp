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
<script src="js/jquery1.4.2.js" type="text/javascript" ></script>

<style type="text/css">
.tdBg{background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px;color:#FFFFFF;text-align:left;margin:0px;padding:0px;}
.tdBg1{border-bottom:solid black 1px;border-left:solid black 1px;}
.tdBg2{border-bottom:solid black 1px;border-left:solid black 1px;border-right:solid black 1px;}
.tdBg3{border-bottom:solid black 1px;border-left:solid black 1px;background-color:#C4D8ED;color:#000000;}
.tdBg4{border-bottom:solid black 1px;border-left:solid black 1px;border-right:solid black 1px;background-color:#C4D8ED;color:#000000;}
</style>

<script type="text/javascript">
function s(){
	var key = document.getElementById("commonSeq").value;
	var popstyle="dialogTop:80px;dialogLeft:650px;help:no;center:yes;dialogHeight:750px;dialogWidth:550px;status:no;resizable:yes;scroll:no;loacation:no;toolbar:no;";
	window.showModalDialog("examQuestion2SelectInit.action?commonSeq=" + key, window, popstyle);
}
function t(){
	var key = document.getElementById("commonSeq").value;
	var popstyle="dialogTop:80px;dialogLeft:650px;help:no;center:yes;dialogHeight:700px;dialogWidth:800px;status:no;resizable:yes;scroll:no;loacation:no;toolbar:no;";
	window.showModalDialog("employeeSelectInit2.action?pageType=performance&commonSeq=" + key, window, popstyle);
}
function d(){
	var key = document.getElementById("commonSeq").value;
	var popstyle="dialogTop:80px;dialogLeft:650px;help:no;center:yes;dialogHeight:700px;dialogWidth:800px;status:no;resizable:yes;scroll:no;loacation:no;toolbar:no;";
	window.showModalDialog("departmentSelectInit.action?pageType=performance2&commonSeq=" + key, window, popstyle);
}
/* function e(){
	var key = document.getElementById("commonSeq").value;
	var popstyle="dialogTop:80px;dialogLeft:650px;help:no;center:yes;dialogHeight:650px;dialogWidth:550px;status:no;resizable:yes;scroll:no;loacation:no;toolbar:no;";
	window.showModalDialog("examQuestionSelectMngInit.action?commonSeq=" + key,window,popstyle);
} */
function f(){
 	var key = document.getElementById("commonSeq").value;
    var param = {"commonSeq" : key};
	$("#itemsInfoDiv").load("examPlan2ItemInfoRefresh.action",param);
}
function Del(id){
	var key = document.getElementById("commonSeq").value;
    var param = {"param1" : id,"commonSeq" : key};
	$("#itemsInfoDiv").load("examPlan2ItemInfoDelete.action",param);
}
function e(){
 	var key = document.getElementById("commonSeq").value;
    var param = {"commonSeq" : key};
	$("#employeeInfoDiv").load("examPlan2EmpInfoRefresh.action",param);
}
function DelEmp(id){
	var key = document.getElementById("commonSeq").value;
    var param = {"param1" : id,"commonSeq" : key};
    $("#employeeInfoDiv").load("examPlan2EmpInfoDelete.action",param);
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
</script>

</head>
<body>
<s:form action="examPlan2InfoSave" method="post" theme="simple">
<input id="pageType" name="pageType" value="${pageType}" type="hidden" />
<input id="param2" name="param2" value="${param2}" type="hidden" />
<input id="commonSeq" name="commonSeq" value="${commonSeq}" type="hidden" />
<input id="optSave" name="optSave" value="${optSave}" type="hidden" />
<input id="optCancel" name="optCancel" value="${optCancel}" type="hidden" />
<input type="button" onclick="f()" name="btnRefresh" value="refresh item" id="btnRefresh" style="display:none;"/>
<input type="button" onclick="e()" name="empRefresh" value="refresh employee" id="empRefresh" style="display:none;"/>
<input type="button" onclick="e()" name="depRefresh" value="refresh employee" id="depRefresh" style="display:none;"/>

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
	<tr>
		<td class='menubar_title' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img border='0'src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Performance Appraisal Plan Management</td>
		<td class='menubar_readme_text' valign='bottom' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help?</td>
	</tr>
</table>
<!-- 头部菜单 End -->

<table cellpadding="0" cellspacing="0" border="0" style="margin-bottom:5px;width:100%;">
	<tr>
		<th colspan=2 style="text-align:center;">Performance Appraisal Plan Management －－ Add New Plan
	        <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
	            <s:param>errMsg</s:param>
	        </s:fielderror>
<!--  	        <s:property value="errMsg"/> -->
		</th>
	</tr>
    <tr>
        <!--按钮　Start-->
        <td>
			<div style="float:left;"><img id='tabImgLeft__0' height='22' src='images/menu/tab_active_left.gif' width="3px"></div>
			<div id="tabLabel__0" onclick='tabClick(0,3)' style="float:left;padding-left:10px;padding-right:10px;background-image: url(images/menu/tab_active_bg.gif);">Plan Information</div>
			<div style="float:left;"><img id='tabImgRight__0' height='22' src='images/menu/tab_active_right.gif' width="3px"></div>
			        
			<div style="float:left;"><img id='tabImgLeft__1' height='22' src='images/menu/tab_unactive_left.gif' width="3px"></div>
			<div id="tabLabel__1" onclick='tabClick(1,3)' style="float:left;padding-left:10px;padding-right:10px;background-image: url(images/menu/tab_unactive_bg.gif);">Appraisal Questionnaire Information</div>
			<div style="float:left;"><img id='tabImgRight__1' height='22' src='images/menu/tab_unactive_right.gif' width="3px"></div>
			
			<div style="float:left;"><img id='tabImgLeft__2' height='22' src='images/menu/tab_unactive_left.gif' width="3px"></div>
			<div id="tabLabel__2" onclick='tabClick(2,3)' style="float:left;padding-left:10px;padding-right:10px;background-image: url(images/menu/tab_unactive_bg.gif);">Related Department Information</div>
			<div style="float:left;"><img id='tabImgRight__2' height='22' src='images/menu/tab_unactive_right.gif' width="3px"></div>
        </td>
        <!--按钮 End-->
    </tr>
</table>

<div id='tabContent__0' style='display:block;'>
	<table cellpadding="2" cellspacing="1" border="0" width="100%" align="center" style="margin-top:5px;">
		<tr>
			<td	width="26%"	align="right" class=td1>Plan Name:</td>
			<td	width="74%"	class=td1>
			    <input name="mp8002.MP8002_TITLE" value="${mp8002.MP8002_TITLE}" type="text" style="width:80%;"/>  (Must Fill)
		        <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
		            <s:param>mp8002.MP8002_TITLE</s:param>
		        </s:fielderror>
			</td>
		</tr>
		<tr>
			<td	width="26%"	align="right" class=td1>Department:</td>
			<td	width="74%"	class=td1><s:select id="param3" name="param3" value="param3" list="commonDepartmentList" style="width:80%;" theme="simple"/></td>
		</tr>
		<tr>
			<td	width="26%"	align="right" class=td1>Year:</td>
			<td	width="74%"	class=td1><input name="mp8002.MP8002_YEAR" value="${mp8002.MP8002_YEAR}" type="text" style="width:80%;"/></td>
		</tr>
		<tr>
			<td	width="26%"	align="right" class=td1>Season:
			<td	width="74%"	class=td1><s:select id="param4" name="param4" value="param4" list="commonSeasonList" theme="simple" style="width:80%;"/></td>
		</tr>
	<s:if test="pageType == 'edit'">
		<tr>
			<td	width="26%"	align="right" class=td1 height=23>Plan Status:</td>
			<td	width="74%"	class=td1><s:select id="commonStatus" name="commonStatus" value="commonStatus" list="commonStatusList" theme="simple"/></td>
		</tr>
	</s:if>
		<tr>
			<td	width="26%"	align="right" class=td1 height=23>Comment:</td>
			<td	width="74%"	class=td1><s:textarea name="mp8002.MP8002_COMMENT" style="width:80%;height:60px;" theme="simple"></s:textarea></td>
		</tr>
		<tr align="center">
			<td	class="td1" colspan="2"><input type="submit" class="button" name="Submit1" value="Save" <s:if test="optSave == 0" >disabled="disabled"</s:if>/></td>
		</tr>
	</table>
</div>

<div id='tabContent__1' style='display:none;height:500px;overflow-y:scroll;'>
	<div style="width:100%;float:left;margin-top:10px;text-align:center;">
	    <input type="button" class="input0" name="addQuestion" onclick="s()" value="Add Appraisal Questionnaire" <s:if test="optSave == 0">disabled="disabled"</s:if>/>
	</div>
	
	<div id="itemsInfoDiv" style="width:100%;float:left;overflow:auto;">
	<table cellspacing="0" cellpadding="0" style="border:0;width:100%;">
	    <tr align="center" class="tdBg">
	        <th scope="col" class="tdBg">Seq</th>
	        <th scope="col" class="tdBg">Appraisal Questionnaire</th>
	        <th scope="col" class="tdBg">&nbsp;</th>
	    </tr>
	<s:iterator id="list" value="examItemsInfo2" status="st">
	    <tr align="left" style="height:28px;">
	        <td width="50px" align="center">${st.index + 1}<input name="examItemsInfo[<s:property value="#st.index"/>].MP8003_SEQ" value="${list.MP8003_SEQ}" type="hidden"/></td>
	        <td><s:property value="MP8003_EXAM_TITLE"/></td>
	        <td width="50px" align="center">
	            <input type="button" onclick="Del('<s:property value="MP8003_SEQ"/>')" name="delBtn" value="Del" id="delBtn" />
	        </td>
	    </tr>
	</s:iterator>
	</table>
	</div>
</div>

<div id='tabContent__2' style='display:none;height:500px;overflow-y:scroll;'>
	<div style="width:100%;float:left;margin-top:10px;text-align:center;">
	    <input type="button" class="input0" name="addQuestion" onclick="d()" value="Select Department" <s:if test="optSave == 0">disabled="disabled"</s:if>/>
	</div>
	
	<div id="employeeInfoDiv" style="width:100%;float:left;overflow:auto;">
	<table cellspacing="0" cellpadding="0" style="border:0;width:100%;">
    <tr align="center" class="tdBg">
        <th scope="col" class="tdBg">Seq</th>
        <th scope="col" class="tdBg">Department</th>
        <th scope="col" class="tdBg">&nbsp;</th>
    </tr>
<s:iterator id="list" value="relatedDepartmentListInfo" status="st">
    <tr align="left" style="height:28px;">
        <td width="50px" align="center">${st.index + 1}</td>
        <td><s:property value="MP8004_DEPARTMENT"/></td>
        <td width="50px" align="center">
            <input class="input0" type="button" onclick="DelEmp('<s:property value="MP8004_SEQ"/>')" name="delEmpBtn" value="Del" id="delEmpBtn" />
        </td>
    </tr>
</s:iterator>
</table>
	</div>
</div>

</s:form>
</body>
</html>