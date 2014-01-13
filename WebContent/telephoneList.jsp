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

<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery1.4.2.js" type="text/javascript" ></script>
<script src="js/jquery.form.js" type="text/javascript" ></script>

<base href="<%=basePath%>">

<script type="text/javascript">
function fileDownload(){
	var options = {
		    url : "telInfoPdf.action",
			type : "post",
			dataType : "script",
			success : function(msg) {
				//window.location.href="login.action";
				//var popstyle="dialogTop:300px;dialogLeft:300px;help:no;center:yes;dialogHeight:300px;dialogWidth:800px;status:no;resizable:no;scroll:no";
				//window.showModalDialog("employeeEducationInit.action?employeeNum=" + comNum, window, popstyle);
		    }
	};
	$("#form2").ajaxSubmit(options);
	//return false;
}
</script>

</head>
<body>

<form id="form2" name="form2" action="employeeTelephoneSearch.action" method="post" enctype="multipart/form-data">
<input id="optSearch" name="optSearch" value="${optSearch}" type="hidden" />
<input id="optPdf" name="optPdf" value="${optPdf}" type="hidden" />

<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
        <td>
            <!-- 头部菜单 Start -->
            <table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
                <tr>
                    <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Telephone Management</td>
                    <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;Help？</td>
                </tr>
                <tr>
                    <td height='27px' class='menubar_function_text' style="width:35%">Operation Function：Telephone List</td> 
                    <td class='menubar_menu_td' align='left'></td>
                </tr>
                <tr><td height='5px' colspan='2'></td></tr>
            </table>
            <!-- 头部菜单 End -->
        </td>
    </tr>
    <!-- 检索条件Start -->
    <tr>
        <td>
            <fieldset style="border-color:#999999;border-width:1px;border-style:Solid;margin-bottom:10px;width:1000px;">
            <legend style="color:#333333;font-size:1.5em;font-weight:bold;">Query Condition</legend>
            <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                <tr>
                    <td class="table_body table_body_NoWidth">employee number</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="employeeNum" name="employeeNum" value="${employeeNum}" type="text" class="text_input" />
                    </td>
                    <td class="table_body table_body_NoWidth">employee name</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="employeeName" name="employeeName" value="${employeeName}" type="text" class="text_input" />
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="right">
<s:if test="optSearch == 1" >
                        <input type="submit" name="searchBtn" value="Search" id="searchBtn" class="" />
</s:if>
<s:if test="optSearch == 0" >
                        <input type="submit" name="searchBtn" value="Search" id="searchBtn" disabled="disabled" class="" />
</s:if>
<s:if test="optPdf == 1" >
                        <input type="button" onclick="fileDownload()" name="downloadBtn" value="Create PDF & DownLoad" id="downloadBtn" />
</s:if>
                    </td>
                </tr>
            </table>
            </fieldset>
        </td>
    </tr>
    <!-- 检索条件End -->

    <!-- 内容部分Start -->
    <tr>
        <td>
            <table cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
                <tr class="table_title" align="center">
                    <th scope="col" width="200px">Employee Number</th>
                    <th scope="col" width="300px">Employee Name</th>
                    <th scope="col" width="200px">Telephone Number</th>
                    <th scope="col" width="200px">Mobile Phone Number</th>
                </tr>
            </table>
<div id="telephoneList" style="overflow:auto;border:solid 0px red;height:600px;width:930px;">
            <table cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
<s:iterator value="employeeTelephoneList" status="st">
                <tr class="row" align="center" style="height:28px;">
                    <td width="200px"><s:property value="MP1001_EMPLOYEE_NUM"></s:property></td>
                    <td width="300px"><s:property value="MP1001_PREFERED_NAME"></s:property> <s:property value="MP1001_SURNAME"></s:property></td>
                    <td width="200px"><s:property value="MP1001_TELEPHONE"></s:property></td>
                    <td width="200px"><s:property value="MP1001_MOBILE_PHONE"></s:property></td>
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