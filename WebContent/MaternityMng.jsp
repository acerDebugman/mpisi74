<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />

<script src="js/calendar.js" type="text/javascript"></script>
<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery1.4.2.js" type="text/javascript"></script>
<script src="js/jquery.pager.js" type="text/javascript"></script>

<style type="text/css">
#pager ul.pages {
display:block;
border:none;
text-transform:uppercase;
font-size:10px;
margin:1px 0 10px;
padding:0;
}

#pager ul.pages li {
list-style:none;
float:left;
border:1px solid #65AB31;
text-decoration:none;
margin:0 5px 0 0;
padding:5px;
}

#pager ul.pages li:hover {
border:1px solid #003f7e;
}

#pager ul.pages li.pgEmpty {
border:1px solid #000000;
color:#000000;
background-color:grey;
}

#pager ul.pages li.pgCurrent {
border:1px solid #003f7e;
color:#000;
font-weight:700;
background-color:#65AB31;
}
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
    var fromDate = $("#fromDate").val();
    var toDate = $("#toDate").val();
    var pr1001DepartmenSeq = $("#pr1001DepartmenSeq").val();
    
    var param = {"pageNum" : pageclickednumber,"fromDate" : fromDate,"toDate" : toDate,"pr1001DepartmenSeq" : pr1001DepartmenSeq};
    document.getElementById("pageNum").value = pageclickednumber;
    $("#requisitionFormDeatillList").load("pages/pms/budgetAdditionRefresh.action",param);
}
</script>

<script type="text/javascript">
function Del(id){
	var msg = "Are you sure you want to delete this record?\n\n"; 
	if (confirm(msg)==true){
		 var fromDate = $("#fromDate").val();
		 var toDate = $("#toDate").val();
		 var pr1001DepartmenSeq = $("#pr1001DepartmenSeq").val();
		 var _pageNum = document.getElementById("pageNum").value;
		 var param = {"pageNum" : _pageNum,"mp4004Seq" : id,"fromDate" : fromDate,"toDate" : toDate,"pr1001DepartmenSeq" : pr1001DepartmenSeq};
		 $("#budgetAdditionInfoListDiv").load("pages/pms/budgetAdditionDel.action",param);
		 
	    return true; 
	}else{ 
	    return false; 
	}
}
function edit(id){
	var popstyle="dialogTop:200px;dialogLeft:300px;help:no;center:yes;dialogHeight:400px;dialogWidth:800px;status:no;resizable:no;scroll:no;loacation:no;toolbar:no;";
	window.showModalDialog("pages/pms/applyBudgetAdditionInit.action?pageType=edit&mp4004Seq=" + id,window,popstyle);
}
//局部刷新
function t(){
    var fromDate = $("#fromDate").val();
    var toDate = $("#toDate").val();
    var pr1001DepartmenSeq = $("#pr1001DepartmenSeq").val();
    var _pageNum = document.getElementById("pageNum").value;
    var param = {"pageNum" : _pageNum,"fromDate" : fromDate,"toDate" : toDate,"pr1001DepartmenSeq" : pr1001DepartmenSeq};

	$("#budgetAdditionInfoListDiv").load("pages/pms/budgetAdditionRefresh.action",param);
}
function approve(id){
	var popstyle="dialogTop:200px;dialogLeft:300px;help:no;center:yes;dialogHeight:400px;dialogWidth:800px;status:no;resizable:no;scroll:no;loacation:no;toolbar:no;";
	window.showModalDialog("pages/pms/applyBudgetAdditionInit.action?pageType=view&mp4004Seq=" + id,window,popstyle);
}
</script>

</head>
<body bgColor="#FFFFFF" topMargin="5">
<form method="post" action="BudgetAdditionSearch.action">
<input id="pageCount" name="pageCount" value="${pageCount}" type="hidden" />
<input id="pageNum" name="pageNum" value="${pageNum}" type="hidden" />
<input id="optSearch" name="optSearch" value="${optSearch}" type="hidden" />
<input id="optDel" name="optDel" value="${optDel}" type="hidden" />
<input id="optEdit" name="optEdit" value="${optEdit}" type="hidden" />
<input id="optView" name="optView" value="${optView}" type="hidden" />
<input id="optApproval" name="optApproval" value="${optApproval}" type="hidden" />
<input id="optAll" name="optAll" value="${optAll}" type="hidden" />
<input id="optDepartment" name="optDepartment" value="${optDepartment}" type="hidden" />
<input id="optPersonal" name="optPersonal" value="${optPersonal}" type="hidden" />

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
  <tr>
    <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Budget Management</td>
    <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
  </tr>
  <tr>
    <td height='27px' class='menubar_function_text'>Operation Function: Budget Addition Management</td>
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

<!--检索区域Start-->
<fieldset style="border-color:#999999;border-width:1px;border-style:Solid;margin-bottom:10px;">
<legend style="color:#333333;font-size:1.5em;font-weight:bold;">Query Condition</legend>
<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
    <tr>
        <td class="table_body table_body_NoWidth">Year</td>
        <td class="table_none table_none_NoWidth">
            <input id="fromDate" name="fromDate" value="${fromDate}" type="text" class="text_input"/> (YYYY)
        </td>
        <td class="table_body table_body_NoWidth">Month</td>
        <td class="table_none table_none_NoWidth">
            <input id="toDate" name="toDate" value="${toDate}" type="text" class="text_input"/> (MM)
        </td>
    </tr>
    <tr>
<s:if test="optAll == 1">
        <td class="table_body table_body_NoWidth">Department</td>
        <td class="table_none table_none_NoWidth">
            <s:select id="pr1001DepartmenSeq" name="pr1001DepartmenSeq" value="pr1001DepartmenSeq" list="departmentInfoList" theme="simple"/>
        </td>
        <td colspan="2" align="right">
            <input type="submit" name="searchBtn" value="Search" id="searchBtn" class="" />
            <input type="button" onclick="t()" name="pageRefresh" value="refresh" id="pageRefresh" style="display:none;"/>
        </td>
</s:if>
<s:else>
        <td colspan="4" align="right">
            <input type="submit" name="searchBtn" value="Search" id="searchBtn" class="" />
            <input type="button" onclick="t()" name="pageRefresh" value="refresh" id="pageRefresh" style="display:none;"/>
        </td>
</s:else>

    </tr>
</table>
</fieldset>
<!--检索区域End-->

<div id="budgetAdditionInfoListDiv" style="border: 0px solid #000000;">
<table class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
    <tr class="table_title" align="center">
        <th scope="col">No.</th>
        <th scope="col">Time</th>
        <th scope="col">Department</th>
        <th scope="col">Applicant</th>
        <th scope="col">Item</th>
        <th scope="col">Amount</th>
        <th scope="col">Status</th>
        <th scope="col">&nbsp;</th>
    </tr>
<s:iterator value="budgetAdditionInfoList" status="st">
    <tr class="row" align="center" style="height:28px;">
        <td width="50px">${st.index + 1}</td>
        <td width="120px"><s:property value="MP4004_MONTH_NAME"/>/<s:property value="MP4004_YEAR"/></td>
        <td width="100px"><s:property value="MP4004_DEPARTMENT_NAME"/></td>
        <td width="150px"><s:property value="MP4004_APPLICANT"/>(<s:property value="MP4004_USER"/>)</td>
        <td><s:property value="MP4004_ACCOUNTING_NUM_NAME"/></td>
        <td width="100px"><s:property value="MP4004_AMOUNT"/></td>
        
<s:if test="MP4004_STATUS == 1">
        <td width="100px"><s:property value="MP4004_STATUS_NAME"/></td>
</s:if>
<s:if test="MP4004_STATUS == 0">
        <td width="100px" style="color:red;"><s:property value="MP4004_STATUS_NAME"/></td>
</s:if>
<s:if test="MP4004_STATUS == 2">
        <td width="100px" style="color:green;"><s:property value="MP4004_STATUS_NAME"/></td>
</s:if>

        <td width="180px">
<s:if test="MP4004_STATUS == 1">
            <input type="button" onclick="edit('<s:property value="MP4004_SEQ"/>')" name="editBtn" value="Edit" id="editBtn"/>
            <input type="button" onclick="Del('<s:property value="MP4004_SEQ"/>')" name="delBtn" value="Del" id="delBtn"/>
</s:if>
<s:if test="MP4004_STATUS == 1 && optApproval == 1">
            <input type="button" onclick="approve('<s:property value="MP4004_SEQ"/>')" name="delBtn" value="Approve" id="delBtn" />
</s:if>
        </td>
    </tr>
</s:iterator>
</table>
</div>

<table cellspacing="1" border="0" style="background-color:White;border-width:0px;width:1300px;" align="center">
    <tr class="">
        <td>
            <div id="pager" style="border: 1px solid #FFFFFF;"></div>
        </td>
    </tr>
</table>
</form>
</body>
</html>