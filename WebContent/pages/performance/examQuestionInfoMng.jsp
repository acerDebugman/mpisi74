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

<script src = "js/skins2/Main82.js" type="text/javascript"></script>
<script src="js/jquery1.4.2.js" type="text/javascript"></script>
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
    
    var param = {"pageNum" : pageclickednumber,"param1" : param1,"param2" : param2};
    document.getElementById("pageNum").value = pageclickednumber;
    $("#examQuestionInfoListDiv").load("examQuestionInfoPageClick.action",param);
}
</script>
<script type="text/javascript">
//局部刷新
function pageRefresh(){
    var param1 = $("#param1").val();
    var param2 = $("#param2").val();
    var _pageNum = document.getElementById("pageNum").value;
    var param = {"pageNum" : _pageNum,"param1" : param1,"param2" : param2};
	$("#examQuestionInfoListDiv").load("examQuestionInfoPageClick.action",param);
}
function edit(id){
	var popstyle="dialogTop:50px;dialogLeft:300px;help:no;center:yes;dialogHeight:520px;dialogWidth:1000px;status:no;resizable:yes;scroll:no;loacation:no;toolbar:no;";
	window.showModalDialog("examQuestionAddInit.action?pageType=edit&commonSeq=" + id,window,popstyle);
}
function Del(id){
	var msg = "Are you sure you want to delete this record?\n\n"; 
	if (confirm(msg)==true){
	    var param1 = $("#param1").val();
	    var param2 = $("#param2").val();
		 var _pageNum = document.getElementById("pageNum").value;
		 var param = {"pageNum" : _pageNum,"commonSeq" : id,"param1" : param1,"param2" : param2};
		 $("#examQuestionInfoListDiv").load("examQuestionInfoDelete.action",param);
		 
	    return true; 
	}else{ 
	    return false; 
	}
}
</script>

</head>
<body>
<s:form action="examQuestionInfoSearch" method="post" theme="simple">
<input id="pageCount" name="pageCount" value="${pageCount}" type="hidden" />
<input id="pageNum" name="pageNum" value="${pageNum}" type="hidden" />
<input type="button" onclick="pageRefresh()" name="btnRefresh" value="refresh" id="btnRefresh" style="display:none;"/>
<input id="optSearch" name="optSearch" value="${optSearch}" type="hidden" />
<input id="optDel" name="optDel" value="${optDel}" type="hidden" />
<input id="optEdit" name="optEdit" value="${optEdit}" type="hidden" />

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
	<tr>
		<td class='menubar_title' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img border='0'src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Appraisal questionnaire Management</td>
		<td class='menubar_readme_text' valign='bottom' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help?</td>
	</tr>
</table>
<!-- 头部菜单 End -->

<!--检索区域Start-->
<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" style="margin-top:5px;">
    <tr>
        <td width="20%">Appraisal questionnaire</td>
        <td width="30%"><input id="param1" name="param1" value="${param1}" type="text" style="width:98%;"/></td>
        <td width="20%">Status</td>
        <td width="30%"><s:select id="param2" name="param2" value="param2" list="commonStatusList" theme="simple"/></td>
    </tr>
    <tr>
        <td colspan="4" align="right"><input type="submit" name="searchBtn" value="Search" id="searchBtn" <s:if test="optSearch == 0" >disabled="disabled"</s:if> class="" /></td>
    </tr>
</table>
<!--检索区域End-->

<div id="examQuestionInfoListDiv" style="border: 0px solid #000000;margin-top:20px;">
<table cellspacing="0" cellpadding="0" style="border:0;width:100%;">
    <tr align="center" style="background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px">
        <th scope="col" class="tdBg">Seq</th>
        <th scope="col" class="tdBg">Title</th>
        <th scope="col" class="tdBg">Sub Title</th>
        <th scope="col" class="tdBg">Comment</th>
        <th scope="col" class="tdBg">Status</th>
        <th scope="col" class="tdBg">&nbsp;</th>
    </tr>
<s:iterator value="examQuestionInfoList" status="st">
    <tr align="left" style="height:28px;">
        <td width="50px" align="center" class="tdBorder">${st.index + 1}</td>
        <td class="tdBorder"><s:property value="MP7001_TITLE2"/></td>
        <td class="tdBorder"><s:property value="MP7001_SUB_TITLE2"/>&nbsp;</td>
        <td class="tdBorder"><s:property value="MP7001_COMMENT2"/>&nbsp;</td>
        <td width="50px" class="tdBorder"><s:property value="MP7001_STATUS_NAME"/>&nbsp;</td>
        <td width="100px" align="center" class="tdBorder">
            <input class="input0" type="button" onclick="edit('<s:property value="MP7001_SEQ"/>')" name="editBtn" value="Edit" id="editBtn" <s:if test="optEdit == 0" >disabled="disabled"</s:if>/>
            <input class="input0" type="button" onclick="Del('<s:property value="MP7001_SEQ"/>')" name="delBtn" value="Del" id="delBtn" <s:if test="optDel == 0" >disabled="disabled"</s:if>/>
        </td>
    </tr>
</s:iterator>
</table>
</div>

<div id="pager" style="border: 0px solid #000000;height:30px;width:1300px;margin-top:10px;"></div>

</s:form>
</body>
</html>