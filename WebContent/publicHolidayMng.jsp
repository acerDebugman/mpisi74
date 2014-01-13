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
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />

<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery1.4.2.js" type="text/javascript"></script>

<style type="text/css">
.textInput2{border-size:0px; background-color:transparent;border-style:none;}
</style>

<script type="text/javascript">
function reloadData1(){
	var param = {};
	$("#mp0010InfoListDiv").load("mp0010InfoListRefresh.action",param);
}
function reloadData2(){
	var param = {};
	 $("#holidayInfoListDiv").load("holidayInfoListRefresh.action",param);
}
function openAddHoliday1(){
	var popstyle="dialogTop:300px;dialogLeft:300px;help:no;center:yes;dialogHeight:300px;dialogWidth:400px;status:yes;resizable:no;scroll:yes";
	window.showModalDialog("mp0010InfoAddInit.action", window, popstyle);
}
function openAddHoliday2(){
	var popstyle="dialogTop:300px;dialogLeft:300px;help:no;center:yes;dialogHeight:300px;dialogWidth:400px;status:yes;resizable:no;scroll:yes";
	window.showModalDialog("holidayInfoAddInit.action", window, popstyle);
}
function Del1(id){
	var msg = "Are you sure you want to delete this record?\n\n"; 
	if (confirm(msg)==true){
		 var param = {"mp0010Seq" : id};
		 $("#mp0010InfoListDiv").load("mp0010InfoListDelete.action",param);
		 
	    return true; 
	}else{ 
	    return false; 
	}
}
function Del2(id){
	var msg = "Are you sure you want to delete this record?\n\n"; 
	if (confirm(msg)==true){
		var param = {"holidaySeq" : id};
		 $("#holidayInfoListDiv").load("holidayInfoListDelete.action",param);
		 
	    return true; 
	}else{ 
	    return false; 
	}
}
</script>

</head>
<body>
<form id="form2" name="form2" action="departmentPosotionSave" method="post">
<input id="mp0002Seq" name="mp0002Seq" value="${mp0002Seq}" type="hidden" />
<input id="optEdit" name="optEdit" value="${optEdit}" type="hidden" />
<input id="optAdd" name="optAdd" value="${optAdd}" type="hidden" />
<input id="optDel" name="optDel" value="${optDel}" type="hidden" />

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
                                         <TD class=tab id=tabLabel__0 onclick='javascript:tabClick(0,4)' background='images/menu/tab_unactive_bg.gif' UNSELECTABLE='on'>Technical Dates Management</TD>
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
                                         <TD class=tab id=tabLabel__1 onclick='javascript:tabClick(1,4)' background='images/menu/tab_unactive_bg.gif' UNSELECTABLE='on'>Public Holiday Management</TD>
                                         <TD width=3><IMG id=tabImgRight__1 height=22 src='images/menu/tab_unactive_right.gif' width=3></TD>
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
					<s:if test="optAdd == 1">
					<table cellspacing="1" border="0" width="1020px" style="background-color:White;border-width:0px;margin-top:0px;">
					    <tr>
					        <td align="left">
					            <input type="button" onclick="openAddHoliday1()" value="Add New Date"/>
					            <input id="refreshtab1" type="button" onclick="reloadData1()" style="display:none;" value=""/>
					        </td>
					    </tr>
					    <tr>
					        <td style="color:red;"><s:property value="errMsg"/></td>
					    </tr>
					</table>
					</s:if>
					
					<table cellspacing="1" border="0" style="background-color:White;border-width:0px;margin-top:10px;">
					    <tr class="table_title" align="center">
					        <th scope="col" width="50px">No.</th>
					        <th scope="col" width="100">Date</th>
					        <th scope="col" width="100">Start Time</th>
					        <th scope="col" width="100">Finish Time</th>
					        <s:if test="optDel==1">
					        <th scope="col" width="100px">-</th>
					        </s:if>
					    </tr>
					</table>
<s:if test="optDel==1">
					<div id="mp0010InfoListDiv" style="height:600px;width:480px;border:1px solid white;overflow:auto;">
</s:if>
<s:else>
					<div id="mp0010InfoListDiv" style="height:600px;width:380px;border:1px solid white;overflow:auto;">
</s:else>
					<table cellspacing="1" border="0" style="background-color:White;border-width:0px;">
					<s:iterator id="mp0010InfoList" value="mp0010InfoList" status="st">
					   <tr class="row">
					       <td width="50px" align="center">${st.index + 1}</td>
					       <td width="100px" align="left"><s:property value="MP0010_DATETIME"/></td>
					       <td width="100px" align="left"><s:property value="MP0010_START_TIME"/></td>
					       <td width="100px" align="left"><s:property value="MP0010_END_TIME"/></td>
					       <s:if test="optDel==1">
					       <td width="100px" align="center"><input type="button" onclick="Del1('<s:property value="MP0010_DATETIME"/>')" name="delBtn" value="Delete" id="delBtn"/></td>
					       </s:if>
					   </tr>
					</s:iterator>
					</table>
					</div>
                </DIV>
                <!--请购物品一览-->
                <DIV id='tabContent__1' style='display:none'>
					<s:if test="optAdd == 1">
					<table cellspacing="1" border="0" width="1020px" style="background-color:White;border-width:0px;margin-top:0px;">
					    <tr>
					        <td align="left">
					            <input type="button" onclick="openAddHoliday2()" value="Add New Public Holiday"/>
					            <input id="refreshtab2" type="button" onclick="reloadData2()" style="display:none;" value=""/>
					        </td>
					    </tr>
					    <tr>
					        <td style="color:red;"><s:property value="errMsg"/></td>
					    </tr>
					</table>
					</s:if>
					<table cellspacing="1" border="0" style="background-color:White;border-width:0px;margin-top:10px;">
					    <tr class="table_title" align="center">
					        <th scope="col" width="50px">No.</th>
					        <th scope="col" width="100">Date</th>
					        <th scope="col" width="300">Holiday Name</th>
                            <s:if test="optDel==1">
					        <th scope="col" width="100px">-</th>
                            </s:if>
					    </tr>
					</table>
<s:if test="optDel==1">
					<div id="holidayInfoListDiv" style="height:600px;width:580px;border:1px solid white;overflow:auto;">
</s:if>
<s:else>
					<div id="holidayInfoListDiv" style="height:600px;width:480px;border:1px solid white;overflow:auto;">
</s:else>
					<table cellspacing="1" border="0" style="background-color:White;border-width:0px;">
					<s:iterator id="holidayInfoList" value="holidayInfoList" status="st">
					   <tr class="row">
					       <td width="50px" align="center">${st.index + 1}</td>
					       <td width="100px" align="left"><s:property value="HOLIDAY_DATE"/></td>
					       <td width="300px" align="left"><s:property value="HOLIDAY_NAME"/></td>
					       <s:if test="optDel==1">
					       <td width="100px" align="center"><input type="button" onclick="Del2('<s:property value="HOLIDAY_SEQ"/>')" name="delBtn" value="Delete" id="delBtn"/></td>
					       </s:if>
					   </tr>
					</s:iterator>
					</table>
					</div>
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