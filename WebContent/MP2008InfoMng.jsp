<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<title></title>
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<!-- <link href="css/skins2/main.css" rel="stylesheet" type="text/css" /> -->

<script src="js/calendar.js" type="text/javascript" ></script>
<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src = "js/skins2/Main82.js" type="text/javascript"></script>
<script src="js/jquery1.4.2.js" type="text/javascript"></script>
<script src="js/jquery.form.js" type="text/javascript" ></script>
<script src="js/jquery.pager.js" type="text/javascript"></script>

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

#pager ul.pages {display:block;border:none;text-transform:uppercase;font-size:10px;margin:1px 0 10px;padding:0;}
#pager ul.pages li {list-style:none;float:left;border:1px solid #65AB31;text-decoration:none;margin:0 5px 0 0;padding:5px;}
#pager ul.pages li:hover {border:1px solid #003f7e;}
#pager ul.pages li.pgEmpty {border:1px solid #000000;color:#000000;background-color:grey;}
#pager ul.pages li.pgCurrent {border:1px solid #003f7e;color:#000;font-weight:700;background-color:#65AB31;}
.tdBg{background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px;color:white;text-align:center;margin:0px;padding:0px;}
.tdBorder{border-bottom:1px solid #2F589C;}
.checkdiv{ float:left; margin:4px 2px 0 7px; height:20px; width:20px; border:1px solid green; overflow:hidden; }
.checkbox{ height:32px; width:32px; border:1px solid green; margin-left:-8px; margin-top:-8px; text-align:center; line-height:32px;}
</style>

<script type="text/javascript">
window.document.onkeydown = keyStroke;
function keyStroke(){
	if (window.event.keyCode==13){
		window.event.keyCode=9;
	}
}
$(document).ready(function() {
    var _pageNum = $("#pageNum").val();
    document.getElementById("commonSeq").value = "";
    if(_pageNum == 0){
    	_pageNum = 1;
    }
    $("#pager").pager({ pagenumber: _pageNum, pagecount: $("#pageCount").val(), buttonClickCallback: PageClick });
});
PageClick = function(pageclickednumber) {
    $("#pager").pager({ pagenumber: pageclickednumber, pagecount: $("#pageCount").val(), buttonClickCallback: PageClick });
    var param1 = $("#param1").val();
    var param2 = $("#param2").val();
    var param3 = $("#param3").val();
    var param4 = $("#param4").val();
    var param5 = $("#param5").val();
    var param6 = $("#param6").val();
    var param = {"pageNum" : pageclickednumber,"param1" : param1,"param2" : param2,"param3" : param3,"param4" : param4,"param5" : param5,"param6" : param6};
    document.getElementById("pageNum").value = pageclickednumber;
    $("#MP2008InfoListDiv").load("mp2008InfoPageClick.action",param);
    document.getElementById("commonSeq").value = "";
}
</script>
<script type="text/javascript">
function pageRefresh(){
    var param1 = $("#param1").val();
    var param2 = $("#param2").val();
    var param3 = $("#param3").val();
    var param4 = $("#param4").val();
    var param5 = $("#param5").val();
    var param6 = $("#param6").val();
    var _pageNum = document.getElementById("pageNum").value;
    var param = {"pageNum" : _pageNum,"param1" : param1,"param2" : param2,"param3" : param3,"param4" : param4,"param5" : param5,"param6" : param6};
	$("#MP2008InfoListDiv").load("mp2008InfoPageClick.action",param);
	document.getElementById("commonSeq").value = "";
}
function rowClick(id,empNum,status,depId){
	//alert(id + "|" + empNum + "|" + status);
	if(event.button == 2){
/* 		document.getElementById("menuBar").style.display = 'block';
		document.getElementById("menuBar").style.position = 'absolute';
		document.getElementById("menuBar").style.left = mouseX;
		document.getElementById("menuBar").style.top = mouseY; */
	}else if(event.button == 1){
		var _oldIndex = document.getElementById("rowIndex").value;

		if(document.getElementById(_oldIndex) != null){
			document.getElementById(_oldIndex).style.backgroundColor = "#C4D8ED";
		}
		if(document.getElementById(id) != null){
			document.getElementById(id).style.backgroundColor = "#FFFFFF";
		}
		document.getElementById("rowIndex").value = id;
		document.getElementById("commonSeq").value = id;
		
		var _empNum = document.getElementById("employeeNum").value;
		var _approve = document.getElementById("optApproval").value;
		var _departApp = document.getElementById("optDepartment").value;
		var _allApp = document.getElementById("optAll").value;
		var _depId = document.getElementById("departmentID").value;
		
		//alert("EMPLOYEE　NUMBER: " + _empNum);
		if(_empNum == empNum && status == "1"){
			document.getElementById("editBtn").disabled = false;
			document.getElementById("delBtn").disabled = false;
		}else{
			document.getElementById("editBtn").disabled = true;
			document.getElementById("delBtn").disabled = true;
		}
		
		if(_approve == "1"){
			//alert("Status:[" + status + "] departApp:[" + _departApp + "] depid:[" + depId + "," + _depId + "] All App:[" + _allApp + "]");
			if(status == "1" && _departApp == "1" && depId == _depId || status == "2" && _allApp == "1"){
				document.getElementById("appBtn").disabled = false;
				document.getElementById("delcBtn").disabled = false;
			}else{
				//M0076 is Chenny
				if((_empNum == "M0076" || _empNum == "m0076") && (_depId == "15" || _depId == "21" ||_depId == "22" ||_depId == "23")){
					document.getElementById("appBtn").disabled = false;
					document.getElementById("delcBtn").disabled = false;
				}else{
					document.getElementById("appBtn").disabled = true;
					document.getElementById("delcBtn").disabled = true;
				}
			}
		}else{
			document.getElementById("appBtn").disabled = true;
			document.getElementById("delcBtn").disabled = true;
		}
	}
}
function selAll(){
	var objId = "";
	for(var i=1; i<= 30; i++){
		objId = "sa_" + i;
		if(document.getElementById(objId) != null){
			document.getElementById(objId).checked = document.getElementById("allSel").checked;
		}
	}
}
</script>
<script type="text/javascript">
function add(){
	var popstyle="dialogTop:50px;dialogLeft:300px;help:no;center:yes;dialogHeight:450px;dialogWidth:1000px;status:no;resizable:yes;scroll:no;loacation:no;toolbar:no;";
	window.showModalDialog("mp2008AddInit.action?pageType=add",window,popstyle);
	document.getElementById("commonSeq").value = "";
}
function edit(){
	var id = $("#commonSeq").val();
	if(id == "undefined" || id == ""){
		alert("Please select record");
	}else{
		var popstyle="dialogTop:50px;dialogLeft:300px;help:no;center:yes;dialogHeight:450px;dialogWidth:1000px;status:no;resizable:yes;scroll:no;loacation:no;toolbar:no;";
		window.showModalDialog("mp2008AddInit.action?pageType=edit&commonSeq=" + id,window,popstyle);
	}
}
function view(){
	var id = $("#commonSeq").val();
	if(id == "undefined" || id == ""){
		alert("Please select record");
	}else{
		var popstyle="dialogTop:50px;dialogLeft:300px;help:no;center:yes;dialogHeight:530px;dialogWidth:1000px;status:no;resizable:yes;scroll:no;loacation:no;toolbar:no;";
		window.showModalDialog("mp2008AddInit.action?pageType=view&commonSeq=" + id,window,popstyle);
	}
}
function Del(id){
	var id = $("#commonSeq").val();
	if(id == "undefined" || id == ""){
		alert("Please select record");
	}else{
		var msg = "Are you sure you want to delete this record?\n\n"; 
		if (confirm(msg)==true){
		    var param1 = $("#param1").val();
		    var param2 = $("#param2").val();
		    var param3 = $("#param3").val();
		    var param4 = $("#param4").val();
		    var param5 = $("#param5").val();
		    var param6 = $("#param6").val();
			var _pageNum = document.getElementById("pageNum").value;
			var param = {"pageNum" : _pageNum,"commonSeq" : id,"param1" : param1,"param2" : param2,"param3" : param3,"param4" : param4,"param5" : param5,"param6" : param6};
			$("#MP2008InfoListDiv").load("mp2008InfoDelete.action",param);
			document.getElementById("commonSeq").value = "";
		    return true;
		}else{
		    return false;
		}
	}
}
function approve(){
	//var id = $("#commonSeq").val();
	var id = getAllKeys();
	//alert("All Keys List:" + id);
	if(id == "undefined" || id == ""){
		alert("Please select record");
	}else{
		var msg = "Are you sure you want to approve this record?\n\n";
		if (confirm(msg)==true){
		    var param1 = $("#param1").val();
		    var param2 = $("#param2").val();
		    var param3 = $("#param3").val();
		    var param4 = $("#param4").val();
		    var param5 = $("#param5").val();
		    var param6 = $("#param6").val();
			var _pageNum = document.getElementById("pageNum").value;
			var param = {"pageNum" : _pageNum,"commonSeq" : id,"param1" : param1,"param2" : param2,"param3" : param3,"param4" : param4,"param5" : param5,"param6" : param6};
			$("#MP2008InfoListDiv").load("mp2008InfoApprove.action",param);
			 
		    return true;
		}else{
		    return false;
		}
	}
}
function getAllKeys(){
	var objId = "";
	var msg = "";
	for(var i=1; i<= 30; i++){
		objId = "sa_" + i;
		if(document.getElementById(objId) != null && document.getElementById(objId).checked == true){
			msg += document.getElementById(objId).value + ",";
		}
	}
	
	return msg;
}
function decline(){
	//var id = $("#commonSeq").val();
	var id = getAllKeys();
	if(id == "undefined" || id == ""){
		alert("Please select record");
	}else{
		var msg = "Are you sure you want to decline this record?\n\n";
		if (confirm(msg)==true){
		    var param1 = $("#param1").val();
		    var param2 = $("#param2").val();
		    var param3 = $("#param3").val();
		    var param4 = $("#param4").val();
		    var param5 = $("#param5").val();
		    var param6 = $("#param6").val();
			var _pageNum = document.getElementById("pageNum").value;
			var param = {"pageNum" : _pageNum,"commonSeq" : id,"param1" : param1,"param2" : param2,"param3" : param3,"param4" : param4,"param5" : param5,"param6" : param6};
			$("#MP2008InfoListDiv").load("mp2008InfoDecline.action",param);
			 
		    return true; 
		}else{
		    return false; 
		}
	}
}
function createExcelDoc(){
    var param1 = $("#param1").val();
    var param2 = $("#param2").val();
    var param3 = $("#param3").val();
    var param4 = $("#param4").val();
    var param5 = $("#param5").val();
    var param6 = $("#param6").val();
    
    if(param5 == "" || param6 == ""){
    	alert("From Date & To Date can not be empty");
    }else{
        var options = {
        		url : "createOvertimeExcel.action?param1=" + param1 + "&param2=" + param2 + "&param3=" + param3 + "&param4=" + param4 + "&param5=" + param5 + "&param6=" + param6,
        		type : "post",
        		dataType : "html",
        		success : function(msg) {
        			//alert("hello");
        		}
        };
        $("#from1").ajaxSubmit(options);
    }
}
</script>
</head>
<body>
<!-- <form id="from1" action="mp2008InfoSearch.action" method="post" enctype="multipart/form-data"> -->
<s:form id="from1" action="mp2008InfoSearch" method="post" theme="simple" enctype="multipart/form-data">
<input type="button" onclick="pageRefresh()" name="btnRefresh" value="refresh" id="btnRefresh" style="display:block;"/>
<input id="rowIndex" name="rowIndex" value="1" type="hidden" />
<input id="commonSeq" name="commonSeq" value="${commonSeq}" type="hidden" />
<input id="pageCount" name="pageCount" value="${pageCount}" type="hidden" />
<input id="pageNum" name="pageNum" value="${pageNum}" type="hidden" />
<input id="mp2007Status" value="" type="hidden" />
<input id="employeeNum" name="employeeNum" value="${employeeNum}" type="hidden" />
<input id="departmentID" name="departmentID" value="${departmentID}" type="hidden" />
<input id="optAdd" name="optAdd" value="${optAdd}" type="hidden" />
<input id="optDel" name="optDel" value="${optDel}" type="hidden" />
<input id="optEdit" name="optEdit" value="${optEdit}" type="hidden" />
<input id="optApproval" name="optApproval" value="${optApproval}" type="hidden" />
<input id="optSearch" name="optSearch" value="${optSearch}" type="hidden" />
<input id="optView" name="optView" value="${optView}" type="hidden" />
<input id="optAll" name="optAll" value="${optAll}" type="hidden" />
<input id="optDepartment" name="optDepartment" value="${optDepartment}" type="hidden" />
<input id="optPersonal" name="optPersonal" value="${optPersonal}" type="hidden" />

<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
	<tr>
		<td class='menubar_title' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img border='0'src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Overtime Application</td>
		<td class='menubar_readme_text' valign='bottom' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help?</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" style="margin-top:5px;">
    <tr>
        <td width="20%">Employee Number:</td>
        <td width="30%"><input id="param1" name="param1" value="${param1}" type="text" style="width:98%;"/></td>
        <td width="20%">Employee Name:</td>
        <td width="30%"><input id="param2" name="param2" value="${param2}" type="text" style="width:98%;"/></td>
    </tr>
    <tr>
        <td width="20%">Department Number:</td>
        <td width="30%"><s:select  id="param3" name="param3" list="departmentInfoList" theme="simple"/></td>
        <td width="20%">Status</td>
        <td width="30%"><s:select  id="param4" name="param4" list="leaveStatusList" theme="simple"/></td>
    </tr>
    <tr>
        <td width="20%">From Date:</td>
        <td width="30%"><input id="param5" name="param5" value="${param5}" type="text" style="width:98%;" onclick="calendar(this);" readonly="readonly" /></td>
        <td width="20%">To Date:</td>
        <td width="30%"><input id="param6" name="param6" value="${param6}" type="text" style="width:98%;" onclick="calendar(this);" readonly="readonly" /></td>
    </tr>
    <tr>
        <td colspan="4" align="right">
            <input type="submit" name="searchBtn" value="Search" id="searchBtn" class="" />
            <input type="button" name="addBtn" onclick="add()" value="Add New Application" id="addBtn" class="" />
            <input type="button" name="editBtn" onclick="edit()" value="Modify Application" id="editBtn" <s:if test="optEdit == 0" >disabled="disabled"</s:if> class="" />
            <input type="button" name="editBtn" onclick="view()" value="View Application" id="editBtn" class="" />
            <input type="button" name="delBtn" onclick="Del()" value="Delete Applicaion" id="delBtn" <s:if test="optDel == 0" >disabled="disabled"</s:if> class="" />
            <input type="button" name="appBtn" onclick="approve()" value="Approve" id="appBtn" <s:if test="optApproval == 0" >disabled="disabled"</s:if> class="" />            
            <input type="button" name="delcBtn" onclick="decline()" value="Declined" id="delcBtn"  <s:if test="optApproval == 0" >disabled="disabled"</s:if> class="" />
            <input type="button" onclick="createExcelDoc()" value="Export To Excel" />
        </td>
    </tr>
</table>

<div id="MP2008InfoListDiv" style="border: 0px solid #000000;margin-top:20px;">
<table cellspacing="0" cellpadding="0" style="border:0;width:100%;">
    <tr align="center" style="background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px;height:20px;">
        <th scope="col" class="tdBg">
            <input id="allSel" onclick="selAll()" type="checkbox" style="height:20px; width:20px; margin-left:-15px; border:1px solid transparent; "/>
        </th>
        <th scope="col" class="tdBg">Seq</th>
        <th scope="col" class="tdBg">Payroll Num</th>
        <th scope="col" class="tdBg">Employee Name</th>
        <th scope="col" class="tdBg">Department</th>        
        <th scope="col" class="tdBg">Date</th>
        <th scope="col" class="tdBg">Clock In</th>
        <th scope="col" class="tdBg">Clock Out</th>
        <th scope="col" class="tdBg">From Time</th>
        <th scope="col" class="tdBg">To Time</th>
        <th scope="col" class="tdBg">Hours</th>
        <th scope="col" class="tdBg">Type</th>
        <th scope="col" class="tdBg">Status</th>
    </tr>
<s:iterator value="mp2008InfoList" status="st">
    <tr id="<s:property value="MP2008_NUM"/>" align="left" style="height:28px;" onmousedown="rowClick('<s:property value="MP2008_NUM"/>','<s:property value="MP2008_EMPLOYEE_NUM"/>','<s:property value="MP2008_APP_STATUS"/>','<s:property value="MP2008_DEPARTMENT"/>')">
        <td class="tdBorder" >
<s:if test="MP2008_APP_STATUS == 3">-</s:if>
<s:elseif test="optAll == 1 && MP2008_APP_STATUS == 2 || optDepartment == 1 && MP2008_APP_STATUS == 1 && departmentID == MP2008_DEPARTMENT ">
            <div class="checkdiv">
                <input id="sa_${st.index + 1}" onclick="" type="checkbox" name="scoreArray5" value="${MP2008_NUM}" class="checkbox" />
            </div>
</s:elseif>
<s:elseif test=" optDepartment == 1 && MP2008_APP_STATUS == 1 && (MP2008_EMPLOYEE_2=='M0076' || MP2008_EMPLOYEE_2=='m0076') && (MP2008_DEPARTMENT==15 || MP2008_DEPARTMENT==21 || MP2008_DEPARTMENT==22 || MP2008_DEPARTMENT==23)">
            <div class="checkdiv">
                <input id="sa_${st.index + 1}" onclick="" type="checkbox" name="scoreArray5" value="${MP2008_NUM}" class="checkbox" />
            </div>
</s:elseif>
<s:else>-</s:else>
        </td>
        <td width="50px" align="center" class="tdBorder">${st.index + 1}</td>
        <td class="tdBorder"><s:property value="MP2008_PAYROLL_NUM"/></td>
        <td class="tdBorder"><s:property value="MP2008_EMPLOYEE_NAME"/></td>
        <td class="tdBorder"><s:property value="MP2008_DEPARTMENT_NAME"/></td>
        <td class="tdBorder"><s:property value="MP2008_DATE"/></td>
        <td class="tdBorder"><s:property value="MP2008_CLOCK_IN"/></td>
        <td class="tdBorder"><s:property value="MP2008_CLOCK_OUT"/></td>
        <td class="tdBorder"><s:property value="MP2008_FROM_DATETIME"/>:<s:property value="MP2008_FROM_MINUTE"/></td>
        <td class="tdBorder"><s:property value="MP2008_TO_DATETIME"/>:<s:property value="MP2008_TO_MINUTE"/></td>
        <td class="tdBorder"><s:property value="MP2008_HOURS"/></td>
        <td class="tdBorder"><s:property value="MP2008_RATING"/></td>
        <td class="tdBorder"><s:property value="MP2008_APP_STATUS_NAME"/></td>
    </tr>
</s:iterator>
</table>
</div>

<div id="pager" style="border: 0px solid #000000;height:30px;width:1300px;margin-top:10px;"></div>

</s:form>
</body>
</html>

