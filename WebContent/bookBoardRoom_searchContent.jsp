<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


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
