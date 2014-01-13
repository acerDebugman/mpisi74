<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新建员工档案</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="css/wbox.css" rel="stylesheet" type="text/css"></link>

<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery1.4.2.js" type="text/javascript" ></script>
<script src="js/jquery.pager.js" type="text/javascript" ></script>
<script src="js/wbox.js" type="text/javascript" ></script> 

<script type="text/javascript">
function addRow1()
{
	var newRow = universityList.insertRow(1);
	var rowIndex = newRow.rowIndex;

	newCell=newRow.insertCell(0);
	
	newCell=newRow.insertCell(0);	
}
</script>

</head>

<body bgColor="#FFFFFF" topMargin="5" >    
<div>
<s:form action="employeeAdd" method="post">
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
                      <td class="menubar_button" id="button_0">
                        <img border="0" align="texttop" src="images/icon/Edit.gif">&nbsp;Save
                      </td>
                      <td class="menubar_button" id="button_0">
                        <img border="0" align="texttop" src="images/icon/Edit.gif">&nbsp;Cancel
                      </td>
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
                                                     <TD class=tab id=tabLabel__0 onclick='javascript:tabClick(0,5)' background='images/menu/tab_unactive_bg.gif' UNSELECTABLE='on'>Personal Information</TD>
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
                                                     <TD class=tab id=tabLabel__1 onclick='javascript:tabClick(1,5)' background='images/menu/tab_unactive_bg.gif' UNSELECTABLE='on'>Education Information</TD>
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
                                                     <TD class=tab id=tabLabel__2 onclick='javascript:tabClick(2,5)' background='images/menu/tab_unactive_bg.gif' UNSELECTABLE='on'>Previous Working Experience</TD>
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
                                                     <TD class=tab id=tabLabel__3 onclick='javascript:tabClick(3,5)' background='images/menu/tab_unactive_bg.gif' UNSELECTABLE='on'>Department Information</TD>
                                                     <TD width=3><IMG id=tabImgRight__3 height=22 src='images/menu/tab_unactive_right.gif' width=3></TD>
                                                   </TR>
                                            </TBODY>
                                        </TABLE>
                                    </TD>
                                    <TD >
                                        <TABLE height=22 cellSpacing=0 cellPadding=0 border=0>
                                            <TBODY>
                                                   <TR>
                                                     <TD width=3><IMG id=tabImgLeft__4 height=22 src='images/menu/tab_unactive_left.gif'  width=3></TD>
                                                     <TD class=tab id=tabLabel__4 onclick='javascript:tabClick(4,5)' background='images/menu/tab_unactive_bg.gif' UNSELECTABLE='on'>Emergency Contact Information</TD>
                                                     <TD width=3><IMG id=tabImgRight__4 height=22 src='images/menu/tab_unactive_right.gif' width=3></TD>
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
                                            <input name="mp1001.MP1001_EMPLOYEE_NUM" type="text" class="text_input" />
                                        </td>
                                        <td class="table_none table_none_NoWidth" colspan="2" rowspan="3">
                                            <img border='0' src='' align='absmiddle' style=" height:100%; width:50px;">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">ID/Passport Number</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_EMPLOYEE_NUM" type="text" class="text_input" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">ID/Passport Surname</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_EMPLOYEE_NUM" type="text" class="text_input" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">ID/Passport Firstname</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_EMPLOYEE_NUM" type="text" class="text_input" />
                                        </td>
                                        <td class="table_body table_body_NoWidth">Prefered Name</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_EMPLOYEE_NUM" type="text" class="text_input" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Employee Type</td>
                                        <td class="table_none table_none_NoWidth">
                                            <select name="select" id="select" style="width:155px;">
                                                <option value="1">Civics</option>
                                                <option value="2">Permanent Resident</option>
                                                <option value="3">Work Visa</option>
                                                <option value="4">Study Visa</option>
                                                <option value="5">伴随签证</option>
                                                <option value="6">Tourist/Business Visa</option>
                                                <option value="8">Other</option>
                                            </select>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Department</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="ctl00$PageBody$U_GroupID" type="hidden" id="ctl00_PageBody_U_GroupID" />
                                            <input name="ctl00$PageBody$U_GroupID_Txt" type="text" id="ctl00_PageBody_U_GroupID_Txt" class="text_input" readonly="" />
                                            <input type="button" value="Select Department" id="button3" name="buttonselect" onClick="javascript:ShowDepartID()" class="cbutton">
                                            <input type="button" value="Clear" onClick="javascript:ClearSelect();" class="cbutton" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Gender</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_EMPLOYEE_NUM" type="text" class="text_input" />
                                        </td>
                                        <td class="table_body table_body_NoWidth">Religion</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_EMPLOYEE_NUM" type="text" class="text_input" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Nationality</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_EMPLOYEE_NUM" type="text" class="text_input" />
                                        </td>
                                        <td class="table_body table_body_NoWidth">Date of Birth</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_EMPLOYEE_NUM" type="text" class="text_input" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Marriage Status</td>
                                        <td class="table_none table_none_NoWidth">
                                            <select name="select" id="select" style="width:155px;">
                                                <option value="1">Single</option>
                                                <option value="2">Married</option>
                                            </select>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Race</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_EMPLOYEE_NUM" type="text" class="text_input" onfocus="javascript:HS_setDate(this);"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Mobile Phone Number</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_EMPLOYEE_NUM" type="text" class="text_input" />
                                        </td>
                                        <td class="table_body table_body_NoWidth">Date of Entry</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_EMPLOYEE_NUM" type="text" class="text_input" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Email Address</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_EMPLOYEE_NUM" type="text" class="text_input" />
                                        </td>
                                        <td class="table_body table_body_NoWidth">Telephone Number</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_EMPLOYEE_NUM" type="text" class="text_input" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Period of Employment(Month)</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_EMPLOYEE_NUM" type="text" class="text_input" />
                                        </td>
                                        <td class="table_body table_body_NoWidth">Other Contact Number</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input name="mp1001.MP1001_EMPLOYEE_NUM" type="text" class="text_input" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Employment Status</td>
                                        <td class="table_none table_none_NoWidth">
                                            <select name="select" id="select" style="width:155px;">
                                                <option value="1">Employed</option>
                                                <option value="2">Unemloyed</option>
                                            </select>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Date of Unemployment</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input type="text" class="text_input" onfocus="javascript:HS_setDate(this);"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Physical Address</td>
                                        <td class="table_none table_none_NoWidth" colspan="3">
                                            <input name="mp1001.MP1001_EMPLOYEE_NUM" type="text" class="text_input" style="width:100%; height:50px;" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4" align="right"></td>
                                    </tr>
                                </table>
                            </DIV>
                            <!--教育信息-->
                            <DIV id='tabContent__1' style='display:none'>
                                <table id="universityList" width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                                    <tr>
                                        <td colspan="6" align="right">
                                            <input name="" type="button" value="添加学校信息" onclick="addRow1()"/>
                                        </td>
                                    </tr>
                                    <tr class="table_title" align="center">
                                        <th scope="col">Type of Institution</th>
                                        <th scope="col">Name of Institution</th>
                                        <th scope="col">Level of Qualification</th>
                                        <th scope="col">Major</th>
                                        <th scope="col">Start Date</th>
                                        <th scope="col">Finish Date</th>
                                    </tr>
                                    <tr class="row" align="center" style="height:28px;">
                                        <td>
                                            <select name="select" id="select" style="width:100%;">
                                                <option value="1">University</option>
                                                <option value="2">College</option>
                                                <option value="3">High School</option>
                                                <option value="4">Others</option>
                                            </select>
                                        </td>
                                        <td>
                                            <input type="text" class="text_input" style="width:100%;"/>
                                        </td>
                                        <td>
                                            <select name="select" id="select" style="width:100%;">
                                                <option value="1">PhD.</option>
                                                <option value="2">Master Degree</option>
                                                <option value="3">Honours Degree</option>
                                                <option value="4">Bachelor Degree</option>
                                                <option value="5">Diploma</option>
                                                <option value="6">Matric</option>
                                                <option value="7">Grade 11</option>
                                                <option value="8">Grade 10</option>
                                                <option value="9">Grade 9</option>
                                                <option value="10">Others</option>
                                            </select>
                                        </td>
                                        <td>
                                            <input type="text" class="text_input" style="width:100%;"/>
                                        </td>
                                        <td>
                                            <input type="text" class="text_input" onfocus="javascript:HS_setDate(this);"/>
                                        </td>
                                        <td>
                                            <input type="text" class="text_input" onfocus="javascript:HS_setDate(this);"/>
                                        </td>
                                    </tr>
                                </table>
                            </DIV>
                            <!--工作经历-->
                            <DIV id='tabContent__2' style='display:none'>
                                <table id="workList" width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                                    <tr>
                                        <td colspan="6" align="right">
                                            <input name="" type="button" value="Add..." onclick="addRow1()"/>
                                        </td>
                                    </tr>
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
                                    <tr class="row" align="center" style="height:28px;">
                                        <td>
                                            <input type="text" class="text_input" style="width:100%;"/>
                                        </td>
                                        <td>
                                            <input type="text" class="text_input" style="width:100%;"/>
                                        </td>
                                        <td>
                                            <input type="text" class="text_input" style="width:100%;"/>
                                        </td>
                                        <td>
                                            <input type="text" class="text_input" onfocus="javascript:HS_setDate(this);"/>
                                        </td>
                                        <td>
                                            <input type="text" class="text_input" onfocus="javascript:HS_setDate(this);"/>
                                        </td>
                                        <td>
                                            <input type="text" class="text_input" style="width:100%;"/>
                                        </td>
                                        <td>
                                            <input type="text" class="text_input" style="width:100%;"/>
                                        </td>
                                        <td>
                                            <input type="text" class="text_input" style="width:100%;"/>
                                        </td>
                                    </tr>
                                </table>
                            </DIV>
                            <!--部门信息-->
                            <DIV id='tabContent__3' style='display:none'>
                                <table id="departmentTb" width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                                    <tr>
                                        <td colspan="6" align="right">
                                            <input name="" type="button" value="Add..." onclick="addRow1()"/>
                                        </td>
                                    </tr>
                                    <tr class="table_title" align="center">
                                        <th scope="col">Department Code</th>
                                        <th scope="col">Department Name</th>
                                        <th scope="col">Position</th>
                                        <th scope="col">Transfer Date</th>
                                        <th scope="col">Job Description</th>
                                    </tr>
                                    <tr class="row" align="center" style="height:28px;">
                                        <td>
                                            <input type="text" class="text_input" style="width:100%;"/>
                                        </td>
                                        <td>
                                            <input type="text" class="text_input" style="width:100%;"/>
                                        </td>
                                        <td>
                                            <input type="text" class="text_input" style="width:100%;"/>
                                        </td>
                                        <td>
                                            <input type="text" class="text_input" onfocus="javascript:HS_setDate(this);"/>
                                        </td>
                                        <td>
                                            <input type="text" class="text_input" style="width:100%;"/>
                                        </td>
                                    </tr>
                                </table>
                            </DIV>                           
                            <!--紧急联络人信息-->
                            <DIV id='tabContent__4' style='display:none'>
                                <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Name</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input type="text" class="text_input" style="width:100%;"/>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Telephone</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input type="text" class="text_input" style="width:100%;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="table_body table_body_NoWidth">Email Address</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input type="text" class="text_input" style="width:100%;"/>
                                        </td>
                                        <td class="table_body table_body_NoWidth">Relationship</td>
                                        <td class="table_none table_none_NoWidth">
                                            <input type="text" class="text_input" style="width:100%;"/>
                                     </tr>
                                     <tr>
                                        <td class="table_body table_body_NoWidth">Physical Address</td>
                                        <td class="table_none table_none_NoWidth" COLSPAN="2">
                                            <input type="text" class="text_input" style="width:100%;height:60px;"/>
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
            
            <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                <tr>
                    <td align="right"></td>
                </tr>
            </table>
        </td>
      </tr>
      
    <script language='javascript' type="text/javascript">
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
     
                tabImgLeft.src = 'images/menu/tab_unactive_left.gif';
                tabImgRight.src = 'images/menu/tab_unactive_right.gif';
                tabLabel.style.backgroundImage = "url(/images/menu/tab_unactive_bg.gif)";
                tabContent.style.visibility = 'hidden';
                tabContent.style.display = 'none';
              }
              //document.getElementById('FrameWork_YOYO_LzppccSelectIndex').value=idx;
            }
            tabClick(0,5);
    </script>
        </form>
        <tr><td height="5"></td></tr>
    </table>
</s:form>
    </div>
</body>
</html>