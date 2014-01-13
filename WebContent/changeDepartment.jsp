<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
<script src="js/jquery.pager.js" type="text/javascript" ></script>
<script src="js/wbox.js" type="text/javascript" ></script> 

<script type="text/javascript">
window.document.onkeydown = keyStroke;
function keyStroke(){
	if (window.event.keyCode==13){
		window.event.keyCode=9;
	}
}

function ClearSelect(){
	document.getElementById("departmentID").value = "";
	document.getElementById("departmentName").value = "";
}
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
	
	var param = {"departmentID" : departmentID};
	$("#positionDiv").load("positionSearch.action",param);
}
function depChanged(){
	var depID = document.getElementById("oldDepId").value;
	
	var param = {"departmentID" : depID}; 
	$("#employeeDiv").load("departmentEmployeeSearch.action",param);
}
</script>
</head>
<body>
	<div>
		<s:form action="changeDepartment" method="post" theme="simple">
            <input type="hidden" id="employeeNum" name="employeeNum" value="${employeeNum}"/>
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td>
						<!-- 头部菜单 Start -->
						<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
							<tr>
								<td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Departmental Transfers</td>
								<td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
							</tr>
							<tr>
								<td height='27px' class='menubar_function_text'>Operation Function：Departmental Transfers</td>
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
						
                        <DIV id='tabContent__0' style=''>
                            <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                                <tr>
                                    <td class="table_body table_body_NoWidth">Department</td>
                                    <td class="table_none table_none_NoWidth">
                                        <s:select id="oldDepId" name="mp1004.MP1004_DEPARTMENT_CODE_OLD" value="mp1004.MP1004_DEPARTMENT_CODE_OLD" list="departmentInfoList" onchange="depChanged()" theme="simple"/>
                                    </td>
                                    <td class="table_body table_body_NoWidth">Employee Number</td>
                                    <td class="table_none table_none_NoWidth">
                                        <div id="employeeDiv">
                                            <s:select name="mp1004.MP1004_EMPLOYEE_NUM" value="mp1004.MP1004_EMPLOYEE_NUM" list="employeeInfoList" theme="simple"/>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                            
                            <fieldset style="border-color:#999999;border-width:1px;border-style:Solid;margin-bottom:30px;">
                            <legend style="color:#333333;font-size:1.5em;font-weight:bold;">Departmental Transfer Information</legend>
                            <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" style="margin-bottom:50px;">
                                <tr>
                                    <td class="table_body table_body_NoWidth">New Department</td>
                                    <td class="table_none table_none_NoWidth">
				                        <input id="departmentID" name="mp1004.MP1004_DEPARTMENT_CODE" value="${mp1004.MP1004_DEPARTMENT_CODE}" type="hidden" />
				                        <input id="departmentName" name="mp1004.MP1004_DEPARTMENT_NAME" value="${mp1004.MP1004_DEPARTMENT_NAME}" type="text" class="text_input" readonly="" />
				                        <input type="button" value="select" id="button3" name="buttonselect" onClick="wBox.showBox()" class="cbutton">
				                        <input type="button" value="clear" onClick="javascript:ClearSelect();" class="cbutton" />
			                            <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
			                                <s:param>mp1004.MP1004_DEPARTMENT_NAME</s:param>
			                            </s:fielderror>
                                    </td>
                                    <td class="table_body table_body_NoWidth">New Position</td>
                                    <td class="table_none table_none_NoWidth">
                                        <div id="positionDiv">
                                            <s:select name="mp1004.MP1004_POSITION" value="mp1004.MP1004_POSITION" list="positionList" theme="simple"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="table_body table_body_NoWidth">Transfer Time</td>
                                    <td class="table_none table_none_NoWidth" colspan="3">
                                        <input name="mp1004.MP1004_TRANSFER_DATETIME" value="${mp1004.MP1004_TRANSFER_DATETIME}" type="text" class="text_input" onFocus="calendar(this);"/>
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td class="table_body table_body_NoWidth">Comment</td>
                                    <td class="table_none table_none_NoWidth" colspan="3">
                                        <s:textarea id="commenttx" name="mp1004.MP1004_JOB_DESC" style="width:90%;height:60px;"></s:textarea>
                                    </td>
                                </tr>
                            </table>
                            </fieldset>
                        </DIV>
					</td>
				</tr>

				<tr>
					<td class="menubar_button" id="button_0" align="center">
					    <input type="submit" name="save" value="Save" />
                        <input type="button" onclick="window.location.href='employeeList.action'" name="cancel" value="Cancel" />
					</td>
				</tr>
			</table>
		</s:form>
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
   	html: "<div style='width:500px;height:350px;overflow:auto;scrollbar-face-color:#9EBFE8;scrollbar-shadow-color: #FFFFFF;scrollbar-highlight-color: #FFFFFF;scrollbar-3dlight-color: #9EBFE8;scrollbar-darkshadow-color: #9EBFE8;scrollbar-track-color: #FFFFFF;scrollbar-arrow-color: #FFFFFF'>" + content + "</div>"
   });
</script>
</body>
</html>
