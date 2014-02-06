<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>员工信息浏览</title>

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
#pager ul.pages {display:block;border:none;text-transform:uppercase;font-size:10px;margin:1px 0 10px;padding:0;}
#pager ul.pages li {list-style:none;float:left;border:1px solid #65AB31;text-decoration:none;margin:0 5px 0 0;padding:5px;}
#pager ul.pages li:hover {border:1px solid #003f7e;}
#pager ul.pages li.pgEmpty {border:1px solid #000000;color:#000000;background-color:grey;}
#pager ul.pages li.pgCurrent {border:1px solid #003f7e;color:#000;font-weight:700;background-color:#65AB31;}
</style>

<script type="text/javascript" language="javascript">
var count = 15;
$(document).ready(function() {
    $("#pager").pager({ pagenumber: 1, pagecount: $("#pageCount").val(), buttonClickCallback: PageClick });
});

PageClick = function(pageclickednumber) {
    $("#pager").pager({ pagenumber: pageclickednumber, pagecount: $("#pageCount").val(), buttonClickCallback: PageClick });
    
    var employeeNum = $("#employeeNum").val();
    var fromDate = $("#fromDate").val();
    var toDate = $("#toDate").val();
    var departmentID = $("#departmentID").val();
    var attendenceStatus = $("#attendenceStatus").val();
    document.getElementById("currentPageNum").value = pageclickednumber;

    var param = {"pageNum":pageclickednumber, "pageCount":count, "employeeNum":employeeNum, "fromDate":fromDate, "toDate":toDate, "departmentID":departmentID, "attendenceStatus":attendenceStatus};
    $("#workRecordList").load("workRecordList.action",param);
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
}
</script>

<script type="text/javascript">
function refreshDataFun(){
    var pageNumber = document.getElementById("currentPageNum").value;
    var employeeNum = $("#employeeNum").val();
    var fromDate = $("#fromDate").val();
    var toDate = $("#toDate").val();
    var departmentID = $("#departmentID").val();
    var attendenceStatus = $("#attendenceStatus").val();
    var param = {"pageNum":pageNumber, "pageCount":count, "employeeNum":employeeNum, "fromDate":fromDate, "toDate":toDate, "departmentID":departmentID, "attendenceStatus":attendenceStatus};
    $("#workRecordList").load("workTimeConfirm.action",param);
}

function workTimeConfirm(_n,_t){
// 	var msg = "Are you sure you want to Confirm this record?\n\n"; 
// 	if (confirm(msg)==true){
// 		var pageNumber = document.getElementById("currentPageNum").value;
// 	    var employeeNum = $("#employeeNum").val();
// 	    var fromDate = $("#fromDate").val();
// 	    var toDate = $("#toDate").val();
// 	    var departmentID = $("#departmentID").val();
// 		var param = {"pageNum":pageNumber, "pageCount":count, "employeeNum":employeeNum, "fromDate":fromDate, "toDate":toDate, "confirmNum":_n, "confirmDate":_t, "departmentID":departmentID};
// 		$("#workRecordList").load("workTimeConfirm.action",param);
// 		return true;
// 	}else{
// 		return false;
// 	}
 	var pageNumber = document.getElementById("currentPageNum").value;
 	var employeeNum = $("#employeeNum").val();
 	var fromDate = $("#fromDate").val();
 	var toDate = $("#toDate").val();
 	var departmentID = $("#departmentID").val();
 	var attendenceStatus = $("#attendenceStatus").val();

    var param = "employeeNum=" + employeeNum + "&fromDate=" + fromDate + "&toDate=" + toDate + "&confirmNum=" + _n + "&confirmDate=" + _t + "&departmentID=" + departmentID + "&pageNum=" + pageNumber + "&attendenceStatus=" + attendenceStatus;;
	var popstyle="dialogTop:300px;dialogLeft:300px;help:no;center:yes;dialogHeight:400px;dialogWidth:600px;status:no;resizable:no;scroll:no";
	window.showModalDialog("workTimeConfirmInit.action?" + param,window,popstyle);
}
function fileDownload(){
    var employeeNum = $("#employeeNum").val();
    var fromDate = $("#fromDate").val();
    var toDate = $("#toDate").val();
    var departmentID = $("#departmentID").val();
    var attendenceStatus = $("#attendenceStatus").val();
    
    var param = "?employeeNum="+employeeNum + "&fromDate=" + fromDate + "&toDate=" + toDate + "&departmentID=" + departmentID + "&attendenceStatus=" + attendenceStatus;
	var options = {
		    url : "workingInfoPdf.action" + param,
			type : "post",
			dataType : "script",
			success : function(msg) {
				//window.location.href="login.action";employeeNum, fromDate, toDate, departmentID
				//var popstyle="dialogTop:300px;dialogLeft:300px;help:no;center:yes;dialogHeight:300px;dialogWidth:800px;status:no;resizable:no;scroll:no";
				//window.showModalDialog("employeeEducationInit.action?employeeNum=" + comNum, window, popstyle);
		    }
	};
	$("#form2").ajaxSubmit(options);
	//return false;
}
function excel(){
    var employeeNum = $("#employeeNum").val();
    var fromDate = $("#fromDate").val();
    var toDate = $("#toDate").val();
    var departmentID = $("#departmentID").val();
    var attendenceStatus = $("#attendenceStatus").val();
    
    var param = "?employeeNum="+employeeNum + "&fromDate=" + fromDate + "&toDate=" + toDate + "&departmentID=" + departmentID + "&attendenceStatus=" + attendenceStatus;
    var options = {
    		url : "workingInfoExcel.action" + param,
    		type : "post",
    		dataType : "html",
    		success : function(msg) {}
    };
    $("#form2").ajaxSubmit(options);
}
</script>

</head>

<body bgColor="#FFFFFF" topMargin="5" theme="simple">

<div>
<form id="form2" name="form2" method="post" action="workingRecordsListSearch.action" enctype="multipart/form-data">
    <input id="pageCount" name="pageCount" value="${pageCount}" type="hidden" />
    <input id="roleGroup" name="roleGroup" value="${roleGroup}" type="hidden" />
    <input id="currentPageNum" name="currentPageNum" value="1" type="hidden" />
    <input id="loginName" name="loginName" value="${loginName}" type="hidden" />
    <input id="depID" name="depID" value="${depID}" type="hidden" />
    
    <input id="optSearch" name="optSearch" value="${optSearch}" type="hidden" />
    <input id="optPdf" name="optPdf" value="${optPdf}" type="hidden" />
    <input id="optApproval" name="optApproval" value="${optApproval}" type="hidden" />
    <input id="optAll" name="optAll" value="${optAll}" type="hidden" />
    <input id="optDepartment" name="optDepartment" value="${optDepartment}" type="hidden" />
    <input id="optPersonal" name="optPersonal" value="${optPersonal}" type="hidden" />

    <div id="helpDiv" style="display:none;background-color:red;color:white;text-align:center;">Downloading......</div>

    <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <td>
            <!-- 头部菜单 Start -->
            <table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
              <tr>
                <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Attendance Management</td>
                <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
              </tr>
              <tr>
                <td height='27px' class='menubar_function_text'>Operation Function：Attendance Management</td>
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
<s:if test="optAll == 1 || optDepartment == 1" >
                    <td class="table_body table_body_NoWidth">Employee Num</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="employeeNum" name="employeeNum" value="${employeeNum}" type="text" class="text_input" />
                    </td>
</s:if>
<!-- joe add M0028, because she need to read Transport Department attendance record all department  -->
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
                    <td class="table_body table_body_NoWidth">Status</td>
                    <td class="table_none table_none_NoWidth">
                        <s:select id="attendenceStatus" name="attendenceStatus" value="attendenceStatus" list="attendenceStatusList" theme="simple"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" align="right">
                        <input type="submit" name="searchBtn" value="Search" id="searchBtn" class="" />
                        <input type="button" onclick="fileDownload()" name="downloadBtn" value="EXPORT TO PDF" id="downloadBtn" />
                        <input type="button" onclick="excel()" name="excelBtn" value="EXPORT TO EXCEL" id="excelBtn" />
                        <input id="refreshData" type="button" value="refresh" onclick="refreshDataFun()" style="display:none;" />
                    </td>
                </tr>
            </table>
            </fieldset>
            <!--检索区域End-->

            <!--内容框Start-->
            <div id="workRecordList" style="border: 0px solid #000000;">
            <table class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;width:1000px">
                <tr class="table_title" align="center">
                    <th scope="col" width="150px">Employee num</th>
                    <th scope="col" width="200px">Employee name</th>
                    <th scope="col" width="150px">Date</th>
                    <th scope="col" width="150px">In</th>
                    <th scope="col" width="150px">Location</th>
                    <th scope="col" width="150px">Out</th>
                    <th scope="col" width="150px">Location</th>
                    <th scope="col" width="100px">Status</th>
                    <th scope="col" width="100px"></th>
                </tr>
<s:iterator value="workingHourRecordList" status="st">
                <tr class="row" align="center" style="height:28px;">
                    <td><s:property value="MP2003_EMPLOYEE_NUM"></s:property></td>
                    <td><s:property value="MP2003_EMPLOYEE_NAME"></s:property></td>
                    <td><s:property value="MP2003_DATETIME"></s:property></td>
                    <td><s:property value="MP2003_START_TIME"></s:property></td>
                    <td><s:property value="MP2003_START_TIME_DOOR"></s:property></td>
                    <td><s:property value="MP2003_FINISH_TIME"></s:property></td>
                    <td><s:property value="MP2003_FINISH_TIME_DOOR"></s:property></td>
                    <td style="color:red;"><s:property value="MP2003_COMMENT"></s:property></td>
                    <td>
<s:if test="MP2003_STATUS==1 &&  optApproval==1 && MP2003_EMPLOYEE_NUM != loginName && MP2003_DEPARTMENT_ID == depID">
                        <input type="button" onclick="workTimeConfirm('<s:property value="MP2003_EMPLOYEE_NUM"/>','<s:property value="MP2003_DATETIME"/>')" id="confrimBtn" name="confrimBtn" value="Confirm" />
</s:if>
<s:if test="MP2003_STATUS==1 && roleGroup==3">
                        <input type="button" onclick="workTimeConfirm('<s:property value="MP2003_EMPLOYEE_NUM"/>','<s:property value="MP2003_DATETIME"/>')" id="confrimBtn" name="confrimBtn" value="Confirm" />
</s:if>
                    </td>
                </tr>
</s:iterator>
            </table>
            </div>
              
            <table class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; width:1000px" align="left">
                <tr class="" >
                    <td width="20%"></td>
                    <td align="center">
                        <div id="pager" style="border: 0px solid #000000;margin:auto;"></div>
                    </td>
                    <td width="20%"></td>
                </tr>
            </table>
            <!--内容框End-->
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