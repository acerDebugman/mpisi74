<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Document Management</title>
<link rel="stylesheet" href="css/Site_Css.css" type="text/css" />
<link rel="shortcut icon" href="images/other/icon.ico" type="image/x-icon" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/wbox.css"></link>

<script type="text/javascript" src="js/checkform.js" charset="utf-8"></script>
<script type="text/javascript" src="js/jquery1.4.2.js"></script>
<script type="text/javascript" src="js/jquery.pager.js"></script>

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

<script type="text/javascript" language="javascript">
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
    var intervieweeID = $("#intervieweeID").val();
    var intervieweeName = $("#intervieweeName").val();
    
    var param = {"pageNum" : pageclickednumber,"intervieweeID" : intervieweeID,"intervieweeName" : intervieweeName};
    document.getElementById("pageNum").value = pageclickednumber;
    $("#interviewDetailList").load("interviewDetailList.action",param);
}
</script>

<script type="text/javascript">
//文档删除操作
function interviewRecordDel(id){
    var intervieweeID = $("#intervieweeID").val();
    var intervieweeName = $("#intervieweeName").val();
    var _pageNum = document.getElementById("pageNum").value;
	var param = {"pageNum" : _pageNum,"intervieweeSelectedID" : id,"intervieweeID" : intervieweeID,"intervieweeName" : intervieweeName};

	$("#interviewDetailList").load("deleteInterviewInfo.action",param);
}
function interviewAdd(){
	var popstyle="dialogTop:300px;dialogLeft:300px;help:no;center:yes;dialogHeight:400px;dialogWidth:500px;status:no;resizable:no;scroll:no;loacation:no;toolbar:no;";
	window.showModalDialog("interviewInfoInit.action?pageType=ADD",window,popstyle);
}
function interviewRecordEdit(id){
	var popstyle="dialogTop:300px;dialogLeft:300px;help:no;center:yes;dialogHeight:400px;dialogWidth:500px;status:no;resizable:no;scroll:no;loacation:no;toolbar:no;";
	window.showModalDialog("interviewInfoInit.action?pageType=EDIT&intervieweeSelectedID=" + id,window,popstyle);
}
//局部刷新
function refresh(type){
	var intervieweeID = $("#intervieweeID").val();
	var intervieweeName = $("#intervieweeName").val();
	var pageNum = $("#pageNum").val();
	var param = {"pageNum" : pageNum, "intervieweeID" : intervieweeID, "intervieweeName" : intervieweeName};
	if(type == "interview"){
		$("#interviewDetailList").load("interviewDetailList.action",param);
	}
}
</script>

</head>
<body>

<form method="post" action="interviewListSearch.action">
<input id="pageCount" name="pageCount" value="${pageCount}" type="hidden" />
<input id="pageNum" name="pageNum" value="${pageNum}" type="hidden" />

<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
        <td>
            <!-- 头部菜单 Start -->
            <table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
              <tr>
                <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Employee Management</td>
                <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
              </tr>
              <tr>
                <td height='27px' class='menubar_function_text'>Operation Function：CV Management</td>
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
                    <td class="table_body table_body_NoWidth">Passport Number/ID</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="intervieweeID" name="intervieweeID" value="${intervieweeID}" type="text" class="text_input"/>
                    </td>
                    <td class="table_body table_body_NoWidth">Name</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="intervieweeName" name="intervieweeName" value="${intervieweeName}" type="text" class="text_input"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="right">
                        <input type="submit" name="searchBtn" value="Search" id="searchBtn" class="" />
                        <input type="button" onclick="interviewAdd()" name="addInterview" value="Add" id="addInterview"/>
                        <input type="button" onclick="refresh('interview')" name="refreshInterview" value="refresh" id="refreshInterview" style="display:none;"/>
                    </td>
                </tr>
            </table>
            </fieldset>
            <!--检索区域End-->
            
            <div id="interviewDetailList" style="border: 0px solid #000000;">
                <table class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
                    <tr class="table_title" align="center">
                        <th scope="col">Passport Number/ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">Phone</th>
                        <th scope="col">Time</th>
                        <th scope="col">Interviewer</th>
                        <th scope="col">CV</th>
                        <th scope="col">&nbsp;</th>
                    </tr>
<s:iterator value="interviewInfo" status="st">
                    <tr class="row" align="center" style="height:28px;">
                        <td width="200px"><s:property value="MP1008_ID"/></td>
                        <td ><s:property value="MP1008_NAME"/></td>
                        <td width="200px"><s:property value="MP1008_PHONE"/></td>
                        <td width="180px"><s:property value="MP1008_INTERVIEW_DATETIME"/></td>
                        <td width="100px"><s:property value="MP1008_INTERVIEWER"/></td>
                        <td width="100px"><a href="documentDownloadEmp.action?fileName=<s:property value='MP1008_CV_PATH'/>" target="download">View</a></td>
                        <td width="100px" align="left">
                            <input type="button" onclick="interviewRecordEdit('<s:property value="MP1008_ID"/>')" name="interviewRecordEditBtn" value="Edit" id="interviewRecordEditBtn" />
                            <input type="button" onclick="interviewRecordDel('<s:property value="MP1008_ID"/>')" name="interviewRecordDelBtn" value="Del" id="interviewRecordDelBtn" />
                        </td>
                    </tr>
</s:iterator>
                </table>
            </div>
            
            <table cellspacing="1" border="0" style="background-color:White;border-width:0px;" align="center">
                <tr class="">
                    <td>
                        <div id="pager" style="border: 1px solid #FFFFFF;"></div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr><td height="5"></td></tr>
</table>

</form>

</body>
</html>