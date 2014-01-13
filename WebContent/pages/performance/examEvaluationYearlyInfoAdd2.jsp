<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />

<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<link href="css/skins2/main.css" rel="stylesheet" type="text/css" />

<script src = "js/skins2/Main82.js" type="text/javascript"></script>

<style type="text/css">
.textInput2{border-size:0px; background-color:transparent;border-style:none;}
</style>

</head>
<body>
<form id="form2" name="form2" action="departmentPosotionSave" method="post">

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
	<tr>
		<td class='menubar_title' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img border='0'src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;年度绩效考核表单</td>
		<td class='menubar_readme_text' valign='bottom' style="background-color:transparent;border:0;border-bottom:2px black solid;"><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help?</td>
	</tr>
</table>
<!-- 头部菜单 End -->




<!-- 选项卡 Start -->
<table cellSpacing=0 cellPadding=0 width='100%' align=center border=0 style="margin-top:5px;">   
<tbody>
    <tr>     
        <TD style='PADDING-LEFT: 2px; HEIGHT: 22px' background='images/menu/tab_top_bg.gif'>
            <table cellSpacing=0 cellPadding=0 border=0>
                <tbody>
                    <tr>
                        <!--按钮　Start-->
                        <td >
                            <table height=22 cellSpacing=0 cellPadding=0 border=0>
                                <tbody>
                                       <tr>
                                         <td style="margin:0px;" width=3><img id=tabImgLeft__0 height=22 src='images/menu/tab_unactive_left.gif'  width=3></td>
                                         <td style="margin:0px;" class=tab id=tabLabel__0 onclick='javascript:tabClick(0,4)' background='images/menu/tab_unactive_bg.gif' UNSELECtable='on'>员工基本信息</td>
                                         <td style="margin:0px;" width=3><img id=tabImgRight__0 height=22 src='images/menu/tab_unactive_right.gif' width=3></td>
                                       </tr>
                                </tbody>
                            </table>
                        </td>
                        <td >
                            <table height=22 cellSpacing=0 cellPadding=0 border=0>
                                <tbody>
                                       <tr>
                                         <td width=3><img id=tabImgLeft__1 height=22 src='images/menu/tab_unactive_left.gif'  width=3></td>
                                         <td class=tab id=tabLabel__1 onclick='javascript:tabClick(1,4)' background='images/menu/tab_unactive_bg.gif' UNSELECtable='on'>考核历史记录(六个月)</td>
                                         <td width=3><img id=tabImgRight__1 height=22 src='images/menu/tab_unactive_right.gif' width=3></td>
                                       </tr>
                                </tbody>
                            </table>
                        </td>
                        <td >
                            <table height=22 cellSpacing=0 cellPadding=0 border=0>
                                <tbody>
                                       <tr>
                                         <td width=3><img id=tabImgLeft__2 height=22 src='images/menu/tab_unactive_left.gif'  width=3></td>
                                         <td class=tab id=tabLabel__2 onclick='javascript:tabClick(2,4)' background='images/menu/tab_unactive_bg.gif' UNSELECtable='on'>考核题目</td>
                                         <td width=3><img id=tabImgRight__2 height=22 src='images/menu/tab_unactive_right.gif' width=3></td>
                                       </tr>
                                </tbody>
                            </table>
                        </td>
                        <!--按钮 End-->
                    </tr>
                </tbody>
            </table>
        </td>
    </tr>
    <tr>
    <td bgColor=#ffffff>           
        <table cellSpacing=0 cellPadding=0 width='100%' border=0>
        <tbody>
        <tr>
            <td width=1 background='images/menu/tab_bg.gif'>
                <img  height=1 src='images/menu/tab_bg.gif'  width=1>
            </td>
            <td style='padding-right: 15px; padding-left: 15px; padding-bottom: 15px; padding-top: 15px; height: 100px' vAlign=top>
                <!--内容框Start-->
                <!--基本信息-->
                <DIV id='tabContent__0' style='display:none'>
<table cellpadding="2" cellspacing="1" border="0" width="100%" align="center" style="margin-top:5px;">
	<tr>
		<td	width="26%"	align="right" class=td1>Employee Number:</td>
		<td	width="74%"	class=td1><s:property value="mp7006.MP7006_EMPLOYEE_NAME"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Employee Name:</td>
		<td	width="74%"	class=td1><s:property value="mp7006.MP7006_EMPLOYEE_NUM"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Department:</td>
		<td	width="74%"	class=td1><s:property value="mp7006.MP7006_DEPARTMENT_NAME"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Designation:</td>
		<td	width="74%"	class=td1><s:property value="mp7006.MP7006_JOB_TITLE"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Current Appraisal Cycle:</td>
		<td	width="74%"	class=td1><s:property value="mp7006.MP7006_CURRENT_APPRAISAL_CYCLE"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Effective Date Last Appraisal:</td>
		<td	width="74%"	class=td1><s:property value="mp7006.MP7006_EFFECTIVE_DATE_LAST_APPRAISAL"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Name of Appraiser:</td>
		<td	width="74%"	class=td1><s:property value="mp7006.MP7006_APPRAISER"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Designation of Appraiser:</td>
		<td	width="74%"	class=td1><s:property value="mp7006.MP7006_APPRAISER_DESIGNATION"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Name of Reviewer:</td>
		<td	width="74%"	class=td1><s:property value="mp7006.MP7006_REVIEWER"/></td>
	</tr>
	<tr>
		<td	width="26%"	align="right" class=td1>Designation of Reviewer:</td>
		<td	width="74%"	class=td1><s:property value="mp7006.MP7006_REVIEWER_DESIGNATION"/></td>
	</tr>
</table>
                </DIV>
                <!--请购物品一览-->
                <DIV id='tabContent__1' style='display:none'>
test22222
                </DIV>
                <!--请购物品一览-->
                <DIV id='tabContent__2' style='display:none'>



			<table cellspacing="0" cellpadding="0" style="width:100%;">
<s:iterator id="list" value="evaluationYearlyScoresList" status="st">
			    <tr>
			        <td colspan="7" class="tdBg" style="border:solid black 1px;">${st.index + 1}. <s:property value="MP7007_EXAM_NAME"/><input name="evaluationYearlyScoresList[<s:property value="#st.index"/>].MP7007_SEQ" value="${list.MP7007_SEQ}" type="hidden"/></td>
			    </tr>
			    <tr>
			        <td rowspan="3" class="tdBg3"><s:property value="MP7007_EXAM_SUB_TITLE"/></td>
			        <td colspan="3" class="tdBg3"><s:property value="MP7007_EXAM_CONTENT"/></td>
			        <td class="tdBg3">Remarks</br>(Pls give reasons for rating 1 or 5)</td>
			        <td class="tdBg3">Weightage</br>(in %)</td>
			        <td class="tdBg4">Score</br>(Rating*Weightage)</td>
			    </tr>
			    <tr>
			        <td class="tdBg3">Self Assessment</td>
			        <td class="tdBg3">Reporting Manager</td>
			        <td class="tdBg3">Agreed Score</td>
			        <td rowspan="2" class="tdBg1"><textarea name="evaluationYearlyScoresList[${st.index}].MP7007_REMARKS" style="width:100%;height:60px;" ><s:property value="MP7007_REMARKS"/></textarea></td>
			        <td rowspan="2" class="tdBg1"><input id="weightage${st.index + 1}" name="evaluationYearlyScoresList[<s:property value="#st.index"/>].MP7007_WEIGHTAGE" value="${list.MP7007_WEIGHTAGE}" type="text" readonly="readonly" style="border:none;"/></td>
			        <td rowspan="2" class="tdBg2"><input id="final${st.index + 1}" name="evaluationYearlyScoresList[<s:property value="#st.index"/>].MP7007_FINAL_SCORE" value="${list.MP7007_FINAL_SCORE}" type="text"/></td>
			    </tr>
			    <tr>
			        <td class="tdBg1"><input name="evaluationYearlyScoresList[<s:property value="#st.index"/>].MP7007_SELF_SCORE" value="${list.MP7007_SELF_SCORE}" type="text"/></td>
			        <td class="tdBg1"><input name="evaluationYearlyScoresList[<s:property value="#st.index"/>].MP7007_MANAGER_SCORE" value="${list.MP7007_MANAGER_SCORE}" type="text"/></td>
			        <td class="tdBg1"><input id="agree${st.index + 1}" name="evaluationYearlyScoresList[<s:property value="#st.index"/>].MP7007_AGREED_SCORE" value="${list.MP7007_AGREED_SCORE}" type="text" onKeyup="sc('${st.index + 1}')"/></td>
			    </tr>
</s:iterator>
			    <tr>
			        <td colspan="5">&nbsp;</td>
			        <td align="left"><input id="totalWeightage" name="mp7006.MP7006_TOTAL_WEIGHTAGE" value="${mp7006.MP7006_TOTAL_WEIGHTAGE}" type="text" style="border:none;" readonly="readonly"/></td>
			        <td align="left"><input id="totalScore" name="mp7006.MP7006_COMPETENCE_TOTAL_SCORES" value="${mp7006.MP7006_COMPETENCE_TOTAL_SCORES}" type="text" style="border:none;" readonly="readonly"/></td>
			    </tr>
			</table>



                </DIV>
                <!--内容框End-->
            </td>
            <td width=1 background='images/menu/tab_bg.gif'><img height=1 src='images/menu/tab_bg.gif'  width=1></td>
        </tr>
        </tbody>
        </table>
    </td>
    </tr>
    <tr>
        <td background='images/menu/tab_bg.gif' bgColor='#ffffff'><img height=1 src='images/menu/tab_bg.gif' width='1'></td>
    </tr>
</tbody>
</table>
<!--选项卡 End-->

</form>
</body>
</html>
<script type="text/javascript">
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

    if(tabImgLeft!=null){
    	tabImgLeft.src = 'images/menu/tab_unactive_left.gif';
    }
    if(tabImgRight!=null){
    	tabImgRight.src = 'images/menu/tab_unactive_right.gif';
    }
    if(tabLabel!=null){
    	tabLabel.style.backgroundImage = "url(images/menu/tab_unactive_bg.gif)";
    }
    if(tabContent!=null){
    	tabContent.style.visibility = 'hidden';
    	tabContent.style.display = 'none';
    }
  }
  //document.getElementById('FrameWork_YOYO_LzppccSelectIndex').value=idx;
}
tabClick(0,5);
</script>