<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新建员工档案</title>
<meta http-equiv="Expires" CONTENT="0"> 
<meta http-equiv="Cache-Control" CONTENT="no-cache"> 
<meta http-equiv="Pragma" CONTENT="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="css/wbox.css" rel="stylesheet" type="text/css"></link>

<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery1.4.2.js" type="text/javascript" ></script>
<script src="js/jquery.pager.js" type="text/javascript" ></script>
<script src="js/wbox.js" type="text/javascript" ></script> 

<style type="text/css">
.h
{
display:none;
}
</style>

<script type="text/javascript">
</script>

</head>

<body bgColor="#FFFFFF" topMargin="5" theme="simple">    
<div>
<form action="employeeAdd.action" method="post">
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
                                            <s:property value="mp1001.MP1001_EMPLOYEE_NUM"/>
                                        </td>
                                        <td class="table_none table_none_NoWidth" colspan="2" rowspan="3">
                                            <img src="<s:property value="mp1001.MP1001_PICTURE_NAME"/>"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">ID/Passport Number</td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:property value="mp1001.MP1001_EMPLOYEE_ID"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">ID/Passport Surname</td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:property value="mp1001.MP1001_SURNAME"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">ID/Passport Firstname</td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:property value="mp1001.MP1001_FIRSTNAME"/>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Prefered Name</td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:property value="mp1001.MP1001_PREFERED_NAME"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Employee Type</td>
                                        <td class="table_none table_none_NoWidth">
				                            <s:property value="mp1001.MP1001_VISA_TYPE"/>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Date of Birth</td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:property value="mp1001.MP1001_BIRTHDAY"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Gender</td>
                                        <td class="table_none table_none_NoWidth">
				                            <s:property value="mp1001.MP1001_GENDER_NAME"/>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Religion</td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:property value="mp1001.MP1001_RELIGION"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Department</td>
                                        <td class="table_none table_none_NoWidth">
					                        <s:property value="mp1001.MP1001_DEPARTMENT_NAME"/>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Position</td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:property value="mp1001.MP1001_POSITION_NAME"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Nationality</td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:property value="mp1001.MP1001_NATIONALITY"/>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Race</td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:property value="mp1001.MP1001_RACE"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Marriage Status</td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:property value="mp1001.MP1001_MARRIAGE_STATUS_NAME"/>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Date of Entry</td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:property value="mp1001.MP1001_ENTRY_DATE"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Email Address</td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:property value="mp1001.MP1001_EMAIL"/>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Mobile Phone Number</td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:property value="mp1001.MP1001_MOBILE_PHONE"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Telephone Number</td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:property value="mp1001.MP1001_TELEPHONE"/>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Other Contact Number</td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:property value="mp1001.MP1001_OTHER_CONTACT"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Physical Address</td>
                                        <td class="table_none table_none_NoWidth" colspan="3">
                                            <s:property value="mp1001.MP1001_PHYSICAL_ADDRESS"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4" align="right"></td>
                                    </tr>
                                </table>
                            </DIV>
                            <!--教育信息-->
                            <DIV id='tabContent__1' style='display:none'>
					            <!--学校信息一览-->
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
                                    </tr>
<s:iterator value="educationInfoList" status="st">
<s:if test="educationInfoList != null">
                                    <tr class="row" align="center" style="height:28px;">
                                        <td><s:property value="MP1002_INSTITUTION_TYPE_NAME"></s:property></td>
                                        <td><s:property value="MP1002_INSTITUTION_NAME"></s:property></td>
                                        <td><s:property value="MP1002_QUALIFICATION_LEV_NAME"></s:property></td>
                                        <td><s:property value="MP1002_MAJOR"></s:property></td>
                                        <td><s:property value="MP1002_START_DATETIME"></s:property></td>
                                        <td><s:property value="MP1002_FINISH_DATETIME"></s:property></td>
                                    </tr>
</s:if>
</s:iterator>
                                </table>
                            </DIV>
                            <!--工作经历-->
                            <DIV id='tabContent__2' style='display:none'>
					            <!--工作经历一览-->
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
                                    </tr>
</s:if>
</s:iterator>
                                </table>
                            </DIV>                       
                            <!--紧急联络人信息-->
                            <DIV id='tabContent__3' style='display:none'>
                                <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Name</td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:property value="mp1005.MP1005_NAME"/>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Telephone</td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:property value="mp1005.MP1005_TELEPHONE"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Email Address</td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:property value="mp1005.MP1005_EMAIL"/>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Relationship</td>
                                        <td class="table_none table_none_NoWidth">
                                            <s:property value="mp1005.MP1005_RELATIONSHIP"/>
                                     </tr>
                                     <tr>
                                        <td class="table_body table_body_NoWidth">Physical Address</td>
                                        <td class="table_none table_none_NoWidth" COLSPAN="3">
                                            <s:property value="mp1005.MP1005_PHYSICAL_ADDRESS"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4" align="right"></td>
                                    </tr>
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
            
            <table width="100%" border="1" cellspacing="1" cellpadding="3" align="center">
                <tr>
                    <td align="center">
                        <input type="button" onclick="windows.history.go(-1)" name="cancel" value="Cancel" />
                    </td>
                </tr>
            </table>
        </td>
      </tr>
        <tr><td height="5"></td></tr>
    </table>
</form>
    </div>

</body>
</html>
