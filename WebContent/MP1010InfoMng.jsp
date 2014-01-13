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
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<title></title>
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<!-- <link href="css/skins2/main.css" rel="stylesheet" type="text/css" /> -->

<script src = "js/skins2/Main82.js" type="text/javascript"></script>
<script src="js/jquery1.4.2.js" type="text/javascript"></script>
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
    
    var param = {"pageNum" : pageclickednumber,"param1" : param1,"param2" : param2,"param3" : param3,"param4" : param4};
    document.getElementById("pageNum").value = pageclickednumber;
    $("#MP1010InfoListDiv").load("mp1010InfoPageClick.action",param);
    document.getElementById("commonSeq").value = "";
}
</script>
<script type="text/javascript">
function pageRefresh(){
    var param1 = $("#param1").val();
    var param2 = $("#param2").val();
    var param3 = $("#param3").val();
    var param4 = $("#param4").val();
    var _pageNum = document.getElementById("pageNum").value;
    var param = {"pageNum" : _pageNum,"param1" : param1,"param2" : param2,"param3" : param3,"param4" : param4};
	$("#MP1010InfoListDiv").load("mp1010InfoPageClick.action",param);
	document.getElementById("commonSeq").value = "";
}
function rowClick(id,empNum,status){
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
	}
}
</script>
<script type="text/javascript">
function add(){
	var popstyle="dialogTop:50px;dialogLeft:300px;help:no;center:yes;dialogHeight:450px;dialogWidth:1000px;status:no;resizable:yes;scroll:no;loacation:no;toolbar:no;";
	window.showModalDialog("mp1010AddInit.action?pageType=add",window,popstyle);
	document.getElementById("commonSeq").value = "";
}
function edit(){
	var id = $("#commonSeq").val();
	if(id == "undefined" || id == ""){
		alert("Please select record");
	}else{
		var popstyle="dialogTop:50px;dialogLeft:300px;help:no;center:yes;dialogHeight:450px;dialogWidth:1000px;status:no;resizable:yes;scroll:no;loacation:no;toolbar:no;";
		window.showModalDialog("mp1010AddInit.action?pageType=edit&commonSeq=" + id,window,popstyle);
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
			var _pageNum = document.getElementById("pageNum").value;
			var param = {"pageNum" : _pageNum,"commonSeq" : id,"param1" : param1,"param2" : param2,"param3" : param3,"param4" : param4};
			$("#MP1010InfoListDiv").load("mp1010InfoDelete.action",param);
			document.getElementById("commonSeq").value = "";
		    return true;
		}else{ 
		    return false;
		}
	}
}
function at(){
	window.location.href='checkinoutInfoMngInit.action?pageType=T';
}
</script>
</head>
<body>
<form name="form2" action="mp1010InfoSearch.action" method="post">
<input type="button" onclick="pageRefresh()" name="btnRefresh" value="refresh" id="btnRefresh" style="display:none;"/>
<input id="rowIndex" name="rowIndex" value="1" type="hidden" />
<input id="commonSeq" name="commonSeq" value="${commonSeq}" type="hidden" />
<input id="pageCount" name="pageCount" value="${pageCount}" type="hidden" />
<input id="pageNum" name="pageNum" value="${pageNum}" type="hidden" />
<input id="optAdd" name="optAdd" value="${optAdd}" type="hidden" />
<input id="optDel" name="optDel" value="${optDel}" type="hidden" />
<input id="optEdit" name="optEdit" value="${optEdit}" type="hidden" />
<input id="optSearch" name="optSearch" value="${optSearch}" type="hidden" />

<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
	<tr>
		<td class='menubar_title' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img border='0'src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Temporary Employee</td>
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
        <td width="20%">Employee ID:</td>
        <td width="30%"><input id="param4" name="param4" value="${param4}" type="text" style="width:98%;"/></td>
        <td width="20%">Department:</td>
        <td width="30%"><s:select  id="param3" name="param3" list="departmentInfoList" theme="simple"/></td>
    </tr>
    <tr>
        <td colspan="4" align="right">
            <input class="input0" type="submit" name="searchBtn" value="Search" id="searchBtn" <s:if test="optSearch == 0" >disabled="disabled"</s:if> class="" />
            <input class="input0" type="button" name="addBtn" onclick="add()" value="Add New Employee" id="addBtn" <s:if test="optAdd == 0" >disabled="disabled"</s:if> class="" />
            <input class="input0" type="button" name="editBtn" onclick="edit()" value="Modify Employee Information" id="editBtn" <s:if test="optEdit == 0" >disabled="disabled"</s:if> class="" />
            <input class="input0" type="button" name="delBtn" onclick="Del()" value="Delete" id="delBtn" <s:if test="optDel == 0" >disabled="disabled"</s:if> class="" />
            <input class="input0" type="button" onclick="at()" name="atBtn" value="Attendance Records" id="atBtn" />
        </td>
    </tr>
</table>

<div id="MP1010InfoListDiv" style="border: 0px solid #000000;margin-top:20px;">
<table cellspacing="0" cellpadding="0" style="border:0;width:100%;">
    <tr align="center" style="background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px;height:20px;">
        <th scope="col" class="tdBg">Seq</th>
        <th scope="col" class="tdBg">Employee Number</th>
        <th scope="col" class="tdBg">Employee Name</th>
        <th scope="col" class="tdBg">ID</th>
        <th scope="col" class="tdBg">Department</th>
        <th scope="col" class="tdBg">Gender</th>
    </tr>
<s:iterator value="mp1010InfoList" status="st">
    <tr id="<s:property value="MP1010_EMPLOYEE_NUM"/>" align="left" style="height:28px;" onmousedown="rowClick('<s:property value="MP1010_EMPLOYEE_NUM"/>')">
        <td width="50px" align="center" class="tdBorder">${st.index + 1}</td>
        <td class="tdBorder"><s:property value="MP1010_EMPLOYEE_NUM"/></td>
        <td class="tdBorder"><s:property value="MP1010_PREFERED_NAME"/></td>
        <td class="tdBorder"><s:property value="MP1010_EMPLOYEE_ID"/></td>
        <td class="tdBorder"><s:property value="MP1010_DEPARTMENT_NAME"/></td>
        <td class="tdBorder"><s:property value="MP1010_GENDER_NAME"/></td>
    </tr>
</s:iterator>
</table>
</div>

<div id="pager" style="border: 0px solid #000000;height:30px;width:1300px;margin-top:10px;"></div>

</form>
</body>
</html>