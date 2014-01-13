<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
    var mp0011EmpNumber = $("#mp0011EmpNumber").val();

    var param = {"pageNum" : pageclickednumber,"fromDate" : fromDate,"toDate" : toDate,"mp0011EmpNumber" : mp0011EmpNumber};
    document.getElementById("pageNum").value = pageclickednumber;
    $("#loginHistoryDetailDiv").load("loginHistoryDetailList.action",param);
}
</script>

</head>
<body bgColor="#FFFFFF" topMargin="5">
<form method="post" action="loginHistoryInfoSearch.action">
<input id="pageCount" name="pageCount" value="${pageCount}" type="hidden" />
<input id="pageNum" name="pageNum" value="${pageNum}" type="hidden" />
<input id="optSearch" name="optSearch" value="${optSearch}" type="hidden" />

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
  <tr>
    <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;System Management</td>
    <td class='menubar_readme_text' valign='bottom'><img src='/images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
  </tr>
  <tr>
    <td height='27px' class='menubar_function_text'>Operation Function:Login History</td>
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
        <td class="table_body table_body_NoWidth">From Date</td>
        <td class="table_none table_none_NoWidth">
            <input id="fromDate" name="fromDate" value="${fromDate}" type="text" class="text_input" onfocus="calendar(this);"/>
        </td>
        <td class="table_body table_body_NoWidth">To Date</td>
        <td class="table_none table_none_NoWidth">
            <input id="toDate" name="toDate" value="${toDate}" type="text" class="text_input" onfocus="calendar(this);"/>
        </td>
    </tr>
    <tr>
        <td class="table_body table_body_NoWidth">No.</td>
        <td class="table_none table_none_NoWidth">
            <input id="mp0011EmpNumber" name="mp0011EmpNumber" value="${mp0011EmpNumber}" type="text" class="text_input" />
        </td>
        <td class="table_none table_none_NoWidth" colspan="2" align="right">
<s:if test="optSearch == 1" >
            <input type="submit" name="searchBtn" value="Search" id="searchBtn" class="" />
</s:if>
        </td>
    </tr>
</table>
</fieldset>
<!--检索区域End-->

<div id="loginHistoryDetailDiv" style="border: 0px solid #000000;">
<table class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;width:1300px;">
    <tr class="table_title" align="center">
        <th scope="col">No.</th>
        <th scope="col">System No.</th>
        <th scope="col">Employee Name</th>
        <th scope="col">Department</th>
        <th scope="col">Time</th>
        <th scope="col">IP</th>
        <th scope="col">Memo</th>
    </tr>
<s:iterator value="loginHistoryInfo" status="st">
    <tr class="row" align="center" style="height:28px;">
        <td width="100px">${st.index + 1}</td>
        <td width="100px"><s:property value="MP0011_SYS_CODE"/></td>
        <td width="200px"><s:property value="MP0011_USR_NAME"/>(<s:property value="MP0011_USR_ID"/>)</td>
        <td width="200px"><s:property value="MP0011_DEPARTMENT"/></td>
        <td width="200px" align="left"><s:property value="MP0011_LOGIN_TIME"/></td>
        <td width="180px"><s:property value="MP0011_LOGIN_IP"/></td>
        <td width="100px" align="right"><s:property value="MP0011_MEMO"/></td>
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