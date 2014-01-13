<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Expires" CONTENT="0"> 
<meta http-equiv="Cache-Control" CONTENT="no-cache"> 
<meta http-equiv="Pragma" CONTENT="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>员工信息浏览</title>
<link rel="stylesheet" href="css/Site_Css.css" type="text/css" />
<link rel="shortcut icon" href="images/other/icon.ico" type="image/x-icon" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/wbox.css"></link>

<script type="text/javascript" src="js/calendar.js" ></script>
<script type="text/javascript" src="js/checkform.js" charset="utf-8"></script>
<script type="text/javascript" src="js/jquery1.4.2.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery.pager.js"></script>
<script type="text/javascript" src="js/wbox.js"></script>

<style type="text/css">
#pager ul.pages {display:block;border:none;text-transform:uppercase;font-size:10px;margin:1px 0 10px;padding:0;}
#pager ul.pages li {list-style:none;float:left;border:1px solid #65AB31;text-decoration:none;margin:0 5px 0 0;padding:5px;}
#pager ul.pages li:hover {border:1px solid #003f7e;}
#pager ul.pages li.pgEmpty {border:1px solid #000000;color:#000000;background-color:grey;}
#pager ul.pages li.pgCurrent {border:1px solid #003f7e;color:#000;font-weight:700;background-color:#65AB31;}
</style>

<script type="text/javascript">
var count = 15;
$(document).ready(function() {
    $("#pager").pager({ pagenumber: 1, pagecount: $("#pageCount").val(), buttonClickCallback: PageClick });
});

PageClick = function(pageclickednumber) {
    $("#pager").pager({ pagenumber: pageclickednumber, pagecount: $("#pageCount").val(), buttonClickCallback: PageClick });
    
    var MP2001_NUM = $("#MP2001_NUM").val();
    var employeeNum = $("#employeeNum").val();
    var leaveType = $("#leaveType").val();
    var approvalType = $("#approvalType").val();
    var fromDate = $("#fromDate").val();
    var toDate = $("#toDate").val();
    var depID = "";
    var jobTitle = "";
    
    if(document.getElementById("depID") != null){
    	depID = document.getElementById("depID").value;
    }
    if(document.getElementById("jobTitle") != null){
    	jobTitle = document.getElementById("jobTitle").value;
    }
    
    var param = {"pageNum" : pageclickednumber,"pageCount" : count,"MP2001_NUM" : MP2001_NUM,"employeeNum" : employeeNum,"leaveType" : leaveType,"approvalType" : approvalType,"fromDate" : fromDate,"toDate" : toDate,"depID" : depID,"jobTitle" : jobTitle};
    document.getElementById("pageNum").value = pageclickednumber;
    $("#leaveDetailList").load("leaveDetailList.action",param);
}

</script>

<script type="text/javascript">
function showApplyDetailInfo(leaveNum){
	var popstyle="dialogTop:50px;dialogLeft:300px;help:no;center:yes;dialogHeight:600px;dialogWidth:500px;status:no;resizable:no;scroll:no;loacation:no;toolbar:no;";
	window.showModalDialog("applyLeaveDis.action?type=view&MP2001_NUM=" + leaveNum,window,popstyle);
}
//请假单删除操作
function leaveRecordDel(id){    
	var msg = "Are you sure you want to delete this record?\n\n"; 
	if (confirm(msg)==true){
		var _fromDate = document.getElementById("fromDate").value;
		var _toDate = document.getElementById("toDate").value;
		var _approvalType = document.getElementById("approvalType").value;
		var _leaveType = document.getElementById("leaveType").value;
		var _MP2001_NUM = id;
		var _employeeNum = document.getElementById("employeeNum").value;
	    var _depID = "";
	    var _jobTitle = "";
		var _pageNum = document.getElementById("pageNum").value;

	    if(document.getElementById("depID") != null){
	    	_depID = document.getElementById("depID").value;
	    }
	    if(document.getElementById("jobTitle") != null){
	    	_jobTitle = document.getElementById("jobTitle").value;
	    }
		
		var param = {"pageNum" : _pageNum,"mp3003Seq" : id,"type":"del","employeeNum":_employeeNum,"MP2001_NUM":_MP2001_NUM,"leaveType":_leaveType,"fromDate":_fromDate,"toDate":_toDate,"approvalType":_approvalType,"depID":_depID,"jobTitle":_jobTitle};
		$("#leaveDetailList").load("deleteLeaveInfo.action",param);
        return true; 
	}else{ 
	    return false; 
	}
}
function leaveRecordApproval(id){
	var popstyle="dialogTop:100px;dialogLeft:200px;help:no;center:yes;dialogHeight:700px;dialogWidth:700px;status:no;resizable:no;scroll:no";
	window.showModalDialog("applyLeaveInit.action?type=approval&MP2001_NUM=" + id, window, popstyle);
}
//局部刷新
function refresh(_type){
	var _fromDate = document.getElementById("fromDate").value;
	var _toDate = document.getElementById("toDate").value;
	var _approvalType = document.getElementById("approvalType").value;
	var _leaveType = document.getElementById("leaveType").value;
	var _MP2001_NUM = "";
	var _employeeNum = document.getElementById("employeeNum").value;
    var _depID = "";
    var _jobTitle = "";
	var _pageNum = document.getElementById("pageNum").value;

    if(document.getElementById("depID") != null){
    	_depID = document.getElementById("depID").value;
    }
    if(document.getElementById("jobTitle") != null){
    	_jobTitle = document.getElementById("jobTitle").value;
    }
	
	var param = {"pageNum" : _pageNum,"employeeNum":_employeeNum,"MP2001_NUM":_MP2001_NUM,"leaveType":_leaveType,"fromDate":_fromDate,"toDate":_toDate,"approvalType":_approvalType,"depID":_depID,"jobTitle":_jobTitle};
	if(_type == "leaveRecord"){
		$("#leaveDetailList").load("refreshLeaveInfo.action",param);
	}
}
</script>

<script type="text/javascript">
window.document.onkeydown = keyStroke;
function keyStroke(){
	if (window.event.keyCode==13){
		window.event.keyCode=9;
	}
}
function ClearSelect(){
	document.getElementById("depID").value = "";
	document.getElementById("depName").value = "";
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

	document.getElementById("depID").value = departmentID;
	document.getElementById("depName").value = departmentName;
}
</script>

<script type="text/javascript">
function uploadFile(id){
	var popstyle="dialogTop:50px;dialogLeft:300px;help:no;center:yes;dialogHeight:800px;dialogWidth:1000px;status:yes;resizable:yes;scroll:yes;loacation:yes;toolbar:yes;";
	window.showModalDialog("DocumentInfoInit.action?mp2005Num=" + id,window,popstyle);
}
function createExcelDoc(){
    var param1 ="";
    var param2 ="";
    var param3 ="";
    var param4 ="";
    var param5 ="";
    var param6 ="";
    var param7 ="";
    var param8 ="";

    if(document.getElementById("employeeNum") != null){
    	param1 = $("#employeeNum").val();
    }
    if(document.getElementById("MP2001_NUM") != null){
    	param2 = $("#MP2001_NUM").val();
    }
    if(document.getElementById("leaveType") != null){
    	param3 = $("#leaveType").val();
    }
    if(document.getElementById("fromDate") != null){
    	param4 = $("#fromDate").val();
    }
    if(document.getElementById("toDate") != null){
    	param5 = $("#toDate").val();
    }
    if(document.getElementById("approvalType") != null){
    	param6 = $("#approvalType").val();
    }
    if(document.getElementById("depID") != null){
    	param7 = $("#depID").val();
    }
    if(document.getElementById("jobTitle") != null){
    	param8 = $("#jobTitle").val();
    }
    if(param4 == "" || param5 == ""){
    	alert("From Date & To Date can not be empty");
    }else{
        var options = {
        		url : "leaveInfoExcel.action?param1=" + param1 + "&param2=" + param2 + "&param3=" + param3 + "&param4=" + param4 + "&param5=" + param5 + "&param6=" + param6 + "&param7=" + param7 + "&param8=" + param8,
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

<body bgColor="#FFFFFF" topMargin="5" theme="simple">    
<div>
<form id="from1" method="post" action="applyLeaveListSearch.action" enctype="multipart/form-data">
    <input id="departmentID" name="departmentID" value="${departmentID}" type="hidden" />
    <input id="roleGroup" name="roleGroup" value="${roleGroup}" type="hidden" />
    <input id="pageCount" name="pageCount" value="${pageCount}" type="hidden" />
    <input id="pageNum" name="pageNum" value="${pageNum}" type="hidden" />
    <input id="positionLev" name="positionLev" value="${positionLev}" type="hidden" />
    <input id="loginName" name="loginName" value="${loginName}" type="hidden" />
    
    <input id="optSearch" name="optSearch" value="${optSearch}" type="hidden" />
    <input id="optDel" name="optDel" value="${optDel}" type="hidden" />
    <input id="optDel2" name="optDel2" value="${optDel2}" type="hidden" />
    <input id="optEdit" name="optEdit" value="${optEdit}" type="hidden" />
    <input id="optView" name="optView" value="${optView}" type="hidden" />
    <input id="optApproval" name="optApproval" value="${optApproval}" type="hidden" />
    <input id="optAll" name="optAll" value="${optAll}" type="hidden" />
    <input id="optDepartment" name="optDepartment" value="${optDepartment}" type="hidden" />
    <input id="optPersonal" name="optPersonal" value="${optPersonal}" type="hidden" />
<s:if test="optPersonal == 1 && optDepartment == 0 && optAll == 0" >
    <input id="employeeNum" name="employeeNum" value="${employeeNum}" type="hidden" />
</s:if>

    <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <td>
            <!-- 头部菜单 Start -->
            <table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
              <tr>
                <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Leave Management</td>
                <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
              </tr>
              <tr>
                <td height='27px' class='menubar_function_text'>Operation Function：Leave Management</td>
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
            <fieldset style="border-color:#999999;border-width:1px;border-style:Solid;margin-bottom:10px;">
            <legend style="color:#333333;font-size:1.5em;font-weight:bold;">Query Condition</legend>
            <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                <tr>
                    <td class="table_body table_body_NoWidth">From Date</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="fromDate" name="fromDate" value="${fromDate}" type="text" class="text_input" onfocus="calendar(this);"/>
                    </td>
                    <td class="table_body table_body_NoWidth">To Date</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="toDate" name="toDate" value="${toDate}" type="text" class="text_input" onfocus="calendar(this);"/>
                    </td>
                </tr>
                <tr>
                    <td class="table_body table_body_NoWidth">Approval</td>
                    <td class="table_none table_none_NoWidth">
                        <s:select id="approvalType" name="approvalType" list="approvalTypeList" onchange="" theme="simple"/>
                    </td>
                    <td class="table_body table_body_NoWidth">Type of leave</td>
                    <td class="table_none table_none_NoWidth">
                        <s:select id="leaveType" name="leaveType" list="leaveTypeList" onchange="" theme="simple"/>
                    </td>
                </tr>
                <tr>
                    <td class="table_body table_body_NoWidth">Leave No.</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="MP2001_NUM" name="MP2001_NUM" value="${MP2001_NUM}" type="text" class="text_input" />
                    </td>
<s:if test="optDepartment == 1 || optAll == 1" >
                    <td class="table_body table_body_NoWidth">Employee Num</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="employeeNum" name="employeeNum" value="${employeeNum}" type="text" class="text_input" />
                    </td>
</s:if>
<s:else>
                    <td class="table_none table_none_NoWidth"></td>
                    <td class="table_none table_none_NoWidth"></td>
</s:else>
                </tr>
<s:if test="optAll == 1" >
                <tr>
                    <td class="table_body table_body_NoWidth">Department</td>
                    <td class="table_none table_none_NoWidth">

                        <input id="depID" name="depID" value="${depID}" type="hidden" />
                        <input id="depName" name="depName" value="${depName}" type="text" class="text_input" readonly="readonly" style="border:solid 0px;background-color:transparent"/>
                        <input type="button" value="select" id="button3" name="buttonselect" onClick="wBox.showBox()" class="cbutton">
                        <input type="button" value="clear" onClick="javascript:ClearSelect();" class="cbutton" />
                    </td>
                    
                    <td class="table_body table_body_NoWidth">Job Title</td>
                    <td class="table_none table_none_NoWidth">
                        <s:select id="jobTitle" name="jobTitle" list="jobTitleList" onchange="" theme="simple"/>
                    </td>
                </tr>
</s:if>
                <tr>
                    <td colspan="4" align="right">
<s:if test="optSearch == 1" >
                        <input type="submit" name="searchBtn" value="Search" id="searchBtn" class="" />
</s:if>
<s:if test="optSearch == 0" >
                        <input type="submit" name="searchBtn" value="Search" id="searchBtn" disabled="disabled" class="" />
</s:if>
                        <input type="button" onclick="refresh('leaveRecord')" name="leaveRecord" value="refresh" id="leaveRecord" style="display:none;"/>
                        <input type="button" onclick="createExcelDoc()" value="Export To Excel" />
                    </td>
                </tr>
            </table>
            </fieldset>
            <!--检索区域End-->

            <div id="leaveDetailList" style="border: 0px solid #000000;">
                <table class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
                    <tr class="table_title" align="center">
                        <th scope="col">Leave No.</th>
                        <th scope="col">employee num</th>
                        <th scope="col">employee name</th>
                        <th scope="col">days(H)</th>
                        <th scope="col">from date</th>
                        <th scope="col">to date</th>
                        <th scope="col">acting person</th>
                        <th scope="col">type of leave</th>
                        <th scope="col">approval</th>
                        <th scope="col">&nbsp;</th>
                    </tr>
<s:iterator value="leaveInfo" status="st">
                    <tr class="row" align="center" style="height:28px;">
<s:if test="optView == 1" >
                        <td><a style="text-decoration:underline;cursor:hand;" onclick="showApplyDetailInfo('<s:property value="MP2001_NUM"/>')"><s:property value="MP2001_NUM"></s:property></a></td>
</s:if>
<s:if test="optView == 0" >
                        <td><s:property value="MP2001_NUM"/></td>
</s:if>
                        <td><s:property value="MP2001_EMPLOYEE_NUM"/></td>
                        <td><s:property value="MP2001_EMPLOYEE_NAME"/></td>
                        <td><s:property value="MP2001_DAYS"/></td>
                        <td><s:property value="MP2001_FROM_DATETIME"/></td>
                        <td><s:property value="MP2001_TO_DATETIME"/></td>
                        <td><s:property value="MP2001_ACTING_PERSON"/>(<s:property value="MP2001_ACTING_PERSON_NAME"/>)</td>
                        <td><s:property value="MP2001_LEAVE_TYPE_NAME"/></td>
<s:if test="MP2001_APPROVAL == 1" >
                        <td style="color:blue;"><s:property value="MP2001_APPROVAL_NAME"></s:property></td>
</s:if>
<s:elseif test="MP2001_APPROVAL == 2" >
                        <td style="color:green;"><s:property value="MP2001_APPROVAL_NAME"></s:property></td>
</s:elseif>
<s:elseif test="MP2001_APPROVAL == 3" >
                        <td style="color:red;"><s:property value="MP2001_APPROVAL_NAME"></s:property></td>
</s:elseif>
                        <td width="140px" align="left">
<s:if test="MP2001_APPROVAL == 1 && (MP2001_EMPLOYEE_NUM == loginName || MP2001_ACTING_APPLICATION_PERSON == loginName)" >
    <input type="button" onclick="leaveRecordDel('<s:property value="MP2001_NUM"/>')" name="leaveRecordDelBtn" value="Del" id="leaveRecordDelBtn" />
   <!-- <a href="applyLeaveInit.action?type=edit&MP2001_NUM=<s:property value="MP2001_NUM"/>" >Edit</a> -->
</s:if>

<s:if test="optApproval == 1 && MP2001_APPROVAL == 1 && MP2001_EMPLOYEE_NUM != loginName && MP2001_DEPARTMENT_ID == departmentID || positionLev == 92 || positionLev == 93">
    <input type="button" onclick="leaveRecordApproval('<s:property value="MP2001_NUM"/>')" name="leaveRecordApprovalBtn" value="Approval" id="leaveRecordApprovalBtn" />
</s:if>
<s:if test="MP2001_APPROVAL == 3 && optDel2 == 1" >
    <input type="button" onclick="leaveRecordDel('<s:property value="MP2001_NUM"/>')" name="leaveRecordDelBtn" value="Del" id="leaveRecordDelBtn" />
</s:if>
<s:if test="(MP2001_EMPLOYEE_NUM == loginName || MP2001_ACTING_APPLICATION_PERSON == loginName) && MP2001_LEAVE_TYPE == 2" >
    <input type="button" onclick="uploadFile('<s:property value="MP2001_NUM"/>')" name="uploadBtn" value="Upload File" id="uploadBtn" />
</s:if>
                        </td>
                    </tr>
</s:iterator>
                </table>
            </div>
              
            <table cellspacing="1" border="0" style="background-color:White;border-width:0px;" align="center">
                <tr class="">
                    <td>
                        <div id="pager" style="border: 1px solid #FFFFFF;"></div>
                    </td>
                </tr>
            </table>
          </td>
        </tr>
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