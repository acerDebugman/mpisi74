<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>员工信息浏览</title>
<base target="_self"/>
<meta http-equiv="Expires" CONTENT="0"> 
<meta http-equiv="Cache-Control" CONTENT="no-cache"> 
<meta http-equiv="Pragma" CONTENT="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/Site_Css.css" type="text/css" />
<link rel="shortcut icon" href="images/other/icon.ico" type="image/x-icon" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/checkform.js" charset="utf-8"></script>
<script type="text/javascript" src="js/jquery1.4.2.js"></script>

<script type="text/javascript">
var closeWinFunc = window.close;
window.close = function() {
    window.open("", "_self");
    closeWinFunc();
}
function approveTmpEmp(id){
	var msg = "Are you sure you want to Approval this record?\n\n";
	if (confirm(msg)==true){
		var param = {"employeeNum" : id, "type" : "approval"};
		$("#tmpEmpInfoList").load("tmpEmployeeApproval.action",param);
	    return true; 
	}else{ 
	    return false; 
	}
}
</script>

</head>

<body bgColor="#FFFFFF" topMargin="5" theme="simple">    
<div>
<form id="form2" method="post">
    <input id="departmentID" name="departmentID" value="${departmentID}" type="hidden" />
    <input id="roleGroup" name="roleGroup" value="${roleGroup}" type="hidden" />
    <input id="employeeNum" name="employeeNum" value="${employeeNum}" type="hidden" />
    <input id="optApproval" name="optApproval" value="${optApproval}" type="hidden" />
    
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

            <!-- 选项卡 Start -->
            <table cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
			    <tr class="table_title" align="center">
			        <th scope="col" width="150px">Employee Number</th>
			        <th scope="col" width="200px">Employee Name</th>
			        <th scope="col" width="100px">Visa Type</th>
			        <th scope="col" width="250px">Department</th>
			        <th scope="col" width="100px">Entry Date</th>
<s:if test="optApproval == 1" >
			        <th scope="col" width="100px">&nbsp;</th>
</s:if>
			    </tr>
            </table>
            
            <div id="tmpEmpInfoList" style="overflow:auto;border:solid 0px red;height:600px;width:940px">
            <table cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
<s:iterator value="tmpEmployeeDataList" status="st">
                <tr class="row" align="center" style="height:28px;">
                    <td width="150px"><s:property value="MP1001_EMPLOYEE_NUM"></s:property></td>
                    <td width="200px"><s:property value="MP1001_PREFERED_NAME"></s:property> <s:property value="MP1001_SURNAME"></s:property></td>
                    <td width="100px"><s:property value="MP1001_VISA_TYPE"></s:property></td>
                    <td width="250px"><s:property value="MP1001_DEPARTMENT_NAME"></s:property></td>
                    <td width="100px"><s:property value="MP1001_ENTRY_DATE"></s:property></td>
<s:if test="optApproval == 1" >
                    <td align="center" width="100px">
                        <input type="button" onclick="approveTmpEmp('<s:property value="MP1001_EMPLOYEE_NUM"/>')" name="tmpEmployeeBtn" value="Approve" id="tmpEmployeeBtn" />
                    </td>
</s:if>
                </tr>
</s:iterator>
            </table>
            </div>
            <!--选项卡 End-->
            </td>
        </tr>
        <tr><td height="5"></td></tr>
    </table>

</form>
</div>

</body>
</html>