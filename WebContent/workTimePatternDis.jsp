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
<title>Worktime Pattern</title>
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
						<td height='27px' class='menubar_function_text'>Operation Function：Work Time Pattern</td>
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
                    <%-- <tr>
                        <td class="table_body">NO.</td>
                        <td class="table_none" style="color:red;font-weight:bold;">
                            <s:property value="mp2001.MP2001_NUM"/>&nbsp;&nbsp;<s:property value="mp2001.MP2001_APPROVAL_NAME"/>
                        </td>
                    </tr> --%>
                    <tr>
                        <td class="table_body">Worktime Pattern Name</td>
                        <td class="table_none">
                            <s:property value="workTimePattern.name"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_body">Pattern Description<span class="errorcss">*</span></td>
                        <td class="table_none">
                             <s:property value="workTimePattern.description"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_body">Circle Days<span class="errorcss">*</span></td>
                        <td class="table_none">
                             <s:property value="workTimePattern.circleDays"/>
                        </td>
                    </tr>
                    <tr>
                    	<td class="table_body">Each Days Work Time<span class="errorcss">*</span></td>
                    	<td class="table_none">
                    		<ul>
                    			<s:iterator value="workTimePattern.allCircleDays" status="st" var="eachDay">
	                    			<li>&nbsp;&nbsp;<span><s:property value="#eachDay.name"/></span></li>
	                    			<ol>
	                    				<s:iterator value="#eachDay.detailWorkTimeItems" var="dt">
	                    					<li>&nbsp;&nbsp;<span><s:property value="#dt.description"/></span>&nbsp;&nbsp;
	                    						<span>Start Time:</span><span><s:date name="#dt.fromTime" format="HH:mm:ss"/></span>&nbsp;&nbsp;
	                    						<span>End Time:</span><span><s:date name="#dt.toTime" format="HH:mm:ss"/></span>
	                    					</li>
	                    				</s:iterator>
	                    			</ol>
                    			</s:iterator>
                    		</ul>
                    	</td>
                    </tr>
                    <tr>
                        <td class="table_body">Calculate Holiday ?<span class="errorcss">*</span></td>
                        <td class="table_none">
                             <span><input type="radio" id="holidayFlag_0" name="calHolidayGrp" <s:if test="workTimePattern.calPubHolidayFlag == true">checked="true"</s:if>></input><label>Yes</label></span>
                             <span><input type="radio" id="holidayFlag_1" name="calHolidayGrp" <s:if test="workTimePattern.calPubHolidayFlag == false">checked="true"</s:if>></input><label>No</label></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_body">Calculate Special Day ?<span class="errorcss">*</span></td>
                        <td class="table_none">
                             <span><input type="radio" id="specialDayFlag_0" name="calSpecialDayGrp" <s:if test="workTimePattern.calSpecialDayFlag == true">checked="true"</s:if> ></input><label>Yes</label></span>
                             <span><input type="radio" id="specialDayFlag_1" name="calSpecialDayGrp" <s:if test="workTimePattern.calSpecialDayFlag == false">checked="true"</s:if> ></input><label>No</label></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_body">Ignore Holiday When Apply Leave<span class="errorcss">*</span></td>
                        <td class="table_none">
                             <span><input type="radio" id="ignoreHoliday_0" name="ignoreHolidayGrp" <s:if test="workTimePattern.applyLeaveIgnorePublicHolidayFlag == true">checked="true"</s:if>></input><label>Yes</label></span>
                             <span><input type="radio" id="ignoreHoliday_1" name="ignoreHolidayGrp" <s:if test="workTimePattern.applyLeaveIgnorePublicHolidayFlag == false">checked="true"</s:if>></input><label>No</label></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_body">initialAnnualLeaveDays<span class="errorcss">*</span></td>
                        <td class="table_none">
                             <s:property value="workTimePattern.initialAnnualLeaveDays"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_body">addAnnualHoursPM<span class="errorcss">*</span></td>
                        <td class="table_none">
                             <s:property value="workTimePattern.addAnnualHoursPM"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_body">initialSickLeaveDays<span class="errorcss">*</span></td>
                        <td class="table_none">
                             <s:property value="workTimePattern.initialSickLeaveDays"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_body">addSickHoursPM<span class="errorcss">*</span></td>
                        <td class="table_none">
                             <s:property value="workTimePattern.addSickHoursPM"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_body">dayWorkHours<span class="errorcss">*</span></td>
                        <td class="table_none">
                             <s:property value="workTimePattern.dayWorkHours"/>
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
<%-- <s:if test="type != 'view'" >
                <input type="button" onclick="window.location.href='applyLeaveListInit.action'" name="cancel" value="OK" />
</s:if>
 --%>			</td>
		</tr>
	</table>
</div>
</body>
<%-- <s:debug /> --%>
</html>
