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
<script src="js/checkform.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
function addRoom(){
	var popstyle="dialogTop:300px;dialogLeft:600px;dialogRight:600px;help:no;center:yes;dialogHeight:350px;dialogWidth:600px;status:no;resizable:no;scroll:no";
	window.showModalDialog("roomAddInit.action?pageType=add", window, popstyle);
}

</script>
</head>
<body>
<form id="form2" name="form2" action="roomSearch.action" method="post" enctype="multipart/form-data">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
        <td>
            <!-- 头部菜单 Start -->
            <table border='0' cellpadding='0' cellspacing='0' width='100%' align='center'>
                <tr>
                    <td class='menubar_title'><img border='0' src='images/icon/config.gif' align='absmiddle' hspace='3' vspace='3'>&nbsp;Room Manager</td>
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
                    <td class="table_body table_body_NoWidth">Room Name</td>
                    <td class="table_none table_none_NoWidth">
                        <input id="roomName" name="roomName" value="${roomName}" type="text" class="text_input" />
                    </td>
                    <td class="table_body table_body_NoWidth">Floor</td>
                    <td class="table_none table_none_NoWidth">
                        <s:select id="floor" name="floor" list="floorList" theme="simple"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="right">
                    	<input type="button" id="AddRoom" name="AddRoom" value="add New Room" onclick="addRoom()" />
                        <input type="submit" name="searchBtn" value="Search" id="searchBtn" class="" />
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
                    <th scope="col" width="200px">Room Floor</th>
                    <th scope="col" width="200px">Room Type</th>
                    <th scope="col" width="200px">Room Description</th>
                </tr>
            </table>
<div id="allRoomsList" style="overflow:auto;border:solid 0px red;height:600px;">
            <table cellspacing="1" border="0" style="background-color:White;border-width:0px; height:22px;">
<s:iterator value="allRoomsList" status="st">
                <tr class="row" align="center" style="height:28px;">
                    <td width="200px"><s:property value="JE0201_ROOM_NAME"></s:property></td>
                    <td width="200px"><s:property value="JE0201_ROOM_FLOOR"/></td>
                    <td width="200px"><s:property value="JE0201_ROOM_TYPE"/></td>
                    <td width="200px"><s:property value="JE0201_ROOM_DES"/></td>
                </tr>
</s:iterator>
            </table>
</div>
        </td>
    </tr>
    <!-- 内容部分End -->
    
    <tr><td height="5"></td></tr>
</table>

</form>
</body>
</html>