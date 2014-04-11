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
<script type="text/javascript" src="js/jquery.pager.js"></script>

<style type="text/css">
#pager ul.pages {display:block;border:none;text-transform:uppercase;font-size:10px;margin:1px 0 10px;padding:0;}
#pager ul.pages li {list-style:none;float:left;border:1px solid #65AB31;text-decoration:none;margin:0 5px 0 0;padding:5px;}
#pager ul.pages li:hover {border:1px solid #003f7e;}
#pager ul.pages li.pgEmpty {border:1px solid #000000;color:#000000;background-color:grey;}
#pager ul.pages li.pgCurrent {border:1px solid #003f7e;color:#000;font-weight:700;background-color:#65AB31;}
.tdBg{background:url(css/table/blue/images/header_bg.gif) #2f589c repeat-x 0px 0px;color:white;text-align:center;margin:0px;padding:0px;}
</style>

<script type="text/javascript">
$(document).ready(function() {
	$("#pager").pager({ pagenumber: 1, pagecount: $("#pageCount").val(), buttonClickCallback: PageClick });
});

PageClick = function(pageclickednumber) {
    $("#pager").pager({ pagenumber: pageclickednumber, pagecount: $("#pageCount").val(), buttonClickCallback: PageClick });
    
	document.getElementById("currentPageNum").value = pageclickednumber;

	searchBookedRoom();
};
</script>

<script type="text/javascript">
function bookOneRoom(){
	var popstyle="dialogTop:300px;dialogLeft:600px;dialogRight:600px;help:no;center:yes;dialogHeight:450px;dialogWidth:600px;status:no;resizable:no;scroll:no";
	window.showModalDialog("bookOneRoomInit.action?pageType=add", window, popstyle);
}

function delRecord(seq){
	$.ajax({
		url:"delBookedRoomRecord.action?bookedRoomSeq=" + seq,
		type:"post",
		dataType:"text"
	});
	searchBookedRoom();
}
function searchBookedRoom(){
	var date = $("#strWhichDay").val();
	var fromTime = $("#strFromTime").val();
	var toTime = $("#strToTime").val();
	var strRoomCode = $("#strRoomCode").val();
	var floor = $("#floor").val();
	var currentPageNum = $("#currentPageNum").val();
	
	if(null == date || "" == date){
		date = "";
		if("---Please Select---" != fromTime || toTime != "---Please Select---"){
			alert("You need select date first !");
			return ;
		}
	}
	if("---Please Select---" == fromTime){
		//alert("Please select from time and to time!");
		fromTime = "";
	}
	if(toTime == "---Please Select---"){
		toTime = "";
	}
	if(floor == "---Please Select---"){
		floor = "";
	}
	if(strRoomCode == null || strRoomCode == "-1"){
		strRoomCode = "";
	}

	var param = {"strWhichDay":date,
			"strFromTime":fromTime,
			"strToTime":toTime,
			"strRoomCode":strRoomCode,
			"floor":floor,
			"currentPageNum":currentPageNum
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
	//$.ajax(options);
	$("#allbookedRoomRecords").load("searchBookedRoom.action", param);
}
function pageRefresh(){
	window.parent.frames['mainFrame'].location="bookBoardroomMngInit.action";
}
</script>
</head>
<body>
<s:form id="form2" name="form2" enctype="multipart/form-data">
<input id="pageCount" name="pageCount" value="${pb.totalPage}" type="hidden"></input>
<input id="currentPageNum" name="currentPageNum" value="${currentPageNum}" type="hidden"></input>

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
                        <!-- <input id="roomName" name="roomName" value="${roomName}" type="text" class="text_input" /> -->
                        <s:select id="strRoomCode" name="strRoomCode" list="listAllRoomNames" headerKey="-1" headerValue="---Please Select---" theme="simple"/>
                    </td>
                    <td class="table_body table_body_NoWidth">Floor</td>
                    <td class="table_none table_none_NoWidth">
                        <!-- <input id="floor" name="floor" value="${floor}" type="text" class="text_input" /> -->
                        <s:select id="floor" name="floor" list="floorList" theme="simple"/>
                    </td>
                    <td></td>
                    <td>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="right">
                    	<input type="button" id="bookRoom" name="bookRoom" value="Book Boardroom" onclick="bookOneRoom()" style="float:left;"/>
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
                    <th scope="col" width="200px">From Time</th>
                    <th scope="col" width="200px">To Time</th>
                    <th scope="col" width="200px">Subscriber</th>
                    <th scope="col" width="200px">Department</th>
                    <th scope="col" width="200px">Room Floor</th>
                    <th scope="col" width="200px">Operation</th>
                </tr>
            </table>
			<div id="allbookedRoomRecords" style="overflow:auto;border:solid 0px red;">
	            <table cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
					<s:iterator value="listBookedRoomRecords" status="st">
		                <tr class="row" align="center" style="height:28px;">
		                    <td width="200px"><s:property value="JE0202_ROOM_NAME" /></td>
		                    <td width="200px"><s:property value="JE0202_FROM_DATETIME.substring(0, 16)"/></td>
		                    <td width="200px"><s:property value="JE0202_END_DATETIME.substring(0, 16)"/></td>
		                    <td width="200px"><s:property value="SubscriberInfo.MP1001_PREFERED_NAME" />&nbsp;(<s:property value="JE0202_USER_NUM"/>)</td>
		                    <td width="200px"><s:property value="SubscriberDepartmentInfo.MP0002_DEPARTMENT_NAME" /></td>
		                    <td width="200px"><s:property value="JE0202_ROOM_FLOOR"/></td>
		               		<td width="200px"><s:if test="mp1001.MP1001_EMPLOYEE_NUM == JE0202_USER_NUM"><input type="button" value="Del" onclick="delRecord('<s:property value="JE0202_SEQ" />')"/></s:if></td>
		                </tr>
					</s:iterator>
	            </table>
			</div>
        </td>
    </tr>
    <!-- 内容部分End -->    
    <tr>
    	<td height="5"><div id="pager" style="border: 0px solid #000000;height:30px;width:1300px;margin-top:10px;"></div></td>
     </tr>
</table>
</s:form>
</body>
</html>