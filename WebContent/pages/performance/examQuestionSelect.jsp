<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<base target="_self"/>
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

<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery1.4.2.js" type="text/javascript" ></script>
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
var closeWinFunc = window.close;
window.close = function() {
    window.open("", "_self");
    closeWinFunc();
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
    $("#examQuestionInfoListDiv").load("examQuestionSelectPageClick.action",param);
}
</script>
<script type="text/javascript">
function sel(id){
 	var commonSeq = $("#commonSeq").val();
 	$.ajax({
		url:"examPlanItemInfoSave.action?param1=" + id + "&commonSeq=" + commonSeq,
		type:"post",
		dataType:"html",
		//data: params,
		error:function(ex){
			alert("failed:" + ex.responseText);
		},
		success:function(ex){
			window.dialogArguments.document.getElementById('btnRefresh').click();
			alert(ex);
		}
	});
}
</script>

</head>
<body>
<s:form action="examQuestionInfoSearch" method="post" theme="simple">
<input id="pageCount" name="pageCount" value="${pageCount}" type="hidden" />
<input id="pageNum" name="pageNum" value="${pageNum}" type="hidden" />
<input id="commonSeq" name="commonSeq" value="${commonSeq}" type="hidden" />

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
	<tr>
		<td class='menubar_title' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img border='0'src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Add New Appraisal Questionnaire</td>
		<td class='menubar_readme_text' valign='bottom' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help?</td>
	</tr>
</table>
<!-- 头部菜单 End -->

<div id="examQuestionInfoListDiv" style="border: 0px solid #000000;margin-top:20px;">
<table cellspacing="0" cellpadding="0" style="border:0;width:100%;">
    <tr align="center" style="background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px">
        <th scope="col" class="tdBg">Seq</th>
        <th scope="col" class="tdBg">Title</th>
        <th scope="col" class="tdBg">Status</th>
        <th scope="col" class="tdBg">&nbsp;</th>
    </tr>
<s:iterator value="examQuestionInfoList" status="st">
    <tr align="left" style="height:28px;">
        <td width="50px" align="center">${st.index + 1}</td>
        <td><s:property value="MP7001_TITLE2"/></td>
        <td width="50px"><s:property value="MP7001_STATUS_NAME"/>&nbsp;</td>
        <td width="50px" align="center">
            <input type="button" onclick="sel('<s:property value="MP7001_SEQ"/>')" name="selBtn" value="Select" id="selBtn"/>
        </td>
    </tr>
</s:iterator>
</table>
</div>

<div id="pager" style="border: 0px solid #000000;height:30px;width:1300px;margin-top:10px;"></div>

</s:form>
</body>
</html>