<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
//设置缓存为空
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Apply Leave</title>
<meta http-equiv="Pragma" content="no-cache">    
<meta http-equiv="Cache-Control" content="no-cache">    
<meta http-equiv="Expires" content="0">  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />

</head>
<body>
<input id="type" name="type" value="${type}" type="hidden" />
<div>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<!-- 头部菜单 Start -->
				<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
					<tr>
						<td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3' />&nbsp;ApplyLeave Management</td>
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

                <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" style="padding-bottom:50px;">
                    <tr>
                        <td class="table_body">NO.</td>
                        <td class="table_none" style="color:red;font-weight:bold;">
                            <s:property value="mp2001.MP2001_NUM"/>&nbsp;&nbsp;<s:property value="mp2001.MP2001_APPROVAL_NAME"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_body">Employee Number</td>
                        <td class="table_none">
                            <s:property value="mp2001.MP2001_EMPLOYEE_NUM"/> <s:property value="mp2001.MP2001_EMPLOYEE_NAME"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_body">Acting Person<span class="errorcss">*</span></td>
                        <td class="table_none">
                             <s:property value="mp2001.MP2001_ACTING_PERSON"/> <s:property value="mp2001.MP2001_ACTING_PERSON_NAME"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_body">Type of leave<span class="errorcss">*</span></td>
                        <td class="table_none">
                             <s:property value="mp2001.MP2001_LEAVE_TYPE_NAME"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_body">From Date<span class="errorcss">*</span></td>
                        <td class="table_none">
                             <s:property value="mp2001.MP2001_FROM_DATETIME"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_body">To Date<span class="errorcss">*</span></td>
                        <td class="table_none">
                             <s:property value="mp2001.MP2001_TO_DATETIME"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_body">No. of days<span class="errorcss">*</span></td>
                        <td class="table_none">
                             <s:property value="mp2001.MP2001_DAYS"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="background-color:#9AADCD;">Attached Document</td>
                        <td colspan="5">
<s:iterator value="attachedDocumentList" status="st">
             <a href="documentDownload.action?fileName=<s:property value='MP1009_PATH'/>" target="download"><s:property value="MP1009_DOCUMENT_NAME"/></a> 
</s:iterator>
                        </td>
                    </tr>
                    
                    <tr style="display:none;">
                        <td class="table_body">Create User<span class="errorcss">*</span></td>
                        <td class="table_none">
                             <s:property value="mp2001.MP2001_CREATE_USER"/> <s:property value="mp2001.MP2001_CREATE_USER_NAME"/>
                        </td>
                    </tr>
                    <tr style="display:none;">
                        <td class="table_body">Create Time<span class="errorcss">*</span></td>
                        <td class="table_none">
                             <s:property value="mp2001.MP2001_CREATE_DATETIME"/>
                        </td>
                    </tr>
                    
                    <tr style="">
                        <td class="table_body" style="border-top:2px solid red;">Approval User<span class="errorcss">*</span></td>
                        <td class="table_none" style="border-top:2px solid red;">
                             <s:property value="mp2001.MP2001_APPROVAL_USER"/> <s:property value="mp2001.MP2001_APPROVAL_USER_NAME"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_body">Approval Time<span class="errorcss">*</span></td>
                        <td class="table_none">
                             <s:property value="mp2001.MP2001_APPROVAL_DATETIME"/>
                        </td>
                    </tr>
                    
                    <tr style="">
                        <td class="table_body" style="border-top:2px solid red;">Create User<span class="errorcss">*</span></td>
                        <td class="table_none" style="border-top:2px solid red;">
                             <s:property value="mp2001.MP2001_CREATE_USER"/> <s:property value="mp2001.MP2001_CREATE_USER_NAME"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_body">Create Time<span class="errorcss">*</span></td>
                        <td class="table_none">
                             <s:property value="mp2001.MP2001_CREATE_DATETIME"/>
                        </td>
                    </tr>                    
                    
                    <tr id="managerApproval">
                        <td class="table_body">Comment<span class="errorcss">*</span></td>
                        <td class="table_none">
                            <s:property value="mp2001.MP2001_COMMENT"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right"></td>
                    </tr>
                </table>
			</td>
		</tr>

		<tr>
			<td class="menubar_button" id="button_0" align="center">
<s:if test="type != 'view'" >
                <input type="button" onclick="window.location.href='applyLeaveListInit.action'" name="cancel" value="OK" />
</s:if>
			</td>
		</tr>
	</table>
</div>
</body>
</html>