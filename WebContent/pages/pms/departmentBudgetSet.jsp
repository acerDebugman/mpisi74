<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../../images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="../../css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="../../css/Site_Css.css" rel="stylesheet" type="text/css" />

<script src="../../js/jquery1.4.2.js" type="text/javascript"></script>

<style type="text/css">
.textInput2{border-size:0px; background-color:transparent;border-style:none;}
#divPageMask{background-color:white; filter:alpha(opacity=50);left:0px;position:absolute;top:0px;}  
#divOpenWin{background-color:white;position: absolute;left:200px;top:100px;display:none;z-index:9999; width:300px;height:200px;border:solid 2px black;}  
</style>

<script language="javascript">
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
function lostFocus(id,max){
	var txtObj = document.getElementById(id);
	
	var temp1 = parseFloat(txtObj.value);
	var temp2 = parseFloat(max);
	
	if(txtObj != null){
		txtObj.className="textInput2";
		//alert(temp1 + "|" + temp2);
/* 		if(temp1 > temp2){
			txtObj.value = "0";
			alert("The maximum amount is " + max);
		} */
	}
}
function checkLimit(id,max){
	var txtObj = document.getElementById(id);
	var temp1 = parseFloat(txtObj.value);
	var temp2 = parseFloat(max);
	
	// 如果预算金额为0，表示不设预算上限
	if(txtObj != null && temp2 > 0 ){
		if(temp1 > temp2){
			alert("The maximum amount is " + max);
			txtObj.value = "0.00";
		}
	}
}
function showData(){
	var _year = "";
	var _month = "";
	var _departmentId = "";
	var _pageType = "";
	var _mainClass = "";
	
	if(document.getElementById("currentYear") != null){
		_year = document.getElementById("currentYear").value;
	}
	if(document.getElementById("currentMonth") != null){
		_month = document.getElementById("currentMonth").value;
	}
	if(document.getElementById("departmentId") != null){
		_departmentId = document.getElementById("departmentId").value;
	}
	if(document.getElementById("pageType") != null){
		_pageType = document.getElementById("pageType").value;
	}
	if(document.getElementById("mainClass") != null){
		_mainClass = document.getElementById("mainClass").value;
	}
	
	if(_month == ""){
		_month = parseInt(new Date().getMonth(),10) + 1;
	    var nextMonthBtnObj = document.getElementById("nextMonthBtn").disabled;
		if(nextMonthBtnObj == true){
			_month = _month + 1;
		}
	}

	param = {"currentYear" : _year,"currentMonth" : _month,"departmentId" : _departmentId,"pageType" : _pageType,"mainClass" : _mainClass};
	$("#resultDiv").load("departmentBudgetInfoList.action",param);
}
function changeMonth(month){
	if(month == '2'){
		month = "next";
		document.getElementById("currentMonthBtn").disabled = false;
		document.getElementById("nextMonthBtn").disabled = true;
	}else{
		month = "current";
		document.getElementById("currentMonthBtn").disabled = true;
		document.getElementById("nextMonthBtn").disabled = false;
	}
 	var _departmentId = "";
	if(document.getElementById("departmentId") != null){
		_departmentId = document.getElementById("departmentId").value;
	}
	/*	param = {"currentMonth" : month,"departmentId" : _departmentId};
	$("#departmentBudgetInfoDiv").load("departmentBudgetInfoList.action",param); */
	if(document.getElementById("pageType") != null){
		document.getElementById("pageType").value = _departmentId;
	}
	form2.action = "budgetSetInit.action?currentMonth=" + month;
	form2.submit();
}
function superaddBudget(id,code,name){
	var popstyle="dialogTop:200px;dialogLeft:300px;help:no;center:yes;dialogHeight:300px;dialogWidth:1000px;status:yes;resizable:yes;scroll:yes;loacation:no;toolbar:yes;";
	window.showModalDialog("superaddBudgetInfoInit.action?mp4003Seq=" + id + "&budgetCode=" + code + "&budgetName=" + name,window,popstyle);
}
function confirmBudget(type){
	if(document.getElementById("budgetStatus") != null){
		document.getElementById("budgetStatus").value = type;
	}
	form2.action = "departmentBudgetSave.action";
	form2.submit();
}
function confirmBudget2(type){
	if(document.getElementById("budgetStatus") != null){
		document.getElementById("budgetStatus").value = type;
	}
 	var _departmentId = "";
	if(document.getElementById("departmentId") != null){
		_departmentId = document.getElementById("departmentId").value;
	}
	
	form2.action = "departmentBudgetSave2.action?departmentId" + _departmentId;
	form2.submit();
}
</script>

</head>
<body>
<form id="form2" name="form2" method="post">
<input id="currentMonthFlag" name="currentMonthFlag" value="${currentMonthFlag}" type="hidden" />
<input id="btnName1" name="btnName1" value="${btnName1}" type="hidden" />
<input id="btnName2" name="btnName2" value="${btnName2}" type="hidden" />
<input type="button" onclick="showData()" name="btnRefresh" value="refresh" id="btnRefresh" style="display:none;"/>


<!-- 
<div id="msgInfo" onMouseOut="msgBoxEvent()" style="display:none;z-index:9999;width:300px;height:200px;position:absolute;left:200px;top:300px;border:solid 3px blue;background-color:white;">msg</div>
<div style="position:absolute;z-index:8888;width:100%;height:100%;filter:Alpha(Opacity=50)"></div>
-->
<div id="divPageMask"></div>  
<div id="divOpenWin"><center><a href="javascript:Close();">关闭</a></center><div id="msgId" style="border-top:solid 1px gray;"></div></div> 

<div>
<div id="content" style="z-index:0;">
<s:if test="pageType == 'view'">
    Year:<s:select id="currentYear" name="currentYear" list="yearInfoList" onchange="showData()" theme="simple"/>&nbsp;&nbsp;
    Month:<s:select id="currentMonth" name="currentMonth" list="monthInfoList" onchange="showData()" theme="simple"/>
</s:if>
<s:if test="optAll == 1">
    Department:<s:select id="departmentId" name="departmentId" list="departmentInfoList" onchange="showData()" theme="simple"/>
</s:if>
<s:else>
    Department:<s:select id="departmentId" name="departmentId" list="departmentInfoList" onchange="showData()" disabled="true" theme="simple"/>
</s:else>
Main Class:<s:select id="mainClass" name="mainClass" list="mainClassList" onchange="showData()" theme="simple"/>
<s:if test="pageType != 'view'">
<s:if test="currentMonthFlag == 0">
            <input id="currentMonthBtn" type="button" onclick="changeMonth('1')" value="<s:property value="btnName1"/>"/>
            <input id="nextMonthBtn" type="button" onclick="changeMonth('2')" value="<s:property value="btnName2"/>" disabled="disabled"/>
</s:if>
<s:else>
            <input id="currentMonthBtn" type="button" onclick="changeMonth('1')" value="<s:property value="btnName1"/>" disabled="disabled"/>
            <input id="nextMonthBtn" type="button" onclick="changeMonth('2')" value="<s:property value="btnName2"/>" />
</s:else>
</s:if>
</div>

<div id="resultDiv">
<input id="optEdit" name="optEdit" value="${optEdit}" type="hidden" />
<input id="optSave" name="optSave" value="${optSave}" type="hidden" />
<input id="optAll" name="optAll" value="${optAll}" type="hidden" />
<input id="optApproval" name="optApproval" value="${optApproval}" type="hidden" />
<input id="budgetStatus" name="budgetStatus" value="${budgetStatus}" type="hidden" />
<input id="pageType" name="pageType" value="${pageType}" type="hidden" />

<s:if test="pageType != 'view'">
<table cellspacing="1" border="0" width="1020px" style="background-color:White;border-width:0px;margin-top:5px;">
    <tr>
        <td align="center">
<s:if test="optSave == 1 && budgetStatus == 0">
            <input type="button" onclick="confirmBudget('0')" value="Save Budget"/>
            <input id="departmentSave" type="button" onclick="confirmBudget('1')" value="Send to Accounting" />
</s:if>
<s:else>
            <input type="button" onclick="confirmBudget('0')" value="Save Budget" disabled="disabled"/>
            <input id="departmentSave" type="button" onclick="confirmBudget('1')" value="Send to Accounting"  disabled="disabled"/>
</s:else>
<s:if test="optApproval == 1 && budgetStatus == 1">
            <input id="approve" type="button" onclick="confirmBudget2('2')" value="Approve" />
            <input id="notApprove" type="button" onclick="confirmBudget2('0')" value="Not Approve" />
</s:if>
<s:else>
            <input id="approve" type="button" onclick="confirmBudget2('2')" value="Approve" disabled="disabled"/>
            <input id="notApprove" type="button" onclick="confirmBudget2('0')" value="Not Approve" disabled="disabled"/>
</s:else>
        </td>
    </tr>
    <tr>
        <td style="color:red;"><s:property value="errMsg"/></td>
    </tr>
</table>
</s:if>

<table cellspacing="1" border="0" style="background-color:White;border-width:0px;margin-top:5px;width:1400px;">
    <tr class="table_title" align="center">
        <th scope="col" width="50px">No.</th>
        <th scope="col" width="200px">Name</th>
        <th scope="col" width="100px">Main Class</th>
        <th scope="col" width="200px">Type</th>
        <th scope="col" width="100px">Month</th>
        <th scope="col" width="80px">Limit</th>
        <th scope="col" width="120px">Budget Amount</th>
        <th scope="col" width="130px">Additional Budget</th>
        <th scope="col" width="120px">Expenditure</th>
        <th scope="col" width="100px">Status</th>
        <th scope="col">-</th>
    </tr>
</table>

<div id="departmentBudgetInfoDiv" style="height:600px;width:1400px;border:0px solid red;overflow-y:scroll;">
<%-- <s:if test="pageType == 'view' && budgetStatus == 0">
 <div style="color:red;font-size:30pt;margin-top:30px;text-align:center;">No Data</div>
</s:if>
<s:else> --%>
<table cellspacing="1" border="0" style="background-color:White;border-width:0px;">
<s:iterator id="mp4003InfoList" value="mp4003InfoList" status="st">
   <tr class="row">
       <td width="50px" align="center">${st.index + 1}</td>
<s:if test="MP4003_ACCOUNT_DESC != ''">
       <td width="200px" align="left" style="text-decoration:underline;" onclick="Open('<s:property value="MP4003_ACCOUNT_DESC"/>')"><s:property value="MP4003_ACCOUNTING_NAME"/></td>
</s:if>
<s:else>
       <td width="200px" align="left"><s:property value="MP4003_ACCOUNTING_NAME"/></td>
</s:else>
       <td width="100px" align="center"><s:property value="MP4003_TYPE_NAME"/></td>
       <td width="200px" align="left"><s:property value="MP4003_ACCOUNT_TYPE_NAME"/></td>
       <td width="100px" align="center"><s:property value="MP4003_MONTH_NAME"/></td>
       <td width="80px" align="right"><s:property value="MP4003_AMOUNT_MAX"/></td>
<s:if test="pageType != 'view' && optEdit == 1 && MP4003_STATUS == 0">
       <td width="120px" align="right">
           <input id="${st.index + 1}" onkeyup="checkLimit('${st.index + 1}','<s:property value="MP4003_AMOUNT_MAX"/>')"  name="amountArray" value="${amountArray[st.index]}" type="text" onmouseover="changestyle('${st.index + 1}')" onmouseout="lostFocus('${st.index + 1}','<s:property value="MP4003_AMOUNT_MAX"/>')" readonly="readonly" class="textInput2" style="width:100%;text-align:right;"/>
       </td>
</s:if>
<s:else>
       <td width="120px" align="right"><s:property value="MP4003_AMOUNT"/></td>
</s:else>
       <td width="130px" align="right" style="color:red;"><s:property value="MP4003_AMOUNT3"/></td>
       <td width="120px" align="right" style="color:green;"><s:property value="MP4003_AMOUNT4"/></td>
       <td width="100px" align="center"><s:property value="MP4003_STATUS_NAME"/></td>
       <td width="150px" align="right">
<s:if test="pageType != 'view' && MP4003_STATUS == 2">
           <input type="button" onclick="superaddBudget('<s:property value="MP4003_SEQ"/>','<s:property value="MP4003_ACCOUNTING_NUM"/>','<s:property value="MP4003_ACCOUNTING_NAME"/>')" name="addBtn" value="Apply Addition" id="addBtn" disabled="disabled"/>
</s:if>
<s:else>
           <input type="button" onclick="superaddBudget('<s:property value="MP4003_SEQ"/>','<s:property value="MP4003_ACCOUNTING_NUM"/>','<s:property value="MP4003_ACCOUNTING_NAME"/>')" name="addBtn" value="Apply Addition" id="addBtn" disabled="disabled"/>
</s:else>
       </td>
   </tr>
</s:iterator>
</table>
<%-- </s:else> --%>
</div>

<table cellspacing="1" border="0" style="background-color:White;border-width:0px;margin-top:0px;width:1400px;">
    <tr class="row" align="center">
<s:if test="pageType == 'view' && budgetStatus == 0">
        <td colspan="6" width="670px" align="right">0</td>
        <td width="130px" align="right">0</td>
        <td width="120px" align="right">0</td>
        <td width="272px">&nbsp;</td>
</s:if>
<s:else>
        <td colspan="6" width="670px" align="right"><s:property value="budgetAmount1"/></td>
        <td width="130px" align="right"><s:property value="budgetAmount3"/></td>
        <td width="120px" align="right"><s:property value="budgetAmount4"/></td>
        <td width="272px">&nbsp;</td>
</s:else>
    </tr>
</table>

</div>

</div>
</form>
</body>
</html>