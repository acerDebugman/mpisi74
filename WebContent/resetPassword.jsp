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

<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery1.4.2.js" type="text/javascript" ></script>

<script type="text/javascript">
window.document.onkeydown = keyStroke;
function keyStroke(){
	if (window.event.keyCode==13){
		window.event.keyCode=9;
	}
}
//删除联系人信息
function rsp(seq){
	$.ajax({
		url:"resetPassword.action?employeeNum="+seq,
		type:"post",
		dataType:"html",
		//data: params,
		error:function(ex){
			alert("failed:" + ex.responseText);
		},
		success:function(data){
			document.getElementById("messageId").innerText = data;
		}
	}); 
}
function reset(){
	document.getElementById("employeeNum").value = "";
	document.getElementById("employeeName").value = "";
}
</script>

</head>

<body bgColor="#FFFFFF" topMargin="5" theme="simple">    
    <div>
<form id="form2" name="form2" method="post" action="employeePasswordSearch.action">
    <input id="group" name="group" value="${group}" type="hidden" />
    <input id="optSearch" name="optSearch" value="${optSearch}" type="hidden" />
    <input id="optReset" name="optReset" value="${optReset}" type="hidden" />
    
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <td>
            <!-- 头部菜单 Start -->
            <table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
              <tr>
                <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Password Management</td>
                <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
              </tr>
              <tr>
                <td height='27px' class='menubar_function_text'>Operation Function：Reset Employee's Password</td>
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
                    <td class="table_body table_body_NoWidth">employee number</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="employeeNum" name="employeeNum" value="${employeeNum}" type="text" class="text_input" />
                        <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
                            <s:param>employeeNum</s:param>
                        </s:fielderror>
                    </td>
                    <td class="table_body table_body_NoWidth">employee name</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="employeeName" name="employeeName" value="${employeeName}" type="text" class="text_input" />
                        <s:fielderror theme="simple" cssStyle="color:red;margin-left:10px;">
                            <s:param>employeeName</s:param>
                        </s:fielderror>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="right">
<s:if test="optSearch==1">
                        <input type="submit" name="searchBtn" value="Search" id="searchBtn" class="" />
</s:if>
<s:else>
                        <input type="submit" name="searchBtn" value="Search" id="searchBtn" class="" disabled="disabled" />
</s:else>

                    </td>
                </tr>
            </table>
            </fieldset>
            <!--检索区域End-->
            
            <!--内容框Start-->
            <div id="employeeDetailList" style="border: 0px solid #000000;">
                <table class="table-box" cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;width:720px">
                    <tr class="table_title" align="center">
                        <th scope="col" width="200px">employee number</th>
                        <th scope="col" width="200px">employee name</th>
                        <th scope="col" width="200px">department</th>
                        <th scope="col" width="120px"></th>
                    </tr>
<s:iterator value="employeeInfo" status="st">
                    <tr class="row" align="center" style="height:28px;">
                        <td><s:property value="MP1001_EMPLOYEE_NUM"></s:property></td>
                        <td><s:property value="MP1001_PREFERED_NAME"></s:property> <s:property value="MP1001_SURNAME"></s:property></td>
                        <td><s:property value="MP1001_DEPARTMENT_NAME"></s:property></td>
                        <td>
<s:if test="optReset==1">
                            <input type="button" onclick="rsp('<s:property value="MP1001_EMPLOYEE_NUM"/>')" name="resetBtn" value="reset Password" id="resetBtn" />
</s:if>
<s:else>
                            <input type="button" onclick="rsp('<s:property value="MP1001_EMPLOYEE_NUM"/>')" name="resetBtn" value="reset Password" id="resetBtn" disabled="disabled" />
</s:else>
                        </td>
                    </tr>
</s:iterator>
                </table>
            </div>
            
            <div id="messageId" style="text-align:center;width:720px;border:solid 1px FFFFFF;color:red;"></div>
            
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
</body>
</html>
