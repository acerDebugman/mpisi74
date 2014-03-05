<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base target="_self"/>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<title>Add Room</title>
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />
<script src="js/jquery1.4.2.js" type="text/javascript" ></script>

<script type="text/javascript">
var closeWinFunc = window.close;
window.close = function() {
    window.open("", "_self");
    closeWinFunc();
};
function roomTypeChange(){
	var _type = document.getElementById("leaveType").value;
	
	if(_type == '4'){
		document.getElementById("majorId").style.display = "block";
	}else{
		document.getElementById("majorId").style.display = "none";
	}
	
	if(_type != '0'){
		document.getElementById("mp2007.MP2007_DAYS_EXT_D").disabled = false;
		document.getElementById("mp2007.MP2007_DAYS_EXT_H").disabled = false;
		document.getElementById("mp2007.MP2007_DAYS_EXT_D").value = '';
		document.getElementById("mp2007.MP2007_DAYS_EXT_H").value = '';
		document.getElementById("mp2007.MP2007_MAJOR_NAME").value ='';
		
	}else{
		document.getElementById("mp2007.MP2007_DAYS_EXT_D").disabled = true;
		document.getElementById("mp2007.MP2007_DAYS_EXT_H").disabled = true;
		document.getElementById("mp2007.MP2007_DAYS_EXT_D").value = '';
		document.getElementById("mp2007.MP2007_DAYS_EXT_H").value ='';
		document.getElementById("mp2007.MP2007_MAJOR_NAME").value ='';
		document.getElementById("submit").disabled = true;
	}
}
$(document).mousemove(function(e){
});

function addRoom(){
	var roomName = $("#roomName").val();
	var floor = $("#floor").val();
	var roomType = $("#roomType").val();
	var roomDes = $("#roomDes").val();
	
	if("---Please Select---" == floor){
		alert("Please select which floor!");
		return ;
	}
	if("---Please Select---" == roomType){
		alert("Please room type !");
		return ;
	}
	
	var param = {"roomName":roomName,
			"floor":floor,
			"roomType":roomType,
			"roomDes":roomDes
			};
	
	var options = {
			url:"roomAdd.action",
			data:param,
			type:"post",
			dataType:"text",
			success:function(msg){
				alert(msg);
				window.close();
			}
	};
	
	$.ajax(options);
}

</script>
</head>
<body>
<form id="form_0" name="form_0" action="roomAdd.action" method="post">

<!-- 头部菜单 Start -->
<table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
  <tr>
    <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Add Room</td>
    <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;help？</td>
  </tr>
  <tr>
    <td height='27px' class='menubar_function_text'>Operation Function: Add Room</td>
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

<table width="100%" border="0" cellspacing="1" cellpadding="3" align="left">
	<tr>
		<td class="table_body table_body_NoWidth">Room Name:&nbsp;<span style="color:red;">*</span></td>
		<td class="table_none table_none_NoWidth"><input id="roomName" name="roomName" type="text" class="text_input"/></td>
	</tr>
	<tr>
		<td class="table_body table_body_NoWidth">Room Floor:&nbsp;<span style="color:red;">*</span></td>
		<td class="table_none table_none_NoWidth"><s:select id="floor" name="floor" list="floorList" theme="simple" /></td>
	</tr>
	<tr>
		<td class="table_body table_body_NoWidth">Type:<span class="errorcss">*</span></td>
		<td class="table_none table_none_NoWidth"><s:select id="roomType" name="roomType" list="roomTypeList" theme="simple" /></td>
	</tr>
	<tr>
		<td class="table_body table_body_NoWidth">Room Description</td>
		<td class="table_none table_none_NoWidth">
			<textarea id="roomDes" name="roomDes" type="text" style="height:50px;width:300px;"></textarea>
		</td>
	</tr>
	<tr>
	    <td colspan="2">
	        <div style="width:100%;color:red;border:solid 0px red;font-weight:bold;" align="left"></div>
	    </td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="1" cellpadding="3" align="center" style="padding-bottom:20px;">
    <tr>
        <td align="center" colspan="2">
            <input id="submit" type="button" value="Save" onclick="addRoom()" />
            <input type="button" onclick="window.close()" value="Close"/>
        </td>
    </tr>
</table>

</form>
</body>
</html>
