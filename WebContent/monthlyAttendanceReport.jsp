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
<base href="<%=basePath%>">

<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="css/wbox.css" rel="stylesheet" type="text/css"></link>

<script src="js/jquery1.4.2.js" type="text/javascript" ></script>
<script src="js/calendar.js" type="text/javascript" ></script>
<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery.form.js" type="text/javascript" ></script>

<script type="text/javascript">

function submitClick(){
	var from = $("#fromDate").val();
	var to = $("#toDate").val();
	if(from == "" || to == ""){
		alert("Please select from date and to date !");
		return ;
	}
	//var param = {"fromDate" : from, "toDate" : to};
	var param = "?fromDate=" + from + "&toDate=" + to;
	var options = {
    		url : "abnormalEarlyLateReport.action" + param,
    		//url : "abnormalEarlyLateReport.action",
    		//data : param,
    		type : "post",
    		//dataType : "html",
    		//dataType : "none",
    		//mimeType : "application/vnd.ms-excel",
    		success : function(msg) {}
    };
    $("#form").ajaxSubmit(options); //can only use .ajaxSubmit
    //$.ajax(options);
}
</script>
</head>
<body>
<s:actionmessage cssStyle="list-style-type:none;" escape="false"/>
	<div>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td>
					<!-- 头部菜单 Start -->
					<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
						<tr>
							<td class='menubar_title'><img border='0' src='images/icon/config.gif' hspace='3' vspace='3' />&nbsp;Monthly Attendance Report</td>
							<td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' border='0' />&nbsp;help？</td>
						</tr>
						<tr>
							<td height='27px' class='menubar_function_text'>Operation Function: Monthly Attendance Report Download</td>
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
			</td>
		  </tr>
	  </table>
	<!-- 头部菜单 End -->
	
		<div>
			<div><h2 style="color:red">Please choose date first</h2></div>
			<form id="form" name="form" method="post" enctype="multipart/form-data">
				<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" style="padding-bottom:50px;">
					<tr>
						<td class="table_body" style="width:150px"><span>From Date: </span></td>
						<td class="table_none">
							<input name="fromDate" id="fromDate" onclick="calendar(this)" />
						</td>
					</tr>
					<tr>
						<td class="table_body"><span>To Date: </span></td>
						<td class="table_none">
							<input name="toDate" id="toDate" onclick="calendar(this)" />
						</td>
					</tr>
				
				
				<tr>
						<td align="center" colspan="2">
							<br />
							<input type="button" id="submitBtn" name="submitBtn" onclick="submitClick()" value="Download" />
						</td>
				</tr>
				</table>					
			</form>			
		</div>
  </div>
</body>
</html>
