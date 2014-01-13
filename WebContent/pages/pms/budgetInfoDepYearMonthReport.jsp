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

<script src="js/jquery1.4.2.js" type="text/javascript"></script>
<script src="js/jquery.form.js" type="text/javascript" ></script>

<style type="text/css">
.textInput2{border-size:0px; background-color:transparent;border-style:none;}
</style>

<script type="text/javascript">
function excelDown(){
	var options = {
		url : "createBudgetExcelDocument.action",
		type : "post",
		dataType : "script",
		success : function(msg) {
			//nothing to do
		}
	};
	$("#form2").ajaxSubmit(options);
}
</script>
</head>
<body>
<form id="form2" name="form2" action="budgetInfoReportByDepYearMonthSearch.action" method="post" enctype="multipart/form-data">
<input id="optAll" name="optAll" value="${optAll}" type="hidden" />

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
	<tr>
		<td class='menubar_title' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img border='0'src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Budget Report</td>
		<td class='menubar_readme_text' valign='bottom' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help?</td>
	</tr>
	<tr>
	    <td height='27px' class='menubar_function_text' style="background-color:transparent;border:0;">Operation Function: View Budget</td>
	    <td class='menubar_menu_td' align='right' style="background-color:transparent;border:0;">
	        <table border="0" cellspacing="0" cellpadding="0">
	            <tr>
	                <td class="menubar_button" id="button_0" style="background-color:transparent;border:0;"><img border="0" align="texttop" src="images/icon/edit.gif">&nbsp;</td>
	            </tr>
	        </table>
	    </td>
	</tr>
</table>
<!-- 头部菜单 End -->

<input type="button" onclick="showData()" name="btnRefresh" value="refresh" id="btnRefresh" style="display:none;"/>
<div>

    Year:<s:select id="currentYear" name="currentYear" list="yearInfoList" onchange="showData()" theme="simple"/>&nbsp;&nbsp;
    Month:<s:select id="currentMonth" name="currentMonth" list="monthInfoList" onchange="showData()" theme="simple"/>
<s:if test="optAll == 1">
    Department:<s:select id="departmentId" name="departmentId" list="departmentInfoList" onchange="showData()" theme="simple"/>
</s:if>
    Main Class:<s:select id="mainClass" name="mainClass" list="mainClassList" onchange="showData()" theme="simple"/>
</div>
<div style="width:760px;border:0px solid red;" align="right">
    <input type="submit" value="Search" />
    <input type="button" onclick="excelDown()" value="Export To Excel" />
</div>

<table cellspacing="1" border="0" style="background-color:White;border-width:0px;margin-top:5px;width:760px;">
    <tr class="table_title" align="center">
        <th scope="col" width="50px">No.</th>
        <th scope="col" width="100px">Department</th>
        <th scope="col" width="100px">Year</th>
        <th scope="col" width="100px">Month</th>
        <th scope="col" width="120px">Budget Amount</th>
        <th scope="col" width="130px">Additional Budget</th>
        <th scope="col">Expenditure</th>
    </tr>
</table>

<div id="departmentBudgetInfoDiv" style="height:600px;width:760px;border:0px solid red;overflow-y:scroll;">
<table cellspacing="1" border="0" style="background-color:White;border-width:0px;">
<s:iterator id="mp4003InfoList" value="mp4003InfoList" status="st">
   <tr class="row">
       <td width="50px" align="center">${st.index + 1}</td>
       <td width="100px" align="center"><s:property value="MP4003_DEPARTMENT_NAME"/></td>
       <td width="100px" align="center"><s:property value="MP4003_YEAR"/></td>
       <td width="100px" align="center"><s:property value="MP4003_MONTH_NAME"/></td>
       <td width="120px" align="right"><s:property value="MP4003_AMOUNT"/></td>
       <td width="130px" align="right" style="color:green;"><s:property value="MP4003_AMOUNT3"/></td>
       <td width="120px" align="right" style="color:red;"><s:property value="MP4003_AMOUNT4"/></td>
   </tr>
</s:iterator>
</table>
</div>
<table cellspacing="1" border="0" style="background-color:White;border-width:0px;margin-top:0px;width:760px;">
    <tr class="row" align="center">
        <td colspan="5" width="470px" align="right"><s:property value="budgetAmount1"/></td>
        <td width="130px" align="right"><s:property value="budgetAmount3"/></td>
        <td width="138px" align="right"><s:property value="budgetAmount4"/></td>
    </tr>
</table>

</form>
</body>
</html>