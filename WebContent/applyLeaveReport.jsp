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
<link href="css/wbox.css" rel="stylesheet" type="text/css"></link>

<script src="js/calendar.js" type="text/javascript" ></script>
<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery1.4.2.js" type="text/javascript" ></script>
<script src="js/jquery.form.js" type="text/javascript" ></script>
<script src="js/wbox.js" type="text/javascript" ></script>

<script type="text/javascript">
function checkPara(){
	var _from = document.getElementById("fromDate").value;
	var _to   = document.getElementById("toDate").value;
	if(_from == "" || _to == ""){
		alert("Please input start date and finish date.");
		return "false";
	}
}
//清除选择的部门
function ClearSelect(){
	document.getElementById("departmentID").value = "";
	document.getElementById("departmentName").value = "";
}
// 选择部门
function SelectDepartment(){
	var departmentID = "";
	var departmentName = "";
	var radios=document.getElementsByName("seq");
	for(var i=0;i<radios.length;i++){
		 if(radios[i].checked==true){
			 departmentID = radios[i].id;
			 departmentName = radios[i].value;
			 break;
		 }
	}
	
	document.getElementById("departmentID").value = departmentID;
	document.getElementById("departmentName").value = departmentName;
}
function createExcelDoc(){
	var _from = document.getElementById("fromDate").value;
	var _to   = document.getElementById("toDate").value;
	var _employeeNum = document.getElementById("employeeNum").value;
	var _departmentID = document.getElementById("departmentID").value;

	if(_from == "" || _to == ""){
		alert("Please input start date and finish date.");
		return "false";
	}else{
	    var options = {
	    	    url : "createLeaveInfoExcelDocument.action?employeeNum=" + _employeeNum + "&departmentID=" + _departmentID + "&fromDate=" + _from + "&toDate=" + _to,
	    		type : "post",
	    		dataType : "html",
	    		success : function(msg) {
	    			//alert("hello");
	    		}
	    	};
	    	$("#from1").ajaxSubmit(options);
	}
}
</script>
</head>
<body>
	<div>
		<form id="from1" action="leaveApplyReportSearch.action" method="post" enctype="multipart/form-data">
		    <input id="roleGroup" name="roleGroup" value="${roleGroup}" type="hidden" />
            <input id="optSearch" name="optSearch" value="${optSearch}" type="hidden" />
            <input id="optAll" name="optAll" value="${optAll}" type="hidden" />
            <input id="optDepartment" name="optDepartment" value="${optDepartment}" type="hidden" />
            <input id="optPersonal" name="optPersonal" value="${optPersonal}" type="hidden" />	

			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td>
						<!-- 头部菜单 Start -->
						<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
							<tr>
								<td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp; Leave Management</td>
								<td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
							</tr>
							<tr>
								<td height='27px' class='menubar_function_text'>Operation Function：Leave Report</td>
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
			            <fieldset style="border-color:#999999;border-width:1px;border-style:Solid;margin-bottom:10px;width:1250px;">
			            <!-- <legend style="color:#333333;font-size:1.5em;font-weight:bold;">Query Condition</legend> -->
			            <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
			                <tr>
			                    <td class="table_body table_body_NoWidth">From Date<span class="errorcss">*</span></td>
			                    <td class="table_none table_none_NoWidth">
			                        <input id="fromDate" name="fromDate" value="${fromDate}" type="text" class="text_input" onfocus="calendar(this);"/>
			                    </td>
			                    <td class="table_body table_body_NoWidth">To Date<span class="errorcss">*</span></td>
			                    <td class="table_none table_none_NoWidth">
			                        <input id="toDate" name="toDate" value="${toDate}" type="text" class="text_input" onfocus="calendar(this);"/>
			                    </td>
			                </tr>
			                <tr>
<s:if test="optAll == 1 || optDepartment==1" >
			                    <td class="table_body table_body_NoWidth">Employee number</td>
			                    <td class="table_none table_none_NoWidth">
			                        <input id="employeeNum" name="employeeNum" value="${employeeNum}" type="text" class="text_input" />
			                    </td>
</s:if>
<s:else>
			                    <td></td>
			                    <td></td>
</s:else>

<s:if test="optAll == 1" >
			                    <td class="table_body table_body_NoWidth">Department</td>
			                    <td class="table_none table_none_NoWidth">
			                        <input id="departmentID" name="departmentID" value="${departmentID}" type="hidden" />
			                        <input id="departmentName" name="departmentName" value="${departmentName}" type="text" class="text_input" readonly="readonly" style="border:solid 0px;background-color:transparent"/>
			                        <input type="button" value="select" id="button3" name="buttonselect" onClick="wBox.showBox()" class="cbutton">
			                        <input type="button" value="clear" onClick="javascript:ClearSelect();" class="cbutton" />
			                    </td>
</s:if>
			                </tr>
			                <tr>
			                    <td colspan="4" align="right">
			                        <input type="button" onclick="createExcelDoc()" value="Export To Excel" />
<s:if test="optSearch == 1" >
			                        <input type="submit" name="searchBtn" value="Search" id="searchBtn" onclick='javascript:if(checkPara()=="false") { return false;}' class="" />
</s:if>
<s:if test="optSearch == 0" >
                                    <input type="submit" name="searchBtn" value="Search" id="searchBtn" onclick='javascript:if(checkPara()=="false") { return false;}' disabled="disabled" class="" />
</s:if>
			                    </td>
			                </tr>
			            </table>
			            </fieldset>
			            <!--检索区域End-->
			            
                <table cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
                    <tr class="" style="FONT: bold 14px Tahoma, Verdana;COLOR: #ffffff;BACKGROUND-COLOR: #698cc3;HEIGHT: 35px;BACKGROUND: url(images/header_bg.gif) #2f589c repeat-x 0px 0px;PADDING-LEFT: 5px;PADDING-RIGHT:5px;" align="center">
                        <th scope="col" width="150px">Employee Number</th>
                        <th scope="col" width="200px">Employee Name</th>
                        <th scope="col" width="150px">Department</th>
                        <th scope="col" width="100px">Annual</th>
                        <th scope="col" width="100px">Sick</th>
                        <th scope="col" width="100px">Family Resp</th>
                        <th scope="col" width="100px">Study</th>
                        <th scope="col" width="100px">Maternity</th>
                        <th scope="col" width="100px">Unpaid</th>
                        <th scope="col" width="100px">Official Business</th>
                    </tr>
                </table>

<div style="overflow:auto;border:solid 0px red;height:600px;width:1250px;">
                <table cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
<s:iterator value="leaveReportList" status="st">
                    <tr class="row" align="center" style="height:28px;">
                        <td width="150px"><s:property value="MP2001_EMPLOYEE_NUM"></s:property></td>
                        <td width="200px"><s:property value="MP2001_PREFERED_NAME"></s:property> <s:property value="MP1001_SURNAME"></s:property></td>
                        <td width="150px"><s:property value="MP2001_DEPARTMENT_NAME"></s:property></td>
                        <td width="100px"><s:property value="MP2001_LEAVE_TYPE_ANNUAL"></s:property></td>
                        <td width="100px"><s:property value="MP2001_LEAVE_TYPE_SICK"></s:property></td>
                        <td width="100px"><s:property value="MP2001_LEAVE_TYPE_FAMILY"></s:property></td>
                        <td width="100px"><s:property value="MP2001_LEAVE_TYPE_STUDY"></s:property></td>
                        <td width="100px"><s:property value="MP2001_LEAVE_TYPE_MATERNITY"></s:property></td>
                        <td width="100px"><s:property value="MP2001_LEAVE_TYPE_UNPAID"></s:property></td>
                        <td width="100px"><s:property value="MP2001_LEAVE_TYPE_OFFICIAL_BUSINESS"></s:property></td>
                    </tr>
</s:iterator>
                </table>
</div>
					</td>
				</tr>
			</table>
		</form>
	</div>

<script type="text/javascript">
var content = "<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">";
content += "<tr style='background-color:#C8D8F1'><td width='50px' align='center'>Num</td><td width='150px' align='center'>Department Code</td><td width='200px' align='center'>Department Name</td></tr>";
content += "<s:iterator value='departmentList' status='st'><tr>";
content += "<td><input id='<s:property value='MP0002_SEQ'></s:property>' type='radio' name='seq' value='<s:property value='MP0002_DEPARTMENT_NAME'/>'></input></td>";
content += "<td><s:property value='MP0002_DEPARTMENT_NUM'></s:property></td>";
content += "<td><s:property value='MP0002_DEPARTMENT_NAME'></s:property></td>";
content += "</tr></s:iterator>";
content += "<tr><td colspan='3' align='center'><input type='submit' name='selectDepart' value='Select Department' id='selectDepart' onclick='SelectDepartment();wBox.close()'/></td></tr>";
content += "</table>";

var wBox=$("#button3").wBox({
   	title: "Department",
   	html: "<div style='width:500px;height:380px;overflow:auto;scrollbar-face-color:#9EBFE8;scrollbar-shadow-color: #FFFFFF;scrollbar-highlight-color: #FFFFFF;scrollbar-3dlight-color: #9EBFE8;scrollbar-darkshadow-color: #9EBFE8;scrollbar-track-color: #FFFFFF;scrollbar-arrow-color: #FFFFFF'>" + content + "</div>"
   });
</script>

</body>
</html>
