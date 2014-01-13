<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Apply Leave</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="css/wbox.css" rel="stylesheet" type="text/css"></link>

<script src="js/calendar.js" type="text/javascript" ></script>
<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery1.4.2.js" type="text/javascript" ></script>
<script src="js/jquery.form.js" type="text/javascript" ></script>
<script src="js/jquery.pager.js" type="text/javascript" ></script>
<script src="js/wbox.js" type="text/javascript" ></script> 

<base href="<%=basePath%>">

<script type="text/javascript">
//新增联系人信息
function addPositionInfo(){
	var popstyle="dialogTop:300px;dialogLeft:300px;help:no;center:yes;dialogHeight:300px;dialogWidth:800px;status:no;resizable:no;scroll:no";
	window.showModalDialog("positionInfoAddInit.action", window, popstyle);
}
//编辑联系人信息
function positionEdit(seq){
	var popstyle="dialogTop:300px;dialogLeft:300px;help:no;center:yes;dialogHeight:300px;dialogWidth:800px;status:no;resizable:no;scroll:no";
	window.showModalDialog("positionInfoAddInit.action?positionSeq=" + seq, window, popstyle);
}
// 删除联系人信息
function positionDel(seq){
	var param = {"positionSeq" : seq};
	$("#positionDetailListDiv").load("positionInfoDel.action",param);
}
//局部刷新教育、工作经历信息
function refresh(type){
	alert("refresh---1");
	if(type == "position"){
		alert("refresh---2");
		$("#positionDetailListDiv").load("positionDetailInfoSearch.action");
	}
}
</script>

</head>
<body>

<form id="form2" name="form2" method="post">
<input id="optAdd" name="optAdd" value="${optAdd}" type="hidden" />
<input id="optEdit" name="optEdit" value="${optEdit}" type="hidden" />
<input id="optDel" name="optDel" value="${optDel}" type="hidden" />

<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
        <td>
            <!-- 头部菜单 Start -->
            <table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
                <tr>
                    <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;System Management</td>
                    <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;Help？</td>
                </tr>
                <tr>
                    <td height='27px' class='menubar_function_text' style="width:35%">Operation Function：Create System Parameter</td> 
                    <td class='menubar_menu_td' align='left'>
<s:if test="optAdd==1">
                        <input type="button" onclick="addPositionInfo()" name="PositionAddBtn" value="Add Position" id="PositionAddBtn" />
</s:if>
<s:else>
                        <input type="button" onclick="addPositionInfo()" name="PositionAddBtn" value="Add Position" id="PositionAddBtn" disabled="disabled" />
</s:else>
                        <input type="button" onclick="refresh('position')" name="refreshPosition" value="refresh" id="refreshPosition" style="display:none;"/>
                    </td>
                </tr>
                <tr><td height='5px' colspan='2'></td></tr>
            </table>
            <!-- 头部菜单 End -->
        </td>
    </tr>
    
    <!-- 内容部分Start -->
    <tr>
        <td>
            <table cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
                <tr class="table_title" align="center">
                    <th scope="col" width="50px" align="center">NO.</th>
                    <th scope="col" width="500px">position name(E)</th>
                    <th scope="col" width="100px">&nbsp;</th>
                </tr>
            </table>
<div id="positionDetailListDiv" style="overflow:auto;border:solid 0px red;height:600px;width:680px;">
            <table cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
<s:iterator value="positionDetailList" status="st">
                <tr class="row" align="center" style="height:28px;">
                    <td width="50px" align="center">${st.index + 1}</td>
                    <td width="500px"><s:property value="MP0009_POSITION_NAME_E"></s:property></td>
                    <td width="100px">
<s:if test="optEdit==1">
                        <input type="button" onclick="positionEdit(<s:property value="MP0009_SEQ"/>)" name="positionEditBtn" value="Edit" id="positionEditBtn" />
</s:if>
<s:else>
                        <input type="button" onclick="positionEdit(<s:property value="MP0009_SEQ"/>)" name="positionEditBtn" value="Edit" id="positionEditBtn" disabled="disabled" />
</s:else>
<s:if test="optDel==1">
                        <input type="button" onclick="positionDel(<s:property value="MP0009_SEQ"/>)" name="positionDelBtn" value="Del" id="positionDelBtn" />
</s:if>
<s:else>
                        <input type="button" onclick="positionDel(<s:property value="MP0009_SEQ"/>)" name="positionDelBtn" value="Del" id="positionDelBtn" disabled="disabled" />
</s:else>
                    </td>
                </tr>
</s:iterator>
            </table>
</div>
        </td>
    </tr>
    <!-- 内容部分End -->
    
    <tr><td height="5"></td></tr>
</table>

</form>

</body>
</html>