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

<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="css/wbox.css" rel="stylesheet" type="text/css"></link>

<script src="js/calendar.js" type="text/javascript" ></script>
<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery1.4.2.js" type="text/javascript" ></script>
<script src="js/jquery.pager.js" type="text/javascript" ></script>
<script src="js/wbox.js" type="text/javascript" ></script> 

<style type="text/css">
#pager ul.pages {
display:block;
border:none;
text-transform:uppercase;
font-size:10px;
margin:1px 0 10px;
padding:0;
}

#pager ul.pages li {
list-style:none;
float:left;
border:1px solid #65AB31;
text-decoration:none;
margin:0 5px 0 0;d
padding:5px;
}

#pager ul.pages li:hover {
border:1px solid #003f7e;
}

#pager ul.pages li.pgEmpty {
border:1px solid #000000;
color:#000000;
background-color:grey;
}

#pager ul.pages li.pgCurrent {
border:1px solid #003f7e;
color:#000;
font-weight:700;
background-color:#65AB31;
}
</style>

<script type="text/javascript" language="javascript">
window.document.onkeydown = keyStroke;
function keyStroke(){
	if (window.event.keyCode==13){
		window.event.keyCode=9;
	}
}
$(document).ready(function() {
    //alert(document.getElementById("pageNum").value);
    var _pageNum = $("#pageNum").val();
    if(_pageNum == 0){
    	_pageNum = 1;
    }
    $("#pager").pager({ pagenumber: _pageNum, pagecount: $("#pageCount").val(), buttonClickCallback: PageClick });
});

PageClick = function(pageclickednumber) {
    $("#pager").pager({ pagenumber: pageclickednumber, pagecount: $("#pageCount").val(), buttonClickCallback: PageClick });
    
    var employeeNum = $("#employeeNum").val();
    var employeeName = $("#employeeName").val();
    var departmentID = $("#departmentID").val();
    var departmentName = $("#departmentName").val();
    var visaType = $("#visaType").val();
    var empStatus = $("#empStatus").val();
    
    var gender = $("#gender").val();
    var nationality = $("#nationality").val();
    var religion = $("#religion").val();
    var race = $("#race").val();
    var passportNum = $("#passportNum").val();
    var qualification = $("#qualification").val();
    var startingDate = $("#startingDate").val();
    var birth = $("#birth").val();
    var birthday = $("#birthday").val();
    //var page_count = $("#pageCount").val();

    //var param = {"pageNum" : pageclickednumber,"pageCount" : page_count,"employeeNum" : employeeNum,"employeeName" : employeeName,"departmentID" : departmentID,"visaType" : visaType};
    var param = {"pageNum" : pageclickednumber,"employeeNum" : employeeNum,"employeeName" : employeeName,"departmentID" : departmentID,"visaType" : visaType,"departmentName" : departmentName,"empStatus" : empStatus,"gender" : gender,"nationality" : nationality,"religion" : religion,"race" : race,"passportNum" : passportNum,"qualification" : qualification,"startingDate" : startingDate,"birth" : birth,"birthday" : birthday};
    document.getElementById("pageNum").value = pageclickednumber;
    $("#employeeDetailList").load("employeeDetailList.action",param);
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
function showAdvanceSearch(){
	var query1 = document.getElementById("query1").style.display;

	if(query1 == ""){
		document.getElementById("query1").style.display = "none";
		document.getElementById("query2").style.display = "none";
		document.getElementById("query3").style.display = "none";
		document.getElementById("query4").style.display = "none";
	}else{
		document.getElementById("query1").style.display = "";
		document.getElementById("query2").style.display = "";
		document.getElementById("query3").style.display = "";
		document.getElementById("query4").style.display = "";
	}
}
</script>

</head>

<body bgColor="#FFFFFF" topMargin="5" theme="simple">    
    <div>
<form method="post" action="employeeSearch.action">
    <input id="pageCount" name="pageCount" value="${pageCount}" type="hidden" />
    <input id="pageNum" name="pageNum" value="${pageNum}" type="hidden" />
    <input id="group" name="group" value="${group}" type="hidden" />
    <input id="loginPosition" name="loginPosition" value="${loginPosition}" type="hidden" />
    <input id="loginDepartId" name="loginDepartId" value="${loginDepartId}" type="hidden" />
    
	<input id="optEdit" name="optEdit" value="${optEdit}" type="hidden" />
	<input id="optView" name="optView" value="${optView}" type="hidden" />
	<input id="optSearch" name="optSearch" value="${optSearch}" type="hidden" />
	<input id="optAll" name="optAll" value="${optAll}" type="hidden" />
	<input id="optAdvanceSearch" name="optAdvanceSearch" value="${optAdvanceSearch}" type="hidden" />
    
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <td>
            <!-- 头部菜单 Start -->
            <table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
              <tr>
                <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Employee Management</td>
                <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
              </tr>
              <tr>
                <td height='27px' class='menubar_function_text'>Operation Function：Employee Management</td>
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
                    <td class="table_body table_body_NoWidth">Employee Number</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="employeeNum" name="employeeNum" value="${employeeNum}" type="text" class="text_input" />
                    </td>
                    <td class="table_body table_body_NoWidth">Employee Name</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="employeeName" name="employeeName" value="${employeeName}" type="text" class="text_input" />
                    </td>
                </tr>
                <tr>
                    <td class="table_body table_body_NoWidth">Visa Type</td>
                    <td class="table_none table_none_NoWidth">
                        <s:select id="visaType" name="visaType" list="visaTypeList" theme="simple"/>
                    </td>
                    <td class="table_body table_body_NoWidth">Status</td>
                    <td class="table_none table_none_NoWidth">
                        <s:select id="empStatus" name="empStatus" list="statusList" theme="simple"/>
                    </td>
                </tr>
           
                <tr id="query1" style="display:none;">
                    <td class="table_body table_body_NoWidth">Gender</td>
                    <td class="table_none table_none_NoWidth">
                        <s:select id="gender" name="gender" list="genderList" theme="simple"/>
                    </td>
                    <td class="table_body table_body_NoWidth">Nationality</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="nationality" name="nationality" value="${nationality}" type="text" class="text_input" />
                    </td>
                </tr>
                <tr id="query2" style="display:none;">
                    <td class="table_body table_body_NoWidth">Religion</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="religion" name="religion" value="${religion}" type="text" class="text_input" />
                    </td>
                    <td class="table_body table_body_NoWidth">Race</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="race" name="race" value="${race}" type="text" class="text_input" />
                    </td>
                </tr>
                <tr id="query3" style="display:none;">
                    <td class="table_body table_body_NoWidth">ID/Passport Number</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="passportNum" name="passportNum" value="${passportNum}" type="text" class="text_input" />
                    </td>
                    <td class="table_body table_body_NoWidth">Top Level of Qualification</td>
                    <td class="table_none table_none_NoWidth">
                        <s:select id="qualification" name="qualification" list="topDegreeList" theme="simple"/>
                    </td>
                </tr>
                <tr id="query4" style="display:none;">
                    <td class="table_body table_body_NoWidth">Starting Date</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="startingDate" name="startingDate" value="${startingDate}" type="text" class="text_input" onfocus="calendar(this);"/>
                    </td>
                    <td class="table_body table_body_NoWidth">Date of Birth</td>
                    <td class="table_none table_none_NoWidth">
                        <s:select id="birth" name="birth" list="birthdayList" theme="simple"/>
                        <input id="birthday" name="birthday" value="${birthday}" type="text" class="text_input" />
                    </td>
                </tr>
                <tr align="left">
<s:if test="optAll == 1">
                    <td class="table_body table_body_NoWidth">Department</td>
                    <td class="table_none table_none_NoWidth">

                        <input id="departmentID" name="departmentID" value="${departmentID}" type="hidden" />
                        <input id="departmentName" name="departmentName" value="${departmentName}" type="text" class="text_input" readonly="readonly" style="border:solid 0px;background-color:transparent"/>
                        <input type="button" value="select" id="button3" name="buttonselect" onClick="wBox.showBox()" class="cbutton">
                        <input type="button" value="clear" onClick="javascript:ClearSelect();" class="cbutton" />
                    </td>
</s:if>
<s:else>
                    <td colspan="2"></td>
</s:else>
                    <td colspan="2" align="right">
<s:if test="optSearch == 1" >
                        <input type="submit" name="searchBtn" value="Search" id="searchBtn" />
</s:if>
<s:if test="optSearch == 0" >
                        <input type="submit" name="searchBtn" value="Search" id="searchBtn" disabled="disabled" />
</s:if>
<s:if test="optAdvanceSearch == 1 && optSearch == 1" >
                        <input id="advanceSearch" type="button" style="border:solid 0 #000000; text-decoration:underline; " value="Advance Search" onclick="showAdvanceSearch()">
</s:if>
                    </td>
                </tr>
            </table>
            </fieldset>
            <!--检索区域End-->
            
            <!--内容框Start-->
            <div id="employeeDetailList" style="border: 0px solid #000000;">
                <table class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
                    <tr class="table_title" align="center">
                        <th scope="col">Employee Number</th>
                        <th scope="col">Employee Name</th>
                        <th scope="col">Visa Type</th>
                        <th scope="col">Department</th>
                        <th scope="col">Mobile Phone</th>
                        <th scope="col">Telephone</th>
                        <th scope="col">Email</th>
<s:if test="optEdit == 1 && optView == 1">
                        <th scope="col" colspan="2">&nbsp;</th>
</s:if>
<s:elseif test="optEdit == 1 || optView == 1">
                        <th scope="col">&nbsp;</th>
</s:elseif>
                    </tr>
<s:iterator value="employeeInfo" status="st">
                    <tr class="row" align="center" style="height:28px;">
                        <td><s:property value="MP1001_EMPLOYEE_NUM"></s:property></td>
                        <td><s:property value="MP1001_PREFERED_NAME"></s:property> <s:property value="MP1001_SURNAME"></s:property></td>
                        <td><s:property value="MP1001_VISA_TYPE"></s:property></td>
                        <td><s:property value="MP1001_DEPARTMENT_NAME"></s:property></td>
                        <td><s:property value="MP1001_MOBILE_PHONE"></s:property></td>
                        <td><s:property value="MP1001_TELEPHONE"></s:property></td>
                        <td><s:property value="MP1001_COMPANY_EMAIL"></s:property></td>
<s:if test="optEdit == 1">
                        <td width="35px"><a href="employeeEditInit.action?type=Edit&employeeNum=<s:property value="MP1001_EMPLOYEE_NUM"/>&pageNum=<s:property value="CURRENT_PAGE_NUM"/>" >Edit</a></td>
</s:if>
<s:if test="optView == 1">
                        <td width="35px"><a href="employeeDisplay.action?employeeNum=<s:property value="MP1001_EMPLOYEE_NUM"/>" >Browse</a></td>
</s:if>
                    </tr>
</s:iterator>
                </table>
            </div>
            
            <table cellspacing="1" border="0" style="background-color:White;border-width:0px;" align="center">
                <tr>
                    <td align="center">
                        <div id="pager" style="border: 1px solid #FFFFFF;margin:0 auto;"></div>
                    </td>
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
