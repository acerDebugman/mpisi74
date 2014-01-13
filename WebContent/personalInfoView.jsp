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
<link rel="shortcut icon" href="images/other/icon.ico" type="image/x-icon" />

<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />

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
<form action="" method="post">
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
            
           
             <!--基本信息-->
             <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                 <tr class="table_title">
                     <td colspan="4" align="center">Personal Information</td>
                 </tr>
                 <tr>
                     <td class="table_body table_body_NoWidth">Employee Number</td>
                     <td class="table_none table_none_NoWidth">
                         <s:property value="mp1001.MP1001_EMPLOYEE_NUM"/>
                     </td>
                     <td class="table_none table_none_NoWidth" colspan="2" rowspan="3">
                         <img width="80px" height="100px" src="<s:property value="mp1001.MP1001_PICTURE_NAME"/>"/>
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
                     <td class="table_body table_body_NoWidth">Job Title</td>
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
                     <td class="table_body table_body_NoWidth">Starting Date</td>
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
                     <td class="table_body table_body_NoWidth">Company Email Address</td>
                     <td class="table_none table_none_NoWidth">
                         <s:property value="mp1001.MP1001_COMPANY_EMAIL"/>
                     </td>
                     <td class="table_body table_body_NoWidth">Group</td>
                     <td class="table_none table_none_NoWidth">
                         <s:property value="mp1001.MP1001_GROUP_NAME"/>
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
                 <tr>
                     <td class="table_body table_body_NoWidth">Physical Address</td>
                     <td class="table_none table_none_NoWidth">
                         <s:property value="mp1001.MP1001_PHYSICAL_ADDRESS"/>
                     </td>
                     <td></td>
                     <td></td>
                 </tr>
             </table>
             
             <!--紧急联络人信息-->
             <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                 <tr class="table_title">
                     <td colspan="4" align="center">Emergency Contact Information</td>
                 </tr>
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
             </table>

<!--学校信息一览-->
             <table id="universityList" width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                 <tr class="table_title">
                     <td colspan="8" align="center">Education Information</td>
                 </tr>
                 <tr class="" style="background-color:#CFDBEC;display:none;" align="center">
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
                 <tr class="row" align="center" style="height:28px;background-color:#FFFFFF;">
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

<!--工作经历一览-->
             <table id="workList" width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                 <tr class="table_title">
                     <td colspan="8" align="center">Previous Working Experience</td>
                 </tr>
                 <tr class="" style="background-color:#CFDBEC;display:none;" align="center">
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
                 <tr class="row" align="center" style="height:28px;background-color:#FFFFFF;">
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
            

            
            <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
                <tr>
                    <td align="center">
                        <input type="button" onclick="javascript:window.history.go(-1);" style="display:block;" name="cancel" value="Cancel" />
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
