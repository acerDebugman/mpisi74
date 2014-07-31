<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新建员工档案</title>
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

<style type="text/css">
.h
{
display:none;
}
</style>

<script type="text/javascript">
window.document.onkeydown = keyStroke;
function keyStroke(){
	if (window.event.keyCode==13){
		window.event.keyCode=9;
	}
}
function uploadImage() {
	$(document).ready(function() {
		var options = {
		    url : "uploadFile.action",
			type : "POST",
			dataType : "script",
			success : function(msg) {
				if (msg.indexOf("#") > 0) {
				    var data = msg.split("#");
					$("#tipDiv").html(data[0]);
					$("#showImage").html("<img width='80px' height='100px' src='showImage.action?imageUrl="+data[1]+"'/>");
				} else {
				    $("#tipDiv").html(msg);
				}
		    }
	    };
	    $("#form2").ajaxSubmit(options);
	    return false;
    });
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
function uploadFile(){
	var comNum = $("#empNum").val();
    var path = $("#filePath").val();
    if ($("#filePath").val() == "") { 
    	alert("Please select a picture。"); 
    	return; 
    } 
	$("#pictureDiv").load("uploadPic.action",{"employeeNum" :comNum, "filePath" :path});
}
//局部刷新教育、工作经历信息
function refresh(type){
	var empNum = $("#empNum").val();
	var param = {"employeeNum" : empNum};
	if(type == "education"){
		$("#universityList").load("employeeEducationSearch.action",param);
	}else if(type == "experience"){
		$("#workList").load("employeeExperienceSearch.action",param);
	}else if(type == "contact"){
		$("#contactList").load("employeeContactSearch.action",param);
	}
}
// 新增教育信息
function addEducationInfo(){
	var comNum = $("#empNum").val();
	var popstyle="dialogTop:300px;dialogLeft:300px;help:no;center:yes;dialogHeight:300px;dialogWidth:800px;status:no;resizable:no;scroll:no";
	window.showModalDialog("employeeEducationInit.action?employeeNum=" + comNum, window, popstyle);
	//window.open("employeeEducationInit.action?employeeNum=" + comNum, "","toolbar=no,location=no,directories=no,status=yes,menubar=no,resizable=yes,scrollbars=yes");
}
//编辑教育信息
function eduEdit(seq){
	var popstyle="dialogTop:300px;dialogLeft:300px;help:no;center:yes;dialogHeight:300px;dialogWidth:800px;status:no;resizable:no;scroll:no";
	window.showModalDialog("employeeEducationEdit.action?eduSeq=" + seq, window, popstyle);
}
// 删除教育信息
function eduDel(seq){
	var msg = "Are you sure you want to delete this record?\n\n"; 
	if (confirm(msg)==true){
		var param = {"eduSeq" : seq};
		$("#universityList").load("educationInfoDel.action",param);
	    return true; 
	}else{ 
	    return false; 
	}
}

//新增工作经历信息
function addExperienceInfo(){
	var comNum = $("#empNum").val();
	var popstyle="dialogTop:300px;dialogLeft:300px;help:no;center:yes;dialogHeight:500px;dialogWidth:800px;status:no;resizable:no;scroll:no";
	window.showModalDialog("employeeExperienceInit.action?employeeNum=" + comNum, window, popstyle);
}
//编辑工作经历信息
function expEdit(seq){
	var popstyle="dialogTop:300px;dialogLeft:300px;help:no;center:yes;dialogHeight:500px;dialogWidth:800px;status:no;resizable:no;scroll:no";
	window.showModalDialog("employeeExperienceEdit.action?expSeq=" + seq, window, popstyle);
}
//删除工作经历信息
function expDel(seq){
	var msg = "Are you sure you want to delete this record?\n\n"; 
	if (confirm(msg)==true){
		var param = {"expSeq" : seq};
		$("#workList").load("workInfoDel.action",param);
	    return true; 
	}else{ 
	    return false; 
	}
}

//新增联系人信息
function addContactPersonInfo(){
	var comNum = $("#empNum").val();
	var popstyle="dialogTop:300px;dialogLeft:300px;help:no;center:yes;dialogHeight:300px;dialogWidth:800px;status:no;resizable:no;scroll:no";
	window.showModalDialog("employeeContactInit.action?employeeNum=" + comNum, window, popstyle);
	//window.open("employeeEducationInit.action?employeeNum=" + comNum, "","toolbar=no,location=no,directories=no,status=yes,menubar=no,resizable=yes,scrollbars=yes");
}
//编辑联系人信息
function conEdit(seq){
	var popstyle="dialogTop:300px;dialogLeft:300px;help:no;center:yes;dialogHeight:300px;dialogWidth:800px;status:no;resizable:no;scroll:no";
	window.showModalDialog("employeeContactEdit.action?conSeq=" + seq, window, popstyle);
}
// 删除联系人信息
function conDel(seq){
	var msg = "Are you sure you want to delete this record?\n\n"; 
	if (confirm(msg)==true){
		var param = {"conSeq" : seq};
		$("#contactList").load("contactInfoDel.action",param);
	    return true; 
	}else{ 
	    return false; 
	}
}
function checkStatus(){
	var sts = document.getElementById("status").value;
	if( 3 == sts ){
		document.getElementById("resignRow1").style.display = "block";
		document.getElementById("resignRow2").style.display = "block";
	}else{
		document.getElementById("resignRow1").style.display = "none";
		document.getElementById("resignRow2").style.display = "none";
	}
}
// 判断员工状态

</script>

</head>

<body bgColor="#FFFFFF" topMargin="5" theme="simple" onload="checkStatus()">
<div>
<form id="form2" action="employeeEdit.action" method="post" enctype="multipart/form-data">
<input type="hidden" id="educationHidden" name="educationHidden" value=""/>
<input type="hidden" id="workHidden" name="workHidden" value=""/>
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <td>
            <!-- 头部菜单 Start -->
            <table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
              <tr>
                <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Employee Management</td>
                <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;Help？</td>
              </tr>
              <tr>
                <td height='27px' class='menubar_function_text'>Operation Function：Create New Employee Profile</td>
                <td class='menubar_menu_td' align='right'>
                  <table border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td class="menubar_button" id="button_0"></td>
                      <td class="menubar_button" id="button_0"></td>
                    </tr>
                  </table>
                </td>
              </tr>
              <tr>
                <td height='5px' colspan='2'><s:property value="errorMessage"/></td>
              </tr>
            </table>
            <!-- 头部菜单 End -->
            
            <!-- 选项卡 Start -->
            <TABLE cellSpacing=0 cellPadding=0 width='100%' align=center border=0>   
            <TBODY>   
                <TR>     
                    <TD style='PADDING-LEFT: 2px; HEIGHT: 22px' background='images/menu/tab_top_bg.gif'>
                        <TABLE cellSpacing=0 cellPadding=0 border=0>
                            <TBODY>
                                <TR>
                                    <!--按钮　Start-->
                                    <TD >
                                        <TABLE height=22 cellSpacing=0 cellPadding=0 border=0>
                                            <TBODY>
                                                   <TR>
                                                     <TD width=3><IMG id=tabImgLeft__0 height=22 src='images/menu/tab_unactive_left.gif'  width=3></TD>
                                                     <TD class=tab id=tabLabel__0 onclick='javascript:tabClick(0,4)' background='images/menu/tab_unactive_bg.gif' UNSELECTABLE='on'>Personal Information</TD>
                                                     <TD width=3><IMG id=tabImgRight__0 height=22 src='images/menu/tab_unactive_right.gif' width=3></TD>
                                                   </TR>
                                            </TBODY>
                                        </TABLE>
                                    </TD>
                                    <TD >
                                        <TABLE height=22 cellSpacing=0 cellPadding=0 border=0>
                                            <TBODY>
                                                   <TR>
                                                     <TD width=3><IMG id=tabImgLeft__1 height=22 src='images/menu/tab_unactive_left.gif'  width=3></TD>
                                                     <TD class=tab id=tabLabel__1 onclick='javascript:tabClick(1,4)' background='images/menu/tab_unactive_bg.gif' UNSELECTABLE='on'>Education Information</TD>
                                                     <TD width=3><IMG id=tabImgRight__1 height=22 src='images/menu/tab_unactive_right.gif' width=3></TD>
                                                   </TR>
                                            </TBODY>
                                        </TABLE>
                                    </TD>
                                    <TD >
                                        <TABLE height=22 cellSpacing=0 cellPadding=0 border=0>
                                            <TBODY>
                                                   <TR>
                                                     <TD width=3><IMG id=tabImgLeft__2 height=22 src='images/menu/tab_unactive_left.gif'  width=3></TD>
                                                     <TD class=tab id=tabLabel__2 onclick='javascript:tabClick(2,4)' background='images/menu/tab_unactive_bg.gif' UNSELECTABLE='on'>Previous Working Experience</TD>
                                                     <TD width=3><IMG id=tabImgRight__2 height=22 src='images/menu/tab_unactive_right.gif' width=3></TD>
                                                   </TR>
                                            </TBODY>
                                        </TABLE>
                                    </TD>
                                    <TD >
                                        <TABLE height=22 cellSpacing=0 cellPadding=0 border=0>
                                            <TBODY>
                                                   <TR>
                                                     <TD width=3><IMG id=tabImgLeft__3 height=22 src='images/menu/tab_unactive_left.gif'  width=3></TD>
                                                     <TD class=tab id=tabLabel__3 onclick='javascript:tabClick(3,4)' background='images/menu/tab_unactive_bg.gif' UNSELECTABLE='on'>Emergency Contact Information</TD>
                                                     <TD width=3><IMG id=tabImgRight__3 height=22 src='images/menu/tab_unactive_right.gif' width=3></TD>
                                                   </TR>
                                            </TBODY>
                                        </TABLE>
                                    </TD>
                                    <!--按钮 End-->
                                </TR>
                            </TBODY>
                        </TABLE>
                    </TD>
                </TR>
                <TR>
                <TD bgColor=#ffffff>           
                    <TABLE cellSpacing=0 cellPadding=0 width='100%' border=0>
                    <TBODY>
                    <TR>
                        <TD width=1 background='images/menu/tab_bg.gif'>
                            <IMG  height=1 src='images/menu/tab_bg.gif'  width=1>
                        </TD>
                        <TD style='PADDING-RIGHT: 15px; PADDING-LEFT: 15px; PADDING-BOTTOM: 15px; PADDING-TOP: 15px; HEIGHT: 100px' vAlign=top>
                            <!--内容框Start-->
                            <!--基本信息-->
                            <DIV id='tabContent__0' style='display:none'>
                                <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Employee Number</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input id="empNum" name="mp1001.MP1001_EMPLOYEE_NUM" value="${mp1001.MP1001_EMPLOYEE_NUM}" type="text" readonly="readonly" class="text_input" style="border:solid 0px;background-color:transparent"/>
                                        </td>
                                        <td class="table_none table_none_NoWidth" colspan="2" rowspan="3">
                                            <div id="tipDiv"></div>
                                            <div id="showImage"><img width="80px" height="100px" src="<s:property value="mp1001.MP1001_PICTURE_NAME"/>"/></div>
                                            <input name="mp1001.MP1001_PICTURE_NAME" value="${mp1001.MP1001_PICTURE_NAME}" type="hidden"/>
                                            <input id="fileupload" name="fileupload" type="file" />
                                            <input type="button" class="right-button02" onclick="uploadImage()" value="Add Picture" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">ID/Passport Number</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_EMPLOYEE_ID" value="${mp1001.MP1001_EMPLOYEE_ID}" type="text" class="text_input" />
                                            <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
                                                <s:param>mp1001.MP1001_EMPLOYEE_ID</s:param>
                                            </s:fielderror>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">ID/Passport Surname</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_SURNAME" value="${mp1001.MP1001_SURNAME}" type="text" class="text_input" />
                                            <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
                                                <s:param>mp1001.MP1001_SURNAME</s:param>
                                            </s:fielderror>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">ID/Passport Firstname</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_FIRSTNAME" value="${mp1001.MP1001_FIRSTNAME}" type="text" class="text_input" />
                                            <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
                                                <s:param>mp1001.MP1001_FIRSTNAME</s:param>
                                            </s:fielderror>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Prefered Name</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_PREFERED_NAME" value="${mp1001.MP1001_PREFERED_NAME}" type="text" class="text_input" />
                                            <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
                                                <s:param>mp1001.MP1001_PREFERED_NAME</s:param>
                                            </s:fielderror>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Employee Type</td>
                                        <td class="table_none table_none_NoWidth">
				                            <s:select name="mp1001.MP1001_VISA_TYPE" list="visaTypeList" theme="simple"/>
                                            <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
                                                <s:param>mp1001.MP1001_VISA_TYPE</s:param>
                                            </s:fielderror>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Date of Birth</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_BIRTHDAY" value="${mp1001.MP1001_BIRTHDAY}" type="text" class="text_input" onfocus="calendar(this);"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Gender</td>
                                        <td class="table_none table_none_NoWidth">
				                            <s:select name="mp1001.MP1001_GENDER" list="genderList" theme="simple"/>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Religion</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_RELIGION" value="${mp1001.MP1001_RELIGION}" type="text" class="text_input" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Department</td>
                                        <td class="table_none table_none_NoWidth">
					                        <input id="departmentID" name="mp1001.MP1001_DEPARTMENT_ID" value="${mp1001.MP1001_DEPARTMENT_ID}" type="hidden" />
					                        <input id="departmentName" name="mp1001.MP1001_DEPARTMENT_NAME" value="${mp1001.MP1001_DEPARTMENT_NAME}" type="text" class="text_input" readonly="readonly" style="border:solid 0px;background-color:transparent"/>
					                        <input type="button" value="select" id="button3" name="buttonselect" onClick="wBox.showBox()" class="cbutton">
					                        <input type="button" value="clear" onClick="javascript:ClearSelect();" class="cbutton" />
                                            <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
                                                <s:param>mp1001.MP1001_DEPARTMENT_NAME</s:param>
                                            </s:fielderror>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Job Title</td>
                                        <td class="table_none table_none_NoWidth">
                                            <div id="positionDiv">
                                                <s:select name="mp1001.MP1001_POSITION" list="positionList" theme="simple"/>
                                            </div>
                                            <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
                                                <s:param>mp1001.MP1001_POSITION</s:param>
                                            </s:fielderror>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Nationality</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_NATIONALITY" value="${mp1001.MP1001_NATIONALITY}" type="text" class="text_input" />
                                        </td>
                                        <td class="table_body table_body_NoWidth">Race</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_RACE" value="${mp1001.MP1001_RACE}" type="text" class="text_input" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Marriage Status</td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:select name="mp1001.MP1001_MARRIAGE_STATUS" list="marryStatusList" theme="simple"/>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Starting Date<span class="errorcss">*</span></td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_ENTRY_DATE" value="${mp1001.MP1001_ENTRY_DATE}" type="text" class="text_input" onfocus="calendar(this);"/>
                                            <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
                                                <s:param>mp1001.MP1001_ENTRY_DATE</s:param>
                                            </s:fielderror>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Email Address</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_EMAIL" value="${mp1001.MP1001_EMAIL}" type="text" class="text_input" />
                                            <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
                                                <s:param>mp1001.MP1001_EMAIL</s:param>
                                            </s:fielderror>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Mobile Phone Number</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_MOBILE_PHONE" value="${mp1001.MP1001_MOBILE_PHONE}" type="text" class="text_input" />
                                            <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
                                                <s:param>mp1001.MP1001_MOBILE_PHONE</s:param>
                                            </s:fielderror>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Telephone Number</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_TELEPHONE" value="${mp1001.MP1001_TELEPHONE}" type="text" class="text_input" />
                                        </td>
                                        <td class="table_body table_body_NoWidth">Other Contact Number</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_OTHER_CONTACT" value="${mp1001.MP1001_OTHER_CONTACT}" type="text" class="text_input" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Company Email Address</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_COMPANY_EMAIL" value="${mp1001.MP1001_COMPANY_EMAIL}" type="text" class="text_input"/>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Group<span class="errorcss">*</span></td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:select name="mp1001.MP1001_GROUP" list="groupList" theme="simple"/>
                                        </td>
                                    </tr>
                                    
                                    
                                    
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Payroll Number</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_PAYROLL_NUM" value="${mp1001.MP1001_PAYROLL_NUM}" type="text" class="text_input"/>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Work Time Pattern</td>
                                        <td class="table_none table_none_NoWidth">
                                        	<span><s:property value="mp1001.empWorkTimePattern_R.workTimePattern.name" /></span>
                                        </td>
                                    </tr>
                                    
                                    
                                    
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Top Level of Qualification</td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:select id="level" name="mp1001.MP1001_DEGREE_LEVEL" list="topDegreeList" theme="simple"/>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Status<span class="errorcss">*</span></td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:select id="status" name="mp1001.MP1001_STATUS" list="statusList" theme="simple" onChange="checkStatus()"/>
                                        </td>
                                    </tr>
					                <tr>
					                    <td class="table_body table_body_NoWidth">Become a regular worker</td>
					                    <td class="table_none table_none_NoWidth">
					                        <s:property value="mp1001.MP1001_CHG_TIME"/>
					                    </td>
					                    <td class="table_body table_body_NoWidth">Approver</td>
					                    <td class="table_none table_none_NoWidth">
					                        <s:property value="mp1001.MP1001_CHG_EMPLOYE"/>
					                    </td>
					                </tr>

                                    <tr id="resignRow1" style="display:none;">
                                        <td class="table_body table_body_NoWidth">Resign Date<span class="errorcss">*</span></td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_RESIGN_DATE" value="${mp1001.MP1001_RESIGN_DATE}" type="text" class="text_input" onfocus="calendar(this);"/>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Resign Type<span class="errorcss">*</span></td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:select name="mp1001.MP1001_RESIGN_TYPE" list="resignTypeList" theme="simple"/>
                                        </td>
                                    </tr>
                                    <tr id="resignRow2" style="display:none;">
                                        <td class="table_body table_body_NoWidth">Resign Reason<span class="errorcss">*</span></td>
                                        <td class="table_none table_none_NoWidth" colspan="3">
                                            <input name="mp1001.MP1001_RESIGN_REASON" value="${mp1001.MP1001_RESIGN_REASON}" type="text" class="text_input" style="width:100%;height:50px;" />
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Physical Address</td>
                                        <td class="table_none table_none_NoWidth" colspan="3">
                                            <input name="mp1001.MP1001_PHYSICAL_ADDRESS" value="${mp1001.MP1001_PHYSICAL_ADDRESS}" type="text" class="text_input" style="width:100%;height:30px;" />
                                        </td>
                                    </tr>
                                    
					                <tr>
					                    <td class="menubar_button" id="button_0" align="center" colspan="4">
					                        <s:submit name="Change department" value="Change department" align="left" method="changeDepartmentInit" theme="simple" style="display:none;"/>
					                        <s:submit name="save" value="Save" align="left" method="employeeEdit" theme="simple"/>
					                        <input type="button" onclick="window.location.href='employeeList.action'" name="cancel" value="Cancel" />
					                    </td>
					                </tr>
                                </table>
                            </DIV>
                            <!--教育信息-->
                            <DIV id='tabContent__1' style='display:none'>                             
                                <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                                    <tr>
                                        <td colspan="7" align="left">
                                            <input type="button" onclick="refresh('education')" name="refreshEdu" value="refresh" id="refreshEdu" style="display:none;"/>
                                            <input type="button" onclick="addEducationInfo()" name="educationAddBtn" value="Add Education Information" id="educationAddBtn" />
                                        </td>
                                    </tr>
                                </table>
                                <table id="universityList" width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                                    <tr class="table_title" align="center">
                                        <td scope="col" style="display:none;"></td>
                                        <td scope="col" style="display:none;"></td>
                                        <td scope="col">Type of Institution</td>
                                        <td scope="col">Name of Institution</td>
                                        <td scope="col">Level of Qualification</td>
                                        <td scope="col">Major</td>
                                        <td scope="col">Start Date</td>
                                        <td scope="col">Finish Date</td>
                                        <td scope="col" width="50px">-</td>
                                    </tr>
<s:iterator value="educationInfoList" status="st">
<s:if test="educationInfoList != null">
                                    <tr class="row" align="center" style="height:28px;">
                                        <td style="display:none;"><s:property value="MP1002_INSTITUTION_TYPE"></s:property></td>
                                        <td style="display:none;"><s:property value="MP1002_QUALIFICATION_LEV"></s:property></td>
                                        <td><s:property value="MP1002_INSTITUTION_TYPE_NAME"></s:property></td>
                                        <td><s:property value="MP1002_INSTITUTION_NAME"></s:property></td>
                                        <td><s:property value="MP1002_QUALIFICATION_LEV_NAME"></s:property></td>
                                        <td><s:property value="MP1002_MAJOR"></s:property></td>
                                        <td><s:property value="MP1002_START_DATETIME"></s:property></td>
                                        <td><s:property value="MP1002_FINISH_DATETIME"></s:property></td>
                                        <td width="100px">
                                            <input type="button" onclick="eduEdit(<s:property value="MP1002_SEQ"/>)" name="eduEditBtn" value="Edit" id="eduEditBtn" />
                                            <input type="button" onclick="eduDel(<s:property value="MP1002_SEQ"/>)" name="eduDelBtn" value="Del" id="eduDelBtn" />
                                        </td>
                                    </tr>
</s:if>
</s:iterator>
                                </table>
                            </DIV>
                            <!--工作经历-->
                            <DIV id='tabContent__2' style='display:none'>
                                <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                                    <tr>
                                        <td colspan="7" align="left">
                                            <input type="button" onclick="refresh('experience')" name="refreshExp" value="refresh" id="refreshExp" style="display:none;"/>
                                            <input type="button" onclick="addExperienceInfo()" name="experienceAddBtn" value="Add Experience Information" id="experienceAddBtn" />
                                        </td>
                                    </tr>
                                </table>
                                <table id="workList" width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                                    <tr class="table_title" align="center">
                                        <th scope="col">Company Name</th>
                                        <th scope="col">Department</th>
                                        <th scope="col">Position</th>
                                        <th scope="col">Date of Entry</th>
                                        <th scope="col">Date of Termination</th>
                                        <th scope="col">Job Description</th>
                                        <th scope="col">Reason of Termination</th>
                                        <th scope="col">Contact Person Information</th>
                                        <th scope="col" width="50px">-</th>
                                    </tr>
<s:iterator value="companyInfoList" status="st">
<s:if test="companyInfoList != null">
									<tr class="row" align="center" style="height:28px;">
							            <td><s:property value="MP1003_COMPANY_NAME"></s:property></td>
							            <td><s:property value="MP1003_DEPARTMENT_ID"></s:property></td>
							            <td><s:property value="MP1003_POSITION"></s:property></td>
							            <td><s:property value="MP1003_ENTRY_DATETIME"></s:property></td>
							            <td><s:property value="MP1003_TERMINATION_DATETIME"></s:property></td>
							            <td><s:property value="MP1003_JOB_DESC"></s:property></td>
							            <td><s:property value="MP1003_TERMINATION_REASON"></s:property></td>
							            <td><s:property value="MP1003_CONTACT_PERSON_INFO"></s:property></td>
							            <td width="100px">
							                <input type="button" onclick="expEdit(<s:property value="MP1003_SEQ"/>)" name="expEditBtn" value="Edit" id="expEditBtn" />
							                <input type="button" onclick="expDel(<s:property value="MP1003_SEQ"/>)" name="expDelBtn" value="Del" id="expDelBtn" />
							            </td>
									</tr>
</s:if>
</s:iterator>
                                </table>
                            </DIV>                       
                            <!--紧急联络人信息-->
                            <DIV id='tabContent__3' style='display:none'>
                                <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                                    <tr>
                                        <td colspan="7" align="left">
                                            <input type="button" onclick="refresh('contact')" name="refreshCon" value="refresh" id="refreshCon" style="display:none;"/>
                                            <input type="button" onclick="addContactPersonInfo()" name="contactAddBtn" value="Add Contact Information" id="contactAddBtn" />
                                        </td>
                                    </tr>
                                </table>
                                <table id="contactList" width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                                    <tr class="table_title" align="center">
                                        <th scope="col">Name</th>
                                        <th scope="col">Telephone</th>
                                        <th scope="col">Email Address</th>
                                        <th scope="col">Relationship</th>
                                        <th scope="col">Physical Address</th>
                                        <th scope="col" width="100px">-</th>
                                    </tr>
<s:iterator value="contactInfoList" status="st">
<s:if test="contactInfoList != null">
									<tr class="row" align="center" style="height:28px;">
							            <td><s:property value="MP1005_NAME"></s:property></td>
							            <td><s:property value="MP1005_TELEPHONE"></s:property></td>
							            <td><s:property value="MP1005_EMAIL"></s:property></td>
							            <td><s:property value="MP1005_RELATIONSHIP"></s:property></td>
							            <td><s:property value="MP1005_PHYSICAL_ADDRESS"></s:property></td>
							            <td width="100px">
							                <input type="button" onclick="conEdit(<s:property value="MP1005_SEQ"/>)" name="conEditBtn" value="Edit" id="conEditBtn" />
							                <input type="button" onclick="conDel(<s:property value="MP1005_SEQ"/>)" name="conDelBtn" value="Del" id="conDelBtn" />
							            </td>
									</tr>
</s:if>
</s:iterator>
                                </table>
                            </DIV>
                            <!--内容框End-->
                        </TD>
                        <TD width=1 background='images/menu/tab_bg.gif'><IMG height=1 src='images/menu/tab_bg.gif'  width=1></TD>
                    </TR>
                    </TBODY>
                    </TABLE>
                </TD>
                </TR>
                <TR>
                    <TD background='images/menu/tab_bg.gif' bgColor='#ffffff'><IMG height=1 src='images/menu/tab_bg.gif' width='1'></TD>
                </TR>
            </TBODY>
            </TABLE>
            <!--选项卡 End-->
            
        </td>
      </tr>
      
    <script type="text/javascript">
            function tabClick(idx,count) {
              for (i_tr = 0; i_tr < count; i_tr++) {
                if (i_tr == idx) {
                  var tabImgLeft = document.getElementById('tabImgLeft__' + idx);
                  var tabImgRight = document.getElementById('tabImgRight__' + idx);
                  var tabLabel = document.getElementById('tabLabel__' + idx);
                  var tabContent = document.getElementById('tabContent__' + idx);
     
                  tabImgLeft.src = 'images/menu/tab_active_left.gif';
                  tabImgRight.src = 'images/menu/tab_active_right.gif';
                  tabLabel.style.backgroundImage = "url(images/menu/tab_active_bg.gif)";
                  tabContent.style.visibility = 'visible';
                  tabContent.style.display = 'block';
                  continue;
                }
                var tabImgLeft = document.getElementById('tabImgLeft__' + i_tr);
                var tabImgRight = document.getElementById('tabImgRight__' + i_tr);
                var tabLabel = document.getElementById('tabLabel__' + i_tr);
                var tabContent = document.getElementById('tabContent__' + i_tr);
     
                if(tabImgLeft!=null){
                	tabImgLeft.src = 'images/menu/tab_unactive_left.gif';
                }
                if(tabImgRight!=null){
                	tabImgRight.src = 'images/menu/tab_unactive_right.gif';
                }
                if(tabLabel!=null){
                	tabLabel.style.backgroundImage = "url(/images/menu/tab_unactive_bg.gif)";
                }
                if(tabContent!=null){
                	tabContent.style.visibility = 'hidden';
                	tabContent.style.display = 'none';
                }
              }
              //document.getElementById('FrameWork_YOYO_LzppccSelectIndex').value=idx;
            }
            tabClick(0,5);
    </script>
        <tr><td height="5"></td></tr>
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