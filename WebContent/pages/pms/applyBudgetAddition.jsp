<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Expires" CONTENT="0">
<meta http-equiv="Cache-Control" CONTENT="no-cache">
<meta http-equiv="Pragma" CONTENT="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />

<script src="js/jquery1.4.2.js" type="text/javascript"></script>
<script src="js/jquery.form.js" type="text/javascript" ></script>

<style type="text/css">
#divPageMask{background-color:white; filter:alpha(opacity=50);left:0px;position:absolute;top:0px;}  
#divOpenWin{background-color:white;position: absolute;left:200px;top:100px;display:none;z-index:9999; width:300px;height:200px;border:solid 2px black;}  
</style>

<script type="text/javascript">
var closeWinFunc = window.close;
window.close = function() {
    window.open("", "_self");
    closeWinFunc();
}
function type1Change(){
	var pr1001Type = document.getElementById("pr1001Type").value;
	var param = {"pr1001Type" : pr1001Type};

	if(pr1001Type != "-1"){
		$("#type2Div").load("pages/pms/type2Info.action",param);
	}
}
function type2Change(){
	// nothing to do
}
function saveData(){
	var type1 = "";
	var type2 = "";
	var budget = "";
	var reason = "";
	
	if(document.getElementById("pr1001Type") != null){
		type1 = document.getElementById("pr1001Type").value;
	}
	if(document.getElementById("pr1001Type2") != null){
		type2 = document.getElementById("pr1001Type2").value;
	}
	if(document.getElementById("bugdet") != null){
		budget = document.getElementById("bugdet").value;
	}
	if(document.getElementById("reason") != null){
		reason = document.getElementById("reason").value;
	}

	var options = {
		url : "pages/pms/budgetAdditionSave.action",
		type : "post",
		dataType : "text",
		success : function(msg) {
			if(msg == 'update'){
				window.dialogArguments.document.getElementById('pageRefresh').click();
				window.close();
			}else{
				Open(msg);
			}
		}
	};
	if(type1 == "" || type1 == "-1" || type2 == "" || type2 == "-1" || budget == "" || budget == "-1" || reason == "" || reason == "-1"){
		alert("Please make sure you fill out all fields required.");
	}else{
		$("#form2").ajaxSubmit(options);
	}
}
function approve(flag){
	var options = {
		url : "pages/pms/budgetAdditionApprove.action?statusType=" + flag,
		type : "post",
		dataType : "text",
		success : function(msg) {
			alert(msg);
			window.dialogArguments.document.getElementById('pageRefresh').click();
			window.close();
		}
    };
	$("#form2").ajaxSubmit(options);
}
function checkBudgetAmount(){
	if(document.getElementById("budgetAmount") != null){
		var budgetAmount = document.getElementById("budgetAmount").value;
		var _value = parseInt(budgetAmount);

		if(isNaN(_value)){
			alert("The value cannot caontain letters.");
			document.getElementById("saveBtn").disabled = true;
		}else{
			document.getElementById("saveBtn").disabled = false;
		}
	}
}
</script>
<script type="text/javascript">
function Open(msg){
  //隐藏select控件
  DispalySelect(0);
  //显示遮罩层
  document.getElementById("divPageMask").style.display="block";
 //处理遮罩层
  resizeMask();
 window.onResize = resizeMask;
  //显示弹出窗口
  document.getElementById("divOpenWin").style.display="block";
  document.getElementById("msgId").innerText = msg;
}
function Close(){
  //显示select控件
  DispalySelect(1);
  //处理遮罩层
  divPageMask.style.width = "0px";
  divPageMask.style.height = "0px";
  divOpenWin.style.display = "none";
  window.onResize = null;

  document.getElementById("divOpenWin").style.display="none";
  document.getElementById("pr1001Type").value = "-1";
  document.getElementById("pr1001Type2").value = "-1";
  document.getElementById("bugdet").value = "0.0";
  document.getElementById("reason").value = "";
}
//页面遮罩
function resizeMask(){
 divPageMask.style.width = document.body.scrollWidth;
 divPageMask.style.height = document.body.scrollHeight;
 //divOpenWin.style.left = ((document.body.offsetWidth - divOpenWin.offsetWidth) / 2);
 //divOpenWin.style.top = ((document.body.offsetHeight - divOpenWin.offsetHeight) / 2);
}
function DispalySelect(val){  //显示和隐藏select控件 
  var dispalyType;
  var arrdispalyType=["hidden","visible"];
  var arrObjSelect=document.getElementsByTagName("select");
  for (i=0;i<arrObjSelect.length;i++)  {
    arrObjSelect[i].style.visibility=arrdispalyType[val];
  }
}
</script>

</head>

<body>
<form id="form2" name="form2" method="post">
<input id="mp4004Seq" name="mp4004Seq" value="${mp4004Seq}" type="hidden" />
<input id="pageType" name="pageType" value="${pageType}" type="hidden" />

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
  <tr>
    <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Budget Management</td>
    <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
  </tr>
  <tr>
    <td height='27px' class='menubar_function_text'>Operation Function: Apply Addition</td>
    <td class='menubar_menu_td' align='right'>
      <table border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="menubar_button" id="button_0"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td height='5px' colspan='2'></td>
  </tr>
</table>
<!-- 头部菜单 End -->

<div id="divPageMask"></div>  
<div id="divOpenWin"><center><a href="javascript:Close();">关闭</a></center><div id="msgId" style="border-top:solid 1px gray;color:red;font-weight:bold;font-size:18pt;"></div></div> 
<table cellpadding="2" cellspacing="1" width="100%" border="0" align="left">	<tr align="center">
	<tr>
		<td style="background-color:#9AADCD;" width="150px" align="right" class=td1>Type:</td>
		<td	class=td1><s:select onchange="type1Change()" id="pr1001Type" name="pr1001Type" value="pr1001Type" list="typeInfoList" style="width:220px;" theme="simple"/>	(Must Fill)</td>
	</tr>
	<tr>
		<td style="background-color:#9AADCD;" width="150px" align="right" class=td1>Item:</td>
		<td	class=td1>
            <div id="type2Div" style="float:left;">
	            <s:select id="pr1001Type2" name="pr1001Type2" value="pr1001Type2" list="typeInfoList2" theme="simple"/>
	        </div>	(Must Fill)
		</td>
	</tr>
	<tr>
		<td style="background-color:#9AADCD;" width="150px" align="right" class=td1 height=23>Budget Amount:</td>
		<td	class=td1><input id="budgetAmount" onkeyup="checkBudgetAmount()" name="bugdet" value="${bugdet}" type="text" style="width:80%;"/>(Must Fill)</td>
	</tr>
	<tr>
		<td style="background-color:#9AADCD;" width="150px" align="right" class=td1 height=23>Reason:</td>
		<td	class=td1><s:textarea id="reason" name="mp4004.MP4004_REASON" style="width:80%;height:100px;" theme="simple"></s:textarea>	(Must Fill)</td>
	</tr>
        <td	colspan="2"	class=td1 align="center">
<s:if test="pageType=='view'">
            <input type="button" onclick="approve('y')" class="button" name="Submit" value="Approve">	
            <input type="button" onclick="approve('n')" class="button" name="Submit" value="Not Approve">
</s:if>
<s:else>
            <input id="saveBtn" type="button" onclick="saveData()" class="button" name="Submit" value="Save Budget">
</s:else>
        </td>
	</tr>
</table>

</form>
</body>
</html>