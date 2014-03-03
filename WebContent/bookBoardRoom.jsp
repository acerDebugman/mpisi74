<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="images/other/icon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/Site_Css.css" rel="stylesheet" type="text/css" />
<link href="css/table/blue/css.css" rel="stylesheet" type="text/css" />

<script src="js/jquery1.4.2.js" type="text/javascript" ></script>
<script src="js/calendar.js" type="text/javascript" ></script>
<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery.form.js" type="text/javascript" ></script>
<script type="text/javascript" src="js/jquery.pager.js"></script>

<style type="text/css">
#pager ul.pages {
	display: block;
	border: none;
	text-transform: uppercase;
	font-size: 10px;
	margin: 1px 0 10px;
	padding: 0;
}

#pager ul.pages li {
	list-style: none;
	float: left;
	border: 1px solid #65AB31;
	text-decoration: none;
	margin: 0 5px 0 0;
	padding: 5px;
}

#pager ul.pages li:hover {
	border: 1px solid #003f7e;
}

#pager ul.pages li.pgEmpty {
	border: 1px solid #000000;
	color: #000000;
	background-color: grey;
}

#pager ul.pages li.pgCurrent {
	border: 1px solid #003f7e;
	color: #000;
	font-weight: 700;
	background-color: #65AB31;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
    $("#pager").pager({ pagenumber: 1, pagecount: $("#pageCount").val(), buttonClickCallback: PageClick });
});

PageClick = function(pageclickednumber) {
	
    $("#pager").pager({ pagenumber: pageclickednumber, pagecount: $("#pageCount").val(), buttonClickCallback: PageClick });
	var _enum = $("#employeeNum").val();
	var _ename = $("#employeeName").val();
	var _dept = $("#departmentID").val();
	var _status =$("#attendenceStatus").val();
	var _type = $("#leaveType").val();
	document.getElementById("pageNum").value = pageclickednumber;
    var param = {"pageNum" : pageclickednumber,"employeeNum" : _enum,"employeeName" : _ename,"leaveType" : _type,"departmentID" : _dept,"attendenceStatus":_status};
    $("#leaveInfoDiv").load("leaveInfoRefresh.action",param);
};
</script>

<script type="text/javascript">
function bookOneRoom(){
	var popstyle="dialogTop:300px;dialogLeft:600px;dialogRight:600px;help:no;center:yes;dialogHeight:450px;dialogWidth:600px;status:no;resizable:no;scroll:no";
	window.showModalDialog("bookOneRoomInit.action?pageType=add", window, popstyle);
}

function searchBookedRoom(){
	var date = $("#strWhichDay").val();
	var fromTime = $("#strFromTime").val();
	var toTime = $("#strToTime").val();
	var roomName = $("#roomName").val();
	var floor = $("#floor").val();
	
	if(null == date || "" == date){
		alert("Please select date first!");
		return ;
	}
	if(fromTime == "---Please Select---" ){
		//alert("Please select from time and to time!");
		fromtime = "-1";
	}
	if(toTime == "---Please Select---"){
		toTime = "-1";
	}
	
	var param = {"strWhichDay":date, 
			"strFromTime":fromTime,
			"strToTime":toTime,
			"roomName":roomName,
			"floor":floor
			};
	var options = {
			url:"searchBookedRoom.action",
			data:param,
			type:"post",
			dataType:"html",
			success:function(msg){

			}
	};
	//$("#form2").ajaxSubmit(options);
	$.ajax(options);
}
</script>
</head>
<body>
<form id="form2" name="form2" enctype="multipart/form-data">
<input id="pageCount" name="pageCount" value="" type="hidden" />

<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
        <td>
            <!-- 头部菜单 Start -->
            <table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
                <tr>
                    <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Book Boardroom</td>
                    <td class='menubar_readme_text' valign='bottom'><img src='images/icon/office.gif' align='absMiddle' border='0' />&nbsp;Help？</td>
                </tr>
                <tr>
                    <td height='27px' class='menubar_function_text' style="width:35%">Operation Function：Rooms Manger</td> 
                    <td class='menubar_menu_td' align='left'></td>
                </tr>
                <tr><td height='5px' colspan='2'></td></tr>
            </table>
            <!-- 头部菜单 End -->
        </td>
    </tr>
    <!-- 检索条件Start -->
    <tr>
        <td>
            <fieldset style="border-color:#999999;border-width:1px;border-style:Solid;margin-bottom:10px;">
            <legend style="color:#333333;font-size:1.5em;font-weight:bold;">Query Condition</legend>
            <table width="100%" border="0" cellspacing="1" cellpadding="3" align="center">
            	<tr>
                    <td class="table_body table_body_NoWidth">Date</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="strWhichDay" name="strWhichDay" value="${strWhichDay}" type="text" class="text_input" onfocus="calendar(this);"/>
                    </td>
                    <td class="table_none table_none_NoWidth">
                    </td>
                    <td class="table_none table_none_NoWidth">
                    </td>
                </tr>
                <tr>
                    <td class="table_body table_body_NoWidth">From Time</td>
                    <td class="table_none table_none_NoWidth">
                    	<s:select id="strFromTime" name="strFromTime" list="listTimeList" theme="simple"/>
                    </td>
                    <td class="table_body table_body_NoWidth">To Time</td>
                    <td class="table_none table_none_NoWidth">
                    	<s:select id="strToTime" name="strToTime" list="listTimeList" theme="simple"/>
                    </td>
                </tr>
                <tr>
                    <td class="table_body table_body_NoWidth">Room Name</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="roomName" name="roomName" value="${roomName}" type="text" class="text_input" />
                    </td>
                    <td class="table_body table_body_NoWidth">Floor</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="floor" name="floor" value="${floor}" type="text" class="text_input" />
                    </td>
                    <td></td>
                    <td>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="right">
                    	<input type="button" id="bookRoom" name="bookRoom" value="Book Boardroom" onclick="bookOneRoom()" />
                        <input type="button" name="searchBtn" value="Search" id="searchBtn" class="" onclick="searchBookedRoom()"/>
                    </td>
                </tr>
            </table>
            </fieldset>
        </td>
    </tr>
    <!-- 检索条件End -->


    <!-- 内容部分Start -->
    <tr>
        <td>
            <table cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
                <tr class="table_title" align="center">
                    <th scope="col" width="200px">Room Name</th>
                    <th scope="col" width="200px">Date</th>
                    <th scope="col" width="200px">From Time</th>
                    <th scope="col" width="200px">To Time</th>
                    <th scope="col" width="200px">Subscriber</th>
                    <th scope="col" width="200px">Room Floor</th>
                </tr>
            </table>
			<div id="allbookedRoomRecords" style="overflow:auto;border:solid 0px red;height:600px;">
	            <table cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
					<s:iterator value="listBookedRoomRecords" status="st">
					                <tr class="row" align="center" style="height:28px;">
					                    <td width="200px"><s:property value="mapRoomCodeObj[JE0202_ROOM_CODE].JE0201_ROOM_NAME" /></td>
					                    <td width="200px"><s:property value="JE0202_DATE.substring(0, 10)"/></td>
					                    <td width="200px"><s:property value="JE0202_FROM_DATETIME.substring(0, 16)"/></td>
					                    <td width="200px"><s:property value="JE0202_END_DATETIME.substring(0, 16)"/></td>
					                    <td width="200px"><s:property value="JE0202_USER_NUM"/></td>
					                    <td width="200px"><s:property value="mapRoomCodeObj[JE0202_ROOM_CODE].JE0201_ROOM_FLOOR"/></td>
					                </tr>
					</s:iterator>
	            </table>
			</div>
        </td>
    </tr>
    <!-- 内容部分End -->
    
    <tr><td height="5"></td></tr>
</table>

			<table cellspacing="1" border="0" style="background-color:White;border-width:0px;" align="center">
                <tr class="">
                    <td>
                        <div id="pager" style="border: 1px solid #FFFFFF;"></div>
                    </td>
                </tr>
            </table>
</form>
<s:debug />
</body>
</html>