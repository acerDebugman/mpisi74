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
<title></title>
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />

<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery1.4.2.js" type="text/javascript"></script>

<style type="text/css">
.textInput2{border-size:0px; background-color:transparent;border-style:none;}
</style>

<script type="text/javascript">
var oldId = "0";
function changestyle(id){
	var txtObj = document.getElementById(id);
	var oldObj = document.getElementById(oldId);
	if(txtObj != null){
		txtObj.className="text_input";
		txtObj.readOnly=false;
		if(oldObj != null && id != oldId){
			oldObj.className="textInput2";
		}
		oldId = id;
	}
}
function lostFocus(id){
	var txtObj = document.getElementById(id);
	if(txtObj != null){
		txtObj.className="textInput2";
	}
}
function openAddPositionPage(){
	var seqObj = document.getElementById("mp0002Seq");
	
	if(seqObj == null || seqObj == 'undefined'){
		alert("Cannot get parameter object.");
	}else{
		var seq = seqObj.value;
		var popstyle="dialogTop:300px;dialogLeft:300px;help:no;center:yes;dialogHeight:200px;dialogWidth:400px;status:yes;resizable:no;scroll:yes";
		window.showModalDialog("positionInfoAddInit.action?mp0002Seq=" + seq, window, popstyle);
	}
}
function reloadData(){
	 var mp0002Seq = document.getElementById("mp0002Seq").value;
	 var param = {"mp0002Seq" : mp0002Seq};
	 
	 $("#mp0008InfoListDiv").load("departmentPositionRefresh.action",param);
}
</script>

</head>
<body>
<form id="form2" name="form2" action="departmentPosotionSave" method="post">
<input id="mp0002Seq" name="mp0002Seq" value="${mp0002Seq}" type="hidden" />
<input id="optEdit" name="optEdit" value="${optEdit}" type="hidden" />
<input id="optAdd" name="optAdd" value="${optAdd}" type="hidden" />

<s:if test="optAdd == 1">
<table cellspacing="1" border="0" width="1020px" style="background-color:White;border-width:0px;margin-top:5px;">
    <tr>
        <td align="left">
            <input type="button" onclick="openAddPositionPage()" value="Add New Job Title"/>
            <input id="refreshPage" type="button" onclick="reloadData()" style="display:none;" value=""/>
        </td>
    </tr>
    <tr>
        <td style="color:red;"><s:property value="errMsg"/></td>
    </tr>
</table>
</s:if>

<table cellspacing="1" border="0" style="background-color:White;border-width:0px;margin-top:10px;">
    <tr class="table_title" align="center">
        <th scope="col" width="50px">No.</th>
        <th scope="col" width="400px">Job Title</th>
    </tr>
</table>

<div id="mp0008InfoListDiv" style="height:600px;width:480px;border:1px solid white;overflow:auto;">
<table cellspacing="1" border="0" style="background-color:White;border-width:0px;">
<s:iterator id="mp0008InfoList" value="mp0008InfoList" status="st">
   <tr class="row">
       <td width="50px" align="center">${st.index + 1}</td>
       <td width="400px" align="left"><s:property value="MP0008_POSITION_NAME_E"/></td>
   </tr>
</s:iterator>
</table>
</div>

</form>
</body>
</html>