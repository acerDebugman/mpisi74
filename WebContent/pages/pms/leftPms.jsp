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
<title>人力资源管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="js/tree/resources/css/lTREE.default.css" charset="utf-8" />
<script type="text/javascript" src="js/tree/js/lTREE.js" charset="utf-8"></script>

<style type="text/css">
.ttl { CURSOR: pointer; COLOR: #ffffff; PADDING-TOP: 4px }
.ttl A:active{COLOR: #000000;TEXT-DECORATION: none}
.ttl A:hover{COLOR: #000000;TEXT-DECORATION: none}
.ttl A:link{COLOR: #000000;TEXT-DECORATION: none}
.ttl A:visited{COLOR: #000000;TEXT-DECORATION: none}
.table_body {BACKGROUND-COLOR: #EDF1F8;height:18px;CURSOR: pointer;}
.table_none {BACKGROUND-COLOR: #FFFFFF;height:18px;CURSOR: pointer;}
</style>

<script type="text/javascript">
function showHide(obj){
	if(document.getElementById("M_100") != null){
		document.getElementById("M_100").style.display = "none";
	}
	if(document.getElementById("M_200") != null){
		document.getElementById("M_200").style.display = "none";
	}
	if(document.getElementById("M_300") != null){
		document.getElementById("M_300").style.display = "none";
	}
	if(document.getElementById("M_400") != null){
		document.getElementById("M_400").style.display = "none";
	}
	if(document.getElementById("M_500") != null){
		document.getElementById("M_500").style.display = "none";
	}
	if(document.getElementById("M_600") != null){
		document.getElementById("M_500").style.display = "none";
	}
	
    var oStyle = document.getElementById(obj).style;
    oStyle.display == "none" ? oStyle.display = "block" : oStyle.display = "none";
}
</script>

</head>
<body bgcolor="#9aadcd" leftmargin="0" topmargin="0" marginheight="0" marginwidth="0">
    <input id="group" name="group" value="${group}" type="hidden" />
	<input id="position" name="position" value="${position}" type="hidden" />
	<input id="departmentID" name="departmentID" value="${departmentID}" type="hidden" />

    <!-- 预算理菜单 -->
    <table cellspacing="0" cellpadding="0" width="180" align="center" border="0">
        <tr>
            <td width="23"><img height="25" src="images/menu/box_topleft.gif" width="23"></td>
            <td class="ttl" onclick="JavaScript:showHide('M_200');" width="180" background="images/menu/box_topbg.gif" style="font-size:9pt;">Budget Management</td>
            <td width="7"><img height="25" src="images/menu/box_topright.gif" width="7"></td>
        </tr>
    </table>
    <table id="M_200" style="display: none" cellspacing="0" cellpadding="0" width="180" align="center" border="0">
        <tr>
            <td background='images/menu/box_bg.gif' height="0px" width='180' colspan='3'>
                <table width="178" border="0" cellpadding="2" cellspacing="1">
                    <tbody>
<s:if test="func0021001 == 1">
                        <tr>
                            <td class="table_none" onclick="javascript:window.parent.frames['mainFrame'].location='budgetSetInit.action';" onmousemove="javascript:TDOverORIn('M_201');" onmouseout="javascript:TDOverOROut('M_201');" id="M_201">
                                <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Budget Management
                            </td>
                        </tr>
</s:if>
<s:if test="func0021002 == 1">
                        <tr>
                            <td class="table_none" onclick="javascript:window.parent.frames['mainFrame'].location='budgetSetInit.action?pageType=view';" onmousemove="javascript:TDOverORIn('M_202');" onmouseout="javascript:TDOverOROut('M_202');" id="M_202">
                                <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">View Budget
                            </td>
                        </tr>
</s:if>

<s:if test="func0021003 == 1">
                        <tr>
                            <td class="table_none" onclick="javascript:window.parent.frames['mainFrame'].location='budgetInfoReportByDepYearInit.action';" onmousemove="javascript:TDOverORIn('M_203');" onmouseout="javascript:TDOverOROut('M_203');" id="M_203">
                                <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Report(Department/Year)
                            </td>
                        </tr>
</s:if>

<s:if test="func0021004 == 1">
                        <tr>
                            <td class="table_none" onclick="javascript:window.parent.frames['mainFrame'].location='budgetInfoReportByDepYearMonthInit.action';" onmousemove="javascript:TDOverORIn('M_204');" onmouseout="javascript:TDOverOROut('M_204');" id="M_204">
                                <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Report(Department)
                            </td>
                        </tr>
</s:if>
<s:if test="func0021006 == 1">
                        <tr>
                            <td class="table_none" onclick="javascript:window.parent.frames['mainFrame'].location='budgetInfoReportByAccYearMonthInit.action';" onmousemove="javascript:TDOverORIn('M_206');" onmouseout="javascript:TDOverOROut('M_206');" id="M_206">
                                <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Report(Item)
                            </td>
                        </tr>
</s:if>
<s:if test="func0021007 == 1">
                        <tr>
                            <td class="table_none" onclick="javascript:window.parent.frames['mainFrame'].location='applyBudgetAdditionInit.action?pageType=add';" onmousemove="javascript:TDOverORIn('M_207');" onmouseout="javascript:TDOverOROut('M_207');" id="M_207">
                                <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Apply Addition Budget
                            </td>
                        </tr>
                        <tr>
                            <td class="table_none" onclick="javascript:window.parent.frames['mainFrame'].location='budgetAdditionMngInit.action';" onmousemove="javascript:TDOverORIn('M_207');" onmouseout="javascript:TDOverOROut('M_207');" id="M_207">
                                <img height='7' hspace='5' src='images/menu/arrow.gif' width='5' align="bottom">Budget Addition Mng
                            </td>
                        </tr>
</s:if>
                    </tbody>
                </table>
            </td>
        </tr>
    </table>
    <table cellspacing="0" cellpadding="0" width="180" align="center" border="0">
        <tr>
            <td colspan="3">
                <img height='10' src='images/menu/box_bottom.gif' width='180'>
            </td>
        </tr>
    </table>
    
</body>
</html>

<script type="text/javascript">
var NowClickName="";
function NowShow(TopMenuName,Url){
    document.getElementById(TopMenuName).className  = "table_body";
    if (NowClickName!="" &&NowClickName!=TopMenuName)
        document.getElementById(NowClickName).className  = "table_none"; 
    NowClickName = TopMenuName;
    //var o=window.open(url); 
   window.parent.frames["mainFrame"].location=Url;
   //parment.mainFrame.src=Url;
}
function TDOverOROut(iname){
    if (NowClickName!=iname){
        document.getElementById(iname).className = "table_none";
    }
}
function TDOverORIn(iname){
    if (NowClickName != iname){
        document.getElementById(iname).className = "table_body";
    }
}
</script>