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
<link href="css/skins2/main.css" rel="stylesheet" type="text/css" />

<script src="js/calendar.js" type="text/javascript" ></script>
<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src = "js/skins2/Main82.js" type="text/javascript"></script>
<script src="js/jquery1.4.2.js" type="text/javascript"></script>
<script src="js/jquery.form.js" type="text/javascript" ></script>
<script src="js/jquery.pager.js" type="text/javascript"></script>

<style type="text/css">
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
    var type = $("#pageType").val();
    
    if(param1 == "" || param2 == ""){
    	alert("From Date & To Date can not be empty.");
    }else{
        var param = {"pageNum" : pageclickednumber,"param1" : param1,"param2" : param2,"param3" : param3,"pageType" : type};
        document.getElementById("pageNum").value = pageclickednumber;
        $("#CHECKINOUTInfoListDiv").load("checkinoutInfoPageClick.action",param);
    }
}
</script>
<script type="text/javascript">
function s(){
    var param1 = $("#param1").val();
    var param2 = $("#param2").val();
    
    if(param1 == "" || param2 == ""){
    	alert("From Date & To Date can not be empty.");
    }
}
function ex(){
    var param1 = $("#param1").val();
    var param2 = $("#param2").val();
    var param3 = $("#param3").val();
    if(param1 == "" || param2 == ""){
    	alert("From Date & To Date can not be empty.");
    }else{
    	document.getElementById("pb").style.display="block";
     	var options = {
     	    url : "loadDataToHrSystem.action",
     		type : "post",
     	    dataType : "html",
            success : function(msg) {
     			document.getElementById("pb").style.display="none";
     		}
     	};
     	$("#from1").ajaxSubmit(options);
    }
}
function ca(){
	document.getElementById("pb").style.display="block";
 	var options = {
     	    url : "calculateAttendanceData.action",
     		type : "post",
     	    dataType : "html",
            success : function(msg) {
     			document.getElementById("pb").style.display="none";
     		}
     	};
     	$("#from1").ajaxSubmit(options);
}
function createExcelDoc(){
    var param1 = $("#param1").val();
    var param2 = $("#param2").val();
    var param3 = $("#param3").val();
    var type = $("#pageType").val();
    var options = {
    		url : "createCheckInOuteExcel.action?param1=" + param1 + "&param2=" + param2 + "&param3=" + param3 + "&pageType=" + type,
    		type : "post",
    		dataType : "html",
    		success : function(msg) {
    			//alert("hello");
    		}
    };
    $("#from1").ajaxSubmit(options);
    //window.location.href = "createCheckInOuteExcel.action?param1=" + param1 + "&param2=" + param2 + "&param3=" + param3 + "&pageType=" + type;
}
</script>
</head>
<body>

<form id="from1" action="checkinoutInfoSearch.action" method="post" enctype="multipart/form-data">
<input id="pageCount" name="pageCount" value="${pageCount}" type="hidden" />
<input id="pageNum" name="pageNum" value="${pageNum}" type="hidden" />
<input id="pageType" name="pageType" value="${pageType}" type="hidden" />
<input type="button" onclick="pageRefresh()" name="btnRefresh" value="refresh" id="btnRefresh" style="display:none;"/>
<input id="optSearch" name="optSearch" value="${optSearch}" type="hidden" />
<input id="optDel" name="optDel" value="${optDel}" type="hidden" />
<input id="optEdit" name="optEdit" value="${optEdit}" type="hidden" />

<div id="pb" style="index:999;position:absolute;width:100%;height:100%;background-color:white;filter:alpha(opacity=70);display:none;text-align:center;padding-top:15%;">
    <div style=""><img alt="Loading ..." src="images/logo/97.gif" style="vertical-align:middle;"></div>
    <div style="font:20pt 'Arial';color:red;">Loading ...</div>
</div>

<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
	<tr>
		<td class='menubar_title' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img border='0'src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Auto Load Attendance Data to HRMS</td>
		<td class='menubar_readme_text' valign='bottom' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help?</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" style="margin-top:5px;">
    <tr>
        <td width="20%">From Date</td>
        <td width="30%"><input id="param1" name="param1" value="${param1}" type="text" onclick="calendar(this);" style="width:98%;"/></td>
        <td width="20%">To Date</td>
        <td width="30%"><input id="param2" name="param2" value="${param2}" type="text" onclick="calendar(this);" style="width:98%;"/></td>
    </tr>
    <tr>
        <td width="20%">Employee Number</td>
        <td width="30%"><input id="param3" name="param3" value="${param3}" type="text" style="width:98%;"/></td>    
        <!-- <td colspan="4" align="right"><input type="submit" name="searchBtn" value="Search" id="searchBtn" <s:if test="optSearch == 0" >disabled="disabled"</s:if> class="" /></td> -->
        <td colspan="2" align="right" width="50%">
<s:if test="pageType != 'T' ">
            <input class="input0" type="button" onclick="ca()" name="calculateBtn" value="Calculate Attendance Data" disable="false" id="calculateBtn" />
            <input class="input0" type="button" onclick="ex()" name="executeBtn" value="Load Data to HR System" id="executeBtn" />
</s:if>
            <input class="input0" type="submit" onclick="s()" name="searchBtn" value="Search" id="searchBtn" class="" />
            <input type="button" onclick="createExcelDoc()" value="Export To Excel" />
        </td>
    </tr>
</table>

<div id="CHECKINOUTInfoListDiv" style="border: 0px solid #000000;margin-top:20px;">
<table cellspacing="0" cellpadding="0" style="border:0;width:100%;">
    <tr align="center" style="background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px">
        <th scope="col" class="tdBg">Index</th>
        <th scope="col" class="tdBg">User ID</th>
        <th scope="col" class="tdBg">Employee Number</th>
        <th scope="col" class="tdBg">Check Time</th>
        <th scope="col" class="tdBg">Door</th>
    </tr>
<s:iterator value="checkinoutInfoList" status="st">
    <tr align="left" style="height:28px;">
        <td width="50px" align="center" class="tdBorder">${st.index + 1}</td>
        <td class="tdBorder"><s:property value="USERID"/></td>
        <td class="tdBorder"><s:property value="EMPLOYEE_NUM"/>&nbsp;</td>
        <td class="tdBorder"><s:property value="CHECKTIME"/>&nbsp;</td>
        <td width="50px" class="tdBorder"><s:property value="SENSORID"/>&nbsp;</td>
    </tr>
</s:iterator>
</table>
</div>

<div id="pager" style="border: 0px solid #000000;height:30px;width:1300px;margin-top:10px;"></div>

</form>
</body>
</html>


