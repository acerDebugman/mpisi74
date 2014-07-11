<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Apply Leave</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
<link rel="shortcut icon" href="images/other/icon.ico" type="image/x-icon"></link>
<link rel="stylesheet" href="css/Site_Css.css" type="text/css"></link>
<link rel="stylesheet" href="css/table/blue/css.css" type="text/css"></link>

<script type="text/javascript" src="js/checkform.js" charset="utf-8"></script>
<script type="text/javascript" src="js/jquery1.4.2.js"></script>
<script src="js/jquery.pager.js" type="text/javascript"></script>

<style type="text/css">
#pager ul.pages {display:block;border:none;text-transform:uppercase;font-size:10px;margin:1px 0 10px;padding:0;}
#pager ul.pages li {list-style:none;float:left;border:1px solid #65AB31;text-decoration:none;margin:0 5px 0 0;padding:5px;}
#pager ul.pages li:hover {border:1px solid #003f7e;}
#pager ul.pages li.pgEmpty {border:1px solid #000000;color:#000000;background-color:grey;}
#pager ul.pages li.pgCurrent {border:1px solid #003f7e;color:#000;font-weight:700;background-color:#65AB31;}
.tdBg{background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px;color:white;text-align:center;margin:0px;padding:0px;}
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
    
    var param = {"pageNum" : pageclickednumber};
    document.getElementById("pageNum").value = pageclickednumber;
    $("#vacationInfoListDiv").load("vacationDataListPageClick.action",param);
}
//局部刷新
function pageRefresh(){
    var _pageNum = document.getElementById("pageNum").value;
    var param = {"pageNum" : _pageNum};
	$("#vacationInfoListDiv").load("vacationDataListPageClick.action",param);
}
</script>

<script type="text/javascript">
function eidtVacation(seq,name){
	var popstyle="dialogTop:300px;dialogLeft:300px;help:no;center:yes;dialogHeight:600px;dialogWidth:600px;status:no;resizable:no;scroll:no;loacation:no;toolbar:no;";
	//window.showModalDialog("vacationAdd.jsp?mp2002Seq=" + id, window, popstyle);
	window.showModalDialog("vacationAddInit.action?mp2002EmpNum=" + seq + "&mp2002EmpName=" + name,window,popstyle);
}
function eidtMaternity(seq,name){
	//var popstyle="dialogTop:300px;dialogLeft:300px;help:no;center:yes;dialogHeight:600px;dialogWidth:600px;status:no;resizable:no;scroll:no;loacation:no;toolbar:no;";
	//window.showModalDialog("vacationMaternityAddInit.action?mp2002EmpNum=" + seq + "&mp2002EmpName=" + name,window,popstyle);
}
</script>
</head>
<body>
<s:form action="vacationDataList" method="post" theme="simple">
<input id="pageCount" name="pageCount" value="${pageCount}" type="hidden" />
<input id="pageNum" name="pageNum" value="${pageNum}" type="hidden" />
<input type="button" onclick="pageRefresh()" name="btnRefresh" value="refresh" id="btnRefresh" style="display:none;"/>
<input id="optEdit" name="optEdit" value="${optEdit}" type="hidden" />
<input id="refreshData" type="submit" value="refresh" style="display:none;" />

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
	<tr>
		<td class='menubar_title' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img border='0'src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Vacation Management</td>
		<td class='menubar_readme_text' valign='bottom' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help?</td>
	</tr>
</table>
<!-- 头部菜单 End -->

<div id="vacationInfoListDiv" style="overflow:auto;border:solid 0px red;width:1455px;">

<table cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
    <tr>
        <td colspan="12" style="font-size:14px;color:red;">D: Day  H: Hour</td>
    </tr>
    <tr class="" style="FONT: bold 14px Tahoma, Verdana;COLOR: #ffffff;BACKGROUND-COLOR: #698cc3;HEIGHT: 35px;BACKGROUND: url(images/header_bg.gif) #2f589c repeat-x 0px 0px;PADDING-LEFT: 5px;PADDING-RIGHT:5px;" align="center">
        <th scope="col" rowspan="2" width="100px">Employee Number</th>
        <th scope="col" rowspan="2" width="300px">Employee Name</th>
        <th scope="col" colspan="2" width="200px">Annual</th>
        <th scope="col" colspan="2" width="200px">Sick</th>
        <th scope="col" colspan="2" width="200px">Family Resp</th>
        <th scope="col" colspan="2" width="200px">Maternity</th>
        <th scope="col" colspan="2" width="200px">Study</th>
    </tr>
    <tr class="table_title" align="center">

        <td width="100px">Balance</td><!-- 剩余天数 -->
        <td width="100px">Taken</td><!-- 请假天数天数 -->

        <td width="100px">Balance</td>
        <td width="100px">Taken</td>

        <td width="100px">Balance</td>
        <td width="100px">Taken</td>

        <td width="100px">Balance</td>
        <td width="100px">Taken</td>

        <td width="100px">Balance</td>
        <td width="100px">Taken</td>
    </tr>

<s:iterator value="VacationInfoList" status="st">
    <tr class="row" align="center" style="height:28px;">
        <td width="100px"><s:property value="MP2002_EMPLOYEE_NUM"></s:property></td>
        <td width="300px"><s:property value="MP2002_EMPLOYEE_NAME"></s:property></td>
        
        <td width="100px"><s:property value="MP2002_ANNUAL"></s:property></td>
        <td width="100px"><s:property value="MP2002_ANNUAL_A"></s:property></td>
    
        <td width="100px"><s:property value="MP2002_SICK"></s:property></td>
        <td width="100px"><s:property value="MP2002_SICK_A"></s:property></td>
    
        <td width="100px"><s:property value="MP2002_FAMILY_RESP"></s:property></td>
        <td width="100px"><s:property value="MP2002_FAMILY_RESP_A"></s:property></td>
<s:if test="optEdit == 1" >
        <td width="100px" style="text-decoration:underline;" onclick="eidtMaternity('<s:property value="MP2002_EMPLOYEE_NUM"/>','<s:property value="MP2002_EMPLOYEE_NAME"/>')"><s:property value="MP2002_MATERNITY"/></td>
</s:if>
<s:else>
        <td width="100px"><s:property value="MP2002_MATERNITY"></s:property></td>
</s:else>
        <td width="100px"><s:property value="MP2002_MATERNITY_A"></s:property></td>
<s:if test="optEdit == 1" >
        <td width="100px" style="text-decoration:underline;" onclick="eidtVacation('<s:property value="MP2002_EMPLOYEE_NUM"/>','<s:property value="MP2002_EMPLOYEE_NAME"/>')"><s:property value="MP2002_STUDY"/></td>
</s:if>
<s:else>
        <td width="100px"><s:property value="MP2002_STUDY"/></td>
</s:else>
        <td width="100px"><s:property value="MP2002_STUDY_A"></s:property></td>
    </tr>
</s:iterator>
</table>
</div>

<div id="pager" style="border: 0px solid #000000;height:30px;width:1300px;margin-top:10px;"></div>

</s:form>

</body>
</html>